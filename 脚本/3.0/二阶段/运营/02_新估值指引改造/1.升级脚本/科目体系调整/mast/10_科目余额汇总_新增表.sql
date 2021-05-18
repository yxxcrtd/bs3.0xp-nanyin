--估值批处理：科目余额汇总批处理
create table BOK_BALANCE_BEF
(
  bookset_id        VARCHAR2(50) not null,
  balance_date      DATE not null,
  subject_no        VARCHAR2(300) not null,
  fsubject_no       VARCHAR2(32),
  subject_level     NUMBER(10),
  bal_flag          VARCHAR2(50),
  bal_co_flag       VARCHAR2(50),
  o_ccy             VARCHAR2(50),
  o_amt             NUMBER(30,2),
  o_c_amt           NUMBER(30,2),
  o_d_amt           NUMBER(30,2),
  o_co_amt          NUMBER(30,2),
  o_co_c_amt        NUMBER(30,2),
  o_co_d_amt        NUMBER(30,2),
  b_ccy             VARCHAR2(50),
  b_amt             NUMBER(30,2),
  b_c_amt           NUMBER(30,2),
  b_co_amt          NUMBER(30,2),
  b_co_c_amt        NUMBER(30,2),
  b_co_d_amt        NUMBER(30,2),
  b_d_amt           NUMBER(30,2),
  tdy_o_amt         NUMBER(30,2),
  tdy_o_c_amt       NUMBER(30,2),
  tdy_o_d_amt       NUMBER(30,2),
  tdy_o_co_amt      NUMBER(30,2),
  tdy_o_co_c_amt    NUMBER(30,2),
  tdy_o_co_d_amt    NUMBER(30,2),
  tdy_b_amt         NUMBER(30,2),
  tdy_b_c_amt       NUMBER(30,2),
  tdy_b_d_amt       NUMBER(30,2),
  tdy_b_co_amt      NUMBER(30,2),
  tdy_b_co_c_amt    NUMBER(30,2),
  tdy_b_co_d_amt    NUMBER(30,2),
  is_leaf           VARCHAR2(50),
  num_amt           NUMBER(30,2),
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null,
  tdy_amt_flag      VARCHAR2(50),
  tdy_co_flag       VARCHAR2(50),
  tdy_pur_o_d_ulamt NUMBER(30,2),
  tdy_red_o_d_ulamt NUMBER(30,2),
  tdy_pur_o_c_ulamt NUMBER(30,2),
  tdy_red_o_c_ulamt NUMBER(30,2),
  tdy_pur_b_d_ulamt NUMBER(30,2),
  tdy_red_b_d_ulamt NUMBER(30,2),
  tdy_pur_b_c_ulamt NUMBER(30,2),
  tdy_red_b_c_ulamt NUMBER(30,2)
);
comment on table BOK_BALANCE_BEF
  is '科目余额_日初';
comment on column BOK_BALANCE_BEF.bookset_id
  is '账套代码';
comment on column BOK_BALANCE_BEF.balance_date
  is '余额日期';
comment on column BOK_BALANCE_BEF.subject_no
  is '科目号';
comment on column BOK_BALANCE_BEF.fsubject_no
  is '父科目号';
comment on column BOK_BALANCE_BEF.subject_level
  is '科目级别';
comment on column BOK_BALANCE_BEF.bal_flag
  is '余额方向';
comment on column BOK_BALANCE_BEF.bal_co_flag
  is '结转余额方向';
comment on column BOK_BALANCE_BEF.o_ccy
  is '原币';
comment on column BOK_BALANCE_BEF.o_amt
  is '原币余额';
comment on column BOK_BALANCE_BEF.o_c_amt
  is '原币余额贷方';
comment on column BOK_BALANCE_BEF.o_d_amt
  is '原币余额借方';
comment on column BOK_BALANCE_BEF.o_co_amt
  is '原币结转余额';
comment on column BOK_BALANCE_BEF.o_co_c_amt
  is '原币结转余额贷方';
comment on column BOK_BALANCE_BEF.o_co_d_amt
  is '原币结转余额借方';
