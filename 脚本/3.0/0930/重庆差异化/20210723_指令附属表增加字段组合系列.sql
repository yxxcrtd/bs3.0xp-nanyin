
alter table etr_entrust_add add (PORTFOLIO_SERIES VARCHAR2(50));
comment on column etr_entrust_add.PORTFOLIO_SERIES is '组合系列';
