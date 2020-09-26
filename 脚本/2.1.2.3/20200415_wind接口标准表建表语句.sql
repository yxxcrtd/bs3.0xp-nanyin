/*wind接口节假日表*/
drop TABLE INTERF_PAYHSRCHOLIDAY;
CREATE TABLE INTERF_PAYHSRCHOLIDAY
  (
    CDATE       DATE NOT NULL ,
    MARKET_CODE VARCHAR2(50 BYTE) NOT NULL ,
    IS_HOLIDAY  NUMBER(10, 0) ,
    CREATE_USER VARCHAR2(20 BYTE) ,
    CREATE_DEPT VARCHAR2(32 BYTE) ,
    CREATE_TIME TIMESTAMP(6) ,
    UPDATE_USER VARCHAR2(20 BYTE) ,
    UPDATE_TIME TIMESTAMP(6) ,
    SOURCE      VARCHAR2(50 BYTE) ,
    CONSTRAINT PK_INTERF_PAYHSRCHOLIDAY PRIMARY KEY ( CDATE , MARKET_CODE ));
COMMENT ON TABLE INTERF_PAYHSRCHOLIDAY
IS
  'wind接口节假日表';
  COMMENT ON COLUMN INTERF_PAYHSRCHOLIDAY.CDATE
IS
  '日期';
  COMMENT ON COLUMN INTERF_PAYHSRCHOLIDAY.MARKET_CODE
IS
  '市场代码';
  COMMENT ON COLUMN INTERF_PAYHSRCHOLIDAY.IS_HOLIDAY
IS
  '是否节假日';
  COMMENT ON COLUMN INTERF_PAYHSRCHOLIDAY.CREATE_USER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_PAYHSRCHOLIDAY.CREATE_DEPT
IS
  '创建部门';
  COMMENT ON COLUMN INTERF_PAYHSRCHOLIDAY.CREATE_TIME
IS
  '创建时间';
  COMMENT ON COLUMN INTERF_PAYHSRCHOLIDAY.UPDATE_USER
IS
  '更新人';
  COMMENT ON COLUMN INTERF_PAYHSRCHOLIDAY.UPDATE_TIME
IS
  '更新时间';
  COMMENT ON COLUMN INTERF_PAYHSRCHOLIDAY.SOURCE
IS
  '数据来源';

/*wind接口机构信息表*/
drop TABLE INTERF_CUSTOMER_INFO;
create table INTERF_CUSTOMER_INFO
(
  CUSTOMER_ID           VARCHAR2(32) not null,
  CUSTOMER_NAME         VARCHAR2(500),
  CUSTOMER_ABBR         VARCHAR2(500),
  PARTY_TYPE3           VARCHAR2(50),
  PARTY_TYPE2           VARCHAR2(50),
  PARTY_TYPE1           VARCHAR2(50),
  INDUSTRY              VARCHAR2(50),
  INDUSTRY2             VARCHAR2(50),
  INSITITUTE_CODE       VARCHAR2(50),
  IS_GOV                VARCHAR2(50),
  MAIN_CAPITAL          NUMBER(30,14),
  REGISTER_CAPITAL      NUMBER(30,14),
  PROVINCE              VARCHAR2(50),
  CITY                  VARCHAR2(50),
  COUNTY                VARCHAR2(50),
  HOLDING_TYPE          VARCHAR2(50),
  CUSTOMER_OBJ_TYPE     VARCHAR2(50),
  IS_LISTED_COMPANY     VARCHAR2(50),
  IS_GROUP_CUSTOMER     VARCHAR2(50),
  PARENT_CUSTOMER_ID    VARCHAR2(32),
  PARENT_CUSTOMER_NAME  VARCHAR2(200),
  IS_PRIVATE            VARCHAR2(50),
  IS_ESCAPE_DEPT        VARCHAR2(50),
  CREDIT_TYPE           VARCHAR2(50),
  IS_CREDIT_RELATED_TRD VARCHAR2(50),
  STRUCT_ADJUST_TYPE    VARCHAR2(50),
  INDUS_UPGRATE_TYPE    VARCHAR2(50),
  BUSI_SCOPE            CLOB,
  MAIN_BUSI             CLOB,
  COMPANY_NATURE        VARCHAR2(50),
  REMARK                VARCHAR2(1000),
  CREATE_USER           VARCHAR2(20),
  CREATE_DEPT           VARCHAR2(32),
  CREATE_TIME           TIMESTAMP(6),
  UPDATE_USER           VARCHAR2(20),
  UPDATE_TIME           TIMESTAMP(6),
  SOURCE                VARCHAR2(50),
  HS_TYPE               VARCHAR2(50),
  INDUSTRY_SRC          VARCHAR2(100),
  INDUSTRY_WIND         VARCHAR2(100),
  INDUSTRY_SHENWAN      VARCHAR2(100),
  INDUSTRY_SRC_TWO      VARCHAR2(100),
  INDUSTRY_WIND_TWO     VARCHAR2(100),
  INDUSTRY_SHENWAN_TWO  VARCHAR2(100)
);
comment on table INTERF_CUSTOMER_INFO
  is 'wind接口机构信息表';
comment on column INTERF_CUSTOMER_INFO.CUSTOMER_ID
  is '客户代码';
comment on column INTERF_CUSTOMER_INFO.CUSTOMER_NAME
  is '机构名称';
comment on column INTERF_CUSTOMER_INFO.CUSTOMER_ABBR
  is '机构简称';
comment on column INTERF_CUSTOMER_INFO.PARTY_TYPE3
  is '机构类型（技术领域），高新技术企业、其他';
comment on column INTERF_CUSTOMER_INFO.PARTY_TYPE2
  is '机构类型（经济类型），国有企业、集体企业、民营企业、其他企业';
comment on column INTERF_CUSTOMER_INFO.PARTY_TYPE1
  is '机构类型（规模），大型企业、中小微企业';
comment on column INTERF_CUSTOMER_INFO.INDUSTRY
  is '国标一级分类';
comment on column INTERF_CUSTOMER_INFO.INDUSTRY2
  is '国标二级分类';
comment on column INTERF_CUSTOMER_INFO.INSITITUTE_CODE
  is '组织机构代码/统一社会信用代码';
comment on column INTERF_CUSTOMER_INFO.IS_GOV
  is '是否政府融资平台';
comment on column INTERF_CUSTOMER_INFO.MAIN_CAPITAL
  is '核心资本（万元），存的是万元';
comment on column INTERF_CUSTOMER_INFO.REGISTER_CAPITAL
  is '注册资本（万元），存的是万元';
comment on column INTERF_CUSTOMER_INFO.PROVINCE
  is '所属省';
comment on column INTERF_CUSTOMER_INFO.CITY
  is '所属市';
comment on column INTERF_CUSTOMER_INFO.COUNTY
  is '所属县';
comment on column INTERF_CUSTOMER_INFO.HOLDING_TYPE
  is '控股类型';
comment on column INTERF_CUSTOMER_INFO.CUSTOMER_OBJ_TYPE
  is '客户主体类型，我国中央政府、境外主权国家或经济实体区域政府、中国人民银行、境外中央银行等';
comment on column INTERF_CUSTOMER_INFO.IS_LISTED_COMPANY
  is '是否上市公司';
comment on column INTERF_CUSTOMER_INFO.IS_GROUP_CUSTOMER
  is '是否集团客户';
comment on column INTERF_CUSTOMER_INFO.PARENT_CUSTOMER_ID
  is '所属集团客户编号';
comment on column INTERF_CUSTOMER_INFO.PARENT_CUSTOMER_NAME
  is '所属集团客户名称';
comment on column INTERF_CUSTOMER_INFO.IS_PRIVATE
  is '是否民营企业';
comment on column INTERF_CUSTOMER_INFO.IS_ESCAPE_DEPT
  is '是否为逃废债企业';
comment on column INTERF_CUSTOMER_INFO.CREDIT_TYPE
  is '授信策略';
comment on column INTERF_CUSTOMER_INFO.IS_CREDIT_RELATED_TRD
  is '是否授信类关联交易';
comment on column INTERF_CUSTOMER_INFO.STRUCT_ADJUST_TYPE
  is '产业结构调整类型';
comment on column INTERF_CUSTOMER_INFO.INDUS_UPGRATE_TYPE
  is '工业转型升级标识';
comment on column INTERF_CUSTOMER_INFO.BUSI_SCOPE
  is '经营范围';
comment on column INTERF_CUSTOMER_INFO.MAIN_BUSI
  is '主营业务';
comment on column INTERF_CUSTOMER_INFO.COMPANY_NATURE
  is '企业性质';
comment on column INTERF_CUSTOMER_INFO.REMARK
  is '备注';
comment on column INTERF_CUSTOMER_INFO.CREATE_USER
  is '创建人';
comment on column INTERF_CUSTOMER_INFO.CREATE_DEPT
  is '创建部门';
comment on column INTERF_CUSTOMER_INFO.CREATE_TIME
  is '创建时间';
comment on column INTERF_CUSTOMER_INFO.UPDATE_USER
  is '更新人';
comment on column INTERF_CUSTOMER_INFO.UPDATE_TIME
  is '更新时间';
comment on column INTERF_CUSTOMER_INFO.SOURCE
  is '数据来源';
comment on column INTERF_CUSTOMER_INFO.HS_TYPE
  is '核算分类(01到10)';
comment on column INTERF_CUSTOMER_INFO.INDUSTRY_SRC
  is '行业（证监会）';
comment on column INTERF_CUSTOMER_INFO.INDUSTRY_WIND
  is '行业（wind）';
comment on column INTERF_CUSTOMER_INFO.INDUSTRY_SHENWAN
  is '行业（申万）';
comment on column INTERF_CUSTOMER_INFO.INDUSTRY_SRC_TWO
  is '行业（证监会）二级';
comment on column INTERF_CUSTOMER_INFO.INDUSTRY_WIND_TWO
  is '行业（wind）二级';
comment on column INTERF_CUSTOMER_INFO.INDUSTRY_SHENWAN_TWO
  is '行业（申万）二级';
alter table INTERF_CUSTOMER_INFO
  add constraint PK_INTERF_CUSTOMER_INFO primary key (CUSTOMER_ID);


  /*wind接口债券基本资料表*/
 drop TABLE INTERF_PAYHSRCSECINFO;
 CREATE TABLE INTERF_PAYHSRCSECINFO
  (
  secid                     VARCHAR2(50) not null,
  secname                   VARCHAR2(200),
  seccode                   VARCHAR2(50),
  market                    CHAR(2),
  secfullname               VARCHAR2(500),
  ratebasic                 VARCHAR2(10),
  couponspecies             CHAR(3),
  interestrate              CHAR(2),
  vdate                     DATE,
  mdate                     DATE,
  paycycle                  VARCHAR2(50),
  facevalue                 NUMBER(20,4),
  issueprice                NUMBER(20,4),
  paperir                   NUMBER(18,12),
  schecalrule               CHAR(2),
  firstrateday              DATE,
  workdayrule               CHAR(2),
  issuershort               VARCHAR2(50),
  resettype                 VARCHAR2(50),
  observebefday             NUMBER(10),
  observebefunit            VARCHAR2(50),
  toprate                   NUMBER(20,14),
  bottomrate                NUMBER(20,14),
  issueamt                  NUMBER(20,4),
  ratecode                  VARCHAR2(50),
  spreadrate_8              NUMBER(20,14),
  coefficient               NUMBER(18,12),
  sectype                   VARCHAR2(50),
  sectype2                  VARCHAR2(50),
  chbsectype                VARCHAR2(50),
  chbsectype2               VARCHAR2(50),
  pbocsectype               VARCHAR2(50),
  pbocsectype2              VARCHAR2(50),
  remark                    NVARCHAR2(255),
  issecbond                 VARCHAR2(50),
  issus                     VARCHAR2(50),
  calloption                VARCHAR2(50),
  putoption                 VARCHAR2(50),
  institution               VARCHAR2(100),
  institutiontext           NVARCHAR2(400),
  markettext                NVARCHAR2(400),
  market2                   CHAR(2),
  ccy                       CHAR(3),
  issuetype                 VARCHAR2(50),
  blnarea                   VARCHAR2(50),
  guarantor                 VARCHAR2(400),
  guartype                  VARCHAR2(100),
  create_user               VARCHAR2(20),
  create_dept               VARCHAR2(32),
  create_time               TIMESTAMP(6),
  update_user               VARCHAR2(20),
  update_time               TIMESTAMP(6),
  source                    VARCHAR2(50) not null,
  pubenddate                DATE,
  city_invest_sec_chb       VARCHAR2(50),
  city_invest_sec_provinces VARCHAR2(50),
  city_invest_sec_cities    VARCHAR2(50),
  city_invest_sec_county    VARCHAR2(50),
  city_invest_sec_wind      VARCHAR2(50),
  city_invest_sec_cbrc      VARCHAR2(50),
  asset_class               VARCHAR2(50),
  concrete_class            VARCHAR2(50),
  concrete_class_sm         VARCHAR2(50),
  issuer_scale_type         VARCHAR2(50),
  issuer_technology_type    VARCHAR2(50),
  issuer_economic_type      VARCHAR2(50),
  issuer_desc               VARCHAR2(50),
  issuer_institutions       VARCHAR2(50),
  ISSUER_INSTITUTIONS_SECOND VARCHAR2(50)
)
;
comment on table INTERF_PAYHSRCSECINFO
  is 'wind接口债券基本资料表';
