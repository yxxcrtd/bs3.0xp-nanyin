--jixu现金流字段改成字符型
alter table JXS_CASHFLOWFLOATRATE ADD (FXID VARCHAR2(32) DEFAULT '0');
update JXS_CASHFLOWFLOATRATE set FXID=CFID;
commit;

alter table JXS_CASHFLOWFLOATRATE drop (CFID);
alter table JXS_CASHFLOWFLOATRATE rename  column "FXID" to "CFID";
commit;
comment on column "JXS_CASHFLOWFLOATRATE"."CFID" is '现金流ID';


alter table JXS_CASHFLOWPAR ADD (FXID VARCHAR2(32) DEFAULT '0');
update JXS_CASHFLOWPAR set FXID=CFID;
commit;

alter table JXS_CASHFLOWPAR drop (CFID);
alter table JXS_CASHFLOWPAR rename  column "FXID" to "CFID";
commit;
comment on column "JXS_CASHFLOWPAR"."CFID" is '现金流ID';


alter table JXS_CASHFLOWPAYMENT ADD (FXID VARCHAR2(32) DEFAULT '0');
update JXS_CASHFLOWPAYMENT set FXID=CFID;
commit;

alter table JXS_CASHFLOWPAYMENT drop (CFID);
alter table JXS_CASHFLOWPAYMENT rename  column "FXID" to "CFID";
commit;
comment on column "JXS_CASHFLOWPAYMENT"."CFID" is '现金流ID';



alter table JXS_CASHFLOWRATE ADD (FXID VARCHAR2(32) DEFAULT '0');
update JXS_CASHFLOWRATE set FXID=CFID;
commit;

alter table JXS_CASHFLOWRATE drop (CFID);
alter table JXS_CASHFLOWRATE rename  column "FXID" to "CFID";
commit;
comment on column "JXS_CASHFLOWRATE"."CFID" is '现金流ID';


alter table JXS_ISSUE_INFO ADD (FXID VARCHAR2(32) DEFAULT '0');
update JXS_ISSUE_INFO set FXID=CASHFLOWCFG;
commit;

alter table JXS_ISSUE_INFO drop (CASHFLOWCFG);
alter table JXS_ISSUE_INFO rename  column "FXID" to "CASHFLOWCFG";
commit;


alter table JXS_ISSUE_INFO ADD (FXID VARCHAR2(32) DEFAULT '0');
update JXS_ISSUE_INFO set FXID=CASHFLOWCFG2;
commit;

alter table JXS_ISSUE_INFO drop (CASHFLOWCFG2);
alter table JXS_ISSUE_INFO rename  column "FXID" to "CASHFLOWCFG2";
commit;
comment on column "JXS_ISSUE_INFO"."CASHFLOWCFG" is '现金流配置';
comment on column "JXS_ISSUE_INFO"."CASHFLOWCFG2" is '空方现金流配置';


alter table JXT_TRAN_FEE_DEF ADD (FXID VARCHAR2(32) DEFAULT '0');
update JXT_TRAN_FEE_DEF set FXID=CASHFLOWID;
commit;

alter table JXT_TRAN_FEE_DEF drop (CASHFLOWID);
alter table JXT_TRAN_FEE_DEF rename  column "FXID" to "CASHFLOWID";
commit;

comment on column "JXT_TRAN_FEE_DEF"."CASHFLOWID" is '现金流ID';




alter table JXS_CASHFLOWCFG ADD (FXID VARCHAR2(32));
update JXS_CASHFLOWCFG set FXID=CASHFLOWID;
commit;

alter table JXS_CASHFLOWCFG drop (CASHFLOWID);
alter table JXS_CASHFLOWCFG rename  column FXID to CASHFLOWID;
alter table JXS_CASHFLOWCFG modify "CASHFLOWID" not null;
alter table JXS_CASHFLOWCFG add constraint PK_JXS_CASHFLOWCFG_ID primary key (CASHFLOWID) enable validate;

comment on column "JXS_CASHFLOWCFG"."CASHFLOWID" is '唯一主键';