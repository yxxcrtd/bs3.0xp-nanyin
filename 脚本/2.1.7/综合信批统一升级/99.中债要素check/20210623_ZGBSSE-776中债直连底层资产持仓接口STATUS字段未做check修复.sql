delete from CHB_DATACHECK_RULE a where a.checkruleid in ('WH3E4A49-6810-5A9E-E050-007F02006A67','B8464A4D-3D95-D9B6-E055-000000000001');

insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F02006A67', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'STATUS', '==', 'SVR_STATUS', 'N', '数据状态要素不可为空或不在值域“D-删除、E-有效”范围内。', 'E');
