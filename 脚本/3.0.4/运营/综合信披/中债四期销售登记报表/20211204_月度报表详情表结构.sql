-- Create table
DROP TABLE CHB_MONTH_PRD_POSITIONDETAIL;
create table CHB_MONTH_PRD_POSITIONDETAIL
(
  posuuid           VARCHAR2(36) not null,
  prodid            VARCHAR2(150),
  trem             VARCHAR2(10),
  detailNo         VARCHAR2(50),
  ccy               VARCHAR2(3),
  raisemode         VARCHAR2(2),
  ivstorientation   VARCHAR2(2),
  termtype          VARCHAR2(20),
  termdays         NUMBER(24,0),
  open_type         VARCHAR2(2),
  operate_mode      VARCHAR2(2),
  profit_type       VARCHAR2(2),
  sale_target       VARCHAR2(50),
  is_transfer         VARCHAR2(2),
  is_fhxgcp           VARCHAR2(2),
  is_yllccp           VARCHAR2(2),
  con_prd_traget    VARCHAR2(2),
  ord_identified    VARCHAR2(2),
  dirtyamtsum       NUMBER(24,2),
  cleanamtsum       NUMBER(24,2),
  redeemamt         NUMBER(24,2),
  terminationamt    NUMBER(24,2),
  endamtsum_oth     NUMBER(24,2),
  customeramt       NUMBER(24,2),
  redeemintamt      NUMBER(24,2),
  terminationintamt NUMBER(24,2),
  cashamt           NUMBER(24,2),
  durationflag      VARCHAR2(2),
  vdate             DATE,
  mdate             DATE,
  openduration      VARCHAR2(2),
  org_code          VARCHAR2(20),
  dept_code         VARCHAR2(20),
  cash_mana_cate    VARCHAR2(2),
  payamtsum         NUMBER(24,2),
  closetermday      NUMBER(24,2),
  bankamt         NUMBER(24,2),
  trustfeeamt     NUMBER(24,2),
  salefeeamt      NUMBER(24,2),
  otherfeeamt     NUMBER(24,2),
  manafeeamt      NUMBER(24,2),
  floatfeeamt     NUMBER(24,2),
  is_new_prd        VARCHAR2(2),
  yield_e           NUMBER(24,2)
);
-- Add comments to the table
comment on table CHB_MONTH_PRD_POSITIONDETAIL
  is '银行月度报表产品头寸';
-- Add comments to the columns
comment on column CHB_MONTH_PRD_POSITIONDETAIL.prodid
  is '产品代码';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.TREM
  is '期次';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.ccy
  is '币种 CNY:人民币/USD:美元/EUR 欧元/QTBZ 其他币种';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.raisemode
  is '募集方式 01：公募 02：私募';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.open_type
  is '开放类型 01：现金管理类产品 02：封闭期在半年以上的定期开放式产品 99 : 其他';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.ivstorientation
  is '投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.termtype
  is '期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.termdays
  is '期限（天）';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.is_transfer
  is '是否母行划转产品';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.is_fhxgcp
  is '是否合规新产品';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.is_yllccp
  is '是否养老理财产品';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.operate_mode
  is '运行方式01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.profit_type
  is '产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.sale_target
  is '客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.con_prd_traget
  is '结构性产品挂钩标的';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.ord_identified
  is '投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.dirtyamtsum
  is '本期总募集金额';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.cleanamtsum
  is '本期净募集金额';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.redeemamt
  is '本期赎回本金';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.terminationamt
  is '提前终止/到期本金';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.endamtsum_oth
  is '期末余额';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.customeramt
  is '本期客户实现收益总额';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.redeemintamt
  is '本期赎回利息';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.terminationintamt
  is '本期提前终止/到期 利息';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.cashamt
  is '本期现金分红/中间付息';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.bankamt
  is '本期银行实现收益总额';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.trustfeeamt
  is '本期本机构托管费';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.salefeeamt
  is '本期本机构销售服务费';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.otherfeeamt
  is '本期本机构其他费用';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.manafeeamt
  is '本期本机构固定管理';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.floatfeeamt
  is '本期浮动费/中收';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.durationflag
  is '存续状态 01存续/02终止/03未起息';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.vdate
  is '起息日';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.mdate
  is '到期日';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.openduration
  is '开放期限 01每日/02 180天以上/03 其他';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.org_code
  is '机构代码';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.dept_code
  is '部门代码';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.cash_mana_cate
  is '是否现金管理类';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.payamtsum
  is '本期兑付金额';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.closetermday
  is '封闭式产品期限';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.is_new_prd
  is '是否新产品';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.yield_e
  is '加权率';