comment on column BOK_BALANCE_BEF.b_ccy
  is '本位币';
comment on column BOK_BALANCE_BEF.b_amt
  is '本位币余额';
comment on column BOK_BALANCE_BEF.b_c_amt
  is '本位币余额贷方';
comment on column BOK_BALANCE_BEF.b_co_amt
  is '本位币结转余额';
comment on column BOK_BALANCE_BEF.b_co_c_amt
  is '本位币结转余额贷方';
comment on column BOK_BALANCE_BEF.b_co_d_amt
  is '本位币结转余额借方';
comment on column BOK_BALANCE_BEF.b_d_amt
  is '本位币余额借方';
comment on column BOK_BALANCE_BEF.tdy_o_amt
  is '原币当日发生额';
comment on column BOK_BALANCE_BEF.tdy_o_c_amt
  is '原币当日发生额贷方';
comment on column BOK_BALANCE_BEF.tdy_o_d_amt
  is '原币当日发生额借方';
comment on column BOK_BALANCE_BEF.tdy_o_co_amt
  is '原币结转当日发生额';
comment on column BOK_BALANCE_BEF.tdy_o_co_c_amt
  is '原币结转当日发生额贷方';
comment on column BOK_BALANCE_BEF.tdy_o_co_d_amt
  is '原币结转当日发生额借方';
comment on column BOK_BALANCE_BEF.tdy_b_amt
  is '本位币当日发生额';
comment on column BOK_BALANCE_BEF.tdy_b_c_amt
  is '本位币当日发生额贷方';
comment on column BOK_BALANCE_BEF.tdy_b_d_amt
  is '本位币当日发生额借方';
comment on column BOK_BALANCE_BEF.tdy_b_co_amt
  is '本位币结转当日发生额';
comment on column BOK_BALANCE_BEF.tdy_b_co_c_amt
  is '本位币结转当日发生额贷方';
comment on column BOK_BALANCE_BEF.tdy_b_co_d_amt
  is '本位币结转当日发生额借方';
comment on column BOK_BALANCE_BEF.is_leaf
  is '是否叶子节点';
comment on column BOK_BALANCE_BEF.num_amt
  is '数量余额';
comment on column BOK_BALANCE_BEF.create_user
  is '创建人';
comment on column BOK_BALANCE_BEF.create_dept
  is '创建部门';
comment on column BOK_BALANCE_BEF.create_time
  is '创建时间';
comment on column BOK_BALANCE_BEF.update_user
  is '更新人';
comment on column BOK_BALANCE_BEF.update_time
  is '更新时间';
comment on column BOK_BALANCE_BEF.tdy_amt_flag
  is '当日发生额方向';
comment on column BOK_BALANCE_BEF.tdy_co_flag
  is '结转当日发生额方向';
comment on column BOK_BALANCE_BEF.tdy_pur_o_d_ulamt
  is '原币申购未实现平准金借';
comment on column BOK_BALANCE_BEF.tdy_red_o_d_ulamt
  is '原币赎回未实现平准金借';
comment on column BOK_BALANCE_BEF.tdy_pur_o_c_ulamt
  is '原币申购未实现平准金贷';
comment on column BOK_BALANCE_BEF.tdy_red_o_c_ulamt
  is '原币赎回未实现平准金贷';
comment on column BOK_BALANCE_BEF.tdy_pur_b_d_ulamt
  is '本位币申购未实现平准金借';
comment on column BOK_BALANCE_BEF.tdy_red_b_d_ulamt
  is '本位币赎回未实现平准金借';
comment on column BOK_BALANCE_BEF.tdy_pur_b_c_ulamt
  is '本位币申购未实现平准金贷';
comment on column BOK_BALANCE_BEF.tdy_red_b_c_ulamt
  is '本位币赎回未实现平准金贷';
create index BOK_BALANCE_BEF_INDEX1 on BOK_BALANCE_BEF (BOOKSET_ID, BALANCE_DATE);
create unique index PK_BOK_BALANCE_BEF on BOK_BALANCE_BEF (BOOKSET_ID, BALANCE_DATE, SUBJECT_NO);
