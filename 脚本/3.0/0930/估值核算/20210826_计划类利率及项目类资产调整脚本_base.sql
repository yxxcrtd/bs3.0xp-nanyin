

delete from sys_page where page_id in ('ValProfitproject001','ValProfitproject002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValProfitproject001', 'VAL', '/valuation/basicdata/asset/plan/profitproject/ValProfitproject001', 'valuation/basicdata/asset/plan/profitproject/ValProfitproject001', 'ASSET_MANAGEMENT_001_002_007', null, null, '02', '01', '股票类资产', 'system', null, sysDate, 'system', sysDate);
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValProfitproject002', 'VAL', '/valuation/basicdata/asset/plan/profitproject/ValProfitproject002', 'valuation/basicdata/asset/plan/profitproject/ValProfitproject002', 'ASSET_MANAGEMENT_001_002_007', null, null, '02', '01', '股票类资产-新建', 'system', null, sysDate, 'system', sysDate);


delete from sys_wfl_business_module where page_id in ('ValProfitproject002');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ValProfitproject002', 'ValProfitproject001_btn_delete', 'oneApproval', 'system', null, sysDate, 'system', sysDate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ValProfitproject002', 'ValProfitproject002_btn_save', 'oneApproval', 'system', null, sysDate, 'system', sysDate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ValProfitproject002', 'ValProfitproject002_btn_submit', 'oneApproval', 'system', null, sysDate, 'system', sysDate, 'ALL', '02');

delete from sys_page_dictlist where PAGE_ID = 'ValProfitproject002' and DICT_ID = 'PROFIT_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', '999999', 'PROFIT_TYPE', '02', '利率型', null, 'system', null, sysDate, 'system', sysDate, null);

delete from sys_page_dictlist where PAGE_ID = 'ValProfitproject001' and DICT_ID = 'PROFIT_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject001', '999999', 'PROFIT_TYPE', '02', '利率型', null, 'system', null, sysDate, 'system', sysDate, null);


delete from sys_page_dictlist where PAGE_ID = 'ValProfitproject002' and DICT_ID = 'SEC_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', '999999', 'SEC_TYPE', '12', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', '999999', 'SEC_TYPE', '13', null, null, 'system', null, sysDate, 'system', sysDate, null);


delete from sys_page_dictlist where PAGE_ID = 'ValProfitproject002' and DICT_ID = 'BASIS';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', '999999', 'BASIS', 'A/360', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', '999999', 'BASIS', 'A/365', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', '999999', 'BASIS', 'A/A_D', null, null, 'system', null, sysDate, 'system', sysDate, null);

delete from sys_page_dictlist where PAGE_ID = 'ValProfitproject002' and DICT_ID = 'TDAY_RULE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', 'FinPayInterestStdInfo', 'TDAY_RULE', '02', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', 'FinPayInterestStdInfo', 'TDAY_RULE', '04', null, null, 'system', null, sysDate, 'system', sysDate, null);


delete from sys_page_dictlist where PAGE_ID = 'ValProfitproject002' and DICT_ID = 'FREQUENCY_UNIT';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', 'FinPayInterestStdInfo', 'FREQUENCY_UNIT', '03', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', 'FinPayInterestStdInfo', 'FREQUENCY_UNIT', '04', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', 'FinPayInterestStdInfo', 'FREQUENCY_UNIT', '05', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', 'FinPayInterestStdInfo', 'FREQUENCY_UNIT', '06', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', 'FinPayInterestStdInfo', 'FREQUENCY_UNIT', '07', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ValProfitproject002', 'FinPayInterestStdInfo', 'FREQUENCY_UNIT', '08', null, null, 'system', null, sysDate, 'system', sysDate, null);

