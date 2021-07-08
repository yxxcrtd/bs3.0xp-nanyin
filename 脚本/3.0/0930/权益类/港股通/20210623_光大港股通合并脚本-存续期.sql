delete from sys_menu where PAGE_ID = 'TrdStockConnectBonus001';
INSERT INTO sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('QS_MANAGEMENT_001_005_021', 'TRD', null, 'QS_MANAGEMENT_001_005', 'TrdStockConnectBonus001', 21, '02', '港股通', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID = 'TrdStockConnectBonus001';
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('TrdStockConnectBonus001', 'TRD', '/invest/period/trdstockconnectbonus/TrdStockConnectBonus001', 'invest/period/trdstockconnectbonus/TrdStockConnectBonus001', 'QS_MANAGEMENT_001_005_021', null, null, '02', '01', '港股通存续期', 'invest', null, sysdate, 'invest', sysdate);

delete from sys_page where PAGE_ID = 'TrdStockConnectBonus002';
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('TrdStockConnectBonus002', 'TRD', '/invest/period/trdstockconnectbonus/TrdStockConnectBonus002', 'invest/period/trdstockconnectbonus/TrdStockConnectBonus002', null, null, null, '02', '01', '港股通存续期', 'invest', null, sysdate, 'invest', sysdate);


INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('TrdStockConnectBonus001', '999999', 'PAGE_DATA_STATUS', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('TrdStockConnectBonus001', '999999', 'PAGE_DATA_STATUS', '03', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('TrdStockConnectBonus001', '999999', 'PAGE_DATA_STATUS', '05', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('TrdStockConnectBonus001', '999999', 'PAGE_DATA_STATUS', '06', null, null, 'system', null, sysdate, 'system', sysdate, null);

INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('TrdStockConnectBonus001', '999999', 'STOCK_DEVISION_TYPE', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('TrdStockConnectBonus001', '999999', 'STOCK_DEVISION_TYPE', '02', null, null, 'system', null, sysdate, 'system', sysdate, null);


delete from sys_wfl_business_module where PAGE_ID = 'TrdStockConnectBonus002';
INSERT INTO sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('TrdStockConnectBonus002', 'TrdStockConnectBonus001_btn_revoke', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('TrdStockConnectBonus002', 'TrdStockConnectBonus002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('TrdStockConnectBonus002', 'TrdStockConnectBonus002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

--
delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE ='TRD79';
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('COMMON_BUSINESS_TYPE', 'TRD79', '港股通股票分红交易', 1217, '0', 'system', null, sysdate, 'system', sysdate, null);

--
delete from sys_page_dictlist where PAGE_ID = 'WorkflowMonitor001' and DICT_ID = 'COMMON_BUSINESS_TYPE' and DICT_ITEM_VAL = 'ETR_HKSE';
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('WorkflowMonitor001', '999999', 'COMMON_BUSINESS_TYPE', 'ETR_HKSE', '港股通投资委托', null, 'system', null, sysdate, 'system', sysdate, null);

delete from sys_page_dictlist where PAGE_ID = 'MainPage' and DICT_ID = 'COMMON_BUSINESS_TYPE' and DICT_ITEM_VAL = 'ETR_HKSE';
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('MainPage', '999999', 'COMMON_BUSINESS_TYPE', 'ETR_HKSE', '港股通投资委托', null, 'system', null, sysdate, 'system', sysdate, null);

delete from sys_page_dictlist where PAGE_ID = 'WorkflowMonitor001' and DICT_ID = 'COMMON_BUSINESS_TYPE' and DICT_ITEM_VAL = 'TRD79';
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('WorkflowMonitor001', '999999', 'COMMON_BUSINESS_TYPE', 'TRD79', '港股通股票分红交易', null, 'system', null, sysdate, 'system', sysdate, null);

delete from sys_page_dictlist where PAGE_ID = 'MainPage' and DICT_ID = 'COMMON_BUSINESS_TYPE' and DICT_ITEM_VAL = 'TRD79';
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('MainPage', '999999', 'COMMON_BUSINESS_TYPE', 'TRD79', '港股通股票分红交易', null, 'system', null, sysdate, 'system', sysdate, null);

----新增字段
alter table TRD_FIN_PRODUCT_DEAL_TOTAL DROP COLUMN IF_DIVIDEND;
alter table TRD_FIN_PRODUCT_DEAL_TOTAL DROP COLUMN DIVID_CCY;
alter table TRD_FIN_PRODUCT_DEAL_TOTAL DROP COLUMN EXCHANGE_RATE;
alter table TRD_FIN_PRODUCT_DEAL_TOTAL DROP COLUMN REPLACE_PRICE;
alter table TRD_FIN_PRODUCT_DEAL_TOTAL DROP COLUMN REG_DATE_AMT;

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD IF_DIVIDEND VARCHAR(32);
comment on column TRD_FIN_PRODUCT_DEAL_TOTAL.IF_DIVIDEND is '是否含股利选择权';

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD DIVID_CCY VARCHAR(32);
comment on column TRD_FIN_PRODUCT_DEAL_TOTAL.DIVID_CCY is '派息币种';

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD EXCHANGE_RATE VARCHAR(32);
comment on column TRD_FIN_PRODUCT_DEAL_TOTAL.EXCHANGE_RATE is '汇率(派息等)';

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD REPLACE_PRICE number(30,14);
comment on column TRD_FIN_PRODUCT_DEAL_TOTAL.REPLACE_PRICE is '以股代息价格';

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD REG_DATE_AMT number(30,4);
comment on column TRD_FIN_PRODUCT_DEAL_TOTAL.REG_DATE_AMT is '权益登记日份额';

alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL DROP COLUMN IF_DIVIDEND;
alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL DROP COLUMN DIVID_CCY;
alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL DROP COLUMN EXCHANGE_RATE;
alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL DROP COLUMN REPLACE_PRICE;
alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL DROP COLUMN REG_DATE_AMT;

alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL ADD IF_DIVIDEND VARCHAR(32);
comment on column WFL_TRD_FIN_PRODUCT_DEAL_TOTAL.IF_DIVIDEND is '是否含股利选择权';

alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL ADD DIVID_CCY VARCHAR(32);
comment on column WFL_TRD_FIN_PRODUCT_DEAL_TOTAL.DIVID_CCY is '派息币种';

alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL ADD EXCHANGE_RATE number(30,14);
comment on column WFL_TRD_FIN_PRODUCT_DEAL_TOTAL.EXCHANGE_RATE is '汇率(派息等)';

alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL ADD REPLACE_PRICE number(30,14);
comment on column WFL_TRD_FIN_PRODUCT_DEAL_TOTAL.REPLACE_PRICE is '以股代息价格';

alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL ADD REG_DATE_AMT number(30,4);
comment on column WFL_TRD_FIN_PRODUCT_DEAL_TOTAL.REG_DATE_AMT is '权益登记日份额';

--
alter table TRD_PRODUCT_DEAL_ADD DROP COLUMN REPLACE_AMT;
alter table TRD_PRODUCT_DEAL_ADD ADD REPLACE_AMT number(30,14);
comment on column TRD_PRODUCT_DEAL_ADD.REPLACE_AMT is '以股代息股本';

alter table WFL_TRD_PRODUCT_DEAL_ADD DROP COLUMN REPLACE_AMT;
alter table WFL_TRD_PRODUCT_DEAL_ADD ADD REPLACE_AMT number(30,14);
comment on column WFL_TRD_PRODUCT_DEAL_ADD.REPLACE_AMT is '以股代息股本';


