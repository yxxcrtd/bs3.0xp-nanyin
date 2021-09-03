--评级PD配置表新增评级PD类型字段
alter table MOD_GRADE_PD_CONFIG add PD_CONFIG_TYPE VARCHAR2(50) default '01' not null;
comment on column MOD_GRADE_PD_CONFIG.PD_CONFIG_TYPE is '评级pd类型 （01债券、其他债权、02回购、03存款）';

-- 更新评级PD配置 = 债券、其他债权评级PD配置
update sys_datadict_list set item_value='债券、其他债权评级PD配置' where dict_code='MODEL_DEC_CONFIG_TYPE' and item_code='03';

--模型_减值配置类型添加回购评级PD配置 和 存款评级PD配置
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_DEC_CONFIG_TYPE', '07', '回购评级PD配置', 7, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_DEC_CONFIG_TYPE', '08', '存款评级PD配置', 8, '0', 'system', null, sysdate, 'system', sysdate, null);