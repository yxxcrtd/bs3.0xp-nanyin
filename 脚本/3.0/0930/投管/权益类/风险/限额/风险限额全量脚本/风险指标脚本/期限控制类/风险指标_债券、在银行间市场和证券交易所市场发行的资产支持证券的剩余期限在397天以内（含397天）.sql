/**风险指标:债券、在银行间市场和证券交易所市场发行的资产支持证券的剩余期限在397天以内（含397天）**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='债券、在银行间市场和证券交易所市场发行的资产支持证券的剩余期限在397天以内（含397天）';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('b2d9d26bd51f4cbf9fe947329be76bca','债券、在银行间市场和证券交易所市场发行的资产支持证券的剩余期限在397天以内（含397天）','LIMIT_TERM','${ZQZCZCZQSYQX}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">","value":"397","unit":"DAY","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:债券、在银行间市场和证券交易所市场发行的资产支持证券的剩余期限在397天以内（含397天）**/