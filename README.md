### 目录结构
* api_model     定义接收前端参数与返回值
* controllers   控制器，门户界面的控制器与bingo系统的控制器
* interceptions 过滤器，用于添加过滤规则
* models        模型用于与数据库进行交互
* service       业务层代码放在此处
* util          工具类的集合

### TODO
1. 登陆身份验证用什么方式？权限检查？
2. 实现前端接口
3. 事务
4. 日志管理
5. 上传下载跟表单分离还是做在一起?
6. 


### 注意事项
1. 在FileService中设置文件保存根路径

#### MyPlan
* 拦截器，权限控制用注解实现
* nosql实现文件管理

#### leapframework爬坑指南
1. model层大驼峰对应数据库下划线
2. 每个表都要有主键
3. 表命在映射到数据库中自动小写

### 运行指令
1. mvn: jetty:run
