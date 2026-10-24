# Week05 作业：Dockerfile 与镜像构建
项目：Pet Hospital 宠物医院后端 monolith 单体应用

## 1 项目说明
本项目为宠物医院就诊记录管理 SpringBoot 项目，本次作业编写多阶段 Dockerfile，构建阶段使用 Maven 镜像编译打包，运行阶段使用轻量 JRE 镜像运行 Jar，不需要宿主机安装 Maven、Java 环境。

## 2 文件位置
`monolith/Dockerfile`：多阶段构建脚本
`monolith/.dockerignore`：构建忽略清单

## 3 镜像构建命令
在仓库根目录执行：
```bash
docker build -t pet-hospital:week05 ./monolith
```

## 4 多阶段构建作用
构建阶段拉取 Maven 镜像，下载依赖、编译源码、打包生成可执行 jar；运行阶段仅复制打好的 jar 到 JRE 镜像，丢弃编译工具与缓存依赖，最终镜像体积更小、攻击面更少，环境统一，可直接运行。

## 5 验证命令
查看本地镜像：
```bash
docker images | grep pet-hospital
```
