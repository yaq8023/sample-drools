package com.fintech.modules.drools.base.impl;

import com.fintech.modules.drools.base.DroolsAutoConfiguration;
import com.fintech.modules.drools.base.DroolsInvokeService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Description
 * <br>  定义drools规则调用公共接口实现类
 *
 * @author chendongdong
 * @date 2019/12/09
 **/
@Slf4j
@Service
public class DroolsInvokeServiceImpl implements DroolsInvokeService {

    /**
     * Description
     * <br>  执行无状态session规则
     *
     * @param kSessionName kSessionName
     * @param factObjList  事实对象列表
     * @return
     * @date 2019/12/09
     * @author chendongdong
     **/
    @Override
    public boolean executeStatelessKSRule(String kSessionName, List<Object> factObjList) {
        AtomicBoolean success = new AtomicBoolean(false);
        try {
            //创建StatelessKieSession
            StatelessKieSession statelessKieSession = DroolsAutoConfiguration.createStatelessKieSession(kSessionName);
            Optional.ofNullable(statelessKieSession).ifPresent(kSession -> {
                //设置事实对象集合，并执行相应规则
                kSession.execute(factObjList);
                success.set(true);
            });

        } catch (Exception e) {
            success.set(false);
            log.error("执行无状态session规则异常,kSessionName:{}", kSessionName, e);
        }
        return success.get();
    }

    /**
     * Description
     * <br>  执行无状态session规则(支持设置多个全局变量)
     *
     * @param kSessionName   kSessionName
     * @param factObjList    事实对象列表
     * @param globalVariable 全局变量
     * @return
     * @date 2019/12/09
     * @author chendongdong
     **/
    @Override
    public boolean executeStatelessKSRule(String kSessionName, List<Object> factObjList, Map<String, Object> globalVariable) {
        boolean feedBack = false;
        try {
            StatelessKieSession kSession = DroolsAutoConfiguration.createStatelessKieSession(kSessionName);
            //遍历全局变量，并加入KieSession中
            if (globalVariable != null && globalVariable.size() > 0) {
                globalVariable.entrySet().stream().forEach(entry ->
                        kSession.setGlobal(entry.getKey(), entry.getValue())
                );
            }
            //设置事实对象集合，并执行相应规则
            kSession.execute(factObjList);
            feedBack = true;
        } catch (Exception e) {
            feedBack = false;
            log.error("执行无状态session规则异常,kSessionName:{}", kSessionName, e);
        }
        return feedBack;
    }

}
