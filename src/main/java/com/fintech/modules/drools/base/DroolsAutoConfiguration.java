package com.fintech.modules.drools.base;


import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

/**
 * Description
 * <br>  定义drools自动配置加载类
 *
 * @author chendongdong
 * @date 2019/12/09
 **/
@Slf4j
public class DroolsAutoConfiguration {


    /**
     * Description
     * <br>  创建StatelessKieSession
     * <br>  使用KieServices创建新的KieContainers
     * <br>  KieContainer对所有的业务资产(规则、流程、电子表格、PMML文档等)都有引用，当我们创建新的规则引擎实例时，这些资产将被加载
     *
     * @param kSessionName
     * @date 2019/12/09
     * @author chendongdong
     **/
    public static StatelessKieSession createStatelessKieSession(String kSessionName) {
        KieServices kieServices = getKieServices();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //verify()方法将允许我们确保我们的业务资产是正确的
        Results results = kieContainer.verify();
        results.getMessages().stream().forEach(message ->
                log.info(">> Message ( " + message.getLevel() + " ): " + message.getText())
        );
        //与KIESession相反，StatelessKIESession隔离了每次与规则引擎的交互，不会维护会话的状态。
        return kieContainer.newStatelessKieSession(kSessionName);
    }

    private static KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    private DroolsAutoConfiguration() {
    }
}
