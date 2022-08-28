DROP TABLE dis_large_redemption CASCADE CONSTRAINTS;
CREATE TABLE dis_large_redemption (
    redemption_id   VARCHAR2(60) NOT NULL,
    prod_id         VARCHAR2(60) NOT NULL,
    prod_regist_no  VARCHAR2(50),
    is_sales_agency VARCHAR2(10),
    deal_date       DATE,
    trigger_remark  VARCHAR2(512),
    reject_remark   VARCHAR2(512),
    rpt_issue_date  DATE,
    data_status     VARCHAR2(10),
    data_source     VARCHAR2(10),
    create_user     VARCHAR2(20),
    create_time     TIMESTAMP,
    update_user     VARCHAR2(20),
    update_time     TIMESTAMP,
    create_dept     VARCHAR2(32)
);

COMMENT ON TABLE dis_large_redemption IS
    '产品巨额赎回信息表';

COMMENT ON COLUMN dis_large_redemption.redemption_id IS
    '赎回编号,主键';

COMMENT ON COLUMN dis_large_redemption.prod_id IS
    '子产品代码';

COMMENT ON COLUMN dis_large_redemption.prod_regist_no IS
    '产品登记编码';

COMMENT ON COLUMN dis_large_redemption.is_sales_agency IS
    '是否有代销机构，数据字典，是否';

COMMENT ON COLUMN dis_large_redemption.deal_date IS
    '赎回日期';

COMMENT ON COLUMN dis_large_redemption.trigger_remark IS
    '触发的情形说明文字';

COMMENT ON COLUMN dis_large_redemption.reject_remark IS
    '拒绝赎回说明（文字）';

COMMENT ON COLUMN dis_large_redemption.rpt_issue_date IS
    '报告发布日期';

COMMENT ON COLUMN dis_large_redemption.data_status IS
    '数据状态';

COMMENT ON COLUMN dis_large_redemption.data_source IS
    '数据来源';

COMMENT ON COLUMN dis_large_redemption.create_user IS
    '创建人';

COMMENT ON COLUMN dis_large_redemption.create_time IS
    '创建时间';

COMMENT ON COLUMN dis_large_redemption.update_user IS
    '更新人';

COMMENT ON COLUMN dis_large_redemption.update_time IS
    '更新时间';

COMMENT ON COLUMN dis_large_redemption.create_dept IS
    '创建部门';

ALTER TABLE dis_large_redemption ADD CONSTRAINT dis_large_redemption_pk PRIMARY KEY ( redemption_id );

DROP TABLE dis_nostd_creditor CASCADE CONSTRAINTS;
CREATE TABLE dis_nostd_creditor (
    nostd_asset_id       VARCHAR2(50) NOT NULL,
    prod_id              VARCHAR2(60) NOT NULL,
    eff_date             DATE,
    eff_end_date         DATE,
    financier            VARCHAR2(128),
    project_no           VARCHAR2(50),
    project_name         VARCHAR2(128),
    rem_financing_period VARCHAR2(128),
    income_distribute    VARCHAR2(512),
    trd_construct        VARCHAR2(1024),
    risk_info            VARCHAR2(1024),
    data_status          VARCHAR2(10),
    data_source          VARCHAR2(10),
    create_user          VARCHAR2(20),
    create_time          TIMESTAMP,
    update_user          VARCHAR2(20),
    update_time          TIMESTAMP,
    create_dept          VARCHAR2(32)
);

COMMENT ON TABLE dis_nostd_creditor IS
    '产品投资非标资产明细信息';

COMMENT ON COLUMN dis_nostd_creditor.nostd_asset_id IS
    '非标资产编号，主键';

COMMENT ON COLUMN dis_nostd_creditor.prod_id IS
    '主产品代码';

COMMENT ON COLUMN dis_nostd_creditor.eff_date IS
    '生效日期';

COMMENT ON COLUMN dis_nostd_creditor.eff_end_date IS
    '生效截至日';

COMMENT ON COLUMN dis_nostd_creditor.financier IS
    '融资客户';

COMMENT ON COLUMN dis_nostd_creditor.project_no IS
    '项目编号';

COMMENT ON COLUMN dis_nostd_creditor.project_name IS
    '项目名称';

COMMENT ON COLUMN dis_nostd_creditor.rem_financing_period IS
    '剩余赎回期限';

COMMENT ON COLUMN dis_nostd_creditor.income_distribute IS
    '到期收益分配';

COMMENT ON COLUMN dis_nostd_creditor.trd_construct IS
    '交易结构';

COMMENT ON COLUMN dis_nostd_creditor.risk_info IS
    '风险状况';

COMMENT ON COLUMN dis_nostd_creditor.data_status IS
    '数据状态';

COMMENT ON COLUMN dis_nostd_creditor.data_source IS
    '数据来源';

COMMENT ON COLUMN dis_nostd_creditor.create_user IS
    '创建人';

COMMENT ON COLUMN dis_nostd_creditor.create_time IS
    '创建时间';

COMMENT ON COLUMN dis_nostd_creditor.update_user IS
    '更新人';

COMMENT ON COLUMN dis_nostd_creditor.update_time IS
    '更新时间';

COMMENT ON COLUMN dis_nostd_creditor.create_dept IS
    '创建部门';

ALTER TABLE dis_nostd_creditor ADD CONSTRAINT dis_nostd_creditor_detail_pk PRIMARY KEY ( nostd_asset_id );

DROP TABLE dis_prod_adjust CASCADE CONSTRAINTS;
CREATE TABLE dis_prod_adjust (
    prod_adjust_id    VARCHAR2(60) NOT NULL,
    prod_id           VARCHAR2(60) NOT NULL,
    prod_regist_no    VARCHAR2(50),
    is_sales_agency   VARCHAR2(10),
    prod_adj_remark   NVARCHAR2(1024),
    prod_adj_remark2  NVARCHAR2(1024),
    adj_field         VARCHAR2(512),
    pre_adjust        VARCHAR2(512),
    after_adjust      VARCHAR2(512),
    eff_date          DATE,
    field_adj_remark  VARCHAR2(512),
    field_adj_remark2 VARCHAR2(512),
    rpt_issue_date    DATE,
    data_status       VARCHAR2(10),
    data_source       VARCHAR2(10),
    create_user       VARCHAR2(20),
    create_time       TIMESTAMP,
    update_user       VARCHAR2(20),
    update_time       TIMESTAMP,
    create_dept       VARCHAR2(32)
);

COMMENT ON TABLE dis_prod_adjust IS
    '产品要素调整表';

COMMENT ON COLUMN dis_prod_adjust.prod_adjust_id IS
    '产品调整编号，主键';

COMMENT ON COLUMN dis_prod_adjust.prod_id IS
    '子产品代码';

