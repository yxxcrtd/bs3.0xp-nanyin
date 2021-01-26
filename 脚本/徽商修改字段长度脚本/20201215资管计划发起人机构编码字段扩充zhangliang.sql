--修改资产要素登记表资管计划发起人机构编码32->50
alter table CHB_ASSET_INFO_SUB modify PARTY_CODE varchar2(50);
alter table CHB_ASSET_INFO modify PARTY_CODE varchar2(50);
alter table CHB_ASSET_INFO_SUB_HIS modify PARTY_CODE varchar2(50);

--修改资产管理计划报送信息表发起人机构编码32->50
alter table REP_ASSET_MNG_PLAN modify REP_PARTY_CODE varchar2(50);

--修改资产要素登记表资管计划登记编码256->500
alter table CHB_ASSET_INFO_SUB modify CHECK_CODE varchar2(500);
alter table CHB_ASSET_INFO modify CHECK_CODE varchar2(500);
alter table CHB_ASSET_INFO_SUB_HIS modify CHECK_CODE varchar2(500);

--修改资产管理计划报送信息表资管计划登记编码256->500
alter table REP_ASSET_MNG_PLAN modify REP_REGIST_CODE varchar2(500);