drop table "JXB_BK_CASH_ACCT";
drop table "JXB_BK_CASH_FLOW";
drop table "JXB_BK_CASH_RCPY";
drop table "JXB_BK_INFO";
drop table "JXB_SECACCT";
drop table "JXC_COAAGLSECMAP";
drop table "JXC_COAAGROUP";
drop table "JXC_GL_COAA_MAP";
drop table "JXJ_WORKFLOWFACTORY";
drop table "JXM_SEC_PRICE";
drop table "JXS_BROKER";
drop table "JXS_CASHFLOWCFG";
drop table "JXS_CASHFLOWFLOATRATE";
drop table "JXS_CASHFLOWPAR";
drop table "JXS_CASHFLOWPAYMENT";
drop table "JXS_CASHFLOWRATE";
drop table "JXS_ISSUE_INFO";
drop table "JXS_ISSUER";
drop table "JXS_PORTFOLIO_HDR";
drop table "JXS_PORTFOLIOINTERFACEMAP";
drop table "JXS_RPSECTYPE";
drop table "JXS_SECCONTRACT";
drop table "JXS_SMFDIV";
drop table "JXT_TRAN";
drop table "JXT_TRAN_BASIC";
drop table "JXT_TRAN_BASIC_FEE";
drop table "JXT_TRAN_CLO";
drop table "JXT_TRAN_COLLATERAL";
drop table "JXT_TRAN_FEE_DEF";
drop table "JXT_TRAN_FEE_TYPEMAP";
drop table "JXT_TRAN_SUM";
drop table "JXU_MSGLOG";

DROP SEQUENCE "COAAGROUP_ID_SEQ";
DROP SEQUENCE "GLCOAAMAP_ID_SEQ";
DROP SEQUENCE "MSGLOG_ID_SEQ";
DROP SEQUENCE "SECACCT_ID_SEQ";
DROP SEQUENCE "SECPRICE_ID_SEQ";
DROP SEQUENCE "CASHFLOWCFG_ID_SEQ";
DROP SEQUENCE "CASHFLOWFLOATRATE_ID_SEQ";
DROP SEQUENCE "CASHFLOWPAR_ID_SEQ";
DROP SEQUENCE "CASHFLOWPAYMENT_ID_SEQ";
DROP SEQUENCE "CASHFLOWRATE_ID_SEQ";
DROP SEQUENCE "ISSUEINFO_ID_SEQUENCE";
DROP SEQUENCE "SECCONTRACT_ID_SEQ";
DROP SEQUENCE "SMFDIV_ID_SEQ";
DROP SEQUENCE "TRADE_ID_SEQUENCE";
DROP SEQUENCE "PORTFOLIOINTERFACEMAP_ID_SEQ";
DROP SEQUENCE "WORKFLOWFACTORY_ID_SEQ";
DROP SEQUENCE "BKCASHACCT_ID_SEQ";
DROP SEQUENCE "TRAN_ID_SEQ";
DROP SEQUENCE "TRANSUM_ID_SEQ";
DROP SEQUENCE "TRANCLO_ID_SEQ";
DROP SEQUENCE "TRANCOLLATERAL_ID_SEQ";
DROP SEQUENCE "TRANFEEDEF_ID_SEQ";
DROP SEQUENCE "TRANBASICFEE_ID_SEQ";
DROP SEQUENCE "TRANFEETYPEMAP_ID_SEQ";
DROP SEQUENCE "BKCASHRCPY_ID_SEQ";
DROP SEQUENCE "BKCASHFLOW_ID_SEQ";
DROP SEQUENCE "BROKER_ID_SEQ";
DROP SEQUENCE "ISSUER_ID_SEQ";
DROP SEQUENCE "RPSECTYPE_ID_SEQ";
DROP SEQUENCE "JXC_COAAGLSECMAP_ID_SEQ";




--总账组
CREATE TABLE "JXC_COAAGROUP" 
   (	
	"GROUPNUM" NUMBER(10,0) NOT NULL ENABLE, 
	"GROUPNAME" VARCHAR2(255), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(32), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),  
	 PRIMARY KEY ("GROUPNUM")
);
   
COMMENT ON COLUMN "JXC_COAAGROUP"."GROUPNUM" IS '唯一主键';
COMMENT ON COLUMN "JXC_COAAGROUP"."GROUPNAME" IS '总账组名称';
COMMENT ON COLUMN "JXC_COAAGROUP"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "JXC_COAAGROUP"."CREATE_DEPT" IS '创建部门';
COMMENT ON COLUMN "JXC_COAAGROUP"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "JXC_COAAGROUP"."UPDATE_USER" IS '更新用户';
COMMENT ON COLUMN "JXC_COAAGROUP"."CREATE_USER" IS '创建用户';


--总账组-总账栏-科目映射表
CREATE TABLE "JXC_GL_COAA_MAP" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"ACCT_NUM" VARCHAR2(50), 
	"CHARTNUM" VARCHAR2(50), 
	"COLUMNNUM" NUMBER(10,0), 
	"GROUPNUM" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	 PRIMARY KEY ("ID")
);

comment on column "JXC_GL_COAA_MAP"."ID" is '唯一主键';
comment on column "JXC_GL_COAA_MAP"."ACCT_NUM" is '科目号';
comment on column "JXC_GL_COAA_MAP"."CHARTNUM" is '科目表';
comment on column "JXC_GL_COAA_MAP"."COLUMNNUM" is '总账栏';
comment on column "JXC_GL_COAA_MAP"."GROUPNUM" is '总账组';
comment on column "JXC_GL_COAA_MAP"."CREATE_TIME" is '创建时间';
comment on column "JXC_GL_COAA_MAP"."CREATE_DEPT" is '创建部门';
comment on column "JXC_GL_COAA_MAP"."UPDATE_TIME" is '更新时间';
comment on column "JXC_GL_COAA_MAP"."UPDATE_USER" is '更新用户';
comment on column "JXC_GL_COAA_MAP"."CREATE_USER" is '创建用户';



--操作日志表
CREATE TABLE "JXU_MSGLOG" 
(	
	"LOGID" NUMBER(20,0) NOT NULL ENABLE, 
	"ERRORCODE" NUMBER(10,0), 
	"ERRORINFO" VARCHAR2(4000),  
	"INGORE" NUMBER(1,0), 
	"LEDGERDATE" DATE, 
	"MSG" VARCHAR2(4000),
	"OPERATIONTIME" DATE, 
	"OPERATIONTYPE" NUMBER(10,0), 
	"PORTFOLIO" VARCHAR2(50), 
	"STATUS" NUMBER(1,0), 
	"SOURCEID" NUMBER(10,0), 
	"USERID" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20), 
	PRIMARY KEY ("LOGID")
);

   
comment on column "JXU_MSGLOG"."LOGID" is '唯一主键';
comment on column "JXU_MSGLOG"."ERRORCODE" is '消息执行失败编号 | 数据字典--错误编号 ';
comment on column "JXU_MSGLOG"."ERRORINFO" is '消息执行失败后的错误信息 ';
comment on column "JXU_MSGLOG"."INGORE" is '是否可以忽略该错误。在工作流中,如果该步骤有执行错误，是不能继续做下一步操作的，除非忽略该错误 ';
comment on column "JXU_MSGLOG"."LEDGERDATE" is '做账日期';
comment on column "JXU_MSGLOG"."MSG" is '消息内容';
comment on column "JXU_MSGLOG"."OPERATIONTIME" is '消息执行时间';
comment on column "JXU_MSGLOG"."OPERATIONTYPE" is '工作流中的操作步骤编号 | 数据字典--工作流步骤 ';
comment on column "JXU_MSGLOG"."PORTFOLIO" is '投资组合系统编号,关联Portfolio_Hdr表的portfolio字段 ';
comment on column "JXU_MSGLOG"."STATUS" is '消息执行是否成功 ';
comment on column "JXU_MSGLOG"."SOURCEID" is '接口编号,通过哪个接口导入的消息 | 关联InterfaceSource表主键 ';
comment on column "JXU_MSGLOG"."USERID" is '用户编号 | 关联User表主键 ';
comment on column "JXU_MSGLOG"."CREATE_TIME" is '创建时间';
comment on column "JXU_MSGLOG"."CREATE_DEPT" is '创建部门';
comment on column "JXU_MSGLOG"."UPDATE_TIME" is '更新时间';
comment on column "JXU_MSGLOG"."UPDATE_USER" is '更新用户';
comment on column "JXU_MSGLOG"."CREATE_USER" is '创建用户';


--证券账户表
CREATE TABLE "JXB_SECACCT" 
(	
	"ACCTID" NUMBER(10,0) NOT NULL ENABLE, 
	"ACCTCODE" VARCHAR2(100), 
	"ACCTDESC" VARCHAR2(255), 
	"DEFAULTCASHACCT" NUMBER(10,0), 
	"PORTFOLIO" VARCHAR2(50), 
	"SEATNUMBER" VARCHAR2(50), 
	"BROKERCODE" VARCHAR2(10), 
	"GLGROP" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20), 
	 PRIMARY KEY ("ACCTID")
 );

COMMENT ON COLUMN "JXB_SECACCT"."ACCTID" IS '唯一主键';
COMMENT ON COLUMN "JXB_SECACCT"."ACCTCODE" IS '证券账户编号 ';
COMMENT ON COLUMN "JXB_SECACCT"."ACCTDESC" IS '证券账户名称 ';
COMMENT ON COLUMN "JXB_SECACCT"."DEFAULTCASHACCT" IS '该证券账户的默认现金账户,当发生自动交易时，一般会使用默认现金账户 | 关联Bk_cash_acct表主键 ';
COMMENT ON COLUMN "JXB_SECACCT"."PORTFOLIO" IS '投资组合系统编号,关联Portfolio_Hdr表的portfolio字段 ';
COMMENT ON COLUMN "JXB_SECACCT"."SEATNUMBER" IS '席位号 | 关联SeatNumber表的seatNumberAcct字段 ';
COMMENT ON COLUMN "JXB_SECACCT"."BROKERCODE" IS '券商编号，该证券账户属于哪个券商 | 关联Broker表主键 ';
COMMENT ON COLUMN "JXB_SECACCT"."GLGROP" IS '尚未使用 ';
COMMENT ON COLUMN "JXB_SECACCT"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "JXB_SECACCT"."CREATE_DEPT" IS '创建部门';
COMMENT ON COLUMN "JXB_SECACCT"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "JXB_SECACCT"."UPDATE_USER" IS '更新用户';
COMMENT ON COLUMN "JXB_SECACCT"."CREATE_USER" IS '创建用户';



--价格表
CREATE TABLE "JXM_SEC_PRICE" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"LST_UPDATE" DATE, 
	"PRICE" NUMBER(30,14), 
	"PRICE_DATE" DATE, 
	"PRICETO" NUMBER(3,0), 
	"YIELD" NUMBER(30,14), 
	"CUSIP" VARCHAR2(50), 
	"SOURCEID" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	 PRIMARY KEY ("ID")
);

comment on column "JXM_SEC_PRICE"."ID" is '唯一主键';
comment on column "JXM_SEC_PRICE"."LST_UPDATE" is '最后一次更新日期';
comment on column "JXM_SEC_PRICE"."PRICE" is '价格';
comment on column "JXM_SEC_PRICE"."PRICE_DATE" is '价格的日期';
comment on column "JXM_SEC_PRICE"."PRICETO" is '尚未使用 | 77:Maturity 66:Call 82:Refunding 80:Put 65:Average Life 74:Cost 86:Amortized 87:Yld To Worst 69:Reset / Expected Call ';
comment on column "JXM_SEC_PRICE"."YIELD" is '货币基金，债券等收益类产品的动态利率 ';
comment on column "JXM_SEC_PRICE"."CUSIP" is '证券编号 | 关联Issue_info表主键 ';
comment on column "JXM_SEC_PRICE"."SOURCEID" is '价格数据来源';
comment on column "JXM_SEC_PRICE"."CREATE_TIME" is '创建时间';
comment on column "JXM_SEC_PRICE"."CREATE_DEPT" is '创建部门';
comment on column "JXM_SEC_PRICE"."UPDATE_TIME" is '更新时间';
comment on column "JXM_SEC_PRICE"."UPDATE_USER" is '更新用户';
comment on column "JXM_SEC_PRICE"."CREATE_USER" is '创建用户';


