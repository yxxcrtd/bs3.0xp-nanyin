delete from sys_page_dictlist where PAGE_ID ='DvpOrdersManagement001' and DICT_ID='PINAN_BUSINESS_TYPE' and DICT_ITEM_VAL ='81';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DvpOrdersManagement001', '999999', 'PINAN_BUSINESS_TYPE', '81', '', '', 'system', '', sysdate, 'system', sysdate, '');
