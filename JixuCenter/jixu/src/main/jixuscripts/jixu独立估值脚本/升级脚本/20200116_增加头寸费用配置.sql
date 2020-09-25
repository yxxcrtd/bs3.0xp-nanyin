--jixu现金流字段改成字符型
alter table JXS_PORTEXPENSECFG ADD (CUSIP VARCHAR2(50));
alter table JXS_PORTEXPENSECFG ADD (LOTNUM NUMBER(10,0));

comment on column "JXS_PORTEXPENSECFG"."CUSIP" is '证券编号';
comment on column "JXS_PORTEXPENSECFG"."LOTNUM" is '批次编号';