--现金流配置
CREATE TABLE "JXS_CASHFLOWCFG" 
(	
	"CASHFLOWID" NUMBER(15,0) NOT NULL ENABLE, 
	"ACTIVEFLAG" NUMBER(1,0), 
	"AUTOINTERESTFLAG" NUMBER(*,0), 
	"AUTOSETFLAG" NUMBER(1,0), 
	"BALLOONDATE" NUMBER(3,0), 
	"CASHFLOWNAME" VARCHAR2(100), 
	"COUPON" NUMBER(30,14), 
	"CPNDATE" NUMBER(10,0), 
	"CPNDELAYDATENUM" NUMBER(10,0), 
	"CPNDELAYDATETYPE" NUMBER(1,0), 
	"CPNFREQ" NUMBER(10,0), 
	"CPNMO" NUMBER(10,0), 
	"CPNTYPE" NUMBER(3,0), 
	"DAYLAG" VARCHAR2(3), 
	"EFFECTIVEDATE" NUMBER(10,0), 
	"EFFECTIVERATE" NUMBER(30,14), 
	"ENDNDAYS" NUMBER(3,0), 
	"ENDNDAYSBASE" NUMBER(3,0), 
	"ENDNDAYSTYPE" NUMBER(3,0), 
	"EXTERNALRATEFLAG" NUMBER(1,0), 
	"FIRSTINTDATE" DATE, 
	"FIXEDINCOME" NUMBER(30,14), 
	"INTCODE" NUMBER(3,0), 
	"INTDECIMALS" NUMBER(3,0), 
	"LOCKOUTPERIOD" NUMBER(10,0), 
	"LOOKBACKPERIOD" NUMBER(10,0), 
	"MATDATE" NUMBER(10,0), 
	"MTGTYPE" NUMBER(10,0), 
	"ORIGTERM" NUMBER(10,0), 
	"PAYMENTKIND" NUMBER(10,0), 
	"PAYMENTMODEL" NUMBER(10,0), 
	"PREPAYALLOCMETHOD" VARCHAR2(50), 
	"PREPAYMENTRATE" NUMBER(30,14), 
	"PRICEDECIMALS" NUMBER(10,0), 
	"PSPAR" NUMBER(30,14), 
	"RECORPAY" NUMBER(10,0), 
	"RESETHOLIDAYSCHED" NUMBER(10,0), 
	"STARTNDAYS" NUMBER(10,0), 
	"STARTNDAYSBASE" NUMBER(10,0), 
	"STARTNDAYSTYPE" NUMBER(10,0), 
	"SVCFEE" NUMBER(30,14), 
	"TRANCHEENDDATE" NUMBER(10,0), 
	"TRANCHESTARTDATE" NUMBER(10,0), 
	"TRANCHETYPE" NUMBER(10,0), 
	"VRBASE" VARCHAR2(50), 
	"VRBASISPTADJUSTMENT" NUMBER(30,14), 
	"VRDETERADJDAYS" NUMBER(10,0), 
	"VRDETERADJMO" NUMBER(10,0), 
	"VRMAX" NUMBER(30,14), 
	"VRMETHOD" NUMBER(10,0), 
	"VRMIN" NUMBER(30,14), 
	"VRRESETDATE" NUMBER(10,0), 
	"VRRESETFREQ" NUMBER(10,0), 
	"VRRESETMO" NUMBER(10,0), 
	"VRRESETNDAYS" NUMBER(10,0), 
	"HOLIDAYSD" VARCHAR2(6), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("CASHFLOWID")
);


comment on column "JXS_CASHFLOWCFG"."CASHFLOWID" is '唯一主键';
comment on column "JXS_CASHFLOWCFG"."ACTIVEFLAG" is '是否有效';
comment on column "JXS_CASHFLOWCFG"."AUTOINTERESTFLAG" is '是否自动生成利息';
comment on column "JXS_CASHFLOWCFG"."AUTOSETFLAG" is '是否自动结息';
comment on column "JXS_CASHFLOWCFG"."BALLOONDATE" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."CASHFLOWNAME" is '现金流名称';
comment on column "JXS_CASHFLOWCFG"."COUPON" is '利率';
comment on column "JXS_CASHFLOWCFG"."CPNDATE" is '税费结转的日的选择';
comment on column "JXS_CASHFLOWCFG"."CPNDELAYDATENUM" is '推迟天数';
comment on column "JXS_CASHFLOWCFG"."CPNDELAYDATETYPE" is '推迟天数的类型 | 1:工作日 2:自然日 ';
comment on column "JXS_CASHFLOWCFG"."CPNFREQ" is '税费结转频率 | 65:每年   66:到期   77：每月   79:每N天   81:每个季度   83:每半年   84:自定义计划 ';
comment on column "JXS_CASHFLOWCFG"."CPNMO" is '税费结转的具体月份/周选择 | 数据字典--InterestCpnMo ';
comment on column "JXS_CASHFLOWCFG"."CPNTYPE" is '现金流利率类型 | 70:固定 86:浮动 ';
comment on column "JXS_CASHFLOWCFG"."DAYLAG" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."EFFECTIVEDATE" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."EFFECTIVERATE" is '有效利率,尚未使用 ';
comment on column "JXS_CASHFLOWCFG"."ENDNDAYS" is '结束计息,结束日+N天，自然日还是工作日根据EndNdaysType决定 ';
comment on column "JXS_CASHFLOWCFG"."ENDNDAYSBASE" is '以结束交易日的结算日还是交易日为基准';
comment on column "JXS_CASHFLOWCFG"."ENDNDAYSTYPE" is 'N天的类型,自然日/交易日';
comment on column "JXS_CASHFLOWCFG"."EXTERNALRATEFLAG" is '是否使用外部利率配置';
comment on column "JXS_CASHFLOWCFG"."FIRSTINTDATE" is '尚未使用 ';
comment on column "JXS_CASHFLOWCFG"."FIXEDINCOME" is '固定计提';
comment on column "JXS_CASHFLOWCFG"."INTCODE" is '计费的日期的选择 | 65:30/360   80:30/365   87:30/act   66:act/act   68:act/360   69:act/365 ';
comment on column "JXS_CASHFLOWCFG"."INTDECIMALS" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."LOCKOUTPERIOD" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."LOOKBACKPERIOD" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."MATDATE" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."MTGTYPE" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."ORIGTERM" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."PAYMENTKIND" is '收到的利息用途,如果是份额再投，会自动生成一笔交易 | 1:现金 2份额再投 ';
comment on column "JXS_CASHFLOWCFG"."PAYMENTMODEL" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."PREPAYALLOCMETHOD" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."PREPAYMENTRATE" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."PRICEDECIMALS" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."PSPAR" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."RECORPAY" is '该现金流配置是收入还是支出 | 0:收入 1:支出 ';
comment on column "JXS_CASHFLOWCFG"."RESETHOLIDAYSCHED" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."STARTNDAYS" is '开始计息，开始日+N天，自然日还是工作日根据StartNDaysType决定 ';
comment on column "JXS_CASHFLOWCFG"."STARTNDAYSBASE" is '利息开始计提基准日 | 1:交易日 2:结算日 ';
comment on column "JXS_CASHFLOWCFG"."STARTNDAYSTYPE" is '日期类型 | 1: 工作日 2:自然日 ';
comment on column "JXS_CASHFLOWCFG"."SVCFEE" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."TRANCHEENDDATE" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."TRANCHESTARTDATE" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."TRANCHETYPE" is '尚未使用';
comment on column "JXS_CASHFLOWCFG"."VRBASE" is '浮动利率基于哪个指数的值调整，可以指向cusip';
comment on column "JXS_CASHFLOWCFG"."VRBASISPTADJUSTMENT" is '浮动利率的辅助浮动规则，相加基数，相乘系数，复利计算的辅助计算数值';
comment on column "JXS_CASHFLOWCFG"."VRDETERADJDAYS" is '浮动利率调整天数';
comment on column "JXS_CASHFLOWCFG"."VRDETERADJMO" is '浮动利率调整月份';
comment on column "JXS_CASHFLOWCFG"."VRMAX" is '浮动利率的最大值';
comment on column "JXS_CASHFLOWCFG"."VRMETHOD" is '浮动利率的计算规则选择  相加基数，相乘系数，复利计算';
comment on column "JXS_CASHFLOWCFG"."VRMIN" is '浮动利率的最小值';
comment on column "JXS_CASHFLOWCFG"."VRRESETDATE" is '浮动利率重置的日期';
comment on column "JXS_CASHFLOWCFG"."VRRESETFREQ" is '浮动利率重置的频率';
comment on column "JXS_CASHFLOWCFG"."VRRESETMO" is '浮动利率重置的月份';
comment on column "JXS_CASHFLOWCFG"."VRRESETNDAYS" is '如果vrResetFreq选择了跟随付款日，这里填写N，可以决定T+/-N';
comment on column "JXS_CASHFLOWCFG"."HOLIDAYSD" is '节假日';
comment on column "JXS_CASHFLOWCFG"."CREATE_TIME" is '创建时间';
comment on column "JXS_CASHFLOWCFG"."CREATE_DEPT" is '创建部门';
comment on column "JXS_CASHFLOWCFG"."UPDATE_TIME" is '更新时间';
comment on column "JXS_CASHFLOWCFG"."UPDATE_USER" is '更新用户';
comment on column "JXS_CASHFLOWCFG"."CREATE_USER" is '创建用户';


--现金流配置的浮动利率
CREATE TABLE "JXS_CASHFLOWFLOATRATE" 
(	
	"ID" NUMBER(15,0) NOT NULL ENABLE, 
	"BASISCLASS" VARCHAR2(100), 
	"CFID" NUMBER(15,0), 
	"MAXRATE" NUMBER(30,14), 
	"MINRATE" NUMBER(30,14), 
	"RATERESETDATE" DATE, 
	"RULEPARAMS" VARCHAR2(50), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	 PRIMARY KEY ("ID")
);
   
comment on column "JXS_CASHFLOWFLOATRATE"."ID" is '唯一主键';
comment on column "JXS_CASHFLOWFLOATRATE"."BASISCLASS" is '利率基准类';
comment on column "JXS_CASHFLOWFLOATRATE"."CFID" is '现金流ID';
comment on column "JXS_CASHFLOWFLOATRATE"."MAXRATE" is '利率最大值';
comment on column "JXS_CASHFLOWFLOATRATE"."MINRATE" is '利率最小值';
comment on column "JXS_CASHFLOWFLOATRATE"."RATERESETDATE" is '利率重置日期';
comment on column "JXS_CASHFLOWFLOATRATE"."RULEPARAMS" is '规则公式';
comment on column "JXS_CASHFLOWFLOATRATE"."CREATE_TIME" is '创建时间';
comment on column "JXS_CASHFLOWFLOATRATE"."CREATE_DEPT" is '创建部门';
comment on column "JXS_CASHFLOWFLOATRATE"."UPDATE_TIME" is '更新时间';
comment on column "JXS_CASHFLOWFLOATRATE"."UPDATE_USER" is '更新用户';
comment on column "JXS_CASHFLOWFLOATRATE"."CREATE_USER" is '创建用户';



