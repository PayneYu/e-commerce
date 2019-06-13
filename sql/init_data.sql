-- ----------------------------
-- 初始化-用户数据
-- ----------------------------
INSERT INTO sys_user (id, login_name,user_name,user_type,password,salt,status,del_flag) VALUES (1,'admin','系统管理员','00','29c67a30398638269fe600f73a054934','111111','0');


-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1,'系统管理',null,'1','#','M','0','','fa fa-gear','admin', now(),'admin', now(),'系统管理目录');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(2,'系统监控',null,'2','#','M','0','','fa fa-video-camera','admin', now(),'admin', now(),'系统监控目录');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(3,'系统工具',null,'3','#','M','0','','fa fa-bars','admin', now(),'admin', now(),'系统工具目录');
-- 二级菜单
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(100,'用户管理',1,'1','/system/user','C','0','system:user:view','#','admin', now(),'admin', now(),'用户管理菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(101,'角色管理',1,'2','/system/role','C','0','system:role:view','#','admin', now(),'admin', now(),'角色管理菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(200,'菜单管理',1,'3','/system/menu','C','0','system:menu:view','#','admin', now(),'admin', now(),'菜单管理菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(201,'字典管理',1,'6','/system/dict','C','0','system:dict:view','#','admin', now(),'admin', now(),'字典管理菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(202,'参数设置',1,'7','/system/config','C','0','system:config:view','#','admin', now(),'admin', now(),'参数设置菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(203,'通知公告',1,'8','/system/notice','C','0','system:notice:view','#','admin', now(),'admin', now(),'通知公告菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(204,'日志管理',1,'9','#','M','0','','#','admin', now(),'admin', now(),'日志管理菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(205,'在线用户',2,'1','/monitor/online','C','0','monitor:online:view','#','admin', now(),'admin', now(),'在线用户菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(206,'定时任务',2,'2','/monitor/job','C','0','monitor:job:view','#','admin', now(),'admin', now(),'定时任务菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(207,'数据监控',2,'3','/monitor/data','C','0','monitor:data:view','#','admin', now(),'admin', now(),'数据监控菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(208,'服务监控',2,'3','/monitor/server','C','0','monitor:server:view','#','admin', now(),'admin', now(),'服务监控菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(209,'表单构建',3,'1','/tool/build','C','0','tool:build:view','#','admin', now(),'admin', now(),'表单构建菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(210,'系统接口',3,'3','/tool/swagger','C','0','tool:swagger:view','#','admin', now(),'admin', now(),'系统接口菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(211,'代码生成',3,'2','/tool/gen','C','0','tool:gen:view', '#','admin', now(),'admin', now(),'代码生成菜单');
-- 三级菜单
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(500,'操作日志',203,'1','/monitor/operlog', 'C','0','monitor:operlog:view','#','admin', now(),'admin', now(),'操作日志菜单');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(501,'登录日志',203,'2','/monitor/loginInfo','C','0','monitor:loginInfo:view','#','admin', now(),'admin', now(),'登录日志菜单');
-- 用户管理按钮
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1000,'用户查询',100,'1','#','F','0','system:user:list','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1001,'用户新增',100,'2','#','F','0','system:user:add','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1002,'用户修改',100,'3','#','F','0','system:user:edit','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1003,'用户删除',100,'4','#','F','0','system:user:remove','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1004,'用户导出',100,'5','#','F','0','system:user:export','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1005,'用户导入',100,'6','#','F','0','system:user:import','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1006,'重置密码',100,'7','#','F','0','system:user:resetPwd', '#','admin', now(),'admin', now(),'');
-- 角色管理按钮
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1007,'角色查询',101,'1','#','F','0','system:role:list','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1008,'角色新增',101,'2','#','F','0','system:role:add','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1009,'角色修改',101,'3','#','F','0','system:role:edit','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1010,'角色删除',101,'4','#','F','0','system:role:remove','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1011,'角色导出',101,'5','#','F','0','system:role:export','#','admin', now(),'admin', now(),'');
-- 菜单管理按钮
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1012,'菜单查询',200,'1','#','F','0','system:menu:list','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1013,'菜单新增',200,'2','#','F','0','system:menu:add','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1014,'菜单修改',200,'3','#','F','0','system:menu:edit','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1015,'菜单删除',200,'4','#','F','0','system:menu:remove','#','admin', now(),'admin', now(),'');
-- 字典管理按钮
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1025,'字典查询',201,'1','#','F','0','system:dict:list','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1026,'字典新增',201,'2','#','F','0','system:dict:add','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1027,'字典修改',201,'3','#','F','0','system:dict:edit','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1028,'字典删除',201,'4','#','F','0','system:dict:remove','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1029,'字典导出',201,'5','#','F','0','system:dict:export','#','admin', now(),'admin', now(),'');
-- 参数设置按钮
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1030,'参数查询',202,'1','#','F','0','system:config:list','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1031,'参数新增',202,'2','#','F','0','system:config:add','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1032,'参数修改',202,'3','#','F','0','system:config:edit','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1033,'参数删除',202,'4','#','F','0','system:config:remove','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1034,'参数导出',202,'5','#','F','0','system:config:export','#','admin', now(),'admin', now(),'');
-- 通知公告按钮
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1035,'公告查询',203,'1','#','F','0','system:notice:list','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1036,'公告新增',203,'2','#','F','0','system:notice:add','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1037,'公告修改',203,'3','#','F','0','system:notice:edit','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1038,'公告删除',203,'4','#','F','0','system:notice:remove', '#','admin', now(),'admin', now(),'');
-- 操作日志按钮
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1039,'操作查询',500,'1','#','F','0','monitor:operlog:list', '#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1040,'操作删除',500,'2','#','F','0','monitor:operlog:remove','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1041,'详细信息',500,'3','#','F','0','monitor:operlog:detail','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1042,'日志导出',500,'4','#','F','0','monitor:operlog:export','#','admin', now(),'admin', now(),'');
-- 登录日志按钮
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1043,'登录查询',501,'1','#','F','0','monitor:loginInfo:list','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1044,'登录删除',501,'2','#','F','0','monitor:loginInfo:remove','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1045,'日志导出',501,'3','#','F','0','monitor:loginInfo:export','#','admin', now(),'admin', now(),'');
-- 在线用户按钮
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1046,'在线查询',205,'1','#','F','0','monitor:online:list','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1047,'批量强退',205,'2','#','F','0','monitor:online:batchForceLogout','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1048,'单条强退',205,'3','#','F','0','monitor:online:forceLogout','#','admin', now(),'admin', now(),'');
-- 定时任务按钮
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1049,'任务查询',206,'1','#','F','0','monitor:job:list','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1050,'任务新增',206,'2','#','F','0','monitor:job:add','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1051,'任务修改',206,'3','#','F','0','monitor:job:edit','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1052,'任务删除',206,'4','#','F','0','monitor:job:remove','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1053,'状态修改',206,'5','#','F','0','monitor:job:changeStatus','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1054,'任务详细',206,'6','#','F','0','monitor:job:detail','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1055,'任务导出',206,'7','#','F','0','monitor:job:export','#','admin', now(),'admin', now(),'');
-- 代码生成按钮
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1056,'生成查询',211,'1','#','F','0','tool:gen:list','#','admin', now(),'admin', now(),'');
insert into sys_menu(id,menu_name,parent_id,order_num,url,menu_type,visible,perms,icon,create_by,create_time,update_by,update_time,remark)
values(1057,'生成代码',211,'2','#','F','0','tool:gen:code','#','admin', now(),'admin', now(),'');

