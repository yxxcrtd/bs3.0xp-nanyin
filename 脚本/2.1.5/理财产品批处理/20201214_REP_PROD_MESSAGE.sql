DROP TABLE REP_PROD_MESSAGE;
-- CREATE TABLE  理财产品信息表
CREATE TABLE REP_PROD_MESSAGE (
	FINPROD_ID VARCHAR2(50) NOT NULL,
	PROD_REGIST_CODE VARCHAR2(15),
	PROD_ID VARCHAR2(50),
	PROD_INS_ID VARCHAR2(50),
	FINPROD_ABBR VARCHAR2(200),
	PROD_BRAND VARCHAR2(30),
	ISSUE_PARTY_ID VARCHAR2(15),
	ISSUE_PARTY_NAME VARCHAR2(200),
	ISSUER_TYPE VARCHAR2(5) DEFAULT '03',
	PROD_STAGE VARCHAR2(8),
	PROFIT_TYPE VARCHAR2(50),
	TERMTYPE VARCHAR2(50),
	RISK_LEVEL VARCHAR2(50),
	SALETARGET VARCHAR2(50),
	INVEST_ARAE VARCHAR2(50),
	OPERATE_MODE VARCHAR2(50),
	REP_BOOK_MODE VARCHAR2(50) DEFAULT '02',
	CMMODE VARCHAR2(50),
	PMMODE VARCHAR2(50),
	MNAME VARCHAR2(50),
	MANAGE_TYPE VARCHAR2(50),
	APPROVER_ID VARCHAR2(50),
	APPROVER_NAME VARCHAR2(300),
	DESIGNER_ID VARCHAR2(50),
	DESIGNER_NAME VARCHAR2(300),
	MANAGER_ID VARCHAR2(50),
	MANAGER_NAME VARCHAR2(300),
	PRICED_MODE VARCHAR2(50),
	INV_ASSET_TYPE VARCHAR2(50),
	CON_PRD_TRAGET VARCHAR2(50),
	CONT_MODE VARCHAR2(50),
	COOPERATION_NAME VARCHAR2(180),
	INV_TYPE_RAT VARCHAR2(300),
	IS_YIELD VARCHAR2(5),
	IS_YIELD_MEAS_BASIS VARCHAR2(5),
	RAISE_CCY VARCHAR2(10),
	CASH_PRIN_CCY VARCHAR2(10),
	CASH_PROF_CCY VARCHAR2(10),
	INVEST_PRIN_DATE VARCHAR2(50),
	INVEST_PROF_DATE VARCHAR2(50),
	TERM_RIGHT_FLAG VARCHAR2(5),
	CUS_REDEEM_FLAG VARCHAR2(5),
	CREDIT_FLAG VARCHAR2(5),
	CREDIT_MODE VARCHAR2(50),
	IS_CONSTRACTE VARCHAR2(5),
	GRADE_TYPE VARCHAR2(5),
	YIELD_COMMENT VARCHAR2(400),
	OPEN_MODE VARCHAR2(5),
	OPEN_CIRCLE VARCHAR2(5),
	OPEN_CIRCLE_DAYS NUMBER(30,4),
	HOLIDAY_ON_FLAG VARCHAR2(50),
	AVG_OPEN_COUNT NUMBER(30,4),
	OPEN_BUSS VARCHAR2(50),
	RAISE_VDATE DATE,
	RAISE_ENDDATE DATE,
	PRD_VDATE DATE,
	PRD_MDATE DATE,
	REAL_MDATE DATE,
	REP_DECLARE_DATE DATE,
	REP_ISSUE_DATE DATE,
	SUBMIT_TIME DATE,
	DURATIONFLAG VARCHAR2(50),
	REP_SALE_AREA VARCHAR2(1000),
	ISAGENCY VARCHAR2(2),
	IN_DEP_CODE VARCHAR2(50),
	IN_DEP_NAME VARCHAR2(50),
	OUT_DEP_CTRY VARCHAR2(50),
	OUT_DEP_NAME VARCHAR2(200),
	BUSI_ACCT_NO VARCHAR2(60),
	ACCT_NAME VARCHAR2(200),
	IS_NEW VARCHAR2(2)  DEFAULT 'Y',
	RAISE_TYPE VARCHAR2(50),
	IVSTORIENTATION VARCHAR2(50),
	IS_SHORT_TERM VARCHAR2(2),
	SHORT_TERM NUMBER(30,0),
	IS_FREE_REDEM VARCHAR2(2),
	CASH_MANA_CATE VARCHAR2(2),
	YIELD_HIGH NUMBER(8,5),
	YIELD_LOW NUMBER(8,5),
	YIELD_REMARK VARCHAR2(400),
	INVEST_FEE_RATE NUMBER(30,14),
	EXC_FIN_SECTOR VARCHAR2(2),
	HIGH_YIELD NUMBER(8,5),
	LOW_YIELD NUMBER(8,5),
	HIGH_YIELD_ISSUE NUMBER(30,14),
	LOW_YIELD_ISSUE NUMBER(30,14),
	EXP_AVG_YIELD NUMBER(30,14),
	TRU_FEE_RATE NUMBER(30,14),
	RAISE_AMT_PLAN NUMBER(30,4),
	TOTAL_AMT NUMBER(30,2),
	REP_ACT_ACHI_PROFIT NUMBER(15,2),
	REP_CASH_CUS_PROFIT NUMBER(15,2),
	REP_CASH_CUS_SUM NUMBER(15,2),
	REP_CUS_ACT_YEAR_YIELD NUMBER(8,5),
	REP_PROD_ACT_YEAR_YIELD NUMBER(8,5),
	CDATE DATE,	
	CREATE_USER VARCHAR2(20) DEFAULT 'system' NOT NULL ,
	CREATE_DEPT VARCHAR2(32),
	CREATE_TIME TIMESTAMP DEFAULT SYSDATE,
	UPDATE_USER VARCHAR2(20) DEFAULT 'system' NOT NULL ,
	UPDATE_TIME TIMESTAMP DEFAULT SYSDATE,
	PRIMARY KEY (FINPROD_ID, CDATE)
);
 
