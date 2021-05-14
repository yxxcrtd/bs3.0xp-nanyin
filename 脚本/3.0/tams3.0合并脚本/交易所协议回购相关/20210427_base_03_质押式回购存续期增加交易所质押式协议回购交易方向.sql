----质押式回购存续期 交易方向下拉框新增到期续作，中途换券，提前购回，到期购回，解除质押
delete from sys_page_dictlist where PAGE_ID = 'TrdRepoDuration001' and DICT_ID = 'PS' and DICT_ITEM_VAL in ('T56','T93','T94','T95','T96');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdRepoDuration001', '999999', 'PS', 'T56', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdRepoDuration001', '999999', 'PS', 'T93', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdRepoDuration001', '999999', 'PS', 'T94', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdRepoDuration001', '999999', 'PS', 'T95', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdRepoDuration001', '999999', 'PS', 'T96', null, null, 'admin', null, sysdate, 'admin', sysdate);