delete from sys_page_dictlist where PAGE_ID ='ComBondQuotationInfo002' and SUB_PAGE_ID ='999999' and DICT_ID = 'VALUATION_SOURCE' and DICT_ITEM_VAL='MOD';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComBondQuotationInfo002', '999999', 'VALUATION_SOURCE', 'MOD', '', '', 'system', '', sysdate, 'system', sysdate, '');
