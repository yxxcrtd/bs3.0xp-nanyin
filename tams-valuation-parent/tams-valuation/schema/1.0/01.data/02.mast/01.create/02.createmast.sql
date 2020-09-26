/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2018/9/18 16:47:16                           */
/*==============================================================*/
drop view VAL_VW_MST_HOLIDAY;
drop table BOK_ACCT_STANDARD cascade constraints;
drop table BOK_ACCT_STANDARD_NATURE cascade constraints;
drop table BOK_BALANCE cascade constraints;
drop table BOK_BAL_TABLE_DATA cascade constraints;
drop table BOK_BATCH_TASK_GRP cascade constraints;
drop table BOK_BATCH_TASK_LINK cascade constraints;
drop index "Index_10";
drop table BOK_BATCH_TASK_TODO cascade constraints;
drop index "Index_9";
drop index "Index_8";
drop index "Index_7";
drop index "Index_6";
drop index "Index_5";
drop index "Index_4";
drop index "Index_3";
drop index "Index_2";
drop index "Index_1";
drop table BOK_BOOKDETAIL_ALL cascade constraints;
drop index "Index_19";
drop index "Index_18";
drop index "Index_17";
drop index "Index_16";
drop index "Index_15";
drop index "Index_14";
drop index "Index_13";
drop index "Index_12";
drop index "Index_11";
drop table BOK_BOOKDETAIL_CURRENT cascade constraints;
drop table BOK_BOOKSET cascade constraints;
drop table BOK_BOOKSET_CAPACCT_INFO cascade constraints;
drop table BOK_BOOKSET_DATE cascade constraints;
drop table BOK_BOOKSET_EXT cascade constraints;
drop table BOK_BOOKSET_OPTION cascade constraints;
drop table BOK_BOOKSET_PLAN cascade constraints;
drop table BOK_BUSS_DETSUB cascade constraints;
drop table BOK_DEAL_ACCOUNTING cascade constraints;
drop table BOK_DEAL_ACCOUNTING_DETAIL cascade constraints;
drop table BOK_DETAIL_ID cascade constraints;
drop table BOK_FILE_GEN cascade constraints;
drop table BOK_MANUAL_BOOKDETAIL cascade constraints;
drop table BOK_MATCH_RLAT cascade constraints;
drop table BOK_SCENECONFIG cascade constraints;
drop table BOK_SCENESUBJECT cascade constraints;
drop table BOK_SCHEME_CONFIG cascade constraints;
drop table BOK_SCHEME_CONFIG_VAL cascade constraints;
drop table BOK_SCHEME_INFO cascade constraints;
drop table BOK_STAT_BOK_POSITION cascade constraints;
drop table BOK_SUBJECT cascade constraints;
drop table BOK_SUBJECT_DETAIL cascade constraints;
drop table BOK_SUBJECT_NATURE_GROUP cascade constraints;
drop table BOK_TABLEDICT cascade constraints;
drop table BOK_TABLELIST cascade constraints;
drop table BOK_TRADE_ORDER cascade constraints;
drop table BOK_VALBAL_TABLE_LOG cascade constraints;
drop table BOK_VALBAL_TABLE_STATUS cascade constraints;
drop table BOK_VAL_RULE cascade constraints;
drop table BOK_VAL_RULE_ASSETDETAIL cascade constraints;
drop table BOK_VAL_RULE_TYPEDETAIL cascade constraints;
drop table BOK_VAL_SCHEME cascade constraints;
drop table BOK_VAL_SCHEME_DETAIL cascade constraints;
drop table BOK_VAL_SCHEME_DETAIL_SPECIAL cascade constraints;
drop table BOK_VAL_TABLE_DATA cascade constraints;
drop table BOK_VOUCH_DETAIL cascade constraints;
drop table BOK_VOUCH_MAIN cascade constraints;
drop table BOK_VOUCH_MAIN_ERROR cascade constraints;
drop table BOK_VOUCH_REL cascade constraints;
drop table FIN_CASH_CALC_RULE cascade constraints;
drop table FIN_CASH_INFO cascade constraints;
drop table FIN_CASH_INT_INFO cascade constraints;
drop table FIN_CASH_PAY_RULE cascade constraints;
drop table FIN_CASH_PLAN cascade constraints;
drop table FIN_DEAL_PLEDGE cascade constraints;
drop table FIN_EXERCISE_PLAN cascade constraints;
drop table FIN_FLOAT_ACCRUAL_SCHE cascade constraints;
drop table FIN_FLOAT_CALC_RULE cascade constraints;
drop table FIN_FLOAT_CALC_RULE_DETAIL cascade constraints;
drop table FIN_FLOAT_COUNTING_RULE cascade constraints;
drop table FIN_PRODUCT cascade constraints;
drop table FIN_PRODUCT_CLASS cascade constraints;
drop table FIN_PRODUCT_EXT cascade constraints;
drop table FIN_PRODUCT_TYPE cascade constraints;
drop table FIN_PRODUCT_VALUATION cascade constraints;
drop table FIN_TRADE_PRODUCT cascade constraints;
drop table FIN_VAL_OPEN_PLAN cascade constraints;
drop table MST_BOND_MARKET_INFO cascade constraints;
drop table MST_BOND_VALUATION_INFO cascade constraints;
drop table MST_CALENDAR_INFO cascade constraints;
drop table MST_COMP_INFO cascade constraints;
drop table MST_CUSTOMER_INFO cascade constraints;
drop table MST_DATES cascade constraints;
drop table MST_EXCHARGE_QUOTE cascade constraints;
drop table MST_EXPONENT_QUOTATION cascade constraints;
drop table MST_FUND_MARKET_INFO cascade constraints;
drop table MST_HOLIDAY cascade constraints;
drop table MST_INDEX_INFO cascade constraints;
drop table MST_INT_RATE_INFO cascade constraints;
drop table MST_RATE_INFO cascade constraints;
drop table MST_SHARE_PRICE cascade constraints;
drop table MST_TRADE_CALENDAR cascade constraints;
drop table MST_YIELD_CURVE_INFO cascade constraints;
drop table STL_CAPACCT_INFO cascade constraints;
drop table STL_FIN_PRODUCT_ACCT cascade constraints;
drop table STL_ORDERS cascade constraints;
drop table STL_ORDERS_EXT cascade constraints;
drop table STL_SEC_MANAGE_ACCT cascade constraints;
drop table TAX_ADT_SCHEDULE cascade constraints;
drop table TAX_BOK_BALANCE cascade constraints;
drop table TAX_BOK_BALANCE_SUM cascade constraints;
drop table TAX_BOK_CONFIG cascade constraints;
drop table TAX_BOK_DETAIL cascade constraints;
drop table TAX_CF_BOK_CONFIG cascade constraints;
drop table TAX_CONFIG_VALUE cascade constraints;
drop table TAX_DECLARE_SUM_INFO cascade constraints;
drop table TAX_TAXABLE_INFO cascade constraints;
drop table TAX_VAT_SCHEDULE cascade constraints;
drop table TG_TRADE_FEE_DETAIL cascade constraints;
drop table TRD_FIN_PRODUCT_BARTER cascade constraints;
drop table TRD_PRODUCT_DEAL cascade constraints;
drop table TRD_PRODUCT_DEAL_EXT cascade constraints;
drop table TRD_VOUCH_DEAL cascade constraints;
drop table TRD_VOUCH_DEAL_DETAIL cascade constraints;
drop table VAL_INTERFLOG cascade constraints;
drop table VAL_INTERF_ACTIONLOG cascade constraints;
drop table VAL_INTERF_DEFINE cascade constraints;
drop table VAL_INTERF_DTO_MATCH cascade constraints;
drop table VAL_INTERF_SERVER_INFO cascade constraints;
drop table VAL_INTERF_TASK cascade constraints;
drop table VAL_INTERF_TEMPLATE cascade constraints;
drop table VAL_TEMP_COL_CHECK_INFO cascade constraints;
drop sequence BOOKORDER_SEQUENCE;
drop sequence BOOKSET_SCHEME_SEQ;
drop sequence BOOK_SEQUENCE;
drop sequence ORDER_SEQUENCE;
drop sequence PD_SEQUENCE;
create sequence BOOKORDER_SEQUENCE
increment by 1
start with 1
 nomaxvalue
 minvalue 1
nocycle
 cache 20;
create sequence BOOKSET_SCHEME_SEQ
increment by 1
start with 1
 nomaxvalue
 minvalue 1
nocycle
 cache 20;
create sequence BOOK_SEQUENCE
increment by 1
start with 1
 maxvalue 99999999
 minvalue 1
cycle
 cache 20;
create sequence ORDER_SEQUENCE
increment by 1
start with 1
 maxvalue 99999999
 minvalue 1
cycle
 cache 20;
create sequence PD_SEQUENCE
increment by 1
start with 1
 maxvalue 99999999
 minvalue 1
cycle
 cache 20;
/*==============================================================*/
/* Table: BOK_ACCT_STANDARD                                     */
/*==============================================================*/
create table BOK_ACCT_STANDARD 
(
   ACCT_STANDARD_ID     VARCHAR(32)          not null,
   ACCT_STANDARD_NAME   VARCHAR(200),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_ACCT_STANDARD primary key (ACCT_STANDARD_ID)
);
comment on table BOK_ACCT_STANDARD is
'会计准则表';
comment on column BOK_ACCT_STANDARD.ACCT_STANDARD_ID is'会计准则代码';
comment on column BOK_ACCT_STANDARD.ACCT_STANDARD_NAME is'会计准则名称';
comment on column BOK_ACCT_STANDARD.CREATE_USER is'创建人';
comment on column BOK_ACCT_STANDARD.CREATE_DEPT is'创建部门';
comment on column BOK_ACCT_STANDARD.CREATE_TIME is'创建时间';
comment on column BOK_ACCT_STANDARD.UPDATE_USER is'更新人';
comment on column BOK_ACCT_STANDARD.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_ACCT_STANDARD_NATURE                              */
/*==============================================================*/
create table BOK_ACCT_STANDARD_NATURE 
(
   ACCT_STANDARD_ID     VARCHAR(32)          not null,
   BASE_SUBJECT_NATURE  VARCHAR(100)         not null,
   SUBJECT_NATURE_NAME  VARCHAR(200),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_ACCT_STANDARD_NATURE primary key (ACCT_STANDARD_ID, BASE_SUBJECT_NATURE)
);
comment on table BOK_ACCT_STANDARD_NATURE is
'准则科目性质表';
comment on column BOK_ACCT_STANDARD_NATURE.ACCT_STANDARD_ID is'会计准则';
comment on column BOK_ACCT_STANDARD_NATURE.BASE_SUBJECT_NATURE is'基础科目性质';
comment on column BOK_ACCT_STANDARD_NATURE.SUBJECT_NATURE_NAME is'科目性质描述';
comment on column BOK_ACCT_STANDARD_NATURE.CREATE_USER is'创建人';
comment on column BOK_ACCT_STANDARD_NATURE.CREATE_DEPT is'创建部门';
comment on column BOK_ACCT_STANDARD_NATURE.CREATE_TIME is'创建时间';
comment on column BOK_ACCT_STANDARD_NATURE.UPDATE_USER is'更新人';
comment on column BOK_ACCT_STANDARD_NATURE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_BALANCE                                           */
/*==============================================================*/
create table BOK_BALANCE 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   BALANCE_DATE         DATE                 not null,
   SUBJECT_NO           VARCHAR(300)         not null,
   FSUBJECT_NO          VARCHAR(32),
   SUBJECT_LEVEL        NUMERIC(10),
   BAL_FLAG             VARCHAR(50),
   BAL_CO_FLAG          VARCHAR(50),
   O_CCY                VARCHAR(50),
   O_AMT                NUMERIC(30, 2),
   O_C_AMT              NUMERIC(30, 2),
   O_D_AMT              NUMERIC(30, 2),
   O_CO_AMT             NUMERIC(30, 2),
   O_CO_C_AMT           NUMERIC(30, 2),
   O_CO_D_AMT           NUMERIC(30, 2),
   B_CCY                VARCHAR(50),
   B_AMT                NUMERIC(30, 2),
   B_C_AMT              NUMERIC(30, 2),
   B_CO_AMT             NUMERIC(30, 2),
   B_CO_C_AMT           NUMERIC(30, 2),
   B_CO_D_AMT           NUMERIC(30, 2),
   B_D_AMT              NUMERIC(30, 2),
   TDY_O_AMT            NUMERIC(30, 2),
   TDY_O_C_AMT          NUMERIC(30, 2),
   TDY_O_D_AMT          NUMERIC(30, 2),
   TDY_O_CO_AMT         NUMERIC(30, 2),
   TDY_O_CO_C_AMT       NUMERIC(30, 2),
   TDY_O_CO_D_AMT       NUMERIC(30, 2),
   TDY_B_AMT            NUMERIC(30, 2),
   TDY_B_C_AMT          NUMERIC(30, 2),
   TDY_B_D_AMT          NUMERIC(30, 2),
   TDY_B_CO_AMT         NUMERIC(30, 2),
   TDY_B_CO_C_AMT       NUMERIC(30, 2),
   TDY_B_CO_D_AMT       NUMERIC(30, 2),
   IS_LEAF              VARCHAR(50),
   NUM_AMT              NUMERIC(30, 2),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_BALANCE primary key (BOOKSET_ID, BALANCE_DATE, SUBJECT_NO)
);
comment on table BOK_BALANCE is
'科目余额';
comment on column BOK_BALANCE.BOOKSET_ID is'账套代码';
comment on column BOK_BALANCE.BALANCE_DATE is'余额日期';
comment on column BOK_BALANCE.SUBJECT_NO is'科目号';
comment on column BOK_BALANCE.FSUBJECT_NO is'父科目号';
comment on column BOK_BALANCE.SUBJECT_LEVEL is'科目级别';
comment on column BOK_BALANCE.BAL_FLAG is'余额方向';
comment on column BOK_BALANCE.BAL_CO_FLAG is'结转余额方向';
comment on column BOK_BALANCE.O_CCY is'原币';
comment on column BOK_BALANCE.O_AMT is'原币余额';
comment on column BOK_BALANCE.O_C_AMT is'原币余额贷方';
comment on column BOK_BALANCE.O_D_AMT is'原币余额借方';
comment on column BOK_BALANCE.O_CO_AMT is'原币结转余额';
comment on column BOK_BALANCE.O_CO_C_AMT is'原币结转余额贷方';
comment on column BOK_BALANCE.O_CO_D_AMT is'原币结转余额借方';
comment on column BOK_BALANCE.B_CCY is'本位币';
comment on column BOK_BALANCE.B_AMT is'本位币余额';
comment on column BOK_BALANCE.B_C_AMT is'本位币余额贷方';
comment on column BOK_BALANCE.B_CO_AMT is'本位币结转余额';
comment on column BOK_BALANCE.B_CO_C_AMT is'本位币结转余额贷方';
comment on column BOK_BALANCE.B_CO_D_AMT is'本位币结转余额借方';
comment on column BOK_BALANCE.B_D_AMT is'本位币余额借方';
comment on column BOK_BALANCE.TDY_O_AMT is'原币当日发生额';
comment on column BOK_BALANCE.TDY_O_C_AMT is'原币当日发生额贷方';
comment on column BOK_BALANCE.TDY_O_D_AMT is'原币当日发生额借方';
comment on column BOK_BALANCE.TDY_O_CO_AMT is'原币结转当日发生额';
comment on column BOK_BALANCE.TDY_O_CO_C_AMT is'原币结转当日发生额贷方';
comment on column BOK_BALANCE.TDY_O_CO_D_AMT is'原币结转当日发生额借方';
comment on column BOK_BALANCE.TDY_B_AMT is'本位币当日发生额';
comment on column BOK_BALANCE.TDY_B_C_AMT is'本位币当日发生额贷方';
comment on column BOK_BALANCE.TDY_B_D_AMT is'本位币当日发生额借方';
comment on column BOK_BALANCE.TDY_B_CO_AMT is'本位币结转当日发生额';
comment on column BOK_BALANCE.TDY_B_CO_C_AMT is'本位币结转当日发生额贷方';
comment on column BOK_BALANCE.TDY_B_CO_D_AMT is'本位币结转当日发生额借方';
comment on column BOK_BALANCE.IS_LEAF is'是否叶子节点';
comment on column BOK_BALANCE.NUM_AMT is'数量余额';
comment on column BOK_BALANCE.CREATE_USER is'创建人';
comment on column BOK_BALANCE.CREATE_DEPT is'创建部门';
comment on column BOK_BALANCE.CREATE_TIME is'创建时间';
comment on column BOK_BALANCE.UPDATE_USER is'更新人';
comment on column BOK_BALANCE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_BAL_TABLE_DATA                                    */
/*==============================================================*/
create table BOK_BAL_TABLE_DATA 
(
   SEQ_NO               VARCHAR(32)          not null,
   O_CCY                VARCHAR(50),
   O_OPEN_BAL_FLAG      VARCHAR(50),
   O_OPEN_BALANCE       NUMERIC(30, 2),
   O_HAPPEN_AMT_D       NUMERIC(30, 2),
   O_HAPPEN_AMT_C       NUMERIC(30, 2),
   O_END_BAL_FLAG       VARCHAR(50),
   O_END_BALANCE        NUMERIC(30, 2),
   B_OPEN_BAL_FLAG      VARCHAR(50),
   B_OPEN_BALANCE       NUMERIC(30, 2),
   B_HAPPEN_AMT_D       NUMERIC(30, 2),
   B_HAPPEN_AMT_C       NUMERIC(30, 2),
   B_END_BAL_FLAG       VARCHAR(50),
   B_END_BALANCE        NUMERIC(30, 2),
   BOOKSET_ID           VARCHAR(32),
   BOOKSET_NAME         VARCHAR(50),
   BAL_DATE             DATE,
   SUBJECT_NO           VARCHAR(300),
   SUBJECT_NAME         VARCHAR(500),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_BAL_TABLE_DATA primary key (SEQ_NO)
);
comment on table BOK_BAL_TABLE_DATA is
'估值表数据';
comment on column BOK_BAL_TABLE_DATA.SEQ_NO is'流水号';
comment on column BOK_BAL_TABLE_DATA.O_CCY is'币种';
comment on column BOK_BAL_TABLE_DATA.O_OPEN_BAL_FLAG is'原币期初余额方向';
comment on column BOK_BAL_TABLE_DATA.O_OPEN_BALANCE is'原币期初余额';
comment on column BOK_BAL_TABLE_DATA.O_HAPPEN_AMT_D is'原币本期借方发生额';
comment on column BOK_BAL_TABLE_DATA.O_HAPPEN_AMT_C is'原币本期贷方发生额';
comment on column BOK_BAL_TABLE_DATA.O_END_BAL_FLAG is'原币期末余额方向';
comment on column BOK_BAL_TABLE_DATA.O_END_BALANCE is'原币期末余额';
comment on column BOK_BAL_TABLE_DATA.B_OPEN_BAL_FLAG is'本位币期初余额方向';
comment on column BOK_BAL_TABLE_DATA.B_OPEN_BALANCE is'本位币期初余额';
comment on column BOK_BAL_TABLE_DATA.B_HAPPEN_AMT_D is'本位币本期借方发生额';
comment on column BOK_BAL_TABLE_DATA.B_HAPPEN_AMT_C is'本位币本期贷方发生额';
comment on column BOK_BAL_TABLE_DATA.B_END_BAL_FLAG is'本位币期末余额方向';
comment on column BOK_BAL_TABLE_DATA.B_END_BALANCE is'本位币期末余额';
comment on column BOK_BAL_TABLE_DATA.BOOKSET_ID is'账套代码';
comment on column BOK_BAL_TABLE_DATA.BOOKSET_NAME is'账套名称';
comment on column BOK_BAL_TABLE_DATA.BAL_DATE is'余额日期';
comment on column BOK_BAL_TABLE_DATA.SUBJECT_NO is'科目号';
comment on column BOK_BAL_TABLE_DATA.SUBJECT_NAME is'科目名称';
comment on column BOK_BAL_TABLE_DATA.CREATE_USER is'创建人';
comment on column BOK_BAL_TABLE_DATA.CREATE_DEPT is'创建部门';
comment on column BOK_BAL_TABLE_DATA.CREATE_TIME is'创建时间';
comment on column BOK_BAL_TABLE_DATA.UPDATE_USER is'更新人';
comment on column BOK_BAL_TABLE_DATA.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_BATCH_TASK_GRP                                    */
/*==============================================================*/
create table BOK_BATCH_TASK_GRP 
(
   TASK_GRP_ID          VARCHAR(32)          not null,
   TASK_GRP_NAME        VARCHAR2(255),
   TASK_GRP_TYPE        VARCHAR2(50),
   P_TASK_GRP_ID        VARCHAR2(32),
   TASK_ID              VARCHAR2(32),
   ORDER_NO             NUMBER(10),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_TIME          TIMESTAMP(6)         not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP(6)         not null,
   CREATE_DEPT          VARCHAR(32),
   constraint PK_BOK_BATCH_TASK_GRP primary key (TASK_GRP_ID)
);
comment on table BOK_BATCH_TASK_GRP is
'批处理分类表';
comment on column BOK_BATCH_TASK_GRP.TASK_GRP_ID is'分类ID';
comment on column BOK_BATCH_TASK_GRP.TASK_GRP_NAME is'分类名称';
comment on column BOK_BATCH_TASK_GRP.TASK_GRP_TYPE is'分类类型 task:批处理';
comment on column BOK_BATCH_TASK_GRP.P_TASK_GRP_ID is'父分类ID';
comment on column BOK_BATCH_TASK_GRP.TASK_ID is'批处理ID';
comment on column BOK_BATCH_TASK_GRP.ORDER_NO is'顺序';
comment on column BOK_BATCH_TASK_GRP.CREATE_USER is'创建人';
comment on column BOK_BATCH_TASK_GRP.CREATE_TIME is'创建时间';
comment on column BOK_BATCH_TASK_GRP.UPDATE_USER is'更新人';
comment on column BOK_BATCH_TASK_GRP.UPDATE_TIME is'更新时间';
comment on column BOK_BATCH_TASK_GRP.CREATE_DEPT is'创建部门';
/*==============================================================*/
/* Table: BOK_BATCH_TASK_LINK                                   */
/*==============================================================*/
create table BOK_BATCH_TASK_LINK 
(
   TASK_ID              VARCHAR(32)          not null,
   P_TASK_ID            VARCHAR(32)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_BATCH_TASK_LINK primary key (TASK_ID, P_TASK_ID)
);
comment on table BOK_BATCH_TASK_LINK is
'批处理前序任务表';
comment on column BOK_BATCH_TASK_LINK.TASK_ID is'任务代码';
comment on column BOK_BATCH_TASK_LINK.P_TASK_ID is'前序任务代码';
comment on column BOK_BATCH_TASK_LINK.CREATE_USER is'创建人';
comment on column BOK_BATCH_TASK_LINK.CREATE_DEPT is'创建部门';
comment on column BOK_BATCH_TASK_LINK.CREATE_TIME is'创建时间';
comment on column BOK_BATCH_TASK_LINK.UPDATE_USER is'更新人';
comment on column BOK_BATCH_TASK_LINK.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_BATCH_TASK_TODO                                   */
/*==============================================================*/
create table BOK_BATCH_TASK_TODO 
(
   SEQ_NO               VARCHAR(32),
   BOOKSET_ID           VARCHAR(32),
   HAPPEN_DATE          DATE,
   TASK_ID              VARCHAR(32),
   BOOKSET_DATE         DATE,
   BOK_DETAIL_ID        VARCHAR(32),
   BOOK_DATE            DATE,
   IS_BACK_VALUE        VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null
);
comment on table BOK_BATCH_TASK_TODO is
'批处理待处理任务表';
comment on column BOK_BATCH_TASK_TODO.SEQ_NO is'批处理任务组执行ID';
comment on column BOK_BATCH_TASK_TODO.BOOKSET_ID is'账套代码';
comment on column BOK_BATCH_TASK_TODO.HAPPEN_DATE is'发生日期';
comment on column BOK_BATCH_TASK_TODO.TASK_ID is'任务代码';
comment on column BOK_BATCH_TASK_TODO.BOOKSET_DATE is'账套日期';
comment on column BOK_BATCH_TASK_TODO.BOK_DETAIL_ID is'账务明细代码';
comment on column BOK_BATCH_TASK_TODO.BOOK_DATE is'入账日期';
comment on column BOK_BATCH_TASK_TODO.IS_BACK_VALUE is'是否倒起息';
comment on column BOK_BATCH_TASK_TODO.CREATE_USER is'创建人';
comment on column BOK_BATCH_TASK_TODO.CREATE_DEPT is'创建部门';
comment on column BOK_BATCH_TASK_TODO.CREATE_TIME is'创建时间';
comment on column BOK_BATCH_TASK_TODO.UPDATE_USER is'更新人';
comment on column BOK_BATCH_TASK_TODO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Index: "Index_10"                                            */
/*==============================================================*/
create index "Index_10" on BOK_BATCH_TASK_TODO (
   SEQ_NO ASC,
   BOOKSET_DATE ASC,
   BOOK_DATE ASC,
   HAPPEN_DATE ASC,
   TASK_ID ASC
);
/*==============================================================*/
/* Table: BOK_BOOKDETAIL_ALL                                    */
/*==============================================================*/
create table BOK_BOOKDETAIL_ALL 
(
   SEQNO                VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32),
   TABLE_ID             VARCHAR(20),
   TRADE_ID             VARCHAR(32),
   BUSI_ID              VARCHAR(32),
   TASK_ID              VARCHAR(32),
   TASK_PRI             NUMERIC(10),
   HAPPEN_DATE          DATE,
   BOOK_DATE            DATE,
   BOOKSET_DATE         DATE,
   ONWAY_DATE           DATE,
   BASE_SUBJECT_NATURE  VARCHAR(100),
   BOK_DETAIL_ID        VARCHAR(32),
   BRANCH               NUMERIC(10)          not null,
   SUBJECT_NO           VARCHAR(32),
   DETAIL_SUBJECT_NO    VARCHAR(300),
   CD_FLAG              VARCHAR(50),
   O_CCY                VARCHAR(50),
   O_HAPPEN_AMT         NUMERIC(30, 2),
   B_CCY                VARCHAR(50),
   B_HAPPEN_AMT         NUMERIC(30, 2),
   HAPPEN_NUMBER        NUMERIC(30, 4),
   MDATE                DATE,
   BOOK_ID              VARCHAR(32),
   BOOK_REMARK          VARCHAR(200),
   OFFSET_FLAG          VARCHAR(50),
   OFFSET_DATE          DATE,
   R_SEQNO              VARCHAR(32),
   BOOK_TYPE            VARCHAR(50),
   BOOK_ORDER           NUMERIC(20),
   BAL_FLAG_HAPPEN_DATE VARCHAR(50),
   O_AMT_HAPPEN_DATE    NUMERIC(30, 2),
   B_AMT_HAPPEN_DATE    NUMERIC(30, 2),
   NUM_AMT_HAPPEN_DATE  NUMERIC(30, 4),
   BAL_FLAG_BOOK_DATE   VARCHAR(50),
   O_AMT_BOOK_DATE      NUMERIC(30, 2),
   B_AMT_BOOK_DATE      NUMERIC(30, 2),
   NUM_AMT_BOOK_DATE    NUMERIC(30, 4),
   EX_DATE              DATE,
   EX_RATE              NUMERIC(30, 14),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_TIME          TIMESTAMP            not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   UPDATE_USER          VARCHAR(20)          not null,
   constraint PK_BOK_BOOKDETAIL_ALL primary key (SEQNO)
)
partition by list
 (BOOKSET_ID)
 (partition BOOKSET_ID_DEFAULT
 values (DEFAULT));