--现金流配置的还本计划
CREATE TABLE "JXS_CASHFLOWPAR" 
(	
	"ID" NUMBER(15,0) NOT NULL ENABLE, 
	"PAYMENTDATE" DATE, 
	"PAYMENTRATE" NUMBER(30,14), 
	"SETTLEDATE" DATE, 
	"TRANTYPE" NUMBER(3,0), 
	"CFID" NUMBER(15,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	 PRIMARY KEY ("ID")
);


comment on column "JXS_CASHFLOWPAR"."ID" is '唯一主键';
comment on column "JXS_CASHFLOWPAR"."PAYMENTDATE" is '还本日期';
comment on column "JXS_CASHFLOWPAR"."PAYMENTRATE" is '还本比例';
comment on column "JXS_CASHFLOWPAR"."SETTLEDATE" is '结转日期';
comment on column "JXS_CASHFLOWPAR"."TRANTYPE" is '交易类型,按面额还本|按数量还本';
comment on column "JXS_CASHFLOWPAR"."CFID" is '现金流ID';
comment on column "JXS_CASHFLOWPAR"."CREATE_TIME" is '创建时间';
comment on column "JXS_CASHFLOWPAR"."CREATE_DEPT" is '创建部门';
comment on column "JXS_CASHFLOWPAR"."UPDATE_TIME" is '更新时间';
comment on column "JXS_CASHFLOWPAR"."UPDATE_USER" is '更新用户';
comment on column "JXS_CASHFLOWPAR"."CREATE_USER" is '创建用户';





--现金流配置的付息计划
CREATE TABLE "JXS_CASHFLOWPAYMENT" 
(	
	"ID" NUMBER(15,0) NOT NULL ENABLE, 
	"CFID" NUMBER(15,0), 
	"PAYDATE" DATE, 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	 PRIMARY KEY ("ID")
);


comment on column "JXS_CASHFLOWPAYMENT"."ID" is '唯一主键';
comment on column "JXS_CASHFLOWPAYMENT"."CFID" is '现金流ID';
comment on column "JXS_CASHFLOWPAYMENT"."PAYDATE" is '付息日期';
comment on column "JXS_CASHFLOWPAYMENT"."CREATE_TIME" is '创建时间';
comment on column "JXS_CASHFLOWPAYMENT"."CREATE_DEPT" is '创建部门';
comment on column "JXS_CASHFLOWPAYMENT"."UPDATE_TIME" is '更新时间';
comment on column "JXS_CASHFLOWPAYMENT"."UPDATE_USER" is '更新用户';
comment on column "JXS_CASHFLOWPAYMENT"."CREATE_USER" is '创建用户';


--现金流配置的利率重置
CREATE TABLE "JXS_CASHFLOWRATE" 
(	
	"ID" NUMBER(15,0) NOT NULL ENABLE, 
	"CFDATE" DATE, 
	"CFRATE" NUMBER(30,14), 
	"CFID" NUMBER(15,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	 PRIMARY KEY ("ID")
);


comment on column "JXS_CASHFLOWRATE"."ID" is '唯一主键';
comment on column "JXS_CASHFLOWRATE"."CFDATE" is '重置日期';
comment on column "JXS_CASHFLOWRATE"."CFRATE" is '利率';
comment on column "JXS_CASHFLOWRATE"."CFID" is '现金流ID';
comment on column "JXS_CASHFLOWRATE"."CREATE_TIME" is '创建时间';
comment on column "JXS_CASHFLOWRATE"."CREATE_DEPT" is '创建部门';
comment on column "JXS_CASHFLOWRATE"."UPDATE_TIME" is '更新时间';
comment on column "JXS_CASHFLOWRATE"."UPDATE_USER" is '更新用户';
comment on column "JXS_CASHFLOWRATE"."CREATE_USER" is '创建用户';



--证券基础信息
CREATE TABLE "JXS_ISSUE_INFO" 
(	
	"CUSIP" VARCHAR2(50) NOT NULL ENABLE, 
	"ACTIVEDATE" DATE, 
	"CASHFLOWCFG" NUMBER(15,0), 
	"CASHFLOWCFG2" NUMBER(15,0), 
	"GLGROUP" NUMBER(10,0), 
	"CONTRACTSIZE" NUMBER(30,2), 
	"ENDPRICE" NUMBER(30,14), 
	"EXPIREDATE" DATE, 
	"FIXINCOMETYPE" NUMBER(2,0), 
	"ISSUETYPE" VARCHAR2(10), 
	"OPTIONTYPE" NUMBER(1,0), 
	"SECURITYID" VARCHAR2(50), 
	"SECURITYNAME" VARCHAR2(100), 
	"STARTPRICE" NUMBER(30,14), 
	"SWAPEXCHANGEIND" NUMBER(10,0), 
	"UNDERCUSIP" VARCHAR2(50), 
	"EXCHANGECODE" VARCHAR2(10),
	"ISSUER" VARCHAR2(10), 
	"RPSECTYPE" NUMBER(10,0), 
	"SOURCEID" NUMBER(15,0), 
	"PRICUR" VARCHAR2(10), 
	"INCOMECUR" VARCHAR2(10), 
	"PRICUR2" VARCHAR2(10), 
	"INCOMECUR2" VARCHAR2(10), 
	"COUNTRYCODE" VARCHAR2(10),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),	
	PRIMARY KEY ("CUSIP")
);

comment on column "JXS_ISSUE_INFO"."CUSIP" is '唯一主键';
comment on column "JXS_ISSUE_INFO"."ACTIVEDATE" is '激活日期';
comment on column "JXS_ISSUE_INFO"."CASHFLOWCFG" is '现金流配置';
comment on column "JXS_ISSUE_INFO"."CASHFLOWCFG2" is '空方现金流配置';
comment on column "JXS_ISSUE_INFO"."GLGROUP" is '总账组 | 关联CoaaGroup表的groupNum字段 ';
comment on column "JXS_ISSUE_INFO"."CONTRACTSIZE" is '票面价值，在期货中则为合约乘数';
comment on column "JXS_ISSUE_INFO"."ENDPRICE" is '到期价';
comment on column "JXS_ISSUE_INFO"."EXPIREDATE" is '证券过期日期,主要用在债券的收益类产品的到期时间 ';
comment on column "JXS_ISSUE_INFO"."FIXINCOMETYPE" is '固收类型';
comment on column "JXS_ISSUE_INFO"."ISSUETYPE" is '证券类型 | 数据字典--证券类型 ';
comment on column "JXS_ISSUE_INFO"."OPTIONTYPE" is '期权类型 | 0:认购期权 1:认沽期权 ';
comment on column "JXS_ISSUE_INFO"."SECURITYID" is '证券代码';
comment on column "JXS_ISSUE_INFO"."SECURITYNAME" is '证券名称 ';
comment on column "JXS_ISSUE_INFO"."STARTPRICE" is '发行价';
comment on column "JXS_ISSUE_INFO"."SWAPEXCHANGEIND" is '在swap交易中，判定前后端是否结转现金的标记;无/不结转, 前端结转，后端结转，前后和后端结转';
comment on column "JXS_ISSUE_INFO"."UNDERCUSIP" is '关联其它证券，用于判断该证券是否被抵押出去,导致不能交易 | 关联IssueInfo表的主键 ';
comment on column "JXS_ISSUE_INFO"."EXCHANGECODE" is '该证券所属交易所编号 | 关联ExchangeCfg表的主键 ';
comment on column "JXS_ISSUE_INFO"."ISSUER" is '发行商公司编号,该证券属于哪个公司发行 | 关联Issuer表的issuerID字段 ';
comment on column "JXS_ISSUE_INFO"."RPSECTYPE" is '自定义证券类型编号, 普通股、优先股等 | 关联RPSecType表的id字段 ';
comment on column "JXS_ISSUE_INFO"."SOURCEID" is '数据源,接口编号,该证券从哪个数据源导入创建 | 关联InterfaceSource表的sourceID字段';
comment on column "JXS_ISSUE_INFO"."PRICUR" is '货币';
comment on column "JXS_ISSUE_INFO"."INCOMECUR" is '收入货币';
comment on column "JXS_ISSUE_INFO"."PRICUR2" is '空方货币';
comment on column "JXS_ISSUE_INFO"."INCOMECUR2" is '空方收入货币';
comment on column "JXS_ISSUE_INFO"."COUNTRYCODE" is '该证券所属国家编号';
comment on column "JXS_ISSUE_INFO"."CREATE_TIME" is '创建时间';
comment on column "JXS_ISSUE_INFO"."CREATE_DEPT" is '创建部门';
comment on column "JXS_ISSUE_INFO"."UPDATE_TIME" is '更新时间';
comment on column "JXS_ISSUE_INFO"."UPDATE_USER" is '更新用户';
comment on column "JXS_ISSUE_INFO"."CREATE_USER" is '创建用户';



--衍生品合同表
CREATE TABLE "JXS_SECCONTRACT" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"CUSIP" VARCHAR2(50), 
	"LONGCASH" NUMBER(30,2), 
	"SHORTCASH" NUMBER(30,2), 
	"LONGCURID" VARCHAR2(10), 
	"SHORTCURID" VARCHAR2(10), 
	"FUTURESTYPE" NUMBER(3,0), 
	"FUTURESTARGET" NUMBER(10,0), 
	"MARGINRATE" NUMBER(30,14), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20), 
	 PRIMARY KEY ("ID")
);
   
comment on column "JXS_SECCONTRACT"."ID" is '唯一主键';
comment on column "JXS_SECCONTRACT"."CUSIP" is '证券ID';
comment on column "JXS_SECCONTRACT"."LONGCASH" is '做多货币金额';
comment on column "JXS_SECCONTRACT"."SHORTCASH" is '做空货币金额';
comment on column "JXS_SECCONTRACT"."LONGCURID" is '做多货币';
comment on column "JXS_SECCONTRACT"."SHORTCURID" is '做空货币';
comment on column "JXS_SECCONTRACT"."FUTURESTYPE" is '期货类型';
comment on column "JXS_SECCONTRACT"."FUTURESTARGET" is '合约标的';
comment on column "JXS_SECCONTRACT"."MARGINRATE" is '保证金';
comment on column "JXS_SECCONTRACT"."CREATE_TIME" is '创建时间';
comment on column "JXS_SECCONTRACT"."CREATE_DEPT" is '创建部门';
comment on column "JXS_SECCONTRACT"."UPDATE_TIME" is '更新时间';
comment on column "JXS_SECCONTRACT"."UPDATE_USER" is '更新用户';
comment on column "JXS_SECCONTRACT"."CREATE_USER" is '创建用户';


--公司行为交易

CREATE TABLE "JXS_SMFDIV" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"CATYPE" NUMBER(10,0), 
	"CUSIP" VARCHAR2(50), 
	"EXDATE" DATE, 
	"LASTUPDATE" DATE, 
	"LTCAPGLRATE" NUMBER(30,14), 
	"PAYDATE" DATE, 
	"RATE" NUMBER(30,14), 
	"RECORDDATE" DATE, 
	"SOURCEID" NUMBER(15,0), 
	"STCAPGLRATE" NUMBER(30,14), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),  
	 PRIMARY KEY ("ID")
);

comment on column "JXS_SMFDIV"."ID" is '唯一主键';
comment on column "JXS_SMFDIV"."CATYPE" is '公司行为类型 | 0:现金股利 8:股票股利 9:拆分 7:Mutual Fund Dividend 13:Stock Rights 11:Conversion 12:Tax Free Exchange 14:Taxable Exchange 16:Tax Free Dividend 17:Return Of Capital ';
comment on column "JXS_SMFDIV"."CUSIP" is '证券编号 | 关联Issue_Info表主键 ';
comment on column "JXS_SMFDIV"."EXDATE" is '除权除息日，发生公司行为的日期 ';
comment on column "JXS_SMFDIV"."LASTUPDATE" is '最后一次更新时间 ';
comment on column "JXS_SMFDIV"."LTCAPGLRATE" is '尚未使用';
comment on column "JXS_SMFDIV"."PAYDATE" is '付款日，公司行为发生后，现金结转日 ';
comment on column "JXS_SMFDIV"."RATE" is '比率';
comment on column "JXS_SMFDIV"."RECORDDATE" is '记录基准日，在除权除息日发生公司行为那一天，需要看以哪天为基准来计算 ';
comment on column "JXS_SMFDIV"."SOURCEID" is '数据来源的接口编号 | 关联 InterfaceSource表主键 ';
comment on column "JXS_SMFDIV"."STCAPGLRATE" is '尚未使用 | For mutual fund dividends, the rate used to determine the portion of the dividend to be considered short-term capital gains or losses. Not used for other dividend types. ';
comment on column "JXS_SMFDIV"."CREATE_TIME" is '创建时间';
comment on column "JXS_SMFDIV"."CREATE_DEPT" is '创建部门';
comment on column "JXS_SMFDIV"."UPDATE_TIME" is '更新时间';
comment on column "JXS_SMFDIV"."UPDATE_USER" is '更新用户';
comment on column "JXS_SMFDIV"."CREATE_USER" is '创建用户';



--投资组合/产品表
CREATE TABLE "JXS_PORTFOLIO_HDR" 
(	
	"PORTFOLIO" VARCHAR2(50) NOT NULL ENABLE, 
	"BOOKNBDAYS" NUMBER(1,0), 
	"CLASSID" NUMBER(10,0), 
	"DEFAULTSECACCT" NUMBER(10,0), 
	"INCEPTIONDATE" DATE, 
	"JZGYFLAG" NUMBER(1,0), 
	"LASTACTIVEDATE" DATE, 
	"LASTGLCLOSE" DATE, 
	"LASTTRANCLOSE" DATE, 
	"CURRENCYCODE" VARCHAR2(10), 
	"LOTSELECTION" NUMBER(1,0), 
	"NAVPRECISION" NUMBER(10,0), 
	"NEXTGLCLOSE" DATE, 
	"NEXTTRANCLOSE" DATE, 
	"NEXTYEALCLOSE" DATE, 
	"PORT_NAME" VARCHAR2(100), 
	"PORTFOLIOTYPE" NUMBER(10,0), 
	"SHORTCODE" VARCHAR2(100), 
	"SHORTNAME" VARCHAR2(100), 
	"SINGLELOT" VARCHAR2(1), 
	"SYZFEFLAG" NUMBER(1,0), 
	"TRADESETTLELEDGER" NUMBER(1,0), 
	"WKNDVALUATIONCLOSE" NUMBER(1,0), 
	"GLGROUP" NUMBER(10,0), 
	"CHARTNUMBER" VARCHAR2(50), 
	"HOLIDAYSD" VARCHAR2(6),
	"MANAGERGROUP" NUMBER(10,0), 
	"PORTFOLIOCLIENT" NUMBER(10,0), 
	"PROVINCE" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),  
	PRIMARY KEY ("PORTFOLIO")
);


comment on column "JXS_PORTFOLIO_HDR"."PORTFOLIO" is '产品/投资组合主键';
comment on column "JXS_PORTFOLIO_HDR"."BOOKNBDAYS" is '如果跳节假日做账,节假日产生的费用或者利息是记在前一天还是下一个做账日 | 0:向后计提   1：向前计提 ';
comment on column "JXS_PORTFOLIO_HDR"."CLASSID" is '指向上一级(父级)投资组合,分级基金使用。没有分级基金则为0 | 关联Portfolio_hdr表主键 ';
comment on column "JXS_PORTFOLIO_HDR"."DEFAULTSECACCT" is '该产品默认证券账户 | 关联SecAcct表主键 ';
comment on column "JXS_PORTFOLIO_HDR"."INCEPTIONDATE" is '基金成立日 ';
comment on column "JXS_PORTFOLIO_HDR"."JZGYFLAG" is '是否净值归一';
comment on column "JXS_PORTFOLIO_HDR"."LASTACTIVEDATE" is '上一个激活日期 ';
comment on column "JXS_PORTFOLIO_HDR"."LASTGLCLOSE" is '上一个总账关帐日 ';
comment on column "JXS_PORTFOLIO_HDR"."LASTTRANCLOSE" is '上一个交易关帐日 ';
comment on column "JXS_PORTFOLIO_HDR"."CURRENCYCODE" is '货币编号,该投资组合的基准货币 | 关联FX_CURRENCY表的ID字段 ';
comment on column "JXS_PORTFOLIO_HDR"."LOTSELECTION" is '尚未使用。如果选择多lot计算,产用哪种计算方式 | 1.移动加权平均 2.先进先出 ';
comment on column "JXS_PORTFOLIO_HDR"."NAVPRECISION" is '单位净值的精度 ';
comment on column "JXS_PORTFOLIO_HDR"."NEXTGLCLOSE" is '下一个总账关帐日';
comment on column "JXS_PORTFOLIO_HDR"."NEXTTRANCLOSE" is '下一个交易关帐日';
comment on column "JXS_PORTFOLIO_HDR"."NEXTYEALCLOSE" is '下一个年度关帐日';
comment on column "JXS_PORTFOLIO_HDR"."PORT_NAME" is '投资组合名称';
comment on column "JXS_PORTFOLIO_HDR"."PORTFOLIOTYPE" is '基金类型 | 1:公募基金 2：私募基金 3:专户 4:信托计划 5:银行理财 6:保险理财 7:QDII ';
comment on column "JXS_PORTFOLIO_HDR"."SHORTCODE" is '产品编号';
comment on column "JXS_PORTFOLIO_HDR"."SHORTNAME" is '产品简称';
comment on column "JXS_PORTFOLIO_HDR"."SINGLELOT" is '是否为单lot计算 | Y:单lot N:多lot ';
comment on column "JXS_PORTFOLIO_HDR"."SYZFEFLAG" is '是否收益转份额';
comment on column "JXS_PORTFOLIO_HDR"."TRADESETTLELEDGER" is '入账日,一般选1 | 1:交易日 2:结算日 ';
comment on column "JXS_PORTFOLIO_HDR"."WKNDVALUATIONCLOSE" is '周末估值的选项,目前一般选1 | 1:无 2:仅限周末 3：仅限季末 4：用户定义周末 ';
comment on column "JXS_PORTFOLIO_HDR"."GLGROUP" is '总账组编号,在一些投资组合相关的凭证需要用到，例如申购赎回 | 关联CoaaGroup表主键 ';
comment on column "JXS_PORTFOLIO_HDR"."CHARTNUMBER" is '投资组合选定一个会计科目表 | 关联COAAHDR表主键 ';
comment on column "JXS_PORTFOLIO_HDR"."HOLIDAYSD" is '投资组合的节假日配置 | 关联HolidaySD表主键 ';
comment on column "JXS_PORTFOLIO_HDR"."MANAGERGROUP" is '默认经理组 | 关联ManagerGroup表主键 ';
comment on column "JXS_PORTFOLIO_HDR"."PORTFOLIOCLIENT" is '客户编码，该投资组合属于哪个公司 | 关联Portfolio_Client表主键 ';
comment on column "JXS_PORTFOLIO_HDR"."PROVINCE" is '该产品发行在哪个省份 | 关联Province表主键 ';
comment on column "JXS_PORTFOLIO_HDR"."CREATE_TIME" is '创建时间';
comment on column "JXS_PORTFOLIO_HDR"."CREATE_DEPT" is '创建部门';
comment on column "JXS_PORTFOLIO_HDR"."UPDATE_TIME" is '更新时间';
comment on column "JXS_PORTFOLIO_HDR"."UPDATE_USER" is '更新用户';
comment on column "JXS_PORTFOLIO_HDR"."CREATE_USER" is '创建用户';



