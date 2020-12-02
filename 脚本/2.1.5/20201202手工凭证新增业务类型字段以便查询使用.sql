-- Add/modify columns 
-- Drop columns 
alter table TRD_VOUCH_DEAL drop column FINPROD_TYPE;
alter table TRD_VOUCH_DEAL add FINPROD_TYPE VARCHAR2(32);
-- Add comments to the columns 
comment on column TRD_VOUCH_DEAL.FINPROD_TYPE
  is '业务类型,仅供查询使用';

--刷新历史数据专用，历史数据业务类型,仅供查询使用=业务明细类型
update trd_vouch_deal set finprod_type = busi_detail_type;