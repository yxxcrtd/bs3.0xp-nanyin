delete from CHB_DATACHECK_RULE a where a.checkruleid='CHB_PRD_PERIODYIELD-0000000000000016';

insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_PERIODYIELD-0000000000000016', 'CHB_PRD_PERIODYIELD', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CHANG_TYPE', null, null, 'N', '变动类型要素必填。', 'E');  
