--添加估值表读取处理监控菜单
delete from SYS_PAGE where PAGE_ID='RskOutValtblImportHis001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskOutValtblImportHis001', 'RSK', '/invest/risk/outsvaltableimporthis/RskOutValtblImportHis001', 'invest/risk/outsvaltableimporthis/RskOutValtblImportHis001', 'RISK_MANAGEMENT_001_005_012', null, null, '02', '01', '估值表读取处理监控', 'system', null, sysdate, 'system', sysdate);

delete from SYS_MENU where MENU_ID='RISK_MANAGEMENT_001_005_012';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RISK_MANAGEMENT_001_005_012', 'RSK', null, 'RISK_MANAGEMENT_001_005', 'RskOutValtblImportHis001', 12, '02', '估值表读取处理监控', 'system', null, sysdate, 'system', sysdate);

delete from SYS_BATCH_TASK where TASK_ID = '9998';
INSERT INTO SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('9998', 'ALL', '估值表批量自动导入', null, 'valBatchImportTask', 1, 'system', null, sysdate, 'system', sysdate);
