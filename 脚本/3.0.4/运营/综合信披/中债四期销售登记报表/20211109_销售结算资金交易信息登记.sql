DROP TABLE CHB_SALER_STL_DEAL_SUB;
create table CHB_SALER_STL_DEAL_SUB(
  REGIST_SEQNO	VARCHAR2(36) NOT NULL
  ,DATA_STATUS	VARCHAR2(10)
  ,INSTITUTION_CODE	VARCHAR2(20)
  ,PRD_REGIST_CODE	VARCHAR2(20)
  ,ACCOUNT_NO	VARCHAR2(60)
  ,ACCOUNT_NAME	VARCHAR2(200)
  ,TRADE_NO	VARCHAR2(100)
  ,TRADE_PS	VARCHAR2(4)
  ,TRADE_COUNTER_ACC_NO	VARCHAR2(60)
  ,TRADE_COUNTER_ACC_NAME	VARCHAR2(200)
  ,TRADE_COUNTER_ACC_BANK_NAME	VARCHAR2(200)
  ,TRADE_COUNTER_ACC_BANK_NO	VARCHAR2(60)
  ,TRADE_CCY	VARCHAR2(20)
  ,TRADE_AMT	NUMBER(30,8)
  ,TRADE_VDATE	DATE
  ,TRADE_TIME	VARCHAR2(6)
  ,POSTSCRIPT	VARCHAR2(600)
  ,REMARK	VARCHAR2(1000)
  ,CHB_SUBMIT_DEADLINE  TIMESTAMP(6)
  ,SUBMIT_DEADLINE  TIMESTAMP(6)
  ,STATUS_DATE  DATE
  ,SUBMIT_TIME  TIMESTAMP(6)
  ,SUMIT_SEQ   VARCHAR2(50)
  ,SEND_STATUS  VARCHAR2(2)
  ,SUBMIT_FEEDBACK_STATUS  VARCHAR2(50)
  ,SUBMIT_FEEDBACK_MSG VARCHAR2(600)
  ,CREATE_USER	VARCHAR2(20)
  ,ORG_CODE	VARCHAR2(20)
  ,CREATE_DEPT	VARCHAR2(20)
  ,CREATE_TIME	TIMESTAMP(6)
  ,UPDATE_USER	VARCHAR2(20)
  ,UPDATE_TIME	TIMESTAMP(6)
);
comment on table CHB_SALER_STL_DEAL_SUB is '销售结算资金交易信息登记表';
comment on column CHB_SALER_STL_DEAL_SUB.REGIST_SEQNO is '主键uuid ';
comment on column CHB_SALER_STL_DEAL_SUB.DATA_STATUS is '数据状态 ';
comment on column CHB_SALER_STL_DEAL_SUB.INSTITUTION_CODE is '提供销售结算资金服务机构代码 ';
comment on column CHB_SALER_STL_DEAL_SUB.PRD_REGIST_CODE is '产品登记编码 ';
comment on column CHB_SALER_STL_DEAL_SUB.ACCOUNT_NO is '销售结算资金账号 ';
comment on column CHB_SALER_STL_DEAL_SUB.ACCOUNT_NAME is '销售结算资金账户名称 ';
comment on column CHB_SALER_STL_DEAL_SUB.TRADE_NO is '销售结算资金交易流水号 ';
comment on column CHB_SALER_STL_DEAL_SUB.TRADE_PS is '销售结算资金流动方向 ';
comment on column CHB_SALER_STL_DEAL_SUB.TRADE_COUNTER_ACC_NO is '销售结算资金交易对手方账号 ';
comment on column CHB_SALER_STL_DEAL_SUB.TRADE_COUNTER_ACC_NAME is '销售结算资金交易对手方账户名称 ';
comment on column CHB_SALER_STL_DEAL_SUB.TRADE_COUNTER_ACC_BANK_NAME is '销售结算资金交易对手方开户行名称 ';
comment on column CHB_SALER_STL_DEAL_SUB.TRADE_COUNTER_ACC_BANK_NO is '销售结算资金交易对手方账户开户行号 ';
comment on column CHB_SALER_STL_DEAL_SUB.TRADE_CCY is '销售结算资金交易币种 ';
comment on column CHB_SALER_STL_DEAL_SUB.TRADE_AMT is '销售结算资金交易金额 ';
comment on column CHB_SALER_STL_DEAL_SUB.TRADE_VDATE is '销售结算资金交易日期 ';
comment on column CHB_SALER_STL_DEAL_SUB.TRADE_TIME is '销售结算资金交易时间 ';
comment on column CHB_SALER_STL_DEAL_SUB.POSTSCRIPT is '销售结算资金交易附言 ';
comment on column CHB_SALER_STL_DEAL_SUB.REMARK is '备注 ';
comment on column CHB_SALER_STL_DEAL_SUB.CHB_SUBMIT_DEADLINE is '中债报送截止日 ';
comment on column CHB_SALER_STL_DEAL_SUB.SUBMIT_DEADLINE is '报送截止日 ';
comment on column CHB_SALER_STL_DEAL_SUB.STATUS_DATE is '数据日期 ';
comment on column CHB_SALER_STL_DEAL_SUB.SUBMIT_TIME is '报送时间 ';
comment on column CHB_SALER_STL_DEAL_SUB.SUMIT_SEQ is '报送流水号 ';
comment on column CHB_SALER_STL_DEAL_SUB.SEND_STATUS is '报送状态 ';
comment on column CHB_SALER_STL_DEAL_SUB.SUBMIT_FEEDBACK_STATUS is '反馈状态 ';
comment on column CHB_SALER_STL_DEAL_SUB.SUBMIT_FEEDBACK_MSG is '报送反馈消息 ';
comment on column CHB_SALER_STL_DEAL_SUB.CREATE_USER is '创建人 ';
comment on column CHB_SALER_STL_DEAL_SUB.ORG_CODE is '机构代码 ';
comment on column CHB_SALER_STL_DEAL_SUB.CREATE_DEPT is '创建部门 ';
comment on column CHB_SALER_STL_DEAL_SUB.CREATE_TIME is '创建时间 ';
comment on column CHB_SALER_STL_DEAL_SUB.UPDATE_USER is '更新人 ';
comment on column CHB_SALER_STL_DEAL_SUB.UPDATE_TIME is '更新时间 ';


