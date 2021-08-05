delete from CHB_DATACHECK_RULE_INV where CHECKRULEID ='FINPRODUCTBUILD002-de4E4A50-6813-5A9E-E151-077F01006A66';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('FINPRODUCTBUILD002-de4E4A50-6813-5A9E-E151-077F01006A66', 'FinProductBuild002', 'isNumber2', 'bussFinProductSaleParamDto.toffStart', '', '15,2', 'Y', '募集要素中认购起点必须为n..(15,2)格式', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');

delete from CHB_DATACHECK_RULE_INV where CHECKRULEID ='de4E4A50-6813-5A9E-E151-077F01006A66';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('de4E4A50-6813-5A9E-E151-077F01006A66', 'FinNetValueProductBuild002', 'isNumber2', 'bussFinProductSaleParamDto.toffStart', '', '15,2', 'Y', '募集要素中认购起点必须为n..(15,2)格式', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');