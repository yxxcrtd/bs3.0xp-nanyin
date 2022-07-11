CREATE TABLE dis_large_redemption (
                                      prod_regist_no  VARCHAR2(50) NOT NULL,
                                      prod_id         VARCHAR2(60),
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

COMMENT ON COLUMN dis_large_redemption.prod_regist_no IS
    '产品登记编码';

COMMENT ON COLUMN dis_large_redemption.prod_id IS
    '子产品代码';

COMMENT ON COLUMN dis_large_redemption.is_sales_agency IS
    '是否有代销机构，数据字典，是否';

COMMENT ON COLUMN dis_large_redemption.deal_date IS
    '优化起始时间';

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

ALTER TABLE dis_large_redemption ADD CONSTRAINT dis_large_redemption_pk PRIMARY KEY ( prod_regist_no );

CREATE TABLE dis_nostd_creditor (
                                    nostd_asset_id       VARCHAR2(50) NOT NULL,
                                    prod_id              VARCHAR2(60) NOT NULL,
                                    eff_date             DATE,
                                    eff_end_date         DATE,
                                    financier            VARCHAR2(128),
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
    '非标资产编号';

COMMENT ON COLUMN dis_nostd_creditor.prod_id IS
    '主产品代码';

COMMENT ON COLUMN dis_nostd_creditor.eff_date IS
    '生效日期';

COMMENT ON COLUMN dis_nostd_creditor.eff_end_date IS
    '生效截至日';

COMMENT ON COLUMN dis_nostd_creditor.financier IS
    '融资客户';

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

CREATE TABLE dis_prod_adjust (
                                 prod_regist_no    VARCHAR2(50) NOT NULL,
                                 prod_id           VARCHAR2(60),
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

COMMENT ON COLUMN dis_prod_adjust.prod_regist_no IS
    '产品登记编码';

COMMENT ON COLUMN dis_prod_adjust.prod_id IS
    '子产品代码';

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

ALTER TABLE dis_prod_adjust ADD CONSTRAINT dis_prod_adjust_pk PRIMARY KEY ( prod_regist_no );

CREATE TABLE dis_prod_asset (
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
    '数据来源';

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

ALTER TABLE dis_prod_asset ADD CONSTRAINT dis_prod_asset_pk PRIMARY KEY ( prod_id );

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
                                    create_user             VARCHAR2(20 BYTE) NOT NULL,
                                    create_time             TIMESTAMP NOT NULL,
                                    update_user             VARCHAR2(20 BYTE) NOT NULL,
                                    update_time             TIMESTAMP NOT NULL,
                                    create_dept             VARCHAR2(32 BYTE)
);

COMMENT ON TABLE dis_prod_bonusplan IS
    '信披：产品分红计划';

COMMENT ON COLUMN dis_prod_bonusplan.bonus_plan_id IS
    '分红计划编号';

COMMENT ON COLUMN dis_prod_bonusplan.prod_id IS
    '产品编号';

COMMENT ON COLUMN dis_prod_bonusplan.bonus_mode IS
    '分红方式：数据字典code:DIS/BONUS_MODE,01-份额分红后净值为1、02-到期兑付';

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
    '数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除';

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

ALTER TABLE dis_prod_bonusplan
    ADD CONSTRAINT dis_prd_bonus_plan_pk PRIMARY KEY ( bonus_plan_id );

CREATE TABLE dis_prod_feediscount (
                                      prod_regist_no     VARCHAR2(50) NOT NULL,
                                      prod_id            VARCHAR2(60),
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

COMMENT ON COLUMN dis_prod_feediscount.prod_regist_no IS
    '产品登记编码';

COMMENT ON COLUMN dis_prod_feediscount.prod_id IS
    '子产品代码';

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

ALTER TABLE dis_prod_feediscount ADD CONSTRAINT dis_prod_feediscount_pk PRIMARY KEY ( prod_regist_no );

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

CREATE TABLE dis_prod_partner (
                                  prod_id      VARCHAR2(60) NOT NULL,
                                  partner_name VARCHAR2(512),
                                  eff_date     DATE,
                                  eff_end_date DATE,
                                  data_status  VARCHAR2(10),
                                  data_source  VARCHAR2(10),
                                  create_user  VARCHAR2(20),
                                  create_time  TIMESTAMP,
                                  update_user  VARCHAR2(20),
                                  update_time  TIMESTAMP,
                                  create_dept  VARCHAR2(32)
);

COMMENT ON TABLE dis_prod_partner IS
    '产品合作机构信息';

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

ALTER TABLE dis_prod_partner ADD CONSTRAINT dis_prod_partner_pk PRIMARY KEY ( prod_id );

CREATE TABLE dis_prod_plan (
                               prod_plan_id         VARCHAR2(60) NOT NULL,
                               prod_id              VARCHAR2(60 BYTE) NOT NULL,
                               open_start_date      DATE NOT NULL,
                               open_end_date        DATE,
                               collingoff_period    DATE,
                               operation_start_date DATE,
                               auto_redeem_date     VARCHAR2(100 BYTE),
                               hold_days            NUMBER(4),
                               data_status          VARCHAR2(10 BYTE),
                               data_source          VARCHAR2(10),
                               create_user          VARCHAR2(20 BYTE) NOT NULL,
                               create_time          TIMESTAMP NOT NULL,
                               update_user          VARCHAR2(20 BYTE) NOT NULL,
                               update_time          TIMESTAMP NOT NULL,
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
    '查询条件key';

COMMENT ON COLUMN dis_prod_plan.operation_start_date IS
    '运营起始日';

COMMENT ON COLUMN dis_prod_plan.auto_redeem_date IS
    '自动赎回日';

COMMENT ON COLUMN dis_prod_plan.hold_days IS
    '客户持有天数';

COMMENT ON COLUMN dis_prod_plan.data_status IS
    '数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除';

COMMENT ON COLUMN dis_prod_plan.data_source IS
    '数据来源:数据字典code:DATA_SOURCE';

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