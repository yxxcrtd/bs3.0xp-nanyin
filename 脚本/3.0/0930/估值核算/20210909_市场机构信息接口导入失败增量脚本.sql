--市场机构接口校验，去除不必要的字段校验
UPDATE VAL_TEMP_COL_CHECK_INFO T 
SET T.IS_EXIST = '-1'
WHERE T.TMP_E_NAME='ValAgencyInfo'
AND T.COL_E_NAME IN (
'parentCustomerName',
'city',
'mainCapital',
'county',
'partyType3',
'industry',
'isGroupCustomer',
'partyType1',
'isPrivate',
'isListedCompany',
'creditType',
'province',
'structAdjustType',
'holdingType',
'customerObjType',
'indusUpgrateType',
'busiScope',
'industry2',
'isEscapeDept',
'registerCapital',
'insitituteCode',
'mainBusi',
'isGov',
'isCreditRelatedTrd'
);