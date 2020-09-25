
-----------------银行账户关账信息--------------
drop table "JXB_BK_CASH_CLO";

CREATE TABLE "JXB_BK_CASH_CLO" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"ACCOUNTTYPE" NUMBER(1,0), 
	"BALANCEAMT" NUMBER(30,2),
	"BKCASHACCTID" NUMBER(10,0), 
	"CLOSEDATE" DATE, 
	"CURID" VARCHAR2(10),
	"FXRATE" NUMBER(30,14), 
	"INTERESTINCOME" NUMBER(30,2),
	"PORTNUM" VARCHAR2(50),
	"TODAYBALANCEAMT" NUMBER(30,2),
	"TODAYINCOME" NUMBER(30,2),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20)
);

COMMENT on TABLE "JXB_BK_CASH_CLO"  is  '银行账户关账表';
comment on column "JXB_BK_CASH_CLO"."ID" is '唯一主键';
comment on column "JXB_BK_CASH_CLO"."BKCASHACCTID" is '银行账户系统代码';
comment on column "JXB_BK_CASH_CLO"."ACCOUNTTYPE" is '银行账户类型';
comment on column "JXB_BK_CASH_CLO"."PORTNUM" is '投资组合代码';
comment on column "JXB_BK_CASH_CLO"."BALANCEAMT" is '账户余额';
comment on column "JXB_BK_CASH_CLO"."CLOSEDATE" is '关账日期';
comment on column "JXB_BK_CASH_CLO"."CURID" is '货币代码';
comment on column "JXB_BK_CASH_CLO"."FXRATE" is '汇率';
comment on column "JXB_BK_CASH_CLO"."INTERESTINCOME" is '利息余额';
comment on column "JXB_BK_CASH_CLO"."TODAYBALANCEAMT" is '当天利息收入';
comment on column "JXB_BK_CASH_CLO"."TODAYINCOME" is '接口编号,通过哪个接口导入的消息 | 关联InterfaceSource表主键 ';
comment on column "JXB_BK_CASH_CLO"."CREATE_TIME" is '创建时间';
comment on column "JXB_BK_CASH_CLO"."CREATE_DEPT" is '创建部门';
comment on column "JXB_BK_CASH_CLO"."UPDATE_TIME" is '更新时间';
comment on column "JXB_BK_CASH_CLO"."UPDATE_USER" is '更新用户';
comment on column "JXB_BK_CASH_CLO"."CREATE_USER" is '创建用户';



-----------------银行利息配置--------------

drop table "JXB_BK_INTE_ACCT";

CREATE TABLE "JXB_BK_INTE_ACCT" 
(	
	"BK_INTE_ID" NUMBER(10,0) NOT NULL ENABLE, 
	"BANKID" VARCHAR2(100),
	"CASHFLOWTYPE" VARCHAR2(3), 
	"INTPERIODCONV" NUMBER(1,0), 
	"MASTERRECNAME" VARCHAR2(100 BYTE), 
	"CASHFLOWCFG" VARCHAR2(32),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),	
	PRIMARY KEY ("BK_INTE_ID")
);


COMMENT on TABLE "JXB_BK_INTE_ACCT"  is  '银行利息配置';
comment on column "JXB_BK_INTE_ACCT"."BK_INTE_ID" is '利息系统代码';
comment on column "JXB_BK_INTE_ACCT"."MASTERRECNAME" is '利息名称';
comment on column "JXB_BK_INTE_ACCT"."BANKID" is '银行系统编号';
comment on column "JXB_BK_INTE_ACCT"."CASHFLOWTYPE" is '尚未使用';
comment on column "JXB_BK_INTE_ACCT"."INTPERIODCONV" is '期间方法(规定时期/期间上一工作日/上一工作日的前一日)';
comment on column "JXB_BK_INTE_ACCT"."CASHFLOWCFG" is '现金流配置';
comment on column "JXB_BK_INTE_ACCT"."CREATE_TIME" is '创建时间';
comment on column "JXB_BK_INTE_ACCT"."CREATE_DEPT" is '创建部门';
comment on column "JXB_BK_INTE_ACCT"."UPDATE_TIME" is '更新时间';
comment on column "JXB_BK_INTE_ACCT"."UPDATE_USER" is '更新用户';
comment on column "JXB_BK_INTE_ACCT"."CREATE_USER" is '创建用户';


-----------------银行利率配置--------------

drop table "JXB_BK_INTE_RATIO";

CREATE TABLE "JXB_BK_INTE_RATIO" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"COMMENTS" VARCHAR2(100),
	"BK_INTE_ID" NUMBER(10,0),
	"STARTDATE" DATE,
	"ENDDATE" DATE, 
	"PRICE" NUMBER(30,14),  
	"RATEFORMULA" NUMBER(3,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),		
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXB_BK_INTE_RATIO"  is  '银行利率数值配置';
comment on column "JXB_BK_INTE_RATIO"."BK_INTE_ID" is '利息系统代码(关联JXB_BK_INTE_ACCT)';
comment on column "JXB_BK_INTE_RATIO"."COMMENTS" is '描述';
comment on column "JXB_BK_INTE_RATIO"."STARTDATE" is '开始日期';
comment on column "JXB_BK_INTE_RATIO"."ENDDATE" is '结束日期';
comment on column "JXB_BK_INTE_RATIO"."PRICE" is '利率值';
comment on column "JXB_BK_INTE_RATIO"."RATEFORMULA" is '利率计算公式';
comment on column "JXB_BK_INTE_RATIO"."CREATE_TIME" is '创建时间';
comment on column "JXB_BK_INTE_RATIO"."CREATE_DEPT" is '创建部门';
comment on column "JXB_BK_INTE_RATIO"."UPDATE_TIME" is '更新时间';
comment on column "JXB_BK_INTE_RATIO"."UPDATE_USER" is '更新用户';
comment on column "JXB_BK_INTE_RATIO"."CREATE_USER" is '创建用户';


-----------------银行利息结转记录表--------------

drop table "JXB_BK_INTE_SETHIS";

CREATE TABLE "JXB_BK_INTE_SETHIS" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"BANKACCT" NUMBER(10,0), 
	"PORTFOLIO" VARCHAR2(50),
	"INTEDATE" DATE,
	"TANUM" VARCHAR2(50),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXB_BK_INTE_SETHIS"  is  '银行利息结转记录表';
comment on column "JXB_BK_INTE_SETHIS"."ID" is '系统主键';
comment on column "JXB_BK_INTE_SETHIS"."BANKACCT" is '银行账户';
comment on column "JXB_BK_INTE_SETHIS"."PORTFOLIO" is '投资组合';
comment on column "JXB_BK_INTE_SETHIS"."INTEDATE" is '结转日期';
comment on column "JXB_BK_INTE_SETHIS"."TANUM" is '交易编号(关联TRAN_BASIC表)';
comment on column "JXB_BK_INTE_SETHIS"."CREATE_TIME" is '创建时间';
comment on column "JXB_BK_INTE_SETHIS"."CREATE_DEPT" is '创建部门';
comment on column "JXB_BK_INTE_SETHIS"."UPDATE_TIME" is '更新时间';
comment on column "JXB_BK_INTE_SETHIS"."UPDATE_USER" is '更新用户';
comment on column "JXB_BK_INTE_SETHIS"."CREATE_USER" is '创建用户';




-----------------银行利息应收应付表(尚未使用该表)--------------

drop table "JXB_INTEREST_RCPY";

CREATE TABLE "JXB_INTEREST_RCPY" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"CASH" NUMBER(30,2),
	"LEDGERDATE" DATE, 
	"SETTLE_STATUS" VARCHAR2(3), 
	"BK_ACCT_NO" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);






-----------------科目配置表--------------

drop table "JXC_COAA";

CREATE TABLE "JXC_COAA" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"ACCNTNUM" VARCHAR2(32), 
	"ACCNTNAME" VARCHAR2(100),
	"ACCNTCATEGORY" NUMBER(10,0),
	"CHARTNUMBER" VARCHAR2(50),	
	"ACCNTSUBCATEGORY" NUMBER(1,0), 
	"ACCTLEVEL" NUMBER(1,0),
	"ACCTISLEAF" NUMBER(1,0),  
	"CLOSETOACCNTNUM" VARCHAR2(32), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXC_COAA"  is  '银行利息结转记录表';
comment on column "JXC_COAA"."ID" is '系统主键';
comment on column "JXC_COAA"."ACCNTNUM" is '科目号';
comment on column "JXC_COAA"."ACCNTCATEGORY" is '科目属性 1:资产 2:负债 3:收入 4:开支 5:资本 6:资本-已实现 7:资本-未实现 ';
comment on column "JXC_COAA"."ACCNTNAME" is '科目名称';
comment on column "JXC_COAA"."ACCNTSUBCATEGORY" is '尚未使用';
comment on column "JXC_COAA"."CHARTNUMBER" is '科目表主键(关联JXC_COAAHDR)';
comment on column "JXC_COAA"."ACCTLEVEL" is '科目等级';
comment on column "JXC_COAA"."ACCTISLEAF" is '是否最末科目';
comment on column "JXC_COAA"."CLOSETOACCNTNUM" is '结转科目';
comment on column "JXC_COAA"."CREATE_TIME" is '创建时间';
comment on column "JXC_COAA"."CREATE_DEPT" is '创建部门';
comment on column "JXC_COAA"."UPDATE_TIME" is '更新时间';
comment on column "JXC_COAA"."UPDATE_USER" is '更新用户';
comment on column "JXC_COAA"."CREATE_USER" is '创建用户';



---------------科目表----------------

drop table "JXC_COAAHDR";

CREATE TABLE "JXC_COAAHDR" 
(	
	"CHARTNUM" VARCHAR2(50) NOT NULL ENABLE, 
	"CHARTNAME" VARCHAR2(100),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("CHARTNUM")
);


COMMENT on TABLE "JXC_COAAHDR"  is  '科目表';
comment on column "JXC_COAAHDR"."CHARTNUM" is '科目表主键';
comment on column "JXC_COAAHDR"."CHARTNAME" is '科目表名称';
comment on column "JXC_COAAHDR"."CREATE_TIME" is '创建时间';
comment on column "JXC_COAAHDR"."CREATE_DEPT" is '创建部门';
comment on column "JXC_COAAHDR"."UPDATE_TIME" is '更新时间';
comment on column "JXC_COAAHDR"."UPDATE_USER" is '更新用户';
comment on column "JXC_COAAHDR"."CREATE_USER" is '创建用户';



---------------科目总账栏匹配----------------

drop table "JXC_GL_COAA_MAP";

CREATE TABLE "JXC_GL_COAA_MAP" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"ACCT_NUM" VARCHAR2(32),
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

COMMENT on TABLE "JXC_GL_COAA_MAP"  is  '科目表';
comment on column "JXC_GL_COAA_MAP"."ID" is '唯一主键';
comment on column "JXC_GL_COAA_MAP"."ACCT_NUM" is '科目号。关联Coaa表的ACCNTNUM字段';
comment on column "JXC_GL_COAA_MAP"."CHARTNUM" is '会计科目表编号,关联CHARTNUM表的CHARTNUM字段 ';
comment on column "JXC_GL_COAA_MAP"."COLUMNNUM" is '总账栏编号。具体请查看数据字典--总账栏 ';
comment on column "JXC_GL_COAA_MAP"."GROUPNUM" is '总账组编号,关联CoaaGroup表的groupNum字段 ';
comment on column "JXC_GL_COAA_MAP"."CREATE_TIME" is '创建时间';
comment on column "JXC_GL_COAA_MAP"."CREATE_DEPT" is '创建部门';
comment on column "JXC_GL_COAA_MAP"."UPDATE_TIME" is '更新时间';
comment on column "JXC_GL_COAA_MAP"."UPDATE_USER" is '更新用户';
comment on column "JXC_GL_COAA_MAP"."CREATE_USER" is '创建用户';



