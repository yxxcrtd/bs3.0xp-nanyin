/**风险指标:某只银登中心资产在所有组合的持仓**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='某只银登中心资产在所有组合的持仓';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('caa48e8f84c04d98a6df5e5b9c488480','某只银登中心资产在所有组合的持仓','LIMIT_AMOUNT','${DZYDZXQMZE}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","value":"20000","unit":"WRMB","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:某只银登中心资产在所有组合的持仓**/