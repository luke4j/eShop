[TOC]

# eshop 开发文档

## 约定

### 日志

> 1.日志优先 也就是想使用日志，要在代码之前 

例如：

```java
public void tt(){
	log.inof("进入方法 tt  ") ;
	//程序代码
}
```

> 2.异常必有日志

```java
try{
    //程序代码       
}catch (Exception e){
    log.error(e.getClass()+":->"+e.getMessage()) ；
    //处理异常代码
}
```





### 表,java类，js文件命名与路径

> 表名与映射类名完全相同

> 命名规则，路径，类，与js文件
>
> 列如业务是商品信息
>
> java.model->com.luke.model.TG_Goods.java
>
> /** 接口与实现分开，以便代码与注释与annotation不用太混乱
>
> Url地址使用     类名_方法名.act命名
>
> 例 
>
> 
>
>  */
>
> java.action ->com.luke.shop.eshop.goods.action.IGoodsAction
>
> java.action ->com.luke.shop.eshop.goods.action.impl.GoodsAction
>
> /** 同一个Action只有一个Service支持  实现每一个Action 都可以独立运行 */
>
> java.service->com.luke.shop.eshop.goods.service.IGoodsService
>
> java.service->com.luke.shop.eshop.goods.service.impl.GoodsService
>
> /**不同业务的Dao方法不通用，但名字要相同，程序代码量大以后不会出现改这里错那里的现象 */
>
> java.dao -> com.luke.shop.eshop.goods.dao.IGoodsDao
>
> java.dao -> com.luke.shop.eshop.goods.dao.impl.GoodsDao
>
> /**每一个业务建一个VO类，以便使用  java  validation  进行验证  */
>
> java.vo -> com.luke.shop.eshop.goods.vo.VOGoodsAdd
>
> java.vo -> com.luke.shop.eshop.goods.vo.VOGoodsEdit
>
> java.vo -> com.luke.shop.eshop.goods.vo.VOGoodsFind
>
> java.vo -> com.luke.shop.eshop.goods.vo.VOGoodsPrice
>
> .......
>
> 类中方法命名需要添加编号，这样写方便写测试代码，不至于少写
>
> 例
>
> com.luke.shop.eshop.action.IGoodsAction.addGoods_1
>
> com.luke.shop.eshop.action.IGoodsAction.eidtGoods_2
>
> com.luke.shop.eshop.action.IGoodsAction.delGoods_3
>
> //页面功能 js入口文件
>
> js.view ->app/goods/goods_view.js
>
> //页面功能js入口文件使用的html模板
>
> js.view ->app/goods/goods_view.html
>
> //页面功能js入口文件可能会很大，这个文件处理页面初始化代码
>
> js.help ->app/goods/goods_view_help.js
>
> //页面功能js入口文件中可以提出来的公用js文件
>
> js.unit ->app/goods/goods_view_unit_find_goods.js
>
> js.unit ->app/goods/goods_view_unit_show_list.js
>
> ==注意：js 文件的路径与java action 的路径相同 例如上例中  java代码类路径是goods/action/IGoodsAction ,js代码文件路径就是goods/goods_view.js==

### 数据库

1.表字段数量不能超15个，尽量使用数字类型

2.如果想要定义的字段与数据库关键字冲突可以在前面加  标识   'c_'   例 想用字段名为unique 定义时就是 c_unique

3.如果字段使用拼音，请用全拼，每个字中间用  ‘_’ 分开   例  字段 学历 为  xue_li

***



## 测试

### java 测试 junit

工具类测试代码与在src/test/java/tool

Action测试代码写在src/test/java/action

Service测试代码写在src/test/java/service

Dao测试代码写在/src/test/java/dao



测试原则 

工具类与Action类的方法必测，

service,dao方法在不确定时写测试

测试时也要写接口，其接口方法与被测试类接口方法名类似

例 

需要测试的  方法  

/** 添加商品信息。。。。 */

IGoodsAction.addGoods_1

测试类接口的方法 

/** 

目地：测试添加度数商品

结果：数据库中TG_Goods表添加商品信息成功

 */

ITestGoods.addGoods_1_1

### js

***



## 整个程序 目录

### java

src/main/java

├─java
│  └─com
│      └─luke
│          └─shop
│              ├─eshop
│              ├─model
│              └─tool
├─resources

src/test/java

├─java
│  ├─action
│  ├─dao
│  ├─service
│  └─tool
└─resources

### js

src/mian/java/webapp

├─api
├─app
├─file
├─js
├─plugin
└─WEB-INF

***

#### app

app.js

程序入口文件

J.js

程序公用方法



package.js

加载固定的js文件

S.js

程序js静态变量

Style.css

#### api

Swagger   页面文件

#### js

使用的js组件

包括jquery ;bootstrap;require;backbone;underscore

##### jquery

js 组件，支持跨浏览器与代码编写

##### bootstrap

多浏览器与浏览器大小支持，并且很美观

##### require

动态加载js文件

##### backbone

代码组织，一个页面级的mvc

##### underscore



***



## 数据库与java映射Model

### TU_User   //用户信息

```sql
id bigint  primary key
loginName varchar(30)  unique not null  --登录名
password varchar(40)  not null	--登录密码
name varchar(30)  not null --性别
sex varchar(2) not null 
birthday date	--	生日
roleId bigint	--角色id
phone varchar(15) -- 手机号
phone2 varchar(15) -- 紧急联系人手机号
xue_li varchar(10) -- 学历 
zhi_wu varchar(20) -- 职务
photo varchar(50)  --照片

```

### TU_Com   //公司信息

```sql
id bigint primary key
name varchar(60)
addr varchar(100)
phone varchar(15)
managerId bigint
```



