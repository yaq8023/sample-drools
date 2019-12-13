package com.fintech.modules.drools.example.decisiontable.rest;


import com.fintech.modules.drools.base.DroolsInvokeService;
import com.fintech.modules.drools.example.decisiontable.entity.Driver;
import com.fintech.modules.drools.example.decisiontable.entity.Policy;
import com.fintech.modules.drools.example.decisiontable.vo.DriverReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * <br>   定义 决策表测试 Rest服务类
 *
 * @author chendongdong
 * @date 2019/12/09
 **/
@Slf4j
@Api(tags = {"drools-决策表测试"})
@RestController
@RequestMapping("/api/policyPricing")
public class PolicyPricingRest {

    @Autowired
    private DroolsInvokeService droolsInvokeService;

    /**
     * Description
     * <br>  测试决策表(电子表格)
     *
     * @param driverReqVO 定义驾驶员申请VO类
     * @date 2019/12/09
     * @author chendongdong
     **/
    @ApiOperation(value = "测试决策表(电子表格)", notes = "测试决策表(电子表格)")
    @PostMapping(value = "/testDecisionTable/v1")
    public Policy testDecisionTable(@Validated @RequestBody DriverReqVO driverReqVO) {
        //定义一个事实对象集合
        List<Object> factObjList = new ArrayList<>(1);
        Driver driver = new Driver();
        BeanUtils.copyProperties(driverReqVO, driver);
        factObjList.add(driver);
        Policy policy = new Policy();
        policy.setApproved(false);
        policy.setType(driverReqVO.getInsuranceType());
        policy.setDiscountPercent(0);
        factObjList.add(policy);
        //执行无状态session规则
        boolean success = droolsInvokeService.executeStatelessKSRule("DecisionTableKS", factObjList);
        if (success) {
            log.info("BASE PRICE IS:{},DISCOUNT IS:{}", policy.getBasePrice(), policy.getDiscountPercent());
        }
        return policy;
    }
}
