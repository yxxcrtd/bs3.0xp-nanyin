-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('WFL_ETR_TARGET') ;
    if num > 0 then
        execute immediate 'drop table WFL_ETR_TARGET' ;
    end if;
end;
/
create table WFL_ETR_TARGET
(
  entrust_id        VARCHAR2(32) not null,
  target_finprod_id VARCHAR2(32) not null,
  target_face_value NUMBER(30,2),
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null,
  WORKFLOW_ID 		VARCHAR2(32),
  WORKFLOW_STATUS   VARCHAR2(10)
)
;
-- Add comments to the table 
comment on table WFL_ETR_TARGET
  is '委托指令标的信息';
-- Add comments to the columns 
comment on column WFL_ETR_TARGET.entrust_id
  is '委托代码';
comment on column WFL_ETR_TARGET.target_finprod_id
  is '标的金融产品代码';
comment on column WFL_ETR_TARGET.target_face_value
  is '标的券面总额';
comment on column WFL_ETR_TARGET.create_user
  is '创建人';
comment on column WFL_ETR_TARGET.create_dept
  is '创建部门';
comment on column WFL_ETR_TARGET.create_time
  is '创建时间';
comment on column WFL_ETR_TARGET.update_user
  is '更新人';
comment on column WFL_ETR_TARGET.update_time
  is '更新时间';
comment on column WFL_ETR_TARGET.WORKFLOW_ID
  is '流程ID';
comment on column WFL_ETR_TARGET.WORKFLOW_STATUS
  is '流程状态';
-- Create/Recreate primary, unique and foreign key constraints 
alter table WFL_ETR_TARGET
  add constraint PK_WFL_ETR_TARGET primary key (ENTRUST_ID, TARGET_FINPROD_ID)
  using index 
  ;


--外汇掉期指令取消委托状态控制
DELETE FROM SYS_PAGE_DICTLIST spd WHERE PAGE_ID ='EtrWtFxswap001' AND DICT_ID ='ENTRUST_STATUS';
--利率互换指令取消委托状态控制
DELETE FROM SYS_PAGE_DICTLIST spd WHERE PAGE_ID ='EtrIRS001' AND DICT_ID ='ENTRUST_STATUS';
--外汇远期指令取消委托状态控制
DELETE FROM SYS_PAGE_DICTLIST spd WHERE PAGE_ID ='EtrForwex001' AND DICT_ID ='ENTRUST_STATUS';
--场内债券一级指令取消委托状态控制
DELETE FROM SYS_PAGE_DICTLIST spd WHERE PAGE_ID ='EtrBondFirstLBidding001' AND DICT_ID ='ENTRUST_STATUS';
--可转债转股指令取消委托状态控制
DELETE FROM SYS_PAGE_DICTLIST spd WHERE PAGE_ID ='EtrBondToStock001' AND DICT_ID ='ENTRUST_STATUS';
--股票指令指令取消委托状态控制
DELETE FROM SYS_PAGE_DICTLIST sdpm WHERE PAGE_ID ='EtrStock001' AND DICT_ID ='ENTRUST_STATUS';
--场内基金指令取消委托状态控制
DELETE from SYS_PAGE_DICTLIST spd WHERE PAGE_ID ='EtrFundEx001' AND DICT_ID ='ENTRUST_STATUS';
--存款类指令取消委托状态控制
DELETE from SYS_PAGE_DICTLIST spd WHERE PAGE_ID ='EtrBrowDepoInfo001' AND DICT_ID ='ENTRUST_STATUS';
--立项指令取消委托状态控制
DELETE from SYS_PAGE_DICTLIST spd WHERE PAGE_ID ='EtrInstruction001' AND DICT_ID ='ENTRUST_STATUS';
--计划类净值型指令取消委托状态控制
DELETE from SYS_PAGE_DICTLIST spd WHERE PAGE_ID ='EtrPlanAssetN001' AND DICT_ID ='ENTRUST_STATUS';
--计划类净值型指令取消委托状态控制
DELETE from SYS_PAGE_DICTLIST spd WHERE PAGE_ID ='EtrPlanAssetR001' AND DICT_ID ='ENTRUST_STATUS';
--项目类资产指令取消委托状态控制
DELETE FROM SYS_PAGE_DICTLIST spd WHERE PAGE_ID ='EtrProject001' AND DICT_ID ='ENTRUST_STATUS';