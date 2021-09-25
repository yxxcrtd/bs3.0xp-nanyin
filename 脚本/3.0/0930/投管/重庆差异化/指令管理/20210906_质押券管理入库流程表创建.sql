drop table WFL_ETR_ENTRUST_PLEDGE;
create table WFL_ETR_ENTRUST_PLEDGE
(
    PORTFOLIO_ID    VARCHAR2(32) not null,
    FINPROD_ID      VARCHAR2(50) not null,
    BRANCH          NUMBER(10)   not null,
    SEC_ACCT_ID     VARCHAR2(32) not null,
    SCALE           NUMBER(30, 14),
    CREATE_USER     VARCHAR2(20) not null,
    CREATE_DEPT     VARCHAR2(32),
    CREATE_TIME     TIMESTAMP(6) not null,
    UPDATE_USER     VARCHAR2(20) not null,
    UPDATE_TIME     TIMESTAMP(6) not null,
    PLEDGE_STOCK_ID VARCHAR2(50),
    WORKFLOW_ID     VARCHAR2(32),
    constraint PK_WFL_ETR_ENTRUST_PLEDGE
        primary key (PORTFOLIO_ID, FINPROD_ID, BRANCH, SEC_ACCT_ID)
);
comment on table WFL_ETR_ENTRUST_PLEDGE is '指令质押券';
comment on column WFL_ETR_ENTRUST_PLEDGE.PORTFOLIO_ID is '组合代码';
comment on column WFL_ETR_ENTRUST_PLEDGE.FINPROD_ID is '金融产品代码';
comment on column WFL_ETR_ENTRUST_PLEDGE.BRANCH is '分支序号';
comment on column WFL_ETR_ENTRUST_PLEDGE.SEC_ACCT_ID is '证券账户代码/通道代码';
comment on column WFL_ETR_ENTRUST_PLEDGE.SCALE is '折算比例';
comment on column WFL_ETR_ENTRUST_PLEDGE.CREATE_USER is '创建人';
comment on column WFL_ETR_ENTRUST_PLEDGE.CREATE_DEPT is '创建部门';
comment on column WFL_ETR_ENTRUST_PLEDGE.CREATE_TIME is '创建时间';
comment on column WFL_ETR_ENTRUST_PLEDGE.UPDATE_USER is '更新人';
comment on column WFL_ETR_ENTRUST_PLEDGE.UPDATE_TIME is '更新时间';
comment on column WFL_ETR_ENTRUST_PLEDGE.PLEDGE_STOCK_ID is '质押券库代码';
comment on column WFL_ETR_ENTRUST_PLEDGE.WORKFLOW_ID is '流程编号';