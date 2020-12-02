-- Add/modify columns 
-- Drop columns 
alter table TRD_VOUCH_DEAL drop column FINPROD_TYPE;
alter table TRD_VOUCH_DEAL add FINPROD_TYPE VARCHAR2(32);
-- Add comments to the columns 
comment on column TRD_VOUCH_DEAL.FINPROD_TYPE
  is '业务类型,仅供查询使用';