COMMENT ON COLUMN dis_prod_adjust.prod_regist_no IS
    '产品登记编码';

COMMENT ON COLUMN dis_prod_adjust.is_sales_agency IS
    '是否有代销机构，数据字典，是否';

COMMENT ON COLUMN dis_prod_adjust.prod_adj_remark IS
    '此产品要素调整（补充性）描述文字1';

COMMENT ON COLUMN dis_prod_adjust.prod_adj_remark2 IS
    '此产品要素调整（补充性）描述文字2';

COMMENT ON COLUMN dis_prod_adjust.adj_field IS
    '调整要素';

COMMENT ON COLUMN dis_prod_adjust.pre_adjust IS
    '调整前';

COMMENT ON COLUMN dis_prod_adjust.after_adjust IS
    '调整后';

COMMENT ON COLUMN dis_prod_adjust.eff_date IS
    '生效时间';

COMMENT ON COLUMN dis_prod_adjust.field_adj_remark IS
    '调整此要素（补充说明文1）';

COMMENT ON COLUMN dis_prod_adjust.field_adj_remark2 IS
    '调整此要素（补充说明文2）';

COMMENT ON COLUMN dis_prod_adjust.rpt_issue_date IS
    '报告发布日期';

COMMENT ON COLUMN dis_prod_adjust.data_status IS
    '数据状态';

COMMENT ON COLUMN dis_prod_adjust.data_source IS
    '数据来源';

COMMENT ON COLUMN dis_prod_adjust.create_user IS
    '创建人';

COMMENT ON COLUMN dis_prod_adjust.create_time IS
    '创建时间';

COMMENT ON COLUMN dis_prod_adjust.update_user IS
    '更新人';

COMMENT ON COLUMN dis_prod_adjust.update_time IS
    '更新时间';

COMMENT ON COLUMN dis_prod_adjust.create_dept IS
    '创建部门';

ALTER TABLE dis_prod_adjust ADD CONSTRAINT dis_prod_adjust_pk PRIMARY KEY ( prod_adjust_id );

DROP TABLE dis_prod_asset CASCADE CONSTRAINTS;
CREATE TABLE dis_prod_asset (
    prod_asset_id      VARCHAR2(60) NOT NULL,
    prod_id            VARCHAR2(60) NOT NULL,
    fix_pre_ratio      NUMBER(5, 2),
    fix_after_ratio    NUMBER(5, 2),
    equity_pre_ratio   NUMBER(5, 2),
    equity_after_ratio NUMBER(5, 2),
    fd_pre_ratio       NUMBER(5, 2),
    fd_after_ratio     NUMBER(5, 2),
    mix_pre_ratio      NUMBER(5, 2),
    mix_after_ratio    NUMBER(5, 2),
    eff_date           DATE,
    eff_end_date       DATE,
    data_status        VARCHAR2(10),
    data_source        VARCHAR2(10),
    create_user        VARCHAR2(20),
    create_time        TIMESTAMP,
    update_user        VARCHAR2(20),
    update_time        TIMESTAMP,
    create_dept        VARCHAR2(32)
);

COMMENT ON TABLE dis_prod_asset IS
    '产品资产组合表';

COMMENT ON COLUMN dis_prod_asset.prod_asset_id IS
    '主键编号';

COMMENT ON COLUMN dis_prod_asset.prod_id IS
    '主产品代码';

COMMENT ON COLUMN dis_prod_asset.fix_pre_ratio IS
    '固定资产穿透前占总资产的比例';

COMMENT ON COLUMN dis_prod_asset.fix_after_ratio IS
    '固定资产穿透后占总资产的比例';

COMMENT ON COLUMN dis_prod_asset.equity_pre_ratio IS
    '权益类资产穿透前占总资产的比例';

COMMENT ON COLUMN dis_prod_asset.equity_after_ratio IS
    '权益类资产穿透后占总资产的比例';

COMMENT ON COLUMN dis_prod_asset.fd_pre_ratio IS
    '商品及金融衍生品资产穿透前占总资产的比例';

COMMENT ON COLUMN dis_prod_asset.fd_after_ratio IS
    '商品及金融衍生品资产穿透后占总资产的比例';

COMMENT ON COLUMN dis_prod_asset.mix_pre_ratio IS
    '混合类资产穿透前占总资产的比例';

COMMENT ON COLUMN dis_prod_asset.mix_after_ratio IS
    '混合类资产穿透后占总资产的比例';

COMMENT ON COLUMN dis_prod_asset.eff_date IS
    '生效日';

COMMENT ON COLUMN dis_prod_asset.eff_end_date IS
    '生效截至日';

COMMENT ON COLUMN dis_prod_asset.data_status IS
    '数据状态';

COMMENT ON COLUMN dis_prod_asset.data_source IS
    '数据来源，DATA_SOURCE';

COMMENT ON COLUMN dis_prod_asset.create_user IS
    '创建人';

COMMENT ON COLUMN dis_prod_asset.create_time IS
    '创建时间';

COMMENT ON COLUMN dis_prod_asset.update_user IS
    '更新人';

COMMENT ON COLUMN dis_prod_asset.update_time IS
    '更新时间';

COMMENT ON COLUMN dis_prod_asset.create_dept IS
    '创建部门';

ALTER TABLE dis_prod_asset ADD CONSTRAINT dis_prod_asset_pk PRIMARY KEY ( prod_asset_id );

DROP TABLE dis_prod_bonusplan CASCADE CONSTRAINTS;
CREATE TABLE dis_prod_bonusplan (
    bonus_plan_id           VARCHAR2(50) NOT NULL,
    prod_id                 VARCHAR2(60 BYTE) NOT NULL,
    bonus_mode              VARCHAR2(10 BYTE),
    sett_date               DATE,
    payment_date            DATE,
    achievement             NVARCHAR2(256),
    excess_achievement_prop NUMBER(6, 4),
    achievement_basis       NVARCHAR2(128),
    achievement_basis_prop  NUMBER(6, 4),
    data_status             VARCHAR2(10 BYTE),
    data_source             VARCHAR2(10 BYTE),
    create_user             VARCHAR2(20 BYTE),
    create_time             TIMESTAMP,
    update_user             VARCHAR2(20 BYTE),
    update_time             TIMESTAMP,
    create_dept             VARCHAR2(32 BYTE)
);

COMMENT ON TABLE dis_prod_bonusplan IS
    '信披：产品分红计划';

COMMENT ON COLUMN dis_prod_bonusplan.bonus_plan_id IS
    '分红计划编号';

COMMENT ON COLUMN dis_prod_bonusplan.prod_id IS
    '产品编号';

COMMENT ON COLUMN dis_prod_bonusplan.bonus_mode IS
    '分红方式：数据字典code:DIS_BONUS_MODE,01-份额分红后净值为1、02-到期兑付';

