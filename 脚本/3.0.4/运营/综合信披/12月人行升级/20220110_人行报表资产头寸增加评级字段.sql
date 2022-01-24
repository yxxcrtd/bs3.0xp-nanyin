--人行报表资产头寸增加评级字段
alter table PBC_MONTH_ASSET_POSITION add rating VARCHAR2(4);

comment on column PBC_MONTH_ASSET_POSITION.rating is '评级结果';