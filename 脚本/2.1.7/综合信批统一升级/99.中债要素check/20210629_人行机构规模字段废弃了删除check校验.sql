--债券二级交易，选择接口表的数据加券失败
--人行机构规模 字段 废弃了，页面隐藏,人行月报改成用贷款明细信息进行统计了
delete from CHB_DATACHECK_RULE_INV where SRCPROPERTY = 'bussFinBondRepInfoDto.repInstitSize' and PAGE_ID in( 'FinBondTargetAsset002','FinProjectTargetAsset002');