CREATE DATABASE rf_mall;
CREATE TABLE user(
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
    `phone` varchar(15) NOT NULL DEFAULT '' COMMENT '手机号',
    `email` varchar(30) NOT NULL DEFAULT '' COMMENT '电子邮箱',
    `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码（加密后）',
    `salt` varchar(10) NOT NULL DEFAULT '' COMMENT '密码盐',
    `deleted` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0-否；1-是',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'DB创建时间，业务无关',
    `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'DB最后修改时间，业务无关',
    PRIMARY KEY (`id`),
    KEY `idx_username_salt_password` (`user_name`,`salt`, `password`),
    KEY `idx_phone_salt_password` (`phone`,`salt`, `password`),
    KEY `idx_email_salt_password` (`email`,`salt`, `password`)
)COMMENT = '用户表';

CREATE TABLE product(
     `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
     `catalog_id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT  '类型id',
     `product_name` varchar(30) NOT NULL DEFAULT '' COMMENT '商品名称',
     `description` varchar(200) NOT NULL DEFAULT '' COMMENT '描述',
     `images` varchar(200) NOT NULL DEFAULT '' COMMENT '商品图片',
     `price` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '价格 (*10000保存)',
     `deleted` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0-否；1-是',
     `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
     `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'DB创建时间，业务无关',
     `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'DB最后修改时间，业务无关',
     PRIMARY KEY (`id`)
)COMMENT = '商品表';

CREATE TABLE order(
     `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
     `order_no` varchar(40) NOT NULL DEFAULT '' COMMENT '订单号',
     `user_id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT  '用户id',
     `total_price` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '总价 (*10000保存)',
     `deleted` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0-否；1-是',
     `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
     `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'DB创建时间，业务无关',
     `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'DB最后修改时间，业务无关',
     PRIMARY KEY (`id`)
)COMMENT = '订单表';

CREATE TABLE order_product(
     `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
     `order_id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT  '订单id',
     `order_no` varchar(40) NOT NULL DEFAULT '' COMMENT '订单号',
     `user_id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT  '用户id',
     `product_id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT  '商品id',
     `product_name` varchar(30) NOT NULL DEFAULT '' COMMENT '商品名称',
     `single_price` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '单价 (*10000保存)',
     `count` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '件数',
     `deleted` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0-否；1-是',
     `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
     `db_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'DB创建时间，业务无关',
     `db_updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'DB最后修改时间，业务无关',
     PRIMARY KEY (`id`)
)COMMENT = '订单表';

