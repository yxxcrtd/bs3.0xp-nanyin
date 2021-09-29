/**风险指标:非公开发行债券资产占比**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='非公开发行债券资产占比';
 INSERT INTO RSK_ISUP_INDICATOR (IND_ID, IND_NAME, IND_TYPE, EXPR, SUP_TYPE, TIME_POINT, SUP_FREQUENCY, SUP_DIRECTION, WARN_COND, APPROVE_COND, FOUL_COND, IS_ADJUST, ADJUST, IS_ENABLED, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_EXCP_WHEN_DIV_ZERO, COMPARE_DAYS, INSTITUTION_CODE, SUP_DISPLAY_CLASSIFY, ASSET_THR_TYPE, INVEST_PROPORTY, IS_DISPLAY_ORIGINAL) VALUES('cacceddb3c8c4933b466161fde6ff6c2','非公开发行债券资产占比','LIMIT_CONCENT','${FGKFXZQZC}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","value":"25","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","value":"28","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","value":"30","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:非公开发行债券资产占比**/