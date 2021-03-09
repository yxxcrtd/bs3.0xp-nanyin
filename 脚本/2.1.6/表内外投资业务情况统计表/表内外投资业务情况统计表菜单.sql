delete from SYS_MENU where MENU_ID = 'REPORT_001_005_002';
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_005_002', 'REP', null, 'REPORT_001_005', 'InvestmentsInfo001', 2, '02', '表内外投资业务情况统计表', 'system', null, sysdate, 'system', sysdate);
--画面page表
delete from SYS_PAGE where PAGE_ID = 'InvestmentsInfo001';
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestmentsInfo001', 'REP', '/invest/report/regulatoryreporting/investmentsinfo/InvestmentsInfo001', 'invest/report/regulatoryreporting/investmentsinfo/InvestmentsInfo001', 'REPORT_001_005_002', null, null, '02', '02', '表内外投资业务情况统计表', 'system', null, sysdate, 'system', sysdate);