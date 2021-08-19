/**风险指标:现金管理类产品应当投资于剩余期限在397天以内（含397天）的债券、在银行间市场和证券交易所市场发行的资产支持证券**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='现金管理类产品应当投资于剩余期限在397天以内（含397天）的债券、在银行间市场和证券交易所市场发行的资产支持证券';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('a283e890612d4bb1bbf4e0a11481c83d','现金管理类产品应当投资于剩余期限在397天以内（含397天）的债券、在银行间市场和证券交易所市场发行的资产支持证券','LIMIT_TERM','${XJGLLCPYDTZYSYQJXZSJQTYNZXQ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">","value":"397","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">","unit":"DAY","is_model":"02"}}','N','','01','现金管理类产品应当投资于剩余期限在397天以内（含397天）的债券、在银行间市场和证券交易所市场发行的资产支持证券','system',null,sysdate,'system',sysdate,'02','','','','','','');
 
/**风险指标:现金管理类产品应当投资于剩余期限在397天以内（含397天）的债券、在银行间市场和证券交易所市场发行的资产支持证券**/