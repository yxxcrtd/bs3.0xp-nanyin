prompt PL/SQL Developer Export User Objects for user IBU_MAST2@192.168.90.100
prompt Created by Administrator on 2020年6月12日星期五
set define off
spool IbuUserObjects.log

prompt
prompt Creating table IBU_INTF_ATTR_CONFIG
prompt ===================================
prompt
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
comment on table IBU_INTF_ATTR_CONFIG
  is '银行间上行报价属性配置';
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
alter table IBU_INTF_ATTR_CONFIG
  add constraint PK_IBU_INTF_ATTR_CONFIG primary key (ARRT_GRP_ID, ATTR_ID);

prompt
prompt Creating table IBU_INTF_ERR_MSG
prompt ===============================
prompt
create table IBU_INTF_ERR_MSG
(
  err_code    VARCHAR2(50) not null,
  err_msg     VARCHAR2(1000),
  create_user VARCHAR2(20) not null,
  create_dept VARCHAR2(32),
  create_time TIMESTAMP(6) not null,
  update_user VARCHAR2(20) not null,
  update_time TIMESTAMP(6) not null
)
;
comment on table IBU_INTF_ERR_MSG
  is '银行间上行错误码';
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
create unique index PK_IBU_INTF_ERR_MSG1 on IBU_INTF_ERR_MSG (ERR_CODE);
alter table IBU_INTF_ERR_MSG
  add constraint PK_IBU_INTF_ERR_MSG primary key (ERR_CODE);

prompt
prompt Creating table IBU_INTF_QUOTE_SCHEME
prompt ====================================
prompt
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
comment on table IBU_INTF_QUOTE_SCHEME
  is '银行间上行报价路由方案';
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
alter table IBU_INTF_QUOTE_SCHEME
  add constraint PK_IBU_INTF_QUOTE_SCHEME primary key (SCHEME_ID, ARRT_GRP_ID);

prompt
prompt Creating table IBU_INTF_SCHEME
prompt ==============================
prompt
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
comment on table IBU_INTF_SCHEME
  is '银行间上行路由配置方案';
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
alter table IBU_INTF_SCHEME
  add constraint PK_IBU_INTF_SCHEME primary key (SCHEME_ID);

prompt
prompt Creating table IBU_PORTFOLIO_ACCT_RELA
prompt ======================================
prompt
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
comment on table IBU_PORTFOLIO_ACCT_RELA
  is '银行间账户组合关系';
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
alter table IBU_PORTFOLIO_ACCT_RELA
  add constraint PK_IBU_PORTFOLIO_ACCT_RELA primary key (PORTFOLIO_ID, PARTY_ID, CFETS_ACCT_TYPE);

prompt
prompt Creating table IBU_QUOTE_BOND
prompt =============================
prompt
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
  branch_id          VARCHAR2(50)
)
;
comment on table IBU_QUOTE_BOND
  is '现券银行间报价';
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
create index IDX_IBU_QUOTE_BOND_CFETS on IBU_QUOTE_BOND (CFETS_QUOTE_ID);
create index IDX_IBU_QUOTE_BOND_QP on IBU_QUOTE_BOND (QUOTE_DATE, P_QUOTE_ID);
alter table IBU_QUOTE_BOND
  add constraint PK_IBU_QUOTE_BOND primary key (QUOTE_ID);

prompt
prompt Creating table IBU_QUOTE_BOND_DET
prompt =================================
prompt
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
  calculate_field VARCHAR2(50)
)
;
comment on table IBU_QUOTE_BOND_DET
  is '现券银行间报价明细';
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
create index IDX_IBU_QUOTE_BOND_DET_QI on IBU_QUOTE_BOND_DET (QUOTE_ID);
alter table IBU_QUOTE_BOND_DET
  add constraint PK_IBU_QUOTE_BOND_DET primary key (DET_ID);

prompt
prompt Creating table IBU_QUOTE_BOND_DET_HIS
prompt =====================================
prompt
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
  calculate_field VARCHAR2(50)
)
;
comment on table IBU_QUOTE_BOND_DET_HIS
  is '现券银行间报价明细历史';
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
create index IDX_IBU_QUOTE_BOND_DET_HIS_QV on IBU_QUOTE_BOND_DET_HIS (QUOTE_ID, VERSION);
alter table IBU_QUOTE_BOND_DET_HIS
  add constraint PK_IBU_QUOTE_BOND_DET_HIS primary key (DET_ID, VERSION);

prompt
prompt Creating table IBU_QUOTE_BOND_HIS
prompt =================================
prompt
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
  branch_id          VARCHAR2(50)
)
;
comment on table IBU_QUOTE_BOND_HIS
  is '现券银行间报价历史';
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
create index IDX_IBU_QUOTE_BOND_HIS_QVP on IBU_QUOTE_BOND_HIS (QUOTE_DATE, VERSION, P_QUOTE_ID);
alter table IBU_QUOTE_BOND_HIS
  add constraint PK_IBU_QUOTE_BOND_HIS primary key (QUOTE_ID, VERSION);

