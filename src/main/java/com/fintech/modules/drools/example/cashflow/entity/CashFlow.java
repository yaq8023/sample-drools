package com.fintech.modules.drools.example.cashflow.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Description
 * <br>  定义现金流实体类
 *
 * @author chendongdong
 * @date 2019/12/09
 **/
@Data
@ApiModel(value = "现金流")
public class CashFlow {

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private Date date;
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private double amount;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private CashFlowType type;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private long accountNo;


    public Date getDate() {
        if (date == null) {
            return null;
        }
        return (Date) date.clone();
    }

    public void setDate(Date date) {
        if (date != null) {
            this.date = (Date) date.clone();
        } else {
            this.date = null;
        }
    }

    public CashFlow(Date date, double amount, CashFlowType type, long accountNo) {
        this.date = date != null ? (Date) date.clone() : null;
        this.amount = amount;
        this.type = type;
        this.accountNo = accountNo;
    }
}
