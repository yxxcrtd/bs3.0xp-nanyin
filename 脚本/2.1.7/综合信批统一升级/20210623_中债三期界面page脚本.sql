delete from sys_menu where PAGE_ID  IN ('ChbFeedbackFileManagement001','ChbSubinfoBatchLog001','ManuallyGeneratingPbcData001','PbcReportFileManage001',
'RegTimePoints001','ReportStatusInfo001','ReportUpInterfMonitor001','DebtSubmitInfo001');
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_001', 'REP', null, 'REPORT_001_006', 'RegTimePoints001', 1, '02', '监管报送时点维护', 'system', null, SYSDATE, 'system', SYSDATE);
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_002', 'REP', null, 'REPORT_001_006', 'PbcReportFileManage001', 2, null, '人行报送文件管理','system', null, SYSDATE, 'system', SYSDATE);
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_003', 'REP', null, 'REPORT_001_006', 'ManuallyGeneratingPbcData001', 3, null, '手工生成人行报送文件','system', null, SYSDATE, 'system', SYSDATE);
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_004', 'REP', null, 'REPORT_001_006', 'ReportUpInterfMonitor001', 4, '02', '上游接口文件读取处理监控', 'system', null, SYSDATE, 'system', SYSDATE);
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_005', 'REP', null, 'REPORT_001_006', 'DebtSubmitInfo001', 5, '02', '报送情况统计及反馈','system', null, SYSDATE, 'system', SYSDATE);
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_006', 'REP', null, 'REPORT_001_006', 'ReportStatusInfo001', 6, '02', '发送状态信息查询','system', null, SYSDATE, 'system', SYSDATE);
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_007', 'REP', null, 'REPORT_001_006', 'ChbFeedbackFileManagement001', 7, '02', '中债反馈文件管理','system', null, SYSDATE, 'system', SYSDATE);
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_008', 'REP', null, 'REPORT_001_006', 'ChbSubinfoBatchLog001', 8, '02', '中债三期大数据量报送文件下载','system', null, SYSDATE, 'system', SYSDATE);


delete from SYS_PAGE where PAGE_ID  IN ('ChbFeedbackFileManagement001','ChbSubinfoBatchLog001','ManuallyGeneratingPbcData001','PbcReportFileManage001',
'RegTimePoints001','ReportStatusInfo001','ReportUpInterfMonitor001','DebtSubmitInfo001');
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ChbFeedbackFileManagement001', 'REP', '/svreport/system/chbfeedbackfilemanagement/ChbFeedbackFileManagement001', 'report/system/chbfeedbackfilemanagement/ChbFeedbackFileManagement001', 'REPORT_001_006_007', null, null, '02', '02', '中债反馈文件管理', 'system', null,sysdate, 'system', sysdate);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ChbSubinfoBatchLog001', 'REP', '/svreport/system/subinfobatchlog/ChbSubinfoBatchLog001', 'report/system/subinfobatchlog/ChbSubinfoBatchLog001', 'REPORT_001_006_008', null, null, '02', '02', '中债报送Excel下载', 'system', null,sysdate, 'system', sysdate);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ManuallyGeneratingPbcData001', 'SYS', '/pbc/classic/manuallygeneratingpbcdata/ManuallyGeneratingPbcData001', 'report/pbc/classic/manuallygeneratingpbcdata/ManuallyGeneratingPbcData001', 'REPORT_001_006_003', null, null, '02', '02', '手工生成人行报送文件', 'system', null,sysdate, 'system', sysdate);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcReportFileManage001', 'SYS', '/pbc/classic/pbcreportfilemanage/PbcReportFileManage001', 'report/pbc/classic/pbcreportfilemanage/PbcReportFileManage001', 'REPORT_001_006_002', null, null, '02', '02', '人行报送文件管理', 'system', null,sysdate, 'system', sysdate);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RegTimePoints001', 'REP', '/svreport/system/regtimepoints/RegTimePoints001', 'report/system/regtimepoints/RegTimePoints001', 'REPORT_001_006_001', null, null, '02', '02', '监管报送时点维护', 'system', null,sysdate, 'system', sysdate);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportStatusInfo001', 'REP', '/svreport/system/reportstatusinfo/ReportStatusInfo001', 'report/system/reportstatusinfo/ReportStatusInfo001', 'REPORT_001_006_006', null, null, '02', '02', '发送状态信息查询', 'system', null,sysdate, 'system', sysdate);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportUpInterfMonitor001', 'REP', '/svreport/system/upinterfmonitor/ReportUpInterfMonitor001', 'report/system/upinterfmonitor/ReportUpInterfMonitor001', 'REPORT_001_006_004', null, null, '02', '02', '上游接口文件读取处理监控', 'system', null,sysdate, 'system', sysdate);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DebtSubmitInfo001', 'REP', '/svreport/system/debtsubmitinfo/DebtSubmitInfo001', 'report/system/debtsubmitinfo/DebtSubmitInfo001', 'REPORT_001_006_005', null, null, '02', '02', '中债报送信息反馈', 'system', null,sysdate, 'system', sysdate);

