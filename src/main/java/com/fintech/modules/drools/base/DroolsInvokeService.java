package com.fintech.modules.drools.base;

import java.util.List;
import java.util.Map;

/**
 * Description
 * <br>  定义drools规则调用公共接口
 *
 * @author chendongdong
 * @date 2019/12/09
 **/
public interface DroolsInvokeService {


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
    boolean executeStatelessKSRule(String kSessionName, List<Object> factObjList);

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
    boolean executeStatelessKSRule(String kSessionName, List<Object> factObjList, Map<String, Object> globalVariable);
}