comment on column INTERF_PAYHSRCSECINFO.secid
  is '债券ID：xxxx.IB/xxxx.SZ';
comment on column INTERF_PAYHSRCSECINFO.secname
  is '债券简称';
comment on column INTERF_PAYHSRCSECINFO.seccode
  is '债券code';
comment on column INTERF_PAYHSRCSECINFO.market
  is '市场 ';
comment on column INTERF_PAYHSRCSECINFO.secfullname
  is '债券全称';
comment on column INTERF_PAYHSRCSECINFO.ratebasic
  is '计息基准：参考右边明细';
comment on column INTERF_PAYHSRCSECINFO.couponspecies
  is '息票品种 NPV付息 DIS贴现 IAM利随本清 ZCO零息';
comment on column INTERF_PAYHSRCSECINFO.interestrate
  is '利率类型 FI固定，FL浮动';
comment on column INTERF_PAYHSRCSECINFO.vdate
  is '起息日';
comment on column INTERF_PAYHSRCSECINFO.mdate
  is '到期日';
comment on column INTERF_PAYHSRCSECINFO.paycycle
  is '付息频率';
comment on column INTERF_PAYHSRCSECINFO.facevalue
  is '面额 默认100';
comment on column INTERF_PAYHSRCSECINFO.issueprice
  is '发行价';
comment on column INTERF_PAYHSRCSECINFO.paperir
  is '票面利率';
comment on column INTERF_PAYHSRCSECINFO.schecalrule
  is '付息区间推算方法 VF起息日向后，MB到期日向前，FD首次付息日';
comment on column INTERF_PAYHSRCSECINFO.firstrateday
  is '首次付息日';
comment on column INTERF_PAYHSRCSECINFO.workdayrule
  is '营业日准则 P向前调整 S向后调整 M调整的向后 N不调整';
comment on column INTERF_PAYHSRCSECINFO.issuershort
  is '发行人';
comment on column INTERF_PAYHSRCSECINFO.resettype
  is '利率调整规则';
comment on column INTERF_PAYHSRCSECINFO.observebefday
  is '延后时间';
comment on column INTERF_PAYHSRCSECINFO.observebefunit
  is '延后时间单位';
comment on column INTERF_PAYHSRCSECINFO.toprate
  is '利率上限';
comment on column INTERF_PAYHSRCSECINFO.bottomrate
  is '利率下限';
comment on column INTERF_PAYHSRCSECINFO.issueamt
  is '发行总额';
comment on column INTERF_PAYHSRCSECINFO.ratecode
  is '浮动利率code';
comment on column INTERF_PAYHSRCSECINFO.spreadrate_8
  is '浮动利差';
comment on column INTERF_PAYHSRCSECINFO.coefficient
  is '浮动系数';
comment on column INTERF_PAYHSRCSECINFO.sectype
  is '系统债券类型：';
comment on column INTERF_PAYHSRCSECINFO.sectype2
  is '系统债券类型：';
comment on column INTERF_PAYHSRCSECINFO.chbsectype
  is '中债债券类型：';
comment on column INTERF_PAYHSRCSECINFO.chbsectype2
  is '中债债券类型：';
comment on column INTERF_PAYHSRCSECINFO.pbocsectype
  is '人行债券类型：';
comment on column INTERF_PAYHSRCSECINFO.pbocsectype2
  is '人行债券类型：';
comment on column INTERF_PAYHSRCSECINFO.remark
  is '备注';
comment on column INTERF_PAYHSRCSECINFO.issecbond
  is '是否次级债,Y是N否';
comment on column INTERF_PAYHSRCSECINFO.issus
  is '是否永续债,Y是N否';
comment on column INTERF_PAYHSRCSECINFO.calloption
  is '是否可赎回,Y是N否';
comment on column INTERF_PAYHSRCSECINFO.putoption
  is '是否可回售,Y是N否';
comment on column INTERF_PAYHSRCSECINFO.institution
  is '登记托管机构';
comment on column INTERF_PAYHSRCSECINFO.institutiontext
  is '登记托管机构说明';
comment on column INTERF_PAYHSRCSECINFO.markettext
  is '交易场所说明';
comment on column INTERF_PAYHSRCSECINFO.market2
  is '交易流通场所：01 银行间市场、02 商业银行柜台市场、03 上海交易所、04 深圳交易所、99 其它';
comment on column INTERF_PAYHSRCSECINFO.ccy
  is '"币种
CNY 人民币、HKD 港币、JPY 日元、USD 美元"';
comment on column INTERF_PAYHSRCSECINFO.issuetype
  is '发行方式';
comment on column INTERF_PAYHSRCSECINFO.blnarea
  is '境内外';
comment on column INTERF_PAYHSRCSECINFO.guarantor
  is '担保人';
comment on column INTERF_PAYHSRCSECINFO.guartype
  is '担保方式';
comment on column INTERF_PAYHSRCSECINFO.create_user
  is '创建人';
comment on column INTERF_PAYHSRCSECINFO.create_dept
  is '创建部门';
comment on column INTERF_PAYHSRCSECINFO.create_time
  is '创建时间';
comment on column INTERF_PAYHSRCSECINFO.update_user
  is '更新人';
comment on column INTERF_PAYHSRCSECINFO.update_time
  is '更新时间';
comment on column INTERF_PAYHSRCSECINFO.source
  is '数据来源';
comment on column INTERF_PAYHSRCSECINFO.pubenddate
  is '发行截止日';
comment on column INTERF_PAYHSRCSECINFO.city_invest_sec_chb
  is '是否城投债（中债）';
comment on column INTERF_PAYHSRCSECINFO.city_invest_sec_provinces
  is '城投债省及省会（单列市）';
comment on column INTERF_PAYHSRCSECINFO.city_invest_sec_cities
  is '城投债地级市';
comment on column INTERF_PAYHSRCSECINFO.city_invest_sec_county
  is '城投债县及县级市';
comment on column INTERF_PAYHSRCSECINFO.city_invest_sec_wind
  is '是否城投债（WIND）';
comment on column INTERF_PAYHSRCSECINFO.city_invest_sec_cbrc
  is '是否城投债（银监）';
comment on column INTERF_PAYHSRCSECINFO.asset_class
  is '资产/负债类别';
comment on column INTERF_PAYHSRCSECINFO.concrete_class
  is '具体类别';
comment on column INTERF_PAYHSRCSECINFO.concrete_class_sm
  is '具体类别说明';
comment on column INTERF_PAYHSRCSECINFO.issuer_scale_type
  is '发行机构类型（按规模划分）';
comment on column INTERF_PAYHSRCSECINFO.issuer_technology_type
  is '发行机构类型（按技术领域划分）';
comment on column INTERF_PAYHSRCSECINFO.issuer_economic_type
  is '发行机构类型（按经济领域划分）';
comment on column INTERF_PAYHSRCSECINFO.issuer_desc
  is '发行机构类型说明';
comment on column INTERF_PAYHSRCSECINFO.issuer_institutions
  is '发行机构所属行业';
comment on column INTERF_PAYHSRCSECINFO.ISSUER_INSTITUTIONS_SECOND
  is '发行机构所属行业二级分类';
alter table INTERF_PAYHSRCSECINFO
  add constraint PK_INTERF_PAYHSRCSECINFO primary key (SECID, SOURCE);

/*wind接口付息计划表*/
drop TABLE INTERF_PAYHSRCSECSCHEDULE;
CREATE TABLE INTERF_PAYHSRCSECSCHEDULE
  (
    SECID                 VARCHAR2(50 BYTE) NOT NULL ,
    SCHEDUTYPE            VARCHAR2(50 BYTE) NOT NULL ,
    TOTALINTERESTSTARTDAY DATE ,
    TOTALINTERESTENDDAY   DATE ,
    INTERESTSTARTDAY      DATE NOT NULL ,
    INTERESTENDDAY        DATE NOT NULL ,
    INTERESTPAYDAY        DATE ,
    COUPONRATE            NUMBER(20, 14) ,
    ACCRUEINTAMT          NUMBER(18, 12) ,
    DAILYACCRUEINT        NUMBER(18, 12) ,
    PRINAMTPAY            NUMBER(18, 12) ,
    CASHPAY               NUMBER(18, 12) ,
    PRINAMT               NUMBER(18, 12) ,
    CREATE_USER           VARCHAR2(20 BYTE) ,
    CREATE_DEPT           VARCHAR2(32 BYTE) ,
    CREATE_TIME           TIMESTAMP(6) ,
    UPDATE_USER           VARCHAR2(20 BYTE) ,
    UPDATE_TIME           TIMESTAMP(6) ,
    SOURCE                VARCHAR2(50 BYTE) NOT NULL ,
    CONSTRAINT PK_INTERF_PAYHSRCSECSCHEDULE PRIMARY KEY ( SECID , SCHEDUTYPE , INTERESTSTARTDAY , INTERESTENDDAY , SOURCE ));
COMMENT ON TABLE INTERF_PAYHSRCSECSCHEDULE
IS
  'wind接口付息计划表';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.SECID
IS
  '债券ID：xxxx.IB/xxxx.SZ';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.SCHEDUTYPE
IS
  '现金流类型 ';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.TOTALINTERESTSTARTDAY
IS
  '理论付息起始日';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.TOTALINTERESTENDDAY
IS
  '理论付息结束日';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.INTERESTSTARTDAY
IS
  '实际付息起始日';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.INTERESTENDDAY
IS
  '实际付息结束日';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.INTERESTPAYDAY
IS
  '理论支付日';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.COUPONRATE
IS
  '当期利率';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.ACCRUEINTAMT
IS
  '应计利息和';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.DAILYACCRUEINT
IS
  '日应计利息';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.PRINAMTPAY
IS
  '支付本金';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.CASHPAY
IS
  '支付现金流，即支付的本金加利息';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.PRINAMT
IS
  '计息本金';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.CREATE_USER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.CREATE_DEPT
IS
  '创建部门';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.CREATE_TIME
IS
  '创建时间';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.UPDATE_USER
IS
  '更新人';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.UPDATE_TIME
