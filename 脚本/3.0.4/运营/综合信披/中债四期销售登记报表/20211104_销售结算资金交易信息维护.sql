DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '3054';
insert into SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('3054', 'VAL', '销售结算资金交易登记报送', null, 'svrSalesTransactionInterfTaskService', 3040, 'system', null, sysdate, 'system', sysdate, null);


DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_REPORT_CODE' and ITEM_CODE = '34';
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_CHECK_INTERF_TYPE' and ITEM_CODE = 'CHB_SALER_STL_DEAL_SUB';
insert into SYS_DATADICT_LIST VALUES ('SVR_REPORT_CODE','34','销售结算资金交易信息登记','34','0','system',null,sysdate,'system',sysdate,null);
insert into SYS_DATADICT_LIST VALUES ('SVR_CHECK_INTERF_TYPE','CHB_SALER_STL_DEAL_SUB','销售结算资金交易信息登记','20','0','system',null,sysdate,'system',sysdate,null);
DELETE FROM CHB_REG_TIME_POINTS WHERE CHB_REG_TIME_POINTS.SEQNO = '34';
INSERT INTO CHB_REG_TIME_POINTS VALUES('34',null,'34',null,'03','03','02','业务发生后N个工作日','1','1','12','4','E',null,null,'qq2',sysdate,'system',sysdate,null);

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'SalesTransactionMaintenance001';
DELETE FROM SYS_PAGE WHERE PAGE_ID = 'SalesTransactionMaintenance002';
delete from SYS_MENU where MENU_ID = 'REPORT_001_001_018';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_001_001_018', 'REP', null, 'REPORT_001_001', 'SalesTransactionMaintenance001', 15, '02', '销售结算资金交易信息维护', 'system', null, TIMESTAMP '2021-06-30 20:43:03', 'system', TIMESTAMP '2021-06-30 20:43:03');
INSERT INTO SYS_PAGE VALUES ('SalesTransactionMaintenance001','REP','report/submitmaintenance/salestransactionmaintenance/SalesTransactionMaintenance001','report/submitmaintenance/salestransactionmaintenance/SalesTransactionMaintenance001','REPORT_001_001_016_003',null,null,'02','01','销售结算资金交易信息维护','system',null,sysdate,'system',sysdate);
INSERT INTO SYS_PAGE VALUES ('SalesTransactionMaintenance002','REP','report/submitmaintenance/salestransactionmaintenance/SalesTransactionMaintenance002','report/submitmaintenance/salestransactionmaintenance/SalesTransactionMaintenance002',null,null,null,'02','01','销售结算资金交易信息维护新建','system',null,sysdate,'system',sysdate);

DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'SalesTransactionMaintenance002';
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'SalesTransactionMaintenance001';
INSERT INTO SYS_PAGE_ELEMENT VALUES('SalesTransactionMaintenance002','SalesTransactionMaintenance002_btn_submit','01','02',null,'提交','system',null,sysdate,'system',sysdate,'C',null);
INSERT INTO SYS_PAGE_ELEMENT VALUES('SalesTransactionMaintenance002','SalesTransactionMaintenance002_btn_save','01','02',null,'保存','system',null,sysdate,'system',sysdate,'C',null);
INSERT INTO SYS_PAGE_ELEMENT VALUES('SalesTransactionMaintenance001','SalesTransactionMaintenance001_btn_delete','01','02',null,'删除','system',null,sysdate,'system',sysdate,'C',null);
INSERT INTO SYS_PAGE_ELEMENT VALUES('SalesTransactionMaintenance002','SalesTransactionMaintenance001_btn_delete','01','02',null,'删除','system',null,sysdate,'system',sysdate,'C',null);

DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID = 'SalesTransactionMaintenance002';
DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID = 'SalesTransactionMaintenance001';
INSERT INTO SYS_WFL_BUSINESS_MODULE VALUES ('SalesTransactionMaintenance002','SalesTransactionMaintenance002_btn_submit','noneApproval','system',null,sysdate,'system',sysdate,'ALL','02');
INSERT INTO SYS_WFL_BUSINESS_MODULE VALUES ('SalesTransactionMaintenance002','SalesTransactionMaintenance002_btn_save','noneApproval','system',null,sysdate,'system',sysdate,'ALL','02');
INSERT INTO SYS_WFL_BUSINESS_MODULE VALUES ('SalesTransactionMaintenance001','SalesTransactionMaintenance001_btn_delete','noneApproval','system',null,sysdate,'system',sysdate,'ALL','02');
INSERT INTO SYS_WFL_BUSINESS_MODULE VALUES ('SalesTransactionMaintenance002','SalesTransactionMaintenance001_btn_delete','noneApproval','system',null,sysdate,'system',sysdate,'ALL','02');