comment on table BOK_BOOKDETAIL_ALL is
'账务明细（历史表）';
comment on column BOK_BOOKDETAIL_ALL.SEQNO is'流水';
comment on column BOK_BOOKDETAIL_ALL.BOOKSET_ID is'账套代码';
comment on column BOK_BOOKDETAIL_ALL.TABLE_ID is'场景代码';
comment on column BOK_BOOKDETAIL_ALL.TRADE_ID is'交易编号';
comment on column BOK_BOOKDETAIL_ALL.BUSI_ID is'业务明细代码';
comment on column BOK_BOOKDETAIL_ALL.TASK_ID is'任务代码';
comment on column BOK_BOOKDETAIL_ALL.TASK_PRI is'任务优先级';
comment on column BOK_BOOKDETAIL_ALL.HAPPEN_DATE is'发生日期';
comment on column BOK_BOOKDETAIL_ALL.BOOK_DATE is'入账日期';
comment on column BOK_BOOKDETAIL_ALL.BOOKSET_DATE is'账套日期';
comment on column BOK_BOOKDETAIL_ALL.ONWAY_DATE is'在途日期';
comment on column BOK_BOOKDETAIL_ALL.BASE_SUBJECT_NATURE is'基础科目性质';
comment on column BOK_BOOKDETAIL_ALL.BOK_DETAIL_ID is'账务明细代码';
comment on column BOK_BOOKDETAIL_ALL.BRANCH is'分支序号';
comment on column BOK_BOOKDETAIL_ALL.SUBJECT_NO is'科目号';
comment on column BOK_BOOKDETAIL_ALL.DETAIL_SUBJECT_NO is'四级科目号';
comment on column BOK_BOOKDETAIL_ALL.CD_FLAG is'借贷方向';
comment on column BOK_BOOKDETAIL_ALL.O_CCY is'原币';
comment on column BOK_BOOKDETAIL_ALL.O_HAPPEN_AMT is'原币发生额';
comment on column BOK_BOOKDETAIL_ALL.B_CCY is'本位币';
comment on column BOK_BOOKDETAIL_ALL.B_HAPPEN_AMT is'本位币发生额';
comment on column BOK_BOOKDETAIL_ALL.HAPPEN_NUMBER is'发生数量';
comment on column BOK_BOOKDETAIL_ALL.MDATE is'结息日';
comment on column BOK_BOOKDETAIL_ALL.BOOK_ID is'凭证编号';
comment on column BOK_BOOKDETAIL_ALL.BOOK_REMARK is'凭证说明';
comment on column BOK_BOOKDETAIL_ALL.OFFSET_FLAG is'冲正标识';
comment on column BOK_BOOKDETAIL_ALL.OFFSET_DATE is'冲正日期';
comment on column BOK_BOOKDETAIL_ALL.R_SEQNO is'反向流水号';
comment on column BOK_BOOKDETAIL_ALL.BOOK_TYPE is'凭证类型';
comment on column BOK_BOOKDETAIL_ALL.BOOK_ORDER is'处理序列';
comment on column BOK_BOOKDETAIL_ALL.BAL_FLAG_HAPPEN_DATE is'余额方向（发生日）';
comment on column BOK_BOOKDETAIL_ALL.O_AMT_HAPPEN_DATE is'原币余额（发生日）';
comment on column BOK_BOOKDETAIL_ALL.B_AMT_HAPPEN_DATE is'本位币余额（发生日）';
comment on column BOK_BOOKDETAIL_ALL.NUM_AMT_HAPPEN_DATE is'数量余额（发生日）';
comment on column BOK_BOOKDETAIL_ALL.BAL_FLAG_BOOK_DATE is'余额方向（入账日）';
comment on column BOK_BOOKDETAIL_ALL.O_AMT_BOOK_DATE is'原币余额（入账日）';
comment on column BOK_BOOKDETAIL_ALL.B_AMT_BOOK_DATE is'本位币余额（入账日）';
comment on column BOK_BOOKDETAIL_ALL.NUM_AMT_BOOK_DATE is'数量余额（入账日）';
comment on column BOK_BOOKDETAIL_ALL.EX_DATE is'换汇日期';
comment on column BOK_BOOKDETAIL_ALL.EX_RATE is'折算汇率';
comment on column BOK_BOOKDETAIL_ALL.CREATE_TIME is'创建时间';
comment on column BOK_BOOKDETAIL_ALL.UPDATE_TIME is'更新时间';
comment on column BOK_BOOKDETAIL_ALL.CREATE_DEPT is'创建部门';
comment on column BOK_BOOKDETAIL_ALL.CREATE_USER is'创建人';
comment on column BOK_BOOKDETAIL_ALL.UPDATE_USER is'更新人';
/*==============================================================*/
/* Index: "Index_1"                                             */
/*==============================================================*/
create index "Index_1" on BOK_BOOKDETAIL_ALL (
   BOOKSET_ID ASC
);
/*==============================================================*/
/* Index: "Index_2"                                             */
/*==============================================================*/
create index "Index_2" on BOK_BOOKDETAIL_ALL (
   TABLE_ID ASC
);
/*==============================================================*/
/* Index: "Index_3"                                             */
/*==============================================================*/
create index "Index_3" on BOK_BOOKDETAIL_ALL (
   TRADE_ID ASC
);
/*==============================================================*/
/* Index: "Index_4"                                             */
/*==============================================================*/
create index "Index_4" on BOK_BOOKDETAIL_ALL (
   TASK_PRI ASC
);
/*==============================================================*/
/* Index: "Index_5"                                             */
/*==============================================================*/
create index "Index_5" on BOK_BOOKDETAIL_ALL (
   HAPPEN_DATE ASC
);
/*==============================================================*/
/* Index: "Index_6"                                             */
/*==============================================================*/
create index "Index_6" on BOK_BOOKDETAIL_ALL (
   BOOK_DATE ASC
);
/*==============================================================*/
/* Index: "Index_7"                                             */
/*==============================================================*/
create index "Index_7" on BOK_BOOKDETAIL_ALL (
   BOOKSET_DATE ASC
);
/*==============================================================*/
/* Index: "Index_8"                                             */
/*==============================================================*/
create index "Index_8" on BOK_BOOKDETAIL_ALL (
   BASE_SUBJECT_NATURE ASC
);
/*==============================================================*/
/* Index: "Index_9"                                             */
/*==============================================================*/
create index "Index_9" on BOK_BOOKDETAIL_ALL (
   BOK_DETAIL_ID ASC
);
/*==============================================================*/
/* Table: BOK_BOOKDETAIL_CURRENT                                */
/*==============================================================*/
create table BOK_BOOKDETAIL_CURRENT 
(
   SEQNO                VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32),
   TABLE_ID             VARCHAR(20),
   TRADE_ID             VARCHAR(32),
   BUSI_ID              VARCHAR(32),
   TASK_ID              VARCHAR(32),
   TASK_PRI             NUMERIC(10),
   HAPPEN_DATE          DATE,
   BOOK_DATE            DATE,
   BOOKSET_DATE         DATE,
   ONWAY_DATE           DATE,
   BASE_SUBJECT_NATURE  VARCHAR(100),
   BOK_DETAIL_ID        VARCHAR(32),
   BRANCH               NUMERIC(10)          not null,
   SUBJECT_NO           VARCHAR(32),
   DETAIL_SUBJECT_NO    VARCHAR(300),
   CD_FLAG              VARCHAR(50),
   O_CCY                VARCHAR(50),
   O_HAPPEN_AMT         NUMERIC(30, 2),
   B_CCY                VARCHAR(50),
   B_HAPPEN_AMT         NUMERIC(30, 2),
   HAPPEN_NUMBER        NUMERIC(30, 4),
   MDATE                DATE,
   BOOK_ID              VARCHAR(32),
   BOOK_REMARK          VARCHAR(200),
   OFFSET_FLAG          VARCHAR(50),
   OFFSET_DATE          DATE,
   R_SEQNO              VARCHAR(32),
   BOOK_TYPE            VARCHAR(50),
   BOOK_ORDER           NUMERIC(20),
   BAL_FLAG_HAPPEN_DATE VARCHAR(50),
   O_AMT_HAPPEN_DATE    NUMERIC(30, 2),
   B_AMT_HAPPEN_DATE    NUMERIC(30, 2),
   NUM_AMT_HAPPEN_DATE  NUMERIC(30, 4),
   BAL_FLAG_BOOK_DATE   VARCHAR(50),
   O_AMT_BOOK_DATE      NUMERIC(30, 2),
   B_AMT_BOOK_DATE      NUMERIC(30, 2),
   NUM_AMT_BOOK_DATE    NUMERIC(30, 4),
   EX_DATE              DATE,
   EX_RATE              NUMERIC(30, 14),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_BOOKDETAIL_CURRENT primary key (SEQNO)
)
partition by list
 (BOOKSET_ID)
 (partition BOOKSET_ID_DEFAULT
 values (DEFAULT));
comment on table BOK_BOOKDETAIL_CURRENT is
'账务明细（当前表）';
comment on column BOK_BOOKDETAIL_CURRENT.SEQNO is'流水';
comment on column BOK_BOOKDETAIL_CURRENT.BOOKSET_ID is'账套代码';
comment on column BOK_BOOKDETAIL_CURRENT.TABLE_ID is'场景代码';
comment on column BOK_BOOKDETAIL_CURRENT.TRADE_ID is'交易编号';
comment on column BOK_BOOKDETAIL_CURRENT.BUSI_ID is'业务明细代码';
comment on column BOK_BOOKDETAIL_CURRENT.TASK_ID is'任务代码';
comment on column BOK_BOOKDETAIL_CURRENT.TASK_PRI is'任务优先级';
comment on column BOK_BOOKDETAIL_CURRENT.HAPPEN_DATE is'发生日期';
comment on column BOK_BOOKDETAIL_CURRENT.BOOK_DATE is'入账日期';
comment on column BOK_BOOKDETAIL_CURRENT.BOOKSET_DATE is'账套日期';
comment on column BOK_BOOKDETAIL_CURRENT.ONWAY_DATE is'在途日期';
comment on column BOK_BOOKDETAIL_CURRENT.BASE_SUBJECT_NATURE is'基础科目性质';
comment on column BOK_BOOKDETAIL_CURRENT.BOK_DETAIL_ID is'账务明细代码';
comment on column BOK_BOOKDETAIL_CURRENT.BRANCH is'分支序号';
comment on column BOK_BOOKDETAIL_CURRENT.SUBJECT_NO is'科目号';
comment on column BOK_BOOKDETAIL_CURRENT.DETAIL_SUBJECT_NO is'四级科目号';
comment on column BOK_BOOKDETAIL_CURRENT.CD_FLAG is'借贷方向';
comment on column BOK_BOOKDETAIL_CURRENT.O_CCY is'原币';
comment on column BOK_BOOKDETAIL_CURRENT.O_HAPPEN_AMT is'原币发生额';
comment on column BOK_BOOKDETAIL_CURRENT.B_CCY is'本位币';
comment on column BOK_BOOKDETAIL_CURRENT.B_HAPPEN_AMT is'本位币发生额';
comment on column BOK_BOOKDETAIL_CURRENT.HAPPEN_NUMBER is'发生数量';
comment on column BOK_BOOKDETAIL_CURRENT.MDATE is'结息日';
comment on column BOK_BOOKDETAIL_CURRENT.BOOK_ID is'凭证编号';
comment on column BOK_BOOKDETAIL_CURRENT.BOOK_REMARK is'凭证说明';
comment on column BOK_BOOKDETAIL_CURRENT.OFFSET_FLAG is'冲正标识';
comment on column BOK_BOOKDETAIL_CURRENT.OFFSET_DATE is'冲正日期';
comment on column BOK_BOOKDETAIL_CURRENT.R_SEQNO is'反向流水号';
comment on column BOK_BOOKDETAIL_CURRENT.BOOK_TYPE is'凭证类型';
comment on column BOK_BOOKDETAIL_CURRENT.BOOK_ORDER is'处理序列';
comment on column BOK_BOOKDETAIL_CURRENT.BAL_FLAG_HAPPEN_DATE is'余额方向（发生日）';
comment on column BOK_BOOKDETAIL_CURRENT.O_AMT_HAPPEN_DATE is'原币余额（发生日）';
comment on column BOK_BOOKDETAIL_CURRENT.B_AMT_HAPPEN_DATE is'本位币余额（发生日）';
comment on column BOK_BOOKDETAIL_CURRENT.NUM_AMT_HAPPEN_DATE is'数量余额（发生日）';
comment on column BOK_BOOKDETAIL_CURRENT.BAL_FLAG_BOOK_DATE is'余额方向（入账日）';
comment on column BOK_BOOKDETAIL_CURRENT.O_AMT_BOOK_DATE is'原币余额（入账日）';
comment on column BOK_BOOKDETAIL_CURRENT.B_AMT_BOOK_DATE is'本位币余额（入账日）';
comment on column BOK_BOOKDETAIL_CURRENT.NUM_AMT_BOOK_DATE is'数量余额（入账日）';
comment on column BOK_BOOKDETAIL_CURRENT.EX_DATE is'换汇日期';
comment on column BOK_BOOKDETAIL_CURRENT.EX_RATE is'折算汇率';
comment on column BOK_BOOKDETAIL_CURRENT.CREATE_USER is'创建人';
comment on column BOK_BOOKDETAIL_CURRENT.CREATE_DEPT is'创建部门';
comment on column BOK_BOOKDETAIL_CURRENT.CREATE_TIME is'创建时间';
comment on column BOK_BOOKDETAIL_CURRENT.UPDATE_USER is'更新人';
comment on column BOK_BOOKDETAIL_CURRENT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Index: "Index_11"                                            */
/*==============================================================*/
create index "Index_11" on BOK_BOOKDETAIL_CURRENT (
   BOOKSET_ID ASC
);
/*==============================================================*/
/* Index: "Index_12"                                            */
/*==============================================================*/
create index "Index_12" on BOK_BOOKDETAIL_CURRENT (
   TABLE_ID ASC
);
/*==============================================================*/
/* Index: "Index_13"                                            */
/*==============================================================*/
create index "Index_13" on BOK_BOOKDETAIL_CURRENT (
   TRADE_ID ASC
);
/*==============================================================*/
/* Index: "Index_14"                                            */
/*==============================================================*/
create index "Index_14" on BOK_BOOKDETAIL_CURRENT (
   TASK_PRI ASC
);
/*==============================================================*/
/* Index: "Index_15"                                            */
/*==============================================================*/
create index "Index_15" on BOK_BOOKDETAIL_CURRENT (
   HAPPEN_DATE ASC
);
/*==============================================================*/
/* Index: "Index_16"                                            */
/*==============================================================*/
create index "Index_16" on BOK_BOOKDETAIL_CURRENT (
   BOOK_DATE ASC
);
/*==============================================================*/
/* Index: "Index_17"                                            */
/*==============================================================*/
create index "Index_17" on BOK_BOOKDETAIL_CURRENT (
   BOOKSET_DATE ASC
);
/*==============================================================*/
/* Index: "Index_18"                                            */
/*==============================================================*/
create index "Index_18" on BOK_BOOKDETAIL_CURRENT (
   BASE_SUBJECT_NATURE ASC
);
/*==============================================================*/
/* Index: "Index_19"                                            */
/*==============================================================*/
create index "Index_19" on BOK_BOOKDETAIL_CURRENT (
   BOK_DETAIL_ID ASC
);
/*==============================================================*/
/* Table: BOK_BOOKSET                                           */
/*==============================================================*/
create table BOK_BOOKSET 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   BOOKSET_NAME         VARCHAR(200),
   BOOKSET_TYPE         VARCHAR(50),
   ACCT_MODE            VARCHAR(50),
   ACCT_SUBJECT_ID      VARCHAR(32),
   ACCT_SUBJECT_TYPE    VARCHAR(50),
   STANDARD_CCY         VARCHAR(50),
   BOOKSET_ID_TMPL      VARCHAR(32),
   ACCT_STANDARD        VARCHAR(32),
   START_DATE           DATE,
   END_DATE             DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_BOOKSET primary key (BOOKSET_ID)
);
comment on table BOK_BOOKSET is
'账套';
comment on column BOK_BOOKSET.BOOKSET_ID is'账套代码';
comment on column BOK_BOOKSET.BOOKSET_NAME is'账套名称';
comment on column BOK_BOOKSET.BOOKSET_TYPE is'账套类别';
comment on column BOK_BOOKSET.ACCT_MODE is'核算方式';
comment on column BOK_BOOKSET.ACCT_SUBJECT_ID is'核算主体代码';
comment on column BOK_BOOKSET.ACCT_SUBJECT_TYPE is'核算主体类别';
comment on column BOK_BOOKSET.STANDARD_CCY is'本位币';
comment on column BOK_BOOKSET.BOOKSET_ID_TMPL is'模板账套代码';
comment on column BOK_BOOKSET.ACCT_STANDARD is'会计准则';
comment on column BOK_BOOKSET.START_DATE is'账套开始日';
comment on column BOK_BOOKSET.END_DATE is'账套结束日';
comment on column BOK_BOOKSET.CREATE_USER is'创建人';
comment on column BOK_BOOKSET.CREATE_DEPT is'创建部门';
comment on column BOK_BOOKSET.CREATE_TIME is'创建时间';
comment on column BOK_BOOKSET.UPDATE_USER is'更新人';
comment on column BOK_BOOKSET.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_BOOKSET_CAPACCT_INFO                              */
/*==============================================================*/
create table BOK_BOOKSET_CAPACCT_INFO 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   ACCT_NO              VARCHAR(32)          not null,
   CCY                  VARCHAR(50)          not null,
   ACCT_TYPE            VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_BOOKSET_CAPACCT_INFO primary key (ACCT_NO, BOOKSET_ID, CCY)
);
comment on table BOK_BOOKSET_CAPACCT_INFO is
'账套清算账户表';
comment on column BOK_BOOKSET_CAPACCT_INFO.BOOKSET_ID is'账套代码';
comment on column BOK_BOOKSET_CAPACCT_INFO.ACCT_NO is'账号';
comment on column BOK_BOOKSET_CAPACCT_INFO.CCY is'币种';
comment on column BOK_BOOKSET_CAPACCT_INFO.ACCT_TYPE is'资金账户类型';
comment on column BOK_BOOKSET_CAPACCT_INFO.CREATE_USER is'创建人';
comment on column BOK_BOOKSET_CAPACCT_INFO.CREATE_DEPT is'创建部门';
comment on column BOK_BOOKSET_CAPACCT_INFO.CREATE_TIME is'创建时间';
comment on column BOK_BOOKSET_CAPACCT_INFO.UPDATE_USER is'更新人';
comment on column BOK_BOOKSET_CAPACCT_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_BOOKSET_DATE                                      */
/*==============================================================*/
create table BOK_BOOKSET_DATE 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   BOOKSET_DATE         DATE,
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_BOOKSET_DATE primary key (BOOKSET_ID)
);
comment on table BOK_BOOKSET_DATE is
'账套日期表';
comment on column BOK_BOOKSET_DATE.BOOKSET_ID is'账套代码';
comment on column BOK_BOOKSET_DATE.BOOKSET_DATE is'当前日期';
comment on column BOK_BOOKSET_DATE.CREATE_USER is'创建人';
comment on column BOK_BOOKSET_DATE.CREATE_DEPT is'创建部门';
comment on column BOK_BOOKSET_DATE.CREATE_TIME is'创建时间';
comment on column BOK_BOOKSET_DATE.UPDATE_USER is'更新人';
comment on column BOK_BOOKSET_DATE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_BOOKSET_EXT                                       */
/*==============================================================*/
create table BOK_BOOKSET_EXT 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   PARAM_CODE           VARCHAR(32)          not null,
   PARAM_VALUE          VARCHAR(50),
   PARAM_NAME           VARCHAR(200),
   REMARK               VARCHAR(500),
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_BOOKSET_EXT primary key (BOOKSET_ID, PARAM_CODE)
);
comment on table BOK_BOOKSET_EXT is
'账套配置表';
comment on column BOK_BOOKSET_EXT.BOOKSET_ID is'账套代码';
comment on column BOK_BOOKSET_EXT.PARAM_CODE is'参数项';
comment on column BOK_BOOKSET_EXT.PARAM_VALUE is'参数值';
comment on column BOK_BOOKSET_EXT.PARAM_NAME is'参数名称';
comment on column BOK_BOOKSET_EXT.REMARK is'参数说明';
comment on column BOK_BOOKSET_EXT.CREATE_USER is'创建人';
comment on column BOK_BOOKSET_EXT.CREATE_DEPT is'创建部门';
comment on column BOK_BOOKSET_EXT.CREATE_TIME is'创建时间';
comment on column BOK_BOOKSET_EXT.UPDATE_USER is'更新人';
comment on column BOK_BOOKSET_EXT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_BOOKSET_OPTION                                    */
/*==============================================================*/
create table BOK_BOOKSET_OPTION 
(
   SEQ                  CHAR(10)             not null,
   OPT_NO               CHAR(10),
   BOOKSET_ID           VARCHAR(20)          not null,
   BOOKSET_NAME         NVARCHAR2(200),
   BOOKSET_TYPE         VARCHAR(10),
   ACCT_SUBJECT_ID      VARCHAR(20),
   STANDARD_CCY         CHAR(3),
   BOOKSET_ID_TMPL      VARCHAR(20),
   ACCT_STANDARD        VARCHAR(10),
   START_DATE           TIMESTAMP,
   END_DATE             TIMESTAMP,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_BOOKSET_OPTION primary key (SEQ)
);
comment on table BOK_BOOKSET_OPTION is
'账套留痕表';
comment on column BOK_BOOKSET_OPTION.SEQ is'操作流水';
comment on column BOK_BOOKSET_OPTION.OPT_NO is'操作序号';
comment on column BOK_BOOKSET_OPTION.BOOKSET_ID is'账套代码';
comment on column BOK_BOOKSET_OPTION.BOOKSET_NAME is'账套名称';
comment on column BOK_BOOKSET_OPTION.BOOKSET_TYPE is'账套类别';
comment on column BOK_BOOKSET_OPTION.ACCT_SUBJECT_ID is'核算主体代码';
comment on column BOK_BOOKSET_OPTION.STANDARD_CCY is'本位币';
comment on column BOK_BOOKSET_OPTION.BOOKSET_ID_TMPL is'模板账套代码';
comment on column BOK_BOOKSET_OPTION.ACCT_STANDARD is'会计准则';
comment on column BOK_BOOKSET_OPTION.START_DATE is'账套开始日';
comment on column BOK_BOOKSET_OPTION.END_DATE is'账套结束日';
comment on column BOK_BOOKSET_OPTION.CREATE_USER is'创建人';
comment on column BOK_BOOKSET_OPTION.CREATE_DEPT is'创建部门';
comment on column BOK_BOOKSET_OPTION.CREATE_TIME is'创建时间';
comment on column BOK_BOOKSET_OPTION.UPDATE_USER is'更新人';
comment on column BOK_BOOKSET_OPTION.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_BOOKSET_PLAN                                      */
/*==============================================================*/
create table BOK_BOOKSET_PLAN 
(
   BOOKSET_SCHEME_ID    VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32),
   SCHEME_ID            VARCHAR(32),
   SCHEME_TYPE          VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_BOOKSET_PLAN primary key (BOOKSET_SCHEME_ID)
);
comment on table BOK_BOOKSET_PLAN is
'账套估值方案';
comment on column BOK_BOOKSET_PLAN.BOOKSET_SCHEME_ID is'账套估值方案代码';
comment on column BOK_BOOKSET_PLAN.BOOKSET_ID is'账套代码';
comment on column BOK_BOOKSET_PLAN.SCHEME_ID is'方案代码';
comment on column BOK_BOOKSET_PLAN.SCHEME_TYPE is'方案类型';
comment on column BOK_BOOKSET_PLAN.CREATE_USER is'创建人';
comment on column BOK_BOOKSET_PLAN.CREATE_DEPT is'创建部门';
comment on column BOK_BOOKSET_PLAN.CREATE_TIME is'创建时间';
comment on column BOK_BOOKSET_PLAN.UPDATE_USER is'更新人';
comment on column BOK_BOOKSET_PLAN.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_BUSS_DETSUB                                       */
/*==============================================================*/
create table BOK_BUSS_DETSUB 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   DETAIL_SUBJECT_NO    VARCHAR(300)         not null,
   DETAIL_SUBJECT_NAME  VARCHAR(500),
   FSUBJECT_ID          VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   DETAIL_SUBJECT_NO_TAIL VARCHAR(32),
   DETAIL_SUBNAME_TAIL  VARCHAR(500),
   constraint PK_BOK_BUSS_DETSUB primary key (BOOKSET_ID, DETAIL_SUBJECT_NO)
)
partition by list
 (BOOKSET_ID)
 (partition BOOKSET_ID_DEFAULT
 values (DEFAULT));
comment on table BOK_BUSS_DETSUB is
'业务明细科目';
comment on column BOK_BUSS_DETSUB.BOOKSET_ID is'账套代码';
comment on column BOK_BUSS_DETSUB.DETAIL_SUBJECT_NO is'四级科目号';
comment on column BOK_BUSS_DETSUB.DETAIL_SUBJECT_NAME is'四级科目名称';
comment on column BOK_BUSS_DETSUB.FSUBJECT_ID is'上级科目号';
comment on column BOK_BUSS_DETSUB.CREATE_USER is'创建人';
comment on column BOK_BUSS_DETSUB.CREATE_DEPT is'创建部门';
comment on column BOK_BUSS_DETSUB.CREATE_TIME is'创建时间';
comment on column BOK_BUSS_DETSUB.UPDATE_USER is'更新人';
comment on column BOK_BUSS_DETSUB.UPDATE_TIME is'更新时间';
comment on column BOK_BUSS_DETSUB.DETAIL_SUBJECT_NO_TAIL is'四级科目尾号';
comment on column BOK_BUSS_DETSUB.DETAIL_SUBNAME_TAIL is'四级科目尾号名称';
/*==============================================================*/
/* Table: BOK_DEAL_ACCOUNTING                                   */
/*==============================================================*/
create table BOK_DEAL_ACCOUNTING 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   BUSI_NO              VARCHAR(32)          not null,
   BUSI_TABLE           VARCHAR(50)          not null,
   BOOKSET_DATE         DATE,
   BOOK_DATE            DATE,
   HAPPEN_DATE          DATE,
   IS_ACCOUNTED         VARCHAR(50),
   BOOK_ID              VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   INV_AIM              VARCHAR(50),
   constraint PK_BOK_DEAL_ACCOUNTING primary key (BUSI_NO, BOOKSET_ID, BUSI_TABLE)
)
partition by list
 (BOOKSET_ID)
 (partition BOOKSET_ID_DEFAULT
 values (DEFAULT));
comment on table BOK_DEAL_ACCOUNTING is
'入账信息表';
comment on column BOK_DEAL_ACCOUNTING.BOOKSET_ID is'账套代码';
comment on column BOK_DEAL_ACCOUNTING.BUSI_NO is'业务编号';
comment on column BOK_DEAL_ACCOUNTING.BUSI_TABLE is'业务表';
comment on column BOK_DEAL_ACCOUNTING.BOOKSET_DATE is'录入日';
comment on column BOK_DEAL_ACCOUNTING.BOOK_DATE is'入账日';
comment on column BOK_DEAL_ACCOUNTING.HAPPEN_DATE is'实际发生日';
comment on column BOK_DEAL_ACCOUNTING.IS_ACCOUNTED is'是否已入账';
comment on column BOK_DEAL_ACCOUNTING.BOOK_ID is'入账凭证编号';
comment on column BOK_DEAL_ACCOUNTING.CREATE_USER is'创建人';
comment on column BOK_DEAL_ACCOUNTING.CREATE_DEPT is'创建部门';
comment on column BOK_DEAL_ACCOUNTING.CREATE_TIME is'创建时间';
comment on column BOK_DEAL_ACCOUNTING.UPDATE_USER is'更新人';
comment on column BOK_DEAL_ACCOUNTING.UPDATE_TIME is'更新时间';
comment on column BOK_DEAL_ACCOUNTING.INV_AIM is'投资目的';
/*==============================================================*/
/* Table: BOK_DEAL_ACCOUNTING_DETAIL                            */
/*==============================================================*/
create table BOK_DEAL_ACCOUNTING_DETAIL 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   BUSI_NO              VARCHAR(32)          not null,
   BUSI_TABLE           VARCHAR(50)          not null,
   BUSI_TYPE            VARCHAR(50),
   BOK_DETAIL_ID        VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_DEAL_ACCOUNTING_DETAIL primary key (BUSI_NO, BOOKSET_ID, BUSI_TABLE)
)
partition by list
 (BOOKSET_ID)
 (partition BOOKSET_ID_DEFAULT
 values (DEFAULT));
comment on table BOK_DEAL_ACCOUNTING_DETAIL is
'入账账务明细表';
comment on column BOK_DEAL_ACCOUNTING_DETAIL.BOOKSET_ID is'账套代码';
comment on column BOK_DEAL_ACCOUNTING_DETAIL.BUSI_NO is'业务编号';
comment on column BOK_DEAL_ACCOUNTING_DETAIL.BUSI_TABLE is'业务表';
comment on column BOK_DEAL_ACCOUNTING_DETAIL.BUSI_TYPE is'业务类型';
comment on column BOK_DEAL_ACCOUNTING_DETAIL.BOK_DETAIL_ID is'账务明细代码';
comment on column BOK_DEAL_ACCOUNTING_DETAIL.CREATE_USER is'创建人';
comment on column BOK_DEAL_ACCOUNTING_DETAIL.CREATE_DEPT is'创建部门';
comment on column BOK_DEAL_ACCOUNTING_DETAIL.CREATE_TIME is'创建时间';
comment on column BOK_DEAL_ACCOUNTING_DETAIL.UPDATE_USER is'更新人';
comment on column BOK_DEAL_ACCOUNTING_DETAIL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_DETAIL_ID                                         */
/*==============================================================*/
create table BOK_DETAIL_ID 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   BOK_DETAIL_ID        VARCHAR(32)          not null,
   BUSI_ID              VARCHAR(32),
   BUSI_DETAIL_TYPE     VARCHAR(50),
   INV_AIM              VARCHAR(50),
   SEC_MANAGE_ACCT_ID   VARCHAR(32),
   TRADE_ID             VARCHAR(32),
   DETAIL_SUBJECT_NO_TAIL VARCHAR(32),
   DETAIL_SUBNAME_TAIL  VARCHAR(500),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_DETAIL_ID primary key (BOOKSET_ID, BOK_DETAIL_ID)
);
comment on table BOK_DETAIL_ID is
'账务明细代码表';
comment on column BOK_DETAIL_ID.BOOKSET_ID is'账套代码';
comment on column BOK_DETAIL_ID.BOK_DETAIL_ID is'账务明细代码';
comment on column BOK_DETAIL_ID.BUSI_ID is'业务明细代码';
comment on column BOK_DETAIL_ID.BUSI_DETAIL_TYPE is'业务明细类型';
comment on column BOK_DETAIL_ID.INV_AIM is'投资目的';
comment on column BOK_DETAIL_ID.SEC_MANAGE_ACCT_ID is'证券管理户代码';
comment on column BOK_DETAIL_ID.TRADE_ID is'交易编号';
comment on column BOK_DETAIL_ID.DETAIL_SUBJECT_NO_TAIL is'四级科目尾号';
comment on column BOK_DETAIL_ID.DETAIL_SUBNAME_TAIL is'四级科目尾号名称';
comment on column BOK_DETAIL_ID.CREATE_USER is'创建人';
comment on column BOK_DETAIL_ID.CREATE_DEPT is'创建部门';
comment on column BOK_DETAIL_ID.CREATE_TIME is'创建时间';
comment on column BOK_DETAIL_ID.UPDATE_USER is'更新人';
comment on column BOK_DETAIL_ID.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_FILE_GEN                                          */
/*==============================================================*/
create table BOK_FILE_GEN 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   GEN_DATE             DATE                 not null,
   FILE_TYPE            VARCHAR(50)          not null,
   FILE_NAME            VARCHAR(255),
   FILE_PATH            VARCHAR(255),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_FILE_GEN primary key (BOOKSET_ID, GEN_DATE, FILE_TYPE)
);
comment on table BOK_FILE_GEN is
'估值表生成信息';
comment on column BOK_FILE_GEN.BOOKSET_ID is'账套代码';
comment on column BOK_FILE_GEN.GEN_DATE is'生成日期';
comment on column BOK_FILE_GEN.FILE_TYPE is'文件类型';
comment on column BOK_FILE_GEN.FILE_NAME is'文件名';
comment on column BOK_FILE_GEN.FILE_PATH is'文件路径';
comment on column BOK_FILE_GEN.CREATE_USER is'创建人';
comment on column BOK_FILE_GEN.CREATE_DEPT is'创建部门';
comment on column BOK_FILE_GEN.CREATE_TIME is'创建时间';
comment on column BOK_FILE_GEN.UPDATE_USER is'更新人';
comment on column BOK_FILE_GEN.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_MANUAL_BOOKDETAIL                                 */
/*==============================================================*/
create table BOK_MANUAL_BOOKDETAIL 
(
   SEQNO                VARCHAR(20)          not null,
   MANUAL_TYPE          VARCHAR(10),
   BOOKSET_ID           VARCHAR(20),
   SUB_BOOKSET_ID       VARCHAR(20),
   TABLE_ID             VARCHAR(20),
   TRADE_ID             VARCHAR(20),
   BUSI_ID              VARCHAR(20),
   HAPPEN_DATE          DATE,
   BOOK_DATE            DATE,
   BOOKSET_DATE         DATE,
   DETAIL_SUBJECT_NO    VARCHAR(50),
   CD_FLAG              VARCHAR(10),
   O_CCY                CHAR(3),
   O_HAPPEN_AMT         DECIMAL(26, 2),
   B_CCY                CHAR(3),
   B_HAPPEN_AMT         DECIMAL(26, 2),
   HAPPEN_NUMBER        DECIMAL(26, 2),
   PAR_VALUE            DECIMAL(30,14),
   BOOK_ID              VARCHAR(20),
   BOOK_REMARK          NVARCHAR2(600),
   OFFSET_FLAG          VARCHAR(10),
   BOOK_ORDER           DECIMAL(10),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_TIME          TIMESTAMP            not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   UPDATE_USER          VARCHAR(20)          not null,
   constraint PK_BOK_MANUAL_BOOKDETAIL primary key (SEQNO)
)
partition by list
 (BOOKSET_ID)
 (partition BOOKSET_ID_DEFAULT
 values (DEFAULT));
