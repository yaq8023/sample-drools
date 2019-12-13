package com.fintech.modules.drools.example.decisiontable.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Description
 * <br>  定义保险单实体类
 *
 * @author chendongdong
 * @date 2019/12/12
 **/
@Data
@ApiModel("保险单")
public class Policy implements Serializable {

    private static final long serialVersionUID = 6256185649510993239L;

    @ApiModelProperty("费用类型")
    private String type;

    @ApiModelProperty("基价")
    private int basePrice;

    @ApiModelProperty("折扣率")
    private int discountPercent;

    @ApiModelProperty("是否被批准")
    private Boolean approved;


    public Policy(String type, int basePrice, int discountPercent, Boolean approved) {
        this.type = type;
        this.basePrice = basePrice;
        this.discountPercent = discountPercent;
        this.approved = approved;
    }

    public void applyDiscount(int discount) {
        this.discountPercent += discount;
    }

    public Policy() {
    }
}
