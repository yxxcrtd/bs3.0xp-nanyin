

/**新增 指令多组合明细表**/
DROP table ETR_ENTRUST_DETAIL;


create table ETR_ENTRUST_DETAIL
(
    ENTRUST_ID     VARCHAR2(32) not null,
    PORTFOLIO_ID   VARCHAR2(32) not null,
    DIS_RATE       NUMBER(30, 14) ,
    ENTRUST_AMT    NUMBER(30, 2) ,
    ENTRUST_NUM    NUMBER(30, 4),
    TARGET_TYPE    VARCHAR2(50),
    CREATE_USER          VARCHAR2(20)         not null,
    CREATE_DEPT          VARCHAR2(32),
    CREATE_TIME          TIMESTAMP            not null,
    UPDATE_USER          VARCHAR2(20)         not null,
    UPDATE_TIME          TIMESTAMP            not null
);

comment on table ETR_ENTRUST_DETAIL is '指令多组合明细表';

comment on column ETR_ENTRUST_DETAIL.ENTRUST_ID is '指令编号';

comment on column ETR_ENTRUST_DETAIL.PORTFOLIO_ID is '组合代码';

comment on column ETR_ENTRUST_DETAIL.DIS_RATE is '分配比例';

comment on column ETR_ENTRUST_DETAIL.ENTRUST_AMT is '委托金额';

comment on column ETR_ENTRUST_DETAIL.ENTRUST_NUM is '委托数量';

comment on column ETR_ENTRUST_DETAIL.TARGET_TYPE is '目标类型';

comment on column ETR_ENTRUST_DETAIL.CREATE_USER is '创建人';

comment on column ETR_ENTRUST_DETAIL.CREATE_DEPT is '创建部门';

comment on column ETR_ENTRUST_DETAIL.CREATE_TIME is '创建时间';

comment on column ETR_ENTRUST_DETAIL.UPDATE_USER is '更新人';

comment on column ETR_ENTRUST_DETAIL.UPDATE_TIME is '更新时间';

