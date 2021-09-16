--去除资产结果管理表业务编号和交易日期必填项校验
alter table MOD_ECL_RESULT modify BUSS_NO null;
alter table MOD_ECL_RESULT modify TRADE_DATE null;




