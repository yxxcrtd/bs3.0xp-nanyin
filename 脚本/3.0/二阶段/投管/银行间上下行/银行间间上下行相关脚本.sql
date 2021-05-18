

-----------------------------------------------------组件pms-----------------------------------------------------------
-- Create table

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('MST_CFETS_CUSTOMER_INFO') ;
    if num > 0 then
        execute immediate 'drop table MST_CFETS_CUSTOMER_INFO' ;
    end if;
end;
/
create table MST_CFETS_CUSTOMER_INFO
(
  party_id          VARCHAR2(32) not null,
  party_customer_id VARCHAR2(32) not null,
  party_abbr        VARCHAR2(200),
  party_name        VARCHAR2(200),
  party_abbr_en     VARCHAR2(200),
  party_name_en     VARCHAR2(200),
  remark            VARCHAR2(1000),
  create_user       VARCHAR2(20) not null,
  create_time       TIMESTAMP(6) not null,
  create_dept       VARCHAR2(32),
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
);
-- Add comments to the table
comment on table MST_CFETS_CUSTOMER_INFO
  is '客户信息_银行机构';
-- Add comments to the columns
comment on column MST_CFETS_CUSTOMER_INFO.party_id
  is '交易成员ID，21位编号';
comment on column MST_CFETS_CUSTOMER_INFO.party_customer_id
  is '交易机构ID，6位编号';
comment on column MST_CFETS_CUSTOMER_INFO.party_abbr
  is '交易成员简称';
comment on column MST_CFETS_CUSTOMER_INFO.party_name
  is '交易成员全称';
comment on column MST_CFETS_CUSTOMER_INFO.party_abbr_en
  is '交易成员英文简称';
comment on column MST_CFETS_CUSTOMER_INFO.party_name_en
  is '交易成员英文全称';
comment on column MST_CFETS_CUSTOMER_INFO.remark
  is '备注';
comment on column MST_CFETS_CUSTOMER_INFO.create_user
  is '创建人';
comment on column MST_CFETS_CUSTOMER_INFO.create_time
  is '创建时间';
comment on column MST_CFETS_CUSTOMER_INFO.create_dept
  is '创建部门';
comment on column MST_CFETS_CUSTOMER_INFO.update_user
  is '更新人';
comment on column MST_CFETS_CUSTOMER_INFO.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table MST_CFETS_CUSTOMER_INFO
  add constraint PK_MST_CFETS_CUSTOMER_INFO primary key (PARTY_ID)
  using index;




  -- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('MST_CFETS_CUSTOMER_TRADER') ;
    if num > 0 then
        execute immediate 'drop table MST_CFETS_CUSTOMER_TRADER' ;
    end if;
