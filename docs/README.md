# SixBox文档

## 开发环境配置说明

### 数据库配置

#### 连接配置文件
在`src`目录下新建`db.properties`文件，并输入如下配置。
```
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/SixBox
jdbc.username=<MySQL用户名>
jdbc.password=<MySQL密码>
```

#### 数据库结构
[schema.sql](schema.sql)
