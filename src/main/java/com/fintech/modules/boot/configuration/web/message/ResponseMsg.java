package com.fintech.modules.boot.configuration.web.message;

import com.fintech.modules.boot.configuration.web.enums.GlobalResponseStatusEnum;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description
 * <br>系统自定义controller返回包装类
 *
 * @author xujunqi
 * @date 2019/1/28
 **/
public class ResponseMsg<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String retCode = GlobalResponseStatusEnum.SUCCESS.getCode();

    private String retDesc = GlobalResponseStatusEnum.SUCCESS.getName();

    @ApiModelProperty("responseBody,自定义返回对象")
    private T responseBody;

    public ResponseMsg(T responseBody) {
        this.responseBody = responseBody;
    }

    public ResponseMsg(String retCode, String retDesc) {
        this.retCode = retCode;
        this.retDesc = retDesc;
    }

    public ResponseMsg(String retCode, String retDesc, T responseBody) {
        this.retCode = retCode;
        this.retDesc = retDesc;
        this.responseBody = responseBody;
    }

    public static ResponseMsg getSuccessResponseMsg(String retDesc) {
        return new ResponseMsg(GlobalResponseStatusEnum.SUCCESS.getCode(), retDesc);
    }

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetDesc() {
        return retDesc;
    }

    public void setRetDesc(String retDesc) {
        this.retDesc = retDesc;
    }

    public ResponseMsg() {

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[retCode=");
        builder.append(this.getRetCode());
        builder.append(", errorDesc=");
        builder.append(this.getRetDesc());
        builder.append("]");
        builder.append("   Body [");
        if (responseBody != null) {
            builder.append(responseBody.toString());
        }
        builder.append("]");
        return builder.toString();
    }
}