---------------JOB关账历史----------------

drop table "JXJ_CLOSEHIS";

CREATE TABLE "JXJ_CLOSEHIS" 
(	
	"CLOSEID" NUMBER(10,0) NOT NULL ENABLE, 
	"CLOSEDATE" DATE, 
	"CLOSETIME" DATE, 
	"PRECLOSE" DATE, 
	"PORTFOLIO" VARCHAR2(50),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("CLOSEID")
);

COMMENT on TABLE "JXJ_CLOSEHIS"  is  '关账历史记录';
comment on column "JXJ_CLOSEHIS"."CLOSEID" is '唯一主键';
comment on column "JXJ_CLOSEHIS"."PORTFOLIO" is '投资组合';
comment on column "JXJ_CLOSEHIS"."CLOSEDATE" is '关账日期';
comment on column "JXJ_CLOSEHIS"."CLOSETIME" is '关账时间';
comment on column "JXJ_CLOSEHIS"."PRECLOSE" is '上一个关账日期';
comment on column "JXJ_CLOSEHIS"."CREATE_TIME" is '创建时间';
comment on column "JXJ_CLOSEHIS"."CREATE_DEPT" is '创建部门';
comment on column "JXJ_CLOSEHIS"."UPDATE_TIME" is '更新时间';
comment on column "JXJ_CLOSEHIS"."UPDATE_USER" is '更新用户';
comment on column "JXJ_CLOSEHIS"."CREATE_USER" is '创建用户';






---------------JOB关账历史----------------


drop table "JXJ_GL_CLOSE";

CREATE TABLE "JXJ_GL_CLOSE" 
(	
	"GLCLOSEID" NUMBER(10,0) NOT NULL ENABLE, 
	"PORTFOLIO" VARCHAR2(50),
	"ACCTNUM" VARCHAR2(32), 
	"CLASSID" NUMBER(10,0), 
	"CLOSEDATE" DATE, 
	"CURRENCYCODE" VARCHAR2(10), 
	"CR_FUNC" NUMBER(30,2),
	"CR_LOCAL" NUMBER(30,2), 
	"DR_FUNC" NUMBER(30,2),
	"DR_LOCAL" NUMBER(30,2),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("GLCLOSEID")
);

COMMENT on TABLE "JXJ_GL_CLOSE"  is  '关账历史记录';
comment on column "JXJ_GL_CLOSE"."GLCLOSEID" is '唯一主键';
comment on column "JXJ_GL_CLOSE"."PORTFOLIO" is '投资组合系统编号,关联Portfolio_Hdr表的portfolio字段 ';
comment on column "JXJ_GL_CLOSE"."ACCTNUM" is '科目代码,关联coaa表的ACCNTNUM字段 ';
comment on column "JXJ_GL_CLOSE"."CLASSID" is '尚未使用,分级基金代码 ';
comment on column "JXJ_GL_CLOSE"."CURRENCYCODE" is '货币代码';
comment on column "JXJ_GL_CLOSE"."CR_FUNC" is '贷方本币值';
comment on column "JXJ_GL_CLOSE"."CR_LOCAL" is '贷方原币值';
comment on column "JXJ_GL_CLOSE"."DR_FUNC" is '借方本币值';
comment on column "JXJ_GL_CLOSE"."DR_LOCAL" is '借方原币值';
comment on column "JXJ_GL_CLOSE"."CREATE_TIME" is '创建时间';
comment on column "JXJ_GL_CLOSE"."CREATE_DEPT" is '创建部门';
comment on column "JXJ_GL_CLOSE"."UPDATE_TIME" is '更新时间';
comment on column "JXJ_GL_CLOSE"."UPDATE_USER" is '更新用户';
comment on column "JXJ_GL_CLOSE"."CREATE_USER" is '创建用户';




---------------JOB执行历史----------------

drop table "JXJ_JOBSHIS";

CREATE TABLE "JXJ_JOBSHIS" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"PORTFOLIO" VARCHAR2(50),
	"JOBID" NUMBER(10,0), 
	"LEDGERDATE" DATE, 
	"OPERATIONTIME" DATE, 
	"STATUS" NUMBER(1,0), 
	"FAILEDDESC" VARCHAR2(3000), 
	"USERID" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXJ_JOBSHIS"  is  'JOB执行历史';
comment on column "JXJ_JOBSHIS"."ID" is '唯一主键';
comment on column "JXJ_JOBSHIS"."PORTFOLIO" is '投资组合系统编号,关联Portfolio_Hdr表的portfolio字段 ';
comment on column "JXJ_JOBSHIS"."JOBID" is 'Job编号 | 数据字典--JobsType ';
comment on column "JXJ_JOBSHIS"."LEDGERDATE" is '做账日期';
comment on column "JXJ_JOBSHIS"."OPERATIONTIME" is 'Job运行的时间 ';
comment on column "JXJ_JOBSHIS"."STATUS" is '执行结果是成功/失败 ';
comment on column "JXJ_JOBSHIS"."FAILEDDESC" is 'Job失败后的错误信息 ';
comment on column "JXJ_JOBSHIS"."USERID" is '用户编号 | 关联User表主键 ';
comment on column "JXJ_JOBSHIS"."CREATE_TIME" is '创建时间';
comment on column "JXJ_JOBSHIS"."CREATE_DEPT" is '创建部门';
comment on column "JXJ_JOBSHIS"."UPDATE_TIME" is '更新时间';
comment on column "JXJ_JOBSHIS"."UPDATE_USER" is '更新用户';
comment on column "JXJ_JOBSHIS"."CREATE_USER" is '创建用户';





---------------工作流执行的JOB匹配----------------

drop table "JXJ_WORKFLOWJOBMAP";

CREATE TABLE "JXJ_WORKFLOWJOBMAP" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"WORKFLOWSTEP" NUMBER(10,0), 
	"JOBID" NUMBER(10,0), 
	 PRIMARY KEY ("ID")
);



COMMENT on TABLE "JXJ_WORKFLOWJOBMAP"  is  '工作流执行的JOB匹配';
comment on column "JXJ_WORKFLOWJOBMAP"."ID" is '唯一主键';
comment on column "JXJ_WORKFLOWJOBMAP"."WORKFLOWSTEP" is '基金会计做帐操作 | 501:到期自动作业 502:交易关帐 503:每日会计作业 504:每月会计作业 505:基金净值计算 506:会计总账关帐 ';
comment on column "JXJ_WORKFLOWJOBMAP"."JOBID" is 'Job编号，类似152费用日记账 | 数据字典--做帐任务类型 ';



---------------工作流执行的JOB匹配----------------

drop table "JXJ_WORKFLOWJOBMAP";

CREATE TABLE "JXJ_WORKFLOWJOBMAP" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"WORKFLOWSTEP" NUMBER(10,0), 
	"JOBID" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	 PRIMARY KEY ("ID")
);



COMMENT on TABLE "JXJ_WORKFLOWJOBMAP"  is  '工作流执行的JOB匹配';
comment on column "JXJ_WORKFLOWJOBMAP"."ID" is '唯一主键';
comment on column "JXJ_WORKFLOWJOBMAP"."WORKFLOWSTEP" is '基金会计做帐操作 | 501:到期自动作业 502:交易关帐 503:每日会计作业 504:每月会计作业 505:基金净值计算 506:会计总账关帐 ';
comment on column "JXJ_WORKFLOWJOBMAP"."JOBID" is 'Job编号，类似152费用日记账 | 数据字典--做帐任务类型 ';
comment on column "JXJ_WORKFLOWJOBMAP"."CREATE_TIME" is '创建时间';
comment on column "JXJ_WORKFLOWJOBMAP"."CREATE_DEPT" is '创建部门';
comment on column "JXJ_WORKFLOWJOBMAP"."UPDATE_TIME" is '更新时间';
comment on column "JXJ_WORKFLOWJOBMAP"."UPDATE_USER" is '更新用户';
comment on column "JXJ_WORKFLOWJOBMAP"."CREATE_USER" is '创建用户';




---------------国家列表----------------

drop table "JXM_COUNTRY";