--产品/组合接口映射表
CREATE TABLE "JXS_PORTFOLIOINTERFACEMAP" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"DEFAULTIMPORTTYPE" NUMBER(1,0), 
	"OPERATIONSTEP" NUMBER(10,0), 
	"RPSECTYPE" NUMBER(10,0), 
	"INTERFACESOURCEID" NUMBER(10,0), 
	"PORTFOLIO" VARCHAR2(50), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);

comment on column "JXS_PORTFOLIOINTERFACEMAP"."ID" is '唯一主键';
comment on column "JXS_PORTFOLIOINTERFACEMAP"."DEFAULTIMPORTTYPE" is '目前使用在数据导出功能,决定是否要在关帐后自动导出数据 | 1:不自动导出 2:自动导出 ';
comment on column "JXS_PORTFOLIOINTERFACEMAP"."OPERATIONSTEP" is '该配置在哪个操作步骤中使用 | 1:证券文件处理 2:行情数据处理 3:汇率数据处理 4:投资组合交易 5:公司行为交易 6:外汇交易操作 7.申购赎回交易 8.现金交易操作 501:到期自动作业 502:交易关帐 503:每日会计作业 504:每月会计作业 505:基金净值计算 506:会计总账关帐 数据字典--工作流步骤 ';
comment on column "JXS_PORTFOLIOINTERFACEMAP"."RPSECTYPE" is '当接口是交易配置的时候使用,匹配交易证券的证券类型(普通股、优先股等) | 关联RPSecType表主键 ';
comment on column "JXS_PORTFOLIOINTERFACEMAP"."INTERFACESOURCEID" is '接口数据源编号 | 关联InterfaceSource表主键 ';
comment on column "JXS_PORTFOLIOINTERFACEMAP"."PORTFOLIO" is '投资组合系统编号,关联Portfolio_Hdr表的portfolio字段 ';
comment on column "JXS_PORTFOLIOINTERFACEMAP"."CREATE_TIME" is '创建时间';
comment on column "JXS_PORTFOLIOINTERFACEMAP"."CREATE_DEPT" is '创建部门';
comment on column "JXS_PORTFOLIOINTERFACEMAP"."UPDATE_TIME" is '更新时间';
comment on column "JXS_PORTFOLIOINTERFACEMAP"."UPDATE_USER" is '更新用户';
comment on column "JXS_PORTFOLIOINTERFACEMAP"."CREATE_USER" is '创建用户';



--工作流状态表
CREATE TABLE "JXJ_WORKFLOWFACTORY" 
(	
	"ID" NUMBER(15,0) NOT NULL ENABLE, 
	"ENDTIME" DATE, 
	"FAILEDNUM" NUMBER(10,0), 
	"LEDGERDATE" DATE, 
	"STARTTIME" DATE, 
	"STATUS" NUMBER(1,0), 
	"SUCCESSNUM" NUMBER(15,0), 
	"WFSTEP" NUMBER(15,0), 
	"USERID" NUMBER(10,0), 
	"PORTFOLIO" VARCHAR2(50), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20), 
	PRIMARY KEY ("ID")
);

comment on column "JXJ_WORKFLOWFACTORY"."ID" is '唯一主键';
comment on column "JXJ_WORKFLOWFACTORY"."ENDTIME" is '结束时间';
comment on column "JXJ_WORKFLOWFACTORY"."FAILEDNUM" is '失败数量';
comment on column "JXJ_WORKFLOWFACTORY"."LEDGERDATE" is '做帐日期';
comment on column "JXJ_WORKFLOWFACTORY"."STARTTIME" is '开始时间';
comment on column "JXJ_WORKFLOWFACTORY"."STATUS" is '成功与否 ';
comment on column "JXJ_WORKFLOWFACTORY"."SUCCESSNUM" is '成功数量';
comment on column "JXJ_WORKFLOWFACTORY"."WFSTEP" is '工作流步骤 | 1:证券文件处理 2:行情数据处理 3:汇率数据处理 4:投资组合交易 5:公司行为交易 6:外汇交易操作 7.申购赎回交易 8.现金交易操作 501:到期自动作业 502:交易关帐 503:每日会计作业 504:每月会计作业 505:基金净值计算 506:会计总账关帐 数据字典--工作流步骤 ';
comment on column "JXJ_WORKFLOWFACTORY"."USERID" is '操作的用户编号 ';
comment on column "JXJ_WORKFLOWFACTORY"."PORTFOLIO" is '投资组合系统编号';
comment on column "JXJ_WORKFLOWFACTORY"."CREATE_TIME" is '创建时间';
comment on column "JXJ_WORKFLOWFACTORY"."CREATE_DEPT" is '创建部门';
comment on column "JXJ_WORKFLOWFACTORY"."UPDATE_TIME" is '更新时间';
comment on column "JXJ_WORKFLOWFACTORY"."UPDATE_USER" is '更新用户';
comment on column "JXJ_WORKFLOWFACTORY"."CREATE_USER" is '创建用户';


--现金账户表
CREATE TABLE "JXB_BK_CASH_ACCT" 
(	
	"BK_ACCT_NO" NUMBER(10,0) NOT NULL ENABLE, 
	"ACCOUNTTYPE" NUMBER(1,0), 
	"BANKDESC" VARCHAR2(255), 
	"CASHGLCODE" VARCHAR2(50), 
	"CASHIER" VARCHAR2(50), 
	"CASHMANAGER" VARCHAR2(50), 
	"CASHREALFXGNLS" VARCHAR2(50), 
	"CASHTRUNCATIONGAIN" VARCHAR2(50), 
	"CASHTRUNCATIONLOSS" VARCHAR2(50), 
	"CASHUNREALFXGNLS" VARCHAR2(50), 
	"CASHUNREALFXOFFSET" VARCHAR2(50), 
	"DEFAULTOFFSET" VARCHAR2(50), 
	"DEFAULTPORT" NUMBER(10,0), 
	"EXTERNALACCTNUMBER" VARCHAR2(100), 
	"INTCSHFLWMASTERREC" NUMBER(10,0), 
	"INTERESTACCOFFSET" VARCHAR2(50), 
	"INTERESTACCRUED" VARCHAR2(50), 
	"INTRCVPAYGL" VARCHAR2(50), 
	"INTREALFXGNLS" VARCHAR2(50), 
	"INTRECVGL" VARCHAR2(50), 
	"INTTOLERANCEGL" VARCHAR2(50), 
	"INTUNREALFXGNLS" VARCHAR2(50), 
	"INTUNREALFXOFFSET" VARCHAR2(50), 
	"LAST_BAL_DATE" DATE, 
	"LAST_BALANCE" NUMBER(30,2),
	"CURRENCYCODE" VARCHAR2(10), 
	"STTLMNTCURRDEFAULT" VARCHAR2(6), 	
	"LASTCLOSE" DATE, 
	"NEXTCLOSE" DATE, 
	"ODACCRUED" VARCHAR2(50), 
	"ODACCRUEDOFFSET" VARCHAR2(50), 
	"ODCSHFLWMASTERREC" NUMBER(10,0), 
	"ODINDICATOR" VARCHAR2(50), 
	"ODPAIDGL" VARCHAR2(50), 
	"ODRCVPAYGL" VARCHAR2(50), 
	"ODREALFXGNLS" VARCHAR2(50), 
	"ODTOLERANCEGL" VARCHAR2(50), 
	"ODUNREALFXGNLS" VARCHAR2(50), 
	"ODUNREALFXOFFSET" VARCHAR2(50), 
	"OMNIBUSINDICATOR" VARCHAR2(50), 
	"PORTBALINDICATOR" VARCHAR2(50), 
	"REASON" NUMBER(10,0), 
	"SEATNUMBER" VARCHAR2(50), 
	"STATUS" NUMBER(10,0), 
	"STRUCTURE" NUMBER(10,0), 
	"BANKID" VARCHAR2(50),
	"PORTFOLIO" VARCHAR2(50), 
	"SECACCTCODE" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20), 
	PRIMARY KEY ("BK_ACCT_NO")
);

COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."BK_ACCT_NO" IS '现金账户表主键';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."ACCOUNTTYPE" IS '尚未使用。在国外是区分这是托管账户还是现金账户。国内目前使用证券账户代替了托管账户';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."BANKDESC" IS '该银行账户的描述';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."CASHGLCODE" IS '现金(现金账户)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."CASHIER" IS '尚未使用。A user-defined cashier code for the account';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."CASHMANAGER" IS '尚未使用。A user-defined code that refers to the cash manager for the account';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."CASHREALFXGNLS" IS '现金(已实现外汇损益)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."CASHTRUNCATIONGAIN" IS '现金(截尾收益)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."CASHTRUNCATIONLOSS" IS '现金(截尾损益)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."CASHUNREALFXGNLS" IS '现金(未实现外汇损益)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."CASHUNREALFXOFFSET" IS '现金(未实现外汇损益冲销)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."DEFAULTOFFSET" IS '现金(默认冲销)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."DEFAULTPORT" IS '尚未使用。The number of the lead portfolio for this account. By default, this portfolio carries the entries for fees, interest and unapplied cash.';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."EXTERNALACCTNUMBER" IS '预留字段,目前和BankDesc字段功能一样';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."INTCSHFLWMASTERREC" IS '银行利息编号,如果不配置现金利息,则为0。关联Bk_inte_acct表的Bk_inte_id字段';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."INTERESTACCOFFSET" IS '利息(应计利息冲销)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."INTERESTACCRUED" IS '利息(应计利息)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."INTRCVPAYGL" IS '利息(应收应付)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."INTREALFXGNLS" IS '利息(已实现外汇损益)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."INTRECVGL" IS '利息(已收利息)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."INTTOLERANCEGL" IS '利息(限度)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."INTUNREALFXGNLS" IS '利息(未实现外汇损益)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."INTUNREALFXOFFSET" IS '利息(未实现外汇损益冲销)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."LAST_BAL_DATE" IS '最后一次现金更新日期';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."LAST_BALANCE" IS '账户现金余额';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."LASTCLOSE" IS '上一个银行现金关帐日';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."NEXTCLOSE" IS '下一个银行现金关帐日';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."ODACCRUED" IS '透支(应计利息)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."ODACCRUEDOFFSET" IS '透支(应计利息冲销)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."ODCSHFLWMASTERREC" IS '尚未使用。This table contains records that outline the cash flow characteristics of bank accounts';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."ODINDICATOR" IS '尚未使用。A Y/N flag indicating whether general ledger accounts exist for recording overdraft interest.';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."ODPAIDGL" IS '透支(已付透支)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."ODRCVPAYGL" IS '透支(应收应付)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."ODREALFXGNLS" IS '透支(未实现外汇损益)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."ODTOLERANCEGL" IS '透支(限度)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."ODUNREALFXGNLS" IS '透支(未实现外汇损益)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."ODUNREALFXOFFSET" IS '透支(未实现外汇损益冲销)科目';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."OMNIBUSINDICATOR" IS 'A Y/N flag indicating whether this account recognizes omnibus (block) trades.';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."PORTBALINDICATOR" IS 'A Y/N flag indicating whether this bank account maintains cash balance on the portfolio level.';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."REASON" IS '尚未使用。A code that refers to the type of custodian arrangements that pertain to the account (e.g., state requirement or cash only). 1:Regular 2:State Requirement 3:State Dep 4:Cash Only';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."SEATNUMBER" IS '席位编号';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."STATUS" IS '是否还在使用, 0为已经失效, 1为在使用中';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."STRUCTURE" IS '尚未使用。A code indicating whether the account is of the trust or linked cash structure type. 1.Trust Type 2.Linked cash';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."BANKID" IS '关联银行总表bkinfo表的bankid字段 ';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."PORTFOLIO" IS '关联portfolio_hdr表的ID字段';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."SECACCTCODE" IS '关联证券账户表SecAcct的acctID字段';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."CREATE_DEPT" IS '创建部门';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."UPDATE_USER" IS '更新用户';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."CREATE_USER" IS '创建用户';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."CURRENCYCODE" IS '货币代码,关联FX_CURRENCY表的ID字段';
COMMENT ON COLUMN "JXB_BK_CASH_ACCT"."STTLMNTCURRDEFAULT" IS '默认结算货币编码。关联FX_CURRENCY表的ID字段';



