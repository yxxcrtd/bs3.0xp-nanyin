
/***需求新增内外增级*/
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_PBC_CREDIT_MODE' AND ITEM_CODE = '3';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_PBC_CREDIT_MODE', '3', '内外增级', 1, '0', 'system', null,sysdate, 'system', sysdate, null);


DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_NEW_DEPT_TYPE' ;
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_NEW_DEPT_TYPE', 'SVR', '新人行增信机构类型', null, '02', 'system', null, SYSDATE, 'system',SYSDATE);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_NEW_DEPT_TYPE' ;
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_NEW_DEPT_TYPE', '1', '住户', 1, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_NEW_DEPT_TYPE', '2', '广义政府', 2, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_NEW_DEPT_TYPE', '3', '非金融性企业', 3, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_NEW_DEPT_TYPE', '4', '金融机构（实体）', 4, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_NEW_DEPT_TYPE', '5', '特定目的载体', 5, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_NEW_DEPT_TYPE', '6', '境外', 6, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);


update SYS_MENU set remark = 'PIB1产品基本信息(资金信托)' where page_id = 'PbcPrdBaseinfoSub';
update SYS_MENU set remark = 'PIB2产品起始募集信息(资金信托)' where page_id = 'PbcPrdInitRaiseSub';
update SYS_MENU set remark = 'PIE1产品终止信息(资金信托)' where page_id = 'PbcPrdBaseinfoEndSub';
update SYS_MENU set remark = 'PCD1产品资金募集信息(资金信托)' where page_id = 'PbcPrdCashRaiseSub';
update SYS_MENU set remark = 'PPB1资产池基本信息(资金信托)' where page_id = 'PbcPrdAccountSub';
update SYS_MENU set remark = 'PPE1资产池终止信息(资金信托)' where page_id = 'PbcPrdAccountEndSub';
update SYS_MENU set remark = 'PVD1资产负债信息(资金信托)' where page_id = 'PbcPrdAssetDebtSub';

/***修改字符类型.界面需要展示时间**/
ALTER TABLE PBC_SEND_ZIP_DAT_FILE MODIFY (SUBMIT_TIME TIMESTAMP(6));
