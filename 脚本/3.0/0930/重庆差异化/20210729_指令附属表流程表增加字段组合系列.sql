
alter table wfl_etr_entrust_add add (PORTFOLIO_SERIES VARCHAR2(50));
comment on column wfl_etr_entrust_add.PORTFOLIO_SERIES is '组合系列';

alter table wfl_etr_entrust_add ADD CALCULATE_FIELD VARCHAR(50);
COMMENT ON COLUMN ETR_ENTRUST_ADD.CALCULATE_FIELD
IS '计算方式';

ALTER TABLE wfl_etr_entrust_add ADD "PLEDGE_STOCK_ID" varchar2(50);
COMMENT ON COLUMN "ETR_ENTRUST_ADD"."PLEDGE_STOCK_ID" IS '质押券库代码';