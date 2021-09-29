/**风险指标:信用债债券偏离度**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='信用债债券偏离度';
 INSERT INTO RSK_ISUP_INDICATOR (IND_ID, IND_NAME, IND_TYPE, EXPR, SUP_TYPE, TIME_POINT, SUP_FREQUENCY, SUP_DIRECTION, WARN_COND, APPROVE_COND, FOUL_COND, IS_ADJUST, ADJUST, IS_ENABLED, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_EXCP_WHEN_DIV_ZERO, COMPARE_DAYS, INSTITUTION_CODE, SUP_DISPLAY_CLASSIFY, ASSET_THR_TYPE, INVEST_PROPORTY, IS_DISPLAY_ORIGINAL) VALUES('c381a66acdc9440ba7ff7d61b0106d41','信用债债券偏离度','LIMIT_DEVIATION','(${XYZDBJYJJ}-${DBJYSZJJ})/${DBJYSZJJ}','INTERNAL','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","value":"1.9","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">=","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">=","value":"2.0","unit":"PERCENT","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:信用债债券偏离度**/