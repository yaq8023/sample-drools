sample-drools 规则引擎开发
===============

当前最新版本： 0.0.1-SNAPSHOT（发布日期：20191210）

## 项目介绍

Drools是业务规则管理系统（BRMS）解决方案。Drools可以与jBPM集成，jBPM是一个业务流程管理工具，用于流程，事件活动，任务等的标准化。


## 后端技术架构
- 基础框架：Spring Boot 2.0.5.RELEASE

- 持久层框架：Mybatis-plus_3.1.2

- 日志打印：logback

- 其他：fastjson，Swagger-ui, lombok（简化代码）等。

## 开发环境

- 语言：Java 8

- IDE(JAVA)：IDEA安装lombok插件

- 依赖管理：Maven

- 数据库：MySQL5.6+

- activiti版本：6.0.0

- drools版本：7.0.0.Final

## 项目结构

```
├─db
│  ├─mysql_schema.sql(数据库创建初始化脚本)
├─doc
│  ├─activiti-6.0.0.zip
├─src
│  ├─main
│    ├─java/come/fintech/modules
│      ├─boot(存放配置文件、异常类、工具类)
│        ├─configuration(存放配置文件)
│            ├─datasource(数据源处理)
│            ├─swagger(接口文档在线自动生成)
│            ├─web(接口返回报文全局处理)
│        ├─exception(存放自定义异常类)
│        ├─util(存放工具类)
│      ├─drools(存放业务类)
│        ├─base(存放Drools规则引擎接口服务以及实现类)
│          ├─DroolsInvokeService.java(定义drools规则调用公共接口)
│            ├─impl
│              ├─DroolsInvokeServiceImpl.java(定义drools规则调用公共接口实现类)
│          ├─DroolsAutoConfiguration.java(定义drools自动配置加载类)
│        ├─bpmn(存放工作流样例)
│            ├─leave(简单请假流程)
│        ├─constants(存放常量类)
│            ├─DroolsConstants.java(常量定义类)
│        ├─example(存放Drools规则引擎调用样例)
│            ├─address(邮政编码校验规则样例)
│            ├─cashflow(现金流校验规则样例)
│            ├─decisiontable(决策表-电子表格示例) 
│    ├─DroolsApplication.java(项目启动类)
│    ├─resources
│      ├─bpmn(流程部署文件)
│      ├─META-INF
│        ├─kmodule.xml(Drools规则引擎kmodule方式本地配置文件)
│      ├─rules(存放Drools规则定义文件)
│        ├─CashFlow.drl
│        ├─Leave.drl
│        ├─PostCodeCheck.drl    
├─pom.xml(项目jar包依赖配置文件)
├─README.md(项目说明文档)
└─target
   └─更多功能开发中。。
   
```



## 技术文档


- 官网地址 ： 
  
  [Drools的官网](https://www.drools.org/)

  [activiti官网](https://www.activiti.org/)

- Drools与Spring集成：[springboot 2.x 集成 drools 7.x](https://www.cnblogs.com/jpfss/p/10870265.html)

- Drools与Activiti集成：[Spring Cloud 2.x之Activiti整合规则引擎Drools](https://blog.csdn.net/huangjinjin520/article/details/84678277)

- Activiti-6.0.0 WEB环境搭建：[Activiti-6.0.0 WEB环境搭建](https://blog.csdn.net/weixin_42704736/article/details/84066240)

- Drools决策表使用: [基于Drools决策表的通用规则集设计](https://blog.csdn.net/ujsleo/article/details/78716842)

## 规则示例

- 邮政编码校验规则 

  邮编为0~9内的任意五位数字。
  
- 现金流校验规则

  [Drools学习笔记-执行控制](https://blog.csdn.net/shenchaohao12321/article/details/80916708)
  
- 请假流程示例

  简单的Activiti请假流程leave.bpmn与Drools请假规则Leave.drl完美结合。
  
- 决策表（电子表格）示例