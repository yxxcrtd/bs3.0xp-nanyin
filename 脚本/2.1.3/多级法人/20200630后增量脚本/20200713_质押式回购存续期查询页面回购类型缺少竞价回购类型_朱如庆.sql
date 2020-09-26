delete from sys_page_dictlist d where d.page_id ='TrdRepoDuration001' and d.dict_id='TARGET_BACK_TYPE' and d.dict_item_val='H02';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdRepoDuration001', '999999', 'TARGET_BACK_TYPE', 'H02', null, null, 'system', null, sysdate, 'system', sysdate, null);
