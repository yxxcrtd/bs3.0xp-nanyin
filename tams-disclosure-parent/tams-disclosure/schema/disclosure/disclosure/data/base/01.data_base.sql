/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2018/7/15 星期日 下午 6:31:18                     */
/*==============================================================*/


/***********************************系统参数表*******************************************/
DELETE FROM SYS_PARA WHERE SYSTEM_CODE='REP' AND PARA_CODE IN ('REPORT_BATCH_SERVER_URL','REPORT_SERVER_URL','REPORT_SERVER_PATH','REPORT_USERNAME','REPORT_PASSWORD','REPORT_BATCH_EXPORT_PATH','REPORT_DEFAULT_FILENAME','REPORT_DOWNLOAD_PATH','PRE_GEN_DATE','REP_REFRESH_JOBRULE','HOLIDAY_IS_WORK');
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'REPORT_BATCH_SERVER_URL', 'http://192.168.70.178:8085/jasperserver', '  ', '', '报表服务器地址', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'REPORT_SERVER_URL', 'http://220.180.239.103:8065/jasperserver', '  ', '', '报表服务器地址外网', 'system', '', SYSDATE, 'system', SYSDATE);


insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'REPORT_SERVER_PATH', '/ZX', '01', null, '报表服务器路径', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'REPORT_USERNAME', 'jasperadmin', '  ', '', '报表服务器用户名', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'REPORT_PASSWORD', 'jasperadmin', '  ', '', '报表服务器密码', 'system', '', SYSDATE, 'system', SYSDATE);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'DATAADAPTER_SERVER_URL', 'http://192.168.70.211:9081/adapter/login.do', '  ', '', '数据适配器服务器地址', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'DATAADAPTER_SERVER_PATH', '/ZX', '01', null, '数据适配器服务器路径', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'DATAADAPTER_USERNAME', 'adapteradmin', '  ', '', '数据适配器服务器用户名', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'DATAADAPTER_PASSWORD', 'OK', '  ', '', '数据适配器服务器密码', 'system', '', SYSDATE, 'system', SYSDATE);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'REPORT_BATCH_EXPORT_PATH', 'prodName,reportName', '  ', '', '批量导出文件层级', 'system', '', SYSDATE, 'system', SYSDATE);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'REPORT_DEFAULT_FILENAME', '产品名称_报表名称_yyyymmdd_序号', '  ', '', '默认文件名称', 'system', '', SYSDATE, 'system', SYSDATE);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REP', 'REPORT_DOWNLOAD_PATH', '/home/tamsuser/download/', '01', null, '报表批量下载临时路径', 'system', null, SYSDATE, 'system', SYSDATE);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'PRE_GEN_DATE', '3', '  ', '', '预生成天数', 'system', '', SYSDATE, 'system', SYSDATE);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'REPORT_FILE', '/home/tamsuser/file/', '01', null, '信息披露sh脚本路径', 'system', null, SYSDATE, 'system', SYSDATE);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'is_show_myhome', '01', '01', null, '是否显示我的主页(01:是,02:否)', 'system', null, SYSDATE, 'system', SYSDATE);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'REP_REFRESH_JOBRULE', '10', '  ', '', '预计刷新执行时间（单位：分）', 'system', '', SYSDATE, 'system', SYSDATE);
/*具体适配器编号待定*/
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'REP_DATA_SOURCE_ID', '1042', '  ', '', '适配器编号', 'system', '', SYSDATE, 'system', SYSDATE);
/*节假日是否披露*/
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP', 'HOLIDAY_IS_WORK', '01', '  ', '', '节假日是否自动披露01 是(披露)02 否(不披露)', 'system', '', SYSDATE, 'system', SYSDATE);

/********************************流程信息******************************************/

--报表确认管理---确认走流程脚本
delete from sys_wfl_business_module where PAGE_ID = 'ReportConfirmManage001' and ELEMENT_ID ='ReportConfirmManage001_btn_submit';
insert into sys_wfl_business_module values ('ReportConfirmManage001', 'ReportConfirmManage001_btn_submit', 'oneApproval4', 'system', '0001', sysdate, 
'system',sysdate);

--报表披露管理--披露走流程脚本

delete from sys_wfl_business_module where PAGE_ID = 'ReportRevealManage001' and ELEMENT_ID ='ReportRevealManage001_btn_submit';
insert into sys_wfl_business_module values ('ReportRevealManage001', 'ReportRevealManage001_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system',sysdate);


/**********************************目录表**************************************/

