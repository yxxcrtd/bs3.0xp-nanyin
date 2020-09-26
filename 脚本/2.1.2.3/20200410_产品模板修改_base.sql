delete from sys_page_dictlist where page_id = 'FinProductModule002' and dict_id = 'CALC_METHOD';

delete from sys_page_dictlist where page_id = 'FinProductModule002' and dict_id = 'CALC_RULE';

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductModule002', '999999', 'CALC_RULE', '01', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductModule002', '999999', 'CALC_RULE', '03', '首次开放日', null, 'admin', null, sysdate, 'admin', sysdate);






delete from sys_page_dictlist where page_id = 'FinProductModule002' and dict_id = 'FREQUENCY_UNIT';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductModule002', '999999', 'FREQUENCY_UNIT', '03', '月付', null, 'system', null, sysdate, 'system', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductModule002', '999999', 'FREQUENCY_UNIT', '04', '季付', null, 'system', null, sysdate, 'system', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductModule002', '999999', 'FREQUENCY_UNIT', '05', '年付', null, 'system', null, sysdate, 'system', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductModule002', '999999', 'FREQUENCY_UNIT', '06', '半年付', null, 'system', null, sysdate, 'system', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductModule002', '999999', 'FREQUENCY_UNIT', '07', '到期支付', null, 'system', null, sysdate, 'system', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductModule002', '999999', 'FREQUENCY_UNIT', '08', '不规则', null, 'system', null, sysdate, 'system', sysdate);











