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

## IOC概念和原理

1、什么是 IOC
（1）控制反转，把对象创建和对象之间的调用过程，交给 Spring 进行管理
（2）使用 IOC 目的：为了耦合度降低
（3）做入门案例就是 IOC 实现

2、IOC 底层原理
（1）**xml 解析、工厂模式、反射**

> 工厂模式对类与类之间的耦合度进行了解耦，但是并不是最优的方法，相当于多了个中间件，通过static的方法获取对象。
>
> 说白了，工厂模式的耦合度依旧很高，但是的的确确省去了new的操作

IOC过程

第一步 xml配置文件，配置创建的对象

```java
<bean id = "dao" class = "top.lep101.UserDao"/>
```

第二步 有service类和dao类，创建工厂类

```java
class UserFactory {
    public static UserDao getDao() {
        String classValue = class属性值;    //xml解析
        Class clazz = Class.forName(classValue);    //通过反射创建对象
        return (UserDao)clazz.newInstance();
    }
}
```

> 注解+反射+设计模式 = 框架

IOC接口

1、IOC 思想基于 IOC 容器完成，**IOC 容器底层就是`对象工厂`**

2、Spring 提供 IOC 容器实现两种方式：（两个接口）

（1）BeanFactory：IOC 容器基本实现，是 Spring 内部的使用接口，不提供开发人员进行使用

\* 加载配置文件时候不会创建对象，在获取对象（使用）才去创建对象

（2）ApplicationContext：BeanFactory 接口的子接口，提供更多更强大的功能，一般由开发人员进行使用

\* 加载配置文件时候就会把在配置文件对象进行创建

3、ApplicationContext 接口有实现类

## IOC操作Bean管理

1、什么是Bean管理

(0) Bean管理指的是两个操作

(1) Spring创建对象

(2) Spring注入属性

2、Bean管理操作有两种方式

### (1) 基于xml配置文件方式实现

#### 	**基于xml方式创建对象**

​    ①在 spring 配置文件中，使用 bean 标签，标签里面添加对应属性，就可以实现对象创建

​	②在 bean 标签有很多属性，介绍常用的属性

​		id 属性：**唯一标识**

​		class 属性：类全路径（包类路径）

​		name属性：跟id一样 唯一标识 可以带上特殊符号

​	③创建对象时候，`默认`也是`执行无参数构造方法`完成对象创建

#### 	**基于xml方式注入属性**

​		DI：依赖注入，就是注入属性

​		第一种注入方式：使用set方式进行注入

```java
//(1)创建类，定义属性和对应的set方法
public class Book {
    //创建属性
    private String bname;
    private String bauthor;
    //创建属性对应的set方法
    public void setBname(String bname) {
        this.bname = bname;
    }
    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }
}
//(2)在spring配置文件配置对象创建，配置属性注入
<bean id="book" class="top.lep101.spring5.Book">
    <!--使用 property 完成属性注入
       name：类里面属性名称
       value：向属性注入的值
     -->
     <property name="bname" value="易筋经"></property>
     <property name="bauthor" value="达摩老祖"></property>
</bean>
```

​		第二种注入方式：使用有参数构造进行注入

```java
//(1)创建类，定义属性，创建属性对应有参数构造方法
/**
* 使用有参数构造注入
*/
public class Orders {
 //属性
 private String oname;
 private String address;
 //有参数构造
 public Orders(String oname,String address) {
 this.oname = oname;
 this.address = address;
 } }（2）在 spring 配置文件中进行配置
<!--3 有参数构造注入属性--> 
<bean id="orders" class="top.lep101.spring5.Orders">
 <constructor-arg name="oname" value="电脑"></constructor-arg>
 <constructor-arg name="address" value="China"></constructor-arg>
</bean>
```

#### xml注入其他类型属性

1、字面量

（1）null 值

*<!--null 值-->* 

```xml
<property name="address">

 <null/>

</property> 
```

（2）属性值包含特殊符号

> *<!--属性值包含特殊符号*
>
>  *1 把<>进行转义 < >*
>
>  2 把带特殊符号内容写到 CDATA
>
> *-->*

```xml
<property name="address">

 	<value><![CDATA[<<南京>>]]></value>

</property> 
```

#### 注入属性-外部bean

（1）创建两个类 service 类和 dao 类 

（2）在 service 调用 dao 里面的方法

