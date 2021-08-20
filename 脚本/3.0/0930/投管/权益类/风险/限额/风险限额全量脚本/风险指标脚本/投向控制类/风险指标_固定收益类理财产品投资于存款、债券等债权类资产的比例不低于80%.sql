/**风险指标:固定收益类理财产品投资于存款、债券等债权类资产的比例不低于80%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='固定收益类理财产品投资于存款、债券等债权类资产的比例不低于80%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('b3bff15b22234571a2535bd8a99800a5','固定收益类理财产品投资于存款、债券等债权类资产的比例不低于80%','LIMIT_INVEST','(${CPZCLZCGM}+${XJGM})/${CPZZC}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","value":"80.0000","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:固定收益类理财产品投资于存款、债券等债权类资产的比例不低于80%**/