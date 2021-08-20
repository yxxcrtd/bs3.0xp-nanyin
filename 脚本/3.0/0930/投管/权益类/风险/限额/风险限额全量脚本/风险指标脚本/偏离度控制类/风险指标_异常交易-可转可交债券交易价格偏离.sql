/**风险指标:异常交易-可转可交债券交易价格偏离**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='异常交易-可转可交债券交易价格偏离';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('8550f75423064e48890bb7cf97550df2','异常交易-可转可交债券交易价格偏离','LIMIT_DEVIATION','(${KZKJZQJYQJ}-${KZKJZQQYRZZQJ})/${KZKJZQQYRZZQJ}','SUPERVISION','02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"1.7","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","value":"2","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:异常交易-可转可交债券交易价格偏离**/