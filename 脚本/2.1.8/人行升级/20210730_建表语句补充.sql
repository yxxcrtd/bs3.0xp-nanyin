
  
-- Add comments to the table 
comment on table PBC_PRD_BASEINFO_SUB_NEW
  is '新人行产品基本信息登记表';
-- Add comments to the columns 
comment on column PBC_PRD_BASEINFO_SUB_NEW.regist_seqno
  is '主键uuid';
comment on column PBC_PRD_BASEINFO_SUB_NEW.pbc_prod_id
  is '人行产品代码';
comment on column PBC_PRD_BASEINFO_SUB_NEW.issue_party_id
  is '理财产品的发行机构给代码';
comment on column PBC_PRD_BASEINFO_SUB_NEW.pbc_prod_type
  is '1-理财产品2-资金信托';
comment on column PBC_PRD_BASEINFO_SUB_NEW.prod_name
  is '产品全称';
comment on column PBC_PRD_BASEINFO_SUB_NEW.prod_brand
  is '产品品牌';
comment on column PBC_PRD_BASEINFO_SUB_NEW.prod_stage
  is '产品期次';
comment on column PBC_PRD_BASEINFO_SUB_NEW.prod_id
  is '理财产品代码';
comment on column PBC_PRD_BASEINFO_SUB_NEW.raise_ccy
  is '募集币种';
comment on column PBC_PRD_BASEINFO_SUB_NEW.cash_prin_ccy
  is '兑付本金币种';
comment on column PBC_PRD_BASEINFO_SUB_NEW.cash_prof_ccy
  is '兑付收益币种';
comment on column PBC_PRD_BASEINFO_SUB_NEW.customer_type
  is '客户类型，3 住户/4 广义政府/5 非金融企业/6 金融机构（实体）/7 特定目的载体/8 境外';
comment on column PBC_PRD_BASEINFO_SUB_NEW.raise_type
  is '募集方式，1-公募，2-私募';
comment on column PBC_PRD_BASEINFO_SUB_NEW.manage_type
  is '管理方式，1-集合管理/2-单独管理';
comment on column PBC_PRD_BASEINFO_SUB_NEW.fiduciary_duty
  is '受托职责，1-主动管理业务/2-被动管理业务';
comment on column PBC_PRD_BASEINFO_SUB_NEW.cont_mode
  is '合作模式，11-独立运作/12-银信合作/13-银保合作/14-银基合作/15-银证合作/16-混合类/17-其他/18-不适用';
comment on column PBC_PRD_BASEINFO_SUB_NEW.operate_mode
  is '运行方式，2-开放式净值型/3-开放式非净值型/4一封闭式净值型/5-封闭式非净值型';
comment on column PBC_PRD_BASEINFO_SUB_NEW.ivstorientation
  is '产品类型，007-固定收益类/008-权益类/009-商品及金融衍生品类/010-混合类';
comment on column PBC_PRD_BASEINFO_SUB_NEW.busi_model
  is '业务模式，1 表内存款/2 表内非存款负债/3 表外';
comment on column PBC_PRD_BASEINFO_SUB_NEW.income_guaran_ident
  is '收益保障标识,1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_NEW.prin_guaran_ident
  is '本金保障标识,1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_NEW.high_yield_issue
  is '预计客户最高年收益率';
comment on column PBC_PRD_BASEINFO_SUB_NEW.low_yield_issue
  is '预计客户最低年收益率';
comment on column PBC_PRD_BASEINFO_SUB_NEW.raise_vdate
  is '募集起始日期';
comment on column PBC_PRD_BASEINFO_SUB_NEW.raise_enddate
  is '募集结束日期';
comment on column PBC_PRD_BASEINFO_SUB_NEW.term_right_flag
  is '发行机构提前终止权标识，1-有，2-无';
comment on column PBC_PRD_BASEINFO_SUB_NEW.cus_redeem_flag
  is '客户赎回权标识，1-有，2-无';
comment on column PBC_PRD_BASEINFO_SUB_NEW.credit_flag
  is '产品增信标识，1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_NEW.credit_type
  is '增信机构类型，1 广义政府/2 非金融性公司/3 金融性公司/4 住户部门/5 国外部门';
comment on column PBC_PRD_BASEINFO_SUB_NEW.credit_mode
  is '增信形式，1-内部增级，2-外部增级';
