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
