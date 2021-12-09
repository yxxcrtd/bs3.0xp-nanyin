delete from sys_menu where page_id='CfetsTrdQuery';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DEAL_MANAGEMENT_001_002_012', 'TRD', null, 'DEAL_MANAGEMENT_001_002', 'CfetsTrdQuery', 12, '01', '交易查询', 'system', null, sysdate, 'system', sysdate);
delete from sys_page where page_id='CfetsTrdQuery';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsTrdQuery', 'TRD', 'home.html#/interbank/transactionsearch?a=a', '/cfets/trdquery/CfetsTrdQuery', 'DEAL_MANAGEMENT_001_002_012', null, null, '02', '01', '交易查询', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where page_id in ('CfetsTrdBondInvestTrade002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsTrdBondInvestTrade002', 'TRD', '/cfets/trdquery/CfetsTrdBondInvestTrade002', 'cfets/trdquery/CfetsTrdBondInvestTrade002', 'DEAL_MANAGEMENT_001_002_012', null, null, '02', '01', '银行间交易', 'system', null, sysdate, 'system', sysdate);


delete from sys_wfl_business_module where page_id='CfetsTrdBondInvestTrade002' and element_id='CfetsTrdBondInvestTrade002_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsTrdBondInvestTrade002', 'CfetsTrdBondInvestTrade002_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

delete from sys_wfl_business_module where page_id='CfetsTrdBondInvestTrade002' and element_id='CfetsTrdBondInvestTrade002_btn_save';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsTrdBondInvestTrade002', 'CfetsTrdBondInvestTrade002_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');



delete from sys_page_dictlist where PAGE_ID = 'CfetsTrdQuery' and DICT_ID ='PS';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'PS', 'T01', '', '', 'admin', '', sysdate, 'admin', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'PS', 'T02', '', '', 'admin', '', sysdate, 'admin', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'PS', 'T03', '', '', 'admin', '', sysdate, 'admin', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'PS', '03', '', '', 'admin', '', sysdate, 'admin', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'PS', '04', '', '', 'admin', '', sysdate, 'admin', sysdate, '');


delete from sys_page_dictlist where page_id='CfetsTrdQuery' and dict_id='TRADE_PLACE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'TRADE_PLACE', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'TRADE_PLACE', '02', null, null, 'system', null, sysdate, 'system', sysdate, null);


delete from sys_page_dictlist where page_id='CfetsTrdQuery' and dict_id='MATCH_STATUS';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'MATCH_STATUS', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'MATCH_STATUS', '02', null, null, 'system', null, sysdate, 'system', sysdate, null);


delete from sys_page_dictlist where page_id='CfetsTrdQuery' and dict_id='ACCOUNT_SPEED';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'ACCOUNT_SPEED', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'ACCOUNT_SPEED', '02', null, null, 'system', null, sysdate, 'system', sysdate, null);

delete from sys_page_dictlist where page_id='CfetsTrdQuery' and dict_id='STATUS_VALID';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'STATUS_VALID', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'STATUS_VALID', '03', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsTrdQuery', '999999', 'STATUS_VALID', '04', null, null, 'system', null, sysdate, 'system', sysdate, null);


delete from sys_para t where t.para_code='cfetsTrdQuery_regCurList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'cfetsTrdQuery_regCurList_refresh_period', '30000', '01', '', '交易查询登记当前列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

delete from sys_para t where t.para_code='cfetsTrdQuery_ibuCurList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'cfetsTrdQuery_ibuCurList_refresh_period', '30000', '01', '', '交易查询当前报价列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

delete from sys_para t where t.para_code='cfetsTrdQuery_cstpCurList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'cfetsTrdQuery_cstpCurList_refresh_period', '30000', '01', '', '交易查询当前成交列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

delete from sys_para t where t.para_code='cfetsTrdQuery_regPreList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'cfetsTrdQuery_regPreList_refresh_period', '30000', '01', '', '交易查询历史登记列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

delete from sys_para t where t.para_code='cfetsTrdQuery_ibuPreList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'cfetsTrdQuery_ibuPreList_refresh_period', '30000', '01', '', '交易查询历史报价列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

delete from sys_para t where t.para_code='cfetsTrdQuery_cstpPreList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'cfetsTrdQuery_cstpPreList_refresh_period', '30000', '01', '', '交易查询历史成交列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

delete from sys_para t where t.para_code='cfetsTrdQuery_trdList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'cfetsTrdQuery_trdList_refresh_period', '30000', '01', '', '交易查询汇总交易查询列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

delete from sys_para t where t.para_code='cfetsTrdQuery_trdDetailList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'cfetsTrdQuery_trdDetailList_refresh_period', '30000', '01', '', '交易查询明细交易查询列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');




delete from sys_datadict where DICT_CODE = 'REG_STATUS';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REG_STATUS', 'TG', '登记状态', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where dict_code='REG_STATUS';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REG_STATUS', '01', '待确认', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REG_STATUS', '02', '已确认', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REG_STATUS', '03', '已撤销', 3, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REG_STATUS', '04', '流程中', 4, '0', 'system', null, sysdate, 'system', sysdate, null);

delete from sys_datadict where DICT_CODE = 'CSTP_MATCH_STATUS';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CSTP_MATCH_STATUS', 'TG', 'CSTP匹配状态', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where dict_code='REG_STATUS';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CSTP_MATCH_STATUS', '01', '待匹配', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CSTP_MATCH_STATUS', '02', '待确认', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CSTP_MATCH_STATUS', '03', '已确认', 3, '0', 'system', null, sysdate, 'system', sysdate, null);

