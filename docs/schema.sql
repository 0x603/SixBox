-- 数据库
DROP DATABASE IF EXISTS `SixBox`;
CREATE DATABASE `SixBox`;
USE `SixBox`;

-- 用户
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  id            INTEGER   NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username      CHAR(255) NOT NULL UNIQUE,
  password      CHAR(255) NOT NULL,
  userType      INTEGER,
  createTime    DATETIME,
  createIp      CHAR(127),
  lastLoginTime DATETIME,
  lastLoginIp   CHAR(127),
  gender        INTEGER,
  email         CHAR(127),
  introduction  TEXT
)
  DEFAULT CHARSET = UTF8;
