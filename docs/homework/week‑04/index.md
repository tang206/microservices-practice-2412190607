# Week04 作业报告

## 1. 业务建模
### 1.1 业务流程
为宠物新增就诊记录：
1. 系统中已存在宠物信息
2. 提交就诊信息（宠物id、就诊时间、就诊描述）
3. Service校验该petId对应的宠物是否存在
4. 宠物存在，则保存就诊记录；不存在抛出异常，拒绝生成就诊记录
5. 支持根据宠物id，查询该宠物全部就诊历史记录

### 1.2 实体与关系
- Pet：宠物，id、宠物名字、出生日期、宠物类型
- Visit：就诊记录，id、petId、就诊时间visitTime、就诊描述description

关系：**一对多**，一只Pet可以有多条Visit；Visit使用petId引用宠物，不直接持有Pet对象。

### 1.3 Service业务规则
> 业务规则写在Service层，不在Controller。
创建就诊记录的时候，校验传入的petId，若找不到对应Pet，抛出`ResourceNotFoundException`，不生成就诊记录。

## 2. 阶段一：模型、内存仓储、Service单元测试
> 

## 3. 阶段二：REST接口
> 

## 4. 阶段三：数据库持久化
> 

## 2. 单体应用实现
### 2.1 实现思路
基于Spring Boot单体工程，包名`com.zjgsu.tfy`。
包含两个业务模型Pet、Visit；VisitService处理业务逻辑，VisitController处理HTTP请求。
一共4个REST接口，全部以`/api/`开头；业务规则在Service层校验，Controller只处理请求与响应，不直接操作数据。
采用内存HashMap仓储，应用重启数据丢失。

### 2.2 接口清单
1. POST `/api/pets`  创建宠物
2. GET `/api/pets/{id}` 查询单条宠物信息
3. POST `/api/visits` 创建就诊记录（业务校验：petId必须存在）
4. GET `/api/visits/pet/{petId}` 查询该宠物所有就诊记录

### 2.3 运行命令
启动项目：
./mvnw spring-boot:run
### 2.4 Curl 测试示例
新增宠物
curl -X POST -H "Content-Type:application/json" -d '{"name":"豆豆","birthDate":"2023-05-10","petType":"Dog"}' http://localhost:8080/api/pets
新增就诊记录
curl -X POST -H "Content-Type:application/json" -d '{"petId":1,"visitTime":"2026-10-12 14:00:00","description":"感冒检查"}' http://localhost:8080/api/visits
### 2.5 验证结果
执行 curl 调用接口，正常场景返回对应资源，非法 petId 创建就诊记录返回异常信息。
