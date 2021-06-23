drop table  hk_jsmx;
create table hk_jsmx
(
    SEQNO            varchar(32) not null,
    IMPORT_DATE      date,
    DATA_FILE        varchar(256),
    IS_SETTLE_TARGET varchar(50),
    JLGS           varchar(3),
    SCDM           varchar(2),
    JLLX           varchar(3),
    JYFS           varchar(3),
    JSFS           varchar(3),
    YWLX           varchar(3),
    QSBZ           varchar(3),
    BDLX           varchar(3),
    QSLSH          varchar(16),
    CJBH           varchar(18),
    SQBH           varchar(18),
    WTBH           varchar(16),
    JYRQ           varchar(8),
    QSRQ           varchar(8),
    JSRQ           varchar(8),
    QTRQ           varchar(8),
    CJSJ           varchar(6),
    XWH1           varchar(6),
    XWH2           varchar(6),
    XWHY           varchar(8),
    JSHY           varchar(8),
    TGHY           varchar(8),
    ZQZH           varchar(20),
    ZQDM1          varchar(8),
    ZQDM2          varchar(8),
    ZQLB           varchar(2),
    LTLX           varchar(1),
    QYLB           varchar(2),
    GPNF           varchar(4),
    QYCS           varchar(1),
    MMBZ           varchar(1),
    SL             varchar(20),
    CJSL           varchar(20),
    ZJZH           varchar(25),
    BZ             varchar(3),
    JG1            varchar(18),
    JG2            varchar(18),
    WBJE           varchar(19),
    YHS            varchar(19),
    JYZF           varchar(19),
    JYF            varchar(19),
    SYF            varchar(19),
    JSF            varchar(19),
    QTJE           varchar(19),
    WBYSF          varchar(19),
    WBHL           varchar(18),
    YSFJE          varchar(19),
    JGDM           varchar(4),
    FJSM           varchar(40),
    CREATE_USER    varchar(20) not null,
    CREATE_DEPT    varchar(32),
    CREATE_TIME    date not null,
    UPDATE_USER    varchar(20) not null,
    UPDATE_TIME    date not null,
    constraint PK_HK_JSMX primary key (SEQNO)
);
-- Add comments to the table 
comment on table HK_JSMX
  is '沪港通hk_jsmx';
-- Add comments to the columns 
comment on column HK_JSMX.SEQNO
  is '流水号';
comment on column HK_JSMX.IMPORT_DATE
  is '导入日期';
comment on column HK_JSMX.DATA_FILE
  is '数据文件';  
comment on column HK_JSMX.IS_SETTLE_TARGET
  is '是否本次数据清算对象';  
comment on column HK_JSMX.JLGS
  is '记录格式';
comment on column HK_JSMX.SCDM
  is '市场代码';
comment on column HK_JSMX.JLLX
  is '记录类型';
comment on column HK_JSMX.JYFS
  is '交易方式';
comment on column HK_JSMX.JSFS
  is '交收方式';
comment on column HK_JSMX.YWLX
  is '业务类型';  
comment on column HK_JSMX.QSBZ
  is '清算标志';    
comment on column HK_JSMX.BDLX
  is '变动类型';  
comment on column HK_JSMX.QSLSH
  is '清算流水号';   
comment on column HK_JSMX.CJBH
  is '成交编号';  
comment on column HK_JSMX.SQBH
  is '申请编号';    
comment on column HK_JSMX.WTBH
  is '委托编号';  
comment on column HK_JSMX.JYRQ
  is '交易日期';    
comment on column HK_JSMX.QSRQ
  is '清算日期';   
comment on column HK_JSMX.JSRQ
  is '交收日期';     
comment on column HK_JSMX.QTRQ
  is '其他日期';   
comment on column HK_JSMX.CJSJ
  is '成交时间';   
comment on column HK_JSMX.XWH1
  is '业务单元1';     
comment on column HK_JSMX.XWH2
  is '业务单元2'; 
comment on column HK_JSMX.XWHY
  is '业务单元1所属结算参与机构的清算编号';
comment on column HK_JSMX.JSHY
  is '结算参与机构的清算编号';
comment on column HK_JSMX.TGHY
  is '指定交易的清算编号';
comment on column HK_JSMX.ZQZH
  is '证券账号';
comment on column HK_JSMX.ZQDM1
  is '证券代码1';
comment on column HK_JSMX.ZQDM2
  is '证券代码2';
comment on column HK_JSMX.ZQLB
  is '证券类别';
