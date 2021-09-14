--PD数据字典枚举值缺少C+,对应C+之后的枚举值以及顺序都需要调整
DELETE FROM sys_datadict_list T WHERE T.DICT_CODE = 'MODEL_STANDARD_GRADE_RESULT' AND ITEM_VALUE IN ('C+','C','C-','D');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_STANDARD_GRADE_RESULT', '23', 'C+', 23, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_STANDARD_GRADE_RESULT', '24', 'C', 24, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_STANDARD_GRADE_RESULT', '25', 'C-', 25, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_STANDARD_GRADE_RESULT', '26', 'D', 26, '0', 'system', null, sysdate, 'system', sysdate, null);