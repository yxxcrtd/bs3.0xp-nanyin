--授信估值穿透
alter table PTL_SEC_PIERCE_VALUTION add ZGPLAN_ID VARCHAR2(50);
comment on column PTL_SEC_PIERCE_VALUTION.ZGPLAN_ID is '资管计划代码';