CREATE TABLE "JXM_COUNTRY" 
(	
	"COUNTRYID" VARCHAR2(32) NOT NULL ENABLE, 
	"COUNTRYCODE" VARCHAR2(32), 
	"COUNTRYNAME" VARCHAR2(100), 
	"ACTIVE" NUMBER(1,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("COUNTRYID")
);



COMMENT on TABLE "JXM_COUNTRY" is '国家列表';
comment on column "JXM_COUNTRY"."COUNTRYID" is '唯一主键';
comment on column "JXM_COUNTRY"."COUNTRYCODE" is '国家代码';
comment on column "JXM_COUNTRY"."COUNTRYNAME" is '国家名称';
comment on column "JXM_COUNTRY"."ACTIVE" is '是否有效 1:有效 0：无效 ';
comment on column "JXM_COUNTRY"."CREATE_TIME" is '创建时间';
comment on column "JXM_COUNTRY"."CREATE_DEPT" is '创建部门';
comment on column "JXM_COUNTRY"."UPDATE_TIME" is '更新时间';
comment on column "JXM_COUNTRY"."UPDATE_USER" is '更新用户';
comment on column "JXM_COUNTRY"."CREATE_USER" is '创建用户';




---------------交易所列表----------------

drop table "JXM_EXCHANGECFG";

CREATE TABLE "JXM_EXCHANGECFG" 
(	
	"CODE" VARCHAR2(32) NOT NULL ENABLE, 
	"NAME" VARCHAR2(100), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("CODE")
);



COMMENT on TABLE "JXM_EXCHANGECFG" is '交易所列表';
comment on column "JXM_EXCHANGECFG"."CODE" is '交易所代码';
comment on column "JXM_EXCHANGECFG"."NAME" is '交易所名称';
comment on column "JXM_EXCHANGECFG"."CREATE_TIME" is '创建时间';
comment on column "JXM_EXCHANGECFG"."CREATE_DEPT" is '创建部门';
comment on column "JXM_EXCHANGECFG"."UPDATE_TIME" is '更新时间';
comment on column "JXM_EXCHANGECFG"."UPDATE_USER" is '更新用户';
comment on column "JXM_EXCHANGECFG"."CREATE_USER" is '创建用户';







---------------货币列表----------------

drop table "JXM_FX_CURRENCY";

CREATE TABLE "JIXU"."JXM_FX_CURRENCY" 
(	
	"CURR_ID" VARCHAR2(10) NOT NULL ENABLE, 
	"CURR_CODE" VARCHAR2(10), 
	"CURR_NAME" VARCHAR2(100), 
	"ACTIVE" NUMBER(1,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("CURR_ID")
);



COMMENT on TABLE "JXM_FX_CURRENCY" is '工作流执行的JOB匹配';
comment on column "JXM_FX_CURRENCY"."CURR_ID" is '货币代码';
comment on column "JXM_FX_CURRENCY"."CURR_CODE" is '货币代码';
comment on column "JXM_FX_CURRENCY"."CURR_NAME" is '货币名称';
comment on column "JXM_FX_CURRENCY"."ACTIVE" is '是否有效 1:有效 0:无效 ';
comment on column "JXM_FX_CURRENCY"."CREATE_TIME" is '创建时间';
comment on column "JXM_FX_CURRENCY"."CREATE_DEPT" is '创建部门';
comment on column "JXM_FX_CURRENCY"."UPDATE_TIME" is '更新时间';
comment on column "JXM_FX_CURRENCY"."UPDATE_USER" is '更新用户';
comment on column "JXM_FX_CURRENCY"."CREATE_USER" is '创建用户';





---------------汇率列表----------------

drop table "JXM_FX_RATE";

CREATE TABLE "JXM_FX_RATE" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"BASECURRID" VARCHAR2(10), 
	"CURR_ID" VARCHAR2(10), 
	"RATE_DATE" DATE, 
	"RATE" NUMBER(30,14), 
	"SOURCEID" NUMBER(10,0), 
	"LST_UPDATE" DATE, 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXM_FX_RATE" is '汇率列表';
comment on column "JXM_FX_RATE"."ID" is '货币代码';
comment on column "JXM_FX_RATE"."BASECURRID" is '基准货币编号,和CURR_ID字段一起使用。比如:美元(CURR_ID)对于人民币(baseCurrID)的汇率。关联FX_CURRENCY表的ID字段';
comment on column "JXM_FX_RATE"."CURR_ID" is '货币编号,和baseCurrID字段一起使用。比如:美元(CURR_ID)对于人民币(baseCurrID)的汇率。关联FX_CURRENCY表的ID字段';
comment on column "JXM_FX_RATE"."RATE_DATE" is '汇率日期';
comment on column "JXM_FX_RATE"."RATE" is '汇率';
comment on column "JXM_FX_RATE"."SOURCEID" is '数据来源,关联InterfaceSource表的sourceID字段 ';
comment on column "JXM_FX_RATE"."LST_UPDATE" is '是否有效 1:有效 0:无效 ';
comment on column "JXM_FX_RATE"."CREATE_TIME" is '创建时间';
comment on column "JXM_FX_RATE"."CREATE_DEPT" is '创建部门';
comment on column "JXM_FX_RATE"."UPDATE_TIME" is '更新时间';
comment on column "JXM_FX_RATE"."UPDATE_USER" is '更新用户';
comment on column "JXM_FX_RATE"."CREATE_USER" is '创建用户';




---------------省份/州----------------

drop table "JXM_PROVINCE";

CREATE TABLE "JXM_PROVINCE" 
(	
	"PROID" NUMBER(10,0) NOT NULL ENABLE,  
	"PRONAME" VARCHAR2(100), 
	"COUNTRY" VARCHAR2(32),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("PROID")
);


COMMENT on TABLE "JXM_PROVINCE" is '省份/州';
comment on column "JXM_PROVINCE"."PROID" is '唯一主键';
comment on column "JXM_PROVINCE"."PRONAME" is '省份名称';
comment on column "JXM_PROVINCE"."COUNTRY" is '省份所属国家 | 关联Country表主键 ';
comment on column "JXM_PROVINCE"."CREATE_TIME" is '创建时间';
comment on column "JXM_PROVINCE"."CREATE_DEPT" is '创建部门';
comment on column "JXM_PROVINCE"."UPDATE_TIME" is '更新时间';
comment on column "JXM_PROVINCE"."UPDATE_USER" is '更新用户';
comment on column "JXM_PROVINCE"."CREATE_USER" is '创建用户';









---------------省份/州----------------

drop table "JXS_BROKER";

CREATE TABLE "JXS_BROKER" 
(	
	"BROKERID" VARCHAR2(32) NOT NULL ENABLE, 
	"BROKERCODE" VARCHAR2(32),  
	"BROKERNAME" VARCHAR2(50),
	"BROKERDESC" VARCHAR2(100),
	"ADDRESS" VARCHAR2(200), 
	"BICCODE" VARCHAR2(100), 
	"CONTRACTOR" VARCHAR2(50), 
	"COUNTRY" VARCHAR2(32), 
	"FAXNUM" VARCHAR2(100), 
	"FINCODE" VARCHAR2(100), 
	"PHONENUM" VARCHAR2(100),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),	
	PRIMARY KEY ("BROKERID")
);


COMMENT on TABLE "JXS_BROKER" is '省份/州';
comment on column "JXS_BROKER"."BROKERID" is '唯一主键';
comment on column "JXS_BROKER"."BROKERCODE" is '券商编号';
comment on column "JXS_BROKER"."BROKERNAME" is '券商名称';
comment on column "JXS_BROKER"."BROKERDESC" is '券商描述';
comment on column "JXS_BROKER"."ADDRESS" is '地址';
comment on column "JXS_BROKER"."BICCODE" is '尚未使用,BIC编号';
comment on column "JXS_BROKER"."CONTRACTOR" is '联系人';
comment on column "JXS_BROKER"."COUNTRY" is '国家编号,关联Country表的countryID字段 ';
comment on column "JXS_BROKER"."FAXNUM" is '传真号码';
comment on column "JXS_BROKER"."FINCODE" is '尚未使用,FIN编号';
comment on column "JXS_BROKER"."PHONENUM" is '电话号码';
comment on column "JXS_BROKER"."CREATE_TIME" is '创建时间';
comment on column "JXS_BROKER"."CREATE_DEPT" is '创建部门';
comment on column "JXS_BROKER"."UPDATE_TIME" is '更新时间';
comment on column "JXS_BROKER"."UPDATE_USER" is '更新用户';
comment on column "JXS_BROKER"."CREATE_USER" is '创建用户';



---------------投资经理组----------------

drop table "JXS_MANAGERGROUP";


 CREATE TABLE "JXS_MANAGERGROUP" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"GROUPNAME" VARCHAR2(100), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),	
	PRIMARY KEY ("ID")
);



COMMENT on TABLE "JXS_MANAGERGROUP" is '投资经理组';
comment on column "JXS_MANAGERGROUP"."ID" is '唯一主键';
comment on column "JXS_MANAGERGROUP"."GROUPNAME" is '投资经理组名字';
comment on column "JXS_MANAGERGROUP"."CREATE_TIME" is '创建时间';
comment on column "JXS_MANAGERGROUP"."CREATE_DEPT" is '创建部门';
comment on column "JXS_MANAGERGROUP"."UPDATE_TIME" is '更新时间';
comment on column "JXS_MANAGERGROUP"."UPDATE_USER" is '更新用户';
comment on column "JXS_MANAGERGROUP"."CREATE_USER" is '创建用户';




---------------投资经理----------------

drop table "JXS_MANAGERS";


