SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_operate_log`
(
    `id`                  bigint NOT NULL COMMENT '日志ID',
    `operate_type`        tinyint(1)    DEFAULT NULL COMMENT '操作类型:0-未知,1-登录,2-登出,3-新增,4-更新,5-查询,6-删除',
    `operate_message`     varchar(64)   DEFAULT NULL COMMENT '操作类型说明',
    `operate_description` varchar(256)  DEFAULT NULL COMMENT '操作描述',
    `request_time`        datetime      DEFAULT NULL COMMENT '请求时间',
    `request_ip`          varchar(64)   DEFAULT NULL COMMENT '请求IP',
    `request_area`        varchar(256)  DEFAULT NULL COMMENT '请求地区',
    `request_ua`          varchar(512)  DEFAULT NULL COMMENT '请求浏览器UA',
    `request_url`         varchar(256)  DEFAULT NULL COMMENT '请求接口',
    `request_method`      varchar(256)  DEFAULT NULL COMMENT '请求方法',
    `request_mode`        varchar(64)   DEFAULT NULL COMMENT '请求方式',
    `request_param`       text          DEFAULT NULL COMMENT '请求参数',
    `response_state`      tinyint(1)    DEFAULT NULL COMMENT '响应状态:0-异常,1-正常',
    `response_time`       datetime      DEFAULT NULL COMMENT '正常响应时间',
    `response_consume`    bigint        DEFAULT NULL COMMENT '响应耗时:单位毫秒',
    `response_data`       varchar(2048) DEFAULT NULL COMMENT '响应数据',
    `error_time`          datetime      DEFAULT NULL COMMENT '异常响应时间',
    `error_message`       varchar(2048) DEFAULT NULL COMMENT '异常信息',
    `creator`             bigint        DEFAULT NULL COMMENT '创建人',
    `create_time`         datetime      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`             bigint        DEFAULT NULL COMMENT '更新人',
    `update_time`         datetime      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_flag`         tinyint(1)    DEFAULT '0' COMMENT '删除标志:0-未删除,1-已删除',
    `version`             int           DEFAULT '0' COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='操作日志表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_user`
(
    `id`          bigint       NOT NULL COMMENT '用户ID',
    `username`    varchar(128) NOT NULL COMMENT '用户账号',
    `password`    varchar(512) NOT NULL COMMENT '用户密码',
    `nickname`    varchar(64)  DEFAULT NULL COMMENT '用户昵称',
    `idno`        varchar(64)  DEFAULT NULL COMMENT '用户身份证号码',
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
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='用户信息表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_role`
(
    `id`          bigint       NOT NULL COMMENT '角色ID',
    `name`        varchar(64)  NOT NULL COMMENT '角色名称',
    `description` varchar(256) NOT NULL COMMENT '角色描述',
    `pid`         bigint       DEFAULT '0' COMMENT '父角色ID',
    `state`       tinyint(1)   DEFAULT '0' COMMENT '角色状态:0-正常,1-禁用',
    `remark`      varchar(512) DEFAULT NULL COMMENT '备注',
    `creator`     bigint       DEFAULT NULL COMMENT '创建人',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     bigint       DEFAULT NULL COMMENT '更新人',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_flag` tinyint(1)   DEFAULT '0' COMMENT '删除标志:0-未删除,1-已删除',
    `version`     int          DEFAULT '0' COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='角色信息表';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_permission`
(
    `id`          bigint      NOT NULL COMMENT '权限ID',
    `name`        varchar(64) NOT NULL COMMENT '权限名称',
    `perms`       varchar(64) NOT NULL COMMENT '权限标识',
    `description` varchar(256) DEFAULT NULL COMMENT '权限描述',
    `type`        tinyint(1)   DEFAULT NULL COMMENT '权限类型:1-目录,2-菜单,3-按钮',
    `order`       int          DEFAULT '0' COMMENT '排序',
    `icon`        varchar(256) DEFAULT NULL COMMENT '图标',
    `pid`         bigint       DEFAULT '0' COMMENT '父权限ID',
    `remark`      varchar(512) DEFAULT NULL COMMENT '备注',
    `state`       tinyint(1)   DEFAULT '0' COMMENT '权限状态:0-正常,1-禁用',
    `creator`     bigint       DEFAULT NULL COMMENT '创建人',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     bigint       DEFAULT NULL COMMENT '更新人',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_flag` tinyint(1)   DEFAULT '0' COMMENT '删除标志:0-未删除,1-已删除',
    `version`     int          DEFAULT '0' COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='权限信息表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_user_role`
(
    `id`          bigint NOT NULL COMMENT '主键ID',
    `user_id`     bigint NOT NULL COMMENT '用户ID',
    `role_id`     bigint NOT NULL COMMENT '角色ID',
    `creator`     bigint     DEFAULT NULL COMMENT '创建人',
    `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     bigint     DEFAULT NULL COMMENT '更新人',
    `update_time` datetime   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志:0-未删除,1-已删除',
    `version`     int        DEFAULT '0' COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='用户角色关联表';

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_role_permission`
(
    `id`            bigint NOT NULL COMMENT '主键ID',
    `role_id`       bigint NOT NULL COMMENT '角色ID',
    `permission_id` bigint NOT NULL COMMENT '权限ID',
    `creator`       bigint     DEFAULT NULL COMMENT '创建人',
    `create_time`   datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`       bigint     DEFAULT NULL COMMENT '更新人',
    `update_time`   datetime   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_flag`   tinyint(1) DEFAULT '0' COMMENT '删除标志:0-未删除,1-已删除',
    `version`       int        DEFAULT '0' COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='角色权限关联表';

INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `idno`, `email`, `phone`, `gender`, `avatar`, `type`,
                        `state`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `delete_flag`, `version`)
VALUES (1787092605400576001, 'admin', '$2a$10$1iJsURpK0FkRIhMYQRO6K.QUOc7BzbyCHqFOl3MpBjYOZhraTtvim', '管理员', NULL,
        NULL, NULL, 0, NULL, 0, 0, NULL, NULL, '2024-05-05 20:14:05', NULL, '2024-05-05 20:14:05', 0, 0);
INSERT INTO `sys_role` (`id`, `name`, `description`, `pid`, `state`, `remark`, `creator`, `create_time`, `updater`,
                        `update_time`, `delete_flag`, `version`)
VALUES (1787093585445844001, 'admin', '管理员', 0, 0, NULL, NULL, '2024-05-05 20:15:43', NULL, '2024-05-05 20:15:43', 0,
        0);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `creator`, `create_time`, `updater`, `update_time`,
                             `delete_flag`, `version`)
VALUES (1787094019136884001, 1787092605400576001, 1787093585445844001, NULL, '2024-05-05 20:16:43', NULL,
        '2024-05-05 20:16:43', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
