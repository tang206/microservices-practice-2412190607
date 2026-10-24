# Week05 作业：Dockerfile 镜像构建与Docker Compose编排
项目：Pet Hospital 宠物医院单体后端 monolith 应用

## 第一部分 Dockerfile 与镜像构建
### 1 项目说明
本项目是基于SpringBoot开发的宠物医院就诊记录管理单体项目。本部分编写多阶段Dockerfile，构建阶段使用Maven镜像完成源码编译打包，运行阶段使用轻量JRE镜像运行Jar包，无需宿主机预先安装Maven与Java环境。

### 2 文件位置
`monolith/Dockerfile`：多阶段构建脚本
`monolith/.dockerignore`：构建忽略清单

### 3 镜像构建命令
在仓库根目录执行：
```bash
docker build -t pet-hospital:week05 ./monolith
```

### 4 多阶段构建作用
构建阶段拉取 Maven 镜像，下载依赖、编译源码、打包生成可执行 jar；运行阶段仅复制打好的 jar 到 JRE 镜像，丢弃编译工具与缓存依赖，最终镜像体积更小、攻击面更少，环境统一，可直接运行。

### 5 验证命令
查看本地镜像：
```bash
docker images | grep pet-hospital
```
临时启动容器测试：
```bash
docker run --rm -p 8080:8080 pet-hospital:week05
```

### 6 结果
镜像构建成功，镜像标签：pet-hospital:week05，容器启动后服务端口正常监听，接口可以正常访问。

## 第二部分 Docker Compose 多服务编排

### 1 文件位置
仓库根目录compose.yaml：服务编排配置文件
仓库根目录.env.example：环境变量模板文件，提交至 Git
仓库根目录.env：本地自用配置文件，加入.gitignore不提交
仓库根目录.gitignore：忽略本地敏感配置文件

### 2 配置说明
mysql 服务使用 docker.1ms.run/mysql:8.4.11 镜像，使用命名卷 mysql-data 持久化数据库数据。
MySQL 配置健康检查，app 服务设置依赖等待数据库健康检测通过后才启动应用。
app 构建上下文为 ./monolith，复用第一部分编写的 Dockerfile。
app 与 mysql 加入同一个自定义网络，应用直接使用服务名mysql作为数据库连接地址。
端口映射：8080 对外暴露，用于访问 SpringBoot 后端接口。
数据库连接地址、账号密码全部通过环境变量注入，不在配置文件硬编码密码。

### 3 操作步骤
复制环境模板生成本地配置文件：
```bash
cp .env.example .env
```
在仓库根目录执行启动命令：
```bash
docker compose up -d --build
```
build 参数自动重新构建 app 镜像，保证代码更新生效。
3. 查看容器运行状态
```bash
docker compose ps
```
预期结果：mysql 和 app 两个容器状态为 Up，mysql 状态标记 healthy。
4. 查看应用日志
```bash
docker compose logs -f app
```
接口测试
```bash
curl http://localhost:8080/api/visits/pet/1
```
### 4 运行结果
执行启动命令后，mysql 容器优先启动，完成数据库初始化，健康检查通过；随后 app 容器启动，SpringBoot 应用成功连接 MySQL，服务正常监听 8080 端口，接口请求正常，返回空数组代表服务连通，当前数据库暂无对应就诊记录。
