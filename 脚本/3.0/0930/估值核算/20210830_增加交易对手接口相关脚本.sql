--交易对手接口相关脚本

DELETE FROM bok_batch_task_grp where TASK_GRP_ID = 'ValMstCounterParty';
insert into bok_batch_task_grp (TASK_GRP_ID, TASK_GRP_NAME, TASK_GRP_TYPE, P_TASK_GRP_ID, TASK_ID, ORDER_NO, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME, CREATE_DEPT)
values ('ValMstCounterParty', '交易对手', 'InterfData', '1111', 'ValMstCounterParty', 100084, 'admin', '17-9月 -18 12.00.00.000000 上午', 'admin', '17-9月 -18 12.00.00.000000 上午', null);


DELETE FROM val_interf_define where INTERF_ID = '100087';
insert into val_interf_define (INTERF_ID, INTERF_C_NAME, INTERF_E_NAME, ORDERNO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('100087', '交易对手', 'ValMstCounterParty', 1111, 'system', null, '26-8月 -21 01.59.49.000000 下午', 'system', '26-8月 -21 01.59.49.000000 下午');


DELETE FROM VAL_INTERF_TEMPLATE where TMP_E_NAME = 'ValMstCounterParty';
insert into VAL_INTERF_TEMPLATE (TMP_C_NAME, TMP_E_NAME, DTO_NAME, INTERF_ID, TMP_TYPE, SYSTEMID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', 'MstCounterParty', '100087', '01', 'ZG', 'system', null, '26-8月 -21 02.35.23.000000 下午', 'system', '26-8月 -21 02.35.23.000000 下午');


DELETE FROM val_temp_col_check_info where TMP_E_NAME = 'ValMstCounterParty';
insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '联系人', 'contact', '1', 'N', 200, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '联系方式', 'contactWay', '1', 'N', 1000, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '交易对手代码', 'counterId', '1', 'Y', 32, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '交易对手名称', 'counterName', '1', 'N', 200, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '交易对手类型', 'counterType', '1', 'N', 50, '1', 'COUNTER_TYPE', null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '数据动作标志', 'dataActionSign', '1', 'N', 50, '1', 'ACTION_STATUS', null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '所属总行', 'headBank', '1', 'N', 32, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '关联内部机构', 'linkDeptCode', '1', 'N', 32, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '关联代码', 'linkId', '1', 'N', 32, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '管理人', 'managerId', '1', 'N', 32, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '人行一级分类', 'pTypeOne', '1', 'N', 50, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '人行二级分类', 'pTypeTwo', '1', 'N', 60, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('交易对手', 'ValMstCounterParty', '备注', 'remark', '1', 'N', 1000, '1', null, null, null, null, null, null);