prompt
prompt Creating table IBU_QUOTE_CREP
prompt =============================
prompt
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
  branch_id          VARCHAR2(50)
)
;
comment on table IBU_QUOTE_CREP
  is '质押式回购银行间报价';
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
create index IDX_IBU_QUOTE_CREP_CFETS on IBU_QUOTE_CREP (CFETS_QUOTE_ID);
create index IDX_IBU_QUOTE_CREP_QVP on IBU_QUOTE_CREP (QUOTE_DATE, P_QUOTE_ID);
alter table IBU_QUOTE_CREP
  add constraint PK_IBU_QUOTE_CREP primary key (QUOTE_ID);

prompt
prompt Creating table IBU_QUOTE_CREP_HIS
prompt =================================
prompt
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
  branch_id          VARCHAR2(50)
)
;
comment on table IBU_QUOTE_CREP_HIS
  is '质押式回购银行间报价历史';
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
create index IDX_IBU_QUOTE_CREP_HIS_QVP on IBU_QUOTE_CREP_HIS (QUOTE_DATE, VERSION, P_QUOTE_ID);
alter table IBU_QUOTE_CREP_HIS
  add constraint PK_IBU_QUOTE_CREP_HIS primary key (QUOTE_ID, VERSION);

prompt
prompt Creating table IBU_QUOTE_CREP_PLEDGE
prompt ====================================
prompt
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
comment on table IBU_QUOTE_CREP_PLEDGE
  is '回购报价质押品';
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
alter table IBU_QUOTE_CREP_PLEDGE
  add constraint PK_IBU_QUOTE_CREP_PLEDGE primary key (QUOTE_ID, BRANCH, PLEDGE_FINPROD_ID);

prompt
prompt Creating table IBU_QUOTE_CREP_PLEDGE_HIS
prompt ========================================
prompt
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
comment on table IBU_QUOTE_CREP_PLEDGE_HIS
  is '回购报价质押品历史';
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
alter table IBU_QUOTE_CREP_PLEDGE_HIS
  add constraint PK_IBU_QUOTE_CREP_PLEDGE_HIS primary key (QUOTE_ID, VERSION, BRANCH, PLEDGE_FINPROD_ID);

prompt
prompt Creating table IBU_QUOTE_ERRLOG
prompt ===============================
prompt
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
comment on table IBU_QUOTE_ERRLOG
  is '报价错误日志表';
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
alter table IBU_QUOTE_ERRLOG
  add constraint PK_IBU_QUOTE_ERRLOG primary key (QUOTE_ID, VERSION);

prompt
prompt Creating table MST_CFETS_CUSTOMER_INFO
prompt ======================================
prompt
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
)
;
comment on table MST_CFETS_CUSTOMER_INFO
  is '客户信息_银行机构';
comment on column MST_CFETS_CUSTOMER_INFO.party_id
  is '交易成员ID，6位编号';
comment on column MST_CFETS_CUSTOMER_INFO.party_customer_id
  is '交易机构ID，21位编号';
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
alter table MST_CFETS_CUSTOMER_INFO
  add constraint PK_MST_CFETS_CUSTOMER_INFO primary key (PARTY_ID);

prompt
prompt Creating table MST_CFETS_CUSTOMER_RELA
prompt ======================================
prompt
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
comment on table MST_CFETS_CUSTOMER_RELA
  is '客户信息_银行机构关系';
comment on column MST_CFETS_CUSTOMER_RELA.rela_id
  is '关系代码';
comment on column MST_CFETS_CUSTOMER_RELA.mparty_id
  is '主交易成员ID，6位编号';
comment on column MST_CFETS_CUSTOMER_RELA.sparty_id
  is '从交易成员ID，6位编号';
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
alter table MST_CFETS_CUSTOMER_RELA
  add constraint PK_MST_CFETS_CUSTOMER_RELA primary key (RELA_ID);

prompt
prompt Creating table MST_CFETS_CUSTOMER_TRADER
prompt ========================================
prompt
create table MST_CFETS_CUSTOMER_TRADER
(
  trader_id   VARCHAR2(32) not null,
  party_id    VARCHAR2(32),
  mparty_id   VARCHAR2(32),
  trader_name VARCHAR2(32),
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
comment on table MST_CFETS_CUSTOMER_TRADER
  is '客户信息_银行交易员';
comment on column MST_CFETS_CUSTOMER_TRADER.trader_id
  is '交易员ID';
comment on column MST_CFETS_CUSTOMER_TRADER.party_id
  is '交易成员ID，6位编号';
comment on column MST_CFETS_CUSTOMER_TRADER.mparty_id
  is '主交易成员ID，6位编号';
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
alter table MST_CFETS_CUSTOMER_TRADER
  add constraint PK_MST_CFETS_CUSTOMER_TRADER primary key (TRADER_ID);

prompt
prompt Creating sequence SEQ_IBU_QUOTE_ERRLOG
prompt ======================================
prompt
create sequence SEQ_IBU_QUOTE_ERRLOG
minvalue 1
maxvalue 9999999999999999999999999
start with 86
increment by 1
nocache;


prompt Done
spool off
set define on
