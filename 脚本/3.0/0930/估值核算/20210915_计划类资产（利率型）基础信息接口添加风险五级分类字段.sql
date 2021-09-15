--计划类资产（利率型）基础信息加风险五级分类字段

insert into val_temp_col_check_info (TMP_C_NAME, TMP_E_NAME, COL_C_NAME, COL_E_NAME, COL_TYPE, IS_NULL, MAX_LENGTH, IS_EXIST, DICT_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('计划类资产（利率型）基础信息', 'ValMngAssetInfo', '风险五级分类', 'bondRiskType', '1', 'N', 36, '1', 'BOND_RISK_TYPE', 'system', null, null, 'system', null);