IS
  '更新时间';
  COMMENT ON COLUMN INTERF_PAYHSRCSECSCHEDULE.SOURCE
IS
  '数据来源';
/*添加索引*/
drop index secid_index;
create index secid_index on INTERF_PAYHSRCSECSCHEDULE(secid);

/*wind接口债券行权信息表*/
drop TABLE INTERF_PAYHSRCSECOPTION;
CREATE TABLE INTERF_PAYHSRCSECOPTION
  (
    SECID              VARCHAR2(50 BYTE) NOT NULL ,
    OPTIONSTARTDAY     DATE ,
    OPTIONENDDAY       DATE ,
    OPTIONPAYDAY       DATE ,
    THEORYOPTIONPAYDAY DATE ,
    OPTIONPRICE        NUMBER(18, 12) ,
    OPTIONTYPE         VARCHAR2(10 BYTE) NOT NULL ,
    OPTIONCLAUSE       VARCHAR2(400 BYTE) ,
    CREATE_USER        VARCHAR2(20 BYTE) ,
    CREATE_DEPT        VARCHAR2(32 BYTE) ,
    CREATE_TIME        TIMESTAMP(6) ,
    UPDATE_USER        VARCHAR2(20 BYTE) ,
    UPDATE_TIME        TIMESTAMP(6) ,
    SOURCE             VARCHAR2(50 BYTE) NOT NULL,
	CONSTRAINT PK_INTERF_PAYHSRCSECOPTION PRIMARY KEY (SECID,OPTIONPAYDAY,OPTIONTYPE,SOURCE));
COMMENT ON TABLE INTERF_PAYHSRCSECOPTION
IS
  'wind接口债券行权信息表';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.SECID
IS
  '债券ID：xxxx.IB/xxxx.SZ';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.OPTIONSTARTDAY
IS
  '行权申请起始日';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.OPTIONENDDAY
IS
  '行权申请结束日';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.OPTIONPAYDAY
IS
  '行权日';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.THEORYOPTIONPAYDAY
IS
  '理论行权日';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.OPTIONPRICE
IS
  '行权价(净价)';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.OPTIONTYPE
IS
  '行权类型';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.OPTIONCLAUSE
IS
  '行权条款';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.CREATE_USER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.CREATE_DEPT
IS
  '创建部门';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.CREATE_TIME
IS
  '创建时间';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.UPDATE_USER
IS
  '更新人';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.UPDATE_TIME
IS
  '更新时间';
  COMMENT ON COLUMN INTERF_PAYHSRCSECOPTION.SOURCE
IS
  '数据来源';

/*wind接口债券评级信息表*/
drop TABLE  INTERF_PAYHSRCSECRATING;
CREATE TABLE INTERF_PAYHSRCSECRATING
  (
    SECID VARCHAR2(50 BYTE) NOT NULL ,
    RATINGORG NVARCHAR2(50) NOT NULL ,
    RATINGTYPE    VARCHAR2(50 BYTE) NOT NULL ,
    RATINGSUBTYPE VARCHAR2(50 BYTE) NOT NULL ,
    RATING        VARCHAR2(50 BYTE) ,
	RATING_PROSPECTS  VARCHAR2(50),
	CHANGE_DIRECTION  VARCHAR2(50),
	ANNOUNCEMENT_DATE DATE,
	RATING_OBJ_TYPE VARCHAR2(50 BYTE),
    PUBDATE       DATE NOT NULL ,
    CREATE_USER   VARCHAR2(20 BYTE) ,
    CREATE_DEPT   VARCHAR2(32 BYTE) ,
    CREATE_TIME   TIMESTAMP(6) ,
    UPDATE_USER   VARCHAR2(20 BYTE) ,
    UPDATE_TIME   TIMESTAMP(6) ,
    SOURCE        VARCHAR2(50 BYTE) NOT NULL ,
    IS_RECOMMAND  VARCHAR2(50 BYTE) ,
    CONSTRAINT PK_INTERF_PAYHSRCSECRATING PRIMARY KEY ( SECID , RATINGORG , RATINGTYPE , RATINGSUBTYPE , PUBDATE , SOURCE ));
COMMENT ON TABLE INTERF_PAYHSRCSECRATING
IS
  'wind接口债券评级信息表';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.SECID
IS
  '债券ID：xxxx.IB/xxxx.SZ';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.RATINGORG
IS
  '评级机构';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.RATINGTYPE
IS
  '评级类型';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.RATINGSUBTYPE
IS
  '长期短期 ';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.RATING
IS
  '评级结果';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.PUBDATE
IS
  '发布日期';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.CREATE_USER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.CREATE_DEPT
IS
  '创建部门';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.CREATE_TIME
IS
  '创建时间';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.UPDATE_USER
IS
  '更新人';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.UPDATE_TIME
IS
  '更新时间';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.SOURCE
IS
  '数据来源';
  COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.IS_RECOMMAND
IS
  '是否推荐';
COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.RATING_PROSPECTS
  IS '评级展望';
COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.CHANGE_DIRECTION
  IS '变动方向';
COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.ANNOUNCEMENT_DATE
  IS '公告日期';
COMMENT ON COLUMN INTERF_PAYHSRCSECRATING.RATING_OBJ_TYPE
  IS '评级对象类型';
  
/*wind接口债券市价估值表*/
drop TABLE INTERF_PAYHSRCCBVLT;
CREATE TABLE INTERF_PAYHSRCCBVLT
  (
    SECID         VARCHAR2(50 BYTE) NOT NULL ,
    CDATE         VARCHAR2(50 BYTE) NOT NULL ,
    ENCASH_YEARS  NUMBER(18, 12) ,
    MDPRICE       NUMBER(18, 12) ,
    MCPRICE       NUMBER(18, 12) ,
    M_MDURATION   NUMBER(18, 12) ,
    M_CNVXTY      NUMBER(18, 12) ,
    MBASISVALUE   NUMBER(18, 12) ,
    BASISVALUE    NUMBER(18, 12) ,
    MSPRD_D       NUMBER(18, 12) ,
    MSPRD_CNVXTY  NUMBER(18, 12) ,
    MYIELD_D      NUMBER(18, 12) ,
    MYIELD_CNVXTY NUMBER(18, 12) ,
    MYIELD        NUMBER(18, 14) ,
    VAR           NUMBER(24, 8) ,
    CVAR          NUMBER(24, 8) ,
    VALUE_SOURCE  VARCHAR2(50 BYTE) NOT NULL ,
    CREATE_USER   VARCHAR2(20 BYTE) ,
    CREATE_DEPT   VARCHAR2(32 BYTE) ,
    CREATE_TIME   TIMESTAMP(6) ,
    UPDATE_USER   VARCHAR2(20 BYTE) ,
    UPDATE_TIME   TIMESTAMP(6) ,
    SOURCE        VARCHAR2(50 BYTE) NOT NULL ,
    CONSTRAINT PK_INTERF_PAYHSRCCBVLT PRIMARY KEY ( SECID , CDATE , VALUE_SOURCE , SOURCE ));
COMMENT ON TABLE INTERF_PAYHSRCCBVLT
IS
  'wind接口债券市价估值表';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.SECID
IS
  '债券ID：xxxx.IB/xxxx.SZ';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.CDATE
IS
  '估值日';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.ENCASH_YEARS
IS
  '剩余期限';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.MDPRICE
IS
  '市价全价';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.MCPRICE
IS
  '市价净价';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.M_MDURATION
IS
  '市价久期';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.M_CNVXTY
IS
  '市价凸性';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.MBASISVALUE
IS
  '市价基点价值（PVBP）';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.BASISVALUE
IS
  '基点价值（PVBP）';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.MSPRD_D
IS
  '利差久期';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.MSPRD_CNVXTY
IS
  '利差凸性';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.MYIELD_D
IS
  '利率久期';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.MYIELD_CNVXTY
IS
  '利率凸性';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.MYIELD
IS
  '市价收益率';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.VAR
IS
  '风险价值';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.CVAR
IS
  '条件风险价值';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.VALUE_SOURCE
IS
  '估值来源';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.CREATE_USER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.CREATE_DEPT
IS
  '创建部门';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.CREATE_TIME
IS
  '创建时间';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.UPDATE_USER
IS
  '更新人';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.UPDATE_TIME
IS
  '更新时间';
  COMMENT ON COLUMN INTERF_PAYHSRCCBVLT.SOURCE
IS
  '数据来源';

/*wind 接口市场行情*/
drop TABLE INTERF_BOND_MARKET_INFO;
create table INTERF_BOND_MARKET_INFO
(
  SEC_ID              VARCHAR2(50) not null,
  VALUE_DATE          DATE not null,
  CLOSE_PRICE         NUMBER(30,14),
  AVERAGE_PRICE       NUMBER(30,14),
  MAX_PRICE           NUMBER(30,14),
  MIN_PRICE           NUMBER(30,14),
  BUY_ONE_PRICE       NUMBER(30,14),
  SELL_ONE_PRICE      NUMBER(30,14),
  CREATE_USER         VARCHAR2(20),
  CREATE_DEPT         VARCHAR2(32),
  CREATE_TIME         TIMESTAMP(6),
  UPDATE_USER         VARCHAR2(20),
  UPDATE_TIME         TIMESTAMP(6),
  SOURCE              VARCHAR2(50) not null,
  CLOSE_PRICE_FULL    NUMBER(30,14),
  AVERAGE_PRICE_FULL  NUMBER(30,14),
  MAX_PRICE_FULL      NUMBER(30,14),
  MIN_PRICE_FULL      NUMBER(30,14),
  BUY_ONE_PRICE_FULL  NUMBER(30,14),
  SELL_ONE_PRICE_FULL NUMBER(30,14)
);
comment on table INTERF_BOND_MARKET_INFO
  is 'wind 接口市场行情';
comment on column INTERF_BOND_MARKET_INFO.SEC_ID
  is '债券ID：xxxx.IB/xxxx.SZ';
comment on column INTERF_BOND_MARKET_INFO.VALUE_DATE
  is '市值日';
comment on column INTERF_BOND_MARKET_INFO.CLOSE_PRICE
  is '收盘净价 ';
comment on column INTERF_BOND_MARKET_INFO.AVERAGE_PRICE
  is '平均净价';
comment on column INTERF_BOND_MARKET_INFO.MAX_PRICE
  is '最高净价 ';
comment on column INTERF_BOND_MARKET_INFO.MIN_PRICE
  is '最低净价';
comment on column INTERF_BOND_MARKET_INFO.BUY_ONE_PRICE
  is '买一净价 ';
comment on column INTERF_BOND_MARKET_INFO.SELL_ONE_PRICE
  is '卖一净价';
comment on column INTERF_BOND_MARKET_INFO.CREATE_USER
  is '创建人 ';
comment on column INTERF_BOND_MARKET_INFO.CREATE_DEPT
  is '创建部门';
comment on column INTERF_BOND_MARKET_INFO.CREATE_TIME
  is '创建时间 ';
comment on column INTERF_BOND_MARKET_INFO.UPDATE_USER
  is '更新人';
comment on column INTERF_BOND_MARKET_INFO.UPDATE_TIME
  is '更新时间 ';
comment on column INTERF_BOND_MARKET_INFO.SOURCE
  is '数据来源';
comment on column INTERF_BOND_MARKET_INFO.CLOSE_PRICE_FULL
  is '收盘全价';
comment on column INTERF_BOND_MARKET_INFO.AVERAGE_PRICE_FULL
  is '平均全价';
comment on column INTERF_BOND_MARKET_INFO.MAX_PRICE_FULL
  is '最高全价';
comment on column INTERF_BOND_MARKET_INFO.MIN_PRICE_FULL
  is '最低全价';
comment on column INTERF_BOND_MARKET_INFO.BUY_ONE_PRICE_FULL
  is '买一全价';