DROP TABLE CHB_SALER_STL_DEAL_SUB_HIS;
create table CHB_SALER_STL_DEAL_SUB_HIS(
  HIS_NO	VARCHAR2(36) NOT NULL
  ,DATA_STATUS	VARCHAR2(10)
  ,INSTITUTION_CODE	VARCHAR2(20)
  ,PRD_REGIST_CODE	VARCHAR2(20)
  ,ACCOUNT_NO	VARCHAR2(60)
  ,ACCOUNT_NAME	VARCHAR2(200)
  ,TRADE_NO	VARCHAR2(100)
  ,TRADE_PS	VARCHAR2(4)
  ,TRADE_COUNTER_ACC_NO	VARCHAR2(60)
  ,TRADE_COUNTER_ACC_NAME	VARCHAR2(200)
  ,TRADE_COUNTER_ACC_BANK_NAME	VARCHAR2(200)
  ,TRADE_COUNTER_ACC_BANK_NO	VARCHAR2(60)
  ,TRADE_CCY	VARCHAR2(20)
  ,TRADE_AMT	NUMBER(30,8)
  ,TRADE_VDATE	DATE
  ,TRADE_TIME	VARCHAR2(6)
  ,POSTSCRIPT	VARCHAR2(600)
  ,REMARK	VARCHAR2(1000)
  ,CHB_SUBMIT_DEADLINE  TIMESTAMP(6)
  ,SUBMIT_DEADLINE  TIMESTAMP(6)
  ,STATUS_DATE  DATE
  ,SUBMIT_TIME  TIMESTAMP(6)
  ,SUMIT_SEQ   VARCHAR2(50)
  ,SEND_STATUS  VARCHAR2(2)
  ,SUBMIT_FEEDBACK_STATUS  VARCHAR2(50)
  ,SUBMIT_FEEDBACK_MSG VARCHAR2(600)
  ,CREATE_USER	VARCHAR2(20)
  ,ORG_CODE	VARCHAR2(20)
  ,CREATE_DEPT	VARCHAR2(20)
  ,CREATE_TIME	TIMESTAMP(6)
  ,UPDATE_USER	VARCHAR2(20)
  ,UPDATE_TIME	TIMESTAMP(6)
);
comment on table CHB_SALER_STL_DEAL_SUB_HIS is '销售结算资金交易信息登记履历表';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.HIS_NO is '履历番号 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.DATA_STATUS is '数据状态 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.INSTITUTION_CODE is '提供销售结算资金服务机构代码 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.PRD_REGIST_CODE is '产品登记编码 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.ACCOUNT_NO is '销售结算资金账号 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.ACCOUNT_NAME is '销售结算资金账户名称 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.TRADE_NO is '销售结算资金交易流水号 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.TRADE_PS is '销售结算资金流动方向 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.TRADE_COUNTER_ACC_NO is '销售结算资金交易对手方账号 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.TRADE_COUNTER_ACC_NAME is '销售结算资金交易对手方账户名称 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.TRADE_COUNTER_ACC_BANK_NAME is '销售结算资金交易对手方开户行名称 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.TRADE_COUNTER_ACC_BANK_NO is '销售结算资金交易对手方账户开户行号 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.TRADE_CCY is '销售结算资金交易币种 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.TRADE_AMT is '销售结算资金交易金额 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.TRADE_VDATE is '销售结算资金交易日期 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.TRADE_TIME is '销售结算资金交易时间 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.POSTSCRIPT is '销售结算资金交易附言 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.REMARK is '备注 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.CHB_SUBMIT_DEADLINE is '中债报送截止日 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.SUBMIT_DEADLINE is '报送截止日 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.STATUS_DATE is '数据日期 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.SUBMIT_TIME is '报送时间 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.SUMIT_SEQ is '报送流水号 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.SEND_STATUS is '报送状态 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.SUBMIT_FEEDBACK_STATUS is '反馈状态 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.SUBMIT_FEEDBACK_MSG is '报送反馈消息 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.CREATE_USER is '创建人 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.ORG_CODE is '机构代码 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.CREATE_DEPT is '创建部门 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.CREATE_TIME is '创建时间 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.UPDATE_USER is '更新人 ';
comment on column CHB_SALER_STL_DEAL_SUB_HIS.UPDATE_TIME is '更新时间 ';