DELETE FROM SYS_MENU WHERE MENU_ID LIKE 'REPORT_006%';
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006', 'REP', 'icon-xinxipilou', '', '', 6, '', '信息披露', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001', 'REP', '', 'REPORT_006', '', 1, '', '报表配置', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001_001', 'REP', '', 'REPORT_006_001', 'ReportTreeMenuConfiguration001', 5, '', '报表集合管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001_002', 'REP', '', 'REPORT_006_001', 'ReportMenuPowerConfig001', 3, '', '报表权限管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001_003', 'REP', '', 'REPORT_006_001', 'ReportContentEnum001', 6, '', '报表指标管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001_004', 'REP', '', 'REPORT_006_001', 'ReportModelManage001', 1, '', '报表模板管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001_005', 'REP', '', 'REPORT_006_001', 'ReportPageManage001', 2, '', '报表页面管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_002', 'REP', '', 'REPORT_006', '', 2, '', '报表管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_002_003', 'REP', '', 'REPORT_006_002', 'ReportRule001', 1, '', '报表规则设置', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_002_001', 'REP', '', 'REPORT_006_002', 'ReportWorkManage001', 2, '', '报表作业管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_002_002', 'REP', '', 'REPORT_006_002', 'ReportWorkMonitor001', 3, '', '报表作业监控', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_003', 'REP', '', 'REPORT_006', '', 3, '', '报表披露', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_003_001', 'REP', '', 'REPORT_006_003', 'RepDetailsShow001', 1, '', '报表生成管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_003_002', 'REP', '', 'REPORT_006_003', 'ReportConfirmManage001', 2, '02', '报表确认管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_003_003', 'REP', '', 'REPORT_006_003', 'ReportRevealManage001', 3, '02', '报表披露管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_004', 'REP', '', 'REPORT_006', '', 12, '02', '自定义报表', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_004_001', 'REP', '', 'REPORT_006_004', 'CustomerReportConfig001', 1, '02', '报表规则管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_004_002', 'REP', '', 'REPORT_006_004', 'CustomerReportPanel001', 2, '02', '报表面板管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_004_003', 'REP', '', 'REPORT_006_004', 'CustomerReportShow001', 3, '02', '报表查询展示', 'system', '', SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('REPORT_006_002_004', 'REP', '', 'REPORT_006_002', 'EstimateReveal001', 4, '', '预计披露', 'system', '', '2018-12-11 19:04:42', 'system', '2018-12-11 19:04:42');



/****************************页面表*************************************/
DELETE FROM SYS_PAGE WHERE MENU_ID LIKE 'REPORT_006%' ;
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RepDetailsShow001', 'REP', '/disclosure/information/reveal/detailshow/RepDetailsShow001', 'disclosure/information/reveal/detailshow/RepDetailsShow001', 'REPORT_006_002_001', '', '', '02', '02', '报表查询展示', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RepDetailsShow002', 'REP', '/disclosure/information/reveal/detailshow/RepDetailsShow002', 'disclosure/information/reveal/detailshow/RepDetailsShow002', 'REPORT_006_002_001', '', '', '02', '02', '报表查询展示', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportConfirmManage001', 'REP', '/disclosure/information/reveal/reportconfirm/ReportConfirmManage001', 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001', 'REPORT_006_002_002', '', '', '02', '01', '报表确认管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportConfirmManage003', 'REP', '/disclosure/information/reveal/reportconfirm/ReportConfirmManage003', 'disclosure/information/reveal/reportconfirm/ReportConfirmManage003', 'REPORT_006_002_002', '', '', '02', '01', '报表确认管理附件上传下载', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportContentEnum001', 'REP', '/disclosure/information/config/reportcontentenum/ReportContentEnum001', 'disclosure/information/config/reportcontentenum/ReportContentEnum001', 'REPORT_006_001_003', '', '', '02', '02', '报表指标管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportContentEnum002', 'REP', '/disclosure/information/config/reportcontentenum/ReportContentEnum002', 'disclosure/information/config/reportcontentenum/ReportContentEnum002', 'REPORT_006_001_003', '', '', '02', '02', '报表指标管理左侧栏新建', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportContentEnum003', 'REP', '/disclosure/information/config/reportcontentenum/ReportContentEnum003', 'disclosure/information/config/reportcontentenum/ReportContentEnum003', 'REPORT_006_001_003', '', '', '02', '02', '报表指标管理右侧表格新建', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportMenuPowerConfig001', 'REP', '/disclosure/information/reportpower/ReportMenuPowerConfig001', 'disclosure/information/reportpower/ReportMenuPowerConfig001', 'REPORT_006_001_002', '', '', '02', '02', '报表菜单权限配置', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportMenuPowerConfig002', 'REP', '/disclosure/information/reportpower/ReportMenuPowerConfig002', 'disclosure/information/reportpower/ReportMenuPowerConfig002', 'REPORT_006_001_002', '', '', '02', '02', '报表菜单权限配置', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportModelManage001', 'REP', '/disclosure/information/config/reportmodelmanage/ReportModelManage001', 'disclosure/information/config/reportmodelmanage/ReportModelManage001', 'REPORT_006_001_004', '', '', '02', '02', '报表模板配置', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportPageManage001', 'REP', '/disclosure/information/reportpage/ReportPageManage001', 'disclosure/information/reportpage/ReportPageManage001', 'REPORT_006_001_005', '', '', '02', '02', '报表模板配置', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportPageManage002', 'REP', '/disclosure/information/reportpage/ReportPageManage002', 'disclosure/information/reportpage/ReportPageManage002', 'REPORT_006_001_005', '', '', '02', '02', '报表模板配置', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportRevealManage001', 'REP', '/disclosure/information/reveal/reportreveal/ReportRevealManage001', 'disclosure/information/reveal/reportreveal/ReportRevealManage001', 'REPORT_006_002_003', '', '', '02', '01', '报表披露管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportRevealManage003', 'REP', '/disclosure/information/reveal/reportreveal/ReportRevealManage003', 'disclosure/information/reveal/reportreveal/ReportRevealManage003', 'REPORT_006_002_003', '', '', '02', '01', '报表披露管理附件上传下载', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportRule001', 'REP', '/disclosure/information/manage/workrule/ReportRule001', 'disclosure/information/manage/workrule/ReportRule001', 'REPORT_006_002_003', '', '', '02', '01', '报表规则设置详情', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportRule002', 'REP', '/disclosure/information/manage/workrule/ReportRule002', 'disclosure/information/manage/workrule/ReportRule002', 'REPORT_006_002_003', '', '', '02', '01', '报表规则设置详情', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportWorkManage001', 'REP', '/disclosure/information/manage/workmanage/ReportWorkManage001', 'disclosure/information/manage/workmanage/ReportWorkManage001', 'REPORT_006_002_001', '', '', '02', '01', '报表作业管理', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportWorkManage002', 'REP', '/disclosure/information/manage/workmanage/ReportWorkManage002', 'disclosure/information/manage/workmanage/ReportWorkManage002', 'REPORT_006_002_001', '', '', '02', '01', '报表作业新建', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportWorkMonitor001', 'REP', '/disclosure/information/manage/workmonitor/ReportWorkMonitor001', 'disclosure/information/manage/workmonitor/ReportWorkMonitor001', 'REPORT_006_002_002', '', '', '02', '01', '报表作业监控', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('ReportConfirmManage004', 'REP', '/disclosure/information/reveal/reportconfirm/ReportConfirmManage004', 'disclosure/information/reveal/reportconfirm/ReportConfirmManage004', 'REPORT_006_002_002', null, null, '02', '01', '报表手工上传', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('ReportTreeMenuConfiguration001', 'REP', '/disclosure/information/config/reporttree/ReportTreeMenuConfiguration001', 'disclosure/information/config/reporttree/ReportTreeMenuConfiguration001', 'REPORT_006_001_001', null, null, '02', '01', '报表树形菜单配置', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('ReportTreeMenuConfiguration002', 'REP', '/disclosure/information/config/reporttree/ReportTreeMenuConfiguration002', 'disclosure/information/config/reporttree/ReportTreeMenuConfiguration002', 'REPORT_006_001_001', null, null, '02', '01', '报表树形菜单配置', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('ReportTreeMenuConfiguration003', 'REP', '/disclosure/information/config/reporttree/ReportTreeMenuConfiguration003', 'disclosure/information/config/reporttree/ReportTreeMenuConfiguration003', 'REPORT_006_001_001', null, null, '02', '01', '报表树形菜单配置', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportTreeMenuConfiguration004', 'REP', '/disclosure/information/config/reporttree/ReportTreeMenuConfiguration004', 'disclosure/information/config/reporttree/ReportTreeMenuConfiguration004', 'REPORT_006_001_001', '', '', '02', '01', '报表树形菜单配置', 'system', '', SYSDATE, 'system', SYSDATE);

