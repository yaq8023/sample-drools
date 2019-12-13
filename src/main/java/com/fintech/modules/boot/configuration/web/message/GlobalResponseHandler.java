package com.fintech.modules.boot.configuration.web.message;

import com.alibaba.fastjson.JSONException;
import com.fintech.modules.boot.configuration.web.enums.GlobalResponseStatusEnum;
import com.fintech.modules.boot.exception.DroolsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


/**
 * Description
 * 定义统一处理handler
 * <br> @ControllerAdvice+@ExceptionHandler配合使用
 * <br> 首先，我们通过@ControllerAdvice来定义一个controller增强处理器，可以通过配合使用@ExceptionHandler来进行异常的统一处理。
 * <br> 其次，通过实现ResponseBodyAdvice，对于数据的返回，进行进一步的处理，使得接口的返回值都是统一的对象。
 * <br> Spring Boot Controller 统一返回格式: https://www.jianshu.com/p/b4d67167e43c
 *
 * @author chendongdong
 * @date 2019/12/10
 **/
@Slf4j
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private static final String DEFAULT_RETURN_TYPE_NAME = "org.springframework.http.ResponseEntity";


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !DEFAULT_RETURN_TYPE_NAME.equals(returnType.getParameterType().getName());
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String methodName = returnType.getMethod().getName();
        log.debug("执行{}方法，返回值:{}", methodName, body);
        if (body instanceof GlobalResponse) {
            return body;
        }
        return new GlobalResponse(body);
    }


    @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<ResponseMsg<T>> handleGlobalException(Exception ex) {
        log.info("全局统一异常拦截到运行时异常信息: {}", ex.getMessage(), ex);
        ResponseMsg responseMsg = new ResponseMsg<T>();
        responseMsg.setRetDesc(GlobalResponseStatusEnum.ERROR.getName());
        responseMsg.setRetCode(GlobalResponseStatusEnum.ERROR.getCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    @ExceptionHandler(JSONException.class)
    public <T> ResponseEntity<ResponseMsg<T>> handleJsonException(JSONException ex) {
        StringBuilder errorMesssage =
                new StringBuilder(GlobalResponseStatusEnum.INVALID_PARAM_DATA_BIND.getName())
                        .append(": [").append(ex.getMessage()).append("]");

        log.info("请求参数转换异常, 请核实请求参数格式: [{}]", ex.getMessage());
        ResponseMsg responseMsg = new ResponseMsg<T>();
        responseMsg.setRetDesc(errorMesssage.toString());
        responseMsg.setRetCode(GlobalResponseStatusEnum.INVALID_PARAM_DATA_BIND.getCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> ResponseEntity<ResponseMsg<T>> handleInvalidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        StringBuilder errorMesssage = new StringBuilder(GlobalResponseStatusEnum.INVALID_PARAM.getName()).append(": [");

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage.append("{").append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("}; ");
        }
        errorMesssage.append("]");
        log.info("请求参数校验异常: [{}]", errorMesssage);
        ResponseMsg responseMsg = new ResponseMsg<T>();
        responseMsg.setRetDesc(errorMesssage.toString());
        responseMsg.setRetCode(GlobalResponseStatusEnum.INVALID_PARAM.getCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public <T> ResponseEntity<ResponseMsg<T>> handleValidationException(ConstraintViolationException ex) {
        StringBuilder errorMesssage = new StringBuilder(GlobalResponseStatusEnum.INVALID_PARAM.getName()).append(": [");

        for (ConstraintViolation<?> s : ex.getConstraintViolations()) {
            errorMesssage.append("{").append(s.getMessage()).append("}; ");
        }
        errorMesssage.append("]");
        log.info("请求参数转换异常, 请核实请求参数格式: [{}]", ex.getMessage());
        ResponseMsg responseMsg = new ResponseMsg<T>();
        responseMsg.setRetDesc(errorMesssage.toString());
        responseMsg.setRetCode(GlobalResponseStatusEnum.INVALID_PARAM.getCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }


    @ExceptionHandler(DroolsException.class)
    public <T> ResponseEntity<ResponseMsg<T>> handleAntfException(DroolsException ex) {
        ResponseMsg responseMsg = new ResponseMsg<T>(GlobalResponseStatusEnum.FAILURE.getCode(), ex.getMessage());
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

}