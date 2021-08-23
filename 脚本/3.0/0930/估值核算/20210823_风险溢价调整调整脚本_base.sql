
delete from sys_page_dictlist where PAGE_ID = 'RiskPremiumAdjConf001' and DICT_ID = 'SEC_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RiskPremiumAdjConf001', '999999', 'SEC_TYPE', '01', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RiskPremiumAdjConf001', '999999', 'SEC_TYPE', '13', null, null, 'system', null, sysDate, 'system', sysDate, null);

delete from sys_page_dictlist where PAGE_ID = 'RiskPremiumAdjConf002' and DICT_ID = 'SEC_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RiskPremiumAdjConf002', '999999', 'SEC_TYPE', '01', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RiskPremiumAdjConf002', '999999', 'SEC_TYPE', '13', null, null, 'system', null, sysDate, 'system', sysDate, null);