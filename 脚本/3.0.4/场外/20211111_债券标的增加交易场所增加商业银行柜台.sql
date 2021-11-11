delete from sys_datadict_list  where dict_code='TRADE_PLACE' and item_code='02';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TRADE_PLACE', '02', '商业银行柜台', 14, '0', 'system', null, sysdate, 'system', sysdate, null);

delete from sys_page_dictlist where page_id='FinBondTargetAsset002' and dict_id='TRADE_PLACE' and dict_item_val='02';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FinBondTargetAsset002', '999999', 'TRADE_PLACE', '02', null, null, 'system', null, sysdate, 'system', sysdate, null);

