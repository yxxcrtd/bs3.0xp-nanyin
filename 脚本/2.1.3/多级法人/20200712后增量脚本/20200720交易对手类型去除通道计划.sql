--sjj add 20200720 交易对手管理去掉通道计划 start
delete from sys_page_dictlist where PAGE_ID='MstTradeOpponent002' and DICT_ID ='COUNTER_TYPE' and DICT_ITEM_VAL in ('01','02','04','05');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MstTradeOpponent002', '999999', 'COUNTER_TYPE', '01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MstTradeOpponent002', '999999', 'COUNTER_TYPE', '02', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MstTradeOpponent002', '999999', 'COUNTER_TYPE', '04', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MstTradeOpponent002', '999999', 'COUNTER_TYPE', '05', '', '', 'system', '', sysdate, 'system', sysdate, '');


delete from sys_page_dictlist where PAGE_ID='MstTradeOpponent001' and DICT_ID ='COUNTER_TYPE' and DICT_ITEM_VAL in ('01','02','04','05');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MstTradeOpponent001', '999999', 'COUNTER_TYPE', '01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MstTradeOpponent001', '999999', 'COUNTER_TYPE', '02', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MstTradeOpponent001', '999999', 'COUNTER_TYPE', '04', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MstTradeOpponent001', '999999', 'COUNTER_TYPE', '05', '', '', 'system', '', sysdate, 'system', sysdate, '');

--sjj add 20200720 交易对手管理去掉通道计划 end