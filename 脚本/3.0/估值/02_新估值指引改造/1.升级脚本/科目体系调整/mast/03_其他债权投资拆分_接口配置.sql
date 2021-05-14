--接口-计划类资产（利率型）基础信息：新增字段【是否其他债权类资产】
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValMngAssetInfo' and COL_E_NAME='isQtzq';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('计划类资产（利率型）基础信息','ValMngAssetInfo','是否其他债权类资产','isQtzq','1','N','50','YES_OR_NO','1');
