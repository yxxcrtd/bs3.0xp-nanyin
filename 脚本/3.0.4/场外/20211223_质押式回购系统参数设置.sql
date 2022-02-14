
DELETE FROM SYS_PAGE sp WHERE PAGE_ID ='CfetsEtrIbRepoinfo003' OR PAGE_ID ='CfetsEtrIbBondinfo003';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES('CfetsEtrIbRepoinfo003', 'ETR', '/cfets/etrib/CfetsEtrIbRepoinfo003', 'cfets/etrib/repo/CfetsEtrIbRepoinfo003', NULL , NULL, NULL, '02', '01', '本币业务指令-质押式回购', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES('CfetsEtrIbBondinfo003', 'ETR', '/cfets/etrib/CfetsEtrIbBondinfo003', 'cfets/etrib/bond/CfetsEtrIbBondinfo003', 'SETTLE_MANAGEMENT_001_002_007', NULL, NULL, '02', '01', '本币业务指令-债券', 'system', NULL, sysdate, 'system', sysdate);