-- Add comments to the table 
comment on table REP_PROD_MESSAGE
  is '理财产品信息表';
-- Add comments to the columns 
comment on column REP_PROD_MESSAGE.FINPROD_ID
  is '产品ID';
comment on column REP_PROD_MESSAGE.PROD_REGIST_CODE
  is '产品登记编码';
comment on column REP_PROD_MESSAGE.PROD_ID
  is '理财产品代码';
comment on column REP_PROD_MESSAGE.PROD_INS_ID
  is '行内标识码';
comment on column REP_PROD_MESSAGE.FINPROD_ABBR
  is '产品名称';
comment on column REP_PROD_MESSAGE.PROD_BRAND
  is '产品品牌';
comment on column REP_PROD_MESSAGE.ISSUE_PARTY_ID
  is '发行机构代码';
comment on column REP_PROD_MESSAGE.ISSUE_PARTY_NAME
  is '发行机构名称';
comment on column REP_PROD_MESSAGE.ISSUER_TYPE
  is '发行机构类型 03 城商行';
comment on column REP_PROD_MESSAGE.PROD_STAGE
  is '产品期次';
comment on column REP_PROD_MESSAGE.PROFIT_TYPE
  is '产品收益类型  01 保证收益/02 保本浮动收益/03 非保本浮动收益';
comment on column REP_PROD_MESSAGE.TERMTYPE
  is '产品期限';
comment on column REP_PROD_MESSAGE.RISK_LEVEL
  is '产品风险等级';
comment on column REP_PROD_MESSAGE.SALETARGET
  is '目标客户类型';
comment on column REP_PROD_MESSAGE.INVEST_ARAE
  is '资金投向地区';
comment on column REP_PROD_MESSAGE.OPERATE_MODE
  is '产品运作模式';
comment on column REP_PROD_MESSAGE.REP_BOOK_MODE
  is '会计核算方式  01 表内/02 表外';
comment on column REP_PROD_MESSAGE.CMMODE
  is '产品资产配置方式';
comment on column REP_PROD_MESSAGE.PMMODE
  is '产品管理模式';
comment on column REP_PROD_MESSAGE.MNAME
  is '实际管理人';
comment on column REP_PROD_MESSAGE.MANAGE_TYPE
  is '管理方式';
