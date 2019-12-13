package com.fintech.modules.drools.example.address.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description
 * <br>  定义地址校验实体类
 *
 * @author chendongdong
 * @date 2019/12/09
 **/
@Data
@ApiModel(value = "地址校验")
public class AddressCheckResult {

    /**
     * 邮编校验结果
     * true:通过校验；false：未通过校验
     */
    @ApiModelProperty(value = "邮编校验结果")
    private boolean postCodeResult = false;
}