comment on column HK_JSMX.LTLX
  is '流通类型';
comment on column HK_JSMX.QYLB
  is '权益类别';
comment on column HK_JSMX.GPNF
  is '挂牌年份';
comment on column HK_JSMX.QYCS
  is '权益次数';
comment on column HK_JSMX.MMBZ
  is '买卖标志';
comment on column HK_JSMX.SL
  is '交收数量'; 
comment on column HK_JSMX.CJSL
  is '成交数量'; 
comment on column HK_JSMX.ZJZH
  is '资金账号'; 
comment on column HK_JSMX.BZ
  is '币种';
comment on column HK_JSMX.JG1
  is '价格1';
comment on column HK_JSMX.JG2
  is '价格2';
comment on column HK_JSMX.WBJE
  is '清算金额';
comment on column HK_JSMX.YHS
  is '印花税';
comment on column HK_JSMX.JYZF
  is '交易征费';
comment on column HK_JSMX.JYF
  is '交易费';
comment on column HK_JSMX.SYF
  is '交易系统使用费';
comment on column HK_JSMX.JSF
  is '股份交收费';
comment on column HK_JSMX.QTJE
  is '其他金额';
comment on column HK_JSMX.WBYSF
  is '应收付金额';
comment on column HK_JSMX.WBHL
  is '汇率';
comment on column HK_JSMX.YSFJE
  is '人民币应收付';
comment on column HK_JSMX.JGDM
  is '结果代码';
comment on column HK_JSMX.FJSM
  is '附加说明';
comment on column HK_JSMX.CREATE_USER
  is '创建人';
comment on column HK_JSMX.CREATE_DEPT
  is '创建部门';
comment on column HK_JSMX.CREATE_TIME
  is '创建时间';
comment on column HK_JSMX.UPDATE_USER
  is '更新人';
comment on column HK_JSMX.UPDATE_TIME
  is '更新时间';

  

drop table hk_tz;
create table hk_tz
(
    SEQNO               varchar(32)    not null,
    IMPORT_DATE         date,
    DATA_FILE           varchar(256),
    IS_SETTLE_TARGET    varchar(50),
    SCDM                varchar(2),
    TZLB                varchar(3),
    TZRQ                varchar(8),
    QSBH                varchar(8),
    ZQDM                varchar(8),
    ZQLB                varchar(2),
    LTLX                varchar(1),
    QYLB                varchar(2),
    GPNF                varchar(4),
    QYCS                varchar(1),
    ZH1                 varchar(25),
    ZH2                 varchar(25),
    RQ1                 varchar(8),
    RQ2                 varchar(8),
    RQ3                 varchar(8),
    JE1                 varchar(19),
    JE2                 varchar(19),
    JE3                 varchar(19),
    JG1                 varchar(18),
    JG2                 varchar(18),
    BZ                  varchar(3),
    WBHL                varchar(18),
    BL1                 varchar(18),
    BL2                 varchar(18),
    SL1                 varchar(20),
    SL2                 varchar(20),
    LX1                 varchar(3),
    LX2                 varchar(3),
    FZDM1               varchar(20),
    FZDM2               varchar(20),
    FJSM1               varchar(80),
    FJSM2               varchar(80),
    CREATE_USER         varchar(20) not null,
    CREATE_DEPT         varchar(32),
    CREATE_TIME         date not null,
    UPDATE_USER         varchar(20) not null,
    UPDATE_TIME         date not null,
    constraint PK_HK_TZ primary key (SEQNO)
);
-- Add comments to the table 
comment on table HK_TZ
  is '沪港通hk_tz';
-- Add comments to the columns   
comment on column HK_TZ.SEQNO
  is '流水号';
comment on column HK_TZ.IMPORT_DATE
  is '导入日期';
comment on column HK_TZ.DATA_FILE
  is '数据文件';  
comment on column HK_TZ.IS_SETTLE_TARGET
  is '是否本次数据清算对象';
comment on column HK_TZ.SCDM
  is '市场代码';
comment on column HK_TZ.TZLB
  is '通知类别';
comment on column HK_TZ.TZRQ
  is '通知日期';
comment on column HK_TZ.QSBH
  is '结算参与机构的清算编号';
comment on column HK_TZ.ZQDM
  is '证券代码';  
comment on column HK_TZ.ZQLB
  is '证券类别';
comment on column HK_TZ.LTLX
  is '流通类型';