COMMENT ON COLUMN dis_prod_bonusplan.sett_date IS
    '收益分配基准日';

COMMENT ON COLUMN dis_prod_bonusplan.payment_date IS
    '红利发放日';

COMMENT ON COLUMN dis_prod_bonusplan.achievement IS
    '业绩报酬';

COMMENT ON COLUMN dis_prod_bonusplan.excess_achievement_prop IS
    '超额业绩报酬收取比例';

COMMENT ON COLUMN dis_prod_bonusplan.achievement_basis IS
    '业绩基准';

COMMENT ON COLUMN dis_prod_bonusplan.achievement_basis_prop IS
    '业绩比较基准比例';

COMMENT ON COLUMN dis_prod_bonusplan.data_status IS
    '数据状态：数据字典，系统模块-DIS，字典code：DIS_DATA_STATUS:E-有效，D-删除';

COMMENT ON COLUMN dis_prod_bonusplan.data_source IS
    '数据来源:数据字典code:DATA_SOURCE';

COMMENT ON COLUMN dis_prod_bonusplan.create_user IS
    '创建人';

COMMENT ON COLUMN dis_prod_bonusplan.create_time IS
    '创建时间';

COMMENT ON COLUMN dis_prod_bonusplan.update_user IS
    '更新人';

COMMENT ON COLUMN dis_prod_bonusplan.update_time IS
    '更新时间';

COMMENT ON COLUMN dis_prod_bonusplan.create_dept IS
    '创建部门';

ALTER TABLE dis_prod_bonusplan ADD CONSTRAINT dis_prd_bonus_plan_pk PRIMARY KEY ( bonus_plan_id );

DROP TABLE dis_prod_feediscount CASCADE CONSTRAINTS;
CREATE TABLE dis_prod_feediscount (
    prod_discount_id   VARCHAR2(60) NOT NULL,
    prod_id            VARCHAR2(60) NOT NULL,
    prod_regist_no     VARCHAR2(50),
    is_sales_agency    VARCHAR2(10),
    prod_dis_remark    NVARCHAR2(1024),
    feename            VARCHAR2(128),
    pre_feerate        NUMBER(6, 4),
    after_feerate      NUMBER(6, 4),
    start_date         DATE,
    end_date           DATE,
    this_pre_feerate   NUMBER(6, 4),
    this_after_feerate NUMBER(6, 4),
    this_end_feerate   NUMBER(6, 4),
    fee_dis_remark     NVARCHAR2(1024),
    rpt_issue_date     DATE,
    data_status        VARCHAR2(10),
    data_source        VARCHAR2(10),
    create_user        VARCHAR2(20),
    create_time        TIMESTAMP,
    update_user        VARCHAR2(20),
    update_time        TIMESTAMP,
    create_dept        VARCHAR2(32)
);

COMMENT ON TABLE dis_prod_feediscount IS
    '产品费率优惠表';

COMMENT ON COLUMN dis_prod_feediscount.prod_discount_id IS
    '优惠编号，主键';

COMMENT ON COLUMN dis_prod_feediscount.prod_id IS
    '子产品代码';

COMMENT ON COLUMN dis_prod_feediscount.prod_regist_no IS
    '产品登记编码';

COMMENT ON COLUMN dis_prod_feediscount.is_sales_agency IS
    '是否有代销机构，数据字典，是否';

COMMENT ON COLUMN dis_prod_feediscount.prod_dis_remark IS
    '此产品优惠调整（补充性）描述文字';

COMMENT ON COLUMN dis_prod_feediscount.feename IS
    '费用名称';

COMMENT ON COLUMN dis_prod_feediscount.pre_feerate IS
    '优惠前费率';

COMMENT ON COLUMN dis_prod_feediscount.after_feerate IS
    '优惠后费率';

COMMENT ON COLUMN dis_prod_feediscount.start_date IS
    '优化起始时间';

COMMENT ON COLUMN dis_prod_feediscount.end_date IS
    '优惠截至时间';

COMMENT ON COLUMN dis_prod_feediscount.this_pre_feerate IS
    '本次优惠前费率';

COMMENT ON COLUMN dis_prod_feediscount.this_after_feerate IS
    '本次优惠后费率';

COMMENT ON COLUMN dis_prod_feediscount.this_end_feerate IS
    '本次优惠结束后费率';

COMMENT ON COLUMN dis_prod_feediscount.fee_dis_remark IS
    '对此费率优惠调整（补充性）描述文字';

COMMENT ON COLUMN dis_prod_feediscount.rpt_issue_date IS
    '报告发布日期';

COMMENT ON COLUMN dis_prod_feediscount.data_status IS
    '数据状态';

COMMENT ON COLUMN dis_prod_feediscount.data_source IS
    '数据来源';

COMMENT ON COLUMN dis_prod_feediscount.create_user IS
    '创建人';

COMMENT ON COLUMN dis_prod_feediscount.create_time IS
    '创建时间';

COMMENT ON COLUMN dis_prod_feediscount.update_user IS
    '更新人';

COMMENT ON COLUMN dis_prod_feediscount.update_time IS
    '更新时间';

COMMENT ON COLUMN dis_prod_feediscount.create_dept IS
    '创建部门';

ALTER TABLE dis_prod_feediscount ADD CONSTRAINT dis_prod_feediscount_pk PRIMARY KEY ( prod_discount_id );

DROP TABLE dis_prod_nocreate CASCADE CONSTRAINTS;
CREATE TABLE dis_prod_nocreate (
    prod_regist_no    VARCHAR2(50) NOT NULL,
    nocreate_reason   NVARCHAR2(1024),
    is_sales_agency   VARCHAR2(10),
    report_issue_date DATE,
    data_status       VARCHAR2(10),
    data_source       VARCHAR2(10),
    create_user       VARCHAR2(20),
    create_time       TIMESTAMP,
    update_user       VARCHAR2(20),
    update_time       TIMESTAMP,
    create_dept       VARCHAR2(32)
);

COMMENT ON TABLE dis_prod_nocreate IS
    '产品不成立信息表';

COMMENT ON COLUMN dis_prod_nocreate.prod_regist_no IS
    '产品登记编码';

COMMENT ON COLUMN dis_prod_nocreate.nocreate_reason IS
    '不成立原因';

COMMENT ON COLUMN dis_prod_nocreate.is_sales_agency IS
    '是否有代销机构，数据字典，是否';

COMMENT ON COLUMN dis_prod_nocreate.report_issue_date IS
    '报表发布日期';

COMMENT ON COLUMN dis_prod_nocreate.data_status IS
    '数据状态';

COMMENT ON COLUMN dis_prod_nocreate.data_source IS
    '数据来源';

COMMENT ON COLUMN dis_prod_nocreate.create_user IS
    '创建人';