comment on column INTERF_BOND_MARKET_INFO.SELL_ONE_PRICE_FULL
  is '卖一全价';
alter table INTERF_BOND_MARKET_INFO
  add constraint PK_INTERF_BOND_MARKET_INFO primary key (SEC_ID, VALUE_DATE, SOURCE);

  
/*wind接口市场行情*/
drop TABLE INTERF_FLOATCOUPRATE;
CREATE TABLE INTERF_FLOATCOUPRATE
  (
    SEC_ID      VARCHAR2(50 BYTE) NOT NULL ,
    CHANGE_DT   DATE NOT NULL ,
    RATE        NUMBER(30, 14) ,
    CREATE_USER VARCHAR2(20 BYTE) ,
    CREATE_DEPT VARCHAR2(32 BYTE) ,
    CREATE_TIME TIMESTAMP(6) ,
    UPDATE_USER VARCHAR2(20 BYTE) ,
    UPDATE_TIME TIMESTAMP(6) ,
    SOURCE      VARCHAR2(50 BYTE) NOT NULL ,
    CONSTRAINT PK_INTERF_FLOATCOUPRATE PRIMARY KEY ( SEC_ID , CHANGE_DT , SOURCE ));
COMMENT ON TABLE INTERF_FLOATCOUPRATE
IS
  'wind接口市场行情';
  COMMENT ON COLUMN INTERF_FLOATCOUPRATE.SEC_ID
IS
  '债券ID：xxxx.IB/xxxx.SZ';
  COMMENT ON COLUMN INTERF_FLOATCOUPRATE.CHANGE_DT
IS
  '变动日期';
  COMMENT ON COLUMN INTERF_FLOATCOUPRATE.RATE
IS
  '利率(%)';
  COMMENT ON COLUMN INTERF_FLOATCOUPRATE.CREATE_USER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_FLOATCOUPRATE.CREATE_DEPT
IS
  '创建部门';
  COMMENT ON COLUMN INTERF_FLOATCOUPRATE.CREATE_TIME
IS
  '创建时间';
  COMMENT ON COLUMN INTERF_FLOATCOUPRATE.UPDATE_USER
IS
  '更新人';
  COMMENT ON COLUMN INTERF_FLOATCOUPRATE.UPDATE_TIME
IS
  '更新时间';
  COMMENT ON COLUMN INTERF_FLOATCOUPRATE.SOURCE
IS
  '数据来源';

/*wind接口回购标准券折算率*/
drop TABLE INTERF_STDSEC_CON_RADIO;
CREATE TABLE INTERF_STDSEC_CON_RADIO
  (
    SEC_ID        VARCHAR2(50 BYTE) NOT NULL ,
    VDATE         DATE NOT NULL ,
    MDATE         DATE ,
    SCALE         NUMBER(30, 14) ,
    CVNPERHUNDRED NUMBER(30, 14) ,
    CREATE_USER   VARCHAR2(20 BYTE) ,
    CREATE_DEPT   VARCHAR2(32 BYTE) ,
    CREATE_TIME   TIMESTAMP(6) ,
    UPDATE_USER   VARCHAR2(20 BYTE) ,
    UPDATE_TIME   TIMESTAMP(6) ,
    SOURCE        VARCHAR2(50 BYTE) NOT NULL ,
    CONSTRAINT PK_INTERF_STDSEC_CONVERSE PRIMARY KEY ( SEC_ID , VDATE , SOURCE ));
COMMENT ON TABLE INTERF_STDSEC_CON_RADIO
IS
  'wind接口回购标准券折算率';
  COMMENT ON COLUMN INTERF_STDSEC_CON_RADIO.SEC_ID
IS
  '债券ID：xxxx.IB/xxxx.SZ';
  COMMENT ON COLUMN INTERF_STDSEC_CON_RADIO.VDATE
IS
  '开始适用日';
  COMMENT ON COLUMN INTERF_STDSEC_CON_RADIO.MDATE
IS
  '结束适用日';
  COMMENT ON COLUMN INTERF_STDSEC_CON_RADIO.SCALE
IS
  '折算比例';
  COMMENT ON COLUMN INTERF_STDSEC_CON_RADIO.CVNPERHUNDRED
IS
  '折合标准券，每100券面折合标准券金额';
  COMMENT ON COLUMN INTERF_STDSEC_CON_RADIO.CREATE_USER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_STDSEC_CON_RADIO.CREATE_DEPT
IS
  '创建部门';
  COMMENT ON COLUMN INTERF_STDSEC_CON_RADIO.CREATE_TIME
IS
  '创建时间';
  COMMENT ON COLUMN INTERF_STDSEC_CON_RADIO.UPDATE_USER
IS
  '更新人';
  COMMENT ON COLUMN INTERF_STDSEC_CON_RADIO.UPDATE_TIME
IS
  '更新时间';
  COMMENT ON COLUMN INTERF_STDSEC_CON_RADIO.SOURCE
IS
  '数据来源';

/*wind接口基金基本资料*/
drop TABLE INTERF_CMFUNDDESCRIP;
CREATE TABLE INTERF_CMFUNDDESCRIP
  (
  f_info_code                  VARCHAR2(40) not null,
  f_info_front_code            VARCHAR2(40),
  f_info_backend_code          VARCHAR2(40),
  f_info_fullname              VARCHAR2(200),
  f_info_name                  VARCHAR2(100),
  f_info_type                  VARCHAR2(50),
  f_info_type2                 VARCHAR2(50),
  f_info_firstinvesttype       VARCHAR2(40),
  f_info_issuedate             VARCHAR2(8),
  f_info_setupdate             VARCHAR2(8),
  f_info_maturitydate          VARCHAR2(8),
  f_issue_totalunit            NUMBER(20,4),
  f_info_managementfeeratio    NUMBER(20,4),
  f_info_custodianfeeratio     NUMBER(20,4),
  ccy                          VARCHAR2(10),
  f_info_benchmark             VARCHAR2(500),
  f_info_exchmarket            VARCHAR2(10),
  f_info_corp_fundmanagementid VARCHAR2(10),
  f_info_custodianbankid       VARCHAR2(40),
  pconfirmspeed                VARCHAR2(50),
  sconfirmspeed                VARCHAR2(50),
  repisgovinvest               VARCHAR2(10),
  repgovinvestfor              VARCHAR2(50),
  repindustry1                 VARCHAR2(50),
  repindustry2                 VARCHAR2(50),
  repregistorg                 VARCHAR2(50),
  repfundinvestasset           VARCHAR2(500),
  reptrustpartyname            VARCHAR2(500),
  market2                      CHAR(2),
  paytype                      VARCHAR2(50),
  paycycle                     VARCHAR2(50),
  dayvalue                     NUMBER(2),
  is_compound_int              VARCHAR2(50),
  create_user                  VARCHAR2(20),
  create_dept                  VARCHAR2(32),
  create_time                  TIMESTAMP(6),
  update_user                  VARCHAR2(20),
  update_time                  TIMESTAMP(6),
  source                       VARCHAR2(50) not null,
  f_profit_type                VARCHAR2(50),
  f_issue_oef_startdateinst    VARCHAR2(8),
  f_issue_oef_dnddateinst      VARCHAR2(8),
  jysjjlx                      VARCHAR2(50),
  jysjjjc                      VARCHAR2(50),
  sgshdm                       VARCHAR2(50),
  sgshjc                       VARCHAR2(50),
  dbsgjexx                     NUMBER(20,4),
  dbshjexx                     NUMBER(20,4),
  zxsgshdw                     VARCHAR2(50),
  etflx                        VARCHAR2(50),
  issuer_scale_type            VARCHAR2(50),
  issuer_technology_type       VARCHAR2(50),
  issuer_economic_type         VARCHAR2(50),
  fund_company_name            VARCHAR2(100),
  investment_phase             VARCHAR2(50)
)
;
comment on table INTERF_CMFUNDDESCRIP
  is 'wind接口基金基本资料';
comment on column INTERF_CMFUNDDESCRIP.f_info_code
  is '基金代码';
comment on column INTERF_CMFUNDDESCRIP.f_info_front_code
  is '前端代码';
comment on column INTERF_CMFUNDDESCRIP.f_info_backend_code
  is '后端代码';
comment on column INTERF_CMFUNDDESCRIP.f_info_fullname
  is '名称';
comment on column INTERF_CMFUNDDESCRIP.f_info_name
  is '简称';
comment on column INTERF_CMFUNDDESCRIP.f_info_type
  is '基金类型';
comment on column INTERF_CMFUNDDESCRIP.f_info_type2
  is '基金类型2';
comment on column INTERF_CMFUNDDESCRIP.f_info_firstinvesttype
  is '投资类型';
comment on column INTERF_CMFUNDDESCRIP.f_info_issuedate
  is '发行日期';
comment on column INTERF_CMFUNDDESCRIP.f_info_setupdate
  is '成立日期';
comment on column INTERF_CMFUNDDESCRIP.f_info_maturitydate
  is '到期日期';
comment on column INTERF_CMFUNDDESCRIP.f_issue_totalunit
  is '发行份额';
comment on column INTERF_CMFUNDDESCRIP.f_info_managementfeeratio
  is '管理费';
comment on column INTERF_CMFUNDDESCRIP.f_info_custodianfeeratio
  is '托管费';
comment on column INTERF_CMFUNDDESCRIP.ccy
  is '币种';
comment on column INTERF_CMFUNDDESCRIP.f_info_benchmark
  is '业绩比较基准';
comment on column INTERF_CMFUNDDESCRIP.f_info_exchmarket
  is '交易所';
comment on column INTERF_CMFUNDDESCRIP.f_info_corp_fundmanagementid
  is '基金管理人ID';
comment on column INTERF_CMFUNDDESCRIP.f_info_custodianbankid
  is '托管人id';
comment on column INTERF_CMFUNDDESCRIP.pconfirmspeed
  is '买入确认日';
comment on column INTERF_CMFUNDDESCRIP.sconfirmspeed
  is '卖出确认日';
comment on column INTERF_CMFUNDDESCRIP.repisgovinvest
  is '是否属于政府投资基金';
comment on column INTERF_CMFUNDDESCRIP.repgovinvestfor
  is '政府投资基金投向';
comment on column INTERF_CMFUNDDESCRIP.repindustry1
  is '行业1';
comment on column INTERF_CMFUNDDESCRIP.repindustry2
  is '行业2';
comment on column INTERF_CMFUNDDESCRIP.repregistorg
  is '登记备案机构';
comment on column INTERF_CMFUNDDESCRIP.repfundinvestasset
  is '基金投资资产';
comment on column INTERF_CMFUNDDESCRIP.reptrustpartyname
  is '基金托管机构名称';
comment on column INTERF_CMFUNDDESCRIP.market2
  is '交易流通场所：01 银行间市场、02 商业银行柜台市场、03 上海交易所、04 深圳交易所、99 其它';
comment on column INTERF_CMFUNDDESCRIP.paytype
  is '分红方式：红利再投';
comment on column INTERF_CMFUNDDESCRIP.paycycle
  is '分红频率';
comment on column INTERF_CMFUNDDESCRIP.dayvalue
  is '分红日';
comment on column INTERF_CMFUNDDESCRIP.is_compound_int
  is '是否日复利';
comment on column INTERF_CMFUNDDESCRIP.create_user
  is '创建人';
comment on column INTERF_CMFUNDDESCRIP.create_dept
  is '创建部门';
comment on column INTERF_CMFUNDDESCRIP.create_time
  is '创建时间';
comment on column INTERF_CMFUNDDESCRIP.update_user
  is '更新人';
comment on column INTERF_CMFUNDDESCRIP.update_time
  is '更新时间';
comment on column INTERF_CMFUNDDESCRIP.source
  is '数据来源';
comment on column INTERF_CMFUNDDESCRIP.f_profit_type
  is '收益类型';