delete from SYS_PAGE where PAGE_ID='ReportWorkManage003';
insert into SYS_PAGE values ('ReportWorkManage003','REP','/disclosure/information/manage/workmanage/ReportWorkManage003','disclosure/information/manage/workmanage/ReportWorkManage003','','','','02','02','预览产品列表','system','system',to_date('2018/12/6', 'yyyy/mm/dd'), 'system',to_date('2018/12/6', 'yyyy/mm/dd'));

delete from SYS_PAGE where PAGE_ID='ReportWorkMonitor002';
delete from SYS_PAGE where PAGE_ID='EstimateReveal001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EstimateReveal001', 'REP', '/disclosure/information/manage/estimatereveal/EstimateReveal001', '/disclosure/information/manage/estimatereveal/EstimateReveal001', 'REPORT_006_002_004', null, null, '02', '02', '预计披露', 'system', null, '2018-12-11 00:00:00', 'system', '2018-12-11 00:00:00');
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ReportWorkMonitor002', 'REP', '/disclosure/information/manage/workmonitor/ReportWorkMonitor002', '/disclosure/information/manage/workmonitor/ReportWorkMonitor002', null, null, null, '02', '02', '查看作业详细', 'system', null, '2018-02-03 00:00:00', 'system', '2018-02-03 00:00:00');

update SYS_PAGE set is_need_workflow = '01' where page_id = 'ReportConfirmManage001';
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ReportRevealManage001';
/*********************************枚举值主表********************************/
DELETE FROM SYS_DATADICT WHERE DICT_CODE IN ('REVEAL_FREQUENCY','BASIS_DATE_TYPE','DATE_TYPE','REPORT_DATA_TYPE','DATA_RANGE','FILE_TYPE','REVEAL_CHANNEL');
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_FREQUENCY', 'REP', '披露频率', 'REVEAL_FREQUENCY', '1', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BASIS_DATE_TYPE', 'REP', '基准日', 'BASIS_DATE_TYPE', '1', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATE_TYPE', 'REP', '日期类型', 'DATE_TYPE', '1', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_DATA_TYPE', 'REP', '报表数据类型', 'REPORT_DATA_TYPE', '1', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', 'REP', '取数范围', 'DATA_RANGE', '1', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FILE_TYPE', 'REP', '文件类型', 'FILE_TYPE', '1', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_CHANNEL', 'REP', '披露渠道', 'REVEAL_CHANNEL', '1', 'system', '', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT WHERE DICT_CODE IN ('EXEC_STATUS','REP_INFO_TYPE','REPORT_TYPE');
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXEC_STATUS', 'REP', '批处理信息披露状态', 'EXEC_STATUS', '1', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP_INFO_TYPE', 'REP', '信息披露报表类型', 'REP_INFO_TYPE', '1', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_TYPE', 'REP', '报表启用状态', 'REPORT_TYPE', '1', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'CONFIRM_STATUS';
DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'REVEAL_STATUS';
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CONFIRM_STATUS', 'REP', '确认状态', 'CONFIRM_STATUS', '1', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REVEAL_STATUS', 'REP', '披露状态', 'REVEAL_STATUS', '1', 'system', null, SYSDATE, 'system', SYSDATE);

