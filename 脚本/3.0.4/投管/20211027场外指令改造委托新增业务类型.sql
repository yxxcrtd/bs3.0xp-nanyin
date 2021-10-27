-- Add/modify columns 
alter table ETR_ENTRUST drop column BUSI_TYPE; 
-- Add/modify columns 
alter table ETR_ENTRUST add BUSI_TYPE VARCHAR2(50);
-- Add comments to the columns 
comment on column ETR_ENTRUST.BUSI_TYPE
  is '业务类型:债券一级投标/现券买卖/债券行权交易/质押式回购首期/买断式回购首期/债券借贷首期/利率互换首期';