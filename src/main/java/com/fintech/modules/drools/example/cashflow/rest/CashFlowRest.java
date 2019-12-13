package com.fintech.modules.drools.example.cashflow.rest;


import com.fintech.modules.boot.util.DateUtil;
import com.fintech.modules.drools.base.DroolsInvokeService;
import com.fintech.modules.drools.example.cashflow.entity.Account;
import com.fintech.modules.drools.example.cashflow.entity.AccountPeriod;
import com.fintech.modules.drools.example.cashflow.entity.CashFlow;
import com.fintech.modules.drools.example.cashflow.entity.CashFlowType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * <br>   定义 现金流转流程控制 Rest服务类
 *
 * @author chendongdong
 * @date 2019/12/10
 **/
@Slf4j
@Api(tags = {"drools-现金流转流程控制"})
@RestController
@RequestMapping("/api/cashFlow")
public class CashFlowRest {


    @Autowired
    private DroolsInvokeService droolsInvokeService;


    /**
     * Description
     * <br>  测试现金流转
     *
     * @date 2019/12/10
     * @author chendongdong
     **/
    @ApiOperation(value = "测试现金流转", notes = "CashFlowKS")
    @GetMapping(value = "/testCashFlowKS/v1")
    public void testCashFlowKS() {
        //定义一个事实对象集合
        List<Object> factObjList = new ArrayList<>(16);
        AccountPeriod accountPeriod = new AccountPeriod(DateUtil.fromStingToDate("2019-01-12"), DateUtil.fromStingToDate("2019-12-31"));
        Account account = new Account(1, 0);
        CashFlow cf1 = new CashFlow(DateUtil.fromStingToDate("2019-01-12"), 100, CashFlowType.CREDIT, 1);
        CashFlow cf2 = new CashFlow(DateUtil.fromStingToDate("2019-02-02"), 200, CashFlowType.DEBIT, 1);
        CashFlow cf3 = new CashFlow(DateUtil.fromStingToDate("2019-05-18"), 50, CashFlowType.CREDIT, 1);
        CashFlow cf4 = new CashFlow(DateUtil.fromStingToDate("2019-09-07"), 75, CashFlowType.CREDIT, 1);
        factObjList.add(accountPeriod);
        factObjList.add(account);
        factObjList.add(cf1);
        factObjList.add(cf2);
        factObjList.add(cf3);
        factObjList.add(cf4);
        droolsInvokeService.executeStatelessKSRule("CashFlowKS", factObjList);
    }


}