comment on column REP_PROD_MESSAGE.APPROVER_ID
  is '产品审批人身份证号';
comment on column REP_PROD_MESSAGE.APPROVER_NAME
  is '产品审批人姓名';
comment on column REP_PROD_MESSAGE.DESIGNER_ID
  is '产品设计人身份证号';
comment on column REP_PROD_MESSAGE.DESIGNER_NAME
  is '产品设计人姓名';
comment on column REP_PROD_MESSAGE.MANAGER_ID
  is '投资经理身份证号';
comment on column REP_PROD_MESSAGE.MANAGER_NAME
  is '投资经理姓名';
comment on column REP_PROD_MESSAGE.PRICED_MODE
  is '产品定价方式';
comment on column REP_PROD_MESSAGE.INV_ASSET_TYPE
  is '投资资产类型';
comment on column REP_PROD_MESSAGE.CON_PRD_TRAGET
  is '结构性产品挂钩标的';
comment on column REP_PROD_MESSAGE.CONT_MODE
  is '合作模式';
comment on column REP_PROD_MESSAGE.COOPERATION_NAME
  is '合作机构名称';
comment on column REP_PROD_MESSAGE.INV_TYPE_RAT
  is '投资资产种类及比例';
comment on column REP_PROD_MESSAGE.IS_YIELD
  is '是否有预期收益率';
comment on column REP_PROD_MESSAGE.IS_YIELD_MEAS_BASIS
  is '是否有收益率测算依据';
comment on column REP_PROD_MESSAGE.RAISE_CCY
  is '募集币种';
comment on column REP_PROD_MESSAGE.CASH_PRIN_CCY
  is '兑付本金币种';
comment on column REP_PROD_MESSAGE.CASH_PROF_CCY
  is '兑付收益币种';
comment on column REP_PROD_MESSAGE.INVEST_PRIN_DATE
  is '投资本金到账日';
comment on column REP_PROD_MESSAGE.INVEST_PROF_DATE
  is '投资收益到账日';
comment on column REP_PROD_MESSAGE.TERM_RIGHT_FLAG
  is '发行机构提前终止权标识';
comment on column REP_PROD_MESSAGE.CUS_REDEEM_FLAG
  is '客户赎回权标识';
comment on column REP_PROD_MESSAGE.CREDIT_FLAG
  is '产品增信标识';
comment on column REP_PROD_MESSAGE.CREDIT_MODE
  is '产品增信形式';
comment on column REP_PROD_MESSAGE.IS_CONSTRACTE
  is '是否为结构化（分级）产品';
comment on column REP_PROD_MESSAGE.GRADE_TYPE
  is '层级类别';
comment on column REP_PROD_MESSAGE.YIELD_COMMENT
  is '收益率分档说明';
comment on column REP_PROD_MESSAGE.OPEN_MODE
  is '开放模式';
comment on column REP_PROD_MESSAGE.OPEN_CIRCLE
  is '规律开放周期';
comment on column REP_PROD_MESSAGE.OPEN_CIRCLE_DAYS
  is '其他规律开放周期';
comment on column REP_PROD_MESSAGE.HOLIDAY_ON_FLAG
  is '节假日是否开放';
comment on column REP_PROD_MESSAGE.AVG_OPEN_COUNT
  is '平均开放次数';
comment on column REP_PROD_MESSAGE.OPEN_BUSS
  is '开放期业务';
comment on column REP_PROD_MESSAGE.RAISE_VDATE
  is '募集起始日期';
comment on column REP_PROD_MESSAGE.RAISE_ENDDATE
  is '募集结束日期';
comment on column REP_PROD_MESSAGE.PRD_VDATE
  is '产品起始日期';
comment on column REP_PROD_MESSAGE.PRD_MDATE
  is '产品终止日期';
comment on column REP_PROD_MESSAGE.REAL_MDATE
  is '产品实际终止日期';
comment on column REP_PROD_MESSAGE.REP_DECLARE_DATE
  is '报告登记日';
comment on column REP_PROD_MESSAGE.REP_ISSUE_DATE
  is '发行登记日';
comment on column REP_PROD_MESSAGE.SUBMIT_TIME
  is '募集登记日';
comment on column REP_PROD_MESSAGE.DURATIONFLAG
  is '产品状态';
