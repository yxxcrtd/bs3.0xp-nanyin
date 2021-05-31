-------------------新增字段：参数类型 start---------------------------
delete from sys_datadict where dict_code = 'BOOKSET_PARAM_TYPE';

insert into sys_datadict 
(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, 
DICT_ENNAME, UPDATE_FLG, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', 'VAL', '账套参数类型', 
null, '02', 'system', 
null, sysdate, 'system', sysdate);




delete from sys_datadict_list where DICT_CODE = 'BOOKSET_PARAM_TYPE';

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '01', '资产计提', 
1, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '02', '费用计提', 
2, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '03', '批处理', 
3, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '04', '其他', 
4, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '05', '损益结转', 
5, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '06', '估值表指标', 
6, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '07', '费用损益处理', 
7, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '08', '浮动管理费', 
8, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '09', '产品红利再投', 
9, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '10', '公允价值', 
10, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '11', '行情', 
11, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '12', '减值', 
12, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BOOKSET_PARAM_TYPE', '13', '增值税', 
13, '0', 'system', 
null, sysdate, 'system', sysdate);
-------------------新增字段：参数类型 end---------------------------