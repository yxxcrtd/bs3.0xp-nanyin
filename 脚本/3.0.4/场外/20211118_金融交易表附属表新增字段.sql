alter table trd_product_deal_add add our_share_amt  NUMBER(30,4);
comment on column trd_product_deal_add.our_share_amt is '本方券交易份额';

alter table trd_product_deal_add add lendin_share_amt  NUMBER(30,4);
comment on column trd_product_deal_add.lendin_share_amt is '融入券交易份额';

alter table trd_product_deal_add add our_cprice_amt  NUMBER(30,4);
comment on column trd_product_deal_add.our_cprice_amt is '本方券净价总额';

alter table trd_product_deal_add add lendin_cprice_amt  NUMBER(30,4);
comment on column trd_product_deal_add.lendin_cprice_amt is '融入券净价总额';

alter table trd_product_deal_add add our_fprice_amt  NUMBER(30,4);
comment on column trd_product_deal_add.our_fprice_amt is '本方券全价总额';

alter table trd_product_deal_add add lendin_fprice_amt  NUMBER(30,4);
comment on column trd_product_deal_add.lendin_fprice_amt  is '融入券全价总额';

alter table trd_product_deal_add add our_int_amt  NUMBER(30,4);
comment on column trd_product_deal_add.our_int_amt is '本方券利息总额';

alter table trd_product_deal_add add lendin_int_amt  NUMBER(30,4);
comment on column trd_product_deal_add.lendin_int_amt is '融入券利息总额';