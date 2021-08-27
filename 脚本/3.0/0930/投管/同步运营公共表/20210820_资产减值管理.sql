--资产减值流水增删字段，删除[市场代码] 新增[金融产品代码、代偿期类型、期限描述]
alter table TRD_ASSET_LOSS drop (SEC_ID);
alter table TRD_ASSET_LOSS add FINPROD_ID VARCHAR2(50);
comment on column TRD_ASSET_LOSS.FINPROD_ID is  '金融产品代码';
alter table TRD_ASSET_LOSS add PEROID_TYPE VARCHAR2(50);
comment on column TRD_ASSET_LOSS.PEROID_TYPE is  '代偿期类型';
alter table TRD_ASSET_LOSS add TERM_DESCRIBE VARCHAR2(50);
comment on column TRD_ASSET_LOSS.TERM_DESCRIBE is  '期限描述';