comment on table BOK_MANUAL_BOOKDETAIL is
'手工账';
comment on column BOK_MANUAL_BOOKDETAIL.SEQNO is'流水';
comment on column BOK_MANUAL_BOOKDETAIL.MANUAL_TYPE is'手工账类型';
comment on column BOK_MANUAL_BOOKDETAIL.BOOKSET_ID is'账套代码';
comment on column BOK_MANUAL_BOOKDETAIL.SUB_BOOKSET_ID is'子账代码';
comment on column BOK_MANUAL_BOOKDETAIL.TABLE_ID is'场景代码';
comment on column BOK_MANUAL_BOOKDETAIL.TRADE_ID is'交易编号';
comment on column BOK_MANUAL_BOOKDETAIL.BUSI_ID is'业务明细代码';
comment on column BOK_MANUAL_BOOKDETAIL.HAPPEN_DATE is'发生日期';
comment on column BOK_MANUAL_BOOKDETAIL.BOOK_DATE is'入账日期';
comment on column BOK_MANUAL_BOOKDETAIL.BOOKSET_DATE is'账套日期';
comment on column BOK_MANUAL_BOOKDETAIL.DETAIL_SUBJECT_NO is'明细科目号';
comment on column BOK_MANUAL_BOOKDETAIL.CD_FLAG is'借贷方向';
comment on column BOK_MANUAL_BOOKDETAIL.O_CCY is'原币';
comment on column BOK_MANUAL_BOOKDETAIL.O_HAPPEN_AMT is'原币发生额';
comment on column BOK_MANUAL_BOOKDETAIL.B_CCY is'本位币';
comment on column BOK_MANUAL_BOOKDETAIL.B_HAPPEN_AMT is'本位币发生额';
comment on column BOK_MANUAL_BOOKDETAIL.HAPPEN_NUMBER is'发生数量';
comment on column BOK_MANUAL_BOOKDETAIL.PAR_VALUE is'单位面值';
comment on column BOK_MANUAL_BOOKDETAIL.BOOK_ID is'凭证编号';
comment on column BOK_MANUAL_BOOKDETAIL.BOOK_REMARK is'凭证说明';
comment on column BOK_MANUAL_BOOKDETAIL.OFFSET_FLAG is'冲正标识';
comment on column BOK_MANUAL_BOOKDETAIL.BOOK_ORDER is'处理序列';
comment on column BOK_MANUAL_BOOKDETAIL.CREATE_TIME is'创建时间';
comment on column BOK_MANUAL_BOOKDETAIL.UPDATE_TIME is'更新时间';
comment on column BOK_MANUAL_BOOKDETAIL.CREATE_DEPT is'创建部门';
comment on column BOK_MANUAL_BOOKDETAIL.CREATE_USER is'创建人';
comment on column BOK_MANUAL_BOOKDETAIL.UPDATE_USER is'更新人';
/*==============================================================*/
/* Table: BOK_MATCH_RLAT                                        */
/*==============================================================*/
create table BOK_MATCH_RLAT 
(
   MATCH_TYPE           VARCHAR(50)          not null,
   ACCT_STANDARD        VARCHAR(50),
   SOURCE               VARCHAR(200)         not null,
   TARGET               VARCHAR(200),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null
);
comment on table BOK_MATCH_RLAT is
'批处理匹配关系表';
comment on column BOK_MATCH_RLAT.MATCH_TYPE is'匹配类型';
comment on column BOK_MATCH_RLAT.ACCT_STANDARD is'会计准则';
comment on column BOK_MATCH_RLAT.SOURCE is'源';
comment on column BOK_MATCH_RLAT.TARGET is'目标';
comment on column BOK_MATCH_RLAT.CREATE_USER is'创建人';
comment on column BOK_MATCH_RLAT.CREATE_DEPT is'创建部门';
comment on column BOK_MATCH_RLAT.CREATE_TIME is'创建时间';
comment on column BOK_MATCH_RLAT.UPDATE_USER is'更新人';
comment on column BOK_MATCH_RLAT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_SCENECONFIG                                       */
/*==============================================================*/
create table BOK_SCENECONFIG 
(
   SCENE_NODE_ID        VARCHAR(100)         not null,
   FSCENE_NODEID        VARCHAR(100),
   BOOKSET_ID           VARCHAR(32)          not null,
   NODE_ID              VARCHAR(32)          not null,
   NODE_VALUE           VARCHAR(32)          not null,
   "LEVEL"              NUMERIC(10),
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SCENECONFIG primary key (SCENE_NODE_ID, BOOKSET_ID, NODE_ID, NODE_VALUE)
);
comment on table BOK_SCENECONFIG is
'场景分支配置';
comment on column BOK_SCENECONFIG.SCENE_NODE_ID is'场景分支节点';
comment on column BOK_SCENECONFIG.FSCENE_NODEID is'场景分支父节点';
comment on column BOK_SCENECONFIG.BOOKSET_ID is'账套代码';
comment on column BOK_SCENECONFIG.NODE_ID is'节点代码';
comment on column BOK_SCENECONFIG.NODE_VALUE is'节点内容';
comment on column BOK_SCENECONFIG."LEVEL" is'级别';
comment on column BOK_SCENECONFIG.REMARK is'备注';
comment on column BOK_SCENECONFIG.CREATE_USER is'创建人';
comment on column BOK_SCENECONFIG.CREATE_DEPT is'创建部门';
comment on column BOK_SCENECONFIG.CREATE_TIME is'创建时间';
comment on column BOK_SCENECONFIG.UPDATE_USER is'更新人';
comment on column BOK_SCENECONFIG.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_SCENESUBJECT                                      */
/*==============================================================*/
create table BOK_SCENESUBJECT 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   SCENE_NODE_ID        VARCHAR(100)         not null,
   SUBJECT_NO           VARCHAR(32)          not null,
   CD_FLAG              VARCHAR(50)          not null,
   FORM_ELE             VARCHAR(50),
   BOK_DETAIL_ID        VARCHAR(32),
   ACCOUNT_NET          VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SCENESUBJECT primary key (BOOKSET_ID, SCENE_NODE_ID, SUBJECT_NO, CD_FLAG)
);
comment on table BOK_SCENESUBJECT is
'场景科目关系';
comment on column BOK_SCENESUBJECT.BOOKSET_ID is'账套代码';
comment on column BOK_SCENESUBJECT.SCENE_NODE_ID is'场景分支节点';
comment on column BOK_SCENESUBJECT.SUBJECT_NO is'科目号';
comment on column BOK_SCENESUBJECT.CD_FLAG is'余额方向';
comment on column BOK_SCENESUBJECT.FORM_ELE is'金额元素';
comment on column BOK_SCENESUBJECT.BOK_DETAIL_ID is'账务明细元素';
comment on column BOK_SCENESUBJECT.ACCOUNT_NET is'记账网点';
comment on column BOK_SCENESUBJECT.CREATE_USER is'创建人';
comment on column BOK_SCENESUBJECT.CREATE_DEPT is'创建部门';
comment on column BOK_SCENESUBJECT.CREATE_TIME is'创建时间';
comment on column BOK_SCENESUBJECT.UPDATE_USER is'更新人';
comment on column BOK_SCENESUBJECT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_SCHEME_CONFIG                                     */
/*==============================================================*/
create table BOK_SCHEME_CONFIG 
(
   CONFIG_ID            VARCHAR(32)          not null,
   SCHEME_TYPE          VARCHAR(50),
   SCHEME_TYPE_2        VARCHAR(50),
   CONFIG_NAME          VARCHAR(200),
   REMARK               VARCHAR(1000),
   DICT_CODE            VARCHAR(32)          not null,
   CONFIG_TYPE          VARCHAR(50)          not null,
   SORT_NO              VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SCHEME_CONFIG primary key (CONFIG_ID)
);
comment on table BOK_SCHEME_CONFIG is
'方案配置';
comment on column BOK_SCHEME_CONFIG.CONFIG_ID is'配置项代码';
comment on column BOK_SCHEME_CONFIG.SCHEME_TYPE is'方案类型';
comment on column BOK_SCHEME_CONFIG.SCHEME_TYPE_2 is'方案二级类型';
comment on column BOK_SCHEME_CONFIG.CONFIG_NAME is'配置项名称';
comment on column BOK_SCHEME_CONFIG.REMARK is'配置项说明';
comment on column BOK_SCHEME_CONFIG.DICT_CODE is'取值数据字典';
comment on column BOK_SCHEME_CONFIG.CONFIG_TYPE is'配置项类型';
comment on column BOK_SCHEME_CONFIG.SORT_NO is'优先级';
comment on column BOK_SCHEME_CONFIG.CREATE_USER is'创建人';
comment on column BOK_SCHEME_CONFIG.CREATE_DEPT is'创建部门';
comment on column BOK_SCHEME_CONFIG.CREATE_TIME is'创建时间';
comment on column BOK_SCHEME_CONFIG.UPDATE_USER is'更新人';
comment on column BOK_SCHEME_CONFIG.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_SCHEME_CONFIG_VAL                                 */
/*==============================================================*/
create table BOK_SCHEME_CONFIG_VAL 
(
   SEQ_ID               VARCHAR(32)          not null,
   SCHEME_ID            VARCHAR(32)          not null,
   CONFIG_ID            VARCHAR(32)          not null,
   CONFIG_VALUE         VARCHAR(50),
   REMARK               VARCHAR(1000),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SCHEME_CONFIG_VAL primary key (CONFIG_ID)
);
comment on table BOK_SCHEME_CONFIG_VAL is
'方案配置值信息表';
comment on column BOK_SCHEME_CONFIG_VAL.SEQ_ID is'流水';
comment on column BOK_SCHEME_CONFIG_VAL.SCHEME_ID is'方案代码';
comment on column BOK_SCHEME_CONFIG_VAL.CONFIG_ID is'配置项代码';
comment on column BOK_SCHEME_CONFIG_VAL.CONFIG_VALUE is'配置项值';
comment on column BOK_SCHEME_CONFIG_VAL.REMARK is'说明';
comment on column BOK_SCHEME_CONFIG_VAL.CREATE_USER is'创建人';
comment on column BOK_SCHEME_CONFIG_VAL.CREATE_DEPT is'创建部门';
comment on column BOK_SCHEME_CONFIG_VAL.CREATE_TIME is'创建时间';
comment on column BOK_SCHEME_CONFIG_VAL.UPDATE_USER is'更新人';
comment on column BOK_SCHEME_CONFIG_VAL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_SCHEME_INFO                                       */
/*==============================================================*/
create table BOK_SCHEME_INFO 
(
   SCH_ID               VARCHAR(32)          not null,
   SCHEME_TYPE          VARCHAR(50)          not null,
   SCH_NAME             VARCHAR2(200)        not null,
   REMARK               VARCHAR(1000),
   IS_DEFAULT           VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SCHEME_INFO primary key (SCH_NAME)
);
comment on table BOK_SCHEME_INFO is
'账套税务配置';
comment on column BOK_SCHEME_INFO.SCH_ID is'方案代码';
comment on column BOK_SCHEME_INFO.SCHEME_TYPE is'方案类型';
comment on column BOK_SCHEME_INFO.SCH_NAME is'方案名称';
comment on column BOK_SCHEME_INFO.REMARK is'方案描述';
comment on column BOK_SCHEME_INFO.IS_DEFAULT is'是否默认';
comment on column BOK_SCHEME_INFO.CREATE_USER is'创建人';
comment on column BOK_SCHEME_INFO.CREATE_DEPT is'创建部门';
comment on column BOK_SCHEME_INFO.CREATE_TIME is'创建时间';
comment on column BOK_SCHEME_INFO.UPDATE_USER is'更新人';
comment on column BOK_SCHEME_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_STAT_BOK_POSITION                                 */
/*==============================================================*/
create table BOK_STAT_BOK_POSITION 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   HAPPEN_DATE          DATE                 not null,
   BOOK_DATE            DATE                 not null,
   BOOKSET_DATE         DATE,
   PROFIT_TYPE          VARCHAR(50),
   PROFIT_AMT           NUMERIC(30, 2),
   CAPITAL              NUMERIC(30, 2),
   NET_ASSET_VALUE_BEF  NUMERIC(30, 2),
   NET_ASSET_VALUE      NUMERIC(30, 2),
   NET_UNIT_VALUE_BEF   NUMERIC(30, 14),
   NET_UNIT_VALUE       NUMERIC(30, 14),
   P_YIELD_BEF          NUMERIC(30, 14),
   P_YIELD              NUMERIC(30, 14),
   TDY_YIELD_BEF        NUMERIC(30, 14),
   TDY_YIELD            NUMERIC(30, 14),
   YIELD_TERM_BEF       NUMERIC(30, 14),
   YIELD_TERM           NUMERIC(30, 14),
   YIELD_7_BEF          NUMERIC(30, 14),
   YIELD_7              NUMERIC(30, 14),
   TOT_BOUNS_AMT        NUMERIC(30, 2),
   TDY_BOUNS_AMT        NUMERIC(30, 2),
   TOT_BOUNS_NET        NUMERIC(30, 14),
   TDY_BOUNS_NET        NUMERIC(30, 14),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_STAT_BOK_POSITION primary key (BOOKSET_ID, HAPPEN_DATE, BOOK_DATE)
);
comment on table BOK_STAT_BOK_POSITION is
'账务汇总表（账套）';
comment on column BOK_STAT_BOK_POSITION.BOOKSET_ID is'账套代码';
comment on column BOK_STAT_BOK_POSITION.HAPPEN_DATE is'发生日期';
comment on column BOK_STAT_BOK_POSITION.BOOK_DATE is'入账日期';
comment on column BOK_STAT_BOK_POSITION.BOOKSET_DATE is'账套日期';
comment on column BOK_STAT_BOK_POSITION.PROFIT_TYPE is'收益类型';
comment on column BOK_STAT_BOK_POSITION.PROFIT_AMT is'应付利润';
comment on column BOK_STAT_BOK_POSITION.CAPITAL is'实收资本';
comment on column BOK_STAT_BOK_POSITION.NET_ASSET_VALUE_BEF is'费前资产净值';
comment on column BOK_STAT_BOK_POSITION.NET_ASSET_VALUE is'资产净值';
comment on column BOK_STAT_BOK_POSITION.NET_UNIT_VALUE_BEF is'费前单位净值';
comment on column BOK_STAT_BOK_POSITION.NET_UNIT_VALUE is'单位净值';
comment on column BOK_STAT_BOK_POSITION.P_YIELD_BEF is'万份收益_费前';
comment on column BOK_STAT_BOK_POSITION.P_YIELD is'万份收益';
comment on column BOK_STAT_BOK_POSITION.TDY_YIELD_BEF is'当日年化收益率_费前';
comment on column BOK_STAT_BOK_POSITION.TDY_YIELD is'当日年化收益率';
comment on column BOK_STAT_BOK_POSITION.YIELD_TERM_BEF is'周期年化收益率_费前';
comment on column BOK_STAT_BOK_POSITION.YIELD_TERM is'周期年化收益率';
comment on column BOK_STAT_BOK_POSITION.YIELD_7_BEF is'当日7日年化收益率_费前';
comment on column BOK_STAT_BOK_POSITION.YIELD_7 is'当日7日年化收益率';
comment on column BOK_STAT_BOK_POSITION.TOT_BOUNS_AMT is'累计分红金额';
comment on column BOK_STAT_BOK_POSITION.TDY_BOUNS_AMT is'当日分红金额';
comment on column BOK_STAT_BOK_POSITION.TOT_BOUNS_NET is'累计分红净值';
comment on column BOK_STAT_BOK_POSITION.TDY_BOUNS_NET is'当日分红净值';
comment on column BOK_STAT_BOK_POSITION.CREATE_USER is'创建人';
comment on column BOK_STAT_BOK_POSITION.CREATE_DEPT is'创建部门';
comment on column BOK_STAT_BOK_POSITION.CREATE_TIME is'创建时间';
comment on column BOK_STAT_BOK_POSITION.UPDATE_USER is'更新人';
comment on column BOK_STAT_BOK_POSITION.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_SUBJECT                                           */
/*==============================================================*/
create table BOK_SUBJECT 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   SUBJECT_NO           VARCHAR(32)          not null,
   SUBJECT_NAME         VARCHAR(200)         not null,
   FSUBJECT_ID          VARCHAR(32),
   BAL_FLAG             VARCHAR(50),
   SUBJECT_TYPE         VARCHAR(50)          not null,
   SUBJECT_LEVEL        NUMERIC(10)          not null,
   ACC_QUA_FLAG         VARCHAR(50),
   ACC_INT_FLAG         VARCHAR(50),
   OVERDRAWN_FLAG       VARCHAR(50),
   GEN_DETSUB_FLAG      VARCHAR(50),
   BASE_SUBJECT_NATURE  VARCHAR(100),
   INV_AIM              VARCHAR(50),
   SEC_MANAGE_ACCT_ID   VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SUBJECT primary key (BOOKSET_ID, SUBJECT_NO)
)
partition by list
 (BOOKSET_ID)
 (partition BOOKSET_ID_DEFAULT
 values (DEFAULT));
comment on table BOK_SUBJECT is
'账套科目';
comment on column BOK_SUBJECT.BOOKSET_ID is'账套代码';
comment on column BOK_SUBJECT.SUBJECT_NO is'科目号';
comment on column BOK_SUBJECT.SUBJECT_NAME is'科目名称';
comment on column BOK_SUBJECT.FSUBJECT_ID is'上级科目号';
comment on column BOK_SUBJECT.BAL_FLAG is'余额方向';
comment on column BOK_SUBJECT.SUBJECT_TYPE is'科目类型';
comment on column BOK_SUBJECT.SUBJECT_LEVEL is'科目级别';
comment on column BOK_SUBJECT.ACC_QUA_FLAG is'是否核算数量';
comment on column BOK_SUBJECT.ACC_INT_FLAG is'是否计息';
comment on column BOK_SUBJECT.OVERDRAWN_FLAG is'是否允许透支';
comment on column BOK_SUBJECT.GEN_DETSUB_FLAG is'是否生成四级科目';
comment on column BOK_SUBJECT.BASE_SUBJECT_NATURE is'基础科目性质';
comment on column BOK_SUBJECT.INV_AIM is'投资目的';
comment on column BOK_SUBJECT.SEC_MANAGE_ACCT_ID is'证券管理户代码';
comment on column BOK_SUBJECT.CREATE_USER is'创建人';
comment on column BOK_SUBJECT.CREATE_DEPT is'创建部门';
comment on column BOK_SUBJECT.CREATE_TIME is'创建时间';
comment on column BOK_SUBJECT.UPDATE_USER is'更新人';
comment on column BOK_SUBJECT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_SUBJECT_DETAIL                                    */
/*==============================================================*/
create table BOK_SUBJECT_DETAIL 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   BASE_SUBJECT_NATURE  VARCHAR(100)         not null,
   BOK_DETAIL_ID        VARCHAR(32)          not null,
   SUBJECT_NO           VARCHAR(32),
   DETAIL_SUBJECT_NO    VARCHAR(300),
   DETAIL_SUBJECT_NO_TAIL VARCHAR(300),
   ACC_INT_FLAG         VARCHAR(50),
   OVERDRAWN_FLAG       VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SUBJECT_DETAIL primary key (BOOKSET_ID, BASE_SUBJECT_NATURE, BOK_DETAIL_ID)
)
partition by list
 (BOOKSET_ID)
 (partition BOOKSET_ID_DEFAULT
 values (DEFAULT));
comment on table BOK_SUBJECT_DETAIL is
'明细科目';
comment on column BOK_SUBJECT_DETAIL.BOOKSET_ID is'账套代码';
comment on column BOK_SUBJECT_DETAIL.BASE_SUBJECT_NATURE is'基础科目性质';
comment on column BOK_SUBJECT_DETAIL.BOK_DETAIL_ID is'账务明细代码';
comment on column BOK_SUBJECT_DETAIL.SUBJECT_NO is'科目号';
comment on column BOK_SUBJECT_DETAIL.DETAIL_SUBJECT_NO is'四级科目号';
comment on column BOK_SUBJECT_DETAIL.DETAIL_SUBJECT_NO_TAIL is'四级科目号尾号';
comment on column BOK_SUBJECT_DETAIL.ACC_INT_FLAG is'是否计息';
comment on column BOK_SUBJECT_DETAIL.OVERDRAWN_FLAG is'是否允许透支';
comment on column BOK_SUBJECT_DETAIL.CREATE_USER is'创建人';
comment on column BOK_SUBJECT_DETAIL.CREATE_DEPT is'创建部门';
comment on column BOK_SUBJECT_DETAIL.CREATE_TIME is'创建时间';
comment on column BOK_SUBJECT_DETAIL.UPDATE_USER is'更新人';
comment on column BOK_SUBJECT_DETAIL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_SUBJECT_NATURE_GROUP                              */
/*==============================================================*/
create table BOK_SUBJECT_NATURE_GROUP 
(
   GROUP_ID             VARCHAR(32)          not null,
   ACCT_STANDARD_ID     VARCHAR(32)          not null,
   BASE_SUBJECT_NATURE  VARCHAR(100)         not null,
   USE_REMARK           VARCHAR(1000),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SUBJECT_NATURE_GROUP primary key (GROUP_ID, ACCT_STANDARD_ID, BASE_SUBJECT_NATURE)
);
comment on table BOK_SUBJECT_NATURE_GROUP is
'科目性质分组表';
comment on column BOK_SUBJECT_NATURE_GROUP.GROUP_ID is'分组代码';
comment on column BOK_SUBJECT_NATURE_GROUP.ACCT_STANDARD_ID is'会计准则代码';
comment on column BOK_SUBJECT_NATURE_GROUP.BASE_SUBJECT_NATURE is'基础科目性质';
comment on column BOK_SUBJECT_NATURE_GROUP.USE_REMARK is'使用说明';
comment on column BOK_SUBJECT_NATURE_GROUP.CREATE_USER is'创建人';
comment on column BOK_SUBJECT_NATURE_GROUP.CREATE_DEPT is'创建部门';
comment on column BOK_SUBJECT_NATURE_GROUP.CREATE_TIME is'创建时间';
comment on column BOK_SUBJECT_NATURE_GROUP.UPDATE_USER is'更新人';
comment on column BOK_SUBJECT_NATURE_GROUP.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_TABLEDICT                                         */
/*==============================================================*/
create table BOK_TABLEDICT 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   DICT_ID              VARCHAR(32)          not null,
   DICT_CODE            VARCHAR(32)          not null,
   TABLE_ID             VARCHAR(32)          not null,
   DICT_NAME            VARCHAR(200),
   DICT_TYPE            VARCHAR(50),
   DICT_SOURCE          VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_TABLEDICT primary key (BOOKSET_ID, DICT_ID)
);
comment on table BOK_TABLEDICT is
'会计元素';
comment on column BOK_TABLEDICT.BOOKSET_ID is'账套代码';
comment on column BOK_TABLEDICT.DICT_ID is'会计元素代码';
comment on column BOK_TABLEDICT.DICT_CODE is'会计元素编码';
comment on column BOK_TABLEDICT.TABLE_ID is'会计场景代码';
comment on column BOK_TABLEDICT.DICT_NAME is'会计元素名称';
comment on column BOK_TABLEDICT.DICT_TYPE is'元素数据类型';
comment on column BOK_TABLEDICT.DICT_SOURCE is'元素数据来源';
comment on column BOK_TABLEDICT.CREATE_USER is'创建人';
comment on column BOK_TABLEDICT.CREATE_DEPT is'创建部门';
comment on column BOK_TABLEDICT.CREATE_TIME is'创建时间';
comment on column BOK_TABLEDICT.UPDATE_USER is'更新人';
comment on column BOK_TABLEDICT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_TABLELIST                                         */
/*==============================================================*/
create table BOK_TABLELIST 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   TABLE_ID             VARCHAR(32)          not null,
   TABLE_NAME           VARCHAR(200),
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_TABLELIST primary key (BOOKSET_ID, TABLE_ID)
);
comment on table BOK_TABLELIST is
'会计场景';
comment on column BOK_TABLELIST.BOOKSET_ID is'账套代码';
comment on column BOK_TABLELIST.TABLE_ID is'会计场景代码';
comment on column BOK_TABLELIST.TABLE_NAME is'会计场景名称';
comment on column BOK_TABLELIST.REMARK is'备注';
comment on column BOK_TABLELIST.CREATE_USER is'创建人';
comment on column BOK_TABLELIST.CREATE_DEPT is'创建部门';
comment on column BOK_TABLELIST.CREATE_TIME is'创建时间';
comment on column BOK_TABLELIST.UPDATE_USER is'更新人';
comment on column BOK_TABLELIST.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_TRADE_ORDER                                       */
/*==============================================================*/
create table BOK_TRADE_ORDER 
(
   ACCT_STANDARD        VARCHAR(50)          not null,
   BUSI_TYPE            VARCHAR(50)          not null,
   PS                   VARCHAR(50)          not null,
   PS_ORDER             NUMERIC(10),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_TRADE_ORDER primary key (ACCT_STANDARD, BUSI_TYPE, PS)
);
comment on table BOK_TRADE_ORDER is
'批处理交易处理顺序';
comment on column BOK_TRADE_ORDER.ACCT_STANDARD is'会计准则';
comment on column BOK_TRADE_ORDER.BUSI_TYPE is'业务类型';
comment on column BOK_TRADE_ORDER.PS is'交易方向';
comment on column BOK_TRADE_ORDER.PS_ORDER is'处理顺序';
comment on column BOK_TRADE_ORDER.CREATE_USER is'创建人';
comment on column BOK_TRADE_ORDER.CREATE_DEPT is'创建部门';
comment on column BOK_TRADE_ORDER.CREATE_TIME is'创建时间';
comment on column BOK_TRADE_ORDER.UPDATE_USER is'更新人';
comment on column BOK_TRADE_ORDER.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VALBAL_TABLE_LOG                                  */
/*==============================================================*/
create table BOK_VALBAL_TABLE_LOG 
(
   FINPROD_ID           VARCHAR(32)          not null,
   VAL_DATE             DATE                 not null,
   VAL_BAL_FLAG         VARCHAR(50)          not null,
   BUILD_STATUS         VARCHAR(50),
   ERROR_TYPE           VARCHAR(200),
   ERROR_INFO           VARCHAR(1000),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VALBAL_TABLE_LOG primary key (FINPROD_ID, VAL_DATE, VAL_BAL_FLAG)
);
comment on table BOK_VALBAL_TABLE_LOG is
'估值表余额表生成日志表
';
comment on column BOK_VALBAL_TABLE_LOG.FINPROD_ID is'产品代码';
comment on column BOK_VALBAL_TABLE_LOG.VAL_DATE is'估值日期';
comment on column BOK_VALBAL_TABLE_LOG.VAL_BAL_FLAG is'估值表余额表标志';
comment on column BOK_VALBAL_TABLE_LOG.BUILD_STATUS is'生成状态';
comment on column BOK_VALBAL_TABLE_LOG.ERROR_TYPE is'错误类型';
comment on column BOK_VALBAL_TABLE_LOG.ERROR_INFO is'错误信息';
comment on column BOK_VALBAL_TABLE_LOG.CREATE_USER is'创建人';
comment on column BOK_VALBAL_TABLE_LOG.CREATE_DEPT is'创建部门';
comment on column BOK_VALBAL_TABLE_LOG.CREATE_TIME is'创建时间';
comment on column BOK_VALBAL_TABLE_LOG.UPDATE_USER is'更新人';
comment on column BOK_VALBAL_TABLE_LOG.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VALBAL_TABLE_STATUS                               */
/*==============================================================*/
create table BOK_VALBAL_TABLE_STATUS 
(
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_NAME         VARCHAR(500),
   VAL_DATE             DATE                 not null,
   VAL_BAL_FLAG         VARCHAR(50)          not null,
   CAPITAL              NUMERIC(30, 2),
   ASSET_VALUE          NUMERIC(30, 2),
   TOTAL_NET_VALUE      NUMBER(30,14),
   SEVEN_DAY_RATE       NUMBER(30,14),
   PAY_PROFIT           NUMBER(30,14),
   FILE_ID              VARCHAR(500),
   NET_UNIT_VALUE       NUMERIC(30, 14),
   CHECK_STATUS         VARCHAR(50),
   CHECK_RESULT         VARCHAR(50),
   CONFIRM_STATUS       VARCHAR(50),
   NOTICE_STATUS        VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VALBAL_TABLE_STATUS primary key (FINPROD_ID, VAL_DATE, VAL_BAL_FLAG)
)
partition by list
 (VAL_DATE)
 (partition BOOKSET_ID_DEFAULT
 values (DEFAULT));
