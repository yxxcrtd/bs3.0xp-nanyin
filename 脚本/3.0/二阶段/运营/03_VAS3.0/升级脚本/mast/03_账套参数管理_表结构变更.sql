----------------账套参数管理：新增字段【参数类型】 start--------------------------
alter table BOK_BOOKSET_EXT add PARAM_TYPE VARCHAR2(50);
comment on column BOK_BOOKSET_EXT.PARAM_TYPE
  is '参数类型';
----------------账套参数管理：新增字段【参数类型】 end--------------------------