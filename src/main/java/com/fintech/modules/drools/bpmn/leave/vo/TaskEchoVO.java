package com.fintech.modules.drools.bpmn.leave.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Description
 * <br>  定义工作流任务响应VO类
 *
 * @author chendongdong
 * @date 2019/12/12
 **/
@Data
@ApiModel("工作流任务响应VO类")
public class TaskEchoVO implements Serializable {

    private static final long serialVersionUID = 8475200110565868825L;

    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @ApiModelProperty(value = "任务名称")
    private String taskName;


    public TaskEchoVO(String taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }
}
