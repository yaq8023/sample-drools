package com.fintech.modules.drools.bpmn.leave.service;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * Description
 * <br> 定义 请假流程 业务实现服务类
 *
 * @author chendongdong
 * @date 2019/12/11
 **/
@Slf4j
public class LeaveService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        log.info("reason:{}", execution.getVariable("reason"));
    }
}