COMMENT ON COLUMN dis_prod_nocreate.create_time IS
    '创建时间';

COMMENT ON COLUMN dis_prod_nocreate.update_user IS
    '更新人';

COMMENT ON COLUMN dis_prod_nocreate.update_time IS
    '更新时间';

COMMENT ON COLUMN dis_prod_nocreate.create_dept IS
    '创建部门';

ALTER TABLE dis_prod_nocreate ADD CONSTRAINT dis_prod_nocreate_pk PRIMARY KEY ( prod_regist_no );

DROP TABLE dis_prod_partner CASCADE CONSTRAINTS;
CREATE TABLE dis_prod_partner (
    prod_partener_id VARCHAR2(60) NOT NULL,
    prod_id          VARCHAR2(60) NOT NULL,
    partner_name     VARCHAR2(512),
    eff_date         DATE,
    eff_end_date     DATE,
    data_status      VARCHAR2(10),
    data_source      VARCHAR2(10),
    create_user      VARCHAR2(20),
    create_time      TIMESTAMP,
    update_user      VARCHAR2(20),
    update_time      TIMESTAMP,
    create_dept      VARCHAR2(32)
);

COMMENT ON TABLE dis_prod_partner IS
    '产品合作机构信息';

COMMENT ON COLUMN dis_prod_partner.prod_partener_id IS
    '合作机构主键';

COMMENT ON COLUMN dis_prod_partner.prod_id IS
    '主产品代码';

COMMENT ON COLUMN dis_prod_partner.partner_name IS
    '合作机构名称';

COMMENT ON COLUMN dis_prod_partner.eff_date IS
    '生效日期';

COMMENT ON COLUMN dis_prod_partner.eff_end_date IS
    '生效截至日';

COMMENT ON COLUMN dis_prod_partner.data_status IS
    '数据状态';

COMMENT ON COLUMN dis_prod_partner.data_source IS
    '数据来源';

COMMENT ON COLUMN dis_prod_partner.create_user IS
    '创建人';

COMMENT ON COLUMN dis_prod_partner.create_time IS
    '创建时间';

COMMENT ON COLUMN dis_prod_partner.update_user IS
    '更新人';

COMMENT ON COLUMN dis_prod_partner.update_time IS
    '更新时间';

COMMENT ON COLUMN dis_prod_partner.create_dept IS
    '创建部门';

ALTER TABLE dis_prod_partner ADD CONSTRAINT dis_prod_partner_pk PRIMARY KEY ( prod_partener_id );

DROP TABLE dis_prod_plan CASCADE CONSTRAINTS;
CREATE TABLE dis_prod_plan (
    prod_plan_id         VARCHAR2(60) NOT NULL,
    prod_id              VARCHAR2(60 BYTE) NOT NULL,
    open_start_date      DATE,
    open_end_date        DATE,
    collingoff_period    DATE,
    operation_start_date DATE,
    auto_redeem_date     DATE,
    hold_days            NUMBER(4),
    data_status          VARCHAR2(10 BYTE),
    data_source          VARCHAR2(10),
    create_user          VARCHAR2(20 BYTE),
    create_time          TIMESTAMP,
    update_user          VARCHAR2(20 BYTE),
    update_time          TIMESTAMP,
    create_dept          VARCHAR2(32 BYTE)
);

COMMENT ON TABLE dis_prod_plan IS
    '信披-产品运营计划表';

COMMENT ON COLUMN dis_prod_plan.prod_plan_id IS
    '产品排期计划编号';

COMMENT ON COLUMN dis_prod_plan.prod_id IS
    '产品ID';

COMMENT ON COLUMN dis_prod_plan.open_start_date IS
    '开放期起始日';

COMMENT ON COLUMN dis_prod_plan.open_end_date IS
    '开放期结束日';

COMMENT ON COLUMN dis_prod_plan.collingoff_period IS
    '产品冷静期';

COMMENT ON COLUMN dis_prod_plan.operation_start_date IS
    '运营起始日';

COMMENT ON COLUMN dis_prod_plan.auto_redeem_date IS
    '自动赎回日';

COMMENT ON COLUMN dis_prod_plan.hold_days IS
    '客户持有天数';

COMMENT ON COLUMN dis_prod_plan.data_status IS
    '数据状态：数据字典，系统模块-DIS，字典code：DIS_DATA_STATUS:01-有效，02-删除';

COMMENT ON COLUMN dis_prod_plan.data_source IS
    '数据来源:数据字典syscode：DIS,code:DATA_SOURCE,';

COMMENT ON COLUMN dis_prod_plan.create_user IS
    '创建人';

COMMENT ON COLUMN dis_prod_plan.create_time IS
    '创建时间';

COMMENT ON COLUMN dis_prod_plan.update_user IS
    '更新人';

COMMENT ON COLUMN dis_prod_plan.update_time IS
    '更新时间';

COMMENT ON COLUMN dis_prod_plan.create_dept IS
    '创建部门';

ALTER TABLE dis_prod_plan ADD CONSTRAINT dis_prod_plan_pk PRIMARY KEY ( prod_plan_id );

/**add by zhengxiaolian 查询信息配置表增加字段 20220719 start**/
ALTER TABLE rep_screen add  MULTI_FLAG VARCHAR2(2);
/**add by zhengxiaolian 查询信息配置表增加字段 20220719 end**/

/**add by zhengxiaolian 20220719 报表模板配置表报表参数字段长度增加 start**/
ALTER TABLE rep_reportinfo MODIFY REPORTPARAMS VARCHAR2(2000);
/**add by zhengxiaolian 20220719 报表模板配置表报表参数字段长度增加 end**/

/**add by zhengxiaolian 20220720 产品基本信息表增加发行状态字段 start**/
ALTER TABLE rep_prod_info add ISSUE_STATUS VARCHAR2(2) ;
comment on column rep_prod_info.ISSUE_STATUS is '发行状态 01 发行成功 02 发行失败 03 确认发行';
/**add by zhengxiaolian 20220720 产品基本信息表增加发行状态字段 end**/

/**add by zhengxiaolian 20220722 产品基本信息表增加信批产品类别字段 start**/
ALTER TABLE rep_prod_info add REP_PROD_TYPE VARCHAR2(2) ;
comment on column rep_prod_info.REP_PROD_TYPE is '信批产品类别 01 母产品 02 普通产品 03 子产品';
/**add by zhengxiaolian 20220722 产品基本信息表增加信批产品类别字段 end**/

/**add by zhengxiaolian 20220722 作业规则表增加日历类型字段 start**/
ALTER TABLE rep_rule add MARKET_CODE VARCHAR2(2) ;
comment on column rep_rule.MARKET_CODE is '日历类型：01 中国法定节假日/04 上交所';
/**add by zhengxiaolian 20220722 作业规则表增加日历类型字段 end**/