comment on table BOK_VALBAL_TABLE_STATUS is
'估值表余额表状态表
';
comment on column BOK_VALBAL_TABLE_STATUS.FINPROD_ID is'产品代码';
comment on column BOK_VALBAL_TABLE_STATUS.FINPROD_NAME is'产品名称';
comment on column BOK_VALBAL_TABLE_STATUS.VAL_DATE is'估值日期';
comment on column BOK_VALBAL_TABLE_STATUS.VAL_BAL_FLAG is'估值表余额表标志';
comment on column BOK_VALBAL_TABLE_STATUS.CAPITAL is'理财份额';
comment on column BOK_VALBAL_TABLE_STATUS.ASSET_VALUE is'产品总净值/本日收益';
comment on column BOK_VALBAL_TABLE_STATUS.TOTAL_NET_VALUE is'累计净值';
comment on column BOK_VALBAL_TABLE_STATUS.SEVEN_DAY_RATE is'七日年化收益率';
comment on column BOK_VALBAL_TABLE_STATUS.PAY_PROFIT is'应付利润';
comment on column BOK_VALBAL_TABLE_STATUS.FILE_ID is'附件uuid';
comment on column BOK_VALBAL_TABLE_STATUS.NET_UNIT_VALUE is'单位净值/万份收益/利率';
comment on column BOK_VALBAL_TABLE_STATUS.CHECK_STATUS is'对账状态';
comment on column BOK_VALBAL_TABLE_STATUS.CHECK_RESULT is'对账结果';
comment on column BOK_VALBAL_TABLE_STATUS.CONFIRM_STATUS is'确认状态';
comment on column BOK_VALBAL_TABLE_STATUS.NOTICE_STATUS is'下发状态';
comment on column BOK_VALBAL_TABLE_STATUS.CREATE_USER is'创建人';
comment on column BOK_VALBAL_TABLE_STATUS.CREATE_DEPT is'创建部门';
comment on column BOK_VALBAL_TABLE_STATUS.CREATE_TIME is'创建时间';
comment on column BOK_VALBAL_TABLE_STATUS.UPDATE_USER is'更新人';
comment on column BOK_VALBAL_TABLE_STATUS.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VAL_RULE                                          */
/*==============================================================*/
create table BOK_VAL_RULE 
(
   RULE_ID              VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32),
   RULE_NAME            VARCHAR(100)         not null,
   RULE_DESCRIPTION     VARCHAR(200),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VAL_RULE primary key (RULE_ID)
);
comment on table BOK_VAL_RULE is
'估值规则';
comment on column BOK_VAL_RULE.RULE_ID is'规则代码';
comment on column BOK_VAL_RULE.BOOKSET_ID is'账套代码';
comment on column BOK_VAL_RULE.RULE_NAME is'规则名称';
comment on column BOK_VAL_RULE.RULE_DESCRIPTION is'规则描述';
comment on column BOK_VAL_RULE.CREATE_USER is'创建人';
comment on column BOK_VAL_RULE.CREATE_DEPT is'创建部门';
comment on column BOK_VAL_RULE.CREATE_TIME is'创建时间';
comment on column BOK_VAL_RULE.UPDATE_USER is'更新人';
comment on column BOK_VAL_RULE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VAL_RULE_ASSETDETAIL                              */
/*==============================================================*/
create table BOK_VAL_RULE_ASSETDETAIL 
(
   RULE_DETAIL_ID       VARCHAR(32)          not null,
   RULE_ID              VARCHAR(32)          not null,
   SEC_ID               VARCHAR(50),
   INV_AIM              VARCHAR(50),
   VDATE                DATE,
   MDATE                DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VAL_RULE_ASSETDETAIL primary key (RULE_DETAIL_ID)
);
comment on table BOK_VAL_RULE_ASSETDETAIL is
'资产估值规则明细表';
comment on column BOK_VAL_RULE_ASSETDETAIL.RULE_DETAIL_ID is'规则明细代码';
comment on column BOK_VAL_RULE_ASSETDETAIL.RULE_ID is'规则代码';
comment on column BOK_VAL_RULE_ASSETDETAIL.SEC_ID is'资产代码';
comment on column BOK_VAL_RULE_ASSETDETAIL.INV_AIM is'投资目的';
comment on column BOK_VAL_RULE_ASSETDETAIL.VDATE is'开始日';
comment on column BOK_VAL_RULE_ASSETDETAIL.MDATE is'结束日';
comment on column BOK_VAL_RULE_ASSETDETAIL.CREATE_USER is'创建人';
comment on column BOK_VAL_RULE_ASSETDETAIL.CREATE_DEPT is'创建部门';
comment on column BOK_VAL_RULE_ASSETDETAIL.CREATE_TIME is'创建时间';
comment on column BOK_VAL_RULE_ASSETDETAIL.UPDATE_USER is'更新人';
comment on column BOK_VAL_RULE_ASSETDETAIL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VAL_RULE_TYPEDETAIL                               */
/*==============================================================*/
create table BOK_VAL_RULE_TYPEDETAIL 
(
   RULE_DETAIL_ID       VARCHAR(32)          not null,
   RULE_ID              VARCHAR(32)          not null,
   SEC_TYPE             VARCHAR(50),
   SEC_DETAIL_TYPE      VARCHAR(50),
   INV_AIM              VARCHAR(50),
   VDATE                DATE,
   MDATE                DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VAL_RULE_TYPEDETAIL primary key (RULE_DETAIL_ID)
);
comment on table BOK_VAL_RULE_TYPEDETAIL is
'分类估值规则明细表';
comment on column BOK_VAL_RULE_TYPEDETAIL.RULE_DETAIL_ID is'规则明细代码';
comment on column BOK_VAL_RULE_TYPEDETAIL.RULE_ID is'规则代码';
comment on column BOK_VAL_RULE_TYPEDETAIL.SEC_TYPE is'证券品种';
comment on column BOK_VAL_RULE_TYPEDETAIL.SEC_DETAIL_TYPE is'证券细分类';
comment on column BOK_VAL_RULE_TYPEDETAIL.INV_AIM is'投资目的';
comment on column BOK_VAL_RULE_TYPEDETAIL.VDATE is'开始日';
comment on column BOK_VAL_RULE_TYPEDETAIL.MDATE is'结束日';
comment on column BOK_VAL_RULE_TYPEDETAIL.CREATE_USER is'创建人';
comment on column BOK_VAL_RULE_TYPEDETAIL.CREATE_DEPT is'创建部门';
comment on column BOK_VAL_RULE_TYPEDETAIL.CREATE_TIME is'创建时间';
comment on column BOK_VAL_RULE_TYPEDETAIL.UPDATE_USER is'更新人';
comment on column BOK_VAL_RULE_TYPEDETAIL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VAL_SCHEME                                        */
/*==============================================================*/
create table BOK_VAL_SCHEME 
(
   SCHEME_ID            VARCHAR(32)          not null,
   SCHEME_NAME          VARCHAR(100)         not null,
   SCHEME_DESCRIPTION   VARCHAR(200),
   ISDEFAULT            VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VAL_SCHEME primary key (SCHEME_ID)
);
comment on table BOK_VAL_SCHEME is
'估值方案';
comment on column BOK_VAL_SCHEME.SCHEME_ID is'方案代码';
comment on column BOK_VAL_SCHEME.SCHEME_NAME is'方案名称';
comment on column BOK_VAL_SCHEME.SCHEME_DESCRIPTION is'方案描述';
comment on column BOK_VAL_SCHEME.ISDEFAULT is'是否默认方案';
comment on column BOK_VAL_SCHEME.CREATE_USER is'创建人';
comment on column BOK_VAL_SCHEME.CREATE_DEPT is'创建部门';
comment on column BOK_VAL_SCHEME.CREATE_TIME is'创建时间';
comment on column BOK_VAL_SCHEME.UPDATE_USER is'更新人';
comment on column BOK_VAL_SCHEME.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VAL_SCHEME_DETAIL                                 */
/*==============================================================*/
create table BOK_VAL_SCHEME_DETAIL 
(
   SCHEME_DETAIL_ID     VARCHAR(32)          not null,
   SCHEME_ID            VARCHAR(32)          not null,
   FINPROD_ID           VARCHAR(50),
   FINPROD_NAME         VARCHAR(50),
   TRADE_MARKET         VARCHAR(50),
   SEC_TYPE             VARCHAR(50),
   SEC_DETAIL_TYPE      VARCHAR(50),
   INV_AIM              VARCHAR(50),
   VALUATION_METHOD     VARCHAR(50),
   VALUATION_PRICE      VARCHAR(50),
   VALUATION_PRICE_2    VARCHAR(50),
   QUOTA_DIGIT          NUMERIC(10),
   VALUATION_DATE       VARCHAR(50),
   VDATE                DATE,
   MDATE                DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VAL_SCHEME_DETAIL primary key (SCHEME_DETAIL_ID)
);
comment on table BOK_VAL_SCHEME_DETAIL is
'估值方案明细';
comment on column BOK_VAL_SCHEME_DETAIL.SCHEME_DETAIL_ID is'方案明细代码';
comment on column BOK_VAL_SCHEME_DETAIL.SCHEME_ID is'方案代码';
comment on column BOK_VAL_SCHEME_DETAIL.FINPROD_ID is'资产代码';
comment on column BOK_VAL_SCHEME_DETAIL.FINPROD_NAME is'资产名称';
comment on column BOK_VAL_SCHEME_DETAIL.TRADE_MARKET is'交易市场';
comment on column BOK_VAL_SCHEME_DETAIL.SEC_TYPE is'证券品种';
comment on column BOK_VAL_SCHEME_DETAIL.SEC_DETAIL_TYPE is'证券细分类';
comment on column BOK_VAL_SCHEME_DETAIL.INV_AIM is'投资目的';
comment on column BOK_VAL_SCHEME_DETAIL.VALUATION_METHOD is'估值方法';
comment on column BOK_VAL_SCHEME_DETAIL.VALUATION_PRICE is'估值市价';
comment on column BOK_VAL_SCHEME_DETAIL.VALUATION_PRICE_2 is'估值第二市价';
comment on column BOK_VAL_SCHEME_DETAIL.QUOTA_DIGIT is'行情位数';
comment on column BOK_VAL_SCHEME_DETAIL.VALUATION_DATE is'估值日期';
comment on column BOK_VAL_SCHEME_DETAIL.VDATE is'开始日';
comment on column BOK_VAL_SCHEME_DETAIL.MDATE is'结束日';
comment on column BOK_VAL_SCHEME_DETAIL.CREATE_USER is'创建人';
comment on column BOK_VAL_SCHEME_DETAIL.CREATE_DEPT is'创建部门';
comment on column BOK_VAL_SCHEME_DETAIL.CREATE_TIME is'创建时间';
comment on column BOK_VAL_SCHEME_DETAIL.UPDATE_USER is'更新人';
comment on column BOK_VAL_SCHEME_DETAIL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VAL_SCHEME_DETAIL_SPECIAL                         */
/*==============================================================*/
create table BOK_VAL_SCHEME_DETAIL_SPECIAL 
(
   SCHEME_DETAIL_ID     VARCHAR(32)          not null,
   SCHEME_ID            VARCHAR(32)          not null,
   FINPROD_ID           VARCHAR(50),
   FINPROD_NAME         VARCHAR(50),
   TRADE_MARKET         VARCHAR(50),
   SEC_TYPE             VARCHAR(50),
   SEC_DETAIL_TYPE      VARCHAR(50),
   INV_AIM              VARCHAR(50),
   VALUATION_METHOD     VARCHAR(50),
   VALUATION_PRICE      VARCHAR(50),
   VALUATION_PRICE_2    VARCHAR(50),
   QUOTA_DIGIT          NUMERIC(10),
   VALUATION_DATE       VARCHAR(50),
   VDATE                DATE,
   MDATE                DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VAL_SCHEME_DETAIL_SPECI primary key (SCHEME_DETAIL_ID)
);
comment on table BOK_VAL_SCHEME_DETAIL_SPECIAL is
'特殊资产估值方案明细';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.SCHEME_DETAIL_ID is'方案明细代码';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.SCHEME_ID is'方案代码';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.FINPROD_ID is'资产代码';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.FINPROD_NAME is'资产名称';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.TRADE_MARKET is'交易市场';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.SEC_TYPE is'证券品种';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.SEC_DETAIL_TYPE is'证券细分类';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.INV_AIM is'投资目的';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.VALUATION_METHOD is'估值方法';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.VALUATION_PRICE is'估值市价';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.VALUATION_PRICE_2 is'估值第二市价';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.QUOTA_DIGIT is'行情位数';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.VALUATION_DATE is'估值日期';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.VDATE is'开始日';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.MDATE is'结束日';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.CREATE_USER is'创建人';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.CREATE_DEPT is'创建部门';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.CREATE_TIME is'创建时间';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.UPDATE_USER is'更新人';
comment on column BOK_VAL_SCHEME_DETAIL_SPECIAL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VAL_TABLE_DATA                                    */
/*==============================================================*/
create table BOK_VAL_TABLE_DATA 
(
   SEQ_NO               VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32),
   BOOKSET_NAME         VARCHAR(50),
   PROFIT_TYPE          VARCHAR(50),
   VAL_DATE             DATE,
   DETAIL_DIST          VARCHAR(50),
   SUBJECT_NO           VARCHAR(300),
   SUBJECT_NAME         VARCHAR(500),
   FSUBJECT_ID          VARCHAR(32),
   NUM_AMT              NUMERIC(30, 2),
   UNIT_COST            NUMERIC(30, 2),
   COST                 NUMERIC(30, 2),
   COST_PERCENT         NUMERIC(30, 14),
   CLOSE_PRICE          NUMERIC(30, 2),
   MARKET_VALUE         NUMERIC(30, 2),
   VALUE_PERCENT        NUMERIC(30, 14),
   VALUE_INCREMENT      NUMERIC(30, 2),
   BAL_FLAG             VARCHAR(50),
   SHADOW_PRICE_VALUE   NUMERIC(30, 2),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VAL_TABLE_DATA primary key (SEQ_NO)
);
comment on table BOK_VAL_TABLE_DATA is
'估值表数据';
comment on column BOK_VAL_TABLE_DATA.SEQ_NO is'流水号';
comment on column BOK_VAL_TABLE_DATA.BOOKSET_ID is'账套代码';
comment on column BOK_VAL_TABLE_DATA.BOOKSET_NAME is'账套名称';
comment on column BOK_VAL_TABLE_DATA.PROFIT_TYPE is'收益类型';
comment on column BOK_VAL_TABLE_DATA.VAL_DATE is'估值日期';
comment on column BOK_VAL_TABLE_DATA.DETAIL_DIST is'明细区分';
comment on column BOK_VAL_TABLE_DATA.SUBJECT_NO is'科目号';
comment on column BOK_VAL_TABLE_DATA.SUBJECT_NAME is'科目名称';
comment on column BOK_VAL_TABLE_DATA.FSUBJECT_ID is'上级科目号';
comment on column BOK_VAL_TABLE_DATA.NUM_AMT is'数量';
comment on column BOK_VAL_TABLE_DATA.UNIT_COST is'单位成本';
comment on column BOK_VAL_TABLE_DATA.COST is'成本';
comment on column BOK_VAL_TABLE_DATA.COST_PERCENT is'成本占净值比';
comment on column BOK_VAL_TABLE_DATA.CLOSE_PRICE is'行情收市价';
comment on column BOK_VAL_TABLE_DATA.MARKET_VALUE is'估值';
comment on column BOK_VAL_TABLE_DATA.VALUE_PERCENT is'估值占净值比';
comment on column BOK_VAL_TABLE_DATA.VALUE_INCREMENT is'估值增值';
comment on column BOK_VAL_TABLE_DATA.BAL_FLAG is'余额方向';
comment on column BOK_VAL_TABLE_DATA.SHADOW_PRICE_VALUE is'影子价格估值';
comment on column BOK_VAL_TABLE_DATA.CREATE_USER is'创建人';
comment on column BOK_VAL_TABLE_DATA.CREATE_DEPT is'创建部门';
comment on column BOK_VAL_TABLE_DATA.CREATE_TIME is'创建时间';
comment on column BOK_VAL_TABLE_DATA.UPDATE_USER is'更新人';
comment on column BOK_VAL_TABLE_DATA.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VOUCH_DETAIL                                      */
/*==============================================================*/
create table BOK_VOUCH_DETAIL 
(
   VOUCH_SUBNUM         VARCHAR(32)          not null,
   VOUCH_NUM            VARCHAR(32),
   BUS_VOUCHSUBNUM      VARCHAR(32),
   HAPPEN_DATE          DATE,
   TABLE_ID             VARCHAR(32),
   CD_FLAG              VARCHAR(50),
   SUBJECT_NO           VARCHAR(32),
   HAPPEN_AMT           NUMERIC(30, 2),
   HAPPEN_NUMBER        NUMERIC(30, 4),
   BUSI_ID              VARCHAR(32),
   CCY                  VARCHAR(50),
   TRADE_ID             VARCHAR(32),
   DETAIL_SUBJECT_NO    VARCHAR(300),
   BOOK_TYPE            VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VOUCH_DETAIL primary key (VOUCH_SUBNUM)
);
comment on table BOK_VOUCH_DETAIL is
'凭证子表';
comment on column BOK_VOUCH_DETAIL.VOUCH_SUBNUM is'凭证子表编号';
comment on column BOK_VOUCH_DETAIL.VOUCH_NUM is'凭证编号';
comment on column BOK_VOUCH_DETAIL.BUS_VOUCHSUBNUM is'业务凭证子表编号';
comment on column BOK_VOUCH_DETAIL.HAPPEN_DATE is'发生日期';
comment on column BOK_VOUCH_DETAIL.TABLE_ID is'场景代码';
comment on column BOK_VOUCH_DETAIL.CD_FLAG is'借贷方向';
comment on column BOK_VOUCH_DETAIL.SUBJECT_NO is'科目号';
comment on column BOK_VOUCH_DETAIL.HAPPEN_AMT is'发生额';
comment on column BOK_VOUCH_DETAIL.HAPPEN_NUMBER is'发生数量';
comment on column BOK_VOUCH_DETAIL.BUSI_ID is'业务明细代码';
comment on column BOK_VOUCH_DETAIL.CCY is'币种';
comment on column BOK_VOUCH_DETAIL.TRADE_ID is'交易编号';
comment on column BOK_VOUCH_DETAIL.DETAIL_SUBJECT_NO is'四级科目号';
comment on column BOK_VOUCH_DETAIL.BOOK_TYPE is'凭证类型';
comment on column BOK_VOUCH_DETAIL.CREATE_USER is'创建人';
comment on column BOK_VOUCH_DETAIL.CREATE_DEPT is'创建部门';
comment on column BOK_VOUCH_DETAIL.CREATE_TIME is'创建时间';
comment on column BOK_VOUCH_DETAIL.UPDATE_USER is'更新人';
comment on column BOK_VOUCH_DETAIL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VOUCH_MAIN                                        */
/*==============================================================*/
create table BOK_VOUCH_MAIN 
(
   VOUCH_NUM            VARCHAR(32)          not null,
   BUS_VOUCHNUM         VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32),
   TRADE_ID             VARCHAR(32),
   BOOK_DATE            DATE,
   COM_TABLE_ID         VARCHAR(32),
   VOUCH_REMARK         VARCHAR(200),
   OFFSET_FLAG          VARCHAR(50),
   VOUCH_YEAR           NUMERIC(10),
   VOUCH_MONTH          NUMERIC(10),
   NUM                  NUMERIC(10),
   BOOK_TYPE            VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VOUCH_MAIN primary key (VOUCH_NUM)
);
comment on table BOK_VOUCH_MAIN is
'凭证主表';
comment on column BOK_VOUCH_MAIN.VOUCH_NUM is'凭证编号';
comment on column BOK_VOUCH_MAIN.BUS_VOUCHNUM is'业务凭证编号';
comment on column BOK_VOUCH_MAIN.BOOKSET_ID is'账套代码';
comment on column BOK_VOUCH_MAIN.TRADE_ID is'交易编号';
comment on column BOK_VOUCH_MAIN.BOOK_DATE is'入账日期';
comment on column BOK_VOUCH_MAIN.COM_TABLE_ID is'合并场景代码';
comment on column BOK_VOUCH_MAIN.VOUCH_REMARK is'凭证说明';
comment on column BOK_VOUCH_MAIN.OFFSET_FLAG is'冲正标识';
comment on column BOK_VOUCH_MAIN.VOUCH_YEAR is'年份';
comment on column BOK_VOUCH_MAIN.VOUCH_MONTH is'月份';
comment on column BOK_VOUCH_MAIN.NUM is'编号位数';
comment on column BOK_VOUCH_MAIN.BOOK_TYPE is'凭证类型';
comment on column BOK_VOUCH_MAIN.CREATE_USER is'创建人';
comment on column BOK_VOUCH_MAIN.CREATE_DEPT is'创建部门';
comment on column BOK_VOUCH_MAIN.CREATE_TIME is'创建时间';
comment on column BOK_VOUCH_MAIN.UPDATE_USER is'更新人';
comment on column BOK_VOUCH_MAIN.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VOUCH_MAIN_ERROR                                  */
/*==============================================================*/
create table BOK_VOUCH_MAIN_ERROR 
(
   VOUCH_NUM            VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32)          not null,
   BOOK_DATE            DATE                 not null,
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP,
   UPDATE_USER          VARCHAR(20),
   UPDATE_TIME          TIMESTAMP,
   constraint PK_BOK_VOUCH_MAIN_ERROR primary key (VOUCH_NUM, BOOKSET_ID, BOOK_DATE)
);
comment on table BOK_VOUCH_MAIN_ERROR is
'错误凭证表';
comment on column BOK_VOUCH_MAIN_ERROR.VOUCH_NUM is'凭证编号';
comment on column BOK_VOUCH_MAIN_ERROR.BOOKSET_ID is'账套代码';
comment on column BOK_VOUCH_MAIN_ERROR.BOOK_DATE is'入账日期';
comment on column BOK_VOUCH_MAIN_ERROR.CREATE_USER is'创建人';
comment on column BOK_VOUCH_MAIN_ERROR.CREATE_DEPT is'创建部门';
comment on column BOK_VOUCH_MAIN_ERROR.CREATE_TIME is'创建时间';
comment on column BOK_VOUCH_MAIN_ERROR.UPDATE_USER is'更新人';
comment on column BOK_VOUCH_MAIN_ERROR.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: BOK_VOUCH_REL                                         */
/*==============================================================*/
create table BOK_VOUCH_REL 
(
   VOUCH_SUBNUM         VARCHAR(32)          not null,
   SEQNO                VARCHAR(32)          not null,
   VOUCH_NUM            VARCHAR(32)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VOUCH_REL primary key (VOUCH_SUBNUM, SEQNO, VOUCH_NUM)
);
comment on table BOK_VOUCH_REL is
'凭证账务明细关联表';
comment on column BOK_VOUCH_REL.VOUCH_SUBNUM is'凭证子表编号';
comment on column BOK_VOUCH_REL.SEQNO is'账务明细流水号';
comment on column BOK_VOUCH_REL.VOUCH_NUM is'凭证编号';
comment on column BOK_VOUCH_REL.CREATE_USER is'创建人';
comment on column BOK_VOUCH_REL.CREATE_DEPT is'创建部门';
comment on column BOK_VOUCH_REL.CREATE_TIME is'创建时间';
comment on column BOK_VOUCH_REL.UPDATE_USER is'更新人';
comment on column BOK_VOUCH_REL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_CASH_CALC_RULE                                    */
/*==============================================================*/
create table FIN_CASH_CALC_RULE 
(
   CASH_ID              VARCHAR(32)          not null,
   EFF_DATE             DATE                 not null,
   CALC_TYPE            VARCHAR(50),
   INT_RATE_ID          VARCHAR(32),
   YIELD                NUMERIC(30, 14),
   BASIS                VARCHAR(50),
   BASE_TYPE            VARCHAR(50),
   BASE_DATE_TYPE       VARCHAR(50),
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   BRANCH               NUMERIC(10)          not null,
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_CASH_CALC_RULE primary key (CASH_ID, EFF_DATE)
);
comment on table FIN_CASH_CALC_RULE is
'现金流计算规则表';
comment on column FIN_CASH_CALC_RULE.CASH_ID is'现金流代码';
comment on column FIN_CASH_CALC_RULE.EFF_DATE is'生效日期';
comment on column FIN_CASH_CALC_RULE.CALC_TYPE is'计算类型';
comment on column FIN_CASH_CALC_RULE.INT_RATE_ID is'浮动利率编号';
comment on column FIN_CASH_CALC_RULE.YIELD is'生效利率';
comment on column FIN_CASH_CALC_RULE.BASIS is'计算基础';
comment on column FIN_CASH_CALC_RULE.BASE_TYPE is'基数类型';
comment on column FIN_CASH_CALC_RULE.BASE_DATE_TYPE is'基数日期类型';
comment on column FIN_CASH_CALC_RULE.FINPROD_ID is'金融产品代码';
comment on column FIN_CASH_CALC_RULE.FINPROD_TYPE is'金融产品类型';
comment on column FIN_CASH_CALC_RULE.BRANCH is'分支序号';
comment on column FIN_CASH_CALC_RULE.REMARK is'备注';
comment on column FIN_CASH_CALC_RULE.CREATE_USER is'创建人';
comment on column FIN_CASH_CALC_RULE.CREATE_DEPT is'创建部门';
comment on column FIN_CASH_CALC_RULE.CREATE_TIME is'创建时间';
comment on column FIN_CASH_CALC_RULE.UPDATE_USER is'更新人';
comment on column FIN_CASH_CALC_RULE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_CASH_INFO                                         */
/*==============================================================*/
create table FIN_CASH_INFO 
(
   CASH_ID              VARCHAR(32)          not null,
   CASH_TYPE_1          VARCHAR(50)          not null,
   CASH_TYPE_2          VARCHAR(50),
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   BRANCH               NUMERIC(10)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_CASH_INFO primary key (CASH_ID)
);
comment on table FIN_CASH_INFO is
'现金流信息表';
comment on column FIN_CASH_INFO.CASH_ID is'现金流代码';
comment on column FIN_CASH_INFO.CASH_TYPE_1 is'现金流一级类型';
comment on column FIN_CASH_INFO.CASH_TYPE_2 is'现金流二级类型';
comment on column FIN_CASH_INFO.FINPROD_ID is'金融产品代码';
comment on column FIN_CASH_INFO.FINPROD_TYPE is'金融产品类型';
comment on column FIN_CASH_INFO.BRANCH is'分支序号';
comment on column FIN_CASH_INFO.CREATE_USER is'创建人';
comment on column FIN_CASH_INFO.CREATE_DEPT is'创建部门';
comment on column FIN_CASH_INFO.CREATE_TIME is'创建时间';
comment on column FIN_CASH_INFO.UPDATE_USER is'更新人';
comment on column FIN_CASH_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_CASH_INT_INFO                                     */
/*==============================================================*/
create table FIN_CASH_INT_INFO 
(
   CASH_ID              VARCHAR(32)          not null,
   EFF_DATE             DATE                 not null,
   INT_TYPE             VARCHAR(50),
   RESET_TYPE           VARCHAR(50),
   RESET_FREQ           VARCHAR(50),
   FIRST_RESET_DATE     NUMERIC(30, 4),
   SPECIFY_RESET_DATE   DATE,
   PRE_OBS_NUM          NUMERIC(30, 2),
   PRE_OBS_UNIT         VARCHAR(50),
   BASIC_RATE           NUMERIC(30, 14),
   BASIS                VARCHAR(50),
   COEFFICIENT          NUMERIC(30, 14),
   SPREAD_RATE          NUMERIC(30, 14),
   HIGHEST_RATE         NUMERIC(30, 14),
   LOWEST_RATE          NUMERIC(30, 14),
   BENCHMARK_ID         VARCHAR(32),
   BENCHMARK_TYPE       VARCHAR(50),
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   BRANCH               NUMERIC(10)          not null,
   constraint PK_FIN_CASH_INT_INFO primary key (CASH_ID, EFF_DATE)
);
comment on table FIN_CASH_INT_INFO is
'现金流利率信息表';
comment on column FIN_CASH_INT_INFO.CASH_ID is'现金流代码';
comment on column FIN_CASH_INT_INFO.EFF_DATE is'生效日';
comment on column FIN_CASH_INT_INFO.INT_TYPE is'利率类型';
comment on column FIN_CASH_INT_INFO.RESET_TYPE is'重置类型';
comment on column FIN_CASH_INT_INFO.RESET_FREQ is'重置频率';
comment on column FIN_CASH_INT_INFO.FIRST_RESET_DATE is'首次重置日';
comment on column FIN_CASH_INT_INFO.SPECIFY_RESET_DATE is'指定重置日期';
comment on column FIN_CASH_INT_INFO.PRE_OBS_NUM is'提前观察数量';
comment on column FIN_CASH_INT_INFO.PRE_OBS_UNIT is'提前观察单位';
comment on column FIN_CASH_INT_INFO.BASIC_RATE is'初始利率';
comment on column FIN_CASH_INT_INFO.BASIS is'计算基础';
comment on column FIN_CASH_INT_INFO.COEFFICIENT is'系数';
comment on column FIN_CASH_INT_INFO.SPREAD_RATE is'利差';
comment on column FIN_CASH_INT_INFO.HIGHEST_RATE is'利率上限';
comment on column FIN_CASH_INT_INFO.LOWEST_RATE is'利率下限';
comment on column FIN_CASH_INT_INFO.BENCHMARK_ID is'基准编号';
comment on column FIN_CASH_INT_INFO.BENCHMARK_TYPE is'基准类型';
comment on column FIN_CASH_INT_INFO.FINPROD_ID is'金融产品代码';
comment on column FIN_CASH_INT_INFO.FINPROD_TYPE is'金融产品类型';
comment on column FIN_CASH_INT_INFO.BRANCH is'分支序号';
/*==============================================================*/
/* Table: FIN_CASH_PAY_RULE                                     */
/*==============================================================*/
create table FIN_CASH_PAY_RULE 
(
   CASH_ID              VARCHAR(32)          not null,
   EFF_DATE             DATE                 not null,
   PAY_CYCLE            VARCHAR(50),
   DAY_TYPE             VARCHAR(50),
   DAY_VALUE            VARCHAR(200),
   TDAY_RULE            VARCHAR(50),
   CALC_RULE            VARCHAR(50),
   FIRST_PAY_DATE       DATE,
   CASH_UNIT_TYPE       VARCHAR(50),
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   PAY_TYPE             VARCHAR(50),
   BRANCH               NUMERIC(10)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_CASH_PAY_RULE primary key (CASH_ID, EFF_DATE)
);
comment on table FIN_CASH_PAY_RULE is
'现金流支付规则表';
comment on column FIN_CASH_PAY_RULE.CASH_ID is'现金流代码';
comment on column FIN_CASH_PAY_RULE.EFF_DATE is'生效日期';
comment on column FIN_CASH_PAY_RULE.PAY_CYCLE is'支付频率';
comment on column FIN_CASH_PAY_RULE.DAY_TYPE is'日期类型';
comment on column FIN_CASH_PAY_RULE.DAY_VALUE is'日期值';
comment on column FIN_CASH_PAY_RULE.TDAY_RULE is'营业日准则';
comment on column FIN_CASH_PAY_RULE.CALC_RULE is'推算方式';
comment on column FIN_CASH_PAY_RULE.FIRST_PAY_DATE is'首次支付日';
comment on column FIN_CASH_PAY_RULE.CASH_UNIT_TYPE is'现金流单位';
comment on column FIN_CASH_PAY_RULE.FINPROD_ID is'金融产品代码';
comment on column FIN_CASH_PAY_RULE.FINPROD_TYPE is'金融产品类型';
comment on column FIN_CASH_PAY_RULE.PAY_TYPE is'支付方式';
comment on column FIN_CASH_PAY_RULE.BRANCH is'分支序号';
comment on column FIN_CASH_PAY_RULE.CREATE_USER is'创建人';
comment on column FIN_CASH_PAY_RULE.CREATE_DEPT is'创建部门';
comment on column FIN_CASH_PAY_RULE.CREATE_TIME is'创建时间';
comment on column FIN_CASH_PAY_RULE.UPDATE_USER is'更新人';
comment on column FIN_CASH_PAY_RULE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_CASH_PLAN                                         */
/*==============================================================*/
create table FIN_CASH_PLAN 
(
   CASH_ID              VARCHAR(32)          not null,
   CASH_NUM             NUMERIC(30, 4)       not null,
   VDATE_UNADJUST       DATE,
   MDATE_UNADJUST       DATE,
   PAY_DATE_UNADJUST    DATE,
   VDATE                DATE,
   MDATE                DATE                 not null,
   PAY_DATE             DATE,
   TERMDAYS             NUMERIC(10),
   VDATE_Y              DATE,
   VDATE_TERM           DATE,
   MDATE_TERM           DATE,
   TERMDAYS_TERM        NUMERIC(10),
   PRIN_AMT             NUMERIC(30, 14),
   INT_PRIN_AMT         NUMERIC(30, 14),
   INT_AMT              NUMERIC(30, 14),
   IS_PAY_INT           VARCHAR(32),
   CASH_AMT             NUMERIC(30, 14),
   CASH_UNIT_TYPE       VARCHAR(50),
   CALC_FUNCTION        VARCHAR(50),
   FREQUENCY            NUMERIC(10),
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   BRANCH               NUMERIC(10)          not null,
   PAY_TYPE             VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_CASH_PLAN primary key (CASH_ID, CASH_NUM)
);
comment on table FIN_CASH_PLAN is
'现金流计划表';
comment on column FIN_CASH_PLAN.CASH_ID is'现金流代码';
comment on column FIN_CASH_PLAN.CASH_NUM is'现金流序号';
comment on column FIN_CASH_PLAN.VDATE_UNADJUST is'未调整的计算开始日';
comment on column FIN_CASH_PLAN.MDATE_UNADJUST is'未调整的计算结束日';
comment on column FIN_CASH_PLAN.PAY_DATE_UNADJUST is'未调整的计划支付日';
comment on column FIN_CASH_PLAN.VDATE is'调整后的计算开始日';
comment on column FIN_CASH_PLAN.MDATE is'调整后的计算结束日';
comment on column FIN_CASH_PLAN.PAY_DATE is'调整后的计划支付日';
comment on column FIN_CASH_PLAN.TERMDAYS is'计算周期天数';
comment on column FIN_CASH_PLAN.VDATE_Y is'理论计息年度开始日';
comment on column FIN_CASH_PLAN.VDATE_TERM is'完整周期开始日';
comment on column FIN_CASH_PLAN.MDATE_TERM is'完整周期结束日';
comment on column FIN_CASH_PLAN.TERMDAYS_TERM is'完整周期计息天数';
comment on column FIN_CASH_PLAN.PRIN_AMT is'计划支付本金';
comment on column FIN_CASH_PLAN.INT_PRIN_AMT is'计息本金';
comment on column FIN_CASH_PLAN.INT_AMT is'计划支付利息';
comment on column FIN_CASH_PLAN.IS_PAY_INT is'是否支付利息';
comment on column FIN_CASH_PLAN.CASH_AMT is'计划支付现金流';
comment on column FIN_CASH_PLAN.CASH_UNIT_TYPE is'现金流单位';
comment on column FIN_CASH_PLAN.CALC_FUNCTION is'计息算法';
comment on column FIN_CASH_PLAN.FREQUENCY is'年付息次数';
comment on column FIN_CASH_PLAN.FINPROD_ID is'金融产品代码';
comment on column FIN_CASH_PLAN.FINPROD_TYPE is'金融产品类型';
comment on column FIN_CASH_PLAN.BRANCH is'分支序号';
comment on column FIN_CASH_PLAN.PAY_TYPE is'支付方式';
comment on column FIN_CASH_PLAN.CREATE_USER is'创建人';
comment on column FIN_CASH_PLAN.CREATE_DEPT is'创建部门';
comment on column FIN_CASH_PLAN.CREATE_TIME is'创建时间';
comment on column FIN_CASH_PLAN.UPDATE_USER is'更新人';
comment on column FIN_CASH_PLAN.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_DEAL_PLEDGE                                       */
/*==============================================================*/
create table FIN_DEAL_PLEDGE 
(
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   BRANCH               NUMERIC(10)          not null,
   PLEDGE_FINPROD_ID    VARCHAR(32)          not null,
   PLEDGE_FINPROD_TYPE  VARCHAR(50)          not null,
   PLEDGE_FACE_VALUE    NUMERIC(30, 2),
   PLEDGE_NUMBER        NUMERIC(30, 2),
   PLEDGE_RATIO         NUMERIC(30, 14),
   PLEDGE_AMT           NUMERIC(30, 2),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_DEAL_PLEDGE primary key (FINPROD_ID, FINPROD_TYPE, BRANCH, PLEDGE_FINPROD_ID, PLEDGE_FINPROD_TYPE)
);
comment on table FIN_DEAL_PLEDGE is
'回购质押品';
comment on column FIN_DEAL_PLEDGE.FINPROD_ID is'金融产品代码';
comment on column FIN_DEAL_PLEDGE.FINPROD_TYPE is'金融产品类型';
comment on column FIN_DEAL_PLEDGE.BRANCH is'分支序号';
comment on column FIN_DEAL_PLEDGE.PLEDGE_FINPROD_ID is'质押金融产品代码';
comment on column FIN_DEAL_PLEDGE.PLEDGE_FINPROD_TYPE is'质押金融产品类型';
comment on column FIN_DEAL_PLEDGE.PLEDGE_FACE_VALUE is'质押面值';
comment on column FIN_DEAL_PLEDGE.PLEDGE_NUMBER is'质押数量';
comment on column FIN_DEAL_PLEDGE.PLEDGE_RATIO is'质押率';
comment on column FIN_DEAL_PLEDGE.PLEDGE_AMT is'质押金额';
comment on column FIN_DEAL_PLEDGE.CREATE_USER is'创建人';
comment on column FIN_DEAL_PLEDGE.CREATE_DEPT is'创建部门';
comment on column FIN_DEAL_PLEDGE.CREATE_TIME is'创建时间';
comment on column FIN_DEAL_PLEDGE.UPDATE_USER is'更新人';
comment on column FIN_DEAL_PLEDGE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_EXERCISE_PLAN                                     */
/*==============================================================*/
create table FIN_EXERCISE_PLAN 
(
   CASH_ID              VARCHAR(32)          not null,
   EXERCISE_TYPE        VARCHAR(50)          not null,
   VDATE                DATE,
   MDATE                DATE,
   EXERCISE_DATE        DATE                 not null,
   EXERCISE_STATUS      VARCHAR(50),
   EXERCISE_PRICE       NUMERIC(30, 14),
   EXERCISE_REMARK      VARCHAR(1000),
   FINPROD_ID           VARCHAR(32),
   FINPROD_TYPE         VARCHAR(50),
   BRANCH               NUMERIC(10)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_EXERCISE_PLAN primary key (CASH_ID, EXERCISE_TYPE, EXERCISE_DATE)
);
comment on table FIN_EXERCISE_PLAN is
'行权计划表';
comment on column FIN_EXERCISE_PLAN.CASH_ID is'现金流代码';
comment on column FIN_EXERCISE_PLAN.EXERCISE_TYPE is'行权方式';
comment on column FIN_EXERCISE_PLAN.VDATE is'申请开始日';
comment on column FIN_EXERCISE_PLAN.MDATE is'申请结束日';
comment on column FIN_EXERCISE_PLAN.EXERCISE_DATE is'计划行权日';
comment on column FIN_EXERCISE_PLAN.EXERCISE_STATUS is'行权状态';
comment on column FIN_EXERCISE_PLAN.EXERCISE_PRICE is'行权价';
comment on column FIN_EXERCISE_PLAN.EXERCISE_REMARK is'行权条款';
comment on column FIN_EXERCISE_PLAN.FINPROD_ID is'金融产品代码';
comment on column FIN_EXERCISE_PLAN.FINPROD_TYPE is'金融产品类型';
comment on column FIN_EXERCISE_PLAN.BRANCH is'分支序号';
comment on column FIN_EXERCISE_PLAN.CREATE_USER is'创建人';
comment on column FIN_EXERCISE_PLAN.CREATE_DEPT is'创建部门';
comment on column FIN_EXERCISE_PLAN.CREATE_TIME is'创建时间';
comment on column FIN_EXERCISE_PLAN.UPDATE_USER is'更新人';
comment on column FIN_EXERCISE_PLAN.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_FLOAT_ACCRUAL_SCHE                                */
/*==============================================================*/
create table FIN_FLOAT_ACCRUAL_SCHE 
(
   CASH_ID              VARCHAR(32)          not null,
   ACCR_DATE            DATE                 not null,
   ACCR_STR_DATE        DATE                 not null,
   ACCR_END_DATE        DATE                 not null,
   VAL_DATE             DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_FLOAT_ACCRUAL_SCHE primary key (CASH_ID, ACCR_DATE)
);
comment on table FIN_FLOAT_ACCRUAL_SCHE is
'业绩报酬计提计划';
comment on column FIN_FLOAT_ACCRUAL_SCHE.CASH_ID is'现金流代码';
comment on column FIN_FLOAT_ACCRUAL_SCHE.ACCR_DATE is'计提日';
comment on column FIN_FLOAT_ACCRUAL_SCHE.ACCR_STR_DATE is'区间开始日';
comment on column FIN_FLOAT_ACCRUAL_SCHE.ACCR_END_DATE is'区间结束日';
comment on column FIN_FLOAT_ACCRUAL_SCHE.VAL_DATE is'调整前结束日';
comment on column FIN_FLOAT_ACCRUAL_SCHE.CREATE_USER is'创建人';
comment on column FIN_FLOAT_ACCRUAL_SCHE.CREATE_DEPT is'创建部门';
comment on column FIN_FLOAT_ACCRUAL_SCHE.CREATE_TIME is'创建时间';
comment on column FIN_FLOAT_ACCRUAL_SCHE.UPDATE_USER is'更新人';
comment on column FIN_FLOAT_ACCRUAL_SCHE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_FLOAT_CALC_RULE                                   */
/*==============================================================*/
create table FIN_FLOAT_CALC_RULE 
(
   CASH_ID              VARCHAR(32)          not null,
   EFF_DATE             DATE                 not null,
   BASE_RULE            VARCHAR(32),
   AST_VDATE_TYPE       VARCHAR(50),
   ACT_RULE             VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_FLOAT_CALC_RULE primary key (CASH_ID, EFF_DATE)
);
comment on table FIN_FLOAT_CALC_RULE is
'业绩报酬计算规则';
comment on column FIN_FLOAT_CALC_RULE.CASH_ID is'现金流代码';
comment on column FIN_FLOAT_CALC_RULE.EFF_DATE is'生效日期';
comment on column FIN_FLOAT_CALC_RULE.BASE_RULE is'业绩基准规则';
comment on column FIN_FLOAT_CALC_RULE.AST_VDATE_TYPE is'资产净值日类型';
comment on column FIN_FLOAT_CALC_RULE.ACT_RULE is'生效规则';
comment on column FIN_FLOAT_CALC_RULE.CREATE_USER is'创建人';
comment on column FIN_FLOAT_CALC_RULE.CREATE_DEPT is'创建部门';
comment on column FIN_FLOAT_CALC_RULE.CREATE_TIME is'创建时间';
comment on column FIN_FLOAT_CALC_RULE.UPDATE_USER is'更新人';
comment on column FIN_FLOAT_CALC_RULE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_FLOAT_CALC_RULE_DETAIL                            */
/*==============================================================*/
create table FIN_FLOAT_CALC_RULE_DETAIL 
(
   CASH_ID              VARCHAR(32)          not null,
   EFF_DATE             DATE                 not null,
   LEV                  NUMERIC(10)          not null,
   BASIC_RATE           VARCHAR(32),
   COEFFICIENT          NUMERIC(30, 14),
   SPREAD_RATE          NUMERIC(30, 14),
   CHARGE_RATIO         NUMERIC(30, 14),
   SALE_RATIO           NUMERIC(30, 14),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_FLOAT_CALC_RULE_DETAIL primary key (CASH_ID, EFF_DATE, LEV)
);
comment on table FIN_FLOAT_CALC_RULE_DETAIL is
'业绩报酬计算规则明细';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.CASH_ID is'现金流代码';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.EFF_DATE is'生效日期';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.LEV is'层级';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.BASIC_RATE is'基准利率';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.COEFFICIENT is'调整系数';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.SPREAD_RATE is'利差';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.CHARGE_RATIO is'银行收取比例';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.SALE_RATIO is'研发部门收取比例';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.CREATE_USER is'创建人';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.CREATE_DEPT is'创建部门';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.CREATE_TIME is'创建时间';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.UPDATE_USER is'更新人';
comment on column FIN_FLOAT_CALC_RULE_DETAIL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_FLOAT_COUNTING_RULE                               */
/*==============================================================*/
create table FIN_FLOAT_COUNTING_RULE 
(
   CASH_ID              VARCHAR(32)          not null,
   EFF_DATE             DATE                 not null,
   ACCR_TYPE            VARCHAR(50),
   ACCR_CYCLE           NUMERIC(10),
   ACCR_CYCLE_UNIT      VARCHAR(50),
   ACCR_DAY_TYPE        VARCHAR(50),
   ACCR_DAY_VALUE       VARCHAR(50),
   ACCR_TDAY_RULE       VARCHAR(50),
   F_ACCR_DATE          DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_FLOAT_COUNTING_RULE primary key (CASH_ID, EFF_DATE)
);
comment on table FIN_FLOAT_COUNTING_RULE is
'业绩报酬计提规则';
comment on column FIN_FLOAT_COUNTING_RULE.CASH_ID is'现金流代码';
comment on column FIN_FLOAT_COUNTING_RULE.EFF_DATE is'生效日期';
comment on column FIN_FLOAT_COUNTING_RULE.ACCR_TYPE is'计提类型';
comment on column FIN_FLOAT_COUNTING_RULE.ACCR_CYCLE is'计提频率';
comment on column FIN_FLOAT_COUNTING_RULE.ACCR_CYCLE_UNIT is'计提频率单位';
comment on column FIN_FLOAT_COUNTING_RULE.ACCR_DAY_TYPE is'计提频率类型';
comment on column FIN_FLOAT_COUNTING_RULE.ACCR_DAY_VALUE is'计提频率值';
comment on column FIN_FLOAT_COUNTING_RULE.ACCR_TDAY_RULE is'计提营业日准则';
comment on column FIN_FLOAT_COUNTING_RULE.F_ACCR_DATE is'首次计提日';
comment on column FIN_FLOAT_COUNTING_RULE.CREATE_USER is'创建人';
comment on column FIN_FLOAT_COUNTING_RULE.CREATE_DEPT is'创建部门';
comment on column FIN_FLOAT_COUNTING_RULE.CREATE_TIME is'创建时间';
comment on column FIN_FLOAT_COUNTING_RULE.UPDATE_USER is'更新人';
comment on column FIN_FLOAT_COUNTING_RULE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_PRODUCT                                           */
/*==============================================================*/
create table FIN_PRODUCT 
(
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   FINPROD_ABBR         VARCHAR(200),
   FINPROD_NAME         VARCHAR(200),
   PROFIT_TYPE          VARCHAR(50),
   COUPON_SPECIES       VARCHAR(50),
   CHL_FINPROD_ID       VARCHAR(32),
   FINPROD_MARKET_ID    VARCHAR(50),
   ISSUE_PRICE          NUMERIC(30, 14),
   ISSUE_AMT            NUMERIC(30, 2),
   CCY                  VARCHAR(50),
   BLN_AREA             VARCHAR(50),
   TRADE_MARKET         VARCHAR(50),
   CALENDAR_ID          VARCHAR(32),
   ISSUE_TYPE           VARCHAR(50),
   ISSUER               VARCHAR(32),
   IDATE                DATE,
   VDATE                DATE,
   MDATE                DATE,
   ACTMDATE             DATE,
   IS_CHL               VARCHAR(50),
   IS_SUS               VARCHAR(50),
   IS_RIGHT             VARCHAR(50),
   P_FINPROD_ID         VARCHAR(32),
   O_FINPROD_ID         VARCHAR(32)          not null,
   O_FINPROD_TYPE       VARCHAR(50)          not null,
   CAPI_INCOME_FEATURE  VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_PRODUCT primary key (FINPROD_ID, FINPROD_TYPE)
)
partition by list
 (FINPROD_TYPE)
 (partition FINPROD_TYPE_DEFAULT
 values (DEFAULT));
