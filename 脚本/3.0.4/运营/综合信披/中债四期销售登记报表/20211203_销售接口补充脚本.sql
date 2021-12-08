DELETE FROM CHB_DATACHECK_RULE WHERE CHECKRULEID in ('CHB_SALER_STL_DEAL_INFO-000000010004','CHB_SALER_STL_DEAL_INFO-000000010005');
insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000010004', 'CHB_SALER_STL_DEAL_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'PRD_REGIST_CODE', null, '^([CZ][0-9]{7}[A]?[0-9]{6})$', 'Y', '产品登记编码要素格式不对。正确的格式：6 位金融机构代码；2 位年度码；1 位标识码(可为空);6 位顺序码。', 'E');
insert into CHB_DATACHECK_RULE (CHECKRULEID, DATATYPE, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000010005', 'CHB_SALER_STL_DEAL_INFO', 'com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib', 'isCustomizedStringForWhitelist', 'PRD_REGIST_CODE', null, '^([CZ][0-9]{5})$', 'Y', '金融机构代码格式不对。请输入正确的金融机构代码', 'E');

DELETE FROM CHB_DATACHECK_RULE_INV WHERE CHECKRULEID IN ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-031','SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-032');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-031', 'SalesTransactionMaintenance002', 'isCustomizedStringForWhitelist', 'prdRegistCode', null, '^([CZ][0-9]{7}[A]?[0-9]{6})$', 'Y', '产品登记编码要素格式不对。正确的格式：6 位金融机构代码；2 位年度码；1 位标识码(可为空);6 位顺序码。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-032', 'SalesTransactionMaintenance002', 'isCustomizedStringForWhitelist', 'institutionCode', null, '^([CZ][0-9]{5})$', 'Y','金融机构代码格式不对。请输入正确的金融机构代码', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');


