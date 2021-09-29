/**风险指标:某只资产支持证券在所有组合的持仓**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='某只资产支持证券在所有组合的持仓';
 INSERT INTO RSK_ISUP_INDICATOR (IND_ID, IND_NAME, IND_TYPE, EXPR, SUP_TYPE, TIME_POINT, SUP_FREQUENCY, SUP_DIRECTION, WARN_COND, APPROVE_COND, FOUL_COND, IS_ADJUST, ADJUST, IS_ENABLED, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_EXCP_WHEN_DIV_ZERO, COMPARE_DAYS, INSTITUTION_CODE, SUP_DISPLAY_CLASSIFY, ASSET_THR_TYPE, INVEST_PROPORTY, IS_DISPLAY_ORIGINAL) VALUES('88853715b6694a1d8096f7bf3f4db08a','某只资产支持证券在所有组合的持仓','LIMIT_AMOUNT','${DZZCZCZQCCQMZE}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","value":"20000","unit":"WRMB","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:某只资产支持证券在所有组合的持仓**/