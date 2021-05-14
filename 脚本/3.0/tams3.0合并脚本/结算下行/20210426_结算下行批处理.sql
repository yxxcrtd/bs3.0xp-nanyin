alter table TRD_FEE_CONFIG ADD RETENTION_BIT NUMBER(10);
COMMENT ON COLUMN TRD_FEE_CONFIG.RETENTION_BIT
IS '计算过程中保留位数';

alter table TRD_FEE_CONFIG ADD BROKER_BAER_FEE VARCHAR2(200);
COMMENT ON COLUMN TRD_FEE_CONFIG.BROKER_BAER_FEE
IS '费用由券商承担费用';

alter table TRD_FEE_CONFIG ADD COMM_CAL_METHOD VARCHAR2(50);
COMMENT ON COLUMN TRD_FEE_CONFIG.COMM_CAL_METHOD
IS '佣金计算方式';

alter table TRD_FEE_CONFIG ADD GRADE_MODE VARCHAR2(50);
COMMENT ON COLUMN TRD_FEE_CONFIG.GRADE_MODE
IS '分档模式';

alter table TRD_FEE_CONFIG_DETAIL ADD GRADE_CONDITION VARCHAR2(50);
COMMENT ON COLUMN TRD_FEE_CONFIG_DETAIL.GRADE_CONDITION
IS '分档条件';

alter table TRD_FEE_CONFIG_DETAIL ADD GRADE_AMT NUMBER(30,2);
COMMENT ON COLUMN TRD_FEE_CONFIG_DETAIL.GRADE_AMT
IS '分档金额';

alter table TRD_PRODUCT_DEAL ADD SETTLEMEN_TYPE VARCHAR2(50);
COMMENT ON COLUMN TRD_PRODUCT_DEAL.SETTLEMEN_TYPE
IS '交收方式';

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD REG_DATE DATE;
COMMENT ON COLUMN TRD_FIN_PRODUCT_DEAL_TOTAL.REG_DATE
IS '权益登记日';

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD EX_DEVIDENT_DATE DATE;
COMMENT ON COLUMN TRD_FIN_PRODUCT_DEAL_TOTAL.EX_DEVIDENT_DATE
IS '除权除息日';

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD RED_SHARE_LISTING_DATE DATE;
COMMENT ON COLUMN TRD_FIN_PRODUCT_DEAL_TOTAL.RED_SHARE_LISTING_DATE
IS '红股上市日';

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD DEVIDENT_DATE DATE;
COMMENT ON COLUMN TRD_FIN_PRODUCT_DEAL_TOTAL.DEVIDENT_DATE
IS '派息日';

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD PER_DEVIDENT_AMT NUMBER(30,14);
COMMENT ON COLUMN TRD_FIN_PRODUCT_DEAL_TOTAL.PER_DEVIDENT_AMT
IS '每股送转';

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD PER_DEVIDENT_CASH NUMBER(30,14);
COMMENT ON COLUMN TRD_FIN_PRODUCT_DEAL_TOTAL.PER_DEVIDENT_CASH
IS '每股派息';

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD DEVIDENT_CASH NUMBER(30,2);
COMMENT ON COLUMN TRD_FIN_PRODUCT_DEAL_TOTAL.DEVIDENT_CASH
IS '分红金额';

alter table TRD_FIN_PRODUCT_DEAL_TOTAL ADD DEVIDENT_AMT NUMBER(30,4);
COMMENT ON COLUMN TRD_FIN_PRODUCT_DEAL_TOTAL.DEVIDENT_AMT
IS '分红股本';



delete from sys_datadict where dict_code = 'COMM_CAL_METHOD';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMM_CAL_METHOD', 'STL', '佣金计算方式', 'COMM_CAL_METHOD', '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE = 'COMM_CAL_METHOD';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMM_CAL_METHOD', '01', '按证券代码汇总', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMM_CAL_METHOD', '02', '按成交编号计算', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMM_CAL_METHOD', '03', '按申请编号汇总', 3, '0', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict where DICT_CODE = 'GRADE_MODE';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('GRADE_MODE', 'STL', '分档模式', 'GRADE_MODE', '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE = 'GRADE_MODE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('GRADE_MODE', '01', '逐档计算汇总', 1, '0', 'system', null, sysdate, 'system', sysdate);


