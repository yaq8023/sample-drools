package com.fintech.modules.boot.configuration.web.message;

import com.fintech.modules.boot.configuration.web.enums.GlobalResponseStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description
 * <br>  定义通用的数据返回对象
 *
 * @author chendongdong
 * @date 2019/12/10
 **/
@Data
public class GlobalResponse<T> {
    private static final long serialVersionUID = 1L;

    private String retCode = GlobalResponseStatusEnum.SUCCESS.getCode();

    private String retDesc = GlobalResponseStatusEnum.SUCCESS.getName();

    @ApiModelProperty("responseBody,自定义返回对象")
    private T responseBody;

    public GlobalResponse(T responseBody) {
        this.responseBody = responseBody;
    }

    public GlobalResponse(String retCode, String retDesc) {
        this.retCode = retCode;
        this.retDesc = retDesc;
    }

    public GlobalResponse(String retCode, String retDesc, T responseBody) {
        this.retCode = retCode;
        this.retDesc = retDesc;
        this.responseBody = responseBody;
    }

    public static ResponseMsg getSuccessResponseMsg(String retDesc) {
        return new ResponseMsg(GlobalResponseStatusEnum.SUCCESS.getCode(), retDesc);
    }
}