### TU_Store   //站点信息

```sql
id bigint primary key
name varchar(60)
addr varchar(100)
phone varchar(15)
managerId bigint
```

### TU_Role    //角色信息

```sql
id bigint primary key
name varchar(40)
c_explain varchar(50)
```



### TU_Fun 	  //可用功能信息

```sql
id bigint primary key 
name varchar(40)  -- 界面显示名
c_explain varchar(50)  --说明
jsFile varchar(120)  --功能js文件入口
```



### TU_RoleRun //角色与功能关系信息

```sql
id bigint primary key 
roleId bigint not null
funId bigint not null
```





***



## java  自定义工具类

### 异常 Exception

#### GlobalException

​	全局异常

#### AppMsgException

​	程序异常信息

### GlobalVal

​	全局常量

### LK

​	全局公共方法，主要包括时间，字符串

#### String uuid()

生成uuid

#### Boolean StrIsEmpty(String str)

字符串为空，"" or null 返回true

#### Boolean StrIsNotEmpty(String str)

字符串不为空，返回true

#### Boolean StrIsEmptyDo(String str,String def)

字符串为空返回默认值 不为空返回本身，为空返回默认值 

#### Boolean StrIsNum(String str)

字符串是否数字 是返回true

是返回 true

#### Boolean StrIsDate(String str,String format)

字符串是否日期

是返回true

#### Date StrToDate(String str,String format)

字符串按指定格式返回日期     2001-01-01  "yyyy-MM-dd"

#### Date StrToDate_YMD(String str)

#### Date StrToDate_YMDHMS(String str)

#### String DateToStr(Date date ,String format)

时间格式转字符串

#### String DateToStr_Y(Date date)

时间格式转年

#### String DateToStr_YM(Date date)

时间格式转  年月

#### String DateToStr_YMD(Date date)

时间格式转	年月日

#### String Lens(String str)

字符串转度数格式   0 ;+1.50;-2.00

#### String Lens (Float num)

数字转度数格式   0 ;+1.50;-2.00

#### String NameToPinYinL(String str)

中文名字转拼音  首字母

#### String NameToYinYinS(String str)

中文名字转拼音，全拼

#### String MD5(String str)

md5加密

#### int BirthdayToAge(Date birthday)

生日转年龄

#### int BirthdayToAge(String birthday)

生日转年龄

#### AddDay(Date day,int num)

日期加减

### DB

​	主要是Dao拼接ql语句方法

### l

​	程序自定义日志

#### public static l getl(String tag)

​	静态构造方法

#### public void i(String msg)

​	信息输出

#### public void d(String msg)

​	测试信息输出 

#### public void e(String msg)

​	异常信息输出 





### 断言 Assertion

​	断言类 Assertion

#### Error()

​	程序异常

#### Error(String msg)

​	程序异常   给定信异常信息

#### Empty(Object obj,String msg)

​	断言对象为空

#### NotEmpty(Object obj,String msg)

​	断言对象不为空

#### Equals(Object obj1,Object obj2,String msg)

​	断言对象相同

#### NotEquals(Object obj1,Object obj2,String msg)

​	断言对象不相同

#### EqualsCanNull(Object obj1,Object obj2,String msg)

​	断言对象相同，如果一个对象是空，不做断言

#### void  (Object obj1,Object obj2,String msg)

​	断言对象不相同，如果一个对象是空，不做断言



### 后台返回数据格式   ActionResult

### Action类的基类  BaseAction

***



## 功能

### 登录 

#### 准备

插入数据 

#### 数据 model

TU_User,TU_Com,TU_Store,TU_Role,TU_RoleFun,TU_Fun

#### http 对外接口 action

##### ILoginAction

###### gotoLogin_1 

​	 跳转到登录页面

###### login_2  

​	登录功能

​	验证登录是否成功，成功进入程序功能页面，不成功给出提示

###### getInfo_3 

​	 得到登录人的可用信息

​	登录人的可用信息包括，登录时间，用户角色，可用功能，登录用户的站内信息，特殊功能  这几项数据 

###### logout_4 

​	登出功能

​	清空session

findCom_5

​	查询所有公司，为登录页面的公司下拉组件提供数据

***

##### 登录功能 图示说明

```sequence
title:登录功能
note left of login.view.js:登录入口文件
note left of ILoginAction:登录后台接口
note left of ILoginService:登录业务接口
note left of ILoginDao:登录查询数据库接口

login.view.js ->login.view.js:initialize 初始化
login.view.js ->login.view.js:render 加载html,加载后台数据
login.view.js->login.view.js:events 添加事件
note left of ILoginAction:login方法接收参数 {loginName:str,password:str,com:str}
login.view.js->ILoginAction:ajax访问 login/login.act 
ILoginAction->ILoginService:findLogin  
ILoginService->ILoginDao:loginDao 查询用户，查询不到抛出异常
ILoginDao-->ILoginService:返回用户信息
ILoginService->ILoginDao:getComById userId 返回用户所在公司信息
ILoginService->ILoginService:判断用户类型，用户信息与公司判断是否可以登录
ILoginAction-->login.view.js:返回ActionResult
login.view.js->login.view.js:成功进入main.view


```



```sequence
title:获取登录详细信息，并初始化程序主界面
note left of main.view.js:程序主界面
main.view.js->main.view.js:render 介面初始化 1.获取信息（login/getInfo.act）;2.设置导航条；3。设置时间，公司，站点
main.view.js->main.view.js:events 添加界面事件 1.导航条.查看用户信息;2.导航条.修改密码;3.导航条.登出;4.返回主界面

```

***