CREATE TABLE "JXS_MANAGERS" 
(	
"ID" NUMBER(10,0) NOT NULL ENABLE, 
"SHORTCODE" VARCHAR2(20),
"MANAGERNAME" VARCHAR2(100), 
"MANAGERGROUPCODE" NUMBER(10,0), 
"ACTIVE" NUMBER(1,0),
"CREATE_TIME" TIMESTAMP (6), 
"CREATE_DEPT" VARCHAR2(50), 
"UPDATE_TIME" TIMESTAMP (6), 
"UPDATE_USER" VARCHAR2(20), 
"CREATE_USER" VARCHAR2(20),		
 PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXS_MANAGERS" is '投资经理';
comment on column "JXS_MANAGERS"."ID" is '唯一主键';
comment on column "JXS_MANAGERS"."SHORTCODE" is '简称';
comment on column "JXS_MANAGERS"."MANAGERNAME" is '经理名字';
comment on column "JXS_MANAGERS"."MANAGERGROUPCODE" is '经理组编号,该经历属于哪个经理组 | 关联managerGroup表主键 ';
comment on column "JXS_MANAGERS"."ACTIVE" is '是否激活';
comment on column "JXS_MANAGERS"."CREATE_TIME" is '创建时间';
comment on column "JXS_MANAGERS"."CREATE_DEPT" is '创建部门';
comment on column "JXS_MANAGERS"."UPDATE_TIME" is '更新时间';
comment on column "JXS_MANAGERS"."UPDATE_USER" is '更新用户';
comment on column "JXS_MANAGERS"."CREATE_USER" is '创建用户';



--------------新建投资组合配置----------------

drop table "JXS_NEWFUND";

CREATE TABLE "JXS_NEWFUND" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"CONTENTNAME" VARCHAR2(100), 
	"PORTNUM" VARCHAR2(50),
	"CLIENTID" NUMBER(10,0), 
	"CREATETIME" DATE, 
	"EXECUTEFLAG" NUMBER(1,0), 
	"NEWFUNDTYPE" NUMBER(1,0), 
	"PPDATETIME" DATE, 
	"CREATEUSERID" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXS_NEWFUND" is '新建投资组合配置';
comment on column "JXS_NEWFUND"."ID" is '唯一主键';
comment on column "JXS_NEWFUND"."CONTENTNAME" is '名称';
comment on column "JXS_NEWFUND"."PORTNUM" is '投资组合系统编号,关联Portfolio_Hdr表的portfolio字段 ';
comment on column "JXS_NEWFUND"."CLIENTID" is '客户编码,决定该投资组合是属于哪个客户 | 关联Portfolio_Client表主键 ';
comment on column "JXS_NEWFUND"."CREATETIME" is '配置创建时间';
comment on column "JXS_NEWFUND"."EXECUTEFLAG" is '是否已经执行';
comment on column "JXS_NEWFUND"."NEWFUNDTYPE" is '创建基金类型 | 0:新基金上线配置    1:老基金迁移配置 ';
comment on column "JXS_NEWFUND"."PPDATETIME" is '配置更新时间';
comment on column "JXS_NEWFUND"."CREATEUSERID" is '创建配置的用户 | 关联User表主键';
comment on column "JXS_NEWFUND"."CREATE_TIME" is '创建时间';
comment on column "JXS_NEWFUND"."CREATE_DEPT" is '创建部门';
comment on column "JXS_NEWFUND"."UPDATE_TIME" is '更新时间';
comment on column "JXS_NEWFUND"."UPDATE_USER" is '更新用户';
comment on column "JXS_NEWFUND"."CREATE_USER" is '创建用户';



--------------新建投资组合消息----------------

drop table "JXS_NEWFUNDCONTENT";

CREATE TABLE "JXS_NEWFUNDCONTENT" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"NEWFUNDID" NUMBER(10,0), 
	"NEWFUNDCONTENT" CLOB, 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXS_NEWFUNDCONTENT" is '新建投资组合配置';
comment on column "JXS_NEWFUNDCONTENT"."ID" is '唯一主键';
comment on column "JXS_NEWFUNDCONTENT"."NEWFUNDID" is '新基金编号 | 关联NewFund表主键';
comment on column "JXS_NEWFUNDCONTENT"."NEWFUNDCONTENT" is '新基金的配置,所有的配置都放在一个字符串里。包括银行，券商，头寸等所有信息 ';
comment on column "JXS_NEWFUNDCONTENT"."CREATE_TIME" is '创建时间';
comment on column "JXS_NEWFUNDCONTENT"."CREATE_DEPT" is '创建部门';
comment on column "JXS_NEWFUNDCONTENT"."UPDATE_TIME" is '更新时间';
comment on column "JXS_NEWFUNDCONTENT"."UPDATE_USER" is '更新用户';
comment on column "JXS_NEWFUNDCONTENT"."CREATE_USER" is '创建用户';




--------------绩效费配置表----------------

drop table "JXS_PERFORMANCEFEECFG";


CREATE TABLE "JXS_PERFORMANCEFEECFG" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"PORTNUM" VARCHAR2(50),
	"CUSIP" VARCHAR2(50), 
	"BENCHMARK" NUMBER(30,14),  
	"CLAWBACKEXPENSETYPE" NUMBER(10,0), 
	"CLAWBACKFLAG" NUMBER(1,0), 
	"CRACCT" VARCHAR2(32), 
	"DRACCT" VARCHAR2(32), 
	"EXPENSEBASISTYPE" NUMBER(1,0), 
	"INPERCENTAGE" NUMBER(30,14),  
	"LOTNUM" NUMBER(10,0), 
	"MAXLEVEL" NUMBER(30,14),
	"MINLEVEL" NUMBER(30,14),
	"OUTPERCENTAGE" NUMBER(30,14),
	"RETURNRATETYPE" NUMBER(1,0),
	"ACTIVEFLAG" NUMBER(1,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXS_PERFORMANCEFEECFG" is '绩效费配置表，用于银行资管、私募等';
comment on column "JXS_PERFORMANCEFEECFG"."ID" is '唯一主键';
comment on column "JXS_PERFORMANCEFEECFG"."PORTNUM" is '投资组合';
comment on column "JXS_PERFORMANCEFEECFG"."CUSIP" is '证券系统代码';
comment on column "JXS_PERFORMANCEFEECFG"."BENCHMARK" is '业绩基准';
comment on column "JXS_PERFORMANCEFEECFG"."CLAWBACKEXPENSETYPE" is '用于回拨的费用类型';
comment on column "JXS_PERFORMANCEFEECFG"."CLAWBACKFLAG" is '低于下限是否回拨';
comment on column "JXS_PERFORMANCEFEECFG"."CRACCT" is '贷方科目';
comment on column "JXS_PERFORMANCEFEECFG"."DRACCT" is '借方科目';
comment on column "JXS_PERFORMANCEFEECFG"."EXPENSEBASISTYPE" is '费用基础，原始金额/剩余金额等';
comment on column "JXS_PERFORMANCEFEECFG"."INPERCENTAGE" is '上下限内收取比例';
comment on column "JXS_PERFORMANCEFEECFG"."LOTNUM" is 'lotnum';
comment on column "JXS_PERFORMANCEFEECFG"."MAXLEVEL" is '上限';
comment on column "JXS_PERFORMANCEFEECFG"."MINLEVEL" is '下限';
comment on column "JXS_PERFORMANCEFEECFG"."OUTPERCENTAGE" is '上限外收取比例';
comment on column "JXS_PERFORMANCEFEECFG"."RETURNRATETYPE" is '收益率选择';
comment on column "JXS_PERFORMANCEFEECFG"."ACTIVEFLAG" is '是否激活使用 ';
comment on column "JXS_PERFORMANCEFEECFG"."CREATE_TIME" is '创建时间';
comment on column "JXS_PERFORMANCEFEECFG"."CREATE_DEPT" is '创建部门';
comment on column "JXS_PERFORMANCEFEECFG"."UPDATE_TIME" is '更新时间';
comment on column "JXS_PERFORMANCEFEECFG"."UPDATE_USER" is '更新用户';
comment on column "JXS_PERFORMANCEFEECFG"."CREATE_USER" is '创建用户';




--------------税费配置表----------------

drop table "JXS_PORTEXPENSECFG";

CREATE TABLE "JXS_PORTEXPENSECFG" 
(	
	"PORTFEEID" NUMBER(10,0) NOT NULL ENABLE,
	"PORTFEENAME" VARCHAR2(100), 	
	"PORTFOLIO" VARCHAR2(50), 
	"ORDERNUM" NUMBER(10,0),
	"STARTDATE" DATE,
	"EXPIREDATE" DATE, 
	"ACTIONITEMS" VARCHAR2(2000), 
	"CLASSSERVICE" VARCHAR2(100), 
	"CPNDATE" NUMBER(10,0), 
	"CPNMO" NUMBER(3,0), 
	"CPNFREQ" NUMBER(3), 
	"BASEACCT" VARCHAR2(32), 
	"CRACCT" VARCHAR2(32), 
	"DRACCT" VARCHAR2(32), 
	"EXPENSEAMOUNT" NUMBER(30,2), 
	"EXPENSEFLAG" NUMBER(1,0), 
	"FIXFEEFLAG" NUMBER(1,0), 
	"HIGHESTFEE" NUMBER(30,2),  
	"INTCODE" NUMBER(3,0), 
	"LOWESTFEE" NUMBER(30,2), 
	"NEWNAVFEEFLAG" NUMBER(1,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("PORTFEEID")
);


COMMENT on TABLE "JXS_PORTEXPENSECFG" is '税费配置表。通过读取该表的配置，计算投资组合各种税费。可以通过配置实现以哪天得基准来计算税费，以及通过动态增加实现类来增加税费的计算方式。ExpenseJob和AfterJob都读取该表来计算税费';
comment on column "JXS_PORTEXPENSECFG"."PORTFEEID" is '唯一主键';
comment on column "JXS_PORTEXPENSECFG"."PORTFEENAME" is '税费名称';
comment on column "JXS_PORTEXPENSECFG"."PORTFOLIO" is '投资组合系统编号,关联Portfolio_Hdr表的portfolio字段 ';
comment on column "JXS_PORTEXPENSECFG"."ORDERNUM" is '执行顺序 | 在afterjob中,根据这里配置的优先级来决定税费配置的执行顺序,使得税费有依赖关系。目前用在附加税,它是基于其它增值税凭证为基础来计算的';
comment on column "JXS_PORTEXPENSECFG"."STARTDATE" is '税费开始日期';
comment on column "JXS_PORTEXPENSECFG"."EXPIREDATE" is '截至时间';
comment on column "JXS_PORTEXPENSECFG"."ACTIONITEMS" is '用在增值税,指定应税品种。这里选取普通股,优先股等的编号，以逗号分隔 | 类似:1,2,3 每个编号关联Rp ';
comment on column "JXS_PORTEXPENSECFG"."CLASSSERVICE" is '税费的计算类名,通过选择哪个类来决定如何计算税费。这里所有的类都是实现TaxFeeJobService接口，可以通过增加类来,以及实用其他字段的配置,来实现更多的税费计算方式 ';
comment on column "JXS_PORTEXPENSECFG"."INTCODE" is '计费的日期的选择 | 65:30/360   80:30/365   87:30/act   66:act/act   68:act/360   69:act/365';
comment on column "JXS_PORTEXPENSECFG"."CPNDATE" is '税费结转的日的选择';
comment on column "JXS_PORTEXPENSECFG"."CPNMO" is '税费结转的具体月份/周选择 | 数据字典--InterestCpnMo';
comment on column "JXS_PORTEXPENSECFG"."CPNFREQ" is '税费结转频率 | 65:每年   66:到期   77：每月   79:每N天   81:每个季度   83:每半年   84:自定义计划';
comment on column "JXS_PORTEXPENSECFG"."BASEACCT" is '税费计算的基准科目';
comment on column "JXS_PORTEXPENSECFG"."CRACCT" is '税费贷方科目';
comment on column "JXS_PORTEXPENSECFG"."DRACCT" is '税费借方科目';
comment on column "JXS_PORTEXPENSECFG"."EXPENSEAMOUNT" is '税费金额/费率 | 如果fixFeeFlag字段选择固定,则是固定金额；如果是浮动,则是税费利率';
comment on column "JXS_PORTEXPENSECFG"."EXPENSEFLAG" is '应付/应收税费 | true:应付   false：应收';
comment on column "JXS_PORTEXPENSECFG"."FIXFEEFLAG" is '税费类型 | 0:固定税费   1:浮动税费';
comment on column "JXS_PORTEXPENSECFG"."HIGHESTFEE" is '最高费用';
comment on column "JXS_PORTEXPENSECFG"."LOWESTFEE" is '最低费用';
comment on column "JXS_PORTEXPENSECFG"."NEWNAVFEEFLAG" is '税费的计费基准 | 0:以当天最新的凭证/净值为基准   1:以前一天的凭证/净值为基准';
comment on column "JXS_PORTEXPENSECFG"."CREATE_TIME" is '创建时间';
comment on column "JXS_PORTEXPENSECFG"."CREATE_DEPT" is '创建部门';
comment on column "JXS_PORTEXPENSECFG"."UPDATE_TIME" is '更新时间';
comment on column "JXS_PORTEXPENSECFG"."UPDATE_USER" is '更新用户';
comment on column "JXS_PORTEXPENSECFG"."CREATE_USER" is '创建用户';






--------------投资组合/客户表----------------

drop table "JXS_PORTFOLIO_CLIENT";

CREATE TABLE "JXS_PORTFOLIO_CLIENT" 
(	
	"CLIENTID" NUMBER(10,0) NOT NULL ENABLE, 
	"CLIENTNAME" VARCHAR2(100), 
	"SHORTNAME" VARCHAR2(32), 
	"ADDRESS" VARCHAR2(1000), 
	"COMPANYTYPE" NUMBER(2,0), 
	"COUNTRYID" VARCHAR2(10), 
	"EMAILADDRESS" VARCHAR2(100), 
	"INITDATE" DATE, 
	"PHONENUMBER" VARCHAR2(32), 
	"POSTCODE" VARCHAR2(100), 
	"WEBADDRESS" VARCHAR2(200), 
	"PROVINCEID" NUMBER(10,0), 
	"ACTIVE" NUMBER(1,0),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	 PRIMARY KEY ("CLIENTID")
);


COMMENT on TABLE "JXS_PORTFOLIO_CLIENT" is '投资组合/客户表';
comment on column "JXS_PORTFOLIO_CLIENT"."CLIENTID" is '唯一主键';
comment on column "JXS_PORTFOLIO_CLIENT"."CLIENTNAME" is '客户名称';
comment on column "JXS_PORTFOLIO_CLIENT"."SHORTNAME" is '客户简称';
comment on column "JXS_PORTFOLIO_CLIENT"."ADDRESS" is '客户地址';
comment on column "JXS_PORTFOLIO_CLIENT"."COMPANYTYPE" is '客户类型 | 1:私募基金 2:公募基金 3:券商 4:信托公司 5:保险公司 6:托管银行 ';
comment on column "JXS_PORTFOLIO_CLIENT"."COUNTRYID" is '该客户所属国家编号 | 关联Country表的主键';
comment on column "JXS_PORTFOLIO_CLIENT"."EMAILADDRESS" is '客户邮箱';
comment on column "JXS_PORTFOLIO_CLIENT"."INITDATE" is '创建客户时间 ';
comment on column "JXS_PORTFOLIO_CLIENT"."PHONENUMBER" is '客户电话';
comment on column "JXS_PORTFOLIO_CLIENT"."POSTCODE" is '客户邮编';
comment on column "JXS_PORTFOLIO_CLIENT"."WEBADDRESS" is '客户官网地址';
comment on column "JXS_PORTFOLIO_CLIENT"."PROVINCEID" is '该客户所属省编号 | 关联Province表的主键 ';
comment on column "JXS_PORTFOLIO_CLIENT"."ACTIVE" is '该客户是否激活 ';
comment on column "JXS_PORTFOLIO_CLIENT"."CREATE_TIME" is '创建时间';
comment on column "JXS_PORTFOLIO_CLIENT"."CREATE_DEPT" is '创建部门';
comment on column "JXS_PORTFOLIO_CLIENT"."UPDATE_TIME" is '更新时间';
comment on column "JXS_PORTFOLIO_CLIENT"."UPDATE_USER" is '更新用户';
comment on column "JXS_PORTFOLIO_CLIENT"."CREATE_USER" is '创建用户';




--------------子组合关系表----------------

drop table "JXS_PORTFOLIOCLASSIDMAP";

CREATE TABLE "JXS_PORTFOLIOCLASSIDMAP" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE,
	"PORTID" VARCHAR2(50),	
	"CLASSID" VARCHAR2(50), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXS_PORTFOLIOCLASSIDMAP" is '投资组合和它下面的子组合之间的map对应关系';
comment on column "JXS_PORTFOLIOCLASSIDMAP"."ID" is '唯一主键';
comment on column "JXS_PORTFOLIOCLASSIDMAP"."PORTID" is '投资组合系统编号';
comment on column "JXS_PORTFOLIOCLASSIDMAP"."CLASSID" is '子投资组合系统编号';
comment on column "JXS_PORTFOLIOCLASSIDMAP"."CREATE_TIME" is '创建时间';
comment on column "JXS_PORTFOLIOCLASSIDMAP"."CREATE_DEPT" is '创建部门';
comment on column "JXS_PORTFOLIOCLASSIDMAP"."UPDATE_TIME" is '更新时间';
comment on column "JXS_PORTFOLIOCLASSIDMAP"."UPDATE_USER" is '更新用户';
comment on column "JXS_PORTFOLIOCLASSIDMAP"."CREATE_USER" is '创建用户';



--------------交易席位号----------------

drop table "JXS_SEATNUMBER";

CREATE TABLE "JXS_SEATNUMBER" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"SEATNUMBERNAME" VARCHAR2(50), 
	"BROKERID" VARCHAR2(32), 
	"SEATNUMBERACCT" VARCHAR2(50), 
	"SEATNUMBERDESC" VARCHAR2(100), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXS_SEATNUMBER" is '投资组合和它下面的子组合之间的map对应关系';
comment on column "JXS_SEATNUMBER"."ID" is '唯一主键';
comment on column "JXS_SEATNUMBER"."SEATNUMBERNAME" is '投资组合系统编号';
comment on column "JXS_SEATNUMBER"."BROKERID" is '子投资组合系统编号';
comment on column "JXS_SEATNUMBER"."SEATNUMBERACCT" is '子投资组合系统编号';
comment on column "JXS_SEATNUMBER"."SEATNUMBERDESC" is '子投资组合系统编号';
comment on column "JXS_SEATNUMBER"."CREATE_TIME" is '创建时间';
comment on column "JXS_SEATNUMBER"."CREATE_DEPT" is '创建部门';
comment on column "JXS_SEATNUMBER"."UPDATE_TIME" is '更新时间';
comment on column "JXS_SEATNUMBER"."UPDATE_USER" is '更新用户';
comment on column "JXS_SEATNUMBER"."CREATE_USER" is '创建用户';



--------------证券类型表----------------

drop table "JXS_SECURITYIDTYPE";

CREATE TABLE "JXS_SECURITYIDTYPE" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE,  
	"ALLOWSPACEFLAG" NUMBER(1,0), 
	"DUPLICATEFLAG" NUMBER(1,0), 
	"FIELDLENGTH" NUMBER(10,0), 
	"IDDESC" VARCHAR2(100), 
	"SECIDTYPENAME" VARCHAR2(50), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXS_SECURITYIDTYPE" is '尚未使用该表';



--------------证券类型关系表----------------

drop table "JXS_SECURITYIDTYPEMAP";

CREATE TABLE "JXS_SECURITYIDTYPEMAP" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"SECIDTYPE" NUMBER(10,0),
	"SECID" VARCHAR2(32), 
	"ALIASNAME" VARCHAR2(100), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);

COMMENT on TABLE "JXS_SECURITYIDTYPEMAP" is '尚未使用该表';




--------------用户/客户关系表----------------

drop table "JXS_USERCLIENTMAP";

CREATE TABLE "JXS_USERCLIENTMAP" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"CLIENTID" NUMBER(10,0), 
	"USERID" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXS_USERCLIENTMAP" is '用户/客户关系表';
comment on column "JXS_USERCLIENTMAP"."ID" is '唯一主键';
comment on column "JXS_USERCLIENTMAP"."CLIENTID" is '客户编号 | 关联Client表主键 ';
comment on column "JXS_USERCLIENTMAP"."USERID" is '用户编号 | 关联User表主键 ';
comment on column "JXS_USERCLIENTMAP"."CREATE_TIME" is '创建时间';
comment on column "JXS_USERCLIENTMAP"."CREATE_DEPT" is '创建部门';
comment on column "JXS_USERCLIENTMAP"."UPDATE_TIME" is '更新时间';
comment on column "JXS_USERCLIENTMAP"."UPDATE_USER" is '更新用户';
comment on column "JXS_USERCLIENTMAP"."CREATE_USER" is '创建用户';




--------------用户/投资组合关系表----------------

drop table "JXS_USERPORTFOLIOMAP";

CREATE TABLE "JXS_USERPORTFOLIOMAP" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"PORTFOLIO" VARCHAR2(50), 
	"USERID" NUMBER(10,0),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),	
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXS_USERPORTFOLIOMAP" is '用户/客户关系表';
comment on column "JXS_USERPORTFOLIOMAP"."ID" is '唯一主键';
comment on column "JXS_USERPORTFOLIOMAP"."PORTFOLIO" is '投资组合 | 关联Portfolio_hdr表主键 ';
comment on column "JXS_USERPORTFOLIOMAP"."USERID" is '用户编号 | 关联User表主键 ';
comment on column "JXS_USERPORTFOLIOMAP"."CREATE_TIME" is '创建时间';
comment on column "JXS_USERPORTFOLIOMAP"."CREATE_DEPT" is '创建部门';
comment on column "JXS_USERPORTFOLIOMAP"."UPDATE_TIME" is '更新时间';
comment on column "JXS_USERPORTFOLIOMAP"."UPDATE_USER" is '更新用户';
comment on column "JXS_USERPORTFOLIOMAP"."CREATE_USER" is '创建用户';





-------------证券派息记录-------------

drop table "JXT_TRAN_ACCRUEDREC";

CREATE TABLE "JXT_TRAN_ACCRUEDREC" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"ACCRUEDDATE" DATE NOT NULL ENABLE, 
	"BASEDDATE" DATE NOT NULL ENABLE, 
	"CUSIP" VARCHAR2(32) NOT NULL ENABLE, 
	"LASTTRANNUM" NUMBER(10,0) NOT NULL ENABLE, 
	"LOTNUM" NUMBER(10,0) NOT NULL ENABLE, 
	"PORTNUM" VARCHAR2(50), 
	"TRANTANUM" VARCHAR2(32) NOT NULL ENABLE,
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),		
	PRIMARY KEY ("ID")
); 


