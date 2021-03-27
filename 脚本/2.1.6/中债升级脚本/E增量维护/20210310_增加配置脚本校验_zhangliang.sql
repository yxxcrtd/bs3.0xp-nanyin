insert into CHB_DATACHECK_RULE (CHECKRULEID,DATATYPE,CHECKCLASS,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,REMARK,STATUS,NULLABLE) values ('We294A49-6813-5A9E-E050-107F01006B57','CHB_ASSET_INFO','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','inArray','IS_PLEDGE','==','02','是否为质押融资只可选择“02 否”。','E','Y');

insert into CHB_DATACHECK_RULE (CHECKRULEID,DATATYPE,CHECKCLASS,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,REMARK,STATUS,NULLABLE) values ('8e294A49-6813-5A9E-F060-107F01006B51','CHB_ASSET_INFO_SUB','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','inArray','IS_PLEDGE','==','02','是否为质押融资只可选择“02 否”。','E','Y');

insert into CHB_DATACHECK_RULE (CHECKRULEID,DATATYPE,CHECKCLASS,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,REMARK,STATUS,NULLABLE) values ('8e294A49-6813-5A9E-E050-107F01006B51','CHB_ASSET_INFO_TMP','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','inArray','IS_PLEDGE','==','02','是否为质押融资只可选择“02 否”。','E','Y');

INSERT INTO CHB_DATACHECK_CONDITION_INV VALUES ('ASSET_INFO_DETAIL_CONDITION_0000-910','ASSET_INFO_DETAIL_RULE_0000-0000-0000-0000-0000-0000-0000-000477','inArray','assetType2','==','1301,1302,1305','','E');

insert into CHB_DATACHECK_RULE_INV values ('ASSET_INFO_DETAIL_RULE_0000-0000-0000-0000-0000-0000-0000-000477','AssetInfoRegist002','inArray','svrBussRepEquityInfoDto.isPledge','==','02','Y','是否为质押融资只可选择“02 否。','E','com.joyintech.tams.common.svreport.datacheck.service.ChbBaseCheckLib');
