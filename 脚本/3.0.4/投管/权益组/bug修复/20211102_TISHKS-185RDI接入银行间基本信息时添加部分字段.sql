alter table interf_cfets_trader_tmp ADD (PARTY_CUSTOMER_NAME VARCHAR2(200));
comment on column interf_cfets_trader_tmp.PARTY_CUSTOMER_NAME is '交易成员名称';


alter table interf_cfets_party_rela_tmp add (MPARTY_NAME  VARCHAR2(200));
alter table interf_cfets_party_rela_tmp add (SPARTY_NAME  VARCHAR2(200));
alter table interf_cfets_party_rela_tmp add (MPARTY_TYPE_NAME  VARCHAR2(100));
alter table interf_cfets_party_rela_tmp add (SPARTY_TYPE_NAME  VARCHAR2(100));

comment on column interf_cfets_party_rela_tmp.MPARTY_NAME is '主机构中文全称';
comment on column interf_cfets_party_rela_tmp.SPARTY_NAME is '从机构中文全称';
comment on column interf_cfets_party_rela_tmp.MPARTY_TYPE_NAME is '主关系类型名称';
comment on column interf_cfets_party_rela_tmp.SPARTY_TYPE_NAME is '从关系类型名称';