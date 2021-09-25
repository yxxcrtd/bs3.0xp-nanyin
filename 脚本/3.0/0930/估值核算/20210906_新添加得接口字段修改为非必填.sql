
UPDATE VAL_TEMP_COL_CHECK_INFO SET IS_NULL = 'N' WHERE tmp_e_name = 'ValSecBaseInfo' and COL_E_NAME IN ('bondRiskType','resalePrice','redeemPrice','conPrice','conFinprodId');
UPDATE VAL_TEMP_COL_CHECK_INFO SET IS_EXIST = '-1' WHERE tmp_e_name = 'ValSecBaseInfo' and COL_E_NAME IN ('bondRiskType','resalePrice','redeemPrice','conPrice','conFinprodId');



UPDATE VAL_TEMP_COL_CHECK_INFO SET IS_NULL = 'N' WHERE tmp_e_name = 'ValBotAstInfo' AND COL_E_NAME IN ('bondRiskType');
UPDATE VAL_TEMP_COL_CHECK_INFO SET IS_EXIST = '-1' WHERE tmp_e_name = 'ValBotAstInfo' AND COL_E_NAME IN ('bondRiskType');


UPDATE VAL_TEMP_COL_CHECK_INFO SET IS_NULL = 'N' WHERE  tmp_e_name = 'ValMngAssetInfo' AND COL_E_NAME IN ('sponsor','financier','issuer','isEffPledge');
UPDATE VAL_TEMP_COL_CHECK_INFO SET IS_EXIST = '-1' WHERE  tmp_e_name = 'ValMngAssetInfo' AND COL_E_NAME IN ('sponsor','financier','issuer','isEffPledge');


UPDATE VAL_TEMP_COL_CHECK_INFO SET IS_NULL = 'N' WHERE tmp_e_name = 'ValRepDealSecSubject' AND COL_E_NAME IN ('counterId');
UPDATE VAL_TEMP_COL_CHECK_INFO SET IS_EXIST = '-1' WHERE tmp_e_name = 'ValRepDealSecSubject' AND COL_E_NAME IN ('counterId');


UPDATE VAL_TEMP_COL_CHECK_INFO SET IS_NULL = 'N' WHERE  tmp_e_name = 'ValAffirmPurTrade' AND COL_E_NAME IN ('fundType');
UPDATE VAL_TEMP_COL_CHECK_INFO SET IS_EXIST = '-1' WHERE  tmp_e_name = 'ValAffirmPurTrade' AND COL_E_NAME IN ('fundType');

