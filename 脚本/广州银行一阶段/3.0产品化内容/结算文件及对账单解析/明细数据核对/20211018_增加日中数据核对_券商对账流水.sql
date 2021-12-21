drop table STL_SEC_RECON;
create table STL_SEC_RECON
(
    SEQ_NO       varchar(50)   not null
        primary key,
    CUSTOMER_ID  varchar(50)   null,
    PORTFOLIO_ID varchar(50)   null,
    FINPROD_ID   varchar(50)   null,
    MARKET_ID    varchar(50)   null,
    SETTLE_DATE  DATE          null,
    TRADE_MARKET varchar(50)   null,
    CHECK_RESULT varchar(50)   null,
    STL_SEC_DEME varchar(50)   null,
    RECON_INFO   VARCHAR2(300) null,
    create_user  varchar(20)   not null,
    create_dept  varchar(32)   null,
    create_time  TIMESTAMP(6)  not null,
    update_user  varchar(20)   not null,
    update_time  TIMESTAMP(6)  not null
);

comment on table STL_SEC_RECON is '券商对账结果';
comment on column STL_SEC_RECON.SEQ_NO is '券商对账单流水号';
comment on column STL_SEC_RECON.CUSTOMER_ID is '券商代码';
comment on column STL_SEC_RECON.PORTFOLIO_ID is '组合代码';
comment on column STL_SEC_RECON.FINPROD_ID is '金融产品代码';
comment on column STL_SEC_RECON.MARKET_ID is '市场代码';
comment on column STL_SEC_RECON.SETTLE_DATE is '清算日期';
comment on column STL_SEC_RECON.TRADE_MARKET is '交易场所';
comment on column STL_SEC_RECON.CHECK_RESULT is '对账结果';
comment on column STL_SEC_RECON.STL_SEC_DEME is '对账维度';
comment on column STL_SEC_RECON.RECON_INFO is '对账信息';
comment on column STL_SEC_RECON.create_user is '创建人';
comment on column STL_SEC_RECON.create_dept is '创建部门';
comment on column STL_SEC_RECON.create_time is '创建时间';
comment on column STL_SEC_RECON.update_user is '更新人';
comment on column STL_SEC_RECON.update_time is '更新时间';


drop table STL_SEC_RECON_DETAIL;
create table STL_SEC_RECON_DETAIL
(
    SEQ_NO      varchar(50)  not null,
    TRADE_ID    varchar(50)  not null,
    create_user varchar(20)  not null,
    create_dept varchar(32)  null,
    create_time timestamp(6) not null,
    update_user varchar(20)  not null,
    update_time timestamp(6) not null,
    primary key (SEQ_NO, TRADE_ID)
);
comment on table STL_SEC_RECON_DETAIL is '券商对账结果明细（结算）';
comment on column STL_SEC_RECON_DETAIL.SEQ_NO is '券商对账单流水号';
comment on column STL_SEC_RECON_DETAIL.TRADE_ID is '成交编号';
comment on column STL_SEC_RECON_DETAIL.create_user is '创建人';
comment on column STL_SEC_RECON_DETAIL.create_dept is '创建部门';
comment on column STL_SEC_RECON_DETAIL.create_time is '创建时间';
comment on column STL_SEC_RECON_DETAIL.update_user is '更新人';
comment on column STL_SEC_RECON_DETAIL.update_time is '更新时间';

delete from SYS_DATADICT where DICT_CODE = 'ACCOUNT_STATUS';
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT,
                                CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('ACCOUNT_STATUS', 'TG', '对账状态结果', null, '02', 'system', null, systimestamp, 'system',
        systimestamp);
delete from SYS_DATADICT_LIST where DICT_CODE = 'ACCOUNT_STATUS';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER,
                                     CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('ACCOUNT_STATUS', '0', '不一致', 1, '0', 'system', null, systimestamp, 'system', systimestamp,
        null);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER,
                                     CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('ACCOUNT_STATUS', '1', '一致', 2, '0', 'system', null, systimestamp, 'system', systimestamp,
        null);

delete from SYS_DATADICT_LIST where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'STL07';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER,
                                     CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'STL07', '券商流水核对', 1217, '0', 'system', null, systimestamp, 'system',
        systimestamp, null);

delete from SYS_MENU where MENU_ID = 'QS_MANAGEMENT_001_008_004';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK,
                      CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('QS_MANAGEMENT_001_008_004', 'STL', null, 'QS_MANAGEMENT_001_008', 'DayEndAccount001', 4, '02', '券商流水核对',
        'system', null, systimestamp, 'system',
        systimestamp);

delete from SYS_PAGE where PAGE_ID = 'DayEndAccount001';
delete from SYS_PAGE where PAGE_ID = 'DayEndAccount002';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT,
                            IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DayEndAccount001', 'STL', '/invest/basicdata/dayendaccount/DayEndAccount001',
        'invest/basicdata/dayendaccount/DayEndAccount001', 'QS_MANAGEMENT_001_008_004', null, null, '02', '02',
        '券商流水核对', 'system', null, systimestamp, 'system', systimestamp);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT,
                            IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DayEndAccount002', 'STL', '/invest/basicdata/dayendaccount/DayEndAccount002',
        'invest/basicdata/dayendaccount/DayEndAccount002', null, null, null, '02', '01', '券商流水核对修改', 'system', null,
        systimestamp, 'system', systimestamp);

delete from sys_wfl_business_module where PAGE_ID = 'DayEndAccount002';
INSERT INTO sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME,
                                           UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('DayEndAccount002', 'DayEndAccount002_btn_save', 'oneApproval', 'system', null, systimestamp, 'system',
        systimestamp, 'ALL', '02');
INSERT INTO sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME,
                                           UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('DayEndAccount002', 'DayEndAccount002_btn_submit', 'oneApproval', 'system', null, systimestamp,
        'system', systimestamp, 'ALL', '02');

delete from sys_page_dictlist where PAGE_ID = 'DayEndAccount001';
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME,
                                     DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                                     UPDATE_TIME, INSTITUTION_CODE)
VALUES ('DayEndAccount001', '999999', 'TRADE_PLACE', '03', null, null, 'system', null, systimestamp, 'system',
        systimestamp, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME,
                                     DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                                     UPDATE_TIME, INSTITUTION_CODE)
VALUES ('DayEndAccount001', '999999', 'TRADE_PLACE', '04', null, null, 'system', null, systimestamp, 'system',
        systimestamp, null);

delete
from sys_page_dictlist
where DICT_ID = 'COMMON_BUSINESS_TYPE'
  and DICT_ITEM_VAL = 'STL07';
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME,
                               DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                               UPDATE_TIME, INSTITUTION_CODE)
VALUES ('WorkflowMonitor001', '999999', 'COMMON_BUSINESS_TYPE', 'STL07', '券商流水核对', null, 'system', null,
        systimestamp, 'system', systimestamp, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME,
                               DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                               UPDATE_TIME, INSTITUTION_CODE)
VALUES ('MainPage', '999999', 'COMMON_BUSINESS_TYPE', 'STL07', '券商流水核对', null, 'system', null,
        systimestamp, 'system', systimestamp, null);