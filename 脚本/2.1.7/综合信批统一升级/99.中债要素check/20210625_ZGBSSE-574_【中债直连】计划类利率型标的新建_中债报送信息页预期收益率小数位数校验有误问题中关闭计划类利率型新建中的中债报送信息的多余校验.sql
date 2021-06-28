--关闭计划类利率型：多余非空校验
update CHB_DATACHECK_RULE_INV set STATUS = 'D' where PAGE_ID='FinPlanProfitAsset001' and SRCPROPERTY='bussRepAssetMngProdInfo.repMaxRate' and REMARK='预期最高收益率%要素不可为空';
--关闭计划类利率型：多余负数校验
update CHB_DATACHECK_RULE_INV set STATUS = 'D' where PAGE_ID='FinPlanProfitAsset002' and SRCPROPERTY='bussRepAssetMngProdInfo.repMaxRate' and REMARK='预期最高收益率%必须大于等于0';
--关闭计划类利率型：多余最大收益与最小收益校验
update CHB_DATACHECK_RULE_INV set STATUS = 'D' where PAGE_ID='FinPlanProfitAsset002' and SRCPROPERTY='bussRepAssetMngProdInfo.repMinRate' and REMARK='预期最低收益率必须小于等于预期最高收益率';
