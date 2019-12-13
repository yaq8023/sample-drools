package com.fintech.modules.drools.example.address.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Description
 * <br>  定义地址实体类
 *
 * @author chendongdong
 * @date 2019/12/09
 **/
@Data
@ApiModel(value = "地址")
public class Address {

    /**
     * 邮编
     */
    @NotBlank(message = "邮政编码postCode不能为空")
    @ApiModelProperty(value = "邮政编码")
    private String postCode;
}