comment on column PBC_PRD_BASEINFO_SUB_NEW.in_dep_name
  is '境内托管机构名称';
comment on column PBC_PRD_BASEINFO_SUB_NEW.in_dep_code
  is '境内托管机构代码';
comment on column PBC_PRD_BASEINFO_SUB_NEW.out_dep_ctry
  is '境外托管机构国别';
comment on column PBC_PRD_BASEINFO_SUB_NEW.out_dep_name
  is '境外托管机构名称';
comment on column PBC_PRD_BASEINFO_SUB_NEW.vdate
  is '产品起始日期';
comment on column PBC_PRD_BASEINFO_SUB_NEW.mdate
  is '产品预计终止日期';
comment on column PBC_PRD_BASEINFO_SUB_NEW.account_id
  is '产品对应资产池代码';
comment on column PBC_PRD_BASEINFO_SUB_NEW.is_constracte
  is '分级产品标识，1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_NEW.transfer_type
  is '收益权转让产品标识，1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_NEW.identi_flag
  is '理财产品发起机构标识,2 理财子公司发行/1 商业银行发行';
comment on column PBC_PRD_BASEINFO_SUB_NEW.send_status
  is '报送状态';
comment on column PBC_PRD_BASEINFO_SUB_NEW.submit_time
  is '报送日期';
comment on column PBC_PRD_BASEINFO_SUB_NEW.submit_feedback_status
  is '报送反馈状态';
comment on column PBC_PRD_BASEINFO_SUB_NEW.sumit_seq
  is '报送流水号';
comment on column PBC_PRD_BASEINFO_SUB_NEW.status_date
  is '数据日期';
comment on column PBC_PRD_BASEINFO_SUB_NEW.pbc_submit_deadline
  is '人行报送截止日';
comment on column PBC_PRD_BASEINFO_SUB_NEW.submit_deadline
  is '行内报送截止日';
comment on column PBC_PRD_BASEINFO_SUB_NEW.status
  is '数据状态';
comment on column PBC_PRD_BASEINFO_SUB_NEW.create_user
  is '创建人';
comment on column PBC_PRD_BASEINFO_SUB_NEW.org_code
  is '机构代码';
comment on column PBC_PRD_BASEINFO_SUB_NEW.create_dept
  is '创建部门';
comment on column PBC_PRD_BASEINFO_SUB_NEW.create_time
  is '创建时间';
comment on column PBC_PRD_BASEINFO_SUB_NEW.update_user
  is '更新人';
comment on column PBC_PRD_BASEINFO_SUB_NEW.update_time
  is '更新时间';
comment on column PBC_PRD_BASEINFO_SUB_NEW.is_market_fund
  is '1：是；2：否（定义人行的枚举值）';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PBC_PRD_BASEINFO_SUB_NEW
  add constraint PBC_PRD_BASEINFO_SUB_NEW_PK primary key (REGIST_SEQNO);
  
  

-- Add comments to the table 
comment on table PBC_PRD_BASEINFO_SUB_NEW_HIS
  is '人行产品基本信息登记表';
