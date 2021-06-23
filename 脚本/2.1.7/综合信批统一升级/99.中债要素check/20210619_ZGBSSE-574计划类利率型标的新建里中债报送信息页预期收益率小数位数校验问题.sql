--利率页：预期最高率
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where PAGE_ID='FinPlanProfitAsset002' and SRCPROPERTY = 'bussRepAssetMngProdInfo.repMaxRate' and REMARK = '预期最高收益率%以百分数形式表示时必须为n（8,5）格式';
--利率页：预期最低率
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where PAGE_ID='FinPlanProfitAsset002' and SRCPROPERTY = 'bussRepAssetMngProdInfo.repMinRate' and REMARK = '预期最低收益率%以百分数形式表示时必须为n(8,5)格式';
--利率页：托管费率
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where PAGE_ID='FinPlanProfitAsset002' and SRCPROPERTY = 'bussRepAssetMngProdInfo.repTruFeeRate' and REMARK = '托管费率%以百分数形式表示时必须为n（8,5）格式';
--利率页：其他合计费率
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where PAGE_ID='FinPlanProfitAsset002' and SRCPROPERTY = 'bussRepAssetMngProdInfo.repOtherFeeRate' and REMARK = '其他合计费率%以百分数形式表示时必须为n（8,5）格式';
--利率页：交易相关合计费率
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where PAGE_ID='FinPlanProfitAsset002' and SRCPROPERTY = 'bussRepAssetMngProdInfo.repTradeFeeRate' and REMARK = '交易相关合计费率%以百分数形式表示时必须为n（8,5）格式';
--利率页：管理费率
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where PAGE_ID='FinPlanProfitAsset002' and SRCPROPERTY = 'bussRepAssetMngProdInfo.repMgrFeeRate' and REMARK = '管理费率%以百分数形式表示时必须为n（8,5）格式';
--利率页：中介服务机构合计费率
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where PAGE_ID='FinPlanProfitAsset002' and SRCPROPERTY = 'bussRepAssetMngProdInfo.repAgencyFeeRate' and REMARK = '中介服务机构合计费率%以百分数形式表示时必须为n（8,5）格式';
