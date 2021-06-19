--合作资产管理资金实际投向新增校验
delete from CHB_DATACHECK_RULE_INV where CHECKRULEID = '884807E5-DF82-42EA-E250-207FD10077FD';
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS) values ('884807E5-DF82-42EA-E250-207FD10077FD','ChannelAsset002','inArray','bussRepAssetMngProdInfo.repCapiInvest','==','20,21,22,23','Y','资金实际投向01至19选项为历史数据，不得修改或新增','E','com.joyintech.tams.invest.common.ChbBaseCheckLib');
