/**风险指标:某只计划类资产在所有组合的持仓**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='某只计划类资产在所有组合的持仓';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('ad488c062d494522ae0f34460e04b5cc','某只计划类资产在所有组合的持仓','LIMIT_AMOUNT','${DZJHLZCCCJE}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","value":"20000","unit":"WRMB","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:某只计划类资产在所有组合的持仓**/