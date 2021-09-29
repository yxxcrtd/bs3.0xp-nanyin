/**风险指标:产品持有资产组合的久期不得长于产品封闭期的1.5倍**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='产品持有资产组合的久期不得长于产品封闭期的1.5倍';
 INSERT INTO RSK_ISUP_INDICATOR (IND_ID, IND_NAME, IND_TYPE, EXPR, SUP_TYPE, TIME_POINT, SUP_FREQUENCY, SUP_DIRECTION, WARN_COND, APPROVE_COND, FOUL_COND, IS_ADJUST, ADJUST, IS_ENABLED, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_EXCP_WHEN_DIV_ZERO, COMPARE_DAYS, INSTITUTION_CODE, SUP_DISPLAY_CLASSIFY, ASSET_THR_TYPE, INVEST_PROPORTY, IS_DISPLAY_ORIGINAL) VALUES('02b90a7e5e5840519ea509668ee9b59c','产品持有资产组合的久期不得长于产品封闭期的1.5倍','LIMIT_TERM','${ZCZHJQ}/${CPFBQ}','SUPERVISION','03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","value":"1.5","unit":"NONE","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:产品持有资产组合的久期不得长于产品封闭期的1.5倍**/