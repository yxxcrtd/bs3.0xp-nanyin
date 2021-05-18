--weipeng: 增加表【资产负债表数据】
/*==============================================================*/
/* Table: VAL_TABLE_BAS                                         */
/*==============================================================*/
create table VAL_TABLE_BAS 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   ITEM_CODE            VARCHAR(32)          not null,
   ITEM_NAME            VARCHAR(200),
   ITEM_TYPE            VARCHAR(50),
   CDATE                DATE                 not null,
   ITEM_AMT             NUMERIC(30, 2),
   constraint PK_VAL_TABLE_BAS primary key (BOOKSET_ID, ITEM_CODE, CDATE)
);

comment on table VAL_TABLE_BAS is
'资产负债表数据';

comment on column VAL_TABLE_BAS.BOOKSET_ID is
'账套代码';

comment on column VAL_TABLE_BAS.ITEM_CODE is
'元素项';

comment on column VAL_TABLE_BAS.ITEM_NAME is
'元素名称';

comment on column VAL_TABLE_BAS.ITEM_TYPE is
'元素类型';

comment on column VAL_TABLE_BAS.CDATE is
'账务日期';

comment on column VAL_TABLE_BAS.ITEM_AMT is
'元素金额';

create index INX_VAL_TABLE_BAS_1 on VAL_TABLE_BAS (BOOKSET_ID, CDATE);
create index INX_VAL_TABLE_BAS_2 on VAL_TABLE_BAS (CDATE);




--weipeng: 增加表【利润表数据】

/*==============================================================*/
/* Table: VAL_TABLE_INS                                         */
/*==============================================================*/
create table VAL_TABLE_INS 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   ITEM_CODE            VARCHAR(32)          not null,
   ITEM_NAME            VARCHAR(200),
   ITEM_TYPE            VARCHAR(50),
   CDATE                DATE                 not null,
   ITEM_AMT             NUMERIC(30, 2),
   ITEM_LEVEL           NUMERIC(10),
   constraint PK_VAL_TABLE_INS primary key (BOOKSET_ID, ITEM_CODE, CDATE)
);

comment on table VAL_TABLE_INS is
'利润表数据';

comment on column VAL_TABLE_INS.BOOKSET_ID is
'账套代码';

comment on column VAL_TABLE_INS.ITEM_CODE is
'元素项';

comment on column VAL_TABLE_INS.ITEM_NAME is
'元素名称';

comment on column VAL_TABLE_INS.ITEM_TYPE is
'元素类型';

comment on column VAL_TABLE_INS.CDATE is
'账务日期';

comment on column VAL_TABLE_INS.ITEM_AMT is
'元素金额';

comment on column VAL_TABLE_INS.ITEM_LEVEL is
'元素层级';

create index INX_VAL_TABLE_INS_1 on VAL_TABLE_INS (BOOKSET_ID, CDATE);
create index INX_VAL_TABLE_INS_2 on VAL_TABLE_INS (CDATE);


--weipeng: 增加表【所有者权益变动表数据】
/*==============================================================*/
/* Table: VAL_TABLE_SCOE                                        */
/*==============================================================*/
create table VAL_TABLE_SCOE 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   ITEM_CODE            VARCHAR(32)          not null,
   ITEM_NAME            VARCHAR(200),
   ITEM_TYPE            VARCHAR(50),
   CDATE                DATE                 not null,
   ITEM1_AMT            NUMERIC(30, 2),
   ITEM2_AMT            NUMERIC(30, 2),
   ITEM3_AMT            NUMERIC(30, 2),
   ITEM4_AMT            NUMERIC(30, 2),
   constraint PK_VAL_TABLE_SCOE primary key (BOOKSET_ID, ITEM_CODE, CDATE)
);

comment on table VAL_TABLE_SCOE is
'所有者权益变动表数据';

comment on column VAL_TABLE_SCOE.BOOKSET_ID is
'账套代码';

comment on column VAL_TABLE_SCOE.ITEM_CODE is
'元素项';

comment on column VAL_TABLE_SCOE.ITEM_NAME is
'元素名称';

comment on column VAL_TABLE_SCOE.ITEM_TYPE is
'元素类型';

comment on column VAL_TABLE_SCOE.CDATE is
'账务日期';

comment on column VAL_TABLE_SCOE.ITEM1_AMT is
'元素金额1';

comment on column VAL_TABLE_SCOE.ITEM2_AMT is
'元素金额2';

comment on column VAL_TABLE_SCOE.ITEM3_AMT is
'元素金额3';

comment on column VAL_TABLE_SCOE.ITEM4_AMT is
'元素金额4';

create index INX_VAL_TABLE_SCOE_1 on VAL_TABLE_SCOE (BOOKSET_ID, CDATE);
create index INX_VAL_TABLE_SCOE_2 on VAL_TABLE_SCOE (CDATE);
