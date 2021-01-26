alter table CHB_INVEST_TRADE modify CUR_ASSETDET_TYPE varchar2(180);
alter table CHB_INVEST_TRADE modify TRADE_COUNTER varchar2(300);
alter table CHB_INVEST_TRADE modify CONTRACT_CODE varchar2(150);


alter table CHB_INVEST_TRADE_SUB modify CUR_ASSETDET_TYPE varchar2(180);
alter table CHB_INVEST_TRADE_SUB modify TRADE_COUNTER varchar2(300);
alter table CHB_INVEST_TRADE_SUB modify CONTRACT_CODE varchar2(150);

alter table CHB_INVEST_TRADE_SUB_HIS modify CUR_ASSETDET_TYPE varchar2(180);
alter table CHB_INVEST_TRADE_SUB_HIS modify TRADE_COUNTER varchar2(300);
alter table CHB_INVEST_TRADE_SUB_HIS modify CONTRACT_CODE varchar2(150);