comment on column INTERF_CMFUNDDESCRIP.f_issue_oef_startdateinst
  is '认购起始日';
comment on column INTERF_CMFUNDDESCRIP.f_issue_oef_dnddateinst
  is '认购终止日';
comment on column INTERF_CMFUNDDESCRIP.jysjjlx
  is '交易所基金类型';
comment on column INTERF_CMFUNDDESCRIP.jysjjjc
  is '交易所基金简称';
comment on column INTERF_CMFUNDDESCRIP.sgshdm
  is '申购赎回代码';
comment on column INTERF_CMFUNDDESCRIP.sgshjc
  is '申购赎回简称';
comment on column INTERF_CMFUNDDESCRIP.dbsgjexx
  is '单笔申购金额下限';
comment on column INTERF_CMFUNDDESCRIP.dbshjexx
  is '单笔赎回份额下限';
comment on column INTERF_CMFUNDDESCRIP.zxsgshdw
  is 'ETF最小申购赎回单位';
comment on column INTERF_CMFUNDDESCRIP.etflx
  is 'ETF类型';
comment on column INTERF_CMFUNDDESCRIP.issuer_scale_type
  is '投资企业类型（按规模划分）';
comment on column INTERF_CMFUNDDESCRIP.issuer_technology_type
  is '投资企业类型（按技术领域划分）';
comment on column INTERF_CMFUNDDESCRIP.issuer_economic_type
  is '投资企业类型（按经济类型划分）';
comment on column INTERF_CMFUNDDESCRIP.fund_company_name
  is '基金公司名称';
comment on column INTERF_CMFUNDDESCRIP.investment_phase
  is '投资阶段';
alter table INTERF_CMFUNDDESCRIP
  add constraint INTERF_CMFUNDDESCRIP_PK primary key (F_INFO_CODE, SOURCE);
  
/*wind接口基金净值*/
drop TABLE INTERF_CMFUNDNAV;
CREATE TABLE INTERF_CMFUNDNAV
  (
    F_INFO_CODE          VARCHAR2(40 BYTE) NOT NULL ,
    ANN_DATE             VARCHAR2(8 BYTE) ,
    PRICE_DATE           VARCHAR2(8 BYTE) NOT NULL ,
    F_NAV_UNIT           NUMBER(20, 4) ,
    F_INFO_YEARLYROE     NUMBER(20, 4) ,
    YEARLYROE            NUMBER(20, 4) ,
    F_NAV_ACCUMULATED    NUMBER(20, 4) ,
    F_NAV_DIVACCUMULATED NUMBER(20, 4) ,
    F_NAV_ADJFACTOR      NUMBER(20, 6) ,
    CCY                  VARCHAR2(10 BYTE) ,
    F_PRT_NETASSET       NUMBER(20, 4) ,
    F_NAV_ADJUSTED       NUMBER(18, 4) ,
    CREATE_USER          VARCHAR2(20 BYTE) ,
    CREATE_DEPT          VARCHAR2(32 BYTE) ,
    CREATE_TIME          TIMESTAMP(6) ,
    UPDATE_USER          VARCHAR2(20 BYTE) ,
    UPDATE_TIME          TIMESTAMP(6) ,
    SOURCE               VARCHAR2(50 BYTE) NOT NULL ,
    CONSTRAINT PK_INTERF_CMFUNDNAV PRIMARY KEY ( F_INFO_CODE , PRICE_DATE , SOURCE )
	);
COMMENT ON TABLE INTERF_CMFUNDNAV
IS
  'wind接口基金净值';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.F_INFO_CODE
IS
  '基金代码';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.ANN_DATE
IS
  '公告日期';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.PRICE_DATE
IS
  '截止日期';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.F_NAV_UNIT
IS
  '单位净值/万份收益';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.F_INFO_YEARLYROE
IS
  '最近七日收益所折算的年资产收益率';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.YEARLYROE
IS
  '年化收益率';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.F_NAV_ACCUMULATED
IS
  '累计净值';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.F_NAV_DIVACCUMULATED
IS
  '累计分红';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.F_NAV_ADJFACTOR
IS
  '复权因子';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.CCY
IS
  '币种';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.F_PRT_NETASSET
IS
  '资产净值';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.F_NAV_ADJUSTED
IS
  '复权单位净值';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.CREATE_USER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.CREATE_DEPT
IS
  '创建部门';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.CREATE_TIME
IS
  '创建时间';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.UPDATE_USER
IS
  '更新人';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.UPDATE_TIME
IS
  '更新时间';
  COMMENT ON COLUMN INTERF_CMFUNDNAV.SOURCE
IS
  '数据来源';

/*wind接口利率行情，SHIBOR行情、LIBOR行情等*/
drop TABLE INTERF_INT_RATE_INFO;
CREATE TABLE INTERF_INT_RATE_INFO
  (
    INT_RATE_ID  VARCHAR2(50 BYTE) NOT NULL ,
    EFF_DATE     DATE NOT NULL ,
    RELAESE_DATE DATE ,
    INT_RATE     NUMBER(30, 14) ,
    CCY          VARCHAR2(50 BYTE) ,
    CREATE_USER  VARCHAR2(20 BYTE) ,
    CREATE_DEPT  VARCHAR2(32 BYTE) ,
    CREATE_TIME  TIMESTAMP(6) ,
    UPDATE_USER  VARCHAR2(20 BYTE) ,
    UPDATE_TIME  TIMESTAMP(6) ,
    SOURCE       VARCHAR2(50 BYTE) NOT NULL ,
    CONSTRAINT PK_INTERF_INT_RATE_INFO PRIMARY KEY ( INT_RATE_ID , EFF_DATE , SOURCE ) 
	);
COMMENT ON TABLE INTERF_INT_RATE_INFO
IS
  'wind接口利率行情，SHIBOR行情、LIBOR行情等';
  COMMENT ON COLUMN INTERF_INT_RATE_INFO.INT_RATE_ID
IS
  '行情代码，行情名称从数据字典中获取';
  COMMENT ON COLUMN INTERF_INT_RATE_INFO.EFF_DATE
IS
  '生效日';
  COMMENT ON COLUMN INTERF_INT_RATE_INFO.RELAESE_DATE
IS
  '发布日';
  COMMENT ON COLUMN INTERF_INT_RATE_INFO.INT_RATE
IS
  '利率';
  COMMENT ON COLUMN INTERF_INT_RATE_INFO.CCY
IS
  '币种';
  COMMENT ON COLUMN INTERF_INT_RATE_INFO.CREATE_USER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_INT_RATE_INFO.CREATE_DEPT
IS
  '创建部门';
  COMMENT ON COLUMN INTERF_INT_RATE_INFO.CREATE_TIME
IS
  '创建时间';
  COMMENT ON COLUMN INTERF_INT_RATE_INFO.UPDATE_USER
IS
  '更新人';
  COMMENT ON COLUMN INTERF_INT_RATE_INFO.UPDATE_TIME
IS
  '更新时间';
  COMMENT ON COLUMN INTERF_INT_RATE_INFO.SOURCE
IS
  '数据来源';

/*wind接口指数行情，上证指数、沪深300指数等*/
drop TABLE INTERF_EXPONENT_QUOTE;
CREATE TABLE INTERF_EXPONENT_QUOTE
  (
    MARKET_ID     VARCHAR2(50 BYTE) NOT NULL ,
	MARKET_NAME  VARCHAR2(200),
    EFF_DATE      DATE NOT NULL ,
    RELAESE_DATE  DATE ,
    P_CLOSE_PRICE NUMBER(30, 14) ,
    OPEN_PRICE    NUMBER(30, 14) ,
    CLOSE_PRICE   NUMBER(30, 14) ,
    MAX_PRICE     NUMBER(30, 14) ,
    MIN_PRICE     NUMBER(30, 14) ,
	CHG  NUMERIC(30, 14),
    CREATE_USER   VARCHAR2(20 BYTE) ,
    CREATE_DEPT   VARCHAR2(32 BYTE) ,
    CREATE_TIME   TIMESTAMP(6) ,
    UPDATE_USER   VARCHAR2(20 BYTE) ,
    UPDATE_TIME   TIMESTAMP(6) ,
    SOURCE        VARCHAR2(50 BYTE) NOT NULL ,
	MARKET_DATE   DATE,
    CONSTRAINT PK_INTERF_EXPONENT_QUOTE PRIMARY KEY ( MARKET_ID , MARKET_DATE , SOURCE )
	);
COMMENT ON TABLE INTERF_EXPONENT_QUOTE
IS
  'wind接口指数行情，上证指数、沪深300指数等';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.MARKET_ID
IS
  '行情代码，行情名称从数据字典中获取';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.EFF_DATE
IS
  '生效日';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.RELAESE_DATE
IS
  '发布日';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.P_CLOSE_PRICE
IS
  '前收盘价';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.OPEN_PRICE
IS
  '开盘价';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.CLOSE_PRICE
IS
  '收盘价';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.MAX_PRICE
IS
  '最高价';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.MIN_PRICE
IS
  '最低价';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.CREATE_USER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.CREATE_DEPT
IS
  '创建部门';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.CREATE_TIME
IS
  '创建时间';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.UPDATE_USER
IS
  '更新人';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.UPDATE_TIME
IS
  '更新时间';
  COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.SOURCE
IS
  '数据来源';
COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.MARKET_NAME
  IS '指数名称';
COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.CHG
  IS '涨跌幅'; 
COMMENT ON COLUMN INTERF_EXPONENT_QUOTE.MARKET_DATE
  IS '行情日期';

/*wind接口汇率行情*/
drop table INTERF_EXCHARGE_QUOTE;
CREATE TABLE INTERF_EXCHARGE_QUOTE
  (
    EXCHARGE_CODE VARCHAR2(50 BYTE) NOT NULL ,
    TRADE_DATE    DATE NOT NULL ,
    OPEN_PRICE    NUMBER(30, 14) ,
    CLOSE_PRICE   NUMBER(30, 14) ,
    HIGH_PRICE    NUMBER(30, 14) ,
    LOW_PRICE     NUMBER(30, 14) ,
    MID_PRICE     NUMBER(30, 14) ,
    BASIS_CYY     VARCHAR2(50 BYTE) ,
    QUOT_CYY      VARCHAR2(50 BYTE) ,
    QUOT_UNIT     NUMBER(10, 0) ,
    CREATE_USER   VARCHAR2(20 BYTE) ,
    CREATE_DEPT   VARCHAR2(32 BYTE) ,
    CREATE_TIME   TIMESTAMP(6) ,
    UPDATE_USER   VARCHAR2(20 BYTE) ,
    UPDATE_TIME   TIMESTAMP(6) ,
    SOURCE        VARCHAR2(50 BYTE) NOT NULL ,
	MAKET_TYPE    VARCHAR2(50),
	TERM          VARCHAR2(50),
    CONSTRAINT PK_INTERF_EXCHARGE_QUOTE PRIMARY KEY ( EXCHARGE_CODE , TRADE_DATE , SOURCE ) 
	);
COMMENT ON TABLE INTERF_EXCHARGE_QUOTE
IS
  'wind接口汇率行情';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.EXCHARGE_CODE
IS
  '行情代码，一般为货币对';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.TRADE_DATE
IS
  '交易日';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.OPEN_PRICE
IS
  '开盘价';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.CLOSE_PRICE
IS
  '收盘价';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.HIGH_PRICE
IS
  '最高价';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.LOW_PRICE
IS
  '最低价';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.MID_PRICE
IS
  '中间价';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.BASIS_CYY
IS
  '基础币种，比如USD/CNY中的USD';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.QUOT_CYY
IS
  '报价币种，比如USD/CNY中的CNY';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.QUOT_UNIT
IS
  '报价单位';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.CREATE_USER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.CREATE_DEPT