/**add by dingrui 20220726 产品基本信息表增加发行状态备注字段 start**/
alter table REP_PROD_INFO add (ISSUE_STATUS_REMARK varchar2(600) default null null);
comment on column REP_PROD_INFO.ISSUE_STATUS_REMARK is '发行状态备注';
/**add by dingrui 20220726 产品基本信息表增加信批产品类别字段 end**/


/**add by dingrui 20220726 新增 业绩基准变更表 start **/

/**add by dingrui 20220726 产品基本信息表增加信批产品类别字段 end**/


/**add by 周强 20220728 代销机构相关表结构 start**/
DROP TABLE DIS_SALE_AGENCY CASCADE CONSTRAINTS;
CREATE TABLE DIS_SALE_AGENCY (
    SEQUENCE_NO   VARCHAR2(50) NOT NULL,
    AGENCY_NAME  VARCHAR2(600 BYTE) NOT NULL,
    AGREEMENT_VDATE       DATE,
    AGREEMENT_MDATE       DATE,
    CREATE_USER  VARCHAR2(20 BYTE) NOT NULL,
    CREATE_TIME  TIMESTAMP (6) NOT NULL,
    UPDATE_USER  VARCHAR2(20 BYTE) NOT NULL,
    UPDATE_TIME  TIMESTAMP (6) NOT NULL,
    CREATE_DEPT  VARCHAR2(32 BYTE)
);

 ALTER TABLE DIS_SALE_AGENCY ADD CONSTRAINT DIS_SALE_AGENCY_PK PRIMARY KEY (SEQUENCE_NO);

   COMMENT ON TABLE DIS_SALE_AGENCY IS '代销机构信息表';

   COMMENT ON COLUMN DIS_SALE_AGENCY.SEQUENCE_NO IS '代销机构uuid';

   COMMENT ON COLUMN DIS_SALE_AGENCY.AGENCY_NAME IS '代销机构名称';

   COMMENT ON COLUMN DIS_SALE_AGENCY.AGREEMENT_VDATE IS '协议起始时间';

   COMMENT ON COLUMN DIS_SALE_AGENCY.AGREEMENT_MDATE IS '协议终止时间';

   COMMENT ON COLUMN DIS_SALE_AGENCY.CREATE_USER IS '创建人';

   COMMENT ON COLUMN DIS_SALE_AGENCY.CREATE_TIME IS '创建时间';

   COMMENT ON COLUMN DIS_SALE_AGENCY.UPDATE_USER IS '更新人';

   COMMENT ON COLUMN DIS_SALE_AGENCY.UPDATE_TIME IS '更新时间';

   COMMENT ON COLUMN DIS_SALE_AGENCY.CREATE_DEPT IS '创建部门';




  DROP TABLE DIS_PROD_AGENCY CASCADE CONSTRAINTS;
  CREATE TABLE DIS_PROD_AGENCY
   (	AGENCY_ID VARCHAR2(50 BYTE) NOT NULL,
      PROD_ID VARCHAR2(100 BYTE) NOT NULL,
      CREATE_USER VARCHAR2(20 BYTE) NOT NULL,
      CREATE_TIME TIMESTAMP (6) NOT NULL,
      UPDATE_USER VARCHAR2(20 BYTE) NOT NULL,
      UPDATE_TIME TIMESTAMP (6) NOT NULL,
      CREATE_DEPT VARCHAR2(32 BYTE)
   ) ;

 ALTER TABLE DIS_PROD_AGENCY ADD CONSTRAINT DIS_PROD_AGENCY_PK PRIMARY KEY (AGENCY_ID,PROD_ID);

   COMMENT ON TABLE DIS_PROD_AGENCY IS '产品代销机构关联表';

   COMMENT ON COLUMN DIS_PROD_AGENCY.AGENCY_ID IS '代销机构id,与DIS_SALE_AGENCY表的SEQUENCE_NO关联';

   COMMENT ON COLUMN DIS_PROD_AGENCY.PROD_ID IS '产品代码';

   COMMENT ON COLUMN DIS_PROD_AGENCY.CREATE_USER IS '创建人';

   COMMENT ON COLUMN DIS_PROD_AGENCY.CREATE_TIME IS '创建时间';

   COMMENT ON COLUMN DIS_PROD_AGENCY.UPDATE_USER IS '更新人';

   COMMENT ON COLUMN DIS_PROD_AGENCY.UPDATE_TIME IS '更新时间';

   COMMENT ON COLUMN DIS_PROD_AGENCY.CREATE_DEPT IS '创建部门';

   /**add by 周强 20220728 代销机构相关表结构 end**/
/**add by 崔伟 20220801 1.1.1信批合规留痕管理 start**/
DROP TABLE INFO_COMPLIANCE;
CREATE TABLE INFO_COMPLIANCE(
    SEQNO VARCHAR2(32) PRIMARY KEY,
    INFO_TYPE VARCHAR(2) NOT NULL ENABLE,
    INFO_NAME VARCHAR2(300) NOT NULL ENABLE,
    INFO_STATUS VARCHAR(2),
    INFO_DEV_STATUS VARCHAR(2),
    REMARK VARCHAR2(1500),
    WORKFLOW_ID VARCHAR2(32), 
    CREATE_USER VARCHAR2(20) NOT NULL ENABLE, 
    CREATE_DEPT VARCHAR2(32), 
    CREATE_TIME TIMESTAMP (6) NOT NULL ENABLE, 
    UPDATE_USER VARCHAR2(20) NOT NULL ENABLE, 
    UPDATE_TIME TIMESTAMP (6) NOT NULL ENABLE
);
COMMENT ON TABLE INFO_COMPLIANCE IS '信批合规留痕表';
COMMENT ON COLUMN INFO_COMPLIANCE.SEQNO IS '主键';
COMMENT ON COLUMN INFO_COMPLIANCE.INFO_TYPE IS '类型:01模板;02信批报表';
COMMENT ON COLUMN INFO_COMPLIANCE.INFO_STATUS IS '审批状态:01待复核;02审核通过';
COMMENT ON COLUMN INFO_COMPLIANCE.INFO_DEV_STATUS IS '开发状态:01待开发;02需修改;03终止';
COMMENT ON COLUMN INFO_COMPLIANCE.REMARK IS '备注';
COMMENT ON COLUMN INFO_COMPLIANCE.WORKFLOW_ID IS '流程ID';
COMMENT ON COLUMN INFO_COMPLIANCE.CREATE_USER IS '创建人';
COMMENT ON COLUMN INFO_COMPLIANCE.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN INFO_COMPLIANCE.UPDATE_USER IS '更新人';
COMMENT ON COLUMN INFO_COMPLIANCE.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN INFO_COMPLIANCE.CREATE_DEPT IS '创建部门';

