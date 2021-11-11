/****资产基本信息维护，项目类非标资产修改提交时，弹出提示金额不能为空但是页面无此字段，请添加*/
UPDATE CHB_DATACHECK_RULE_INV SET SRCPROPERTY = 'svrBussRepOtherInvestTargetInfoDto.amt' where CHECKRULEID = 'ASSET_INFO_DETAIL_RULE_0000-884807E5-DF36-D920-E050-007F010077FD';
