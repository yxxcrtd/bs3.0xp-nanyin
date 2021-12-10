--手工交收新增共通业务类型
delete from sys_page_dictlist where PAGE_ID='MainPage' and DICT_ID = 'COMMON_BUSINESS_TYPE' and DICT_ITEM_VAL='STL06';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MainPage', '999999', 'COMMON_BUSINESS_TYPE', 'STL06', '', '', 'system', '', sysdate, 'system', sysdate, '');

--手工交收新增交收失败原因
-- Add/modify columns
alter table TRD_BANK_MANUAL_SETTLE_INFO add REMARK VARCHAR2(200);
-- Add comments to the columns
comment on column TRD_BANK_MANUAL_SETTLE_INFO.REMARK
  is '交收失败原因';