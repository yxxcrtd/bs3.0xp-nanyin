/**风险指标:利率债债券偏离度**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='利率债债券偏离度';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('cb98dcf2eb724ccbac839e56c6788287','利率债债券偏离度','LIMIT_DEVIATION','(${LLZDBJYJJ}-${DBJYSZJJ})/${DBJYSZJJ}','INTERNAL','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","value":"0.9","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">=","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">=","value":"1","unit":"PERCENT","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:利率债债券偏离度**/