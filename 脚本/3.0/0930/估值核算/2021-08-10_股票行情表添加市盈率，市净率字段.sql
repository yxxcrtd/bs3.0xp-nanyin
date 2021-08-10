--股票行情表添加市盈率字段
alter table MST_SHARE_PRICE add PE NUMBER(26,4);
comment on column MST_SHARE_PRICE.PE is '市盈率';
--股票行情表添加市净率字段
alter table MST_SHARE_PRICE add PB NUMBER(26,4);
comment on column MST_SHARE_PRICE.PB is '市净率';