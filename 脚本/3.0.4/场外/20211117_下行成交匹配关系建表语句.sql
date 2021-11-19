drop table TRDQUTO_MATCH_INFO;

create table TRDQUTO_MATCH_INFO
(
   SEQNO                VARCHAR(32)          not null,
   CSTP_TRADE_ID        VARCHAR(32),
   EXE_ID               VARCHAR(32),
   PRE_TRADE_ID         VARCHAR(32),
   MATCH_TYPE           VARCHAR(50),
   MATCH_STATUS         VARCHAR(50),
   TRD_CONF_STATUS      VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TRDQUTO_MATCH_INFO primary key (SEQNO)
);

comment on table TRDQUTO_MATCH_INFO is
'下行成交匹配关系';

comment on column TRDQUTO_MATCH_INFO.SEQNO is
'流水号';

comment on column TRDQUTO_MATCH_INFO.CSTP_TRADE_ID is
'下行交易编号';

comment on column TRDQUTO_MATCH_INFO.EXE_ID is
'成交编号';

comment on column TRDQUTO_MATCH_INFO.PRE_TRADE_ID is
'报价编号/登记编号';

comment on column TRDQUTO_MATCH_INFO.MATCH_TYPE is
'匹配方式，手工匹配，自动匹配';

comment on column TRDQUTO_MATCH_INFO.MATCH_STATUS is
'匹配状态，已匹配';

comment on column TRDQUTO_MATCH_INFO.TRD_CONF_STATUS is
'成交确认状态，已确认';

comment on column TRDQUTO_MATCH_INFO.CREATE_USER is
'创建人';

comment on column TRDQUTO_MATCH_INFO.CREATE_DEPT is
'创建部门';

comment on column TRDQUTO_MATCH_INFO.CREATE_TIME is
'创建时间';

comment on column TRDQUTO_MATCH_INFO.UPDATE_USER is
'更新人';

comment on column TRDQUTO_MATCH_INFO.UPDATE_TIME is
'更新时间';