-- Add comments to the columns 
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.regist_seqno
  is '主键uuid';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.pbc_prod_id
  is '人行产品代码';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.issue_party_id
  is '理财产品的发行机构给代码';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.pbc_prod_type
  is '1-理财产品2-资金信托';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.prod_name
  is '产品全称';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.prod_brand
  is '产品品牌';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.prod_stage
  is '产品期次';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.prod_id
  is '理财产品代码';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.raise_ccy
  is '募集币种';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.cash_prin_ccy
  is '兑付本金币种';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.cash_prof_ccy
  is '兑付收益币种';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.customer_type
  is '客户类型，3 住户/4 广义政府/5 非金融企业/6 金融机构（实体）/7 特定目的载体/8 境外';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.raise_type
  is '募集方式，1-公募，2-私募';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.manage_type
  is '管理方式，1-集合管理/2-单独管理';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.fiduciary_duty
  is '受托职责，1-主动管理业务/2-被动管理业务';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.cont_mode
  is '合作模式，11-独立运作/12-银信合作/13-银保合作/14-银基合作/15-银证合作/16-混合类/17-其他/18-不适用';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.operate_mode
  is '运行方式，2-开放式净值型/3-开放式非净值型/4一封闭式净值型/5-封闭式非净值型';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.ivstorientation
  is '产品类型，007-固定收益类/008-权益类/009-商品及金融衍生品类/010-混合类';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.busi_model
  is '业务模式，1 表内存款/2 表内非存款负债/3 表外';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.income_guaran_ident
  is '收益保障标识,1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.prin_guaran_ident
  is '本金保障标识,1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.high_yield_issue
  is '预计客户最高年收益率';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.low_yield_issue
  is '预计客户最低年收益率';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.raise_vdate
  is '募集起始日期';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.raise_enddate
  is '募集结束日期';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.term_right_flag
  is '发行机构提前终止权标识，1-有，2-无';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.cus_redeem_flag
  is '客户赎回权标识，1-有，2-无';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.credit_flag
  is '产品增信标识，1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.credit_type
  is '增信机构类型，1 广义政府/2 非金融性公司/3 金融性公司/4 住户部门/5 国外部门';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.credit_mode
  is '增信形式，1-内部增级，2-外部增级';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.in_dep_name
  is '境内托管机构名称';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.in_dep_code
  is '境内托管机构代码';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.out_dep_ctry
  is '境外托管机构国别';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.out_dep_name
  is '境外托管机构名称';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.vdate
  is '产品起始日期';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.mdate
  is '产品预计终止日期';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.account_id
  is '产品对应资产池代码';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.is_constracte
  is '分级产品标识，1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.transfer_type
  is '收益权转让产品标识，1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.identi_flag
  is '理财产品发起机构标识,2 理财子公司发行/1 商业银行发行';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.send_status
  is '报送状态';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.submit_time
  is '报送日期';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.submit_feedback_status
  is '报送反馈状态';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.sumit_seq
  is '报送流水号';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.status_date
  is '数据日期';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.pbc_submit_deadline
  is '人行报送截止日';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.submit_deadline
  is '行内报送截止日';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.status
  is '数据状态';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.create_user
  is '创建人';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.org_code
  is '机构代码';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.create_dept
  is '创建部门';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.create_time
  is '创建时间';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.update_user
  is '更新人';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.update_time
  is '更新时间';
comment on column PBC_PRD_BASEINFO_SUB_NEW_HIS.is_market_fund
  is '1：是；2：否（定义人行的枚举值）';



-- Add comments to the table 
comment on table PBC_PRD_BASEINFO_END_SUB_NEW
  is '新人行产品终止信息登记表';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.regist_seqno
  is '主键uuid';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.pbc_prod_id
  is '人行产品代码';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.mdate
  is '产品实际终止日期';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.ccy
  is '币种';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.profit
  is '发行机构实现收入';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.profit_cny
  is '发行机构实现收入折人民币';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.custom_profit
  is '兑付客户收益';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.custom_profit_cny
  is '兑付客户收益折人民币';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.cus_year_yield
  is '兑付客户收益率';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.send_status
  is '报送状态';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.submit_time
  is '报送日期';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.submit_feedback_status
  is '报送反馈状态';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.sumit_seq
  is '报送流水号';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.status_date
  is '数据日??';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.pbc_submit_deadline
  is '人行报送截止日';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.submit_deadline
  is '行内报送截止日';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.status
  is '数据状态';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.create_user
  is '创建人';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.org_code
  is '机构代码';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.create_dept
  is '创建部门';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.create_time
  is '创建时间';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.update_user
  is '更新人';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PBC_PRD_BASEINFO_END_SUB_NEW
  add constraint PBC_PRD_BASE_END_SUB_NEW_PK primary key (REGIST_SEQNO);
  

-- Add comments to the table 
comment on table PBC_PRD_BASEEND_SUB_NEW_HIS
  is '新人行产品终止信息登记表历史';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.regist_seqno
  is '主键uuid';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.pbc_prod_id
  is '人行产品代码';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.mdate
  is '产品实际终止日期';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.ccy
  is '币种';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.profit
  is '发行机构实现收入';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.profit_cny
  is '发行机构实现收入折人民币';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.custom_profit
  is '兑付客户收益';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.custom_profit_cny
  is '兑付客户收益折人民币';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.cus_year_yield
  is '兑付客户收益率';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.send_status
  is '报送状态';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.submit_time
  is '报送日期';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.submit_feedback_status
  is '报送反馈状态';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.sumit_seq
  is '报送流水号';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.status_date
  is '数据日??';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.pbc_submit_deadline
  is '人行报送截止日';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.submit_deadline
  is '行内报送截止日';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.status
  is '数据状态';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.create_user
  is '创建人';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.org_code
  is '机构代码';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.create_dept
  is '创建部门';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.create_time
  is '创建时间';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.update_user
  is '更新人';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.update_time
  is '更新时间';
  
  
  
  
