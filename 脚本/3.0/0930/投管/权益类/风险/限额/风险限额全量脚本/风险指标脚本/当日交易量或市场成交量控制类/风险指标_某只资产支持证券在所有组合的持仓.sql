/**风险指标:某只资产支持证券在所有组合的持仓**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='某只资产支持证券在所有组合的持仓';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('88853715b6694a1d8096f7bf3f4db08a','某只资产支持证券在所有组合的持仓','LIMIT_AMOUNT','${DZZCZCZQCCQMZE}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","value":"20000","unit":"WRMB","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:某只资产支持证券在所有组合的持仓**/