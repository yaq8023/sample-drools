package com.fintech.modules.drools.bpmn.leave.rest;


import com.fintech.modules.drools.bpmn.leave.entity.Leave;
import com.fintech.modules.drools.bpmn.leave.vo.TaskEchoVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 * <br> 定义 请假流程 Rest服务类
 *
 * @author chendongdong
 * @date 2019/12/11
 **/
@Slf4j
@Api(tags = {"drools-请假流程"})
@RestController
@RequestMapping("/api/leave")
public class LeaveRest {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /**
     * Description
     * <br>  发起请假流程
     *
     * @param leave
     * @date 2019/12/12
     * @author chendongdong
     **/
    @PostMapping("/askForLeave/v1")
    public TaskEchoVO askForLeave(@Validated @RequestBody Leave leave) {
        /**
         * 注意这里：必须要把drl文件一起deploy
         */
        DeploymentBuilder deploy = repositoryService.createDeployment();
        deploy.addClasspathResource("bpmn/leave.activiti.bpmn").addClasspathResource("rules/leave/Leave.drl");
        deploy.deploy();
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("leave");
        Map<String, Object> vars = new HashMap<>(1);
        vars.put("leave", leave);
        /**
         * 当前任务
         */
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
        for (Task task : tasks) {
            log.info("任务ID:{},任务名称：{}", task.getId(), task.getName());
            taskService.complete(task.getId(), vars);
        }
        /**
         * 下一步任务
         */
        //任务描述
        TaskEchoVO currentTask = null;
        tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
        for (Task task : tasks) {
            log.info("任务ID:{},任务名称：{}", task.getId(), task.getName());
            currentTask = new TaskEchoVO(task.getId(), task.getName());
        }

        return currentTask;
    }
}