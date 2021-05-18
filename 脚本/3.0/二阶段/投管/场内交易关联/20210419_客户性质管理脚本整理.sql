/*==============================================================*/
/* 字典及字典明细                                               */
/*==============================================================*/

/*字典明细：客户性质增加明细 券商,交易对手*/

delete from sys_datadict_list  where DICT_CODE = 'CUSTOMER_NATURE' and ITEM_CODE = '10';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CUSTOMER_NATURE', '10', '券商', 10, '0', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list  where DICT_CODE = 'CUSTOMER_NATURE' and ITEM_CODE = '12';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CUSTOMER_NATURE', '12', '交易对手', 12, '0', 'system', null, sysdate, 'system', sysdate);

/*画面数据字典明细关联: 客户性质修改*/
delete from sys_page_dictlist where PAGE_ID = 'MstCustomerNature003' and DICT_ID = 'CUSTOMER_NATURE' and DICT_ITEM_VAL in ('10','12');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MstCustomerNature003', '999999', 'CUSTOMER_NATURE', '10', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MstCustomerNature003', '999999', 'CUSTOMER_NATURE', '12', null, null, 'system', null, sysdate, 'system', sysdate, null);


/*客户信息表 新增字段：是否银行,是否券商,是否交易对手*/
alter table MST_CUSTOMER_INFO add (IS_BANK varchar2(50));
comment on column MST_CUSTOMER_INFO.IS_BANK is '是否银行';

ALTER TABLE MST_CUSTOMER_INFO ADD IS_BROKER VARCHAR2(50);
COMMENT ON COLUMN MST_CUSTOMER_INFO.IS_BROKER IS '是否券商';

alter table MST_CUSTOMER_INFO add(IS_COUNTER varchar2(50));
comment on column MST_CUSTOMER_INFO.IS_COUNTER is '是否交易对手';


