/*交易手续费*/
alter table TRD_MATCH_INFO drop column PROCEDURE_AMT;
Alter Table TRD_MATCH_INFO Add PROCEDURE_AMT NUMBER(30, 2);
comment on column TRD_MATCH_INFO.PROCEDURE_AMT is '交易手续费';
/*结算手续费*/
alter table TRD_MATCH_INFO drop column SETTLE_AMT;
Alter Table TRD_MATCH_INFO Add SETTLE_AMT NUMBER(30, 2);
comment on column TRD_MATCH_INFO.SETTLE_AMT is '结算手续费';