（3）在 spring 配置文件中进行配置

```java
public class UserService {
 //创建 UserDao 类型属性，生成 set 方法
 private UserDao userDao;
 public void setUserDao(UserDao userDao) {
 this.userDao = userDao;
 }
 public void add() {
 System.out.println("service add...............");
 userDao.update();
 } }
```

```xml
<!--1 service 和 dao 对象创建--> 
<bean id="userService"
      class="top.lep101.spring5.service.UserService">
 <!--注入 userDao 对象
 name 属性：类里面属性名称
 ref 属性：创建 userDao 对象 bean 标签 id 值
 -->
 <property name="userDao" ref="userDaoImpl"></property>
</bean> 
<bean id="userDaoImpl" 	class="top.lep101.spring5.dao.UserDaoImpl">
</bean>
```

#### 注入属性-内部bean和级联赋值

（1）一对多关系：部门和员工

一个部门有多个员工，一个员工属于一个部门

部门是一，员工是多

（2）在实体类之间表示一对多关系，员工表示所属部门，使用对象类型属性进行表示

```java
//部门类

public class Dept {

 private String dname;

 public void setDname(String dname) {

 this.dname = dname;

 } 

}

//员工类

public class Emp {

 private String ename;

 private String gender;

 //员工属于某一个部门，使用对象形式表示

 private Dept dept;

 public void setDept(Dept dept) {

 this.dept = dept;

 }

 public void setEname(String ename) {

 this.ename = ename;

 }

 public void setGender(String gender) {

 this.gender = gender; } 

}
```

（3）在 spring 配置文件中进行配置

```xml
<!--内部 bean-->*

<bean id="emp" class="top.lep101.spring5.bean.Emp">

 <!--设置两个普通属性-->

 <property name="ename" value="lucy"></property>

 <property name="gender" value="女"></property>

 *<!--设置对象类型属性-->*

 <property name="dept">

 <bean id="dept" class="top.lep101.spring5.bean.Dept">

 <property name="dname" value="安保部"></property>

 </bean>

 </property>

</bean>
```

#### xml注入集合属性

**1**、注入数组类型属性

**2**、注入 **List** **集合类型属性**

**3**、注入 **Map** **集合类型属性**

```java
（1）创建类，定义数组、list、map、set 类型属性，生成对应 set 方法

public class Stu {

 //1 数组类型属性

 private String[] courses;

 //2 list 集合类型属性

 private List<String> list;

 //3 map 集合类型属性

 private Map<String,String> maps;

 //4 set 集合类型属性*

 private Set<String> sets;

 public void setSets(Set<String> sets) {

 this.sets = sets;

 }

 public void setCourses(String[] courses) {

 this.courses = courses;

 }

 public void setList(List<String> list) {

 this.list = list;

 }

 public void setMaps(Map<String, String> maps) {

 this.maps = maps;

 } 

}
```

```xml
（2）在 spring 配置文件进行配置

<!--1 集合类型属性注入--> 

<bean id="stu"class="com.atguigu.spring5.collectiontype.Stu">

 <!--数组类型属性注入-->

 <property name="courses">

 <array>

 <value>java 课程</value>

 <value>数据库课程</value>

 </array>

 </property>

 <!--list 类型属性注入-->

 <property name="list">

 <list> <value>张三</value>

 <value>小三</value>

 </list>

 </property>

 <!--map 类型属性注入-->

 <property name="maps">

 <map>

 <entry key="JAVA" value="java"></entry>

 <entry key="PHP" value="php"></entry>

 </map>

 </property>

 <!--set 类型属性注入-->

 <property name="sets">

 <set>

 <value>MySQL</value>

 <value>Redis</value>

 </set>

 </property>

</bean> 
```

**4**、在集合里面设置对象类型值

```xml
<!--创建多个 course 对象-->* 

<bean id="course1" class="com.atguigu.spring5.collectiontype.Course">

 <property name="cname" value="Spring5 框架"></property>

</bean> 

<bean id="course2" class="com.atguigu.spring5.collectiontype.Course">

 <property name="cname" value="MyBatis 框架"></property>

</bean>

<!--注入 list 集合类型，值是对象-->

<property name="courseList">

 <list>

 <ref bean="course1"></ref>

 <ref bean="course2"></ref>

 </list>

</property> 
```

