# Week03 作业计划
## 选题衔接
沿用宠物医院管理系统，本周搭建基础SpringBoot单体工程，不开发业务功能。
## 本周实现范围
1. 在仓库根目录新建 monolith 文件夹，存放Maven SpringBoot工程
2. 使用Spring Initializr创建工程：Java25，SpringBoot4.0.x，Maven，包名com.zjgsu.tfy
3. 配置application.yml，端口8080
4. 编写基础启动类，增加健康检查和验证用GET接口
5. 编写基础启动测试，保证`./mvnw spring-boot:run`和`./mvnw test`可正常执行
6. 截图保存命令运行结果，提交代码到GitHub仓库

# Week03 SpringBoot工程创建、启动与接口测试
## 1. 项目说明
本次基于宠物医院管理系统，搭建Spring Boot单体工程，引入Spring Web和Actuator组件，实现简单Web接口，并且完成单元测试验证项目上下文正常加载。

## 2. 工程启动与接口访问
### 启动命令
./mvnw.cmd spring-boot:run
项目启动成功，监听 8080 端口。

### 接口 1：GET /api/hello
浏览器访问地址：http://localhost:8080/api/hello
返回内容：Pet Hospital Management System Running
### 接口 2：GET /actuator/health
浏览器访问地址：http://localhost:8080/actuator/health
返回 JSON：{"groups":["liveness","readiness"],"status":"UP"}

### 单元测试
执行测试命令./mvnw.cmd test
使用@SpringBootTest编写上下文加载测试，验证 Spring 容器正常启动。
测试结果：Tests run: 1, Failures: 0, Errors: 0，BUILD SUCCESS。
