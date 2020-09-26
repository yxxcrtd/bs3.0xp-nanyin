
/*画面数据字典明细关联：期次管理 - 利率类型*/
delete from SYS_PAGE_DICTLIST where page_id = 'PeriodicManagement002' and DICT_ID = 'INT_TYPE';
Insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
values ('PeriodicManagement002','999999','INT_TYPE','01','固定利率',null,'admin',null,sysdate,'admin',sysdate,null);
Insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
values ('PeriodicManagement002','999999','INT_TYPE','02','浮动利率',null,'admin',null,sysdate,'admin',sysdate,null);

