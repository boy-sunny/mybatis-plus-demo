SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `age` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1559443677976227842 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `name`, `age`) VALUES (1, '张三', 18);
INSERT INTO `sys_user` (`id`, `name`, `age`) VALUES (2, '李四', 11);
INSERT INTO `sys_user` (`id`, `name`, `age`) VALUES (3, '王五', 33);
INSERT INTO `sys_user` (`id`, `name`, `age`) VALUES (1559011432316645377, '马六', 43);
INSERT INTO `sys_user` (`id`, `name`, `age`) VALUES (1559017554834382849, 'String', 30);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