**5**、把集合注入部分提取出来

（1）在 spring 配置文件中引入名称空间 util

```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"

 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

 xmlns:p="http://www.springframework.org/schema/p"

 xmlns:util="http://www.springframework.org/schema/util"

 xsi:schemaLocation="http://www.springframework.org/schema/beans 

http://www.springframework.org/schema/beans/spring-beans.xsd

 http://www.springframework.org/schema/util 

http://www.springframework.org/schema/util/spring-util.xsd"> 

（2）使用 util 标签完成 list 集合注入提取

<!--1 提取 list 集合类型属性注入-->

<util:list id="bookList"> 
    <value>易筋经</value>

 	<value>九阴真经</value>

 	<value>九阳神功</value>

</util:list**>

<!--2 提取 list 集合类型属性注入使用-->

<bean id="book" class="com.atguigu.spring5.collectiontype.Book">

 <property name="list" ref="bookList"></property>

</bean>
```

#### FactoryBean

**1**、Spring **有两种类型** **bean**，一种普通**bean**，另外一种工厂 **bean**（FactoryBean)

**2**、普通 **bean**：在配置文件中定义 **bean** **类型就是返回类型**

**3**、工厂 **bean**：在配置文件定义 **bean** **类型可以和返回类型不一样**

第一步 创建类，让这个类作为工厂 bean，实现接口 FactoryBean

第二步 实现接口里面的方法，在实现的方法中定义返回的 bean 类型

```java
public class MyBean implements FactoryBean<Course> {

 //定义返回 bean

 @Override

 public Course getObject() throws Exception {

 Course course = new Course();

 course.setCname("abc");

 return course;

 }

 @Override

 public Class<?> getObjectType() {

 return null;

 }

 @Override

 public boolean isSingleton() {

 return false;

 } 

}

<bean id="myBean" class="com.atguigu.spring5.factorybean.MyBean">

</bean>

@Test

public void test3() {

 ApplicationContext context =

 new ClassPathXmlApplicationContext("bean3.xml");

 Course course = context.getBean("myBean", Course.class);

 System.out.println(course);

}
```

#### Bean作用域和Bean生命周期

> 在Spring里面，默认情况下，bean是单实例对象

验证

```java
@Test
public void testCollection2() {
	ApplicationContext context = 
		new ClassPathXmlApplicationContext("bean2.xml");
	Book book1 = context.getBean("book",Book.class);
	Book book2 = context.getBean("book",Book.class);
	// book.test()
	System.out.println(book1);//相同
	System.out.println(book2);//
}
```

如何设置单实例还是多实例

(1) 在spring 配置文件bean标签里面有属性(scope)用于设置单实例还是多实例

(2) scope属性值

> 第一个值，默认值，singleton，表示是单实例对象
>
> 第二个值，prototype，表示是多实例对象

代码实例：

```xml
<bean id="book" class="top.lep101.spring5.collectiontype.Book" scope="prototype">
	<property name="list" ref="bookList"></property>
</bean>
```

> singleton 和 prototype 区别
>
> 第一 singleton 单实例，prototype 多实例
>
> 第二 设置 scope 值是 singleton 时候，加载 spring 配置文件时候就会创建单实例对象
>
> 设置 scope 值是 prototype 时候，不是在加载 spring 配置文件时候创建 对象，在调用getBean 方法时候创建多实例对象

**生命周期**

​	从对象创建到对象销毁的过程

**bean生命周期**

①通过构造器创建bean实例(无参数构造)

②为bean的属性设置值和对其他bean引用(调用set方法)

③调用bean的初始化的方法(需要进行配置初始化的方法)

④bean可以使用了(对象获取到了)

⑤当容器关闭时候，调用bean的销毁的方法(需要进行配置销毁的方法)

**演示bean生命周期**

```java
public class Orders {
    public Orders() {
        System.out.println("第一步 执行无参数构造创建bean 实例")
    }
    private String oname;
    public void setOname(String oname) {
         this.oname = oname;
         System.out.println("第二步 调用 set 方法设置属性值");
         }
         //创建执行的初始化的方法
         public void initMethod() {
         System.out.println("第三步 执行初始化的方法");
         }
         //创建执行的销毁的方法
         public void destroyMethod() {
         System.out.println("第五步 执行销毁的方法");
     }
}
```

