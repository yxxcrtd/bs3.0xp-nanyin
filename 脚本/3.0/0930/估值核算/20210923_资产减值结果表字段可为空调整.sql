--资产减值结果表字段设为可为空：交易编号、交易日期
alter table MOD_ECL_RESULT modify BUSS_NO null;
alter table MOD_ECL_RESULT modify TRADE_DATE null;