alter table sys_upload_file_info drop column file_type;
alter table sys_upload_file_info drop column seqno_comment;
alter table sys_upload_file_info add file_type varchar2(2);
alter table sys_upload_file_info add seqno_comment varchar2(32);

drop table INFO_COMPLIANCE_COMMENT;
CREATE TABLE INFO_COMPLIANCE_COMMENT(
   SEQNO VARCHAR2(32) PRIMARY KEY,
   INFO_SEQNO VARCHAR2(32) NOT NULL,
   COMMENT_REMARK VARCHAR2(1500),
   CREATE_USER VARCHAR2(20) NOT NULL ENABLE,
   CREATE_DEPT VARCHAR2(32),
   CREATE_TIME TIMESTAMP (6) NOT NULL ENABLE,
   UPDATE_USER VARCHAR2(20) NOT NULL ENABLE,
   UPDATE_TIME TIMESTAMP (6) NOT NULL ENABLE
);
COMMENT ON TABLE INFO_COMPLIANCE_COMMENT IS '信批合规留痕批注表';
COMMENT ON COLUMN INFO_COMPLIANCE_COMMENT.SEQNO IS '主键';
COMMENT ON COLUMN INFO_COMPLIANCE_COMMENT.INFO_SEQNO IS '合规留痕主键';
COMMENT ON COLUMN INFO_COMPLIANCE_COMMENT.COMMENT_REMARK IS '批注';
COMMENT ON COLUMN INFO_COMPLIANCE_COMMENT.CREATE_USER IS '创建人';
COMMENT ON COLUMN INFO_COMPLIANCE_COMMENT.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN INFO_COMPLIANCE_COMMENT.UPDATE_USER IS '更新人';
COMMENT ON COLUMN INFO_COMPLIANCE_COMMENT.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN INFO_COMPLIANCE_COMMENT.CREATE_DEPT IS '创建部门';
/**add by 崔伟 20220801 1.1.1信批合规留痕管理 end**/



/**add by 丁锐 20220804   报表数据校验信息  start**/
DROP TABLE REP_REPORT_CHECK;
create table REP_REPORT_CHECK
(
    report_check_id VARCHAR2(60) not null,
    report_type     VARCHAR2(2),
    report_name     VARCHAR2(200),
    prod_name       VARCHAR2(200),
    prod_id         VARCHAR2(60),
    check_date      DATE,
    errormsg        VARCHAR2(500),
    data_status     VARCHAR2(10),
    data_source     VARCHAR2(10),
    create_user     VARCHAR2(20),
    create_time     TIMESTAMP(6),
    update_user     VARCHAR2(20),
    update_time     TIMESTAMP(6),
    create_dept     VARCHAR2(32),
    tips            VARCHAR2(2)
);
-- Add comments to the table
comment on table REP_REPORT_CHECK
  is '报表数据校验信息';
-- Add comments to the columns
comment on column REP_REPORT_CHECK.report_check_id
  is '报表数据校验主键';
comment on column REP_REPORT_CHECK.report_type
  is '报表类别';
comment on column REP_REPORT_CHECK.report_name
  is '报表名称';
comment on column REP_REPORT_CHECK.prod_name
  is '产品名称';
comment on column REP_REPORT_CHECK.prod_id
  is '产品代码';
comment on column REP_REPORT_CHECK.check_date
  is '检查日期';
comment on column REP_REPORT_CHECK.errormsg
  is '错误信息';
comment on column REP_REPORT_CHECK.data_status
  is '数据状态';
comment on column REP_REPORT_CHECK.data_source
  is '数据来源';
comment on column REP_REPORT_CHECK.create_user
  is '创建人';
comment on column REP_REPORT_CHECK.create_time
  is '创建时间';
comment on column REP_REPORT_CHECK.update_user
  is '更新人';
comment on column REP_REPORT_CHECK.update_time
  is '更新时间';
comment on column REP_REPORT_CHECK.create_dept
  is '创建部门';
comment on column REP_REPORT_CHECK.tips
  is '是否提示（01 是；02否，默认是）';
-- Create/Recreate primary, unique and foreign key constraints
alter table REP_REPORT_CHECK
    add constraint PK_REP_REPORTCHECK primary key (REPORT_CHECK_ID);
/**add by 丁锐 20220804   报表数据校验信息  end**/

/**报表指标管理 add by cuiwei 2022-08-07 start**/
ALTER TABLE REP_INDEX_SUB DROP COLUMN END_DATE;
ALTER TABLE REP_INDEX_SUB add END_DATE DATE;
COMMENT ON COLUMN REP_INDEX_SUB.END_DATE is '生效截止日期';
/**报表指标管理 add by cuiwei 2022-08-07 end**/

/**模板启用管理 add by cuiwei 2022-08-07 start*/
DROP TABLE REP_INFO_STATUS;
CREATE TABLE REP_INFO_STATUS(
    SEQNO VARCHAR2(32) PRIMARY KEY,
	REP_ID VARCHAR2(32) NOT NULL ENABLE,
	START_OFF VARCHAR2(2) NOT NULL ENABLE,
	EFF_DATE TIMESTAMP (6) NOT NULL ENABLE,
	REP_STATUS VARCHAR2(2),
	REMARK VARCHAR2(3000),
	WORKFLOW_TYPE VARCHAR2(2),
	WORKFLOW_ID VARCHAR2(32),
    CREATE_USER VARCHAR2(20) NOT NULL ENABLE,
    CREATE_DEPT VARCHAR2(32),
    CREATE_TIME TIMESTAMP (6) NOT NULL ENABLE,
    UPDATE_USER VARCHAR2(20) NOT NULL ENABLE,
    UPDATE_TIME TIMESTAMP (6) NOT NULL ENABLE
);
COMMENT ON TABLE REP_INFO_STATUS IS '信披模板启用禁用记录表';
COMMENT ON COLUMN REP_INFO_STATUS.SEQNO IS '主键';
COMMENT ON COLUMN REP_INFO_STATUS.REP_ID IS '报表ID';
COMMENT ON COLUMN REP_INFO_STATUS.START_OFF IS '启用禁用标识:01启用;02禁用';
COMMENT ON COLUMN REP_INFO_STATUS.EFF_DATE IS '生效日';
COMMENT ON COLUMN REP_INFO_STATUS.REP_STATUS IS '报表审核状态:01流程中;02审核通过';
COMMENT ON COLUMN REP_INFO_STATUS.REMARK IS '备注';
COMMENT ON COLUMN REP_INFO_STATUS.WORKFLOW_TYPE IS '流程类型:01启用流程;02禁用流程';
COMMENT ON COLUMN REP_INFO_STATUS.WORKFLOW_ID IS '流程ID';
COMMENT ON COLUMN REP_INFO_STATUS.CREATE_USER IS '创建人';
COMMENT ON COLUMN REP_INFO_STATUS.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN REP_INFO_STATUS.UPDATE_USER IS '更新人';
COMMENT ON COLUMN REP_INFO_STATUS.UPDATE_TIME IS '更新时间';
COMMENT ON COLUMN REP_INFO_STATUS.CREATE_DEPT IS '创建部门';
/**模板启用管理 add by cuiwei 2022-08-07 end*/

