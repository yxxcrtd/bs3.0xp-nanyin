--资产减值管理界面增加页面数据字典限制：证券品种
DELETE FROM SYS_PAGE_DICTLIST T WHERE T.PAGE_ID='TrdAssetLoss001';
--只显示：债券、存款、回购、其他债权类
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdAssetLoss001', '999999', 'SEC_TYPE', '01', null, null, 'system', null, sysdate, 'system',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdAssetLoss001', '999999', 'SEC_TYPE', '07', null, null, 'system', null, sysdate, 'system',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdAssetLoss001', '999999', 'SEC_TYPE', '09', null, null, 'system', null, sysdate, 'system',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdAssetLoss001', '999999', 'SEC_TYPE', '13', null, null, 'system', null, sysdate, 'system',sysdate, null);


--删除无用数据字典：减值录入方式（按比例减值)
DELETE FROM Sys_Datadict t WHERE t.dict_code='LOSS_INPUT_PATTERN';
DELETE FROM sys_datadict_list t WHERE t.dict_code='LOSS_INPUT_PATTERN';

--资产减值流水表增加字段：备注 REMARK
ALTER TABLE TRD_ASSET_LOSS ADD REMARK VARCHAR2(200);
comment on column TRD_ASSET_LOSS.REMARK is
'备注';