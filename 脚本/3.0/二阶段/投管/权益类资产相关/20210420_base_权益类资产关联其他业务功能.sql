--/**实时资产头寸查询资产类型下拉框增加权益类资产**/
delete from sys_page_dictlist where PAGE_ID = 'ActualTimeAsset001' and DICT_ID = 'PINAN_ASSET_TYPE' and DICT_ITEM_VAL='F19';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ActualTimeAsset001', '999999', 'PINAN_ASSET_TYPE', 'F19', null, null, 'system', null, sysdate, 'system', sysdate);


--/**授信资产类型:非标债券和权益类资产**/
delete from sys_datadict_list where DICT_CODE = 'RISK_ASSET_TYPE' and ITEM_CODE in ('F19');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RISK_ASSET_TYPE', 'F19', '权益类资产', 19, '0', 'system', null, sysdate, 'system', sysdate);


--/**字典明细：授权类型一级**/
delete from sys_datadict_list where DICT_CODE = 'RSK_AUTHORIZE_TYPE1' and ITEM_CODE in ('F19');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RSK_AUTHORIZE_TYPE1', 'F19', '权益类资产', 19, '0', 'system', null, sysdate, 'system', sysdate);


--/** 应收款查询资产类型新增下拉枚举值 **/
delete from SYS_PAGE_DICTLIST where PAGE_ID = 'TrdSecOverdueDuration001' and SUB_PAGE_ID = '999999' and DICT_ID = 'PINAN_ASSET_TYPE' and DICT_ITEM_VAL = 'F19';
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 values ('TrdSecOverdueDuration001','999999','PINAN_ASSET_TYPE','F19',null,null,'system',null,sysdate,'system',sysdate);


--/** 自定义现金流查询画面 资产类型新增枚举值 **/
delete from SYS_PAGE_DICTLIST where PAGE_ID = 'PtlCashFlow001' and SUB_PAGE_ID = '999999' and DICT_ID = 'PINAN_ASSET_TYPE' and DICT_ITEM_VAL = 'F19';
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 values ('PtlCashFlow001','999999','PINAN_ASSET_TYPE','F19',null,null,'system',null,sysdate,'system',sysdate);

--/** 自定义现金流新建画面 资产类型新增枚举值 **/
delete from SYS_PAGE_DICTLIST where PAGE_ID = 'PtlCashFlow002' and SUB_PAGE_ID = '999999' and DICT_ID = 'PINAN_ASSET_TYPE' and DICT_ITEM_VAL = 'F19';
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 values ('PtlCashFlow002','999999','PINAN_ASSET_TYPE','F19',null,null,'system',null,sysdate,'system',sysdate);
