/**风险指标:混合类产品投资于债权类资产比例需小于80%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='混合类产品投资于债权类资产比例需小于80%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('b656eaa137e84d77ba7c76e87cbd95fa','混合类产品投资于债权类资产比例需小于80%','LIMIT_INVEST','${CPZCLZCGM}/${CPZZC}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","value":"80.0000","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:混合类产品投资于债权类资产比例需小于80%**/