--修改表主键、索引
-- Create/Recreate primary, unique and foreign key constraints 
alter table FIN_PRODUCT_VALUATION  drop constraint PK_FIN_PRODUCT_VALUATION cascade;
-- Create/Recreate indexes 
drop index PK_FIN_PRODUCT_VALUATION;
create unique index PK_FIN_PRODUCT_VALUATION on FIN_PRODUCT_VALUATION (FINPROD_ID, VAL_DATE, VALUE_SOURCE);


--估值来源新增枚举值
delete from sys_datadict_list where DICT_CODE ='VALUATION_SOURCE' and ITEM_CODE ='OTH';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('VALUATION_SOURCE', 'OTH', '第三方估值', 10, '0', 'system', '', sysdate, 'system', sysdate, '');

--数据来源新增枚举值
delete from sys_datadict_list where DICT_CODE ='INPUT_TYPE' and ITEM_CODE ='07';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INPUT_TYPE', '07', '模型', 7, '0', 'system', '', sysdate, 'system', sysdate, '');


--债权类资产新建页面新增估值来源
delete from sys_page_dictlist where PAGE_ID ='ComRateAssetQuotationInfo002' and SUB_PAGE_ID ='999999' and DICT_ID ='VALUATION_SOURCE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateAssetQuotationInfo002', '999999', 'VALUATION_SOURCE', 'CBC', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateAssetQuotationInfo002', '999999', 'VALUATION_SOURCE', 'OTH', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateAssetQuotationInfo002', '999999', 'VALUATION_SOURCE', 'MOD', '', '', 'system', '', sysdate, 'system', sysdate, '');
 
--债权类资产查询页面新增估值来源
delete from sys_page_dictlist where PAGE_ID ='ComRateAssetQuotationInfo001' and SUB_PAGE_ID ='999999' and DICT_ID ='VALUATION_SOURCE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateAssetQuotationInfo001', '999999', 'VALUATION_SOURCE', 'CBC', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateAssetQuotationInfo001', '999999', 'VALUATION_SOURCE', 'OTH', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateAssetQuotationInfo001', '999999', 'VALUATION_SOURCE', 'MOD', '', '', 'system', '', sysdate, 'system', sysdate, '');

--债权类资产查询页面新增数据来源
delete from sys_page_dictlist where PAGE_ID ='ComRateAssetQuotationInfo001' and SUB_PAGE_ID ='999999' and DICT_ID ='INPUT_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateAssetQuotationInfo001', '999999', 'INPUT_TYPE', '05', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateAssetQuotationInfo001', '999999', 'INPUT_TYPE', '01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateAssetQuotationInfo001', '999999', 'INPUT_TYPE', '07', '', '', 'system', '', sysdate, 'system', sysdate, '');