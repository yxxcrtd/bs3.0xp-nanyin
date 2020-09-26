delete from sys_page_dictlist where page_id = 'FinNetValueProductBuild002' and dict_id = 'PROFIT_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinNetValueProductBuild002', '999999', 'PROFIT_TYPE', '01', '净值型', null, 'admin', null, sysdate, 'admin', sysdate);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinNetValueProductBuild002', '999999', 'PROFIT_TYPE', '03', '货币型', null, 'admin', null, sysdate, 'admin', sysdate);
