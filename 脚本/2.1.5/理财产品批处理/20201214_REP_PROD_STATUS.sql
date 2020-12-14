DROP TABLE REP_PROD_STATUS;
-- CREATE TABLE  理财产品状态表
CREATE TABLE REP_PROD_STATUS (
	FINPROD_ID VARCHAR2(50) NOT NULL,
	PROD_REGIST_CODE VARCHAR2(15),
	FINPROD_ABBR VARCHAR2(200),
	ISSUE_PARTY_ID VARCHAR2(15),
	ISSUE_PARTY_NAME VARCHAR2(200),
	REP_APPLY_CNY NUMBER(20, 2),
	REP_SUM_APPLY_SHARE NUMBER(20, 2),
	REP_CASH_CNY NUMBER(20, 2),
	REP_CASH_PROFIT_CNY NUMBER(20, 2),
	REP_SUM_CASH_SHARE NUMBER(20, 2),
	PROD_STATUS VARCHAR2(20),
	PROD_PRD_AMT NUMBER(20, 2),
	ORIGINAL_VALUE NUMBER(10, 6),
	NET_VALUE NUMBER(10, 6),
	TOTAL_NET_NALUE NUMBER(10, 6),
	VALUE_CCY VARCHAR2(20),
	REP_INIT_NETVAL_CNY NUMBER(10, 6),
	REP_NETVAL_CNY NUMBER(10, 6),
	REP_SUM_NETVAL_CNY NUMBER(10, 6),
	REA_YIELD NUMBER(10, 6),
	REP_NEW_EXPECTED_YIELD NUMBER(10, 6),
	BANK_IMP_INT NUMBER(20, 2),
	CDATE DATE,
	OPEN_PRODD_END_SCALE NUMBER(10, 6),
	CLOSE_PRODD_END_SCALE NUMBER(10, 6),
	NON_STAND_BOND_RATIO NUMBER(20, 2),
	CREATE_USER VARCHAR2(20) DEFAULT 'system' NOT NULL ,
	CREATE_DEPT VARCHAR2(32),
	CREATE_TIME TIMESTAMP DEFAULT SYSDATE,
	UPDATE_USER VARCHAR2(20) DEFAULT 'system' NOT NULL ,
	UPDATE_TIME TIMESTAMP DEFAULT SYSDATE,
	PRIMARY KEY (FINPROD_ID, CDATE)
);
 
-- Add comments to the table 
comment on table REP_PROD_STATUS
  is '理财产品状态表';
-- Add comments to the columns 
comment on column REP_PROD_STATUS.FINPROD_ID
  is '产品ID';
comment on column REP_PROD_STATUS.PROD_REGIST_CODE
  is '产品登记编码';
comment on column REP_PROD_STATUS.FINPROD_ABBR
  is '产品名称';
comment on column REP_PROD_STATUS.ISSUE_PARTY_ID
  is '发行机构代码';
comment on column REP_PROD_STATUS.ISSUE_PARTY_NAME
  is '发行机构名称';
comment on column REP_PROD_STATUS.REP_APPLY_CNY
  is '累计申购金额';
comment on column REP_PROD_STATUS.REP_SUM_APPLY_SHARE
  is '累计申购份额';
comment on column REP_PROD_STATUS.REP_CASH_CNY
  is '累计兑付金额';
comment on column REP_PROD_STATUS.REP_CASH_PROFIT_CNY
  is '累计兑付收益金额';
comment on column REP_PROD_STATUS.REP_SUM_CASH_SHARE
  is '累计赎回份额';
comment on column REP_PROD_STATUS.PROD_STATUS
  is '产品统计日状态 存续/终止(1/0)';
comment on column REP_PROD_STATUS.PROD_PRD_AMT
  is '产品存续余额';
comment on column REP_PROD_STATUS.ORIGINAL_VALUE
  is '初始净值';
comment on column REP_PROD_STATUS.NET_VALUE
  is '产品净值';
comment on column REP_PROD_STATUS.TOTAL_NET_NALUE
  is '累计净值';
comment on column REP_PROD_STATUS.VALUE_CCY
  is '净值币种';
comment on column REP_PROD_STATUS.REP_INIT_NETVAL_CNY
  is '折算人民币初始净值';
comment on column REP_PROD_STATUS.REP_NETVAL_CNY
  is '折算人民币净值';
comment on column REP_PROD_STATUS.REP_SUM_NETVAL_CNY
  is '折算人民币累计净值';
comment on column REP_PROD_STATUS.REA_YIELD
  is '实现收益率';
comment on column REP_PROD_STATUS.REP_NEW_EXPECTED_YIELD
  is '最新预期收益率';
comment on column REP_PROD_STATUS.BANK_IMP_INT
  is '银行实现收益';
comment on column REP_PROD_STATUS.CDATE
  is '产品统计日';
comment on column REP_PROD_STATUS.OPEN_PRODD_END_SCALE
  is '开放式产品易变现资产期末持仓占比'; 
comment on column REP_PROD_STATUS.CLOSE_PRODD_END_SCALE
  is '封闭式产品债权类资产平均期限错配比'; 
comment on column REP_PROD_STATUS.NON_STAND_BOND_RATIO
  is '非标债权资产持仓比例'; 
comment on column REP_PROD_STATUS.CREATE_USER
  is '创建人';
comment on column REP_PROD_STATUS.CREATE_DEPT
  is '创建部门';
comment on column REP_PROD_STATUS.CREATE_TIME
  is '创建时间';
comment on column REP_PROD_STATUS.UPDATE_USER
  is '最后更新人';
comment on column REP_PROD_STATUS.UPDATE_TIME
  is '最后更新时间';

	