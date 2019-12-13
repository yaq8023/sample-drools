package com.fintech.modules.drools.example.address.rest;


import com.fintech.modules.drools.base.DroolsInvokeService;
import com.fintech.modules.drools.constants.DroolsConstants;
import com.fintech.modules.drools.example.address.entity.Address;
import com.fintech.modules.drools.example.address.entity.AddressCheckResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
 * <br>   定义 地址校验 Rest服务类
 *
 * @author chendongdong
 * @date 2019/12/09
 **/
@Slf4j
@Api(tags = {"drools-地址校验"})
@RestController
@RequestMapping("/api/address")
public class AddressRest {

    @Autowired
    private DroolsInvokeService droolsInvokeService;

    /**
     * Description
     * <br>  邮编校验
     *
     * @param address 地址实体类对象
     * @date 2019/12/09
     * @author chendongdong
     **/
    @ApiOperation(value = "执行邮编校验规则", notes = "执行邮编校验规则")
    @PostMapping(value = "/checkPostCode/v1")
    public String checkPostCode(@Validated @RequestBody Address address) {
        String tipMsg = DroolsConstants.RULE_CHECK_FAILED;
        AddressCheckResult checkResult = new AddressCheckResult();
        try {
            //定义一个事实对象集合
            List<Object> factObjList = new ArrayList<>(1);
            factObjList.add(address);
            factObjList.add(checkResult);
            //执行无状态session规则
            boolean success = droolsInvokeService.executeStatelessKSRule("PostCodeCheckKS", factObjList);
            if (Boolean.TRUE.equals(success) && checkResult.isPostCodeResult()) {
                tipMsg = DroolsConstants.RULE_CHECK_PASS;
                log.info(tipMsg);
            }
        } catch (Exception e) {
            tipMsg = DroolsConstants.RULE_CHECK_ERROR;
            log.error("执行邮编校验异常", e);
        }
        return tipMsg;
    }
}
