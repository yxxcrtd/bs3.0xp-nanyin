/**风险指标:公募产品单一证券或证券投资基金投资限额**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='公募产品单一证券或证券投资基金投资限额';
 INSERT INTO RSK_ISUP_INDICATOR (IND_ID, IND_NAME, IND_TYPE, EXPR, SUP_TYPE, TIME_POINT, SUP_FREQUENCY, SUP_DIRECTION, WARN_COND, APPROVE_COND, FOUL_COND, IS_ADJUST, ADJUST, IS_ENABLED, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_EXCP_WHEN_DIV_ZERO, COMPARE_DAYS, INSTITUTION_CODE, SUP_DISPLAY_CLASSIFY, ASSET_THR_TYPE, INVEST_PROPORTY, IS_DISPLAY_ORIGINAL) VALUES('13eb055fe09e4caab1cfb0a4ee3e04b9','公募产品单一证券或证券投资基金投资限额','LIMIT_TRAN','${GMZDDDZZQHJJ}/${GZQSZHZZQTZJJSZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"27.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"30.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:公募产品单一证券或证券投资基金投资限额**/