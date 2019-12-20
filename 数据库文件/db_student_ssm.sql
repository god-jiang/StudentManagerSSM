/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : db_student_ssm

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2018-11-28 21:23:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `c3p0testtable`
-- ----------------------------
DROP TABLE IF EXISTS `c3p0testtable`;
CREATE TABLE `c3p0testtable` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c3p0testtable
-- ----------------------------

-- ----------------------------
-- Table structure for `clazz`
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gradeId` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `remark` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gradeId` (`gradeId`),
  CONSTRAINT `clazz_ibfk_1` FOREIGN KEY (`gradeId`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('12', '13', '网络工程1801', '网络工程专业2018届1班');
INSERT INTO `clazz` VALUES ('13', '1', '软件工程1101', '软件工程1101班');
INSERT INTO `clazz` VALUES ('14', '12', '计算机1101', '计算机专业1101班');
INSERT INTO `clazz` VALUES ('18', '12', '计算机1102', '计算机2班');

-- ----------------------------
-- Table structure for `grade`
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `remark` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1', '软件工程2011届', '计算机学院软件工程专业2011届');
INSERT INTO `grade` VALUES ('12', '计算机2011届', '计算机学院计算机专业2011届。');
INSERT INTO `grade` VALUES ('13', '网络工程2018届', '计算机学院网络工程专业2018届。');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clazzId` int(11) NOT NULL,
  `sn` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `sex` varchar(8) NOT NULL,
  `photo` varchar(128) NOT NULL,
  `remark` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gradeId` (`clazzId`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`clazzId`) REFERENCES `clazz` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('24', '13', 'S1543327958875', '张三', '123', '男', '/StudentManagerSSM/upload/1543408794070.jpg', '张三是河南人。');
INSERT INTO `student` VALUES ('25', '13', 'S1543328011478', '李四', '123456', '女', '/StudentManagerSSM/upload/1543408773940.jpg', '李四是河南人。');
INSERT INTO `student` VALUES ('27', '14', 'S1543329112844', '张军', '1221212', '男', '/StudentManagerSSM/photo/student.jpg', '好学生。');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin');
INSERT INTO `user` VALUES ('2', '猿来入此', '123456');
INSERT INTO `user` VALUES ('8', '猿来入此站长', '110');
