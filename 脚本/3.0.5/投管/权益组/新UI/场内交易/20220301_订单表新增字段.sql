
/**订单表新增字段**/
alter table T_ORDER add CON_NUM NUMBER(30, 4);
comment on column T_ORDER.CON_NUM is '可转债数量';

alter table T_ORDER add ENTRUST_CLASS varchar2(50);
comment on column T_ORDER.ENTRUST_CLASS is '委托指令类别：01-单组合，02-多组合';


/**历史订单令表新增字段**/
alter table T_ORDERHISTORY add CON_NUM NUMBER(30, 4);
comment on column T_ORDERHISTORY.CON_NUM is '可转债数量';

alter table T_ORDERHISTORY add ENTRUST_CLASS varchar2(50);
comment on column T_ORDERHISTORY.ENTRUST_CLASS is '委托指令类别：01-单组合，02-多组合';

