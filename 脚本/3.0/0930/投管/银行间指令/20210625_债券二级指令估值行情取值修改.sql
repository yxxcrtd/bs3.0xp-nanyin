--估值行情汇总批
-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('MST_BOND_VALUATION_INFO_TOTAL') ;
    if num > 0 then
        execute immediate 'drop table MST_BOND_VALUATION_INFO_TOTAL' ;
    end if;
end;
/
create table MST_BOND_VALUATION_INFO_TOTAL
(
  sec_id                   VARCHAR2(32) not null,
  value_date               DATE not null,
  value_source             VARCHAR2(50) not null,
  last_period              NUMBER(30,14),
  price                    NUMBER(30,14),
  netprice                 NUMBER(30,14),
  m_duration               NUMBER(30,14),
  m_convexity              NUMBER(30,14),
  bpvalue                  NUMBER(30,14),
  sduration                NUMBER(30,14),
  scnvxty                  NUMBER(30,14),
  interest_duration        NUMBER(30,14),
  interest_cnvxty          NUMBER(30,14),
  bpyield                  NUMBER(30,14),
  input_type               VARCHAR2(50),
  create_user              VARCHAR2(20) not null,
  create_dept              VARCHAR2(32),
  create_time              TIMESTAMP(6) not null,
  update_user              VARCHAR2(20) not null,
  update_time              TIMESTAMP(6) not null,
  mbpvalue                 NUMBER(30,14),
  var                      NUMBER(30,14),
  cvar                     NUMBER(30,14),
  mduration                NUMBER(30,14),
  implicit_grade           VARCHAR2(50),
  implicit_hgrade          VARCHAR2(50),
  netvaluation_exercise    NUMBER(18,12),
  netvaluation_recommended NUMBER(18,12)
)
;
-- Add comments to the table 
comment on table MST_BOND_VALUATION_INFO_TOTAL
  is '债券估值行情';
-- Add comments to the columns 
comment on column MST_BOND_VALUATION_INFO_TOTAL.sec_id
  is '债券代码，市场代码';
comment on column MST_BOND_VALUATION_INFO_TOTAL.value_date
  is '估值日';
comment on column MST_BOND_VALUATION_INFO_TOTAL.value_source
  is '估值来源，中债、中证等';
comment on column MST_BOND_VALUATION_INFO_TOTAL.last_period
  is '剩余期限';
comment on column MST_BOND_VALUATION_INFO_TOTAL.price
  is '市价全价';
comment on column MST_BOND_VALUATION_INFO_TOTAL.netprice
  is '市价净价';
comment on column MST_BOND_VALUATION_INFO_TOTAL.m_duration
  is '市价久期';
comment on column MST_BOND_VALUATION_INFO_TOTAL.m_convexity
  is '市价凸性';
comment on column MST_BOND_VALUATION_INFO_TOTAL.bpvalue
  is '基点价值';
comment on column MST_BOND_VALUATION_INFO_TOTAL.sduration
  is '利差久期';
comment on column MST_BOND_VALUATION_INFO_TOTAL.scnvxty
  is '利差凸性';
comment on column MST_BOND_VALUATION_INFO_TOTAL.interest_duration
  is '利率久期';
comment on column MST_BOND_VALUATION_INFO_TOTAL.interest_cnvxty
  is '利率凸性';
comment on column MST_BOND_VALUATION_INFO_TOTAL.bpyield
  is '市价收益率';
comment on column MST_BOND_VALUATION_INFO_TOTAL.input_type
  is '录入方式，接口、手工等';
comment on column MST_BOND_VALUATION_INFO_TOTAL.create_user
  is '创建人';
comment on column MST_BOND_VALUATION_INFO_TOTAL.create_dept
  is '创建部门';
comment on column MST_BOND_VALUATION_INFO_TOTAL.create_time
  is '创建时间';
comment on column MST_BOND_VALUATION_INFO_TOTAL.update_user
  is '更新人';
comment on column MST_BOND_VALUATION_INFO_TOTAL.update_time
  is '更新时间';
comment on column MST_BOND_VALUATION_INFO_TOTAL.mbpvalue
  is '估价基点价值';
comment on column MST_BOND_VALUATION_INFO_TOTAL.var
  is '风险价值';
comment on column MST_BOND_VALUATION_INFO_TOTAL.cvar
  is '条件风险价值';
comment on column MST_BOND_VALUATION_INFO_TOTAL.mduration
  is '麦考利久期';
comment on column MST_BOND_VALUATION_INFO_TOTAL.implicit_grade
  is '市价隐含评级（中债）';
comment on column MST_BOND_VALUATION_INFO_TOTAL.implicit_hgrade
  is '市价历史隐含评级（中债）';
comment on column MST_BOND_VALUATION_INFO_TOTAL.netvaluation_exercise
  is '估值净价(行权)';
comment on column MST_BOND_VALUATION_INFO_TOTAL.netvaluation_recommended
  is '估值净价(推荐)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table MST_BOND_VALUATION_INFO_TOTAL
  add constraint PK_MST_BOND_TOTAL primary key (SEC_ID, VALUE_DATE, VALUE_SOURCE)
  using index 
  ;

--债券估值行情取值规则
delete from sys_para where PARA_CODE = 'BOND_VALUATION_RULE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('ETR', 'BOND_VALUATION_RULE', '01', '01', null, '债券估值行情取值规则 01：优先取中债；02：根据托管机构取值', 'system', null, sysdate, 'admin', sysdate, null, '01');

--批处理任务
delete from sys_batch_task where BEAN_NAME = 'mstBondValuationTotalTask';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('20008', 'INV', '债券估值行情按配置规则生成汇总', null, 'mstBondValuationTotalTask', 17, 'system', null, sysdate, 'system', sysdate, null);