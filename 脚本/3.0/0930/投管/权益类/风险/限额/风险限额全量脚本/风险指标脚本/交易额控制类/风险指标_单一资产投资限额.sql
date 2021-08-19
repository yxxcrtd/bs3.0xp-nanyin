/**风险指标:单一资产投资限额**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='单一资产投资限额';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('76fdc6fc9e1947fd82189f2eb7a5f31d','单一资产投资限额','LIMIT_TRAN','${DYZCGM}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","value":"2700000.0000","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">=","value":"3000000.0000","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">=","unit":"WRMB","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:单一资产投资限额**/