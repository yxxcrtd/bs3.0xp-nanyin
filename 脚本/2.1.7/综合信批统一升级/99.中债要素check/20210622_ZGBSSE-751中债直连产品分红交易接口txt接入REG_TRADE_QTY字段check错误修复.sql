delete from CHB_DATACHECK_RULE a where a.checkruleid='CHB_PRD_BONUS_DEAL-00000000000000023';

insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_BONUS_DEAL-00000000000000023', 'CHB_PRD_BONUS_DEAL', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REG_TRADE_QTY', null, null, 'N', '权益登记日份额要素必填。', 'E');

update CHB_DATACHECK_RULE a set a.compareto='>',a.remark='权益登记日份额必须大于0。' where a.CHECKRULEID='CHB_PRD_BONUS_DEAL-0012';