--交易基础信息表
CREATE TABLE "JXT_TRAN_BASIC" 
(	
	"TANUM" VARCHAR2(255) NOT NULL ENABLE,
	"TRADEDATE" DATE, 
	"SETTLEDATE" DATE,
	"LEDGERDATE" DATE, 
	"PORTFOLIO" VARCHAR2(50), 
	"CUSIP" VARCHAR2(50),
	"TRANTYPE" NUMBER(3,0),
	"POST_STATUS" VARCHAR2(1), 
	"AMOUNT" NUMBER(30,4), 
	"PRICE" NUMBER(30,14),
	"PRINCIPAL" NUMBER(30,2), 
	"CASH" NUMBER(30,2), 
	"FXRATE" NUMBER(30,14), 
	"SECACCTCODE" NUMBER(10,0), 
	"BK_CASHACCT_NO" NUMBER(15,0), 
	"CURRENCYCODE" VARCHAR2(10), 
	"SETTLEFXRATE" NUMBER(30,14), 
	"SETTLECURRENCY" VARCHAR2(10), 
	"SETTLECASH" NUMBER(30,2), 
	"INTATMAT" NUMBER(30,2), 
	"SETALLINTATMATFLAG" NUMBER(1,0), 
	"ALTDATE" DATE, 
	"ALTRATE" NUMBER(30,14), 
	"SETTLE_STATUS" VARCHAR2(1), 
	"VALUATIONTYPE" NUMBER(1,0), 
	"HILOCODE" NUMBER(10,0), 
	"SPECIFICLOTID" NUMBER(10,0),
	"MANAGERCODE" NUMBER(10,0), 
	"BROKER" VARCHAR2(10), 
	"CONTRACT" VARCHAR2(255), 
	"TRADEMARKET" NUMBER(1,0),
	"TRANDESC" VARCHAR2(255),
	"GLGROUP" NUMBER(10,0), 
	"GLACCT" VARCHAR2(100), 
	"DIVBASICDATE" DATE, 
	"PORTFOLIO2" VARCHAR2(50), 
	"CUSIP2" VARCHAR2(50), 
	"CURRENCYCODE2" VARCHAR2(10), 
	"AMOUNT2" NUMBER(30,4), 
	"PRICE2" NUMBER(30,14),
	"PRINCIPAL2" NUMBER(30,2), 
	"CASH2" NUMBER(30,2), 
	"FXRATE2" NUMBER(30,14), 
	"SETTLEDATE2" DATE, 
	"SECACCTCODE2" NUMBER(10,0), 
	"BK_CASHACCT_NO2" NUMBER(15,0),
	"SETTLECURRENCY2" VARCHAR2(10), 	
	"SETTLEFXRATE2" NUMBER(30,14), 
	"SETTLECASH2" NUMBER(30,2), 
	"INTATMAT2" NUMBER(30,2), 
	"SETALLINTATMATFLAG2" NUMBER(1,0), 
	"IMPORTTYPE" NUMBER(1,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	 PRIMARY KEY ("TANUM")
);

CREATE INDEX "TRANBASIC_INDEX1" ON "JXT_TRAN_BASIC" ("POST_STATUS", "TRADEDATE");
CREATE INDEX "TRANBASIC_INDEX2" ON "JXT_TRAN_BASIC" ("POST_STATUS", "TRADEDATE", "TANUM");
CREATE INDEX "TRANBASIC_INDEX3" ON "JXT_TRAN_BASIC" ("TRANTYPE", "TANUM");


comment on column "JXT_TRAN_BASIC"."TANUM" is '交易编号';
comment on column "JXT_TRAN_BASIC"."TRADEDATE" is '交易日期';
comment on column "JXT_TRAN_BASIC"."SETTLEDATE" is '结算日期';
comment on column "JXT_TRAN_BASIC"."LEDGERDATE" is '入账日期';
comment on column "JXT_TRAN_BASIC"."PORTFOLIO" is '投资组合系统编号';
comment on column "JXT_TRAN_BASIC"."PORTFOLIO2" is '空方投资组合系统编号';
comment on column "JXT_TRAN_BASIC"."CUSIP" is '证券编号';
comment on column "JXT_TRAN_BASIC"."CUSIP2" is '空方证券编号';
comment on column "JXT_TRAN_BASIC"."CURRENCYCODE" is '交易货币编号';
comment on column "JXT_TRAN_BASIC"."CURRENCYCODE2" is '空方交易货币编号';
comment on column "JXT_TRAN_BASIC"."SETTLECURRENCY" is '结算货币编号';
comment on column "JXT_TRAN_BASIC"."SETTLECURRENCY2" is '空方结算货币编号';
comment on column "JXT_TRAN_BASIC"."ALTDATE" is '收益类理财交易的下一个结息日期';
comment on column "JXT_TRAN_BASIC"."ALTRATE" is '收益类理财交易使用的收益利率';
comment on column "JXT_TRAN_BASIC"."AMOUNT" is '交易数量';
comment on column "JXT_TRAN_BASIC"."AMOUNT2" is '空方交易数量';
comment on column "JXT_TRAN_BASIC"."CASH" is '交易金额，包括费用、利息等 ';
comment on column "JXT_TRAN_BASIC"."CASH2" is '空方交易金额';
comment on column "JXT_TRAN_BASIC"."CONTRACT" is '合同编号';
comment on column "JXT_TRAN_BASIC"."DIVBASICDATE" is '股权登记日';
comment on column "JXT_TRAN_BASIC"."FXRATE" is '交易汇率';
comment on column "JXT_TRAN_BASIC"."FXRATE2" is '空方交易汇率';
comment on column "JXT_TRAN_BASIC"."GLGROUP" is '总账组，在换汇等交易使用 | 关联CoaaGroup表主键 ';
comment on column "JXT_TRAN_BASIC"."GLACCT" is '科目号，用于现金冲销等交易 | 关联Coaa表主键 ';
comment on column "JXT_TRAN_BASIC"."HILOCODE" is '用什么方法卖出存在的lot。参考 LotSelectionType';
comment on column "JXT_TRAN_BASIC"."INTATMAT" is '债券等带入的应收/应付利息 ';
comment on column "JXT_TRAN_BASIC"."INTATMAT2" is '空方利息';
comment on column "JXT_TRAN_BASIC"."POST_STATUS" is '交易是否已经提交，提交后数据将进入tran和tran_sum表计算 | P:已提交 D:已删除 N:未提交 ';
comment on column "JXT_TRAN_BASIC"."PRICE" is '交易价格';
comment on column "JXT_TRAN_BASIC"."PRICE2" is '空方交易价格';
comment on column "JXT_TRAN_BASIC"."PRINCIPAL" is '交易成本，即价格*数量 ';
comment on column "JXT_TRAN_BASIC"."PRINCIPAL2" is '空方交易成本';
comment on column "JXT_TRAN_BASIC"."SETTLECASH" is '结算金额';
comment on column "JXT_TRAN_BASIC"."SETTLECASH2" is '结算汇率';
comment on column "JXT_TRAN_BASIC"."SETTLEFXRATE" is '结算汇率';
comment on column "JXT_TRAN_BASIC"."SETTLEFXRATE2" is '空方结算汇率';
comment on column "JXT_TRAN_BASIC"."SETTLE_STATUS" is '尚未使用';
comment on column "JXT_TRAN_BASIC"."SETTLEDATE2" is '空方结转日期,对于换汇、现金划拨等交易';
comment on column "JXT_TRAN_BASIC"."SPECIFICLOTID" is '如果hiloCode选择了特定，则在这里指定哪个lot';
comment on column "JXT_TRAN_BASIC"."TRADEMARKET" is '交易场所 | 1:一级市场 2：二级市场';
comment on column "JXT_TRAN_BASIC"."TRANDESC" is '交易描述，在定存、回购等交易，作为存单交易号数据存储字段 ';
comment on column "JXT_TRAN_BASIC"."TRANTYPE" is '交易类型,股票买入(100),股票卖出(200)等 | 数据字典--交易类型';
comment on column "JXT_TRAN_BASIC"."VALUATIONTYPE" is '估值类型';
comment on column "JXT_TRAN_BASIC"."BK_CASHACCT_NO" is '银行现金账户 | 关联Bk_cash_acct表主键 ';
comment on column "JXT_TRAN_BASIC"."BK_CASHACCT_NO2" is '空方银行现金账户';
comment on column "JXT_TRAN_BASIC"."BROKER" is '该交易使用的券商编码 | 关联Broker表主键 ';
comment on column "JXT_TRAN_BASIC"."MANAGERCODE" is '交易的基金经理 | 关联Manager表主键 ';
comment on column "JXT_TRAN_BASIC"."SECACCTCODE" is '证券交易账户 | 关联SecAcct表主键 ';
comment on column "JXT_TRAN_BASIC"."SECACCTCODE2" is '空方证券账户';
comment on column "JXT_TRAN_BASIC"."IMPORTTYPE" is '导入类型。1. 手工导入 2. 接口导入 3. 自动导入 比如自动结息等交易';
comment on column "JXT_TRAN_BASIC"."CREATE_TIME" is '创建时间';
comment on column "JXT_TRAN_BASIC"."CREATE_DEPT" is '创建部门';
comment on column "JXT_TRAN_BASIC"."UPDATE_TIME" is '更新时间';
comment on column "JXT_TRAN_BASIC"."UPDATE_USER" is '更新用户';
comment on column "JXT_TRAN_BASIC"."CREATE_USER" is '创建用户';
comment on column "JXT_TRAN_BASIC"."SETALLINTATMATFLAG" is '是否结清利息';
comment on column "JXT_TRAN_BASIC"."SETALLINTATMATFLAG2" is '是否结清空方利息';


--交易表
CREATE TABLE "JXT_TRAN" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"TANUM" VARCHAR2(50),
	"PORTFOLIO" VARCHAR2(50),
	"CUSIP" VARCHAR2(50),
	"LOTNUM" NUMBER(10,0), 
	"TRANNUM" NUMBER(10,0), 	
	"TRANTYPE" NUMBER(3,0),
	"TRADE_DATE" DATE,
	"SETTLE_DATE" DATE, 
	"LEDGER_DATE" DATE, 
	"AMOUNT" NUMBER(30,4), 
	"AMOUNT2" NUMBER(30,4), 
	"G_COST" NUMBER(30,2), 
	"GCOST2" NUMBER(30,2), 
	"G_COSTFUNC" NUMBER(30,2), 
	"GCOSTFUNC2" NUMBER(30,2), 
	"G_ORIGINALAMT" NUMBER(30,4), 
	"GORIGINALAMT2" NUMBER(30,4), 
	"INC_FXRATE" NUMBER(30,14), 
	"INCFXRATE2" NUMBER(30,14), 
	"INTPS" NUMBER(30,2), 
	"SETALLINTPSFLAG" NUMBER(1,0), 
	"LSINDICATOR" NUMBER(1,0), 
	"MANAGERID" NUMBER(10,0),  
	"PRIN_FXRATE" NUMBER(30,14), 
	"PRINFXRATE2" NUMBER(30,14),
	"PROCEEDS" NUMBER(30,2), 
	"PROCEEDS2" NUMBER(30,2), 
	"SECACCT" NUMBER(10,0),   
	"VALUATIONTYPE" NUMBER(1,0),  
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),  
	PRIMARY KEY ("ID")
);

CREATE INDEX "TRAN_INDEX1" ON "JXT_TRAN" ("PORTFOLIO", "CUSIP");
CREATE INDEX "TRAN_INDEX2" ON "JXT_TRAN" ("PORTFOLIO", "CUSIP", "LOTNUM");
CREATE INDEX "TRAN_INDEX3" ON "JXT_TRAN" ("PORTFOLIO", "CUSIP", "TRADE_DATE");
CREATE INDEX "TRAN_INDEX4" ON "JXT_TRAN" ("PORTFOLIO", "CUSIP", "LEDGER_DATE");
CREATE INDEX "TRAN_INDEX5" ON "JXT_TRAN" ("PORTFOLIO", "CUSIP", "LOTNUM", "LEDGER_DATE");
CREATE INDEX "TRAN_INDEX6" ON "JXT_TRAN" ("PORTFOLIO", "CUSIP", "SETTLE_DATE");



comment on column "JXT_TRAN"."ID" is '唯一主键';
comment on column "JXT_TRAN"."CUSIP" is '投资组合系统编号,关联Issue_info表主键 ';
comment on column "JXT_TRAN"."PORTFOLIO" is '投资组合系统编号,关联Portfolio_Hdr表主键 ';
comment on column "JXT_TRAN"."TANUM" is '交易编号,关联Tran_basic表主键 ';
comment on column "JXT_TRAN"."LOTNUM" is '同一个产品投资同一个证券,因为证券账户、基金经理等不同，区分头寸的标记。具体算法根据标交易的不同而不同 | 关联Tran_Basic表的lot字段 ';
comment on column "JXT_TRAN"."TRANNUM" is '同一个LOT下的交易编号';
comment on column "JXT_TRAN"."TRANTYPE" is '交易类型 | 数据字典--交易类型 ';
comment on column "JXT_TRAN"."TRADE_DATE" is '交易日期';
comment on column "JXT_TRAN"."SETTLE_DATE" is '结转日期';
comment on column "JXT_TRAN"."LEDGER_DATE" is '记账日期';
comment on column "JXT_TRAN"."AMOUNT" is '数量';
comment on column "JXT_TRAN"."AMOUNT2" is '空方数量';
comment on column "JXT_TRAN"."G_COST" is '证券交易的原币成本；在卖出交易时，计算卖出数量占买入成本的金额 ';
comment on column "JXT_TRAN"."GCOST2" is '空方成本';
comment on column "JXT_TRAN"."G_COSTFUNC" is '证券交易的本币成本';
comment on column "JXT_TRAN"."GCOSTFUNC2" is '空方本币成本';
comment on column "JXT_TRAN"."G_ORIGINALAMT" is '证券交易的数量';
comment on column "JXT_TRAN"."GORIGINALAMT2" is '空方数量';
comment on column "JXT_TRAN"."INC_FXRATE" is '收入汇率';
comment on column "JXT_TRAN"."INCFXRATE2" is '空方收入汇率';
comment on column "JXT_TRAN"."INTPS" is '交易的应收/应付利息 ';
comment on column "JXT_TRAN"."SETALLINTATMATFLAG" is '是否结清利息';
comment on column "JXT_TRAN"."LSINDICATOR" is '标记这是一笔买多交易还是卖空交易 | 1:买多 2:卖空 ';
comment on column "JXT_TRAN"."MANAGERID" is '基金经理 | 关联Manager表主键 ';
comment on column "JXT_TRAN"."PRIN_FXRATE" is '交易汇率';
comment on column "JXT_TRAN"."PRINFXRATE2" is '空方交易汇率';
comment on column "JXT_TRAN"."PROCEEDS" is '卖出交易时的成交金额,数量*价格 ';
comment on column "JXT_TRAN"."PROCEEDS2" is '空方成交金额';
comment on column "JXT_TRAN"."SECACCT" is '证券账户 | 关联Sec_acct表主键 ';
comment on column "JXT_TRAN"."VALUATIONTYPE" is '估值方法';
comment on column "JXT_TRAN"."CREATE_TIME" is '创建时间';
comment on column "JXT_TRAN"."CREATE_DEPT" is '创建部门';
comment on column "JXT_TRAN"."UPDATE_TIME" is '更新时间';
comment on column "JXT_TRAN"."UPDATE_USER" is '更新用户';
comment on column "JXT_TRAN"."CREATE_USER" is '创建用户';



