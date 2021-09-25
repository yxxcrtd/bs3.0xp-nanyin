

-- delete from sys_menu where MENU_ID = 'INFOSEARCH_001_004_019';
-- insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
-- values ('INFOSEARCH_001_004_019', 'VAL', null, 'INFOSEARCH_001_004', 'FinStockInfo001', 19, '2', '股票类资产', 'system', null, sysDate, 'system', sysDate);


delete from sys_page where page_id in ('FinStockInfo001','FinStockInfo002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinStockInfo001', 'VAL', '/valuation/basicdata/asset/stock/FinStockInfo001', 'valuation/basicdata/asset/stock/FinStockInfo001', 'ASSET_MANAGEMENT_001_002_009', null, null, '02', '01', '股票类资产', 'system', null, sysDate, 'system', sysDate);
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinStockInfo002', 'VAL', '/valuation/basicdata/asset/stock/FinStockInfo002', 'valuation/basicdata/asset/stock/FinStockInfo002', '', null, null, '02', '01', '股票类资产-新建', 'system', null, sysDate, 'system', sysDate);


delete from sys_wfl_business_module where page_id in ('FinStockInfo001','FinStockInfo002');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('FinStockInfo002', 'FinStockInfo001_btn_delete', 'oneApproval', 'system', null, sysDate, 'system', sysDate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('FinStockInfo002', 'FinStockInfo002_btn_save', 'oneApproval', 'system', null, sysDate, 'system', sysDate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('FinStockInfo002', 'FinStockInfo002_btn_submit', 'oneApproval', 'system', null, sysDate, 'system', sysDate, 'ALL', '02');



delete from sys_page_dictlist where PAGE_ID = 'FinStockInfo001' and DICT_ID = 'TRADE_PLACE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FinStockInfo001', '999999', 'TRADE_PLACE', '03', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FinStockInfo001', '999999', 'TRADE_PLACE', '04', null, null, 'system', null, sysDate, 'system', sysDate, null);

delete from sys_page_dictlist where PAGE_ID = 'FinStockInfo002' and DICT_ID = 'TRADE_PLACE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FinStockInfo002', '999999', 'TRADE_PLACE', '03', null, null, 'system', null, sysDate, 'system', sysDate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FinStockInfo002', '999999', 'TRADE_PLACE', '04', null, null, 'system', null, sysDate, 'system', sysDate, null);

delete from sys_datadict_list_link where DICT_CODE = 'BOARD' and LINKED_DICT_CODE = 'TRADE_PLACE';

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BOARD', 'BD03', 'TRADE_PLACE', '03                                                ', 'system', null, sysDate, 'system', sysDate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BOARD', 'BD01', 'TRADE_PLACE', '03                                                ', 'system', null, sysDate, 'system', sysDate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BOARD', 'BD04', 'TRADE_PLACE', '04                                                ', 'system', null, sysDate, 'system', sysDate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BOARD', 'BD01', 'TRADE_PLACE', '04                                                ', 'system', null, sysDate, 'system', sysDate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BOARD', 'BD02', 'TRADE_PLACE', '04                                                ', 'system', null, sysDate, 'system', sysDate);

