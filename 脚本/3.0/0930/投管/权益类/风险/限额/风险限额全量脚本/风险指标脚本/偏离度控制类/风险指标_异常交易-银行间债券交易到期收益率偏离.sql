/**风险指标:异常交易-银行间债券交易到期收益率偏离**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='异常交易-银行间债券交易到期收益率偏离';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('6f904cbb2a1e450d86049fd5337b899b','异常交易-银行间债券交易到期收益率偏离','LIMIT_DEVIATION','${YHJZQJYDQSYL}-${YHJZQQYRJYDQSYL}','SUPERVISION','02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"50","unit":"BP","is_model":"01"}}','{"threshold_1":{"op":">","value":"100","unit":"BP","is_model":"01"}}','{"threshold_1":{"op":">","unit":"BP","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:异常交易-银行间债券交易到期收益率偏离**/