--添加估值表读取处理监控菜单
delete from SYS_PAGE where PAGE_ID='RskOutValtblImportHis002';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskOutValtblImportHis002', 'RSK', '/invest/risk/outsvaltableimporthis/RskOutValtblImportHis002', 'invest/risk/outsvaltableimporthis/RskOutValtblImportHis002', 'RISK_MANAGEMENT_001_005_012', null, null, '02', '01', '估值表读取处理监控', 'system', null, sysdate, 'system', sysdate);