delete from SYS_MENU where MENU_ID = 'REPORT_001_003_017';
delete from SYS_PAGE where PAGE_ID = 'SalesTransactionMaintenanceRegist001';
delete from SYS_PAGE where PAGE_ID = 'SalesTransactionMaintenanceRegist002';
delete from SYS_MENU_SPLIT where MENU_ID = 'REPORT_001_003_017';
INSERT INTO SYS_MENU_SPLIT (MENU_ID, SPLIT_ORDER_NO, SPLIT_MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_001_003_017', 3, '中债四期', 'admin', null, TIMESTAMP '2021-09-13 15:26:47', 'admin', TIMESTAMP '2021-09-13 15:26:47');
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_001_003_017', 'SVR', null, 'REPORT_001_003', 'SalesTransactionMaintenanceRegist001', 17, '02', '销售结算资金交易登记', 'system', null, TIMESTAMP '2019-09-12 00:00:00', 'system', TIMESTAMP '2019-09-12 00:00:00');
INSERT INTO SYS_PAGE VALUES ('SalesTransactionMaintenanceRegist001','REP','report/submitmanagement/salestransactionmaintenanceregist/SalesTransactionMaintenanceRegist001','report/submitmanagement/salestransactionmaintenanceregist/SalesTransactionMaintenanceRegist001','REPORT_001_003_017',null,null,'02','02','销售结算资金交易登记','system',null,sysdate,'system',sysdate);
INSERT INTO SYS_PAGE VALUES ('SalesTransactionMaintenanceRegist002','REP','report/submitmanagement/salestransactionmaintenanceregist/SalesTransactionMaintenanceRegist002','report/submitmanagement/salestransactionmaintenanceregist/SalesTransactionMaintenanceRegist002',null,null,null,'02','02','销售结算资金交易登记','system',null,sysdate,'system',sysdate);