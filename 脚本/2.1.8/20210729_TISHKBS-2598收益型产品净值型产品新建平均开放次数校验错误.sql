--平均开放次数（年化）中债check
delete from CHB_DATACHECK_RULE_INV t WHERE t.checkruleid='yb214A49-6813-5A9E-E050-007F22700013';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('yb214A49-6813-5A9E-E050-007F22700013', 'FinNetValueProductBuild002', 'compareValueAsNumber', 'bussRepFinProductInfoDto.repAvgOpenCount', '<=', '366', 'Y', '平均开放次数（年化）不能超过366', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');

delete from CHB_DATACHECK_RULE_INV t WHERE t.checkruleid='FINPRODUCTBUILD002-yb214A49-6813-5A9E-E050-007F22700013';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('FINPRODUCTBUILD002-yb214A49-6813-5A9E-E050-007F22700013', 'FinProductBuild002', 'compareValueAsNumber', 'bussRepFinProductInfoDto.repAvgOpenCount', '<=', '366', 'Y', '平均开放次数（年化）不能超过366', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');