comment on column HK_TZ.QYLB
  is '权益类别';
comment on column HK_TZ.GPNF
  is '挂牌年份';
comment on column HK_TZ.QYCS
  is '权益次数';
comment on column HK_TZ.ZH1
  is '账户1';    
comment on column HK_TZ.ZH2
  is '账户2';   
comment on column HK_TZ.RQ1
  is '日期1';    
comment on column HK_TZ.RQ2
  is '日期2';
comment on column HK_TZ.RQ3
  is '日期3';    
comment on column HK_TZ.JE1
  is '金额1';
comment on column HK_TZ.JE2
  is '金额2';
comment on column HK_TZ.JE3
  is '金额3';
comment on column HK_TZ.JG1
  is '价格1';
comment on column HK_TZ.JG2
  is '价格2';
comment on column HK_TZ.BZ
  is '币种';
comment on column HK_TZ.WBHL
  is '汇率';
comment on column HK_TZ.BL1
  is '比率1';
comment on column HK_TZ.BL2
  is '比率2';
comment on column HK_TZ.SL1
  is '数量1';
comment on column HK_TZ.SL2
  is '数量2';
comment on column HK_TZ.LX1
  is '类型1';
comment on column HK_TZ.LX2
  is '类型2';
comment on column HK_TZ.FZDM1
  is '辅助代码1';
comment on column HK_TZ.FZDM2
  is '辅助代码2';
comment on column HK_TZ.FJSM1
  is '附加说明1';
comment on column HK_TZ.FJSM2
  is '附加说明2';
comment on column HK_TZ.CREATE_USER
  is '创建人';
comment on column HK_TZ.CREATE_DEPT
  is '创建部门';
comment on column HK_TZ.CREATE_TIME
  is '创建时间';
comment on column HK_TZ.UPDATE_USER
  is '更新人';
comment on column HK_TZ.UPDATE_TIME
  is '更新时间';

  
drop table  SZHK_SJSMX;
create table SZHK_SJSMX
(
    SEQNO               varchar(32)    not null,
    IMPORT_DATE         date,
    DATA_FILE           varchar(256),
    IS_SETTLE_TARGET    varchar(50),
    SCDM                varchar(2),
    JYFS                varchar(3),
    JSFS                varchar(3),
    YWLB                varchar(4),
    ZQLB                varchar(2),
    QSLSH               varchar(16),
    CJBH                varchar(18),
    WTBH                varchar(16),
    JYRQ                varchar(8),
    QSRQ                varchar(8),
    JSRQ                varchar(8),
    QTRQ                varchar(8),
    CJSJ                varchar(6),
    JYDY                varchar(6),
    TGDY                varchar(6),
    JSZH                varchar(8),
    ZQZH                varchar(20),
    ZQDM1               varchar(8),
    ZQDM2               varchar(8),
    CJSL                number(20, 3),
    CJJG                number(18, 10),
    JG2                 number(18, 10),
    QSSL                number(20, 3),
    WBJE                number(19, 2),
    ZJZH                varchar(25),
    YHS                 number(19, 2),
    JYZF                number(19, 2),
    JYF                 number(19, 2),
    SYF                 number(19, 2),
    JSF                 number(19, 2),
    QTJE                number(19, 2),
    WBYSF               number(19, 2),
    QSBZ                varchar(3),
    HL                  number(18, 10),
    YSFJE               number(19, 2),
    CWDH                varchar(4),
    FJSM                varchar(40),
    FSRQ                varchar(8),
    CREATE_USER         varchar(20) not null,
    CREATE_DEPT         varchar(32),
    CREATE_TIME         date not null,
    UPDATE_USER         varchar(20) not null,
    UPDATE_TIME         date not null,
    constraint PK_SZHK_SJSMX primary key (SEQNO)
);
-- Add comments to the table 
comment on table SZHK_SJSMX
  is '深港通SZHK_SJSMX';
-- Add comments to the columns   
comment on column SZHK_SJSMX.SEQNO
  is '流水号';
comment on column SZHK_SJSMX.IMPORT_DATE
  is '导入日期';
comment on column SZHK_SJSMX.DATA_FILE
  is '数据文件';  
comment on column SZHK_SJSMX.IS_SETTLE_TARGET
  is '是否本次数据清算对象';
comment on column SZHK_SJSMX.SCDM
  is '市场代码';
comment on column SZHK_SJSMX.JYFS
  is '交易方式';
comment on column SZHK_SJSMX.JSFS
  is '交收方式';
