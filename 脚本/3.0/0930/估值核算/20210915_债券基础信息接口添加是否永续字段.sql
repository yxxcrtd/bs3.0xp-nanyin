--债券基础信息接口添加是否永续字段

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('债券基础信息', 'ValSecBaseInfo', '是否永续', 'isSus', '1', 'N', 50, '1', 'YES_OR_NO', null, null, null, null, null);