COMMENT on TABLE "JXT_TRAN_ACCRUEDREC" is '用户/客户关系表';
comment on column "JXT_TRAN_ACCRUEDREC"."ID" is '';
comment on column "JXT_TRAN_ACCRUEDREC"."ACCRUEDDATE" is '派息日';
comment on column "JXT_TRAN_ACCRUEDREC"."BASEDDATE" is '基于哪一天派息';
comment on column "JXT_TRAN_ACCRUEDREC"."CUSIP" is '投资组合系统编号,关联Issue_info表主键 ';
comment on column "JXT_TRAN_ACCRUEDREC"."LASTTRANNUM" is '某个头寸的最后一个交易编号，同一头寸，每增加一次交易，lasttrannum+1 ';
comment on column "JXT_TRAN_ACCRUEDREC"."LOTNUM" is '同一个产品投资同一个证券,因为证券账户、基金经理等不同，区分头寸的标记 | 关联Tran_Basic表的lot字段 ';
comment on column "JXT_TRAN_ACCRUEDREC"."PORTNUM" is '投资组合';
comment on column "JXT_TRAN_ACCRUEDREC"."TRANTANUM" is '该交易的交易编号，同一头寸，新增一笔交易，交易编号为lastTranNum+1 ';
comment on column "JXT_TRAN_ACCRUEDREC"."CREATE_TIME" is '创建时间';
comment on column "JXT_TRAN_ACCRUEDREC"."CREATE_DEPT" is '创建部门';
comment on column "JXT_TRAN_ACCRUEDREC"."UPDATE_TIME" is '更新时间';
comment on column "JXT_TRAN_ACCRUEDREC"."UPDATE_USER" is '更新用户';
comment on column "JXT_TRAN_ACCRUEDREC"."CREATE_USER" is '创建用户';




-------------抵押物汇总-------------

drop table "JXT_TRAN_COLLATERALSUM";

CREATE TABLE "JXT_TRAN_COLLATERALSUM" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"AMOUNT" FLOAT(126), 
	"COLLATERALID" NUMBER(10,0), 
	"LASTTRANNUM" NUMBER(10,0), 
	"LINK" NUMBER(10,0), 
	"LOCATION" NUMBER(10,0), 
	"TRANTYPE" NUMBER(3,0), 
	"PORTFOLIO" VARCHAR2(50), 
	"TANUM" VARCHAR2(32), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),	
	 PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXT_TRAN_COLLATERALSUM" is '尚未使用该表';



------------申赎交易表-----------

drop table "JXT_TRAN_SUBRED";

CREATE TABLE "JXT_TRAN_SUBRED" 
(	
	"TRANID" NUMBER(10,0) NOT NULL ENABLE, 
	"PORTFOLIO" VARCHAR2(50), 
	"TRANTYPE" NUMBER(3,0), 
	"AMOUNT" NUMBER(30,2),
	"CASH" NUMBER(30,2), 
	"CLASSID" NUMBER(10,0), 
	"TANUM" VARCHAR2(32 BYTE), 
	"EFFECTIVEDATE" DATE, 
	"FXRATE" NUMBER(30,14),
	"GCOST" NUMBER(30,2),
	"LEDGERREVERSED" NUMBER(10,0), 
	"LEDGERIN" DATE, 
	"NAV" NUMBER(30,14), 
	"SETTLEMENTDATE" DATE, 
	"TRADEDATE" DATE, 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("TRANID")
);


