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

## 2. 单体应用实现
### 实现思路
定义Pet与Visit实体模型，编写内存版Repository，使用HashMap模拟数据库存储，实现基础增删查询。在Service层实现业务校验逻辑，编写JUnit5单元测试，验证新增就诊记录时宠物不存在抛出异常、正常新增就诊、按宠物ID查询就诊记录等业务场景。
基于 Spring Web 开发 REST 接口，采用 DTO 封装前端请求入参，分离请求对象与数据库实体。编写 PetController、VisitController，提供宠物与就诊记录的新增、查询接口，统一返回ResponseEntity包装结果。Service 层保持原有业务校验逻辑不变。
### 核心代码要点
1. 模型类Pet、Visit，定义对应属性与get/set方法
2. 内存Repository（InMemoryPetRepository、InMemoryVisitRepository），基于HashMap实现CRUD
3. PetService、VisitService实现业务逻辑，业务校验放置于Service层
4. 单元测试类，覆盖正常新增、宠物不存在异常、查询就诊记录等测试用例

### 运行单元测试命令
mvn test
验证结果
执行mvn test，全部单元测试用例通过，证明业务逻辑符合需求。

### 接口清单
POST /api/pets：新建宠物，接收 PetCreateDTO
GET /api/pets：查询全部宠物
GET /api/pets/{id}：根据 ID 查询单条宠物，不存在返回 404
POST /api/visits：新建就诊记录，接收 VisitCreateDTO，service 校验 petId 是否存在
GET /api/visits：查询全部就诊记录
GET /api/visits/pet/{petId}：根据宠物 ID 查询该宠物所有就诊记录

### curl 测试命令
```bash
#创建宠物
curl -X POST -H "Content-Type:application/json" -d '{"name":"旺财","petType":"DOG","birthDate":"2024-01-01"}' http://localhost:8080/api/pets
#查询全部宠物
curl http://localhost:8080/api/pets
#根据id查询宠物
curl http://localhost:8080/api/pets/1
#创建就诊记录
curl -X POST -H "Content-Type:application/json" -d '{"petId":1,"description":"犬瘟筛查","visitDate":"2026-10-10"}' http://localhost:8080/api/visits
#查询全部就诊记录
curl http://localhost:8080/api/visits
#根据宠物id查询就诊记录
curl http://localhost:8080/api/visits/pet/1
```
### 验证结果
启动 SpringBoot 项目，执行 curl 命令调用接口，可正常完成新增与查询；传入不存在 petId 新增就诊记录，服务抛出 ResourceNotFoundException，接口返回异常

## 3.数据库持久化
### 实现思路
替换内存仓储，接入 MySQL，使用 Spring Data JPA 实现持久化。Pet、Visit 实体增加 JPA 持久化注解，Repository 继承 JpaRepository。使用 data.sql 预置测试数据，DTO 继续承载 API 请求，保留原有 Service 业务校验逻辑。设计 pet 与 visit 一对多关系，visit 通过 pet_id 外键关联 pet。
### 配置信息
application.yml
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/pet_hospital?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true
  sql:
    init:
      mode: always
```
本地 MySQL 执行建库语句
```sql
CREATE DATABASE pet_hospital DEFAULT CHARACTER SET utf8mb4;
```
### 运行步骤
本地启动 MySQL，执行 SQL 创建 pet_hospital 数据库
修改 application.yml 的数据库账号密码
启动项目，自动执行 data.sql 加载预置测试数据
```bash
mvn spring-boot:run
```
使用 curl 调用接口读写数据，数据持久存入 MySQL
### 验证结果
调用接口新增数据后，在 MySQL 客户端执行select * from pet;、select * from visit;，可查询到写入数据。新增就诊记录时，传入不存在 petId 会抛出 ResourceNotFoundException，业务校验生效。

curl -X POST -H "Content-Type:application/json" -d '{"petId":999,"description":"体检","visitDate":"2026-10-13"}' http://localhost:8080/api/visits
## 运行结果
1.创建宠物接口返回带自增 id 的宠物 JSON 对象。
2.根据 id 查询宠物接口返回对应宠物信息。
3.创建就诊记录成功时返回就诊记录；宠物不存在则返回业务异常提示。
4.查询宠物就诊记录返回该宠物所有就诊记录数组。
