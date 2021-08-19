/**风险指标:所有关联方占用授信不得超过66亿元**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='所有关联方占用授信不得超过66亿元';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('a914b9014ac54fbc9edac690b02548de','所有关联方占用授信不得超过66亿元','LIMIT_TRAN','${ALLGLFZYSXED}','INTERNAL','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","value":"660000","unit":"WRMB","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:所有关联方占用授信不得超过66亿元**/