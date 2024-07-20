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
    `request_user`        bigint        DEFAULT NULL COMMENT '请求人',
    `request_time`        datetime      DEFAULT NULL COMMENT '请求时间',
    `request_ip`          varchar(64)   DEFAULT NULL COMMENT '请求IP',
    `request_area`        varchar(256)  DEFAULT NULL COMMENT '请求地区',
    `request_os`          varchar(64)   DEFAULT NULL COMMENT '请求系统',
    `request_device`      varchar(64)   DEFAULT NULL COMMENT '请求设备',
    `request_browser`     varchar(64)   DEFAULT NULL COMMENT '请求浏览器',
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
-- Table structure for sys_role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_role`
(
    `id`          bigint      NOT NULL COMMENT '角色ID',
    `name`        varchar(64) NOT NULL COMMENT '角色名称',
    `code`        varchar(64) NOT NULL COMMENT '角色权限码',
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
-- Table structure for sys_menu
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_menu`
(
    `id`             bigint      NOT NULL COMMENT '菜单ID',
    `name`           varchar(64) NOT NULL COMMENT '菜单名称',
    `code`           varchar(64) NOT NULL COMMENT '菜单权限码',
    `pid`            bigint       DEFAULT '0' COMMENT '父菜单ID',
    `description`    varchar(256) DEFAULT NULL COMMENT '菜单描述',
    `type`           tinyint(1)   DEFAULT NULL COMMENT '菜单类型:1-目录,2-菜单,3-按钮',
    `sort`           int          DEFAULT '0' COMMENT '菜单排序',
    `icon`           varchar(256) DEFAULT NULL COMMENT '菜单图标',
    `route_name`     varchar(256) DEFAULT NULL COMMENT '菜单路由名称',
    `route_path`     varchar(256) DEFAULT NULL COMMENT '菜单路由地址',
    `component_path` varchar(256) DEFAULT NULL COMMENT '菜单组件路径',
    `status`         tinyint(1)   DEFAULT '0' COMMENT '菜单状态:0-正常,1-禁用',
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
  ROW_FORMAT = DYNAMIC COMMENT ='菜单信息表';

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
CREATE TABLE IF NOT EXISTS `sys_role_menu`
(
    `id`          bigint NOT NULL COMMENT '主键ID',
    `role_id`     bigint NOT NULL COMMENT '角色ID',
    `menu_id`     bigint NOT NULL COMMENT '菜单ID',
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
  ROW_FORMAT = DYNAMIC COMMENT ='角色菜单关联表';

-- 初始管理员用户
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `idno`, `email`, `phone`, `gender`, `avatar`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `delete_flag`, `version`) VALUES (1, 'admin', '$2a$10$Gw2T4jeYpwGEDCBbBKGPTuktW84axtujvQoFhlivm.dcAMKauDXky', '管理员', NULL, NULL, NULL, 0, NULL, 0, 0, NULL, NULL, '2024-05-26 17:19:48', NULL, '2024-05-26 17:19:48', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
