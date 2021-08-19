/**风险指标:公募产品单一证券或证券投资基金投资限额**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='公募产品单一证券或证券投资基金投资限额';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('13eb055fe09e4caab1cfb0a4ee3e04b9','公募产品单一证券或证券投资基金投资限额','LIMIT_TRAN','${GMZDDDZZQHJJ}/${GZQSZHZZQTZJJSZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"27.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"30.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:公募产品单一证券或证券投资基金投资限额**/