-- Add comments to the table 
comment on table PBC_PRD_BASEINFO_SUB_DAT
  is '新人行产品基本信息登记表_DAT';
-- Add comments to the columns 
comment on column PBC_PRD_BASEINFO_SUB_DAT.regist_seqno
  is '主键uuid';
comment on column PBC_PRD_BASEINFO_SUB_DAT.pbc_prod_id
  is '人行产品代码';
comment on column PBC_PRD_BASEINFO_SUB_DAT.issue_party_id
  is '理财产品的发行机构给代码';
comment on column PBC_PRD_BASEINFO_SUB_DAT.pbc_prod_type
  is '1-理财产品2-资金信托';
comment on column PBC_PRD_BASEINFO_SUB_DAT.prod_name
  is '产品全称';
comment on column PBC_PRD_BASEINFO_SUB_DAT.prod_brand
  is '产品品牌';
comment on column PBC_PRD_BASEINFO_SUB_DAT.prod_stage
  is '产品期次';
comment on column PBC_PRD_BASEINFO_SUB_DAT.prod_id
  is '理财产品代码';
comment on column PBC_PRD_BASEINFO_SUB_DAT.raise_ccy
  is '募集币种';
comment on column PBC_PRD_BASEINFO_SUB_DAT.cash_prin_ccy
  is '兑付本金币种';
comment on column PBC_PRD_BASEINFO_SUB_DAT.cash_prof_ccy
  is '兑付收益币种';
comment on column PBC_PRD_BASEINFO_SUB_DAT.customer_type
  is '客户类型，3 住户/4 广义政府/5 非金融企业/6 金融机构（实体）/7 特定目的载体/8 境外';
comment on column PBC_PRD_BASEINFO_SUB_DAT.raise_type
  is '募集方式，1-公募，2-私募';
comment on column PBC_PRD_BASEINFO_SUB_DAT.manage_type
  is '管理方式，1-集合管理/2-单独管理';
comment on column PBC_PRD_BASEINFO_SUB_DAT.fiduciary_duty
  is '受托职责，1-主动管理业务/2-被动管理业务';
comment on column PBC_PRD_BASEINFO_SUB_DAT.cont_mode
  is '合作模式，11-独立运作/12-银信合作/13-银保合作/14-银基合作/15-银证合作/16-混合类/17-其他/18-不适用';
comment on column PBC_PRD_BASEINFO_SUB_DAT.operate_mode
  is '运行方式，2-开放式净值型/3-开放式非净值型/4一封闭式净值型/5-封闭式非净值型';
comment on column PBC_PRD_BASEINFO_SUB_DAT.ivstorientation
  is '产品类型，007-固定收益类/008-权益类/009-商品及金融衍生品类/010-混合类';
comment on column PBC_PRD_BASEINFO_SUB_DAT.busi_model
  is '业务模式，1 表内存款/2 表内非存款负债/3 表外';
comment on column PBC_PRD_BASEINFO_SUB_DAT.income_guaran_ident
  is '收益保障标识,1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_DAT.prin_guaran_ident
  is '本金保障标识,1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_DAT.high_yield_issue
  is '预计客户最高年收益率';
comment on column PBC_PRD_BASEINFO_SUB_DAT.low_yield_issue
  is '预计客户最低年收益率';
comment on column PBC_PRD_BASEINFO_SUB_DAT.raise_vdate
  is '募集起始日期';
comment on column PBC_PRD_BASEINFO_SUB_DAT.raise_enddate
  is '募集结束日期';
comment on column PBC_PRD_BASEINFO_SUB_DAT.term_right_flag
  is '发行机构提前终止权标识，1-有，2-无';
comment on column PBC_PRD_BASEINFO_SUB_DAT.cus_redeem_flag
  is '客户赎回权标识，1-有，2-无';
