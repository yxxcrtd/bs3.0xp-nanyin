--Add By QuJianZhong 20200507 估值表组合底层资产映射表增加字段：债券市场代码 start
alter table RSK_VALTBL_PRT_BOTASSET_MAP add MARKET_ID char(2 byte);
comment on column RSK_VALTBL_PRT_BOTASSET_MAP.MARKET_ID is '估值表债券市场代码';
--Add By QuJianZhong 20200507 估值表组合底层资产映射表增加字段：债券市场代码 end