comment on column REP_PROD_MESSAGE.REP_SALE_AREA
  is '产品销售区域';
comment on column REP_PROD_MESSAGE.ISAGENCY
  is '是否有其他机构代销';
comment on column REP_PROD_MESSAGE.IN_DEP_CODE
  is '境内托管机构代码';
comment on column REP_PROD_MESSAGE.IN_DEP_NAME
  is '境内托管机构名称';
comment on column REP_PROD_MESSAGE.OUT_DEP_CTRY
  is '境外托管机构国别';
comment on column REP_PROD_MESSAGE.OUT_DEP_NAME
  is '境外托管机构名称';
comment on column REP_PROD_MESSAGE.BUSI_ACCT_NO
  is '理财产品资金托管账号';
comment on column REP_PROD_MESSAGE.ACCT_NAME
  is '理财产品资金托管账户';
comment on column REP_PROD_MESSAGE.IS_NEW
  is '是否新产品';
comment on column REP_PROD_MESSAGE.RAISE_TYPE
  is '产品募集方式';
comment on column REP_PROD_MESSAGE.IVSTORIENTATION
  is '产品投资性质';
comment on column REP_PROD_MESSAGE.IS_SHORT_TERM
  is '是否设置最短持有期限';
comment on column REP_PROD_MESSAGE.SHORT_TERM
  is '最短持有期限（天）';
comment on column REP_PROD_MESSAGE.IS_FREE_REDEM
  is '最短持有期后是否自由赎回';
comment on column REP_PROD_MESSAGE.CASH_MANA_CATE
  is '是否为现金管理类理财产品';
comment on column REP_PROD_MESSAGE.YIELD_HIGH
  is '业绩比较基准上限';
comment on column REP_PROD_MESSAGE.YIELD_LOW
  is '业绩比较基准下限';
comment on column REP_PROD_MESSAGE.YIELD_REMARK
  is '业绩比较基准说明';
comment on column REP_PROD_MESSAGE.INVEST_FEE_RATE
  is '投资管理费率';
comment on column REP_PROD_MESSAGE.EXC_FIN_SECTOR
  is '是否金融同业专属';
comment on column REP_PROD_MESSAGE.HIGH_YIELD
  is '申报期预计客户最高年收益率';
comment on column REP_PROD_MESSAGE.LOW_YIELD
  is '申报期预计客户最低年收益率';
comment on column REP_PROD_MESSAGE.HIGH_YIELD_ISSUE
  is '发行期预计客户最高年收益率';
comment on column REP_PROD_MESSAGE.LOW_YIELD_ISSUE
  is '发行期预计客户最低年收益率';
comment on column REP_PROD_MESSAGE.EXP_AVG_YIELD
  is '发行期预计平均收益率';
comment on column REP_PROD_MESSAGE.TRU_FEE_RATE
  is '托管费率';
comment on column REP_PROD_MESSAGE.RAISE_AMT_PLAN
  is '计划募集金额';
comment on column REP_PROD_MESSAGE.TOTAL_AMT
  is '实际募集金额';
comment on column REP_PROD_MESSAGE.REP_ACT_ACHI_PROFIT
  is '银行实际实现收入';
comment on column REP_PROD_MESSAGE.REP_CASH_CUS_PROFIT
  is '兑付客户收益';
comment on column REP_PROD_MESSAGE.REP_CASH_CUS_SUM
  is '兑付客户总金额';
comment on column REP_PROD_MESSAGE.REP_CUS_ACT_YEAR_YIELD
  is '客户实际年化收益率';
comment on column REP_PROD_MESSAGE.REP_PROD_ACT_YEAR_YIELD
  is '产品实际年化收益率';
comment on column REP_PROD_MESSAGE.CDATE
  is '产品统计日';
comment on column REP_PROD_MESSAGE.CREATE_USER
  is '创建人';
comment on column REP_PROD_MESSAGE.CREATE_DEPT
  is '创建部门';
comment on column REP_PROD_MESSAGE.CREATE_TIME
  is '创建时间';
comment on column REP_PROD_MESSAGE.UPDATE_USER
  is '最后更新人';
comment on column REP_PROD_MESSAGE.UPDATE_TIME
  is '最后更新时间';

	