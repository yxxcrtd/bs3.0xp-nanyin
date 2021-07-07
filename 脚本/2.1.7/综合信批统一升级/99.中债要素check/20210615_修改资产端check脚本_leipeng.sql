/***计划类利率型资产   资金运用方式验证失败**/
UPDATE CHB_DATACHECK_RULE_INV SET STATUS = 'E' WHERE PAGE_ID = 'FinPlanProfitAsset002' AND SRCPROPERTY = 'bussRepAssetMngProdInfo.repCapUseWay';


/***ZGBSSE-583  【中债直连check】新建项目类标的，报送信息中份额面值的check未符合报送规范****/
UPDATE CHB_DATACHECK_RULE_INV SET STATUS = 'E', SRCPROPERTY = 'bussRepAssetCreditNonstdInfoDto.repPrinBaseamt' WHERE PAGE_ID = 'FinProjectTargetAsset002' AND SRCPROPERTY = 'bussRepAssetCreditNonstdInfoDto.repPrinBaseAmt' AND CHECKMETHOD != 'isNotEmpty';


/****ZGBSSE-590 【中债直连】债券标的新建页面，当资产分类为权益类资产时，行业未进行必填项check.***/
UPDATE CHB_DATACHECK_CONDITION_INV SET OBJVALUE = '1301,1302,1305' WHERE  CHECKRULEID = 'B4D4ACB1-4DA7-14B5-E053-045AA8C0F0EF';



UPDATE CHB_DATACHECK_RULE_INV SET STATUS = 'E' WHERE CHECKRULEID ='443E4A49-6810-5A9E-E050-007F01006A50';


/****ZGBSSE-643 【中债直连check】新建项目类资产，当资产负债类别为2617 其他私募股权投资基金时，点击提交，系统提示：基金管理机构名称不可为空；****/
UPDATE CHB_DATACHECK_RULE_INV SET STATUS = 'D'  WHERE CHECKRULEID = 'B66B580F-F249-128E-E053-045AA8C04C6C';