comment on column PBC_PRD_BASEINFO_SUB_DAT.credit_flag
  is '产品增信标识，1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_DAT.credit_type
  is '增信机构类型，1 广义政府/2 非金融性公司/3 金融性公司/4 住户部门/5 国外部门';
comment on column PBC_PRD_BASEINFO_SUB_DAT.credit_mode
  is '增信形式，1-内部增级，2-外部增级';
comment on column PBC_PRD_BASEINFO_SUB_DAT.in_dep_name
  is '境内托管机构名称';
comment on column PBC_PRD_BASEINFO_SUB_DAT.in_dep_code
  is '境内托管机构代码';
comment on column PBC_PRD_BASEINFO_SUB_DAT.out_dep_ctry
  is '境外托管机构国别';
comment on column PBC_PRD_BASEINFO_SUB_DAT.out_dep_name
  is '境外托管机构名称';
comment on column PBC_PRD_BASEINFO_SUB_DAT.vdate
  is '产品起始日期';
comment on column PBC_PRD_BASEINFO_SUB_DAT.mdate
  is '产品预计终止日期';
comment on column PBC_PRD_BASEINFO_SUB_DAT.account_id
  is '产品对应资产池代码';
comment on column PBC_PRD_BASEINFO_SUB_DAT.is_constracte
  is '分级产品标识，1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_DAT.transfer_type
  is '收益权转让产品标识，1-是，2-否';
comment on column PBC_PRD_BASEINFO_SUB_DAT.identi_flag
  is '理财产品发起机构标识,2 理财子公司发行/1 商业银行发行';
comment on column PBC_PRD_BASEINFO_SUB_DAT.send_status
  is '报送状态';
comment on column PBC_PRD_BASEINFO_SUB_DAT.submit_time
  is '报送日期';
comment on column PBC_PRD_BASEINFO_SUB_DAT.submit_feedback_status
  is '报送反馈状态';
comment on column PBC_PRD_BASEINFO_SUB_DAT.sumit_seq
  is '报送流水号';
comment on column PBC_PRD_BASEINFO_SUB_DAT.status_date
  is '数据日期';
comment on column PBC_PRD_BASEINFO_SUB_DAT.pbc_submit_deadline
  is '人行报送截止日';
comment on column PBC_PRD_BASEINFO_SUB_DAT.submit_deadline
  is '行内报送截止日';
comment on column PBC_PRD_BASEINFO_SUB_DAT.status
  is '数据状态';
comment on column PBC_PRD_BASEINFO_SUB_DAT.create_user
  is '创建人';
comment on column PBC_PRD_BASEINFO_SUB_DAT.org_code
  is '机构代码';
comment on column PBC_PRD_BASEINFO_SUB_DAT.create_dept
  is '创建部门';
comment on column PBC_PRD_BASEINFO_SUB_DAT.create_time
  is '创建时间';
comment on column PBC_PRD_BASEINFO_SUB_DAT.update_user
  is '更新人';
comment on column PBC_PRD_BASEINFO_SUB_DAT.update_time
  is '更新时间';
comment on column PBC_PRD_BASEINFO_SUB_DAT.is_market_fund
  is '1：是；2：否（定义人行的枚举值）';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PBC_PRD_BASEINFO_SUB_DAT
  add constraint PBC_PRD_BASEINFO_SUB_DAT_PK primary key (REGIST_SEQNO);
  

-- Add comments to the table 
comment on table PBC_PRD_BASEINFO_END_SUB_DAT
  is '新人行产品终止信息登记表Dat';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.regist_seqno
  is '主键uuid';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.pbc_prod_id
  is '人行产品代码';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.mdate
  is '产品实际终止日期';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.ccy
  is '币种';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.profit
  is '发行机构实现收入';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.profit_cny
  is '发行机构实现收入折人民币';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.custom_profit
  is '兑付客户收益';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.custom_profit_cny
  is '兑付客户收益折人民币';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.cus_year_yield
  is '兑付客户收益率';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.send_status
  is '报送状态';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.submit_time
  is '报送日期';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.submit_feedback_status
  is '报送反馈状态';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.sumit_seq
  is '报送流水号';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.status_date
  is '数据日??';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.pbc_submit_deadline
  is '人行报送截止日';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.submit_deadline
  is '行内报送截止日';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.status
  is '数据状态';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.create_user
  is '创建人';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.org_code
  is '机构代码';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.create_dept
  is '创建部门';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.create_time
  is '创建时间';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.update_user
  is '更新人';
