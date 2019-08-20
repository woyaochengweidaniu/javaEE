/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 12/07/2019 20:52:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hobby` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '刘备', 21, '男', '楚国', '12321313', '睡觉', '1989-02-06', '2019-07-04 17:26:30');
INSERT INTO `user` VALUES (2, '孔明', 24, '男', '上海市黄浦区', '544554545', '象棋', '1987-06-05', '2019-07-04 17:26:34');
INSERT INTO `user` VALUES (3, '云长', 43, '男', '杭州市仓前区', '1236785429', '打篮球', '1981-05-05', '2019-07-04 17:26:37');
INSERT INTO `user` VALUES (4, '小明', 24, '男', '上海市浦东新区', '1323342543', '睡觉', '1995-05-08', NULL);
INSERT INTO `user` VALUES (5, '小花', 22, '女', '上海市黄浦区', '1254548961', '钢琴', '1998-07-09', NULL);

SET FOREIGN_KEY_CHECKS = 1;
