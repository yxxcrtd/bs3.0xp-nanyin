/**风险指标:信用债债券偏离度**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='信用债债券偏离度';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('c381a66acdc9440ba7ff7d61b0106d41','信用债债券偏离度','LIMIT_DEVIATION','(${XYZDBJYJJ}-${DBJYSZJJ})/${DBJYSZJJ}','INTERNAL','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","value":"1.9","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">=","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">=","value":"2.0","unit":"PERCENT","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:信用债债券偏离度**/