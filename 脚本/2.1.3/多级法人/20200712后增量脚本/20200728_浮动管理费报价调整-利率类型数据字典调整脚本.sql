
/*画面数据字典明细关联：浮动管理费报价调整-利率类型*/
delete from SYS_PAGE_DICTLIST where page_id ='FinFloatMgtExpQuotation002' and dict_id ='INT_TYPE';
Insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
values ('FinFloatMgtExpQuotation002','999999','INT_TYPE','01',null,null,'system',null,sysdate,'system',sysdate,null);
Insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
values ('FinFloatMgtExpQuotation002','999999','INT_TYPE','02',null,null,'system',null,sysdate,'system',sysdate,null);

/*画面数据字典明细关联：产品业绩基准调整-利率类型*/
delete from SYS_PAGE_DICTLIST where page_id ='FinProductBmQuotation002' and dict_id ='INT_TYPE';
Insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
values ('FinProductBmQuotation002','999999','INT_TYPE','01',null,null,'system',null,sysdate,'system',sysdate,null);
Insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
values ('FinProductBmQuotation002','999999','INT_TYPE','02',null,null,'system',null,sysdate,'system',sysdate,null);

