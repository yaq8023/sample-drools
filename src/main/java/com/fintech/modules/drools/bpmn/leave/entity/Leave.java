package com.fintech.modules.drools.bpmn.leave.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description
 * <br>  定义请假实体类
 *
 * @author chendongdong
 * @date 2019/12/11
 **/
@Data
@ApiModel(value = "请假实体类")
public class Leave implements Serializable {


    private static final long serialVersionUID = 8362381052802328843L;

    @NotBlank(message = "申请人姓名不能为空")
    @ApiModelProperty(value = "申请人姓名")
    private String name;

    @NotNull(message = "申请请假天数不能为空")
    @ApiModelProperty(value = "申请请假天数")
    private int day;

    @ApiModelProperty(value = "实际请假天数")
    private int total;


    public Leave(String name, int day) {
        this.name = name;
        this.day = day;
    }
}
