/**风险指标:每只现金管理类产品投资组合的平均剩余存续期限不得超过240天**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='每只现金管理类产品投资组合的平均剩余存续期限不得超过240天';
 INSERT INTO RSK_ISUP_INDICATOR (IND_ID, IND_NAME, IND_TYPE, EXPR, SUP_TYPE, TIME_POINT, SUP_FREQUENCY, SUP_DIRECTION, WARN_COND, APPROVE_COND, FOUL_COND, IS_ADJUST, ADJUST, IS_ENABLED, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_EXCP_WHEN_DIV_ZERO, COMPARE_DAYS, INSTITUTION_CODE, SUP_DISPLAY_CLASSIFY, ASSET_THR_TYPE, INVEST_PROPORTY, IS_DISPLAY_ORIGINAL) VALUES('706ba2d5f19f44a381d9845c17ca4799','每只现金管理类产品投资组合的平均剩余存续期限不得超过240天','LIMIT_TERM','${ZCSYCXQX}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">","value":"240","unit":"DAY","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:每只现金管理类产品投资组合的平均剩余存续期限不得超过240天**/