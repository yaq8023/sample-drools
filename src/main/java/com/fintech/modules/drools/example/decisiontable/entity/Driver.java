package com.fintech.modules.drools.example.decisiontable.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * Description
 * <br>  定义驾驶员实体类
 *
 * @author chendongdong
 * @date 2019/12/12
 **/
@Data
@ApiModel("驾驶员")
public class Driver implements Serializable {


    private static final long serialVersionUID = -5866085439205692627L;

    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    @NotNull(message = "年龄不能为空")
    @ApiModelProperty(value = "年龄", required = true)
    private Integer age;

    @NotNull(message = "优先要求权不能为空")
    @ApiModelProperty(value = "优先要求权", required = true)
    private Integer priorClaims;

    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty(value = "风险决策等级(LOW-低级,MED-中级,HIGH-高级)", required = true)
    private String locationRiskProfile;

    public Driver(String name, Integer age, Integer priorClaims, String locationRiskProfile) {
        this.name = name;
        this.age = age;
        this.priorClaims = priorClaims;
        this.locationRiskProfile = locationRiskProfile;
    }

    public Driver() {
    }
}
