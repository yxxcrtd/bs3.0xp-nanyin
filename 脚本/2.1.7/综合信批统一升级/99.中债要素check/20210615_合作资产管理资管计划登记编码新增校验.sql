--合作资产管理资管计划登记编码新增校验
delete from CHB_DATACHECK_RULE_INV where CHECKRULEID in ('123E4A49-6810-5A9E43B7-007F01006A52');

Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS) values ('123E4A49-6810-5A9E43B7-007F01006A52','ChannelAsset002','isCustomizedString','bussRepAssetMngProdInfo.repRegistCode',null,'^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$','Y','资管计划登记编码必须填写白名单中的文字。','E','com.joyintech.tams.invest.common.ChbBaseCheckLib');
