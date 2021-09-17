DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValSecBaseInfo' AND COL_E_NAME IN ('','','conFinprodId');

insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('债券基础信息', 'ValSecBaseInfo', '转股市场代码', 'comShareId', '1', 'N', 50, '1', null, 'system', null, date '2021-09-17', 'system', date '2021-09-17');

insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('债券基础信息', 'ValSecBaseInfo', '转股起始日', 'conVdate', '1', 'N', 10, '1',null, 'system', null, date '2021-09-17', 'system', date '2021-09-17');

insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('债券基础信息', 'ValSecBaseInfo', '正股代码', 'conFinprodId', '1', 'N', 50, '1', null, 'system', null, date '2021-09-17', 'system', date '2021-09-17');
