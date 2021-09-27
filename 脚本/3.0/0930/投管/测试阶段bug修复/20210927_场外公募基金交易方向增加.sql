delete from sys_page_dictlist where page_id='TrdFundInvestTradeOff001' and dict_id='PS' and DICT_ITEM_VAL='T65';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdFundInvestTradeOff001', '999999', 'PS', 'T65', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
