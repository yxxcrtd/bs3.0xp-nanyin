/**结算指令接口规则配置增加交收状态字段**/
delete from val_temp_col_check_info info where info.tmp_e_name='ValCleanOrder' and info.col_e_name='settleStatus';
insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('结算指令', 'ValCleanOrder', '交收状态', 'settleStatus', '1', 'N', 2, '1', 'IS_SUCCESS', 'system', null,sysdate, 'system', sysdate);
