delete from sys_page_dictlist where PAGE_ID = 'MstTradeOpponent001' and DICT_ID = 'COUNTER_TYPE' and DICT_ITEM_VAL = '06';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstTradeOpponent001', '999999', 'COUNTER_TYPE', '06', null, null, 'system', null, sysdate, 'system', sysdate);

delete from sys_page_dictlist where PAGE_ID = 'MstTradeOpponent002' and DICT_ID = 'COUNTER_TYPE' and DICT_ITEM_VAL = '06';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstTradeOpponent002', '999999', 'COUNTER_TYPE', '06', null, null, 'system', null, sysdate, 'system', sysdate);