DELETE FROM CHB_DATACHECK_RULE_INV WHERE PAGE_ID = 'SalesTransactionMaintenance002';
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-001','SalesTransactionMaintenance002','isNotEmpty','prdRegistCode',null,null,'N','产品登记编码要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-002','SalesTransactionMaintenance002','isNotEmpty','institutionCode',null,null,'N','提供销售结算资金服务机构代码要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-003','SalesTransactionMaintenance002','lessThanCharLength','institutionCode',null,'7','Y','提供销售结算资金服务机构代码不能超过6字符。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-004','SalesTransactionMaintenance002','lessThanCharLength','prdRegistCode',null,'16','Y','产品登记编码不能超过15字符。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-005','SalesTransactionMaintenance002','isNotEmpty','accountNo',null,null,'N','销售结算资金账号要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-006','SalesTransactionMaintenance002','lessThanCharLength','accountNo',null,'61','Y','销售结算资金账号不能超过60字符。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-007','SalesTransactionMaintenance002','isNotEmpty','accountName',null,null,'N','销售结算资金账户名称要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-008','SalesTransactionMaintenance002','lessThanCharLength','accountName',null,'201','Y','销售结算资金账户名称不能超过200字符。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-009','SalesTransactionMaintenance002','isNotEmpty','tradeNo',null,null,'N','销售结算资金交易流水号要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-010','SalesTransactionMaintenance002','lessThanCharLength','tradeNo',null,'61','Y','销售结算资金交易流水号不能超过60字符。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-011','SalesTransactionMaintenance002','isNotEmpty','tradePs',null,null,'N','销售结算资金流动方向要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-012','SalesTransactionMaintenance002','isNotEmpty','tradePs','==','TRADE_PS','Y','销售结算资金流动方向不再值域范围内。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-013','SalesTransactionMaintenance002','isNotEmpty','tradeCounterAccNo',null,null,'N','销售结算资金交易对手方账号要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-014','SalesTransactionMaintenance002','lessThanCharLength','tradeCounterAccNo',null,'61','Y','销售结算资金交易对手方账号不能超过60字符。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-015','SalesTransactionMaintenance002','isNotEmpty','tradeCounterAccName',null,null,'N','销售结算资金交易对手方账户名称要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-016','SalesTransactionMaintenance002','lessThanCharLength','tradeCounterAccName',null,'201','Y','销售结算资金交易对手方账户名称不能超过200字符。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-017','SalesTransactionMaintenance002','isNotEmpty','tradeCounterAccBankName',null,null,'N','销售结算资金交易对手方开户行名称要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-018','SalesTransactionMaintenance002','lessThanCharLength','tradeCounterAccBankName',null,'201','Y','销售结算资金交易对手方开户行名称不能超过200字符。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-019','SalesTransactionMaintenance002','isNotEmpty','tradeCounterAccBankNo',null,null,'N','销售结算资金交易对手方账户开户行号要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-020','SalesTransactionMaintenance002','lessThanCharLength','tradeCounterAccBankNo',null,'13','Y','销售结算资金交易对手方账户开户行号不能超过12字符。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-021','SalesTransactionMaintenance002','isNotEmpty','tradeCcy',null,null,'N','销售结算资金交易币种要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-022','SalesTransactionMaintenance002','isNotEmpty','tradeCcy','==','CURRENCY_CHB','Y','销售结算资金交易币种不在值域范围内。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-023','SalesTransactionMaintenance002','isNotEmpty','tradeAmt',null,null,'N','销售结算资金交易金额要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-024','SalesTransactionMaintenance002','isNumber2','tradeAmt','>=','15,2','Y','销售结算资金交易金额必须为n(15,2)格式。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-025','SalesTransactionMaintenance002','compareValueAsNumber','tradeAmt','>','0','Y','销售结算资金交易金额必须大于0。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-026','SalesTransactionMaintenance002','isNotEmpty','tradeVdate',null,null,'N','销售结算资金交易日期要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-027','SalesTransactionMaintenance002','isValidDate2','tradeVdate',null,'yyyy-MM-dd','Y','销售结算资金交易日期必须为日期格式（YYYY-MM-DD）。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-028','SalesTransactionMaintenance002','isNotEmpty','tradeTime',null,null,'N','销售结算资金交易时间要素不可为空。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-029','SalesTransactionMaintenance002','lessThanCharLength','postscript',null,'201','Y','销售结算资金交易附言不能超过200字符。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS)
values ('SALES_TRANSCATION_MAINTENANCE_RULE_PRD_REGIST_CODE-2021-1119-030','SalesTransactionMaintenance002','lessThanCharLength','remark',null,'201','Y','备注不能超过200字符。','E','com.joyintech.service.chb.common.service.ChbBaseCheckLib');
