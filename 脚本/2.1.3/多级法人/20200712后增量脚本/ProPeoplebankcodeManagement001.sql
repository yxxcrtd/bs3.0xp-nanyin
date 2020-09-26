DELETE FROM SYS_MENU WHERE MENU_ID = 'REPORT_001_001_009';
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_009', 'REP', null, 'REPORT_001_001', 'ProPeoplebankcodeManagement001', '6', '02', '人行产品资产池代码管理', 'system', null,SYSDATE, 'system', SYSDATE);

DELETE FROM sys_page WHERE PAGE_ID = 'ProPeoplebankcodeManagement001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ProPeoplebankcodeManagement001', 'REP', '/svreport/system/peoplebankcodepoolmanagement/ProPeoplebankcodeManagement001', 'report/system/peoplebankcodepoolmanagement/ProPeoplebankcodeManagement001', 'REPORT_001_001_009', null, null, '02', '02', '人行产品资产池代码管理', 'system', null, SYSDATE, 'system',SYSDATE);
