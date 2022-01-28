drop database if exists `brian`;

create database `brian`;
use `brian`;

create table `user` (
    id bigint(11) not null primary key auto_increment,
    username varchar(32) not null comment '用户名',
    age int(4) comment '年龄',
    sex tinyint(1) comment '性别'
);