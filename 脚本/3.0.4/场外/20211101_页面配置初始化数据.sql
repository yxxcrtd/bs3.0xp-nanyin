delete from sys_menu where menu_id='SETTLE_MANAGEMENT_001_002_007';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SETTLE_MANAGEMENT_001_002_007', 'STL', null, 'SETTLE_MANAGEMENT_001_002', 'CfetsEtrBondInfo', 7, '02', '本币业务指令', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where page_id in ('CfetsEtrIbBondinfo003','CfetsEtrBondInfo');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsEtrIbBondinfo003', 'ETR', '/cfets/etrib/CfetsEtrIbBondinfo003', 'cfets/etrib/CfetsEtrIbBondinfo003', 'SETTLE_MANAGEMENT_001_002_007', null, null, '02', '01', '本币业务指令', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsEtrBondInfo', 'ETR', 'home.html?a=a', '/cfets/etrib/cfetsEtrIbBondinfo003', 'SETTLE_MANAGEMENT_001_002_007', null, null, '02', '01', '本币业务指令', 'system', null, sysdate, 'system', sysdate);


delete from sys_wfl_business_module where page_id='CfetsEtrIbBondinfo003' and element_id='CfetsEtrIbBondinfo003_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsEtrIbBondinfo003', 'CfetsEtrIbBondinfo003_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

delete from sys_wfl_business_module where page_id='CfetsEtrIbBondinfo003' and element_id='CfetsEtrIbBondinfo003_btn_save';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsEtrIbBondinfo003', 'CfetsEtrIbBondinfo003_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');


delete from sys_page_dictlist where page_id='CfetsEtrBondInfo'  and dict_id='PRICE_MODEL';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrBondInfo', '999999', 'PRICE_MODEL', 'PRM01', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrBondInfo', '999999', 'PRICE_MODEL', 'PRM02', null, null, 'system', null, sysdate, 'system',sysdate, null);


delete from sys_page_dictlist where page_id='CfetsEtrBondInfo'  and dict_id='ACCOUNT_SPEED';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrBondInfo', '999999', 'ACCOUNT_SPEED', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrBondInfo', '999999', 'ACCOUNT_SPEED', '02', null, null, 'system', null, sysdate, 'system',sysdate, null);

delete from sys_page_dictlist where page_id='CfetsEtrBondInfo'  and dict_id='PS';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrBondInfo', '999999', 'PS', 'T01', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrBondInfo', '999999', 'PS', 'T02', null, null, 'system', null, sysdate, 'system',sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrBondInfo', '999999', 'PS', 'T03', null, null, 'system', null, sysdate, 'system',sysdate, null);
