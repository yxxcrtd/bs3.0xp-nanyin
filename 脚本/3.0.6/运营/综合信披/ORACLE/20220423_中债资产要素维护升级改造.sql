/***修改升级脚本**/
DELETE FROM CHB_DATACHECK_RULE_INV WHERE REMARK LIKE '新人行%';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('ASSET_INFO_DETAIL_RULE_0000-182110B0-6B55-4D18-9496-0C92E4B001DB', 'AssetInfoRegist002', 'isNotEmpty', 'repPbcAssetType1', null, null, 'N', '新人行一级分类要素不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('ASSET_INFO_DETAIL_RULE_0000-182110B0-6B55-4D18-9496-0C92E4B002DB', 'AssetInfoRegist002', 'isNotEmpty', 'repPbcAssetType2', null, null, 'N', '新人行二级分类要素不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('ASSET_INFO_DETAIL_RULE_0000-182110B0-6B55-4D18-9496-0C92E4B003DB', 'AssetInfoRegist002', 'isNotEmpty', 'repPbcAssetType3', null, null, 'N', '新人行三级分类要素不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');

