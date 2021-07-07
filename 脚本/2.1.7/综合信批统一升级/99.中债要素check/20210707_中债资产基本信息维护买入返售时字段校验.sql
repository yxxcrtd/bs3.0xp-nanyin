update CHB_DATACHECK_RULE_INV set status='D' where checkruleid='ASSET_INFO_DETAIL_RULE_0000-067f5ec0-6d32-40c3-94f9-cf2a3f5b9463';
update chb_datacheck_condition_inv set status='D' where checkruleid='ASSET_INFO_DETAIL_RULE_0000-067f5ec0-6d32-40c3-94f9-cf2a3f5b9463';


delete  from CHB_DATACHECK_RULE_INV where checkruleid='ASSET_INFO_DETAIL_RULE_0000-0000-0000-0000-0000-0000-0000-123441';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('ASSET_INFO_DETAIL_RULE_0000-0000-0000-0000-0000-0000-0000-123441', 'AssetInfoRegist002', 'isNumber2', 'svrBussRepPlacementInfoDto.targetAmt', null, '15,2', 'Y', '回购标的金额要素必须为n(15,2)格式。', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');
delete from chb_datacheck_condition_inv where checkruleid='ASSET_INFO_DETAIL_RULE_0000-0000-0000-0000-0000-0000-0000-123441';
insert into chb_datacheck_condition_inv (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('ASSET_INFO_DETAIL_CONDITION_0000-999', 'ASSET_INFO_DETAIL_RULE_0000-0000-0000-0000-0000-0000-0000-123441', 'inArray', 'assetType2', '==', '1108,1902', '当资产/负债类别为卖出回购或买入返售时', 'E');