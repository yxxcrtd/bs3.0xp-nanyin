delete from CHB_DATACHECK_RULE_INV where SRCPROPERTY = 'bussFinProductSaleParamDto.raiseAmtPlan'
and PAGE_ID = 'FinNetValueProductBuild002';

insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('de4E4A59-6813-5A9E-E050-107F01006A77                            ', 'FinNetValueProductBuild002', 'isNotEmpty', 'bussFinProductSaleParamDto.raiseAmtPlan', null, null, 'N', '计划募集金额（元）要素不可为空', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');

insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('de4E4A59-6813-5A9E-E050-007F01006A77                            ', 'FinNetValueProductBuild002', 'isNumber2', 'bussFinProductSaleParamDto.raiseAmtPlan', null, '15,2', 'Y', '计划募集金额（元）必须为n(15,2)格式', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');

insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('de314A60-6813-5A9E-E050-007F01006A77                            ', 'FinNetValueProductBuild002', 'compareValueAsNumber', 'bussFinProductSaleParamDto.raiseAmtPlan', '>', '0', 'Y', '计划募集金额（元）必须大于0', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');

update CHB_DATACHECK_RULE_INV set checkclass = 'com.joyintech.tams.invest.common.ChbBaseCheckLib';
