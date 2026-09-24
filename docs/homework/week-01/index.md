# Week 01 作业：环境检查
## 1. 环境版本输出
### java --version
openjdk 26.0.2 2026-07-21
OpenJDK Runtime Environment (build 26.0.2+10-2-26.04.2-Ubuntu)
OpenJDK 64-Bit Server VM (build 26.0.2+10-2-26.04.2-Ubuntu, mixed mode, sharing)

### mvn --version
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: /opt/maven
Java version: 26.0.2, vendor: Ubuntu, runtime: /usr/lib/jvm/java-26-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "6.18.33.2-microsoft-standard-wsl2", arch: "amd64", family: "unix"

### git --version
git version 2.53.0

### docker version
Client: Docker Engine - Community
 Version:           29.8.1
 API version:       1.56
 Go version:        go1.26.8
 Git commit:        4a63305
 Built:             Tue Sep 15 16:25:42 2026
 OS/Arch:           linux/amd64
 Context:           default

Server: Docker Engine - Community
 Engine:
  Version:          29.8.1
  API version:      1.56 (minimum version 1.40)
  Go version:       go1.26.8
  Git commit:       464cd50
  Built:            Tue Sep 15 16:25:42 2026
  OS/Arch:          linux/amd64
  Experimental:     false
 containerd:
  Version:          v2.3.5
  GitCommit:        1294c24a7da8e5a793ed378161673abe94118892
 runc:
  Version:          1.5.1
  GitCommit:        v1.5.1-0-g8f2685a4
 docker-init:
  Version:          0.19.0
  GitCommit:        de40ad0
  
### docker compose version
Docker Compose version v5.5.1

## 2. 问题记录
本次环境安装无报错，所有工具均可正常执行
## 概念回答
1. **什么是微服务架构？**
微服务架构是将大型软件应用拆分为若干个小型、自治的独立服务。每个服务聚焦单一业务能力，独立开发、独立打包部署，服务之间通过网络接口进行通信，各个服务可以独立伸缩、独立迭代更新。

2. **微服务和单体架构的主要区别是什么？**
单体架构将全部业务代码放在同一个工程，统一打包，整体部署；任何小修改都需要全量发布，扩容需要整体扩容。微服务架构按业务拆分成多个独立项目，服务独立部署，可单独升级、单独扩容；代价是引入分布式网络、服务治理等额外复杂度。

3. **为什么本课程先实现单体系统，再逐步拆分为微服务？**
先开发单体系统可以快速落地完整业务，快速验证业务逻辑，避开分布式带来的大量复杂问题。等业务模型清晰之后再做微服务拆分，更容易合理划分服务边界，直观感受单体架构存在的缺陷，理解微服务的价值与适用场景。

4. **为什么作业需要提供可重复运行的测试或验证脚本？**
可重复运行脚本能够自动化验证程序功能，不受人工操作、环境差异影响，方便反复回归测试；同时便于老师快速复现项目运行效果，保证作业结果可验证，提升项目交付规范性。
