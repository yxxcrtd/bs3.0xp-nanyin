--当前头寸映射表证券账户的字段类型改成VARCHAR2
alter table JXT_TRAN_SUM_RECORD ADD (FXID VARCHAR2(32));
update JXT_TRAN_SUM_RECORD set FXID="SECACCT";

alter table JXT_TRAN_SUM_RECORD drop ("SECACCT");
alter table JXT_TRAN_SUM_RECORD rename  column "FXID" to "SECACCT";
commit;

comment on column "JXT_TRAN_SUM_RECORD"."SECACCT" is '证券账户';
