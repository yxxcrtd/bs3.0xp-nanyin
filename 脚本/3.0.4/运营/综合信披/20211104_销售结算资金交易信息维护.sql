DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '3054';
insert into SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('3054', 'VAL', '销售结算资金交易登记报送', null, 'svrSalesTransactionInterfTaskService', 3040, 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST VALUES ('SVR_REPORT_CODE','34','销售结算资金交易信息登记','34','0','system',null,sysdate,'system',sysdate,null);
insert into SYS_DATADICT_LIST VALUES ('SVR_CHECK_INTERF_TYPE','CHB_SALER_STL_DEAL_SUB','销售结算资金交易信息登记','20','0','system',null,sysdate,'system',sysdate,null);
INSERT INTO CHB_REG_TIME_POINTS VALUES('34',null,'34',null,'03','03','02','业务发生后N个工作日','1','1','12','4','E',null,null,'qq2',sysdate,'system',sysdate,null);

INSERT INTO SYS_MENU VALUES ('REPORT_001_001_016_003', 'REP',null,'REPORT_001_001_016','SalesTransactionMaintenance001','5','02','销售结算资金交易信息维护','system',null,sysdate,'system',sysdate);
INSERT INTO SYS_PAGE VALUES ('SalesTransactionMaintenance001','REP','report/submitmaintenance/salestransactionmaintenance/SalesTransactionMaintenance001','report/submitmaintenance/salestransactionmaintenance/SalesTransactionMaintenance001','REPORT_001_001_016_003',null,null,'02','01','销售结算资金交易信息维护','system',null,sysdate,'system',sysdate);
INSERT INTO SYS_PAGE VALUES ('SalesTransactionMaintenance002','REP','report/submitmaintenance/salestransactionmaintenance/SalesTransactionMaintenance002','report/submitmaintenance/salestransactionmaintenance/SalesTransactionMaintenance002',null,null,null,'02','01','销售结算资金交易信息维护新建','system',null,sysdate,'system',sysdate);

INSERT INTO SYS_PAGE_ELEMENT VALUES('SalesTransactionMaintenance002','SalesTransactionMaintenance002_btn_submit','01','02',null,'提交','system',null,sysdate,'system',sysdate,null);
INSERT INTO SYS_PAGE_ELEMENT VALUES('SalesTransactionMaintenance002','SalesTransactionMaintenance002_btn_save','01','02',null,'保存','system',null,sysdate,'system',sysdate,null);
INSERT INTO SYS_PAGE_ELEMENT VALUES('SalesTransactionMaintenance001','SalesTransactionMaintenance001_btn_delete','01','02',null,'删除','system',null,sysdate,'system',sysdate,null);
INSERT INTO SYS_PAGE_ELEMENT VALUES('SalesTransactionMaintenance002','SalesTransactionMaintenance001_btn_delete','01','02',null,'删除','system',null,sysdate,'system',sysdate,null);
INSERT INTO SYS_WFL_BUSINESS_MODULE VALUES ('SalesTransactionMaintenance002','SalesTransactionMaintenance002_btn_submit','noneApproval','system',null,sysdate,'system',sysdate,'ALL','02');
INSERT INTO SYS_WFL_BUSINESS_MODULE VALUES ('SalesTransactionMaintenance002','SalesTransactionMaintenance002_btn_save','noneApproval','system',null,sysdate,'system',sysdate,'ALL','02');
INSERT INTO SYS_WFL_BUSINESS_MODULE VALUES ('SalesTransactionMaintenance001','SalesTransactionMaintenance001_btn_delete','noneApproval','system',null,sysdate,'system',sysdate,'ALL','02');
INSERT INTO SYS_WFL_BUSINESS_MODULE VALUES ('SalesTransactionMaintenance002','SalesTransactionMaintenance001_btn_delete','noneApproval','system',null,sysdate,'system',sysdate,'ALL','02');

DELETE FROM CHB_DATACHECK_RULE_INV WHERE PAGE_ID = 'SalesTransactionMaintenance002';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000001','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','institutionCode','','','N','提供销售结算资金服务机构代码要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000002','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','lessThanCharLength','institutionCode','','7','Y','提供销售结算资金服务机构代码不能超过6字符','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000003','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','prdRegistCode','','','N','产品登记编码要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000004','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','lessThanCharLength','prdRegistCode','','16','Y','产品登记编码不能超过15字符','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000005','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','accountNo','','','N','销售结算资金账号要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000006','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','lessThanCharLength','accountNo','','61','Y','销售结算资金账号不能超过60字符','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000007','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','accountName','','','N','销售结算资金账户名称要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000008','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','lessThanCharLength','accountName','','201','Y','销售结算资金账户名称不能超过200字符','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000009','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','tradeNo','','','N','销售结算资金交易流水号要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000010','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','lessThanCharLength','tradeNo','','61','Y','销售结算资金交易流水号不能超过60字符','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000011','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','tradePs','','','N','销售结算资金流动方向要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000012','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','inDatadict','tradePs','==','TRADE_PS','Y','销售结算资金流动方向不再值域范围内','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000013','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','tradeCounterAccNo','','','N','销售结算资金交易对手方账号要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000014','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','lessThanCharLength','tradeCounterAccNo','','61','Y','销售结算资金交易对手方账号不能超过60字符','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000015','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','tradeCounterAccName','','','N','销售结算资金交易对手方账户名称要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000016','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','lessThanCharLength','tradeCounterAccName','','201','Y','销售结算资金交易对手方账户名称不能超过200字符','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000017','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','tradeCounterAccBankName','','','N','销售结算资金交易对手方开户行名称要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000018','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','lessThanCharLength','tradeCounterAccBankName','','201','Y','销售结算资金交易对手方开户行名称不能超过200字符','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000019','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','tradeCounterAccBankNo','','','N','销售结算资金交易对手方账户开户行号要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000020','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','lessThanCharLength','tradeCounterAccBankNo','','13','Y','销售结算资金交易对手方账户开户行号不能超过12字符','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000021','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','tradeCcy','','','N','销售结算资金交易币种要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000022','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','inDatadict','tradeCcy','==','CURRENCY_CHB','Y','销售结算资金交易币种不在值域范围内','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000023','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','tradeAmt','','','N','销售结算资金交易金额要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000024','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNumber2','tradeAmt','>=','15,2','Y','销售结算资金交易金额必须为n(15,2)格式。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000025','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','compareValueAsNumber','tradeAmt','>','0','Y','销售结算资金交易金额必须大于0','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000026','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','tradeVdate','','','N','销售结算资金交易日期要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000027','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isValidDate2','tradeVdate','==','yyyy-MM-dd','Y','销售结算资金交易日期必须为日期格式（YYYY-MM-DD）。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000028','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isNotEmpty','tradeTime','','','N','销售结算资金交易时间要素不可为空。','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000029','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','isValidDate2','tradeTime','','yyyy-MM-dd HH:mm:ss','Y','销售结算资金交易时间必须是正常时间','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000030','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','lessThanCharLength','postscript','','201','Y','销售结算资金交易附言不能超过200字符','E');
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKCLASS, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS)
values ('CHB_SALER_STL_DEAL_INFO-000000000031','SalesTransactionMaintenance002','com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib','lessThanCharLength','remark','','201','Y','备注不能超过200字符','E');
