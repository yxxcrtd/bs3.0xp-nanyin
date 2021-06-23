----港股通交易指令
alter table ETR_ENTRUST_ADD drop column  HK_MARKET;
alter table ETR_ENTRUST_ADD add  HK_MARKET varchar(32);
comment on column ETR_ENTRUST_ADD.HK_MARKET is '港股通市场';

alter table ETR_ENTRUST_ADD drop column  ENTRUST_AMT_TARGET;
alter table ETR_ENTRUST_ADD add  ENTRUST_AMT_TARGET number(30,2);
comment on column ETR_ENTRUST_ADD.ENTRUST_AMT_TARGET is '目标币种成交金额';


alter table WFL_ETR_ENTRUST_ADD drop column  HK_MARKET;
alter table WFL_ETR_ENTRUST_ADD add  HK_MARKET varchar(32);
comment on column WFL_ETR_ENTRUST_ADD.HK_MARKET is '港股通市场';

alter table WFL_ETR_ENTRUST_ADD drop column  ENTRUST_AMT_TARGET;
alter table WFL_ETR_ENTRUST_ADD add  ENTRUST_AMT_TARGET number(30,2);
comment on column WFL_ETR_ENTRUST_ADD.ENTRUST_AMT_TARGET is '目标币种成交金额';


delete from sys_menu where PAGE_ID = 'EtrStockConnectMain';
INSERT INTO sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SH_DEAL_MANAGEMENT_001_001_003', 'ETR', null, 'SH_DEAL_MANAGEMENT_001_001', 'EtrStockConnectMain', 3, '02', '港股通指令', 'system', 'system', sysdate, 'system', sysdate);
delete from sys_page where PAGE_ID = 'EtrStockConnectMain';
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EtrStockConnectMain', 'ETR', '/invest/entrust/etrstockconnect/EtrStockConnectMain', 'invest/entrust/etrstockconnect/EtrStockConnectMain', 'SH_DEAL_MANAGEMENT_001_001_003', null, null, '02', '01', '港股通指令主页面', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID = 'EtrStockConnect001';
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EtrStockConnect001', 'ETR', '/invest/entrust/etrstockconnect/EtrStockConnect001', 'invest/entrust/etrstockconnect/EtrStockConnect001', null, null, null, '02', '01', '港股通指令页面', 'system', null, sysdate, 'system', sysdate);
delete from sys_page where PAGE_ID = 'EtrStockConnect002';
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EtrStockConnect002', 'ETR', '/invest/entrust/etrstockconnect/EtrStockConnect002', 'invest/entrust/etrstockconnect/EtrStockConnect002', null, null, null, '02', '01', '港股通指令页面', 'system', null, sysdate, 'system', sysdate);
delete from sys_page where PAGE_ID = 'EtrStockConnect003';
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EtrStockConnect003', 'ETR', '/invest/entrust/etrstockconnect/EtrStockConnect003', 'invest/entrust/etrstockconnect/EtrStockConnect003', null, null, null, '02', '01', '港股通指令页面多对多', 'system', null, sysdate, 'system', sysdate);
delete from sys_page where PAGE_ID = 'EtrStockConnectHq';
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EtrStockConnectHq', 'ETR', '/invest/entrust/etrstockconnect/EtrStockConnectHq', 'invest/entrust/etrstockconnect/EtrStockConnectHq', null, null, null, '02', '01', '港股通指令页面', 'system', null, sysdate, 'system', sysdate);
delete from sys_page where PAGE_ID = 'EtrStockConnectRiskcal';
INSERT INTO sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EtrStockConnectRiskcal', 'ETR', '/invest/entrust/etrstockconnect/EtrStockConnectRiskcal', 'invest/entrust/etrstockconnect/EtrStockConnectRiskcal', null, null, null, '02', '01', '港股通指令页面', 'system', null, sysdate, 'system', sysdate);

--
delete from sys_wfl_business_module where PAGE_ID = 'EtrStockConnect002';
INSERT INTO sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('EtrStockConnect002', 'EtrStockConnect001_btn_delete', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('EtrStockConnect002', 'EtrStockConnect002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('EtrStockConnect002', 'EtrStockConnect002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

--
delete from sys_page_dictlist where PAGE_ID = 'EtrStockConnect001' and DICT_ID = 'SH_OR_ZH';
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrStockConnect001', '999999', 'SH_OR_ZH', '01', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrStockConnect001', '999999', 'SH_OR_ZH', '02', null, null, 'admin', null, sysdate, 'admin', sysdate, null);

delete from sys_page_dictlist where PAGE_ID = 'EtrStockConnect002' and DICT_ID = 'SH_OR_ZH';
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrStockConnect002', '999999', 'SH_OR_ZH', '01', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrStockConnect002', '999999', 'SH_OR_ZH', '02', null, null, 'admin', null, sysdate, 'admin', sysdate, null);

delete from sys_page_dictlist where PAGE_ID = 'EtrStockConnect002' and DICT_ID = 'PRICE_MODEL';
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrStockConnect002', '999999', 'PRICE_MODEL', 'PRM01', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrStockConnect002', '999999', 'PRICE_MODEL', 'PRM02', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrStockConnect002', '999999', 'PRICE_MODEL', 'PRM03', null, null, 'admin', null, sysdate, 'admin', sysdate, null);

delete from sys_page_dictlist where PAGE_ID = 'EtrStockConnect002' and DICT_ID = 'PS';
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrStockConnect002', '999999', 'PS', 'T01', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrStockConnect002', '999999', 'PS', 'T02', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrStockConnect002', '999999', 'PS', 'T46', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrStockConnect002', '999999', 'PS', 'T47', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrStockConnect002', '999999', 'PS', 'TBS001', null, null, 'admin', null, sysdate, 'admin', sysdate, null);

--
delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'ETR_HKSE';
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('COMMON_BUSINESS_TYPE', 'ETR_HKSE', '港股通投资委托', 1214, '0', 'system', null, sysdate, 'system', sysdate, null);

delete from sys_datadict_list where DICT_CODE = 'PS' and ITEM_CODE = 'TBS001';
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('PS', 'TBS001', '卖碎股', 125, '0', 'system', null, sysdate, 'system', sysdate, null);


----去除草稿箱
delete from SYS_TRASH_NOT_TARGET_PAGE where PAGE_ID in ('EtrStockConnectMain','EtrStockConnect001','EtrStockConnect002','EtrStockConnect003','EtrStockConnectRiskcal');
INSERT INTO SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO,PAGE_ID) VALUES ('A96EA0A538G1043FE0538C5AA8C0280A','EtrAgreRepTradeMain');
INSERT INTO SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO,PAGE_ID) VALUES ('A96EA0A538G2043FE0538C5AA8C0280A','EtrStockConnect001');
INSERT INTO SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO,PAGE_ID) VALUES ('A96EA0A538G3043FE0538C5AA8C0280A','EtrStockConnect002');
INSERT INTO SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO,PAGE_ID) VALUES ('A96EA0A538G4043FE0538C5AA8C0280A','EtrStockConnect003');
INSERT INTO SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO,PAGE_ID) VALUES ('A96EA0A538G5043FE0538C5AA8C0280A','EtrStockConnectRiskcal');


