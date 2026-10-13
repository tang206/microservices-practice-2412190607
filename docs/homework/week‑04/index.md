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

# 单体应用实现
## 实现思路
基于Spring Boot搭建宠物医院单体应用。
包含两个业务模型Pet宠物、Visit就诊记录。
分层结构：Model、Repository内存存储、Service业务层、Controller接口层。
Controller仅负责HTTP请求响应，数据访问逻辑放在Repository。
业务规则：新增就诊记录时，对应的petId必须在系统中存在，不能为不存在的宠物添加就诊记录。

## 运行命令
mvn spring-boot:run
## 接口测试 curl 命令
### 创建宠物
curl -X POST -H "Content-Type:application/json" -d '{"name":"旺财","birthDate":"2023-05-01","petType":"dog"}' http://localhost:8080/api/pets
### 根据 id 查询单只宠物
curl http://localhost:8080/api/pets/1
### 创建就诊记录
curl -X POST -H "Content-Type:application/json" -d '{"petId":1,"description":"疫苗接种","visitDate":"2026-10-13"}' http://localhost:8080/api/visits
### 查询指定宠物的就诊记录
curl http://localhost:8080/api/visits/pet/1
### 业务规则验证
curl -X POST -H "Content-Type:application/json" -d '{"petId":999,"description":"体检","visitDate":"2026-10-13"}' http://localhost:8080/api/visits
## 运行结果
1.创建宠物接口返回带自增 id 的宠物 JSON 对象。
2.根据 id 查询宠物接口返回对应宠物信息。
3.创建就诊记录成功时返回就诊记录；宠物不存在则返回业务异常提示。
4.查询宠物就诊记录返回该宠物所有就诊记录数组。