delete from sys_datadict where DICT_CODE = 'GRADE_CONDITION';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('GRADE_CONDITION', 'STL', '分档条件', 'GRADE_CONDITION', '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE = 'GRADE_CONDITION';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('GRADE_CONDITION', '01', '小于等于', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('GRADE_CONDITION', '02', '大于', 2, '0', 'system', null, sysdate, 'system', sysdate);


-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SZD_JSMX') ;
    if num > 0 then
        execute immediate 'drop table SZD_JSMX';
    end if;
end;
/
create table SZD_JSMX
(
  seqno            VARCHAR2(32) not null,
  import_date      DATE,
  data_file        VARCHAR2(256),
  is_settle_target VARCHAR2(50),
  cjbh             VARCHAR2(16),
  zqzh             VARCHAR2(10),
  scdm             VARCHAR2(2),
  jllx             VARCHAR2(3),
  jyfs             VARCHAR2(3),
  jsfs             VARCHAR2(3),
  ywlx             VARCHAR2(3),
  qsbz             VARCHAR2(3),
  ghlx             VARCHAR2(3),
  jsbh             VARCHAR2(16),
  sqbh             VARCHAR2(16),
  wtbh             VARCHAR2(16),
  jyrq             VARCHAR2(8),
  qsrq             VARCHAR2(8),
  jsrq             VARCHAR2(8),
  qtrq             VARCHAR2(8),
  wtsj             VARCHAR2(6),
  cjsj             VARCHAR2(6),
  xwh1             VARCHAR2(5),
  xwh2             VARCHAR2(5),
  xwhy             VARCHAR2(8),
  jshy             VARCHAR2(8),
  tghy             VARCHAR2(8),
  zqdm1            VARCHAR2(6),
  zqdm2            VARCHAR2(6),
  zqlb             VARCHAR2(2),
  ltlx             VARCHAR2(1),
  qylb             VARCHAR2(2),
  gpnf             VARCHAR2(4),
  mmbz             VARCHAR2(1),
  sl               VARCHAR2(16),
  cjsl             VARCHAR2(16),
  zjzh             VARCHAR2(25),
  bz               VARCHAR2(3),
  jg1              VARCHAR2(17),
  jg2              VARCHAR2(17),
  qsje             VARCHAR2(19),
  yhs              VARCHAR2(17),
  jsf              VARCHAR2(17),
  ghf              VARCHAR2(17),
  zgf              VARCHAR2(17),
  sxf              VARCHAR2(17),
  qtje1            VARCHAR2(19),
  qtje2            VARCHAR2(19),
  qtje3            VARCHAR2(19),
  sjsf             VARCHAR2(19),
  jgdm             VARCHAR2(4),
  fjsm             VARCHAR2(120),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SZD_JSMX
  is '上中登JSMX';
-- Add comments to the columns 
comment on column SZD_JSMX.seqno
  is '流水号';
comment on column SZD_JSMX.import_date
  is '导入日期';
comment on column SZD_JSMX.data_file
  is '数据文件';
comment on column SZD_JSMX.is_settle_target
  is '是否本次数据清算对象';
comment on column SZD_JSMX.cjbh
  is '成交编号';
comment on column SZD_JSMX.zqzh
  is '每个产品在交易所有唯一的身份ID即股东代码，也称证券账户。需转换成系统的组合代码代码';
comment on column SZD_JSMX.scdm
  is '市场代码';
comment on column SZD_JSMX.jllx
  is '记录类型';
comment on column SZD_JSMX.jyfs
  is '交易方式';
comment on column SZD_JSMX.jsfs
  is '交收方式';
comment on column SZD_JSMX.ywlx
  is '业务类型';
comment on column SZD_JSMX.qsbz
  is '清算标志';
comment on column SZD_JSMX.ghlx
  is '过户类型';
comment on column SZD_JSMX.jsbh
  is '交收编号';
comment on column SZD_JSMX.sqbh
  is '申请编号';
comment on column SZD_JSMX.wtbh
  is '委托编号';
comment on column SZD_JSMX.jyrq
  is '交易日期';
comment on column SZD_JSMX.qsrq
  is '清算日期';
comment on column SZD_JSMX.jsrq
  is '交收日期';
comment on column SZD_JSMX.qtrq
  is '其它日期';
comment on column SZD_JSMX.wtsj
  is '委托时间';
comment on column SZD_JSMX.cjsj
  is '成交时间';
comment on column SZD_JSMX.xwh1
  is '交易单元-席位号';
comment on column SZD_JSMX.xwh2
  is '结算单元';
comment on column SZD_JSMX.xwhy
  is '交易单元所属结算参与人的清算编号';
comment on column SZD_JSMX.jshy
  is '结算单元所属结算参与人的清算编号';
comment on column SZD_JSMX.tghy
  is 'QFII托管银行的清算编号';
comment on column SZD_JSMX.zqdm1
  is '证券代码1';
comment on column SZD_JSMX.zqdm2
  is '证券代码2';
comment on column SZD_JSMX.zqlb
  is '证券类别';
comment on column SZD_JSMX.ltlx
  is '流通类型';
comment on column SZD_JSMX.qylb
  is '权益类别';
comment on column SZD_JSMX.gpnf
  is '挂牌年份';
comment on column SZD_JSMX.mmbz
  is '买卖标志';
comment on column SZD_JSMX.sl
  is '交收数量';
comment on column SZD_JSMX.cjsl
  is '成交数量';
comment on column SZD_JSMX.zjzh
  is '资金账号';
comment on column SZD_JSMX.bz
  is '币种';
comment on column SZD_JSMX.jg1
  is '价格1';
comment on column SZD_JSMX.jg2
  is '价格2';
comment on column SZD_JSMX.qsje
  is '清算金额';
comment on column SZD_JSMX.yhs
  is '印花税';
comment on column SZD_JSMX.jsf
  is '经手费';
comment on column SZD_JSMX.ghf
  is '过户费';
comment on column SZD_JSMX.zgf
  is '证管费';
comment on column SZD_JSMX.sxf
  is '手续费';
comment on column SZD_JSMX.qtje1
  is '其它金额1';
comment on column SZD_JSMX.qtje2
  is '其它金额2';
comment on column SZD_JSMX.qtje3
  is '其它金额3';
comment on column SZD_JSMX.sjsf
  is '实际收付';
comment on column SZD_JSMX.jgdm
  is '结果代码';
comment on column SZD_JSMX.fjsm
  is '附加说明';
comment on column SZD_JSMX.create_user
  is '创建人';
comment on column SZD_JSMX.create_dept
  is '创建部门';
comment on column SZD_JSMX.create_time
  is '创建时间';
comment on column SZD_JSMX.update_user
  is '更新人';
comment on column SZD_JSMX.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SZD_JSMX
  add constraint PK_SZD_JSMX primary key (SEQNO)
  using index 
  ;
  
  
-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SZD_SJSJG') ;
    if num > 0 then
        execute immediate 'drop table SZD_SJSJG';
    end if;
end;
/
create table SZD_SJSJG
(
  seqno            VARCHAR2(32) not null,
  import_date      DATE,
  data_file        VARCHAR2(256),
  is_settle_target VARCHAR2(50),
  jgjszh           VARCHAR2(6),
  jgbfzh           VARCHAR2(25),
  jgsjlx           VARCHAR2(2),
  jgywlb           VARCHAR2(4),
  jgzqdm           VARCHAR2(8),
  jgjydy           VARCHAR2(6),
  jgtgdy           VARCHAR2(6),
  jgzqzh           VARCHAR2(20),
  jgddbh           VARCHAR2(24),
  jgyyb            VARCHAR2(4),
  jgzxbh           VARCHAR2(16),
  jgywlsh          VARCHAR2(16),
  jgcjsl           NUMBER(15,2),
  jgqssl           NUMBER(15,2),
  jgjssl           NUMBER(15,2),
  jgcjjg           NUMBER(13,4),
  jgqsjg           NUMBER(18,9),
  jgxyjy           VARCHAR2(1),
  jgpcbs           VARCHAR2(1),
  jgzqlb           VARCHAR2(2),
  jgzqzl           VARCHAR2(2),
  jggfxz           VARCHAR2(2),
  jgltlx           VARCHAR2(1),
  jgjsfs           VARCHAR2(1),
  jghbdh           VARCHAR2(3),
  jgqsbj           NUMBER(17,2),
  jgyhs            NUMBER(12,2),
  jgjyjsf          NUMBER(12,2),
  jgjggf           NUMBER(12,2),
  jgghf            NUMBER(12,2),
  jgjsf            NUMBER(12,2),
  jgsxf            NUMBER(12,2),
  jgqsyj           NUMBER(12,2),
  jgqtfy           NUMBER(12,2),
  jgzjje           NUMBER(17,2),
  jgsfje           NUMBER(18,2),
  jgjsbz           VARCHAR2(1),
  jgzydh           VARCHAR2(4),
  jgcjrq           VARCHAR2(8),
  jgqsrq           VARCHAR2(8),
  jgjsrq           VARCHAR2(8),
  jgfsrq           VARCHAR2(8),
  jgqtrq           VARCHAR2(8),
  jgscdm           VARCHAR2(2),
  jgjyfs           VARCHAR2(2),
  jgzqdm2          VARCHAR2(8),
  jgtgdy2          VARCHAR2(6),
  jgddbh2          VARCHAR2(16),
  jgfjsm           VARCHAR2(300),
  jgbybz           VARCHAR2(1),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SZD_SJSJG
  is '深中登SJSJG';
-- Add comments to the columns 
comment on column SZD_SJSJG.seqno
  is '流水号';
comment on column SZD_SJSJG.import_date
  is '导入日期';
comment on column SZD_SJSJG.data_file
  is '数据文件';
comment on column SZD_SJSJG.is_settle_target
  is '是否本次数据清算对象';
comment on column SZD_SJSJG.jgjszh
  is '结算账号';
comment on column SZD_SJSJG.jgbfzh
  is '备付金账户';
comment on column SZD_SJSJG.jgsjlx
  is '数据类型';
comment on column SZD_SJSJG.jgywlb
  is '业务类别';
comment on column SZD_SJSJG.jgzqdm
  is '证券代码';
comment on column SZD_SJSJG.jgjydy
  is '交易单元';
comment on column SZD_SJSJG.jgtgdy
  is '托管单元';
comment on column SZD_SJSJG.jgzqzh
  is '证券账户号码';
comment on column SZD_SJSJG.jgddbh
  is '客户订单编号';
comment on column SZD_SJSJG.jgyyb
  is '营业部代码';
comment on column SZD_SJSJG.jgzxbh
  is '执行编号';
comment on column SZD_SJSJG.jgywlsh
  is '业务流水号';
comment on column SZD_SJSJG.jgcjsl
  is '成交数量';
comment on column SZD_SJSJG.jgqssl
  is '清算数量';
comment on column SZD_SJSJG.jgjssl
  is '交收数量';
comment on column SZD_SJSJG.jgcjjg
  is '成交价格';
comment on column SZD_SJSJG.jgqsjg
  is '清算价格';
comment on column SZD_SJSJG.jgxyjy
  is '信用交易标识';
comment on column SZD_SJSJG.jgpcbs
  is '平仓标识';
comment on column SZD_SJSJG.jgzqlb
  is '证券类别';
comment on column SZD_SJSJG.jgzqzl
  is '证券子类';
comment on column SZD_SJSJG.jggfxz
  is '股份性质';
comment on column SZD_SJSJG.jgltlx
  is '流通类型';
comment on column SZD_SJSJG.jgjsfs
  is '交收方式';
comment on column SZD_SJSJG.jghbdh
  is '货币代号';
comment on column SZD_SJSJG.jgqsbj
  is '清算本金';
comment on column SZD_SJSJG.jgyhs
  is '印花税';
comment on column SZD_SJSJG.jgjyjsf
  is '交易经手费';
comment on column SZD_SJSJG.jgjggf
  is '监管规费';
comment on column SZD_SJSJG.jgghf
  is '过户费';
comment on column SZD_SJSJG.jgjsf
  is '结算费';
comment on column SZD_SJSJG.jgsxf
  is '手续费';
comment on column SZD_SJSJG.jgqsyj
  is '结算参与人佣金';
comment on column SZD_SJSJG.jgqtfy
  is '其他费用';
comment on column SZD_SJSJG.jgzjje
  is '资金金额';
comment on column SZD_SJSJG.jgsfje
  is '收付净额';
comment on column SZD_SJSJG.jgjsbz
  is '交收标志';
comment on column SZD_SJSJG.jgzydh
  is '摘要代号';
comment on column SZD_SJSJG.jgcjrq
  is '成交日期';
comment on column SZD_SJSJG.jgqsrq
  is '清算日期';
comment on column SZD_SJSJG.jgjsrq
  is '交收日期';
comment on column SZD_SJSJG.jgfsrq
  is '发送日期';
comment on column SZD_SJSJG.jgqtrq
  is '其它日期';
comment on column SZD_SJSJG.jgscdm
  is '市场代码';
comment on column SZD_SJSJG.jgjyfs
  is '交易方式';
comment on column SZD_SJSJG.jgzqdm2
  is '证券代码2';
comment on column SZD_SJSJG.jgtgdy2
  is '托管单元2';
comment on column SZD_SJSJG.jgddbh2
  is '订单编号2';
comment on column SZD_SJSJG.jgfjsm
  is '附加说明';
comment on column SZD_SJSJG.jgbybz
  is '备用标志';
comment on column SZD_SJSJG.create_user
  is '创建人';
comment on column SZD_SJSJG.create_dept
  is '创建部门';
comment on column SZD_SJSJG.create_time
  is '创建时间';
comment on column SZD_SJSJG.update_user
  is '更新人';
comment on column SZD_SJSJG.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SZD_SJSJG
  add constraint PK_SZD_SJSJG primary key (SEQNO)
  using index 
  ;


-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SZD_SJSMX') ;
    if num > 0 then
        execute immediate 'drop table SZD_SJSMX';
    end if;
end;
/
create table SZD_SJSMX
(
  seqno            VARCHAR2(32) not null,
  import_date      DATE,
  data_file        VARCHAR2(256),
  is_settle_target VARCHAR2(50),
  mxywlsh          VARCHAR2(16),
  mxjszh           VARCHAR2(6),
  mxbfzh           VARCHAR2(25),
  mxsjlx           VARCHAR2(2),
  mxywlb           VARCHAR2(4),
  mxzqdm           VARCHAR2(8),
  mxjydy           VARCHAR2(6),
  mxtgdy           VARCHAR2(6),
  mxzqzh           VARCHAR2(20),
  mxddbh           VARCHAR2(24),
  mxyyb            VARCHAR2(4),
  mxzxbh           VARCHAR2(16),
  mxcjsl           NUMBER(15,2),
  mxqssl           NUMBER(15,2),
  mxcjjg           NUMBER(13,4),
  mxqsjg           NUMBER(18,9),
  mxxyjy           VARCHAR2(1),
  mxpcbs           VARCHAR2(1),
  mxzqlb           VARCHAR2(2),
  mxzqzl           VARCHAR2(2),
  mxgfxz           VARCHAR2(2),
  mxjsfs           VARCHAR2(1),
  mxhbdh           VARCHAR2(3),
  mxqsbj           NUMBER(17,2),
  mxyhs            NUMBER(12,2),
  mxjyjsf          NUMBER(12,2),
  mxjggf           NUMBER(12,2),
  mxghf            NUMBER(12,2),
  mxjsf            NUMBER(12,2),
  mxsxf            NUMBER(12,2),
  mxqsyj           NUMBER(12,2),
  mxqtfy           NUMBER(12,2),
  mxzjje           NUMBER(17,2),
  mxsfje           NUMBER(18,2),
  mxcjrq           VARCHAR2(8),
  mxqsrq           VARCHAR2(8),
  mxjsrq           VARCHAR2(8),
  mxfsrq           VARCHAR2(8),
  mxqtrq           VARCHAR2(8),
  mxscdm           VARCHAR2(2),
  mxjyfs           VARCHAR2(2),
  mxzqdm2          VARCHAR2(8),
  mxtgdy2          VARCHAR2(6),
  mxddbh2          VARCHAR2(16),
  mxcwdh           VARCHAR2(4),
  mxpphm           VARCHAR2(10),
  mxfjsm           VARCHAR2(120),
  mxbybz           VARCHAR2(1),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SZD_SJSMX
  is '深中登SJSMX';
-- Add comments to the columns 
comment on column SZD_SJSMX.seqno
  is '流水号';
comment on column SZD_SJSMX.import_date
  is '导入日期';
comment on column SZD_SJSMX.data_file
  is '数据文件';
comment on column SZD_SJSMX.is_settle_target
  is '是否本次数据清算对象';
comment on column SZD_SJSMX.mxywlsh
  is '业务流水号';
comment on column SZD_SJSMX.mxjszh
  is '结算账号';
comment on column SZD_SJSMX.mxbfzh
  is '备付金账户';
comment on column SZD_SJSMX.mxsjlx
  is '数据类型';
comment on column SZD_SJSMX.mxywlb
  is '业务类别';
comment on column SZD_SJSMX.mxzqdm
  is '证券代码';
comment on column SZD_SJSMX.mxjydy
  is '交易单元';
comment on column SZD_SJSMX.mxtgdy
  is '托管单元';
comment on column SZD_SJSMX.mxzqzh
  is '证券账户号码';
comment on column SZD_SJSMX.mxddbh
  is '客户订单编号';
comment on column SZD_SJSMX.mxyyb
  is '营业部代码';
comment on column SZD_SJSMX.mxzxbh
  is '执行编号';
comment on column SZD_SJSMX.mxcjsl
  is '成交数量';
comment on column SZD_SJSMX.mxqssl
  is '清算数量';
comment on column SZD_SJSMX.mxcjjg
  is '成交价格';
comment on column SZD_SJSMX.mxqsjg
  is '清算价格';
comment on column SZD_SJSMX.mxxyjy
  is '信用交易标识';
comment on column SZD_SJSMX.mxpcbs
  is '平仓标识';
comment on column SZD_SJSMX.mxzqlb
  is '证券类别';
comment on column SZD_SJSMX.mxzqzl
  is '证券子类别';
comment on column SZD_SJSMX.mxgfxz
  is '股份性质';
comment on column SZD_SJSMX.mxjsfs
  is '交收方式';
comment on column SZD_SJSMX.mxhbdh
  is '货币代号';
comment on column SZD_SJSMX.mxqsbj
  is '清算本金';
comment on column SZD_SJSMX.mxyhs
  is '印花税';
comment on column SZD_SJSMX.mxjyjsf
  is '交易经手费';
comment on column SZD_SJSMX.mxjggf
  is '监管规费';
comment on column SZD_SJSMX.mxghf
  is '过户费';
comment on column SZD_SJSMX.mxjsf
  is '结算费';
comment on column SZD_SJSMX.mxsxf
  is '手续费';
comment on column SZD_SJSMX.mxqsyj
  is '结算参与人佣金';
comment on column SZD_SJSMX.mxqtfy
  is '其他费用';
comment on column SZD_SJSMX.mxzjje
  is '资金金额';
comment on column SZD_SJSMX.mxsfje
  is '收付净额';
comment on column SZD_SJSMX.mxcjrq
  is '成交日期';
comment on column SZD_SJSMX.mxqsrq
  is '清算日期';
comment on column SZD_SJSMX.mxjsrq
  is '交收日期';
comment on column SZD_SJSMX.mxfsrq
  is '发送日期';
comment on column SZD_SJSMX.mxqtrq
  is '其它日期';
comment on column SZD_SJSMX.mxscdm
  is '市场代码';
comment on column SZD_SJSMX.mxjyfs
  is '交易方式';
comment on column SZD_SJSMX.mxzqdm2
  is '证券代码2';
comment on column SZD_SJSMX.mxtgdy2
  is '托管单元2';
comment on column SZD_SJSMX.mxddbh2
  is '订单编号2';
comment on column SZD_SJSMX.mxcwdh
  is '错误代号';
comment on column SZD_SJSMX.mxpphm
  is '匹配号码';
comment on column SZD_SJSMX.mxfjsm
  is '附加说明';
comment on column SZD_SJSMX.mxbybz
  is '备用标志';
comment on column SZD_SJSMX.create_user
  is '创建人';
comment on column SZD_SJSMX.create_dept
  is '创建部门';
comment on column SZD_SJSMX.create_time
  is '创建时间';
comment on column SZD_SJSMX.update_user
  is '更新人';
comment on column SZD_SJSMX.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SZD_SJSMX
  add constraint PK_SZD_SJSMX primary key (SEQNO)
  using index 
  ;


-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SZD_ABCSJ') ;
    if num > 0 then
        execute immediate 'drop table SZD_ABCSJ';
    end if;
end;
/
create table SZD_ABCSJ
(
  seqno            VARCHAR2(32) not null,
  import_date      DATE,
  data_file        VARCHAR2(256),
  is_settle_target VARCHAR2(50),
  xh               VARCHAR2(16),
  scdm             VARCHAR2(2),
  tzlb             VARCHAR2(3),
  tzrq             VARCHAR2(8),
  qsbh             VARCHAR2(5),
  zqzh             VARCHAR2(10),
  zqdm             VARCHAR2(6),
  zqlb             VARCHAR2(2),
  ltlx             VARCHAR2(1),
  qylb             VARCHAR2(2),
  gpnf             VARCHAR2(4),
  zh1              VARCHAR2(25),
  zh2              VARCHAR2(25),
  rq1              VARCHAR2(8),
  rq2              VARCHAR2(8),
  rq3              VARCHAR2(8),
  je1              VARCHAR2(19),
  je2              VARCHAR2(19),
  je3              VARCHAR2(19),
  jg1              VARCHAR2(17),
  jg2              VARCHAR2(17),
  bl1              VARCHAR2(17),
  bl2              VARCHAR2(17),
  sl1              VARCHAR2(16),
  sl2              VARCHAR2(16),
  fzdm             VARCHAR2(10),
  bz               VARCHAR2(240),
  by1              VARCHAR2(40),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SZD_ABCSJ
  is '上中登股息红利税补缴';
-- Add comments to the columns 
comment on column SZD_ABCSJ.seqno
  is '流水号';
comment on column SZD_ABCSJ.import_date
  is '导入日期';
comment on column SZD_ABCSJ.data_file
  is '数据文件';
comment on column SZD_ABCSJ.is_settle_target
  is '是否本次数据清算对象';
comment on column SZD_ABCSJ.xh
  is '序号';
comment on column SZD_ABCSJ.scdm
  is '市场代码';
comment on column SZD_ABCSJ.tzlb
  is '通知类别';
comment on column SZD_ABCSJ.tzrq
  is '通知日期';
comment on column SZD_ABCSJ.qsbh
  is '托管会员的清算编号，对于QFII账户，填写托管银行的清算编号';
comment on column SZD_ABCSJ.zqzh
  is '证券账号';
comment on column SZD_ABCSJ.zqdm
  is '证券代码';
comment on column SZD_ABCSJ.zqlb
  is '证券类别';
comment on column SZD_ABCSJ.ltlx
  is '流通类型';
comment on column SZD_ABCSJ.qylb
  is '权益类别';
comment on column SZD_ABCSJ.gpnf
  is '挂牌年份';
comment on column SZD_ABCSJ.zh1
  is '账户1';
comment on column SZD_ABCSJ.zh2
  is '账户2';
comment on column SZD_ABCSJ.rq1
  is '日期1';
comment on column SZD_ABCSJ.rq2
  is '日期2';
comment on column SZD_ABCSJ.rq3
  is '日期3';
comment on column SZD_ABCSJ.je1
  is '金额1';
comment on column SZD_ABCSJ.je2
  is '金额2';
comment on column SZD_ABCSJ.je3
  is '金额3';
comment on column SZD_ABCSJ.jg1
  is '价格1';
comment on column SZD_ABCSJ.jg2
  is '价格2';
comment on column SZD_ABCSJ.bl1
  is '比率1';
comment on column SZD_ABCSJ.bl2
  is '比率2';
comment on column SZD_ABCSJ.sl1
  is '数量1';
comment on column SZD_ABCSJ.sl2
  is '数量2';
comment on column SZD_ABCSJ.fzdm
  is '辅助代码';
comment on column SZD_ABCSJ.bz
  is '备注';
comment on column SZD_ABCSJ.by1
  is '备用';
comment on column SZD_ABCSJ.create_user
  is '创建人';
comment on column SZD_ABCSJ.create_dept
  is '创建部门';
comment on column SZD_ABCSJ.create_time
  is '创建时间';
comment on column SZD_ABCSJ.update_user
  is '更新人';
comment on column SZD_ABCSJ.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SZD_ABCSJ
  add constraint PK_SZD_ABCSJ primary key (SEQNO)
  using index 
  ;


-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SZD_SJSFX') ;
    if num > 0 then
        execute immediate 'drop table SZD_SJSFX';
    end if;
end;
/
create table SZD_SJSFX
(
  seqno            VARCHAR2(32) not null,
  import_date      DATE,
  data_file        VARCHAR2(256),
  is_settle_target VARCHAR2(50),
  fxjszh           VARCHAR2(6),
  fxtgdy           VARCHAR2(6),
  fxzqdm           VARCHAR2(8),
  fxzqzh           VARCHAR2(20),
  fxywlb           VARCHAR2(4),
  fxddbh           VARCHAR2(10),
  fxwtgs           NUMBER(15,2),
  fxqrgs           NUMBER(15,2),
  fxzjje           NUMBER(17,3),
  fxcwdh           VARCHAR2(4),
  fxfsrq           VARCHAR2(8),
  fxbybz           VARCHAR2(1),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SZD_SJSFX
  is '深中登发行信息库';
-- Add comments to the columns 
comment on column SZD_SJSFX.seqno
  is '流水号';
comment on column SZD_SJSFX.import_date
  is '导入日期';
comment on column SZD_SJSFX.data_file
  is '数据文件';
comment on column SZD_SJSFX.is_settle_target
  is '是否本次数据清算对象';
comment on column SZD_SJSFX.fxjszh
  is '结算账号';
comment on column SZD_SJSFX.fxtgdy
  is '托管单元';
comment on column SZD_SJSFX.fxzqdm
  is '证券代码';
comment on column SZD_SJSFX.fxzqzh
  is '证券账户';
comment on column SZD_SJSFX.fxywlb
  is '业务类别';
comment on column SZD_SJSFX.fxddbh
  is '客户订单编号';
comment on column SZD_SJSFX.fxwtgs
  is '委托股数';
comment on column SZD_SJSFX.fxqrgs
  is '确认股数';
comment on column SZD_SJSFX.fxzjje
  is '资金金额';
comment on column SZD_SJSFX.fxcwdh
  is '错误代号';
comment on column SZD_SJSFX.fxfsrq
  is '发送日期';
comment on column SZD_SJSFX.fxbybz
  is '备用标志';
comment on column SZD_SJSFX.create_user
  is '创建人';
comment on column SZD_SJSFX.create_dept
  is '创建部门';
comment on column SZD_SJSFX.create_time
  is '创建时间';
comment on column SZD_SJSFX.update_user
  is '更新人';
comment on column SZD_SJSFX.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SZD_SJSFX
  add constraint PK_SZD_SJSFX primary key (SEQNO)
  using index 
  ;



-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SZD_SJSKS') ;
    if num > 0 then
        execute immediate 'drop table SZD_SJSKS';
    end if;
end;
/
create table SZD_SJSKS
(
  seqno            VARCHAR2(32) not null,
  import_date      DATE,
  data_file        VARCHAR2(256),
  is_settle_target VARCHAR2(50),
  ksxwdm           VARCHAR2(6),
  ksgddm           VARCHAR2(10),
  ksywlb           VARCHAR2(2),
  ksksge           NUMBER(12),
  ksfsrq           VARCHAR2(8),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SZD_SJSKS
  is '深中登SJSKS';
-- Add comments to the columns 
comment on column SZD_SJSKS.seqno
  is '流水号';
comment on column SZD_SJSKS.import_date
  is '导入日期';
comment on column SZD_SJSKS.data_file
  is '数据文件';
comment on column SZD_SJSKS.is_settle_target
  is '是否本次数据清算对象';
comment on column SZD_SJSKS.ksxwdm
  is '托管编码';
comment on column SZD_SJSKS.ksgddm
  is '证券账户';
comment on column SZD_SJSKS.ksywlb
  is '业务类别，‘A9’：新股发行投资者可申购额度数据';
comment on column SZD_SJSKS.ksksge
  is '可申购额度，按市值计算的可申购额度，单位股，>0';
comment on column SZD_SJSKS.ksfsrq
  is '发送日期，格式YYYYMMDD';
comment on column SZD_SJSKS.create_user
  is '创建人';
comment on column SZD_SJSKS.create_dept
  is '创建部门';
comment on column SZD_SJSKS.create_time
  is '创建时间';
comment on column SZD_SJSKS.update_user
  is '更新人';
comment on column SZD_SJSKS.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SZD_SJSKS
  add constraint PK_SZD_SJSKS primary key (SEQNO)
  using index 
  ;


-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SZD_ZQBD') ;
    if num > 0 then
        execute immediate 'drop table SZD_ZQBD';
    end if;
end;
/
create table SZD_ZQBD
(
  seqno            VARCHAR2(32) not null,
  import_date      DATE,
  data_file        VARCHAR2(256),
  is_settle_target VARCHAR2(50),
  scdm             VARCHAR2(2),
  qsbh             VARCHAR2(8),
  zqzh             VARCHAR2(10),
  xwh              VARCHAR2(5),
  zqdm             VARCHAR2(6),
  zqlb             VARCHAR2(2),
  ltlx             VARCHAR2(1),
  qylb             VARCHAR2(2),
  gpnf             VARCHAR2(4),
  bdsl             VARCHAR2(16),
  bdlx             VARCHAR2(3),
  bdrq             VARCHAR2(8),
  sl               VARCHAR2(16),
  bh               VARCHAR2(20),
  by1              VARCHAR2(20),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SZD_ZQBD
  is '上中登ZQBD';
-- Add comments to the columns 
comment on column SZD_ZQBD.seqno
  is '流水号';
comment on column SZD_ZQBD.import_date
  is '导入日期';
comment on column SZD_ZQBD.data_file
  is '数据文件';
comment on column SZD_ZQBD.is_settle_target
  is '是否本次数据清算对象';
comment on column SZD_ZQBD.scdm
  is '市场代码';
comment on column SZD_ZQBD.qsbh
  is '托管会员的清算编号，对于QFII账户，填写托管银行的清算编号';
comment on column SZD_ZQBD.zqzh
  is '每个产品在交易所有唯一的身份ID即股东代码，也称证券账户。需转换成系统的组合代码代码';
comment on column SZD_ZQBD.xwh
  is '交易单元';
comment on column SZD_ZQBD.zqdm
  is '证券代码';
comment on column SZD_ZQBD.zqlb
  is '证券类别';
comment on column SZD_ZQBD.ltlx
  is '流通类型';
comment on column SZD_ZQBD.qylb
  is '权益类别';
comment on column SZD_ZQBD.gpnf
  is '挂牌年份';
comment on column SZD_ZQBD.bdsl
  is '变动数量';
comment on column SZD_ZQBD.bdlx
  is '变动类型';
comment on column SZD_ZQBD.bdrq
  is '变动日期';
comment on column SZD_ZQBD.sl
  is '数量(保留字段)';
comment on column SZD_ZQBD.bh
  is '编号(保留字段)';
comment on column SZD_ZQBD.by1
  is '备用';
comment on column SZD_ZQBD.create_user
  is '创建人';
comment on column SZD_ZQBD.create_dept
  is '创建部门';
comment on column SZD_ZQBD.create_time
  is '创建时间';
comment on column SZD_ZQBD.update_user
  is '更新人';
comment on column SZD_ZQBD.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SZD_ZQBD
  add constraint PK_SZD_ZQBD primary key (SEQNO)
  using index 
  ;



-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SZD_ZQY') ;
    if num > 0 then
        execute immediate 'drop table SZD_ZQY';
    end if;
end;
/
-- Create table
create table SZD_ZQY
(
  seqno            VARCHAR2(32) not null,
  import_date      DATE,
  data_file        VARCHAR2(256),
  is_settle_target VARCHAR2(50),
  gddm             VARCHAR2(10),
  zsz              NUMBER(18),
  zbqy             NUMBER(18),
  kcqy             NUMBER(18),
  gsdm             VARCHAR2(5),
  zhxh             VARCHAR2(10),
  djrq             VARCHAR2(8),
  clbj             VARCHAR2(1),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SZD_ZQY
  is '上中登ZQY';
-- Add comments to the columns 
comment on column SZD_ZQY.seqno
  is '流水号';
comment on column SZD_ZQY.import_date
  is '导入日期';
comment on column SZD_ZQY.data_file
  is '数据文件';
comment on column SZD_ZQY.is_settle_target
  is '是否本次数据清算对象';
comment on column SZD_ZQY.gddm
  is '每个产品在交易所有唯一的身份ID即股东代码，也称证券账户。需转换成系统的组合代码代码';
comment on column SZD_ZQY.zsz
  is '总市值，登记日该投资者的股票总市值，单位为分';
comment on column SZD_ZQY.zbqy
  is '主板权益，登记日该投资者主板申购额度，单位为股';
comment on column SZD_ZQY.kcqy
  is '科创板权益，登记日该投资者科创板申购额度，单位';
comment on column SZD_ZQY.gsdm
  is '指定席位号（PBU）';
comment on column SZD_ZQY.zhxh
  is '账户组序号';
comment on column SZD_ZQY.djrq
  is '权益登记日期，格式YYYYMMDD';
comment on column SZD_ZQY.clbj
  is '处理标志，柜台系统自由使用';
comment on column SZD_ZQY.create_user
  is '创建人';
comment on column SZD_ZQY.create_dept
  is '创建部门';
comment on column SZD_ZQY.create_time
  is '创建时间';
comment on column SZD_ZQY.update_user
  is '更新人';
comment on column SZD_ZQY.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SZD_ZQY
  add constraint PK_SZD_ZQY primary key (SEQNO)
  using index 
  ;



-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SZD_ZSMX') ;
    if num > 0 then
        execute immediate 'drop table SZD_ZSMX';
    end if;
end;
/
create table SZD_ZSMX
(
  seqno            VARCHAR2(32) not null,
  import_date      DATE,
  data_file        VARCHAR2(256),
  is_settle_target VARCHAR2(50),
  mxjsls           VARCHAR2(10),
  mxjszh           VARCHAR2(6),
  mxbfzh           VARCHAR2(10),
  mxywlb           VARCHAR2(2),
  mxzqdh           VARCHAR2(6),
  mxxwdh           VARCHAR2(6),
  mxgddm           VARCHAR2(10),
  mxjcgs           NUMBER(12),
  mxjcrq           DATE,
  mxjsrq           DATE,
  mxfsje           NUMBER(16,2),
  mxhbdh           VARCHAR2(3),
  mxfsrq           DATE,
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SZD_ZSMX
  is '深中登征税明细信息库';
-- Add comments to the columns 
comment on column SZD_ZSMX.seqno
  is '流水号';
comment on column SZD_ZSMX.import_date
  is '导入日期';
comment on column SZD_ZSMX.data_file
  is '数据文件';
comment on column SZD_ZSMX.is_settle_target
  is '是否本次数据清算对象';
comment on column SZD_ZSMX.mxjsls
  is '计税流水号';
comment on column SZD_ZSMX.mxjszh
  is '结算账户';
comment on column SZD_ZSMX.mxbfzh
  is '结算备付金账号';
comment on column SZD_ZSMX.mxywlb
  is '业务类别';
comment on column SZD_ZSMX.mxzqdh
  is '证券代号';
comment on column SZD_ZSMX.mxxwdh
  is '托管单元';
comment on column SZD_ZSMX.mxgddm
  is '证券账户';
comment on column SZD_ZSMX.mxjcgs
  is '净减持股数';
comment on column SZD_ZSMX.mxjcrq
  is '减持股份交割日期';
comment on column SZD_ZSMX.mxjsrq
  is '计税日期';
comment on column SZD_ZSMX.mxfsje
  is '应缴税金金额';
comment on column SZD_ZSMX.mxhbdh
  is '货币代号';
comment on column SZD_ZSMX.mxfsrq
  is '发送日期';
comment on column SZD_ZSMX.create_user
  is '创建人';
comment on column SZD_ZSMX.create_dept
  is '创建部门';
comment on column SZD_ZSMX.create_time
  is '创建时间';
comment on column SZD_ZSMX.update_user
  is '更新人';
comment on column SZD_ZSMX.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SZD_ZSMX
  add constraint PK_SZD_ZSMX primary key (SEQNO)
  using index 
  ;



-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SETTLE_DATA_RULE') ;
    if num > 0 then
        execute immediate 'drop table SETTLE_DATA_RULE';
    end if;
end;
/
create table SETTLE_DATA_RULE
(
  seqno               VARCHAR2(32) not null,
  table_name          VARCHAR2(50),
  data_file           VARCHAR2(256),
  finprod_type        VARCHAR2(50),
  interf_field_cnname VARCHAR2(300),
  table_field         VARCHAR2(50),
  field_code          VARCHAR2(50),
  field_value         VARCHAR2(300),
  remark              VARCHAR2(1000),
  create_user         VARCHAR2(20) not null,
  create_dept         VARCHAR2(32),
  create_time         TIMESTAMP(6) not null,
  update_user         VARCHAR2(20) not null,
  update_time         TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SETTLE_DATA_RULE
  is '清算数据规则表';
-- Add comments to the columns 
comment on column SETTLE_DATA_RULE.seqno
  is '流水号';
comment on column SETTLE_DATA_RULE.table_name
  is '清算数据表';
comment on column SETTLE_DATA_RULE.data_file
  is '数据源文件';
comment on column SETTLE_DATA_RULE.finprod_type
  is '证券品种';
comment on column SETTLE_DATA_RULE.interf_field_cnname
  is '接口字段中文名';
comment on column SETTLE_DATA_RULE.table_field
  is '表字段名';
comment on column SETTLE_DATA_RULE.field_code
  is '数据项CODE';
comment on column SETTLE_DATA_RULE.field_value
  is '数据项值';
comment on column SETTLE_DATA_RULE.remark
  is '备注';
comment on column SETTLE_DATA_RULE.create_user
  is '创建人';
comment on column SETTLE_DATA_RULE.create_dept
  is '创建部门';
comment on column SETTLE_DATA_RULE.create_time
  is '创建时间';
comment on column SETTLE_DATA_RULE.update_user
  is '更新人';
comment on column SETTLE_DATA_RULE.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SETTLE_DATA_RULE
  add constraint PK_SETTLE_DATA_RULE primary key (SEQNO)
  using index 
  ;


-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SETTLE_TRANS_DATA') ;
    if num > 0 then
        execute immediate 'drop table SETTLE_TRANS_DATA';
    end if;
end;
/
create table SETTLE_TRANS_DATA
(
  trade_id           VARCHAR2(32) not null,
  deal_date          DATE,
  sec_type           VARCHAR2(50),
  apply_trade_id     VARCHAR2(32),
  finprod_id         VARCHAR2(32),
  finprod_type       VARCHAR2(50),
  finprod_type2      VARCHAR2(50),
  branch             NUMBER(10) not null,
  busi_type          VARCHAR2(50),
  trade_date         DATE,
  settle_date        DATE,
  vdate              DATE,
  mdate              DATE,
  vpay_date          DATE,
  mpay_date          DATE,
  term_type          VARCHAR2(50),
  settle_speed       NUMBER(10),
  ccy                VARCHAR2(50),
  unit_cprice        NUMBER(30,14),
  unit_int           NUMBER(30,14),
  unit_fprice        NUMBER(30,14),
  par_value          NUMBER(30,14),
  cprice_amt         NUMBER(30,2),
  share_amt          NUMBER(30,4),
  prin_amt           NUMBER(30,2),
  int_amt            NUMBER(30,2),
  fprice_amt         NUMBER(30,2),
  stamp_fee          NUMBER(30,2),
  jsf_amt            NUMBER(30,2),
  ghf_amt            NUMBER(30,2),
  zgf_amt            NUMBER(30,2),
  sxf_amt            NUMBER(30,2),
  settle_fee         NUMBER(30,2),
  commission_amt     NUMBER(30,2),
  is_pay_with_trade  VARCHAR2(50),
  trade_amt          NUMBER(30,2),
  ps                 VARCHAR2(50),
  trade_market       VARCHAR2(50),
  trade_market_mode  VARCHAR2(50),
  trade_plat         VARCHAR2(50),
  pledge_sec_num     NUMBER(10),
  seat_number        VARCHAR2(50),
  portfolio_id       VARCHAR2(50),
  sec_acct_id        VARCHAR2(50),
  acct_no            VARCHAR2(50),
  batch_number       VARCHAR2(32),
  m_prin_amt         NUMBER(30,2),
  m_int_amt          NUMBER(30,2),
  m_trade_amt        NUMBER(30,2),
  int_rate           NUMBER(30,14),
  basis              VARCHAR2(50),
  busi_scene_calss   VARCHAR2(50),
  settle_mode        VARCHAR2(50),
  stamp_fee_raw      NUMBER(30,2),
  jsf_amt_raw        NUMBER(30,2),
  ghf_amt_raw        NUMBER(30,2),
  zgf_amt_raw        NUMBER(30,2),
  sxf_amt_raw        NUMBER(30,2),
  settle_fee_raw     NUMBER(30,2),
  commission_amt_raw NUMBER(30,2),
  trade_amt_raw      NUMBER(30,2),
  trade_id_raw       VARCHAR2(32),
  seq_no             VARCHAR2(32),
  is_purch_type_yx   VARCHAR2(50),
  create_user        VARCHAR2(20) not null,
  create_dept        VARCHAR2(32),
  create_time        TIMESTAMP(6) not null,
  update_user        VARCHAR2(20) not null,
  update_time        TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SETTLE_TRANS_DATA
  is '成交数据明细表';
-- Add comments to the columns 
comment on column SETTLE_TRANS_DATA.trade_id
  is '成交编号';
comment on column SETTLE_TRANS_DATA.deal_date
  is '执行日期';
comment on column SETTLE_TRANS_DATA.sec_type
  is '证券品种';
comment on column SETTLE_TRANS_DATA.apply_trade_id
  is '申请编号';
comment on column SETTLE_TRANS_DATA.finprod_id
  is '金融产品代码';
comment on column SETTLE_TRANS_DATA.finprod_type
  is '金融产品类型(估值核算)';
comment on column SETTLE_TRANS_DATA.finprod_type2
  is '金融产品类型(投管分类)';
comment on column SETTLE_TRANS_DATA.branch
  is '分支序号（默认为0）';
comment on column SETTLE_TRANS_DATA.busi_type
  is '业务类型';
comment on column SETTLE_TRANS_DATA.trade_date
  is '交易日期';
comment on column SETTLE_TRANS_DATA.settle_date
  is '交割日期';
comment on column SETTLE_TRANS_DATA.vdate
  is '起息日';
comment on column SETTLE_TRANS_DATA.mdate
  is '到期日';
comment on column SETTLE_TRANS_DATA.vpay_date
  is '首期交付日';
comment on column SETTLE_TRANS_DATA.mpay_date
  is '到期交付日';
comment on column SETTLE_TRANS_DATA.term_type
  is '期限品种';
comment on column SETTLE_TRANS_DATA.settle_speed
  is '清算速度';
comment on column SETTLE_TRANS_DATA.ccy
  is '交易币种';
comment on column SETTLE_TRANS_DATA.unit_cprice
  is '单位净价';
comment on column SETTLE_TRANS_DATA.unit_int
  is '单位利息';
comment on column SETTLE_TRANS_DATA.unit_fprice
  is '单位全价';
comment on column SETTLE_TRANS_DATA.par_value
  is '单位面值，债券100或者当前面值(还本前)，其他资产1';
comment on column SETTLE_TRANS_DATA.cprice_amt
  is '净价总额';
comment on column SETTLE_TRANS_DATA.share_amt
  is '交易份额，债券数量等';
comment on column SETTLE_TRANS_DATA.prin_amt
  is '交易本金，交易成本等';
comment on column SETTLE_TRANS_DATA.int_amt
  is '利息总额';
comment on column SETTLE_TRANS_DATA.fprice_amt
  is '全价总额';
comment on column SETTLE_TRANS_DATA.stamp_fee
  is '印花税';
comment on column SETTLE_TRANS_DATA.jsf_amt
  is '经手费';
comment on column SETTLE_TRANS_DATA.ghf_amt
  is '过户费';
comment on column SETTLE_TRANS_DATA.zgf_amt
  is '证管费';
comment on column SETTLE_TRANS_DATA.sxf_amt
  is '手续费';
comment on column SETTLE_TRANS_DATA.settle_fee
  is '结算费';
comment on column SETTLE_TRANS_DATA.commission_amt
  is '佣金';
comment on column SETTLE_TRANS_DATA.is_pay_with_trade
  is '佣金是否随交易支付';
comment on column SETTLE_TRANS_DATA.trade_amt
  is '交易金额';
comment on column SETTLE_TRANS_DATA.ps
  is '交易方向，买入、卖出、收息、还本、申购、赎回等';
comment on column SETTLE_TRANS_DATA.trade_market
  is '交易场所，银行间、上交所、深交所、柜面等';
comment on column SETTLE_TRANS_DATA.trade_market_mode
  is '交易市场，一级市场、二级市场';
comment on column SETTLE_TRANS_DATA.trade_plat
  is '交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等';
comment on column SETTLE_TRANS_DATA.pledge_sec_num
  is '押品只数';
comment on column SETTLE_TRANS_DATA.seat_number
  is '席位号';
comment on column SETTLE_TRANS_DATA.portfolio_id
  is '组合代码';
comment on column SETTLE_TRANS_DATA.sec_acct_id
  is '证券账户';
comment on column SETTLE_TRANS_DATA.acct_no
  is '资金账户';
comment on column SETTLE_TRANS_DATA.batch_number
  is '批次号';
comment on column SETTLE_TRANS_DATA.m_prin_amt
  is '到期本金';
comment on column SETTLE_TRANS_DATA.m_int_amt
  is '到期利息';
comment on column SETTLE_TRANS_DATA.m_trade_amt
  is '到期金额';
comment on column SETTLE_TRANS_DATA.int_rate
  is '利率';
comment on column SETTLE_TRANS_DATA.basis
  is '计息基础';
comment on column SETTLE_TRANS_DATA.busi_scene_calss
  is '业务场景分类';
comment on column SETTLE_TRANS_DATA.settle_mode
  is '交收方式';
comment on column SETTLE_TRANS_DATA.stamp_fee_raw
  is '印花税_原始';
comment on column SETTLE_TRANS_DATA.jsf_amt_raw
  is '经手费_原始';
comment on column SETTLE_TRANS_DATA.ghf_amt_raw
  is '过户费_原始';
comment on column SETTLE_TRANS_DATA.zgf_amt_raw
  is '证管费_原始';
comment on column SETTLE_TRANS_DATA.sxf_amt_raw
  is '手续费_原始';
comment on column SETTLE_TRANS_DATA.settle_fee_raw
  is '结算费_原始';
comment on column SETTLE_TRANS_DATA.commission_amt_raw
  is '佣金_原始';
comment on column SETTLE_TRANS_DATA.trade_amt_raw
  is '交易金额_原始';
comment on column SETTLE_TRANS_DATA.trade_id_raw
  is '成交编号_原始';
comment on column SETTLE_TRANS_DATA.seq_no
  is '临时表流水号';
comment on column SETTLE_TRANS_DATA.is_purch_type_yx
  is '是否优先配售';
comment on column SETTLE_TRANS_DATA.create_user
  is '创建人';
comment on column SETTLE_TRANS_DATA.create_dept
  is '创建部门';
comment on column SETTLE_TRANS_DATA.create_time
  is '创建时间';
comment on column SETTLE_TRANS_DATA.update_user
  is '更新人';
comment on column SETTLE_TRANS_DATA.update_time
  is '更新时间';
-- Create/Recreate indexes 
create index STL_IDX_TRANS_DATA_BUSI_TYPE on SETTLE_TRANS_DATA (BUSI_TYPE)
  ;
create index STL_IDX_TRANS_DATA_DEAL_DATE on SETTLE_TRANS_DATA (DEAL_DATE)
  ;
-- Create/Recreate primary, unique and foreign key constraints 
alter table SETTLE_TRANS_DATA
  add constraint PK_SETTLE_TRANS_DATA primary key (TRADE_ID)
  using index 
  ;



-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SETTLE_TRANS_DATA_ADD') ;
    if num > 0 then
        execute immediate 'drop table SETTLE_TRANS_DATA_ADD';
    end if;
end;
/
create table SETTLE_TRANS_DATA_ADD
(
  trade_id        VARCHAR2(32) not null,
  repo_type       VARCHAR2(50),
  con_price       NUMBER(30,14),
  con_amt         NUMBER(30,4),
  con_prin_amt    NUMBER(30,2),
  r_finprod_id    VARCHAR2(32),
  se_rep_day_type VARCHAR2(50),
  create_user     VARCHAR2(20) not null,
  create_dept     VARCHAR2(32),
  create_time     TIMESTAMP(6) not null,
  update_user     VARCHAR2(20) not null,
  update_time     TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SETTLE_TRANS_DATA_ADD
  is '成交数据明细附属表';
-- Add comments to the columns 
comment on column SETTLE_TRANS_DATA_ADD.trade_id
  is '成交编号';
comment on column SETTLE_TRANS_DATA_ADD.repo_type
  is '回购类型，质押式协议回购、质押式竞价回购、买断式回购';
comment on column SETTLE_TRANS_DATA_ADD.con_price
  is '转股价格';
comment on column SETTLE_TRANS_DATA_ADD.con_amt
  is '转股数量';
comment on column SETTLE_TRANS_DATA_ADD.con_prin_amt
  is '转股本金';
comment on column SETTLE_TRANS_DATA_ADD.r_finprod_id
  is '转换资产代码/债转股代码（基金转换/债转股）';
comment on column SETTLE_TRANS_DATA_ADD.se_rep_day_type
  is '交易所回购天数方式';
comment on column SETTLE_TRANS_DATA_ADD.create_user
  is '创建人';
comment on column SETTLE_TRANS_DATA_ADD.create_dept
  is '创建部门';
comment on column SETTLE_TRANS_DATA_ADD.create_time
  is '创建时间';
comment on column SETTLE_TRANS_DATA_ADD.update_user
  is '更新人';
comment on column SETTLE_TRANS_DATA_ADD.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SETTLE_TRANS_DATA_ADD
  add constraint PK_SETTLE_TRANS_DATA_ADD primary key (TRADE_ID)
  using index 
  ;



-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SETTLE_TRANS_DATA_PLEDGE') ;
    if num > 0 then
        execute immediate 'drop table SETTLE_TRANS_DATA_PLEDGE';
    end if;
end;
/
create table SETTLE_TRANS_DATA_PLEDGE
(
  trade_id          VARCHAR2(32) not null,
  branch            NUMBER(10) not null,
  pledge_finprod_id VARCHAR2(50) not null,
  pledge_face_value NUMBER(30,2),
  pledge_number     NUMBER(30,2),
  pledge_ratio      NUMBER(30,14),
  pledge_amt        NUMBER(30,2),
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SETTLE_TRANS_DATA_PLEDGE
  is '成交数据明细质押品信息表';
-- Add comments to the columns 
comment on column SETTLE_TRANS_DATA_PLEDGE.trade_id
  is '成交编号';
comment on column SETTLE_TRANS_DATA_PLEDGE.branch
  is '分支序号';
comment on column SETTLE_TRANS_DATA_PLEDGE.pledge_finprod_id
  is '质押金融产品代码';
comment on column SETTLE_TRANS_DATA_PLEDGE.pledge_face_value
  is '质押面值';
comment on column SETTLE_TRANS_DATA_PLEDGE.pledge_number
  is '质押数量';
comment on column SETTLE_TRANS_DATA_PLEDGE.pledge_ratio
  is '质押率';
comment on column SETTLE_TRANS_DATA_PLEDGE.pledge_amt
  is '质押金额';
comment on column SETTLE_TRANS_DATA_PLEDGE.create_user
  is '创建人';
comment on column SETTLE_TRANS_DATA_PLEDGE.create_dept
  is '创建部门';
comment on column SETTLE_TRANS_DATA_PLEDGE.create_time
  is '创建时间';
comment on column SETTLE_TRANS_DATA_PLEDGE.update_user
  is '更新人';
comment on column SETTLE_TRANS_DATA_PLEDGE.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SETTLE_TRANS_DATA_PLEDGE
  add constraint PK_SETTLE_TRANS_DATA_PLEDGE primary key (TRADE_ID)
  using index 
  ;




-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SETTLE_TRANS_TG_MAPPING') ;
    if num > 0 then
        execute immediate 'drop table SETTLE_TRANS_TG_MAPPING';
    end if;
end;
/
create table SETTLE_TRANS_TG_MAPPING
(
  trade_id       VARCHAR2(32) not null,
  trade_id_tg    VARCHAR2(32),
  tg_data_status VARCHAR2(50),
  create_user    VARCHAR2(20) not null,
  create_dept    VARCHAR2(32),
  create_time    TIMESTAMP(6) not null,
  update_user    VARCHAR2(20) not null,
  update_time    TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SETTLE_TRANS_TG_MAPPING
  is '成交数据与投管交易关系';
-- Add comments to the columns 
comment on column SETTLE_TRANS_TG_MAPPING.trade_id
  is '成交编号';
comment on column SETTLE_TRANS_TG_MAPPING.trade_id_tg
  is '投管交易编号';
comment on column SETTLE_TRANS_TG_MAPPING.tg_data_status
  is '投管交易数据状态（01：待处理、02：处理结束）';
comment on column SETTLE_TRANS_TG_MAPPING.create_user
  is '创建人';
comment on column SETTLE_TRANS_TG_MAPPING.create_dept
  is '创建部门';
comment on column SETTLE_TRANS_TG_MAPPING.create_time
  is '创建时间';
comment on column SETTLE_TRANS_TG_MAPPING.update_user
  is '更新人';
comment on column SETTLE_TRANS_TG_MAPPING.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SETTLE_TRANS_TG_MAPPING
  add constraint PK_SETTLE_TRANS_TG_MAPPING primary key (TRADE_ID)
  using index 
  ;



-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('FIN_PRODUCT_QUTO') ;
    if num > 0 then
        execute immediate 'drop table FIN_PRODUCT_QUTO';
    end if;
end;
/
create table FIN_PRODUCT_QUTO
(
  seq_no       VARCHAR2(32) not null,
  portfolio_id VARCHAR2(32),
  sec_acct_id  VARCHAR2(32),
  finprod_id   VARCHAR2(32),
  oth_type     VARCHAR2(50),
  cdate        DATE,
  quto         NUMBER(30,14),
  ipo_quto     NUMBER(30,14),
  bond_id      VARCHAR2(50),
  trade_market VARCHAR2(50),
  create_user  VARCHAR2(20) not null,
  create_dept  VARCHAR2(32),
  create_time  TIMESTAMP(6) not null,
  update_user  VARCHAR2(20) not null,
  update_time  TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table FIN_PRODUCT_QUTO
  is '交易所品种额度';
-- Add comments to the columns 
comment on column FIN_PRODUCT_QUTO.seq_no
  is '流水号';
comment on column FIN_PRODUCT_QUTO.portfolio_id
  is '组合代码';
comment on column FIN_PRODUCT_QUTO.sec_acct_id
  is '证券账户代码';
comment on column FIN_PRODUCT_QUTO.finprod_id
  is '金融产品代码';
comment on column FIN_PRODUCT_QUTO.oth_type
  is '业务类型';
comment on column FIN_PRODUCT_QUTO.cdate
  is '日期';
comment on column FIN_PRODUCT_QUTO.quto
  is '额度';
comment on column FIN_PRODUCT_QUTO.ipo_quto
  is '科创板额度';
comment on column FIN_PRODUCT_QUTO.bond_id
  is '股东代码';
comment on column FIN_PRODUCT_QUTO.trade_market
  is '交易场所';
comment on column FIN_PRODUCT_QUTO.create_user
  is '创建人';
comment on column FIN_PRODUCT_QUTO.create_dept
  is '创建部门';
comment on column FIN_PRODUCT_QUTO.create_time
  is '创建时间';
comment on column FIN_PRODUCT_QUTO.update_user
  is '更新人';
comment on column FIN_PRODUCT_QUTO.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table FIN_PRODUCT_QUTO
  add constraint PK_FIN_PRODUCT_QUTO primary key (SEQ_NO)
  using index 
  ;



-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('COMMISSION_CACL_PROCESS') ;
    if num > 0 then
        execute immediate 'drop table COMMISSION_CACL_PROCESS';
    end if;
end;
/
create table COMMISSION_CACL_PROCESS
(
  busi_no            VARCHAR2(32) not null,
  calc_way           VARCHAR2(50),
  calc_base          VARCHAR2(50),
  yield              NUMBER(30,14),
  include_jsf_amt    NUMBER(30,2),
  include_settle_fee NUMBER(30,2),
  include_zgf_amt    NUMBER(30,2),
  include_ghf_amt    NUMBER(30,2),
  include_stamp_fee  NUMBER(30,2),
  max_quota_amt      NUMBER(30,2),
  min_quota_amt      NUMBER(30,2),
  commission_amt     NUMBER(30,2),
  batch_number       VARCHAR2(32),
  create_user        VARCHAR2(20) not null,
  create_dept        VARCHAR2(32),
  create_time        TIMESTAMP(6) not null,
  update_user        VARCHAR2(20) not null,
  update_time        TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table COMMISSION_CACL_PROCESS
  is '佣金计算过程表';
-- Add comments to the columns 
comment on column COMMISSION_CACL_PROCESS.busi_no
  is '业务编号';
comment on column COMMISSION_CACL_PROCESS.calc_way
  is '佣金计算方式';
comment on column COMMISSION_CACL_PROCESS.calc_base
  is '计算基数';
comment on column COMMISSION_CACL_PROCESS.yield
  is '费率';
comment on column COMMISSION_CACL_PROCESS.include_jsf_amt
  is '佣金包含经手费金额';
comment on column COMMISSION_CACL_PROCESS.include_settle_fee
  is '佣金包含结算费金额';
comment on column COMMISSION_CACL_PROCESS.include_zgf_amt
  is '佣金包含证管费金额';
comment on column COMMISSION_CACL_PROCESS.include_ghf_amt
  is '佣金包含过户费金额';
comment on column COMMISSION_CACL_PROCESS.include_stamp_fee
  is '佣金包含印花税金额';
comment on column COMMISSION_CACL_PROCESS.max_quota_amt
  is '限额最大值';
comment on column COMMISSION_CACL_PROCESS.min_quota_amt
  is '限额最小值';
comment on column COMMISSION_CACL_PROCESS.commission_amt
  is '佣金金额';
comment on column COMMISSION_CACL_PROCESS.batch_number
  is '批次号';
comment on column COMMISSION_CACL_PROCESS.create_user
  is '创建人';
comment on column COMMISSION_CACL_PROCESS.create_dept
  is '创建部门';
comment on column COMMISSION_CACL_PROCESS.create_time
  is '创建时间';
comment on column COMMISSION_CACL_PROCESS.update_user
  is '更新人';
comment on column COMMISSION_CACL_PROCESS.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table COMMISSION_CACL_PROCESS
  add constraint PK_COMMISSION_CACL_PROCESS primary key (BUSI_NO)
  using index 
  ;

delete from  sys_datadict where DICT_CODE = 'SETTLE_MODE';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SETTLE_MODE', 'STL', '交收方式', 'SETTLE_MODE', '02', 'system', null, sysdate, 'system', sysdate); 

delete from sys_datadict_list where DICT_CODE = 'SETTLE_MODE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SETTLE_MODE', '01', '担保', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SETTLE_MODE', '02', '非担保', 2, '0', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict where DICT_CODE = 'BUSI_SCENE_CALSS';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', 'STL', '业务场景分类明细', 'BUSI_SCENE_CALSS', '02', 'system', null, sysdate, 'system', sysdate);
  
delete from sys_datadict_list where DICT_CODE = 'BUSI_SCENE_CALSS';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '010101', '债券-债券买入', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '010201', '债券-债券卖出', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '010301', '债券-还本收息-还本', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '010302', '债券-还本收息-收息', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '010401', '债券-到期-到期', 5, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '010501', '债券-回售-行权回售', 6, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '010601', '债券-赎回', 7, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '010701', '债券-投标-申购', 8, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '010702', '债券-中签-买入', 9, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '010801', '债券-网上申购-申购', 10, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '010802', '债券-网上申购-申购确认', 11, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '010901', '债券-网下申购-申购确认', 12, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '011001', '债券-申购确认', 13, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '011101', '债券-保证金-保证金划款', 14, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '011102', '债券-保证金-保证金回款', 15, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '011201', '债券-换股', 16, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '011301', '债券-优先配售-申购', 17, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '011401', '债券-优先配售-买入', 18, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '011501', '债券-转股-债转股', 19, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020101', '基金-ETF-买入', 20, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020102', '基金-ETF-卖出', 21, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020103', '基金-ETF-分红-现金分红', 22, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020104', '基金-ETF-分红-除权登记', 23, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020105', '基金-ETF-分红-红利再投', 24, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020106', '基金-ETF-认购', 25, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020107', '基金-ETF-认购确认', 26, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020108', '基金-ETF-认购退款', 27, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020109', '基金-ETF-申购', 28, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020110', '基金-ETF-申购确认', 29, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020111', '基金-ETF-申购退款', 30, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020112', '基金-ETF-赎回', 31, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020113', '基金-ETF-赎回确认', 32, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020114', '基金-ETF-赎回到账', 33, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020115', '基金-ETF-份额折算-份额强增', 34, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020116', '基金-ETF-份额折算-份额强减', 35, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020201', '基金-实时申赎货币基金-买入', 36, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020202', '基金-实时申赎货币基金-卖出', 37, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020203', '基金-实时申赎货币基金-分红-现金分红', 38, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020204', '基金-实时申赎货币基金-分红-除权登记', 39, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020205', '基金-实时申赎货币基金-分红-红利再投', 40, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020206', '基金-实时申赎货币基金-认购', 41, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020207', '基金-实时申赎货币基金-认购确认', 42, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020208', '基金-实时申赎货币基金-认购退款', 43, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020209', '基金-实时申赎货币基金-申购', 44, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020210', '基金-实时申赎货币基金-申购确认', 45, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020211', '基金-实时申赎货币基金-申购退款', 46, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020212', '基金-实时申赎货币基金-赎回', 47, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020213', '基金-实时申赎货币基金-赎回确认', 48, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020214', '基金-实时申赎货币基金-赎回到账', 49, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020215', '基金-实时申赎货币基金-份额折算-份额强增', 50, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020216', '基金-实时申赎货币基金-份额折算-份额强减', 51, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020301', '基金-交易型货币基金-买入', 52, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020302', '基金-交易型货币基金-卖出', 53, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020303', '基金-交易型货币基金-分红-现金分红', 54, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020304', '基金-交易型货币基金-分红-除权登记', 55, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020305', '基金-交易型货币基金-分红-红利再投', 56, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020306', '基金-交易型货币基金-认购', 57, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020307', '基金-交易型货币基金-认购确认', 58, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020308', '基金-交易型货币基金-认购退款', 59, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020309', '基金-交易型货币基金-申购', 60, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020310', '基金-交易型货币基金-申购确认', 61, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020311', '基金-交易型货币基金-申购退出', 62, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020312', '基金-交易型货币基金-赎回', 63, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020313', '基金-交易型货币基金-赎回确认', 64, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020314', '基金-交易型货币基金-赎回到账', 65, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020315', '基金-交易型货币基金-份额折算-份额强增', 66, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020316', '基金-交易型货币基金-份额折算-份额强减', 67, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020401', '基金-LOF-买入', 68, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020402', '基金-LOF-卖出', 69, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020403', '基金-LOF-分红-现金分红', 70, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020404', '基金-LOF-分红-除权登记', 71, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020405', '基金-LOF-分红-红利再投', 72, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020406', '基金-LOF-认购', 73, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020407', '基金-LOF-认购确认', 74, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020408', '基金-LOF-认购退款', 75, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020409', '基金-LOF-申购', 76, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020410', '基金-LOF-申购确认', 77, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020411', '基金-LOF-申购退款', 78, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020412', '基金-LOF-赎回', 79, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020413', '基金-LOF-赎回确认', 80, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020414', '基金-LOF-赎回到账', 81, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020415', '基金-LOF-份额折算-份额强增', 82, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020416', '基金-LOF-份额折算-份额强减', 83, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020501', '基金-分级LOF-买入', 84, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020502', '基金-分级LOF-卖出', 85, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020503', '基金-分级LOF-分红-现金分红', 86, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020504', '基金-分级LOF-分红-除权登记', 87, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020505', '基金-分级LOF-分红-红利再投', 88, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020506', '基金-分级LOF-认购', 89, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020507', '基金-分级LOF-认购确认', 90, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020508', '基金-分级LOF-认购退款', 91, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020509', '基金-分级LOF-申购', 92, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020510', '基金-分级LOF-申购确认', 93, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020511', '基金-分级LOF-申购退款', 94, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020512', '基金-分级LOF-赎回', 95, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020513', '基金-分级LOF-赎回确认', 96, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020514', '基金-分级LOF-赎回到账', 97, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020515', '基金-分级LOF-份额折算-份额强增', 98, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020516', '基金-分级LOF-份额折算-份额强减', 99, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020601', '基金-封闭式基金-买入', 100, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020602', '基金-封闭式基金-卖出', 101, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020603', '基金-封闭式基金-分红-现金分红', 102, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020604', '基金-封闭式基金-分红-除权登记', 103, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020605', '基金-封闭式基金-分红-红利再投', 104, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020606', '基金-封闭式基金-认购', 105, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020607', '基金-封闭式基金-认购确认', 106, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020608', '基金-封闭式基金-认购退款', 107, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020609', '基金-封闭式基金-申购', 108, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020610', '基金-封闭式基金-申购确认', 109, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020611', '基金-封闭式基金-申购退款', 110, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020612', '基金-封闭式基金-赎回', 111, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020613', '基金-封闭式基金-赎回确认', 112, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020614', '基金-封闭式基金-赎回到账', 113, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020615', '基金-封闭式基金-份额折算-份额强增', 114, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020616', '基金-封闭式基金-份额折算-份额强减', 115, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020701', '基金-非货币基金-买入', 116, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020702', '基金-非货币基金-卖出', 117, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020703', '基金-非货币基金-分红-现金分红', 118, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020704', '基金-非货币基金-分红-除权登记', 119, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020705', '基金-非货币基金-分红-红利再投', 120, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020706', '基金-非货币基金-认购', 121, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020707', '基金-非货币基金-认购确认', 122, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020708', '基金-非货币基金-认购退款', 123, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020709', '基金-非货币基金-申购', 124, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020710', '基金-非货币基金-申购确认', 125, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020711', '基金-非货币基金-申购退款', 126, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020712', '基金-非货币基金-赎回', 127, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020713', '基金-非货币基金-赎回确认', 128, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020714', '基金-非货币基金-赎回到账', 129, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020715', '基金-非货币基金-份额折算-份额强增', 130, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020716', '基金-非货币基金-份额折算-份额强减', 131, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020801', '基金-货币基金-买入', 132, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020802', '基金-货币基金-卖出', 133, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020803', '基金-货币基金-分红-现金分红', 134, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020804', '基金-货币基金-分红-除权登记', 135, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020805', '基金-货币基金-分红-红利再投', 136, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020806', '基金-货币基金-认购', 137, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020807', '基金-货币基金-认购确认', 138, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020808', '基金-货币基金-认购退款', 139, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020809', '基金-货币基金-申购', 140, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020810', '基金-货币基金-申购确认', 141, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020811', '基金-货币基金-申购退款', 142, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020812', '基金-货币基金-赎回', 143, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020813', '基金-货币基金-赎回确认', 144, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020814', '基金-货币基金-赎回到账', 145, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020815', '基金-货币基金-份额折算-份额强增', 146, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020816', '基金-货币基金-份额折算-份额强减', 147, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020901', '基金-开放式基金-买入', 148, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020902', '基金-开放式基金-卖出', 149, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020903', '基金-开放式基金-分红-现金分红', 150, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020904', '基金-开放式基金-分红-除权登记', 151, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020905', '基金-开放式基金-分红-红利再投', 152, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020906', '基金-开放式基金-认购', 153, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020907', '基金-开放式基金-认购确认', 154, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020908', '基金-开放式基金-认购退款', 155, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020909', '基金-开放式基金-申购', 156, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020910', '基金-开放式基金-申购确认', 157, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020911', '基金-开放式基金-申购退款', 158, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020912', '基金-开放式基金-赎回', 159, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020913', '基金-开放式基金-赎回确认', 160, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020914', '基金-开放式基金-赎回到账', 161, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020915', '基金-开放式基金-份额折算-份额强增', 162, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '020916', '基金-开放式基金-份额折算-份额强减', 163, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '060101', '股票-新股中签', 164, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '060201', '股票-配股认购-配股', 165, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '060301', '股票-公开增发申购-增发(配售)', 166, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '060401', '股票-买入', 167, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '060501', '股票-卖出', 168, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '060601', '股票-分红-现金分红', 169, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '060602', '股票-分红-除权登记', 170, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '060603', '股票-分红-红利再投', 171, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '060701', '股票-送股', 172, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '060801', '股票-股息红利税-补缴', 173, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0901RB01', '债券质押式竞价逆回购-首期-买入', 174, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0901RB02', '债券质押式竞价逆回购-到期', 175, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0901RS01', '债券质押式竞价正回购-标准券入库', 176, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0901RS02', '债券质押式竞价正回购-标准券出库', 177, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0901RS03', '债券质押式竞价正回购-首期-买入', 178, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0901RS04', '债券质押式竞价正回购-到期', 179, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0902RB01', '债券协议逆回购-首期-买入', 180, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0902RB02', '债券协议逆回购-到期', 181, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0902RB03', '债券协议逆回购-提取终止', 182, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0902RB04', '债券协议逆回购-到期续作', 183, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0902RB05', '债券协议逆回购-逾期返售(解除质押)', 184, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0902RB06', '债券协议逆回购-换券', 185, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0902RS01', '债券协议正回购-换券', 186, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0902RS02', '债券协议正回购-首期-买入', 187, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0902RS03', '债券协议正回购-到期', 188, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0902RS04', '债券协议正回购-到期续作', 189, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0902RS05', '债券协议正回购-提取终止', 190, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0902RS06', '债券协议正回购-逾期返售(解除质押)', 191, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0903RB01', '买断式回购-卖出(融券方)-买入', 196, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0903RB02', '买断式回购-卖出(融券方)-到期', 197, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0903RB03', '买断式回购-卖出(融券方)-保证金划款', 198, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0903RB04', '买断式回购-卖出(融券方)-保证金回款', 199, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0903RS01', '买断式回购-买入(融资方)-买入', 192, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0903RS02', '买断式回购-买入(融资方)-到期', 193, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0903RS03', '买断式回购-买入(融资方)-保证金划款', 194, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0903RS04', '买断式回购-买入(融资方)-保证金回款', 195, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0904RB01', '隔夜逆回购-首期-买入', 202, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0904RB02', '隔夜逆回购-到期', 203, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0904RS01', '隔夜正回购-首期-买入', 200, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0904RS02', '隔夜正回购-到期', 201, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0905RB01', '报价逆回购-首期-买入', 206, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0905RB02', '报价逆回购-到期', 207, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0905RS01', '报价正回购-首期-买入', 204, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BUSI_SCENE_CALSS', '0905RS02', '报价正回购-到期', 205, '0', 'system', null, sysdate, 'system', sysdate);  


delete from sys_datadict_list where DICT_CODE = 'PS' and ITEM_CODE in ('T67','T68','T69','T70','T71','T72','T73','T74');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PS', 'T67', '换券', 100, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PS', 'T68', '质押券入库', 100, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PS', 'T69', '质押券出库', 100, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PS', 'T70', '换股', 100, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PS', 'T71', '收购方过入', 100, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PS', 'T72', '预受方过出', 100, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PS', 'T73', '送股', 100, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PS', 'T74', '派本派息', 100, '0', 'system', null, sysdate, 'system', sysdate);
  

delete from sys_datadict where DICT_CODE = 'CLEARDATA_SOURCE';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CLEARDATA_SOURCE', 'STL', '清算数据源表', 'CLEARDATA_SOURCE', '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE = 'CLEARDATA_SOURCE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CLEARDATA_SOURCE', 'ABCSJ', '上中登ABCSJ', 6, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CLEARDATA_SOURCE', 'JSMX', '上中登JSMX', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CLEARDATA_SOURCE', 'SJSFX', '深中登SJSFX', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CLEARDATA_SOURCE', 'SJSJG', '深中登SJSJG', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CLEARDATA_SOURCE', 'SJSMX', '深中登SJSMX', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CLEARDATA_SOURCE', 'ZQBD', '上中登ZQBD', 5, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CLEARDATA_SOURCE', 'ZSMX', '深中登ZSMX', 7, '0', 'system', null, sysdate, 'system', sysdate);
  
  
delete from sys_datadict where DICT_CODE = 'TG_DATA_STATUS';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TG_DATA_STATUS', 'STL', '投管交易数据状态', 'BATCH_RESULT', '02', 'system', null, sysdate, 'system', sysdate);  
  
  
delete from sys_datadict_list where dict_code = 'TG_DATA_STATUS';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TG_DATA_STATUS', '01', '待处理', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TG_DATA_STATUS', '02', '处理结束', 2, '0', 'system', null, sysdate, 'system', sysdate);
  

delete from sys_datadict_list where DICT_CODE = 'CUSTOM_ASSET_TYPE_TWO' and ITEM_CODE in ('B01','B05','B06','B07','B13','B14','B17');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CUSTOM_ASSET_TYPE_TWO', 'B01', '国债', 19, '0', 'system', null, sysdate, 'system', sysdate);
  
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CUSTOM_ASSET_TYPE_TWO', 'B05', '金融债', 23, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CUSTOM_ASSET_TYPE_TWO', 'B06', '企业债', 24, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CUSTOM_ASSET_TYPE_TWO', 'B07', '公司债', 25, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CUSTOM_ASSET_TYPE_TWO', 'B13', '资产支持证券', 31, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CUSTOM_ASSET_TYPE_TWO', 'B14', '可转债', 32, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CUSTOM_ASSET_TYPE_TWO', 'B17', '优先股', 35, '0', 'system', null, sysdate, 'system', sysdate);

--批处理任务
delete from sys_batch_task where BEAN_NAME in ('stlDataDownTask','stlDownDataDealTask','stlDownDataGatherTask','investDealRevokeTask','stlDataCreateTask'); 
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('50001', 'STL', '结算数据下行导入批处理', null, 'stlDataDownTask', 70, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('50011', 'STL', '结算下行数据清算批处理', '50001', 'stlDownDataDealTask', 72, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('50012', 'STL', '结算下行交易汇总批处理', '50013', 'stlDownDataGatherTask', 73, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('50013', 'STL', '投管交易撤销批处理', '50011', 'investDealRevokeTask', 71, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('500014', 'STL', '结算下行数据自动生成批处理', null, 'stlDataCreateTask', 74, 'system', null, sysdate, 'system', sysdate, null);


--视图创建
CREATE OR REPLACE VIEW STL_VW_BOND_SEAT_PTL_NO AS
SELECT
    psa.PORTFOLIO_ID,
    ssai.BOND_ID,
    msd.SH_SEATNO,
    msd.SZ_SEATNO
FROM
    STL_SEC_ACCT_INFO ssai
INNER JOIN
    PTL_SEC_ACCT psa
ON
    psa.ACCT_NO = ssai.SEC_ACCT_ID
INNER JOIN
    PTL_CAPACCT_INFO pci
ON
    pci.PORTFOLIO_ID = psa.PORTFOLIO_ID AND pci.CCY = 'CNY' AND pci.ACCT_TYPE = 'A07'
INNER JOIN
    STL_CAPACCT_INFO sci
ON
    sci.ACCT_NO = pci.ACCT_NO AND sci.CCY = 'CNY'
INNER JOIN
    MST_SALE_DEPARTMENT msd
ON
    msd.SALE_DEPID = sci.OWN_DEPT
WHERE
    ssai.SEC_ACCT_TYPE = 'SEC';
comment on table STL_VW_BOND_SEAT_PTL_NO is '股东代码和席位号关联组合代码视图';
comment on column STL_VW_BOND_SEAT_PTL_NO.PORTFOLIO_ID is '组合代码';
comment on column STL_VW_BOND_SEAT_PTL_NO.BOND_ID is '证券账号（股东代码）';
comment on column STL_VW_BOND_SEAT_PTL_NO.SH_SEATNO is '上交所席位号';
comment on column STL_VW_BOND_SEAT_PTL_NO.SZ_SEATNO is '深交所席位号';

--系统参数
delete from sys_para where PARA_CODE = 'STL_DATA_DOWN_FILE_PATH';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('STL', 'STL_DATA_DOWN_FILE_PATH', 'D:/home/tams/snstzjy/stldownloadpath', '01', null, '结算数据下行数据文件存放路径', 'system', null, sysdate, 'etliu', sysdate);










