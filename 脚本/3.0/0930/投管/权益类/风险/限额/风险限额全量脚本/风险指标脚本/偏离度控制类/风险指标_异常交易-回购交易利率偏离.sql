/**风险指标:异常交易-回购交易利率偏离**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='异常交易-回购交易利率偏离';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('e071b11d2c0844d8b52e4e661248c74f','异常交易-回购交易利率偏离','LIMIT_DEVIATION','${HGJYLL}-${QXPZQYRJQSYL}','SUPERVISION','02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"80","unit":"BP","is_model":"01"}}','{"threshold_1":{"op":">","value":"100","unit":"BP","is_model":"01"}}','{"threshold_1":{"op":">","unit":"BP","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:异常交易-回购交易利率偏离**/