IS
  '创建部门';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.CREATE_TIME
IS
  '创建时间';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.UPDATE_USER
IS
  '更新人';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.UPDATE_TIME
IS
  '更新时间';
  COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.SOURCE
IS
  '数据来源';
COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.MAKET_TYPE
  IS '行情类型';
COMMENT ON COLUMN INTERF_EXCHARGE_QUOTE.TERM
  IS '期限';

--wind接口收益率曲线表
drop TABLE INTERF_PAYHSRCCASHYIELD;
CREATE TABLE INTERF_PAYHSRCCASHYIELD
  (
    STATE_DATE VARCHAR2(10 BYTE) NOT NULL ,
    CURVE_CODE VARCHAR2(50 BYTE) NOT NULL ,
    CURVE_NAME VARCHAR2(128 BYTE) ,
    MTRTY      NUMBER(30, 14) NOT NULL ,
    YLD        NUMBER(30, 14) ,
    CURVE_TYPE CHAR(2 BYTE) ,
    N          NUMBER(20, 4) ,
    K          NUMBER(20, 4) ,
    CREATEDATE DATE NOT NULL ,
    CREATEUSER NVARCHAR2(20) ,
    LSTMNTDATE DATE ,
    LSTMNTUSER NVARCHAR2(20) ,
    SOURCE VARCHAR2(50 BYTE) NOT NULL ,
    CONSTRAINT PK_INTERF_PAYHSRCCASHYIELD PRIMARY KEY ( STATE_DATE , CURVE_CODE , MTRTY )
	);
COMMENT ON TABLE INTERF_PAYHSRCCASHYIELD
IS
  'wind接口收益率曲线表';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.STATE_DATE
IS
  '曲线日期';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.CURVE_CODE
IS
  '曲线code-原始code';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.CURVE_NAME
IS
  '曲线名称';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.MTRTY
IS
  '关键年限点';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.YLD
IS
  '收益率';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.CURVE_TYPE
IS
  '曲线类型';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.N
IS
  'N';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.K
IS
  'K';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.CREATEDATE
IS
  '创建日期';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.CREATEUSER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.LSTMNTDATE
IS
  '最后修改日期';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.LSTMNTUSER
IS
  '最后修改人';
  COMMENT ON COLUMN INTERF_PAYHSRCCASHYIELD.SOURCE
IS
  '数据来源';

--  基金场内行情 

drop table INTERF_F_MARKET_INFO cascade constraints;
CREATE TABLE INTERF_F_MARKET_INFO
  (
    FUND_CODE       VARCHAR2(32 BYTE) NOT NULL ,
    VALUE_DATE      DATE NOT NULL ,
	VALUATION_SOURCE VARCHAR2(50),
    FUND_TYPE        VARCHAR2(50),
    CLOSE_PRICE_PRE NUMBER(30, 14) NOT NULL ,
    CLOSE_PRICE     NUMBER(30, 14) NOT NULL ,
    AVERAGE_PRICE   NUMBER(30, 14) ,
    MAX_PRICE       NUMBER(30, 14) NOT NULL ,
    MIN_PRICE       NUMBER(30, 14) NOT NULL ,
    VOLUME          NUMBER(30, 2) ,
    TURNOVER        NUMBER(30, 2) ,
    CHANGE          NUMBER(30, 14) ,
    CHG             NUMBER(30, 14) ,
    AMPLITUDE       NUMBER(30, 14) ,
    TURNOVERRATE    NUMBER(30, 14) ,
    DISCOUNTRATE    NUMBER(30, 14) ,
    BUY_ONE_PRICE   NUMBER(30, 14) ,
    SELL_ONE_PRICE  NUMBER(30, 14) ,
    CREATE_USER     VARCHAR2(20 BYTE) NOT NULL ,
    CREATE_DEPT     VARCHAR2(32 BYTE) ,
    CREATE_TIME     TIMESTAMP(6) NOT NULL ,
    UPDATE_USER     VARCHAR2(20 BYTE) NOT NULL ,
    UPDATE_TIME     TIMESTAMP(6) NOT NULL ,
    SOURCE          VARCHAR2(50 BYTE) NOT NULL ,
    CONSTRAINT PK_INTERF_F_MARKET_INFO PRIMARY KEY ( FUND_CODE , VALUE_DATE , SOURCE )
	);
COMMENT ON TABLE INTERF_F_MARKET_INFO
IS
  '基金场内行情';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.FUND_CODE
IS
  '基金代码（市场代码）';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.VALUE_DATE
IS
  '交易日';
comment on column INTERF_F_MARKET_INFO.VALUATION_SOURCE
  is '估值来源';
comment on column INTERF_F_MARKET_INFO.FUND_TYPE
  is '基金类型';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.CLOSE_PRICE_PRE
IS
  '前收盘价';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.CLOSE_PRICE
IS
  '收盘价';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.AVERAGE_PRICE
IS
  '平均价';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.MAX_PRICE
IS
  '最高价';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.MIN_PRICE
IS
  '最低价';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.VOLUME
IS
  '成交量';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.TURNOVER
IS
  '成交金额';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.CHANGE
IS
  '涨跌';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.CHG
IS
  '涨跌幅';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.AMPLITUDE
IS
  '振幅';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.TURNOVERRATE
IS
  '换手率';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.DISCOUNTRATE
IS
  '折价率';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.BUY_ONE_PRICE
IS
  '买一价';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.SELL_ONE_PRICE
IS
  '卖一价';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.CREATE_USER
IS
  '创建人';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.CREATE_DEPT
IS
  '创建部门';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.CREATE_TIME
IS
  '创建时间';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.UPDATE_USER
IS
  '更新人';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.UPDATE_TIME
IS
  '更新时间';
  COMMENT ON COLUMN INTERF_F_MARKET_INFO.SOURCE
IS
  '数据来源';
  
  
  /*万德接口-股票信息*/
drop table INTERF_STOCK_INFO;
-- Create table
create table INTERF_STOCK_INFO
(
  ASSET_COOD          VARCHAR2(50) NOT NULL,
  ASSET_NAME          VARCHAR2(500),
  COMPANY_NAME        VARCHAR2(500),
  COMPANY_CODE        VARCHAR2(50),
  TRADE_PLACE         VARCHAR2(50),
  LISTING_BOARD       VARCHAR2(50),
  LISTING_DATE        DATE,
  DELISTING_DATE      DATE,
  IS_HORS_EXPRESS     VARCHAR2(50),
  IS_MARGIN_TRADING   VARCHAR2(50),
  CCY                 VARCHAR2(5),
  TRADE_CALENDER      VARCHAR2(50),
  STOCK_TYPE          VARCHAR2(50),
  INDUSTRY_N          VARCHAR2(250),
  INDUSTRY_S          VARCHAR2(250),
  ENTERPRISE_TYPE3    VARCHAR2(50),
  ENTERPRISE_TYPE1    VARCHAR2(50),
  ENTERPRISE_TYPE2    VARCHAR2(50),
  CREATE_USER         VARCHAR2(50),
  CREATE_DEPT         VARCHAR2(50),
  CREATE_TIME         TIMESTAMP(6),
  UPDATE_USER         VARCHAR2(50),
  UPDATE_TIME         TIMESTAMP(6)
   );
-- Add comments to the table 
comment on table INTERF_STOCK_INFO
  is '股票信息接口表';
  -- Add comments to the columns 
comment on column INTERF_STOCK_INFO.ASSET_COOD
  is '01.资产代码';
comment on column INTERF_STOCK_INFO.ASSET_NAME
  is '02.资产名称';  
comment on column INTERF_STOCK_INFO.COMPANY_NAME
  is '03.公司名称';   
comment on column INTERF_STOCK_INFO.COMPANY_CODE
  is '04.公司客户代码';   
comment on column INTERF_STOCK_INFO.TRADE_PLACE
  is '05.交易场所';    
comment on column INTERF_STOCK_INFO.LISTING_BOARD
  is '06.上市板';   
comment on column INTERF_STOCK_INFO.LISTING_DATE
  is '07.上市日期';  
comment on column INTERF_STOCK_INFO.DELISTING_DATE
  is '08.退市日期';  
comment on column INTERF_STOCK_INFO.IS_HORS_EXPRESS
  is '09.是否沪港通或深港通';
comment on column INTERF_STOCK_INFO.IS_MARGIN_TRADING
  is '10.是否融资融券标的';
comment on column INTERF_STOCK_INFO.CCY
  is '11.币种';
comment on column INTERF_STOCK_INFO.TRADE_CALENDER
  is '12.交易日历';
comment on column INTERF_STOCK_INFO.STOCK_TYPE
  is '13.股票类型';
comment on column INTERF_STOCK_INFO.INDUSTRY_N
  is '14.行业（国标）';
comment on column INTERF_STOCK_INFO.INDUSTRY_S
  is '15.行业（证监会）';
comment on column INTERF_STOCK_INFO.ENTERPRISE_TYPE3
  is '16.投资企业类型（按技术领域）';
comment on column INTERF_STOCK_INFO.ENTERPRISE_TYPE1
  is '17.投资企业类型（按规模）';
comment on column INTERF_STOCK_INFO.ENTERPRISE_TYPE2
  is '18.投资企业类型（按经济类型）';
comment on column INTERF_STOCK_INFO.CREATE_USER
  is '创建人';
comment on column INTERF_STOCK_INFO.CREATE_DEPT
  is '创建部门';
comment on column INTERF_STOCK_INFO.CREATE_TIME
  is '创建时间';
comment on column INTERF_STOCK_INFO.UPDATE_USER
  is '更新人';
comment on column INTERF_STOCK_INFO.UPDATE_TIME
  is '更新时间';
    
  -- Create/Recreate primary, unique and foreign key constraints 
alter table INTERF_STOCK_INFO
  add constraint PK_INTERF_STOCK_INFO primary key (ASSET_COOD);


/*万德接口-股票行情*/
drop table INTERF_STOCK_QUOTE;
-- Create table
create table INTERF_STOCK_QUOTE
(
  STOCK_COOD          VARCHAR2(20) NOT NULL,
  STOCK_NAME          VARCHAR2(500),
  TRADE_DATE          DATE,
  TOTAL_SHARECAP      NUMBER(30,4),
  CIRCULATE_SHARECAP  NUMBER(30,4),
  CLOSE_PRICE_PRE     NUMBER(30,14),
  OPENPRICE           NUMBER(30,14),
  MAX_PRICE           NUMBER(30,14),
  MIN_PRICE           NUMBER(30,14),
  CLOSE_PRICE         NUMBER(30,14),
  VOLUME              NUMBER(30,4),
  TURNOVER            NUMBER(30,4),
  MEAN_FIVEDAY        NUMBER(30,14),
  MEAN_FTENDAY        NUMBER(30,14),
  MEAN_TWENTYDAY      NUMBER(30,14),
  TRADE_STATUS        VARCHAR2(8),
  SOURCE              VARCHAR2(8),
  CHANGE              NUMBER(30,14),
  CHG                 NUMBER(30,14),
  AVERAGE_PRICE       NUMBER(30,14),
  REHAB_YESCLOSEPRICE NUMBER(30,14),
  REHAB_OPENPRICE     NUMBER(30,14),
  REHAB_MAXPRICE      NUMBER(30,14),
  REHAB_MINPRICE      NUMBER(30,14),
  REHAB_CLOSEPRICE    NUMBER(30,14),
  REHAB_FACTOR        NUMBER(30,14),
  LIMITSALE_ENDDATE   DATE,
  SHARECHANGE_DATE    DATE,
  INDUSTRY            VARCHAR2(250),
  INDUSTRY_INDEX      NUMBER(30,14),
  SURPLUS_LIMITSALE   NUMBER(20),
  MOBILITY_DISCOUNT   NUMBER(30,14),
  CREATE_USER         VARCHAR2(50),
  CREATE_DEPT         VARCHAR2(50),
  CREATE_TIME         TIMESTAMP(6),
  UPDATE_USER         VARCHAR2(50),
  UPDATE_TIME         TIMESTAMP(6)
   );
