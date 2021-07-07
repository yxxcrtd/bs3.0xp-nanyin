delete from CHB_DATACHECK_RULE a where a.checkruleid='CHB_PRD_PERIODYIELD-0000000000000017';

insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_PERIODYIELD-0000000000000017', 'CHB_PRD_PERIODYIELD', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'PROD_VALUE', '>', '0', 'Y', '收益率/单位净值必须大于0。', 'E');