/**信披合规留痕,模板临时公告长度修改为100 add by cuiwei 2022-08-07 start*/
ALtER TABLE INFO_COMPLIANCE MODIFY INFO_NAME VARCHAR2(300);
/**信披合规留痕,模板临时公告长度修改为100 add by cuiwei 2022-08-07 end*/

/**信息披露表,产品名称长度修改为1000 add by 郑孝廉 2022-08-09 start*/
ALtER TABLE REP_REVEAL MODIFY PROD_NAME VARCHAR2(1000);
/**信息披露表,产品名称长度修改为1000 add by 郑孝廉 2022-08-09 end*/

/**报表信息,增加字段 add by 郑孝廉 2022-08-09 start*/
alter table REP_INFO add (FILE_NAME varchar2(600) default null null);
comment on column REP_INFO.FILE_NAME is '生成的文件名';
/**报表信息,增加字段 add by 郑孝廉 2022-08-09 end*/

/**上传文件记录返回信息表"披露文件名称"字段长度修改(应该和REP_REVEAL中的FILENAME字段长度保持一致) add by zhouqiang 2022-08-09 start*/
ALTER TABLE REP_FTP_DETAIL_MSG MODIFY FILE_NAME VARCHAR2(150 BYTE);
/**上传文件记录返回信息表"披露文件名称"字段长度修改(应该和REP_REVEAL中的FILENAME字段长度保持一致) add by zhouqiang 2022-08-09 end*/

/**报表确认管理,增加字段 add by shengning 2022-08-10 start*/
alter table REP_REVEAL add WORKFLOW_ID VARCHAR2(50);
comment on column REP_REVEAL.WORKFLOW_ID is '流程ID';

alter table REP_REVEAL add CHECK_USER VARCHAR2(50);
comment on column REP_REVEAL.CHECK_USER is '确认复核人';

alter table REP_REVEAL drop column CHECK_TIME;
alter table REP_REVEAL add CHECK_TIME TIMESTAMP(6);
comment on column REP_REVEAL.CHECK_TIME is '复核时间';
/**报表确认管理,增加字段 add by shengning 2022-08-10 end*/
/**报表确认管理批注表 add by shengning 2022-08-10 start*/
drop table REP_REVEAL_COMMENT;
CREATE TABLE
    REP_REVEAL_COMMENT
    (
        SEQNO VARCHAR2(32) NOT NULL,
        INFO_SEQNO VARCHAR2(32) NOT NULL,
        COMMENT_REMARK VARCHAR2(1500),
        CREATE_USER VARCHAR2(20) NOT NULL,
        CREATE_DEPT VARCHAR2(32),
        CREATE_TIME TIMESTAMP(6) NOT NULL,
        UPDATE_USER VARCHAR2(20) NOT NULL,
        UPDATE_TIME TIMESTAMP(6) NOT NULL,
        PRIMARY KEY (SEQNO)
    );
COMMENT ON TABLE REP_REVEAL_COMMENT
IS
    '报表确认管理批注表';
COMMENT ON COLUMN REP_REVEAL_COMMENT.SEQNO
IS
    '主键';
COMMENT ON COLUMN REP_REVEAL_COMMENT.INFO_SEQNO
IS
    '报表确认管理主键';
COMMENT ON COLUMN REP_REVEAL_COMMENT.COMMENT_REMARK
IS
    '批注';
COMMENT ON COLUMN REP_REVEAL_COMMENT.CREATE_USER
IS
    '创建人';
COMMENT ON COLUMN REP_REVEAL_COMMENT.CREATE_DEPT
IS
    '创建部门';
COMMENT ON COLUMN REP_REVEAL_COMMENT.CREATE_TIME
IS
    '创建时间';
COMMENT ON COLUMN REP_REVEAL_COMMENT.UPDATE_USER
IS
    '更新人';
COMMENT ON COLUMN REP_REVEAL_COMMENT.UPDATE_TIME
IS
    '更新时间';
/**报表确认管理批注表 add by shengning 2022-08-10 end*/

/**产品要素调整表增加字段 add by sunjie 2022-08-11 start*/
alter table DIS_PROD_ADJUST
	add ADJ_REASON VARCHAR2(512);
comment on column DIS_PROD_ADJUST.ADJ_REASON is '调整原因';
/**产品要素调整表增加字段 add by sunjie 2022-08-11 end*/

/**add by zhengxiaolian 20220808   报表数据校验信息  start**/
DROP TABLE REP_REPORT_CHECK;
create table REP_REPORT_CHECK
(
    report_check_id VARCHAR2(60) not null,
    rep_id          VARCHAR2(60),
    report_type     VARCHAR2(2),
    report_name     VARCHAR2(1000),
    prod_id         VARCHAR2(60),
    check_date      DATE,
    start_date      DATE,
    end_date        DATE,
    errormsg        VARCHAR2(500),
    file_no         VARCHAR2(100),
    data_status     VARCHAR2(10),
    data_source     VARCHAR2(10),
    create_user     VARCHAR2(20),
    create_time     TIMESTAMP(6),
    update_user     VARCHAR2(20),
    update_time     TIMESTAMP(6),
    create_dept     VARCHAR2(32)
);
-- Add comments to the table
comment on table REP_REPORT_CHECK
    is '报表数据校验信息';
-- Add comments to the columns
comment on column REP_REPORT_CHECK.report_check_id
    is '报表数据校验主键';
comment on column REP_REPORT_CHECK.rep_id
    is '报表ID';
comment on column REP_REPORT_CHECK.report_type
    is '报表类别';
comment on column REP_REPORT_CHECK.report_name
    is '报表名称';
comment on column REP_REPORT_CHECK.prod_id
    is '产品代码';
comment on column REP_REPORT_CHECK.check_date
    is '检查日期';
comment on column REP_REPORT_CHECK.errormsg
    is '错误信息';
comment on column REP_REPORT_CHECK.file_no
    is '文件编号';
comment on column REP_REPORT_CHECK.data_status
    is '数据状态';
comment on column REP_REPORT_CHECK.data_source
    is '数据来源';
comment on column REP_REPORT_CHECK.create_user
    is '创建人';
comment on column REP_REPORT_CHECK.create_time
    is '创建时间';
comment on column REP_REPORT_CHECK.update_user
    is '更新人';
