delete from sys_page_dictlist where PAGE_ID = 'ManualSettle001' and DICT_ID = 'TRADE_PLACE' and DICT_ITEM_VAL in ('03','04');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ManualSettle001', '999999', 'TRADE_PLACE', '03', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ManualSettle001', '999999', 'TRADE_PLACE', '04', null, null, 'system', null, sysdate, 'system', sysdate, null);