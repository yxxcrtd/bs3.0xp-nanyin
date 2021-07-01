--市场机构管理新建市场机构名称白名单校验
delete from CHB_DATACHECK_RULE_INV where CHECKRULEID = '884807E5-4C08-D920-885C-107F010077FD';

Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS) values ('884807E5-4C08-D920-885C-107F010077FD','MstMarketInstitutions002','isCustomizedString','customerName',null,'^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$','Y','市场机构名称必须填写白名单中的文字。','E','com.joyintech.tams.invest.common.ChbBaseCheckLib');
