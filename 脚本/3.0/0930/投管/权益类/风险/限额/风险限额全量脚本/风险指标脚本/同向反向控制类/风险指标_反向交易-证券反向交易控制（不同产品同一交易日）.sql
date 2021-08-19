/**风险指标:反向交易-证券反向交易控制（不同产品同一交易日）**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='反向交易-证券反向交易控制（不同产品同一交易日）';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('16ed8e3666f343c684763f97a41e6d12','反向交易-证券反向交易控制（不同产品同一交易日）','INNER_DIREATION','${BTCPONEZQJYKZBUY}-${BTCPONEZQJYKZSELL}','SUPERVISION','02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","value":"0","unit":"NONE","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:反向交易-证券反向交易控制（不同产品同一交易日）**/