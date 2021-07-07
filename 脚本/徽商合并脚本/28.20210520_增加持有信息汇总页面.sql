--增加持有信息汇总页面
delete from SYS_PAGE where PAGE_ID = 'InvestorsQualityCheck002';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('InvestorsQualityCheck002', 'SVR', '/svreport/submitexamination/investorsqualitycheck/InvestorsQualityCheck002', 'report/submitexamination/investorsqualitycheck/InvestorsQualityCheck002', 'REPORT_001_002_007', null, null, '02', '02', '持有信息汇总', 'system', null, sysdate, 'system', sysdate);
