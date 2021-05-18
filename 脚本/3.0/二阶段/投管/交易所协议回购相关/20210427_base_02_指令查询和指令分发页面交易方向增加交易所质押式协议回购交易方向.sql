----指令查询画面 交易方向下拉框新增到期续作，中途换券，提前购回，到期购回，解除质押
delete from sys_page_dictlist where PAGE_ID = 'EtrInvestAllInfo001' and DICT_ID = 'PS' and DICT_ITEM_VAL in ('T56','T93','T94','T95','T96');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrInvestAllInfo001', '999999', 'PS', 'T56', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrInvestAllInfo001', '999999', 'PS', 'T93', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrInvestAllInfo001', '999999', 'PS', 'T94', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrInvestAllInfo001', '999999', 'PS', 'T95', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrInvestAllInfo001', '999999', 'PS', 'T96', null, null, 'admin', null, sysdate, 'admin', sysdate);


----指令分发画面 交易方向下拉框新增到期续作，中途换券，提前购回，到期购回，解除质押
delete from sys_page_dictlist where PAGE_ID = 'TrdCommandDispatcher001' and DICT_ID = 'PS' and DICT_ITEM_VAL in ('T56','T93','T94','T95','T96');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdCommandDispatcher001', '999999', 'PS', 'T56', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdCommandDispatcher001', '999999', 'PS', 'T93', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdCommandDispatcher001', '999999', 'PS', 'T94', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdCommandDispatcher001', '999999', 'PS', 'T95', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdCommandDispatcher001', '999999', 'PS', 'T96', null, null, 'admin', null, sysdate, 'admin', sysdate);