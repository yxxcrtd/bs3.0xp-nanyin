alter table fin_trade_product add DATA_SOURCE  varchar2(50);
comment on column fin_trade_product.DATA_SOURCE  is '数据来源';