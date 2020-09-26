 alter table ASSET_TARGET_DB add DV01 NUMBER(30,14);
comment on column ASSET_TARGET_DB.DV01 is '基点价值';
 alter table ASSET_TARGET_DB add FLOAT_INGPL_ADD_PRICE NUMBER(30,14);
comment on column ASSET_TARGET_DB.FLOAT_INGPL_ADD_PRICE is '浮盈浮亏(单价)';
 alter table ASSET_TARGET_DB add FLOAT_INGPL_ADD NUMBER(30,14);
comment on column ASSET_TARGET_DB.FLOAT_INGPL_ADD is '浮盈浮亏';