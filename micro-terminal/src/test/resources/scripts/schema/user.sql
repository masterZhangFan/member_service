DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` char(36) NOT NULL DEFAULT '' COMMENT '主键ID',
  `user_name` varchar(64) DEFAULT '' COMMENT '用户名称',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登录名称',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

