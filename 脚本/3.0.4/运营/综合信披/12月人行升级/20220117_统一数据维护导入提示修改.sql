--以财产权信托名义开展的资金信托标识
delete  from   CHB_DATACHECK_RULE  where  CHECKRULEID in('de164A49-6813-5A9E-2022-007F01006A01','de164A49-6813-5A9E-2022-007F01006A02');

insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de164A49-6813-5A9E-2022-007F01006A01', 'CHB_PRD_BASEINFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'ZJXT_FLAG', '==', 'YES_NO', 'Y', '以财产权信托名义开展的资金信托标识不在值域范围内。', 'E');

insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de164A49-6813-5A9E-2022-007F01006A02', 'CHB_PRD_BASEINFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ZJXT_FLAG', null, null, 'N', '以财产权信托名义开展的资金信托标识要素不可为空。', 'E');
