# 数据库设计
业务共2张表：pet 宠物表，visit 就诊记录表。
一对多关系：一个宠物可以有多条就诊记录，visit.pet_id 外键关联 pet.id。

## pet 宠物表
|字段|类型|约束|说明|
| ---- | ---- | ---- | ---- |
|id|bigint|主键，自增|宠物唯一编号|
|name|varchar(50)|非空|宠物名字|
|birth_date|varchar(20)| |出生日期|
|pet_type|varchar(30)| |宠物种类|

## visit 就诊记录表
|字段|类型|约束|说明|
| ---- | ---- | ---- | ---- |
|id|bigint|主键，自增|就诊记录编号|
|pet_id|bigint|外键，非空|关联宠物id|
|description|varchar(200)| |就诊描述|
|visit_date|varchar(20)| |就诊日期|

### 约束说明
1. pet.id 主键自增
2. visit.id 主键自增
3. visit.pet_id 引用pet表主键，不能引用不存在宠物id
