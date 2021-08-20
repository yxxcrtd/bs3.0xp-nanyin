/**风险指标:全部开放式公募产品投资单一上市公司发行的股票比例**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='全部开放式公募产品投资单一上市公司发行的股票比例';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('2cfe824b6561493fa6784a71005f4f3e','全部开放式公募产品投资单一上市公司发行的股票比例','LIMIT_CONCENT','${TZDYSSGSFXGP}/${SSGSKLTGB}','INTERNAL','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"10","unit":"BP","is_model":"02"}}','{"threshold_1":{"op":">","value":"15","unit":"BP","is_model":"02"}}','{"threshold_1":{"op":">","unit":"BP","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:全部开放式公募产品投资单一上市公司发行的股票比例**/