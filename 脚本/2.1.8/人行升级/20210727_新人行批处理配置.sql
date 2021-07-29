DELETE FROM SYS_BATCH_TASK WHERE BEAN_NAME = 'svrNewPbcSinglePrdReportTaskService';
insert into SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3055', 'SVR', '新人行单产品报表生成', null, 'svrNewPbcSinglePrdReportTaskService', 3260, 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_MENU WHERE PAGE_ID = 'PbcSendZipDatFile001';
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_004_010', 'SVR', null, 'REPORT_001_004', 'PbcSendZipDatFile001', 10, '02', '人行报送文件管理', 'system', null, SYSDATE, 'system', SYSDATE);


DELETE FROM SYS_PAGE WHERE PAGE_ID = 'PbcSendZipDatFile001';
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcSendZipDatFile001', 'MST', '/pbc/prdbaseinfosub/PbcSendZipDatFile001', 'report/pbc/classic/prdbaseinfosub/PbcSendZipDatFile001', 'REPORT_001_004_010', null, null, '02', '01', '人行报送文件管理', 'system', null, 	SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'PbcSendZipDatFile002';
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcSendZipDatFile002', 'MST', '/pbc/prdbaseinfosub/PbcSendZipDatFile002', 'report/pbc/classic/prdbaseinfosub/PbcSendZipDatFile002', 'REPORT_001_004_010', null, null, '02', '01', '人行报送文件管理', 'system', null, 	SYSDATE, 'system', SYSDATE);

