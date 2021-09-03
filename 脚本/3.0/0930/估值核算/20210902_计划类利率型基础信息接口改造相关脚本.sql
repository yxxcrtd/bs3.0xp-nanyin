--计划类利率型基础信息接口增加字段：发行人、融资人、担保人、是否包含有效抵质押物
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValMngAssetInfo' AND COL_E_NAME IN ('issuer','financier','sponsor','isEffPledge');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('计划类资产（利率型）基础信息','ValMngAssetInfo','发行人','issuer','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('计划类资产（利率型）基础信息','ValMngAssetInfo','融资人','financier','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('计划类资产（利率型）基础信息','ValMngAssetInfo','担保人','sponsor','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('计划类资产（利率型）基础信息','ValMngAssetInfo','是否包含有效抵质押物','isEffPledge','1','N','50','YES_OR_NO','1');
