/**风险指标:全部产品投资单一上市公司发行的股票比例**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='全部产品投资单一上市公司发行的股票比例';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('693e65b630034d059021cf8d5c402a5d','全部产品投资单一上市公司发行的股票比例','LIMIT_CONCENT','${TZDYSSGSFXGP}/${SSGSKLTGB}','INTERNAL','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"25","unit":"BP","is_model":"02"}}','{"threshold_1":{"op":">","value":"30","unit":"BP","is_model":"02"}}','{"threshold_1":{"op":">","unit":"BP","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:全部产品投资单一上市公司发行的股票比例**/