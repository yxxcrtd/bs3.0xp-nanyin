alter table ETR_SEC_POSITION_FLOW add (CLOSE_FLAG VARCHAR2(50));
comment on column ETR_SEC_POSITION_FLOW.CLOSE_FLAG is '关闭时冲销标识，关闭：01，重新打开：02';