CREATE TABLE IF NOT EXISTS `sys_org` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '机构主键',
  `parent_id` BIGINT comment '上级机构',
  `org_type` int(11) not null comment '机构类型',
  `org_code` varchar(40) comment '机构编码',
  `org_name`	varchar(100)  comment '机构名称',
  `org_level` int(11) comment '机构级别',
  `org_address` varchar(255) comment '机构地址',
  `org_path_code` varchar(255) comment '机构全路径ID',
  `org_path_name` varchar(255) comment '机构全路径名称',
  `lng` double NULL comment '经度',
  `lat` double NULL comment '纬度',
  primary key (id),
  CONSTRAINT `FK_sys_org_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `sys_org` (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '机构信息表';

CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '用户ID',
  `org_id` BIGINT 	comment '区划ID',
  `login_name` varchar(30) 	not null comment '登录账号',
  `user_name` varchar(30) not null comment '用户昵称',
  `user_type` varchar(2) default '00' comment '用户类型（00系统用户）',
  `email`	varchar(50)  comment '用户邮箱',
  `phone_number` varchar(11) comment '手机号码',
  `sex` char(1) default '0' comment '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) comment '头像路径',
  `password` varchar(50) comment '密码',
  `salt` varchar(20) comment '盐加密',
  `status` char(1) default '0' comment '帐号状态（0正常 1停用）',
  `del_flag` char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) comment '最后登陆IP',
  `login_date` TIMESTAMP(6) comment '最后登陆时间',
  `create_by` varchar(64) comment '创建者',
  `create_time` TIMESTAMP(6)  comment '创建时间',
  `update_by` varchar(64) comment '更新者',
  `update_time` TIMESTAMP(6) comment '更新时间',
  `remark` varchar(500) comment '备注',
  primary key (id),
  CONSTRAINT `FK_sys_user_org_id` FOREIGN KEY (`org_id`) REFERENCES `sys_org` (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '用户信息表';

CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '用户ID',
  `role_name` varchar(30) not null comment '角色名称',
  `role_key` varchar(100) not null comment '角色权限字符串',
  `role_sort` int(4) not null comment '显示顺序',
  `data_scope` char(1)   default '1'	comment '数据范围（1：全部数据权限 2：自定数据权限）',
  `status` char(1)	 not null comment '角色状态（0正常 1停用）',
  `del_flag`	char(1) default '0' 	comment '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) comment '创建者',
  `create_time` TIMESTAMP(6)  comment '创建时间',
  `update_by` varchar(64) comment '更新者',
  `update_time` TIMESTAMP(6) comment '更新时间',
  `remark` varchar(500) comment '备注',
  primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '用户信息表';

create table sys_role_menu (
  `role_id` 	BIGINT NOT NULL comment '角色ID',
  `menu_id` 	BIGINT NOT NULL comment '菜单ID',
  primary key(role_id, menu_id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '角色和菜单关联表';


CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `user_id` BIGINT not null comment '用户ID',
  `role_id` BIGINT not null comment '角色ID',
  CONSTRAINT `FK_sys_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK_sys_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '用户和角色关联表';

CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '菜单ID',
  `menu_name` 	varchar(50) 	not null 	comment '菜单名称',
  `parent_id`	BIGINT default 0 	comment '父菜单ID',
  `order_num` 	int(4) default 0 comment '显示顺序',
  `url` varchar(200) default '#'	comment '请求地址',
  `menu_type` char(1) 	comment '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) 	default 0 comment '菜单状态（0显示 1隐藏）',
  `perms`	 varchar(100) comment '权限标识',
  `icon` 	varchar(100) 	default '#' comment '菜单图标',
  `create_by` varchar(64) comment '创建者',
  `create_time` TIMESTAMP(6)  comment '创建时间',
  `update_by` varchar(64) comment '更新者',
  `update_time` TIMESTAMP(6) comment '更新时间',
  `remark` varchar(500) comment '备注',
  primary key (id),
  CONSTRAINT `FK_sys_menu_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `sys_menu` (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '菜单权限表';

CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `role_id` BIGINT not null comment '角色ID',
  `menu_id` BIGINT not null comment '菜单ID',
  CONSTRAINT `FK_sys_role_menu_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_sys_role_menu_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '角色和菜单关联表';

CREATE TABLE IF NOT EXISTS `sys_config` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '参数主键',
  `config_name` 	varchar(100) not null 	comment '参数名称',
  `config_key` varchar(100) unique comment '参数键名',
  `config_value` varchar(100) comment '参数键值',
  `config_type` char(1) default 'N' comment '系统内置（Y是 N否）',
  `create_by` varchar(64) comment '创建者',
  `create_time` TIMESTAMP(6)  comment '创建时间',
  `update_by` varchar(64) comment '更新者',
  `update_time` TIMESTAMP(6) comment '更新时间',
  `remark` varchar(500) comment '备注',
  primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '参数配置表';

