--违约损失率管理页面数据字典限制：证券品种
DELETE FROM SYS_PAGE_DICTLIST T WHERE T.PAGE_ID='LossGivenDefaultMaintain001';
--只显示：债券、存款、回购、其他债权类
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('LossGivenDefaultMaintain001', '999999', 'SEC_TYPE', '01', null, null, 'system', null, sysdate, 'system',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('LossGivenDefaultMaintain001', '999999', 'SEC_TYPE', '07', null, null, 'system', null, sysdate, 'system',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('LossGivenDefaultMaintain001', '999999', 'SEC_TYPE', '09', null, null, 'system', null, sysdate, 'system',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('LossGivenDefaultMaintain001', '999999', 'SEC_TYPE', '13', null, null, 'system', null, sysdate, 'system',sysdate, null);