end;
/
create table MST_CFETS_CUSTOMER_TRADER
(
  trader_id   VARCHAR2(32) not null,
  party_id    VARCHAR2(32),
  mparty_id   VARCHAR2(32),
  trader_name VARCHAR2(500),
  tel_no      VARCHAR2(50),
  fax_no      VARCHAR2(50),
  msn         VARCHAR2(50),
  email       VARCHAR2(50),
  address     VARCHAR2(1000),
  post_code   VARCHAR2(50),
  remark      VARCHAR2(1000),
  create_user VARCHAR2(20) not null,
  create_time TIMESTAMP(6) not null,
  create_dept VARCHAR2(32),
  update_user VARCHAR2(20) not null,
  update_time TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table MST_CFETS_CUSTOMER_TRADER
  is '客户信息_银行交易员';
-- Add comments to the columns
comment on column MST_CFETS_CUSTOMER_TRADER.trader_id
  is '交易员ID';
comment on column MST_CFETS_CUSTOMER_TRADER.party_id
  is '交易成员ID，21位编号';
comment on column MST_CFETS_CUSTOMER_TRADER.mparty_id
  is '主交易成员ID，21位编号';
comment on column MST_CFETS_CUSTOMER_TRADER.trader_name
  is '交易员姓名';
comment on column MST_CFETS_CUSTOMER_TRADER.tel_no
  is '联系电话';
comment on column MST_CFETS_CUSTOMER_TRADER.fax_no
  is '传真';
comment on column MST_CFETS_CUSTOMER_TRADER.msn
  is 'MSN';
comment on column MST_CFETS_CUSTOMER_TRADER.email
  is '电子邮箱';
comment on column MST_CFETS_CUSTOMER_TRADER.address
  is '地址';
comment on column MST_CFETS_CUSTOMER_TRADER.post_code
  is '邮政编号';
comment on column MST_CFETS_CUSTOMER_TRADER.remark
  is '备注';
comment on column MST_CFETS_CUSTOMER_TRADER.create_user
  is '创建人';
comment on column MST_CFETS_CUSTOMER_TRADER.create_time
  is '创建时间';
comment on column MST_CFETS_CUSTOMER_TRADER.create_dept
  is '创建部门';
comment on column MST_CFETS_CUSTOMER_TRADER.update_user
  is '更新人';
comment on column MST_CFETS_CUSTOMER_TRADER.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table MST_CFETS_CUSTOMER_TRADER
  add constraint PK_MST_CFETS_CUSTOMER_TRADER primary key (TRADER_ID)
  using index
  ;



  -- Create table

  declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_PORTFOLIO_ACCT_RELA') ;
    if num > 0 then
        execute immediate 'drop table IBU_PORTFOLIO_ACCT_RELA' ;
    end if;
end;
/
create table IBU_PORTFOLIO_ACCT_RELA
(
  portfolio_id    VARCHAR2(32) not null,
  party_id        VARCHAR2(32) not null,
  cfets_acct_type VARCHAR2(50) not null,
  create_user     VARCHAR2(20) not null,
  create_dept     VARCHAR2(32),
  create_time     TIMESTAMP(6) not null,
  update_user     VARCHAR2(20) not null,
  update_time     TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table IBU_PORTFOLIO_ACCT_RELA
  is '银行间账户组合关系';
-- Add comments to the columns
comment on column IBU_PORTFOLIO_ACCT_RELA.portfolio_id
  is '组合代码';
comment on column IBU_PORTFOLIO_ACCT_RELA.party_id
  is '交易成员ID，21位编号';
comment on column IBU_PORTFOLIO_ACCT_RELA.cfets_acct_type
  is '银行间账户类型，自营/理财';
comment on column IBU_PORTFOLIO_ACCT_RELA.create_user
  is '创建人';
comment on column IBU_PORTFOLIO_ACCT_RELA.create_dept
  is '创建部门';
comment on column IBU_PORTFOLIO_ACCT_RELA.create_time
  is '创建时间';
comment on column IBU_PORTFOLIO_ACCT_RELA.update_user
  is '更新人';
comment on column IBU_PORTFOLIO_ACCT_RELA.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_PORTFOLIO_ACCT_RELA
  add constraint PK_IBU_PORTFOLIO_ACCT_RELA primary key (PORTFOLIO_ID, PARTY_ID, CFETS_ACCT_TYPE)
  using index
  ;

  -- Create table
   declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_INTF_ATTR_CONFIG') ;
    if num > 0 then
        execute immediate 'drop table IBU_INTF_ATTR_CONFIG' ;
    end if;
end;
/
create table IBU_INTF_ATTR_CONFIG
(
  arrt_grp_id VARCHAR2(32) not null,
  attr_id     VARCHAR2(50) not null,
  attr_value  VARCHAR2(50),
  attr_type   VARCHAR2(50),
  create_user VARCHAR2(20) not null,
  create_dept VARCHAR2(32),
  create_time TIMESTAMP(6) not null,
  update_user VARCHAR2(20) not null,
  update_time TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table IBU_INTF_ATTR_CONFIG
  is '银行间上行报价属性配置';
-- Add comments to the columns
comment on column IBU_INTF_ATTR_CONFIG.arrt_grp_id
  is '属性组';
comment on column IBU_INTF_ATTR_CONFIG.attr_id
  is '属性ID';
comment on column IBU_INTF_ATTR_CONFIG.attr_value
  is '属性值';
comment on column IBU_INTF_ATTR_CONFIG.attr_type
  is '属性类型';
comment on column IBU_INTF_ATTR_CONFIG.create_user
  is '创建人';
comment on column IBU_INTF_ATTR_CONFIG.create_dept
  is '创建部门';
comment on column IBU_INTF_ATTR_CONFIG.create_time
  is '创建时间';
comment on column IBU_INTF_ATTR_CONFIG.update_user
  is '更新人';
comment on column IBU_INTF_ATTR_CONFIG.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_INTF_ATTR_CONFIG
  add constraint PK_IBU_INTF_ATTR_CONFIG primary key (ARRT_GRP_ID, ATTR_ID)
  using index
  ;

  -- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_INTF_ERR_MSG') ;
    if num > 0 then
        execute immediate 'drop table IBU_INTF_ERR_MSG' ;
    end if;
end;
/
create table IBU_INTF_ERR_MSG
(
  err_code    VARCHAR2(50) not null,
  err_msg     VARCHAR2(1000) not null,
  create_user VARCHAR2(20) not null,
  create_dept VARCHAR2(32),
  create_time TIMESTAMP(6) not null,
  update_user VARCHAR2(20) not null,
  update_time TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table IBU_INTF_ERR_MSG
  is '银行间上行错误码';
-- Add comments to the columns
comment on column IBU_INTF_ERR_MSG.err_code
  is '错误码';
comment on column IBU_INTF_ERR_MSG.err_msg
  is '错误信息';
comment on column IBU_INTF_ERR_MSG.create_user
  is '创建人';
comment on column IBU_INTF_ERR_MSG.create_dept
  is '创建部门';
comment on column IBU_INTF_ERR_MSG.create_time
  is '创建时间';
comment on column IBU_INTF_ERR_MSG.update_user
  is '更新人';
comment on column IBU_INTF_ERR_MSG.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_INTF_ERR_MSG
  add constraint PK_IBU_INTF_ERR_MSG primary key (ERR_CODE, ERR_MSG)
  using index
  ;



-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_INTF_QUOTE_SCHEME') ;
    if num > 0 then
        execute immediate 'drop table IBU_INTF_QUOTE_SCHEME' ;
    end if;
end;
/
create table IBU_INTF_QUOTE_SCHEME
(
  scheme_id   VARCHAR2(32) not null,
  arrt_grp_id VARCHAR2(32) not null,
  create_user VARCHAR2(20) not null,
  create_dept VARCHAR2(32),
  create_time TIMESTAMP(6) not null,
  update_user VARCHAR2(20) not null,
  update_time TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table IBU_INTF_QUOTE_SCHEME
  is '银行间上行报价路由方案';
-- Add comments to the columns
comment on column IBU_INTF_QUOTE_SCHEME.scheme_id
  is '方案代码';
comment on column IBU_INTF_QUOTE_SCHEME.arrt_grp_id
  is '属性组';
comment on column IBU_INTF_QUOTE_SCHEME.create_user
  is '创建人';
comment on column IBU_INTF_QUOTE_SCHEME.create_dept
  is '创建部门';
comment on column IBU_INTF_QUOTE_SCHEME.create_time
  is '创建时间';
comment on column IBU_INTF_QUOTE_SCHEME.update_user
  is '更新人';
comment on column IBU_INTF_QUOTE_SCHEME.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_INTF_QUOTE_SCHEME
  add constraint PK_IBU_INTF_QUOTE_SCHEME primary key (SCHEME_ID, ARRT_GRP_ID)
  using index
  ;


  -- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_INTF_SCHEME') ;
    if num > 0 then
        execute immediate 'drop table IBU_INTF_SCHEME' ;
    end if;
end;
/
create table IBU_INTF_SCHEME
(
  scheme_id        VARCHAR2(32) not null,
  scheme_name      VARCHAR2(200),
  send_type        VARCHAR2(50),
  result_wait_type VARCHAR2(50),
  receive_type     VARCHAR2(50),
  isdefault        VARCHAR2(50),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table IBU_INTF_SCHEME
  is '银行间上行路由配置方案';
-- Add comments to the columns
comment on column IBU_INTF_SCHEME.scheme_id
  is '方案代码';
comment on column IBU_INTF_SCHEME.scheme_name
  is '方案名称';
comment on column IBU_INTF_SCHEME.send_type
  is '接口发送方式';
comment on column IBU_INTF_SCHEME.result_wait_type
  is '结果等待方式';
comment on column IBU_INTF_SCHEME.receive_type
  is '接口接收方式';
comment on column IBU_INTF_SCHEME.isdefault
  is '是否默认方案';
comment on column IBU_INTF_SCHEME.create_user
  is '创建人';
comment on column IBU_INTF_SCHEME.create_dept
  is '创建部门';
comment on column IBU_INTF_SCHEME.create_time
  is '创建时间';
comment on column IBU_INTF_SCHEME.update_user
  is '更新人';
comment on column IBU_INTF_SCHEME.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_INTF_SCHEME
  add constraint PK_IBU_INTF_SCHEME primary key (SCHEME_ID)
  using index
  ;



-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_QUOTE_BOND') ;
    if num > 0 then
        execute immediate 'drop table IBU_QUOTE_BOND' ;
    end if;
end;
/
create table IBU_QUOTE_BOND
(
  quote_id           VARCHAR2(32) not null,
  quote_type         VARCHAR2(50),
  quote_role_type    VARCHAR2(50),
  version            NUMBER(10),
  exe_type           VARCHAR2(50),
  p_quote_id         VARCHAR2(32),
  cfets_quote_id     VARCHAR2(32),
  finprod_id         VARCHAR2(50),
  finprod_type2      VARCHAR2(50),
  branch             NUMBER(10),
  portfolio_id       VARCHAR2(32),
  entrust_id         VARCHAR2(32),
  quote_date         DATE,
  settle_date        DATE,
  settle_speed       NUMBER(10),
  delivery_type      VARCHAR2(50),
  sett_type          VARCHAR2(50),
  sett_ccy           VARCHAR2(50),
  ps                 VARCHAR2(50),
  share_amt          NUMBER(30,4),
  fprice_amt         NUMBER(30,2),
  trade_status       VARCHAR2(50),
  send_status        VARCHAR2(50),
  eff_time           TIMESTAMP(6),
  ou_party_id        VARCHAR2(32) not null,
  ou_trader_id       VARCHAR2(32),
  b_version          NUMBER(10),
  remark             VARCHAR2(1000),
  create_user        VARCHAR2(20) not null,
  create_time        TIMESTAMP(6) not null,
  create_dept        VARCHAR2(32),
  update_user        VARCHAR2(20) not null,
  update_time        TIMESTAMP(6) not null,
  my_acct_no         VARCHAR2(32),
  sec_manage_acct_id VARCHAR2(32),
  branch_id          VARCHAR2(50),
  transactime        VARCHAR2(50)
)
;
-- Add comments to the table
comment on table IBU_QUOTE_BOND
  is '现券银行间报价';
-- Add comments to the columns
comment on column IBU_QUOTE_BOND.quote_id
  is '报价编号（内部代码）';
comment on column IBU_QUOTE_BOND.quote_type
  is '报价类型，对话报价/请求报价/质押式回购报价';
comment on column IBU_QUOTE_BOND.quote_role_type
  is '本方角色';
comment on column IBU_QUOTE_BOND.version
  is '版本号';
comment on column IBU_QUOTE_BOND.exe_type
  is '操作类型';
comment on column IBU_QUOTE_BOND.p_quote_id
  is '外部报价编号';
comment on column IBU_QUOTE_BOND.cfets_quote_id
  is 'CFETS报价编号';
comment on column IBU_QUOTE_BOND.finprod_id
  is '金融产品代码';
comment on column IBU_QUOTE_BOND.finprod_type2
  is '金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层';
comment on column IBU_QUOTE_BOND.branch
  is '分支序号';
comment on column IBU_QUOTE_BOND.portfolio_id
  is '组合代码';
comment on column IBU_QUOTE_BOND.entrust_id
  is '委托代码';
comment on column IBU_QUOTE_BOND.quote_date
  is '报价日期';
comment on column IBU_QUOTE_BOND.settle_date
  is '交割日期';
comment on column IBU_QUOTE_BOND.settle_speed
  is '清算速度';
comment on column IBU_QUOTE_BOND.delivery_type
  is '结算方式，券款对付、见券付款、见款付券';
comment on column IBU_QUOTE_BOND.sett_type
  is '清算类型';
comment on column IBU_QUOTE_BOND.sett_ccy
  is '结算币种';
comment on column IBU_QUOTE_BOND.ps
  is '交易方向，买入、卖出、收息、还本、申购、赎回等';
comment on column IBU_QUOTE_BOND.share_amt
  is '交易份额，债券数量等';
comment on column IBU_QUOTE_BOND.fprice_amt
  is '全价总额';
comment on column IBU_QUOTE_BOND.trade_status
  is '报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/';
comment on column IBU_QUOTE_BOND.send_status
  is '发送状态，待发送/成功/失败/发送中';
comment on column IBU_QUOTE_BOND.eff_time
  is '有效时间';
comment on column IBU_QUOTE_BOND.ou_party_id
  is '本方银行间机构，21位编号';
comment on column IBU_QUOTE_BOND.ou_trader_id
  is '本方银行间交易员';
comment on column IBU_QUOTE_BOND.b_version
  is '前置版本号';
comment on column IBU_QUOTE_BOND.remark
  is '备注';
comment on column IBU_QUOTE_BOND.create_user
  is '创建人';
comment on column IBU_QUOTE_BOND.create_time
  is '创建时间';
comment on column IBU_QUOTE_BOND.create_dept
  is '创建部门';
comment on column IBU_QUOTE_BOND.update_user
  is '更新人';
comment on column IBU_QUOTE_BOND.update_time
  is '更新时间';
comment on column IBU_QUOTE_BOND.my_acct_no
  is '本方资金账户';
comment on column IBU_QUOTE_BOND.sec_manage_acct_id
  is '证券管理户代码';
comment on column IBU_QUOTE_BOND.branch_id
  is '序号ID,平安个性化字段';
comment on column IBU_QUOTE_BOND.transactime
  is '业务发生时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_QUOTE_BOND
  add constraint PK_IBU_QUOTE_BOND primary key (QUOTE_ID)
  using index
  ;


-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_QUOTE_BOND_DET') ;
    if num > 0 then
        execute immediate 'drop table IBU_QUOTE_BOND_DET' ;
    end if;
end;
/
create table IBU_QUOTE_BOND_DET
(
  det_id          VARCHAR2(32) not null,
  req_id          VARCHAR2(32),
  version         NUMBER(10),
  cfets_req_id    VARCHAR2(32),
  quote_id        VARCHAR2(32),
  cp_party_id     VARCHAR2(32),
  cp_trader       VARCHAR2(32),
  unit_cprice     NUMBER(30,14),
  unit_int        NUMBER(30,14),
  unit_fprice     NUMBER(30,14),
  par_value       NUMBER(30,14),
  share_amt       NUMBER(30,4),
  prin_amt        NUMBER(30,2),
  par_value_amt   NUMBER(30,2),
  cprice_amt      NUMBER(30,2),
  int_amt         NUMBER(30,2),
  fprice_amt      NUMBER(30,2),
  yield           NUMBER(30,14),
  exer_yield      NUMBER(30,14),
  trade_status    VARCHAR2(50),
  send_status     VARCHAR2(50),
  execid          VARCHAR2(32),
  re_eff_time     TIMESTAMP(6),
  remark          VARCHAR2(1000),
  create_user     VARCHAR2(20) not null,
  create_time     TIMESTAMP(6) not null,
  create_dept     VARCHAR2(32),
  update_user     VARCHAR2(20) not null,
  update_time     TIMESTAMP(6) not null,
  calculate_field VARCHAR2(50),
  transactime     VARCHAR2(50)
)
;
-- Add comments to the table
comment on table IBU_QUOTE_BOND_DET
  is '现券银行间报价明细';
-- Add comments to the columns
comment on column IBU_QUOTE_BOND_DET.det_id
  is '明细编号';
comment on column IBU_QUOTE_BOND_DET.req_id
  is '请求编号(请求报价使用的外部编号)';
comment on column IBU_QUOTE_BOND_DET.version
  is '版本号';
comment on column IBU_QUOTE_BOND_DET.cfets_req_id
  is 'CFETS请求编号';
comment on column IBU_QUOTE_BOND_DET.quote_id
  is '报价编号（内部代码）';
comment on column IBU_QUOTE_BOND_DET.cp_party_id
  is '对手银行间机构，21位编号';
comment on column IBU_QUOTE_BOND_DET.cp_trader
  is '对手方银行间交易员';
comment on column IBU_QUOTE_BOND_DET.unit_cprice
  is '单位净价';
comment on column IBU_QUOTE_BOND_DET.unit_int
  is '单位利息';
comment on column IBU_QUOTE_BOND_DET.unit_fprice
  is '单位全价';
comment on column IBU_QUOTE_BOND_DET.par_value
  is '单位面值，债券100或者当前面值(还本前)，其他资产1';
comment on column IBU_QUOTE_BOND_DET.share_amt
  is '交易份额，债券数量等';
comment on column IBU_QUOTE_BOND_DET.prin_amt
  is '交易本金，交易成本等';
comment on column IBU_QUOTE_BOND_DET.par_value_amt
  is '面值总额';
comment on column IBU_QUOTE_BOND_DET.cprice_amt
  is '净价总额';
comment on column IBU_QUOTE_BOND_DET.int_amt
  is '利息总额';
comment on column IBU_QUOTE_BOND_DET.fprice_amt
  is '全价总额';
comment on column IBU_QUOTE_BOND_DET.yield
  is '到期收益率';
comment on column IBU_QUOTE_BOND_DET.exer_yield
  is '行权收益率';
comment on column IBU_QUOTE_BOND_DET.trade_status
  is '报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/';
comment on column IBU_QUOTE_BOND_DET.send_status
  is '发送状态，待发送/成功/失败/发送中';
comment on column IBU_QUOTE_BOND_DET.execid
  is '成交编号';
comment on column IBU_QUOTE_BOND_DET.re_eff_time
  is '回复有效时间';
comment on column IBU_QUOTE_BOND_DET.remark
  is '备注';
comment on column IBU_QUOTE_BOND_DET.create_user
  is '创建人';
comment on column IBU_QUOTE_BOND_DET.create_time
  is '创建时间';
comment on column IBU_QUOTE_BOND_DET.create_dept
  is '创建部门';
comment on column IBU_QUOTE_BOND_DET.update_user
  is '更新人';
comment on column IBU_QUOTE_BOND_DET.update_time
  is '更新时间';
comment on column IBU_QUOTE_BOND_DET.calculate_field
  is '计算方式，净价/到期收益率/行权收益率';
comment on column IBU_QUOTE_BOND_DET.transactime
  is '业务发生时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_QUOTE_BOND_DET
  add constraint PK_IBU_QUOTE_BOND_DET primary key (DET_ID)
  using index
  ;


-- Create table

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_QUOTE_BOND_DET_HIS') ;
    if num > 0 then
        execute immediate 'drop table IBU_QUOTE_BOND_DET_HIS' ;
    end if;
end;
/
create table IBU_QUOTE_BOND_DET_HIS
(
  det_id          VARCHAR2(32) not null,
  req_id          VARCHAR2(32),
  version         NUMBER(10) not null,
  cfets_req_id    VARCHAR2(32),
  quote_id        VARCHAR2(32),
  cp_party_id     VARCHAR2(32),
  cp_trader       VARCHAR2(32),
  unit_cprice     NUMBER(30,14),
  unit_int        NUMBER(30,14),
  unit_fprice     NUMBER(30,14),
  par_value       NUMBER(30,14),
  share_amt       NUMBER(30,4),
  prin_amt        NUMBER(30,2),
  par_value_amt   NUMBER(30,2),
  cprice_amt      NUMBER(30,2),
  int_amt         NUMBER(30,2),
  fprice_amt      NUMBER(30,2),
  yield           NUMBER(30,14),
  exer_yield      NUMBER(30,14),
  trade_status    VARCHAR2(50),
  send_status     VARCHAR2(50),
  execid          VARCHAR2(32),
  re_eff_time     TIMESTAMP(6),
  remark          VARCHAR2(1000),
  create_user     VARCHAR2(20) not null,
  create_time     TIMESTAMP(6) not null,
  create_dept     VARCHAR2(32),
  update_user     VARCHAR2(20) not null,
  update_time     TIMESTAMP(6) not null,
  calculate_field VARCHAR2(50),
  transactime     VARCHAR2(50)
)
;
-- Add comments to the table
comment on table IBU_QUOTE_BOND_DET_HIS
  is '现券银行间报价明细历史';
-- Add comments to the columns
comment on column IBU_QUOTE_BOND_DET_HIS.det_id
  is '明细编号';
comment on column IBU_QUOTE_BOND_DET_HIS.req_id
  is '请求编号(请求报价使用的外部编号)';
comment on column IBU_QUOTE_BOND_DET_HIS.version
  is '版本号';
comment on column IBU_QUOTE_BOND_DET_HIS.cfets_req_id
  is 'CFETS请求编号';
comment on column IBU_QUOTE_BOND_DET_HIS.quote_id
  is '报价编号（内部代码）';
comment on column IBU_QUOTE_BOND_DET_HIS.cp_party_id
  is '对手银行间机构，21位编号';
comment on column IBU_QUOTE_BOND_DET_HIS.cp_trader
  is '对手方银行间交易员';
comment on column IBU_QUOTE_BOND_DET_HIS.unit_cprice
  is '单位净价';
comment on column IBU_QUOTE_BOND_DET_HIS.unit_int
  is '单位利息';
comment on column IBU_QUOTE_BOND_DET_HIS.unit_fprice
  is '单位全价';
comment on column IBU_QUOTE_BOND_DET_HIS.par_value
  is '单位面值，债券100或者当前面值(还本前)，其他资产1';
comment on column IBU_QUOTE_BOND_DET_HIS.share_amt
  is '交易份额，债券数量等';
comment on column IBU_QUOTE_BOND_DET_HIS.prin_amt
  is '交易本金，交易成本等';
comment on column IBU_QUOTE_BOND_DET_HIS.par_value_amt
  is '面值总额';
comment on column IBU_QUOTE_BOND_DET_HIS.cprice_amt
  is '净价总额';
comment on column IBU_QUOTE_BOND_DET_HIS.int_amt
  is '利息总额';
comment on column IBU_QUOTE_BOND_DET_HIS.fprice_amt
  is '全价总额';
comment on column IBU_QUOTE_BOND_DET_HIS.yield
  is '到期收益率';
comment on column IBU_QUOTE_BOND_DET_HIS.exer_yield
  is '行权收益率';
comment on column IBU_QUOTE_BOND_DET_HIS.trade_status
  is '报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/';
comment on column IBU_QUOTE_BOND_DET_HIS.send_status
  is '发送状态，待发送/成功/失败/发送中';
comment on column IBU_QUOTE_BOND_DET_HIS.execid
  is '成交编号';
comment on column IBU_QUOTE_BOND_DET_HIS.re_eff_time
  is '回复有效时间';
comment on column IBU_QUOTE_BOND_DET_HIS.remark
  is '备注';
comment on column IBU_QUOTE_BOND_DET_HIS.create_user
  is '创建人';
comment on column IBU_QUOTE_BOND_DET_HIS.create_time
  is '创建时间';
comment on column IBU_QUOTE_BOND_DET_HIS.create_dept
  is '创建部门';
comment on column IBU_QUOTE_BOND_DET_HIS.update_user
  is '更新人';
comment on column IBU_QUOTE_BOND_DET_HIS.update_time
  is '更新时间';
comment on column IBU_QUOTE_BOND_DET_HIS.calculate_field
  is '计算方式，净价/到期收益率/行权收益率';
comment on column IBU_QUOTE_BOND_DET_HIS.transactime
  is '业务发生时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_QUOTE_BOND_DET_HIS
  add constraint PK_IBU_QUOTE_BOND_DET_HIS primary key (DET_ID, VERSION)
  using index
  ;



-- Create table

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_QUOTE_BOND_HIS') ;
    if num > 0 then
        execute immediate 'drop table IBU_QUOTE_BOND_HIS' ;
    end if;
end;
/
create table IBU_QUOTE_BOND_HIS
(
  quote_id           VARCHAR2(32) not null,
  quote_type         VARCHAR2(50),
  quote_role_type    VARCHAR2(50),
  version            NUMBER(10) not null,
  exe_type           VARCHAR2(50),
  p_quote_id         VARCHAR2(32),
  cfets_quote_id     VARCHAR2(32),
  finprod_id         VARCHAR2(50),
  finprod_type2      VARCHAR2(50),
  branch             NUMBER(10),
  portfolio_id       VARCHAR2(32),
  entrust_id         VARCHAR2(32),
  quote_date         DATE,
  settle_date        DATE,
  settle_speed       NUMBER(10),
  delivery_type      VARCHAR2(50),
  sett_type          VARCHAR2(50),
  sett_ccy           VARCHAR2(50),
  ps                 VARCHAR2(50),
  share_amt          NUMBER(30,4),
  fprice_amt         NUMBER(30,2),
  trade_status       VARCHAR2(50),
  send_status        VARCHAR2(50),
  eff_time           TIMESTAMP(6),
  ou_party_id        VARCHAR2(32) not null,
  ou_trader_id       VARCHAR2(32),
  b_version          NUMBER(10),
  remark             VARCHAR2(1000),
  create_user        VARCHAR2(20) not null,
  create_time        TIMESTAMP(6) not null,
  create_dept        VARCHAR2(32),
  update_user        VARCHAR2(20) not null,
  update_time        TIMESTAMP(6) not null,
  my_acct_no         VARCHAR2(32),
  sec_manage_acct_id VARCHAR2(32),
  branch_id          VARCHAR2(50),
  transactime        VARCHAR2(50)
)
;
-- Add comments to the table
comment on table IBU_QUOTE_BOND_HIS
  is '现券银行间报价历史';
-- Add comments to the columns
comment on column IBU_QUOTE_BOND_HIS.quote_id
  is '报价编号（内部代码）';
comment on column IBU_QUOTE_BOND_HIS.quote_type
  is '报价类型，对话报价/请求报价/质押式回购报价';
comment on column IBU_QUOTE_BOND_HIS.quote_role_type
  is '本方角色';
comment on column IBU_QUOTE_BOND_HIS.version
  is '版本号';
comment on column IBU_QUOTE_BOND_HIS.exe_type
  is '操作类型';
comment on column IBU_QUOTE_BOND_HIS.p_quote_id
  is '外部报价编号';
comment on column IBU_QUOTE_BOND_HIS.cfets_quote_id
  is 'CFETS报价编号';
comment on column IBU_QUOTE_BOND_HIS.finprod_id
  is '金融产品代码';
comment on column IBU_QUOTE_BOND_HIS.finprod_type2
  is '金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层';
comment on column IBU_QUOTE_BOND_HIS.branch
  is '分支序号';
comment on column IBU_QUOTE_BOND_HIS.portfolio_id
  is '组合代码';
comment on column IBU_QUOTE_BOND_HIS.entrust_id
  is '委托代码';
comment on column IBU_QUOTE_BOND_HIS.quote_date
  is '报价日期';
comment on column IBU_QUOTE_BOND_HIS.settle_date
  is '交割日期';
comment on column IBU_QUOTE_BOND_HIS.settle_speed
  is '清算速度';
comment on column IBU_QUOTE_BOND_HIS.delivery_type
  is '结算方式，券款对付、见券付款、见款付券';
comment on column IBU_QUOTE_BOND_HIS.sett_type
  is '清算类型';
comment on column IBU_QUOTE_BOND_HIS.sett_ccy
  is '结算币种';
comment on column IBU_QUOTE_BOND_HIS.ps
  is '交易方向，买入、卖出、收息、还本、申购、赎回等';
comment on column IBU_QUOTE_BOND_HIS.share_amt
  is '交易份额，债券数量等';
comment on column IBU_QUOTE_BOND_HIS.fprice_amt
  is '全价总额';
comment on column IBU_QUOTE_BOND_HIS.trade_status
  is '报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/';
comment on column IBU_QUOTE_BOND_HIS.send_status
  is '发送状态，待发送/成功/失败/发送中';
comment on column IBU_QUOTE_BOND_HIS.eff_time
  is '有效时间';
comment on column IBU_QUOTE_BOND_HIS.ou_party_id
  is '本方银行间机构，21位编号';
comment on column IBU_QUOTE_BOND_HIS.ou_trader_id
  is '本方银行间交易员';
comment on column IBU_QUOTE_BOND_HIS.b_version
  is '前置版本号';
comment on column IBU_QUOTE_BOND_HIS.remark
  is '备注';
comment on column IBU_QUOTE_BOND_HIS.create_user
  is '创建人';
comment on column IBU_QUOTE_BOND_HIS.create_time
  is '创建时间';
comment on column IBU_QUOTE_BOND_HIS.create_dept
  is '创建部门';
comment on column IBU_QUOTE_BOND_HIS.update_user
  is '更新人';
comment on column IBU_QUOTE_BOND_HIS.update_time
  is '更新时间';
comment on column IBU_QUOTE_BOND_HIS.my_acct_no
  is '本方资金账户';
comment on column IBU_QUOTE_BOND_HIS.sec_manage_acct_id
  is '证券管理户代码';
comment on column IBU_QUOTE_BOND_HIS.branch_id
  is '序号ID，平安个性化字段';
comment on column IBU_QUOTE_BOND_HIS.transactime
  is '业务发生时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_QUOTE_BOND_HIS
  add constraint PK_IBU_QUOTE_BOND_HIS primary key (QUOTE_ID, VERSION)
  using index
  ;


-- Create table

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_QUOTE_CREP') ;
    if num > 0 then
        execute immediate 'drop table IBU_QUOTE_CREP' ;
    end if;
end;
/
create table IBU_QUOTE_CREP
(
  quote_id           VARCHAR2(32) not null,
  quote_type         VARCHAR2(50),
  p_quote_id         VARCHAR2(32),
  version            NUMBER(10),
  exe_type           VARCHAR2(50),
  cfets_quote_id     VARCHAR2(32),
  quote_role_type    VARCHAR2(50),
  portfolio_id       VARCHAR2(32),
  entrust_id         VARCHAR2(32),
  quote_date         DATE,
  v_settle_date      DATE,
  m_settle_date      DATE,
  settle_speed       NUMBER(10),
  v_delivery_type    VARCHAR2(50),
  m_delivery_type    VARCHAR2(50),
  sett_type          VARCHAR2(50),
  sett_ccy           VARCHAR2(50),
  ccy                VARCHAR2(50),
  repo_ps            VARCHAR2(50),
  rate               NUMBER(30,14),
  term               NUMBER(10),
  share_amt          NUMBER(30,4),
  share_ct_amt       NUMBER(30,2),
  cb_share_ct_amt    NUMBER(30,2),
  sc_share_ct_amt    NUMBER(30,2),
  fprice_amt         NUMBER(30,2),
  cb_fprice_amt      NUMBER(30,2),
  sc_fprice_amt      NUMBER(30,2),
  m_int_amt          NUMBER(30,2),
  m_trade_amt        NUMBER(30,2),
  basis              VARCHAR2(50),
  trade_status       VARCHAR2(50),
  send_status        VARCHAR2(50),
  eff_time           TIMESTAMP(6),
  ou_party_id        VARCHAR2(32) not null,
  ou_trader_id       VARCHAR2(32),
  cp_party_id        VARCHAR2(32),
  cp_trader_id       VARCHAR2(32),
  my_acct_no         VARCHAR2(32),
  sec_manage_acct_id VARCHAR2(32),
  execid             VARCHAR2(32),
  b_version          NUMBER(10),
  remark             VARCHAR2(1000),
  create_user        VARCHAR2(20) not null,
  create_time        TIMESTAMP(6) not null,
  create_dept        VARCHAR2(32),
  update_user        VARCHAR2(20) not null,
  update_time        TIMESTAMP(6) not null,
  branch_id          VARCHAR2(50),
  term_type          VARCHAR2(50),
  transactime        VARCHAR2(50)
)
;
-- Add comments to the table
comment on table IBU_QUOTE_CREP
  is '质押式回购银行间报价';
-- Add comments to the columns
comment on column IBU_QUOTE_CREP.quote_id
  is '报价编号（内部代码）';
comment on column IBU_QUOTE_CREP.quote_type
  is '报价类型，对话报价/请求报价';
comment on column IBU_QUOTE_CREP.p_quote_id
  is '外部报价编号';
comment on column IBU_QUOTE_CREP.version
  is '版本号';
comment on column IBU_QUOTE_CREP.exe_type
  is '操作类型';
comment on column IBU_QUOTE_CREP.cfets_quote_id
  is 'CFETS报价编号';
comment on column IBU_QUOTE_CREP.quote_role_type
  is '本方角色';
comment on column IBU_QUOTE_CREP.portfolio_id
  is '组合代码';
comment on column IBU_QUOTE_CREP.entrust_id
  is '委托代码';
comment on column IBU_QUOTE_CREP.quote_date
  is '报价日期';
comment on column IBU_QUOTE_CREP.v_settle_date
  is '首期结算日';
comment on column IBU_QUOTE_CREP.m_settle_date
  is '到期结算日';
comment on column IBU_QUOTE_CREP.settle_speed
  is '清算速度';
comment on column IBU_QUOTE_CREP.v_delivery_type
  is '首期结算方式，券款对付、见券付款、见款付券';
comment on column IBU_QUOTE_CREP.m_delivery_type
  is '到期结算方式，券款对付、见券付款、见款付券';
comment on column IBU_QUOTE_CREP.sett_type
  is '清算类型';
comment on column IBU_QUOTE_CREP.sett_ccy
  is '结算币种';
comment on column IBU_QUOTE_CREP.ccy
  is '债券币种';
comment on column IBU_QUOTE_CREP.repo_ps
  is '回购方向';
comment on column IBU_QUOTE_CREP.rate
  is '回购利率 单位：%，精度：4位';
comment on column IBU_QUOTE_CREP.term
  is '期限(天)';
comment on column IBU_QUOTE_CREP.share_amt
  is '交易份额，债券数量等';
comment on column IBU_QUOTE_CREP.share_ct_amt
  is '折算券面金额';
comment on column IBU_QUOTE_CREP.cb_share_ct_amt
  is '中债折算券面金额';
comment on column IBU_QUOTE_CREP.sc_share_ct_amt
  is '上清折算券面金额';
comment on column IBU_QUOTE_CREP.fprice_amt
  is '全价总额';
comment on column IBU_QUOTE_CREP.cb_fprice_amt
  is '中债全价总额';
comment on column IBU_QUOTE_CREP.sc_fprice_amt
  is '上清全价总额';
comment on column IBU_QUOTE_CREP.m_int_amt
  is '到期利息';
comment on column IBU_QUOTE_CREP.m_trade_amt
  is '到期金额';
comment on column IBU_QUOTE_CREP.basis
  is '计息基础';
comment on column IBU_QUOTE_CREP.trade_status
  is '报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/';
comment on column IBU_QUOTE_CREP.send_status
  is '发送状态，待发送/成功/失败/发送中';
comment on column IBU_QUOTE_CREP.eff_time
  is '有效时间';
comment on column IBU_QUOTE_CREP.ou_party_id
  is '本方银行间机构，21位编号';
comment on column IBU_QUOTE_CREP.ou_trader_id
  is '本方银行间交易员';
comment on column IBU_QUOTE_CREP.cp_party_id
  is '对手银行间机构，21位编号';
comment on column IBU_QUOTE_CREP.cp_trader_id
  is '对手方银行间交易员';
comment on column IBU_QUOTE_CREP.my_acct_no
  is '本方资金账户';
comment on column IBU_QUOTE_CREP.sec_manage_acct_id
  is '证券管理户代码';
comment on column IBU_QUOTE_CREP.execid
  is '成交编号';
comment on column IBU_QUOTE_CREP.b_version
  is '前置版本号';
comment on column IBU_QUOTE_CREP.remark
  is '备注';
comment on column IBU_QUOTE_CREP.create_user
  is '创建人';
comment on column IBU_QUOTE_CREP.create_time
  is '创建时间';
comment on column IBU_QUOTE_CREP.create_dept
  is '创建部门';
comment on column IBU_QUOTE_CREP.update_user
  is '更新人';
comment on column IBU_QUOTE_CREP.update_time
  is '更新时间';
comment on column IBU_QUOTE_CREP.branch_id
  is '序号ID，平安个性化字段';
comment on column IBU_QUOTE_CREP.term_type
  is '期限品种';
comment on column IBU_QUOTE_CREP.transactime
  is '业务发生时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_QUOTE_CREP
  add constraint PK_IBU_QUOTE_CREP primary key (QUOTE_ID)
  using index
  ;

  -- Create table

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_QUOTE_CREP_HIS') ;
    if num > 0 then
        execute immediate 'drop table IBU_QUOTE_CREP_HIS' ;
    end if;
end;
/
create table IBU_QUOTE_CREP_HIS
(
  quote_id           VARCHAR2(32) not null,
  quote_type         VARCHAR2(50),
  p_quote_id         VARCHAR2(32),
  version            NUMBER(10) not null,
  exe_type           VARCHAR2(50),
  cfets_quote_id     VARCHAR2(32),
  quote_role_type    VARCHAR2(50),
  portfolio_id       VARCHAR2(32),
  entrust_id         VARCHAR2(32),
  quote_date         DATE,
  v_settle_date      DATE,
  m_settle_date      DATE,
  settle_speed       NUMBER(10),
  v_delivery_type    VARCHAR2(50),
  m_delivery_type    VARCHAR2(50),
  sett_type          VARCHAR2(50),
  sett_ccy           VARCHAR2(50),
  ccy                VARCHAR2(50),
  repo_ps            VARCHAR2(50),
  rate               NUMBER(30,14),
  term               NUMBER(10),
  share_amt          NUMBER(30,4),
  share_ct_amt       NUMBER(30,2),
  cb_share_ct_amt    NUMBER(30,2),
  sc_share_ct_amt    NUMBER(30,2),
  fprice_amt         NUMBER(30,2),
  cb_fprice_amt      NUMBER(30,2),
  sc_fprice_amt      NUMBER(30,2),
  m_int_amt          NUMBER(30,2),
  m_trade_amt        NUMBER(30,2),
  basis              VARCHAR2(50),
  trade_status       VARCHAR2(50),
  send_status        VARCHAR2(50),
  eff_time           TIMESTAMP(6),
  ou_party_id        VARCHAR2(32) not null,
  ou_trader_id       VARCHAR2(32),
  cp_party_id        VARCHAR2(32),
  cp_trader_id       VARCHAR2(32),
  my_acct_no         VARCHAR2(32),
  sec_manage_acct_id VARCHAR2(32),
  execid             VARCHAR2(32),
  b_version          NUMBER(10),
  remark             VARCHAR2(1000),
  create_user        VARCHAR2(20) not null,
  create_time        TIMESTAMP(6) not null,
  create_dept        VARCHAR2(32),
  update_user        VARCHAR2(20) not null,
  update_time        TIMESTAMP(6) not null,
  branch_id          VARCHAR2(50),
  term_type          VARCHAR2(50),
  transactime        VARCHAR2(50)
)
;
-- Add comments to the table
comment on table IBU_QUOTE_CREP_HIS
  is '质押式回购银行间报价历史';
-- Add comments to the columns
comment on column IBU_QUOTE_CREP_HIS.quote_id
  is '报价编号（内部代码）';
comment on column IBU_QUOTE_CREP_HIS.quote_type
  is '报价类型，对话报价/请求报价';
comment on column IBU_QUOTE_CREP_HIS.p_quote_id
  is '外部报价编号';
comment on column IBU_QUOTE_CREP_HIS.version
  is '版本号';
comment on column IBU_QUOTE_CREP_HIS.exe_type
  is '操作类型';
comment on column IBU_QUOTE_CREP_HIS.cfets_quote_id
  is 'CFETS报价编号';
comment on column IBU_QUOTE_CREP_HIS.quote_role_type
  is '本方角色';
comment on column IBU_QUOTE_CREP_HIS.portfolio_id
  is '组合代码';
comment on column IBU_QUOTE_CREP_HIS.entrust_id
  is '委托代码';
comment on column IBU_QUOTE_CREP_HIS.quote_date
  is '报价日期';
comment on column IBU_QUOTE_CREP_HIS.v_settle_date
  is '首期结算日';
comment on column IBU_QUOTE_CREP_HIS.m_settle_date
  is '到期结算日';
comment on column IBU_QUOTE_CREP_HIS.settle_speed
  is '清算速度';
comment on column IBU_QUOTE_CREP_HIS.v_delivery_type
  is '首期结算方式，券款对付、见券付款、见款付券';
comment on column IBU_QUOTE_CREP_HIS.m_delivery_type
  is '到期结算方式，券款对付、见券付款、见款付券';
comment on column IBU_QUOTE_CREP_HIS.sett_type
  is '清算类型';
comment on column IBU_QUOTE_CREP_HIS.sett_ccy
  is '结算币种';
comment on column IBU_QUOTE_CREP_HIS.ccy
  is '债券币种';
comment on column IBU_QUOTE_CREP_HIS.repo_ps
  is '回购方向';
comment on column IBU_QUOTE_CREP_HIS.rate
  is '回购利率 单位：%，精度：4位';
comment on column IBU_QUOTE_CREP_HIS.term
  is '期限(天)';
comment on column IBU_QUOTE_CREP_HIS.share_amt
  is '交易份额，债券数量等';
comment on column IBU_QUOTE_CREP_HIS.share_ct_amt
  is '折算券面金额';
comment on column IBU_QUOTE_CREP_HIS.cb_share_ct_amt
  is '中债折算券面金额';
comment on column IBU_QUOTE_CREP_HIS.sc_share_ct_amt
  is '上清折算券面金额';
comment on column IBU_QUOTE_CREP_HIS.fprice_amt
  is '全价总额';
comment on column IBU_QUOTE_CREP_HIS.cb_fprice_amt
  is '中债全价总额';
comment on column IBU_QUOTE_CREP_HIS.sc_fprice_amt
  is '上清全价总额';
comment on column IBU_QUOTE_CREP_HIS.m_int_amt
  is '到期利息';
comment on column IBU_QUOTE_CREP_HIS.m_trade_amt
  is '到期金额';
comment on column IBU_QUOTE_CREP_HIS.basis
  is '计息基础';
comment on column IBU_QUOTE_CREP_HIS.trade_status
  is '报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/';
comment on column IBU_QUOTE_CREP_HIS.send_status
  is '发送状态，待发送/成功/失败/发送中';
comment on column IBU_QUOTE_CREP_HIS.eff_time
  is '有效时间';
comment on column IBU_QUOTE_CREP_HIS.ou_party_id
  is '本方银行间机构，21位编号';
comment on column IBU_QUOTE_CREP_HIS.ou_trader_id
  is '本方银行间交易员';
comment on column IBU_QUOTE_CREP_HIS.cp_party_id
  is '对手银行间机构，21位编号';
comment on column IBU_QUOTE_CREP_HIS.cp_trader_id
  is '对手方银行间交易员';
comment on column IBU_QUOTE_CREP_HIS.my_acct_no
  is '本方资金账户';
comment on column IBU_QUOTE_CREP_HIS.sec_manage_acct_id
  is '证券管理户代码';
comment on column IBU_QUOTE_CREP_HIS.execid
  is '成交编号';
comment on column IBU_QUOTE_CREP_HIS.b_version
  is '前置版本号';
comment on column IBU_QUOTE_CREP_HIS.remark
  is '备注';
comment on column IBU_QUOTE_CREP_HIS.create_user
  is '创建人';
comment on column IBU_QUOTE_CREP_HIS.create_time
  is '创建时间';
comment on column IBU_QUOTE_CREP_HIS.create_dept
  is '创建部门';
comment on column IBU_QUOTE_CREP_HIS.update_user
  is '更新人';
comment on column IBU_QUOTE_CREP_HIS.update_time
  is '更新时间';
comment on column IBU_QUOTE_CREP_HIS.branch_id
  is '序号ID，平安个性化字段';
comment on column IBU_QUOTE_CREP_HIS.term_type
  is '期限品种';
comment on column IBU_QUOTE_CREP_HIS.transactime
  is '业务发生时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_QUOTE_CREP_HIS
  add constraint PK_IBU_QUOTE_CREP_HIS primary key (QUOTE_ID, VERSION)
  using index
  ;


-- Create table

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_QUOTE_CREP_PLEDGE') ;
    if num > 0 then
        execute immediate 'drop table IBU_QUOTE_CREP_PLEDGE' ;
    end if;
end;
/
create table IBU_QUOTE_CREP_PLEDGE
(
  quote_id          VARCHAR2(32) not null,
  version           NUMBER(10),
  pledge_finprod_id VARCHAR2(50) not null,
  branch            NUMBER(10) not null,
  pledge_face_value NUMBER(30,2),
  pledge_number     NUMBER(30,2),
  pledge_ratio      NUMBER(30,14),
  pledge_amt        NUMBER(30,2),
  unit_cprice       NUMBER(30,14),
  cprice_date       DATE,
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table IBU_QUOTE_CREP_PLEDGE
  is '回购报价质押品';
-- Add comments to the columns
comment on column IBU_QUOTE_CREP_PLEDGE.quote_id
  is '报价编号（内部代码）';
comment on column IBU_QUOTE_CREP_PLEDGE.version
  is '版本号';
comment on column IBU_QUOTE_CREP_PLEDGE.pledge_finprod_id
  is '质押金融产品代码';
comment on column IBU_QUOTE_CREP_PLEDGE.branch
  is '分支序号';
comment on column IBU_QUOTE_CREP_PLEDGE.pledge_face_value
  is '质押面值';
comment on column IBU_QUOTE_CREP_PLEDGE.pledge_number
  is '质押数量';
comment on column IBU_QUOTE_CREP_PLEDGE.pledge_ratio
  is '质押率';
comment on column IBU_QUOTE_CREP_PLEDGE.pledge_amt
  is '质押金额';
comment on column IBU_QUOTE_CREP_PLEDGE.unit_cprice
  is '单位净价';
comment on column IBU_QUOTE_CREP_PLEDGE.cprice_date
  is '净值日期';
comment on column IBU_QUOTE_CREP_PLEDGE.create_user
  is '创建人';
comment on column IBU_QUOTE_CREP_PLEDGE.create_dept
  is '创建部门';
comment on column IBU_QUOTE_CREP_PLEDGE.create_time
  is '创建时间';
comment on column IBU_QUOTE_CREP_PLEDGE.update_user
  is '更新人';
comment on column IBU_QUOTE_CREP_PLEDGE.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_QUOTE_CREP_PLEDGE
  add constraint PK_IBU_QUOTE_CREP_PLEDGE primary key (QUOTE_ID, BRANCH, PLEDGE_FINPROD_ID)
  using index
  ;


 -- Create table
 declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_QUOTE_CREP_PLEDGE_HIS') ;
    if num > 0 then
        execute immediate 'drop table IBU_QUOTE_CREP_PLEDGE_HIS' ;
    end if;
end;
/
create table IBU_QUOTE_CREP_PLEDGE_HIS
(
  quote_id          VARCHAR2(32) not null,
  version           NUMBER(10) not null,
  pledge_finprod_id VARCHAR2(50) not null,
  branch            NUMBER(10) not null,
  pledge_face_value NUMBER(30,2),
  pledge_number     NUMBER(30,2),
  pledge_ratio      NUMBER(30,14),
  pledge_amt        NUMBER(30,2),
  unit_cprice       NUMBER(30,14),
  cprice_date       DATE,
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table IBU_QUOTE_CREP_PLEDGE_HIS
  is '回购报价质押品历史';
-- Add comments to the columns
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.quote_id
  is '报价编号（内部代码）';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.version
  is '版本号';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.pledge_finprod_id
  is '质押金融产品代码';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.branch
  is '分支序号';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.pledge_face_value
  is '质押面值';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.pledge_number
  is '质押数量';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.pledge_ratio
  is '质押率';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.pledge_amt
  is '质押金额';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.unit_cprice
  is '单位净价';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.cprice_date
  is '净值日期';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.create_user
  is '创建人';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.create_dept
  is '创建部门';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.create_time
  is '创建时间';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.update_user
  is '更新人';
comment on column IBU_QUOTE_CREP_PLEDGE_HIS.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_QUOTE_CREP_PLEDGE_HIS
  add constraint PK_IBU_QUOTE_CREP_PLEDGE_HIS primary key (QUOTE_ID, VERSION, BRANCH, PLEDGE_FINPROD_ID)
  using index
  ;

  -- Create table

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_QUOTE_ERRLOG') ;
    if num > 0 then
        execute immediate 'drop table IBU_QUOTE_ERRLOG' ;
    end if;
end;
/
create table IBU_QUOTE_ERRLOG
(
  quote_id    VARCHAR2(32) not null,
  version     NUMBER(10) not null,
  err_code    VARCHAR2(50),
  err_msg     VARCHAR2(1000),
  create_user VARCHAR2(20) not null,
  create_dept VARCHAR2(32),
  create_time TIMESTAMP(6) not null,
  update_user VARCHAR2(20) not null,
  update_time TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table IBU_QUOTE_ERRLOG
  is '报价错误日志表';
-- Add comments to the columns
comment on column IBU_QUOTE_ERRLOG.quote_id
  is '报价编号（内部代码）';
comment on column IBU_QUOTE_ERRLOG.version
  is '版本号(内部流水号)';
comment on column IBU_QUOTE_ERRLOG.err_code
  is '错误码';
comment on column IBU_QUOTE_ERRLOG.err_msg
  is '错误信息';
comment on column IBU_QUOTE_ERRLOG.create_user
  is '创建人';
comment on column IBU_QUOTE_ERRLOG.create_dept
  is '创建部门';
comment on column IBU_QUOTE_ERRLOG.create_time
  is '创建时间';
comment on column IBU_QUOTE_ERRLOG.update_user
  is '更新人';
comment on column IBU_QUOTE_ERRLOG.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_QUOTE_ERRLOG
  add constraint PK_IBU_QUOTE_ERRLOG primary key (QUOTE_ID, VERSION)
  using index
;

-- Create table

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('MST_CFETS_CUSTOMER_RELA') ;
    if num > 0 then
        execute immediate 'drop table MST_CFETS_CUSTOMER_RELA' ;
    end if;
end;
/
create table MST_CFETS_CUSTOMER_RELA
(
  rela_id     VARCHAR2(32) not null,
  mparty_id   VARCHAR2(32),
  sparty_id   VARCHAR2(32),
  create_user VARCHAR2(20) not null,
  create_time TIMESTAMP(6) not null,
  create_dept VARCHAR2(32),
  update_user VARCHAR2(20) not null,
  update_time TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table MST_CFETS_CUSTOMER_RELA
  is '客户信息_银行机构关系';
-- Add comments to the columns
comment on column MST_CFETS_CUSTOMER_RELA.rela_id
  is '关系代码';
comment on column MST_CFETS_CUSTOMER_RELA.mparty_id
  is '主交易成员ID，21位编号';
comment on column MST_CFETS_CUSTOMER_RELA.sparty_id
  is '从交易成员ID，21位编号';
comment on column MST_CFETS_CUSTOMER_RELA.create_user
  is '创建人';
comment on column MST_CFETS_CUSTOMER_RELA.create_time
  is '创建时间';
comment on column MST_CFETS_CUSTOMER_RELA.create_dept
  is '创建部门';
comment on column MST_CFETS_CUSTOMER_RELA.update_user
  is '更新人';
comment on column MST_CFETS_CUSTOMER_RELA.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table MST_CFETS_CUSTOMER_RELA
  add constraint PK_MST_CFETS_CUSTOMER_RELA primary key (RELA_ID)
  using index
  ;


-- Create table

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('IBU_INTF_ATTR_CONFIG') ;
    if num > 0 then
        execute immediate 'drop table IBU_INTF_ATTR_CONFIG' ;
    end if;
end;
/
create table IBU_INTF_ATTR_CONFIG
(
  arrt_grp_id VARCHAR2(32) not null,
  attr_id     VARCHAR2(50) not null,
  attr_value  VARCHAR2(50),
  attr_type   VARCHAR2(50),
  create_user VARCHAR2(20) not null,
  create_dept VARCHAR2(32),
  create_time TIMESTAMP(6) not null,
  update_user VARCHAR2(20) not null,
  update_time TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table IBU_INTF_ATTR_CONFIG
  is '银行间上行报价属性配置';
-- Add comments to the columns
comment on column IBU_INTF_ATTR_CONFIG.arrt_grp_id
  is '属性组';
comment on column IBU_INTF_ATTR_CONFIG.attr_id
  is '属性ID';
comment on column IBU_INTF_ATTR_CONFIG.attr_value
  is '属性值';
comment on column IBU_INTF_ATTR_CONFIG.attr_type
  is '属性类型';
comment on column IBU_INTF_ATTR_CONFIG.create_user
  is '创建人';
comment on column IBU_INTF_ATTR_CONFIG.create_dept
  is '创建部门';
comment on column IBU_INTF_ATTR_CONFIG.create_time
  is '创建时间';
comment on column IBU_INTF_ATTR_CONFIG.update_user
  is '更新人';
comment on column IBU_INTF_ATTR_CONFIG.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table IBU_INTF_ATTR_CONFIG
  add constraint PK_IBU_INTF_ATTR_CONFIG primary key (ARRT_GRP_ID, ATTR_ID)
  using index
  ;

-----------------------------------------------------------------------银行间下行成交接口脚本---------------------------------------------------
--银行间下行成交接口序列
DROP SEQUENCE CSTP_SEQNO;
create sequence CSTP_SEQNO
		minvalue 1
		maxvalue 9999999999999999999999999
		start with 51
		increment by 1
		nocache;


--银行间成交数据是否自动生成交易数据--
delete from SYS_PARA   WHERE PARA_CODE = 'FLAG_AUTOTRADE';
insert into SYS_PARA values ('SYS','FLAG_AUTOTRADE','02','01','','下行匹配自动录单开关 01:打开 02:关闭','system','',sysdate, 'admin',sysdate,'','01');

--------------------------------------------------------------------画面pms-------------------------------------------------------------------------

--编号生成规则
INSERT INTO MST_BUSINESS_ID_RULE (SEQ_NO, COMMON_BUSINESS_TYPE, TYPE_1, TYPE_2, TYPE_3, TYPE_4, TYPE_5, TYPE_6, BUSINESS_ID_RULE_TYPE, PREFIX_NAME, PREFIX_SEQUENCE_NAME, PREFIX_SEQUENCE_LEN, POSTFIX_NAME, POSTFIX_SEQUENCE_NAME, POSTFIX_SEQUENCE_LEN, DATE_RULE_TYPE, BUSINESS_ID_SERVICE_NAME, BUSINESS_ID_METHOD_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('IBU_001', null, null, null, null, null, null, null, '04', 'DHBOND', null, null, null, 'DHBOND_SEQUENCE', 4, '01', null, null, 'system', null, sysdate, 'system', sysdate);
INSERT INTO MST_BUSINESS_ID_RULE (SEQ_NO, COMMON_BUSINESS_TYPE, TYPE_1, TYPE_2, TYPE_3, TYPE_4, TYPE_5, TYPE_6, BUSINESS_ID_RULE_TYPE, PREFIX_NAME, PREFIX_SEQUENCE_NAME, PREFIX_SEQUENCE_LEN, POSTFIX_NAME, POSTFIX_SEQUENCE_NAME, POSTFIX_SEQUENCE_LEN, DATE_RULE_TYPE, BUSINESS_ID_SERVICE_NAME, BUSINESS_ID_METHOD_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('IBU_002', null, null, null, null, null, null, null, '04', 'QQBOND', null, null, null, 'QQBOND_SEQUENCE', 4, '01', null, null, 'system', null, sysdate, 'system', sysdate);
INSERT INTO MST_BUSINESS_ID_RULE (SEQ_NO, COMMON_BUSINESS_TYPE, TYPE_1, TYPE_2, TYPE_3, TYPE_4, TYPE_5, TYPE_6, BUSINESS_ID_RULE_TYPE, PREFIX_NAME, PREFIX_SEQUENCE_NAME, PREFIX_SEQUENCE_LEN, POSTFIX_NAME, POSTFIX_SEQUENCE_NAME, POSTFIX_SEQUENCE_LEN, DATE_RULE_TYPE, BUSINESS_ID_SERVICE_NAME, BUSINESS_ID_METHOD_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('IBU_003', null, null, null, null, null, null, null, '04', 'DHREPO', null, null, null, 'DHREPO_SEQUENCE', 4, '01', null, null, 'system', null, sysdate, 'system', sysdate);
CREATE SEQUENCE DHBOND_SEQUENCE INCREMENT BY 1 START WITH 1 MAXVALUE 9999999999999999999999999999 MINVALUE 1 NOCYCLE CACHE 20 NOORDER;
CREATE SEQUENCE QQBOND_SEQUENCE INCREMENT BY 1 START WITH 1 MAXVALUE 9999999999999999999999999999 MINVALUE 1 NOCYCLE CACHE 20 NOORDER;
CREATE SEQUENCE DHREPO_SEQUENCE INCREMENT BY 1 START WITH 1 MAXVALUE 9999999999999999999999999999 MINVALUE 1 NOCYCLE CACHE 20 NOORDER;

--报价方式
ALTER TABLE ETR_ENTRUST_ADD ADD QUOTE_TYPE VARCHAR(32);
COMMENT ON COLUMN ETR_ENTRUST_ADD.QUOTE_TYPE IS '报价方式';

 --债券质押冻结
-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('FIN_FREEZE_PLEDGE') ;
    if num > 0 then
        execute immediate 'drop table FIN_FREEZE_PLEDGE' ;
    end if;
end;
/
create table FIN_FREEZE_PLEDGE
(
  portfolio_id VARCHAR2(32) not null,
  finprod_id   VARCHAR2(32) not null,
  acct_no      VARCHAR2(32) not null,
  cdate        DATE not null,
  create_dept  VARCHAR2(32),
  create_user  VARCHAR2(20) not null,
  create_time  TIMESTAMP(6) not null,
  update_user  VARCHAR2(20) not null,
  update_time  TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table FIN_FREEZE_PLEDGE
  is '债券质押冻结';
-- Add comments to the columns
comment on column FIN_FREEZE_PLEDGE.portfolio_id
  is '组合代码';
comment on column FIN_FREEZE_PLEDGE.finprod_id
  is '债券代码';
comment on column FIN_FREEZE_PLEDGE.acct_no
  is '证券账户';
comment on column FIN_FREEZE_PLEDGE.cdate
  is '冻结日期';
comment on column FIN_FREEZE_PLEDGE.create_dept
  is '创建部门';
comment on column FIN_FREEZE_PLEDGE.create_user
  is '创建人';
comment on column FIN_FREEZE_PLEDGE.create_time
  is '创建时间';
comment on column FIN_FREEZE_PLEDGE.update_user
  is '更新人';
comment on column FIN_FREEZE_PLEDGE.update_time
  is '更新时间';



  -- Create table
  --报价完成状况表
  declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('ETR_QUOTE_FINISH_STATUS') ;
    if num > 0 then
        execute immediate 'drop table ETR_QUOTE_FINISH_STATUS' ;
    end if;
end;
/
create table ETR_QUOTE_FINISH_STATUS
(
  entrust_id  VARCHAR2(32) not null,
  quote_id    VARCHAR2(32) not null,
  finish_date DATE,
  finish_amt  NUMBER(30,4),
  trader      VARCHAR2(20),
  create_user VARCHAR2(20) not null,
  create_dept VARCHAR2(32),
  create_time TIMESTAMP(6) not null,
  update_user VARCHAR2(20) not null,
  update_time TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table ETR_QUOTE_FINISH_STATUS
  is '报价完成状况表';
-- Add comments to the columns
comment on column ETR_QUOTE_FINISH_STATUS.entrust_id
  is '委托代码';
comment on column ETR_QUOTE_FINISH_STATUS.quote_id
  is '报价编号';
comment on column ETR_QUOTE_FINISH_STATUS.finish_date
  is '完成日期';
comment on column ETR_QUOTE_FINISH_STATUS.finish_amt
  is '完成额度';
comment on column ETR_QUOTE_FINISH_STATUS.trader
  is '交易员';
comment on column ETR_QUOTE_FINISH_STATUS.create_user
  is '创建人';
comment on column ETR_QUOTE_FINISH_STATUS.create_dept
  is '创建部门';
comment on column ETR_QUOTE_FINISH_STATUS.create_time
  is '创建时间';
comment on column ETR_QUOTE_FINISH_STATUS.update_user
  is '更新人';
comment on column ETR_QUOTE_FINISH_STATUS.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table ETR_QUOTE_FINISH_STATUS
  add constraint PK_ETR_QUOTE_FINISH_STATUS primary key (ENTRUST_ID, QUOTE_ID)
  using index
  ;

-------------------------------------------------------------------------------画面vas----------------------------------------------------------------------
--菜单
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INVEST_MANAGEMENT_001_099', 'CFETS', null, 'INVEST_MANAGEMENT_001', null, 99, '02', '对话报价', 'system', 'system', sysDate, 'system', sysDate);
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('INVEST_MANAGEMENT_001_099_004', 'CFETS', null, 'INVEST_MANAGEMENT_001_099', 'CfetsBondDealQuotedPrice001', 4, '02', 'CFETS现券买卖报价', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('INVEST_MANAGEMENT_001_099_005', 'CFETS', null, 'INVEST_MANAGEMENT_001_099', 'CfetsRepoDealQuotedPrice001', 5, '02', 'CFETS质押式回购报价', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('INVEST_MANAGEMENT_001_099_006', 'CFETS', null, 'INVEST_MANAGEMENT_001_099', 'CfetsHisDealQuery001', 6, '02', 'CFETS历史报价查询', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BASICDATA_MANAGEMENT_001_001_020', 'MST', null, 'BASICDATA_MANAGEMENT_001_001', 'CfetsPortfolioAcctRela001', 20, '02', 'CFETS账户关联', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BASICDATA_MANAGEMENT_001_001_021', 'MST', null, 'BASICDATA_MANAGEMENT_001_001', 'CfetsCustomerTrader001', 21, '02', '银行间交易员信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BASICDATA_MANAGEMENT_001_001_022', 'MST', null, 'BASICDATA_MANAGEMENT_001_001', 'CfetsCustomerInfo001', 22, '02', '银行间机构信息', 'system', null, sysdate, 'system', sysdate);
--INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BASICDATA_MANAGEMENT_001_001_023', 'MST', null, 'BASICDATA_MANAGEMENT_001_001', 'ErrorCodeInfoCompare001','23','02','错误码信息查询','system',null,sysDate,'system',sysDate);

--画面
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsBondDealQuotedPrice001', 'CFETS', '/invest/bankuplink/bond/CfetsBondDealQuotedPrice001', 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001', 'INVEST_MANAGEMENT_001_099_004', null, null, '02', '01', 'CFETS现券买卖报价', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsBondDealQuotedPrice002', 'CFETS', '/invest/bankuplink/bond/CfetsBondDealQuotedPrice002', 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002', null, null, null, '02', '01', 'CFETS现券买卖对话报价', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsBondDealQuotedPrice003', 'CFETS', '/invest/bankuplink/bond/CfetsBondDealQuotedPrice003', 'invest/bankuplink/bond/CfetsBondDealQuotedPrice003', null, null, null, '02', '01', 'CFETS现券买卖请求报价', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsBondDealQuotedPrice006', 'CFETS', '/invest/bankuplink/bond/CfetsBondDealQuotedPrice006', 'invest/bankuplink/bond/CfetsBondDealQuotedPrice006', null, null, null, '02', '01', 'CFETS现券买卖委托选择页面', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsRepoDealQuotedPrice001', 'CFETS', '/invest/bankuplink/repo/CfetsRepoDealQuotedPrice001', 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice001', 'INVEST_MANAGEMENT_001_099_005', null, null, '02', '01', 'CFETS质押式回购报价', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsRepoDealQuotedPrice002', 'CFETS', '/invest/bankuplink/repo/CfetsRepoDealQuotedPrice002', 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002', null, null, null, '02', '01', 'CFETS质押式回购报价', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsRepoDealQuotedPrice003', 'CFETS', '/invest/bankuplink/repo/CfetsRepoDealQuotedPrice003', 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice003', null, null, null, '02', '01', 'CFETS质押式回购报价', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsRepoDealQuotedPrice004', 'CFETS', '/invest/bankuplink/repo/CfetsRepoDealQuotedPrice004', 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice004', null, null, null, '02', '01', 'CFETS质押式回购报价', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsRepoDealQuotedPrice005', 'CFETS', '/invest/bankuplink/repo/CfetsRepoDealQuotedPrice005', 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice005', null, null, null, '02', '01', 'CFETS质押式回购报价', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsHisDealQuery001', 'CFETS', '/invest/bankuplink/basic/cfetshisdealquery/CfetsHisDealQuery001', 'invest/bankuplink/basic/cfetshisdealquery/CfetsHisDealQuery001', 'INVEST_MANAGEMENT_001_099_006', null, null, '02', '01', 'CFETS历史报价查询', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsPortfolioAcctRela001', 'MST', '/invest/bankuplink/basic/cfetsportfolioacctrela/CfetsPortfolioAcctRela001', 'invest/bankuplink/basic/cfetsportfolioacctrela/CfetsPortfolioAcctRela001', 'BASICDATA_MANAGEMENT_001_001_020', null, null, '02', '01', 'CFETS账户关联', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsPortfolioAcctRela002', 'MST', '/invest/bankuplink/basic/cfetsportfolioacctrela/CfetsPortfolioAcctRela002', 'invest/bankuplink/basic/cfetsportfolioacctrela/CfetsPortfolioAcctRela002', null, null, null, '02', '01', 'CFETS账户关联', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsCustomerTrader001', 'MST', '/invest/bankuplink/basic/cfetscustomertrader/CfetsCustomerTrader001', 'invest/bankuplink/basic/cfetscustomertrader/CfetsCustomerTrader001', 'BASICDATA_MANAGEMENT_001_001_021', null, null, '02', '01', '银行间交易员信息', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsCustomerInfo001', 'MST', '/invest/bankuplink/basic/cfetscustomerinfo/CfetsCustomerInfo001', 'invest/bankuplink/basic/cfetscustomerinfo/CfetsCustomerInfo001', 'BASICDATA_MANAGEMENT_001_001_022', null, null, '02', '01', '银行间机构信息', 'system', null, sysdate, 'system', sysdate);
--INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ErrorCodeInfoCompare001','MST','/invest/bankuplink/basic/errorcodeinfocompare/ErrorCodeInfoCompare001','invest/bankuplink/basic/errorcodeinfocompare/ErrorCodeInfoCompare001','BASICDATA_MANAGEMENT_001_001_023',null,null,'02','01','错误码信息查询','system',null,sysDate,'system',sysDate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ErrorCodeInfoCompare002','MST','/invest/bankuplink/basic/errorcodeinfocompare/ErrorCodeInfoCompare002','invest/bankuplink/basic/errorcodeinfocompare/ErrorCodeInfoCompare002',null,null,null,'02','01','错误码信息明细','system',null,sysDate,'system',sysDate);

 --字典
 INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('QUOTE_ROLE_TYPE', 'COM', '银行间上行本方角色', 'QUOTE_ROLE_TYPE', '02', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPLY_QUERY_TYPE_IBU', 'COM', '银行间上行回复查询方式', 'REPLY_QUERY_TYPE_IBU', '02', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('QUOTE_STUTAS_IBU', 'COM', '银行间上行报价状态', 'QUOTE_STUTAS_IBU', '02', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SEND_TYPE_IBU', 'COM', '银行间上行发送状态', 'SEND_TYPE_IBU', '02', 'system', null, sysdate, 'system', sysdate);
 INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CALC_INTPUT_TYPE', 'COM', '计算选择方式', 'CALC_INTPUT_TYPE', '02', 'system', null, sysdate, 'system', sysdate);
 INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('DEAL_TYPE_IBU', 'COM', '银行间成交状态', 'DEAL_TYPE_IBU', '02', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('QUOTE_SYSMARK_IBU', 'COM', '报价系统区分类型', 'QUOTE_SYSMARK_IBU', '02', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('QUOTE_TYPE', 'COM', '报价方式', null, '02', 'system', null, sysdate, 'system', sysdate);

--字典明细
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_ROLE_TYPE', '01', '询价方', 1, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_ROLE_TYPE', '02', '回复方', 2, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_ROLE_TYPE', '03', '非做市方', 1, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_ROLE_TYPE', '04', '做市方', 2, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPLY_QUERY_TYPE_IBU', '01', '全部交易员可见', 1, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPLY_QUERY_TYPE_IBU', '02', '指定交易员可见', 2, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '01', '新增中', 1, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '02', '已新增', 2, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '03', '修改中', 3, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '04', '已修改', 4, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '05', '撤回中', 5, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '06', '已撤回', 6, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '07', '已成交', 7, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '08', '已终止', 8, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '09', '待回复', 9, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '10', '成交中', 10, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '11', '终止中', 11, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '12', '已回复', 12, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '13', '已过期', 13, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '14', '已拒绝', 14, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '15', '已撤销', 15, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '16', '拒绝中', 16, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '17', '回复中', 17, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '18', '交谈中', 18, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_STUTAS_IBU', '19', '已交谈', 19, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SEND_TYPE_IBU', '00', '待发送', 1, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SEND_TYPE_IBU', '01', '已发送', 2, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SEND_TYPE_IBU', '02', '成功', 3, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SEND_TYPE_IBU', '03', '失败', 4, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CALC_INTPUT_TYPE', '01', '净价', 1, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CALC_INTPUT_TYPE', '02', '到期收益率', 2, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CALC_INTPUT_TYPE', '03', '行权收益率', 3, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CALC_INTPUT_TYPE', '04', '全价总额', 4, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DEAL_TYPE_IBU', '01', '未成交', 1, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DEAL_TYPE_IBU', '02', '已成交', 2, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_SYSMARK_IBU', '01', '现券对话报价', 1, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_SYSMARK_IBU', '02', '现券请求报价', 2, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_SYSMARK_IBU', '03', '质押式回购对话报价', 3, '0', 'admin', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_TYPE', '01', '对话报价', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTE_TYPE', '02', '请求报价', 2, '0', 'system', null, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('POSITION_TYPE', '201', '报价质押', 201, '0', 'system', null, sysdate, 'system',sysdate);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('COMMON_BUSINESS_TYPE', 'MST33', 'CFETS账户关联', 922, '0', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('COMMON_BUSINESS_TYPE', 'TRD83', '银行间上行', 575, '0', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SETT_MODE', '07', '纯券过户', 4, '0', 'admin', null, sysdate, 'system', sysdate);

--画面关联数据字典
insert into SYS_PAGE_DICTLIST (PAGE_ID, DICT_ID, DICT_ITEM_VAL, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, SUB_PAGE_ID, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT)
values ('CfetsBondDealQuotedPrice003', 'PS', 'T02', 'admin', null, sysdate, 'admin', sysdate, '999999', null, null);

insert into SYS_PAGE_DICTLIST (PAGE_ID, DICT_ID, DICT_ITEM_VAL, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, SUB_PAGE_ID, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT)
values ('CfetsBondDealQuotedPrice003', 'PS', 'T01', 'admin', null, sysdate, 'admin', sysdate, '999999', null, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'ACCOUNT_SPEED', '01', 'T+0', null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'ACCOUNT_SPEED', '02', 'T+1', null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'BACK_LEND_DIRECTION', '03', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'BACK_LEND_DIRECTION', '04', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'SETT_MODE', '01', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'SETT_MODE', '02', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'SETT_MODE', '03', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'SETT_MODE', '07', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'TERM_TYPE_REBACK', 'R001', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'TERM_TYPE_REBACK', 'R007', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'TERM_TYPE_REBACK', 'R014', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'TERM_TYPE_REBACK', 'R021', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'TERM_TYPE_REBACK', 'R1M', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'TERM_TYPE_REBACK', 'R1Y', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'TERM_TYPE_REBACK', 'R2M', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'TERM_TYPE_REBACK', 'R3M', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'TERM_TYPE_REBACK', 'R4M', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'TERM_TYPE_REBACK', 'R6M', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice002', '999999', 'TERM_TYPE_REBACK', 'R9M', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsBondDealQuotedPrice001', '999999', 'PS', 'T01', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsBondDealQuotedPrice001', '999999', 'PS', 'T02', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsBondDealQuotedPrice002', '999999', 'ACCOUNT_SPEED', '01', null, null, 'system', null, sysdate, 'system', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsBondDealQuotedPrice002', '999999', 'ACCOUNT_SPEED', '02', null, null, 'system', null, sysdate, 'system', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsBondDealQuotedPrice002', '999999', 'PS', 'T01', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsBondDealQuotedPrice002', '999999', 'PS', 'T02', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsBondDealQuotedPrice002', '999999', 'SETT_MODE', '01', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsBondDealQuotedPrice002', '999999', 'SETT_MODE', '02', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsBondDealQuotedPrice002', '999999', 'SETT_MODE', '03', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsBondDealQuotedPrice002', '999999', 'SETT_MODE', '07', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsBondDealQuotedPrice006', '999999', 'PS', 'T01', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsBondDealQuotedPrice006', '999999', 'PS', 'T02', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice001', '999999', 'BACK_LEND_DIRECTION', '03', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice001', '999999', 'BACK_LEND_DIRECTION', '04', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice005', '999999', 'BACK_LEND_DIRECTION', '03', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsRepoDealQuotedPrice005', '999999', 'BACK_LEND_DIRECTION', '04', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsHisDealQuery001', '999999', 'BACK_LEND_DIRECTION', '03', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsHisDealQuery001', '999999', 'BACK_LEND_DIRECTION', '04', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsHisDealQuery001', '999999', 'PS', 'T01', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsHisDealQuery001', '999999', 'PS', 'T02', null, null, 'admin', null, sysdate, 'admin', sysdate);


--系统参数
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'CSEFSUP_SWITCH', 'Y', '01', null, '外汇交易中心上行开关控制，如果是Y则为开，N为关', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'IbuFlag_secondaryMarketTrade', 'Y', '01', null, '委托未接收/已接收是否显示二级市场交易（Y：是，N：否）', 'system', null, sysdate, 'dzw', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuFlag_startDialogueQuoteWithNoEntrust', '02', '01', null, '银行间上行没有选择委托指令的场合是否允许发起对话报价（01：不允许，02：允许）', 'system', null, sysdate, 'xhr', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuVar_calendarId', '03', '01', null, '银行间上行银行间交易日历', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuFlag_ouTraderIdCanEdit', 'Y', '01', null, '新增报价银行间交易员是否允许修改（Y：可以，N：不可以修改）', 'system', null, sysdate, 'chendi', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuFlag_allTraderCanShowUnAnswerQuote', 'N', '01', null, '待回复报价是否所有交易员可见（Y：所有交易员都可见，N：只有交易员可见）', 'system', null, sysdate, 'xx333', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuVar_quoteEffTime', '19:30', '01', null, '报价有效时间', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuFlag_repoOverAvaCanContinue', '02', '01', null, '银行间报价-质押式回购报价-质押券面超可质押是否继续（01：不允许，02：允许）', 'system', null, sysdate, 'xhr1', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuFlag_bondOverEntrustAvaAmt', '02', '01', null, '银行间报价-现券买卖报价-超委托剩余额度校验开关（01 硬阻挡 02 软阻挡）', 'system', null, sysdate, 'etliu', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuFlag_bondOverEntrustPrice', '02', '01', null, '银行间报价-现券买卖报价-超委托的价格校验开关（01 硬阻挡 02 软阻挡）', 'system', null, sysdate, 'chendi', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuFlag_answerQuoteWithOutEntrust', '02', '01', null, '银行间报价-回复报价未选委托是否可继续提交（01 硬阻挡 02 软阻挡）', 'system', null, sysdate, 'chendi', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuFlag_repoOverEntrustPrice', '02', '01', null, '银行间报价-质押式回购报价-超委托的价格校验开关（01 硬阻挡 02 软阻挡）', 'system', null, sysdate, 'etliu', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuVar_dealDeviationVal', '1', '01', null, '银行间报价-现券买卖报价-交易偏离度阈值', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuFlag_dealDeviation', '02', '01', null, '银行间报价-现券买卖报价-交易偏离度开关（01 硬阻挡 02 软阻挡）', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CFETS', 'ibuFlag_repoOverEntrustAvaAmt', '02', '01', null, '银行间报价-质押式回购报价-超委托剩余额度校验开关（01 硬阻挡 02 软阻挡）', 'system', null, sysdate, 'etliu', sysdate);

--流程模板
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsPortfolioAcctRela002', 'CfetsPortfolioAcctRela002_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsPortfolioAcctRela002', 'CfetsPortfolioAcctRela001_btn_delete', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsBondDealQuotedPrice002', 'CfetsBondDealQuotedPrice002_btn_bondRevoke', 'oneApproval', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsBondDealQuotedPrice003', 'CfetsBondDealQuotedPrice003_btn_bondRevoke', 'oneApproval', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsRepoDealQuotedPrice002', 'CfetsRepoDealQuotedPrice002_btn_repoRevoke', 'oneApproval', 'system', null, sysdate, 'system', sysdate);

------------------------------------------------------------------------------画面补充----------------------------------------------------------

ALTER TABLE PA_PTL_PORTFOLIO ADD VALUATION_METHOD VARCHAR(10);
COMMENT ON COLUMN PA_PTL_PORTFOLIO.VALUATION_METHOD IS '估值方式';

-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('WFL_IBU_QUOTE_BOND2') ;
    if num > 0 then
        execute immediate 'drop table WFL_IBU_QUOTE_BOND2' ;
    end if;
end;
/
create table WFL_IBU_QUOTE_BOND2
(
  quote_id           VARCHAR2(32) not null,
  quote_type         VARCHAR2(50),
  quote_role_type    VARCHAR2(50),
  version            NUMBER(10),
  exe_type           VARCHAR2(50),
  p_quote_id         VARCHAR2(50),
  cfets_quote_id     VARCHAR2(50),
  finprod_id         VARCHAR2(50),
  finprod_type2      VARCHAR2(50),
  branch             NUMBER(10),
  portfolio_id       VARCHAR2(32),
  entrust_id         VARCHAR2(32),
  quote_date         DATE,
  settle_date        DATE,
  settle_speed       NUMBER(10),
  delivery_type      VARCHAR2(50),
  sett_type          VARCHAR2(50),
  sett_ccy           VARCHAR2(50),
  ps                 VARCHAR2(50),
  share_amt          NUMBER(30,4),
  fprice_amt         NUMBER(30,2),
  trade_status       VARCHAR2(50),
  send_status        VARCHAR2(50),
  eff_time           TIMESTAMP(6),
  ou_party_id        VARCHAR2(32),
  ou_trader_id       VARCHAR2(50),
  b_version          NUMBER(10),
  my_acct_no         VARCHAR2(32),
  sec_manage_acct_id VARCHAR2(32),
  branch_id          VARCHAR2(50),
  workflow_id        VARCHAR2(32),
  workflow_status    VARCHAR2(50) not null,
  remark             VARCHAR2(1000),
  create_user        VARCHAR2(20) not null,
  create_time        TIMESTAMP(6) not null,
  create_dept        VARCHAR2(32),
  update_user        VARCHAR2(20) not null,
  update_time        TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table WFL_IBU_QUOTE_BOND2
  is '现券银行间报价业务流程';
-- Add comments to the columns
comment on column WFL_IBU_QUOTE_BOND2.quote_id
  is '报价编号（内部代码）';
comment on column WFL_IBU_QUOTE_BOND2.quote_type
  is '报价类型，对话报价/请求报价/质押式回购报价';
comment on column WFL_IBU_QUOTE_BOND2.quote_role_type
  is '本方角色';
comment on column WFL_IBU_QUOTE_BOND2.version
  is '版本号';
comment on column WFL_IBU_QUOTE_BOND2.exe_type
  is '操作类型';
comment on column WFL_IBU_QUOTE_BOND2.p_quote_id
  is '外部报价编号';
comment on column WFL_IBU_QUOTE_BOND2.cfets_quote_id
  is 'CFETS报价编号';
comment on column WFL_IBU_QUOTE_BOND2.finprod_id
  is '金融产品代码';
comment on column WFL_IBU_QUOTE_BOND2.finprod_type2
  is '金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层';
comment on column WFL_IBU_QUOTE_BOND2.branch
  is '分支序号';
comment on column WFL_IBU_QUOTE_BOND2.portfolio_id
  is '组合代码';
comment on column WFL_IBU_QUOTE_BOND2.entrust_id
  is '委托代码';
comment on column WFL_IBU_QUOTE_BOND2.quote_date
  is '报价日期';
comment on column WFL_IBU_QUOTE_BOND2.settle_date
  is '交割日期';
comment on column WFL_IBU_QUOTE_BOND2.settle_speed
  is '清算速度';
comment on column WFL_IBU_QUOTE_BOND2.delivery_type
  is '结算方式，券款对付、见券付款、见款付券';
comment on column WFL_IBU_QUOTE_BOND2.sett_type
  is '清算类型';
comment on column WFL_IBU_QUOTE_BOND2.sett_ccy
  is '结算币种';
comment on column WFL_IBU_QUOTE_BOND2.ps
  is '交易方向，买入、卖出、收息、还本、申购、赎回等';
comment on column WFL_IBU_QUOTE_BOND2.share_amt
  is '交易份额，债券数量等';
comment on column WFL_IBU_QUOTE_BOND2.fprice_amt
  is '全价总额';
comment on column WFL_IBU_QUOTE_BOND2.trade_status
  is '报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/';
comment on column WFL_IBU_QUOTE_BOND2.send_status
  is '发送状态，待发送/成功/失败/发送中';
comment on column WFL_IBU_QUOTE_BOND2.eff_time
  is '有效时间';
comment on column WFL_IBU_QUOTE_BOND2.ou_party_id
  is '本方银行间机构，21位编号';
comment on column WFL_IBU_QUOTE_BOND2.ou_trader_id
  is '本方银行间交易员';
comment on column WFL_IBU_QUOTE_BOND2.b_version
  is '前置版本号';
comment on column WFL_IBU_QUOTE_BOND2.my_acct_no
  is '本方资金账户';
comment on column WFL_IBU_QUOTE_BOND2.sec_manage_acct_id
  is '证券管理户代码';
comment on column WFL_IBU_QUOTE_BOND2.branch_id
  is '序号ID,平安个性化字段';
comment on column WFL_IBU_QUOTE_BOND2.workflow_id
  is '工作流代码';
comment on column WFL_IBU_QUOTE_BOND2.workflow_status
  is '数据状态';
comment on column WFL_IBU_QUOTE_BOND2.remark
  is '备注';
comment on column WFL_IBU_QUOTE_BOND2.create_user
  is '创建人';
comment on column WFL_IBU_QUOTE_BOND2.create_time
  is '创建时间';
comment on column WFL_IBU_QUOTE_BOND2.create_dept
  is '创建部门';
comment on column WFL_IBU_QUOTE_BOND2.update_user
  is '更新人';
comment on column WFL_IBU_QUOTE_BOND2.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table WFL_IBU_QUOTE_BOND2
  add constraint PK_WFL_IBU_QUOTE_BOND2 primary key (QUOTE_ID)
  using index
  ;

-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('WFL_IBU_QUOTE_BOND_DET') ;
    if num > 0 then
        execute immediate 'drop table WFL_IBU_QUOTE_BOND_DET' ;
    end if;
end;
/
create table WFL_IBU_QUOTE_BOND_DET
(
  det_id          VARCHAR2(32) not null,
  req_id          VARCHAR2(50),
  version         NUMBER(10),
  cfets_req_id    VARCHAR2(50),
  quote_id        VARCHAR2(32),
  cp_party_id     VARCHAR2(32),
  cp_trader       VARCHAR2(50),
  unit_cprice     NUMBER(30,14),
  unit_int        NUMBER(30,14),
  unit_fprice     NUMBER(30,14),
  par_value       NUMBER(30,14),
  share_amt       NUMBER(30,4),
  prin_amt        NUMBER(30,2),
  par_value_amt   NUMBER(30,2),
  cprice_amt      NUMBER(30,2),
  int_amt         NUMBER(30,2),
  fprice_amt      NUMBER(30,2),
  yield           NUMBER(30,14),
  exer_yield      NUMBER(30,14),
  calculate_field VARCHAR2(50),
  trade_status    VARCHAR2(50),
  send_status     VARCHAR2(50),
  execid          VARCHAR2(32),
  re_eff_time     TIMESTAMP(6),
  workflow_id     VARCHAR2(32),
  workflow_status VARCHAR2(50) not null,
  remark          VARCHAR2(1000),
  create_user     VARCHAR2(20) not null,
  create_time     TIMESTAMP(6) not null,
  create_dept     VARCHAR2(32),
  update_user     VARCHAR2(20) not null,
  update_time     TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table WFL_IBU_QUOTE_BOND_DET
  is '现券银行间报价明细业务流程';
-- Add comments to the columns
comment on column WFL_IBU_QUOTE_BOND_DET.det_id
  is '明细编号';
comment on column WFL_IBU_QUOTE_BOND_DET.req_id
  is '请求编号(请求报价使用的外部编号)';
comment on column WFL_IBU_QUOTE_BOND_DET.version
  is '版本号';
comment on column WFL_IBU_QUOTE_BOND_DET.cfets_req_id
  is 'CFETS请求编号';
comment on column WFL_IBU_QUOTE_BOND_DET.quote_id
  is '报价编号（内部代码）';
comment on column WFL_IBU_QUOTE_BOND_DET.cp_party_id
  is '对手银行间机构，21位编号';
comment on column WFL_IBU_QUOTE_BOND_DET.cp_trader
  is '对手方银行间交易员';
comment on column WFL_IBU_QUOTE_BOND_DET.unit_cprice
  is '单位净价';
comment on column WFL_IBU_QUOTE_BOND_DET.unit_int
  is '单位利息';
comment on column WFL_IBU_QUOTE_BOND_DET.unit_fprice
  is '单位全价';
comment on column WFL_IBU_QUOTE_BOND_DET.par_value
  is '单位面值，债券100或者当前面值(还本前)，其他资产1';
comment on column WFL_IBU_QUOTE_BOND_DET.share_amt
  is '交易份额，债券数量等';
comment on column WFL_IBU_QUOTE_BOND_DET.prin_amt
  is '交易本金，交易成本等';
comment on column WFL_IBU_QUOTE_BOND_DET.par_value_amt
  is '面值总额';
comment on column WFL_IBU_QUOTE_BOND_DET.cprice_amt
  is '净价总额';
comment on column WFL_IBU_QUOTE_BOND_DET.int_amt
  is '利息总额';
comment on column WFL_IBU_QUOTE_BOND_DET.fprice_amt
  is '全价总额';
comment on column WFL_IBU_QUOTE_BOND_DET.yield
  is '到期收益率';
comment on column WFL_IBU_QUOTE_BOND_DET.exer_yield
  is '行权收益率';
comment on column WFL_IBU_QUOTE_BOND_DET.calculate_field
  is '计算方式，净价/到期收益率/行权收益率';
comment on column WFL_IBU_QUOTE_BOND_DET.trade_status
  is '报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/';
comment on column WFL_IBU_QUOTE_BOND_DET.send_status
  is '发送状态，待发送/成功/失败/发送中';
comment on column WFL_IBU_QUOTE_BOND_DET.execid
  is '成交编号';
comment on column WFL_IBU_QUOTE_BOND_DET.re_eff_time
  is '回复有效时间';
comment on column WFL_IBU_QUOTE_BOND_DET.workflow_id
  is '工作流代码';
comment on column WFL_IBU_QUOTE_BOND_DET.workflow_status
  is '数据状态';
comment on column WFL_IBU_QUOTE_BOND_DET.remark
  is '备注';
comment on column WFL_IBU_QUOTE_BOND_DET.create_user
  is '创建人';
comment on column WFL_IBU_QUOTE_BOND_DET.create_time
  is '创建时间';
comment on column WFL_IBU_QUOTE_BOND_DET.create_dept
  is '创建部门';
comment on column WFL_IBU_QUOTE_BOND_DET.update_user
  is '更新人';
comment on column WFL_IBU_QUOTE_BOND_DET.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table WFL_IBU_QUOTE_BOND_DET
  add constraint PK_WFL_IBU_QUOTE_BOND_DET primary key (DET_ID)
  using index
  ;



-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('WFL_IBU_QUOTE_CREP_PLEDGE') ;
    if num > 0 then
        execute immediate 'drop table WFL_IBU_QUOTE_CREP_PLEDGE' ;
    end if;
end;
/
create table WFL_IBU_QUOTE_CREP_PLEDGE
(
  quote_id          VARCHAR2(32) not null,
  version           NUMBER(10),
  pledge_finprod_id VARCHAR2(50) not null,
  branch            NUMBER(10) not null,
  pledge_face_value NUMBER(30,2),
  pledge_number     NUMBER(30,2),
  pledge_ratio      NUMBER(30,14),
  pledge_amt        NUMBER(30,2),
  unit_cprice       NUMBER(30,14),
  workflow_id       VARCHAR2(32),
  cprice_date       DATE,
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null,
  workflow_status   VARCHAR2(50) not null
)
;
-- Add comments to the table
comment on table WFL_IBU_QUOTE_CREP_PLEDGE
  is '回购报价质押品业务流程';
-- Add comments to the columns
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.quote_id
  is '报价编号（内部代码）';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.version
  is '版本号';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.pledge_finprod_id
  is '质押金融产品代码';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.branch
  is '分支序号';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.pledge_face_value
  is '质押面值';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.pledge_number
  is '质押数量';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.pledge_ratio
  is '质押率';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.pledge_amt
  is '质押金额';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.unit_cprice
  is '单位净价';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.workflow_id
  is '工作流代码';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.cprice_date
  is '净值日期';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.create_user
  is '创建人';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.create_dept
  is '创建部门';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.create_time
  is '创建时间';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.update_user
  is '更新人';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.update_time
  is '更新时间';
comment on column WFL_IBU_QUOTE_CREP_PLEDGE.workflow_status
  is '数据状态';
-- Create/Recreate primary, unique and foreign key constraints
alter table WFL_IBU_QUOTE_CREP_PLEDGE
  add constraint PK_WFL_IBU_QUOTE_CREP_PLEDGE primary key (QUOTE_ID, BRANCH, PLEDGE_FINPROD_ID)
  using index
  ;


-- Create table

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('WFL_IBU_QUOTE_CREP') ;
    if num > 0 then
        execute immediate 'drop table WFL_IBU_QUOTE_CREP' ;
    end if;
end;
/
create table WFL_IBU_QUOTE_CREP
(
  quote_id           VARCHAR2(32) not null,
  quote_type         VARCHAR2(50),
  p_quote_id         VARCHAR2(50),
  version            NUMBER(10),
  exe_type           VARCHAR2(50),
  cfets_quote_id     VARCHAR2(50),
  quote_role_type    VARCHAR2(50),
  portfolio_id       VARCHAR2(32),
  entrust_id         VARCHAR2(32),
  quote_date         DATE,
  v_settle_date      DATE,
  m_settle_date      DATE,
  settle_speed       NUMBER(10),
  v_delivery_type    VARCHAR2(50),
  m_delivery_type    VARCHAR2(50),
  sett_type          VARCHAR2(50),
  sett_ccy           VARCHAR2(50),
  ccy                VARCHAR2(50),
  repo_ps            VARCHAR2(50),
  rate               NUMBER(30,14),
  term               NUMBER(10),
  term_type          VARCHAR2(50),
  share_amt          NUMBER(30,4),
  share_ct_amt       NUMBER(30,2),
  cb_share_ct_amt    NUMBER(30,2),
  sc_share_ct_amt    NUMBER(30,2),
  fprice_amt         NUMBER(30,2),
  cb_fprice_amt      NUMBER(30,2),
  sc_fprice_amt      NUMBER(30,2),
  m_int_amt          NUMBER(30,2),
  m_trade_amt        NUMBER(30,2),
  basis              VARCHAR2(50),
  trade_status       VARCHAR2(50),
  send_status        VARCHAR2(50),
  eff_time           TIMESTAMP(6),
  ou_party_id        VARCHAR2(32) not null,
  ou_trader_id       VARCHAR2(50),
  cp_party_id        VARCHAR2(32),
  cp_trader_id       VARCHAR2(50),
  my_acct_no         VARCHAR2(32),
  sec_manage_acct_id VARCHAR2(32),
  execid             VARCHAR2(32),
  b_version          NUMBER(10),
  branch_id          VARCHAR2(50),
  workflow_id        VARCHAR2(32),
  workflow_status    VARCHAR2(50) not null,
  remark             VARCHAR2(1000),
  create_user        VARCHAR2(20) not null,
  create_time        TIMESTAMP(6) not null,
  create_dept        VARCHAR2(32),
  update_user        VARCHAR2(20) not null,
  update_time        TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table WFL_IBU_QUOTE_CREP
  is '质押式回购银行间报价业务流程';
-- Add comments to the columns
comment on column WFL_IBU_QUOTE_CREP.quote_id
  is '报价编号（内部代码）';
comment on column WFL_IBU_QUOTE_CREP.quote_type
  is '报价类型，对话报价/请求报价';
comment on column WFL_IBU_QUOTE_CREP.p_quote_id
  is '外部报价编号';
comment on column WFL_IBU_QUOTE_CREP.version
  is '版本号';
comment on column WFL_IBU_QUOTE_CREP.exe_type
  is '操作类型';
comment on column WFL_IBU_QUOTE_CREP.cfets_quote_id
  is 'CFETS报价编号';
comment on column WFL_IBU_QUOTE_CREP.quote_role_type
  is '本方角色';
comment on column WFL_IBU_QUOTE_CREP.portfolio_id
  is '组合代码';
comment on column WFL_IBU_QUOTE_CREP.entrust_id
  is '委托代码';
comment on column WFL_IBU_QUOTE_CREP.quote_date
  is '报价日期';
comment on column WFL_IBU_QUOTE_CREP.v_settle_date
  is '首期结算日';
comment on column WFL_IBU_QUOTE_CREP.m_settle_date
  is '到期结算日';
comment on column WFL_IBU_QUOTE_CREP.settle_speed
  is '清算速度';
comment on column WFL_IBU_QUOTE_CREP.v_delivery_type
  is '首期结算方式，券款对付、见券付款、见款付券';
comment on column WFL_IBU_QUOTE_CREP.m_delivery_type
  is '到期结算方式，券款对付、见券付款、见款付券';
comment on column WFL_IBU_QUOTE_CREP.sett_type
  is '清算类型';
comment on column WFL_IBU_QUOTE_CREP.sett_ccy
  is '结算币种';
comment on column WFL_IBU_QUOTE_CREP.ccy
  is '债券币种';
comment on column WFL_IBU_QUOTE_CREP.repo_ps
  is '回购方向';
comment on column WFL_IBU_QUOTE_CREP.rate
  is '回购利率 单位：%，精度：4位';
comment on column WFL_IBU_QUOTE_CREP.term
  is '期限(天)';
comment on column WFL_IBU_QUOTE_CREP.term_type
  is '期限品种';
comment on column WFL_IBU_QUOTE_CREP.share_amt
  is '交易份额，债券数量等';
comment on column WFL_IBU_QUOTE_CREP.share_ct_amt
  is '折算券面金额';
comment on column WFL_IBU_QUOTE_CREP.cb_share_ct_amt
  is '中债折算券面金额';
comment on column WFL_IBU_QUOTE_CREP.sc_share_ct_amt
  is '上清折算券面金额';
comment on column WFL_IBU_QUOTE_CREP.fprice_amt
  is '全价总额';
comment on column WFL_IBU_QUOTE_CREP.cb_fprice_amt
  is '中债全价总额';
comment on column WFL_IBU_QUOTE_CREP.sc_fprice_amt
  is '上清全价总额';
comment on column WFL_IBU_QUOTE_CREP.m_int_amt
  is '到期利息';
comment on column WFL_IBU_QUOTE_CREP.m_trade_amt
  is '到期金额';
comment on column WFL_IBU_QUOTE_CREP.basis
  is '计息基础';
comment on column WFL_IBU_QUOTE_CREP.trade_status
  is '报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/';
comment on column WFL_IBU_QUOTE_CREP.send_status
  is '发送状态，待发送/成功/失败/发送中';
comment on column WFL_IBU_QUOTE_CREP.eff_time
  is '有效时间';
comment on column WFL_IBU_QUOTE_CREP.ou_party_id
  is '本方银行间机构，21位编号';
comment on column WFL_IBU_QUOTE_CREP.ou_trader_id
  is '本方银行间交易员';
comment on column WFL_IBU_QUOTE_CREP.cp_party_id
  is '对手银行间机构，21位编号';
comment on column WFL_IBU_QUOTE_CREP.cp_trader_id
  is '对手方银行间交易员';
comment on column WFL_IBU_QUOTE_CREP.my_acct_no
  is '本方资金账户';
comment on column WFL_IBU_QUOTE_CREP.sec_manage_acct_id
  is '证券管理户代码';
comment on column WFL_IBU_QUOTE_CREP.execid
  is '成交编号';
comment on column WFL_IBU_QUOTE_CREP.b_version
  is '前置版本号';
comment on column WFL_IBU_QUOTE_CREP.branch_id
  is '序号ID，平安个性化字段';
comment on column WFL_IBU_QUOTE_CREP.workflow_id
  is '工作流代码';
comment on column WFL_IBU_QUOTE_CREP.workflow_status
  is '数据状态';
comment on column WFL_IBU_QUOTE_CREP.remark
  is '备注';
comment on column WFL_IBU_QUOTE_CREP.create_user
  is '创建人';
comment on column WFL_IBU_QUOTE_CREP.create_time
  is '创建时间';
comment on column WFL_IBU_QUOTE_CREP.create_dept
  is '创建部门';
comment on column WFL_IBU_QUOTE_CREP.update_user
  is '更新人';
comment on column WFL_IBU_QUOTE_CREP.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table WFL_IBU_QUOTE_CREP
  add constraint PK_WFL_IBU_QUOTE_CREP primary key (QUOTE_ID)
  using index
 ;

INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsBondDealQuotedPrice002', 'CfetsBondDealQuotedPrice002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate);

INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsBondDealQuotedPrice002', 'CfetsBondDealQuotedPrice002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate);

INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsBondDealQuotedPrice002', 'CfetsBondDealQuotedPrice002_btn_done', 'oneApproval', 'system', null, sysdate, 'system', sysdate);

INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsBondDealQuotedPrice003', 'CfetsBondDealQuotedPrice003_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate);

INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsBondDealQuotedPrice003', 'CfetsBondDealQuotedPrice003_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate);

INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsBondDealQuotedPrice003', 'CfetsBondDealQuotedPrice003_btn_done', 'oneApproval', 'system', null, sysdate, 'system', sysdate);


INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsRepoDealQuotedPrice002', 'CfetsRepoDealQuotedPrice002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate);

INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsRepoDealQuotedPrice002', 'CfetsRepoDealQuotedPrice002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate);

INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CfetsRepoDealQuotedPrice002', 'CfetsRepoDealQuotedPrice002_btn_done', 'oneApproval', 'system', null, sysdate, 'system', sysdate);

alter table INTERF_CSTP_TRADE
add (miscfeeamt                  NUMBER(22,4),
  disputesettlementmethod        CHAR(1),
  couponpaymentindicator         CHAR(1),
  marginreplacement              CHAR(1),
  margintotalamt                 NUMBER(22,4),
  margintype                     CHAR(1),
  startdate                      VARCHAR2(8),
  enddate                        VARCHAR2(8),
  firstperiodstartdate           VARCHAR2(8),
  leginterestaccrualdate         VARCHAR2(8),
  trdtype                        VARCHAR2(10),
  legdaycount                    CHAR(1),
  legbenchmarkcurvename          VARCHAR2(100),
  legprice                       NUMBER(18,12),
  legbenchmarkspread             NUMBER(18,12),
  leginterestaccrualfrequency    CHAR(1),
  leginterestaccrualmethod       CHAR(1),
  couponpaymentdatereset         CHAR(1),
  interestaccuraldaysadjustment  CHAR(1),
  calculateagency                CHAR(1),
  legside1                       VARCHAR2(50),
  legside2                       VARCHAR2(50),
  fixlegdaycount                 CHAR(1),
  fltlegdaycount                 CHAR(1),
  fixlegcouponpaymentfrequency   CHAR(1),
  fltlegcouponpaymentfrequency   CHAR(1),
  fixlegcouponpaymentdate        VARCHAR2(8),
  fltlegcouponpaymentdate        VARCHAR2(8));



-------------------------------------------------------------增加保存按钮工作流---------------------------------------------

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsPortfolioAcctRela002', 'CfetsPortfolioAcctRela002_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate);

insert into SYS_PAGE_DICTLIST (PAGE_ID, DICT_ID, DICT_ITEM_VAL, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, SUB_PAGE_ID, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT)
values ('CfetsPortfolioAcctRela002', 'ACCOUNT_SOURCE', '01', 'admin', null, sysdate, 'admin', sysdate, '999999', null, null);


--------------------------------------------------------------交易对手同步批处理---------------------------------------------------
alter table SYS_BATCH_TASK ADD IS_BATCH_START_TARGET VARCHAR(50);

COMMENT ON COLUMN SYS_BATCH_TASK.IS_BATCH_START_TARGET
IS
    '是否批处理启动执行对象';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('20006', 'INV', '交易对手同步', null, 'updateTraderTask', 2, 'system', null, sysdate, 'system', sysdate, null);

--------------------------------------------------------------清算类型字典变更-----------------------------------------------------

delete from sys_datadict_list t where t.dict_code='CLEAR_TYPE';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('CLEAR_TYPE', '01', '全额清算', 1, '0', 'admin', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('CLEAR_TYPE', '02', '净额清算', 2, '0', 'admin', null, sysdate, 'system', sysdate);


-------------------------------------------------------------新增cfets数据字典转换表-----------------------------------------------
-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('CFETS_DATADICT_CONVERT') ;
    if num > 0 then
        execute immediate 'drop table CFETS_DATADICT_CONVERT' ;
    end if;
end;
/
create table CFETS_DATADICT_CONVERT
(
  seqno            VARCHAR2(50) not null,
  field_code       VARCHAR2(50),
  field_name       VARCHAR2(200),
  system_dict_code VARCHAR2(50),
  system_item_code VARCHAR2(50),
  cfets_item_code  VARCHAR2(50),
  remark           VARCHAR2(200),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table CFETS_DATADICT_CONVERT
  is 'Cfets数据字典转换表';
-- Add comments to the columns
comment on column CFETS_DATADICT_CONVERT.seqno
  is '流水号';
comment on column CFETS_DATADICT_CONVERT.field_code
  is '字段代码';
comment on column CFETS_DATADICT_CONVERT.field_name
  is '字段名称';
comment on column CFETS_DATADICT_CONVERT.system_dict_code
  is '系统字典代码';
comment on column CFETS_DATADICT_CONVERT.system_item_code
  is '系统数据代码(业务系统)';
comment on column CFETS_DATADICT_CONVERT.cfets_item_code
  is 'Cfets数据代码';
comment on column CFETS_DATADICT_CONVERT.remark
  is '备注';
comment on column CFETS_DATADICT_CONVERT.create_user
  is '创建人';
comment on column CFETS_DATADICT_CONVERT.create_dept
  is '创建部门';
comment on column CFETS_DATADICT_CONVERT.create_time
  is '创建时间';
comment on column CFETS_DATADICT_CONVERT.update_user
  is '更新人';
comment on column CFETS_DATADICT_CONVERT.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table CFETS_DATADICT_CONVERT
  add constraint PK_CFETS_DATADICT_CONVERT primary key (SEQNO)
  using index
  ;

  insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3', 'quoteTransType', '10272域-操作类型', 'QUOTE_STUTAS_IBU', '18', '1', '交谈', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('4', 'side', '54域-交易方向', 'PS', 'T01', '1', '买入', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('5', 'side', '54域-交易方向', 'PS', 'T02', '2', '卖出', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('6', 'side', '54域-交易方向', 'BACK_LEND_DIRECTION', '03', '2', '正回购', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('7', 'side', '54域-交易方向', 'BACK_LEND_DIRECTION', '04', '1', '逆回购', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('8', 'deliveryType', '919域-结算方式', 'SETT_MODE', '01', '4', '见券付款', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('9', 'deliveryType', '919域-结算方式', 'SETT_MODE', '02', '5', '见款付券', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('10', 'deliveryType', '919域-结算方式', 'SETT_MODE', '03', '0', '券款对付', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('11', 'clearingMethod', '11143域-清算类型', 'SETTLETYPE', '02', '6', '净额清算', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('12', 'clearingMethod', '11143域-清算类型', 'SETTLETYPE', '01', '13', '全额清算', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('13', 'settlType', '63域-清算速度', 'ACCOUNT_SPEED', '0', '1', 'T+0', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('14', 'settlType', '63域-清算速度', 'ACCOUNT_SPEED', '1', '2', 'T+1', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('15', 'settlType', '63域-清算速度', 'ACCOUNT_SPEED', '2', '3', 'T+2', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('16', 'quoteRespType', '694域-操作类型', 'EXE_TYPE_IBU', '09', '1', '成交确认', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('17', 'quoteRespType', '694域-操作类型', 'EXE_TYPE_IBU', '10', '7', '报价拒绝', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('18', 'quoteRespType', '694域-操作类型', 'EXE_TYPE_IBU', '38', '1', '成交确认', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('19', 'quoteRespType', '694域-操作类型', 'EXE_TYPE_IBU', '62', '2', '交谈', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('20', 'quoteRespType', '694域-操作类型', 'EXE_TYPE_IBU', '66', '101', '修改', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('21', 'quoteRespType', '694域-操作类型', 'EXE_TYPE_IBU', '15', '102', '撤销', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('1', 'quoteTransType', '10272域-操作类型', 'QUOTE_STUTAS_IBU', '01', 'N', '新增', 'system', null, sysdate, 'system', sysdate);

insert into CFETS_DATADICT_CONVERT (SEQNO, FIELD_CODE, FIELD_NAME, SYSTEM_DICT_CODE, SYSTEM_ITEM_CODE, CFETS_ITEM_CODE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('2', 'quoteTransType', '10272域-操作类型', 'QUOTE_STUTAS_IBU', '03', 'R', '修改', 'system', null, sysdate, 'system', sysdate);

-----------------------------------------------------新增cfets回写状态数据字典转换表-----------------------------------------------
-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('CFETS_STATUS_DATADICT_CONVERT') ;
    if num > 0 then
        execute immediate 'drop table CFETS_STATUS_DATADICT_CONVERT' ;
    end if;
end;
/
create table CFETS_STATUS_DATADICT_CONVERT
(
  seqno            VARCHAR2(50) not null,
  quote_type_ibu   VARCHAR2(50),
  msg_type         VARCHAR2(50),
  quote_trans_type VARCHAR2(50),
  quote_status     VARCHAR2(50),
  exec_type        VARCHAR2(50),
  my_side          VARCHAR2(50),
  exe_type         VARCHAR2(50),
  trade_status     VARCHAR2(50),
  send_status      VARCHAR2(50),
  remark           VARCHAR2(200),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table CFETS_STATUS_DATADICT_CONVERT
  is 'Cfets回写状态数据字典转换表';
-- Add comments to the columns
comment on column CFETS_STATUS_DATADICT_CONVERT.seqno
  is '流水号';
comment on column CFETS_STATUS_DATADICT_CONVERT.quote_type_ibu
  is '报价类别';
comment on column CFETS_STATUS_DATADICT_CONVERT.msg_type
  is '报文类型';
comment on column CFETS_STATUS_DATADICT_CONVERT.quote_trans_type
  is '操作类型';
comment on column CFETS_STATUS_DATADICT_CONVERT.quote_status
  is '报价状态';
comment on column CFETS_STATUS_DATADICT_CONVERT.exec_type
  is '成交状态';
comment on column CFETS_STATUS_DATADICT_CONVERT.my_side
  is '业务系统本方角色';
comment on column CFETS_STATUS_DATADICT_CONVERT.exe_type
  is '业务操作类型';
comment on column CFETS_STATUS_DATADICT_CONVERT.trade_status
  is '交易状态';
comment on column CFETS_STATUS_DATADICT_CONVERT.send_status
  is '发送状态';
comment on column CFETS_STATUS_DATADICT_CONVERT.remark
  is '备注';
comment on column CFETS_STATUS_DATADICT_CONVERT.create_user
  is '创建人';
comment on column CFETS_STATUS_DATADICT_CONVERT.create_dept
  is '创建部门';
comment on column CFETS_STATUS_DATADICT_CONVERT.create_time
  is '创建时间';
comment on column CFETS_STATUS_DATADICT_CONVERT.update_user
  is '更新人';
comment on column CFETS_STATUS_DATADICT_CONVERT.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table CFETS_STATUS_DATADICT_CONVERT
  add constraint PK_CFETS_STATUS_DT_CONVERT primary key (SEQNO)
  using index
  ;

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('6-2', '01', 'AI', null, '5-拒绝', null, '01-询价方', '22-对手方回复拒绝', '08-已终止', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('29-2', '03', 'AI', null, '19-撤销', null, '01-询价方', '16-本方报价撤回成功', '06-已撤回', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('32-2', '03', 'AI', null, '5-拒绝', null, '01-询价方', '12-本方回复拒绝成功', '08-已终止', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('41-2', '03', 'AI', null, '19-撤销', null, '02-回复方', '16-本方报价撤回成功', '06-已撤回', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('44-2', '03', 'AI', null, '5-拒绝', null, '02-回复方', '12-本方回复拒绝成功', '08-已终止', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('7-2', '01', 'AI', null, '5-拒绝', null, '02-回复方', '12-本方回复拒绝成功', '08-已终止', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('1', '01', 'AI', 'N-新增', '16-正常', null, '01-询价方', '03-本方新增发送成功', '02-已新增', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('2', '01', 'AI', 'R-修改', '16-正常', null, '01-询价方', '07-本方修改发送成功', '04-已修改', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3', '01', 'AI', 'N-新增', '19-撤销', null, '01-询价方', '16-本方报价撤回成功', '06-已撤回', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('4', '01', 'AI', 'R-修改', '19-撤销', null, '01-询价方', '16-本方报价撤回成功', '06-已撤回', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('5', '01', 'AI', 'N-新增', '5-拒绝', null, '01-询价方', '22-对手方回复拒绝', '08-已终止', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('6', '01', 'AI', 'R-修改', '5-拒绝', null, '01-询价方', '22-对手方回复拒绝', '08-已终止', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('7', '01', 'AI', 'N-新增', '5-拒绝', null, '02-回复方', '12-本方回复拒绝成功', '08-已终止', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('8', '01', '8', null, null, 'F-正常', '01-询价方', '21-对手方回复成交', '07-已成交', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('9', '01', '8', null, null, 'F-正常', '02-回复方', '11-本方回复成交成功', '07-已成交', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('10', '01', 'S', 'N-新增', '16-正常', null, '02-回复方', '19-对手方报价新增', '09-待回复', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('11', '01', 'S', 'R-修改', '16-正常', null, '02-回复方', '20-对手方报价修改', '09-待回复', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('13', '02', '8', null, null, 'F-正常', '03-非做市方', '39-本方请求回复成交成功', '07-已成交', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('14', '02', 'AG', null, null, null, '03-非做市方', '42-对手方拒绝请求报价', '14-已拒绝', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('15', '02', 'S', 'N-新增', '16-正常', null, '03-非做市方', '43-对手方新增请求回复', '12-已回复', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('16', '02', 'S', 'R-修改', '16-正常', null, '03-非做市方', '44-对手方修改请求回复', '12-已回复', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('17', '02', 'U29', null, '16-正常', null, '03-非做市方', '32-本方新增请求报价发送成功', '09-待回复', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('18', '02', 'U29', null, '19-撤销', null, '03-非做市方', '35-本方请求报价撤回成功', '06-已撤回', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('19', '02', 'U32', null, '21-过期', null, '03-非做市方', '26-接收已过期', '13-已过期', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('20', '02', 'U32', null, '115-做市方拒绝', null, '03-非做市方', '37-接收全拒绝请求报价', '08-已终止', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('21', '02', 'Z', null, '19-撤销', null, '03-非做市方', '45-对手方撤销请求回复', '15-已撤销', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('22', '02', 'Z', null, '21-过期', null, '03-非做市方', '26-接收已过期', '13-已过期', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('23', '03', '8', null, null, 'F-成交', '01-询价方', '11-本方回复成交成功', '07-已成交', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('24', '03', 'AI', 'N-新增', '16-正常', null, '01-询价方', '03-本方新增发送成功', '02-已新增', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('25', '03', 'AI', 'R-修改', '16-正常', null, '01-询价方', '07-本方修改发送成功', '04-已修改', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('26', '03', 'AI', '1-交谈', '16-正常', null, '01-询价方', '63-本方交谈发送成功', '19-已交谈', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('27', '03', 'AI', 'N-新增', '19-撤销', null, '01-询价方', '16-本方报价撤回成功', '06-已撤回', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('28', '03', 'AI', 'R-修改', '19-撤销', null, '01-询价方', '16-本方报价撤回成功', '06-已撤回', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('29', '03', 'AI', '1-交谈', '19-撤销', null, '01-询价方', '16-本方报价撤回成功', '06-已撤回', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('30', '03', 'AI', 'N-新增', '5-拒绝', null, '01-询价方', '12-本方回复拒绝成功', '08-已终止', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('31', '03', 'AI', 'R-修改', '5-拒绝', null, '01-询价方', '12-本方回复拒绝成功', '08-已终止', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('32', '03', 'AI', '1-交谈', '5-拒绝', null, '01-询价方', '12-本方回复拒绝成功', '08-已终止', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33', '03', 'AJ', '2-交谈', '16-正常', null, '01-询价方', '24-对手方交谈新增', '09-待回复', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('34', '03', 'AJ', '101-修改', '16-正常', null, '01-询价方', '25-对手方交谈修改', '09-待回复', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('35', '03', '8', null, null, 'F-成交', '02-回复方', '11-本方回复成交成功', '07-已成交', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('36', '03', 'AI', 'N-新增', '16-正常', null, '02-回复方', '63-本方交谈发送成功', '19-已交谈', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('37', '03', 'AI', 'R-修改', '16-正常', null, '02-回复方', '67-本方交谈修改发送成功', '19-已交谈', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('38', '03', 'AI', '1-交谈', '16-正常', null, '02-回复方', '63-本方交谈发送成功', '19-已交谈', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('39', '03', 'AI', 'N-新增', '19-撤销', null, '02-回复方', '16-本方报价撤回成功', '06-已撤回', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('40', '03', 'AI', 'R-修改', '19-撤销', null, '02-回复方', '16-本方报价撤回成功', '06-已撤回', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('41', '03', 'AI', '1-交谈', '19-撤销', null, '02-回复方', '16-本方报价撤回成功', '06-已撤回', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('42', '03', 'AI', 'N-新增', '5-拒绝', null, '02-回复方', '12-本方回复拒绝成功', '08-已终止', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('43', '03', 'AI', 'R-修改', '5-拒绝', null, '02-回复方', '12-本方回复拒绝成功', '08-已终止', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('44', '03', 'AI', '1-交谈', '5-拒绝', null, '02-回复方', '12-本方回复拒绝成功', '08-已终止', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('45', '03', 'S', 'N-新增', '16-正常', null, '02-回复方', '19-对手方报价新增', '09-待回复', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('46', '03', 'S', 'R-修改', '16-正常', null, '02-回复方', '20-对手方报价修改', '09-待回复', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('47', '03', 'S', '1-交谈', '16-正常', null, '02-回复方', '24-对手方交谈新增', '09-待回复', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('12', '01', 'AI', null, '19-撤销', null, '02-回复方', '23-对手方报价撤回', '06-已撤回', null, null, 'system', null, sysdate, 'system', sysdate);

insert into CFETS_STATUS_DATADICT_CONVERT (SEQNO, QUOTE_TYPE_IBU, MSG_TYPE, QUOTE_TRANS_TYPE, QUOTE_STATUS, EXEC_TYPE, MY_SIDE, EXE_TYPE, TRADE_STATUS, SEND_STATUS, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('4-2', '01', 'AI', null, '19-撤销', null, '01-询价方', '16-本方报价撤回成功', '06-已撤回', '02-成功', null, 'system', null, sysdate, 'system', sysdate);

-----------------------------------------------------------增加user_add表-------------------------------------------------------------
-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SYS_USER_ADD') ;
    if num > 0 then
        execute immediate 'drop table SYS_USER_ADD' ;
    end if;
end;
/
create table SYS_USER_ADD
(
  user_id        VARCHAR2(32) not null,
  lst_login_time TIMESTAMP(6) not null,
  create_user    VARCHAR2(20) not null,
  create_dept    VARCHAR2(32),
  create_time    TIMESTAMP(6) not null,
  update_user    VARCHAR2(20) not null,
  update_time    TIMESTAMP(6) not null,
  ibu_trader     VARCHAR2(32),
  pwd_reset_date DATE
)
;
-- Add comments to the table
comment on table SYS_USER_ADD
  is '用户附属表';
-- Add comments to the columns
comment on column SYS_USER_ADD.user_id
  is '用户代码';
comment on column SYS_USER_ADD.lst_login_time
  is '最后登录时间';
comment on column SYS_USER_ADD.create_user
  is '创建人';
comment on column SYS_USER_ADD.create_dept
  is '创建部门';
comment on column SYS_USER_ADD.create_time
  is '创建时间';
comment on column SYS_USER_ADD.update_user
  is '更新人';
comment on column SYS_USER_ADD.update_time
  is '更新时间';
comment on column SYS_USER_ADD.ibu_trader
  is '银行间交易员ID';
-- Create/Recreate primary, unique and foreign key constraints
alter table SYS_USER_ADD
  add constraint PK_SYS_USER_ADD primary key (USER_ID)
  using index
  ;

-- 系统参数相关
DELETE FROM SYS_PARA where PARA_CODE = 'IS_SHOW_IBU_TRADER';
insert into SYS_PARA values ('SYS','IS_SHOW_IBU_TRADER','01','01','','用户新建是否显示银行间交易员ID','system','',to_date('2020/12/25', 'yyyy/mm/dd'), 'system',to_date('2020/12/25', 'yyyy/mm/dd'),'','01');


--------------------------------------------------------银行间基础数据同步批处理----------------------------------------------------------
-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('INTERF_CFETS_ERR_MSG_TMP') ;
    if num > 0 then
        execute immediate 'drop table INTERF_CFETS_ERR_MSG_TMP' ;
    end if;
end;
/
create table INTERF_CFETS_ERR_MSG_TMP
(
  err_code    VARCHAR2(50) not null,
  err_msg     VARCHAR2(1000) not null,
  err_type    VARCHAR2(32) not null,
  create_user VARCHAR2(20) not null,
  create_dept VARCHAR2(32),
  create_time TIMESTAMP(6) not null,
  update_user VARCHAR2(20) not null,
  update_time TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table INTERF_CFETS_ERR_MSG_TMP
  is '外汇交易中心错误码对照表';
-- Add comments to the columns
comment on column INTERF_CFETS_ERR_MSG_TMP.err_code
  is '错误码';
comment on column INTERF_CFETS_ERR_MSG_TMP.err_msg
  is '错误信息';
comment on column INTERF_CFETS_ERR_MSG_TMP.err_type
  is '错误应用类型';
comment on column INTERF_CFETS_ERR_MSG_TMP.create_user
  is '创建人';
comment on column INTERF_CFETS_ERR_MSG_TMP.create_dept
  is '创建部门';
comment on column INTERF_CFETS_ERR_MSG_TMP.create_time
  is '创建时间';
comment on column INTERF_CFETS_ERR_MSG_TMP.update_user
  is '更新人';
comment on column INTERF_CFETS_ERR_MSG_TMP.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table INTERF_CFETS_ERR_MSG_TMP
  add constraint PK_INTERF_CFETS_ERR_MSG_TMP primary key (ERR_CODE, ERR_MSG, ERR_TYPE)
  using index
  ;

-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('INTERF_CFETS_CUSTOMER_TMP') ;
    if num > 0 then
        execute immediate 'drop table INTERF_CFETS_CUSTOMER_TMP' ;
    end if;
end;
/
create table INTERF_CFETS_CUSTOMER_TMP
(
  party_id          VARCHAR2(32) not null,
  party_customer_id VARCHAR2(32) not null,
  party_abbr        VARCHAR2(200),
  party_name        VARCHAR2(200),
  party_abbr_en     VARCHAR2(200),
  party_name_en     VARCHAR2(200),
  remark            VARCHAR2(1000),
  create_user       VARCHAR2(20) not null,
  create_time       TIMESTAMP(6) not null,
  create_dept       VARCHAR2(32),
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table INTERF_CFETS_CUSTOMER_TMP
  is '外汇交易中心_客户信息_银行机构';
-- Add comments to the columns
comment on column INTERF_CFETS_CUSTOMER_TMP.party_id
  is '交易成员ID，21位编号';
comment on column INTERF_CFETS_CUSTOMER_TMP.party_customer_id
  is '交易机构ID，6位编号';
comment on column INTERF_CFETS_CUSTOMER_TMP.party_abbr
  is '交易成员简称';
comment on column INTERF_CFETS_CUSTOMER_TMP.party_name
  is '交易成员全称';
comment on column INTERF_CFETS_CUSTOMER_TMP.party_abbr_en
  is '交易成员英文简称';
comment on column INTERF_CFETS_CUSTOMER_TMP.party_name_en
  is '交易成员英文全称';
comment on column INTERF_CFETS_CUSTOMER_TMP.remark
  is '备注';
comment on column INTERF_CFETS_CUSTOMER_TMP.create_user
  is '创建人';
comment on column INTERF_CFETS_CUSTOMER_TMP.create_time
  is '创建时间';
comment on column INTERF_CFETS_CUSTOMER_TMP.create_dept
  is '创建部门';
comment on column INTERF_CFETS_CUSTOMER_TMP.update_user
  is '更新人';
comment on column INTERF_CFETS_CUSTOMER_TMP.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table INTERF_CFETS_CUSTOMER_TMP
  add constraint PK_INTERF_CFETS_CUSTOMER_TMP primary key (PARTY_ID)
  using index
  ;

-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('INTERF_CFETS_PARTY_RELA_TMP') ;
    if num > 0 then
        execute immediate 'drop table INTERF_CFETS_PARTY_RELA_TMP' ;
    end if;
end;
/
create table INTERF_CFETS_PARTY_RELA_TMP
(
  rela_id            VARCHAR2(32) not null,
  mparty_customer_id VARCHAR2(32),
  sparty_customer_id VARCHAR2(32),
  create_user        VARCHAR2(20) not null,
  create_time        TIMESTAMP(6) not null,
  create_dept        VARCHAR2(32),
  update_user        VARCHAR2(20) not null,
  update_time        TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table INTERF_CFETS_PARTY_RELA_TMP
  is '外汇交易中心_客户信息_银行机构关系';
-- Add comments to the columns
comment on column INTERF_CFETS_PARTY_RELA_TMP.rela_id
  is '关系代码';
comment on column INTERF_CFETS_PARTY_RELA_TMP.mparty_customer_id
  is '主交易机构ID，6位编号';
comment on column INTERF_CFETS_PARTY_RELA_TMP.sparty_customer_id
  is '从交易机构ID，6位编号';
comment on column INTERF_CFETS_PARTY_RELA_TMP.create_user
  is '创建人';
comment on column INTERF_CFETS_PARTY_RELA_TMP.create_time
  is '创建时间';
comment on column INTERF_CFETS_PARTY_RELA_TMP.create_dept
  is '创建部门';
comment on column INTERF_CFETS_PARTY_RELA_TMP.update_user
  is '更新人';
comment on column INTERF_CFETS_PARTY_RELA_TMP.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table INTERF_CFETS_PARTY_RELA_TMP
  add constraint PK_INTERF_CFETS_PARTY_RELA_TMP primary key (RELA_ID)
  using index
  ;

-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('INTERF_CFETS_TRADER_TMP') ;
    if num > 0 then
        execute immediate 'drop table INTERF_CFETS_TRADER_TMP' ;
    end if;
end;
/
create table INTERF_CFETS_TRADER_TMP
(
  trader_id         VARCHAR2(50) not null,
  party_customer_id VARCHAR2(32),
  trader_name       VARCHAR2(1000),
  telphone_no       VARCHAR2(50),
  tel_no            VARCHAR2(50),
  fax_no            VARCHAR2(50),
  msn               VARCHAR2(50),
  email             VARCHAR2(50),
  address           VARCHAR2(1000),
  post_code         VARCHAR2(50),
  remark            VARCHAR2(1000),
  create_user       VARCHAR2(20) not null,
  create_time       TIMESTAMP(6) not null,
  create_dept       VARCHAR2(32),
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
)
;
-- Add comments to the table
comment on table INTERF_CFETS_TRADER_TMP
  is '外汇交易中心_客户信息_银行交易员';
-- Add comments to the columns
comment on column INTERF_CFETS_TRADER_TMP.trader_id
  is '交易员ID';
comment on column INTERF_CFETS_TRADER_TMP.party_customer_id
  is '交易机构ID，6位编号';
comment on column INTERF_CFETS_TRADER_TMP.trader_name
  is '交易员姓名';
comment on column INTERF_CFETS_TRADER_TMP.telphone_no
  is '座机、手机号码';
comment on column INTERF_CFETS_TRADER_TMP.tel_no
  is '联系电话';
comment on column INTERF_CFETS_TRADER_TMP.fax_no
  is '传真';
comment on column INTERF_CFETS_TRADER_TMP.msn
  is 'MSN';
comment on column INTERF_CFETS_TRADER_TMP.email
  is '电子邮箱';
comment on column INTERF_CFETS_TRADER_TMP.address
  is '地址';
comment on column INTERF_CFETS_TRADER_TMP.post_code
  is '邮政编号';
comment on column INTERF_CFETS_TRADER_TMP.remark
  is '备注';
comment on column INTERF_CFETS_TRADER_TMP.create_user
  is '创建人';
comment on column INTERF_CFETS_TRADER_TMP.create_time
  is '创建时间';
comment on column INTERF_CFETS_TRADER_TMP.create_dept
  is '创建部门';
comment on column INTERF_CFETS_TRADER_TMP.update_user
  is '更新人';
comment on column INTERF_CFETS_TRADER_TMP.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table INTERF_CFETS_TRADER_TMP
  add constraint PK_INTERF_CFETS_TRADER_TMP primary key (TRADER_ID)
  using index
  ;


 --系统参数
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INV', 'CFETS_LOCAL_PATH', 'D:\testFile', '  ', '  ', 'CFETS文件下载文件本地存放路径', 'system', null, sysdate, 'system', sysdate);

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INV', 'CFETS_CUSTOMER_PATH', 'http://192.168.31.129:8899/bridge/ftp/listCfetsCpySrc', '  ', '  ', 'CFETS交易成员基本信息批处理接口地址', 'system', null, sysdate, 'system', sysdate);

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INV', 'CFETS_ERR_MSG_PATH', 'http://192.168.31.129:8899/bridge/ftp/analyticalErrorCode', '  ', '  ', 'CFETS交易成员错误代码对照表批处理接口地址', 'system', null, sysdate, 'system', sysdate);

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INV', 'CFETS_TRADER_PATH', 'http://192.168.31.129:8899/bridge/ftp/analyticalCpyUser', '  ', '  ', 'CFETS交易成员通讯录批处理接口地址', 'system', null, sysdate, 'system', sysdate);

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INV', 'CFETS_CUSTOMER_RELA_PATH', 'http://192.168.31.129:8899/bridge/ftp/analyticalExchmemberRelation', '  ', '  ', 'CFETS机构主从信息批处理接口地址', 'system', null, sysdate, 'system', sysdate);



--批处理

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('20002', 'INV', 'CFETS银行成员信息表数据更新', null, 'cfetsCustomerTask', 1, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('20003', 'INV', 'CFETS银行成员关联信息表数据更新', null, 'cfetsCustomerRelaTask', 1, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('20004', 'INV', 'CFETS银行交易员信息表数据更新', null, 'cfetsTraderTask', 1, 'system', null, sysdate, 'system', sysdate, null);

----------------------------------------------------------------------页面草稿去除-----------------------------------------------------------------
insert into SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO, PAGE_ID)
values ('A96EA0A5352F043FE0538C5AA8C1223A', 'CfetsBondDealQuotedPrice002');

insert into SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO, PAGE_ID)
values ('A96EA0A5352F043FE0538C5AA8C2223A', 'CfetsPortfolioAcctRela002');

insert into SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO, PAGE_ID)
values ('A96EA0A5352F043FE0538C5AA8C3223A', 'CfetsPortfolioAcctRela003');

insert into SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO, PAGE_ID)
values ('A96EA0A5352F043FE0538C5AA8C4223A', 'CfetsRepoDealQuotedPrice002');


insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYS', 'cfets_server_url', 'http://127.0.0.1:8899/bridge', null, null, '对话报价、请求报价、质押式回购报价服务器地址', 'system', null, sysdate, 'admin', sysdate);


--------------mq配置-------------------------

insert into sys_mq_exchange (EXCHANGE_NAME, IS_DURABLE, EXCHANGE_TYPE, REMARK)
values ('exchange.fanout.yitihua.bankup', '01', '02', null);

insert into sys_mq_queue (QUEUE_NAME, IS_DURABLE, IS_AUTO_DELETE, IS_EXCLUSIVE, REMARK)
values ('queue.bankup.trade', '01', '02', '02', null);

insert into sys_mq_route (ROUTE_NAME, EXCHANGE_NAME, QUEUE_NAME, REMARK)
values ('yitihua.bankup.dispatch', 'exchange.fanout.yitihua.bankup', 'queue.bankup.trade', null);

Alter Table TRD_TRADE_FEE_DETAIL Add REGIST_ORG varchar2(50);
alter table TRD_TRADE_FEE_DETAIL modify REGIST_ORG default '999999' not null;
comment on column TRD_TRADE_FEE_DETAIL.REGIST_ORG
  is '更新时间';

























