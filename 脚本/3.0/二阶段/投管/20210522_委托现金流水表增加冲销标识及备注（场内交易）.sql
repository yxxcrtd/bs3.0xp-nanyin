alter table ETR_CASH_POSITION_FLOW add (CLOSE_FLAG VARCHAR2(50));
comment on column ETR_CASH_POSITION_FLOW.CLOSE_FLAG is '关闭时冲销标识，关闭：01，重新打开：02';
alter table ETR_CASH_POSITION_FLOW add (REMARK VARCHAR2(200));
comment on column ETR_CASH_POSITION_FLOW.REMARK is '备注';

