--新增菜单
delete from sys_page where PAGE_ID ='CfetsEtrInfoQuery';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsEtrInfoQuery', 'ETR', 'home.html#/interbank/instructionsearch?a=a', 'cfets/cfetsetrinfoquery/CfetsEtrInfoQuery', 'SETTLE_MANAGEMENT_001_002_008', '', '', '02', '01', '指令查询', 'system', '', sysdate, 'system', sysdate);

delete from sys_menu where MENU_ID ='SETTLE_MANAGEMENT_001_002_008';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SETTLE_MANAGEMENT_001_002_008', 'ETR', '', 'SETTLE_MANAGEMENT_001_002', 'CfetsEtrInfoQuery', 8, '02', '指令查询', 'system', '', sysdate, 'system', sysdate);

--自动刷新
delete from sys_para t where t.para_code='cfetsEtrInfoQuery_trade_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('STL', 'cfetsEtrInfoQuery_trade_refresh_period', '30000', '01', '', '银行间指令查询自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

--交易方向
delete from sys_page_dictlist where PAGE_ID = 'CfetsEtrInfoQuery' and DICT_ID IN ('PS','BACK_LEND_DIRECTION');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PS', 'T01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PS', 'T02', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PS', 'T03', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BACK_LEND_DIRECTION', '03', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BACK_LEND_DIRECTION', '04', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BACK_LEND_DIRECTION', '05', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BACK_LEND_DIRECTION', '06', '', '', 'system', '', sysdate, 'system', sysdate, '');

--资产类型
delete from sys_page_dictlist where PAGE_ID = 'CfetsEtrInfoQuery' and DICT_ID='PINAN_ASSET_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F07', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F10', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F12', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F16', '', '', 'system', '', sysdate, 'system', sysdate, '');


--业务类型
delete from sys_page_dictlist where PAGE_ID = 'CfetsEtrInfoQuery' and DICT_ID='BUSI_TYPE_POSITION';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '02', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '03', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '05', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '10', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '12', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '17', '', '', 'system', '', sysdate, 'system', sysdate, '');


--清算速度
delete from sys_page_dictlist where PAGE_ID = 'CfetsEtrInfoQuery' and DICT_ID='ACCOUNT_SPEED';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'ACCOUNT_SPEED', '01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'ACCOUNT_SPEED', '02', '', '', 'system', '', sysdate, 'system', sysdate, '');

--交易场所
delete from sys_page_dictlist where PAGE_ID = 'CfetsEtrInfoQuery' and DICT_ID='TRADE_PLACE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'TRADE_PLACE', '01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'TRADE_PLACE', '02', '', '', 'system', '', sysdate, 'system', sysdate, '');