comment on table FIN_PRODUCT is
'标的类金融产品，按金融资产类型分区。';
comment on column FIN_PRODUCT.FINPROD_ID is'金融产品代码';
comment on column FIN_PRODUCT.FINPROD_TYPE is'金融产品类型';
comment on column FIN_PRODUCT.FINPROD_ABBR is'金融产品简称';
comment on column FIN_PRODUCT.FINPROD_NAME is'金融产品全称';
comment on column FIN_PRODUCT.PROFIT_TYPE is'收益类型';
comment on column FIN_PRODUCT.COUPON_SPECIES is'息票品种';
comment on column FIN_PRODUCT.CHL_FINPROD_ID is'通道代码';
comment on column FIN_PRODUCT.FINPROD_MARKET_ID is'市场代码';
comment on column FIN_PRODUCT.ISSUE_PRICE is'发行价';
comment on column FIN_PRODUCT.ISSUE_AMT is'发行规模';
comment on column FIN_PRODUCT.CCY is'币种';
comment on column FIN_PRODUCT.BLN_AREA is'境内外';
comment on column FIN_PRODUCT.TRADE_MARKET is'交易场所';
comment on column FIN_PRODUCT.CALENDAR_ID is'交易日历';
comment on column FIN_PRODUCT.ISSUE_TYPE is'发行方式';
comment on column FIN_PRODUCT.ISSUER is'发行人';
comment on column FIN_PRODUCT.IDATE is'发行日';
comment on column FIN_PRODUCT.VDATE is'起息日';
comment on column FIN_PRODUCT.MDATE is'到期日';
comment on column FIN_PRODUCT.ACTMDATE is'实际到期日';
comment on column FIN_PRODUCT.IS_CHL is'是否通道';
comment on column FIN_PRODUCT.IS_SUS is'是否永续';
comment on column FIN_PRODUCT.IS_RIGHT is'是否含权';
comment on column FIN_PRODUCT.P_FINPROD_ID is'母金融产品代码';
comment on column FIN_PRODUCT.O_FINPROD_ID is'原金融产品代码';
comment on column FIN_PRODUCT.O_FINPROD_TYPE is'原金融产品类型';
comment on column FIN_PRODUCT.CAPI_INCOME_FEATURE is'本金收益特征';
comment on column FIN_PRODUCT.CREATE_USER is'创建人';
comment on column FIN_PRODUCT.CREATE_DEPT is'创建部门';
comment on column FIN_PRODUCT.CREATE_TIME is'创建时间';
comment on column FIN_PRODUCT.UPDATE_USER is'更新人';
comment on column FIN_PRODUCT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_PRODUCT_CLASS                                     */
/*==============================================================*/
create table FIN_PRODUCT_CLASS 
(
   OPTSN                VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   ASSET_TYPE           VARCHAR(50)          not null,
   ASSET_TYPE_NAME      VARCHAR2(200),
   TRADE_MARKET         VARCHAR(50),
   BLN_AREA             VARCHAR(50),
   ORG_CLASS            VARCHAR(50),
   ORG_CLASS_NAME       VARCHAR2(200),
   ACCOUNTING_CLASS     VARCHAR(50),
   ACCOUNTING_CLASS_NAME VARCHAR2(200),
   TAX_CLASS            VARCHAR(50),
   TAX_CLASS_NAME       VARCHAR2(200),
   CREATE_USER          VARCHAR(20)          default 'sysadmin' not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            default SYSDATE not null,
   UPDATE_USER          VARCHAR(20)          default 'sysadmin' not null,
   UPDATE_TIME          TIMESTAMP            default SYSDATE not null,
   constraint PK_FIN_PRODUCT_CLASS primary key (OPTSN)
);
comment on table FIN_PRODUCT_CLASS is
'金融产品分类表字段映射表';
comment on column FIN_PRODUCT_CLASS.OPTSN is'流水号';
comment on column FIN_PRODUCT_CLASS.FINPROD_TYPE is'金融产品类型';
comment on column FIN_PRODUCT_CLASS.ASSET_TYPE is'资产类型';
comment on column FIN_PRODUCT_CLASS.ASSET_TYPE_NAME is'资产类型名称';
comment on column FIN_PRODUCT_CLASS.TRADE_MARKET is'交易场所';
comment on column FIN_PRODUCT_CLASS.BLN_AREA is'境内外';
comment on column FIN_PRODUCT_CLASS.ORG_CLASS is'机构核算分类';
comment on column FIN_PRODUCT_CLASS.ORG_CLASS_NAME is'机构核算分类名称';
comment on column FIN_PRODUCT_CLASS.ACCOUNTING_CLASS is'核算分类';
comment on column FIN_PRODUCT_CLASS.ACCOUNTING_CLASS_NAME is'核算分类名称';
comment on column FIN_PRODUCT_CLASS.TAX_CLASS is'税务分类';
comment on column FIN_PRODUCT_CLASS.TAX_CLASS_NAME is'税务分类名称';
comment on column FIN_PRODUCT_CLASS.CREATE_USER is'创建人';
comment on column FIN_PRODUCT_CLASS.CREATE_DEPT is'创建部门';
comment on column FIN_PRODUCT_CLASS.CREATE_TIME is'创建时间';
comment on column FIN_PRODUCT_CLASS.UPDATE_USER is'更新人';
comment on column FIN_PRODUCT_CLASS.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_PRODUCT_EXT                                       */
/*==============================================================*/
create table FIN_PRODUCT_EXT 
(
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   SALE_CHANNEL         VARCHAR(50),
   SALE_LAYER           VARCHAR(32),
   SALE_PERIOD          NUMERIC(10),
   OPEN_TYPE            VARCHAR(50),
   CARRYOVER_FREQ       VARCHAR(50),
   PUR_SPEED            NUMERIC(10),
   RED_SPEED            NUMERIC(10),
   LOSS_HANDLE_MODE     VARCHAR(50),
   IS_CALC              VARCHAR(50),
   IS_COMPOUND_INT      VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null
);
comment on table FIN_PRODUCT_EXT is
'标的类金融产品扩展表';
comment on column FIN_PRODUCT_EXT.FINPROD_ID is'金融产品代码';
comment on column FIN_PRODUCT_EXT.FINPROD_TYPE is'金融产品类型';
comment on column FIN_PRODUCT_EXT.SALE_CHANNEL is'销售渠道';
comment on column FIN_PRODUCT_EXT.SALE_LAYER is'销售分层';
comment on column FIN_PRODUCT_EXT.SALE_PERIOD is'销售期次';
comment on column FIN_PRODUCT_EXT.OPEN_TYPE is'开放类型';
comment on column FIN_PRODUCT_EXT.CARRYOVER_FREQ is'基金结转频率';
comment on column FIN_PRODUCT_EXT.PUR_SPEED is'申购确认速度';
comment on column FIN_PRODUCT_EXT.RED_SPEED is'赎回确认速度';
comment on column FIN_PRODUCT_EXT.LOSS_HANDLE_MODE is'亏损处理方式';
comment on column FIN_PRODUCT_EXT.IS_CALC is'托管活期利息计提';
comment on column FIN_PRODUCT_EXT.IS_COMPOUND_INT is'是否按日复利';
comment on column FIN_PRODUCT_EXT.CREATE_USER is'创建人';
comment on column FIN_PRODUCT_EXT.CREATE_DEPT is'创建部门';
comment on column FIN_PRODUCT_EXT.CREATE_TIME is'创建时间';
comment on column FIN_PRODUCT_EXT.UPDATE_USER is'更新人';
comment on column FIN_PRODUCT_EXT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_PRODUCT_TYPE                                      */
/*==============================================================*/
create table FIN_PRODUCT_TYPE 
(
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   TYPE_1               VARCHAR(50),
   TYPE_2               VARCHAR(50),
   TYPE_3               VARCHAR(50),
   TYPE_4               VARCHAR(50),
   TYPE_5               VARCHAR(50),
   TYPE_6               VARCHAR(50),
   constraint PK_FIN_PRODUCT_TYPE primary key (FINPROD_ID, FINPROD_TYPE)
);
comment on table FIN_PRODUCT_TYPE is
'金融产品分类表';
comment on column FIN_PRODUCT_TYPE.FINPROD_ID is'金融产品代码';
comment on column FIN_PRODUCT_TYPE.FINPROD_TYPE is'金融产品类型';
comment on column FIN_PRODUCT_TYPE.TYPE_1 is'分类1';
comment on column FIN_PRODUCT_TYPE.TYPE_2 is'分类2';
comment on column FIN_PRODUCT_TYPE.TYPE_3 is'分类3';
comment on column FIN_PRODUCT_TYPE.TYPE_4 is'分类4';
comment on column FIN_PRODUCT_TYPE.TYPE_5 is'分类5';
comment on column FIN_PRODUCT_TYPE.TYPE_6 is'分类6';
/*==============================================================*/
/* Table: FIN_PRODUCT_VALUATION                                 */
/*==============================================================*/
create table FIN_PRODUCT_VALUATION 
(
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   PUBLISH_DATE         DATE,
   VAL_DATE             DATE                 not null,
   VALUATION            NUMERIC(30, 14)      not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_PRODUCT_VALUATION primary key (FINPROD_ID, FINPROD_TYPE, VAL_DATE)
);
comment on table FIN_PRODUCT_VALUATION is
'场外金融产品行情';
comment on column FIN_PRODUCT_VALUATION.FINPROD_ID is'金融产品代码';
comment on column FIN_PRODUCT_VALUATION.FINPROD_TYPE is'金融产品类型';
comment on column FIN_PRODUCT_VALUATION.PUBLISH_DATE is'公布日期';
comment on column FIN_PRODUCT_VALUATION.VAL_DATE is'估值日期';
comment on column FIN_PRODUCT_VALUATION.VALUATION is'估值';
comment on column FIN_PRODUCT_VALUATION.CREATE_USER is'创建人';
comment on column FIN_PRODUCT_VALUATION.CREATE_DEPT is'创建部门';
comment on column FIN_PRODUCT_VALUATION.CREATE_TIME is'创建时间';
comment on column FIN_PRODUCT_VALUATION.UPDATE_USER is'更新人';
comment on column FIN_PRODUCT_VALUATION.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_TRADE_PRODUCT                                     */
/*==============================================================*/
create table FIN_TRADE_PRODUCT 
(
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   BRANCH               NUMERIC(10)          not null,
   BRANCH_TYPE          VARCHAR(50),
   CHL_AGRT_ID          VARCHAR(32),
   PRIN                 NUMERIC(30, 2),
   CCY                  VARCHAR(50),
   VDATE                DATE,
   MDATE                DATE,
   TERM                 NUMERIC(10),
   TERM_TYPE            VARCHAR(50),
   INT_TYPE             VARCHAR(50),
   INT_RATE             NUMERIC(30, 14),
   INT_RATE_ID          VARCHAR(32),
   BASIS                VARCHAR(50),
   M_PRIN_AMT           NUMERIC(30, 2),
   M_INT_AMT            NUMERIC(30, 2),
   M_TRADE_AMT          NUMERIC(30, 2),
   O_FINPROD_ID         VARCHAR(32)          not null,
   O_FINPROD_TYPE       VARCHAR(50)          not null,
   O_TRADE_ID           VARCHAR(32),
   CAPI_INCOME_FEATURE  VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_TRADE_PRODUCT primary key (FINPROD_ID, FINPROD_TYPE, BRANCH)
)
partition by list
 (FINPROD_TYPE)
 (partition ASSET_TYPE_DEFAULT
 values (DEFAULT));