COMMENT on TABLE "JXT_TRAN_SUBRED" is '申赎交易表';
comment on column "JXT_TRAN_SUBRED"."TRANID" is '唯一主键';
comment on column "JXT_TRAN_SUBRED"."PORTFOLIO" is '投资组合系统编号,关联Portfolio_Hdr表主键';
comment on column "JXT_TRAN_SUBRED"."TRANTYPE" is '交易类型 | 数据字典--交易类型';
comment on column "JXT_TRAN_SUBRED"."AMOUNT" is '交易数量';
comment on column "JXT_TRAN_SUBRED"."CASH" is '交易金额，包括税费';
comment on column "JXT_TRAN_SUBRED"."CLASSID" is '母基金编号，用于分级基金，目前尚未使用';
comment on column "JXT_TRAN_SUBRED"."TANUM" is '交易编号 | 关联Tran_Basic表主键';
comment on column "JXT_TRAN_SUBRED"."EFFECTIVEDATE" is '尚未使用';
comment on column "JXT_TRAN_SUBRED"."FXRATE" is '交易汇率';
comment on column "JXT_TRAN_SUBRED"."GCOST" is '交易成本';
comment on column "JXT_TRAN_SUBRED"."LEDGERREVERSED" is '撤帐日';
comment on column "JXT_TRAN_SUBRED"."LEDGERIN" is '入账日';
comment on column "JXT_TRAN_SUBRED"."NAV" is '交易的单位净值';
comment on column "JXT_TRAN_SUBRED"."TRADEDATE" is '交易日';
comment on column "JXT_TRAN_SUBRED"."SETTLEMENTDATE" is '结算日';
comment on column "JXT_TRAN_SUBRED"."CREATE_TIME" is '创建时间';
comment on column "JXT_TRAN_SUBRED"."CREATE_DEPT" is '创建部门';
comment on column "JXT_TRAN_SUBRED"."UPDATE_TIME" is '更新时间';
comment on column "JXT_TRAN_SUBRED"."UPDATE_USER" is '更新用户';
comment on column "JXT_TRAN_SUBRED"."CREATE_USER" is '创建用户';



--------------费用每日关账记录---------------

drop table "JXT_TRANFEEFOLLOWCLO";

CREATE TABLE "JXT_TRANFEEFOLLOWCLO" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"PORTNUM" VARCHAR2(50), 
	"CUSIP" VARCHAR2(32),
	"LOTNUM" NUMBER(10,0), 	
	"CLOSEDATE" DATE, 
	"TRANCLOID" VARCHAR2(32), 
	"TRANFEEDEFID" NUMBER(10,0), 
	"AMOUNT" NUMBER(30,2),
	"FEERATE" NUMBER(30,14),
	"FEEAMOUNT" NUMBER(30,2),
	"FEEAMOUNTTODAY" NUMBER(30,2),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);



COMMENT on TABLE "JXT_TRANFEEFOLLOWCLO" is '费用每日关账记录';
comment on column "JXT_TRANFEEFOLLOWCLO"."ID" is '唯一主键';
comment on column "JXT_TRANFEEFOLLOWCLO"."PORTNUM" is '投资组合';
comment on column "JXT_TRANFEEFOLLOWCLO"."CUSIP" is '证券系统代码';
comment on column "JXT_TRANFEEFOLLOWCLO"."LOTNUM" is '批次编号';
comment on column "JXT_TRANFEEFOLLOWCLO"."CLOSEDATE" is '关账日';
comment on column "JXT_TRANFEEFOLLOWCLO"."TRANCLOID" is '交易关账id';
comment on column "JXT_TRANFEEFOLLOWCLO"."TRANFEEDEFID" is '费用定义id | JXS_PORTEXPENSECFG表主键';
comment on column "JXT_TRANFEEFOLLOWCLO"."AMOUNT" is '数量';
comment on column "JXT_TRANFEEFOLLOWCLO"."FEERATE" is '费率';
comment on column "JXT_TRANFEEFOLLOWCLO"."FEEAMOUNT" is '费用金额';
comment on column "JXT_TRANFEEFOLLOWCLO"."FEEAMOUNTTODAY" is '当天的费用金额';
comment on column "JXT_TRANFEEFOLLOWCLO"."CREATE_TIME" is '创建时间';
comment on column "JXT_TRANFEEFOLLOWCLO"."CREATE_DEPT" is '创建部门';
comment on column "JXT_TRANFEEFOLLOWCLO"."UPDATE_TIME" is '更新时间';
comment on column "JXT_TRANFEEFOLLOWCLO"."UPDATE_USER" is '更新用户';
comment on column "JXT_TRANFEEFOLLOWCLO"."CREATE_USER" is '创建用户';




--------------净值表---------------

drop table "JXV_NAV";

CREATE TABLE "JXV_NAV" 
(	
	"NAVID" NUMBER(10,0) NOT NULL ENABLE, 
	"PORTFOLIO" VARCHAR2(50),
	"NAVDATE" DATE, 
	"ASSETS" NUMBER(30,2),
	"CAPITAL" NUMBER(30,2),
	"CAPITALRG" NUMBER(30,2),
	"CAPITALUNREAL" NUMBER(30,2),
	"CLASSID" NUMBER(10,0), 
	"DAILYDIVDISTRATE" NUMBER(30,14),
	"DISTCG" NUMBER(30,2),
	"EQUALIZATIONRATE" NUMBER(30,14),
	"EXPENSES" NUMBER(30,2),
	"INCOME" NUMBER(30,2),
	"INCOMEDIST" NUMBER(30,2),
	"INITFLAG" NUMBER(1,0), 
	"LIABILITIES" NUMBER(30,2),
	"MAXOFFERINGPRICE" NUMBER(30,14),
	"NAVAMOUNT" NUMBER(30,14),
	"NUMBEROFDAYSINSUM" NUMBER(10,0), 
	"SHARESOUTSTANDING" NUMBER(30,2),
	"SHARESSETTLED" NUMBER(30,2),
	"SUMOFDAILYNETASSETS" NUMBER(30,2),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),	
	PRIMARY KEY ("NAVID")
);


COMMENT on TABLE "JXV_NAV" is '费用每日关账记录';
comment on column "JXV_NAV"."NAVID" is '唯一主键';
comment on column "JXV_NAV"."PORTFOLIO" is '投资组合系统编号,关联Portfolio_Hdr表的portfolio字段';
comment on column "JXV_NAV"."ASSETS" is '资产';
comment on column "JXV_NAV"."CAPITAL" is '资本';
comment on column "JXV_NAV"."CAPITALRG" is '已实现收益';
comment on column "JXV_NAV"."CAPITALUNREAL" is '未实现收益';
comment on column "JXV_NAV"."CLASSID" is '尚未使用 | 分级基金使用';
comment on column "JXV_NAV"."DAILYDIVDISTRATE" is '利率--日分配';
comment on column "JXV_NAV"."DISTCG" is '未实现收益';
comment on column "JXV_NAV"."EQUALIZATIONRATE" is '利率--均等化';
comment on column "JXV_NAV"."EXPENSES" is '开支';
comment on column "JXV_NAV"."INCOME" is '收入';
comment on column "JXV_NAV"."INCOMEDIST" is '尚未使用';
comment on column "JXV_NAV"."INITFLAG" is '尚未使用';
comment on column "JXV_NAV"."LIABILITIES" is '负债';
comment on column "JXV_NAV"."MAXOFFERINGPRICE" is '尚未使用 | For front sales load funds, the calculated price for the day (NAV / (1 - sales load)).';
comment on column "JXV_NAV"."NAVAMOUNT" is '单位净值';
comment on column "JXV_NAV"."NAVDATE" is '投资组合的净值日期';
comment on column "JXV_NAV"."NUMBEROFDAYSINSUM" is '天数';
comment on column "JXV_NAV"."SHARESOUTSTANDING" is '份额总计';
comment on column "JXV_NAV"."SHARESSETTLED" is '已经结转的份额';
comment on column "JXV_NAV"."SUMOFDAILYNETASSETS" is '总计 - 日净资产';
comment on column "JXV_NAV"."CREATE_TIME" is '创建时间';
comment on column "JXV_NAV"."CREATE_DEPT" is '创建部门';
comment on column "JXV_NAV"."UPDATE_TIME" is '更新时间';
comment on column "JXV_NAV"."UPDATE_USER" is '更新用户';
comment on column "JXV_NAV"."CREATE_USER" is '创建用户';



-------------资管资金池每日净资产------------


drop table "JXV_NAVBANKZIGUAN";

CREATE TABLE "JXV_NAVBANKZIGUAN" 
(	
	"NAVID" NUMBER(10,0) NOT NULL ENABLE, 
	"PORTNUM" VARCHAR2(50),
	"TRANCLOID" VARCHAR2(32), 
	"CUSIP" VARCHAR2(32), 
	"LOTNUM" NUMBER(10,0), 
	"GCOST" NUMBER(30,2),
	"INCOMEWF" NUMBER(30,14),
	"NAV" NUMBER(30,14),
	"NAVDATE" DATE, 
	"NAVEXTERNAL" NUMBER(30,2),
	"NAVINCREASE" NUMBER(30,2),
	"NAVTODAYINCREASE" NUMBER(30,2),
	"NETASSET" NUMBER(30,2), 
	"SHARESENTERNAL" NUMBER(30,2),
	"SHARESINTERNAL" NUMBER(30,2),
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("NAVID")
);



COMMENT on TABLE "JXV_NAVBANKZIGUAN" is '费用每日关账记录';
comment on column "JXV_NAVBANKZIGUAN"."NAVID" is '唯一主键';
comment on column "JXV_NAVBANKZIGUAN"."PORTNUM" is '投资组合编号';
comment on column "JXV_NAVBANKZIGUAN"."TRANCLOID" is '关帐记录的ID';
comment on column "JXV_NAVBANKZIGUAN"."CUSIP" is '资管产品在smf的id';
comment on column "JXV_NAVBANKZIGUAN"."LOTNUM" is '批次';
comment on column "JXV_NAVBANKZIGUAN"."GCOST" is '原始申购时的金额';
comment on column "JXV_NAVBANKZIGUAN"."INCOMEWF" is '万份收益';
comment on column "JXV_NAVBANKZIGUAN"."NAV" is '单位净值';
comment on column "JXV_NAVBANKZIGUAN"."NAVDATE" is '日期';
comment on column "JXV_NAVBANKZIGUAN"."NAVEXTERNAL" is '对外的单位净值';
comment on column "JXV_NAVBANKZIGUAN"."NAVINCREASE" is '总的净值增长率';
comment on column "JXV_NAVBANKZIGUAN"."NAVTODAYINCREASE" is '当天净值增长率';
comment on column "JXV_NAVBANKZIGUAN"."NETASSET" is '净资产';
comment on column "JXV_NAVBANKZIGUAN"."SHARESENTERNAL" is '产品对外的份额,申购时份额=金额';
comment on column "JXV_NAVBANKZIGUAN"."SHARESINTERNAL" is '产品在组合内占有的份额';
comment on column "JXV_NAVBANKZIGUAN"."CREATE_TIME" is '创建时间';
comment on column "JXV_NAVBANKZIGUAN"."CREATE_DEPT" is '创建部门';
comment on column "JXV_NAVBANKZIGUAN"."UPDATE_TIME" is '更新时间';
comment on column "JXV_NAVBANKZIGUAN"."UPDATE_USER" is '更新用户';
comment on column "JXV_NAVBANKZIGUAN"."CREATE_USER" is '创建用户';



-------------估值表------------

drop table "JXV_VALUATIONREPORT";

