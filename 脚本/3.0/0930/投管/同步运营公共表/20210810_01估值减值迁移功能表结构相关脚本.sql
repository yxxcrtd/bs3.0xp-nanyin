--标的类金融产品附属表添加是否含有利率调整权字段
alter table FIN_PRODUCT_ADD add is_rate_adjust varchar2(5);
comment on column FIN_PRODUCT_ADD.is_rate_adjust is
'是否含有利率调整权 ''N''-否，''Y''-是';



--股票行情表添加市盈率字段
alter table MST_SHARE_PRICE add PE NUMBER(26,4);
comment on column MST_SHARE_PRICE.PE is '市盈率';
--股票行情表添加市净率字段
alter table MST_SHARE_PRICE add PB NUMBER(26,4);
comment on column MST_SHARE_PRICE.PB is '市净率';