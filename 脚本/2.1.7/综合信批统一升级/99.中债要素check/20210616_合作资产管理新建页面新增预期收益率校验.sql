--合作资产管理新建页面新增预期收益率校验
delete from CHB_DATACHECK_RULE_INV where CHECKRULEID in ('884807E5-DF8A-D920-BCA7-107F010077FD','884807E5-DF8D-D920-4A35-007F010077FD');

Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS) values ('884807E5-DF8A-D920-BCA7-107F010077FD','ChannelAsset002','isNumber2','bussRepAssetMngProdInfo.repMaxRate',null,'8,5','Y','预期最高收益率%以百分数形式表示时必须为n(8,5)格式','E','com.joyintech.tams.invest.common.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS) values ('884807E5-DF8D-D920-4A35-007F010077FD','ChannelAsset002','isNumber2','bussRepAssetMngProdInfo.repMinRate','==','8,5','Y','预期最低收益率%以百分数形式表示时必须为n(8,5)格式','E','com.joyintech.tams.invest.common.ChbBaseCheckLib');