CREATE TABLE "JXV_VALUATIONREPORT" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE,
	"PORTNUM" VARCHAR2(50),    
	"PORTCODE" VARCHAR2(50), 
	"PORTNAME" VARCHAR2(100),
	"SECURITYID" VARCHAR2(32),
	"SECMASTERID" VARCHAR2(32),  	
	"ACCNTCATEGORY" NUMBER(10,0), 
	"ACCNTISLEAF" NUMBER(1,0), 
	"ACCNTLEVEL" NUMBER(3,0), 
	"ACCNTNUM" VARCHAR2(32),
	"ACCNTNAME" VARCHAR2(100), 
	"AMOUNT" NUMBER(30,2), 
	"ASOFDATE" DATE, 
	"COSTFUNC" NUMBER(30,2),
	"COSTLOCAL" NUMBER(30,2), 
	"COSTPERUNITFUNC" NUMBER(30,2),
	"COSTPERUNITLOCAL" NUMBER(30,2),
	"COSTPERCENT" NUMBER(30,14),
	"CURRENCY" VARCHAR2(10), 
	"CURRENCYCODE" VARCHAR2(10), 
	"FXRATE" NUMBER(30,14),
	"PRICEFUNC" NUMBER(30,14), 
	"PRICELOCAL" NUMBER(30,14), 
	"GAINLOSSFUNC" NUMBER(30,2),
	"GAINLOSSLOCAL" NUMBER(30,2),
	"INTERESTINFO" VARCHAR2(100), 
	"LASTLEVELACCNTNUM" VARCHAR2(10), 
	"MARKETVALUEFUNC" NUMBER(30,2), 
	"MARKETVALUELOCAL" NUMBER(30,2),
	"MARKETVALUEPERCENT" NUMBER(30,14), 
	"SECURITYSTATUSDESC" VARCHAR2(100), 
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXV_VALUATIONREPORT" is '费用每日关账记录';
comment on column "JXV_VALUATIONREPORT"."PORTNUM" is '投资组合系统编号';
comment on column "JXV_VALUATIONREPORT"."PORTCODE" is '投资组合代码';
comment on column "JXV_VALUATIONREPORT"."PORTNAME" is '投资组合名称';
comment on column "JXV_VALUATIONREPORT"."ACCNTCATEGORY" is '科目归类';
comment on column "JXV_VALUATIONREPORT"."ACCNTISLEAF" is '是否末级科目';
comment on column "JXV_VALUATIONREPORT"."ACCNTLEVEL" is '科目登记';
comment on column "JXV_VALUATIONREPORT"."ACCNTNUM" is '科目号';
comment on column "JXV_VALUATIONREPORT"."ACCNTNAME" is '科目名称';
comment on column "JXV_VALUATIONREPORT"."AMOUNT" is '数量';
comment on column "JXV_VALUATIONREPORT"."ASOFDATE" is '估值日期';
comment on column "JXV_VALUATIONREPORT"."COSTFUNC" is '成本(本币)';
comment on column "JXV_VALUATIONREPORT"."COSTLOCAL" is '成本(原币)';
comment on column "JXV_VALUATIONREPORT"."COSTPERUNITFUNC" is '单元成本(本币)';
comment on column "JXV_VALUATIONREPORT"."COSTPERUNITLOCAL" is '单元成本(原币)';
comment on column "JXV_VALUATIONREPORT"."COSTPERCENT" is '成本占比';
comment on column "JXV_VALUATIONREPORT"."CURRENCY" is '货币';
comment on column "JXV_VALUATIONREPORT"."CURRENCYCODE" is '货币代码';
comment on column "JXV_VALUATIONREPORT"."FXRATE" is '汇率';
comment on column "JXV_VALUATIONREPORT"."GAINLOSSFUNC" is '估增(本币)';
comment on column "JXV_VALUATIONREPORT"."GAINLOSSLOCAL" is '估增(原币)';
comment on column "JXV_VALUATIONREPORT"."INTERESTINFO" is '信息';
comment on column "JXV_VALUATIONREPORT"."LASTLEVELACCNTNUM" is '';
comment on column "JXV_VALUATIONREPORT"."MARKETVALUEFUNC" is '市值(本币)';
comment on column "JXV_VALUATIONREPORT"."MARKETVALUELOCAL" is '市值(原币)';
comment on column "JXV_VALUATIONREPORT"."MARKETVALUEPERCENT" is '市值占比';
comment on column "JXV_VALUATIONREPORT"."PRICEFUNC" is '价格(本币)';
comment on column "JXV_VALUATIONREPORT"."PRICELOCAL" is '价格(原币)';
comment on column "JXV_VALUATIONREPORT"."SECMASTERID" is '证券代码';
comment on column "JXV_VALUATIONREPORT"."SECURITYID" is '证券代码';
comment on column "JXV_VALUATIONREPORT"."SECURITYSTATUSDESC" is '证券描述';



-------------凭证主表------------

drop table "JXV_VOUCHER";

CREATE TABLE "JXV_VOUCHER" 
(	
	"VOUCHERNUM" VARCHAR2(32) NOT NULL ENABLE, 
	"PORTFOLIONUM" VARCHAR2(50), 
	"LEDGERDATE" DATE, 
	"JOURNALSOURCECODE" NUMBER(3,0), 
	"MANAGERGROUPCODE" NUMBER(3,0), 
	"TRADEDATE" DATE, 
	"SETTLEMENTDATE" DATE, 
	"TANUM" VARCHAR2(32), 
	"STAMPDATE" DATE, 
	"STAMPUSER" VARCHAR2(32), 
	"AFFIRMDATE" DATE, 
	"AFFIRMUSER" VARCHAR2(32), 
	"DESCRIPTION" VARCHAR2(1000), 
	"ISAUDIT" NUMBER(1,0), 
	"ISAUDITMSGVALID" NUMBER(1,0),
	"AUDITDATE" DATE, 
	"AUDITMSG" VARCHAR2(1000), 
	"AUDITUSER" VARCHAR2(32), 
	"IMPORTEDDATE" DATE, 
	"IMPORTEDUSER" VARCHAR2(32), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	 PRIMARY KEY ("VOUCHERNUM")
);



COMMENT on TABLE "JXV_VOUCHER" is '凭证主表';
comment on column "JXV_VOUCHER"."VOUCHERNUM" is '';
comment on column "JXV_VOUCHER"."AFFIRMDATE" is '尚未使用';
comment on column "JXV_VOUCHER"."AFFIRMUSER" is '尚未使用';
comment on column "JXV_VOUCHER"."AUDITDATE" is '审核日期';
comment on column "JXV_VOUCHER"."AUDITMSG" is '审核消息';
comment on column "JXV_VOUCHER"."AUDITUSER" is '审核用户';
comment on column "JXV_VOUCHER"."DESCRIPTION" is '描述';
comment on column "JXV_VOUCHER"."IMPORTEDDATE" is '操作任务生成凭证的系统时间 ';
comment on column "JXV_VOUCHER"."IMPORTEDUSER" is '操作任务生成凭证的用户 | 关联User表主键 ';
comment on column "JXV_VOUCHER"."ISAUDIT" is '是否已被审核过 ';
comment on column "JXV_VOUCHER"."ISAUDITMSGVALID" is '尚未使用';
comment on column "JXV_VOUCHER"."JOURNALSOURCECODE" is '凭证类型 | 7:估值增值日记账 15:交易日记账 9:申赎日记账 17:收入日记账 14：现金日记账 19:分红日记账 18:结算日记账 99:手工日记账 ';
comment on column "JXV_VOUCHER"."LEDGERDATE" is '出账日期';
comment on column "JXV_VOUCHER"."MANAGERGROUPCODE" is '尚未使用 | 经理组编号';
comment on column "JXV_VOUCHER"."PORTFOLIONUM" is '投资组合系统编号,关联Portfolio_Hdr表主键 ';
comment on column "JXV_VOUCHER"."TRADEDATE" is '交易日期';
comment on column "JXV_VOUCHER"."SETTLEMENTDATE" is '结转日期';
comment on column "JXV_VOUCHER"."STAMPDATE" is '尚未使用';
comment on column "JXV_VOUCHER"."STAMPUSER" is '尚未使用';
comment on column "JXV_VOUCHER"."TANUM" is '如果有相关交易产生的凭证，则关联交易编号 | 关联Tran_Basic表主键 ';
comment on column "JXV_VOUCHER"."CREATE_TIME" is '创建时间';
comment on column "JXV_VOUCHER"."CREATE_DEPT" is '创建部门';
comment on column "JXV_VOUCHER"."UPDATE_TIME" is '更新时间';
comment on column "JXV_VOUCHER"."UPDATE_USER" is '更新用户';
comment on column "JXV_VOUCHER"."CREATE_USER" is '创建用户';




-------------凭证明细表------------

drop table "JXV_VOUCHERDETAILS"; 

CREATE TABLE "JXV_VOUCHERDETAILS" 
(	
	"VOUCH_SUBNUM" VARCHAR2(32) NOT NULL ENABLE, 
	"VOUCH_NUM" VARCHAR2(32), 
	"SUBJECT_NO" VARCHAR2(32), 
	"AMOUNT" NUMBER(30,2),
	"BUSIDETAILTYPE" VARCHAR2(32), 
	"BUSIID" VARCHAR2(32), 
	"CD_FLAG" VARCHAR2(2), 
	"CHARTNUM" VARCHAR2(32), 
	"CR_FUNC" NUMBER(30,2), 
	"CR_LOCAL" NUMBER(30,2), 
	"CURFXRATE" NUMBER(30,14),
	"DR_FUNC" NUMBER(30,2),
	"DR_LOCAL" NUMBER(30,14), 
	"FUNCTIONCURRENCY" VARCHAR2(10), 
	"FXRATE" NUMBER(30,14),
	"ISADJUSTED" NUMBER(1,0), 
	"BUS_VOUCHSUBNUM" VARCHAR2(32), 
	"LOCALCURRENCY" VARCHAR2(10), 
	"PRICE" NUMBER(30,14),
	"DETAIL_SUBJECT_NO" VARCHAR2(32), 
	"SECURITYTYPECODE" VARCHAR2(10), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("VOUCH_SUBNUM")
);


COMMENT on TABLE "JXV_VOUCHERDETAILS" is '凭证明细表';
comment on column "JXV_VOUCHERDETAILS"."VOUCH_SUBNUM" is '明细表主键';
comment on column "JXV_VOUCHERDETAILS"."VOUCH_NUM" is '凭证编号，关联Voucher表主键';
comment on column "JXV_VOUCHERDETAILS"."SUBJECT_NO" is '科目号';
comment on column "JXV_VOUCHERDETAILS"."AMOUNT" is '数量';
comment on column "JXV_VOUCHERDETAILS"."BUSIDETAILTYPE" is '业务明细类型';
comment on column "JXV_VOUCHERDETAILS"."BUSIID" is '业务代码';
comment on column "JXV_VOUCHERDETAILS"."CD_FLAG" is '借贷';
comment on column "JXV_VOUCHERDETAILS"."CHARTNUM" is '科目表';
comment on column "JXV_VOUCHERDETAILS"."CR_FUNC" is '贷方金额(本币)';
comment on column "JXV_VOUCHERDETAILS"."CR_LOCAL" is '贷方金额(原币)';
comment on column "JXV_VOUCHERDETAILS"."CURFXRATE" is '汇率';
comment on column "JXV_VOUCHERDETAILS"."DR_FUNC" is '借方金额(本币)';
comment on column "JXV_VOUCHERDETAILS"."DR_LOCAL" is '借方金额(原币)';
comment on column "JXV_VOUCHERDETAILS"."FUNCTIONCURRENCY" is '本币币种';
comment on column "JXV_VOUCHERDETAILS"."FXRATE" is '汇率';
comment on column "JXV_VOUCHERDETAILS"."ISADJUSTED" is '是否手工调整';
comment on column "JXV_VOUCHERDETAILS"."BUS_VOUCHSUBNUM" is '';
comment on column "JXV_VOUCHERDETAILS"."LOCALCURRENCY" is '原币币种';
comment on column "JXV_VOUCHERDETAILS"."PRICE" is '价格';
comment on column "JXV_VOUCHERDETAILS"."DETAIL_SUBJECT_NO" is '明细科目号';
comment on column "JXV_VOUCHERDETAILS"."SECURITYTYPECODE" is '证券类型';
comment on column "JXV_VOUCHERDETAILS"."CREATE_TIME" is '创建时间';
comment on column "JXV_VOUCHERDETAILS"."CREATE_DEPT" is '创建部门';
comment on column "JXV_VOUCHERDETAILS"."UPDATE_TIME" is '更新时间';
comment on column "JXV_VOUCHERDETAILS"."UPDATE_USER" is '更新用户';
comment on column "JXV_VOUCHERDETAILS"."CREATE_USER" is '创建用户';








