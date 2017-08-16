# SixBox文档

## 开发环境配置

### IDEA
* `Check out from Version Control|GitHub|0x603/SixBox`

### 数据库

#### 连接配置文件
在`src`目录下新建`db.properties`文件，并输入如下配置。
```
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/SixBox
jdbc.username=<MySQL用户名>
jdbc.password=<MySQL密码>
```

#### 建立数据库
使用[schema.sql](schema.sql)中的语句建立数据库。

### 服务器
* `Run|Edit Configurations...|+|Tomcat Server|Local`
  * 设置`Name`, `Application server`
  * 点击下方`Warning`的`Fix`
