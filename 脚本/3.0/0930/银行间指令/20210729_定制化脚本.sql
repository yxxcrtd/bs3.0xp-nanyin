
DELETE FROM DYN_CONFIG_INFO dci WHERE DYN_ID ='EtrIbBondinfoMain';
INSERT INTO DYN_CONFIG_INFO
(DYN_ID, DYN_INFO, REMARK, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('EtrIbBondinfoMain', '{"all":[{"id":"ZLGLCC","name":"指令管理&持仓"},{"id":"ZLGL","name":"指令管理"},{"id":"CC","name":"持仓"}],"checked":{"settingSaveIds":[1],"pageList":["CC,ZLGLCC"]}}',
       '现券指令', 'ld', sysdate, 'ld', sysdate);

DELETE FROM DYN_CONFIG_INFO WHERE DYN_ID='EtrIbRepoMain';
INSERT INTO DYN_CONFIG_INFO
(DYN_ID, DYN_INFO, REMARK, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('EtrIbRepoMain', '{"all":[{"id":"ZLGLZYQGL","name":"指令管理&质押券管理"},{"id":"ZLGL","name":"指令管理"},{"id":"ZYQGL","name":"质押券管理"}],"checked":{"settingSaveIds":[1],"pageList":["ZLGLZYQGL"]}}',
       '银行间质押式回购指令', 'ld', sysdate , 'ld', sysdate);

DELETE FROM SYS_MENU sm WHERE PAGE_ID ='MyHome';
INSERT INTO SYS_MENU
(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('IB_DEAL_MANAGEMENT_001_001_003', 'STL', NULL, 'IB_DEAL_MANAGEMENT_001_001', 'MyHome', 3, '02', '设置页面', 'system', NULL, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE sm WHERE PAGE_ID ='MyHome';
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('MyHome', 'ETR', '/invest/entrust/home/Home', 'invest/entrust/home/Home', 'IB_DEAL_MANAGEMENT_001_001_003', NULL, NULL, '02', '02', '设置页面', 'system', NULL, sysdate, 'system', sysdate);

