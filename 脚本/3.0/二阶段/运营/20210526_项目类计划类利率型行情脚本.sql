delete from sys_page where PAGE_ID = 'ComRateAssetQuotationInfo001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComRateAssetQuotationInfo001', 'MST', '/common/basicdata/marketdata/mstrateassetquotationinfo/ComRateAssetQuotationInfo001', 'common/basicdata/marketdata/mstrateassetquotationinfo/ComRateAssetQuotationInfo001', 'ASSET_MANAGEMENT_001_003_017', null, null, '02', '01', '项目类/计划类利率型估值行情', 'system', null, sysdate, 'system', sysdate);
delete from sys_page where PAGE_ID = 'ComRateAssetQuotationInfo002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComRateAssetQuotationInfo002', 'MST', '/common/basicdata/marketdata/mstrateassetquotationinfo/ComRateAssetQuotationInfo002', 'common/basicdata/marketdata/mstrateassetquotationinfo/ComRateAssetQuotationInfo002', '', null, null, '02', '01', '项目类/计划类利率型估值行情', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID = 'ComRateAssetQuotationInfo002';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComRateAssetQuotationInfo002', 'ComRateAssetQuotationInfo002_btn_delete', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'All', '02');

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComRateAssetQuotationInfo002', 'ComRateAssetQuotationInfo002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComRateAssetQuotationInfo002', 'ComRateAssetQuotationInfo002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'All', '02');