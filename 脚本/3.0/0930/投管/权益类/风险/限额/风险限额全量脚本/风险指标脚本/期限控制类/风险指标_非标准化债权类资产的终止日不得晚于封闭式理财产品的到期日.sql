/**风险指标:非标准化债权类资产的终止日不得晚于封闭式理财产品的到期日**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='非标准化债权类资产的终止日不得晚于封闭式理财产品的到期日';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('2dfa44c6e5314515a92d83fcfe986229','非标准化债权类资产的终止日不得晚于封闭式理财产品的到期日','LIMIT_TERM','${FBZHZQLZCZZR}-${FBSLCCPDQRHKFSLCCP}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">=","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">=","value":"1","unit":"DAY","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:非标准化债权类资产的终止日不得晚于封闭式理财产品的到期日**/