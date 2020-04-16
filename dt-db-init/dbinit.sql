-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL COMMENT '产品目录名称',
  `category_type` int(11) NOT NULL COMMENT '产品目录类型，用于存储特定类型的商品',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('1', '热饮', '99', '2019-03-20 22:47:41', '2019-03-20 22:47:41');
INSERT INTO `product_category` VALUES ('2', '酒水', '98', '2019-03-20 22:48:13', '2019-03-20 22:48:13');
INSERT INTO `product_category` VALUES ('3', '甜品', '97', '2019-03-20 22:47:51', '2019-03-20 22:47:51');


DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_price` decimal(8,2) DEFAULT NULL,
  `product_description` varchar(255) DEFAULT NULL,
  `product_icon` varchar(255) DEFAULT NULL,
  `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态， 0正常  1下架',
  `category_type` int(11) DEFAULT NULL COMMENT '产品目录',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '拿铁咖啡', '99', '20.99', '咖啡，提神醒脑', null, '0', '99', '2019-03-20 22:49:47', '2019-03-20 22:49:50');
INSERT INTO `product` VALUES ('2', '青岛纯生', '200', '7.50', '啤酒', null, '0', '98', '2019-03-20 22:50:48', '2019-03-20 22:50:55');
INSERT INTO `product` VALUES ('3', '卡布奇诺', '87', '15.00', '卡布奇诺的香味', null, '0', '99', '2019-03-20 22:51:53', '2019-03-20 22:51:56');
