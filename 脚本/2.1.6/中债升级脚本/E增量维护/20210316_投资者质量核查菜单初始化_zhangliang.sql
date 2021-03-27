DELETE FROM SYS_MENU WHERE PAGE_ID = 'InvestorsQualityCheck001';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_001_002_004', 'REP', null, 'REPORT_001_002', 'InvestorsQualityCheck001', 4, '02', '投资者质量核查', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'InvestorsQualityCheck001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('InvestorsQualityCheck001', 'REP', '/svreport/submitexamination/investorsqualitycheck/InvestorsQualityCheck001', 'report/submitexamination/investorsqualitycheck/InvestorsQualityCheck001', 'REPORT_001_002_004', null, null, '02', '02', '投资者质量核查', 'system', null, sysdate, 'system', sysdate);