-- Create/Recreate primary, unique and foreign key constraints
alter table CHB_MONTH_PRD_POSITIONDETAIL
  add constraint CHB_MONTH_PRD_POSIDETAIL_PK primary key (POSUUID);



DROP TABLE CHB_MONTH_ASSET_POSITIONDETAIL;
create table CHB_MONTH_ASSET_POSITIONDETAIL(
  posuuid          VARCHAR2(36) not null,
  trem             VARCHAR2(10),
  detailNo         VARCHAR2(50),
  invest_variety   VARCHAR2(4),
  asset_code       VARCHAR2(50),
  assettype        VARCHAR2(4) not null,
  prod_id          VARCHAR2(150),
  is_fhxgcp        VARCHAR2(2),
  assetvalue_a     NUMBER(28,6),
  assetvalue_b     NUMBER(28,6),
  vdate             DATE,
  mdate             DATE,
  openPlan             DATE,
  SUBJECT_NAME     VARCHAR2(300),
  rival_party      VARCHAR2(300),
  rate             NUMBER(28,6),
  ccy              VARCHAR2(4),
  industry1        VARCHAR2(4),
  industry2        VARCHAR2(10),
  industry3        VARCHAR2(15),
  industry4        VARCHAR2(20),
  delay_pay_amt NUMBER(30,2),
  cost_amt    NUMBER(30,2),
  float_ingpl NUMBER(30,2),
  intincexp   NUMBER(30,2),
  INTADJEXP_ADD NUMBER(30,2),
  LOSS_AMT      NUMBER(30,2),
  in_asset_type    VARCHAR2(2),
  pppflag          VARCHAR2(4),
  monit_type       VARCHAR2(2),
  manage_type      VARCHAR2(2),
  is_govern_fund1  VARCHAR2(2),
  privatefund_flag VARCHAR2(2),
  privateprd_flag  VARCHAR2(2),
  bondtostock_flag VARCHAR2(2),
  CREDIT_GRADE     VARCHAR2(50),
  MAIN_GRADE       VARCHAR2(50),
  TYPE_FINAL       VARCHAR2(50),
  org_asset_type1         VARCHAR2(50),
  org_asset_type2         VARCHAR2(50),
  org_asset_type3         VARCHAR2(50),
  rating           VARCHAR2(4),
  assettype2       VARCHAR2(50),
  org_code         VARCHAR2(20),
  dept_code        VARCHAR2(20),
  old_asset_code   VARCHAR2(50)
);
-- Add comments to the table
comment on table CHB_MONTH_ASSET_POSITIONDETAIL
  is '月度报表资产头寸';
-- Add comments to the columns
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.posuuid
  is '头寸编号';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.trem
  is '统计日期';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.detailNo
  is '月报分类';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.invest_variety
  is '投资品种';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.asset_code
  is '资产ID';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.assettype
  is '资产一级分类';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.prod_id
  is '产品代码';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.is_fhxgcp
  is '是否合规新产品';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.vdate
  is '资产起息日';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.mdate
  is '资产到期日';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.assetvalue_a
  is '穿透前资产价值';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.assetvalue_b
  is '穿透后资产价值';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.industry1
  is '所属行业';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.cost_amt
  is '当日成本';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.float_ingpl
  is '当日公允价值变动';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.intincexp
  is '当日累计应计利息余额';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.delay_pay_amt
  is '待清算款';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.in_asset_type
  is '行类一级分类';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.pppflag
  is '公共PPP';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.monit_type
  is '是否投向地方政府平台';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.manage_type
  is '管理方式';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.is_govern_fund1
  is '是否政府投资基金';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.privatefund_flag
  is '是否投向投向“金融资产投资公司或其附属机构发行的私募股权投资基金';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.privateprd_flag
  is '是否投向金融资产投资公司发行的私募资产管理产品';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.bondtostock_flag
  is '是否投向市场化债转股相关产品的部分';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.rating
  is '评级结果';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.assettype2
  is '资产二级分类';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.org_code
  is '机构代码';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.dept_code
  is '部门代码';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.old_asset_code
  is '穿透前原始资产代码';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.is_fhxgcp
  is '是否合规新产品';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CHB_MONTH_ASSET_POSITIONDETAIL
  add constraint CHB_MONTH_ASSET_POSDETAIL_PK primary key (POSUUID);