-- ----------------------------
-- 字典类型表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_dict_type` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '字典主键',
  `dict_name` varchar(100) comment '字典名称',
  `dict_type` varchar(100) unique comment '字典类型',
  `status` int(1) default 0 comment '操作状态（0正常 1异常）',
  `create_by` varchar(64) comment '创建者',
  `create_time` TIMESTAMP(6)  comment '创建时间',
  `update_by` varchar(64) comment '更新者',
  `update_time` TIMESTAMP(6) comment '更新时间',
  `remark` varchar(500) comment '备注',
  primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '字典类型表';

-- ----------------------------
-- 字典数据表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_dict_data` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '字典主键',
  `dict_code` int(11) NOT NULL comment '字典编码',
  `dict_sort` int(4) default 0 comment '字典排序',
  `dict_label` 	varchar(100) not null 	comment '字典标签',
  `dict_value` varchar(100) comment '字典键值',
  `dict_type` varchar(100) comment '字典类型',
  `css_class` varchar(100) comment '样式属性（其他样式扩展）',
  `list_class` varchar(100) comment '表格回显样式',
  `is_default` char(1) default 'N' comment '是否默认（Y是 N否）',
  `status` char(1) default '0' comment '状态（0正常 1停用）',
  `create_by` varchar(64) comment '创建者',
  `create_time` TIMESTAMP(6)  comment '创建时间',
  `update_by` varchar(64) comment '更新者',
  `update_time` TIMESTAMP(6) comment '更新时间',
  `remark` varchar(500) comment '备注',
  primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '字典数据表';
-- ----------------------------
-- 操作日志记录
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_oper_log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '日志主键',
  `title` varchar(100) comment '模块标题',
  `business_type` int(2) default 0 comment '业务类型（0其它 1新增 2修改 3删除）',
  `method` 	varchar(100) comment '方法名称',
  `operator_type` int(1) default 0 comment '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) comment '操作人员',
  `oper_url` varchar(255) comment '请求URL',
  `oper_ip` varchar(50) comment '主机地址',
  `oper_location` varchar(225) comment '操作地点',
  `oper_param` varchar(225) comment '请求参数',
  `status` int(1) default 0 comment '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) comment '错误消息',
  `oper_time` TIMESTAMP(6)  comment '操作时间',
  primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '操作日志记录';

-- ----------------------------
-- 系统访问记录
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_login_info` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '日志主键',
  `login_name` varchar(50) comment '登录账号',
  `ip_address` varchar(50) comment '登录IP地址',
  `login_location` 	varchar(255) comment '登录地点',
  `browser` varchar(50) comment '浏览器类型',
  `os` varchar(50) comment '操作系统',
  `msg` 	varchar(2550) comment '提示消息',
  `status` int(1) default 0 comment '操作状态（0正常 1异常）',
  `login_time` TIMESTAMP(6)  comment '操作时间',
  primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '系统访问记录';

-- ----------------------------
-- 在线用户记录
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_user_online` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '主键',
  `session_id` varchar(50) UNIQUE comment '用户会话id',
  `login_name` varchar(50) comment '登录账号',
  `ip_address` varchar(50) comment '登录IP地址',
  `login_location` 	varchar(255) comment '登录地点',
  `browser` varchar(50) comment '浏览器类型',
  `os` varchar(50) comment '操作系统',
  `msg` 	varchar(2550) comment '提示消息',
  `status` varchar(10) comment '在线状态on_line在线off_line离线',
  `start_time` TIMESTAMP(6)  comment 'session创建时间',
  `last_access_time` TIMESTAMP(6)  comment 'session最后访问时间',
  `expire_time` int(5)  comment '超时时间，单位为分钟',
  primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '在线用户记录';

-- ----------------------------
-- 定时任务调度表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_job` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '主键',
  `job_name` varchar(64) comment '任务名称',
  `job_group` varchar(63) comment '任务组名',
  `method_name` varchar(500) comment '任务方法',
  `method_params` 	varchar(50) comment '方法参数',
  `cron_expression` varchar(255) comment 'cron执行表达式',
  `misfire_policy` varchar(1) default '3' comment '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `status` char(1) default '0' comment '状态（0正常 1停用）',
  `create_by` varchar(64) comment '创建者',
  `create_time` TIMESTAMP(6)  comment '创建时间',
  `update_by` varchar(64) comment '更新者',
  `update_time` TIMESTAMP(6) comment '更新时间',
  `remark` varchar(500) comment '备注',
  primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '定时任务调度表';

-- ----------------------------
-- 定时任务调度日志表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_job_log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '主键',
  `job_name` varchar(64) UNIQUE comment '任务名称',
  `job_group` varchar(63) comment '任务组名',
  `method_name` varchar(500) comment '任务方法',
  `method_params` 	varchar(50) comment '方法参数',
  `job_message` varchar(500) comment '日志信息',
  `status` char(1) default '0' comment '状态（0正常 1停用）',
  `exception_info` varchar(2000) comment '异常信息',
  `create_time` TIMESTAMP(6)  comment '创建时间',
  primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '定时任务调度日志表';


CREATE TABLE IF NOT EXISTS `sys_notice` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '主键',
  `notice_title` varchar(64) comment '公告标题',
  `notice_type` char(1) default '0' comment '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) comment '公告内容',
  `status` char(1) default '0' comment '状态（0正常 1停用）',
  `create_by` varchar(64) comment '创建者',
  `create_time` TIMESTAMP(6)  comment '创建时间',
  `update_by` varchar(64) comment '更新者',
  `update_time` TIMESTAMP(6) comment '更新时间',
  primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci comment = '通知公告表';


