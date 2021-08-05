delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-E050-123F0100129D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0100129D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BONDTOSTOCK_FLAG', null, null, 'N', '是否投向“市场化债转股相关产品的部分”不可以为空。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-E050-123F0100129D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0104129D', 'W23E4A49-6810-5A9E-E050-123F0100129D', 'inArray', 'ASSET_TYPE2', '==', '1101,1102,1109,1110,1111,1112,1113,1114,1115,1116,1117,2101,2501,2502', '当资产为债券类资产/理财直接融资工具/同业存单类资产时', 'E');
