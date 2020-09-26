--产品台账，收益类型为预期收益型
delete from SYS_PAGE_DICTLIST where PAGE_ID = 'FinNetValueProductBuild002' and DICT_ID = 'PROFIT_TYPE' and DICT_ITEM_VAL in ('02');
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinNetValueProductBuild002', '999999', 'PROFIT_TYPE', '02', '预期收益型', null, 'admin', null, sysdate, 'admin', sysdate);
