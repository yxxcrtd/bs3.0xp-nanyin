/**风险指标:单笔交易价格偏离2%预警**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='单笔交易价格偏离2%预警';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('01879f76f6414de4aefbb9adac49101c','单笔交易价格偏离2%预警','LIMIT_EXECU','(${DBJYJJZQ}-${DBJYSZJJ})/${DBJYSZJJ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"2","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:单笔交易价格偏离2%预警**/