
delete from sys_page_dictlist where PAGE_ID = 'ModStockDivitrateConfig001' and DICT_ID = 'SEC_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ModStockDivitrateConfig001', '999999', 'SEC_TYPE', '01', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ModStockDivitrateConfig001', '999999', 'SEC_TYPE', '13', null, null, 'system', null, sysDate, 'system', sysDate, null);

delete from sys_page_dictlist where PAGE_ID = 'ModStockDivitrateConfig002' and DICT_ID = 'SEC_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ModStockDivitrateConfig002', '999999', 'SEC_TYPE', '01', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ModStockDivitrateConfig002', '999999', 'SEC_TYPE', '13', null, null, 'system', null, sysDate, 'system', sysDate, null);

update sys_menu set remark = '预期股息增长率配置' where menu_id = 'VALUATION_001_010_004';

delete from sys_datadict_list where DICT_CODE = 'MODEL_SEC_DETAIL_TYPE' and  ITEM_CODE= '033';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_SEC_DETAIL_TYPE', '033', '优先股', 33, '0', 'system', null, sysDate, 'system', sysDate, null);
