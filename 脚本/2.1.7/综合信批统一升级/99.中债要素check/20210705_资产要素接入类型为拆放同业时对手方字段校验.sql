delete from chb_datacheck_rule where CHECKRULEID='W23E4A49-6810-5A9E-E050-123F0112345D';
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0112345D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'RIVAL_PARTY', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '对手方必须填写白名单中的文字。', 'E');

delete from chb_datacheck_condition where CHECKRULEID='W23E4A49-6810-5A9E-E050-123F0112345D';
insert into chb_datacheck_condition (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0112346D', 'W23E4A49-6810-5A9E-E050-123F0112345D', 'inArray', 'ASSET_TYPE2', '==', '1107,1108,1901,1902', '当资产/负债类别为拆放同业及买入返售/同业拆入及卖出回购类资产时', 'E');
