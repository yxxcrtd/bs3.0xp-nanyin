---------更改风险预占实占表历史表的资产代码长度----------------
alter table RSK_OCCUPY_INFO modify ASSET_CODE VARCHAR2(50);

alter table RSK_OCCUPY_INFO_HIS modify ASSET_CODE VARCHAR2(50);

alter table RSK_PRE_OCCUPY_INFO modify ASSET_CODE VARCHAR2(50);

alter table RSK_PRE_OCCUPY_INFO_HIS modify ASSET_CODE VARCHAR2(50);
