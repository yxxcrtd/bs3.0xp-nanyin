-----------------------银行间上下行  多组合成交实占授信eventid由报价编号和委托编号拼接，导致授信实占tradeid长度不够------------------
alter table CREDIT_USING_AST modify TRADE_ID VARCHAR2(60);
alter table CREDIT_USING_SERIES modify TRADE_ID VARCHAR2(60);
alter table CREDIT_USING_CUSTOMER modify TRADE_ID VARCHAR2(60);
alter table CREDIT_USING_CUSTOMER_MST modify TRADE_ID VARCHAR2(60);