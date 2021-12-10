delete from sys_menu where menu_id='QS_MANAGEMENT_001_009';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QS_MANAGEMENT_001_009', 'TRD', null, 'QS_MANAGEMENT_001', '', 9, '01', '成交确认', 'system', null, sysdate, 'system', sysdate);
delete from sys_menu where page_id='CfetsTrdConfirm';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QS_MANAGEMENT_001_009_001', 'TRD', null, 'QS_MANAGEMENT_001_009', 'CfetsTrdConfirm', 10, '01', '成交确认', 'system', null, sysdate, 'system', sysdate);
delete from sys_page where page_id='CfetsTrdConfirm';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsTrdConfirm', 'TRD', 'home.html#/interbank/confirmation?a=a', '/cfets/trdconfirm/CfetsTrdConfirm', 'QS_MANAGEMENT_001_009_001', null, null, '02', '01', '成交确认', 'system', null, sysdate, 'system', sysdate);

delete from SYS_DATADICT where dict_code='REG_ASSET_TYPE';
INSERT INTO SYS_DATADICT(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('REG_ASSET_TYPE', 'COM', '登记资产类型', '', '02', 'system', NULL, sysdate, 'system', sysdate);


delete from sys_datadict_list where dict_code='REG_ASSET_TYPE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REG_ASSET_TYPE', '01', '债券', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REG_ASSET_TYPE', '02', '质押式回购', 2, '0', 'system', null, sysdate, 'system', sysdate, null);


delete from sys_para t where t.para_code='cfetsTrdConfirm_regList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'cfetsTrdConfirm_regList_refresh_period', '30000', '01', '', '交易执行列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

delete from sys_para t where t.para_code='cfetsTrdConfirm_cstpList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'cfetsTrdConfirm_cstpList_refresh_period', '30000', '01', '', 'CSTP列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

delete from sys_para t where t.para_code='cfetsTrdConfirm_matchList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'cfetsTrdConfirm_matchList_refresh_period', '30000', '01', '', '匹配确认列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

delete from sys_page_dictlist where PAGE_ID = 'CfetsTrdConfirm' and DICT_ID ='PS';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdConfirm', '999999', 'PS', 'T01', '', '', 'admin', '', sysdate, 'admin', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdConfirm', '999999', 'PS', 'T02', '', '', 'admin', '', sysdate, 'admin', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdConfirm', '999999', 'PS', '03', '', '', 'admin', '', sysdate, 'admin', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdConfirm', '999999', 'PS', '04', '', '', 'admin', '', sysdate, 'admin', sysdate, '');

delete from sys_page_dictlist where page_id='CfetsTrdConfirm' and dict_id='TRADE_PLACE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdConfirm', '999999', 'TRADE_PLACE', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdConfirm', '999999', 'TRADE_PLACE', '02', null, null, 'system', null, sysdate, 'system', sysdate, null);


delete from sys_page_dictlist where page_id='CfetsTrdConfirm' and dict_id='ACCOUNT_SPEED';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdConfirm', '999999', 'ACCOUNT_SPEED', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdConfirm', '999999', 'ACCOUNT_SPEED', '02', null, null, 'system', null, sysdate, 'system', sysdate, null);

delete from sys_para t where t.para_code='IS_QUERYALLCSTP_FLAG';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'IS_QUERYALLCSTP_FLAG', 'Y', '', '', '交易员是否可查看全部CSTP下行成交数据(Y:是，N:否)', 'system', '', sysdate, 'system', sysdate, '', '01');

delete from sys_datadict_list where dict_code='CSTP_BUSINESS_TYPE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CSTP_BUSINESS_TYPE', 'BOND', '现券买卖', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CSTP_BUSINESS_TYPE', 'REPO', '质押式回购', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

delete from sys_para where para_code='IS_SUPPORT_CSTP_FLAG';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('COM', 'IS_SUPPORT_CSTP_FLAG', 'Y', '01', null, '是否支持CSTP下行接口(Y:是，N:否)', 'system', null, sysdate, 'xq', sysdate, null, '01');

delete from sys_para where para_code='IS_CFETSSECTRD_MANUALCONFIRM_FLAG';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('COM', 'IS_CFETSSECTRD_MANUALCONFIRM_FLAG', 'Y', '01', null, '银行间债券交易执行是否可手工确认(Y:是，N:否)', 'system', null, sysdate, 'xq', sysdate, null, '01');
delete from sys_para where para_code='IS_CFETSREPOTRD_MANUALCONFIRM_FLAG';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('COM', 'IS_CFETSREPOTRD_MANUALCONFIRM_FLAG', 'Y', '01', null, '银行间回购交易执行是否可手工确认(Y:是，N:否)', 'system', null, sysdate, 'xq', sysdate, null, '01');

