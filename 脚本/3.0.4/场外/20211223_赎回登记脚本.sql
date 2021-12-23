delete from sys_menu where page_id='CfetsTrdRedeem';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DEAL_MANAGEMENT_001_002_013', 'TRD', null, 'DEAL_MANAGEMENT_001_002', 'CfetsTrdRedeem', 13, '01', '赎回登记', 'system', null, sysdate, 'system', sysdate);
delete from sys_page where page_id='CfetsTrdRedeem';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsTrdRedeem', 'TRD', 'home.html#/interbank/redeem?a=a', '/cfets/trdredeem/CfetsTrdRedeem', 'DEAL_MANAGEMENT_001_002_013', null, null, '02', '01', '赎回登记', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where page_id in ('CfetsTrdRedeem001');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsTrdRedeem001', 'TRD', '/cfets/trdredeem/CfetsTrdRedeem001', 'cfets/trdredeem/CfetsTrdRedeem001', 'DEAL_MANAGEMENT_001_002_013', null, null, '02', '01', '赎回登记', 'system', null, sysdate, 'system', sysdate);


delete from sys_wfl_business_module where page_id='CfetsTrdRedeem001' and element_id='CfetsTrdRedeem001_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsTrdRedeem001', 'CfetsTrdRedeem001_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

delete from sys_wfl_business_module where page_id='CfetsTrdRedeem001' and element_id='CfetsTrdRedeem001_btn_save';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsTrdRedeem001', 'CfetsTrdRedeem001_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

delete from sys_wfl_business_module where page_id='CfetsTrdRedeem001' and element_id='CfetsTrdRedeem001_btn_revoke';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsTrdRedeem001', 'CfetsTrdRedeem001_btn_revoke', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');


delete from sys_para t where t.para_code='cfetsTrdRedeem_regList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'cfetsTrdRedeem_regList_refresh_period', '30000', '01', '', '查询赎回登记当前列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

delete from sys_page_dictlist where page_id='CfetsTrdRedeem' and dict_id='STATUS_VALID';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdRedeem', '999999', 'STATUS_VALID', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdRedeem', '999999', 'STATUS_VALID', '03', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdRedeem', '999999', 'STATUS_VALID', '04', null, null, 'system', null, sysdate, 'system', sysdate, null);







