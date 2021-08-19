/**风险指标:某只理财直融工具在所有组合的持仓**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='某只理财直融工具在所有组合的持仓';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('12011ec2cd0d43978ebcb5b65aa5253d','某只理财直融工具在所有组合的持仓','LIMIT_AMOUNT','${DZZQB18QMZE}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","value":"20000","unit":"WRMB","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:某只理财直融工具在所有组合的持仓**/