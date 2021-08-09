drop index "Index_MOD_CURVE_RESULT10";

drop index "Index_MOD_CURVE_RESULT9";

drop table MOD_CURVE_RESULT cascade constraints;

/*==============================================================*/
/* Table: MOD_CURVE_RESULT                                      */
/*==============================================================*/
create table MOD_CURVE_RESULT 
(
   SEQNO                VARCHAR2(50)         not null,
   FINPROD_ID           VARCHAR2(50)         not null,
   CDATE                DATE                 not null,
   FULL_PRICE           NUMBER(30,14)        not null,
   NET_PRICE            NUMBER(30,14)        not null,
   CURVE_SPREAD         NUMBER(30,14),
   CURVE_NAME           VARCHAR2(50),
   CURVE_CODE           VARCHAR2(50),
   BASIS                VARCHAR2(50),
   VAL_MODEL            VARCHAR2(50)         not null,
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MOD_CURVE_RESULT primary key (SEQNO)
);

comment on table MOD_CURVE_RESULT is
'模型估值结果';

comment on column MOD_CURVE_RESULT.SEQNO is
'序号';

comment on column MOD_CURVE_RESULT.FINPROD_ID is
'金融产品代码';

comment on column MOD_CURVE_RESULT.CDATE is
'日期';

comment on column MOD_CURVE_RESULT.FULL_PRICE is
'估值全价';

comment on column MOD_CURVE_RESULT.NET_PRICE is
'估值净价';

comment on column MOD_CURVE_RESULT.CURVE_SPREAD is
'交易点差';

comment on column MOD_CURVE_RESULT.CURVE_NAME is
'曲线名称';

comment on column MOD_CURVE_RESULT.CURVE_CODE is
'评级结果';

comment on column MOD_CURVE_RESULT.BASIS is
'计息基础';

comment on column MOD_CURVE_RESULT.VAL_MODEL is
'估值模型';

comment on column MOD_CURVE_RESULT.CREATE_USER is
'创建人';

comment on column MOD_CURVE_RESULT.CREATE_DEPT is
'创建部门';

comment on column MOD_CURVE_RESULT.CREATE_TIME is
'创建时间';

comment on column MOD_CURVE_RESULT.UPDATE_USER is
'更新人';

comment on column MOD_CURVE_RESULT.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Index: "Index_MOD_CURVE_RESULT9"                                             */
/*==============================================================*/
create index "Index_MOD_CURVE_RESULT9" on MOD_CURVE_RESULT (
   CDATE ASC,
   VAL_MODEL ASC
);

/*==============================================================*/
/* Index: "Index_MOD_CURVE_RESULT10"                                            */
/*==============================================================*/
create index "Index_MOD_CURVE_RESULT10" on MOD_CURVE_RESULT (
   FINPROD_ID ASC,
   CDATE ASC,
   VAL_MODEL ASC
);
