# Spring5 框架

## Spring框架概述

1、Spring 是轻量级的开源的 JavaEE 框架

2、Spring 可以解决企业应用开发的复杂性

3、Spring 有**两个核心部分**：IOC 和 Aop

（1）IOC：控制反转，把**创建对象过程**交给 Spring 进行管理

（2）Aop：面向切面，不修改源代码进行功能增强

4、Spring 特点

（1）方便解耦，简化开发

（2）Aop 编程支持

（3）方便程序测试

（4）方便和其他框架进行整合

（5）方便进行事务操作

（6）降低 API 开发难度

## 用Maven引入Spring核心

第一步，创建Maven文件，选择Create from archetype找到webapp那一行

一路Next，可以更改groupId什么的

第二步，引入Spring核心jar包spring-core等九个，打开Maven Repository（[点击进入](https://mvnrepository.com/)），搜索spring-core，点进最新版本，如下所示：

![](D:\Image\Spring\Maven查找Spring-core.png)

将依赖导入

> <dependency></dependency>包含在内的一块代码复制黏贴进刚创建的Maven项目的pom.xml中

导入后点击IDEA右侧的Maven,有个刷新键点一点让依赖自动去下载更新jar包

![](D:\Image\Spring\Maven导入Spring依赖后.png)

## 测试Spring框架

在新创建的Maven项目的main中创建java资源包和resources包，resources包里存放bean.xml，java里存放对象类等，编写测试类：

![](D:\Image\Spring\Spring测试.PNG)

其中bean1.xml存放了对象信息，体现了Spring核心中IOC的思想：

![](D:\Image\Spring\IOC思想.PNG)