comment on column PBC_PRD_BASEINFO_END_SUB_DAT.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PBC_PRD_BASEINFO_END_SUB_DAT
  add constraint PBC_PRD_BASE_END_SUB_DAT_PK primary key (REGIST_SEQNO);
  
  
-- Add comments to the table 
comment on table PBC_REPORT_AREA_DAT
  is '新人行除回购和拆借外贷款分地区统计表';
-- Add comments to the columns 
comment on column PBC_REPORT_AREA_DAT.detailuuid
  is '明细统计uuid';
comment on column PBC_REPORT_AREA_DAT.reporttype
  is '报表类型 2_3-除回购和拆借外贷款分地区统计表';
comment on column PBC_REPORT_AREA_DAT.grouptype
  is '分组类型 AREA-地区';
comment on column PBC_REPORT_AREA_DAT.propertycode
  is '属性代码';
comment on column PBC_REPORT_AREA_DAT.balance
  is '余额';
comment on column PBC_REPORT_AREA_DAT.reportuuid
  is '主表关联id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PBC_REPORT_AREA_DAT
  add constraint PBC_REPORT_AREA_DAT_PK primary key (DETAILUUID);
  
-- Add comments to the table 
comment on table PBC_REPORT_ASSET_INCOME_DAT
  is '资产收益权按基础资产投向分类统计表DAT';
-- Add comments to the columns 
comment on column PBC_REPORT_ASSET_INCOME_DAT.detailuuid
  is '主键';
comment on column PBC_REPORT_ASSET_INCOME_DAT.reportuuid
  is '关联主表UUID';
comment on column PBC_REPORT_ASSET_INCOME_DAT.reporttype
  is '报表类型:2_4 资产收益权按基础资产投向分类统计报表';
comment on column PBC_REPORT_ASSET_INCOME_DAT.grouptype
  is '分组类型';
comment on column PBC_REPORT_ASSET_INCOME_DAT.propertycode
  is '属性代码';
comment on column PBC_REPORT_ASSET_INCOME_DAT.loan_income_right
  is '贷款收益权  ';
comment on column PBC_REPORT_ASSET_INCOME_DAT.debt_securities_income_rights
  is '债务证券收益权';
comment on column PBC_REPORT_ASSET_INCOME_DAT.receivable_income_rights
  is '应收账款收益权';
comment on column PBC_REPORT_ASSET_INCOME_DAT.other_creditor_rights
  is '其他债权收益权';
comment on column PBC_REPORT_ASSET_INCOME_DAT.stock_income_right
  is '股票收益权';
comment on column PBC_REPORT_ASSET_INCOME_DAT.other_equity_income_rights
  is '其他股权收益权';
comment on column PBC_REPORT_ASSET_INCOME_DAT.property_rights
  is '物权资产收益权';
comment on column PBC_REPORT_ASSET_INCOME_DAT.total
  is '总计';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PBC_REPORT_ASSET_INCOME_DAT
  add constraint PBC_REPORT_ASSET_INCOME_DAT_PK primary key (DETAILUUID);
  
  
-- Add comments to the table 
comment on table PBC_REPORT_BALANCE_DAT
  is '资管产品资产负债统计表';
-- Add comments to the columns 
comment on column PBC_REPORT_BALANCE_DAT.detailuuid
  is '明细统计uuid';
comment on column PBC_REPORT_BALANCE_DAT.reporttype
  is '报表类型2_1-资管产品资产负债统计';
comment on column PBC_REPORT_BALANCE_DAT.grouptype
  is '分组类型 资产-ASSET，负债-DEBT 资产负债合计-ASSETDEBTSUM';
comment on column PBC_REPORT_BALANCE_DAT.propertycode
  is '属性代码';
comment on column PBC_REPORT_BALANCE_DAT.cny
  is '人民币';
comment on column PBC_REPORT_BALANCE_DAT.usd_to_cny
  is '美元折人民币';
comment on column PBC_REPORT_BALANCE_DAT.eur_to_cny
  is '欧元折人民币';
comment on column PBC_REPORT_BALANCE_DAT.gbp_to_cny
  is '英镑折人民币';
