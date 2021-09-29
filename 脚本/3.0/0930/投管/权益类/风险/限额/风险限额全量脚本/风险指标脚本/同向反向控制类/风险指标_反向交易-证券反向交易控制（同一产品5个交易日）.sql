/**风险指标:反向交易-证券反向交易控制（同一产品5个交易日）**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='反向交易-证券反向交易控制（同一产品5个交易日）';
 INSERT INTO RSK_ISUP_INDICATOR (IND_ID, IND_NAME, IND_TYPE, EXPR, SUP_TYPE, TIME_POINT, SUP_FREQUENCY, SUP_DIRECTION, WARN_COND, APPROVE_COND, FOUL_COND, IS_ADJUST, ADJUST, IS_ENABLED, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_EXCP_WHEN_DIV_ZERO, COMPARE_DAYS, INSTITUTION_CODE, SUP_DISPLAY_CLASSIFY, ASSET_THR_TYPE, INVEST_PROPORTY, IS_DISPLAY_ORIGINAL) VALUES('36e8503a65244e2e8003ea45f50c5526','反向交易-证券反向交易控制（同一产品5个交易日）','INNER_DIREATION','${TYCPFIVEJYRZQFXBUY}-${TYCPFIVEJYRZQFXSELL}','SUPERVISION','02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","value":"0","unit":"NONE","is_model":"02"}}','N','','01','实时计算','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:反向交易-证券反向交易控制（同一产品5个交易日）**/