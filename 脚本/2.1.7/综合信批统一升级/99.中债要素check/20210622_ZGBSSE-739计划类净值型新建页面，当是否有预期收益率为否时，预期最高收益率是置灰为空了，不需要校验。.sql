--计划类净值型:预期最高收益率关闭多余校验
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'E','D') where PAGE_ID='FinPlanNetAsset002' and SRCPROPERTY = 'bussRepAssetMngProdInfo.repMaxRate' and REMARK != '预期最高收益率%以百分数形式表示时必须为n（8,5）格式';
--计划类净值型:预期最低收益率关闭多余校验
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'E','D') where PAGE_ID='FinPlanNetAsset002' and SRCPROPERTY = 'bussRepAssetMngProdInfo.repMinRate' and REMARK != '预期最低收益率%以百分数形式表示时必须为n(8,5)格式';