comment on column SZHK_SJSMX.YWLB
  is '业务类型';
comment on column SZHK_SJSMX.ZQLB
  is '证券类别';  
comment on column SZHK_SJSMX.QSLSH
  is '清算流水号';
comment on column SZHK_SJSMX.CJBH
  is '成交编号';
comment on column SZHK_SJSMX.WTBH
  is '委托编号';
comment on column SZHK_SJSMX.JYRQ
  is '交易日期';
comment on column SZHK_SJSMX.QSRQ
  is '清算日期';
comment on column SZHK_SJSMX.JSRQ
  is '交收日期';    
comment on column SZHK_SJSMX.QTRQ
  is '其他日期';   
comment on column SZHK_SJSMX.CJSJ
  is '成交时间';    
comment on column SZHK_SJSMX.JYDY
  is '交易单元';
comment on column SZHK_SJSMX.TGDY
  is '托管单元';    
comment on column SZHK_SJSMX.JSZH
  is '结算账号';
comment on column SZHK_SJSMX.ZQZH
  is '证券账号';
comment on column SZHK_SJSMX.ZQDM1
  is '证券代码1';
comment on column SZHK_SJSMX.ZQDM2
  is '证券代码2';
comment on column SZHK_SJSMX.CJSL
  is '成交数量';
comment on column SZHK_SJSMX.CJJG
  is '成交价格';
comment on column SZHK_SJSMX.JG2
  is '价格2';
comment on column SZHK_SJSMX.QSSL
  is '清算数量';
comment on column SZHK_SJSMX.WBJE
  is '外币清算金额';
comment on column SZHK_SJSMX.ZJZH
  is '资金账号';
comment on column SZHK_SJSMX.YHS
  is '印花税';
comment on column SZHK_SJSMX.JYZF
  is '交易征费';
comment on column SZHK_SJSMX.JYF
  is '交易费';
comment on column SZHK_SJSMX.SYF
  is '交易系统使用费';
comment on column SZHK_SJSMX.JSF
  is '股份交收费';
comment on column SZHK_SJSMX.QTJE
  is '其他金额';
comment on column SZHK_SJSMX.WBYSF
  is '应收付金额';

comment on column SZHK_SJSMX.QSBZ
  is '清算币种';
comment on column SZHK_SJSMX.HL
  is '汇率';
comment on column SZHK_SJSMX.YSFJE
  is '人民币应收付';
comment on column SZHK_SJSMX.CWDH
  is '错误代号';
comment on column SZHK_SJSMX.FJSM
  is '附加说明';
comment on column SZHK_SJSMX.FSRQ
  is '发送日期'; 
comment on column SZHK_SJSMX.CREATE_USER
  is '创建人';
comment on column SZHK_SJSMX.CREATE_DEPT
  is '创建部门';
comment on column SZHK_SJSMX.CREATE_TIME
  is '创建时间';
comment on column SZHK_SJSMX.UPDATE_USER
  is '更新人';
comment on column SZHK_SJSMX.UPDATE_TIME
  is '更新时间';  
  
 
drop table SZHK_TZ;
create table SZHK_TZ
(
    SEQNO                    varchar(32) not null,
    IMPORT_DATE              date,
    DATA_FILE                varchar(256),
    IS_SETTLE_TARGET         varchar(50),
    SCDM                     varchar(2),
    TZLB                     varchar(3),
    FSRQ                     varchar(8),
    ZQDM                     varchar(8),
    GFXZ                     varchar(2),
    SSZT                     varchar(1),
    QYLB                     varchar(2),
    QYBH                     varchar(10),
    ZH1                      varchar(25),
    ZH2                      varchar(25),
    RQ1                      varchar(8),
    RQ2                      varchar(8),
    RQ3                      varchar(8),
    JE1                      number(19, 2),
    JE2                      number(19, 2),
    JE3                      number(19, 2),
    JG1                      number(18, 10),
    JG2                      number(18, 10),
    BZ                       varchar(3),
    HL                       number(18, 10),
    BL1                      number(18, 10),
    BL2                      number(18, 10),
    SL1                      number(20, 3),
    SL2                      number(20, 3),
    LX1                      varchar(3),
    LX2                      varchar(3),
    FZDM1                    varchar(20),
    FZDM2                    varchar(20),
    FJSM1                    varchar(80),
    FJSM2                    varchar(80),
    ZT                       varchar(1),
    CREATE_USER              varchar(20) not null,
    CREATE_DEPT              varchar(32),
    CREATE_TIME              date not null,
    UPDATE_USER              varchar(20) not null,
    UPDATE_TIME              date not null,
    constraint PK_SZHK_TZ primary key (SEQNO)
);  