--头寸表

CREATE TABLE "JXT_TRAN_SUM" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"PORTFOLIO" VARCHAR2(50), 
	"CUSIP" VARCHAR2(50),
	"LOTNUM" NUMBER(10,0), 
	"LST_TRANNUM" NUMBER(10,0), 	
	"ALTDATE" DATE, 
	"ALTRATE" NUMBER(30,14), 
	"AMOUNT" NUMBER(30,4), 
	"AMOUNT2" NUMBER(30,4), 
	"FXTRANFLG" NUMBER(1,0), 
	"G_COST" NUMBER(30,2), 
	"GCOST2" NUMBER(30,2), 
	"G_COSTFUNC" NUMBER(30,2), 
	"GCOSTFUNC2" NUMBER(30,2), 
	"LASTACTIVETYDATE" DATE, 
	"LSINDICATOR" NUMBER(1,0), 
	"LST_LEDGERDATE" DATE, 
	"LST_SETTLEDATE" DATE, 
	"LSTTRADE_DATE" DATE, 
	"MANAGERID" NUMBER(10,0), 
	"POSCLOSED" VARCHAR2(1), 
	"SECACCT" NUMBER(10,0), 
	"VALUATIONTYPE" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),   
	PRIMARY KEY ("ID")
);

CREATE INDEX "TRANSUM_INDEX1" ON "JXT_TRAN_SUM" ("PORTFOLIO", "CUSIP");
CREATE INDEX "TRANSUM_INDEX2" ON "JXT_TRAN_SUM" ("PORTFOLIO", "CUSIP", "LOTNUM");


comment on column "JXT_TRAN_SUM"."ID" is '唯一主键';
comment on column "JXT_TRAN_SUM"."PORTFOLIO" is '投资组合系统编号';
comment on column "JXT_TRAN_SUM"."CUSIP" is '证券编号';
comment on column "JXT_TRAN_SUM"."LOTNUM" is '同一个产品投资同一个证券,因为证券账户、基金经理等不同，区分头寸的标记。具体算法根据标交易的不同而不同 | 关联Tran_Basic表的lot字段 ';
comment on column "JXT_TRAN_SUM"."LST_TRANNUM" is '';
comment on column "JXT_TRAN_SUM"."ALTDATE" is '对于债券(摊余成本)、回购等交易，每次买入都会生成一笔新的头寸，altdate记录到期日期 ';
comment on column "JXT_TRAN_SUM"."ALTRATE" is '对于债券(摊余成本)、回购等交易，每次买入都会生成一笔新的头寸，altrate记录实际利率 ';
comment on column "JXT_TRAN_SUM"."AMOUNT" is '头寸数量';
comment on column "JXT_TRAN_SUM"."AMOUNT2" is '空方头寸数量';
comment on column "JXT_TRAN_SUM"."FXTRANFLG" is '是否为换汇/互换交易 | 1:换汇/互换 0:其它交易 ';
comment on column "JXT_TRAN_SUM"."G_COST" is '头寸的原币成本,每钟证券类型有自己的成本计算方法。具体实现在Post类 ';
comment on column "JXT_TRAN_SUM"."GCOST2" is '空方成本';
comment on column "JXT_TRAN_SUM"."G_COSTFUNC" is '头寸的本币成本,每钟证券类型有自己的成本计算方法。具体实现在Post类 ';
comment on column "JXT_TRAN_SUM"."GCOSTFUNC2" is '空方本币成本';
comment on column "JXT_TRAN_SUM"."LASTACTIVETYDATE" is '最后激活的日期在债券里,卖空之后可能还会计息,等计息结束,再通过判断这个日期参数,决定是否关闭交易 ';
comment on column "JXT_TRAN_SUM"."LSINDICATOR" is '该交易是买多还是卖空 | 1:买多 2:卖空 ';
comment on column "JXT_TRAN_SUM"."LST_LEDGERDATE" is '头寸最后一次交易的入账日期 ';
comment on column "JXT_TRAN_SUM"."LST_SETTLEDATE" is '头寸最后一次交易的结算日期 ';
comment on column "JXT_TRAN_SUM"."LSTTRADE_DATE" is '头寸最后一次交易的交易日期';
comment on column "JXT_TRAN_SUM"."MANAGERID" is '基金经理';
comment on column "JXT_TRAN_SUM"."POSCLOSED" is '该头寸是否已被关闭';
comment on column "JXT_TRAN_SUM"."SECACCT" is '证券账户';
comment on column "JXT_TRAN_SUM"."VALUATIONTYPE" is '估值方法';
comment on column "JXT_TRAN_SUM"."CREATE_TIME" is '创建时间';
comment on column "JXT_TRAN_SUM"."CREATE_DEPT" is '创建部门';
comment on column "JXT_TRAN_SUM"."UPDATE_TIME" is '更新时间';
comment on column "JXT_TRAN_SUM"."UPDATE_USER" is '更新用户';
comment on column "JXT_TRAN_SUM"."CREATE_USER" is '创建用户';




--每日头寸表
CREATE TABLE "JXT_TRAN_CLO" 
(	
	"ID" VARCHAR2(50) NOT NULL ENABLE,
	"PORTFOLIO" VARCHAR2(50),	
	"CUSIP" VARCHAR2(50), 
	"LOTNUM" NUMBER(10,0),  
	"CLOSEDATE" DATE, 
	"ACCRUED" NUMBER(30,2), 
	"ALTDATE" DATE, 
	"ALTRATE" NUMBER(30,14), 
	"AMOUNT" NUMBER(30,4), 
	"AMRVALUE" NUMBER(30,2), 
	"CYCLESTARTAMRVALUE" NUMBER(30,2), 
	"GCOST" NUMBER(30,2), 
	"GCOSTFUNC" NUMBER(30,2), 
	"GMKVALUE" NUMBER(30,2), 
	"INCFXRATE" NUMBER(30,14), 
	"LASTACTIVETYDATE" DATE, 
	"LSINDICATOR" NUMBER(1,0), 
	"LSTSETTLEDATE" DATE, 
	"LSTTRANNUM" NUMBER(10,0), 
	"LSTTRADEDATE" DATE, 
	"MANAGERID" NUMBER(10,0), 
	"PRICE" NUMBER(30,14), 
	"PRINFXRATE" NUMBER(30,14), 
	"SECACCT" NUMBER(10,0), 
	"TODAYINTEREST" NUMBER(30,2), 
	"VALUATIONTYPE" NUMBER(1,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20), 
	PRIMARY KEY ("ID")
);

CREATE INDEX "TRANCLO_INDEX1" ON "JXT_TRAN_CLO" ("PORTFOLIO", "CLOSEDATE");
CREATE INDEX "TRANCLO_INDEX2" ON "JXT_TRAN_CLO" ("PORTFOLIO", "CLOSEDATE", "CUSIP");
CREATE INDEX "TRANCLO_INDEX3" ON "JXT_TRAN_CLO" ("PORTFOLIO", "CLOSEDATE", "CUSIP", "LOTNUM");



comment on column "JXT_TRAN_CLO"."ID" is '唯一主键';
comment on column "JXT_TRAN_CLO"."PORTFOLIO" is '投资组合系统编号';
comment on column "JXT_TRAN_CLO"."CUSIP" is '证券编号 ';
comment on column "JXT_TRAN_CLO"."LOTNUM" is '同一个产品投资同一个证券,因为证券账户、基金经理等不同，区分头寸的标记。具体算法根据标交易的不同而不同 | 关联Tran_Basic表的lot字段 ';
comment on column "JXT_TRAN_CLO"."CLOSEDATE" is '关帐日期';
comment on column "JXT_TRAN_CLO"."ACCRUED" is '截至当天，应收利息余额 ';
comment on column "JXT_TRAN_CLO"."ALTDATE" is '如果是债券(摊余成本),回购等单lot交易，altDate下一个到期日 ';
comment on column "JXT_TRAN_CLO"."ALTRATE" is '如果是债券(摊余成本),回购等单lot交易，altrate表示有效利率 ';
comment on column "JXT_TRAN_CLO"."AMOUNT" is '数量';
comment on column "JXT_TRAN_CLO"."AMRVALUE" is '持有到期估值法的当天摊余成本 ';
comment on column "JXT_TRAN_CLO"."CYCLESTARTAMRVALUE" is '本期的期初摊余成本';
comment on column "JXT_TRAN_CLO"."GCOST" is '该头寸的买入原币成本 ';
comment on column "JXT_TRAN_CLO"."GCOSTFUNC" is '该头寸的买入本币成本 ';
comment on column "JXT_TRAN_CLO"."GMKVALUE" is '当天的市值';
comment on column "JXT_TRAN_CLO"."INCFXRATE" is '收入汇率';
comment on column "JXT_TRAN_CLO"."LASTACTIVETYDATE" is '最后激活的日期。在债券里,卖空之后可能还会计息,等计息结束,再通过判断这个日期参数,决定是否关闭交易 ';
comment on column "JXT_TRAN_CLO"."LSINDICATOR" is '判断该头寸是买多还是卖空 | 1:买多 2:卖空 ';
comment on column "JXT_TRAN_CLO"."LSTSETTLEDATE" is '最后结算日';
comment on column "JXT_TRAN_CLO"."LSTTRANNUM" is '头寸里最后一个交易顺序编号，头寸每增加一次交易，交易编号增加1 ';
comment on column "JXT_TRAN_CLO"."LSTTRADEDATE" is '最后一个交易日';
comment on column "JXT_TRAN_CLO"."MANAGERID" is '经理组编号 ';
comment on column "JXT_TRAN_CLO"."PRICE" is '成本价格 ';
comment on column "JXT_TRAN_CLO"."PRINFXRATE" is '汇率';
comment on column "JXT_TRAN_CLO"."SECACCT" is '交易证券账户 ';
comment on column "JXT_TRAN_CLO"."TODAYINTEREST" is '当天利息';
comment on column "JXT_TRAN_CLO"."VALUATIONTYPE" is '估值类型';
comment on column "JXT_TRAN_CLO"."CREATE_TIME" is '创建时间';
comment on column "JXT_TRAN_CLO"."CREATE_DEPT" is '创建部门';
comment on column "JXT_TRAN_CLO"."UPDATE_TIME" is '更新时间';
comment on column "JXT_TRAN_CLO"."UPDATE_USER" is '更新用户';
comment on column "JXT_TRAN_CLO"."CREATE_USER" is '创建用户';


--抵押物表
CREATE TABLE "JXT_TRAN_COLLATERAL" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"CUSIP" VARCHAR2(50), 
	"PORTFOLIO" VARCHAR2(50),
	"TANUM" VARCHAR2(50), 	
	"TRADEDATE" DATE, 
	"SETTLEDATE" DATE, 
	"AMOUNT" NUMBER(30,4), 
	"COLLATERALNUM" NUMBER(10,0), 
	"EFFECTIVEIN" NUMBER(10,0), 
	"LEDGERIN" NUMBER(10,0), 
	"LEDGERREVERSED" NUMBER(10,0), 
	"LOCATION" NUMBER(10,0), 
	"TRANNUM" NUMBER(10,0), 
	"TRANTYPE" NUMBER(3,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),   
	PRIMARY KEY ("ID")
);

comment on column "JXT_TRAN_COLLATERAL"."ID" is '唯一主键';
comment on column "JXT_TRAN_COLLATERAL"."CUSIP" is '投资组合系统编号';
comment on column "JXT_TRAN_COLLATERAL"."PORTFOLIO" is '投资组合系统编号';
comment on column "JXT_TRAN_COLLATERAL"."TANUM" is '交易编号 ';
comment on column "JXT_TRAN_COLLATERAL"."TRADEDATE" is '交易日期';
comment on column "JXT_TRAN_COLLATERAL"."SETTLEDATE" is '结算日期';
comment on column "JXT_TRAN_COLLATERAL"."AMOUNT" is '抵押数量';
comment on column "JXT_TRAN_COLLATERAL"."COLLATERALNUM" is '尚未使用';
comment on column "JXT_TRAN_COLLATERAL"."EFFECTIVEIN" is '尚未使用,生效日期 ';
comment on column "JXT_TRAN_COLLATERAL"."LEDGERIN" is '尚未使用,入账日期 ';
comment on column "JXT_TRAN_COLLATERAL"."LEDGERREVERSED" is '尚未使用,撤帐日期 ';
comment on column "JXT_TRAN_COLLATERAL"."LOCATION" is '抵押物的证券账户 | 关联SecAcct表主键 ';
comment on column "JXT_TRAN_COLLATERAL"."TRANNUM" is '抵押物交易的TranNum，功能类似Tran表的Trannum ';
comment on column "JXT_TRAN_COLLATERAL"."TRANTYPE" is '交易类型 | 数据字典--交易类型 ';
comment on column "JXT_TRAN_COLLATERAL"."CREATE_TIME" is '创建时间';
comment on column "JXT_TRAN_COLLATERAL"."CREATE_DEPT" is '创建部门';
comment on column "JXT_TRAN_COLLATERAL"."UPDATE_TIME" is '更新时间';
comment on column "JXT_TRAN_COLLATERAL"."UPDATE_USER" is '更新用户';
comment on column "JXT_TRAN_COLLATERAL"."CREATE_USER" is '创建用户';





