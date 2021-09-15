  ----  删除数据

delete sys_page_dictlist where dict_id = 'TERM_TYPE_REBACK' and page_id = 'MstBankTranFeeManage002' and dict_item_val in ('R1Y','R9M');


------ insert
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MstBankTranFeeManage002', '999999', 'TERM_TYPE_REBACK', 'R1Y', null, null, 'admin', null, sysdate, 'admin',sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MstBankTranFeeManage002', '999999', 'TERM_TYPE_REBACK', 'R9M', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
