CREATE TABLE IF NOT EXISTS `sys_user` (
  `id`           BIGINT      NOT NULL AUTO_INCREMENT
  COMMENT '用户ID',
  `login_name`   VARCHAR(30) NOT NULL
  COMMENT '登录账号',
  `user_name`    VARCHAR(30) NOT NULL
  COMMENT '用户昵称',
  `user_type`    VARCHAR(2)           DEFAULT '00'
  COMMENT '用户类型（00系统用户）',
  `email`        VARCHAR(50) COMMENT '用户邮箱',
  `phone_number` VARCHAR(11) COMMENT '手机号码',
  `sex`          CHAR(1)              DEFAULT '0'
  COMMENT '用户性别（0男 1女 2未知）',
  `avatar`       VARCHAR(100) COMMENT '头像路径',
  `password`     VARCHAR(50) COMMENT '密码',
  `salt`         VARCHAR(20) COMMENT '盐加密',
  `status`       CHAR(1)              DEFAULT '0'
  COMMENT '帐号状态（0正常 1停用）',
  `del_flag`     CHAR(1)              DEFAULT '0'
  COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip`     VARCHAR(50) COMMENT '最后登陆IP',
  `login_date`   TIMESTAMP(6) COMMENT '最后登陆时间',
  `create_by`    VARCHAR(64) COMMENT '创建者',
  `create_time`  TIMESTAMP(6) COMMENT '创建时间',
  `update_by`    VARCHAR(64) COMMENT '更新者',
  `update_time`  TIMESTAMP(6) COMMENT '更新时间',
  `remark`       VARCHAR(500) COMMENT '备注',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  COMMENT = '用户信息表';

CREATE TABLE IF NOT EXISTS `sys_role` (
  `id`          BIGINT       NOT NULL AUTO_INCREMENT
  COMMENT '用户ID',
  `role_name`   VARCHAR(30)  NOT NULL
  COMMENT '角色名称',
  `role_key`    VARCHAR(100) NOT NULL
  COMMENT '角色权限字符串',
  `role_sort`   INT(4)       NOT NULL
  COMMENT '显示顺序',
  `data_scope`  CHAR(1)               DEFAULT '1'
  COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
  `status`      CHAR(1)      NOT NULL
  COMMENT '角色状态（0正常 1停用）',
  `del_flag`    CHAR(1)               DEFAULT '0'
  COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by`   VARCHAR(64) COMMENT '创建者',
  `create_time` TIMESTAMP(6) COMMENT '创建时间',
  `update_by`   VARCHAR(64) COMMENT '更新者',
  `update_time` TIMESTAMP(6) COMMENT '更新时间',
  `remark`      VARCHAR(500) COMMENT '备注',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  COMMENT = '用户信息表';

CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `user_id` BIGINT NOT NULL
  COMMENT '用户ID',
  `role_id` BIGINT NOT NULL
  COMMENT '角色ID',
  CONSTRAINT `FK_sys_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK_sys_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  COMMENT = '用户和角色关联表';

CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id`          BIGINT      NOT NULL AUTO_INCREMENT
  COMMENT '菜单ID',
  `menu_name`   VARCHAR(50) NOT NULL
  COMMENT '菜单名称',
  `parent_id`   BIGINT               DEFAULT 0
  COMMENT '父菜单ID',
  `order_num`   INT(4)               DEFAULT 0
  COMMENT '显示顺序',
  `url`         VARCHAR(200)         DEFAULT '#'
  COMMENT '请求地址',
  `menu_type`   CHAR(1) COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible`     CHAR(1)              DEFAULT 0
  COMMENT '菜单状态（0显示 1隐藏）',
  `perms`       VARCHAR(100) COMMENT '权限标识',
  `icon`        VARCHAR(100)         DEFAULT '#'
  COMMENT '菜单图标',
  `create_by`   VARCHAR(64) COMMENT '创建者',
  `create_time` TIMESTAMP(6) COMMENT '创建时间',
  `update_by`   VARCHAR(64) COMMENT '更新者',
  `update_time` TIMESTAMP(6) COMMENT '更新时间',
  `remark`      VARCHAR(500) COMMENT '备注',
  PRIMARY KEY (id),
  CONSTRAINT `FK_sys_menu_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `sys_menu` (`id`)
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  COMMENT = '菜单权限表';

CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `role_id` BIGINT NOT NULL
  COMMENT '角色ID',
  `menu_id` BIGINT NOT NULL
  COMMENT '菜单ID',
  CONSTRAINT `FK_sys_role_menu_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_sys_role_menu_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`)
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  COMMENT = '角色和菜单关联表';

