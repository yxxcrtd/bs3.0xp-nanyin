delete from sys_menu where MENU_ID = 'DEAL_MANAGEMENT_001_007_002';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DEAL_MANAGEMENT_001_007_002', 'TRD', null, 'DEAL_MANAGEMENT_001_007', 'ExchangeTransMatch001', 1, '01', '场内成交管理', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID = 'ExchangeTransMatch001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ExchangeTransMatch001', 'TRD', '/invest/trade/exchangetransmatch/ExchangeTransMatch001', 'invest/trade/exchangetransmatch/ExchangeTransMatch001', 'DEAL_MANAGEMENT_001_007_002', null, null, '02', '01', '场内成交管理', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID = 'ExchangeTransMatch002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ExchangeTransMatch002', 'TRD', '/invest/trade/exchangetransmatch/ExchangeTransMatch002', 'invest/trade/exchangetransmatch/ExchangeTransMatch002', '', null, null, '02', '01', '匹配明细查看', 'system', null, sysdate, 'system', sysdate);

delete from sys_page_element where PAGE_ID = 'ExchangeTransMatch001' and ELEMENT_ID in ('ExchangeTransMatch001_btn_search','ExchangeTransMatch001_btn_export');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ExchangeTransMatch001', 'ExchangeTransMatch001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM');

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ExchangeTransMatch001', 'ExchangeTransMatch001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM');


----下拉框选项设置
delete from sys_page_dictlist where PAGE_ID = 'ExchangeTransMatch001' and DICT_ID = 'TRADE_PLACE' and DICT_ITEM_VAL in ('03','04');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'TRADE_PLACE', '03', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'TRADE_PLACE', '04', null, null, 'system', null, sysdate, 'system', sysdate, null);


delete from sys_page_dictlist where PAGE_ID = 'ExchangeTransMatch001' and DICT_ID = 'SHARES_TRADE_PLATFORM' and DICT_ITEM_VAL in ('02','03','04','05');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'SHARES_TRADE_PLATFORM', '02', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'SHARES_TRADE_PLATFORM', '03', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'SHARES_TRADE_PLATFORM', '04', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'SHARES_TRADE_PLATFORM', '05', null, null, 'system', null, sysdate, 'system', sysdate, null);


delete from sys_page_dictlist where PAGE_ID = 'ExchangeTransMatch001' and DICT_ID = 'PS' and DICT_ITEM_VAL in ('T01','T02','03','04','T68','T69','T49','T03','T46','T97','T04');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'PS', 'T01', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'PS', 'T02', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'PS', '03', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'PS', '04', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'PS', 'T68', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'PS', 'T69', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'PS', 'T49', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'PS', 'T03', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'PS', 'T46', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'PS', 'T97', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'PS', 'T04', null, null, 'system', null, sysdate, 'system', sysdate, null);


delete from sys_page_dictlist where PAGE_ID = 'ExchangeTransMatch001' and DICT_ID = 'MATCH_STATUS' and DICT_ITEM_VAL in ('02','03');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'MATCH_STATUS', '02', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ExchangeTransMatch001', '999999', 'MATCH_STATUS', '03', null, null, 'system', null, sysdate, 'system', sysdate, null);



delete from sys_para where SYSTEM_CODE = 'SYS' and PARA_CODE = 'MATCH_RULE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('SYS', 'MATCH_RULE', '01', null, null, '匹配规则 01:按照指令下达时间顺序匹配 02:按照指令金额从高到低匹配 03:按照指令金额总体占比分配', 'system', null, sysdate,  'system', sysdate, null, '01');



drop table SETTLE_TRANS_DATA_TOTAL_MATCH cascade constraints;

/*==============================================================*/
/* Table: SETTLE_TRANS_DATA_TOTAL_MATCH                         */
/*==============================================================*/
create table SETTLE_TRANS_DATA_TOTAL_MATCH 
(
   SEQ_NO               VARCHAR(32)          not null,
   ENTRUNST_TRADE_ID    VARCHAR(32)          not null,
   MATCH_CLASS          VARCHAR(50)          not null,
   MATCH_TYPE           VARCHAR(50),
   MARCH_QTY            NUMERIC(30,4),
   MARCH_AMT            NUMERIC(30, 2),
   MARCH_QTY_EN         NUMERIC(30,4),
   MARCH_AMT_EN         NUMERIC(30, 2),
   CCY                  VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SETTLE_TRANS_DATA_TOTAL_MAT primary key (SEQ_NO, ENTRUNST_TRADE_ID, MATCH_CLASS)
);

comment on table SETTLE_TRANS_DATA_TOTAL_MATCH is
'汇总成交数据匹配结果表';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.SEQ_NO is
'成交数据明细的流水号';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.ENTRUNST_TRADE_ID is
'指令编号';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.MATCH_CLASS is
'匹配方式，手工匹配，自动匹配';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.MATCH_TYPE is
'目标类型';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.MARCH_QTY is
'已匹配份额';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.MARCH_AMT is
'已匹配金额';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.MARCH_QTY_EN is
'指令已匹配份额';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.MARCH_AMT_EN is
'指令已匹配金额';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.CCY is
'币种';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.CREATE_USER is
'创建人';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.CREATE_DEPT is
'创建部门';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.CREATE_TIME is
'创建时间';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.UPDATE_USER is
'更新人';

comment on column SETTLE_TRANS_DATA_TOTAL_MATCH.UPDATE_TIME is
'更新时间';


drop table SETTLE_TRANS_TG_MAPPING cascade constraints;

/*==============================================================*/
/* Table: SETTLE_TRANS_TG_MAPPING                               */
/*==============================================================*/
create table SETTLE_TRANS_TG_MAPPING 
(
   TRADE_ID             VARCHAR(32)          not null,
   TRADE_ID_TG          VARCHAR(32)          not null,
   TG_DATA_STATUS       VARCHAR(50),
   DEAL_DATE            DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SETTLE_TRANS_TG_MAPPING primary key (TRADE_ID, TRADE_ID_TG)
);

comment on table SETTLE_TRANS_TG_MAPPING is
'成交数据与投管交易关系';

comment on column SETTLE_TRANS_TG_MAPPING.TRADE_ID is
'成交编号';

comment on column SETTLE_TRANS_TG_MAPPING.TRADE_ID_TG is
'投管交易编号';

comment on column SETTLE_TRANS_TG_MAPPING.TG_DATA_STATUS is
'投管交易数据状态（01：待处理、02：处理结束）';

comment on column SETTLE_TRANS_TG_MAPPING.DEAL_DATE is
'执行日期';

comment on column SETTLE_TRANS_TG_MAPPING.CREATE_USER is
'创建人';

comment on column SETTLE_TRANS_TG_MAPPING.CREATE_DEPT is
'创建部门';

comment on column SETTLE_TRANS_TG_MAPPING.CREATE_TIME is
'创建时间';

comment on column SETTLE_TRANS_TG_MAPPING.UPDATE_USER is
'更新人';

comment on column SETTLE_TRANS_TG_MAPPING.UPDATE_TIME is
'更新时间';



