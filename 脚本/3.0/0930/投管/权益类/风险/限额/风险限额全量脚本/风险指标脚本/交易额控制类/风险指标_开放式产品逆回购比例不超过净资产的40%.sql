/**风险指标:开放式产品逆回购比例不超过净资产的40%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='开放式产品逆回购比例不超过净资产的40%';
 INSERT INTO RSK_ISUP_INDICATOR (IND_ID, IND_NAME, IND_TYPE, EXPR, SUP_TYPE, TIME_POINT, SUP_FREQUENCY, SUP_DIRECTION, WARN_COND, APPROVE_COND, FOUL_COND, IS_ADJUST, ADJUST, IS_ENABLED, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_EXCP_WHEN_DIV_ZERO, COMPARE_DAYS, INSTITUTION_CODE, SUP_DISPLAY_CLASSIFY, ASSET_THR_TYPE, INVEST_PROPORTY, IS_DISPLAY_ORIGINAL) VALUES('e1df2697bde541c887e277c60553f655','开放式产品逆回购比例不超过净资产的40%','LIMIT_TRAN','${NHGZJYE}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"40.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:开放式产品逆回购比例不超过净资产的40%**/