comment on table FIN_TRADE_PRODUCT is
'交易类金融产品';
comment on column FIN_TRADE_PRODUCT.FINPROD_ID is'金融产品代码';
comment on column FIN_TRADE_PRODUCT.FINPROD_TYPE is'金融产品类型';
comment on column FIN_TRADE_PRODUCT.BRANCH is'分支序号';
comment on column FIN_TRADE_PRODUCT.BRANCH_TYPE is'分支类型';
comment on column FIN_TRADE_PRODUCT.CHL_AGRT_ID is'通道代码';
comment on column FIN_TRADE_PRODUCT.PRIN is'名义本金';
comment on column FIN_TRADE_PRODUCT.CCY is'本金币种';
comment on column FIN_TRADE_PRODUCT.VDATE is'起息日';
comment on column FIN_TRADE_PRODUCT.MDATE is'到期日';
comment on column FIN_TRADE_PRODUCT.TERM is'期限(天)';
comment on column FIN_TRADE_PRODUCT.TERM_TYPE is'期限品种';
comment on column FIN_TRADE_PRODUCT.INT_TYPE is'利率类型';
comment on column FIN_TRADE_PRODUCT.INT_RATE is'固定利率';
comment on column FIN_TRADE_PRODUCT.INT_RATE_ID is'利率编号';
comment on column FIN_TRADE_PRODUCT.BASIS is'计息基础';
comment on column FIN_TRADE_PRODUCT.M_PRIN_AMT is'到期本金';
comment on column FIN_TRADE_PRODUCT.M_INT_AMT is'到期利息';
comment on column FIN_TRADE_PRODUCT.M_TRADE_AMT is'到期金额';
comment on column FIN_TRADE_PRODUCT.O_FINPROD_ID is'原金融产品代码';
comment on column FIN_TRADE_PRODUCT.O_FINPROD_TYPE is'原金融产品类型';
comment on column FIN_TRADE_PRODUCT.O_TRADE_ID is'逾期交易编号';
comment on column FIN_TRADE_PRODUCT.CAPI_INCOME_FEATURE is'本金收益特征';
comment on column FIN_TRADE_PRODUCT.CREATE_USER is'创建人';
comment on column FIN_TRADE_PRODUCT.CREATE_DEPT is'创建部门';
comment on column FIN_TRADE_PRODUCT.CREATE_TIME is'创建时间';
comment on column FIN_TRADE_PRODUCT.UPDATE_USER is'更新人';
comment on column FIN_TRADE_PRODUCT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: FIN_VAL_OPEN_PLAN                                     */
/*==============================================================*/
create table FIN_VAL_OPEN_PLAN 
(
   FINPROD_ID           VARCHAR(32)          not null,
   OPEN_DATE_STR        DATE,
   OPEN_DATE_END        DATE                 not null,
   OPEN_VDATE           DATE,
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(20),
   CREATE_TIME          TIMESTAMP,
   UPDATE_USER          VARCHAR(20),
   UPDATE_TIME          TIMESTAMP,
   constraint PK_FIN_VAL_OPEN_PLAN primary key (FINPROD_ID, OPEN_DATE_END)
);
comment on table FIN_VAL_OPEN_PLAN is
'开放式产品的开放计划';
comment on column FIN_VAL_OPEN_PLAN.FINPROD_ID is'金融产品代码';
comment on column FIN_VAL_OPEN_PLAN.OPEN_DATE_STR is'开放周期起始日';
comment on column FIN_VAL_OPEN_PLAN.OPEN_DATE_END is'开放周期结束日';
comment on column FIN_VAL_OPEN_PLAN.OPEN_VDATE is'开放周期起息日';
comment on column FIN_VAL_OPEN_PLAN.CREATE_USER is'创建人';
comment on column FIN_VAL_OPEN_PLAN.CREATE_DEPT is'创建部门';
comment on column FIN_VAL_OPEN_PLAN.CREATE_TIME is'创建时间';
comment on column FIN_VAL_OPEN_PLAN.UPDATE_USER is'更新人';
comment on column FIN_VAL_OPEN_PLAN.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: MST_BOND_MARKET_INFO                                  */
/*==============================================================*/
create table MST_BOND_MARKET_INFO 
(
   SEC_ID               VARCHAR(32)          not null,
   VALUE_SOURCE         VARCHAR(50)          not null,
   VALUE_DATE           DATE                 not null,
   CLOSE_PRICE          NUMERIC(30, 14),
   AVERAGE_PRICE        NUMERIC(30, 14),
   MAX_PRICE            NUMERIC(30, 14),
   MIN_PRICE            NUMERIC(30, 14),
   BUY_ONE_PRICE        NUMERIC(30, 14),
   SELL_ONE_PRICE       NUMERIC(30, 14),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MST_BOND_MARKET_INFO primary key (SEC_ID, VALUE_SOURCE, VALUE_DATE)
);
comment on table MST_BOND_MARKET_INFO is
'债券市场行情';
comment on column MST_BOND_MARKET_INFO.SEC_ID is'债券代码';
comment on column MST_BOND_MARKET_INFO.VALUE_SOURCE is'估值来源';
comment on column MST_BOND_MARKET_INFO.VALUE_DATE is'估值日';
comment on column MST_BOND_MARKET_INFO.CLOSE_PRICE is'收盘价';
comment on column MST_BOND_MARKET_INFO.AVERAGE_PRICE is'平均价';
comment on column MST_BOND_MARKET_INFO.MAX_PRICE is'最高价';
comment on column MST_BOND_MARKET_INFO.MIN_PRICE is'最低价';
comment on column MST_BOND_MARKET_INFO.BUY_ONE_PRICE is'买一价';
comment on column MST_BOND_MARKET_INFO.SELL_ONE_PRICE is'卖一价';
comment on column MST_BOND_MARKET_INFO.CREATE_USER is'创建人';
comment on column MST_BOND_MARKET_INFO.CREATE_DEPT is'创建部门';
comment on column MST_BOND_MARKET_INFO.CREATE_TIME is'创建时间';
comment on column MST_BOND_MARKET_INFO.UPDATE_USER is'更新人';
comment on column MST_BOND_MARKET_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: MST_BOND_VALUATION_INFO                               */
/*==============================================================*/
create table MST_BOND_VALUATION_INFO 
(
   SEC_ID               VARCHAR(32)          not null,
   VALUE_SOURCE         VARCHAR(50)          not null,
   VALUE_DATE           DATE                 not null,
   LAST_PERIOD          NUMERIC(30, 14),
   PRICE                NUMERIC(30, 14),
   NETPRICE             NUMERIC(30, 14),
   M_DURATION           NUMERIC(30, 14),
   M_CONVEXITY          NUMERIC(30, 14),
   BPVALUE              NUMERIC(30, 14),
   SDURATION            NUMERIC(30, 14),
   SCNVXTY              NUMERIC(30, 14),
   INTEREST_DURATION    NUMERIC(30, 14),
   INTEREST_CNVXTY      NUMERIC(30, 14),
   BPYIELD              NUMERIC(30, 14),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MST_BOND_VALUATION_INFO primary key (SEC_ID, VALUE_SOURCE, VALUE_DATE)
);
comment on table MST_BOND_VALUATION_INFO is
'债券估值行情';
comment on column MST_BOND_VALUATION_INFO.SEC_ID is'债券代码';
comment on column MST_BOND_VALUATION_INFO.VALUE_SOURCE is'估值来源';
comment on column MST_BOND_VALUATION_INFO.VALUE_DATE is'估值日';
comment on column MST_BOND_VALUATION_INFO.LAST_PERIOD is'剩余期限';
comment on column MST_BOND_VALUATION_INFO.PRICE is'市价全价';
comment on column MST_BOND_VALUATION_INFO.NETPRICE is'市价净价';
comment on column MST_BOND_VALUATION_INFO.M_DURATION is'市价久期';
comment on column MST_BOND_VALUATION_INFO.M_CONVEXITY is'市价凸性';
comment on column MST_BOND_VALUATION_INFO.BPVALUE is'基点价值';
comment on column MST_BOND_VALUATION_INFO.SDURATION is'利差久期';
comment on column MST_BOND_VALUATION_INFO.SCNVXTY is'利差凸性';
comment on column MST_BOND_VALUATION_INFO.INTEREST_DURATION is'利率久期';
comment on column MST_BOND_VALUATION_INFO.INTEREST_CNVXTY is'利率凸性';
comment on column MST_BOND_VALUATION_INFO.BPYIELD is'市价收益率';
comment on column MST_BOND_VALUATION_INFO.CREATE_USER is'创建人';
comment on column MST_BOND_VALUATION_INFO.CREATE_DEPT is'创建部门';
comment on column MST_BOND_VALUATION_INFO.CREATE_TIME is'创建时间';
comment on column MST_BOND_VALUATION_INFO.UPDATE_USER is'更新人';
comment on column MST_BOND_VALUATION_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: MST_CALENDAR_INFO                                     */
/*==============================================================*/
create table MST_CALENDAR_INFO 
(
   CALENDAR_ID          VARCHAR(32)          not null,
   CALENDAR_NAME        VARCHAR(200),
   OTHER_ID             VARCHAR(50),
   IS_TRADE_CALENDAR    VARCHAR(50),
   REMARK               VARCHAR(1000),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MST_CALENDAR_INFO primary key (CALENDAR_ID)
);
comment on table MST_CALENDAR_INFO is
'日历信息表';
comment on column MST_CALENDAR_INFO.CALENDAR_ID is'日历编号';
comment on column MST_CALENDAR_INFO.CALENDAR_NAME is'日历名称';
comment on column MST_CALENDAR_INFO.OTHER_ID is'第三方日历编号';
comment on column MST_CALENDAR_INFO.IS_TRADE_CALENDAR is'是否交易日历';
comment on column MST_CALENDAR_INFO.REMARK is'备注';
comment on column MST_CALENDAR_INFO.CREATE_USER is'创建人';
comment on column MST_CALENDAR_INFO.CREATE_DEPT is'创建部门';
comment on column MST_CALENDAR_INFO.CREATE_TIME is'创建时间';
comment on column MST_CALENDAR_INFO.UPDATE_USER is'更新人';
comment on column MST_CALENDAR_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: MST_COMP_INFO                                         */
/*==============================================================*/
create table MST_COMP_INFO 
(
   BENCHMARK_NO         VARCHAR(32)          not null,
   DATA_SOURCE          VARCHAR(32)          not null,
   RATE_ID              VARCHAR(32)          not null,
   RATE_TYPE            VARCHAR(50)          not null,
   WEIGHTS              NUMERIC(30, 14),
   constraint PK_MST_COMP_INFO primary key (BENCHMARK_NO, DATA_SOURCE, RATE_ID, RATE_TYPE)
);
comment on table MST_COMP_INFO is
'基准成分表';
comment on column MST_COMP_INFO.BENCHMARK_NO is'基准编号';
comment on column MST_COMP_INFO.DATA_SOURCE is'数据来源';
comment on column MST_COMP_INFO.RATE_ID is'行情代码';
comment on column MST_COMP_INFO.RATE_TYPE is'行情类型';
comment on column MST_COMP_INFO.WEIGHTS is'权重';
/*==============================================================*/
/* Table: MST_CUSTOMER_INFO                                     */
/*==============================================================*/
create table MST_CUSTOMER_INFO 
(
   CUSTOMER_ID          VARCHAR(32)          not null,
   CUSTOMER_NAME        VARCHAR(200),
   CUSTOMER_ABBR        VARCHAR(200),
   IS_CORPORATION       VARCHAR(50),
   IS_COUNTER_PARTY     VARCHAR(50),
   IS_ISSUER            VARCHAR(50),
   IS_ASSTE_MANAGER     VARCHAR(50),
   IS_TRUSTER           VARCHAR(50),
   IS_SALER             VARCHAR(50),
   P_CUSTOMER_ID        VARCHAR(32),
   CUSTOMER_TYPE        VARCHAR(50),
   CORPORATION_TYPE     VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MST_CUSTOMER_INFO primary key (CUSTOMER_ID)
);
comment on table MST_CUSTOMER_INFO is
'客户信息表';
comment on column MST_CUSTOMER_INFO.CUSTOMER_ID is'客户代码';
comment on column MST_CUSTOMER_INFO.CUSTOMER_NAME is'客户名称';
comment on column MST_CUSTOMER_INFO.CUSTOMER_ABBR is'客户简称';
comment on column MST_CUSTOMER_INFO.IS_CORPORATION is'是否法人机构';
comment on column MST_CUSTOMER_INFO.IS_COUNTER_PARTY is'是否交易对手';
comment on column MST_CUSTOMER_INFO.IS_ISSUER is'是否发行人';
comment on column MST_CUSTOMER_INFO.IS_ASSTE_MANAGER is'是否资产管理人';
comment on column MST_CUSTOMER_INFO.IS_TRUSTER is'是否托管人';
comment on column MST_CUSTOMER_INFO.IS_SALER is'是否承销商';
comment on column MST_CUSTOMER_INFO.P_CUSTOMER_ID is'上级客户代码';
comment on column MST_CUSTOMER_INFO.CUSTOMER_TYPE is'客户类型';
comment on column MST_CUSTOMER_INFO.CORPORATION_TYPE is'企业性质';
comment on column MST_CUSTOMER_INFO.CREATE_USER is'创建人';
comment on column MST_CUSTOMER_INFO.CREATE_DEPT is'创建部门';
comment on column MST_CUSTOMER_INFO.CREATE_TIME is'创建时间';
comment on column MST_CUSTOMER_INFO.UPDATE_USER is'更新人';
comment on column MST_CUSTOMER_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: MST_DATES                                             */
/*==============================================================*/
create table MST_DATES 
(
   DATES                DATE                 not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MST_DATES primary key (DATES)
);
comment on table MST_DATES is
'日期表
';
comment on column MST_DATES.DATES is'日期';
comment on column MST_DATES.CREATE_USER is'创建人';
comment on column MST_DATES.CREATE_DEPT is'创建部门';
comment on column MST_DATES.CREATE_TIME is'创建时间';
comment on column MST_DATES.UPDATE_USER is'更新人';
comment on column MST_DATES.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: MST_EXCHARGE_QUOTE                                    */
/*==============================================================*/
create table MST_EXCHARGE_QUOTE 
(
   BASE_CCY             VARCHAR(50)          not null,
   QUO_CCY              VARCHAR(50),
   QUO_UNIT             VARCHAR(50),
   QUO_SRC              VARCHAR(50),
   TRADE_DATE           DATE                 not null,
   EX_RATE              NUMERIC(30, 14),
   constraint PK_MST_EXCHARGE_QUOTE primary key (BASE_CCY, TRADE_DATE)
);
comment on table MST_EXCHARGE_QUOTE is
'汇率行情';
comment on column MST_EXCHARGE_QUOTE.BASE_CCY is'基础币种';
comment on column MST_EXCHARGE_QUOTE.QUO_CCY is'报价币种';
comment on column MST_EXCHARGE_QUOTE.QUO_UNIT is'报价单位';
comment on column MST_EXCHARGE_QUOTE.QUO_SRC is'行情来源';
comment on column MST_EXCHARGE_QUOTE.TRADE_DATE is'交易日';
comment on column MST_EXCHARGE_QUOTE.EX_RATE is'汇率';
/*==============================================================*/
/* Table: MST_EXPONENT_QUOTATION                                */
/*==============================================================*/
create table MST_EXPONENT_QUOTATION 
(
   RATE_ID              VARCHAR(32)          not null,
   EFF_DATE             DATE                 not null,
   RELAESE_DATE         DATE,
   EXPONENT             NUMERIC(30, 14),
   constraint PK_MST_EXPONENT_QUOTATION primary key (RATE_ID, EFF_DATE)
);
comment on table MST_EXPONENT_QUOTATION is
'指数行情';
comment on column MST_EXPONENT_QUOTATION.RATE_ID is'行情代码';
comment on column MST_EXPONENT_QUOTATION.EFF_DATE is'生效日';
comment on column MST_EXPONENT_QUOTATION.RELAESE_DATE is'发布日';
comment on column MST_EXPONENT_QUOTATION.EXPONENT is'指数';
/*==============================================================*/
/* Table: MST_FUND_MARKET_INFO                                  */
/*==============================================================*/
create table MST_FUND_MARKET_INFO 
(
   FUND_CODE            VARCHAR(32)          not null,
   VALUE_SOURCE         VARCHAR(50)          not null,
   VALUE_DATE           DATE                 not null,
   CLOSE_PRICE          NUMERIC(30, 14),
   AVERAGE_PRICE        NUMERIC(30, 14),
   MAX_PRICE            NUMERIC(30, 14),
   MIN_PRICE            NUMERIC(30, 14),
   BUY_ONE_PRICE        NUMERIC(30, 14),
   SELL_ONE_PRICE       NUMERIC(30, 14),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MST_FUND_MARKET_INFO primary key (FUND_CODE, VALUE_SOURCE, VALUE_DATE)
);
comment on table MST_FUND_MARKET_INFO is
'基金市场行情';
comment on column MST_FUND_MARKET_INFO.FUND_CODE is'基金代码';
comment on column MST_FUND_MARKET_INFO.VALUE_SOURCE is'估值来源';
comment on column MST_FUND_MARKET_INFO.VALUE_DATE is'估值日';
comment on column MST_FUND_MARKET_INFO.CLOSE_PRICE is'收盘价';
comment on column MST_FUND_MARKET_INFO.AVERAGE_PRICE is'平均价';
comment on column MST_FUND_MARKET_INFO.MAX_PRICE is'最高价';
comment on column MST_FUND_MARKET_INFO.MIN_PRICE is'最低价';
comment on column MST_FUND_MARKET_INFO.BUY_ONE_PRICE is'买一价';
comment on column MST_FUND_MARKET_INFO.SELL_ONE_PRICE is'卖一价';
comment on column MST_FUND_MARKET_INFO.CREATE_USER is'创建人';
comment on column MST_FUND_MARKET_INFO.CREATE_DEPT is'创建部门';
comment on column MST_FUND_MARKET_INFO.CREATE_TIME is'创建时间';
comment on column MST_FUND_MARKET_INFO.UPDATE_USER is'更新人';
comment on column MST_FUND_MARKET_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: MST_HOLIDAY                                           */
/*==============================================================*/
create table MST_HOLIDAY 
(
   CALENDAR_ID          VARCHAR(32)          not null,
   CDATE                DATE                 not null,
   IS_HOLIDAY           NUMERIC(10),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MST_HOLIDAY primary key (CALENDAR_ID, CDATE)
);
comment on table MST_HOLIDAY is
'交易场所日历';
comment on column MST_HOLIDAY.CALENDAR_ID is'日历编号';
comment on column MST_HOLIDAY.CDATE is'日期';
comment on column MST_HOLIDAY.IS_HOLIDAY is'是否节假日';
comment on column MST_HOLIDAY.CREATE_USER is'创建人';
comment on column MST_HOLIDAY.CREATE_DEPT is'创建部门';
comment on column MST_HOLIDAY.CREATE_TIME is'创建时间';
comment on column MST_HOLIDAY.UPDATE_USER is'更新人';
comment on column MST_HOLIDAY.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: MST_INDEX_INFO                                        */
/*==============================================================*/
create table MST_INDEX_INFO 
(
   BENCHMARK_NO         VARCHAR(32)          not null,
   INT_RATE_ID          VARCHAR(50),
   BENCHMARK_NAME       VARCHAR(200),
   constraint PK_MST_INDEX_INFO primary key (BENCHMARK_NO)
);
comment on table MST_INDEX_INFO is
'基准信息';
comment on column MST_INDEX_INFO.BENCHMARK_NO is'基准编号';
comment on column MST_INDEX_INFO.INT_RATE_ID is'基准类型';
comment on column MST_INDEX_INFO.BENCHMARK_NAME is'基准名称';
/*==============================================================*/
/* Table: MST_INT_RATE_INFO                                     */
/*==============================================================*/
create table MST_INT_RATE_INFO 
(
   INT_RATE_ID          VARCHAR(32)          not null,
   EFF_DATE             DATE                 not null,
   RELAESE_DATE         DATE,
   UPDATE_TIME          TIMESTAMP            not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   INT_RATE             NUMERIC(30, 14),
   constraint PK_MST_INT_RATE_INFO primary key (INT_RATE_ID, EFF_DATE)
);
comment on table MST_INT_RATE_INFO is
'利率行情';
comment on column MST_INT_RATE_INFO.INT_RATE_ID is'行情代码';
comment on column MST_INT_RATE_INFO.EFF_DATE is'生效日';
comment on column MST_INT_RATE_INFO.RELAESE_DATE is'发布日';
comment on column MST_INT_RATE_INFO.UPDATE_TIME is'更新时间';
comment on column MST_INT_RATE_INFO.CREATE_USER is'创建人';
comment on column MST_INT_RATE_INFO.CREATE_DEPT is'创建部门';
comment on column MST_INT_RATE_INFO.CREATE_TIME is'创建时间';
comment on column MST_INT_RATE_INFO.UPDATE_USER is'更新人';
comment on column MST_INT_RATE_INFO.INT_RATE is'利率';
/*==============================================================*/
/* Table: MST_RATE_INFO                                         */
/*==============================================================*/
create table MST_RATE_INFO 
(
   RATE_ID              VARCHAR(32)          not null,
   RATE_NAME            VARCHAR(32)          not null,
   RATE_TYPE            VARCHAR(50)          not null,
   CALENDAR_ID          VARCHAR(32),
   constraint PK_MST_RATE_INFO primary key (RATE_ID)
);
comment on table MST_RATE_INFO is
'行情信息';
comment on column MST_RATE_INFO.RATE_ID is'行情代码';
comment on column MST_RATE_INFO.RATE_NAME is'行情名称';
comment on column MST_RATE_INFO.RATE_TYPE is'行情类型';
comment on column MST_RATE_INFO.CALENDAR_ID is'关联日历';
/*==============================================================*/
/* Table: MST_SHARE_PRICE                                       */
/*==============================================================*/
create table MST_SHARE_PRICE 
(
   SHARES_ID            VARCHAR(32)          not null,
   DEAL_DATE            DATE                 not null,
   PRE_CLOSE_PRICE      NUMERIC(30, 14),
   OPEN_PRICE           NUMERIC(30, 14),
   HIGH_PRICE           NUMERIC(30, 14),
   LOW_PRICE            NUMERIC(30, 14),
   CLOSE_PRICE          NUMERIC(30, 14),
   TRADE_STATUS         VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MST_SHARE_PRICE primary key (SHARES_ID, DEAL_DATE)
);
comment on table MST_SHARE_PRICE is
'股票行情';
comment on column MST_SHARE_PRICE.SHARES_ID is'股票代码';
comment on column MST_SHARE_PRICE.DEAL_DATE is'交易日';
comment on column MST_SHARE_PRICE.PRE_CLOSE_PRICE is'前收盘价';
comment on column MST_SHARE_PRICE.OPEN_PRICE is'开盘价';
comment on column MST_SHARE_PRICE.HIGH_PRICE is'最高价';
comment on column MST_SHARE_PRICE.LOW_PRICE is'最低价';
comment on column MST_SHARE_PRICE.CLOSE_PRICE is'收盘价';
comment on column MST_SHARE_PRICE.TRADE_STATUS is'交易状态';
comment on column MST_SHARE_PRICE.CREATE_USER is'创建人';
comment on column MST_SHARE_PRICE.CREATE_DEPT is'创建部门';
comment on column MST_SHARE_PRICE.CREATE_TIME is'创建时间';
comment on column MST_SHARE_PRICE.UPDATE_USER is'更新人';
comment on column MST_SHARE_PRICE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: MST_TRADE_CALENDAR                                    */
/*==============================================================*/
create table MST_TRADE_CALENDAR 
(
   TRADE_CALENDAR_ID    VARCHAR(32)          not null,
   CALENDAR_ID          VARCHAR(32)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MST_TRADE_CALENDAR primary key (TRADE_CALENDAR_ID, CALENDAR_ID)
);
comment on table MST_TRADE_CALENDAR is
'交易日历';
comment on column MST_TRADE_CALENDAR.TRADE_CALENDAR_ID is'交易日历代码';
comment on column MST_TRADE_CALENDAR.CALENDAR_ID is'日历编码';
comment on column MST_TRADE_CALENDAR.CREATE_USER is'创建人';
comment on column MST_TRADE_CALENDAR.CREATE_DEPT is'创建部门';
comment on column MST_TRADE_CALENDAR.CREATE_TIME is'创建时间';
comment on column MST_TRADE_CALENDAR.UPDATE_USER is'更新人';
comment on column MST_TRADE_CALENDAR.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: MST_YIELD_CURVE_INFO                                  */
/*==============================================================*/
create table MST_YIELD_CURVE_INFO 
(
   CURVE_DATE           DATE                 not null,
   CURVE_CODE           VARCHAR(32)          not null,
   CURVE_NAME           VARCHAR(200),
   KEY_POINT            VARCHAR(10),
   YIELD                NUMERIC(30, 14),
   CURVE_TYPE           VARCHAR(50),
   N                    NUMERIC(30, 14),
   K                    NUMERIC(30, 14),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MST_YIELD_CURVE_INFO primary key (CURVE_DATE, CURVE_CODE)
);
comment on table MST_YIELD_CURVE_INFO is
'收益率曲线信息';
comment on column MST_YIELD_CURVE_INFO.CURVE_DATE is'曲线日期';
comment on column MST_YIELD_CURVE_INFO.CURVE_CODE is'曲线代码';
comment on column MST_YIELD_CURVE_INFO.CURVE_NAME is'曲线名称';
comment on column MST_YIELD_CURVE_INFO.KEY_POINT is'关键年限点';
comment on column MST_YIELD_CURVE_INFO.YIELD is'收益率';
comment on column MST_YIELD_CURVE_INFO.CURVE_TYPE is'曲线类型';
comment on column MST_YIELD_CURVE_INFO.N is'N';
comment on column MST_YIELD_CURVE_INFO.K is'K';
comment on column MST_YIELD_CURVE_INFO.CREATE_USER is'创建人';
comment on column MST_YIELD_CURVE_INFO.CREATE_DEPT is'创建部门';
comment on column MST_YIELD_CURVE_INFO.CREATE_TIME is'创建时间';
comment on column MST_YIELD_CURVE_INFO.UPDATE_USER is'更新人';
comment on column MST_YIELD_CURVE_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: STL_CAPACCT_INFO                                      */
/*==============================================================*/
create table STL_CAPACCT_INFO 
(
   ACCT_NO              VARCHAR(32)          not null,
   CCY                  VARCHAR(50)          not null,
   ACCT_NAME            VARCHAR(200),
   BANK_DEP             VARCHAR(50),
   CUSTOMER_ID          VARCHAR(32),
   IN_OUT_BANK          VARCHAR(50),
   CORR_NO              VARCHAR(200),
   LARGE_PAYNO          VARCHAR(200),
   BLN_AREA             VARCHAR(50),
   IS_DVP               VARCHAR(50),
   IS_OVERDRAW          VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_STL_CAPACCT_INFO primary key (ACCT_NO, CCY)
);
comment on table STL_CAPACCT_INFO is
'清算账户表';
comment on column STL_CAPACCT_INFO.ACCT_NO is'账号';
comment on column STL_CAPACCT_INFO.CCY is'币种';
comment on column STL_CAPACCT_INFO.ACCT_NAME is'户名';
comment on column STL_CAPACCT_INFO.BANK_DEP is'开户行';
comment on column STL_CAPACCT_INFO.CUSTOMER_ID is'客户代码';
comment on column STL_CAPACCT_INFO.IN_OUT_BANK is'行内行外';
comment on column STL_CAPACCT_INFO.CORR_NO is'联行行号';
comment on column STL_CAPACCT_INFO.LARGE_PAYNO is'大额支付系统号';
comment on column STL_CAPACCT_INFO.BLN_AREA is'境内境外';
comment on column STL_CAPACCT_INFO.IS_DVP is'是否DVP清算';
comment on column STL_CAPACCT_INFO.IS_OVERDRAW is'是否允许透支';
comment on column STL_CAPACCT_INFO.CREATE_USER is'创建人';
comment on column STL_CAPACCT_INFO.CREATE_DEPT is'创建部门';
comment on column STL_CAPACCT_INFO.CREATE_TIME is'创建时间';
comment on column STL_CAPACCT_INFO.UPDATE_USER is'更新人';
comment on column STL_CAPACCT_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: STL_FIN_PRODUCT_ACCT                                  */
/*==============================================================*/
create table STL_FIN_PRODUCT_ACCT 
(
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   ACCT_NO              VARCHAR(32)          not null,
   CCY                  VARCHAR(50)          not null,
   ACCT_TYPE            VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_STL_FIN_PRODUCT_ACCT primary key (FINPROD_ID, FINPROD_TYPE, ACCT_NO, CCY)
);
comment on table STL_FIN_PRODUCT_ACCT is
'金融产品账户表';
comment on column STL_FIN_PRODUCT_ACCT.FINPROD_ID is'金融产品代码';
comment on column STL_FIN_PRODUCT_ACCT.FINPROD_TYPE is'金融产品类型';
comment on column STL_FIN_PRODUCT_ACCT.ACCT_NO is'账号';
comment on column STL_FIN_PRODUCT_ACCT.CCY is'币种';
comment on column STL_FIN_PRODUCT_ACCT.ACCT_TYPE is'账户类型';
comment on column STL_FIN_PRODUCT_ACCT.CREATE_USER is'创建人';
comment on column STL_FIN_PRODUCT_ACCT.CREATE_DEPT is'创建部门';
comment on column STL_FIN_PRODUCT_ACCT.CREATE_TIME is'创建时间';
comment on column STL_FIN_PRODUCT_ACCT.UPDATE_USER is'更新人';
comment on column STL_FIN_PRODUCT_ACCT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: STL_ORDERS                                            */
/*==============================================================*/
create table STL_ORDERS 
(
   ORDER_ID             VARCHAR(32)          not null,
   BUSI_TYPE            VARCHAR(50),
   ORDER_TYPE           VARCHAR(50),
   ORDER_NUM            NUMERIC(10),
   SETTLE_DATE          DATE,
   TRADE_ID             VARCHAR(32),
   MY_ACCT_NO           VARCHAR(32),
   PS                   VARCHAR(50),
   COUNTER_ACCT_NO      VARCHAR(32),
   SETTLE_AMT           NUMERIC(30, 2),
   CCY                  VARCHAR(50),
   SETTLE_STATUS        VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_STL_ORDERS primary key (ORDER_ID)
);
comment on table STL_ORDERS is
'结算指令表';
comment on column STL_ORDERS.ORDER_ID is'指令编号';
comment on column STL_ORDERS.BUSI_TYPE is'业务类型';
comment on column STL_ORDERS.ORDER_TYPE is'指令类型';
comment on column STL_ORDERS.ORDER_NUM is'指令序号';
comment on column STL_ORDERS.SETTLE_DATE is'结算日期';
comment on column STL_ORDERS.TRADE_ID is'交易编号';
comment on column STL_ORDERS.MY_ACCT_NO is'本方资金账户';
comment on column STL_ORDERS.PS is'收付方向';
comment on column STL_ORDERS.COUNTER_ACCT_NO is'对方资金账户';
comment on column STL_ORDERS.SETTLE_AMT is'结算金额';
comment on column STL_ORDERS.CCY is'币种';
comment on column STL_ORDERS.SETTLE_STATUS is'结算状态';
comment on column STL_ORDERS.CREATE_USER is'创建人';
comment on column STL_ORDERS.CREATE_DEPT is'创建部门';
comment on column STL_ORDERS.CREATE_TIME is'创建时间';
comment on column STL_ORDERS.UPDATE_USER is'更新人';
comment on column STL_ORDERS.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: STL_ORDERS_EXT                                        */
/*==============================================================*/
create table STL_ORDERS_EXT 
(
   ORDER_ID             VARCHAR(32)          not null,
   IS_TRADE             VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_STL_ORDERS_EXT primary key (ORDER_ID)
);
comment on table STL_ORDERS_EXT is
'结算指令扩展表';
comment on column STL_ORDERS_EXT.ORDER_ID is'指令编号';
comment on column STL_ORDERS_EXT.IS_TRADE is'是否随交易出账';
comment on column STL_ORDERS_EXT.CREATE_USER is'创建人';
comment on column STL_ORDERS_EXT.CREATE_DEPT is'创建部门';
comment on column STL_ORDERS_EXT.CREATE_TIME is'创建时间';
comment on column STL_ORDERS_EXT.UPDATE_USER is'更新人';
comment on column STL_ORDERS_EXT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: STL_SEC_MANAGE_ACCT                                   */
/*==============================================================*/
create table STL_SEC_MANAGE_ACCT 
(
   SEC_MANAGE_ACCT_ID   VARCHAR(32)          not null,
   SEC_MANAGE_ACCT_NAME VARCHAR(32),
   CREATE_USER          VARCHAR(32)          not null,
   CREATE_DEPT          VARCHAR(20),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_STL_SEC_MANAGE_ACCT primary key (SEC_MANAGE_ACCT_ID)
);
comment on table STL_SEC_MANAGE_ACCT is
'证券管理户';
comment on column STL_SEC_MANAGE_ACCT.SEC_MANAGE_ACCT_ID is'证券管理户代码';
comment on column STL_SEC_MANAGE_ACCT.SEC_MANAGE_ACCT_NAME is'证券管理户名称';
comment on column STL_SEC_MANAGE_ACCT.CREATE_USER is'创建人';
comment on column STL_SEC_MANAGE_ACCT.CREATE_DEPT is'创建部门';
comment on column STL_SEC_MANAGE_ACCT.CREATE_TIME is'创建时间';
comment on column STL_SEC_MANAGE_ACCT.UPDATE_USER is'更新人';
comment on column STL_SEC_MANAGE_ACCT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TAX_ADT_SCHEDULE                                      */
/*==============================================================*/
create table TAX_ADT_SCHEDULE 
(
   TAX_ID               VARCHAR(32)          not null,
   SCH_ID               VARCHAR(32),
   ADD_TAX_TYPE         VARCHAR(50),
   TAX_RATE             NUMERIC(30, 14),
   EFF_DATE             DATE,
   CHL_CPCT_ID          VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TAX_ADT_SCHEDULE primary key (TAX_ID)
);
comment on table TAX_ADT_SCHEDULE is
'附加税则表';
comment on column TAX_ADT_SCHEDULE.TAX_ID is'税则代码';
comment on column TAX_ADT_SCHEDULE.SCH_ID is'方案代码';
comment on column TAX_ADT_SCHEDULE.ADD_TAX_TYPE is'附加税类型';
comment on column TAX_ADT_SCHEDULE.TAX_RATE is'应税税率';
comment on column TAX_ADT_SCHEDULE.EFF_DATE is'生效日';
comment on column TAX_ADT_SCHEDULE.CHL_CPCT_ID is'通道协议代码';
comment on column TAX_ADT_SCHEDULE.CREATE_USER is'创建人';
comment on column TAX_ADT_SCHEDULE.CREATE_DEPT is'创建部门';
comment on column TAX_ADT_SCHEDULE.CREATE_TIME is'创建时间';
comment on column TAX_ADT_SCHEDULE.UPDATE_USER is'更新人';
comment on column TAX_ADT_SCHEDULE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TAX_BOK_BALANCE                                       */
/*==============================================================*/
create table TAX_BOK_BALANCE 
(
   SEQ_NO               VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32),
   BOOKSET_DATE         DATE,
   BOK_DETAIL_ID        VARCHAR(32),
   HAPPEN_DATE          DATE,
   BOOK_DATE            DATE,
   O_CCY                VARCHAR(50),
   VAT_TYPE             VARCHAR(50),
   TAX_P_TYPE           VARCHAR(50),
   CALC_TYPE            VARCHAR(50),
   INCOME_SOURCE        VARCHAR(50),
   INCOME_AMT_ALL       NUMERIC(30, 4),
   INCOME_HAPPEN_AMT    NUMERIC(30, 14),
   CARRY_AMT            NUMERIC(30, 14),
   HAPPEN_AMT           NUMERIC(30, 14),
   TAX_AMT              NUMERIC(30, 14),
   BOOK_ORDER           NUMERIC(20),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TAX_BOK_BALANCE primary key (SEQ_NO)
);
comment on table TAX_BOK_BALANCE is
'增值税账务明细余额';
comment on column TAX_BOK_BALANCE.SEQ_NO is'流水';
comment on column TAX_BOK_BALANCE.BOOKSET_ID is'账套代码';
comment on column TAX_BOK_BALANCE.BOOKSET_DATE is'账套日期';
comment on column TAX_BOK_BALANCE.BOK_DETAIL_ID is'账务明细代码';
comment on column TAX_BOK_BALANCE.HAPPEN_DATE is'发生日期';
comment on column TAX_BOK_BALANCE.BOOK_DATE is'入账日期';
comment on column TAX_BOK_BALANCE.O_CCY is'币种';
comment on column TAX_BOK_BALANCE.VAT_TYPE is'增值税类型';
comment on column TAX_BOK_BALANCE.TAX_P_TYPE is'确认标识';
comment on column TAX_BOK_BALANCE.CALC_TYPE is'计税方式';
comment on column TAX_BOK_BALANCE.INCOME_SOURCE is'发生场景';
comment on column TAX_BOK_BALANCE.INCOME_AMT_ALL is'计税收入余额';
comment on column TAX_BOK_BALANCE.INCOME_HAPPEN_AMT is'当日计税收入发生额';
comment on column TAX_BOK_BALANCE.CARRY_AMT is'当日结转累计发生额';
comment on column TAX_BOK_BALANCE.HAPPEN_AMT is'当日发生额';
comment on column TAX_BOK_BALANCE.TAX_AMT is'增值税金额';
comment on column TAX_BOK_BALANCE.BOOK_ORDER is'处理序列';
comment on column TAX_BOK_BALANCE.CREATE_USER is'创建人';
comment on column TAX_BOK_BALANCE.CREATE_DEPT is'创建部门';
comment on column TAX_BOK_BALANCE.CREATE_TIME is'创建时间';
comment on column TAX_BOK_BALANCE.UPDATE_USER is'更新人';
comment on column TAX_BOK_BALANCE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TAX_BOK_BALANCE_SUM                                   */
/*==============================================================*/
create table TAX_BOK_BALANCE_SUM 
(
   SEQ_NO               VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32),
   BOOKSET_DATE         DATE,
   HAPPEN_DATE          DATE,
   BOOK_DATE            DATE,
   O_CCY                VARCHAR(50),
   VAT_TYPE             VARCHAR(50),
   TAX_P_TYPE           VARCHAR(50),
   INCOME_AMT_ALL       NUMERIC(30, 2),
   HAPPEN_AMT           NUMERIC(30, 2),
   TAX_AMT_SUM          NUMERIC(30, 14),
   TAX_AMT              NUMERIC(30, 2),
   BOOK_ORDER           NUMERIC(20),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TAX_BOK_BALANCE_SUM primary key (SEQ_NO)
);
comment on table TAX_BOK_BALANCE_SUM is
'增值税汇总余额';
comment on column TAX_BOK_BALANCE_SUM.SEQ_NO is'流水';
comment on column TAX_BOK_BALANCE_SUM.BOOKSET_ID is'账套代码';
comment on column TAX_BOK_BALANCE_SUM.BOOKSET_DATE is'账套日期';
comment on column TAX_BOK_BALANCE_SUM.HAPPEN_DATE is'发生日期';
comment on column TAX_BOK_BALANCE_SUM.BOOK_DATE is'入账日期';
comment on column TAX_BOK_BALANCE_SUM.O_CCY is'币种';
comment on column TAX_BOK_BALANCE_SUM.VAT_TYPE is'增值税类型';
comment on column TAX_BOK_BALANCE_SUM.TAX_P_TYPE is'确认标识';
comment on column TAX_BOK_BALANCE_SUM.INCOME_AMT_ALL is'计税收入余额';
comment on column TAX_BOK_BALANCE_SUM.HAPPEN_AMT is'当日发生额';
comment on column TAX_BOK_BALANCE_SUM.TAX_AMT_SUM is'计税发生额汇总';
comment on column TAX_BOK_BALANCE_SUM.TAX_AMT is'增值税金额';
comment on column TAX_BOK_BALANCE_SUM.BOOK_ORDER is'处理序列';
comment on column TAX_BOK_BALANCE_SUM.CREATE_USER is'创建人';
comment on column TAX_BOK_BALANCE_SUM.CREATE_DEPT is'创建部门';
comment on column TAX_BOK_BALANCE_SUM.CREATE_TIME is'创建时间';
comment on column TAX_BOK_BALANCE_SUM.UPDATE_USER is'更新人';
comment on column TAX_BOK_BALANCE_SUM.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TAX_BOK_CONFIG                                        */
/*==============================================================*/
create table TAX_BOK_CONFIG 
(
   CONFIG_ID            VARCHAR(32)          not null,
   TABLE_ID             VARCHAR(50),
   SUBJECT_NATURE       VARCHAR(50),
   VAT_TYPE             VARCHAR(50),
   TAX_P_TYPE           VARCHAR(50),
   INCOME_SOURCE        VARCHAR(50),
   IN_OUT               VARCHAR(50),
   CALC_TYPE            VARCHAR(50),
   CD_FLAG              VARCHAR(50),
   CALC_SCALE           VARCHAR(50),
   CALC_SNATURE         VARCHAR(50),
   CALC_BASE            VARCHAR(50),
   IS_CARRT             VARCHAR(50),
   INT_REPAY_HANDLE     VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TAX_BOK_CONFIG primary key (CONFIG_ID)
);
comment on table TAX_BOK_CONFIG is
'税额记账分类配置';
comment on column TAX_BOK_CONFIG.CONFIG_ID is'配置代码';
comment on column TAX_BOK_CONFIG.TABLE_ID is'记账场景(会计事件编码)12';
comment on column TAX_BOK_CONFIG.SUBJECT_NATURE is'发生额科目性质';
comment on column TAX_BOK_CONFIG.VAT_TYPE is'增值税类型';
comment on column TAX_BOK_CONFIG.TAX_P_TYPE is'确认类型';
comment on column TAX_BOK_CONFIG.INCOME_SOURCE is'发生场景';
comment on column TAX_BOK_CONFIG.IN_OUT is'增项/减项';
comment on column TAX_BOK_CONFIG.CALC_TYPE is'计算方式';
comment on column TAX_BOK_CONFIG.CD_FLAG is'发生科目借贷方向';
comment on column TAX_BOK_CONFIG.CALC_SCALE is'占比方式';
comment on column TAX_BOK_CONFIG.CALC_SNATURE is'占比科目科目性质';
comment on column TAX_BOK_CONFIG.CALC_BASE is'占比基数';
comment on column TAX_BOK_CONFIG.IS_CARRT is'金额计入方式';
comment on column TAX_BOK_CONFIG.INT_REPAY_HANDLE is'处理条件';
comment on column TAX_BOK_CONFIG.CREATE_USER is'创建人';
comment on column TAX_BOK_CONFIG.CREATE_DEPT is'创建部门';
comment on column TAX_BOK_CONFIG.CREATE_TIME is'创建时间';
comment on column TAX_BOK_CONFIG.UPDATE_USER is'更新人';
comment on column TAX_BOK_CONFIG.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TAX_BOK_DETAIL                                        */
/*==============================================================*/
create table TAX_BOK_DETAIL 
(
   SEQ_NO               VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32),
   BOOKSET_DATE         DATE,
   BOK_SEQNO            VARCHAR(32),
   BOK_DETAIL_ID        VARCHAR(32),
   HAPPEN_DATE          DATE,
   BOOK_DATE            DATE,
   BOOK_ID              VARCHAR(32),
   BOOK_ORDER           NUMERIC(20),
   O_CCY                VARCHAR(50),
   HAPPEN_AMT           NUMERIC(30, 2),
   TAX_AMT              NUMERIC(30, 14),
   BAL_AMT              NUMERIC(30, 14),
   CARRY_AMT            NUMERIC(30, 14),
   VAT_TYPE             VARCHAR(50),
   TAX_P_TYPE           VARCHAR(50),
   CALC_TYPE            VARCHAR(50),
   TAX_RATE             NUMERIC(30, 14),
   TAX_REG_ID           VARCHAR(32),
   FEE_TYPE             VARCHAR(50),
   INCOME_SOURCE        VARCHAR(50),
   MDATE                DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TAX_BOK_DETAIL primary key (SEQ_NO)
);
comment on table TAX_BOK_DETAIL is
'增值税账务明细流水';
comment on column TAX_BOK_DETAIL.SEQ_NO is'流水';
comment on column TAX_BOK_DETAIL.BOOKSET_ID is'账套代码';
comment on column TAX_BOK_DETAIL.BOOKSET_DATE is'账套日期';
comment on column TAX_BOK_DETAIL.BOK_SEQNO is'账务明细流水';
comment on column TAX_BOK_DETAIL.BOK_DETAIL_ID is'账务明细代码';
comment on column TAX_BOK_DETAIL.HAPPEN_DATE is'发生日期';
comment on column TAX_BOK_DETAIL.BOOK_DATE is'入账日期';
comment on column TAX_BOK_DETAIL.BOOK_ID is'凭证编号';
comment on column TAX_BOK_DETAIL.BOOK_ORDER is'处理序列';
comment on column TAX_BOK_DETAIL.O_CCY is'币种';
comment on column TAX_BOK_DETAIL.HAPPEN_AMT is'发生含税销售额';
comment on column TAX_BOK_DETAIL.TAX_AMT is'当日计税发生额';
comment on column TAX_BOK_DETAIL.BAL_AMT is'计税余额发生额';
comment on column TAX_BOK_DETAIL.CARRY_AMT is'当日结转发生额';
comment on column TAX_BOK_DETAIL.VAT_TYPE is'增值税类型';
comment on column TAX_BOK_DETAIL.TAX_P_TYPE is'确认标识';
comment on column TAX_BOK_DETAIL.CALC_TYPE is'计税方式';
comment on column TAX_BOK_DETAIL.TAX_RATE is'税率';
comment on column TAX_BOK_DETAIL.TAX_REG_ID is'税则代码';
comment on column TAX_BOK_DETAIL.FEE_TYPE is'免税收入类型';
comment on column TAX_BOK_DETAIL.INCOME_SOURCE is'发生场景';
comment on column TAX_BOK_DETAIL.MDATE is'结转区间截止日';
comment on column TAX_BOK_DETAIL.CREATE_USER is'创建人';
comment on column TAX_BOK_DETAIL.CREATE_DEPT is'创建部门';
comment on column TAX_BOK_DETAIL.CREATE_TIME is'创建时间';
comment on column TAX_BOK_DETAIL.UPDATE_USER is'更新人';
comment on column TAX_BOK_DETAIL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TAX_CF_BOK_CONFIG                                     */
/*==============================================================*/
create table TAX_CF_BOK_CONFIG 
(
   SEQ_NO               VARCHAR(32)          not null,
   CONFIG_ID            VARCHAR(32)          not null,
   CONFIG_VALUE         VARCHAR(50),
   BOK_CONFIG_ID        VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TAX_CF_BOK_CONFIG primary key (SEQ_NO)
);
comment on table TAX_CF_BOK_CONFIG is
'计税不启用场景配置,不启用场景挂钩税务配置';
comment on column TAX_CF_BOK_CONFIG.SEQ_NO is'流水';
comment on column TAX_CF_BOK_CONFIG.CONFIG_ID is'配置项代码';
comment on column TAX_CF_BOK_CONFIG.CONFIG_VALUE is'配置项值';
comment on column TAX_CF_BOK_CONFIG.BOK_CONFIG_ID is'记账分类配置';
comment on column TAX_CF_BOK_CONFIG.CREATE_USER is'创建人';
comment on column TAX_CF_BOK_CONFIG.CREATE_DEPT is'创建部门';
comment on column TAX_CF_BOK_CONFIG.CREATE_TIME is'创建时间';
comment on column TAX_CF_BOK_CONFIG.UPDATE_USER is'更新人';
comment on column TAX_CF_BOK_CONFIG.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TAX_CONFIG_VALUE                                      */
/*==============================================================*/
create table TAX_CONFIG_VALUE 
(
   CONFIG_ID            VARCHAR(32)          not null,
   CONFIG_VALUE         VARCHAR2(20),
   REMARK               VARCHAR(1000),
   IS_DEFAULT           VARCHAR(50)          not null,
   STATUS               VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TAX_CONFIG_VALUE primary key (CONFIG_ID, STATUS)
);
comment on table TAX_CONFIG_VALUE is
'税务配置取值范围表';
comment on column TAX_CONFIG_VALUE.CONFIG_ID is'配置项';
comment on column TAX_CONFIG_VALUE.CONFIG_VALUE is'配置项值';
comment on column TAX_CONFIG_VALUE.REMARK is'配置项值说明';
comment on column TAX_CONFIG_VALUE.IS_DEFAULT is'是否默认';
comment on column TAX_CONFIG_VALUE.STATUS is'数据状态';
comment on column TAX_CONFIG_VALUE.CREATE_USER is'创建人';
comment on column TAX_CONFIG_VALUE.CREATE_DEPT is'创建部门';
comment on column TAX_CONFIG_VALUE.CREATE_TIME is'创建时间';
comment on column TAX_CONFIG_VALUE.UPDATE_USER is'更新人';
comment on column TAX_CONFIG_VALUE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TAX_DECLARE_SUM_INFO                                  */
/*==============================================================*/
create table TAX_DECLARE_SUM_INFO 
(
   SEQ_ID               VARCHAR(32)          not null,
   DECLARE_DATE         VARCHAR(20),
   DECLARE_TYPE         VARCHAR(50),
   SUBJECT_NATURE       VARCHAR(32),
   SIGN_FLAG            VARCHAR(50),
   A1                   NUMERIC(30, 2),
   B1                   NUMERIC(30, 2),
   A2                   NUMERIC(30, 2),
   B2                   NUMERIC(30, 2),
   B3                   NUMERIC(30, 2),
   FREE_36FILE          NUMERIC(30, 2),
   FREE_46FILE          NUMERIC(30, 2),
   FREE_70FILE          NUMERIC(30, 2),
   FREE_SEC             NUMERIC(30, 2),
   C1                   NUMERIC(30, 2),
   C2                   NUMERIC(30, 2),
   C3                   NUMERIC(30, 2),
   C4                   NUMERIC(30, 2),
   C5                   NUMERIC(30, 2),
   E1                   NUMERIC(30, 2),
   E2                   NUMERIC(30, 2),
   E3                   NUMERIC(30, 2),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   DECLARE_YEAR         VARCHAR(20),
   constraint PK_TAX_DECLARE_SUM_INFO primary key (SEQ_ID)
);
comment on table TAX_DECLARE_SUM_INFO is
'纳税申报表';
comment on column TAX_DECLARE_SUM_INFO.SEQ_ID is'流水号';
comment on column TAX_DECLARE_SUM_INFO.DECLARE_DATE is'征期';
comment on column TAX_DECLARE_SUM_INFO.DECLARE_TYPE is'汇总维度';
comment on column TAX_DECLARE_SUM_INFO.SUBJECT_NATURE is'账套代码';
comment on column TAX_DECLARE_SUM_INFO.SIGN_FLAG is'收益标识';
comment on column TAX_DECLARE_SUM_INFO.A1 is'应税利息及红利收入-不含税';
comment on column TAX_DECLARE_SUM_INFO.B1 is'利息及红利收入增值税';
comment on column TAX_DECLARE_SUM_INFO.A2 is'金融商品转让损益';
comment on column TAX_DECLARE_SUM_INFO.B2 is'金融商品转让增值税';
comment on column TAX_DECLARE_SUM_INFO.B3 is'金融商品转让入账增值税';
comment on column TAX_DECLARE_SUM_INFO.FREE_36FILE is'免税收入-36号文件';
comment on column TAX_DECLARE_SUM_INFO.FREE_46FILE is'免税收入-46号文件';
comment on column TAX_DECLARE_SUM_INFO.FREE_70FILE is'免税收入-70号文件';
comment on column TAX_DECLARE_SUM_INFO.FREE_SEC is'免税收入-国债及地方债';
comment on column TAX_DECLARE_SUM_INFO.C1 is'本期应交增值税产品划转到银行增值税';
comment on column TAX_DECLARE_SUM_INFO.C2 is'本期应交附加税费产品划转到银行附加税费';
comment on column TAX_DECLARE_SUM_INFO.C3 is'本期应交增值税银行划转到税局金融商品转让增值税';
comment on column TAX_DECLARE_SUM_INFO.C4 is'本期应交增值税银行划转到税局增值税';
comment on column TAX_DECLARE_SUM_INFO.C5 is'本期应交附加税费银行划转到税局附加税费';
comment on column TAX_DECLARE_SUM_INFO.E1 is'本期留存收益变动额';
comment on column TAX_DECLARE_SUM_INFO.E2 is'期初留存收益';
comment on column TAX_DECLARE_SUM_INFO.E3 is'期末留存收益';
comment on column TAX_DECLARE_SUM_INFO.CREATE_USER is'创建人';
comment on column TAX_DECLARE_SUM_INFO.CREATE_DEPT is'创建部门';
comment on column TAX_DECLARE_SUM_INFO.CREATE_TIME is'创建时间';
comment on column TAX_DECLARE_SUM_INFO.UPDATE_USER is'更新人';
comment on column TAX_DECLARE_SUM_INFO.UPDATE_TIME is'更新时间';
comment on column TAX_DECLARE_SUM_INFO.DECLARE_YEAR is'年份';
/*==============================================================*/
/* Table: TAX_TAXABLE_INFO                                      */
/*==============================================================*/
create table TAX_TAXABLE_INFO 
(
   SEQ_NO               VARCHAR(32)          not null,
   SCH_ID               VARCHAR(32),
   ASSET_ID             VARCHAR(50),
   ASSET_CLASSIFY       VARCHAR(50),
   CAPI_INCOME_FEATURE  VARCHAR(50),
   INCOME_TYPE          VARCHAR(50),
   TAXABLE_SIGN         VARCHAR(50),
   TAX_RATE             NUMERIC(30, 14),
   FEE_TYPE             VARCHAR(50),
   EFF_DATE             DATE,
   MAINTENANCE_MODE     VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TAX_TAXABLE_INFO primary key (SEQ_NO)
);
comment on table TAX_TAXABLE_INFO is
'应税信息表';
comment on column TAX_TAXABLE_INFO.SEQ_NO is'流水号';
comment on column TAX_TAXABLE_INFO.SCH_ID is'方案代码';
comment on column TAX_TAXABLE_INFO.ASSET_ID is'资产代码';
comment on column TAX_TAXABLE_INFO.ASSET_CLASSIFY is'资产分类';
comment on column TAX_TAXABLE_INFO.CAPI_INCOME_FEATURE is'本金收益特征';
comment on column TAX_TAXABLE_INFO.INCOME_TYPE is'收入类型';
comment on column TAX_TAXABLE_INFO.TAXABLE_SIGN is'计税方式';
comment on column TAX_TAXABLE_INFO.TAX_RATE is'应税税率';
comment on column TAX_TAXABLE_INFO.FEE_TYPE is'免税收入类型';
comment on column TAX_TAXABLE_INFO.EFF_DATE is'生效日';
comment on column TAX_TAXABLE_INFO.MAINTENANCE_MODE is'维护方式';
comment on column TAX_TAXABLE_INFO.CREATE_USER is'创建人';
comment on column TAX_TAXABLE_INFO.CREATE_DEPT is'创建部门';
comment on column TAX_TAXABLE_INFO.CREATE_TIME is'创建时间';
comment on column TAX_TAXABLE_INFO.UPDATE_USER is'更新人';
comment on column TAX_TAXABLE_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TAX_VAT_SCHEDULE                                      */
/*==============================================================*/
create table TAX_VAT_SCHEDULE 
(
   SEQ_NO               VARCHAR(32)          not null,
   SCH_ID               VARCHAR(32),
   ASSET_CLASSIFY       VARCHAR(50),
   CAPI_INCOME_FEATURE  VARCHAR(50),
   INCOME_TYPE          VARCHAR(50),
   TAXABLE_SIGN         VARCHAR(50),
   TAX_RATE             NUMERIC(30, 14),
   FEE_TYPE             VARCHAR(50),
   EFF_DATE             DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TAX_VAT_SCHEDULE primary key (SEQ_NO)
);
comment on table TAX_VAT_SCHEDULE is
'增值税则表';
comment on column TAX_VAT_SCHEDULE.SEQ_NO is'流水号';
comment on column TAX_VAT_SCHEDULE.SCH_ID is'方案代码';
comment on column TAX_VAT_SCHEDULE.ASSET_CLASSIFY is'资产分类';
comment on column TAX_VAT_SCHEDULE.CAPI_INCOME_FEATURE is'本金收益特征';
comment on column TAX_VAT_SCHEDULE.INCOME_TYPE is'收入类型';
comment on column TAX_VAT_SCHEDULE.TAXABLE_SIGN is'计税方式';
comment on column TAX_VAT_SCHEDULE.TAX_RATE is'应税税率';
comment on column TAX_VAT_SCHEDULE.FEE_TYPE is'免税收入类型';
comment on column TAX_VAT_SCHEDULE.EFF_DATE is'生效日';
comment on column TAX_VAT_SCHEDULE.CREATE_USER is'创建人';
comment on column TAX_VAT_SCHEDULE.CREATE_DEPT is'创建部门';
comment on column TAX_VAT_SCHEDULE.CREATE_TIME is'创建时间';
comment on column TAX_VAT_SCHEDULE.UPDATE_USER is'更新人';
comment on column TAX_VAT_SCHEDULE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TG_TRADE_FEE_DETAIL                                   */
/*==============================================================*/
create table TG_TRADE_FEE_DETAIL 
(
   TRADE_ID             VARCHAR(32)          not null,
   FEE_TYPE             VARCHAR(50)          not null,
   IS_PAY_WITH_TRADE    VARCHAR(50)          not null,
   FEE_AMT              NUMERIC(30, 2),
   FEE_ID               VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TG_TRADE_FEE_DETAIL primary key (TRADE_ID, FEE_TYPE)
);
comment on table TG_TRADE_FEE_DETAIL is
'交易费用明细表';
comment on column TG_TRADE_FEE_DETAIL.TRADE_ID is'交易编号';
comment on column TG_TRADE_FEE_DETAIL.FEE_TYPE is'一次性费用类型';
comment on column TG_TRADE_FEE_DETAIL.IS_PAY_WITH_TRADE is'是否随交易支付';
comment on column TG_TRADE_FEE_DETAIL.FEE_AMT is'费用金额';
comment on column TG_TRADE_FEE_DETAIL.FEE_ID is'费用代码';
comment on column TG_TRADE_FEE_DETAIL.CREATE_USER is'创建人';
comment on column TG_TRADE_FEE_DETAIL.CREATE_DEPT is'创建部门';
comment on column TG_TRADE_FEE_DETAIL.CREATE_TIME is'创建时间';
comment on column TG_TRADE_FEE_DETAIL.UPDATE_USER is'更新人';
comment on column TG_TRADE_FEE_DETAIL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TRD_FIN_PRODUCT_BARTER                                */
/*==============================================================*/
create table TRD_FIN_PRODUCT_BARTER 
(
   TRADE_ID             VARCHAR(32)          not null,
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   BRANCH               NUMERIC(10),
   UNIT_CPRICE          NUMERIC(30, 14),
   PRIN_AMT             NUMERIC(30, 2),
   SHARE_AMT            NUMERIC(30, 4),
   INT_AMT              NUMERIC(30, 2),
   INV_AIM              VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TRD_FIN_PRODUCT_BARTER primary key (TRADE_ID, FINPROD_ID, FINPROD_TYPE)
);
comment on table TRD_FIN_PRODUCT_BARTER is
'金融产品实物交易';
comment on column TRD_FIN_PRODUCT_BARTER.TRADE_ID is'交易编号';
comment on column TRD_FIN_PRODUCT_BARTER.FINPROD_ID is'金融产品代码';
comment on column TRD_FIN_PRODUCT_BARTER.FINPROD_TYPE is'金融产品类型';
comment on column TRD_FIN_PRODUCT_BARTER.BRANCH is'分支序号';
comment on column TRD_FIN_PRODUCT_BARTER.UNIT_CPRICE is'单位净价';
comment on column TRD_FIN_PRODUCT_BARTER.PRIN_AMT is'交易本金';
comment on column TRD_FIN_PRODUCT_BARTER.SHARE_AMT is'交易份额';
comment on column TRD_FIN_PRODUCT_BARTER.INT_AMT is'利息总额';
comment on column TRD_FIN_PRODUCT_BARTER.INV_AIM is'投资目的';
comment on column TRD_FIN_PRODUCT_BARTER.CREATE_USER is'创建人';
comment on column TRD_FIN_PRODUCT_BARTER.CREATE_DEPT is'创建部门';
comment on column TRD_FIN_PRODUCT_BARTER.CREATE_TIME is'创建时间';
comment on column TRD_FIN_PRODUCT_BARTER.UPDATE_USER is'更新人';
comment on column TRD_FIN_PRODUCT_BARTER.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TRD_PRODUCT_DEAL                                      */
/*==============================================================*/
create table TRD_PRODUCT_DEAL 
(
   TRADE_ID             VARCHAR(32)          not null,
   BUSI_TYPE            VARCHAR(50)          not null,
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   BRANCH               NUMERIC(10),
   TRADE_DATE           DATE,
   VDATE                DATE,
   MDATE                DATE,
   SETTLE_DATE          DATE,
   CCY                  VARCHAR(50),
   CPRICE_DATE          DATE,
   UNIT_CPRICE          NUMERIC(30, 14),
   UNIT_INT             NUMERIC(30, 14),
   UNIT_FPRICE          NUMERIC(30, 14),
   PAR_VALUE            NUMERIC(30, 14),
   CPRICE_AMT           NUMERIC(30, 2),
   SHARE_AMT            NUMERIC(30, 4),
   PRIN_AMT             NUMERIC(30, 2),
   INT_AMT              NUMERIC(30, 2),
   FPRICE_AMT           NUMERIC(30, 2),
   FEE_AMT_PAY          NUMERIC(30, 2),
   TRADE_AMT            NUMERIC(30, 2),
   FEE_AMT_UNPAY        NUMERIC(30, 2),
   FEE_AMT              NUMERIC(30, 2),
   PS                   VARCHAR(50),
   YIELD                NUMERIC(30, 14),
   EXER_YIELD           NUMERIC(30, 14),
   INV_AIM              VARCHAR(50),
   TRADE_STATUS         VARCHAR(50),
   P_TRADE_ID           VARCHAR(32),
   IS_CLEAN             VARCHAR(50),
   TOTLE_TRADE_ID       VARCHAR(32),
   R_TRADE_ID           VARCHAR(32),
   CHL_ID               VARCHAR(32),
   COUNTER_ID           VARCHAR(32),
   COUNTER_TYPE         VARCHAR(50),
   TRADE_MARKET         VARCHAR(50),
   TRADER               VARCHAR(32),
   SEC_MANAGE_ACCT_ID   VARCHAR(32),
   F_TRADE_ID           VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TRD_PRODUCT_DEAL primary key (TRADE_ID)
)
partition by list
 (FINPROD_TYPE)
 (partition ASSET_TYPE_DEFAULT
 values (DEFAULT));
