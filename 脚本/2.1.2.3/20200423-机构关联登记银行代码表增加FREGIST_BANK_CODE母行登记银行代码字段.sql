/********** 机构关联登记银行代码表增加 FREGIST_BANK_CODE 母行登记银行代码 字段  start *********/
alter table CHB_ORG_REGIST_BANK_CODE add FREGIST_BANK_CODE VARCHAR2(20);
comment on column CHB_ORG_REGIST_BANK_CODE.FREGIST_BANK_CODE is '母行登记银行代码';
/********** 机构关联登记银行代码表增加 FREGIST_BANK_CODE 母行登记银行代码 字段  end *********/