--交易费用定义表
CREATE TABLE "JXT_TRAN_FEE_DEF" 
(	
	"FEETYPEID" NUMBER(15,0) NOT NULL ENABLE, 
	"CASHFLOWID" NUMBER(15,0), 
	"CASHPERCENT" NUMBER(30,14), 
	"COSTGLCOL" NUMBER(10,0), 
	"FEEGLCOL" NUMBER(10,0), 
	"FEENAME" VARCHAR2(100), 
	"FIXEDFLAG" NUMBER(1,0), 
	"ONETIMEFEEFLAG" NUMBER(1,0), 
	"RCPYGLCOL" NUMBER(10,0), 
	"SECPAYGLCOL" NUMBER(10,0), 
	"SHORTNAME" VARCHAR2(255), 
	"STATUS" NUMBER(1,0), 
	"UNCAPITALIZEDPERCENT" NUMBER(30,14), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20), 
	 PRIMARY KEY ("FEETYPEID")
);

comment on column "JXT_TRAN_FEE_DEF"."FEETYPEID" is '唯一主键';
comment on column "JXT_TRAN_FEE_DEF"."CASHFLOWID" is '现金流ID';
comment on column "JXT_TRAN_FEE_DEF"."CASHPERCENT" is '现金结转比例';
comment on column "JXT_TRAN_FEE_DEF"."COSTGLCOL" is '成本总账栏(通过总账栏，证券总账组，找到匹配科目) | 数据字典--总账栏 ';
comment on column "JXT_TRAN_FEE_DEF"."FEEGLCOL" is '费用总账栏(通过总账栏，证券总账组，找到匹配科目) | 数据字典--总账栏 ';
comment on column "JXT_TRAN_FEE_DEF"."FEENAME" is '费用名称';
comment on column "JXT_TRAN_FEE_DEF"."FIXEDFLAG" is '是否固定费用';
comment on column "JXT_TRAN_FEE_DEF"."ONETIMEFEEFLAG" is '是否一次性计提';
comment on column "JXT_TRAN_FEE_DEF"."RCPYGLCOL" is '应收应付总账栏(通过总账栏，证券总账组，找到匹配科目) | 数据字典--总账栏 ';
comment on column "JXT_TRAN_FEE_DEF"."SECPAYGLCOL" is '证券清算款总账栏(通过总账栏，证券总账组，找到匹配科目) | 数据字典--总账栏 ';
comment on column "JXT_TRAN_FEE_DEF"."SHORTNAME" is '费用简称 ';
comment on column "JXT_TRAN_FEE_DEF"."STATUS" is '费用是否有效| 0:无效 1:有效 ';
comment on column "JXT_TRAN_FEE_DEF"."UNCAPITALIZEDPERCENT" is '费用化比例';
comment on column "JXT_TRAN_FEE_DEF"."CREATE_TIME" is '创建时间';
comment on column "JXT_TRAN_FEE_DEF"."CREATE_DEPT" is '创建部门';
comment on column "JXT_TRAN_FEE_DEF"."UPDATE_TIME" is '更新时间';
comment on column "JXT_TRAN_FEE_DEF"."UPDATE_USER" is '更新用户';
comment on column "JXT_TRAN_FEE_DEF"."CREATE_USER" is '创建用户';


--交易费用表
CREATE TABLE "JXT_TRAN_BASIC_FEE" 
(	
	"FEEID" NUMBER(10,0) NOT NULL ENABLE,
	"TANUM" VARCHAR2(50), 
	"FEETYPEID" NUMBER(10,0), 
	"FEECURRENCY" VARCHAR2(10), 	
	"AMTEXCLFROMCASH" NUMBER(30,2), 
	"CAPITALISEDFEEAMT" NUMBER(30,2), 
	"DEFERREDFEEAMT" NUMBER(30,2), 
	"EXPENSEFEEAMT" NUMBER(30,2), 
	"FXRATE" NUMBER(30,14), 
	"RCPYFEEAMT" NUMBER(30,2), 
	"RECORPAY" NUMBER(1,0), 
	"SETTLEDATE" DATE, 
	"TOTALFEEAMT" NUMBER(30,2), 
	"BKCASHACCT" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),   
	PRIMARY KEY ("FEEID")
);


comment on column "JXT_TRAN_BASIC_FEE"."FEEID" is '';
comment on column "JXT_TRAN_BASIC_FEE"."TANUM" is '';
comment on column "JXT_TRAN_BASIC_FEE"."FEETYPEID" is '';
comment on column "JXT_TRAN_BASIC_FEE"."FEECURRENCY" is '';
comment on column "JXT_TRAN_BASIC_FEE"."AMTEXCLFROMCASH" is '尚未使用';
comment on column "JXT_TRAN_BASIC_FEE"."CAPITALISEDFEEAMT" is '资本化的费用，之后还要根据费用成本化比率和费用RCPY比率算出相应的金额 ';
comment on column "JXT_TRAN_BASIC_FEE"."DEFERREDFEEAMT" is '尚未使用 ';
comment on column "JXT_TRAN_BASIC_FEE"."EXPENSEFEEAMT" is '费用化的费用，之后还要根据费用成本化比率和费用RCPY比率算出相应的金额 ';
comment on column "JXT_TRAN_BASIC_FEE"."FXRATE" is '汇率';
comment on column "JXT_TRAN_BASIC_FEE"."RCPYFEEAMT" is '进入应收应付的费用 ';
comment on column "JXT_TRAN_BASIC_FEE"."RECORPAY" is '判定这条数据记录是收入还是支出 | 0:收入 1:支出 ';
comment on column "JXT_TRAN_BASIC_FEE"."SETTLEDATE" is '应收应付费用的结算日期 ';
comment on column "JXT_TRAN_BASIC_FEE"."TOTALFEEAMT" is '总费用 ';
comment on column "JXT_TRAN_BASIC_FEE"."BKCASHACCT" is '银行现金账户，用于结转应收应付费用 | 关联Bk_Cash_Acct表主键 ';
comment on column "JXT_TRAN_BASIC_FEE"."CREATE_TIME" is '创建时间';
comment on column "JXT_TRAN_BASIC_FEE"."CREATE_DEPT" is '创建部门';
comment on column "JXT_TRAN_BASIC_FEE"."UPDATE_TIME" is '更新时间';
comment on column "JXT_TRAN_BASIC_FEE"."UPDATE_USER" is '更新用户';
comment on column "JXT_TRAN_BASIC_FEE"."CREATE_USER" is '创建用户';


--交易费用和交易类型的匹配表
CREATE TABLE "JXT_TRAN_FEE_TYPEMAP" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"ASSETTYPE" VARCHAR2(10), 
	"FEENAME" VARCHAR2(10),
	"TRANFEETYPECODE" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20), 
	 PRIMARY KEY ("ID")
);


comment on column "JXT_TRAN_FEE_TYPEMAP"."ID" is '唯一主键';
comment on column "JXT_TRAN_FEE_TYPEMAP"."ASSETTYPE" is '资产类型';
comment on column "JXT_TRAN_FEE_TYPEMAP"."TRANFEETYPECODE" is '费用类型 | 关联Tran_Fee_Def表主键 ';
comment on column "JXT_TRAN_FEE_TYPEMAP"."CREATE_TIME" is '创建时间';
comment on column "JXT_TRAN_FEE_TYPEMAP"."CREATE_DEPT" is '创建部门';
comment on column "JXT_TRAN_FEE_TYPEMAP"."UPDATE_TIME" is '更新时间';
comment on column "JXT_TRAN_FEE_TYPEMAP"."UPDATE_USER" is '更新用户';
comment on column "JXT_TRAN_FEE_TYPEMAP"."CREATE_USER" is '创建用户';



--现金应收应付表
CREATE TABLE "JXB_BK_CASH_RCPY" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"BLOCKID" NUMBER(10,0), 
	"DBCR" VARCHAR2(1), 
	"EXPDCASHDATE" DATE, 
	"CURR_CODE" VARCHAR2(10),
	"FXORIGBAL" NUMBER(30,2), 
	"FXRATE" NUMBER(30,14), 
	"FXREMAINBAL" NUMBER(30,2), 
	"LASTMATCH" DATE, 
	"LEDGER" DATE, 
	"MISCORIGBAL" NUMBER(10,0), 
	"MISCREMAINBAL" NUMBER(10,0), 
	"ORIGBAL" NUMBER(30,2), 
	"PAYDATE" DATE, 
	"POSTTYPE" NUMBER(3,0), 
	"RCPYDESC" VARCHAR2(255), 
	"REMAINBAL" NUMBER(30,2), 
	"TRANTYPE" NUMBER(3,0), 
	"UORIGBAL" NUMBER(30,4), 
	"UREMAINBAL" NUMBER(30,4), 
	"ZERO" NUMBER(10,0), 
	"BK_ACCT_NO" NUMBER(10,0), 
	"CUSIP" VARCHAR2(50), 
	"FEEID" NUMBER(10,0), 
	"PORTFOLIO" VARCHAR2(50), 
	"TANUM" VARCHAR2(50), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),  
	 PRIMARY KEY ("ID")
);

CREATE INDEX "BKCASHRCPY_INDEX1" ON "JXB_BK_CASH_RCPY" ("PORTFOLIO", "PAYDATE");
CREATE INDEX "BKCASHRCPY_INDEX2" ON "JXB_BK_CASH_RCPY" ("PORTFOLIO", "LEDGER", "PAYDATE");

COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."ID" IS '唯一主键';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."BLOCKID" IS '尚未使用。A system-generated number uniquely identifying the linkage among a futures variation margin receivable/payable, and the related commissions and gains/losses from futures transaction records. Used only for futures variation margin-related records. ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."DBCR" IS '应收应付方向。C:应收 D:应付 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."EXPDCASHDATE" IS '尚未使用。期望的现金结转日期 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."FXORIGBAL" IS '原币的原始金额 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."FXRATE" IS '汇率';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."FXREMAINBAL" IS '原币的剩余金额 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."LASTMATCH" IS '最后一次现金结转日期 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."LEDGER" IS '出账日期 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."CURR_CODE" IS '货币代码';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."MISCORIGBAL" IS '尚未使用。The original difference between the functional settlement amount with rounding applied, and the functional settlement amount with truncation applied ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."MISCREMAINBAL" IS '尚未使用。 The remaining difference between the functional settlement amount with rounding applied, and the functional settlement amount with truncation applied. If the record is matched, the value is zero. Otherwise, contains a unit value of functional currency. ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."ORIGBAL" IS '本币的原始金额 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."PAYDATE" IS '现金结转的日期 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."POSTTYPE" IS '生成现金流的来源类型,证券清算款、费用、收入等 | 数据字典--现金流来源类型 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."RCPYDESC" IS '现金流的描述';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."REMAINBAL" IS '本币的剩余金额';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."TRANTYPE" IS '交易类型,请查看数据字典--证券交易类型 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."UORIGBAL" IS '原币数量';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."UREMAINBAL" IS '剩余未结转数量';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."ZERO" IS '暂未使用,用来判断该现金流是否完全结转 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."BK_ACCT_NO" IS '银行账户编号。关联bk_cash_acct表的BK_ACCT_NO字段 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."CUSIP" IS '如果现金流由证券交易产生,则记录证券编号。关联到Issue_info表的cusip字段 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."FEEID" IS '费用编号。关联tran_basic_fee表的FEEID字段 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."PORTFOLIO" IS '投资组合系统编号,关联Portfolio_Hdr表的portfolio字段 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."TANUM" IS '交易编号。关联到Tran_basic表的tanum字段 ';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."CREATE_DEPT" IS '创建部门';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."UPDATE_USER" IS '更新用户';
COMMENT ON COLUMN "JXB_BK_CASH_RCPY"."CREATE_USER" IS '创建用户';
   



--现金已收已付表
CREATE TABLE "JXB_BK_CASH_FLOW" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"BANKCASHDATE" DATE, 
	"BANKCASHDATE_REV" VARCHAR2(8), 
	"CASHDESC" VARCHAR2(255), 
	"DBCR" VARCHAR2(1), 
	"CURR_CODE" VARCHAR2(10), 
	"FXORIGBAL" NUMBER(30,2), 
	"FXRATE" NUMBER(30,14), 
	"FXREMAINBAL" NUMBER(30,2), 
	"LASTMATCH" DATE, 
	"LEDGER" DATE, 
	"ORIGBAL" NUMBER(30,2), 
	"ORIGPAYDATE" DATE, 
	"REMAINBAL" NUMBER(30,2), 
	"UORIGBAL" NUMBER(30,4), 
	"UREMAINBAL" NUMBER(30,4), 
	"ZERO" NUMBER(10,0), 
	"BK_ACCT_NO" NUMBER(10,0), 
	"CUSIP" VARCHAR2(50), 
	"PORTFOLIO" VARCHAR2(50), 
	"RCPYID" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20), 
	PRIMARY KEY ("ID")
);

  
CREATE INDEX "BKCASHFLOW_INDEX1" ON "JXB_BK_CASH_FLOW" ("PORTFOLIO", "LEDGER");
CREATE INDEX "BKCASHFLOW_INDEX2" ON "JXB_BK_CASH_FLOW" ("BANKCASHDATE", "PORTFOLIO", "ZERO");
CREATE INDEX "BKCASHFLOW_INDEX3" ON "JXB_BK_CASH_FLOW" ("LEDGER", "BK_ACCT_NO", "PORTFOLIO");

COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."ID" IS '主键';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."BANKCASHDATE" IS '现金流发生日期';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."BANKCASHDATE_REV" IS '目前尚未使用,银行的结帐日期 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."CASHDESC" IS '现金流的描述 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."DBCR" IS '标记现金流进出方向。D:流入 C:流出 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."FXORIGBAL" IS '原始原币金额 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."FXRATE" IS '汇率 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."FXREMAINBAL" IS '原币剩余未结转金额 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."LASTMATCH" IS '最后一次现金结转日期';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."LEDGER" IS '出账日期 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."ORIGBAL" IS '原始本币金额 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."ORIGPAYDATE" IS '原始的结转日期 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."REMAINBAL" IS '本币剩余未结转金额 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."UORIGBAL" IS '原币数量 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."UREMAINBAL" IS '剩余未结转数量 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."ZERO" IS '暂未使用,用来判断该现金流是否完全结转 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."BK_ACCT_NO" IS '银行账户编号。关联bk_cash_acct表的BK_ACCT_NO字段 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."CUSIP" IS '证券的系统编号。如果是证券产生的现金流,关联Issue_info的cusip字段 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."PORTFOLIO" IS '投资组合系统编号,关联Portfolio_Hdr表的portfolio字段 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."RCPYID" IS '应收应付编号。关联bk_cash_rcpy表的id字段 ';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."CREATE_DEPT" IS '创建部门';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."UPDATE_USER" IS '更新用户';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."CREATE_USER" IS '创建用户';
COMMENT ON COLUMN "JXB_BK_CASH_FLOW"."CURR_CODE" IS '货币';



 CREATE TABLE "JXB_BK_INFO" 
   (
	"BANKID" VARCHAR2(100) NOT NULL ENABLE, 
	"ADDRESS" VARCHAR2(255), 
	"BANKCODE" VARCHAR2(255), 
	"BANKDESC" VARCHAR2(255), 
	"CONTRACTOR" VARCHAR2(255), 
	"PHONEFAX" VARCHAR2(255), 
	"PHONENUM" VARCHAR2(255), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(255), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(255), 
	"CREATE_USER" VARCHAR2(255), 
	"COUNTRY" VARCHAR2(6),
	 CONSTRAINT "PK_JXB_BANKID" PRIMARY KEY ("BANKID")
   );
   
   
CREATE TABLE "JXS_BROKER" 
   (	
   	"BROKERID" VARCHAR2(10) NOT NULL ENABLE, 
	"ADDRESS" VARCHAR2(255), 
	"BICCODE" VARCHAR2(255), 
	"BROKERCODE" VARCHAR2(255), 
	"BROKERDESC" VARCHAR2(255), 
	"BROKERNAME" VARCHAR2(255), 
	"CONTRACTOR" VARCHAR2(255), 
	"FAXNUM" VARCHAR2(255), 
	"FINCODE" VARCHAR2(255), 
	"PHONENUM" VARCHAR2(255), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(255), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(255), 
	"CREATE_USER" VARCHAR2(255), 
	"COUNTRY" VARCHAR2(6), 
	 PRIMARY KEY ("BROKERID")
   );

CREATE TABLE "JXS_ISSUER" 
(	
"ISSUERID" VARCHAR2(10) NOT NULL ENABLE, 
"ISSUERCODE" VARCHAR2(50), 
"ISSUERNAME" VARCHAR2(255), 
"ISSUERSHORTNAME" VARCHAR2(255), 
"COUNTRY" VARCHAR2(6), 
"UPDATETIME" DATE, 
"RPSECTYPE" NUMBER(10,0), 
"CREATE_TIME" TIMESTAMP (6), 
"CREATE_DEPT" VARCHAR2(50), 
"UPDATE_TIME" TIMESTAMP (6), 
"UPDATE_USER" VARCHAR2(20), 
"CREATE_USER" VARCHAR2(20),
PRIMARY KEY ("ISSUERID")
);

CREATE TABLE "JXS_RPSECTYPE" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"FATHERNODE" NUMBER(10,0), 
	"GROUPLEVEL" NUMBER(5,0), 
	"STATUS" NUMBER(1,0), 
	"TABLECODE" NUMBER(1,0), 
	"TYPENAME" VARCHAR2(100), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);

CREATE TABLE "JXC_COAAGLSECMAP" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"ASSETTYPE" VARCHAR2(10), 
	"RPSECTYPE" NUMBER(10,0) DEFAULT 0,
	"PURPOSE" VARCHAR2(5), 
	"LONGCUR" VARCHAR2(10), 
	"SHORTCUR" VARCHAR2(10), 
	"GLGROUP" NUMBER(5,0),
  	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	 PRIMARY KEY ("ID")
);

COMMENT on TABLE "JXC_COAAGLSECMAP"  is  '证券和总账栏的映射关系';
COMMENT ON COLUMN "JXC_COAAGLSECMAP"."ID" IS '唯一主键';
COMMENT ON COLUMN "JXC_COAAGLSECMAP"."ASSETTYPE" IS '资产类型';
COMMENT ON COLUMN "JXC_COAAGLSECMAP"."RPSECTYPE" IS '自定义证券次级类型';
COMMENT ON COLUMN "JXC_COAAGLSECMAP"."PURPOSE" IS '投资目的';
COMMENT ON COLUMN "JXC_COAAGLSECMAP"."LONGCUR" IS '做多货币';
COMMENT ON COLUMN "JXC_COAAGLSECMAP"."SHORTCUR" IS '做空货币';
COMMENT ON COLUMN "JXC_COAAGLSECMAP"."GLGROUP" IS '总账栏';

ALTER TABLE "JXS_PORTFOLIOINTERFACEMAP" DROP CONSTRAINT "FK_GDXAI42F1BHEWVWGKEMSJ38R3";
ALTER TABLE "JXJ_WORKFLOWFACTORY" DROP CONSTRAINT "FK_F8OBSO2PWTVGFCXRR5DIXEAXR";
ALTER TABLE "JXT_TRAN_BASIC" DROP CONSTRAINT "FK_QWY96ER3U3BMWNETLDOBYG85G";
ALTER TABLE "JXT_TRAN_BASIC" DROP CONSTRAINT "FK_G5LR5NLAC4YTBYXFT50TK3M7T";
ALTER TABLE "JXT_TRAN_BASIC" DROP CONSTRAINT "FK_KOQO3A1KYRI1SUCU42SDMFIFG";
ALTER TABLE "JXT_TRAN_BASIC" DROP CONSTRAINT "FK_11RWNWITCXPWQWDTA7LOWIV0E";
ALTER TABLE "JXT_TRAN_BASIC" DROP CONSTRAINT "FK_OC1DVP2G9S08C4HM1GGM65AKS";
ALTER TABLE "JXT_TRAN_BASIC" DROP CONSTRAINT "FK_6Q2TAE15AL0RAC7U4N5CVNMDC";
ALTER TABLE "JXT_TRAN_BASIC" DROP CONSTRAINT "FK_JSN62O00DMTCNNQ43ABK5DWF6";
ALTER TABLE "JXT_TRAN" DROP CONSTRAINT "TRAN_UNIQUE1";
ALTER TABLE "JXT_TRAN" DROP CONSTRAINT "FK_6TOBKO8Q28PNKBW5SLTP676P4";
ALTER TABLE "JXT_TRAN" DROP CONSTRAINT "FK_40YBD5D0GEHVXHRFQHR2FE0JG";
ALTER TABLE "JXT_TRAN" DROP CONSTRAINT "FK_C4OKSG3M94ON3BWDGJCCRJONH";
ALTER TABLE "JXB_BK_CASH_ACCT" DROP CONSTRAINT "FK_2Q49CU9CA9C0SQP6EIGLWA53M";
ALTER TABLE "JXB_BK_CASH_ACCT" DROP CONSTRAINT "FK_M1IKAQ5SY0YOSRX8B4IE13AJ5";
ALTER TABLE "JXT_TRAN_SUM" DROP CONSTRAINT "TRANSUM_UNIQUE1";
ALTER TABLE "JXT_TRAN_SUM" DROP CONSTRAINT "FK_OA5XDOBRGF7CRYFUNATASKLQA";
ALTER TABLE "JXT_TRAN_COLLATERAL" DROP CONSTRAINT "FK_390YWJ9VF7I71FJKQHVDRXPJC";
ALTER TABLE "JXT_TRAN_COLLATERAL" DROP CONSTRAINT "FK_E6EEWO7HF2LB38TS39CJEQ6W3";
ALTER TABLE "JXT_TRAN_COLLATERAL" DROP CONSTRAINT "FK_QNMVTVP2U9A40PCVQS2QX9B6R";
ALTER TABLE "JXT_TRAN_BASIC_FEE" DROP CONSTRAINT "FK_3RO43I1I8CFYI7UHVR9U15MI";
ALTER TABLE "JXT_TRAN_BASIC_FEE" DROP CONSTRAINT "FK_GL9H1RQVKNOXL044RQSO9TONU";
ALTER TABLE "JXT_TRAN_BASIC_FEE" DROP CONSTRAINT "FK_4MD1XYTR107N5DNWJY8X486FO";
ALTER TABLE "JXT_TRAN_FEE_TYPEMAP" DROP CONSTRAINT "FK_LMTUM3DW5FHDFV4KV7T31FTE2";
ALTER TABLE "JXB_BK_CASH_RCPY" DROP CONSTRAINT "FK_KIF8MS66QCD93ED2RS24OHDBU";
ALTER TABLE "JXB_BK_CASH_RCPY" DROP CONSTRAINT "FK_ORGPWHI59VCRRSQ25B36UW4C7";
ALTER TABLE "JXB_BK_CASH_RCPY" DROP CONSTRAINT "FK_PHICCHGW5HEMAK1V6V59DEA2S";
ALTER TABLE "JXB_BK_CASH_RCPY" DROP CONSTRAINT "FK_9QIP6CEKMDIK8I3LUCDIQKV0S";
ALTER TABLE "JXB_BK_CASH_RCPY" DROP CONSTRAINT "FK_DF49SUG8SPE54W20EIL9MQ6EV";
ALTER TABLE "JXB_BK_CASH_FLOW" DROP CONSTRAINT "FK_GNOHCPWYK11PTPO25358I8UY4";
ALTER TABLE "JXB_BK_CASH_FLOW" DROP CONSTRAINT "FK_A71OEOG20YUND4CBYVKYEUDJT";
ALTER TABLE "JXB_BK_CASH_FLOW" DROP CONSTRAINT "FK_2FV8VVGE1NHR07M9PVAL5MFW3";
ALTER TABLE "JXB_BK_CASH_FLOW" DROP CONSTRAINT "FK_PQ4KNHO71W98V5HAXQ4XQ0YMD";

--创建sequence
--DROP SEQUENCE  "COAAGROUP_ID_SEQ";
--DROP SEQUENCE  "GLCOAAMAP_ID_SEQ";
--DROP SEQUENCE  "MSGLOG_ID_SEQ";
--DROP SEQUENCE  "SECACCT_ID_SEQ";
--DROP SEQUENCE  "SECPRICE_ID_SEQ";
--DROP SEQUENCE  "CASHFLOWCFG_ID_SEQ";
--DROP SEQUENCE  "CASHFLOWFLOATRATE_ID_SEQ";
--DROP SEQUENCE  "CASHFLOWPAR_ID_SEQ";
--DROP SEQUENCE  "CASHFLOWPAYMENT_ID_SEQ";
--DROP SEQUENCE  "CASHFLOWRATE_ID_SEQ";
--DROP SEQUENCE  "ISSUEINFO_ID_SEQUENCE";
--DROP SEQUENCE  "SECCONTRACT_ID_SEQ";
--DROP SEQUENCE  "SMFDIV_ID_SEQ";
--DROP SEQUENCE  "TRADE_ID_SEQUENCE";
--DROP SEQUENCE  "PORTFOLIOINTERFACEMAP_ID_SEQ";
--DROP SEQUENCE  "WORKFLOWFACTORY_ID_SEQ";
--DROP SEQUENCE  "BKCASHACCT_ID_SEQ";
--DROP SEQUENCE  "TRAN_ID_SEQ";
--DROP SEQUENCE  "TRANSUM_ID_SEQ";
--DROP SEQUENCE  "TRANCLO_ID_SEQ";
--DROP SEQUENCE  "TRANCOLLATERAL_ID_SEQ";
--DROP SEQUENCE  "TRANFEEDEF_ID_SEQ";
--DROP SEQUENCE  "TRANBASICFEE_ID_SEQ";
--DROP SEQUENCE  "TRANFEETYPEMAP_ID_SEQ";
--DROP SEQUENCE  "BKCASHRCPY_ID_SEQ";
--DROP SEQUENCE  "BKCASHFLOW_ID_SEQ";
--DROP SEQUENCE  "BROKER_ID_SEQ";
--DROP SEQUENCE  "ISSUER_ID_SEQ";
--DROP SEQUENCE  "RPSECTYPE_ID_SEQ";
--DROP SEQUENCE  "JXC_COAAGLSECMAP_ID_SEQ";

CREATE SEQUENCE  "COAAGROUP_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "GLCOAAMAP_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "MSGLOG_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "SECACCT_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "SECPRICE_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "CASHFLOWCFG_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "CASHFLOWFLOATRATE_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "CASHFLOWPAR_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "CASHFLOWPAYMENT_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "CASHFLOWRATE_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "ISSUEINFO_ID_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "SECCONTRACT_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "SMFDIV_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "TRADE_ID_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "PORTFOLIOINTERFACEMAP_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "WORKFLOWFACTORY_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "BKCASHACCT_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "TRAN_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "TRANSUM_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "TRANCLO_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "TRANCOLLATERAL_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "TRANFEEDEF_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "TRANBASICFEE_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "TRANFEETYPEMAP_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "BKCASHRCPY_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "BKCASHFLOW_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "BROKER_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "ISSUER_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "RPSECTYPE_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "JXC_COAAGLSECMAP_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6000 CACHE 20 NOORDER  NOCYCLE ;
