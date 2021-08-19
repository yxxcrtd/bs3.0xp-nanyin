/**风险指标:反向交易-证券反向交易控制（同一产品5个交易日）**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='反向交易-证券反向交易控制（同一产品5个交易日）';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('36e8503a65244e2e8003ea45f50c5526','反向交易-证券反向交易控制（同一产品5个交易日）','INNER_DIREATION','${TYCPFIVEJYRZQFXBUY}-${TYCPFIVEJYRZQFXSELL}','SUPERVISION','02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","value":"0","unit":"NONE","is_model":"02"}}','N','','01','实时计算','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:反向交易-证券反向交易控制（同一产品5个交易日）**/