-- 参数配置表
insert into sys_config values(1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y', 'admin',now(), 'admin', now(), '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow' );
insert into sys_config values(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y', 'admin', now(), 'admin', now(), '初始化密码 123456' );
-- 字典配置表
insert into sys_dict_type values(1,  '用户性别', 'sys_user_sex','0', 'admin',now(), 'admin',now(), '用户性别列表');
insert into sys_dict_type values(2,  '菜单状态', 'sys_show_hide','0', 'admin',now(), 'admin',now(), '菜单状态列表');
insert into sys_dict_type values(3,  '系统开关', 'sys_normal_disable','0', 'admin',now(), 'admin',now(), '系统开关列表');
insert into sys_dict_type values(4,  '任务状态', 'sys_job_status', '0', 'admin',now(), 'admin',now(), '任务状态列表');
insert into sys_dict_type values(5,  '系统是否', 'sys_yes_no','0', 'admin',now(), 'admin',now(), '系统是否列表');
insert into sys_dict_type values(6,  '通知类型', 'sys_notice_type','0','admin',now(), 'admin',now(), '通知类型列表');
insert into sys_dict_type values(7,  '通知状态', 'sys_notice_status','0', 'admin',now(), 'admin',now(), '通知状态列表');
insert into sys_dict_type values(8,  '操作类型', 'sys_oper_type','0', 'admin',now(), 'admin',now(), '操作类型列表');
insert into sys_dict_type values(9,  '系统状态', 'sys_common_status','0', 'admin',now(), 'admin',now(), '登录状态列表');

-- 字典数据表
insert into sys_dict_data values(1,1,1,'男','0','sys_user_sex', '','', 'Y', '0', 'admin',now(), 'admin',now(), '性别男');
insert into sys_dict_data values(2,2,2,'女','1','sys_user_sex', '','', 'N', '0', 'admin',now(), 'admin',now(), '性别女');
insert into sys_dict_data values(3,3,3,'未知','2','sys_user_sex', '','', 'N', '0', 'admin',now(), 'admin',now(), '性别未知');
insert into sys_dict_data values(4,4,1,'显示','0','sys_show_hide','','primary', 'Y', '0', 'admin',now(), 'admin',now(), '显示菜单');
insert into sys_dict_data values(5,5,2,'隐藏','1','sys_show_hide','','danger','N', '0', 'admin',now(), 'admin',now(), '隐藏菜单');
insert into sys_dict_data values(6,6,1,'正常','0','sys_normal_disable','','primary', 'Y', '0', 'admin',now(), 'admin',now(), '正常状态');
insert into sys_dict_data values(7,7,2,'停用','1','sys_normal_disable','','danger','N', '0', 'admin',now(), 'admin',now(), '停用状态');
insert into sys_dict_data values(8,8,1,'正常','0','sys_job_status', '','primary', 'Y', '0', 'admin',now(), 'admin',now(), '正常状态');
insert into sys_dict_data values(9,9,2,'暂停','1','sys_job_status', '','danger','N', '0', 'admin',now(), 'admin',now(), '停用状态');
insert into sys_dict_data values(10,10, 1,'是','Y','sys_yes_no','','primary', 'Y', '0', 'admin',now(), 'admin',now(), '系统默认是');
insert into sys_dict_data values(11,11, 2,'否','N','sys_yes_no','','danger','N', '0', 'admin',now(), 'admin',now(), '系统默认否');
insert into sys_dict_data values(12,12, 1,'通知','1','sys_notice_type','','warning', 'Y', '0', 'admin',now(), 'admin',now(), '通知');
insert into sys_dict_data values(12,13, 2,'公告','2','sys_notice_type','','success', 'N', '0', 'admin',now(), 'admin',now(), '公告');
insert into sys_dict_data values(14,14, 1,'正常','0','sys_notice_status','','primary', 'Y', '0', 'admin',now(), 'admin',now(), '正常状态');
insert into sys_dict_data values(15,15, 2,'关闭','1','sys_notice_status','','danger','N', '0', 'admin',now(), 'admin',now(), '关闭状态');
insert into sys_dict_data values(16,16, 1,'新增','1','sys_oper_type','','info', 'N', '0', 'admin',now(), 'admin',now(), '新增操作');
insert into sys_dict_data values(17,17, 2,'修改','2','sys_oper_type','','info', 'N', '0', 'admin',now(), 'admin',now(), '修改操作');
insert into sys_dict_data values(18,18, 3,'删除','3','sys_oper_type','','danger','N', '0', 'admin',now(), 'admin',now(), '删除操作');
insert into sys_dict_data values(19,19, 4,'授权','4','sys_oper_type','','primary', 'N', '0', 'admin',now(), 'admin',now(), '授权操作');
insert into sys_dict_data values(20,20, 5,'导出','5','sys_oper_type','','warning', 'N', '0', 'admin',now(), 'admin',now(), '导出操作');
insert into sys_dict_data values(21,21, 6,'导入','6','sys_oper_type','','warning', 'N', '0', 'admin',now(), 'admin',now(), '导入操作');
insert into sys_dict_data values(22,22, 7,'强退','7','sys_oper_type','','danger','N', '0', 'admin',now(), 'admin',now(), '强退操作');
insert into sys_dict_data values(23,23, 8,'生成代码', '8','sys_oper_type','','warning', 'N', '0', 'admin',now(), 'admin',now(), '生成操作');
insert into sys_dict_data values(24,24, 8,'清空数据', '9','sys_oper_type','','danger','N', '0', 'admin',now(), 'admin',now(), '清空操作');
insert into sys_dict_data values(25,25, 1,'成功','0','sys_common_status','','primary', 'N', '0', 'admin',now(), 'admin',now(), '正常状态');
insert into sys_dict_data values(26,26, 2,'失败','1','sys_common_status','','danger','N', '0', 'admin',now(), 'admin',now(), '停用状态');

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into sys_role values(1, '管理员',   'admin',  1, 1, '0', 'admin', now(), 'admin', now(), '管理员');
insert into sys_role values(2, '普通角色', 'common', 2, 2, '0', 'admin', now(), 'admin', now(), '普通角色');



