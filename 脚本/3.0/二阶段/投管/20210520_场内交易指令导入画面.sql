DELETE FROM SYS_PAGE WHERE PAGE_ID='EtrExchangeImport';
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('EtrExchangeImport', 'ETR', '/invest/entrust/etrexchangetrade/EtrExchangeImport', 'invest/entrust/etrexchangetrade/EtrExchangeImport', '', NULL, NULL, '02', '01', '场内交易指令导入页面', 'system', NULL, sysdate, 'system', sysdate);


delete from sys_wfl_business_module where PAGE_ID = 'EtrExchangeImport' and ELEMENT_ID = 'EtrExchangeImport_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrExchangeImport', 'EtrExchangeImport_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate);