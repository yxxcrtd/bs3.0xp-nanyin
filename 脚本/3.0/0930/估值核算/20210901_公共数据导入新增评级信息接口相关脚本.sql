--评级信息接口相关脚本

DELETE FROM bok_batch_task_grp where TASK_GRP_ID = 'ValMstBondCreditRating';
insert into bok_batch_task_grp (TASK_GRP_ID, TASK_GRP_NAME, TASK_GRP_TYPE, P_TASK_GRP_ID, TASK_ID, ORDER_NO, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME, CREATE_DEPT)
values ('ValMstBondCreditRating', '评级信息', 'InterfData', '1111', 'ValMstBondCreditRating', 100085, 'admin', sysdate, 'admin', sysdate, null);


DELETE FROM val_interf_define where INTERF_ID = '100088';
insert into val_interf_define (INTERF_ID, INTERF_C_NAME, INTERF_E_NAME, ORDERNO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('100088', '评级信息', 'ValMstBondCreditRating', 1111, 'system', null, sysdate, 'system', sysdate);


DELETE FROM VAL_INTERF_TEMPLATE where TMP_E_NAME = 'ValMstBondCreditRating';
insert into VAL_INTERF_TEMPLATE (TMP_C_NAME, TMP_E_NAME, DTO_NAME, INTERF_ID, TMP_TYPE, SYSTEMID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', 'ValMstBondCreditRating', '100088', '01', 'ZG', 'system', null, sysdate, 'system', sysdate);


DELETE FROM val_temp_col_check_info where TMP_E_NAME = 'ValMstBondCreditRating';
insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', '债券代码', 'oCode', '1', 'Y', 32, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', '评级机构', 'gradeOrgId', '1', 'Y', 50, '1', 'GRADE_PARTY', null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', '评级日期', 'gradeDate', '2', 'Y', 10, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', '评级类型', 'oType', '1', 'Y', 50, '1', 'MAIN_GRADE_TYPE', null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', '长短期', 'shortLongTerm', '1', 'N', 50, '1', 'LONG_SHORT_TERM', null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', '评级结果', 'gradeResult', '1', 'N', 50, '1', 'MAIN_GRADE_RESULT', null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', '是否推荐', 'isRecommand', '1', 'N', 50, '1', 'YES_OR_NO', null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', '评级发起类型', 'issuerRateType', '1', 'N', 50, '1', 'ISSUER_RATE_TYPE', null, null, null, null, null);


insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', '公告日期', 'noticeDate', '2', 'N', 10, '1', null, null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', '评级展望', 'gradeExpectation', '1', 'N', 50, '1', 'RATING_OUTLOOK', null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', '变动方向', 'directionChange', '1', 'N', 50, '1', 'CHANGE_DIRECTION', null, null, null, null, null);

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('评级信息', 'ValMstBondCreditRating', '数据动作标志', 'dataActionSign', '1', 'N', 50, '1', 'ACTION_STATUS', null, null, null, null, null);
