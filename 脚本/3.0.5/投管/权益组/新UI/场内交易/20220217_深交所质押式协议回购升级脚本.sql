delete from sys_page where PAGE_ID = 'EtrExchangeAgreRepTrade002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrExchangeAgreRepTrade002', 'ETR', '/etrexchange/agreementrep/EtrExchangeAgreRepTrade002', 'etrexchange/agreementrep/EtrExchangeAgreRepTrade002', null, null, null, '02', '01', '协议回购指令页面', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_WFL_BUSINESS_MODULE where PAGE_ID='EtrExchangeAgreRepTrade002'  and ELEMENT_ID='EtrExchangeAgreRepTrade002_btn_submit';
INSERT INTO SYS_WFL_BUSINESS_MODULE(PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('EtrExchangeAgreRepTrade002', 'EtrExchangeAgreRepTrade002_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

DELETE FROM SYS_WFL_BUSINESS_MODULE where PAGE_ID='EtrExchangeAgreRepTrade002'  and ELEMENT_ID='EtrExchangeAgreRepTrade002_btn_save';
INSERT INTO SYS_WFL_BUSINESS_MODULE(PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('EtrExchangeAgreRepTrade002', 'EtrExchangeAgreRepTrade002_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');