```xml
<bean id="orders" class="com.atguigu.spring5.bean.Orders" initmethod="initMethod" destroy-method="destroyMethod">
 <property name="oname" value="手机"></property>
</bean>
```

```java
@Test
 public void testBean3() {
    // ApplicationContext context =
    // new ClassPathXmlApplicationContext("bean4.xml");
     ClassPathXmlApplicationContext context =
     new ClassPathXmlApplicationContext("bean4.xml");
     Orders orders = context.getBean("orders", Orders.class);
     System.out.println("第四步 获取创建 bean 实例对象");
     System.out.println(orders);
     //手动让 bean 实例销毁
     context.close();
 }
```

#### xml自动装配

1.什么是自动装配

> 根据指定装配规则（属性名称或者属性类型），Spring 自动将匹配的属性值进行注入

2.演示自动装配过程

```xml
<!--手动装配-->
<bean id="emp" class="top.lep101.spring5.autowire.Emp">
	<property name="dept" ref="dept"></property>
</bean>
<bean id="dept" class="top.lep101.spring5.autowire.Dept"></bean>

<!--实现自动装配
 bean 标签属性 autowire，配置自动装配
 autowire 属性常用两个值：
 byName 根据属性名称注入 ，注入值 bean 的 id 值和类属性名称一样
 byType 根据属性类型注入
-->
<!--自动装配-->
<bean id="emp" class="top.lep101.spring5.autowire.Emp" autowire="byName">
</bean>
<bean id="dept" class="top.lep101.spring5.autowire.Dept"></bean>
```

> 使用byType相同类型的bean不能定义多个，否则找不到

### (2) 基于注解方式实现

#### 1.什么是注解

①注解是代码特殊标记，格式：@注解名称(属性名称=属性值, 属性名称=属性值..)

②使用注解，注解作用在类上面，方法上面，属性上面

③使用注解目的：简化 xml 配置

#### 2.Spring针对Bean管理中创建对象提供注解

① @Component (普遍)

② @Service (一般用在业务逻辑层/Service层)

③ @Controller (一般用在Web层)

④ @Repository (Dao层/持久层)

> 注解只是为了开发更方便,混着用没事
>
> 上面四个注解功能是一样的，都可以用来创建bean实例

#### 3.基于注解方式实现对象创建

第一步 引入依赖

第二步 开启组件扫描

```xml
<!--开启组件扫描

 1 如果扫描多个包，多个包使用逗号隔开

 2 扫描包上层目录

-->

<context:component-scan base-package="com.atguigu"></context:component-scan>
```

第三步 创建类，在类上面添加创建对象注解

```java
//在注解里面 value 属性值可以省略不写，
//默认值是类名称，首字母小写
//UserService -- userService
@Component(value = "userService") //<bean id="userService" class=".."/>
public class UserService {
     public void add() {
     	System.out.println("service add.......");
     } 
}
```

#### 4.开启组件扫描细节配置

```xml
<!--示例 1
 use-default-filters="false" 表示现在不使用默认 filter，自己配置 filter
 context:include-filter ，设置扫描哪些内容
--><context:component-scan base-package="com.atguigu" use-default-filters="false">
 <context:include-filter type="annotation" 
 
expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
<!--示例 2
 下面配置扫描包所有内容
 context:exclude-filter： 设置哪些内容不进行扫描
--><context:component-scan base-package="com.atguigu">
 <context:exclude-filter type="annotation" 
 
expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```

#### 5.基于注解方式实现属性注入

（1）@Autowired：根据属性类型进行自动装配

第一步 把 service 和 dao 对象创建，在 service 和 dao 类添加创建对象注解

第二步 在 service 注入 dao 对象，在 service 类添加 dao 类型属性，在属性上面使用注解

```java
@Service
public class UserService {
     //定义 dao 类型属性
     //不需要添加 set 方法
     //添加注入属性注解
     @Autowired 
     private UserDao userDao;
     public void add() {
         System.out.println("service add.......");
         userDao.add();
     } 
}
```

（2）@Qualifier：根据名称进行注入

这个@Qualifier 注解的使用，和上面@Autowired 一起使用

> 一个接口有多个实现类的时候，需要有@Qualifier指定实现类的ID

```java
//定义 dao 类型属性
//不需要添加 set 方法
//添加注入属性注解
@Autowired //根据类型进行注入
@Qualifier(value = "userDaoImpl1") //根据名称进行注入
private UserDao userDao;
```

