/**风险指标:公募产品单一投资品集中度**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='公募产品单一投资品集中度';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('e5eb232db36b491f90f864c5585980cc','公募产品单一投资品集中度','LIMIT_CONCENT','${GMZDDDZZQHJJ}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"10.0000","unit":"PERCENT","is_model":"02"}}','N','10','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:公募产品单一投资品集中度**/