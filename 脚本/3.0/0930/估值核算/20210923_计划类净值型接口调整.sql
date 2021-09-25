--导入模板新增profitFlag
DELETE FROM val_temp_col_check_info WHERE TMP_E_NAME = 'ValMngAssetInfoN' AND COL_E_NAME = 'profitFlag';
insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('计划类资产（净值型）基础信息', 'ValMngAssetInfoN', '收益标识', 'profitFlag', '1', 'N', 50, '-1', 'PRD_INCOME_TYPE', 'system', null, '07-6月 -21 02.34.06.000000 下午', 'system', '07-6月 -21 02.34.06.000000 下午');