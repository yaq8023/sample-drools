package com.fintech.modules.drools.example.decisiontable.vo;

import com.fintech.modules.drools.example.decisiontable.entity.Driver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * Description
 * <br>  定义驾驶员申请VO类
 *
 * @author chendongdong
 * @date 2019/12/12
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("驾驶员申请VO类")
public class DriverReqVO extends Driver {

    private static final long serialVersionUID = 1585372014312759519L;

    @NotBlank(message = "保险类型不能为空")
    @ApiModelProperty(value = "保险类型(COMPREHENSIVE-综合保险,FIRE_THEFT-火险及盗窃险,THIRD_PARTY-第三方责任险)", required = true)
    private String insuranceType;

}