comment on table TRD_PRODUCT_DEAL is
'金融产品交易，按资产类型分区';
comment on column TRD_PRODUCT_DEAL.TRADE_ID is'交易编号';
comment on column TRD_PRODUCT_DEAL.BUSI_TYPE is'业务类型';
comment on column TRD_PRODUCT_DEAL.FINPROD_ID is'金融产品代码';
comment on column TRD_PRODUCT_DEAL.FINPROD_TYPE is'金融产品类型';
comment on column TRD_PRODUCT_DEAL.BRANCH is'分支序号';
comment on column TRD_PRODUCT_DEAL.TRADE_DATE is'交易日';
comment on column TRD_PRODUCT_DEAL.VDATE is'起息日';
comment on column TRD_PRODUCT_DEAL.MDATE is'到期日';
comment on column TRD_PRODUCT_DEAL.SETTLE_DATE is'交割日';
comment on column TRD_PRODUCT_DEAL.CCY is'交易币种';
comment on column TRD_PRODUCT_DEAL.CPRICE_DATE is'净值日期';
comment on column TRD_PRODUCT_DEAL.UNIT_CPRICE is'单位净价';
comment on column TRD_PRODUCT_DEAL.UNIT_INT is'单位利息';
comment on column TRD_PRODUCT_DEAL.UNIT_FPRICE is'单位全价';
comment on column TRD_PRODUCT_DEAL.PAR_VALUE is'单位面值';
comment on column TRD_PRODUCT_DEAL.CPRICE_AMT is'净价总额';
comment on column TRD_PRODUCT_DEAL.SHARE_AMT is'交易份额';
comment on column TRD_PRODUCT_DEAL.PRIN_AMT is'交易本金';
comment on column TRD_PRODUCT_DEAL.INT_AMT is'利息总额';
comment on column TRD_PRODUCT_DEAL.FPRICE_AMT is'全价总额';
comment on column TRD_PRODUCT_DEAL.FEE_AMT_PAY is'随交易支付费用';
comment on column TRD_PRODUCT_DEAL.TRADE_AMT is'交易金额';
comment on column TRD_PRODUCT_DEAL.FEE_AMT_UNPAY is'未随交易支付费用';
comment on column TRD_PRODUCT_DEAL.FEE_AMT is'总交易费用';
comment on column TRD_PRODUCT_DEAL.PS is'交易方向';
comment on column TRD_PRODUCT_DEAL.YIELD is'到期收益率';
comment on column TRD_PRODUCT_DEAL.EXER_YIELD is'行权收益率';
comment on column TRD_PRODUCT_DEAL.INV_AIM is'投资目的';
comment on column TRD_PRODUCT_DEAL.TRADE_STATUS is'交易状态';
comment on column TRD_PRODUCT_DEAL.P_TRADE_ID is'原交易编号';
comment on column TRD_PRODUCT_DEAL.IS_CLEAN is'是否结清';
comment on column TRD_PRODUCT_DEAL.TOTLE_TRADE_ID is'汇总交易编号';
comment on column TRD_PRODUCT_DEAL.R_TRADE_ID is'反向交易编号';
comment on column TRD_PRODUCT_DEAL.CHL_ID is'通道代码';
comment on column TRD_PRODUCT_DEAL.COUNTER_ID is'交易对手';
comment on column TRD_PRODUCT_DEAL.COUNTER_TYPE is'交易对手类型';
comment on column TRD_PRODUCT_DEAL.TRADE_MARKET is'交易场所';
comment on column TRD_PRODUCT_DEAL.TRADER is'交易员';
comment on column TRD_PRODUCT_DEAL.SEC_MANAGE_ACCT_ID is'证券管理户代码';
comment on column TRD_PRODUCT_DEAL.F_TRADE_ID is'关联系统交易编号';
comment on column TRD_PRODUCT_DEAL.CREATE_USER is'创建人';
comment on column TRD_PRODUCT_DEAL.CREATE_DEPT is'创建部门';
comment on column TRD_PRODUCT_DEAL.CREATE_TIME is'创建时间';
comment on column TRD_PRODUCT_DEAL.UPDATE_USER is'更新人';
comment on column TRD_PRODUCT_DEAL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TRD_PRODUCT_DEAL_EXT                                  */
/*==============================================================*/
create table TRD_PRODUCT_DEAL_EXT 
(
   TRADE_ID             VARCHAR(32)          not null,
   PORTFOLIO_ID         CHAR(10),
   PROD_ID              VARCHAR(32),
   HAS_ORDER            VARCHAR(50),
   GEN_TYPE             VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TRD_PRODUCT_DEAL_EXT primary key (TRADE_ID)
);
comment on table TRD_PRODUCT_DEAL_EXT is
'估值核算交易扩展表';
comment on column TRD_PRODUCT_DEAL_EXT.TRADE_ID is'交易编号';
comment on column TRD_PRODUCT_DEAL_EXT.PORTFOLIO_ID is'投资组合代码';
comment on column TRD_PRODUCT_DEAL_EXT.PROD_ID is'理财产品代码';
comment on column TRD_PRODUCT_DEAL_EXT.HAS_ORDER is'是否有结算指令';
comment on column TRD_PRODUCT_DEAL_EXT.GEN_TYPE is'生成方式';
comment on column TRD_PRODUCT_DEAL_EXT.CREATE_USER is'创建人';
comment on column TRD_PRODUCT_DEAL_EXT.CREATE_DEPT is'创建部门';
comment on column TRD_PRODUCT_DEAL_EXT.CREATE_TIME is'创建时间';
comment on column TRD_PRODUCT_DEAL_EXT.UPDATE_USER is'更新人';
comment on column TRD_PRODUCT_DEAL_EXT.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TRD_VOUCH_DEAL                                        */
/*==============================================================*/
create table TRD_VOUCH_DEAL 
(
   TRADE_ID             VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32),
   RECORD_TYPE          VARCHAR(50),
   BOOK_DATE            DATE,
   OFFSET_FLAG          VARCHAR(50),
   BUSI_ID              VARCHAR(32),
   BUSI_DETAIL_TYPE     VARCHAR(50),
   BUSI_TYPE            VARCHAR(50),
   BOOK_ID              VARCHAR(32),
   BOOK_TYPE            VARCHAR(50),
   BOOK_REMARK          VARCHAR(200),
   P_TRADE_ID           VARCHAR(32),
   INV_AIM              VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TRD_VOUCH_DEAL primary key (TRADE_ID)
);
comment on table TRD_VOUCH_DEAL is
'手工凭证交易';
comment on column TRD_VOUCH_DEAL.TRADE_ID is'交易编号';
comment on column TRD_VOUCH_DEAL.BOOKSET_ID is'账套代码';
comment on column TRD_VOUCH_DEAL.RECORD_TYPE is'分录类型';
comment on column TRD_VOUCH_DEAL.BOOK_DATE is'记账日期';
comment on column TRD_VOUCH_DEAL.OFFSET_FLAG is'冲销标识';
comment on column TRD_VOUCH_DEAL.BUSI_ID is'业务明细代码';
comment on column TRD_VOUCH_DEAL.BUSI_DETAIL_TYPE is'业务明细类型';
comment on column TRD_VOUCH_DEAL.BUSI_TYPE is'业务类型';
comment on column TRD_VOUCH_DEAL.BOOK_ID is'凭证编号';
comment on column TRD_VOUCH_DEAL.BOOK_TYPE is'凭证类型';
comment on column TRD_VOUCH_DEAL.BOOK_REMARK is'摘要';
comment on column TRD_VOUCH_DEAL.P_TRADE_ID is'原始交易编号';
comment on column TRD_VOUCH_DEAL.INV_AIM is'投资目的';
comment on column TRD_VOUCH_DEAL.CREATE_USER is'创建人';
comment on column TRD_VOUCH_DEAL.CREATE_DEPT is'创建部门';
comment on column TRD_VOUCH_DEAL.CREATE_TIME is'创建时间';
comment on column TRD_VOUCH_DEAL.UPDATE_USER is'更新人';
comment on column TRD_VOUCH_DEAL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: TRD_VOUCH_DEAL_DETAIL                                 */
/*==============================================================*/
create table TRD_VOUCH_DEAL_DETAIL 
(
   SEQ_NO               VARCHAR(32)          not null,
   TRADE_ID             VARCHAR(32),
   BOOKSET_ID           VARCHAR(32),
   BRANCH               NUMERIC(10),
   SUBJECT_NO           VARCHAR(32),
   SUBJECT_NAME         VARCHAR(500),
   DETAIL_SUBJECT_NO_TAIL VARCHAR(32),
   DETAIL_SUBNAME_TAIL  VARCHAR(500),
   O_HAPPEN_AMT         NUMERIC(30, 2),
   CD_FLAG              VARCHAR(50),
   CCY                  VARCHAR(50),
   HAPPEN_NUMBER        NUMERIC(30, 4),
   BOK_DETAIL_ID        VARCHAR(32),
   BUSI_ID              VARCHAR(32),
   BUSI_DETAIL_TYPE     VARCHAR(50),
   INV_AIM              VARCHAR(50),
   SEC_MANAGE_ACCT_ID   VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TRD_VOUCH_DEAL_DETAIL primary key (SEQ_NO)
);
comment on table TRD_VOUCH_DEAL_DETAIL is
'手工凭证交易明细';
comment on column TRD_VOUCH_DEAL_DETAIL.SEQ_NO is'流水';
comment on column TRD_VOUCH_DEAL_DETAIL.TRADE_ID is'交易编号';
comment on column TRD_VOUCH_DEAL_DETAIL.BOOKSET_ID is'账套代码';
comment on column TRD_VOUCH_DEAL_DETAIL.BRANCH is'分支序号';
comment on column TRD_VOUCH_DEAL_DETAIL.SUBJECT_NO is'科目号';
comment on column TRD_VOUCH_DEAL_DETAIL.SUBJECT_NAME is'科目名称';
comment on column TRD_VOUCH_DEAL_DETAIL.DETAIL_SUBJECT_NO_TAIL is'科目尾号';
comment on column TRD_VOUCH_DEAL_DETAIL.DETAIL_SUBNAME_TAIL is'尾号名称';
comment on column TRD_VOUCH_DEAL_DETAIL.O_HAPPEN_AMT is'金额';
comment on column TRD_VOUCH_DEAL_DETAIL.CD_FLAG is'借贷方向';
comment on column TRD_VOUCH_DEAL_DETAIL.CCY is'币种';
comment on column TRD_VOUCH_DEAL_DETAIL.HAPPEN_NUMBER is'数量';
comment on column TRD_VOUCH_DEAL_DETAIL.BOK_DETAIL_ID is'账务明细代码';
comment on column TRD_VOUCH_DEAL_DETAIL.BUSI_ID is'业务明细代码';
comment on column TRD_VOUCH_DEAL_DETAIL.BUSI_DETAIL_TYPE is'业务明细类型';
comment on column TRD_VOUCH_DEAL_DETAIL.INV_AIM is'投资目的';
comment on column TRD_VOUCH_DEAL_DETAIL.SEC_MANAGE_ACCT_ID is'证券托管户代码';
comment on column TRD_VOUCH_DEAL_DETAIL.CREATE_USER is'创建人';
comment on column TRD_VOUCH_DEAL_DETAIL.CREATE_DEPT is'创建部门';
comment on column TRD_VOUCH_DEAL_DETAIL.CREATE_TIME is'创建时间';
comment on column TRD_VOUCH_DEAL_DETAIL.UPDATE_USER is'更新人';
comment on column TRD_VOUCH_DEAL_DETAIL.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: VAL_INTERFLOG                                         */
/*==============================================================*/
create table VAL_INTERFLOG 
(
   TMP_NAME             VARCHAR(50)          not null,
   FILE_NAME            VARCHAR(1000)        not null,
   EX_DATE              TIMESTAMP            not null,
   NUM                  NUMERIC(10)          not null,
   IS_CHECK             VARCHAR(50)          not null,
   DETAIL               VARCHAR(1000),
   REMARK               VARCHAR(1000),
   PARENT_SEQNO         VARCHAR(32),
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(20),
   CREATE_TIME          TIMESTAMP,
   UPDATE_USER          VARCHAR(20),
   UPDATE_TIME          TIMESTAMP,
   constraint PK_VAL_INTERFLOG primary key (TMP_NAME, FILE_NAME, EX_DATE, NUM)
);
comment on table VAL_INTERFLOG is
'接口-日志表';
comment on column VAL_INTERFLOG.TMP_NAME is'模板名称';
comment on column VAL_INTERFLOG.FILE_NAME is'文件名称';
comment on column VAL_INTERFLOG.EX_DATE is'日期';
comment on column VAL_INTERFLOG.NUM is'明细记录编号';
comment on column VAL_INTERFLOG.IS_CHECK is'是否通过校验';
comment on column VAL_INTERFLOG.DETAIL is'记录内容';
comment on column VAL_INTERFLOG.REMARK is'备注';
comment on column VAL_INTERFLOG.PARENT_SEQNO is'行为日志ID';
comment on column VAL_INTERFLOG.CREATE_USER is'创建人';
comment on column VAL_INTERFLOG.CREATE_DEPT is'创建部门';
comment on column VAL_INTERFLOG.CREATE_TIME is'创建时间';
comment on column VAL_INTERFLOG.UPDATE_USER is'更新人';
comment on column VAL_INTERFLOG.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: VAL_INTERF_ACTIONLOG                                  */
/*==============================================================*/
create table VAL_INTERF_ACTIONLOG 
(
   SEQNO                VARCHAR(32)          not null,
   BUSI_DATE            DATE,
   ORDERNO              NUMERIC(10),
   TASK_ENAME           VARCHAR(50),
   START_TIME           TIMESTAMP,
   END_TIME             TIMESTAMP,
   STATUS               VARCHAR(50),
   EXEC_RESULT          VARCHAR(4000),
   LAST_TIME            NUMERIC(10),
   DETAIL_ACTION        CLOB,
   CREATE_TIME          TIMESTAMP,
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(20),
   UPDATE_TIME          TIMESTAMP,
   UPDATE_USER          VARCHAR(20),
   EXECTASK_SEQNO       VARCHAR2(32 BYTE),
   constraint PK_VAL_INTERF_ACTIONLOG primary key (SEQNO)
);
comment on table VAL_INTERF_ACTIONLOG is
'接口-行为日志';
comment on column VAL_INTERF_ACTIONLOG.SEQNO is'主键';
comment on column VAL_INTERF_ACTIONLOG.BUSI_DATE is'业务日期';
comment on column VAL_INTERF_ACTIONLOG.ORDERNO is'批次号';
comment on column VAL_INTERF_ACTIONLOG.TASK_ENAME is'任务组英文名称';
comment on column VAL_INTERF_ACTIONLOG.START_TIME is'开始时间';
comment on column VAL_INTERF_ACTIONLOG.END_TIME is'结束时间';
comment on column VAL_INTERF_ACTIONLOG.STATUS is'执行状态';
comment on column VAL_INTERF_ACTIONLOG.EXEC_RESULT is'执行结果';
comment on column VAL_INTERF_ACTIONLOG.LAST_TIME is'执行时长';
comment on column VAL_INTERF_ACTIONLOG.DETAIL_ACTION is'执行明细';
comment on column VAL_INTERF_ACTIONLOG.CREATE_TIME is'创建时间';
comment on column VAL_INTERF_ACTIONLOG.CREATE_USER is'创建人';
comment on column VAL_INTERF_ACTIONLOG.CREATE_DEPT is'创建部门';
comment on column VAL_INTERF_ACTIONLOG.UPDATE_TIME is'更新时间';
comment on column VAL_INTERF_ACTIONLOG.UPDATE_USER is'更新人';
comment on column VAL_INTERF_ACTIONLOG.EXECTASK_SEQNO is'任务序号';
/*==============================================================*/
/* Table: VAL_INTERF_DEFINE                                     */
/*==============================================================*/
create table VAL_INTERF_DEFINE 
(
   INTERF_ID            VARCHAR(32)          not null,
   INTERF_C_NAME        VARCHAR(200),
   INTERF_E_NAME        VARCHAR(200)         not null,
   ORDERNO              NUMERIC(10)          not null,
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(20),
   CREATE_TIME          TIMESTAMP,
   UPDATE_USER          VARCHAR(20),
   UPDATE_TIME          TIMESTAMP,
   constraint PK_VAL_INTERF_DEFINE primary key (INTERF_ID)
);
comment on table VAL_INTERF_DEFINE is
'接口-接口定义表';
comment on column VAL_INTERF_DEFINE.INTERF_ID is'接口代码';
comment on column VAL_INTERF_DEFINE.INTERF_C_NAME is'接口中文名称';
comment on column VAL_INTERF_DEFINE.INTERF_E_NAME is'接口英文名称';
comment on column VAL_INTERF_DEFINE.ORDERNO is'接口执行批次';
comment on column VAL_INTERF_DEFINE.CREATE_USER is'创建人';
comment on column VAL_INTERF_DEFINE.CREATE_DEPT is'创建部门';
comment on column VAL_INTERF_DEFINE.CREATE_TIME is'创建时间';
comment on column VAL_INTERF_DEFINE.UPDATE_USER is'更新人';
comment on column VAL_INTERF_DEFINE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: VAL_INTERF_DTO_MATCH                                  */
/*==============================================================*/
create table VAL_INTERF_DTO_MATCH 
(
   TMP_C_NAME           VARCHAR(100)         not null,
   TMP_E_NAME           VARCHAR(50)          not null,
   DTO_NAME             VARCHAR(50)          not null,
   TMP_COL_E_NAME       VARCHAR(50)          not null,
   TMP_COL_C_NAME       VARCHAR(100)         not null,
   DTO_COL_E_NAME       VARCHAR(50)          not null,
   DTO_COL_C_NAME       VARCHAR(100)         not null,
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(20),
   CREATE_TIME          TIMESTAMP,
   UPDATE_USER          VARCHAR(20),
   UPDATE_TIME          TIMESTAMP,
   constraint PK_VAL_INTERF_DTO_MATCH primary key (TMP_E_NAME, DTO_NAME, TMP_COL_E_NAME)
);
comment on table VAL_INTERF_DTO_MATCH is
'接口-接口模板DTO匹配表';
comment on column VAL_INTERF_DTO_MATCH.TMP_C_NAME is'模版中文名称';
comment on column VAL_INTERF_DTO_MATCH.TMP_E_NAME is'模版英文名称';
comment on column VAL_INTERF_DTO_MATCH.DTO_NAME is'DTO名称';
comment on column VAL_INTERF_DTO_MATCH.TMP_COL_E_NAME is'模版字段英文名';
comment on column VAL_INTERF_DTO_MATCH.TMP_COL_C_NAME is'模版字段中文名';
comment on column VAL_INTERF_DTO_MATCH.DTO_COL_E_NAME is'DTO字段英文名';
comment on column VAL_INTERF_DTO_MATCH.DTO_COL_C_NAME is'DTO字段中文名';
comment on column VAL_INTERF_DTO_MATCH.CREATE_USER is'创建人';
comment on column VAL_INTERF_DTO_MATCH.CREATE_DEPT is'创建部门';
comment on column VAL_INTERF_DTO_MATCH.CREATE_TIME is'创建时间';
comment on column VAL_INTERF_DTO_MATCH.UPDATE_USER is'更新人';
comment on column VAL_INTERF_DTO_MATCH.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: VAL_INTERF_SERVER_INFO                                */
/*==============================================================*/
create table VAL_INTERF_SERVER_INFO 
(
   SERVERIP             VARCHAR(32)          not null,
   SERVERPORT           VARCHAR(32)          not null,
   USERNAME             VARCHAR(32)          not null,
   PASSWORD             VARCHAR(32)          not null,
   SERVERTYPE           VARCHAR(32)          not null,
   STATUS               VARCHAR(50),
   SYSTEMID             VARCHAR(50)          not null,
   REMOTE_PATH          VARCHAR(32),
   LOCAL_PATH           VARCHAR(32),
   RS                   VARCHAR(50),
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(20),
   CREATE_TIME          TIMESTAMP,
   UPDATE_USER          VARCHAR(20),
   UPDATE_TIME          TIMESTAMP,
   constraint PK_VAL_INTERF_SERVER_INFO primary key (SERVERIP, SYSTEMID)
);
comment on table VAL_INTERF_SERVER_INFO is
'接口-接口服务器配置表';
comment on column VAL_INTERF_SERVER_INFO.SERVERIP is'服务器IP';
comment on column VAL_INTERF_SERVER_INFO.SERVERPORT is'服务器端口号';
comment on column VAL_INTERF_SERVER_INFO.USERNAME is'用户名';
comment on column VAL_INTERF_SERVER_INFO.PASSWORD is'服务器登录密码';
comment on column VAL_INTERF_SERVER_INFO.SERVERTYPE is'服务器登录类型';
comment on column VAL_INTERF_SERVER_INFO.STATUS is'状态';
comment on column VAL_INTERF_SERVER_INFO.SYSTEMID is'系统ID';
comment on column VAL_INTERF_SERVER_INFO.REMOTE_PATH is'远程路径';
comment on column VAL_INTERF_SERVER_INFO.LOCAL_PATH is'本地路径';
comment on column VAL_INTERF_SERVER_INFO.RS is'发送和接收';
comment on column VAL_INTERF_SERVER_INFO.CREATE_USER is'创建人';
comment on column VAL_INTERF_SERVER_INFO.CREATE_DEPT is'创建部门';
comment on column VAL_INTERF_SERVER_INFO.CREATE_TIME is'创建时间';
comment on column VAL_INTERF_SERVER_INFO.UPDATE_USER is'更新人';
comment on column VAL_INTERF_SERVER_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: VAL_INTERF_TASK                                       */
/*==============================================================*/
create table VAL_INTERF_TASK 
(
   TASK_ID              VARCHAR(32)          not null,
   BEAN_NAME            VARCHAR(200),
   TASK_NAME            VARCHAR(200),
   ORDERNO              NUMBER(4,0),
   STATUS               CHAR(1 BYTE)         not null,
   RS                   CHAR(1 BYTE)         not null,
   SYSTEMID             VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(20),
   CREATE_TIME          TIMESTAMP,
   UPDATE_USER          VARCHAR(20),
   UPDATE_TIME          TIMESTAMP,
   constraint PK_VAL_INTERF_TASK primary key (TASK_ID)
);
comment on table VAL_INTERF_TASK is
'接口批次信息';
comment on column VAL_INTERF_TASK.TASK_ID is'任务ID';
comment on column VAL_INTERF_TASK.BEAN_NAME is'bean名称';
comment on column VAL_INTERF_TASK.TASK_NAME is'批次名称';
comment on column VAL_INTERF_TASK.ORDERNO is'批次序号';
comment on column VAL_INTERF_TASK.STATUS is'数据有效状态';
comment on column VAL_INTERF_TASK.RS is'发送接收状态';
comment on column VAL_INTERF_TASK.SYSTEMID is'系统账号';
comment on column VAL_INTERF_TASK.CREATE_USER is'创建人';
comment on column VAL_INTERF_TASK.CREATE_DEPT is'创建部门';
comment on column VAL_INTERF_TASK.CREATE_TIME is'创建时间';
comment on column VAL_INTERF_TASK.UPDATE_USER is'更新人';
comment on column VAL_INTERF_TASK.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: VAL_INTERF_TEMPLATE                                   */
/*==============================================================*/
create table VAL_INTERF_TEMPLATE 
(
   TMP_C_NAME           VARCHAR(100)         not null,
   TMP_E_NAME           VARCHAR(50)          not null,
   DTO_NAME             VARCHAR(50),
   INTERF_ID            VARCHAR(32)          not null,
   TMP_TYPE             VARCHAR(12)          not null,
   SYSTEMID             VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(20),
   CREATE_TIME          TIMESTAMP,
   UPDATE_USER          VARCHAR(20),
   UPDATE_TIME          TIMESTAMP,
   constraint PK_VAL_INTERF_TEMPLATE primary key (TMP_E_NAME, INTERF_ID)
);
comment on table VAL_INTERF_TEMPLATE is
'接口-接口模版管理表';
comment on column VAL_INTERF_TEMPLATE.TMP_C_NAME is'模版中文名称';
comment on column VAL_INTERF_TEMPLATE.TMP_E_NAME is'模版英文名称';
comment on column VAL_INTERF_TEMPLATE.DTO_NAME is'DTO名称';
comment on column VAL_INTERF_TEMPLATE.INTERF_ID is'接口代码';
comment on column VAL_INTERF_TEMPLATE.TMP_TYPE is'模板类型';
comment on column VAL_INTERF_TEMPLATE.SYSTEMID is'系统类型';
comment on column VAL_INTERF_TEMPLATE.CREATE_USER is'创建人';
comment on column VAL_INTERF_TEMPLATE.CREATE_DEPT is'创建部门';
comment on column VAL_INTERF_TEMPLATE.CREATE_TIME is'创建时间';
comment on column VAL_INTERF_TEMPLATE.UPDATE_USER is'更新人';
comment on column VAL_INTERF_TEMPLATE.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* Table: VAL_TEMP_COL_CHECK_INFO                               */
/*==============================================================*/
create table VAL_TEMP_COL_CHECK_INFO 
(
   TMP_C_NAME           VARCHAR(100)         not null,
   TMP_E_NAME           VARCHAR(50)          not null,
   COL_C_NAME           VARCHAR(50)          not null,
   COL_E_NAME           VARCHAR(32)          not null,
   COL_TYPE             VARCHAR(2),
   IS_NULL              VARCHAR(2),
   MAX_LENGTH           NUMERIC(10),
   IS_EXIST             VARCHAR(2),
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(20),
   CREATE_TIME          TIMESTAMP,
   UPDATE_USER          VARCHAR(20),
   UPDATE_TIME          TIMESTAMP,
   constraint PK_VAL_TEMP_COL_CHECK_INFO primary key (TMP_E_NAME, COL_E_NAME)
);
comment on table VAL_TEMP_COL_CHECK_INFO is
'接口-模板字段校验信息配置表';
comment on column VAL_TEMP_COL_CHECK_INFO.TMP_C_NAME is'模板中文名';
comment on column VAL_TEMP_COL_CHECK_INFO.TMP_E_NAME is'模板英文名';
comment on column VAL_TEMP_COL_CHECK_INFO.COL_C_NAME is'字段中文名';
comment on column VAL_TEMP_COL_CHECK_INFO.COL_E_NAME is'字段英文名';
comment on column VAL_TEMP_COL_CHECK_INFO.COL_TYPE is'字段类型';
comment on column VAL_TEMP_COL_CHECK_INFO.IS_NULL is'非空判断';
comment on column VAL_TEMP_COL_CHECK_INFO.MAX_LENGTH is'长度';
comment on column VAL_TEMP_COL_CHECK_INFO.IS_EXIST is'存在性';
comment on column VAL_TEMP_COL_CHECK_INFO.CREATE_USER is'创建人';
comment on column VAL_TEMP_COL_CHECK_INFO.CREATE_DEPT is'创建部门';
comment on column VAL_TEMP_COL_CHECK_INFO.CREATE_TIME is'创建时间';
comment on column VAL_TEMP_COL_CHECK_INFO.UPDATE_USER is'更新人';
comment on column VAL_TEMP_COL_CHECK_INFO.UPDATE_TIME is'更新时间';
/*==============================================================*/
/* View: VAL_VW_MST_HOLIDAY                                     */
/*==============================================================*/
create or replace view VAL_VW_MST_HOLIDAY as
SELECT
    c.CDATE,
    a.TRADE_CALENDAR_ID,
    SUM(c.IS_HOLIDAY) AS IS_HOLIDAY