-- Add comments to the table 
comment on table SZHK_TZ
  is '深港通SZHK_TZ';
-- Add comments to the columns   
comment on column SZHK_TZ.SEQNO
  is '流水号';
comment on column SZHK_TZ.IMPORT_DATE
  is '导入日期';
comment on column SZHK_TZ.DATA_FILE
  is '数据文件';  
comment on column SZHK_TZ.IS_SETTLE_TARGET
  is '是否本次数据清算对象';
comment on column SZHK_TZ.SCDM
  is '市场代码';
comment on column SZHK_TZ.TZLB
  is '通知类别';
comment on column SZHK_TZ.FSRQ
  is '通知发送日期';
comment on column SZHK_TZ.ZQDM
  is '证券代码';
comment on column SZHK_TZ.GFXZ
  is '股份性质';  
comment on column SZHK_TZ.SSZT
  is '上市状态';
comment on column SZHK_TZ.QYLB
  is '权益类别';
comment on column SZHK_TZ.QYBH
  is '权益编号';
comment on column SZHK_TZ.ZH1
  is '账户1';    
comment on column SZHK_TZ.ZH2
  is '账户2';   
comment on column SZHK_TZ.RQ1
  is '日期1';    
comment on column SZHK_TZ.RQ2
  is '日期2';
comment on column SZHK_TZ.RQ3
  is '日期3';    
comment on column SZHK_TZ.JE1
  is '金额1';
comment on column SZHK_TZ.JE2
  is '金额2';
comment on column SZHK_TZ.JE3
  is '金额3';
comment on column SZHK_TZ.JG1
  is '价格1';
comment on column SZHK_TZ.JG2
  is '价格2';
comment on column SZHK_TZ.BZ
  is '币种';
comment on column SZHK_TZ.HL
  is '汇率';
comment on column SZHK_TZ.BL1
  is '比率1';
comment on column SZHK_TZ.BL2
  is '比率2';
comment on column SZHK_TZ.SL1
  is '数量1';
comment on column SZHK_TZ.SL2
  is '数量2';
comment on column SZHK_TZ.LX1
  is '类型1';
comment on column SZHK_TZ.LX2
  is '类型2';
comment on column SZHK_TZ.FZDM1
  is '辅助代码1';
comment on column SZHK_TZ.FZDM2
  is '辅助代码2';
comment on column SZHK_TZ.FJSM1
  is '附加说明1';
comment on column SZHK_TZ.FJSM2
  is '附加说明2';
comment on column SZHK_TZ.ZT
  is '状态';
comment on column SZHK_TZ.CREATE_USER
  is '创建人';
comment on column SZHK_TZ.CREATE_DEPT
  is '创建部门';
comment on column SZHK_TZ.CREATE_TIME
  is '创建时间';
comment on column SZHK_TZ.UPDATE_USER
  is '更新人';
comment on column SZHK_TZ.UPDATE_TIME
  is '更新时间';
 

drop table COST_POSITION_DB;
create table COST_POSITION_DB
(
    PORTFOLIO_ID            varchar(32)     not null,
    FINPROD_ID              varchar(32)     not null,
    CDATE                   date            not null,
    COST_POSITION           decimal(30, 14),
    COST_POSITION_NUM       decimal(30, 14),
    CREATE_USER             varchar(20)     not null,
    CREATE_DEPT             varchar(32),
    CREATE_TIME             date            not null,
    UPDATE_USER             varchar(20)     not null,
    UPDATE_TIME             date            not null,
    constraint PK_COST_POSITION_DB primary key (PORTFOLIO_ID, FINPROD_ID, CDATE)    
);
-- Add comments to the table 
comment on table COST_POSITION_DB
  is '组合持仓成本';
-- Add comments to the columns 
comment on column COST_POSITION_DB.PORTFOLIO_ID
  is '组合代码';
comment on column COST_POSITION_DB.FINPROD_ID
  is '金融产品代码';      
comment on column COST_POSITION_DB.CDATE
  is '日期';  
comment on column COST_POSITION_DB.COST_POSITION
  is '持仓单位成本(综合成本法)';  
comment on column COST_POSITION_DB.COST_POSITION_NUM
  is '持仓数量(综合成本法)';  
