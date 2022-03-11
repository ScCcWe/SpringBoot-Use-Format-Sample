create database boot_example;

use boot_example;

create table example_category(
    id bigint(20) unsigned primary key auto_increment,
    name varchar(64) not null unique comment '分类名',
    parent_category_id bigint(20) not null comment '父分类id',
    category_level int(10) not null comment '层级'
)engine=innodb
auto_increment=1
default charset=utf8

insert into `example_category`(`id`, `name`, `parent_category_id`, `category_level`) values 
    (1, '手机', 0, 1),
    (2, '手机通讯', 1, 2),
    (3, '手机配件', 1, 2),
    (4, '游戏手机', 2, 3),
    (5, '5G手机', 2, 3),
    (6, '拍照手机', 2, 3),
    (7, '全面屏手机', 2, 3),
    (8, '手机壳', 3, 3),
    (9, '数据线', 3, 3),
    (10, '手机饰品', 3, 3),
    (11, '苹果周边', 3, 3);
