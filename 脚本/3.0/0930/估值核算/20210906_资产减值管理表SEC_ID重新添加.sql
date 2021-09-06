--资产减值管理表 SEC_ID字段重新添加
--注意：该脚本执行报错可忽略，是由于历史脚本执行了删除该字段，现在重新添加，如果执行报错说明该字段已存在，不需要管
alter table TRD_ASSET_LOSS add SEC_ID VARCHAR2(50);
comment on column TRD_ASSET_LOSS.SEC_ID is  '市场代码';