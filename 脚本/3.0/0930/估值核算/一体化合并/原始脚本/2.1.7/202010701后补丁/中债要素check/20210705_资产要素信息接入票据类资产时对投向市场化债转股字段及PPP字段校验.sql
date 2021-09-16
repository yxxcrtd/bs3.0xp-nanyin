delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-E050-123F0100125D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0100125D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PPPFLAG', null, null, 'N', '公共PPP不可以为空。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-E050-123F0100125D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0100126D', 'W23E4A49-6810-5A9E-E050-123F0100125D', 'inArray', 'ASSET_TYPE2', '==', '1201,1210', '当资产/负债类别为票据类/信用证类资产时', 'E');

delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-E050-123F0100127D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0100127D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BONDTOSTOCK_FLAG', null, null, 'N', '是否投向“市场化债转股相关产品的部分”不可以为空。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-E050-123F0100127D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0100128D', 'W23E4A49-6810-5A9E-E050-123F0100127D', 'inArray', 'ASSET_TYPE2', '==', '1201,1210', '当资产/负债类别为票据类/信用证类资产时', 'E');
