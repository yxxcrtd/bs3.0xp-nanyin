/**风险指标:异常交易-股票交易价格偏离**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='异常交易-股票交易价格偏离';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('917f5ed66ba441e9b4d297ad653ce88f','异常交易-股票交易价格偏离','LIMIT_DEVIATION','(${GPJYJG}-${GPQYRSPJ})/${GPQYRSPJ}','SUPERVISION','02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"1.7","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","value":"2","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:异常交易-股票交易价格偏离**/