/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50023
Source Host           : localhost:3306
Source Database       : mypro

Target Server Type    : MYSQL
Target Server Version : 50023
File Encoding         : 65001

Date: 2014-04-30 17:07:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `c3p0testtable`
-- ----------------------------
DROP TABLE IF EXISTS `c3p0testtable`;
CREATE TABLE `c3p0testtable` (
  `a` char(1) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c3p0testtable
-- ----------------------------

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(50) NOT NULL,
  `sort` int(10) NOT NULL auto_increment,
  `create_date` datetime default NULL,
  `update_date` datetime default NULL,
  `create_by` varchar(50) default NULL,
  `update_by` varchar(50) default NULL,
  `win_name` varchar(200) default NULL,
  `pid` varchar(50) default NULL COMMENT '父节点ID',
  `name` varchar(50) default NULL COMMENT '菜单名称',
  `url` varchar(200) default NULL COMMENT '菜单链接',
  `nlevel` int(10) default NULL COMMENT '级别',
  PRIMARY KEY  (`id`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(50) NOT NULL,
  `sort` int(11) NOT NULL auto_increment,
  `create_date` datetime default NULL,
  `update_date` datetime default NULL,
  `create_by` varchar(50) default NULL,
  `update_by` varchar(50) default NULL,
  `name` varchar(50) default NULL COMMENT '权限名称',
  `type` varchar(10) default NULL COMMENT '权限类型',
  `code` varchar(50) default NULL COMMENT '权限编码',
  `is_enabled` varchar(1) default NULL COMMENT '启用状态',
  PRIMARY KEY  (`id`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` varchar(50) NOT NULL,
  `role_id` varchar(50) default NULL,
  `menu_id` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(50) NOT NULL,
  `sort` int(10) NOT NULL auto_increment,
  `create_date` datetime default NULL,
  `update_date` datetime default NULL,
  `create_by` varchar(50) default NULL,
  `update_by` varchar(50) default NULL,
  `user_name` varchar(100) default NULL,
  `password` varchar(100) default NULL,
  `real_name` varchar(20) default NULL,
  `sex` varchar(1) default NULL,
  `age` int(2) default NULL,
  `state` varchar(1) default NULL,
  PRIMARY KEY  (`id`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '2014-04-30 15:36:35', '2014-04-30 15:36:38', '1', '1', '1', '356a192b7913b04c54574d18c28d46e6395428ab', '管理员', '1', '20', '1');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) default NULL,
  `role_id` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
