/*
 Navicat Premium Data Transfer

 Source Server         : 123.206.71.118
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 123.206.71.118
 Source Database       : activitybar

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 07/05/2017 07:36:10 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `activity`
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `surface` varchar(200) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `personnum` int(5) DEFAULT NULL,
  `cost` double(10,0) DEFAULT NULL,
  `contact` varchar(50) DEFAULT NULL,
  `detail` varchar(5000) DEFAULT NULL,
  `creator` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `creator` (`creator`),
  CONSTRAINT `activity_creator_fk` FOREIGN KEY (`creator`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `activity`
-- ----------------------------
BEGIN;
INSERT INTO `activity` VALUES ('1', '一起来夜跑', '2017-01-01', 'pic/14951674324440.jpg', '天津城建大学', '100', '0', '18302992058', '一起来夜跑', '2'), ('3', '自习', '2017-02-02', 'pic/14951674766142.1.jpg', '未央区', '40', '30', '23432432432', '自习室', '3'), ('4', '考研来', '2017-03-03', 'pic/14951674990223.jpg', 'weiyangqu', '34', '20', '23213213123', 'sadkjsahdk ', '3'), ('5', '骑行天津', '2017-03-19', 'pic/14951675139804.jpg', '天津', '2', '7', '12387238183', '一起骑行天津市', '3'), ('6', '穿越沙哈拉沙漠', '2017-03-28', 'pic/14951675409145.jpg', 'china', '10', '5', '32131231233', '徒步穿越沙哈拉沙漠', '3'), ('7', '我是歌手', '2017-04-18', 'pic/14951675534536.jpg', 'china', '1000', '10', '89378234883', '一展歌喉', '3'), ('8', '花儿与少年', '2017-05-11', 'pic/14951675742907.jpg', 'eather', '219', '100', '12312313134', '冒险，走你', '3'), ('9', '一起来探案', '2017-05-17', 'pic/14951675954508.jpg', 'club', '10', '50', '82374897472', '探案、逻辑、看你的', '3'), ('12', '爬山', '2017-05-19', 'pic/14951676216919.jpg', '你啊', '11', '11', '11', '11', '3'), ('16', '瑜伽训练', '2017-05-31', 'pic/149618778272112.jpg', '天津', '8', '10', '1236', '瑜伽', '3');
COMMIT;

-- ----------------------------
--  Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `admin`
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES ('1', 'ma', 'BBB8AAE57C104CDA40C93843AD5E6DB8');
COMMIT;

-- ----------------------------
--  Table structure for `cards`
-- ----------------------------
DROP TABLE IF EXISTS `cards`;
CREATE TABLE `cards` (
  `id` varchar(32) NOT NULL,
  `balance` double(10,2) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `isused` int(1) DEFAULT NULL,
  `user_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `cards_userid_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `cards`
-- ----------------------------
BEGIN;
INSERT INTO `cards` VALUES ('69A563C9A4C7B83E9C9B2C8AF22C9235', '0.00', '2017-05-02', '1', '1'), ('85A69A2E9EDC7E9EB7F3596A1200FC0E', '0.00', '2017-05-03', '1', '1'), ('A382024882291FE825B7D49D2D88C432', '0.00', '2017-05-17', '1', '6'), ('B04C206A21C2AF4F8F30246A1F41CB44', '10.00', '2017-05-03', '0', null), ('C9BBFE87EF390A2C11DEC906B3F2AF84', '1111.00', '2017-05-03', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `userid` int(5) DEFAULT NULL,
  `activityid` int(6) DEFAULT NULL,
  KEY `userid` (`userid`),
  KEY `activityid` (`activityid`),
  CONSTRAINT `link_activityid_fk` FOREIGN KEY (`activityid`) REFERENCES `activity` (`id`),
  CONSTRAINT `link_userid_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `item`
-- ----------------------------
BEGIN;
INSERT INTO `item` VALUES ('1', '1'), ('1', '3'), ('1', '8'), ('3', '5'), ('4', '5'), ('2', '1'), ('2', '6'), ('4', '6'), ('3', '6'), ('2', '7'), ('6', '1'), ('3', '12'), ('3', '4'), ('7', '1'), ('4', '7'), ('3', '7'), ('6', '6'), ('6', '3'), ('2', '4');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `balance` double(10,0) DEFAULT NULL,
  `gender` int(1) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'ma', 'E8BADA12034BFB60179E30BDD99193F0', '2271', '1', '22', 'maazhiyuan@qq.com'), ('2', 'mazhiyuan', 'E8BADA12034BFB60179E30BDD99193F0', '925', '1', '20', null), ('3', 'tom', '1BBD886460827015E5D605ED44252251', '941', '1', '20', null), ('4', 'tim', '1BBD886460827015E5D605ED44252251', '1041', '1', '20', null), ('5', 'tam', '1BBD886460827015E5D605ED44252251', '1000', '1', '20', null), ('6', 'lele', 'FCDE6039075773191663F153A72784B1', '14', '1', '22', null), ('7', 'le', 'FCDE6039075773191663F153A72784B1', '0', '1', '20', null);
COMMIT;

-- ----------------------------
--  Table structure for `useropenid`
-- ----------------------------
DROP TABLE IF EXISTS `useropenid`;
CREATE TABLE `useropenid` (
  `user_id` int(6) DEFAULT NULL,
  `openid` varchar(100) DEFAULT NULL,
  KEY `openid` (`openid`),
  KEY `user_id` (`user_id`),
  KEY `openid_2` (`openid`),
  CONSTRAINT `useropenid_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `useropenid`
-- ----------------------------
BEGIN;
INSERT INTO `useropenid` VALUES ('2', 'oG9BMwzQtjbLl_GywGs2QnwZz4Y4'), ('1', 'oG9BMw133tXeGD-135rmcA_QlmEc'), ('6', 'oG9BMw4vf2j6KRK8mG9YolC2freE');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
