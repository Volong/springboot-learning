CREATE DATABASE test DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE users (
                     id BIGINT NOT NULL COMMENT '主键id',
                     userName VARCHAR(16) NOT NULL COMMENT '用户名',
                     passWord VARCHAR(16) NOT NULL COMMENT '密码',
                     user_sex VARCHAR(6) NOT NULL COMMENT '性别',
                     nick_name VARCHAR(16) COMMENT '昵称',
                     PRIMARY KEY (id)
) ENGINE = INNODB auto_increment = 1 DEFAULT CHARSET = utf8 COMMENT '用户表';