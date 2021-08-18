delete chb_datacheck_rule_inv  where CHECKRULEID = 'ASSET_INFO_DETAIL_RULE_0000-0000-0000-0000-0000-0000-0000-000500';
insert into chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('ASSET_INFO_DETAIL_RULE_0000-0000-0000-0000-0000-0000-0000-000500', 'AssetInfoRegist002', 'isNotEmpty', 'orgAssetType1', null, null, 'N', '资产一级分类不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
