--删除所属机构属性的必填校验
update VAL_TEMP_COL_CHECK_INFO set is_exist = '-1' where tmp_e_name = 'ValMngAssetInfo' and col_e_name = 'belongInstitutionCode';
