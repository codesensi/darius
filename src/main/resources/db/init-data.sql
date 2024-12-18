SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE IF NOT EXISTS `sys_config`
(
    `id`          bigint NOT NULL COMMENT '配置ID',
    `init_flag`   tinyint(1) DEFAULT '0' COMMENT '初始化标志:0-未初始化,1-已初始化',
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
  ROW_FORMAT = DYNAMIC COMMENT ='系统配置表';

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE IF NOT EXISTS `sys_login_log`
(
    `id`          bigint NOT NULL COMMENT '日志ID',
    `type`        tinyint(1)    DEFAULT NULL COMMENT '登录类型:0-未知,1-登录,2-登出',
    `mode`        tinyint(1)    DEFAULT NULL COMMENT '登录方式:0-未知,1-账号密码,2-手机验证码',
    `login_time`  datetime      DEFAULT NULL COMMENT '登录时间',
    `ip`          varchar(64)   DEFAULT NULL COMMENT '登录IP',
    `area`        varchar(256)  DEFAULT NULL COMMENT '登录地区',
    `os`          varchar(256)  DEFAULT NULL COMMENT '登录系统',
    `device`      varchar(64)   DEFAULT NULL COMMENT '登录设备',
    `browser`     varchar(64)   DEFAULT NULL COMMENT '登录浏览器',
    `state`       tinyint(1)    DEFAULT NULL COMMENT '登录状态:0-失败,1-成功',
    `fail_reason` varchar(2048) DEFAULT NULL COMMENT '登录失败原因',
    `creator`     bigint        DEFAULT NULL COMMENT '创建人',
    `create_time` datetime      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     bigint        DEFAULT NULL COMMENT '更新人',
    `update_time` datetime      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_flag` tinyint(1)    DEFAULT '0' COMMENT '删除标志:0-未删除,1-已删除',
    `version`     int           DEFAULT '0' COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='登录日志表';

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE IF NOT EXISTS `sys_operate_log`
(
    `id`               bigint NOT NULL COMMENT '日志ID',
    `type`             tinyint(1)    DEFAULT NULL COMMENT '操作类型:0-未知,1-登录,2-登出,3-新增,4-更新,5-查询,6-删除',
    `message`          varchar(64)   DEFAULT NULL COMMENT '操作类型说明',
    `description`      varchar(256)  DEFAULT NULL COMMENT '操作描述',
    `request_time`     datetime      DEFAULT NULL COMMENT '请求时间',
    `request_ip`       varchar(64)   DEFAULT NULL COMMENT '请求IP',
    `request_area`     varchar(256)  DEFAULT NULL COMMENT '请求地区',
    `request_os`       varchar(64)   DEFAULT NULL COMMENT '请求系统',
    `request_device`   varchar(64)   DEFAULT NULL COMMENT '请求设备',
    `request_browser`  varchar(64)   DEFAULT NULL COMMENT '请求浏览器',
    `request_url`      varchar(256)  DEFAULT NULL COMMENT '请求接口',
    `request_method`   varchar(256)  DEFAULT NULL COMMENT '请求方法',
    `request_mode`     varchar(64)   DEFAULT NULL COMMENT '请求方式',
    `request_param`    text          DEFAULT NULL COMMENT '请求参数',
    `response_state`   tinyint(1)    DEFAULT NULL COMMENT '响应状态:0-异常,1-正常',
    `response_time`    datetime      DEFAULT NULL COMMENT '正常响应时间',
    `response_consume` bigint        DEFAULT NULL COMMENT '响应耗时:单位毫秒',
    `response_data`    varchar(2048) DEFAULT NULL COMMENT '响应数据',
    `error_time`       datetime      DEFAULT NULL COMMENT '异常响应时间',
    `error_message`    varchar(2048) DEFAULT NULL COMMENT '异常信息',
    `creator`          bigint        DEFAULT NULL COMMENT '创建人',
    `create_time`      datetime      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`          bigint        DEFAULT NULL COMMENT '更新人',
    `update_time`      datetime      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_flag`      tinyint(1)    DEFAULT '0' COMMENT '删除标志:0-未删除,1-已删除',
    `version`          int           DEFAULT '0' COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='操作日志表';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE IF NOT EXISTS `sys_permission`
(
    `id`             bigint      NOT NULL COMMENT '权限ID',
    `name`           varchar(64) NOT NULL COMMENT '权限名称',
    `code`           varchar(64) NOT NULL COMMENT '权限编码',
    `pid`            bigint       DEFAULT '0' COMMENT '父权限ID',
    `description`    varchar(256) DEFAULT NULL COMMENT '权限描述',
    `type`           tinyint(1)   DEFAULT NULL COMMENT '权限类型:1-目录,2-菜单,3-按钮',
    `sort`           int          DEFAULT '0' COMMENT '权限排序',
    `icon`           varchar(256) DEFAULT NULL COMMENT '权限图标',
    `route_name`     varchar(256) DEFAULT NULL COMMENT '权限路由名称',
    `route_path`     varchar(256) DEFAULT NULL COMMENT '权限路由地址',
    `component_path` varchar(256) DEFAULT NULL COMMENT '权限组件路径',
    `status`         tinyint(1)   DEFAULT '0' COMMENT '权限状态:0-正常,1-禁用',
    `remark`         varchar(512) DEFAULT NULL COMMENT '备注',
    `creator`        bigint       DEFAULT NULL COMMENT '创建人',
    `create_time`    datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`        bigint       DEFAULT NULL COMMENT '更新人',
    `update_time`    datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_flag`    tinyint(1)   DEFAULT '0' COMMENT '删除标志:0-未删除,1-已删除',
    `version`        int          DEFAULT '0' COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='权限信息表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role`
(
    `id`          bigint      NOT NULL COMMENT '角色ID',
    `name`        varchar(64) NOT NULL COMMENT '角色名称',
    `code`        varchar(64) NOT NULL COMMENT '角色编码',
    `pid`         bigint       DEFAULT '0' COMMENT '父角色ID',
    `description` varchar(256) DEFAULT NULL COMMENT '角色描述',
    `type`        tinyint(1)   DEFAULT '0' COMMENT '角色类型:0-默认角色',
    `sort`        int          DEFAULT '0' COMMENT '角色排序',
    `status`      tinyint(1)   DEFAULT '0' COMMENT '角色状态:0-正常,1-禁用',
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
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
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

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user`
(
    `id`          bigint       NOT NULL COMMENT '用户ID',
    `username`    varchar(128) NOT NULL COMMENT '用户名称',
    `password`    varchar(512) NOT NULL COMMENT '用户密码',
    `nickname`    varchar(64)  DEFAULT NULL COMMENT '用户昵称',
    `idno`        varchar(64)  DEFAULT NULL COMMENT '用户身份证号码',
    `email`       varchar(64)  DEFAULT NULL COMMENT '用户邮箱',
    `phone`       varchar(11)  DEFAULT NULL COMMENT '用户手机号码',
    `gender`      tinyint(1)   DEFAULT '0' COMMENT '用户性别:0-保密,1-男,2-女',
    `avatar`      varchar(512) DEFAULT NULL COMMENT '用户头像地址',
    `type`        tinyint(1)   DEFAULT '0' COMMENT '用户类型:0-系统用户',
    `status`      tinyint(1)   DEFAULT '0' COMMENT '用户状态:0-启用,1-禁用',
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
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
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

-- 系统配置
INSERT INTO `sys_config` (`id`, `init_flag`, `creator`, `updater`, `delete_flag`, `version`) VALUES (1, 1, NULL, NULL, 0, 0);
-- 超级管理员
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `idno`, `email`, `phone`, `gender`, `avatar`, `type`, `status`, `remark`, `creator`, `updater`, `delete_flag`, `version`) VALUES (1, 'admin', '$2a$10$Gw2T4jeYpwGEDCBbBKGPTuktW84axtujvQoFhlivm.dcAMKauDXky', '超级管理员', '110105197000000001', 'admin@gmail.com', '18900000000', 0, 'https://lsky.codesensi.cn:1443/i/2024/02/06/65c242e1493f8.png', 0, 0, '超级管理员', 1, NULL, 0, 0);
-- 超级管理员角色
INSERT INTO `sys_role` (`id`, `name`, `code`, `pid`, `description`, `type`, `sort`, `status`, `remark`, `creator`, `updater`, `delete_flag`, `version`) VALUES (1, '超级管理员', 'admin', 0, '超级管理员', 0, 0, 0, '超级管理员', 1, NULL, 0, 0);
-- 用户角色关联
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `creator`, `updater`, `delete_flag`, `version`) VALUES (1, 1, 1, 1, NULL, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