--------------台账----------------

drop table "JXV_YGZTAIZHANG";

CREATE TABLE "JXV_YGZTAIZHANG" 
(
	"ID" NUMBER(10,0) NOT NULL ENABLE,
	"PORTNUM" VARCHAR2(50), 	
	"LEDGERDATE" DATE, 
	"CUSIP" VARCHAR2(32), 
	"DRDKJE" NUMBER(30,2),
	"DRKMYE" NUMBER(30,2),
	"DRPZJT" NUMBER(30,2),
	"DRSFFSE" NUMBER(30,2),
	"DRSFJZFSE" NUMBER(30,2),
	"DRSFJZYE" NUMBER(30,2),
	"DRSFYE" NUMBER(30,2),
	"EXCHANGEID" VARCHAR2(32), 
	"ISSUETYPE" VARCHAR2(10), 
	"LSDKJE" NUMBER(30,2),
	"LSJZYE" NUMBER(30,2),
	"RPSECTYPE" NUMBER(10,0), 
	"SYRKMYE" NUMBER(30,2), 
	"SYRSFJZYE" NUMBER(30,2), 
	"SYRSFYE" NUMBER(30,2),
	"TAXFEETYPE" NUMBER(3,0), 
	"YSFS" NUMBER(1,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20),
	PRIMARY KEY ("ID")
);


COMMENT on TABLE "JXV_YGZTAIZHANG" is '凭证明细表';
comment on column "JXV_YGZTAIZHANG"."PORTNUM" is '投资组合编号，关联Portfolio_hdr表的ID字段';
comment on column "JXV_YGZTAIZHANG"."LEDGERDATE" is '台账日期';
comment on column "JXV_YGZTAIZHANG"."CUSIP" is '系统内部证券ID, 关联IssueInfo表的ID字段 ';
comment on column "JXV_YGZTAIZHANG"."DRDKJE" is '当日抵扣金额';
comment on column "JXV_YGZTAIZHANG"."DRKMYE" is '当日科目余额';
comment on column "JXV_YGZTAIZHANG"."DRPZJT" is '当日凭证计提';
comment on column "JXV_YGZTAIZHANG"."DRSFFSE" is '当日税费发生额';
comment on column "JXV_YGZTAIZHANG"."DRSFJZFSE" is '当日税费基准发生额';
comment on column "JXV_YGZTAIZHANG"."DRSFJZYE" is '当日税费基准余额';
comment on column "JXV_YGZTAIZHANG"."DRSFYE" is '当日税费余额';
comment on column "JXV_YGZTAIZHANG"."EXCHANGEID" is '交易所编码, 关联ExchangeCfg表的Code字段 ';
comment on column "JXV_YGZTAIZHANG"."ISSUETYPE" is '证券类型, 查询数据字典的AssetTypes表 ';
comment on column "JXV_YGZTAIZHANG"."LSDKJE" is '历史抵扣金额 ';
comment on column "JXV_YGZTAIZHANG"."LSJZYE" is '历史基准余额';
comment on column "JXV_YGZTAIZHANG"."RPSECTYPE" is '自定义的报表行业分类,普通股、优先股等,在这里决定了是否应税。如果该字段为0,则表示这是一行汇总记录。如果不为0，则关联RpSecType表的ID字段 ';
comment on column "JXV_YGZTAIZHANG"."SYRKMYE" is '上一日科目余额';
comment on column "JXV_YGZTAIZHANG"."SYRSFJZYE" is '上一日税费基准余额 ';
comment on column "JXV_YGZTAIZHANG"."SYRSFYE" is '上一日税费余额 ';
comment on column "JXV_YGZTAIZHANG"."TAXFEETYPE" is '自定义的税费类型ID, 关联PortExpenseCfg表的portFeeID字段 ';
comment on column "JXV_YGZTAIZHANG"."YSFS" is '应税方式, true(1)为应税，false(0)为免税 ';
comment on column "JXV_YGZTAIZHANG"."CREATE_TIME" is '创建时间';
comment on column "JXV_YGZTAIZHANG"."CREATE_DEPT" is '创建部门';
comment on column "JXV_YGZTAIZHANG"."UPDATE_TIME" is '更新时间';
comment on column "JXV_YGZTAIZHANG"."UPDATE_USER" is '更新用户';
comment on column "JXV_YGZTAIZHANG"."CREATE_USER" is '创建用户';



drop SEQUENCE "JXC_COA_ID_SEQ";
drop SEQUENCE "COAA_ID_SEQ";
drop SEQUENCE "COAAGROUP_ID_SEQ";
drop SEQUENCE "GLCOAAMAP_ID_SEQ";
drop SEQUENCE "RPSECTYPE_ID_SEQ";
drop SEQUENCE "TRANFEEDEF_ID_SEQ";
drop SEQUENCE "TRANFEETYPEMAP_ID_SEQ";
drop SEQUENCE "BKINFO_ID_SEQ";
drop SEQUENCE "BROKER_ID_SEQ";
drop SEQUENCE "EXCHANGECFG_ID_SEQ"
drop SEQUENCE "ISSUER_ID_SEQ";
drop SEQUENCE "JXV_VOUCHER_ID_SEQ";
drop SEQUENCE "BKCASHCLO_ID_SEQ";
drop SEQUENCE "BKINTEACCT_ID_SEQ";
drop SEQUENCE "BKINTERATIO_ID_SEQ";
drop SEQUENCE "BKINTESETHIS_ID_SEQ";
drop SEQUENCE "CLOSEHIS_ID_SEQ";
drop SEQUENCE "FXRATE_ID_SEQ";
drop SEQUENCE "GLCLOSE_ID_SEQ";
drop SEQUENCE "INTERESTRCPY_ID_SEQ";
drop SEQUENCE "JOBSHIS_ID_SEQ";
drop SEQUENCE "MANAGERGROUP_ID_SEQ";
drop SEQUENCE "MANAGERS_ID_SEQ";
drop SEQUENCE "NAV_ID_SEQ";
drop SEQUENCE "NEWFUND_ID_SEQ";
drop SEQUENCE "NEWFUNDCONTENT_ID_SEQ";
drop SEQUENCE "PERFORMANCEFEECFG_ID_SEQ";
drop SEQUENCE "PORTEXPENSECFG_ID_SEQ";
drop SEQUENCE "NAVBANKZIGUAN_ID_SEQ";
drop SEQUENCE "PORTFOLIOCLASSIDMAP_ID_SEQ";
drop SEQUENCE "PORTFOLIOCLIENT_ID_SEQ";
drop SEQUENCE "PROVINCE_ID_SEQ";
drop SEQUENCE "SEATNUMBER_ID_SEQ";
drop SEQUENCE "SECURITYIDTYPE_ID_SEQ";
drop SEQUENCE "SECURITYIDTYPEMAP_ID_SEQ";
drop SEQUENCE "STANDARDVOUCHER_ID_SEQ";
drop SEQUENCE "STANDARDVOUCHERDETAILS_ID_SEQ";
drop SEQUENCE "TRANACCRUEDREC_ID_SEQ";
drop SEQUENCE "TRANCOLLATERALSUM_ID_SEQ";
drop SEQUENCE "TRANFEEFOLLOWCLO_ID_SEQ";
drop SEQUENCE "TRANSUBRED_ID_SEQ";
drop SEQUENCE "TRANSUP_ID_SEQ";
drop SEQUENCE "USERCLIENTMAP_ID_SEQ";
drop SEQUENCE "USERPORTFOLIOMAP_ID_SEQ";
drop SEQUENCE "VALUATIONREPORT_ID_SEQ";
drop SEQUENCE "WORKFLOWJOBMAP_ID_SEQ";
drop SEQUENCE "YGZTAIZHANG_ID_SEQ";


CREATE SEQUENCE  "JXC_COA_ID_SEQ"  MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 10000 CACHE 20;
CREATE SEQUENCE "COAA_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 10000 CACHE 20;
CREATE SEQUENCE "COAAGROUP_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 10000 CACHE 20;
CREATE SEQUENCE "GLCOAAMAP_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 10000 CACHE 20;
CREATE SEQUENCE "RPSECTYPE_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 10000 CACHE 20;
CREATE SEQUENCE "TRANFEEDEF_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 10000 CACHE 20;
CREATE SEQUENCE "TRANFEETYPEMAP_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 10000 CACHE 20;
CREATE SEQUENCE "BKINFO_ID_SEQ"  MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 10000 CACHE 20;
CREATE SEQUENCE "BROKER_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 10000 CACHE 20;
CREATE SEQUENCE "EXCHANGECFG_ID_SEQ"  MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 10000 CACHE 20;
CREATE SEQUENCE "ISSUER_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 50000 CACHE 20;
CREATE SEQUENCE "JXV_VOUCHER_ID_SEQ"  MINVALUE 1 MAXVALUE 99999999 INCREMENT BY 1 START WITH 1 CACHE 20;
CREATE SEQUENCE "BKCASHCLO_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE;
CREATE SEQUENCE "BKINTEACCT_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE;
CREATE SEQUENCE "BKINTERATIO_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE;
CREATE SEQUENCE "BKINTESETHIS_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;
CREATE SEQUENCE "CLOSEHIS_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE;
CREATE SEQUENCE "FXRATE_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE;
CREATE SEQUENCE "GLCLOSE_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE;
CREATE SEQUENCE "INTERESTRCPY_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE;
CREATE SEQUENCE "JOBSHIS_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE;
CREATE SEQUENCE "MANAGERGROUP_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE;
CREATE SEQUENCE "MANAGERS_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE;
CREATE SEQUENCE "NAV_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "NEWFUND_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "NEWFUNDCONTENT_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "PERFORMANCEFEECFG_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "PORTEXPENSECFG_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "NAVBANKZIGUAN_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "PORTFOLIOCLASSIDMAP_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "PORTFOLIOCLIENT_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "PROVINCE_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "SEATNUMBER_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "SECURITYIDTYPE_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "SECURITYIDTYPEMAP_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "STANDARDVOUCHER_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "STANDARDVOUCHERDETAILS_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "TRANACCRUEDREC_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "TRANCOLLATERALSUM_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "TRANFEEFOLLOWCLO_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "TRANSUBRED_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "TRANSUP_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "USERCLIENTMAP_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "USERPORTFOLIOMAP_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "VALUATIONREPORT_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "WORKFLOWJOBMAP_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE "YGZTAIZHANG_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 10000 CACHE 20 NOORDER  NOCYCLE ;



