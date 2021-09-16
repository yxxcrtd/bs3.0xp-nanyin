--是否商业银行二级资本债
delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112345D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112345D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_SEC_CAPITAL', null, null, 'N', '是否商业银行二级资本债不可以为空。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112345D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112346D', 'W23E4A49-6810-5A9E-1234-123F0112345D', 'inArray', 'ASSET_TYPE2', '==', '1101,1102,1109,1110,1111,1112,1113,1114,1115,1116,1117,2101,2501,2502', '当资产为债券类资产/理财直接融资工具/同业存单类资产时', 'E');

delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112347D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112347D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BANK_SEC_CAPITAL', '==', 'SVR_YES_OR_NO', 'Y', '是否商业银行二级资本债不在值域范围内。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112347D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112348D', 'W23E4A49-6810-5A9E-1234-123F0112347D', 'inArray', 'ASSET_TYPE2', '==', '1101,1102,1109,1110,1111,1112,1113,1114,1115,1116,1117,2101,2501,2502', '当资产为债券类资产/理财直接融资工具/同业存单类资产时', 'E');

--是否商业银行永续债
delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112349D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112349D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_SEC_PERPETUAL', null, null, 'N', '是否商业银行永续债不可以为空。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112349D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112310D', 'W23E4A49-6810-5A9E-1234-123F0112349D', 'inArray', 'ASSET_TYPE2', '==', '1101,1102,1109,1110,1111,1112,1113,1114,1115,1116,1117,2101,2501,2502', '当资产为债券类资产/理财直接融资工具/同业存单类资产时', 'E');

delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112311D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112311D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BANK_SEC_PERPETUAL', '==', 'SVR_YES_OR_NO', 'Y', '是否商业银行永续债不在值域范围内。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112311D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112312D', 'W23E4A49-6810-5A9E-1234-123F0112311D', 'inArray', 'ASSET_TYPE2', '==', '1101,1102,1109,1110,1111,1112,1113,1114,1115,1116,1117,2101,2501,2502', '当资产为债券类资产/理财直接融资工具/同业存单类资产时', 'E');

--是否商业银行优先股
delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112313D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112313D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_SEC_PREFER', null, null, 'N', '是否商业银行优先股不可以为空。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112313D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112314D', 'W23E4A49-6810-5A9E-1234-123F0112313D', 'inArray', 'ASSET_TYPE2', '==', '1101,1102,1109,1110,1111,1112,1113,1114,1115,1116,1117,2101,2501,2502', '当资产为债券类资产/理财直接融资工具/同业存单类资产时', 'E');

delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112315D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112315D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BANK_SEC_PREFER', '==', 'SVR_YES_OR_NO', 'Y', '是否商业银行优先股不在值域范围内。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112315D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112316D', 'W23E4A49-6810-5A9E-1234-123F0112315D', 'inArray', 'ASSET_TYPE2', '==', '1101,1102,1109,1110,1111,1112,1113,1114,1115,1116,1117,2101,2501,2502', '当资产为债券类资产/理财直接融资工具/同业存单类资产时', 'E');

--是否商业银行可转债
delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112317D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112317D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_SEC_KZZQ', null, null, 'N', '是否商业银行可转债不可以为空。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112317D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112318D', 'W23E4A49-6810-5A9E-1234-123F0112317D', 'inArray', 'ASSET_TYPE2', '==', '1101,1102,1109,1110,1111,1112,1113,1114,1115,1116,1117,2101,2501,2502', '当资产为债券类资产/理财直接融资工具/同业存单类资产时', 'E');

delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112319D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112319D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BANK_SEC_KZZQ', '==', 'SVR_YES_OR_NO', 'Y', '是否商业银行可转债不在值域范围内。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112319D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112320D', 'W23E4A49-6810-5A9E-1234-123F0112319D', 'inArray', 'ASSET_TYPE2', '==', '1101,1102,1109,1110,1111,1112,1113,1114,1115,1116,1117,2101,2501,2502', '当资产为债券类资产/理财直接融资工具/同业存单类资产时', 'E');

--是否专项债券
delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112321D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112321D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BANK_SEC_ZXZQ', '==', '0', 'Y', '是否专项债券必须为空。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-1234-123F0112321D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112322D', 'W23E4A49-6810-5A9E-1234-123F0112321D', 'inArray', 'ASSET_TYPE2', '!=', '1110', '当资产/负债类别不是地方政府债时', 'E');


