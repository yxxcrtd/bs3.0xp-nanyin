drop table STD_BOND_MORE_CONVRATE_INFO;
create table STD_BOND_MORE_CONVRATE_INFO
(
    SEQ_NO  VARCHAR(50) not null,
    BROKER_ID     VARCHAR2(50)   not null,
    ACCT_NO       VARCHAR2(50),
    FINPROD_ID    VARCHAR2(50)  not null,
    START_DATE    VARCHAR(40) not null,
    END_DATE    VARCHAR(40),
    MORE_CONVRATE NUMBER(30, 14) not null,
    CREATE_USER   VARCHAR2(20)   not null,
    CREATE_DEPT   VARCHAR2(32),
    CREATE_TIME   TIMESTAMP(6)   not null,
    UPDATE_USER   VARCHAR2(20)   not null,
    UPDATE_TIME   TIMESTAMP(6)   not null,
    constraint PK_STD_BOND_MORE_CONVRATE_INFO
        primary key (SEQ_NO)
);
comment on table STD_BOND_MORE_CONVRATE_INFO is '标准券再折算率信息';
comment on column STD_BOND_MORE_CONVRATE_INFO.SEQ_NO is '序列号';
comment on column STD_BOND_MORE_CONVRATE_INFO.BROKER_ID is '券商代码';
comment on column STD_BOND_MORE_CONVRATE_INFO.ACCT_NO is '资金账号';
comment on column STD_BOND_MORE_CONVRATE_INFO.FINPROD_ID is '资产代码';
comment on column STD_BOND_MORE_CONVRATE_INFO.START_DATE is '开始日期';
comment on column STD_BOND_MORE_CONVRATE_INFO.END_DATE is '结束日期';
comment on column STD_BOND_MORE_CONVRATE_INFO.MORE_CONVRATE is '再折算率';
comment on column STD_BOND_MORE_CONVRATE_INFO.CREATE_USER is '创建人';
comment on column STD_BOND_MORE_CONVRATE_INFO.CREATE_DEPT is '创建部门';
comment on column STD_BOND_MORE_CONVRATE_INFO.CREATE_TIME is '创建时间';
comment on column STD_BOND_MORE_CONVRATE_INFO.UPDATE_USER is '更新人';
comment on column STD_BOND_MORE_CONVRATE_INFO.UPDATE_TIME is '更新时间';