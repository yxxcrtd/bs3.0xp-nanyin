/****ZGBSSE-691  【中债直连】产品基本信息接口txt接入时，FPROD_ID字段未做长度及白名单check**/
DELETE FROM CHB_DATACHECK_RULE WHERE CHECKRULEID = 'BASEINFO-ffTT-5A9E-E050-007F01000003';
insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('BASEINFO-ffTT-5A9E-E050-007F01000003', 'CHB_PRD_BASEINFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'FPROD_ID', null, '^[\u0030-\u0039\u0041-\u005a\u0061-\u007a\u0020-\u0026\u0028-\u007B\u007D\u007E]{0,}$', 'Y', '父产品代码只能含有白名单内的阿拉伯数字，英文字母，半角符号，全角符号。', 'E');
DELETE FROM CHB_DATACHECK_RULE WHERE CHECKRULEID = 'BASEINFO-FFTT-5A9E-E050-007F01000002';
insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('BASEINFO-FFTT-5A9E-E050-007F01000002', 'CHB_PRD_BASEINFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'lessThanCharLength', 'FPROD_ID', null, '101', 'Y', '父产品代码超长（最大长100）。', 'E');




/****ZGBSSE-561 【中债直连check】新建股票资产，未输入资产名称，点击提交时系统提示信息重复**/
DELETE FROM CHB_DATACHECK_RULE_INV WHERE CHECKRULEID = 'b22f191a-6eef-5083-9cbb-6c3e251568fd';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('b22f191a-6eef-5083-9cbb-6c3e251568fd', 'FinStockInfo002', 'lessThanCharLengthUTF8', 'finprodName', null, '201', 'Y', '资产/企业名称要素不能过长。', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');
DELETE FROM CHB_DATACHECK_RULE_INV WHERE CHECKRULEID = 'b22f191a-6eef-5083-9cbC-6c3e251568fd';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('b22f191a-6eef-5083-9cbC-6c3e251568fd', 'FinStockInfo002', 'isNotEmpty', 'finprodName', null, '0', 'N', '资产/企业名称要素不可为空。', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');

