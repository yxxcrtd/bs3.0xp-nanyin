--是否以财产权信托名义开展的资金信托不可为空
delete from chb_datacheck_rule_inv where CHECKRULEID in('CHB_PRD_DECLARE-00-2022-000000000001','CHB_PRD_DECLARE-00-2022-000000000002');

insert into chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('CHB_PRD_DECLARE-00-2022-000000000001', 'FinProductBuild002', 'isNotEmpty', 'bussRepFinProductInfoDto.zjxtFlag', null, null, 'N', '是否以财产权信托名义开展的资金信托不可为空', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');


insert into chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('CHB_PRD_DECLARE-00-2022-000000000002', 'FinNetValueProductBuild002', 'isNotEmpty', 'bussRepFinProductInfoDto.zjxtFlag', null, null, 'N', '是否以财产权信托名义开展的资金信托不可为空', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
