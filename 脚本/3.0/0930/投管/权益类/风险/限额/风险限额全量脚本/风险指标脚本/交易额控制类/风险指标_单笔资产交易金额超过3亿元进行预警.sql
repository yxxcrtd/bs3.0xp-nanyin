/**风险指标:单笔资产交易金额超过3亿元进行预警**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='单笔资产交易金额超过3亿元进行预警';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('b311e1023c734402a43f3023467a5379','单笔资产交易金额超过3亿元进行预警','LIMIT_TRAN','${FV202001010001}','INTERNAL','01,02','','','{"threshold_1":{"op":">=","value":"30000","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">=","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">=","unit":"WRMB","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:单笔资产交易金额超过3亿元进行预警**/