-- Add comments to the table 
comment on table INTERF_STOCK_QUOTE
  is '股票行情接口表';
-- Add comments to the columns 
comment on column INTERF_STOCK_QUOTE.STOCK_COOD
  is '01.股票代码';
comment on column INTERF_STOCK_QUOTE.STOCK_NAME
  is '02.股票名称';  
comment on column INTERF_STOCK_QUOTE.TRADE_DATE
  is '03.交易日期';   
comment on column INTERF_STOCK_QUOTE.TOTAL_SHARECAP
  is '04.总股本';    
comment on column INTERF_STOCK_QUOTE.CIRCULATE_SHARECAP
  is '05.流通股本';   
comment on column INTERF_STOCK_QUOTE.CLOSE_PRICE_PRE
  is '06.前收盘价（元）';  
comment on column INTERF_STOCK_QUOTE.OPENPRICE
  is '07.开盘价（元）';  
comment on column INTERF_STOCK_QUOTE.MAX_PRICE
  is '08.最高价（元）';
comment on column INTERF_STOCK_QUOTE.MIN_PRICE
  is '09.最低价（元）';
comment on column INTERF_STOCK_QUOTE.CLOSE_PRICE
  is '10.收盘价（元）';
comment on column INTERF_STOCK_QUOTE.VOLUME
  is '11.成交量';
comment on column INTERF_STOCK_QUOTE.TURNOVER
  is '12.成交金额';
comment on column INTERF_STOCK_QUOTE.MEAN_FIVEDAY
  is '13.5日均值（元）';
comment on column INTERF_STOCK_QUOTE.MEAN_FTENDAY
  is '14.10日均值（元）';
comment on column INTERF_STOCK_QUOTE.MEAN_TWENTYDAY
  is '15.20日均值（元）';
comment on column INTERF_STOCK_QUOTE.TRADE_STATUS
  is '16.交易状态';
comment on column INTERF_STOCK_QUOTE.SOURCE
  is '17.数据来源';
comment on column INTERF_STOCK_QUOTE.CHANGE
  is '18.涨跌';
comment on column INTERF_STOCK_QUOTE.CHG
  is '19.涨跌幅%';
comment on column INTERF_STOCK_QUOTE.AVERAGE_PRICE
  is '20.均价';
comment on column INTERF_STOCK_QUOTE.REHAB_YESCLOSEPRICE
  is '21.复权昨收盘价';
comment on column INTERF_STOCK_QUOTE.REHAB_OPENPRICE
  is '22.复权开盘价';
comment on column INTERF_STOCK_QUOTE.REHAB_MAXPRICE
  is '23.复权最高价';
comment on column INTERF_STOCK_QUOTE.REHAB_MINPRICE
  is '24.复权最低价';
comment on column INTERF_STOCK_QUOTE.REHAB_CLOSEPRICE
  is '25.复权收盘价';
comment on column INTERF_STOCK_QUOTE.REHAB_FACTOR
  is '26.复权因子';
comment on column INTERF_STOCK_QUOTE.LIMITSALE_ENDDATE
  is '27.限售期结束日';
comment on column INTERF_STOCK_QUOTE.SHARECHANGE_DATE
  is '28.股份变动公告日';
comment on column INTERF_STOCK_QUOTE.INDUSTRY
  is '29.所属行业';
comment on column INTERF_STOCK_QUOTE.INDUSTRY_INDEX
  is '30.对应行业指数';
comment on column INTERF_STOCK_QUOTE.SURPLUS_LIMITSALE
  is '31.剩余限售期';
comment on column INTERF_STOCK_QUOTE.MOBILITY_DISCOUNT
  is '32.流动性折扣(%)';
comment on column INTERF_STOCK_QUOTE.CREATE_USER
  is '创建人';
comment on column INTERF_STOCK_QUOTE.CREATE_DEPT
  is '创建部门';
comment on column INTERF_STOCK_QUOTE.CREATE_TIME
  is '创建时间';
comment on column INTERF_STOCK_QUOTE.UPDATE_USER
  is '更新人';
comment on column INTERF_STOCK_QUOTE.UPDATE_TIME
  is '更新时间';
    
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTERF_STOCK_QUOTE
  add constraint PK_INTERF_STOCK_QUOTE primary key (STOCK_COOD,TRADE_DATE,SOURCE);


/*万德接口-利率互换曲线行情*/
drop table INTERF_INTRATESWAP_VALUE;
-- Create table
create table INTERF_INTRATESWAP_VALUE
(
  CURVE_DATE          DATE,
  CURVE_CODE          VARCHAR2(50),
  CURVE_NAME          VARCHAR2(500),
  TREM_YEAR           NUMBER(20),
  CURVE_INT           NUMBER(30,14),
  CURVE_TYPE          VARCHAR2(50),
  CREATE_USER         VARCHAR2(50),
  CREATE_DEPT         VARCHAR2(50),
  CREATE_TIME         TIMESTAMP(6),
  UPDATE_USER         VARCHAR2(50),
  UPDATE_TIME         TIMESTAMP(6)
   );
-- Add comments to the table 
comment on table INTERF_INTRATESWAP_VALUE
  is '利率互换曲线行情接口表';
  -- Add comments to the columns 
comment on column INTERF_INTRATESWAP_VALUE.CURVE_DATE
  is '01.曲线日期';
comment on column INTERF_INTRATESWAP_VALUE.CURVE_CODE
  is '02.曲线code-原始code';  
comment on column INTERF_INTRATESWAP_VALUE.CURVE_NAME
  is '03.曲线名称';   
comment on column INTERF_INTRATESWAP_VALUE.TREM_YEAR
  is '04.期限（年）';    
comment on column INTERF_INTRATESWAP_VALUE.CURVE_INT
  is '05.收益率（%）';   
comment on column INTERF_INTRATESWAP_VALUE.CURVE_TYPE
  is '06.曲线类型：定盘曲线、收盘曲线';  
comment on column INTERF_INTRATESWAP_VALUE.CREATE_USER
  is '创建人';
comment on column INTERF_INTRATESWAP_VALUE.CREATE_DEPT
  is '创建部门';
comment on column INTERF_INTRATESWAP_VALUE.CREATE_TIME
  is '创建时间';
comment on column INTERF_INTRATESWAP_VALUE.UPDATE_USER
  is '更新人';
comment on column INTERF_INTRATESWAP_VALUE.UPDATE_TIME
  is '更新时间';
    
  -- Create/Recreate primary, unique and foreign key constraints 
alter table INTERF_INTRATESWAP_VALUE
  add constraint PK_INTERF_INTRATESWAP_VALUE primary key (CURVE_DATE,CURVE_CODE);
  

/*风险接口_中国A股日收益率接口表*/
drop table P_INTERF_ASHAREYIELD; 
create table P_INTERF_ASHAREYIELD
(
  object_id            VARCHAR2(100),
  s_info_windcode      VARCHAR2(40) not null,
  trade_dt             VARCHAR2(8) not null,
  pct_change_d         NUMBER(22,4),
  pct_change_w         NUMBER(22,4),
  pct_change_m         NUMBER(22,4),
  volume_w             NUMBER(22,4),
  volume_m             NUMBER(22,4),
  amount_w             NUMBER(22,4),
  amount_m             NUMBER(22,4),
  turnover_d           NUMBER(22,4),
  turnover_d_float     NUMBER(22,4),
  turnover_w           NUMBER(22,4),
  turnover_w_float     NUMBER(22,4),
  turnover_w_ave       NUMBER(22,4),
  turnover_w_ave_float NUMBER(22,4),
  turnover_m           NUMBER(22,4),
  turnover_m_float     NUMBER(22,4),
  turnover_m_ave       NUMBER(22,4),
  turnover_m_ave_float NUMBER(22,4),
  pct_change_ave_100w  NUMBER(22,4),
  std_deviation_100w   NUMBER(22,4),
  variance_100w        NUMBER(22,4),
  pct_change_ave_24m   NUMBER(22,4),
  std_deviation_24m    NUMBER(22,4),
  variance_24m         NUMBER(22,4),
  pct_change_ave_60m   NUMBER(22,4),
  std_deviation_60m    NUMBER(22,4),
  variance_60m         NUMBER(22,4),
  beta_day_1y          NUMBER(22,8),
  beta_day_2y          NUMBER(22,8),
  alpha_day_1y         NUMBER(22,8),
  alpha_day_2y         NUMBER(22,8),
  beta_100w            NUMBER(22,8),
  alpha_100w           NUMBER(22,8),
  beta_24m             NUMBER(22,8),
  beta_60m             NUMBER(22,8),
  alpha_24m            NUMBER(22,8),
  alpha_60m            NUMBER(22,8),
  opmode               VARCHAR2(2),
  create_time          TIMESTAMP(6),
  update_time          TIMESTAMP(6),
  constraint PK_P_INTERF_ASHAREYIELD primary key (S_INFO_WINDCODE, TRADE_DT)
);

comment on table P_INTERF_ASHAREYIELD
  is '风险接口_中国A股日收益率接口表';
comment on column P_INTERF_ASHAREYIELD.object_id
  is '对象ID';
comment on column P_INTERF_ASHAREYIELD.s_info_windcode
  is 'Wind代码';
comment on column P_INTERF_ASHAREYIELD.trade_dt
  is '日期';
comment on column P_INTERF_ASHAREYIELD.pct_change_d
  is '日涨跌幅(%)';
comment on column P_INTERF_ASHAREYIELD.pct_change_w
  is '周涨跌幅(%)';
comment on column P_INTERF_ASHAREYIELD.pct_change_m
  is '月涨跌幅(%)';
comment on column P_INTERF_ASHAREYIELD.volume_w
  is '周成交量';
comment on column P_INTERF_ASHAREYIELD.volume_m
  is '月成交量';
comment on column P_INTERF_ASHAREYIELD.amount_w
  is '周成交额';
comment on column P_INTERF_ASHAREYIELD.amount_m
  is '月成交额';
comment on column P_INTERF_ASHAREYIELD.turnover_d
  is '日换手率';
comment on column P_INTERF_ASHAREYIELD.turnover_d_float
  is '日换手率(自由流通股本)';
comment on column P_INTERF_ASHAREYIELD.turnover_w
  is '周换手率';
comment on column P_INTERF_ASHAREYIELD.turnover_w_float
  is '周换手率(自由流通股本)';
comment on column P_INTERF_ASHAREYIELD.turnover_w_ave
  is '周平均换手率';
comment on column P_INTERF_ASHAREYIELD.turnover_w_ave_float
  is '周平均换手率(自由流通股本)';
comment on column P_INTERF_ASHAREYIELD.turnover_m
  is '月换手率';
comment on column P_INTERF_ASHAREYIELD.turnover_m_float
  is '月换手率(自由流通股本)';
comment on column P_INTERF_ASHAREYIELD.turnover_m_ave
  is '月平均换手率';
comment on column P_INTERF_ASHAREYIELD.turnover_m_ave_float
  is '月平均换手率(自由流通股本)';
comment on column P_INTERF_ASHAREYIELD.pct_change_ave_100w
  is '周平均涨跌幅(100周)';
comment on column P_INTERF_ASHAREYIELD.std_deviation_100w
  is '周标准差(100周)';
comment on column P_INTERF_ASHAREYIELD.variance_100w
  is '周方差(100周)';
comment on column P_INTERF_ASHAREYIELD.pct_change_ave_24m
  is '月平均涨跌幅(24月)';
comment on column P_INTERF_ASHAREYIELD.std_deviation_24m
  is '月标准差(24月)';
