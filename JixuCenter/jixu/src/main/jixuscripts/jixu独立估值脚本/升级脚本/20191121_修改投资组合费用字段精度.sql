--jixu现金流字段改成字符型
alter table JXS_PORTEXPENSECFG ADD (FXID NUMBER(30,14));
update JXS_PORTEXPENSECFG set FXID=EXPENSEAMOUNT;
commit;

alter table JXS_PORTEXPENSECFG drop (EXPENSEAMOUNT);
alter table JXS_PORTEXPENSECFG rename  column "FXID" to "EXPENSEAMOUNT";
commit;
comment on column "JXS_PORTEXPENSECFG"."EXPENSEAMOUNT" is '税费金额/费率 | 如果fixFeeFlag字段选择固定,则是固定金额；如果是浮动,则是税费利率';