/****ZGBSSE-676  【中债直连check】新建项目类资产，当资产负债类别为[2201][2202]时，“分期还本条款标识”字段应不可填写**/
update CHB_DATACHECK_RULE_INV set remark = '分期还本条款标识不可为空,请重新选择还款周期' where CHECKRULEID = '443E4A49-5TFD-5A9E-E050-107F01006A28';
update CHB_DATACHECK_CONDITION_INV set COMPARETO = '==' ,OBJVALUE = '1202,1203,1204,1205,1206,1207,1208,1209' where CHECKRULEID = '443E4A49-5TFD-5A9E-E050-107F01006A28';


/****ZGBSSE-554 【中债直连】存款类标的，现金及银行存款类  (  含大额存单 ) 备注check不合规**/
update CHB_DATACHECK_RULE_INV set CHECKMETHOD = 'lessThanCharLength' where checkruleid = '7c35f75f-dd70-4994-bb5d-ec0db27b2cd7';


update CHB_DATACHECK_RULE_INV set CHECKMETHOD = 'lessThanCharLength',REMARK = '资金运用方式不得超过300字节' where SRCPROPERTY = 'bussRepAssetMngProdInfo.repCapUseWay' AND CHECKMETHOD = 'lessThanCharLengthUTF8';

/**ZGBSSE-595  【中债直连check】新建项目类资产，报送信息页面“融资人”check未满足报送规范***/
update CHB_DATACHECK_RULE_INV set REMARK = '融资人必须填写白名单中的文字' where CHECKRULEID = 'A23E4A49-6810-5A9E-E050-007F21006A15';
DELETE FROM CHB_DATACHECK_RULE_INV WHERE CHECKRULEID IN ('443E4A49-6810-FF1E-E050-007F01006A41','443E4A49-6810-5A9E-E050-007F01006A41');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('443E4A49-6810-FF1E-E050-007F01006A41', 'FinProjectTargetAsset002', 'lessThanCharLengthUTF8', 'bussRepOtherInvestTargetInfoDto.repFinancier', null, '201', 'Y', '融资人要素长度不超过200', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('443E4A49-6810-5A9E-E050-007F01006A41', 'FinProjectTargetAsset002', 'isNotEmpty', 'bussRepOtherInvestTargetInfoDto.repFinancier', null, null, 'N', '融资人要素不可为空', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');



/***ZGBSSE-561 【中债直连check】新建股票资产，未输入资产名称，点击提交时系统提示信息重复***/
UPDATE CHB_DATACHECK_RULE_INV SET REMARK = SUBSTR(REMARK,0,length(REMARK) -1) WHERE REMARK LIKE '%。';


/**ZGBSSE-626 【中债直连check】新建项目类资产，当资产负债类别为1201、1210时，“贴现利率”字段check不符合中债规范****/
UPDATE CHB_DATACHECK_RULE_INV SET STATUS = 'E' WHERE CHECKRULEID = '884807E5-DFB6-D920-E050-007F010077FD';
UPDATE CHB_DATACHECK_RULE_INV SET STATUS = 'E',NULLABLE='Y',remark = '加权剩余期限（天）要素必须为(7,2)格式' WHERE CHECKRULEID = '884807E5-DFAC-D920-E050-007F010077FD';

/***ZGBSSE-646 【中债直连check】新建项目类资产，当资产负债类别为2617 其他私募股权投资基金时，资产名称的值未做必须非白名单文字校验**/
UPDATE CHB_DATACHECK_RULE_INV SET remark = '资产名称必须填写白名单中的文字' WHERE CHECKRULEID = '884807E5-DF34-D920-E050-107F010077FD';

/***ZGBSSE-699 【统一数据维护】选择“资产要素信息”，点击“查询”按钮，查询到“资产收益率%”不以百分数形式表示的数据**/
update CHB_DATACHECK_RULE SET STATUS = 'E' WHERE CHECKRULEID = 'W23E4A49-6810-5A9E-E050-007FA1006A66';
