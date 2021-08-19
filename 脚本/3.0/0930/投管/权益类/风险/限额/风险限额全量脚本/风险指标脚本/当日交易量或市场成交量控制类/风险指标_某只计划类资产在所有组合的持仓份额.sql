/**风险指标:某只计划类资产在所有组合的持仓份额**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='某只计划类资产在所有组合的持仓份额';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('9dc52b6a3f814a9f9e8c0e4e3979e9d5','某只计划类资产在所有组合的持仓份额','LIMIT_AMOUNT','${DZJHLZCCCFE}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"150000000.0000","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","value":"190000000.0000","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","value":"200000000","unit":"NONE","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:某只计划类资产在所有组合的持仓份额**/