comment on column REP_REPORT_CHECK.update_time
    is '更新时间';
comment on column REP_REPORT_CHECK.create_dept
    is '创建部门';
-- Create/Recreate primary, unique and foreign key constraints
alter table REP_REPORT_CHECK
    add constraint PK_REP_REPORTCHECK primary key (REPORT_CHECK_ID);
/**add by zhengxiaolian 20220808   报表数据校验信息  end**/

-- Create table
drop table REP_DATA_CHECK;
create table REP_DATA_CHECK
(
    check_id           VARCHAR2(100) not null,
    check_type         VARCHAR2(10),
    check_desc         VARCHAR2(1000),
    param_value1       VARCHAR2(1000),
    param_value2       VARCHAR2(1000),
    status             VARCHAR2(10) not null,
    create_user        VARCHAR2(50) not null,
    create_dept        VARCHAR2(50),
    create_time        TIMESTAMP(6) not null,
    update_user        VARCHAR2(50) not null,
    update_time        TIMESTAMP(6) not null
);

-- Add comments to the table
comment on table REP_DATA_CHECK
    is '信批内容校验';
-- Add comments to the columns
comment on column REP_DATA_CHECK.check_id
    is '校验编号';
comment on column REP_DATA_CHECK.check_type
    is '校验方式';
comment on column REP_DATA_CHECK.check_desc
    is '校验描述';
comment on column REP_DATA_CHECK.param_value1
    is '参数值1';
comment on column REP_DATA_CHECK.param_value2
    is '参数值2';
comment on column REP_DATA_CHECK.status
    is '数据状态';
comment on column REP_DATA_CHECK.create_user
    is '创建人';
comment on column REP_DATA_CHECK.create_dept
    is '创建部门';
comment on column REP_DATA_CHECK.create_time
    is '创建时间';
comment on column REP_DATA_CHECK.update_user
    is '更新人';
comment on column REP_DATA_CHECK.update_time
    is '更新时间';
alter table REP_DATA_CHECK  add constraint P_REP_DATA_CHECK_ID primary key (CHECK_ID, STATUS);


/**报表数据校验信息 错误信息字段长度调整 add by zhengxiaolian 2022-08-13 start*/
ALtER TABLE REP_REPORT_CHECK MODIFY errormsg VARCHAR2(2000);
/**报表数据校验信息 错误信息字段长度调整 add by zhengxiaolian 2022-08-13 end*/


/**产品要素调整表增加字段 add by sunjie 2022-08-14 start*/
alter table DIS_PROD_ADJUST add (
ADJ_REASON2 VARCHAR2(512)
);
comment on column DIS_PROD_ADJUST.ADJ_REASON2 is '调整原因2';
/**产品要素调整表增加字段 add by sunjie 2022-08-14 end*/

/**上传文件信息 路径字段长度调整 add by zhengxiaolian 2022-08-15 start*/
ALTER TABLE SYS_UPLOAD_FILE_INFO MODIFY PATH VARCHAR2(2000);
/**上传文件信息 路径字段长度调整 add by zhengxiaolian 2022-08-15 end*/


-- 删除产品不成立信息表主键 add by sunjie 20220817 start
alter table DIS_PROD_NOCREATE drop constraint DIS_PROD_NOCREATE_PK;--只能在同一个库中执行一次
-- 删除产品不成立信息表主键 add by sunjie 20220817 end
commit ;

-- 增加模板状态是否同步到模板表标识 add by cuiwei 20220819 start
ALTER TABLE REP_INFO_STATUS ADD IS_SYNCHRO VARCHAR2(1);
comment on column REP_INFO_STATUS.IS_SYNCHRO is '模板状态是否同步到REP_INFO表:Y是;N否';
-- 增加模板状态是否同步到模板表标识 add by cuiwei 20220819 end
-- 增加模板状态是否同步到模板表标识 add by cuiwei 20220820 start
ALTER TABLE REP_JOB_RANGE MODIFY RULE_TYPE4 VARCHAR2(4000);
-- 增加模板状态是否同步到模板表标识 add by cuiwei 20220820 end

/**披露预期信息 字段长度调整 add by zhengxiaolian 2022-08-25 start*/
ALTER TABLE REP_EXPECTED_LOG MODIFY PROD_NAME VARCHAR2(1000);
ALTER TABLE REP_EXPECTED_LOG MODIFY REP_NAME VARCHAR2(1000);
/**披露预期信息 字段长度调整 add by zhengxiaolian 2022-08-25 end*/

/**创建母产品视图  add by chenxiaocang 2022-08-28 start **/
CREATE OR REPLACE VIEW V_REP_RROD_INFO AS
SELECT nvl(P_PROD_ID,PROD_ID) AS PROD_ID,
PROD_NAME,
SET_DATE,
RAISE_AMT,
PROD_ABBR,
PROD_RE,
RAISE_STRDATE,
RAISE_ENDDATE,
MDATE,
RAISE_TYPE,
PERFORM_COMPARE,
IS_PERIOD,
IS_GRADE,
IS_INDEX,
OPEN_FREQ,
NATURE_INVEST,
PROD_TYPE,
ISSUING_WAY,
ACCT_CHNNAME,
PROFIT_TYPE,
PD_STATUS,
PROD_SERIES,
HOLDDAY,
TRUSTPART,
ISSUE_STATUS,
REP_PROD_TYPE,
ERRORMSG,
CREATE_USER,
CREATE_DEPT,
CREATE_TIME,
UPDATE_USER,
UPDATE_TIME,
STATUS,
ISSUE_STATUS_REMARK,
RAISE_MIN,
RAISE_MAX,
RAISE_CCY,
VDATE,
FIRST_OPEN_DAY,
ACT_MDATE,
TERM,
WINDUP_DATE,
ISSUE_MODE,
RISK_LEVEL,
OPEN_MODE,
INVEST_PROF_DATE,
INVEST_PRIN_DATE,
IS_INDEPE_ACC,
PROD_INVEST_TYPE,
REVEAL_CHANNEL,
DEAL_CAL,
FINA_TYPE,
TRU_ACCOUNT,
TRU_FEE_RAGE,
TRU_ACCOUNT_NUM,
TRU_ACCOUNT_NAME,
PROD_TERM_TYPE,
BASIS,
TARGET_SALE,
IS_LAYER,
PRO_MANAGER,
PRO_CUSTODIAN,
HUGE_REDEEM_THRESHOLD,
SALE_AMT_STR,
OPERATE_MODE,
F_PROD_ID,
PRODPROFITFLAG,
SALETARGET_DECLARE,
BONUS_MODE,
LOWEST_AMT
FROM REP_PROD_INFO
WHERE REP_PROD_TYPE IN ('01','02');
/**创建母产品视图  add by chenxiaocang 2022-08-28 end **/
