/**风险指标:混合类产品投资债权类资产比例**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='混合类产品投资债权类资产比例';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('7cc22755d9bf4cd98482b973d8eecb10','混合类产品投资债权类资产比例','LIMIT_INVEST','(${CPZCLZCGM}+${XJGM})/${CPZZC}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","value":"63","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","value":"80","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:混合类产品投资债权类资产比例**/