alter table WFL_IBU_QUOTE_CREP add TRANSACTIME VARCHAR2(50);
alter table WFL_IBU_QUOTE_CREP add ZZ_TOTAL_AMT NUMBER(30, 4);
alter table WFL_IBU_QUOTE_CREP add SQ_TOTAL_AMT NUMBER(30, 4);
comment on column WFL_IBU_QUOTE_CREP.TRANSACTIME is '业务发生时间';
comment on column WFL_IBU_QUOTE_CREP.ZZ_TOTAL_AMT is '中债券面总额合计';
comment on column WFL_IBU_QUOTE_CREP.SQ_TOTAL_AMT is '上清券面总额合计';


alter table IBU_QUOTE_ETR_RE_HIS add SHARE_CT_AMT NUMBER(30, 2);
comment on column IBU_QUOTE_ETR_RE_HIS.SHARE_CT_AMT is '折算券面金额';