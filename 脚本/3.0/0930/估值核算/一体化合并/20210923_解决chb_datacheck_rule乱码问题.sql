delete from chb_datacheck_rule;
insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_PERIODYIELD-0000000000000017', 'CHB_PRD_PERIODYIELD', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'PROD_VALUE', '>', '0', 'Y', '收益率/单位净值必须大于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F02006A67', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'STATUS', '==', 'SVR_STATUS', 'N', '数据状态要素不可为空或不在值域“D-删除、E-有效”范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-1A1A-5A9E-F060-007F01006A93', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES', null, null, 'N', '行业一级分类不可为空', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-1A1A-5A9E-F060-007F01006A94', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES', '==', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 'Y', '行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B24', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BLN_AREA', '==', 'BLN_AREA', 'Y', '境内外不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B25', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRIVATEPRD_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否投向金融资产投资公司发行的私募资产管理产品不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B26', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRIVATEFUND_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否投向金融资产投资公司或其附属机构发行的私募股权投资基金不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B27', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY', '==', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 'N', '发行机构所属行业一级分类要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B28', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY', '==', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 'N', '发行机构所属行业一级分类要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B29', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES', null, null, 'N', '行业一级分类要素不可为空。', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B30', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES', '==', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 'Y', '行业一级分类不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B31', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES', '==', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 'Y', '行业一级分类不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B32', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES', null, null, 'N', '行业一级分类要素不可为空。', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B33', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES', null, null, 'N', '行业一级分类要素不可为空。', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B34', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES', '==', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 'Y', '行业一级分类不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B35', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES', null, null, 'N', '行业一级分类要素不可为空。', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B36', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES', '==', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 'Y', '行业一级分类不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B37', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRO_INDUSTRY', null, null, 'N', '融资项目所属行业一级分类要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B38', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRO_INDUSTRY', null, 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 'Y', '融资项目所属行业一级分类不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B39', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRO_INDUSTRY', null, null, 'N', '融资项目所属行业一级分类要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B40', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRO_INDUSTRY', null, 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 'Y', '融资项目所属行业一级分类不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B41', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY1', null, null, 'N', '融资人所属行业一级分类要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B42', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY1', null, 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 'Y', '融资人所属行业一级分类不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B43', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY1', null, null, 'N', '融资人所属行业一级分类要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B44', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY1', null, 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 'Y', '融资人所属行业一级分类不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B45', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CAP_USE_INDU', null, null, 'N', '资金运用行业一级分类要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B46', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CAP_USE_INDU', null, null, 'N', '资金运用行业一级分类要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B47', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CAP_USE_INDU', '>=', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 'Y', '资金运用行业一级分类不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B48', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IN_ASSET_TYPE_CMM', '==', '0', 'Y', '行内资产类别说明要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B49', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'GUA_TYPE_REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '担保情况说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B50', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'FIRST_EXERCISE_DATE', '==', 'yyyy-MM-dd', 'Y', '首次行权日期必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B51', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_FBZB', '==', 'SVR_YES_OR_NO', 'Y', '是否非标转标资产不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B52', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IS_FBZB', '==', '0', 'Y', '是否非标转标资产必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B53', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BANK_SEC_ZXZQ', '==', 'SVR_YES_OR_NO', 'Y', '是否专项债券不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B54', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_SEC_ZXZQ', '==', '0', 'N', '是否专项债券不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B55', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_BZHPJ', '==', 'SVR_YES_OR_NO', 'Y', '是否标准化票据不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B56', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_BZHPJ', '==', '0', 'N', '是否标准化票据不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B57', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'IS_PLEDGE', '==', '02', 'Y', '是否为质押融资只可选择“02 否”。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B58', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_VALUE', '==', '0', 'Y', '抵质押物价值（元）要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B59', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FLOAT_FACTOR', '==', '0', 'Y', '浮动因子要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B60', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE', '==', '0', 'Y', '担保方式要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_DECLARE-00-0000-000000000003', 'CHB_PRD_DECLARE', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IS_FHXGCP', '==', '0', 'Y', '新老产品标记要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A45', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'POS_AMT', '>=', '0', 'Y', '底层资产折算人民币市值（元）必须大于等于0。', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WM3E4A49-6810-5A9E-E050-007F01006A45', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'POS_AMT', '>=', '0', 'Y', '底层资产折算人民币市值（元）必须大于等于0。', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000005', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', null, 'yyyy-MM-dd', 'Y', '业务起始日必须为日期格式（YYYY-MM-DD）且为正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000006', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', null, 'yyyy-MM-dd', 'Y', '业务结束日必须为日期格式（YYYY-MM-DD）且为正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000015', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REMARK', null, '257', 'Y', '备注不能超过256字符', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000016', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'REA_YIELD', null, '8,5', 'Y', '实现收益率%以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000017', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'NEW_EXP_YIELD', null, '8,5', 'Y', '最新预期收益率以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000018', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'REMARK', null, '^[\u4e00-\u9fef\u3400-\u4db5\x{00020000}-\x{0002a6d6}\x{0002a700}-\x{0002b734}\x{0002b740}-\x{0002b81d}\x{0002b820}-\x{0002cea1}\x{0002ceb0}-\x{0002ebe0}\uf900-\ufad9\ue815-\ue86f\u00b7\u0020-\u0026\u0028-\u007B\u007D\u007E\u2000-\u2040\u2160-\u216B\u3000-\u301F\uFF00-\uFF65\uFFE0-\uFFEF]{0,}$', 'Y', '备注必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000019', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'CNY_ORL_VALUE', '==', 'ORIGINAL_VALUE', 'Y', '折算人民币初始净值与初始净值必须相等。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000020', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'CNY_PRD_VALUE', '==', 'PRD_VALUE', 'Y', '折算人民币净值与产品净值必须相等。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000021', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'CNY_TAL_VALUE', '==', 'TOTAL_VALUE', 'Y', '折算人民币累计净值与累计净值必须相等。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-107F01000018', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CCY_PURCHASE', null, null, 'N', '币种及申购兑付金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-307F01000018', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'VALUE_CCY', '==', 'CURRENCY_CHB', 'Y', '净值币种不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D7-9FA8-2777-F060-007F01000006', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '业务结束日要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112B00D6-9FA8-2777-F060-007F01000005', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '业务起始日要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112E00D6-9FA8-2777-F060-007F01000006', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '业务结束日必须大于等于业务起始日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A11', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'CNY_PRD_VALUE', '>=', '0', 'Y', '折算人民币净值必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A15', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'CNY_TAL_VALUE', '>=', '0', 'Y', '折算人民币累计净值必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A70', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'PRD_QTY', '>=', '0', 'Y', '产品份额必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A72', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'PURCHASE_QTY', '>=', '0', 'Y', '累计申购份额必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A74', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'REDEEM_QTY', '>=', '0', 'Y', '累计赎回份额必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A81', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'BALANCE_AMT', '>=', '0', 'Y', '产品余额必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A85', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ORIGINAL_VALUE', '>=', '0', 'Y', '初始净值必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A89', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'PRD_VALUE', '>=', '0', 'Y', '产品净值必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A93', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TOTAL_VALUE', '>=', '0', 'Y', '累计净值必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A97', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'CNY_ORL_VALUE', '>=', '0', 'Y', '折人民币初始净值必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A98', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ORIGINAL_VALUE', null, '10,5', 'Y', '初始净值必须为n（10,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A99', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'PRD_VALUE', null, '10,5', 'Y', '产品净值必须为n（10,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B01', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TOTAL_VALUE', null, '10,5', 'Y', '累计净值必须为n（10,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B02', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'CNY_ORL_VALUE', null, '10,5', 'Y', '折人民币初始净值必须为n（10,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B03', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'CNY_PRD_VALUE', null, '10,5', 'Y', '折算人民币净值必须为n（10,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B04', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'CNY_TAL_VALUE', null, '10,5', 'Y', '折算人民币累计净值必须为n（10,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B05', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_IMP_INT', null, null, 'N', '银行实现收益(元)要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B06', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'BANK_IMP_INT', null, '15,2', 'Y', '银行实现收益(元)必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ASSEINFO-681E-5A9E-E050-007F01000045', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PBC_ASSET_TYPE2', '==', '11', 'Y', '人行资产二级分类超长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B07', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'PURCHASE_QTY', null, '18,5', 'Y', '累计申购份额必须为n（18,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B08', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'REDEEM_QTY', null, '18,5', 'Y', '累计赎回份额必须为n（18,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B09', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PURCHASE_QTY', null, null, 'N', '累计申购份额不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B10', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REDEEM_QTY', null, null, 'N', '累计赎回份额不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B11', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'SHARE_AMT_WAN', '>=', '0', 'Y', '每万份份额分红必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B12', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'SHARE_AMT_WAN', null, '18,5', 'Y', '每万份份额分红必须为n（18,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B13', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'CASH_AMT_WAN', '>=', '0', 'Y', '每万份现金分红必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B14', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'CASH_AMT_WAN', null, '18,5', 'Y', '每万份现金分红必须为n（18,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B15', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'BALANCE_AMT', null, '15,2', 'Y', '产品余额必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B16', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'PRD_QTY', null, '18,5', 'Y', '产品份额必须为n（18,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006B17', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRD_QTY', null, null, 'N', '产品份额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A32', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_ID', null, null, 'N', '发行机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A33', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLAN_ASSET_ID', null, '41', 'Y', '对应资管及委外资产行内资产/负债编码过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A35', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ISSUE_AMT', null, '15,2', 'Y', '资管及委外资产当前总折算人民币金额（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A36', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ISSUE_AMT', '>=', '0', 'Y', '资管及委外资产当前总折算人民币金额（元）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A37', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TOTAL_NUM', null, '18,5', 'Y', '资管及委外资产当前总数量必须为n（18,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A38', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TOTAL_NUM', '>', '0', 'Y', '资管及委外资产当前总数量必须大于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A39', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'UNINVEST_CASH', null, '15,2', 'Y', '资管及委外资产未投资头寸（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A40', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'UNINVEST_CASH', '>=', '0', 'Y', '资管及委外资产未投资头寸（元）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A41', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_CODE', null, '41', 'Y', '底层资产行内资产/负债编码过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A42', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'POS_NUM', null, '18,5', 'Y', '底层资产持仓数量必须为n（18,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A43', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'POS_NUM', '>=', '0', 'Y', '底层资产持仓数量必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A44', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'POS_AMT', null, '15,2', 'Y', '底层资产折算人民币市值（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A46', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'POS_DATE', '==', 'yyyy-MM-dd', 'Y', '持仓日期必须为日期格式（YYYY-MM-DD）且为正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A36', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'UNINVEST_CASH', null, null, 'N', '资管及委外资产未投资头寸（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A37', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TOTAL_NUM', null, null, 'N', '资管及委外资产当前总数量要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A38', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_AMT', null, null, 'N', '资管及委外资产当前总折算人民币金额（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A40', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_CODE', null, null, 'N', '底层资产行内资产/负债编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A42', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'POS_NUM', null, null, 'N', '底层资产持仓数量要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A43', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'POS_AMT', null, null, 'N', '底层资产折算人民币市值（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A45', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'POS_DATE', null, null, 'N', '持仓日期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A65', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ISSUE_PARTY_ID', null, '^[0-9a-zA-Z]{6}$', 'Y', '发行机构代码要素格式不对。正确的格式：6位英文或数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ASSEINFO-681F-5A9E-E050-007F01000045', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PBC_ASSET_TYPE3', '==', '11', 'Y', '人行资产三级分类超长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A66', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'PLAN_ASSET_ID', null, '^[\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF]{0,}$', 'Y', '对应资管及委外资产行内资产/负债编码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A67', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_CODE', null, '^[\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF]{0,}$', 'Y', '底层资产行内资产/负债编码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-107F01006A65', 'CHB_BOT_AST_POS_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLAN_ASSET_ID', null, null, 'N', '对应资管及委外资产行内资产/负债编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A32', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_ID', null, null, 'N', '发行机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A65', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ISSUE_PARTY_ID', null, '^[0-9a-zA-Z]{6}$', 'Y', '发行机构代码要素格式不对。正确的格式：6位英文或数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-107F01006A65', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLAN_ASSET_ID', null, null, 'N', '对应资管及委外资产行内资产/负债编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A33', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanLength', 'PLAN_ASSET_ID', null, '41', 'Y', '对应资管及委外资产行内资产/负债编码过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A66', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'PLAN_ASSET_ID', null, '^[\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF]{0,}$', 'Y', '对应资管及委外资产行内资产/负债编码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-107F01006A37', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TOTAL_NUM', null, null, 'N', '资管及委外资产当前总数量要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A37', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TOTAL_NUM', null, '18,5', 'Y', '资管及委外资产当前总数量必须为n（18,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A38', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TOTAL_NUM', '>', '0', 'Y', '资管及委外资产当前总数量必须大于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-107F01006A38', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_AMT', null, null, 'N', '资管及委外资产当前总折算人民币金额（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A35', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ISSUE_AMT', null, '15,2', 'Y', '资管及委外资产当前总折算人民币金额（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A36', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ISSUE_AMT', '>=', '0', 'Y', '资管及委外资产当前总折算人民币金额（元）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-107F01006A36', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'UNINVEST_CASH', null, null, 'N', '资管及委外资产未投资头寸（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A39', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'UNINVEST_CASH', null, '15,2', 'Y', '资管及委外资产未投资头寸（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A40', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'UNINVEST_CASH', '>=', '0', 'Y', '资管及委外资产未投资头寸（元）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-107F01006A40', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_CODE', null, null, 'N', '底层资产行内资产/负债编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A41', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanLength', 'ASSET_CODE', null, '41', 'Y', '底层资产行内资产/负债编码过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A67', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_CODE', null, '^[\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF]{0,}$', 'Y', '底层资产行内资产/负债编码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-107F01006A42', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'POS_NUM', null, null, 'N', '底层资产持仓数量要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WM3E4A49-6810-5A9E-E050-007F01006A42', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'POS_NUM', null, '18,5', 'Y', '底层资产持仓数量必须为n（18,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WM3E4A49-6810-5A9E-E050-007F01006A43', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'POS_NUM', '>=', '0', 'Y', '底层资产持仓数量必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WM3E4A49-6810-5A9E-E050-107F01006A43', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'POS_AMT', null, null, 'N', '底层资产折算人民币市值（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WM3E4A49-6810-5A9E-E050-007F01006A44', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'POS_AMT', null, '15,2', 'Y', '底层资产折算人民币市值（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WM3E4A49-6810-5A9E-E050-107F01006A45', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'POS_DATE', null, null, 'N', '持仓日期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WM3E4A49-6810-5A9E-E050-007F01006A46', 'CHB_BOT_AST_POS', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'POS_DATE', '==', 'yyyy-MM-dd', 'Y', '持仓日期必须为日期格式（YYYY-MM-DD）且为正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ASSEINFO-6813-5A9E-A2D4-007F01000046', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'STATUS', null, null, 'N', '状态字段不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A23', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PROD_ID', null, '101', 'Y', '理财产品代码过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A26', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRD_VDATE', null, null, 'N', '产品起始日期不能为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A27', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRD_MDATE', null, null, 'N', '产品终止日期不能为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A37', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'OPEN_CIRCLE_DAYS', '==', '0', 'Y', '其他规律开放周期(天)要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A38', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RULE_COMMENT', null, null, 'N', '无规律开放说明不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('873E4A49-6810-5A9E-F060-007F01006A47', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'OPEN_BUSS_REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '开放期业务说明必须在白名单范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ya214A49-6813-5A9E-F060-007F22700013', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AVG_OPEN_COUNT', '>', '0', 'Y', '平均开放次数（年化）必须大于0.', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ya214A49-6813-5A9E-F060-007F22700033', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ACCT_NAME', null, null, 'N', '资金托管账户要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ya214A49-6813-5A9E-F060-007F22700035', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BUSI_ACCT_NO', null, null, 'N', '资金托管账号要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ya3E4A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_CONSTRACTE', '==', 'SVR_YES_OR_NO', 'Y', '是否结构化分级产品不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('yb214A49-6813-5A9E-F060-007F22700013', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AVG_OPEN_COUNT', '<=', '366', 'Y', '平均开放次数（年化）不能超过366。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys154A49-6813-5A9E-F060-007F22006A65', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'RAISE_VDATE', null, 'yyyy-MM-dd', 'Y', '募集起始日期必须为日期格式（YYYY-MM-DD）且是正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700001', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'OPEN_MODE', '==', 'SVR_OPEN_MODE', 'Y', '开放模式不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700002', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OPEN_CIRCLE', null, null, 'N', '规律开放周期不能为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700003', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'OPEN_CIRCLE', '==', '0', 'Y', '规律开放周期必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700004', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'OPEN_CIRCLE', '==', 'SVR_REGULAR_OPEN_CYCLE', 'Y', '规律开放周期不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700005', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OPEN_CIRCLE_DAYS', null, null, 'N', '其他规律开放周期(天)要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700006', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'OPEN_CIRCLE_DAYS', '==', '0', 'Y', '其他规律开放周期(天)要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700007', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RULE_COMMENT', '==', '0', 'Y', '无规律开放说明必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700008', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RULE_COMMENT', null, '257', 'Y', '无规律开放说明过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700009', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'FIRST_OPEN_DAY', null, 'yyyy-MM-dd', 'Y', '首次开放周期起始日必须为日期格式（YYYY-MM-DD）且是正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700011', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'HOLIDAY_ON_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '节假日是否开放不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700013', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AVG_OPEN_COUNT', null, '5,2', 'Y', '平均开放次数（年化）必须为n（5,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700015', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'OPEN_BUSS', '==', 'SVR_OPEN_BUSS', 'Y', '开放期业务不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700016', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OPEN_BUSS_REMARK', null, null, 'N', '开放期业务说明不能为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700017', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'OPEN_BUSS_REMARK', null, '257', 'Y', '开放期业务说明不能超过256字符', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700024', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MANAGE_TYPE', null, null, 'N', '管理方式不能为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700025', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'MANAGE_TYPE', '==', 'SVR_PRO_STYLE', 'Y', '管理方式不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700028', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'YIELD_LOW', null, '8,5', 'Y', '业绩比较基准下限以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700029', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'YIELD_HIGH', null, '8,5', 'Y', '业绩比较基准上限以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700031', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'YIELD_REMARK', null, '401', 'Y', '业绩比较基准说明不能超过400字符', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700032', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'YIELD_REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '业绩比较基准说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700033', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ACCT_NAME', null, '201', 'Y', '资金托管账户不能超过200字符', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700034', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'ACCT_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资金托管账户必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700035', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BUSI_ACCT_NO', null, '61', 'Y', '资金托管账号不能超过60字符', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700036', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'BUSI_ACCT_NO', null, '^[A-Za-z0-9-]+$', 'Y', '资金托管账号要素格式不对。正确的格式：大小写字母或数字以及“-”。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys3E4A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_CONSTRACTE', null, null, 'N', '是否为结构化（分级）产品要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A67', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RAISE_ENDDATE', null, null, 'N', '募集结束日期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A68', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_ID', null, null, 'N', '发行机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A69', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ISSUE_PARTY_ID', null, '^[0-9a-zA-Z]{6}$', 'Y', '发行机构代码要素格式不对。正确的格式：6位英文或数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A70', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PROD_REGIST_CODE', null, null, 'N', '产品登记编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A71', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'PROD_REGIST_CODE', null, '^([CZ][0-9]{7}[A]?[0-9]{6})$', 'Y', '产品登记编码要素格式不对。正确的格式：6 位金融机构代码；1 位标识码(可为空);2 位年度码；6 位顺序码。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A72', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PROD_ID', null, null, 'N', '理财产品代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A73', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'PROD_ID', null, '^[\u0030-\u0039\u0041-\u005a\u0061-\u007a\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '理财产品代码必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A74', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RAISE_VDATE', null, null, 'N', '募集起始日期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A75', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'RAISE_ENDDATE', null, 'yyyy-MM-dd', 'Y', '募集结束日期必须为日期格式（YYYY-MM-DD）且是正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A76', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'RAISE_ENDDATE', '>=', 'RAISE_VDATE', 'Y', '募集结束日期必须大于募集起始日期', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A77', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'PRD_VDATE', null, 'yyyy-MM-dd', 'Y', '产品起始日期必须为日期格式（YYYY-MM-DD）且是正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A78', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'PRD_MDATE', null, 'yyyy-MM-dd', 'Y', '产品终止日期必须为日期格式（YYYY-MM-DD）且是正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A79', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'PRD_VDATE', '>=', 'RAISE_ENDDATE', 'Y', '产品起始日期必须大于等于募集结束日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A80', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'PRD_MDATE', '>', 'PRD_VDATE', 'Y', '产品终止日期必须大于产品起始日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A81', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'OPEN_CIRCLE_DAYS', null, '4,0', 'Y', '其他规律开放周期(天)必须为n..4格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A82', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'OPEN_CIRCLE_DAYS', '>', '0', 'Y', '其他规律开放周期(天)必须大于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys8E4A49-6813-5A9E-F060-007F21706A83', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'RULE_COMMENT', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '无规律开放说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('99A882DC-FF4A-4334-E053-045AA8C01535', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'UNIT_VAL_NET', null, null, 'N', '单位估值（净价）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZC1BE4A49-6813-5A9E-F060-007F0106A69', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VAL_DATE', null, null, 'N', '估值日期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZC2BE4A49-6813-5A9E-F060-007F0106A85', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'UNIT_VAL_TOTAL', '>=', 'UNIT_VAL_NET', 'Y', '单位估值（全价）必须大于等于单位估值（净价）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ASSEINFO-681G-5A9E-E050-007F01000045', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'STATUS', '==', 'SVR_STATUS', 'Y', '状态字段不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBE4A49-6813-5A9E-F060-007F0106A69', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VAL_DATE', null, 'yyyy-MM-dd', 'Y', '估值日期必须是日期格式（yyyy-mm-dd）且需为正确日期', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBE4A49-6813-5A9E-F060-007F0106A71', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'UNIT_VAL_NET', null, '17,4', 'Y', '单位估值（净价）必须为n（17,4）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBE4A49-6813-5A9E-F060-007F0106A78', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REMARK', null, '257', 'Y', '备注不能超过256', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBE4A49-6813-5A9E-F060-007F0106A79', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ISSUE_PARTY_ID', null, '^[0-9a-zA-Z]{6}$', 'Y', '发行机构代码要素格式不对。正确的格式：6位英文或数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBE4A49-6813-5A9E-F060-007F0106A80', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_ID', null, null, 'N', '发行机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBE4A49-6813-5A9E-F060-007F0106A81', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_CODE', null, null, 'N', '行内资产/负债编码要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBE4A49-6813-5A9E-F060-007F0106A82', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_CODE', null, '41', 'Y', '行内资产/负债编码过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBE4A49-6813-5A9E-F060-007F0106A83', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_CODE', null, '^[\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF]{0,}$', 'Y', '行内资产/负债编码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBE4A49-6813-5A9E-F060-007F0106A84', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'UNIT_VAL_TOTAL', null, null, 'N', '单位估值（全价）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBE4A49-6813-5A9E-F060-007F0106A85', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'UNIT_VAL_TOTAL', null, '17,4', 'Y', '单位估值（全价价）必须为n（17,4）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBE4A49-6813-5A9E-F060-007F0106A86', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '备注必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A01', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRADE_CODE', null, '33', 'Y', '行内交易编码过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A02', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '发生金额必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A03', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '发生金额必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A04', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'NUM', null, '18,5', 'Y', '数量必须为n（18,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A05', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'NUM', '>=', '0', 'Y', '数量必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A06', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CUR_ASSETDET_TYPE', null, '61', 'Y', '资金流动类型说明过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A07', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEALDATE', null, null, 'N', '交易日要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A08', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRADE_COUNTER', null, '201', 'Y', '交易对手方过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A09', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'END_RATE', null, '8,5', 'Y', '到期收益率以百分数形式表示时必须为n(8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A10', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CONTRACT_CODE', null, '101', 'Y', '成交编号/合同号过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A12', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REMARK', null, '257', 'Y', '备注要素文字长度（最大长）256', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A25', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'UNIT_PRICE_FULL', null, null, 'N', '单位成交价格（全价）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A26', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'UNIT_PRICE_FULL', null, '17,4', 'Y', '单位成交价格（全价）必须为n(17,4)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A27', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'UNIT_PRICE_FULL', '>=', 'UNIT_PRICE_NET', 'Y', '单位成交价格（全价）必须大于等于单位成交价格（净价）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A29', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'UNIT_PRICE_NET', null, null, 'N', '单位成交价格（净价）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A30', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'UNIT_PRICE_NET', null, '17,4', 'Y', '单位成交价格（净价）必须为n(17,4)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A31', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CUR_ASSET_TYPE', '==', 'SVR_FUND_FLOW_TYPE', 'Y', '资金流动类型不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A01', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRADE_CODE', null, null, 'N', '行内交易编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A03', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CNY_AMT', null, null, 'N', '折算人民币金额（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A04', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'NUM', null, null, 'N', '数量要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A07', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'DEALDATE', '==', 'yyyy-MM-dd', 'Y', '交易日必须为日期格式（YYYY-MM-DD）且为正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A09', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CONTRACT_CODE', null, null, 'N', '成交编号/合同号要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A31', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CUR_ASSETDET_TYPE', null, null, 'N', '资金流动类型说明要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-207F01006A07', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRADE_COUNTER', null, null, 'N', '交易对手方要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('103E4A49-6810-5A9E-F060-007F01006A05', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_METHOD', null, null, 'N', '资产计量方式要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A16', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'CUR_ASSETDET_TYPE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资金流动类型说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A17', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'CONTRACT_CODE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '成交编号/合同号必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A18', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '备注必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('203E4A49-6810-5A9E-F060-007F01006A05', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_METHOD', '==', '0', 'Y', '资产计量方式要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('8574CD55-FFAF-41C9-AAA3-F9595B864230', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'CNY_AMT', '==', 'AMT', 'Y', '折算人民币金额（元）与发生金额必须相等', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007901', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RIVAL_PARTY_TYPE', null, null, 'N', '交易对手方类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007902', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'REGIST_BANK_CODE', null, '^[0-9a-zA-Z]{6}$', 'Y', '发行机构代码要素格式不对。正确的格式：6位英文或数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007903', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REGIST_BANK_CODE', null, null, 'N', '发行机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007904', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PROD_REGIST_CODE', null, null, 'N', '产品登记编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007905', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'PROD_REGIST_CODE', null, '^([CZ][0-9]{7}[A]?[0-9]{6})$', 'Y', '产品登记编码要素格式不对。正确的格式：6 位金融机构代码；1 位标识码(可为空);2 位年度码；6 位顺序码。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007906', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TRADE_CODE', null, '^[\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF]{0,}$', 'Y', '行内交易编码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007907', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_CODE', null, null, 'N', '行内资产/负债编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007908', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_CODE', null, '41', 'Y', '行内资产/负债编码过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007909', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_CODE', null, '^[\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF]{0,}$', 'Y', '行内资产/负债编码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007910', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'CNY_AMT', null, '15,2', 'Y', '折算人民币金额（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007911', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'CNY_AMT', '>=', '0', 'Y', '折算人民币金额（元）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007912', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'ASSET_METHOD', '==', 'SVR_ASSET_METHOD', 'Y', '资产计量方式不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007913', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'TRADE_COUNTER', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '交易对手方必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F01007914', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'Y', '备注要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-107F01007901', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RIVAL_PARTY_TYPE', '==', 'SVR_RIVAL_PARTY_TYPE', 'Y', '交易对手方类型不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('11194A80-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'OUT_DEP_NAME', '==', '201', 'Y', '境外托管机构名称要素超长（200）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('11294A49-6813-5A9E-F060-007F01006A67', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'SHORT_TERM', null, '5,0', 'Y', '最短持有期限（天）必须为n..5格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('1e184A86-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CREDIT_MODE', '==', 'SVR_CREDIT_MODE', 'Y', '产品增信形式要素不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('1e194A89-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REMARK', '==', '257', 'Y', '备注要素超长（256）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('1e234A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINAN_SER_MODEL', '==', 'SVR_SERVICEMODE', 'Y', '理财业务服务模式要素不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('1e234A49-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OPERATE_MODE', null, null, 'N', '产品运作模式要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('1e324A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_FREE_REDEM', '==', 'SVR_YN_REDEM', 'Y', '最短持有期后是否自由赎回要素不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('1e364A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CASH_MANA_CATE', '==', 'YES_NO', 'Y', '是否现金管理类要素不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('1e424A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MNAME', '==', '121', 'Y', '实际管理人名称要素超长（最大长）120', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('1e464A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'COOPERATION_NAME', null, '121', 'Y', '合作机构名称要素超长（最大长）120', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('1e4E4A67-6813-5A9E-F060-007F01006A67', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IN_DEP_NAME', '==', 'SVR_IN_DEP_NAME', 'Y', '境内托管机构名称不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de104A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'LIAISON_TEL', null, '31', 'Y', '业务联络人座机过长（最大长）30。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de104A49-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'LIAISON_TEL', null, null, 'N', '业务联络人座机要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de114A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'LIAISON_MOBILE', null, null, 'N', '业务联络人手机必填', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de124A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'LIAISON_EMAIL', null, '51', 'Y', '业务联络人邮箱过长（最大长）50。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de134A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PROFIT_TYPE', '==', 'SVR_PROD_PROFIT_TYPE', 'Y', '产品收益类型不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de134A49-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TERMTYPE', null, null, 'N', '产品期限要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de144A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'TERMTYPE', '==', 'SVR_PRODUCT_TERM', 'Y', '产品期限不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de144A49-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EXC_FIN_SECTOR', null, null, 'N', '是否金融同业专属要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de154A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RAISE_TYPE', '==', 'SVR_RAISE_TYPE', 'Y', '产品募集方式不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de154A49-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RAISE_TYPE', null, null, 'N', '产品募集方式要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de164A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'EXC_FIN_SECTOR', '==', 'YES_NO', 'Y', '是否金融同业专属不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de174A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_ARAE', null, null, 'N', '资金投向地区要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de174A49-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INVEST_ARAE', '==', 'SVR_SALEAREA_DECLARE', 'Y', '资金投向地区不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de184A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_COUNTRY', null, null, 'N', '产品投资国家或地区要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de184A49-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValue', 'INVEST_COUNTRY', '!=', 'CHN', 'Y', '产品投资国家或地区不能填写中国。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de184A86-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_TYPE', null, null, 'N', '产品增信机构类型要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A52-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RISK_TYPE', null, null, 'N', '投资者风险偏好要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A53-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SALE_AREA', null, null, 'N', '产品销售区域要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A54-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RAISE_CCY', null, null, 'N', '募集币种要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A55-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CASH_PRIN_CCY', null, null, 'N', '兑付本金币种要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A56-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CASH_PROF_CCY', null, null, 'N', '兑付收益币种要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A76-6813-5A9E-F060-007F01006A03', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OUT_DEP_CTRY', null, null, 'N', '境外托管机构国别要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A77-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'OUT_DEP_CTRY', '==', '0', 'Y', '境外托管机构国别要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('BASEINFO-ffTT-5A9E-E050-007F01000003', 'CHB_PRD_BASEINFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'FPROD_ID', null, '^[\u0030-\u0039\u0041-\u005a\u0061-\u007a\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '父产品代码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A78-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OUT_DEP_NAME', null, null, 'N', '境外托管机构名称要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A79-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'OUT_DEP_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '境外托管机构名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A80-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'OUT_DEP_NAME', '==', '0', 'Y', '境外托管机构名称要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A81-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TRU_FEE_RATE', '>=', '0', 'Y', '托管费率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A82-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TRU_FEE_RATE', null, '8,5', 'Y', '托管费率%以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A82-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRU_FEE_RATE', null, null, 'N', '托管费率%要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A87-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CREDIT_TYPE', '==', '0', 'Y', '产品增信机构类型要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A89-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CREDIT_MODE', '==', '0', 'Y', '产品增信形式要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A90-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A91-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A92-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A93-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A94-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A95-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de194A96-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de1E4A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PROD_NAME', null, null, 'N', '产品名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de1E4A49-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PROD_NAME', null, '201', 'Y', '产品名称超长（最大长）200', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de204A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INVEST_COUNTRY', '==', '0', 'Y', '产品投资国家或地区要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de224A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FINAN_SER_MODEL', null, null, 'N', '理财业务服务模式要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de234A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FINAN_SER_MODEL', '==', '0', 'Y', '理财业务服务模式要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de244A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'OPERATE_MODE', '==', 'SVR_OPERATEMODE_DECLARE', 'Y', '产品运作模式不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de244A61-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'RAISE_PENDDATE', '>=', 'RAISE_PSTRDATE', 'Y', '募集起始日期（到）必须大于等于募集起始日期（从）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de254A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_SHORT_TERM', null, null, 'N', '是否设置最短持有期限要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de264A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_SHORT_TERM', '==', 'YES_NO', 'Y', '是否设置最短持有期限不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de274A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IS_SHORT_TERM', '==', '0', 'Y', '是否设置最短持有期限要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de284A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'SHORT_TERM', '==', '0', 'Y', '最短持有期限（天）要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHORT_TERM', null, null, 'N', '最短持有期限（天）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B11', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'PROD_NAME', 'inWhitelistNoSymbol', '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '产品名称必须填写白名单中的文字，仅可由汉字、阿拉伯数字、英文字母组成，建议名称中不要含有任何符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B12', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'PROD_INS_ID', null, '^[\u0030-\u0039\u0041-\u005a\u0061-\u007a\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '行内标识码只能含有白名单内的阿拉伯数字，英文字母，半角符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('BASEINFO-FFTT-5A9E-E050-007F01000002', 'CHB_PRD_BASEINFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FPROD_ID', null, '101', 'Y', '父产品代码超长（最大长100）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_BONUS_DEAL-00000000000000020', 'CHB_PRD_BONUS_DEAL', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRADE_ID', null, '51', 'Y', '上游系统交易编码超长（最大长50）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B13', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'APPROVER_ID', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '产品审批人身份证号必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B14', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'DESIGNER_ID', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '产品设计人身份证号必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B15', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'MANAGER_ID', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '投资经理身份证号必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B16', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'LIAISON_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '业务联络人姓名必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B17', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'MNAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '实际管理人名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B18', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'IN_DEP_CODE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '境内托管机构代码必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B20', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'PROD_BRAND', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '产品品牌必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B21', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'COOPERATION_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '合作机构名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B22', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'INV_TYPE_RAT', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '投资资产种类及比例必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B23', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '备注必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B24', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ISSUE_PARTY_ID', null, '^[0-9a-zA-Z]{6}$', 'Y', '发行机构代码要素格式不对。正确的格式：6位英文或数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B25', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'LIAISON_TEL', null, '^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,8})?$', 'Y', '业务联络人座机不合法,合法的模式：XXXX(3或4位)-XXXXXXXX（7或8位）-XXXXX（小于等于8位）分机号码可以不填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B26', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'LIAISON_MOBILE', null, '^1\d{10}$', 'Y', '业务联络人手机不合法,合法的模式：13488996666（11位）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B27', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'LIAISON_EMAIL', null, '^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_.-]+(\.[a-zA-Z0-9_.-]+)*\.[a-zA-Z0-9]{2,6}$', 'Y', '业务联络人邮箱地址不合法,合法email格式如：John@163.com。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B29', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INVEST_COUNTRY', '<', 'SVR_COUNTRY_AREA', 'Y', '产品投资国家或地区不在值域范围内并且产品投资国家或地区的多选选项不可重复。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_BONUS_DEAL-00000000000000021', 'CHB_PRD_BONUS_DEAL', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRADE_ID', null, null, 'N', '上游系统交易编码要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_BONUS_DEAL-00000000000000022', 'CHB_PRD_BONUS_DEAL', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TRADE_ID', null, '^[\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF]{0,}$', 'Y', '上游系统交易编码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_BONUS_DEAL-00000000000000023', 'CHB_PRD_BONUS_DEAL', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REG_TRADE_QTY', null, null, 'N', '权益登记日份额要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B30', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RAISE_CCY', '<', 'CURRENCY_CHB', 'Y', '募集币种区域不在值域范围内并且募集币种的多选选项不可重复。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B31', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CASH_PRIN_CCY', '<', 'CURRENCY_CHB', 'Y', '兑付本金币种不在值域范围内并且兑付本金币种的多选选项不可重复。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B32', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CASH_PROF_CCY', '<', 'CURRENCY_CHB', 'Y', '兑付收益币种不在值域范围内并且兑付收益币种的多选选项不可重复。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-007F01006B33', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'OUT_DEP_CTRY', '<', 'SVR_COUNTRY_AREA', 'Y', '境外托管机构国别不在值域范围内并且境外托管机构国别的多选选项不可重复。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-107F01006B14', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DESIGNER_ID', null, '31', 'Y', '产品设计人身份证号过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-107F01006B15', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MANAGER_ID', null, '31', 'Y', '投资经理身份证号过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-107F01006B16', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'LIAISON_NAME', null, '33', 'Y', '业务联络人姓名过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-107F01006B26', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'LIAISON_EMAIL', null, null, 'N', '业务联络人邮箱要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-107F01006B27', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PROFIT_TYPE', null, null, 'N', '产品收益类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de2E4A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PROD_INS_ID', null, null, 'N', '行内标识码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de2E4A49-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PROD_INS_ID', null, '101', 'Y', '行内标识码超长（最大长100）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de304A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'SHORT_TERM', '==', '0', 'Y', '最短持有期限（天）要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de304A71-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IN_DEP_NAME', '==', '0', 'Y', '境内托管机构名称要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de304A73-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IN_DEP_CODE', '==', '0', 'Y', '境内托管机构代码要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de314A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'SHORT_TERM', '>', '0', 'Y', '最短持有期限（天）必须大于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de314A58-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'SALE_AMT_STR', '>', '0', 'Y', '起点销售金额必须大于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de314A60-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'RAISE_AMT_PLAN', '>', '0', 'Y', '计划募集金额（元）必须大于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de314A61-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'RAISE_PSTRDATE', '==', 'yyyy-MM-dd', 'Y', '募集起始日期（从）必须为日期格式（YYYY-MM-DD）且为正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de314A61-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RAISE_PSTRDATE', null, null, 'N', '募集起始日期（从）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de314A62-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'RAISE_PENDDATE', '==', 'yyyy-MM-dd', 'Y', '募集起始日期（到）必须为日期格式（YYYY-MM-DD）且为正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de314A62-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RAISE_PENDDATE', null, null, 'N', '募集起始日期（到）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de324A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_FREE_REDEM', null, null, 'N', '最短持有期后是否自由赎回要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de324A49-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IS_FREE_REDEM', '==', '0', 'Y', '最短持有期后是否自由赎回要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de334A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IS_FREE_REDEM', '==', '0', 'Y', '最短持有期后是否自由赎回要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de344A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CASH_MANA_CATE', null, null, 'N', '是否现金管理类要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de374A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CASH_MANA_CATE', '==', '0', 'Y', '是否现金管理类要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de384A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CMMODE', '==', 'SVR_CMMODE', 'Y', '产品资产配置方式不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de384A49-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CMMODE', null, null, 'N', '产品资产配置方式要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de394A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PMMODE', '==', 'SVR_PROD_MANAGE', 'Y', '产品管理模式不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de394A49-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PMMODE', null, null, 'N', '产品管理模式要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de3F4B49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PROD_BRAND', null, '121', 'Y', '产品品牌过长（最大长120）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de404A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MNAME', null, null, 'N', '实际管理人名称要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de434A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MNAME', '==', '0', 'Y', '实际管理人名称要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de444A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRICED_MODE', '==', 'SVR_PRODVALUETYPE', 'Y', '产品定价方式不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de444A49-6813-5A9E-F060-17F01006A65 ', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRICED_MODE', null, null, 'N', '产品定价方式要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de454A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IVSTORIENTATION', '==', 'INVEST_TYPE', 'Y', '产品投资性质不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de454A49-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IVSTORIENTATION', null, null, 'N', '产品投资性质要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464101-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'TERM_RIGHT_FLAG', '==', 'SVR_HAVE_OR_NOT', 'Y', '发行机构提前终止权标识不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464101-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TERM_RIGHT_FLAG', null, null, 'N', '发行机构提前终止权标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CONT_MODE', '==', 'SVR_CONTMODE', 'Y', '合作模式不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A49-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CONT_MODE', null, null, 'N', '合作模式要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A63-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INVEST_PRIN_DATE', '==', 'SVR_AMTDATETYPE', 'Y', '投资本金到账日不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A63-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_PRIN_DATE', null, null, 'N', '投资本金到账日要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A64-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INVEST_PROF_DATE', '==', 'SVR_AMTDATETYPE', 'Y', '投资收益到账日不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A64-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_PROF_DATE', null, null, 'N', '投资收益到账日要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A69-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IN_DEP_NAME', null, null, 'N', '境内托管机构名称要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A70-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValue', 'OUT_DEP_CTRY', '!=', 'CHN', 'Y', '境外托管机构国别不能填写中国。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A83-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RISK_LEVEL', '==', 'PRODUCT_RISK_GRADE', 'Y', '产品风险等级不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A83-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RISK_LEVEL', null, null, 'N', '产品风险等级要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A84-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CUS_REDEEM_FLAG', '==', 'YES_NO', 'Y', '客户赎回权标识不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A85-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CREDIT_FLAG', '==', 'YES_NO', 'Y', '产品增信标识不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A85-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_FLAG', null, null, 'N', '产品增信标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de464A88-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_MODE', null, null, 'N', '产品增信形式要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de469111-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CUS_REDEEM_FLAG', null, null, 'N', '客户赎回权标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de474A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'COOPERATION_NAME', null, null, 'N', '合作机构名称要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de484A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'COOPERATION_NAME', '==', '0', 'Y', '合作机构名称要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de494A49-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INV_TYPE_RAT', null, '301', 'Y', '投资资产种类及比例超长（最大长）300', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de494A49-6813-5A9E-F060-107F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INV_TYPE_RAT', null, null, 'N', '投资资产种类及比例要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de494A74-6813-5A9E-F060-007F01006A65', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IN_DEP_CODE', '==', '13', 'Y', '境内托管机构代码要素超长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'PROD_STAGE', null, '6,0', 'Y', '产品期次必须为n..6格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A49-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PROD_STAGE', null, null, 'N', '产品期次要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A50-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'YIELD', null, '8,5', 'Y', '业绩比较基准%必须为n..(8,5)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A50-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'YIELD', '>=', '0', 'Y', '业绩比较基准必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_BONUS_DEAL-00000000000000024', 'CHB_PRD_BONUS_DEAL', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRADE_QTY', null, null, 'N', '红利再投份额要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_BONUS_DEAL-00000000000000025', 'CHB_PRD_BONUS_DEAL', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRADE_AMT', null, null, 'N', '红利再投金额要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_BONUS_DEAL-00000000000000026', 'CHB_PRD_BONUS_DEAL', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INT_AMT', null, null, 'N', '现金分红金额要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('PRDYIELD-6813-5A9E-A2D4-007F01000046', 'CHB_PRD_PERIODYIELD', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'STATUS', null, null, 'N', '状态字段不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('PRDYIELD-6813-5A9E-E050-007F01000045', 'CHB_PRD_PERIODYIELD', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'STATUS', '==', 'SVR_STATUS', 'Y', '状态字段不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('PRDYIELD-6813-5A9E-2D2C-007F01000046', 'CHB_PRD_PERIODYIELD', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REV_DATE', null, null, 'N', '撤销日字段不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A80', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EMPLOYEE_TYPE', null, null, 'N', '从业人员类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A81', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'EMPLOYEE_TYPE', '==', 'SVR_EMPLOYEE_TYPE', 'Y', '从业人员类型不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A82', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EMPLOYEE_NAME', null, null, 'N', '姓名要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A83', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EMPLOYEE_NAME', null, '201', 'Y', '姓名过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A84', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'EMPLOYEE_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '姓名必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A85', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EMPLOYEE_SEX', null, null, 'N', '性别要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A86', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'EMPLOYEE_SEX', '==', 'SVR_SEX', 'Y', '性别不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A57-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'SALE_AMT_STR', null, '15,2', 'Y', '起点销售金额必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A57-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SALE_AMT_STR', null, null, 'N', '起点销售金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A59-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'RAISE_AMT_PLAN', null, '15,2', 'Y', '计划募集金额（元）必须为n(15,2)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A59-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RAISE_AMT_PLAN', null, null, 'N', '计划募集金额（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A65-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'SALE_FEE_RATE', null, '8,5', 'Y', '销售手续费率%以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A65-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SALE_FEE_RATE', null, null, 'N', '销售手续费率%要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A66-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'SALE_FEE_RATE', '>=', '0', 'Y', '销售手续费率%必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A67-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'INVEST_FEE_RATE', null, '8,5', 'Y', '投资管理费率%以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A67-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_FEE_RATE', null, null, 'N', '投资管理费率%要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A68-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INVEST_FEE_RATE', '>=', '0', 'Y', '投资管理费率%必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de5E4A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_ID', null, null, 'N', '发行机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de6E4A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'APPROVER_ID', null, null, 'N', '产品审批人身份证号要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de6E4A49-6813-5A9E-F060-107F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'APPROVER_ID', null, '31', 'Y', '产品审批人身份证号过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de7E4A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DESIGNER_ID', null, null, 'N', '产品设计人身份证号要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de8E4A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MANAGER_ID', null, null, 'N', '投资经理身份证号要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de8E4A49-6813-5A9E-F060-107F21206A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'RISK_LEVEL', '==', '01', 'Y', '产品风险等级必须选择“01 一级（低）”。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de8E4A49-6813-5A9E-F060-107F21206A67', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'RISK_LEVEL', '==', '01,02', 'Y', '产品风险等级必须选择“01 一级（低）”或者“02 二级（中低）”。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de8E4A49-6813-5A9E-F060-107F21206A68', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'RISK_LEVEL', '==', '01,02,03', 'Y', '产品风险等级必须选择“01 一级（低）”或者“02 二级（中低）”或者“03 三级（中）”。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de8E4A49-6813-5A9E-F060-107F21206A69', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'RISK_LEVEL', '==', '01,02,03,04', 'Y', '产品风险等级必须选择“01 一级（低）”或者“02 二级（中低）”或者“03 三级（中）”或者“04 四级（中高）”。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de9E4A49-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'LIAISON_NAME', null, null, 'N', '业务联络人姓名要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('PRDYIELD-6813-5A9E-222C-007F01000046', 'CHB_PRD_PERIODYIELD', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate', 'REV_DATE', '==', 'yyyy-MM-dd', 'Y', '撤销日字段不符合日期规范', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ASSEINFO-68c3-5A9E-E050-007F01000045', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BLN_AREA', '==', 'SVR_SALEAREA_DECLARE', 'Y', '境内外字段不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ASSEINFO-68a3-5A9E-E050-007F01000045', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BONDTOSTOCK_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否投向“市场化债转股相关产品的部分”字段不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ASSEINFO-68B3-5A9E-E050-007F01000045', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PPPFLAG', '==', 'SVR_YES_OR_NO', 'Y', '公共PPP字段不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A87', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_ID', null, null, 'N', '发行机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A88', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'ISSUE_PARTY_ID', null, '^[0-9a-zA-Z]{6}$', 'Y', '发行机构代码要素格式不对。正确的格式：6位英文或数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A89', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IDENTIFY_NUMBER', null, null, 'N', '身份证号要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A90', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IDENTIFY_NUMBER', null, '31', 'Y', '身份证号过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A92', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BELONGS_BANK', null, null, 'N', '所属总行或分支行要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A93', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BELONGS_BANK', '==', 'SVR_BELONGS_BANK', 'Y', '所属总行或分支行不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A94', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BELONGS_PLACE', null, null, 'N', '所属区域要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A95', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BELONGS_PLACE', '==', 'SVR_BUY_PLACE', 'Y', '所属区域不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A96', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'COMPANY_NAME', null, null, 'N', '具体单位名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A97', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'COMPANY_NAME', null, '41', 'Y', '具体单位名称过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A98', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'COMPANY_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '具体单位名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106A99', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BELONGS_DEPT', null, null, 'N', '所属部门要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B01', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BELONGS_DEPT', null, '41', 'Y', '所属部门过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B02', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'BELONGS_DEPT', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '所属部门必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B03', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'POSITION', null, null, 'N', '职位要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B04', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'POSITION', null, '41', 'Y', '职位过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B05', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'POSITION', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '职位必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B06', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EDUCATION', null, null, 'N', '学历要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B07', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'EDUCATION', '==', 'SVR_EDUCATION', 'Y', '学历不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B08', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ACADEMIC_DEGREE', null, null, 'N', '学位要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B09', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'ACADEMIC_DEGREE', '==', 'SVR_ACADEMIC_DEGREE', 'Y', '学位不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B10', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIRSTTIME2WORK', null, null, 'N', '首次参加工作时间要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B12', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIRSTTIME2FINWORK', null, null, 'N', '首次从事理财业务时间要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B14', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PROFESSIONAL_TITLE', null, null, 'N', '专业技术职称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B15', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PROFESSIONAL_TITLE', '==', 'SVR_PROFESSIONAL_TITLE', 'Y', '专业技术职称不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B16', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIN_PROFESSIONAL_CRTF', null, null, 'N', '理财专业证书要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ASSEINFO-681A-5A9E-E050-007F01000045', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ORG_ASSET_TYPE1', '==', '11', 'Y', '资产一级分类超长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ASSEINFO-681B-5A9E-E050-007F01000045', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ORG_ASSET_TYPE2', '==', '11', 'Y', '资产二级分类超长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ASSEINFO-681C-5A9E-E050-007F01000045', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ORG_ASSET_TYPE3', '==', '11', 'Y', '资产三级分类超长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ASSEINFO-681D-5A9E-E050-007F01000045', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PBC_ASSET_TYPE1', '==', '11', 'Y', '人行资产一级分类超长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B18', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'FIN_TRAINING_NO', null, '^LC\d{12}$', 'Y', '理财登记培训证书编号要素格式不对。正确的格式：LC+12位数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B19', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AWARDS', null, null, 'N', '所获奖励要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B20', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'AWARDS', null, '401', 'Y', '所获奖励过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B21', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'AWARDS', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '所获奖励必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B22', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OFFICE_TEL', null, null, 'N', '办公电话要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B23', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'OFFICE_TEL', null, '31', 'Y', '办公电话过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B24', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'OFFICE_TEL', null, '^([0-9]{3,4}-)?([0-9]{7,8}){1}(-[0-9]{1,8})?$', 'Y', '办公电话不合法,合法的模式：XXXX(3或4位)-XXXXXXXX（7或8位）-XXXXX（小于等于8位）分机号码可以不填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B25', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MOBILE_TEL', null, null, 'N', '移动电话要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B26', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'MOBILE_TEL', null, '^1\d{10}$', 'Y', '移动电话不合法,合法的模式：13488996666（11位）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B27', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MAIL', null, null, 'N', '电子邮箱要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B28', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MAIL', null, '51', 'Y', '电子邮箱过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B29', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'MAIL', null, '^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_.-]+(\.[a-zA-Z0-9_.-]+)*\.[a-zA-Z0-9]{2,6}$', 'Y', '电子邮箱地址不合法,合法email格式如：John@163.com。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B30', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REG_BUSI_TYPE', null, null, 'N', '登记业务分类要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B31', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanLength', 'REG_BUSI_TYPE', '==', '0', 'Y', '登记业务分类要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B33', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REG_EMPLOYEE_TYPE', null, null, 'N', '登记人员类别要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B34', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanLength', 'REG_EMPLOYEE_TYPE', '==', '0', 'Y', '登记人员类别要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B36', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '备注要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B37', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REMARK', null, '257', 'Y', '备注过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B38', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '备注要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B39', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '备注要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106B40', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '备注必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106C01', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'COMPANY_NAME', null, null, 'N', '具体单位名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ZCZBA4A49-6813-5A9E-E050-A07F0106C02', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'COMPANY_NAME', null, null, 'N', '具体单位名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000001', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ISSUE_PARTY_ID', null, '^[0-9a-zA-Z]{6}$', 'Y', '发行机构代码要素格式不对。正确的格式：6位英文或数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000002', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_ID', null, null, 'N', '发行机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000003', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PROD_REGIST_CODE', null, null, 'N', '产品登记编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-2777-F060-007F01000004', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'PROD_REGIST_CODE', null, '^([CZ][0-9]{7}[A]?[0-9]{6})$', 'Y', '产品登记编码要素格式不对。正确的格式：6 位金融机构代码；1 位标识码(可为空);2 位年度码；6 位顺序码。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_PRD_PERIODYIELD-0000000000000016', 'CHB_PRD_PERIODYIELD', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CHANG_TYPE', null, null, 'N', '变动类型要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-107F01006A90', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MAN_FEE_OUT', null, '15,2', 'Y', '其他机构管理费（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-107F01006A92', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'COU_FEE_OUT', null, '15,2', 'Y', '其他机构销售手续费（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-107F01006A94', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ADV_FEE_OUT', null, '15,2', 'Y', '投资顾问费用（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-107F01006A99', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRD_YEAR_YIELD', null, null, 'N', '产品实际年化收益率%要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-207F01006A99', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'PRD_YEAR_YIELD', null, '8,5', 'Y', '产品实际年化收益率%以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-68A0-5A9E-F060-007F01006A75', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'CUSTOM_PROFIT', null, '15,2', 'Y', '兑付客户收益（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-68B0-5A9E-F060-007F01006A76', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'CUSTOM_AMT', null, '16,2', 'Y', '兑付客户总金额（元）必须为n（16,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973G4A49-6810-5A9E-F060-007F01006A74', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'PROFIT', null, '15,2', 'Y', '银行实际实现收入（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973W4A49-6810-5A9E-F060-007F01006A73', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', null, 'yyyy-MM-dd', 'Y', '理财产品实际终止日期必须为日期格式（YYYY-MM-DD）且为正确的日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('97AE4A49-6810-5A9E-F060-007F01006A88', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'CUST_FEE_OUT', null, '15,2', 'Y', '其他机构托管费（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('9A3E4A49-6810-5A9E-F060-007F01006A82', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MAN_FEE_IN', null, '15,2', 'Y', '本机构管理费（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('9A3E4A49-6810-5A9E-F060-007F01006A84', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'COU_FEE_IN', null, '15,2', 'Y', '本机构销售手续费（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-E0WM-007F01006A64', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BANK_NAME', '==', '0', 'Y', '资金存入银行要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A48', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '存款金额必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A49', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '存款金额必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A50', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A51', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A52', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A53', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '年利率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A54', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '年利率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A55', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BASIS', null, null, 'N', '计息基础要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A56', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BASIS', '==', 'SVR_BASIS', 'Y', '计息基础要素必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A59', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET_TYPE', null, null, 'N', '结构性存款挂钩标的类别要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A60', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TARGET_TYPE', '==', '0', 'Y', '结构性存款挂钩标的类别要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A61', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET', null, null, 'N', '结构性存款挂钩标的要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A62', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TARGET', '==', '0', 'Y', '结构性存款挂钩标的要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A63', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_NAME', null, null, 'N', '资金存入银行要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A64', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BANK_NAME', '==', '0', 'Y', '资金存入银行要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A65', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_NAME', null, null, 'N', '资金存入银行要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A67', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A68', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A69', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A70', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '年利率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF8A-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MAX_RATE', null, '8,5', 'Y', '预期最高收益率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF8B-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'MAX_RATE', '>=', '0', 'Y', '预期最高收益率%必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF8C-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MAX_RATE', '==', '0', 'Y', '预期最高收益率%要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF8D-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MIN_RATE', '==', '8,5', 'Y', '预期最低收益率%以百分数形式表示时必须为n(8,5)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF8E-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'MIN_RATE', '<=', 'MAX_RATE', 'Y', '预期最低收益率必须小于等于预期最高收益率', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF8F-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MIN_RATE', '==', '0', 'Y', '预期最低收益率%要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF90-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BUY_STRUCT', '==', 'SVR_BUY_STRUCT', 'Y', '购买结构要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF91-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'MANAGE_TYPE', '==', 'SVR_MANAGE_STYLE', 'Y', '管理方式要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF93-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MGR_FEE_RATE', '==', '8,5', 'Y', '管理费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF93-D920-F060-007F030077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MGR_FEE_RATE', '==', '8,5', 'Y', '管理费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF93-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MGR_FEE_RATE', null, null, 'Y', '管理费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF93-D920-F060-107F030077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MGR_FEE_RATE', null, null, 'Y', '管理费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF94-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'MGR_FEE_RATE', '>=', '0', 'Y', '管理费率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF95-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TRU_FEE_RATE', null, '8,5', 'Y', '托管费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF95-D920-F060-007F710077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TRU_FEE_RATE', null, '8,5', 'Y', '托管费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF95-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRU_FEE_RATE', null, null, 'N', '托管费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF95-D920-F060-107F710077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRU_FEE_RATE', null, null, 'N', '托管费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF96-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TRU_FEE_RATE', '>=', '0', 'Y', '托管费率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF97-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TRADE_FEE_RATE', null, '8,5', 'Y', '交易相关合计费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF97-D920-F060-007F910077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TRADE_FEE_RATE', null, '8,5', 'Y', '交易相关合计费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF97-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRADE_FEE_RATE', null, null, 'N', '交易相关合计费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF97-D920-F060-107F910077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRADE_FEE_RATE', null, null, 'N', '交易相关合计费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF98-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TRADE_FEE_RATE', '>=', '0', 'Y', '交易相关合计费率%必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF99-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AGENCY_FEE_RATE', '>=', '8,5', 'Y', '中介服务机构合计费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF99-D920-F060-007FX10077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AGENCY_FEE_RATE', '>=', '8,5', 'Y', '中介服务机构合计费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF99-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AGENCY_FEE_RATE', null, null, 'N', '中介服务机构合计费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF99-D920-F060-107FX10077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AGENCY_FEE_RATE', null, null, 'N', '中介服务机构合计费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF9A-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AGENCY_FEE_RATE', '>=', '0', 'Y', '中介服务机构合计费率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF9B-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'OTHER_FEE_RATE', '>=', '8,5', 'Y', '其他合计费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF9B-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OTHER_FEE_RATE', null, null, 'N', '其他合计费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF9B-D922-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'OTHER_FEE_RATE', '>=', '8,5', 'Y', '其他合计费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF9B-D922-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OTHER_FEE_RATE', null, null, 'N', '其他合计费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-207F01006A35', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SPECIAL_CMT1', null, null, 'N', '含权情况说明要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-207F01006A37', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REG_DATE_PRICE', null, null, 'N', '挂钩标的资产登记日价格要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-307F01006A22', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '股票/基金名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-407F01006A22', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '基金名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-507F01006A22', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '委外投资协议名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5AWM-E050-007F01006A30', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'MON_TYPE_COMM', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '重点监控行业和领域类别说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5AWM-E050-007F01006A32', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'IN_GRADE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资产内部评级必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5AWM-E050-007F01006A33', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'CHOSE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '选择权必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5AWM-E050-007F01006A40', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ISSUE_PARTY_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '发行机构必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5AWM-E050-007F01006A41', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'PARTY_GRADE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '发行机构主体信用评级必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6811-5A9E-E152-007F01006A26', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资管计划名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W33E4A49-6810-5A9E-E050-007F01006A68', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A00', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_STAGE', null, null, 'N', '投资阶段要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A01', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE1', null, null, 'N', '投资企业类型（按规模划分）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A02', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE1', '==', 'SVR_PARTY_TYPE1', 'Y', '投资企业类型（按规模划分）不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A05', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE2', null, null, 'N', '投资企业类型（按经济类型划分）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A06', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE2', null, 'SVR_PARTY_TYPE2', 'Y', '投资企业类型（按经济类型划分）不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A12', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE3', null, null, 'N', '投资企业类型（按技术领域划分）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A13', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A18', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_ID', null, null, 'N', '基金代码要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FF', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', '==', '0', 'Y', '代码要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FG', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '存款金额要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FH', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'AMT', '==', '0', 'Y', '存款金额要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FJ', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', '==', '0', 'Y', '名称要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FK', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_CODE', null, null, 'N', '行内资产/负债编码不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FL', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（现金及银行存款）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FM', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（现金及银行存款）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FO', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（拆放同业及买入返售/同业拆入及卖出回购）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FP', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（拆放同业及买入返售/同业拆入及卖出回购）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FQ', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（新增可投资资产以及非标准化债权类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FR', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（新增可投资资产以及非标准化债权类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FS', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（非标准化债权类投资）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FT', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（非标准化债权类投资）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FU', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（QDII债券类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FV', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（QDII债券类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FW', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（QDII结构性票据）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FX', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（QDII结构性票据）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FY', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（另类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FZ', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（另类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077GG', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '贴现利率（%）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JA', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（其他）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JB', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（其他）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JC', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'VDATE', '==', '0', 'Y', '起息日必须为空', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MDATE', '==', '0', 'Y', '到期日必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JE', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（QDII拆出/逆回购）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JF', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（QDII拆出/逆回购）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JG', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '年利率%不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JH', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '年利率%不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JI', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '项目收益率%不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JJ', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '贴现利率（%）不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JK', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '票面利率%不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JL', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '票面利率%不可以为空。', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JN', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '项目收益率（利率）%不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JO', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_YIELD', '==', '0', 'Y', '年利率必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JP', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '年利率%不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JQ', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BASIS', '==', '0', 'Y', '计息基础必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A71', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '年利率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A72', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BASIS', '==', 'SVR_BASIS', 'N', '计息基础要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A73', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RIVAL_PARTY', null, '201', 'N', '对手方不可为空或超长（最大长200字节）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A74', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET_TYPE1', null, null, 'N', '回购标的类别要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A75', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET_TYPE1', null, null, 'N', '回购标的类别要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A76', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TARGET_TYPE1', '==', '0', 'Y', '回购标的类别要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A78', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'TARGET_TYPE1', '==', 'SVR_TARGET_TYPE2', 'Y', '回购标的类别不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A79', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET_AMT', null, null, 'N', '回购标的金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A80', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET_AMT', null, null, 'N', '回购标的金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A81', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TARGET_AMT', '==', '0', 'Y', '回购标的金额要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A83', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TARGET_AMT', null, '15,2', 'Y', '回购标的金额必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A84', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TARGET_AMT', '>=', '0', 'Y', '回购标的金额必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A85', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REMARK', null, '257', 'Y', '备注不能超过256字符', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A86', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A87', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_ID', null, null, 'N', '股票/基金代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A88', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '股票/基金名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A90', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_NAME', null, null, 'N', '发行机构不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A91', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES2', null, null, 'N', '行业二级分类不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A92', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A94', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A95', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A96', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A97', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '年利率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A98', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '年利率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01006A99', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BASIS', '==', 'SVR_BASIS', 'N', '计息基础要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01007A64', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BANK_NAME', '==', '0', 'Y', '资金存入银行要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01016A56', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BONDTOSTOCK_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否投向“市场化债转股相关产品的部分”不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-007F01026A56', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PPPFLAG', '==', 'SVR_YES_OR_NO', 'Y', '公共PPP不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A87', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '21', 'Y', '股票/基金代码过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A90', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ISSUE_PARTY_NAME', null, '201', 'Y', '发行机构过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('003E4A49-6810-5A9E-F060-107F01006A91', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES2', '==', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('103E4A49-6810-5A9E-F060-007F01006A55', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEPO_TYPE', null, null, 'N', '存款类型要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('103E4A49-6810-5A9E-F060-007F01006A56', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DEPO_TYPE', '==', '0', 'Y', '存款类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('103E4A49-6810-5A9E-F060-007F01006A57', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'DEPO_TYPE', '==', 'SVR_DEPO_TYPE', 'Y', '存款类型要素必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF9C-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'OTHER_FEE_RATE', '>=', '0', 'Y', '其他合计费率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF9D-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF9D-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF9E-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '管理费率计算公式需要在备注中说明。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF9F-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '托管费率计算公式需要在备注中说明。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFA0-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '交易相关合计费率计算公式需要在备注中说明', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFA1-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '中介服务机构合计费率计算公式需要在备注中说明。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFA2-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '其他合计费率计算公式需要在备注中说明。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFA3-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_RIGHT', null, null, 'N', '是否为收/受益权要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFA3-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_RIGHT', null, 'SVR_YES_OR_NO', 'Y', '是否为收/受益权不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFA4-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_RESALE', null, 'SVR_YES_OR_NO', 'N', '是否属于买入返售要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFA5-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'NOTSTAND_SEC_TYPE', null, null, 'N', '类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFA5-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'NOTSTAND_SEC_TYPE', '==', 'SVR_ASSET_TYPE_BILL_LETTERCREDIT', 'Y', '类型不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFA6-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValue', 'NOTSTAND_SEC_TYPE', '==', '05', 'Y', '类型只能填写信用证。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFA7-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValue', 'NOTSTAND_SEC_TYPE', '!=', '05', 'Y', '类型不能填写信用证。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFA8-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'NOTSTAND_SEC_NUM', '==', '6,0', 'N', '数量要素不可为空且为n..6格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFA9-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'NOTSTAND_SEC_NUM', '>=', '0', 'Y', '数量必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFAA-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '合计金额要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFAA-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '合计金额必须为n（15,2）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFAB-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '金额必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFAC-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'REMAIN_TERM', null, '7,2', 'N', '加权剩余期限（天）要素不可为空且为(7,2)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFAD-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'REMAIN_TERM', '>=', '0', 'Y', '加权剩余期限（天）必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFAE-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'REMAIN_TERM', '<=', 'MAX_REMAIN_TERM', 'Y', '加权剩余期限（天）≤最长剩余期限（天）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFAF-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'MIN_REMAIN_TERM', null, '5,0', 'N', '最短剩余期限（天）要素不可为空且为n..5格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFB0-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'MAX_REMAIN_TERM', null, '5,0', 'N', '最长剩余期限（天）要素不可为空且为n..5格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFB1-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'MIN_REMAIN_TERM', '>=', '0', 'Y', '最短剩余期限（天）必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFB2-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'MAX_REMAIN_TERM', '>=', '0', 'Y', '最长剩余期限（天）必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFB3-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFB4-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', null, 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFB5-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFB6-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '贴现利率（%）要素不可为空且为(8,5)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFB7-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'DISCOUNT_YIELD', '>=', '0', 'Y', '贴现利率（%）要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFB8-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES2', null, null, 'N', '行业二级分类不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A19', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '基金名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A20', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES2', null, null, 'N', '行业要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A21', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'REGIST_ORG1', '==', 'SVR_REGIST_ORG2', 'Y', '登记备案机构不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A22', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_GOVERN_FUND', '==', 'SVR_YES_OR_NO', 'Y', '是否属于政府投资基金不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A23', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FUND_ENPE_NAME', null, '201', 'Y', '基金公司名称过长。', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A24', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MNG_ORG_NAME', null, '201', 'N', '基金管理机构名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A25', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRU_ORG_NAME', null, '201', 'N', '基金托管机构名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A26', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FUND_INVEST_ASSET', null, '401', 'Y', '基金投资资产过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A27', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A28', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IN_ASSET_TYPE', '==', 'SVR_BANK_IN_ASSET_TYPE', 'Y', '行内资产类别不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A29', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IN_ASSET_TYPE_CMM', null, null, 'N', '行内资产类别说明要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A30', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '金额必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A31', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '金额必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A32', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）且必须为正确日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A33', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A34', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A35', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A36', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RATE_FLAG', null, null, 'N', '是否有预期收益率不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A37', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '项目收益率（利率）%以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A38', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_YIELD', '==', '0', 'Y', '项目收益率（利率）%要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A39', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'INTPAY_CYCLE', null, '2,0', 'Y', '付息频率（个月/次）必须为n..2格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A40', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INTPAY_CYCLE', '>=', '0', 'Y', '付息频率（个月/次）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A41', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FINANCIE', null, '201', 'N', '融资人要素不可为空，且长度不超过200', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A42', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIN_ORG_CODE', null, null, 'N', '融资人组织机构代码不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A43', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FCR_GRADE_OUT', null, '201', 'Y', '外部评级机构名称及对融资人评级结果过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A44', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_TYPE1', '==', 'SVR_PARTY_TYPE1', 'N', '融资人类型（按规模划分）要素不可为空,且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A45', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_TYPE2', '==', 'SVR_PARTY_TYPE3', 'N', '融资人类型（按技术领域划分）要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A46', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_TYPE3', '==', 'SVR_PARTY_TYPE2', 'N', '融资人类型（按经济类型划分）要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A47', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FINANCE_PROJ', null, '201', 'N', '融资项目要素不可为空且不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A48', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_LEVEL2_TYPE', null, null, 'N', '融资人所属行业（其他）要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A49', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRO_INDUSTRY_LEVEL2', null, null, 'N', '融资项目所属行业要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A50', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_FLAG', '==', 'SVR_YES_OR_NO', 'N', '项目是否属于重点监控行业和领域要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A51', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MONIT_TYPE', null, null, 'N', '重点监控行业和领域类别要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JR', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INTPAY_CYCLE', null, null, 'N', '付息频率（个月/次）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JS', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INTPAY_CYCLE', null, null, 'N', '付息频率（个月/次）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JT', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INTPAY_CYCLE', null, null, 'N', '付息频率（个月/次）要素不可为空。', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JU', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INTPAY_CYCLE', null, null, 'N', '付息频率（个月/次）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JV', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INTPAY_CYCLE', '==', '0', 'Y', '付息频率必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JW', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CHB_SECTYPE', null, null, 'N', '具体类别不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JX', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CHB_SECTYPE', '==', '0', 'Y', '具体类别必须为空', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JY', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CHB_SECTYPE', null, 'SVR_SEC_DETAIL_TYPE', 'Y', '具体类别不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077JZ', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ISSUE_MODE', '==', '0', 'Y', '发行方式必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077KA', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'SEC_MAIN_GRADE', '==', '0', 'Y', '主体评级必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077KB', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PART_TYPE1', '==', '0', 'Y', '发行机构类型（按规模划分）必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077KC', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PART_TYPE2', '==', '0', 'Y', '发行机构类型（按技术领域划分）必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077KD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PART_TYPE3', '==', '0', 'Y', '发行机构类型（按经济类型划分）必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077KE', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INDUSTRY_LEVEL2', '==', '0', 'Y', '发行机构所属行业必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077KF', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REGIST_ORG', '==', '0', 'Y', '登记托管机构必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077KG', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REGISTORG_COMMENT', '==', '0', 'Y', '登记托管机构说明必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077KH', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RIVAL_PARTY', '==', '0', 'Y', '对手方（拆放同业及买入返售/同业拆入及卖出回购）必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077KJ', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RIVAL_PARTY_TYPE', null, 'SVR_RIVAL_PARTY_TYPE', 'N', '对手方类型不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W85607E5-DF91-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MANAGE_TYPE', null, null, 'N', '管理方式要素不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W85607E5-DF91-D920-E050-007F037077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BUY_STRUCT', null, null, 'N', '购买结构要素不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A14', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '票面利率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A15', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '票面利率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A19', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'SPECIAL_CMT', null, '201', 'N', '是否含权等特殊条款情况说明不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A20', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A21', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CONTRACT_NAME', null, '201', 'N', '合约名称必填且不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A22', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A23', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A24', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A25', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '票面利率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A26', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '票面利率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A27', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'INTPAY_CYCLE', null, '2,0', 'Y', '付息频率（个月/次）必须为n..2格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A28', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INTPAY_CYCLE', '>=', '0', 'Y', '付息频率（个月/次）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A29', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'INCOME_RATIO', null, '8,5', 'Y', '固定收益部分所占比例以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A34', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'DER_INSTR_RATIO', '<=', '100', 'Y', '衍生金融工具所占比例必须小于等于100。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('103E4A49-6810-5A9E-F060-007F01006A63', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BANK_NAME', '==', '201', 'Y', '资金存入银行要素过长（最大长200字节）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('103E4A49-6810-5A9E-F060-007F01006A68', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('111E4A49-6810-5A9E-F060-007F01006A22', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRADE_MARKET', null, null, 'N', '交易流通场所要素必填', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('113E4A49-6810-5A9E-F060-007F01006A62', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TARGET', '==', '257', 'Y', '结构性存款挂钩标的要素过长（最大长256字节）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A14', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_CODE', null, '^[\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF]{0,}$', 'Y', '行内资产/负债编码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A15', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '备注必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A19', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'BANK_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资金存入银行必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A20', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TARGET', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '结构性存款挂钩标的必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A21', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_ID', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '代码必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A22', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A23', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'REGISTORG_COMMENT', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '登记托管机构说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A24', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'RIVAL_PARTY', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '对手方必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A25', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'REPAY_CMT', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '还本付息情况说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A26', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FINANCIE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '融资人必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A27', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FCR_GRADE_IN', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '融资人内部信用评级必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DFB8-D920-F060-007F010077FE', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES2', '==', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D001-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PLEDGE_TYPE', null, 'SVR_PLEDGE_TYPE', 'Y', '抵质押物类型要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D002-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_VALUE', null, null, 'N', '抵质押物价值（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D003-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_VALUE', null, null, 'N', '抵质押物价值（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D004-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_VALUE', '==', '0', 'Y', '抵质押物价值（元）要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D005-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_VALUE', '==', '0', 'Y', '抵质押物价值（元）要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D006-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'PLEDGE_VALUE', '>=', '15,2', 'Y', '抵质押物价值（元）要素必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D007-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'NATURE_GUARANTEE', null, null, 'N', '担保性质要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D008-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'NATURE_GUARANTEE', null, null, 'N', '担保性质要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D009-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'NATURE_GUARANTEE', '==', '0', 'Y', '担保性质要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D010-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'NATURE_GUARANTEE', '==', '0', 'Y', '担保性质要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D011-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'NATURE_GUARANTEE', null, 'SVR_NATURE_GUARANTEE', 'Y', '担保性质要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D012-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RELATION_FINAN', null, null, 'N', '担保人与融资人关系要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D013-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RELATION_FINAN', null, null, 'N', '担保人与融资人关系要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D015-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RELATION_FINAN', '==', '0', 'Y', '担保人与融资人关系要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D016-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RELATION_FINAN', '==', '0', 'Y', '担保人与融资人关系要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D017-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RELATION_FINAN', null, 'SVR_RELATION_FINAN', 'Y', '担保人与融资人关系要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D018-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CONTAIN_TYPE', '==', '0', 'Y', '含权类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D019-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CONTAIN_TYPE', null, 'SVR_CONTAIN_TYPE', 'Y', '含权类型要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D020-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EXERCISE_TYPE', null, null, 'N', '行权方式要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D021-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXERCISE_TYPE', '==', '0', 'Y', '行权方式要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D022-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'EXERCISE_TYPE', null, 'SVR_EXERCISE_TYPE', 'Y', '行权方式要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D023-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EXERCISE_DATE', null, null, 'N', '固定行权日要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D024-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXERCISE_DATE', '==', '0', 'Y', '固定行权日要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D025-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXERCISE_DATE', '==', '0', 'Y', '固定行权日要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D027-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIRST_EXERCISE_DATE', null, null, 'N', '首次行权日期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D028-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FIRST_EXERCISE_DATE', '==', '0', 'Y', '首次行权日期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D029-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FIRST_EXERCISE_DATE', '==', '0', 'Y', '首次行权日期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D031-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EXERCISE_PERIOD', null, null, 'N', '行权周期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D032-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXERCISE_PERIOD', '==', '0', 'Y', '行权周期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D033-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXERCISE_PERIOD', '==', '0', 'Y', '行权周期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D034-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'EXERCISE_PERIOD', '>=', '5,0', 'Y', '行权周期要素必须为n(5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D035-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'EXERCISE_PERIOD', '>=', '0', 'Y', '行权周期要素必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A52', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MONIT_TYPE', '==', '0', 'Y', '重点监控行业和领域类别要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A53', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MON_TYPE_COMM', null, null, 'N', '重点监控行业和领域类别说明要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A55', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'OUT_GRADE', null, 'SVR_MAIN_GRADE', 'N', '资产外部评级要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006A68', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-007F01006B41', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IN_ASSET_TYPE_CMM', null, '257', 'Y', '行内资产类别说明过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-107F01006A12', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE3', null, 'SVR_PARTY_TYPE3', 'Y', '投资企业类型（按技术领域划分）不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-107F01006A18', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '21', 'Y', '基金代码过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-107F01006A27', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-107F01006A28', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IN_ASSET_TYPE', null, null, 'N', '行内资产类别不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-107F01006A42', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIN_ORG_CODE', null, null, 'N', '融资人组织机构代码不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-107F01006A43', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FCR_GRADE_OUT', null, '201', 'Y', '外部评级机构名称及对融资人评级结果过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-107F01006A48', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_LEVEL2_TYPE', null, 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '融资人所属行业（其他）不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-107F01006A49', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRO_INDUSTRY_LEVEL2', null, 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '融资项目所属行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-107F01006A51', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'MONIT_TYPE', null, 'SVR_MOINT_TYPE', 'Y', '重点监控行业和领域类别不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-107F01006A53', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MON_TYPE_COMM', null, '257', 'Y', '重点监控行业和领域类别说明过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-117F01006A13', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E050-207F01006A53', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MON_TYPE_COMM', '==', '0', 'Y', '重点监控行业和领域类别说明要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A49-6810-5A9E-E151-007F01006A42', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FIN_ORG_CODE', null, '^[A-Z0-9]{18}$|^[A-Z0-9]{9}$', 'Y', '融资人组织机构代码不合格(应为9或18位大写字母或数字组合，如无则填写“000000000”)。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W43E4A59-6810-5A9E-E050-007F01006A36', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RATE_FLAG', null, null, 'N', '是否有预期收益率不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W53E4A49-6810-5A9E-E050-007F01006A68', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84804E5-DF7F-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CHOSE', null, null, 'N', '选择权必填，如无则填写“无”。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84804E5-DF7F-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CHOSE', null, '61', 'Y', '选择权过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF1A-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_CODE', null, '41', 'Y', '行内资产/负债编码过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF1B-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'ASSET_TYPE', null, 'SVR_ASSETDEBT_TYPE', 'Y', '资产/负债类别不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF1C-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CCY', null, null, 'N', '币种要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF1C-D920-E050-007F010177FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'ASSET_TYPE2', '==', 'SVR_ASSET_TYPE_SECOND_LEVEL', 'Y', '资产/负债二级类别要素必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF1C-D921-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CCY', '==', 'CURRENCY_CHB', 'Y', '币种不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF1E-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF1E-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF1E-D920-E050-117F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF1E-D920-E050-207F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF1F-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '16', 'Y', '代码过长（最大15字节）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF20-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A43', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'BASIC_PRICE', null, '15,2', 'Y', '挂钩标的资产基准价格必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A44', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'BASIC_PRICE', '>=', '0', 'Y', '挂钩标的资产基准价格不为空时，必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A45', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'REG_DATE_PRICE', null, '15,2', 'Y', '挂钩标的资产登记日价格必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A46', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'REG_DATE_PRICE', '>=', '0', 'Y', '挂钩标的资产登记日价格必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A47', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRADE_FEE', null, '201', 'N', '交易费不可为空且不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A49', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A50', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'NOM_CAPI', null, '15,2', 'N', '名义本金不可为空且为(15,2)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A51', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'NOM_CAPI', '>=', '0', 'Y', '名义本金必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A52', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'TARGET_TYPE3', '<', 'SVR_TARGET_TYPE', 'Y', '标的类别不在值域范围内并且标的类别的多选选项不可重复。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A54', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A55', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A56', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A57', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A58', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A59', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_VALUE', null, '15,2', 'N', '资产价值不可为空且为(15,2)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A69', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'TERM_DATE', null, '5,0', 'Y', '期限（天）必须为n..5格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A97', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_STAGE', null, null, 'N', '投资阶段要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH4807E5-DF1C-D920-E050-007F010177FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_TYPE2', null, null, 'N', '资产/负债二级类别要素不能为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH4807E5-DF43-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REPAY_CMT', '>=', '201', 'N', '还本付息情况说明要素不可为空且不可超过200', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH4807E5-DFAE-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'MIN_REMAIN_TERM', '<=', 'REMAIN_TERM', 'Y', '最短剩余期限（天）≤加权剩余期限（天）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-E050-107F01006B23', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TRUSTEE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '受托人必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4A6A-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'PROD_STAGE', '>=', '0', 'Y', '产品期次必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A53', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'MANAGER_ID', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '管理人必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A53', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'MANAGER_ID', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '管理人必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('8e294A49-6813-5A9E-F060-107F01006B51', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'IS_PLEDGE', '==', '02', 'Y', '是否为质押融资只可选择“02 否”。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF58-D920-F060-607F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE_REMARK', '==', '0', 'Y', '担保情况说明要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('ys214A49-6813-5A9E-F060-007F22700030', 'CHB_PRD_ISSUE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'YIELD_HIGH', '>=', 'YIELD_LOW', 'Y', '业绩比较基准上限必须大于等于业绩比较基准下限。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4AAA-6813-5A9E-F060-007F01006A66', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'IDENTIFY_NUMBER', null, '(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)|(^\d{14}(\d|X|x)$)', 'Y', '身份证号必须符合规范要求', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A28', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FCR_GRADE_OUT', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '外部评级机构名称及对融资人评级结果必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A29', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FINANCE_PROJ', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '融资项目必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A34', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'CONDITION_CMM', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '行权条件说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A42', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'SEC_CREDIT_GRADE_TYPE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '债券信用评级必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A43', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'GUA_TYPE_REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '担保情况说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A44', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'SPECIAL_CMT', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '是否含权等特殊条款情况说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A45', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'CONTRACT_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '合约名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A46', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'DER_INSTR_INV_TYPE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '衍生金融工具具体投资方式必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A47', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'DER_INSTR_TARGET', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '衍生金融工具挂钩标的资产必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A48', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ACCT_MODE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '利息结算方式必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A49', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'SPECIAL_CMT1', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '含权情况说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A50', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TRADE_FEE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '交易费必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A51', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'PARTY_CODE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资管计划发起人机构编码必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A52', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'CHECK_CODE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资管计划登记编码必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D036-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EXERCISE_PRICE', null, null, 'N', '行权价格要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D037-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EXERCISE_PRICE', null, null, 'N', '行权价格要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D038-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'EXERCISE_PRICE', '>=', '17,4', 'Y', '行权价格要素必须为n(17,4)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D039-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERPETUAL_CLAUSE_TYPE', null, null, 'N', '永续条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D047-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PERPETUAL_CLAUSE_TYPE', '==', '0', 'Y', '永续条款类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D048-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PERPETUAL_CLAUSE_TYPE', null, 'SVR_PERPETUAL_CLAUSE_TYPE', 'Y', '永续条款类型要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D049-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D050-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D051-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D052-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D053-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D054-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D055-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D056-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D057-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DEFERRED_INTEREST_TYPE', '==', '0', 'Y', '利息递延条款类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D058-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'DEFERRED_INTEREST_TYPE', null, 'SVR_DEFERRED_INTEREST_TYPE', 'Y', '利息递延条款类型要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D059-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_INT', null, null, 'N', '递延利息是否计息要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D060-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_INT', null, null, 'N', '递延利息是否计息要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D061-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DEFERRED_INTEREST_INT', '==', '0', 'Y', '递延利息是否计息要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D062-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DEFERRED_INTEREST_INT', '==', '0', 'Y', '递延利息是否计息要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D063-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'DEFERRED_INTEREST_INT', null, 'SVR_YES_OR_NO', 'Y', '递延利息是否计息要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D064-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIRST_REPRCE_DATE', null, null, 'N', '首次重定价日期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D065-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIRST_REPRCE_DATE', null, null, 'N', '首次重定价日期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D066-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FIRST_REPRCE_DATE', '==', '0', 'Y', '首次重定价日期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D067-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FIRST_REPRCE_DATE', '==', '0', 'Y', '首次重定价日期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D068-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'FIRST_REPRCE_DATE', null, 'yyyy-MM-dd', 'Y', '首次重定价日期要素必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D069-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REPRCE_CYCLE', null, null, 'N', '重定价周期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D070-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REPRCE_CYCLE', null, null, 'N', '重定价周期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D071-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REPRCE_CYCLE', '==', '0', 'Y', '重定价周期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D072-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REPRCE_CYCLE', '==', '0', 'Y', '重定价周期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D073-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'REPRCE_CYCLE', '>=', '5,0', 'Y', '重定价周期要素必须为n(5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D074-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'REPRCE_CYCLE', '>', '0', 'Y', '重定价周期要素必须大于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D075-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D076-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF21-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'CHB_SECTYPE', '==', '01,02,03,04,05,06,07,08,09,10,99', 'N', '具体类别要素不可为空且必须选择01-10或 99中的一项', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF22-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'CHB_SECTYPE', '==', '11,12,99', 'N', '具体类别要素不可为空且必须选择11、12、99中的一项', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF23-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'CHB_SECTYPE', '==', '13,14,15,99', 'N', '具体类别要素不可为空且必须选择13-15、99中的一项', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF24-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'CHB_SECTYPE', '==', '16,17,18,19,20,21,99', 'N', '具体类别要素不可为空且必须选择16-21、99中的一项', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF25-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'CHB_SECTYPE', '==', '22,23,24,99', 'N', '具体类别要素不可为空且必须选择22-24、99中的一项', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF26-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'CHB_SECTYPE', '==', '25,26,27,28,99', 'N', '具体类别要素不可为空且必须选择25-28、99中的一项', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF27-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'ISSUE_MODE', null, 'SVR_ISSUE_MODE_FUND', 'N', '发行方式不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF28-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SEC_MAIN_GRADE', null, 'SVR_MAIN_GRADE', 'N', '主体评级不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF28-D920-E050-007F010084FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISUI_BOND', null, null, 'N', '是否城投债（债券类资产）要素不可为空', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF28-D920-E050-007F010088FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'ISUI_BOND', '==', 'SVR_YES_OR_NO', 'Y', '是否城投债（债券类资产）要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF28-D920-E050-007F010096FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'LENDING', null, null, 'N', '借贷方向要素不可为空', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF28-D920-E050-007F010097FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'LENDING', '==', 'SVR_IS_LENDING', 'Y', '借贷方向要素不在值域范围内。', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF28-D920-E050-007F010098FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRIVATE_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '投向地方政府融资平台要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF28-D920-E050-007F010099FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INSTIT_SIZE', '==', 'SVR_INSTIT_SIZE', 'Y', '人行机构规模要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF28-D920-E050-007F010100FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INSTIT_SIZE', null, null, 'N', '人行机构规模要素不能为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF28-D981-E051-007F01007914', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF29-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PARTY_TYPE1', null, 'SVR_PARTY_TYPE1', 'N', '发行机构类型（按规模划分）要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF2A-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PARTY_TYPE2', null, 'SVR_PARTY_TYPE2', 'N', '发行机构类型（按经济类型划分）要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF2B-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PARTY_TYPE3', null, 'SVR_PARTY_TYPE3', 'N', '发行机构类型（按技术领域划分）要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF2C-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_LEVEL2', null, 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'N', '发行机构所属行业二级类型要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF2D-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'REGIST_ORG', null, 'SVR_REGIST_ORG3', 'N', '登记托管机构要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF2E-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REGISTORG_COMMENT', null, '257', 'N', '登记托管机构说明要素不可为空且不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF2F-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REGISTORG_COMMENT', '==', '0', 'Y', '登记托管机构说明要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF34-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF34-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF35-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '金额格式必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF35-D981-E051-007F01007914', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF35-D981-E051-117F01007914', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF36-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF37-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PROFIT_TYPE', '==', 'SVR_PROFIT_TYPE', 'N', '收/受益权类型要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF38-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PROFIT_TYPE', '==', '0', 'Y', '收/受益权类型要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4AAA-6813-5A9E-F060-A07F01006A66', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FIRSTTIME2WORK', null, '^\d{4}(0[1-9]|1[0-2])$', 'Y', '首次参加工作时间必须为年月格式（YYYYMM）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4AAA-6813-5A9E-F060-B07F01006A66', 'CHB_EMPLOYEE_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FIRSTTIME2FINWORK', null, '^\d{4}(0[1-9]|1[0-2])$', 'Y', '首次从事理财业务时间必须为年月格式（YYYYMM）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4AEA-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'APPROVER_ID', null, '(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)|(^\d{14}(\d|X|x)$)$)', 'Y', '产品审批人身份证号必须符合规范要求', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4AFA-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'DESIGNER_ID', null, '(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)|(^\d{14}(\d|X|x)$)$)', 'Y', '产品设计人身份证号必须符合规范要求', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de4E4AGA-6813-5A9E-F060-007F01006A66', 'CHB_PRD_DECLARE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'MANAGER_ID', null, '(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)|(^\d{14}(\d|X|x)$)$)', 'Y', '投资经理身份证号必须符合规范要求', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WA3E4A49-6810-5A9E-E050-007F01006A36', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'UNIT_VAL_NET', '>=', '0', 'Y', '单位估值（净价）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WA3EAA49-6810-5A9E-E050-007F01006A36', 'CHB_AST_VAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'UNIT_VAL_TOTAL', '>=', '0', 'Y', '单位估值（全价）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF77-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FLOAT_FACTOR', '==', '0', 'Y', '浮动因子要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF77-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FLOAT_FACTOR', '==', '0', 'Y', '浮动因子要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF56-D920-F060-207F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE', '==', '0', 'Y', '担保方式要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF56-D920-E050-207F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE', '==', '0', 'Y', '担保方式要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D025-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'EXERCISE_DATE', '==', 'yyyy-MM-dd', 'Y', '固定行权日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D025-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'EXERCISE_DATE', '==', 'yyyy-MM-dd', 'Y', '固定行权日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('We294A49-6813-5A9E-F060-107F01006B50', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'FIRST_EXERCISE_DATE', '==', 'yyyy-MM-dd', 'Y', '首次行权日期必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('WH3E4A49-6810-5A9E-E050-007F01006A42', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MIN_YIELD', null, '8,5', 'Y', '结构性票据最低收益率以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A42', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MIN_YIELD', null, '8,5', 'Y', '结构性票据最低收益率以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A40', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'MAX_YIELD', '>=', '0', 'Y', '结构性票据最高收益率必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A40', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'MAX_YIELD', '>=', '0', 'Y', '结构性票据最高收益率必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A41', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'MIN_YIELD', '<=', 'MAX_YIELD', 'Y', '结构性票据最高收益率必须大于等于结构性票据最低收益率。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A41', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'MIN_YIELD', '<=', 'MAX_YIELD', 'Y', '结构性票据最高收益率必须大于等于结构性票据最低收益率。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-207F01006A36', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MAX_YIELD', null, null, 'N', '结构性票据最高收益率要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-207F01006A36', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MAX_YIELD', null, null, 'N', '结构性票据最高收益率要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A39', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MAX_YIELD', null, '8,5', 'Y', '结构性票据最高收益率以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A39', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MAX_YIELD', null, '8,5', 'Y', '结构性票据最高收益率以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('112A00D6-9FA8-AAAA-F060-307F01000018', 'CHB_PRD_DEAL_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VALUE_CCY', null, null, 'Y', '净值币种不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D038-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXERCISE_PRICE', '==', '0', 'Y', '行权价格要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('8e294A49-6813-5A9E-F060-107F01006B48', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IN_ASSET_TYPE_CMM', '==', '0', 'Y', '行内资产类别说明要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF99-D920-G060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_TYPE', '==', '0', 'Y', '抵质押物类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D036-S050-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'EXERCISE_PRICE', '>=', '0', 'Y', '行权价格要素不能小于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CCACA7E5-EF00-D005-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'PLEDGE_VALUE', '>=', '0', 'Y', '抵质押物价值（元）要素必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('88CL07E5-EF00-D005-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_VALUE', '==', '0', 'Y', '抵质押物价值（元）要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A54', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TRUSTEE_ID', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '托管人必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A55', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'INVEST_TYPE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资金运用方式必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A56', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FUND_ENPE_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '基金公司名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A57', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'MNG_ORG_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '基金管理机构名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A58', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TRU_ORG_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '基金托管机构名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A59', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'IN_ASSET_TYPE_CMM', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '行内资产类别说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A62', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ISSUE_PARTY_ID', null, '^[0-9a-zA-Z]{6}$', 'Y', '发行机构代码要素格式不对正确的格式：6位英文或数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-007F01006A97', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INVEST_STAGE', '==', 'SVR_INVEST_STAGE', 'Y', '投资阶段要素必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-107F01006A22', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '股票/企业名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-107F01006A43', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'GUA_TYPE_REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '担保情况说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-207F01006A22', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '债券名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-307F01006A22', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '股票/基金名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-407F01006A22', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '基金名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5A9E-F060-507F01006A22', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '委外投资协议名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D077-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D078-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D079-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D080-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D081-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D082-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D083-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PART_REDEM_FLAG', '==', '0', 'Y', '部分赎回标识要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D084-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PART_REDEM_FLAG', null, 'SVR_YES_OR_NO', 'Y', '部分赎回标识要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D086-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PART_REDEM_RATE', '==', '0', 'Y', '部分赎回比例%要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D087-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PART_REDEM_RATE', '==', '0', 'Y', '部分赎回比例%要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D088-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'PART_REDEM_RATE', '>=', '8,5', 'Y', '部分赎回比例%要素必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D089-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'PART_REDEM_RATE', '>=', '0', 'Y', '部分赎回比例%要素必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D090-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D091-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D092-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D093-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D094-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D095-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D096-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D097-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D098-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CREDIT_ORG_CODE', '==', '0', 'Y', '增信机构代码要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D099-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'CREDIT_ORG_CODE', null, '(([0-9A-Z]{9})|([0-9A-Z]{18})|([\u65e0]))', 'Y', '增信机构代码要素格式不对。正确的格式：9或18位大写字母或数字（社会信用代码，如果没有则填写无）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D100-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D101-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D102-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D103-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D104-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D105-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D106-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D107-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D108-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CREDIT_ORG_NAME', '==', '0', 'Y', '增信机构名称要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D109-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CREDIT_ORG_NAME', null, '201', 'Y', '增信机构名称要素过长，最大长c..200。如果没有则填写无', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D110-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'CREDIT_ORG_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '增信机构名称要素必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF39-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_RESALE', null, 'SVR_YES_OR_NO', 'N', '是否属于买入返售要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF3A-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF3B-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', null, 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF3C-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF3E-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RATE_FLAG', null, null, 'N', '是否有预期收益率不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF3F-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '项目收益率%以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF40-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_YIELD', '==', '0', 'Y', '项目收益率%要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF41-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'INTPAY_CYCLE', null, '2,0', 'Y', '付息频率（个月/次）必须为n..2格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF42-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INTPAY_CYCLE', '>=', '0', 'Y', '付息频率（个月/次）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF43-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REPAY_CMT', '==', '0', 'Y', '还本付息情况说明要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF44-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BASIS', null, 'SVR_BASIS', 'N', '计息基础要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF45-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BASIS_TYPE', null, 'SVR_BASIS_TYPE', 'Y', '基准利率种类不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF46-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'FLOAT_FACTOR', null, '8,5', 'Y', '浮动因子（%）以百分数形式表示时必须为n(8,5)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF47-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'SPREAD_RATE', null, '8,5', 'Y', '利差（BP）以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF48-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FINANCIE', null, '201', 'N', '融资人要素不可为空，且长度不超过200', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF49-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FCR_GRADE_IN', null, '11', 'N', '融资人内部信用评级不可为空，且长度不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF4B-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_TYPE1', null, 'SVR_PARTY_TYPE1', 'N', '融资人类型（按规模划分）要素不可为空,且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF4C-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_TYPE2', null, 'SVR_PARTY_TYPE3', 'N', '融资人类型（按技术领域划分）要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF4D-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_TYPE3', null, 'SVR_PARTY_TYPE2', 'N', '融资人类型（按经济类型划分）要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF4E-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FINANCE_PROJ', null, '201', 'N', '融资项目要素不可为空且不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF4F-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_LEVEL2_TYPE', null, null, 'N', '融资人所属行业（新增可投资资产以及非标准化债权类资产）要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF4F-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_LEVEL2_TYPE', null, 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '融资人所属行业（新增可投资资产以及非标准化债权类资产）不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF50-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRO_INDUSTRY_LEVEL2', null, null, 'N', '融资项目所属行业要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF50-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRO_INDUSTRY_LEVEL2', null, 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '融资项目所属行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF51-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_FLAG', '==', 'SVR_YES_OR_NO', 'N', '项目是否属于重点监控行业和领域要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF52-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MONIT_TYPE', null, null, 'N', '重点监控行业和领域类别要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF53-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MONIT_TYPE', '==', '0', 'Y', '重点监控行业和领域类别要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF54-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MON_TYPE_COMM', null, null, 'N', '重点监控行业和领域类别说明要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF54-D920-E050-207F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MON_TYPE_COMM', '==', '0', 'Y', '重点监控行业和领域类别说明要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF56-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'GUA_TYPE', null, 'SVR_GUA_TYPE', 'Y', '担保方式不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF56-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE', '==', '0', 'Y', '担保方式（其他）要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF57-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'GUA_TYPE_REMARK', null, null, 'N', '担保情况说明要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5AWM-F060-007F01006A30', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'MON_TYPE_COMM', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '重点监控行业和领域类别说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5AWM-F060-007F01006A32', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'IN_GRADE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资产内部评级必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5AWM-F060-007F01006A33', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'CHOSE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '选择权必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5AWM-F060-007F01006A40', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ISSUE_PARTY_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '发行机构必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6810-5AWM-F060-007F01006A41', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'PARTY_GRADE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '发行机构主体信用评级必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('123E4A49-6811-5A9E-E152-007F01006A26', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资管计划名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('184807E5-DF1C-D920-F060-007F010177FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'ASSET_TYPE2', '==', 'SVR_ASSET_TYPE_SECOND_LEVEL', 'Y', '资产/负债二级类别要素必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('203E4A49-6810-5A9E-F060-007F01006A68', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('211E4A49-6810-5A9E-F060-007F01006A22', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'TRADE_MARKET', '==', 'SVR_TRADE_MARKET', 'Y', '交易流通场所不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A00', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RIVAL_PARTY', null, '201', 'N', '对手方不可为空或超长（最大长200字节）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A02', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A03', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '16', 'N', '债券代码要素不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A04', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '债券名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A05', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_NAME', null, null, 'N', '发行机构不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A06', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_LEVEL2', '==', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'N', '发行机构所属行业二级类型要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A07', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'TERM_MONTH', null, '5,0', 'N', '期限（月）不可为空且为n..5格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A08', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TERM_MONTH', '>=', '0', 'Y', '期限（月）必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A09', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A10', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A11', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A12', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PARTY_GRADE', null, '11', 'N', '发行机构主体信用评级不可为空且不能过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A13', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'SEC_CREDIT_GRADE_TYPE', null, '11', 'N', '债券信用评级要素不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A14', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '票面利率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A15', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '票面利率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A16', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'INTPAY_CYCLE', null, '2,0', 'Y', '付息频率（个月/次）必须为n..2格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D111-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EQUITY_QUIT', null, null, 'N', '股权退出安排要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D112-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EQUITY_QUIT', '==', '0', 'Y', '股权退出安排要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D113-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'EQUITY_QUIT', null, 'yyyy-MM-dd', 'Y', '股权退出安排必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D114-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEPT_EQUITY', null, null, 'N', '是否为债转股要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D115-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEPT_EQUITY', null, null, 'N', '是否为债转股要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D116-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEPT_EQUITY', null, null, 'N', '是否为债转股要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D117-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DEPT_EQUITY', '==', '0', 'Y', '是否为债转股要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D118-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'DEPT_EQUITY', null, 'SVR_YES_OR_NO', 'Y', '是否为债转股要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D119-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'HOLD_PURPOSE', null, null, 'N', '持有目的要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D120-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'HOLD_PURPOSE', null, 'SVR_HOLD_PURPOSE', 'Y', '持有目的要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D122-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FINANCIAL_COMISSUE', null, null, 'N', '是否由金融资产投资公司发行要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D123-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FINANCIAL_COMISSUE', '==', '0', 'Y', '是否由金融资产投资公司发行要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D124-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIAL_COMISSUE', null, 'SVR_YES_OR_NO', 'Y', '是否由金融资产投资公司发行要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D125-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FINANCIAL_COMISSUE', null, null, 'N', '是否由金融资产投资公司发行要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D150-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_TYPE', null, null, 'N', '抵质押物类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D151-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_TYPE', null, null, 'N', '抵质押物类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D153-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_TYPE', '==', '0', 'Y', '抵质押物类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D154-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_TYPE', '==', '0', 'Y', '抵质押物类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D156-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_VALUE', null, null, 'N', '抵质押物价值（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D157-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_VALUE', null, null, 'N', '抵质押物价值（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D158-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_VALUE', '==', '0', 'Y', '抵质押物价值（元）要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D159-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_VALUE', '==', '0', 'Y', '抵质押物价值（元）要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D161-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'NATURE_GUARANTEE', null, null, 'N', '担保性质要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D162-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'NATURE_GUARANTEE', null, null, 'N', '担保性质要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D163-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'NATURE_GUARANTEE', '==', '0', 'Y', '担保性质要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D164-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'NATURE_GUARANTEE', null, 'SVR_NATURE_GUARANTEE', 'Y', '担保性质要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D165-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RELATION_FINAN', null, null, 'N', '担保人与融资人关系要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D166-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RELATION_FINAN', null, null, 'N', '担保人与融资人关系要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D168-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RELATION_FINAN', '==', '0', 'Y', '担保人与融资人关系要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D169-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RELATION_FINAN', '==', '0', 'Y', '担保人与融资人关系要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D171-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEPT_EQUITY', null, null, 'N', '是否为债转股要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D173-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BASIC_ASSET_TYPE', null, '201', 'Y', '基础资产类型过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF00-D174-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'BASIC_ASSET_TYPE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '基础资产类型必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF57-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'GUA_TYPE_REMARK', null, null, 'N', '担保情况说明要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF57-D920-E050-117F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'GUA_TYPE_REMARK', null, null, 'N', '担保情况说明要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF58-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE_REMARK', '==', '0', 'Y', '担保情况说明要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF58-D920-E050-507F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE_REMARK', '==', '0', 'Y', '担保情况说明要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF59-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_GRADE', null, 'SVR_MAIN_GRADE', 'Y', '融资人主体评级不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF59-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FINANCIER_GRADE', null, null, 'N', '融资人主体评级要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF5A-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IN_GRADE', null, null, 'N', '资产内部评级要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF5A-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IN_GRADE', null, '21', 'Y', '资产内部评级过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF5B-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'OUT_GRADE', null, 'SVR_MAIN_GRADE', 'N', '资产外部评级要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF5C-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadictMulti', 'FIN_AREA', null, 'ADMINISTRATIVE_TYPE', 'N', '融资人所属地区不可为空且在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF5D-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CONDITION_CMM', null, '201', 'N', '行权条件说明要素不可为空且不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF5E-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TOTAL_FEE_RATE', null, '8,5', 'N', '融资总费率%要素不可为空且必须是(8,5)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF5F-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TOTAL_FEE_RATE', '>=', '0', 'Y', '融资总费率%必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF60-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF61-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF62-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF63-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF64-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF65-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '融资总费率计算公式需要在备注中说明', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF66-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '21', 'N', '股票代码要素不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF66-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '21', 'Y', '股票代码要素不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF68-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '21', 'N', '股票代码要素不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF6A-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', '==', '0', 'Y', '当资产/负债类别不为1302/1305时，股票代码要素为空', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF6C-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '股票/企业名称要素不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF6C-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '委外投资协议名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF6C-D920-E050-107F01007SFD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRUSTEE', null, null, 'N', '受托人不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF6C-D920-E050-207F01007SFD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRUSTEE', null, '201', 'Y', '受托人过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF6E-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE', null, null, 'N', '股票类型要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF6F-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE', null, null, 'N', '股票类型要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF6F-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE', '==', 'SVR_SHARES_TYPE', 'Y', '股票类型不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF70-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES2', null, null, 'N', '行业二级分类不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF70-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES2', '==', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF71-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_STAGE', null, null, 'N', '投资阶段要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF72-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE1', null, null, 'N', '企业类型（按规模划分）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF72-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE1', '==', 'SVR_PARTY_TYPE1', 'Y', '企业类型（按规模划分）不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF73-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE3', null, null, 'N', '企业类型（按技术领域划分）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A17', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INTPAY_CYCLE', '>=', '0', 'Y', '付息频率（个月/次）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A18', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE_REMARK', null, '201', 'Y', '担保情况说明过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A19', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'SPECIAL_CMT', null, '201', 'N', '是否含权等特殊条款情况说明不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A20', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A21', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CONTRACT_NAME', null, '201', 'N', '合约名称必填且不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A22', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A23', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A24', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A25', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '票面利率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A26', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '票面利率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A27', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'INTPAY_CYCLE', null, '2,0', 'Y', '付息频率（个月/次）必须为n..2格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A28', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INTPAY_CYCLE', '>=', '0', 'Y', '付息频率（个月/次）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A29', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'INCOME_RATIO', null, '8,5', 'Y', '固定收益部分所占比例以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A30', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INCOME_RATIO', '>=', '0', 'Y', '固定收益部分所占比例必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A31', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INCOME_RATIO', '<=', '100', 'Y', '固定收益部分所占比例必须小于等于100。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A32', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'DER_INSTR_RATIO', null, '8,5', 'Y', '衍生金融工具所占比例以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A33', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'DER_INSTR_RATIO', '>=', '0', 'Y', '衍生金融工具所占比例必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A34', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'DER_INSTR_RATIO', '<=', '100', 'Y', '衍生金融工具所占比例必须小于等于100。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A35', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DER_INSTR_INV_TYPE', null, '301', 'Y', '衍生金融工具具体投资方式过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A36', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DER_INSTR_TARGET', null, '201', 'Y', '衍生金融工具挂钩标的资产过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A37', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ACCT_MODE', null, '201', 'Y', '利息结算方式过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A38', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'SPECIAL_CMT1', null, '201', 'Y', '含权情况说明过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A43', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'BASIC_PRICE', null, '15,2', 'Y', '挂钩标的资产基准价格必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A44', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'BASIC_PRICE', '>=', '0', 'Y', '挂钩标的资产基准价格不为空时，必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A45', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'REG_DATE_PRICE', null, '15,2', 'Y', '挂钩标的资产登记日价格必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A46', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'REG_DATE_PRICE', '>=', '0', 'Y', '挂钩标的资产登记日价格必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A47', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRADE_FEE', null, '201', 'N', '交易费不可为空且不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A49', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF13-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRIN_BASEAMT', null, null, 'N', '份额面值要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF21-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PRIN_BASEAMT', '==', '0', 'Y', '份额面值要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF22-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'PRIN_BASEAMT', '>=', '17,4', 'Y', '份额面值必须为n(17,4)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF23-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'PRIN_BASEAMT', '>=', '0', 'Y', '份额面值必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF24-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FMDATE', '==', '0', 'Y', '法定到期日要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF25-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FMDATE', '==', '0', 'Y', '法定到期日要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF26-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'FMDATE', null, 'yyyy-MM-dd', 'Y', '法定到期日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF27-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'COUPON_SPECIES', null, null, 'N', '计息类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF35-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'COUPON_SPECIES', '==', '0', 'Y', '计息类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF36-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUPON_SPECIES', null, 'SVR_COUPON_SPECIES', 'Y', '计息类型要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF37-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INTEREST_RULE_FLAG', null, null, 'N', '规则付息标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF45-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INTEREST_RULE_FLAG', '==', '0', 'Y', '规则付息标识要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF46-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INTEREST_RULE_FLAG', null, 'SVR_YES_OR_NO', 'Y', '规则付息标识要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF47-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INTPAY_RULE', null, null, 'N', '利息分布方式要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF55-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INTPAY_RULE', '==', '0', 'Y', '利息分布方式要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF56-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INTPAY_RULE', null, 'SVR_INTPAY_RULE', 'Y', '利息分布方式要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF66-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FLOAT_FACTOR_FLAG', null, null, 'N', '是否有浮动因子要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF74-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FLOAT_FACTOR_FLAG', '==', '0', 'Y', '是否有浮动因子要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF75-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FLOAT_FACTOR_FLAG', null, 'SVR_YES_OR_NO', 'Y', '是否有浮动因子要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF76-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FLOAT_FACTOR', null, null, 'N', '浮动因子要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF77-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FLOAT_FACTOR', '==', '0', 'Y', '浮动因子要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF78-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'STRUCT_GRADE', '==', '0', 'Y', '结构档次要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF79-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'STRUCT_GRADE', null, 'SVR_STRUCT_GRADE', 'Y', '结构档次要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF80-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REPAYMENT_TYPE', '==', '0', 'Y', '还本方式要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF81-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'REPAYMENT_TYPE', null, 'SVR_REPAYMENT_TYPE', 'Y', '还本方式要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF82-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF83-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF84-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF85-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF86-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF87-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF88-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF89-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF90-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PERIOD_REPAYMENT_FLAG', '==', '0', 'Y', '分期还本条款标识要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF73-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE3', null, 'SVR_PARTY_TYPE3', 'Y', '企业类型（按技术领域划分）不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF74-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE2', null, null, 'N', '企业类型（按经济类型划分）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF74-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE2', null, 'SVR_PARTY_TYPE2', 'Y', '企业类型（按经济类型划分）不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF75-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_PLEDGE', null, null, 'N', '是否为质押融资要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF75-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_PLEDGE', null, 'SVR_YES_OR_NO', 'Y', '是否为质押融资不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF76-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF77-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF78-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF79-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF79-D920-E050-117F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7A-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7A-D920-E050-117F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7A-D920-E050-117F010078FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7A-D920-E050-117F010079FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7A-D920-E050-117F010080FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7A-D920-E050-117F010081FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7A-D920-E051-117F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7A-D920-E051-117F010078FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7B-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_NAME', null, null, 'N', '资管计划名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7C-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PARTY_CODE', null, null, 'N', '资管计划发起人机构编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7C-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PARTY_CODE', '==', '33', 'Y', '资管计划发起人机构编码过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7D-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CHECK_CODE', null, '257', 'Y', '资管计划登记编码要素不可超长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7D-D920-E050-007F011277FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'Y', '资管计划名称要素不可超长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7E-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MANAGER_ID', null, null, 'N', '管理人要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7E-D920-E050-007F010A77FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MANAGER_ID', null, null, 'N', '实际管理人要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7E-D920-E050-107F010A77FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MANAGER_ID', null, '201', 'Y', '管理人过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7F-D920-E036-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '金额要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7F-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRUSTEE_ID', null, null, 'N', '托管人要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7F-D920-E050-007FC10077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRUSTEE_ID', null, null, 'N', '托管人要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7F-D920-E050-107FC10077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRUSTEE_ID', null, '201', 'Y', '托管人过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF7H-D920-E036-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '委托投资金额要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF80-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '金额必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF80-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '金额必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF80-D920-E050-207F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF81-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '合计金额必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF82-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'TRU_ACT_TYPE', '>=', 'SVR_TRU_ACT_TYPE', 'Y', '资金实际投向不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF82-D920-E050-007FD10077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'TRU_ACT_TYPE', '>=', 'SVR_TRU_ACT_TYPE', 'Y', '资金实际投向不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF82-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRU_ACT_TYPE', null, null, 'N', '资金实际投向要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A50', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'NOM_CAPI', null, '15,2', 'N', '名义本金不可为空且为(15,2)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A51', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'NOM_CAPI', '>=', '0', 'Y', '名义本金必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A54', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A55', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A56', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A57', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A58', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A59', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_VALUE', null, '15,2', 'N', '资产价值不可为空且为(15,2)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A60', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_VALUE', '>=', '0', 'Y', '资产价值必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A61', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '资产收益率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A63', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A64', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A65', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_VALUE', null, '15,2', 'N', '资产价值不可为空且为(15,2)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A66', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_VALUE', '>=', '0', 'Y', '资产价值必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A67', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '资产收益率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A69', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'TERM_DATE', null, '5,0', 'Y', '期限（天）必须为n..5格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A70', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TERM_DATE', '>=', '0', 'Y', '期限（天）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A71', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A72', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A73', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'TERM_DATE', null, '5,0', 'Y', '期限（天）必须为n..5格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A74', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TERM_DATE', '>=', '0', 'Y', '期限（天）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A75', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'DEBT_SCALE', null, '15,2', 'N', '负债规模不可为空且为(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A76', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'DEBT_SCALE', '>=', '0', 'Y', '负债规模必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A77', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '利率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A78', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '利率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A80', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_ID', null, null, 'N', '基金代码要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A81', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '基金名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A82', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES2', '==', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A83', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REGIST_ORG1', null, null, 'N', '登记备案机构要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A84', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_GOVERN_FUND', null, null, 'N', '是否属于政府投资基金要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A86', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_GOVERN_FUND', null, null, 'N', '是否属于政府投资基金要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A87', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_GOVERN_FUND', null, null, 'N', '是否属于政府投资基金要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A88', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_GOVERN_FUND', null, null, 'N', '是否属于政府投资基金要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF91-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PERIOD_REPAYMENT_FLAG', null, 'SVR_PERIOD_REPAYMENT_FLAG', 'Y', '分期还本条款标识要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF92-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BASIC_ASSET_TYPE', '==', '0', 'Y', '基础资产类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF94-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXCESS_PROFIT_RATE', '==', '0', 'Y', '超额收益分配比例%要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF95-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'EXCESS_PROFIT_RATE', '>=', '8,5', 'Y', '超额收益分配比例%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF95-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'EXCESS_PROFIT_RATE', '>=', '0', 'Y', '超额收益分配比例必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF95-D921-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_TYPE', null, null, 'N', '抵质押物类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF96-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_TYPE', null, null, 'N', '抵质押物类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF98-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_TYPE', '==', '0', 'Y', '抵质押物类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-EF99-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_TYPE', '==', '0', 'Y', '抵质押物类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884817E5-DF81-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '委托投资金额必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '委托投资金额必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FE', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_ID', null, null, 'N', '代码要素不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FF', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', '==', '0', 'Y', '代码要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FG', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '存款金额要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FH', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'AMT', '==', '0', 'Y', '存款金额要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FK', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_CODE', null, null, 'N', '行内资产/负债编码不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FL', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（现金及银行存款）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FM', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（现金及银行存款）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FO', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（拆放同业及买入返售/同业拆入及卖出回购）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FP', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（拆放同业及买入返售/同业拆入及卖出回购）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FQ', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（新增可投资资产以及非标准化债权类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FR', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（新增可投资资产以及非标准化债权类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FS', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（非标准化债权类投资）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FT', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（非标准化债权类投资）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FU', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（QDII债券类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FV', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（QDII债券类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FW', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（QDII结构性票据）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FX', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（QDII结构性票据）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FY', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（另类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077FZ', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（另类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077GG', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '贴现利率（%）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JA', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（其他）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JB', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（其他）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JE', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（QDII拆出/逆回购）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JF', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（QDII拆出/逆回购）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF82-D920-E050-107FD10077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRU_ACT_TYPE', null, null, 'N', '资金实际投向要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF82-D920-E050-207FD10077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'TRU_ACT_TYPE', '==', '20,21,22,23', 'Y', '资金实际投向01至19选项为历史数据，不得修改或新增', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF82-D920-E050-307FD10077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'TRU_ACT_TYPE', '==', '20,21,22,23', 'Y', '资金实际投向01至19选项为历史数据，不得修改或新增', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF83-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_TYPE', null, null, 'N', '资金运用方式要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF83-D920-E050-A07F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_TYPE', null, null, 'N', '资金运用方式要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF83-D920-E050-A17F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INVEST_TYPE', '<=', '301', 'Y', '资金运用方式过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF84-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CAP_USE_INDU_LEVEL2', null, null, 'N', '资金运用行业要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF84-D920-E050-007FD10077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CAP_USE_INDU_LEVEL2', null, null, 'N', '资金运用行业要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF84-D920-E050-107FD10077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CAP_USE_INDU_LEVEL2', '>=', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '资金运用行业不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF85-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'PLAN_SDATE', null, 'yyyy-MM-dd', 'N', '资管计划成立日期要素不可为空且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF85-D920-EE52-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'PLAN_SDATE', null, 'yyyy-MM-dd', 'N', '投资运作起始日期要素不可为空且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF86-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'PLAN_EDATE', null, 'yyyy-MM-dd', 'N', '资管计划终止日期要素不可为空且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF86-D920-E050-007FA10077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'PLAN_EDATE', null, 'yyyy-MM-dd', 'N', '投资运作终止日期要素不可为空且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF87-D920-EE51-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'PLAN_EDATE', '>', 'PLAN_SDATE', 'Y', '资管计划终止日期必须大于资管计划成立日期', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF87-D920-EE51-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'PLAN_EDATE', '>=', 'PLAN_SDATE', 'Y', '投资运作终止日期必须大于等于投资运作起始日期', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF88-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRO_PROPERTY', null, null, 'N', '资管计划属性要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF88-D920-E050-007F010877FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRO_PROPERTY', null, null, 'N', '委外投资属性要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF88-D920-E050-107F010877FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRO_PROPERTY', null, 'CHB_OUTSOURCE_INVEST_PROPERTY', 'Y', '委外投资属性不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF88-D920-E050-207F010877FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRO_PROPERTY', null, 'SVR_PRO_PROPERTY', 'Y', '资管计划属性不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF89-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RATE_FLAG', null, null, 'N', '是否有预期收益率不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF89-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RATE_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否有预期收益率不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF89-D920-E050-207F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RATE_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否有预期收益率不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF89-D920-E050-307F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RATE_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否有预期收益率不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF89-D920-E050-407F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RATE_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否有预期收益率不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF8A-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MAX_RATE', null, null, 'N', '预期最高收益率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF8A-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MAX_RATE', null, '8,5', 'Y', '预期最高收益率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF8B-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'MAX_RATE', '>=', '0', 'Y', '预期最高收益率%必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF8C-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MAX_RATE', '==', '0', 'Y', '预期最高收益率%要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF8D-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MIN_RATE', '==', '8,5', 'Y', '预期最低收益率%以百分数形式表示时必须为n(8,5)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF8E-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'MIN_RATE', '<=', 'MAX_RATE', 'Y', '预期最低收益率必须小于等于预期最高收益率', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF8F-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MIN_RATE', '==', '0', 'Y', '预期最低收益率%要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF90-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BUY_STRUCT', '==', 'SVR_BUY_STRUCT', 'Y', '购买结构要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF91-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'MANAGE_TYPE', '==', 'SVR_MANAGE_STYLE', 'Y', '管理方式要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A89', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_GOVERN_FUND', null, null, 'N', '是否属于政府投资基金要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A8S', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '41', 'N', '委外投资协议编号要素不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A90', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_GOVERN_FUND', null, null, 'N', '是否属于政府投资基金要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A92', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'GOVERN_INVEST_FUND', null, null, 'N', '政府投资基金投向要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A93', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FUND_ENPE_NAME', null, '201', 'Y', '基金公司名称过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A94', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MNG_ORG_NAME', null, '201', 'N', '基金管理机构名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A95', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRU_ORG_NAME', null, '201', 'N', '基金托管机构名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A96', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FUND_INVEST_ASSET', null, null, 'N', '基金投资资产要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A97', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_STAGE', null, null, 'N', '投资阶段要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A98', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_STAGE', null, null, 'N', '投资阶段要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01006A99', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_STAGE', null, null, 'N', '投资阶段要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-007F01016A88', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_ID', null, null, 'N', '发行机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-107F01006A05', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ISSUE_PARTY_NAME', null, '201', 'Y', '发行机构过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-107F01006A18', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE_REMARK', null, '201', 'Y', '担保情况说明过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-107F01006A31', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INCOME_RATIO', null, null, 'N', '固定收益部分所占比例要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-107F01006A49', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-107F01006A52', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET_TYPE3', null, null, 'N', '标的类别要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-107F01006A55', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-107F01006A64', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-107F01006A72', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-107F01006A80', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '21', 'Y', '基金代码过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-107F01006A92', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'GOVERN_INVEST_FUND', '==', 'SVR_GOV_INVEST_DIRECT', 'Y', '政府投资基金投向不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-207F01006A18', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE_REMARK', null, '201', 'Y', '担保情况说明过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-207F01006A31', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DER_INSTR_RATIO', null, null, 'N', '衍生金融工具所占比例要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-207F01006A32', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DER_INSTR_INV_TYPE', null, null, 'N', '衍生金融工具具体投资方式要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-207F01006A33', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DER_INSTR_TARGET', null, null, 'N', '衍生金融工具挂钩标的资产要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-207F01006A34', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ACCT_MODE', null, null, 'N', '利息结算方式要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-207F01006A35', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SPECIAL_CMT1', null, null, 'N', '含权情况说明要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('223E4A49-6810-5A9E-F060-207F01006A37', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REG_DATE_PRICE', null, null, 'N', '挂钩标的资产登记日价格要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JG', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '年利率%不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JH', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '年利率%不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JI', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '项目收益率%不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JJ', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '贴现利率（%）不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JK', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '票面利率%不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JN', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '项目收益率（利率）%不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JO', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_YIELD', '==', '0', 'Y', '年利率必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JP', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_YIELD', null, null, 'N', '年利率%不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JQ', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BASIS', '==', '0', 'Y', '计息基础必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JR', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INTPAY_CYCLE', null, null, 'N', '付息频率（个月/次）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JS', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INTPAY_CYCLE', null, null, 'N', '付息频率（个月/次）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JU', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INTPAY_CYCLE', null, null, 'N', '付息频率（个月/次）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JV', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INTPAY_CYCLE', '==', '0', 'Y', '付息频率必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JW', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CHB_SECTYPE', null, null, 'N', '具体类别不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JY', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CHB_SECTYPE', null, 'SVR_SEC_DETAIL_TYPE', 'Y', '具体类别不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077JZ', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ISSUE_MODE', '==', '0', 'Y', '发行方式必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077KA', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'SEC_MAIN_GRADE', '==', '0', 'Y', '主体评级必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077KB', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PART_TYPE1', '==', '0', 'Y', '发行机构类型（按规模划分）必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077KC', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PART_TYPE2', '==', '0', 'Y', '发行机构类型（按技术领域划分）必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077KD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PART_TYPE3', '==', '0', 'Y', '发行机构类型（按经济类型划分）必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077KE', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INDUSTRY_LEVEL2', '==', '0', 'Y', '发行机构所属行业必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077KF', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REGIST_ORG', '==', '0', 'Y', '登记托管机构必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077KG', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REGISTORG_COMMENT', '==', '0', 'Y', '登记托管机构说明必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077KH', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RIVAL_PARTY', '==', '0', 'Y', '对手方（拆放同业及买入返售/同业拆入及卖出回购）必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884827E5-DF35-D920-F060-007F010077KJ', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RIVAL_PARTY_TYPE', null, 'SVR_RIVAL_PARTY_TYPE', 'N', '对手方类型不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('885607E5-DF91-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MANAGE_TYPE', null, null, 'N', '管理方式要素不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('885607E5-DF91-D920-F060-007F037077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BUY_STRUCT', null, null, 'N', '购买结构要素不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A84807E5-DFAE-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'MIN_REMAIN_TERM', '<=', 'REMAIN_TERM', 'Y', '最短剩余期限（天）≤加权剩余期限（天）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('de294A49-6813-5A9E-F060-107F01006B23', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TRUSTEE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '受托人必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('8e294A49-6813-5A9E-F060-107F01006B49', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'GUA_TYPE_REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '担保情况说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('8e294A49-6813-5A9E-F060-107F01006B50', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'FIRST_EXERCISE_DATE', '==', 'yyyy-MM-dd', 'Y', '首次行权日期必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF93-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MGR_FEE_RATE', '==', '8,5', 'Y', '管理费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF93-D920-E050-007F030077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'MGR_FEE_RATE', '==', '8,5', 'Y', '管理费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF93-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MGR_FEE_RATE', null, null, 'Y', '管理费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF93-D920-E050-107F030077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MGR_FEE_RATE', null, null, 'Y', '管理费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF94-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'MGR_FEE_RATE', '>=', '0', 'Y', '管理费率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF95-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TRU_FEE_RATE', null, '8,5', 'Y', '托管费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF95-D920-E050-007F710077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TRU_FEE_RATE', null, '8,5', 'Y', '托管费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF95-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRU_FEE_RATE', null, null, 'N', '托管费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF95-D920-E050-107F710077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRU_FEE_RATE', null, null, 'N', '托管费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF96-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TRU_FEE_RATE', '>=', '0', 'Y', '托管费率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF97-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TRADE_FEE_RATE', null, '8,5', 'Y', '交易相关合计费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF97-D920-E050-007F910077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TRADE_FEE_RATE', null, '8,5', 'Y', '交易相关合计费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF97-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRADE_FEE_RATE', null, null, 'N', '交易相关合计费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF97-D920-E050-107F910077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRADE_FEE_RATE', null, null, 'N', '交易相关合计费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF98-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TRADE_FEE_RATE', '>=', '0', 'Y', '交易相关合计费率%必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF99-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AGENCY_FEE_RATE', '>=', '8,5', 'Y', '中介服务机构合计费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF99-D920-E050-007FX10077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AGENCY_FEE_RATE', '>=', '8,5', 'Y', '中介服务机构合计费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF99-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AGENCY_FEE_RATE', null, null, 'N', '中介服务机构合计费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF99-D920-E050-107FX10077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AGENCY_FEE_RATE', null, null, 'N', '中介服务机构合计费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF9A-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AGENCY_FEE_RATE', '>=', '0', 'Y', '中介服务机构合计费率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF9B-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'OTHER_FEE_RATE', '>=', '8,5', 'Y', '其他合计费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF9B-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OTHER_FEE_RATE', null, null, 'N', '其他合计费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF9B-D922-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'OTHER_FEE_RATE', '>=', '8,5', 'Y', '其他合计费率%以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF9B-D922-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OTHER_FEE_RATE', null, null, 'N', '其他合计费率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF9C-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'OTHER_FEE_RATE', '>=', '0', 'Y', '其他合计费率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF9D-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF9D-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF9E-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '管理费率计算公式需要在备注中说明。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DF9F-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '托管费率计算公式需要在备注中说明。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFA0-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '交易相关合计费率计算公式需要在备注中说明', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFA1-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '中介服务机构合计费率计算公式需要在备注中说明。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFA2-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '其他合计费率计算公式需要在备注中说明。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFA3-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_RIGHT', null, null, 'N', '是否为收/受益权要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFA3-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_RIGHT', null, 'SVR_YES_OR_NO', 'Y', '是否为收/受益权不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('284807E5-DF28-D920-F060-007F010088FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'ISUI_BOND', '==', 'SVR_YES_OR_NO', 'Y', '是否城投债（债券类资产）要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('284807E5-DF28-D920-F060-007F010098FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRIVATE_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '投向地方政府融资平台要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('284807E5-DF28-D920-F060-007F010099FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INSTIT_SIZE', '==', 'SVR_INSTIT_SIZE', 'Y', '人行机构规模要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('284807E5-DF28-D920-F060-007F010100FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INSTIT_SIZE', null, null, 'N', '人行机构规模要素不能为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('284807E5-DF43-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REPAY_CMT', '==', '0', 'Y', '还本付息情况说明要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('303E4A49-6810-5A9E-F060-007F01006A68', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('403E4A49-6810-5A9E-F060-007F01006A68', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-E151-007F01006A42', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FIN_ORG_CODE', null, '^[A-Z0-9]{18}$|^[A-Z0-9]{9}$', 'Y', '融资人组织机构代码不合格(应为9或18位大写字母或数字组合，如无则填写“000000000”)。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A00', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_STAGE', null, null, 'N', '投资阶段要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A01', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE1', null, null, 'N', '投资企业类型（按规模划分）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A02', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE1', '==', 'SVR_PARTY_TYPE1', 'Y', '投资企业类型（按规模划分）不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A05', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE2', null, null, 'N', '投资企业类型（按经济类型划分）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A06', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE2', null, 'SVR_PARTY_TYPE2', 'Y', '投资企业类型（按经济类型划分）不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A12', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE3', null, null, 'N', '投资企业类型（按技术领域划分）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A13', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A18', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_ID', null, null, 'N', '基金代码要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A19', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '基金名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A20', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES2', null, null, 'N', '行业要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A21', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'REGIST_ORG1', '==', 'SVR_REGIST_ORG2', 'Y', '登记备案机构不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A22', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_GOVERN_FUND', '==', 'SVR_YES_OR_NO', 'Y', '是否属于政府投资基金不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A24', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MNG_ORG_NAME', null, '201', 'N', '基金管理机构名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A25', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRU_ORG_NAME', null, '201', 'N', '基金托管机构名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A26', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FUND_INVEST_ASSET', null, '401', 'Y', '基金投资资产过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A27', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A28', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IN_ASSET_TYPE', '==', 'SVR_BANK_IN_ASSET_TYPE', 'Y', '行内资产类别不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A29', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IN_ASSET_TYPE_CMM', null, null, 'N', '行内资产类别说明要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A30', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '金额必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A31', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '金额必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A32', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A33', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A34', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A35', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A36', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RATE_FLAG', null, null, 'N', '是否有预期收益率不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A48', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '存款金额必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A49', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '存款金额必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A50', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A51', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A52', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A53', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '年利率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A54', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '年利率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A55', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BASIS', null, null, 'N', '计息基础要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A56', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BASIS', '==', 'SVR_BASIS', 'Y', '计息基础要素必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A57', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'DEPO_TYPE', '==', 'SVR_DEPO_TYPE', 'Y', '存款类型要素必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A59', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET_TYPE', null, null, 'N', '结构性存款挂钩标的类别要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A60', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TARGET_TYPE', '==', '0', 'Y', '结构性存款挂钩标的类别要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A61', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET', null, null, 'N', '结构性存款挂钩标的要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A62', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TARGET', '==', '0', 'Y', '结构性存款挂钩标的要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A63', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_NAME', null, null, 'N', '资金存入银行要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A64', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BANK_NAME', '==', '0', 'Y', '资金存入银行要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A65', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_NAME', null, null, 'N', '资金存入银行要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A67', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A68', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A69', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A70', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '年利率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A71', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '年利率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A72', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BASIS', '==', 'SVR_BASIS', 'N', '计息基础要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A73', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RIVAL_PARTY', null, '201', 'N', '对手方不可为空或超长（最大长200字节）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A74', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET_TYPE1', null, null, 'N', '回购标的类别要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A75', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET_TYPE1', null, null, 'N', '回购标的类别要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A76', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TARGET_TYPE1', '==', '0', 'Y', '回购标的类别要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A78', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'TARGET_TYPE1', '==', 'SVR_TARGET_TYPE2', 'Y', '回购标的类别不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A79', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET_AMT', null, null, 'N', '回购标的金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A80', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET_AMT', null, null, 'N', '回购标的金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A81', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TARGET_AMT', '==', '0', 'Y', '回购标的金额要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A83', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TARGET_AMT', null, '15,2', 'Y', '回购标的金额必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A84', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TARGET_AMT', '>=', '0', 'Y', '回购标的金额必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFA4-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_RESALE', null, 'SVR_YES_OR_NO', 'N', '是否属于买入返售要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFA5-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'NOTSTAND_SEC_TYPE', null, null, 'N', '类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFA5-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'NOTSTAND_SEC_TYPE', '==', 'SVR_ASSET_TYPE_BILL_LETTERCREDIT', 'Y', '类型不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFA6-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValue', 'NOTSTAND_SEC_TYPE', '==', '05', 'Y', '类型只能填写信用证。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFA7-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValue', 'NOTSTAND_SEC_TYPE', '!=', '05', 'Y', '类型不能填写信用证。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFA8-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'NOTSTAND_SEC_NUM', '==', '6,0', 'N', '数量要素不可为空且为n..6格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFA9-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'NOTSTAND_SEC_NUM', '>=', '0', 'Y', '数量必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFAA-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '合计金额要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFAA-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '合计金额必须为n（15,2）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFAB-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '金额必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFAC-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'REMAIN_TERM', null, '7,2', 'N', '加权剩余期限（天）要素不可为空且为(7,2)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFAD-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'REMAIN_TERM', '>=', '0', 'Y', '加权剩余期限（天）必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFAE-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'comparePropertyAsNumber', 'REMAIN_TERM', '<=', 'MAX_REMAIN_TERM', 'Y', '加权剩余期限（天）≤最长剩余期限（天）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFAF-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'MIN_REMAIN_TERM', null, '5,0', 'N', '最短剩余期限（天）要素不可为空且为n..5格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFB0-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'MAX_REMAIN_TERM', null, '5,0', 'N', '最长剩余期限（天）要素不可为空且为n..5格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFB1-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'MIN_REMAIN_TERM', '>=', '0', 'Y', '最短剩余期限（天）必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFB2-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'MAX_REMAIN_TERM', '>=', '0', 'Y', '最长剩余期限（天）必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFB3-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFB4-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', null, 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFB5-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFB6-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '贴现利率（%）要素不可为空且为(8,5)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFB7-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'DISCOUNT_YIELD', '>=', '0', 'Y', '贴现利率（%）要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFB8-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES2', null, null, 'N', '行业二级分类不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-DFB8-D920-E050-007F010077FE', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES2', '==', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D001-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PLEDGE_TYPE', null, 'SVR_PLEDGE_TYPE', 'Y', '抵质押物类型要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D002-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_VALUE', null, null, 'N', '抵质押物价值（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D003-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_VALUE', null, null, 'N', '抵质押物价值（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D004-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_VALUE', '==', '0', 'Y', '抵质押物价值（元）要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D005-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_VALUE', '==', '0', 'Y', '抵质押物价值（元）要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D006-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'PLEDGE_VALUE', '>=', '15,2', 'Y', '抵质押物价值（元）要素必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D007-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'NATURE_GUARANTEE', null, null, 'N', '担保性质要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D008-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'NATURE_GUARANTEE', null, null, 'N', '担保性质要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D009-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'NATURE_GUARANTEE', '==', '0', 'Y', '担保性质要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D010-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'NATURE_GUARANTEE', '==', '0', 'Y', '担保性质要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A37', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '项目收益率（利率）%以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A38', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_YIELD', '==', '0', 'Y', '项目收益率（利率）%要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A39', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'INTPAY_CYCLE', null, '2,0', 'Y', '付息频率（个月/次）必须为n..2格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A40', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INTPAY_CYCLE', '>=', '0', 'Y', '付息频率（个月/次）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A41', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FINANCIE', null, '201', 'N', '融资人要素不可为空，且长度不超过200', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A42', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIN_ORG_CODE', null, null, 'N', '融资人组织机构代码不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A43', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FCR_GRADE_OUT', null, '201', 'Y', '外部评级机构名称及对融资人评级结果过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A44', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_TYPE1', '==', 'SVR_PARTY_TYPE1', 'N', '融资人类型（按规模划分）要素不可为空,且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A45', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_TYPE2', '==', 'SVR_PARTY_TYPE3', 'N', '融资人类型（按技术领域划分）要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A46', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_TYPE3', '==', 'SVR_PARTY_TYPE2', 'N', '融资人类型（按经济类型划分）要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A47', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FINANCE_PROJ', null, '201', 'N', '融资项目要素不可为空且不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A48', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_LEVEL2_TYPE', null, null, 'N', '融资人所属行业（其他）要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A49', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRO_INDUSTRY_LEVEL2', null, null, 'N', '融资项目所属行业要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A50', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_FLAG', '==', 'SVR_YES_OR_NO', 'N', '项目是否属于重点监控行业和领域要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A51', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MONIT_TYPE', null, null, 'N', '重点监控行业和领域类别要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A52', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MONIT_TYPE', '==', '0', 'Y', '重点监控行业和领域类别要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A53', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MON_TYPE_COMM', null, null, 'N', '重点监控行业和领域类别说明要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006A55', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'OUT_GRADE', null, 'SVR_MAIN_GRADE', 'N', '资产外部评级要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-007F01006B41', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IN_ASSET_TYPE_CMM', null, '257', 'Y', '行内资产类别说明过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-107F01006A12', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE3', null, 'SVR_PARTY_TYPE3', 'Y', '投资企业类型（按技术领域划分）不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-107F01006A18', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '21', 'Y', '基金代码过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-107F01006A27', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-107F01006A28', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IN_ASSET_TYPE', null, null, 'N', '行内资产类别不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-107F01006A42', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIN_ORG_CODE', null, null, 'N', '融资人组织机构代码不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-107F01006A43', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FCR_GRADE_OUT', null, '201', 'Y', '外部评级机构名称及对融资人评级结果过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-107F01006A48', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_LEVEL2_TYPE', null, 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '融资人所属行业（其他）不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-107F01006A49', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRO_INDUSTRY_LEVEL2', null, 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '融资项目所属行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-107F01006A51', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'MONIT_TYPE', null, 'SVR_MOINT_TYPE', 'Y', '重点监控行业和领域类别不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-107F01006A53', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MON_TYPE_COMM', null, '257', 'Y', '重点监控行业和领域类别说明过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-117F01006A13', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A49-6810-5A9E-F060-207F01006A53', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MON_TYPE_COMM', '==', '0', 'Y', '重点监控行业和领域类别说明要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A85', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REMARK', null, '257', 'Y', '备注不能超过256字符', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A86', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A87', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_ID', null, null, 'N', '股票/基金代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A88', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '股票/基金名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A90', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_NAME', null, null, 'N', '发行机构不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A91', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES2', null, null, 'N', '行业二级分类不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A92', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A94', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A95', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A96', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A97', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '年利率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A98', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '年利率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01006A99', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BASIS', '==', 'SVR_BASIS', 'N', '计息基础要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01007A64', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BANK_NAME', '==', '0', 'Y', '资金存入银行要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01016A56', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BONDTOSTOCK_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否投向“市场化债转股相关产品的部分”不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01026A56', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PPPFLAG', '==', 'SVR_YES_OR_NO', 'Y', '公共PPP不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-007F01216A31', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'STATUS', '==', 'SVR_STATUS', 'N', '数据状态不可为空或不在值域范围内。', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-107F01006A87', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '21', 'Y', '股票/基金代码过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-107F01006A90', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ISSUE_PARTY_NAME', null, '201', 'Y', '发行机构过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E050-107F01006A91', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES2', '==', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W03E4A49-6810-5A9E-E0WM-007F01006A64', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BANK_NAME', '==', '0', 'Y', '资金存入银行要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W11E4A49-6810-5A9E-E050-007F01006A22', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRADE_MARKET', null, null, 'N', '交易流通场所要素必填', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W13E4A49-6810-5A9E-E050-007F01006A55', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEPO_TYPE', null, null, 'N', '存款类型要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W13E4A49-6810-5A9E-E050-007F01006A56', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DEPO_TYPE', '==', '0', 'Y', '存款类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W13E4A49-6810-5A9E-E050-007F01006A59', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'TARGET_TYPE', '<', 'SVR_TARGET_TYPE', 'Y', '结构性存款挂钩标的类别不在值域范围内并且结构性存款挂钩标的类别的多选选项不可重复。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W13E4A49-6810-5A9E-E050-007F01006A62', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TARGET', '==', '257', 'Y', '结构性存款挂钩标的要素过长（最大长256字节）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W13E4A49-6810-5A9E-E050-007F01006A63', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BANK_NAME', '==', '201', 'Y', '资金存入银行要素过长（最大长200字节）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W13E4A49-6810-5A9E-E050-007F01006A68', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W21E4A49-6810-5A9E-E050-007F01006A22', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'TRADE_MARKET', '==', 'SVR_TRADE_MARKET', 'Y', '交易流通场所不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A00', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RIVAL_PARTY', null, '201', 'N', '对手方不可为空或超长（最大长200字节）', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A02', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A03', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '16', 'N', '债券代码要素不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A04', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '债券名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A05', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_NAME', null, null, 'N', '发行机构不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D011-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'NATURE_GUARANTEE', null, 'SVR_NATURE_GUARANTEE', 'Y', '担保性质要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D012-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RELATION_FINAN', null, null, 'N', '担保人与融资人关系要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D013-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RELATION_FINAN', null, null, 'N', '担保人与融资人关系要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D015-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RELATION_FINAN', '==', '0', 'Y', '担保人与融资人关系要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D016-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RELATION_FINAN', '==', '0', 'Y', '担保人与融资人关系要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D017-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RELATION_FINAN', null, 'SVR_RELATION_FINAN', 'Y', '担保人与融资人关系要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D018-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CONTAIN_TYPE', '==', '0', 'Y', '含权类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D019-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CONTAIN_TYPE', null, 'SVR_CONTAIN_TYPE', 'Y', '含权类型要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D020-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EXERCISE_TYPE', null, null, 'N', '行权方式要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D021-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXERCISE_TYPE', '==', '0', 'Y', '行权方式要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D022-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'EXERCISE_TYPE', null, 'SVR_EXERCISE_TYPE', 'Y', '行权方式要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D023-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EXERCISE_DATE', null, null, 'N', '固定行权日要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D024-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXERCISE_DATE', '==', '0', 'Y', '固定行权日要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D025-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXERCISE_DATE', '==', '0', 'Y', '固定行权日要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D027-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIRST_EXERCISE_DATE', null, null, 'N', '首次行权日期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D028-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FIRST_EXERCISE_DATE', '==', '0', 'Y', '首次行权日期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D029-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FIRST_EXERCISE_DATE', '==', '0', 'Y', '首次行权日期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D031-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EXERCISE_PERIOD', null, null, 'N', '行权周期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D032-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXERCISE_PERIOD', '==', '0', 'Y', '行权周期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D033-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXERCISE_PERIOD', '==', '0', 'Y', '行权周期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D034-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'EXERCISE_PERIOD', '>=', '5,0', 'Y', '行权周期要素必须为n(5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D035-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'EXERCISE_PERIOD', '>=', '0', 'Y', '行权周期要素必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D036-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EXERCISE_PRICE', null, null, 'N', '行权价格要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D037-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EXERCISE_PRICE', null, null, 'N', '行权价格要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D038-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'EXERCISE_PRICE', '>=', '17,4', 'Y', '行权价格要素必须为n(17,4)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D039-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERPETUAL_CLAUSE_TYPE', null, null, 'N', '永续条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D047-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PERPETUAL_CLAUSE_TYPE', '==', '0', 'Y', '永续条款类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D048-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PERPETUAL_CLAUSE_TYPE', null, 'SVR_PERPETUAL_CLAUSE_TYPE', 'Y', '永续条款类型要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D049-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D050-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D051-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D052-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D053-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D054-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('443E4A59-6810-5A9E-F060-007F01006A36', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RATE_FLAG', null, null, 'N', '是否有预期收益率不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('503E4A49-6810-5A9E-F060-007F01006A68', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884804E5-DF7F-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CHOSE', null, null, 'N', '选择权必填，如无则填写“无”。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884804E5-DF7F-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CHOSE', null, '61', 'Y', '选择权过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF1A-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_CODE', null, '41', 'Y', '行内资产/负债编码过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF1B-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'ASSET_TYPE', null, 'SVR_ASSETDEBT_TYPE', 'Y', '资产/负债类别不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF1C-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CCY', null, null, 'N', '币种要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF1C-D920-F060-007F010177FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_TYPE2', null, null, 'N', '资产/负债二级类别要素不能为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF1C-D921-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CCY', '==', 'CURRENCY_CHB', 'Y', '币种不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF1E-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF1E-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF1E-D920-F060-117F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF1E-D920-F060-207F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF1F-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '16', 'Y', '代码过长（最大15字节）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF20-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF21-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'CHB_SECTYPE', '==', '01,02,03,04,05,06,07,08,09,10,99', 'N', '具体类别要素不可为空且必须选择01-10或 99中的一项', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF22-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'CHB_SECTYPE', '==', '11,12,99', 'N', '具体类别要素不可为空且必须选择11、12、99中的一项', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF23-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'CHB_SECTYPE', '==', '13,14,15,99', 'N', '具体类别要素不可为空且必须选择13-15、99中的一项', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF24-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'CHB_SECTYPE', '==', '16,17,18,19,20,21,99', 'N', '具体类别要素不可为空且必须选择16-21、99中的一项', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF25-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'CHB_SECTYPE', '==', '22,23,24,99', 'N', '具体类别要素不可为空且必须选择22-24、99中的一项', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF26-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'CHB_SECTYPE', '==', '25,26,27,28,99', 'N', '具体类别要素不可为空且必须选择25-28、99中的一项', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF27-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'ISSUE_MODE', null, 'SVR_ISSUE_MODE_FUND', 'N', '发行方式不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SEC_MAIN_GRADE', null, 'SVR_MAIN_GRADE', 'N', '主体评级不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D981-E051-007F01007914', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF29-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PARTY_TYPE1', null, 'SVR_PARTY_TYPE1', 'N', '发行机构类型（按规模划分）要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF2A-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PARTY_TYPE2', null, 'SVR_PARTY_TYPE2', 'N', '发行机构类型（按经济类型划分）要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF2B-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PARTY_TYPE3', null, 'SVR_PARTY_TYPE3', 'N', '发行机构类型（按技术领域划分）要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF2C-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_LEVEL2', null, 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'N', '发行机构所属行业二级类型要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF2D-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'REGIST_ORG', null, 'SVR_REGIST_ORG3', 'N', '登记托管机构要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF2E-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REGISTORG_COMMENT', null, '257', 'N', '登记托管机构说明要素不可为空且不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF2F-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REGISTORG_COMMENT', '==', '0', 'Y', '登记托管机构说明要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF34-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A06', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_LEVEL2', '==', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'N', '发行机构所属行业二级类型要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A07', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'TERM_MONTH', null, '5,0', 'N', '期限（月）不可为空且为n..5格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A08', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TERM_MONTH', '>=', '0', 'Y', '期限（月）必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A09', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A10', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A11', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A12', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PARTY_GRADE', null, '11', 'N', '发行机构主体信用评级不可为空且不能过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A13', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'SEC_CREDIT_GRADE_TYPE', null, '11', 'N', '债券信用评级要素不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A14', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_CODE', null, '^[\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF]{0,}$', 'Y', '行内资产/负债编码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A15', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '备注必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A16', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'INTPAY_CYCLE', null, '2,0', 'Y', '付息频率（个月/次）必须为n..2格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A17', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INTPAY_CYCLE', '>=', '0', 'Y', '付息频率（个月/次）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A18', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE_REMARK', null, '201', 'Y', '担保情况说明过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A19', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'BANK_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资金存入银行必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A20', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TARGET', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '结构性存款挂钩标的必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A21', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_ID', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '债券代码必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A22', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A23', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'REGISTORG_COMMENT', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '登记托管机构说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A24', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'RIVAL_PARTY', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '对手方必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A25', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'REPAY_CMT', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '还本付息情况说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A26', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FINANCIE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '融资人必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D055-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D056-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_TYPE', null, null, 'N', '利息递延条款类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D057-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DEFERRED_INTEREST_TYPE', '==', '0', 'Y', '利息递延条款类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D058-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'DEFERRED_INTEREST_TYPE', null, 'SVR_DEFERRED_INTEREST_TYPE', 'Y', '利息递延条款类型要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D059-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_INT', null, null, 'N', '递延利息是否计息要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D060-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEFERRED_INTEREST_INT', null, null, 'N', '递延利息是否计息要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D061-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DEFERRED_INTEREST_INT', '==', '0', 'Y', '递延利息是否计息要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D062-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DEFERRED_INTEREST_INT', '==', '0', 'Y', '递延利息是否计息要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D063-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'DEFERRED_INTEREST_INT', null, 'SVR_YES_OR_NO', 'Y', '递延利息是否计息要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D064-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIRST_REPRCE_DATE', null, null, 'N', '首次重定价日期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D065-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FIRST_REPRCE_DATE', null, null, 'N', '首次重定价日期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D066-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FIRST_REPRCE_DATE', '==', '0', 'Y', '首次重定价日期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D067-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FIRST_REPRCE_DATE', '==', '0', 'Y', '首次重定价日期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D068-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'FIRST_REPRCE_DATE', null, 'yyyy-MM-dd', 'Y', '首次重定价日期要素必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D069-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REPRCE_CYCLE', null, null, 'N', '重定价周期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D070-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REPRCE_CYCLE', null, null, 'N', '重定价周期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D071-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REPRCE_CYCLE', '==', '0', 'Y', '重定价周期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D072-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REPRCE_CYCLE', '==', '0', 'Y', '重定价周期要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D073-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'REPRCE_CYCLE', '>=', '5,0', 'Y', '重定价周期要素必须为n(5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D074-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'REPRCE_CYCLE', '>', '0', 'Y', '重定价周期要素必须大于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D075-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D076-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D077-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D078-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D079-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D080-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D081-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D082-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_FLAG', null, null, 'N', '部分赎回标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D083-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PART_REDEM_FLAG', '==', '0', 'Y', '部分赎回标识要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D084-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PART_REDEM_FLAG', null, 'SVR_YES_OR_NO', 'Y', '部分赎回标识要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D085-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PART_REDEM_RATE', null, null, 'N', '部分赎回比例%要素不可为空。', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D086-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PART_REDEM_RATE', '==', '0', 'Y', '部分赎回比例%要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D087-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PART_REDEM_RATE', '==', '0', 'Y', '部分赎回比例%要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D088-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'PART_REDEM_RATE', '>=', '8,5', 'Y', '部分赎回比例%要素必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF34-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF35-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '金额格式必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF35-D981-E051-007F01007914', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF35-D981-E051-117F01007914', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF36-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF37-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PROFIT_TYPE', '==', 'SVR_PROFIT_TYPE', 'N', '收/受益权类型要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF38-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PROFIT_TYPE', '==', '0', 'Y', '收/受益权类型要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF39-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_RESALE', null, 'SVR_YES_OR_NO', 'N', '是否属于买入返售要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF3A-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'VDATE', '==', 'yyyy-MM-dd', 'Y', '起息日必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF3B-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', null, 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF3C-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'MDATE', '>=', 'VDATE', 'Y', '到期日必须大于等于起息日。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF3E-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RATE_FLAG', null, null, 'N', '是否有预期收益率不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF3F-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '项目收益率%以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF40-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_YIELD', '==', '0', 'Y', '项目收益率%要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF41-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'INTPAY_CYCLE', null, '2,0', 'Y', '付息频率（个月/次）必须为n..2格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF42-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INTPAY_CYCLE', '>=', '0', 'Y', '付息频率（个月/次）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF43-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REPAY_CMT', '>=', '201', 'N', '还本付息情况说明要素不可为空且不可超过200', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF44-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BASIS', null, 'SVR_BASIS', 'N', '计息基础要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF45-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BASIS_TYPE', null, 'SVR_BASIS_TYPE', 'Y', '基准利率种类不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF46-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'FLOAT_FACTOR', null, '8,5', 'Y', '浮动因子（%）以百分数形式表示时必须为n(8,5)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF47-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'SPREAD_RATE', null, '8,5', 'Y', '利差（BP）以百分数形式表示时必须为n（8,5）格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF48-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FINANCIE', null, '201', 'N', '融资人要素不可为空，且长度不超过200', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF49-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FCR_GRADE_IN', null, '11', 'N', '融资人内部信用评级不可为空，且长度不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF4B-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_TYPE1', null, 'SVR_PARTY_TYPE1', 'N', '融资人类型（按规模划分）要素不可为空,且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF4C-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_TYPE2', null, 'SVR_PARTY_TYPE3', 'N', '融资人类型（按技术领域划分）要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF4D-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_TYPE3', null, 'SVR_PARTY_TYPE2', 'N', '融资人类型（按经济类型划分）要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF4E-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FINANCE_PROJ', null, '201', 'N', '融资项目要素不可为空且不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF4F-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_LEVEL2_TYPE', null, null, 'N', '融资人所属行业（新增可投资资产以及非标准化债权类资产）要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF4F-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_LEVEL2_TYPE', null, 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '融资人所属行业（新增可投资资产以及非标准化债权类资产）不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF50-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRO_INDUSTRY_LEVEL2', null, null, 'N', '融资项目所属行业要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF50-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRO_INDUSTRY_LEVEL2', null, 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '融资项目所属行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A27', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FCR_GRADE_IN', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '融资人内部信用评级必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A28', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FCR_GRADE_OUT', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '外部评级机构名称及对融资人评级结果必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A29', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FINANCE_PROJ', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '融资项目必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A30', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INCOME_RATIO', '>=', '0', 'Y', '固定收益部分所占比例必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A31', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'INCOME_RATIO', '<=', '100', 'Y', '固定收益部分所占比例必须小于等于100。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A32', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'DER_INSTR_RATIO', null, '8,5', 'Y', '衍生金融工具所占比例以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A33', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'DER_INSTR_RATIO', '>=', '0', 'Y', '衍生金融工具所占比例必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A34', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'CONDITION_CMM', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '行权条件说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A35', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DER_INSTR_INV_TYPE', null, '301', 'Y', '衍生金融工具具体投资方式过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A36', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DER_INSTR_TARGET', null, '201', 'Y', '衍生金融工具挂钩标的资产过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A37', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ACCT_MODE', null, '201', 'Y', '利息结算方式过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A38', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'SPECIAL_CMT1', null, '201', 'Y', '含权情况说明过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A42', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'SEC_CREDIT_GRADE_TYPE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '债券信用评级必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A43', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'GUA_TYPE_REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '担保情况说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A44', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'SPECIAL_CMT', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '是否含权等特殊条款情况说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A45', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'CONTRACT_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '合约名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A46', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'DER_INSTR_INV_TYPE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '衍生金融工具具体投资方式必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D089-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'PART_REDEM_RATE', '>=', '0', 'Y', '部分赎回比例%要素必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D090-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D091-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D092-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D093-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D094-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D095-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D096-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D097-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_CODE', null, null, 'N', '增信机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D098-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CREDIT_ORG_CODE', '==', '0', 'Y', '增信机构代码要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D099-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'CREDIT_ORG_CODE', null, '(([0-9A-Z]{9})|([0-9A-Z]{18})|([\u65e0]))', 'Y', '增信机构代码要素格式不对。正确的格式：9或18位大写字母或数字（社会信用代码，如果没有则填写无）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D100-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D101-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D102-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D103-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D104-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D105-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D106-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D107-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CREDIT_ORG_NAME', null, null, 'N', '增信机构名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D108-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CREDIT_ORG_NAME', '==', '0', 'Y', '增信机构名称要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D109-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CREDIT_ORG_NAME', null, '201', 'Y', '增信机构名称要素过长，最大长c..200。如果没有则填写无', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D110-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'CREDIT_ORG_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '增信机构名称要素必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D111-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'EQUITY_QUIT', null, null, 'N', '股权退出安排要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D112-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EQUITY_QUIT', '==', '0', 'Y', '股权退出安排要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D113-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'EQUITY_QUIT', null, 'yyyy-MM-dd', 'Y', '股权退出安排必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D114-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEPT_EQUITY', null, null, 'N', '是否为债转股要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D115-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEPT_EQUITY', null, null, 'N', '是否为债转股要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D116-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEPT_EQUITY', null, null, 'N', '是否为债转股要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D117-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'DEPT_EQUITY', '==', '0', 'Y', '是否为债转股要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D118-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'DEPT_EQUITY', null, 'SVR_YES_OR_NO', 'Y', '是否为债转股要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D119-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'HOLD_PURPOSE', null, null, 'N', '持有目的要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D120-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'HOLD_PURPOSE', null, 'SVR_HOLD_PURPOSE', 'Y', '持有目的要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D122-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FINANCIAL_COMISSUE', null, null, 'N', '是否由金融资产投资公司发行要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF51-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_FLAG', '==', 'SVR_YES_OR_NO', 'N', '项目是否属于重点监控行业和领域要素不可为空，且必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF52-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MONIT_TYPE', null, null, 'N', '重点监控行业和领域类别要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF53-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MONIT_TYPE', '==', '0', 'Y', '重点监控行业和领域类别要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF54-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MON_TYPE_COMM', null, null, 'N', '重点监控行业和领域类别说明要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF54-D920-F060-207F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MON_TYPE_COMM', '==', '0', 'Y', '重点监控行业和领域类别说明要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF56-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'GUA_TYPE', null, 'SVR_GUA_TYPE', 'Y', '担保方式不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF56-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE', '==', '0', 'Y', '担保方式（其他）要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF57-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'GUA_TYPE_REMARK', null, null, 'N', '担保情况说明要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF57-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'GUA_TYPE_REMARK', null, null, 'N', '担保情况说明要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF57-D920-F060-117F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'GUA_TYPE_REMARK', null, null, 'N', '担保情况说明要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF58-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE_REMARK', '==', '0', 'Y', '担保情况说明要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF58-D920-F060-507F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE_REMARK', '==', '0', 'Y', '担保情况说明要素必须为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF59-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIER_GRADE', null, 'SVR_MAIN_GRADE', 'Y', '融资人主体评级不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF59-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FINANCIER_GRADE', null, null, 'N', '融资人主体评级要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF5A-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IN_GRADE', null, null, 'N', '资产内部评级要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF5A-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IN_GRADE', null, '21', 'Y', '资产内部评级过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF5B-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'OUT_GRADE', null, 'SVR_MAIN_GRADE', 'N', '资产外部评级要素不可为空，且必须在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF5C-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FIN_AREA', null, 'ADMINISTRATIVE_TYPE', 'N', '融资人所属地区不可为空且在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF5D-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CONDITION_CMM', null, '201', 'N', '行权条件说明要素不可为空且不可过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF5E-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TOTAL_FEE_RATE', null, '8,5', 'N', '融资总费率%要素不可为空且必须是(8,5)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF5F-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TOTAL_FEE_RATE', '>=', '0', 'Y', '融资总费率%必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF60-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF61-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF62-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF63-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF64-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF65-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, null, 'N', '融资总费率计算公式需要在备注中说明', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF66-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '21', 'N', '股票代码要素不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF66-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '21', 'Y', '股票代码要素不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF68-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '21', 'N', '股票代码要素不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF6C-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '股票/企业名称要素不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF6C-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '委外投资协议名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF6C-D920-F060-107F01007SFD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRUSTEE', null, null, 'N', '受托人不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF6C-D920-F060-207F01007SFD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRUSTEE', null, '201', 'Y', '受托人过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF6E-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE', null, null, 'N', '股票类型要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A47', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'DER_INSTR_TARGET', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '衍生金融工具挂钩标的资产必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A48', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ACCT_MODE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '利息结算方式必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A49', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'SPECIAL_CMT1', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '含权情况说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A50', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TRADE_FEE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '交易费必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A51', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'PARTY_CODE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资管计划发起人机构编码必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A52', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'CHECK_CODE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资管计划登记编码必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A54', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TRUSTEE_ID', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '托管人必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A55', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'INVEST_TYPE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '资金运用方式必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A56', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FUND_ENPE_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '基金公司名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A57', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'MNG_ORG_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '基金管理机构名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A58', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'TRU_ORG_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '基金托管机构名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A59', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'IN_ASSET_TYPE_CMM', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '行内资产类别说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A60', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_VALUE', '>=', '0', 'Y', '资产价值必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A61', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '资产收益率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A62', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ISSUE_PARTY_ID', null, '^[0-9a-zA-Z]{6}$', 'Y', '发行机构代码要素格式不对正确的格式：6位英文或数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D123-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FINANCIAL_COMISSUE', '==', '0', 'Y', '是否由金融资产投资公司发行要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D124-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FINANCIAL_COMISSUE', null, 'SVR_YES_OR_NO', 'Y', '是否由金融资产投资公司发行要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D125-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FINANCIAL_COMISSUE', null, null, 'N', '是否由金融资产投资公司发行要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D150-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_TYPE', null, null, 'N', '抵质押物类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D151-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_TYPE', null, null, 'N', '抵质押物类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D153-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_TYPE', '==', '0', 'Y', '抵质押物类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D154-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_TYPE', '==', '0', 'Y', '抵质押物类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D156-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_VALUE', null, null, 'N', '抵质押物价值（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D157-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_VALUE', null, null, 'N', '抵质押物价值（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D158-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_VALUE', '==', '0', 'Y', '抵质押物价值（元）要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D159-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_VALUE', '==', '0', 'Y', '抵质押物价值（元）要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D161-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'NATURE_GUARANTEE', null, null, 'N', '担保性质要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D162-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'NATURE_GUARANTEE', null, null, 'N', '担保性质要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D163-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'NATURE_GUARANTEE', '==', '0', 'Y', '担保性质要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D164-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'NATURE_GUARANTEE', null, 'SVR_NATURE_GUARANTEE', 'Y', '担保性质要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D165-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RELATION_FINAN', null, null, 'N', '担保人与融资人关系要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D166-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RELATION_FINAN', null, null, 'N', '担保人与融资人关系要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D168-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RELATION_FINAN', '==', '0', 'Y', '担保人与融资人关系要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D169-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'RELATION_FINAN', '==', '0', 'Y', '担保人与融资人关系要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D171-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DEPT_EQUITY', null, null, 'N', '是否为债转股要素不能为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D173-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BASIC_ASSET_TYPE', null, '201', 'Y', '基础资产类型过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF00-D174-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'BASIC_ASSET_TYPE', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '基础资产类型必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF13-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRIN_BASEAMT', null, null, 'N', '份额面值要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF21-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PRIN_BASEAMT', '==', '0', 'Y', '份额面值要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF22-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'PRIN_BASEAMT', '>=', '17,4', 'Y', '份额面值必须为n(17,4)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF23-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'PRIN_BASEAMT', '>=', '0', 'Y', '份额面值必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF24-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FMDATE', '==', '0', 'Y', '法定到期日要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF25-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FMDATE', '==', '0', 'Y', '法定到期日要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF26-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'FMDATE', null, 'yyyy-MM-dd', 'Y', '到期日必须为日期格式（YYYY-MM-DD）且必须为正确日期。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF27-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'COUPON_SPECIES', null, null, 'N', '计息类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF35-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'COUPON_SPECIES', '==', '0', 'Y', '计息类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF36-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUPON_SPECIES', null, 'SVR_COUPON_SPECIES', 'Y', '计息类型要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF37-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INTEREST_RULE_FLAG', null, null, 'N', '规则付息标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-107F01007903', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CCY', null, null, 'N', '币种要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-107F01007909', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '发生金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-107F01007912', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CUR_ASSET_TYPE', null, null, 'N', '资金流动类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF28-D920-F060-207F01007903', 'CHB_INVEST_TRADE_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CCY', '==', 'CURRENCY_CHB', 'Y', '币种不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000019', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_ID', null, null, 'N', '发行机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000020', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ISSUE_PARTY_ID', null, '^[0-9a-zA-Z]{6}$', 'Y', '发行机构代码要素格式不对。正确的格式：6位英文或数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000021', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PROD_REGIST_CODE', null, null, 'N', '产品登记编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000022', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'PROD_REGIST_CODE', null, '^([CZ][0-9]{7}[A]?[0-9]{6})$', 'Y', '产品登记编码要素格式不对。正确的格式：6 位金融机构代码；1 位标识码(可为空);2 位年度码；6 位顺序码。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000023', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SINGLE_TOTAL', null, null, 'N', '个人投资者总数要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000024', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'SINGLE_TOTAL', null, '9,0', 'Y', '个人投资者总数必须为n..9格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000025', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'LEGAL_TOTAL', null, '9,0', 'Y', '法人投资者总数必须为n..9格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000026', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'LEGAL_TOTAL', '>=', '0', 'Y', '法人投资者总数必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000027', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'UNLEGAL_TOTAL', null, '9,0', 'Y', '非法人投资者总数必须为n..9格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000028', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'UNLEGAL_TOTAL', '>=', '0', 'Y', '非法人投资者总数必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000029', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TOTAL_AMT', null, null, 'N', '实际募集金额（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000030', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'TOTAL_AMT', null, '15,2', 'Y', '实际募集金额（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000031', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'RAISE_QTY', null, '18,5', 'Y', '募集总份额必须为n（18,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000032', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'RAISE_QTY', '>=', '0', 'Y', '募集总份额必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000033', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_CONSIGN', null, 'SVR_YES_OR_NO', 'Y', '是否有其他机构代销不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000034', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CONSIGN_AMT', null, null, 'N', '代销总金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000035', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CONSIGN_AMT', '==', '0', 'Y', '代销总金额要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000036', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'CONSIGN_AMT', null, '15,2', 'Y', '代销总金额必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000037', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'CONSIGN_AMT', '>', '0', 'Y', '代销总金额必须大于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000038', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REMARK', null, '257', 'Y', '备注不能超过256', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-007F01000039', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '备注必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-107F01000039', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SALE_AREA_AMT', null, null, 'N', '产品销售区域及募集金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A12A00D6-9FA8-2777-F060-207F01000039', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SUBSCRIBE_CCY_AMT', null, null, 'N', '认购币种及金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A22A00D6-9FA8-2777-F060-007F01000024', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'SINGLE_TOTAL', '>=', '0', 'Y', '个人投资者总数必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A22A00D6-9FA8-2777-F060-007F01000026', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'UNLEGAL_TOTAL', null, null, 'N', '非法人投资者总数要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A22A00D6-9FA8-2777-F060-007F01000030', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TOTAL_AMT', '>=', '0', 'Y', '实际募集金额（元）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A22A00D6-9FA8-2777-F060-007F01000031', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RAISE_QTY', null, null, 'N', '募集总份额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A22A00D6-9FA8-2777-F060-007F01000032', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_CONSIGN', null, null, 'N', '是否有其他机构代销要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('A32A00D6-9FA8-2777-F060-007F01000024', 'CHB_PRD_RAISETOT_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'LEGAL_TOTAL', null, null, 'N', '法人投资者总数要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF6F-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE', null, null, 'N', '股票类型要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF6F-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE', '==', 'SVR_SHARES_TYPE', 'Y', '股票类型不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF70-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INDUSTRY_SHARES2', null, null, 'N', '行业二级分类不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF70-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES2', '==', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF71-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_STAGE', null, null, 'N', '投资阶段要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF72-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE1', null, null, 'N', '企业类型（按规模划分）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF72-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE1', '==', 'SVR_PARTY_TYPE1', 'Y', '企业类型（按规模划分）不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF73-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE3', null, null, 'N', '企业类型（按技术领域划分）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF73-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE3', null, 'SVR_PARTY_TYPE3', 'Y', '企业类型（按技术领域划分）不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF74-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'SHARES_TYPE2', null, null, 'N', '企业类型（按经济类型划分）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF74-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'SHARES_TYPE2', null, 'SVR_PARTY_TYPE2', 'Y', '企业类型（按经济类型划分）不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF75-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_PLEDGE', null, null, 'N', '是否为质押融资要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF75-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'IS_PLEDGE', null, 'SVR_YES_OR_NO', 'Y', '是否为质押融资不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF76-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF77-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF78-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF79-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF79-D920-F060-117F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7A-D920-E051-117F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7A-D920-E051-117F010078FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7A-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7A-D920-F060-117F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7A-D920-F060-117F010078FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7A-D920-F060-117F010079FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7A-D920-F060-117F010080FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7A-D920-F060-117F010081FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REMARK', null, '0', 'N', '备注要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7B-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_NAME', null, null, 'N', '资管计划名称要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7C-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PARTY_CODE', null, null, 'N', '资管计划发起人机构编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7C-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PARTY_CODE', '==', '33', 'Y', '资管计划发起人机构编码过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7D-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'CHECK_CODE', null, '257', 'Y', '资管计划登记编码要素不可超长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7D-D920-F060-007F011277FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'Y', '资管计划名称要素不可超长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7E-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MANAGER_ID', null, null, 'N', '管理人要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7E-D920-F060-007F010A77FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MANAGER_ID', null, null, 'N', '实际管理人要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7E-D920-F060-107F010A77FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MANAGER_ID', null, '201', 'Y', '管理人过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7F-D920-E036-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '金额要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7F-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRUSTEE_ID', null, null, 'N', '托管人要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7F-D920-F060-007FC10077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRUSTEE_ID', null, null, 'N', '托管人要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A63', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A64', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A65', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_VALUE', null, '15,2', 'N', '资产价值不可为空且为(15,2)格式', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A66', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_VALUE', '>=', '0', 'Y', '资产价值必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A67', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '资产收益率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A68', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'MDATE', '==', 'yyyy-MM-dd', 'Y', '到期日要素必填且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A69', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'FUND_INVEST_ASSET', null, '(((\d{1,3}(.\d{1,2})?%)|(\d{1,3}(.\d{1,2})?%-\d{1,3}(.\d{1,2})?%)):[\u4e00-\u9fef-\u3400-\u4db5-\u20000-\u2a6d6-\u2a700-\u2b734-\u2b740-\u2b81d-\u2b820-\u2cea1-\u2ceb0-\u2ebe0-\uf900-\ufad9\ue815-\ue86f\u00b7\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF&[^\u0025\u003A-\u003B\u002D]]+;)*(((\d{1,3}(.\d{1,2})?%)|(\d{1,3}(.\d{1,2})?%-\d{1,3}(.\d{1,2})?%)):[\u4e00-\u9fef-\u3400-\u4db5-\u20000-\u2a6d6-\u2a700-\u2b734-\u2b740-\u2b81d-\u2b820-\u2cea1-\u2ceb0-\u2ebe0-\uf900-\ufad9\ue815-\ue86f\u00b7\u0020-\u0026-\u0028-\u007B-\u007D-\u007E-\u2000-\u2040-\u2160-\u216B-\u3000-\u301F-\uFF00-\uFF65-\uFFE0-\uFFEF&[^\u0025\u003A-\u003B\u002D]]+)', 'Y', '基金投资资产要素格式不对。正确的格式：数字%：文字；数字%-数字%：文字（其中，百分号、冒号、分号、连字号均应为英文标点，文字和数字部分均不得含有英文百分号、冒号、分号、连字号）。例如，“10%:股票(二级市场);10%-20%:公司债券;30%:信贷资产”', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A70', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TERM_DATE', '>=', '0', 'Y', '期限（天）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A71', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'COUNTRY_AREA', '==', 'SVR_COUNTRY_AREA', 'N', '所属国家或地区必填或不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A72', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A73', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber', 'TERM_DATE', null, '5,0', 'Y', '期限（天）必须为n..5格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A74', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'TERM_DATE', '>=', '0', 'Y', '期限（天）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A75', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'DEBT_SCALE', null, '15,2', 'N', '负债规模不可为空且为(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A76', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'DEBT_SCALE', '>=', '0', 'Y', '负债规模必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A77', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'ASSET_YIELD', null, '8,5', 'Y', '利率%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A78', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '利率%必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A80', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_ID', null, null, 'N', '基金代码要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A81', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_NAME', null, '201', 'N', '基金名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A82', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INDUSTRY_SHARES2', '==', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '行业不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A83', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'REGIST_ORG1', null, null, 'N', '登记备案机构要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A84', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_GOVERN_FUND', null, null, 'N', '是否属于政府投资基金要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A86', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_GOVERN_FUND', null, null, 'N', '是否属于政府投资基金要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A87', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_GOVERN_FUND', null, null, 'N', '是否属于政府投资基金要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A88', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_GOVERN_FUND', null, null, 'N', '是否属于政府投资基金要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A89', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_GOVERN_FUND', null, null, 'N', '是否属于政府投资基金要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A8S', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '41', 'N', '委外投资协议编号要素不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A90', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'IS_GOVERN_FUND', null, null, 'N', '是否属于政府投资基金要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A91', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'IS_GOVERN_FUND', '==', '0', 'Y', '是否属于政府投资基金要素必须为空', 'D');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A92', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'GOVERN_INVEST_FUND', null, null, 'N', '政府投资基金投向要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A93', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FUND_ENPE_NAME', null, '201', 'Y', '基金公司名称过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF45-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INTEREST_RULE_FLAG', '==', '0', 'Y', '规则付息标识要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF46-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INTEREST_RULE_FLAG', null, 'SVR_YES_OR_NO', 'Y', '规则付息标识要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF47-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INTPAY_RULE', null, null, 'N', '利息分布方式要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF55-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INTPAY_RULE', '==', '0', 'Y', '利息分布方式要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF56-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INTPAY_RULE', null, 'SVR_INTPAY_RULE', 'Y', '利息分布方式要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF66-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FLOAT_FACTOR_FLAG', null, null, 'N', '是否有浮动因子要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF74-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FLOAT_FACTOR_FLAG', '==', '0', 'Y', '是否有浮动因子要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF75-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'FLOAT_FACTOR_FLAG', null, 'SVR_YES_OR_NO', 'Y', '是否有浮动因子要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF76-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FLOAT_FACTOR', null, null, 'N', '浮动因子要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF77-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FLOAT_FACTOR', '==', '0', 'Y', '浮动因子要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF78-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'STRUCT_GRADE', '==', '0', 'Y', '结构档次要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF79-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'STRUCT_GRADE', null, 'SVR_STRUCT_GRADE', 'Y', '结构档次要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF80-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'REPAYMENT_TYPE', '==', '0', 'Y', '还本方式要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF81-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'REPAYMENT_TYPE', null, 'SVR_REPAYMENT_TYPE', 'Y', '还本方式要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF82-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF83-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF84-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF85-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF86-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF87-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF88-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF89-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PERIOD_REPAYMENT_FLAG', null, null, 'N', '分期还本条款标识要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF90-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PERIOD_REPAYMENT_FLAG', '==', '0', 'Y', '分期还本条款标识要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF91-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PERIOD_REPAYMENT_FLAG', null, 'SVR_PERIOD_REPAYMENT_FLAG', 'Y', '分期还本条款标识要素不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF92-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BASIC_ASSET_TYPE', '==', '0', 'Y', '基础资产类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF94-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'EXCESS_PROFIT_RATE', '==', '0', 'Y', '超额收益分配比例%要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF95-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'EXCESS_PROFIT_RATE', '>=', '8,5', 'Y', '超额收益分配比例%以百分数形式表示时必须为n(8,5)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF95-D920-E050-107F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'EXCESS_PROFIT_RATE', '>=', '0', 'Y', '超额收益分配比例必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF95-D921-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_TYPE', null, null, 'N', '抵质押物类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF96-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PLEDGE_TYPE', null, null, 'N', '抵质押物类型要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF98-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_TYPE', '==', '0', 'Y', '抵质押物类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84807E5-EF99-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'PLEDGE_TYPE', '==', '0', 'Y', '抵质押物类型要素必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84817E5-DF81-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '委托投资金额必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FD', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '委托投资金额必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077FE', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ASSET_ID', null, null, 'N', '代码要素不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('971E4A49-6810-5A9E-F060-007F01006A79', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CUST_FEE_IN', null, null, 'N', '本机构托管费（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-EA50-007F01006A77', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CUSTOM_QTY', null, null, 'N', '兑付总份额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A69', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'ISSUE_PARTY_ID', null, '^[0-9a-zA-Z]{6}$', 'Y', '发行机构代码要素格式不对。正确的格式：6位英文或数字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A70', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_ID', null, null, 'N', '发行机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A71', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PROD_REGIST_CODE', null, null, 'N', '产品登记编码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A72', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'PROD_REGIST_CODE', null, '^([CZ][0-9]{7}[A]?[0-9]{6})$', 'Y', '产品登记编码要素格式不对。正确的格式：6 位金融机构代码；1 位标识码(可为空);2 位年度码；6 位顺序码。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A73', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '理财产品实际终止日期要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A74', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PROFIT', null, null, 'N', '银行实际实现收入（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A75', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CUSTOM_PROFIT', null, null, 'N', '兑付客户收益（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A76', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CUSTOM_AMT', null, null, 'N', '兑付客户总金额（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A77', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'CUSTOM_AMT', '>=', '0', 'Y', '兑付客户总金额（元）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A78', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'CUSTOM_QTY', null, '18,5', 'Y', '兑付总份额必须为n（18,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A79', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'CUSTOM_QTY', '>=', '0', 'Y', '兑付总份额必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A80', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'CUST_FEE_IN', null, '15,2', 'Y', '本机构托管费（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A81', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'CUST_FEE_IN', '>=', '0', 'Y', '本机构托管费（元）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A82', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MAN_FEE_IN', null, null, 'N', '本机构管理费（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A83', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'MAN_FEE_IN', '>=', '0', 'Y', '本机构管理费（元）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A84', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'COU_FEE_IN', null, null, 'N', '本机构销售手续费（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A85', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'COU_FEE_IN', '>=', '0', 'Y', '本机构销售手续费（元）必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A86', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OTHER_FEE_IN', null, null, 'N', '本机构其他产品费用（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A87', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'OTHER_FEE_IN', null, '15,2', 'Y', '本机构其他产品费用（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A88', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CUST_FEE_OUT', null, null, 'N', '其他机构托管费（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A89', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'CUST_FEE_OUT', '>=', '0', 'Y', '其他机构托管费（元）必须大于等于0。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A90', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MAN_FEE_OUT', null, null, 'N', '其他机构管理费（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A91', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'MAN_FEE_OUT', '>=', '0', 'Y', '其他机构管理费（元）必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A92', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'COU_FEE_OUT', null, null, 'N', '其他机构销售手续费（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A93', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'COU_FEE_OUT', '>=', '0', 'Y', '其他机构销售手续费（元）必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A94', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ADV_FEE_OUT', null, null, 'N', '投资顾问费用（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A95', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ADV_FEE_OUT', '>=', '0', 'Y', '投资顾问费用（元）必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A96', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'OTHER_FEE_OUT', null, null, 'N', '其他机构其他产品费用（元）要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A97', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'OTHER_FEE_OUT', null, '15,2', 'Y', '其他机构其他产品费用（元）必须为n（15,2）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A98', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CUS_YEAR_YIELD', null, null, 'N', '客户实际年化收益率%要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('973E4A49-6810-5A9E-F060-007F01006A99', 'CHB_PRD_END_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'CUS_YEAR_YIELD', null, '8,5', 'Y', '客户实际年化收益率%以百分数形式表示时必须为n（8,5）格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7F-D920-F060-107FC10077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRUSTEE_ID', null, '201', 'Y', '托管人过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF7H-D920-E036-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '委托投资金额要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF80-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNumber2', 'AMT', null, '15,2', 'Y', '金额必须为n(15,2)格式。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF80-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '金额必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF80-D920-F060-207F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'AMT', null, null, 'N', '金额要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF81-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'AMT', '>=', '0', 'Y', '合计金额必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF82-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'TRU_ACT_TYPE', '>=', 'SVR_TRU_ACT_TYPE', 'Y', '资金实际投向不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF82-D920-F060-007FD10077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'TRU_ACT_TYPE', '>=', 'SVR_TRU_ACT_TYPE', 'Y', '资金实际投向不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF82-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRU_ACT_TYPE', null, null, 'N', '资金实际投向要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF82-D920-F060-107FD10077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TRU_ACT_TYPE', null, null, 'N', '资金实际投向要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF82-D920-F060-207FD10077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'TRU_ACT_TYPE', '==', '20,21,22,23', 'Y', '资金实际投向01至19选项为历史数据，不得修改或新增', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF82-D920-F060-307FD10077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inArray', 'TRU_ACT_TYPE', '==', '20,21,22,23', 'Y', '资金实际投向01至19选项为历史数据，不得修改或新增', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF83-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_TYPE', null, null, 'N', '资金运用方式要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF83-D920-F060-A07F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_TYPE', null, null, 'N', '资金运用方式要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF83-D920-F060-A17F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'INVEST_TYPE', '<=', '301', 'Y', '资金运用方式过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF84-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CAP_USE_INDU_LEVEL2', null, null, 'N', '资金运用行业要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF84-D920-F060-007FD10077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CAP_USE_INDU_LEVEL2', null, null, 'N', '资金运用行业要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF84-D920-F060-107FD10077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'CAP_USE_INDU_LEVEL2', '>=', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'Y', '资金运用行业不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF85-D920-EE52-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'PLAN_SDATE', null, 'yyyy-MM-dd', 'N', '投资运作起始日期要素不可为空且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF85-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'PLAN_SDATE', null, 'yyyy-MM-dd', 'N', '资管计划成立日期要素不可为空且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF86-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'PLAN_EDATE', null, 'yyyy-MM-dd', 'N', '资管计划终止日期要素不可为空且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF86-D920-F060-007FA10077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isValidDate2', 'PLAN_EDATE', null, 'yyyy-MM-dd', 'N', '投资运作终止日期要素不可为空且必须为日期格式（YYYY-MM-DD）。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF87-D920-EE51-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'PLAN_EDATE', '>', 'PLAN_SDATE', 'Y', '资管计划终止日期必须大于资管计划成立日期', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF87-D920-EE51-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareProperty', 'PLAN_EDATE', '>=', 'PLAN_SDATE', 'Y', '投资运作终止日期必须大于等于投资运作起始日期', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF88-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRO_PROPERTY', null, null, 'N', '资管计划属性要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF88-D920-F060-007F010877FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PRO_PROPERTY', null, null, 'N', '委外投资属性要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF88-D920-F060-107F010877FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRO_PROPERTY', null, 'CHB_OUTSOURCE_INVEST_PROPERTY', 'Y', '委外投资属性不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF88-D920-F060-207F010877FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'PRO_PROPERTY', null, 'SVR_PRO_PROPERTY', 'Y', '资管计划属性不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF89-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'RATE_FLAG', null, null, 'N', '是否有预期收益率不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF89-D920-F060-107F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RATE_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否有预期收益率不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF89-D920-F060-207F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RATE_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否有预期收益率不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF89-D920-F060-307F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RATE_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否有预期收益率不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF89-D920-F060-407F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'RATE_FLAG', '==', 'SVR_YES_OR_NO', 'Y', '是否有预期收益率不在值域范围内', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('884807E5-DF8A-D920-F060-007F010077FD', 'CHB_ASSET_INFO_SUB', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MAX_RATE', null, null, 'N', '预期最高收益率%要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A94', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'MNG_ORG_NAME', null, '201', 'N', '基金管理机构名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A95', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'TRU_ORG_NAME', null, '201', 'N', '基金托管机构名称不可为空且不能过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A96', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'FUND_INVEST_ASSET', null, null, 'N', '基金投资资产要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A97', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'INVEST_STAGE', '==', 'SVR_INVEST_STAGE', 'Y', '投资阶段要素必须在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A98', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_STAGE', null, null, 'N', '投资阶段要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01006A99', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INVEST_STAGE', null, null, 'N', '投资阶段要素必填。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007F01016A88', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ISSUE_PARTY_ID', null, null, 'N', '发行机构代码要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-007FA1006A66', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'compareValueAsNumber', 'ASSET_YIELD', '>=', '0', 'Y', '资产收益率%必须大于等于0', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-107F01006A05', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ISSUE_PARTY_NAME', null, '201', 'Y', '发行机构过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-107F01006A18', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE_REMARK', null, '201', 'Y', '担保情况说明过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-107F01006A22', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '股票/企业名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-107F01006A31', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'INCOME_RATIO', null, null, 'N', '固定收益部分所占比例要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-107F01006A43', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'GUA_TYPE_REMARK', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '担保情况说明必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-107F01006A49', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-107F01006A52', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'TARGET_TYPE3', null, null, 'N', '标的类别要素不可为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-107F01006A55', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-107F01006A64', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-107F01006A72', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-107F01006A80', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'ASSET_ID', null, '21', 'Y', '基金代码过长', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-107F01006A92', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'GOVERN_INVEST_FUND', '==', 'SVR_GOV_INVEST_DIRECT', 'Y', '政府投资基金投向不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-207F01006A18', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'GUA_TYPE_REMARK', null, '201', 'Y', '担保情况说明过长。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-207F01006A22', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_NAME', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '债券名称必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-207F01006A31', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DER_INSTR_RATIO', null, null, 'N', '衍生金融工具所占比例要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-207F01006A32', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DER_INSTR_INV_TYPE', null, null, 'N', '衍生金融工具具体投资方式要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-207F01006A33', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'DER_INSTR_TARGET', null, null, 'N', '衍生金融工具挂钩标的资产要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-207F01006A34', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ACCT_MODE', null, null, 'N', '利息结算方式要素不可为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0100129D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BONDTOSTOCK_FLAG', null, null, 'N', '是否投向“市场化债转股相关产品的部分”不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0100123D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'ASSET_ID', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '股票代码必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0100125D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'PPPFLAG', null, null, 'N', '公共PPP不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0100127D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BONDTOSTOCK_FLAG', null, null, 'N', '是否投向“市场化债转股相关产品的部分”不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-E050-123F0112345D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedString', 'RIVAL_PARTY', null, '^[\u4e00-\u9fa5\ue815-\ue864\uff01-\uff5e\uffe0-\uffe5\u3000-\u3003\u3005-\u3017\u301d-\u301e\u2010\u2013-\u2016\u2018-\u2019\u201c-\u201d\u2025-\u2026\u2030\u2032-\u2033\u2035\u203b\u00b7\u0030-\u0039\u0041-\u005a\u0061-\u007a\u2160-\u216b\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '对手方必须填写白名单中的文字。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112345D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_SEC_CAPITAL', null, null, 'N', '是否商业银行二级资本债不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112347D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BANK_SEC_CAPITAL', '==', 'SVR_YES_OR_NO', 'Y', '是否商业银行二级资本债不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112349D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_SEC_PERPETUAL', null, null, 'N', '是否商业银行永续债不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112311D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BANK_SEC_PERPETUAL', '==', 'SVR_YES_OR_NO', 'Y', '是否商业银行永续债不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112313D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_SEC_PREFER', null, null, 'N', '是否商业银行优先股不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112315D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BANK_SEC_PREFER', '==', 'SVR_YES_OR_NO', 'Y', '是否商业银行优先股不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112317D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'BANK_SEC_KZZQ', null, null, 'N', '是否商业银行可转债不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112319D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'inDatadict', 'BANK_SEC_KZZQ', '==', 'SVR_YES_OR_NO', 'Y', '是否商业银行可转债不在值域范围内。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W23E4A49-6810-5A9E-1234-123F0112321D', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'BANK_SEC_ZXZQ', '==', '0', 'Y', '是否专项债券必须为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DD41-D920-E050-007F010077FM', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'MDATE', null, null, 'N', '到期日（债券类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DD51-D920-E050-007F010077FM', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'VDATE', null, null, 'N', '起息日（债券类资产）不可以为空', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077ZZ', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'CHECK_CODE', null, null, 'N', '对应资管计划代码不可以为空。', 'E');

insert into chb_datacheck_rule (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('W84827E5-DF35-D920-E050-007F010077ZY', 'CHB_ASSET_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isNotEmpty', 'ORG_ASSET_TYPE1', null, null, 'N', '上游资产一级分类不可以为空。', 'E');

