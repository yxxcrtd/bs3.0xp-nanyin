alter table wfl_reg_fin_pro_deal_total_add drop column  THE_SETTE_DATE;
alter table wfl_reg_fin_pro_deal_total_add add ACT_SETTE_DATE  DATE;
comment on column wfl_reg_fin_pro_deal_total_add.ACT_SETTE_DATE is '实际行权日';