comment on column PBC_REPORT_BALANCE_DAT.jpy_to_cny
  is '日元折人民币';
comment on column PBC_REPORT_BALANCE_DAT.oth_to_cny
  is '其他币种折人名币';
comment on column PBC_REPORT_BALANCE_DAT.total
  is '合计';
comment on column PBC_REPORT_BALANCE_DAT.reportuuid
  is '主表关联id';
comment on column PBC_REPORT_BALANCE_DAT.ccy_amount
  is '币种金额信息，多币种存值，人民币也存值，格式：CNY,原币金额,折人民币金额;USD,原币金额,折人民币金额';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PBC_REPORT_BALANCE_DAT
  add constraint PBC_REPORT_BALANCE_DAT_PK primary key (DETAILUUID);
  

comment on table PBC_REPORT_BALANCETERM_DAT
  is '资管产品资产负债剩余期限统计表';
-- Add comments to the columns 
comment on column PBC_REPORT_BALANCETERM_DAT.detailuuid
  is '明细统计uuid';
comment on column PBC_REPORT_BALANCETERM_DAT.reporttype
  is '报表类型:3_3-资管产品资产负债剩余期限统计';
comment on column PBC_REPORT_BALANCETERM_DAT.grouptype
  is '分组类型 资产-ASSET，负债-DEBT';
comment on column PBC_REPORT_BALANCETERM_DAT.propertycode
  is '属性代码';
comment on column PBC_REPORT_BALANCETERM_DAT.debt_instrument
  is '债务工具';
comment on column PBC_REPORT_BALANCETERM_DAT.stock_right
  is '股权';
comment on column PBC_REPORT_BALANCETERM_DAT.special_share
  is '特定目的载体份额';
comment on column PBC_REPORT_BALANCETERM_DAT.actual_amt
  is '实收本金';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PBC_REPORT_BALANCETERM_DAT
  add constraint PBC_REPORT_BALANCETERM_DAT_PK primary key (DETAILUUID);
  
  
comment on table PBC_REPORT_COMPANY_DAT
  is '除回购和拆借外贷款分行业、企业规模统计表';
-- Add comments to the columns 
comment on column PBC_REPORT_COMPANY_DAT.detailuuid
  is '明细统计uuid';
comment on column PBC_REPORT_COMPANY_DAT.reporttype
  is '报表类型2_5-企业债券分行业、企业规模统计;2_2-除回购和拆借外贷款分行业、企业规模统计';
comment on column PBC_REPORT_COMPANY_DAT.grouptype
  is '分组类型 企业-COMPANY';
comment on column PBC_REPORT_COMPANY_DAT.propertycode
  is '属性代码';
comment on column PBC_REPORT_COMPANY_DAT.large_company_amt
  is '大型企业';
comment on column PBC_REPORT_COMPANY_DAT.medium_company_amt
  is '中型企业';
comment on column PBC_REPORT_COMPANY_DAT.small_company_amt
  is '小型企业';
comment on column PBC_REPORT_COMPANY_DAT.micro_company_amt
  is '微型企业';
comment on column PBC_REPORT_COMPANY_DAT.oth_amt
  is '其他（住户、广义政府及境外）';
comment on column PBC_REPORT_COMPANY_DAT.sum_amt
  is '合计';
comment on column PBC_REPORT_COMPANY_DAT.reportuuid
  is '主表关联id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PBC_REPORT_COMPANY_DAT
  add constraint PBC_REPORT_COMPANY_DAT_PK primary key (DETAILUUID);
  
ALTER TABLE PBC_PRD_BASEINFO_END_SUB_DAT ADD PROD_ID VARCHAR2(150);
ALTER TABLE PBC_PRD_BASEINFO_END_SUB_NEW ADD PROD_ID VARCHAR2(150);
ALTER TABLE PBC_PRD_BASEEND_SUB_NEW_HIS ADD PROD_ID VARCHAR2(150);

comment on column PBC_PRD_BASEINFO_END_SUB_DAT.PROD_ID
  is '理财产品代码';
comment on column PBC_PRD_BASEINFO_END_SUB_NEW.PROD_ID
  is '理财产品代码';
comment on column PBC_PRD_BASEEND_SUB_NEW_HIS.PROD_ID
  is '理财产品代码';