comment on column COST_POSITION_DB.CREATE_USER
  is '创建人';
comment on column COST_POSITION_DB.CREATE_DEPT
  is '创建部门';
comment on column COST_POSITION_DB.CREATE_TIME
  is '创建时间';
comment on column COST_POSITION_DB.UPDATE_USER
  is '更新人';
comment on column COST_POSITION_DB.UPDATE_TIME
  is '更新时间';      

 ---- 
 drop table REFER_SETTLE_RATE_MANAGEMENT;
create table REFER_SETTLE_RATE_MANAGEMENT
(
    VALUE_DATE     varchar(50)    not null,
    CCY            varchar(50),
    HKS_PRICE_TYPE varchar(50)    not null,
    RATE           NUMBER(30, 4),
    REMARK         varchar(1000),
    DATA_SOURCE    varchar(50),
    CREATE_USER    varchar(20)    not null,
    CREATE_DEPT    varchar(32),
    CREATE_TIME    DATE       not null,
    UPDATE_USER    varchar(20)    not null,
    UPDATE_TIME    DATE       not null,
    constraint REFER_SETTLE_RATE_MANAGEMENT primary key (VALUE_DATE, HKS_PRICE_TYPE)    
);
-- Add comments to the table 
comment on table REFER_SETTLE_RATE_MANAGEMENT
  is '参考汇率/结算汇率管理';
-- Add comments to the columns 
comment on column REFER_SETTLE_RATE_MANAGEMENT.VALUE_DATE
  is '适用日期';
comment on column REFER_SETTLE_RATE_MANAGEMENT.CCY
  is '币种';
comment on column REFER_SETTLE_RATE_MANAGEMENT.HKS_PRICE_TYPE
  is '价格类型';
comment on column REFER_SETTLE_RATE_MANAGEMENT.RATE
  is '汇率';
comment on column REFER_SETTLE_RATE_MANAGEMENT.REMARK
  is '备注';
comment on column REFER_SETTLE_RATE_MANAGEMENT.DATA_SOURCE
  is '数据来源';
comment on column REFER_SETTLE_RATE_MANAGEMENT.CREATE_USER
  is '创建人';
comment on column REFER_SETTLE_RATE_MANAGEMENT.CREATE_DEPT
  is '创建部门'; 
comment on column REFER_SETTLE_RATE_MANAGEMENT.CREATE_TIME
  is '创建时间';
comment on column REFER_SETTLE_RATE_MANAGEMENT.UPDATE_USER
  is '更新人';
comment on column REFER_SETTLE_RATE_MANAGEMENT.UPDATE_TIME
  is '更新时间';
 
 
-----

DROP TABLE spring_festival_date;
CREATE TABLE spring_festival_date(
   year_id VARCHAR(20) NOT NULL,
   spring_festival_date DATE NOT NULL ,
   constraint spring_festival_date primary key (year_id)    
);
-- Add comments to the table 
comment on table spring_festival_date
  is '参考汇率/结算汇率管理';
-- Add comments to the columns 
comment on column spring_festival_date.year_id
  is '年份';
comment on column spring_festival_date.spring_festival_date
  is '春节日期';



delete from spring_festival_date;
Insert into spring_festival_date(year_id,spring_festival_date) values ('2020',TO_DATE('2020-01-25', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2021',TO_DATE('2021-02-12', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2022',TO_DATE('2022-02-01', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2023',TO_DATE('2023-01-22', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2024',TO_DATE('2024-02-10', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2025',TO_DATE('2025-01-29', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2026',TO_DATE('2026-02-17', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2027',TO_DATE('2027-02-06', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2028',TO_DATE('2028-01-26', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2029',TO_DATE('2029-02-13', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2030',TO_DATE('2030-02-03', 'yyyy-mm-dd'));

Insert into spring_festival_date(year_id,spring_festival_date) values ('2031',TO_DATE('2031-01-23', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2032',TO_DATE('2032-02-11', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2033',TO_DATE('2033-01-31', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2034',TO_DATE('2034-02-19', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2035',TO_DATE('2035-02-08', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2036',TO_DATE('2036-01-28', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2037',TO_DATE('2037-02-15', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2038',TO_DATE('2038-02-04', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2039',TO_DATE('2039-01-24', 'yyyy-mm-dd'));
Insert into spring_festival_date(year_id,spring_festival_date) values ('2040',TO_DATE('2040-02-12', 'yyyy-mm-dd'));
