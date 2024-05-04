SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_user`
(
    `id`          bigint       NOT NULL COMMENT '用户ID',
    `username`    varchar(64)  NOT NULL COMMENT '用户账号',
    `password`    varchar(512) NOT NULL COMMENT '用户密码',
    `nickname`    varchar(64)  DEFAULT NULL COMMENT '用户昵称',
    `email`       varchar(64)  DEFAULT NULL COMMENT '用户邮箱',
    `phone`       varchar(11)  DEFAULT NULL COMMENT '用户手机号码',
    `gender`      tinyint(1)   DEFAULT '0' COMMENT '用户性别:0-保密,1-男,2-女',
    `avatar`      varchar(512) DEFAULT NULL COMMENT '用户头像地址',
    `type`        tinyint(1)   DEFAULT '0' COMMENT '用户类型:0-系统用户',
    `state`       tinyint(1)   DEFAULT '0' COMMENT '用户帐号状态:0-启用,1-禁用',
    `remark`      varchar(512) DEFAULT NULL COMMENT '备注',
    `creator`     bigint       DEFAULT NULL COMMENT '创建人',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     bigint       DEFAULT NULL COMMENT '更新人',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_flag` tinyint(1)   DEFAULT '0' COMMENT '删除标志:0-未删除,1-已删除',
    `version`     int          DEFAULT '0' COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          bigint       NOT NULL COMMENT '菜单ID',
    `name`        varchar(64)  NOT NULL COMMENT '菜单名称',
    `order`       int          NULL DEFAULT 0 COMMENT '显示顺序',
    `path`        varchar(128) NULL DEFAULT NULL COMMENT '路由地址',
    `component`   varchar(128) NULL DEFAULT NULL COMMENT '组件路径',
    `icon`        varchar(128) NULL DEFAULT NULL COMMENT '菜单图标',
    `type`        tinyint(1)   NULL DEFAULT NULL COMMENT '菜单类型:1-目录,2-菜单,3-按钮',
    `visible`     tinyint(1)   NULL DEFAULT 0 COMMENT '显隐标识:0-显示,1-隐藏',
    `status`      tinyint(1)   NULL DEFAULT 0 COMMENT '菜单状态:0-正常,1-停用',
    `remark`      varchar(512) NULL DEFAULT NULL COMMENT '备注',
    `creator`     bigint       NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime     NULL DEFAULT NULL COMMENT '创建时间',
    `updater`     bigint       NULL DEFAULT NULL COMMENT '更新人',
    `update_time` datetime     NULL DEFAULT NULL COMMENT '更新时间',
    `delete_flag` tinyint(1)   NULL DEFAULT NULL COMMENT '删除标志:0-未删除,1-已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '菜单信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`          bigint       NOT NULL COMMENT '权限ID',
    `name`        varchar(64)  NOT NULL COMMENT '权限名称',
    `code`        varchar(64)  NOT NULL COMMENT '权限码',
    `remark`      varchar(512) NULL DEFAULT NULL COMMENT '备注',
    `status`      tinyint(1)   NULL DEFAULT 0 COMMENT '权限状态:0-正常,1-禁用',
    `creator`     bigint       NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime     NULL DEFAULT NULL COMMENT '创建时间',
    `updater`     bigint       NULL DEFAULT NULL COMMENT '更新人',
    `update_time` datetime     NULL DEFAULT NULL COMMENT '更新时间',
    `delete_flag` tinyint(1)   NULL DEFAULT 0 COMMENT '删除标志:0-未删除,1-已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '权限信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint       NOT NULL COMMENT '角色ID',
    `name`        varchar(64)  NOT NULL COMMENT '角色名称',
    `code`        varchar(128) NOT NULL COMMENT '权限码',
    `status`      tinyint(1)   NULL DEFAULT 0 COMMENT '角色状态:0-正常,1-禁用',
    `remark`      varchar(512) NULL DEFAULT NULL COMMENT '备注',
    `creator`     bigint       NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime     NULL DEFAULT NULL COMMENT '创建时间',
    `updater`     bigint       NULL DEFAULT NULL COMMENT '更新人',
    `update_time` datetime     NULL DEFAULT NULL COMMENT '更新时间',
    `delete_flag` tinyint(1)   NULL DEFAULT 0 COMMENT '删除标志:0-未删除,1-已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          bigint     NOT NULL COMMENT '主键ID',
    `role_id`     bigint     NOT NULL COMMENT '角色ID',
    `menu_id`     bigint     NOT NULL COMMENT '菜单ID',
    `creator`     bigint     NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime   NULL DEFAULT NULL COMMENT '创建时间',
    `updater`     bigint     NULL DEFAULT NULL COMMENT '更新人',
    `update_time` datetime   NULL DEFAULT NULL COMMENT '更新时间',
    `delete_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志:0-未删除,1-已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关联表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `id`            bigint     NOT NULL COMMENT '主键ID',
    `role_id`       bigint     NOT NULL COMMENT '角色ID',
    `permission_id` bigint     NOT NULL COMMENT '权限ID',
    `creator`       bigint     NULL DEFAULT NULL COMMENT '创建人',
    `create_time`   datetime   NULL DEFAULT NULL COMMENT '创建时间',
    `updater`       bigint     NULL DEFAULT NULL COMMENT '更新人',
    `update_time`   datetime   NULL DEFAULT NULL COMMENT '更新时间',
    `delete_flag`   tinyint(1) NULL DEFAULT 0 COMMENT '删除标志:0-未删除,1-已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色权限关联表'
  ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `sys_user`;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          bigint     NOT NULL COMMENT '主键ID',
    `user_id`     bigint     NOT NULL COMMENT '用户ID',
    `role_id`     bigint     NOT NULL COMMENT '角色ID',
    `creator`     bigint     NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime   NULL DEFAULT NULL COMMENT '创建时间',
    `updater`     bigint     NULL DEFAULT NULL COMMENT '更新人',
    `update_time` datetime   NULL DEFAULT NULL COMMENT '更新时间',
    `delete_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志:0-未删除,1-已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联表'
  ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
