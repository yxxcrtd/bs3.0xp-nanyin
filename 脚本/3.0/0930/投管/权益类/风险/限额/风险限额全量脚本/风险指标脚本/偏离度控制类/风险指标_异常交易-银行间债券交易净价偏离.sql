/**风险指标:异常交易-银行间债券交易净价偏离**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='异常交易-银行间债券交易净价偏离';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('634e122aff5349a9942b3fab2346255a','异常交易-银行间债券交易净价偏离','LIMIT_DEVIATION','(${YHJZQJYJJ}-${YHJZQQYRZZJJ})/${YHJZQQYRZZJJ}','SUPERVISION','02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"1.7","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","value":"2","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:异常交易-银行间债券交易净价偏离**/