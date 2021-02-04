/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : app_monitor

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 02/02/2021 16:52:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_app_info
-- ----------------------------
CREATE TABLE if not exists `t_app_info` (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'app id',
  `kind` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'app种类',
  `track_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '专辑名称',
  `version` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '版本号',
  `bundle_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'bundle id',
  `artist_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '专辑id',
  `screenshot_urls` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '快照图片url',
  `artwork_url512` text,
  `artwork_url60` text,
  `artwork_url100` text,
  `supported_devices` text,
  `file_size_bytes` double(10,0) DEFAULT NULL COMMENT '文件大小',
  `average_user_rating` float(5,0) DEFAULT NULL COMMENT '用户打分分均分',
  `formatted_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '价格',
  `minimum_os_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支持系统最小版本号',
  `current_version_release_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '当前版本号',
  `release_notes` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '发布说明',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '简介',
  `content_advisory_rating` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `update_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除字段',
  UNIQUE KEY `uk_app_id` (`id`) USING BTREE COMMENT 'appid唯一索引',
  UNIQUE KEY `uk_bundle_id` (`bundle_id`(50)) USING BTREE COMMENT 'bundle_id唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='App详细信息\n';

-- ----------------------------
-- Table structure for t_app_outline
-- ----------------------------
CREATE TABLE if not exists  `t_app_outline` (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'app id',
  `kind` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `artist_name` varchar(255) DEFAULT NULL,
  `copyright` varchar(255) DEFAULT NULL,
  `artist_id` varchar(50) DEFAULT NULL,
  `artist_url` text,
  `artwork_url100` text,
  `url` text,
  `genres` varchar(255) DEFAULT NULL,
  `country_code` varchar(10) DEFAULT NULL,
  `media_type` varchar(255) DEFAULT NULL,
  `feed_type` varchar(50) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `update_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_id` (`id`(20)) USING BTREE COMMENT 'app id唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='App简介信息';

-- ----------------------------
-- Table structure for t_country
-- ----------------------------
CREATE TABLE if not exists  `t_country` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `country_name` varchar(255) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_data_fetch_recoder
-- ----------------------------
CREATE TABLE if not exists  `t_data_fetch_recoder` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_data_fetch_recoder_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_genre
-- ----------------------------
CREATE TABLE if not exists  `t_genre` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name_url` (`name`(10),`url`(100)) USING BTREE COMMENT '类别名字'
) ENGINE=InnoDB AUTO_INCREMENT=6450 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE if not exists  `t_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像url',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别（0女，1男）',
  `age` tinyint DEFAULT NULL COMMENT '年龄',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户角色',
  `state` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '用户账号状态（0:封禁不可用，1正常）',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username_index` (`username`) USING BTREE COMMENT '用户名唯一索引',
  UNIQUE KEY `uk_email_index` (`email`) USING BTREE COMMENT '邮箱唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Table structure for t_user_favour
-- ----------------------------
CREATE TABLE if not exists  `t_user_favour` (
  `id` bigint unsigned NOT NULL COMMENT '主键id',
  `user_id` bigint unsigned NOT NULL COMMENT '用户id',
  `app_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'app id',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关注';

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
CREATE TABLE if not exists  `t_user_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `tag` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色标签',
  `menu` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色对应菜单',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role_index` (`role`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
CREATE TABLE if not exists  `undo_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
