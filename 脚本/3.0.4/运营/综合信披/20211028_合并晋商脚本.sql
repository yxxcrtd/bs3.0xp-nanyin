--信批 产品基本信息 新增字段：分红方式、最少追加金额

  alter table REP_PROD_INFO add BONUS_MODE varchar2(2);
comment on column REP_PROD_INFO.BONUS_MODE
  is '分红方式:数据字典BONUS_MODE';

  alter table REP_PROD_INFO add LOWEST_AMT NUMBER(30,4);
comment on column REP_PROD_INFO.LOWEST_AMT
  is '最少追加金额';

  --信批 产品费率接口 新增银行收取比例（浮动管理费时填写）
   alter table REP_CASH_YIELD add CHARGE_RATIO NUMBER(30,4);
comment on column REP_CASH_YIELD.CHARGE_RATIO
  is '银行收取比例';


--监管报送子系统 表结构修改
ALTER TABLE CHB_BOT_AST_POS ADD  int_amt        NUMBER(30,2);
ALTER TABLE CHB_BOT_AST_POS ADD  cash_flag       VARCHAR2(2);

comment on column CHB_BOT_AST_POS.int_amt
  is '利息';
comment on column CHB_BOT_AST_POS.cash_flag
  is '是否只有现金 01 是,02 否';


-- 增加系统参数，控制报送数据是否同步上游数据。如果以监管为准，则手工维护后，不再同步上游数据。产品目前是上游推数就会覆盖。
delete from sys_para s where s.para_code='SVR_DATA_BENCHMARK';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('SVR', 'SVR_DATA_BENCHMARK', 'SVR', '02', 'SVR,INV', '监管报送数据基准(SVR:以监管维护为准,INV:以上游投管系统为准)', 'system', null, '19-9月 -19 01.49.58.000000 下午', 'system', '19-9月 -19 01.49.58.000000 下午', null, '01');


--修改产品基础指标数据的数据来源为xml文件。
update chb_interf_config c set c.content_src='X' where c.interf_id='DIS_PRD_INDEX';