FROM
    MST_TRADE_CALENDAR a
INNER JOIN
    MST_CALENDAR_INFO b
ON
    a.CALENDAR_ID = b.CALENDAR_ID
INNER JOIN
    MST_HOLIDAY c
ON
    c.CALENDAR_ID = b.CALENDAR_ID
GROUP BY
    c.CDATE,
    a.TRADE_CALENDAR_ID
with read only;
 comment on table VAL_VW_MST_HOLIDAY is
'交易日历的视图';
comment on column VAL_VW_MST_HOLIDAY.CDATE is
'日期';
comment on column VAL_VW_MST_HOLIDAY.TRADE_CALENDAR_ID is
'交易日历代码';
comment on column VAL_VW_MST_HOLIDAY.IS_HOLIDAY is
'IS_HOLIDAY';
drop table SYS_BATCH_TASK;
/*==============================================================*/
/* Table: SYS_BATCH_TASK                                        */
/*==============================================================*/
create table SYS_BATCH_TASK 
(
   TASK_ID              VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   TASK_NAME            VARCHAR(200),
   FTASK_ID             VARCHAR(32),
   BEAN_NAME            VARCHAR(200),
   TASK_PRI             NUMERIC(10),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_BATCH_TASK primary key (TASK_ID)
);
comment on table SYS_BATCH_TASK is
'批处理任务';
comment on column SYS_BATCH_TASK.TASK_ID is
'任务代码';
comment on column SYS_BATCH_TASK.SYSTEM_CODE is
'系统代码';
comment on column SYS_BATCH_TASK.TASK_NAME is
'任务名称';
comment on column SYS_BATCH_TASK.FTASK_ID is
'父任务代码';
comment on column SYS_BATCH_TASK.BEAN_NAME is
'批处理业务名称';
comment on column SYS_BATCH_TASK.TASK_PRI is
'任务优先级';
comment on column SYS_BATCH_TASK.CREATE_USER is
'创建人';
comment on column SYS_BATCH_TASK.CREATE_DEPT is
'创建部门';
comment on column SYS_BATCH_TASK.CREATE_TIME is
'创建时间';
comment on column SYS_BATCH_TASK.UPDATE_USER is
'更新人';
comment on column SYS_BATCH_TASK.UPDATE_TIME is
'更新时间';