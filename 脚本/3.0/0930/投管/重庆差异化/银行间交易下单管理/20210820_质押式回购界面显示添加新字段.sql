Alter Table IBU_QUOTE_CREP Add ZZ_TOTAL_AMT  NUMBER(30, 4);
comment on column IBU_QUOTE_CREP.ZZ_TOTAL_AMT is '中债券面总额合计';
Alter Table IBU_QUOTE_CREP Add SQ_TOTAL_AMT  NUMBER(30, 4);
comment on column IBU_QUOTE_CREP.SQ_TOTAL_AMT is '上清券面总额合计';
Alter Table IBU_QUOTE_CREP_HIS Add ZZ_TOTAL_AMT  NUMBER(30, 4);
comment on column IBU_QUOTE_CREP_HIS.ZZ_TOTAL_AMT is '中债券面总额合计';
Alter Table IBU_QUOTE_CREP_HIS Add SQ_TOTAL_AMT  NUMBER(30, 4);
comment on column IBU_QUOTE_CREP_HIS.SQ_TOTAL_AMT is '上清券面总额合计';