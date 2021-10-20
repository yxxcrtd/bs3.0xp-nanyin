
--组合持仓公式配置表
--drop table "PTL_POSITION_CFG";
CREATE TABLE "PTL_POSITION_CFG"
(
    "SEQNO" VARCHAR2(100),
    "S_HOLDINGTYPE" VARCHAR2(100) NOT null,
    "CAL_FLAG" VARCHAR2(100) NOT null,
    "HOLDINGTYPE" VARCHAR2(100) NOT null,
    "REMARK" VARCHAR2(500),
    PRIMARY KEY ("SEQNO")
);

COMMENT on TABLE "PTL_POSITION_CFG"  is  '组合持仓公式配置表';
comment on column "PTL_POSITION_CFG"."SEQNO" is '流水号';
comment on column "PTL_POSITION_CFG"."S_HOLDINGTYPE" is '汇总持仓类型';
comment on column "PTL_POSITION_CFG"."CAL_FLAG" is '计算标识';
comment on column "PTL_POSITION_CFG"."HOLDINGTYPE" is '持仓类型';
comment on column "PTL_POSITION_CFG"."REMARK" is '备注';

--创建索引
--DROP INDEX PTL_P_C_inx_S_HOLDINGTYPE;
--DROP INDEX PTL_P_C_inx_HOLDINGTYPE;
CREATE INDEX PTL_P_C_inx_S_HOLDINGTYPE ON PTL_POSITION_CFG(S_HOLDINGTYPE);
CREATE INDEX PTL_P_C_inx_HOLDINGTYPE ON PTL_POSITION_CFG(HOLDINGTYPE);

--DROP SEQUENCE PTL_POSITION_CFG_SEQ;
create sequence PTL_POSITION_CFG_SEQ
    minvalue 1
    maxvalue 99999999999
    start with 1
    increment by 1
    cache 20;

--DROP TRIGGER PTL_POSITION_CFG_TRIGGER;
create or replace trigger PTL_POSITION_CFG_TRIGGER
before insert on PTL_POSITION_CFG for each row
begin
select to_char(PTL_POSITION_CFG_SEQ.nextval) into :new.SEQNO from dual;
end PTL_POSITION_CFG_TRIGGER;
