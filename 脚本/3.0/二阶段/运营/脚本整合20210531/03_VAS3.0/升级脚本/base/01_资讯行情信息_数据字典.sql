/*基准汇率行情信息（新增2个字段：行情类型、期限）*/
---------------------------------------基准汇率行情类型 start--------------------------------
delete from sys_datadict where dict_code = 'BASIC_RATE_MARKET_TYPE';

insert into sys_datadict 
(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, 
DICT_ENNAME, UPDATE_FLG, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BASIC_RATE_MARKET_TYPE', 'VAL', '基准汇率行情类型', 
null, '02', 'system', 
null, sysdate, 'system', sysdate);


delete from sys_datadict_list where DICT_CODE = 'BASIC_RATE_MARKET_TYPE';

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BASIC_RATE_MARKET_TYPE', '01', '远期', 
1, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BASIC_RATE_MARKET_TYPE', '02', '即期', 
2, '0', 'system', 
null, sysdate, 'system', sysdate);
---------------------------------------基准汇率行情类型 end--------------------------------


---------------------------------------基准汇率期限 start--------------------------------
delete from sys_datadict where dict_code = 'BASIC_RATE_DEAD_LINE';

insert into sys_datadict 
(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, 
DICT_ENNAME, UPDATE_FLG, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BASIC_RATE_DEAD_LINE', 'VAL', '基准汇率期限', 
null, '02', 'system', 
null, sysdate, 'system', sysdate);


delete from sys_datadict_list where DICT_CODE = 'BASIC_RATE_DEAD_LINE';

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BASIC_RATE_DEAD_LINE', 'ONE_WEEK', '一周', 
1, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BASIC_RATE_DEAD_LINE', 'ONE_MONTH', '一个月', 
2, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BASIC_RATE_DEAD_LINE', 'THREE_MONTH', '三个月', 
3, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BASIC_RATE_DEAD_LINE', 'SIX_MONTH', '六个月', 
4, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BASIC_RATE_DEAD_LINE', 'NINE_MONTH', '九个月', 
5, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('BASIC_RATE_DEAD_LINE', 'ONE_YEAR', '一年', 
6, '0', 'system', 
null, sysdate, 'system', sysdate);
---------------------------------------基准汇率期限 end--------------------------------



-------------------新增字段：数据来源 start---------------------------
delete from sys_datadict where dict_code = 'VAL_DATA_SOURCE';

insert into sys_datadict 
(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, 
DICT_ENNAME, UPDATE_FLG, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('VAL_DATA_SOURCE', 'VAL', '估值数据来源', 
null, '02', 'system', 
null, sysdate, 'system', sysdate);




delete from sys_datadict_list where DICT_CODE = 'VAL_DATA_SOURCE';

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('VAL_DATA_SOURCE', '01', '手工', 
1, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('VAL_DATA_SOURCE', '02', 'Wind', 
2, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('VAL_DATA_SOURCE', '03', '资管系统', 
3, '0', 'system', 
null, sysdate, 'system', sysdate);

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, 
ORDER_NO, DEFAULT_VALUE, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('VAL_DATA_SOURCE', '04', '投管系统', 
4, '0', 'system', 
null, sysdate, 'system', sysdate);
-------------------新增字段：数据来源 end---------------------------
























