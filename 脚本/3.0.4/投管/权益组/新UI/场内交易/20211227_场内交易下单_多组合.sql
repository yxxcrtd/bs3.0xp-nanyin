/**指令表新增 委托指令类别**/
alter table T_INSTRUCTION add ENTRUST_CLASS varchar2(50)
;

comment on column T_INSTRUCTION.ENTRUST_CLASS is '委托指令类别'
;

/**新增 指令多组合明细表**/
DROP TABLE T_INSTRUCTION_DETAIL;
create table T_INSTRUCTION_DETAIL
(
    ID             VARCHAR2(64) not null
        primary key,
    ENTRUST_ID     VARCHAR2(32) not null,
    PORTFOLIO_ID   VARCHAR2(32),
    PORTFOLIO_NAME VARCHAR2(200),
    DIS_RATE       NUMBER(30, 2) default 0,
    ENTRUST_AMT    NUMBER(30, 2) default 0,
    ENTRUST_NUM    NUMBER(30, 2) default 0,
    TARGET_TYPE    VARCHAR2(50)
)
;

comment on table T_INSTRUCTION_DETAIL is '指令多组合明细表'
;

comment on column T_INSTRUCTION_DETAIL.ENTRUST_ID is '指令编号'
;

comment on column T_INSTRUCTION_DETAIL.PORTFOLIO_ID is '组合代码'
;

comment on column T_INSTRUCTION_DETAIL.PORTFOLIO_NAME is '组合名称'
;

comment on column T_INSTRUCTION_DETAIL.DIS_RATE is '分配比例'
;

comment on column T_INSTRUCTION_DETAIL.ENTRUST_AMT is '委托金额'
;

comment on column T_INSTRUCTION_DETAIL.ENTRUST_NUM is '委托数量'
;

comment on column T_INSTRUCTION_DETAIL.TARGET_TYPE is '目标类型'
;

