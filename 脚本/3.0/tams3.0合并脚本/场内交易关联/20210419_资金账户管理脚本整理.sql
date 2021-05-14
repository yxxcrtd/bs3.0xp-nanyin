/*==============================================================*/
/* 字典及字典明细                                               */
/*==============================================================*/
/*字典明细：账户类型*/
delete from SYS_DATADICT_LIST where DICT_CODE = 'ACCOUNT_NATURE' and ITEM_CODE in ('01','02');
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('ACCOUNT_NATURE','01','银行账户',1,'0','system',null,sysdate,'system',sysdate);

Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('ACCOUNT_NATURE','02','非银行账户',2,'0','system',null,sysdate,'system',sysdate);


/*==============================================================*/
/* 建表                                                         */
/*==============================================================*
  
 -- Create table
/*清算账户关联表*/
drop table STL_CAPACCT_RE_INFO;
create table STL_CAPACCT_RE_INFO
(
  acct_no     VARCHAR2(50) not null,
  ccy         VARCHAR2(50) not null,
  acct_type   VARCHAR2(50) not null,
  create_user VARCHAR2(20) not null,
  create_dept VARCHAR2(32),
  create_time TIMESTAMP(6) not null,
  update_user VARCHAR2(20) not null,
  update_time TIMESTAMP(6) not null,
  constraint PK_STL_CAPACCT_RE_INFO primary key (ACCT_NO, CCY, ACCT_TYPE)
);
-- Add comments to the table 
comment on table STL_CAPACCT_RE_INFO
  is '清算账户关联表';
-- Add comments to the columns 
comment on column STL_CAPACCT_RE_INFO.acct_no
  is '网点账号，网点号+账号';
comment on column STL_CAPACCT_RE_INFO.ccy
  is '币种';
comment on column STL_CAPACCT_RE_INFO.acct_type
  is '账户类型';
comment on column STL_CAPACCT_RE_INFO.create_user
  is '创建人';
comment on column STL_CAPACCT_RE_INFO.create_dept
  is '创建部门';
comment on column STL_CAPACCT_RE_INFO.create_time
  is '创建时间';
comment on column STL_CAPACCT_RE_INFO.update_user
  is '更新人';
comment on column STL_CAPACCT_RE_INFO.update_time
  is '更新时间';



/*==============================================================*/
/* 表结构改动                                                   */
/*==============================================================*/

/*清算账户表 增加字段:账户性质,是否计提活期利息, 账户类型, 所属营业部, 账户密码, 交易密码*/
alter table STL_CAPACCT_INFO add ACCOUNT_NATURE VARCHAR2(2);
comment on column STL_CAPACCT_INFO.ACCOUNT_NATURE
  is '账户性质';
  
alter table STL_CAPACCT_INFO add IS_CALINT VARCHAR2(2);
comment on column STL_CAPACCT_INFO.IS_CALINT
  is '是否计提活期利息';
  
alter table STL_CAPACCT_INFO add OWN_DEPT VARCHAR2(100);
comment on column STL_CAPACCT_INFO.OWN_DEPT
  is '所属营业部';
  
alter table STL_CAPACCT_INFO add ACC_PASS VARCHAR2(50);
comment on column STL_CAPACCT_INFO.ACC_PASS
  is '账户密码';
  
alter table STL_CAPACCT_INFO add TRADE_PASS VARCHAR2(50);
comment on column STL_CAPACCT_INFO.TRADE_PASS
  is '交易密码';
