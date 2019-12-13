package com.fintech.modules.drools.example.cashflow.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description
 * <br>  定义帐户实体类
 *
 * @author chendongdong
 * @date 2019/12/09
 **/
@Data
@ApiModel(value = "帐户")
public class Account {

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private long accountNo;

    /**
     * 余额
     */
    @ApiModelProperty(value = "余额")
    private double balance;


    public Account(long accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }
}
