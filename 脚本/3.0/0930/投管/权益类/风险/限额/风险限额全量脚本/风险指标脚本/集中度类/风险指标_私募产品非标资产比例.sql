/**风险指标:私募产品非标资产比例**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='私募产品非标资产比例';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('0b04cc54c07846e7af6f2d01d3c0a93b','私募产品非标资产比例','LIMIT_CONCENT','${FBZCJE}/${CPJZCJZ}','CONTRACT','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","value":"63.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","value":"70.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:私募产品非标资产比例**/