CREATE TABLE IF NOT EXISTS `sys_config` (
  `id`           BIGINT       NOT NULL AUTO_INCREMENT
  COMMENT '参数主键',
  `config_name`  VARCHAR(100) NOT NULL
  COMMENT '参数名称',
  `config_key`   VARCHAR(100) UNIQUE
  COMMENT '参数键名',
  `config_value` VARCHAR(100) COMMENT '参数键值',
  `config_type`  CHAR(1)               DEFAULT 'N'
  COMMENT '系统内置（Y是 N否）',
  `create_by`    VARCHAR(64) COMMENT '创建者',
  `create_time`  TIMESTAMP(6) COMMENT '创建时间',
  `update_by`    VARCHAR(64) COMMENT '更新者',
  `update_time`  TIMESTAMP(6) COMMENT '更新时间',
  `remark`       VARCHAR(500) COMMENT '备注',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  COMMENT = '参数配置表';

CREATE TABLE IF NOT EXISTS `sys_dict_data` (
  `id`          BIGINT       NOT NULL AUTO_INCREMENT
  COMMENT '字典主键',
  `dict_code`   INT(11)      NOT NULL
  COMMENT '字典编码',
  `dict_sort`   INT(4)                DEFAULT 0
  COMMENT '字典排序',
  `dict_label`  VARCHAR(100) NOT NULL
  COMMENT '字典标签',
  `dict_value`  VARCHAR(100) COMMENT '字典键值',
  `dict_type`   VARCHAR(100) COMMENT '字典类型',
  `css_class`   VARCHAR(100) COMMENT '样式属性（其他样式扩展）',
  `list_class`  VARCHAR(100) COMMENT '表格回显样式',
  `is_default`  CHAR(1)               DEFAULT 'N'
  COMMENT '是否默认（Y是 N否）',
  `status`      CHAR(1)               DEFAULT '0'
  COMMENT '状态（0正常 1停用）',
  `create_by`   VARCHAR(64) COMMENT '创建者',
  `create_time` TIMESTAMP(6) COMMENT '创建时间',
  `update_by`   VARCHAR(64) COMMENT '更新者',
  `update_time` TIMESTAMP(6) COMMENT '更新时间',
  `remark`      VARCHAR(500) COMMENT '备注',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  COMMENT = '字典数据表';
-- ----------------------------
-- 操作日志记录
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_oper_log` (
  `id`            BIGINT NOT NULL AUTO_INCREMENT
  COMMENT '日志主键',
  `title`         VARCHAR(100) COMMENT '模块标题',
  `business_type` INT(2)          DEFAULT 0
  COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method`        VARCHAR(100) COMMENT '方法名称',
  `operator_type` INT(1)          DEFAULT 0
  COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name`     VARCHAR(50) COMMENT '操作人员',
  `oper_url`      VARCHAR(255) COMMENT '请求URL',
  `oper_ip`       VARCHAR(50) COMMENT '主机地址',
  `oper_location` VARCHAR(225) COMMENT '操作地点',
  `oper_param`    VARCHAR(225) COMMENT '请求参数',
  `status`        INT(1)          DEFAULT 0
  COMMENT '操作状态（0正常 1异常）',
  `error_msg`     VARCHAR(2000) COMMENT '错误消息',
  `oper_time`     TIMESTAMP(6) COMMENT '操作时间',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  COMMENT = '操作日志记录';

