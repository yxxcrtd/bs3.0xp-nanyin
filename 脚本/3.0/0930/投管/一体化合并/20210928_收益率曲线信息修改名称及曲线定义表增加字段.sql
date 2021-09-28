update sys_menu set remark = '收益率曲线行情' where menu_id = 'ASSET_MANAGEMENT_001_003_003' and remark like '收益率曲线%';


--收益率曲线新增字段
alter table MST_CURVE_DEF add CURVE_GROUP_TYPE VARCHAR2(20);
comment on column MST_CURVE_DEF.CURVE_GROUP_TYPE is '是否曲线族  Y是 N 否';

alter table MST_CURVE_DEF add GRADE_RESULT VARCHAR2(50);
comment on column MST_CURVE_DEF.GRADE_RESULT is '评级结果';

alter table MST_CURVE_DEF add CURVE_GROUP_CODE VARCHAR2(50);
comment on column MST_CURVE_DEF.CURVE_GROUP_CODE is '所属曲线族';

alter table MST_CURVE_DEF add CCY VARCHAR2(50);
comment on column MST_CURVE_DEF.CCY is '币种';

alter table MST_CURVE_DEF add CURVE_NAME VARCHAR2(200);
comment on column MST_CURVE_DEF.CURVE_NAME is '曲线名称';


update MST_CURVE_DEF mcd set mcd.CURVE_NAME = (select sdl.item_value from sys_datadict_list sdl where mcd.curve_code =  sdl.item_code
and  sdl.dict_code = 'CURVE_CODE')
where mcd.curve_code in (select item_code from sys_datadict_list where dict_code = 'CURVE_CODE');
