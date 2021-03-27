delete from  SYS_MENU where page_id in ('ManuallyGeneratingPbcData001','PbcReportFileManage001');       
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_004_001002', 'REP', null, 'REPORT_004_001', 'ManuallyGeneratingPbcData001', 2, null, '手工生成人行报送文件', 'system', null, sysdate, 'system', sysdate);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_004_001001', 'REP', null, 'REPORT_004_001', 'PbcReportFileManage001', 1, null, '人行报送文件管理', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE page_id in ('ManuallyGeneratingPbcData001','PbcReportFileManage001');       
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ManuallyGeneratingPbcData001', 'SYS', '/pbc/classic/manuallygeneratingpbcdata/ManuallyGeneratingPbcData001', 'report/pbc/classic/manuallygeneratingpbcdata/ManuallyGeneratingPbcData001', 'REPORT_004_001002', null, null, '02', '02', '手工生成人行报送文件', 'system', null,SYSDATE, 'system',SYSDATE);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcReportFileManage001', 'SYS', '/pbc/classic/pbcreportfilemanage/PbcReportFileManage001', 'report/pbc/classic/pbcreportfilemanage/PbcReportFileManage001', 'REPORT_004_001001', null, null, '02', '02', '人行报送文件管理', 'system', null,SYSDATE, 'system',SYSDATE);
     