delete from SYS_DATADICT where DICT_CODE='REPORT_STYLE';
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_STYLE', 'REP', '报表类别', 'REPORT_STYLE', '1', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'EXEC_FLAG';
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXEC_FLAG', 'REP', '执行标志', 'EXEC_FLAG', '1', 'system', '', SYSDATE, 'system', SYSDATE);

/*数据字典表*/
delete from SYS_DATADICT where SYS_DATADICT.DICT_CODE='RULE_TYPE1';
insert into SYS_DATADICT values ('RULE_TYPE1','REP','作业规则1','','01','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT where SYS_DATADICT.DICT_CODE='RULE_TYPE2';
insert into SYS_DATADICT values ('RULE_TYPE2','REP','作业规则2','','01','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT where SYS_DATADICT.DICT_CODE='RULE_TYPE3';
insert into SYS_DATADICT values ('RULE_TYPE3','REP','作业规则3','','01','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT where SYS_DATADICT.DICT_CODE='RAISE_TYPE';
insert into SYS_DATADICT values ('RAISE_TYPE','REP','募集方式','','02','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT where SYS_DATADICT.DICT_CODE='PROFIT_TYPE';
insert into SYS_DATADICT values ('PROFIT_TYPE','REP','收益类型','','02','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT where SYS_DATADICT.DICT_CODE='JOB_RANGE_FLAG';
insert into SYS_DATADICT values ('JOB_RANGE_FLAG','REP','规则类型','','02','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT where SYS_DATADICT.DICT_CODE='INDEX_TYPE';
insert into SYS_DATADICT values ('INDEX_TYPE','REP','指标类型','','02','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT where SYS_DATADICT.DICT_CODE='ISSUE_STATUS';
insert into SYS_DATADICT values ('ISSUE_STATUS','REP','发行状态','','01','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT where SYS_DATADICT.DICT_CODE='OPEN_MODE';
insert into SYS_DATADICT values ('OPEN_MODE','REP','开放模式','','01','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT where SYS_DATADICT.DICT_CODE='ISSUE_MODE';
insert into SYS_DATADICT values ('ISSUE_MODE','REP','发行方式','','01','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));


delete from SYS_DATADICT where SYS_DATADICT.DICT_CODE='EXEC_RESULT';
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('EXEC_RESULT', 'REP', '执行结果', 'EXEC_RESULT', '1', 'system', null, '2018-12-12 20:11:54', 'system', '2018-12-12 20:11:54');

DELETE FROM SYS_DATADICT WHERE SYS_DATADICT.DICT_CODE='VALUE_STARTDAY';
INSERT INTO SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
VALUES ('VALUE_STARTDAY', 'REP','取数日','VALUE_STARTDAY','1','system',to_date('2018/12/06','yyyy/mm/dd'),'system',to_date('2018/12/06','yyyy/mm/dd'));
DELETE FROM SYS_DATADICT WHERE SYS_DATADICT.DICT_CODE='CONFIRM_TYPE';
INSERT INTO SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
VALUES ('CONFIRM_TYPE', 'REP','确认方式','CONFIRM_TYPE','1','system',to_date('2018/12/06','yyyy/mm/dd'),'system',to_date('2018/12/06','yyyy/mm/dd'));
DELETE FROM SYS_DATADICT WHERE SYS_DATADICT.DICT_CODE='REVEAL_TYPE';
INSERT INTO SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
VALUES ('REVEAL_TYPE', 'REP','披露方式','REVEAL_TYPE','1','system',to_date('2018/12/06','yyyy/mm/dd'),'system',to_date('2018/12/06','yyyy/mm/dd'));


DELETE FROM SYS_DATADICT WHERE SYS_DATADICT.DICT_CODE='TIME_TYPE';
INSERT INTO SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
VALUES ('TIME_TYPE', 'REP','时点类型','TIME_TYPE','1','system',to_date('2018/12/06','yyyy/mm/dd'),'system',to_date('2018/12/06','yyyy/mm/dd'));
DELETE FROM SYS_DATADICT WHERE SYS_DATADICT.DICT_CODE='PROD_INVEST_NATURE';
INSERT INTO SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
VALUES ('PROD_INVEST_TYPE', 'REP','产品投资性质','PROD_INVEST_NATURE','1','system',to_date('2018/12/06','yyyy/mm/dd'),'system',to_date('2018/12/06','yyyy/mm/dd'));

/*********************************枚举值详情表********************************/

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'REVEAL_CHANNEL';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES('REVEAL_CHANNEL','01','官网','1','0','system',TO_DATE('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES('REVEAL_CHANNEL','02','点对点','1','0','system',TO_DATE('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES('REVEAL_CHANNEL','03','其他','1','0','system',TO_DATE('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'REVEAL_FREQUENCY';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_FREQUENCY', 'A01', '每工作日', 1, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_FREQUENCY', 'A02', '每自然日', 2, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_FREQUENCY', 'A03', '每周', 3, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_FREQUENCY', 'A04', '每月', 4, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_FREQUENCY', 'A05', '每季度', 5, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_FREQUENCY', 'A06', '每半年', 6, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_FREQUENCY', 'A07', '每年', 4, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_FREQUENCY', 'B01', '每开放周期', 5, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_FREQUENCY', 'C02', '业务发生时', 6, '0', 'system', '', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'BASIS_DATE_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BASIS_DATE_TYPE', '01', '平台日', 1, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BASIS_DATE_TYPE', 'RAISE_STRDATE', '募集起始日', 2, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BASIS_DATE_TYPE', 'RAISE_ENDDATE', '募集结束日', 3, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BASIS_DATE_TYPE', '04', '开放周期结束日', 4, '0', 'system', '', SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('BASIS_DATE_TYPE','VDATE','起息日','5','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('BASIS_DATE_TYPE','MDATE','到期日','6','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('BASIS_DATE_TYPE','基准日','成立日','7','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('BASIS_DATE_TYPE','08','周期开始日','8','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('BASIS_DATE_TYPE','09','周期结束日','9','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('BASIS_DATE_TYPE','WINDUP_DATE','清盘日','10','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'DATE_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATE_TYPE', '01', '工作日', 1, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATE_TYPE', '02', '自然日', 2, '0', 'system', '', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'REPORT_DATA_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_DATA_TYPE', '01', '时点报表', 1, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_DATA_TYPE', '02', '区间报表', 2, '0', 'system', '', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'DATA_RANGE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '01', '上一工作日', 1, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '02', '上一自然日', 2, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '03', '上周', 3, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '04', '本周', 4, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '05', '上月', 5, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '06', '上月末最后一自然日', 6, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '07', '上月末最后一工作日', 7, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '08', '本月', 8, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '09', '本月末最后一自然日', 9, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '10', '本月末最后一工作日', 10, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '11', '上季度', 11, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '12', '上半年', 12, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '13', '上年', 13, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '14', '近N个自然日', 14, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_RANGE', '15', '近N个工作日', 15, '0', 'system', '', SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES('DATA_RANGE','16','基准日','16','0','system',to_date('2018-12-17','yyyy/mm/dd'),'system',to_date('2018-12-17','yyyy/mm/dd'));


DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'FILE_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FILE_TYPE', 'pdf', 'PDF', 1, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FILE_TYPE', 'xlsx', 'Excle', 2, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FILE_TYPE', 'docx', 'Word', 3, '0', 'system', '', SYSDATE, 'system', SYSDATE);


DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'EXEC_STATUS';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXEC_STATUS', '01', '未执行', 1, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXEC_STATUS', '02', '已执行', 2, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXEC_STATUS', '03', '已确认', 3, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXEC_STATUS', '04', '未披露', 4, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXEC_STATUS', '05', '已生成', 5, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXEC_STATUS', '06', '执行中', 6, '0', 'system', '', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'REP_INFO_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP_INFO_TYPE', '01', '报表', 1, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP_INFO_TYPE', '02', '集合', 2, '0', 'system', '', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'REPORT_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_TYPE', 'Y', '已启用', 1, '0', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_TYPE', 'N', '未启用', 2, '0', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'CONFIRM_STATUS';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CONFIRM_STATUS', '01', '已确认', 1, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CONFIRM_STATUS', '02', '未确认', 2, '0', 'system', '', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'REVEAL_STATUS';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_STATUS', '01', '已披露', 1, '0', 'system', '', SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVEAL_STATUS', '02', '未披露', 2, '0', 'system', '', SYSDATE, 'system', SYSDATE);

delete from SYS_DATADICT_LIST where DICT_CODE='REPORT_STYLE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_STYLE', '01', '销售文件信息披露', 1, '0', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_STYLE', '02', '成立公告', 2, '0', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_STYLE', '03', '重大事项公告', 3, '0', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_STYLE', '04', '净值披露', 4, '0', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_STYLE', '05', '临时性信息披露', 5, '0', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_STYLE', '06', '到期公告', 6, '0', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_STYLE', '07', '理财产品定期公告', 7, '0', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_STYLE', '08', '理财业务报告', 8, '0', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('REPORT_STYLE', '09', '其他公告', 9, '0', 'system', null, SYSDATE, 'system', SYSDATE);

delete from SYS_DATADICT_LIST where DICT_CODE='EXEC_RESULT';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('EXEC_RESULT', '01', '成功', 1, '0', 'system', null, '2018-12-12 00:00:00', 'system', '2018-12-12 00:00:00');
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('EXEC_RESULT', '02', '失败', 2, '0', 'system', null, '2018-12-12 00:00:00', 'system', '2018-12-12 00:00:00');
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('EXEC_RESULT', '03', '部分成功', 3, '0', 'system', null, '2018-12-12 00:00:00', 'system', '2018-12-12 00:00:00');
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('EXEC_RESULT', '04', '参数不完整', 4, '0', 'system', null, '2018-12-19 00:00:00', 'system', '2018-12-19 00:00:00');
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('EXEC_RESULT', '05', '报表不完整', 5, '0', 'system', null, '2018-12-19 00:00:00', 'system', '2018-12-19 00:00:00');
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('EXEC_RESULT', '06', '已确认报表', 5, '0', 'system', null, '2018-12-19 00:00:00', 'system', '2018-12-19 00:00:00');

INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('EXEC_RESULT', '08', '生成报表失败', 8, '0', 'system', null, '2019-01-07 00:00:00', 'system', '2019-01-07 00:00:00');

DELETE FROM SYS_DATADICT_LIST WHERE SYS_DATADICT_LIST.DICT_CODE='VALUE_STARTDAY';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('VALUE_STARTDAY','01','周一','1','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('VALUE_STARTDAY','02','周二','2','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('VALUE_STARTDAY','03','周三','3','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('VALUE_STARTDAY','04','周四','4','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('VALUE_STARTDAY','05','周五','5','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('VALUE_STARTDAY','06','周六','6','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('VALUE_STARTDAY','07','周日','7','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));

DELETE FROM SYS_DATADICT_LIST WHERE SYS_DATADICT_LIST.DICT_CODE='CONFIRM_TYPE';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('CONFIRM_TYPE','01','手动','1','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('CONFIRM_TYPE','02','自动','2','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));

DELETE FROM SYS_DATADICT_LIST WHERE SYS_DATADICT_LIST.DICT_CODE='REVEAL_TYPE';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('REVEAL_TYPE','01','手动','1','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('REVEAL_TYPE','02','自动','2','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));

DELETE FROM SYS_DATADICT_LIST WHERE SYS_DATADICT_LIST.DICT_CODE='TIME_TYPE';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('TIME_TYPE','01','基准日前','1','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('TIME_TYPE','02','基准日后','2','0','system',to_date('2018-12-06','yyyy/mm/dd'),'system',to_date('2018-12-06','yyyy/mm/dd'));

DELETE FROM SYS_DATADICT_LIST WHERE SYS_DATADICT_LIST.DICT_CODE='EXEC_FLAG';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('EXEC_FLAG','01','首次执行','1','0','system',to_date('2018-12-20','yyyy/mm/dd'),'system',to_date('2018-12-20','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('EXEC_FLAG','02','重新执行','2','0','system',to_date('2018-12-20','yyyy/mm/dd'),'system',to_date('2018-12-20','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('EXEC_FLAG','03','执行错误','3','0','system',to_date('2018-12-20','yyyy/mm/dd'),'system',to_date('2018-12-20','yyyy/mm/dd'));

DELETE FROM SYS_DATADICT_LIST WHERE SYS_DATADICT_LIST.DICT_CODE='PROD_INVEST_NATURE';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('PROD_INVEST_NATURE','01','固定收益类','1','0','system',to_date('2018-12-20','yyyy/mm/dd'),'system',to_date('2018-12-20','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('PROD_INVEST_NATURE','02','权益类','2','0','system',to_date('2018-12-20','yyyy/mm/dd'),'system',to_date('2018-12-20','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('PROD_INVEST_NATURE','03','商品及金融衍生品类','3','0','system',to_date('2018-12-20','yyyy/mm/dd'),'system',to_date('2018-12-20','yyyy/mm/dd'));
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
VALUES ('PROD_INVEST_NATURE','04','混合类','4','0','system',to_date('2018-12-20','yyyy/mm/dd'),'system',to_date('2018-12-20','yyyy/mm/dd'));

/**********************************级联菜单表***************************************/
/*数据字典明细关联表*/
delete from SYS_DATADICT_LIST_LINK where SYS_DATADICT_LIST_LINK.LINKED_DICT_CODE ='RULE_TYPE1';
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','=','RULE_TYPE1','PROFIT_TYPE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE3','01','RULE_TYPE1','PROFIT_TYPE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','=','RULE_TYPE1','VDATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','>','RULE_TYPE1','VDATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','<','RULE_TYPE1','VDATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','<=','RULE_TYPE1','VDATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','>=','RULE_TYPE1','VDATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE3','03','RULE_TYPE1','VDATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','=','RULE_TYPE1','RAISE_TYPE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE3','01','RULE_TYPE1','RAISE_TYPE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','=','RULE_TYPE1','MDATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','>','RULE_TYPE1','MDATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','<','RULE_TYPE1','MDATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','<=','RULE_TYPE1','MDATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','>=','RULE_TYPE1','MDATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE3','03','RULE_TYPE1','MDATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','=','RULE_TYPE1','ISSUE_MODE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE3','01','RULE_TYPE1','ISSUE_MODE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','=','RULE_TYPE1','ISSUE_STATUS','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE3','01','RULE_TYPE1','ISSUE_STATUS','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','=','RULE_TYPE1','WINDUP_DATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','>','RULE_TYPE1','WINDUP_DATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','<','RULE_TYPE1','WINDUP_DATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','<=','RULE_TYPE1','WINDUP_DATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','>=','RULE_TYPE1','WINDUP_DATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE3','03','RULE_TYPE1','WINDUP_DATE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','=','RULE_TYPE1','IS_INDEPE_ACC','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE3','02','RULE_TYPE1','IS_INDEPE_ACC','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE2','=','RULE_TYPE1','OPEN_MODE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('RULE_TYPE3','02','RULE_TYPE1','OPEN_MODE','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));


/*****************************画面数据字典明细关联表*************************************/

delete from SYS_PAGE_DICTLIST where SYS_PAGE_DICTLIST.PAGE_ID='ReportWorkManage002';
insert into SYS_PAGE_DICTLIST values ('ReportWorkManage002','RULE_TYPE2','=','system','',to_date('2018/12/6', 'yyyy/mm/dd'), 'system',to_date('2018/12/6', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ReportWorkManage002','RULE_TYPE2','>','system','',to_date('2018/12/6', 'yyyy/mm/dd'), 'system',to_date('2018/12/6', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ReportWorkManage002','RULE_TYPE2','<','system','',to_date('2018/12/6', 'yyyy/mm/dd'), 'system',to_date('2018/12/6', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ReportWorkManage002','RULE_TYPE2','<=','system','',to_date('2018/12/6', 'yyyy/mm/dd'), 'system',to_date('2018/12/6', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ReportWorkManage002','RULE_TYPE2','>=','system','',to_date('2018/12/6', 'yyyy/mm/dd'), 'system',to_date('2018/12/6', 'yyyy/mm/dd'));

delete from SYS_PAGE_DICTLIST where SYS_PAGE_DICTLIST.PAGE_ID='ReportWorkMonitor001';
delete from SYS_PAGE_DICTLIST where SYS_PAGE_DICTLIST.PAGE_ID='ReportWorkMonitor002';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, DICT_ID, DICT_ITEM_VAL, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ReportWorkMonitor001', 'EXEC_STATUS', '01', 'system', '', '2018-12-10 00:00:00', 'system', '2018-12-10 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, DICT_ID, DICT_ITEM_VAL, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ReportWorkMonitor001', 'EXEC_STATUS', '02', 'system', '', '2018-12-10 00:00:00', 'system', '2018-12-10 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, DICT_ID, DICT_ITEM_VAL, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ReportWorkMonitor002', 'EXEC_RESULT', '01', 'system', '', '2018-12-10 00:00:00', 'system', '2018-12-10 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, DICT_ID, DICT_ITEM_VAL, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ReportWorkMonitor002', 'EXEC_RESULT', '02', 'system', '', '2018-12-10 00:00:00', 'system', '2018-12-10 00:00:00');

delete from SYS_PAGE_DICTLIST where SYS_PAGE_DICTLIST.PAGE_ID='ReportRule002';
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','01','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','02','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999');          
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','03','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','04','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999'); 
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','05','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','06','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999'); 
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','07','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','08','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','09','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','10','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999'); 
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','11','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','12','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999'); 
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','13','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','14','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','15','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999'); 
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SUB_PAGE_ID) values ('ReportRule002','DATA_RANGE','16','system','',to_date('2018/1/9', 'yyyy/mm/dd'), 'system',to_date('2018/1/9', 'yyyy/mm/dd'),'999999');

/*********************************数据字典明细关联********************************/
delete from sys_datadict_list_link where item_code = '14' and linked_dict_code = 'REPORT_DATA_TYPE' and dict_code = 'DATA_RANGE' and LINKED_ITEM_CODE = '02';
delete from sys_datadict_list_link where item_code = '15' and linked_dict_code = 'REPORT_DATA_TYPE' and dict_code = 'DATA_RANGE' and LINKED_ITEM_CODE = '02';



/*********************************批处理任务表********************************/


DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '8';
INSERT INTO SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('8', 'REP', '信息披露生成报表并发送批处理', null, 'infoDisclosureGenAndSendService', 2, 'system', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '9';
INSERT INTO SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('9', 'REP', '读取作业信息并生成待处理信息', null, 'infoDisclosureReadAndGenService', 0, 'system', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '10';
INSERT INTO SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('10', 'REP', '信息披露扫描作业情况', null, 'InfoDisclosureQuartzRrunner', 2, 'system', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '11';
INSERT INTO SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('11', 'REP', '自动确认批处理', null, 'infoDiscAutoConfirmService', 3, 'system', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '12';
INSERT INTO SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('12', 'REP', '自动披露批处理', null, 'infoDiscAutoRevealTask', 4, 'system', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '13';
INSERT INTO SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('13', 'REP', '预处理作业信息', null, 'infoDiscPreReadAndGenService', 1, 'system', SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '14';
INSERT INTO SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('14', 'REP', '生成待处理详情', null, 'infoDiscGenDetailFileService', 1, 'system', SYSDATE, 'system', SYSDATE);

/*****************************************************************************/



/*数据字典明细表*/
delete from SYS_DATADICT_LIST where SYS_DATADICT_LIST.DICT_CODE ='RULE_TYPE1';
insert into SYS_DATADICT_LIST values ('RULE_TYPE1','PROFIT_TYPE','产品收益类型','1','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE1','VDATE','起息日','2','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE1','MDATE','到期日','3','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE1','RAISE_TYPE','募集方式','4','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE1','ISSUE_MODE','发行方式','5','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE1','ISSUE_STATUS','发行状态','7','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE1','WINDUP_DATE','清盘日','8','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE1','OPEN_MODE','开放模式','9','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE1','IS_INDEPE_ACC','是否独立开户','10','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));


delete from SYS_DATADICT_LIST where SYS_DATADICT_LIST.DICT_CODE ='RULE_TYPE2';
insert into SYS_DATADICT_LIST values ('RULE_TYPE2','=','=','1','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE2','>','>','2','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE2','<','<','3','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE2','<=','≤','4','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE2','>=','≥','5','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE2','like','like','6','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where SYS_DATADICT_LIST.DICT_CODE ='RULE_TYPE3';
insert into SYS_DATADICT_LIST values ('RULE_TYPE3','01','字段值','1','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE3','02','常量','2','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RULE_TYPE3','03','平台日','3','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));



delete from SYS_DATADICT_LIST where SYS_DATADICT_LIST.DICT_CODE ='PROFIT_TYPE';
insert into SYS_DATADICT_LIST values ('PROFIT_TYPE','01','净值','1','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PROFIT_TYPE','02','预期收益','2','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PROFIT_TYPE','03','货币','3','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where SYS_DATADICT_LIST.DICT_CODE ='RAISE_TYPE';
insert into SYS_DATADICT_LIST values ('RAISE_TYPE','1','公募','1','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RAISE_TYPE','2','私募','2','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where SYS_DATADICT_LIST.DICT_CODE ='JOB_RANGE_FLAG';
insert into SYS_DATADICT_LIST values ('JOB_RANGE_FLAG','01','作业表','1','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('JOB_RANGE_FLAG','02','指标值','2','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where SYS_DATADICT_LIST.DICT_CODE ='INDEX_TYPE';
insert into SYS_DATADICT_LIST values ('INDEX_TYPE','01','录入型','1','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('INDEX_TYPE','02','附件型','2','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where SYS_DATADICT_LIST.DICT_CODE ='ISSUE_STATUS';
insert into SYS_DATADICT_LIST values ('ISSUE_STATUS','01','初始','1','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ISSUE_STATUS','02','确认发行','2','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ISSUE_STATUS','03','发行成功','3','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ISSUE_STATUS','04','发行失败','4','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ISSUE_STATUS','05','不发行','5','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where SYS_DATADICT_LIST.DICT_CODE ='OPEN_MODE';
insert into SYS_DATADICT_LIST values ('OPEN_MODE','01','定期开放','1','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('OPEN_MODE','02','高频开放','2','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where SYS_DATADICT_LIST.DICT_CODE ='ISSUE_MODE';
insert into SYS_DATADICT_LIST values ('ISSUE_MODE','01','封闭式','1','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ISSUE_MODE','02','开放式','2','0','system','system',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));





/*画面数据字典明细关联表*/
delete from SYS_PAGE_DICTLIST where SYS_PAGE_DICTLIST.PAGE_ID='ReportWorkManage002';
insert into SYS_PAGE_DICTLIST values ('ReportWorkManage002','RULE_TYPE2','=','system','',to_date('2018/12/6', 'yyyy/mm/dd'), 'system',to_date('2018/12/6', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ReportWorkManage002','RULE_TYPE2','>','system','',to_date('2018/12/6', 'yyyy/mm/dd'), 'system',to_date('2018/12/6', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ReportWorkManage002','RULE_TYPE2','<','system','',to_date('2018/12/6', 'yyyy/mm/dd'), 'system',to_date('2018/12/6', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ReportWorkManage002','RULE_TYPE2','<=','system','',to_date('2018/12/6', 'yyyy/mm/dd'), 'system',to_date('2018/12/6', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ReportWorkManage002','RULE_TYPE2','>=','system','',to_date('2018/12/6', 'yyyy/mm/dd'), 'system',to_date('2018/12/6', 'yyyy/mm/dd'));

/*加产品预览页面*/
delete from SYS_PAGE where SYS_PAGE.PAGE_ID='ReportWorkManage003';
insert into SYS_PAGE values ('ReportWorkManage003','REP','/disclosure/information/manage/workmanage/ReportWorkManage003','disclosure/information/manage/workmanage/ReportWorkManage003','','','','02','02','预览产品列表','system','system',to_date('2018/12/6', 'yyyy/mm/dd'), 'system',to_date('2018/12/6', 'yyyy/mm/dd'));

/*修改sso登录参数  zhangyf 20181224 start*/
DELETE FROM sys_para WHERE system_code = 'SYS' AND para_code = 'ssoCookieOrSessionName';
INSERT INTO SYS_PARA VALUES ('SYS','ssoCookieOrSessionName','ssoUserName','01','字符串','SSO用户名在Session或cookie中存储的关键字（当系统开启SSO认证时，需要配置该参数）','system','',to_date('2018/7/23', 'yyyy/mm/dd'), 'system',to_date('2018/7/23', 'yyyy/mm/dd'));
/*修改sso登录参数  zhangyf 20181224 end*/

/*修改节假日信息菜单的page_file zhangyf 20180226 start*/
DELETE FROM sys_page WHERE PAGE_ID ='Holidaymarket001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('Holidaymarket001', 'REP', '/submitmaintenance/holidaymarket/Holidaymarket001', 'disclosure/submitmaintenance/holidaymarket/Holidaymarket001', 'REPORT_001004003', null, null, '02', '02', '节假日市场维护', 'system', null, sysdate, 'system', sysdate);
/*修改节假日信息菜单的page_file zhangyf 20180226 end*/

/*投资组合详细批处理 zhangyf 20180228 start*/
DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '15';
INSERT INTO SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('15', 'REP', '投资组合详细批处理', NULL, 'infoDiscInvestGroupDetailTask', 0, 'system', SYSDATE, 'system', SYSDATE);
/*投资组合详细批处理 zhangyf 20180228 end*/



