alter table FIN_PRODUCT_ADD add is_rate_adjust varchar2(5);
comment on column FIN_PRODUCT_ADD.is_rate_adjust is
'是否含有利率调整权 ''N''-否，''Y''-是';