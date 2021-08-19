/**风险指标:现金管理类产品不得投资于股票、可转换债券、可交换债券、浮动利率债券、信用等级在AA+以下的债券和资产支持证券**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='现金管理类产品不得投资于股票、可转换债券、可交换债券、浮动利率债券、信用等级在AA+以下的债券和资产支持证券';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('086902891df347da91422da8b18b79e2','现金管理类产品不得投资于股票、可转换债券、可交换债券、浮动利率债券、信用等级在AA+以下的债券和资产支持证券','FORBID_FUND','${BKTZZQ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','','','{"threshold_1":{"value":"Y,Y,Y,Y,Y,","value_cn":"股票,可转换债券,可交换债券,浮动利率债券,信用等级在AA+以下的债券和资产支持证券,","fb_model":"Y"}}','N','2','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:现金管理类产品不得投资于股票、可转换债券、可交换债券、浮动利率债券、信用等级在AA+以下的债券和资产支持证券**/