（3）@Resource：可以根据类型注入，可以根据名称注入

```java
//@Resource //根据类型进行注入
@Resource(name = "userDaoImpl1") //根据名称进行注入
private UserDao userDao;
```

（4）@Value：注入普通类型属性

```java
@Value(value = "abc")
private String name;
```

#### 6.完全注解开发

①创建配置类，替代xml配置文件

```java
@Configuration //作为配置类，替代 xml 配置文件
@ComponentScan(basePackages = {"com.atguigu"})
public class SpringConfig {
}
```

②编写测试类

```java
@Test
public void testService2() {
 //加载配置类
 ApplicationContext context
 = new AnnotationConfigApplicationContext(SpringConfig.class);
 UserService userService = context.getBean("userService", 
UserService.class);
 System.out.println(userService);
 userService.add();
}
```

> Springboot本质上就是Spring,用的就是完全注解开发

### AOP

什么是AOP

（1）面向切面编程（方面），利用 AOP 可以对业务逻辑的各个部分进行隔离，从而使得

业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

（2）通俗描述：不通过修改源代码方式，在主干功能里面添加新功能

（3）使用登录例子说明 AOP

![image-20211120170816710](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20211120170816710.png)

#### Aop操作

**1**、Spring **框架一般都是基于** **AspectJ** **实现** **AOP** **操作**

（1）AspectJ 不是 Spring 组成部分，独立 AOP 框架，一般把 AspectJ 和 Spirng 框架一起使用，进行 AOP 操作

**2**、基于 **AspectJ** **实现** **AOP** **操作**

（1）基于 xml 配置文件实现

**（2）基于注解方式实现（使用）**

1.创建类，在类里面定义方法

```java
package top.lep101.spring5.aopanno;

import org.springframework.stereotype.Component;

//被增强的类
@Component
public class User {

    public void add() {

        System.out.println("add.....");

    }
}
```

2.创建增强类（编写增强逻辑）

> 在增强类里面，创建方法，让不同方法代表不同通知类型

```java
package top.lep101.spring5.aopanno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//增强的类
@Component
@Aspect
public class UserProxy {

    //前置通知
    //@Before注解表示作为前置通知
    @Before(value = "execution(* top.lep101.spring5.aopanno.User.add(..))")
    public void before() {
        System.out.println("before......");
    }

    //后置通知（返回通知）
    @AfterReturning(value = "execution(* top.lep101.spring5.aopanno.User.add(..))")
    public void afterReturning() {
        System.out.println("afterReturning.........");
    }

    //最终通知
    @After(value = "execution(* top.lep101.spring5.aopanno.User.add(..))")
    public void after() {
        System.out.println("after......");
    }

    //异常通知
    @AfterThrowing(value = "execution(* top.lep101.spring5.aopanno.User.add(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing.........");
    }
    //环绕通知 (在方法环绕之前和环绕之后都执行)
    @Around(value = "execution(* top.lep101.spring5.aopanno.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws
            Throwable {
        System.out.println("环绕之前.........");
        //被增强的方法执行
        proceedingJoinPoint.proceed();
        System.out.println("环绕之后.........");
    }
}

```

3.在 spring 配置文件中开启生成代理对象

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--  开启注解扫描  -->
    <context:component-scan base-package="top.lep101.spring5.aopanno"/>

    <!--  开启Aspect生成代理对象  -->
    <aop:aspectj-autoproxy/>

</beans>
```

4.相同的切入点抽取(解耦)

```java
//相同切入点抽取
    @Pointcut(value = "execution(* top.lep101.spring5.aopanno.User.add(..))")
    public void pointDemo() {

    }

    //前置通知
    //@Before注解表示作为前置通知
    @Before(value = "pointDemo()")
    public void before() {
        System.out.println("before......");
    }
```

5.**有多个增强类多同一个方法进行增强，设置增强类优先级**

> 在增强类上面添加注解 @Order(数字类型值)，数字类型值越小优先级越高

```java
@Component
@Aspect
@Order(1)
public class PersonProxy
```

**6.完全使用注解开发**

**创建配置类，不需要创建 xml 配置文件** 

```java
@Configuration
@ComponentScan(basePackages = {"com.atguigu"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConfigAop {
}
```