comment on column P_INTERF_ASHAREYIELD.variance_24m
  is '月方差(24月)';
comment on column P_INTERF_ASHAREYIELD.pct_change_ave_60m
  is '月平均涨跌幅(60月)';
comment on column P_INTERF_ASHAREYIELD.std_deviation_60m
  is '月标准差(60月)';
comment on column P_INTERF_ASHAREYIELD.variance_60m
  is '月方差(60月)';
comment on column P_INTERF_ASHAREYIELD.beta_day_1y
  is '日BETA(1年)';
comment on column P_INTERF_ASHAREYIELD.beta_day_2y
  is '日BETA(2年)';
comment on column P_INTERF_ASHAREYIELD.alpha_day_1y
  is '日ALPHA(1年)';
comment on column P_INTERF_ASHAREYIELD.alpha_day_2y
  is '日ALPHA(2年)';
comment on column P_INTERF_ASHAREYIELD.beta_100w
  is '周BETA(100周)';
comment on column P_INTERF_ASHAREYIELD.alpha_100w
  is '周ALPHA(100周)';
comment on column P_INTERF_ASHAREYIELD.beta_24m
  is '月BETA(24月)';
comment on column P_INTERF_ASHAREYIELD.beta_60m
  is '月BETA(60月)';
comment on column P_INTERF_ASHAREYIELD.alpha_24m
  is '月ALPHA(24月)';
comment on column P_INTERF_ASHAREYIELD.alpha_60m
  is '月ALPHA(60月)';
comment on column P_INTERF_ASHAREYIELD.opmode
  is '数据状态 0:新增;1:修改;2:删除;';
comment on column P_INTERF_ASHAREYIELD.create_time
  is '创建时间';
comment on column P_INTERF_ASHAREYIELD.update_time
  is '更新时间';
                                                                                                                                 
/*万德接口-新债日历*/
DROP TABLE INTERF_SECRELEASE_INFO;
CREATE TABLE INTERF_SECRELEASE_INFO(
    SEC_ID                 VARCHAR2(50) NOT NULL,
    SECNAME                VARCHAR(200),
    SEC_ABBR               VARCHAR(200),
    TERM_YEARS             NUMBER(30,14),
    TRADE_MARKET           VARCHAR(50),
    REGIST_ORG             VARCHAR(50),
    ISSUE_PRICE            NUMERIC(20),
    PUBENDDATE             DATE,
    VDATE                  DATE,
    MDATE                  DATE,
    SECTYPE                VARCHAR(50),
    SECTYPE2               VARCHAR(50),
    CHBSECTYPE             VARCHAR(50),
    CHBSECTYPE2            VARCHAR(50),
    PBOCSECTYPE            VARCHAR(50),
    PBOCSECTYPE2           VARCHAR(50),
    ISSUE_RATE             NUMBER(30,14),
    ISSUE_AMT              NUMBER(20,4),
    ISSUER                 VARCHAR(32),
    SPONSOR                VARCHAR2(32),
    OTH_CLAUSE             VARCHAR2(1000),
    MAIN_SALER             VARCHAR2(32),
    ISRIGHT                VARCHAR(50),
    ISSUETYPE              VARCHAR(50),
    BLNAREA                VARCHAR(50),
    CAPIINCOMEFEATURE      VARCHAR(50),
    CCY                    CHAR(3),
    CALENDARID             VARCHAR(50),
    FACEVALUE              NUMBER(20,4),
    BID_DATE               DATE,
    PAY_DATE               DATE,
    SEC_GRADE_RESULT       VARCHAR2(50),
    ISSUER_GRADE_RESULT    VARCHAR2(50),
    ONMARKET_DATE          DATE,
    BID_PRICE_MIN          NUMBER(30,14),
    BID_PRICE_MAX          NUMBER(30,15),
    CREATE_USER            VARCHAR(20),
    CREATE_DEPT            VARCHAR(32),
    CREATE_TIME            TIMESTAMP,
    UPDATE_USER            VARCHAR(20),
    UPDATE_TIME            TIMESTAMP,
    SOURCE                 VARCHAR(50) NOT NULL,
    CONSTRAINT PK_INTERF_SECRELEASE_INFO PRIMARY KEY(SEC_ID, SOURCE)
);
COMMENT ON TABLE INTERF_SECRELEASE_INFO 
  IS '新债日历';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.SEC_ID 
  IS '债券ID：xxxx.IB/xxxx.SZ';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.SECNAME 
  IS '债券名称';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.SEC_ABBR 
  IS '债券简称';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.TERM_YEARS 
  IS '期限（年）';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.TRADE_MARKET 
  IS '交易场所';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.REGIST_ORG 
  IS '登记托管机构';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.ISSUE_PRICE 
  IS '发行价格';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.PUBENDDATE 
  IS '发行日';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.VDATE 
  IS '起息日';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.MDATE 
  IS '到期日';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.SECTYPE 
  IS '系统债券类型';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.SECTYPE2 
  IS '系统债券类型';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.CHBSECTYPE 
  IS '中债债券类型';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.CHBSECTYPE2 
  IS '中债债券类型';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.PBOCSECTYPE 
  IS '人行债券类型';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.PBOCSECTYPE2 
  IS '人行债券类型';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.ISSUE_RATE 
  IS '发行利率';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.ISSUE_AMT 
  IS '发行金额（万元）';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.ISSUER 
  IS '发行人';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.SPONSOR 
  IS '担保人';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.OTH_CLAUSE 
  IS '特殊条款';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.MAIN_SALER 
  IS '主承销商';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.ISRIGHT 
  IS '是否含权债';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.ISSUETYPE 
  IS '发行方式';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.BLNAREA 
  IS '境内外';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.CAPIINCOMEFEATURE 
  IS '保本标识';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.CCY 
  IS '币种';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.CALENDARID 
  IS '交易日历';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.FACEVALUE 
  IS '面值';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.BID_DATE 
  IS '投标日期';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.PAY_DATE 
  IS '缴款日';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.SEC_GRADE_RESULT 
  IS '债项评级';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.ISSUER_GRADE_RESULT 
  IS '主体评级';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.ONMARKET_DATE 
  IS '上市日';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.BID_PRICE_MIN  
  IS '投标区间(最小）';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.BID_PRICE_MAX 
  IS '投标区间(最大）';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.CREATE_USER 
  IS '创建人';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.CREATE_DEPT 
  IS '创建部门';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.CREATE_TIME 
  IS '创建时间';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.UPDATE_USER 
  IS '更新人';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.UPDATE_TIME 
  IS '更新时间';
COMMENT ON COLUMN INTERF_SECRELEASE_INFO.SOURCE 
  IS '数据来源';

/*机构负面信息接口表*/
DROP TABLE INTERF_NEGATIVE_NEWS;
-- CREATE TABLE
CREATE TABLE INTERF_NEGATIVE_NEWS
(
  ORG_NO      VARCHAR2(30),
  PUB_DATE    DATE,
  TITLE       VARCHAR2(100),
  COMINFO     CLOB,
  LINKID      VARCHAR2(100),
  CREATE_USER VARCHAR2(20) NOT NULL,
  CREATE_DEPT VARCHAR2(32),
  CREATE_TIME TIMESTAMP(6) NOT NULL,
  UPDATE_USER VARCHAR2(20) NOT NULL,
  UPDATE_TIME TIMESTAMP(6) NOT NULL,
  SOURCE      VARCHAR2(50)
);
-- ADD COMMENTS TO THE TABLE 
COMMENT ON TABLE INTERF_NEGATIVE_NEWS
  IS '机构负面信息接口表';
-- ADD COMMENTS TO THE COLUMNS 
COMMENT ON COLUMN INTERF_NEGATIVE_NEWS.ORG_NO
  IS '机构代码';
COMMENT ON COLUMN INTERF_NEGATIVE_NEWS.PUB_DATE
  IS '日期';
COMMENT ON COLUMN INTERF_NEGATIVE_NEWS.TITLE
  IS '标题';
COMMENT ON COLUMN INTERF_NEGATIVE_NEWS.COMINFO
  IS '正文';
COMMENT ON COLUMN INTERF_NEGATIVE_NEWS.LINKID
  IS '网址链接';
COMMENT ON COLUMN INTERF_NEGATIVE_NEWS.CREATE_USER
  IS '创建人';
COMMENT ON COLUMN INTERF_NEGATIVE_NEWS.CREATE_DEPT
  IS '创建部门';
COMMENT ON COLUMN INTERF_NEGATIVE_NEWS.CREATE_TIME
  IS '创建时间';
COMMENT ON COLUMN INTERF_NEGATIVE_NEWS.UPDATE_USER
  IS '更新人';
COMMENT ON COLUMN INTERF_NEGATIVE_NEWS.UPDATE_TIME
  IS '更新时间';
COMMENT ON COLUMN INTERF_NEGATIVE_NEWS.SOURCE
  IS '数据来源';
-- CREATE/RECREATE PRIMARY, UNIQUE AND FOREIGN KEY CONSTRAINTS 
ALTER TABLE INTERF_NEGATIVE_NEWS
  ADD CONSTRAINT INTERF_NEGATIVE_NEWS PRIMARY KEY (ORG_NO, PUB_DATE);

/*机构关联信息接口表*/
-- CREATE TABLE
DROP TABLE INTERF_CORRELATION;
CREATE TABLE INTERF_CORRELATION
(
  ORG_NO         VARCHAR2(30),
  COR_CLASS      VARCHAR2(50),
  COR_ORGTYPE    VARCHAR2(50),
  COR_ORGID      VARCHAR2(100),
  EXECUTIVE_NAME VARCHAR2(50),
  TITLE          VARCHAR2(100),
  CREATE_USER    VARCHAR2(20) NOT NULL,
  CREATE_DEPT    VARCHAR2(32),
  CREATE_TIME    TIMESTAMP(6) NOT NULL,
  UPDATE_USER    VARCHAR2(20) NOT NULL,
  UPDATE_TIME    TIMESTAMP(6) NOT NULL,
  SOURCE         VARCHAR2(50)
);
-- ADD COMMENTS TO THE TABLE 
COMMENT ON TABLE INTERF_CORRELATION
  IS '机构关联信息接口表';
-- ADD COMMENTS TO THE COLUMNS 
COMMENT ON COLUMN INTERF_CORRELATION.ORG_NO
  IS '机构代码';
COMMENT ON COLUMN INTERF_CORRELATION.COR_CLASS
  IS '关联分类';
COMMENT ON COLUMN INTERF_CORRELATION.COR_ORGTYPE
  IS '关联机构类型';
COMMENT ON COLUMN INTERF_CORRELATION.COR_ORGID
  IS '关联机构代码';
COMMENT ON COLUMN INTERF_CORRELATION.EXECUTIVE_NAME
  IS '人员名称';
COMMENT ON COLUMN INTERF_CORRELATION.TITLE
  IS '职称';
COMMENT ON COLUMN INTERF_CORRELATION.CREATE_USER
  IS '创建人';
COMMENT ON COLUMN INTERF_CORRELATION.CREATE_DEPT
  IS '创建部门';
COMMENT ON COLUMN INTERF_CORRELATION.CREATE_TIME
  IS '创建时间';
COMMENT ON COLUMN INTERF_CORRELATION.UPDATE_USER
  IS '更新人';
COMMENT ON COLUMN INTERF_CORRELATION.UPDATE_TIME
  IS '更新时间';
COMMENT ON COLUMN INTERF_CORRELATION.SOURCE
  IS '数据来源';
-- CREATE/RECREATE PRIMARY, UNIQUE AND FOREIGN KEY CONSTRAINTS 
ALTER TABLE INTERF_CORRELATION
  ADD CONSTRAINT INTERF_CORRELATION PRIMARY KEY (ORG_NO);
