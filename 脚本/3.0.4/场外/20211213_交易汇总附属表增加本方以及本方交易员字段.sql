
alter table TRD_DEAL_TOTAL_ADD add ou_party_id  varchar2(32);
comment on column TRD_DEAL_TOTAL_ADD.ou_party_id is '本方';

alter table TRD_DEAL_TOTAL_ADD add ou_trader_id  varchar2(32);
comment on column TRD_DEAL_TOTAL_ADD.ou_trader_id is '本方交易员';


alter table reg_fin_product_deal_total_add add EXERCISE_PRICE  NUMBER(30,14);
comment on column reg_fin_product_deal_total_add.EXERCISE_PRICE is '行权价';

alter table reg_fin_product_deal_total_add add redeem_Rate  NUMBER(30,14);
comment on column reg_fin_product_deal_total_add.redeem_Rate is '赎回比例';

alter table WFL_REG_FIN_PRO_DEAL_TOTAL_ADD add EXERCISE_PRICE  NUMBER(30,14);
comment on column WFL_REG_FIN_PRO_DEAL_TOTAL_ADD.EXERCISE_PRICE is '行权价';

alter table WFL_REG_FIN_PRO_DEAL_TOTAL_ADD add redeem_Rate  NUMBER(30,14);
comment on column WFL_REG_FIN_PRO_DEAL_TOTAL_ADD.redeem_Rate is '赎回比例';