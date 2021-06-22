delete from CHB_DATACHECK_RULE a where a.CHECKRULEID in ('CHB_PRD_BONUS_DEAL-00000000000000020','CHB_PRD_BONUS_DEAL-00000000000000021','CHB_PRD_BONUS_DEAL-00000000000000022');

insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_BONUS_DEAL-00000000000000020', 'CHB_PRD_BONUS_DEAL', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRADE_ID', null, '51', 'Y', '上游系统交易编码超长（最大长50）。', 'E');

insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_BONUS_DEAL-00000000000000021', 'CHB_PRD_BONUS_DEAL', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRADE_ID', null, null, 'N', '上游系统交易编码要素必填。', 'E');

insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_BONUS_DEAL-00000000000000022', 'CHB_PRD_BONUS_DEAL', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TRADE_ID', null, '^[\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF]{0,}$', 'Y', '上游系统交易编码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');
