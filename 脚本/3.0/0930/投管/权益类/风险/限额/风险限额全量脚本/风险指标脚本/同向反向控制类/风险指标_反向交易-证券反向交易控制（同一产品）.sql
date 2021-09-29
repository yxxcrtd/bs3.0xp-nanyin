/**风险指标:反向交易-证券反向交易控制（同一产品）**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='反向交易-证券反向交易控制（同一产品）';
 INSERT INTO RSK_ISUP_INDICATOR (IND_ID, IND_NAME, IND_TYPE, EXPR, SUP_TYPE, TIME_POINT, SUP_FREQUENCY, SUP_DIRECTION, WARN_COND, APPROVE_COND, FOUL_COND, IS_ADJUST, ADJUST, IS_ENABLED, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_EXCP_WHEN_DIV_ZERO, COMPARE_DAYS, INSTITUTION_CODE, SUP_DISPLAY_CLASSIFY, ASSET_THR_TYPE, INVEST_PROPORTY, IS_DISPLAY_ORIGINAL) VALUES('b9bb30322337488f871228bf601a25ec','反向交易-证券反向交易控制（同一产品）','INNER_DIREATION','${TYCPZQFXJYBUY}*${TYCPZQFXJYSELL}','SUPERVISION','02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":"<","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":"<","value":"0","unit":"NONE","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:反向交易-证券反向交易控制（同一产品）**/