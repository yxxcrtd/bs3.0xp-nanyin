/**风险指标:现金、国债、中央银行票据、政策性金融债券以及五个交易日内到期的其他金融工具不低于该产品资产净值的10%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='现金、国债、中央银行票据、政策性金融债券以及五个交易日内到期的其他金融工具不低于该产品资产净值的10%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('80434e46d8bd490cb75b2418bfe53b02','现金、国债、中央银行票据、政策性金融债券以及五个交易日内到期的其他金融工具不低于该产品资产净值的10%','LIMIT_FLUID','(${XJGM}+${GZZYYHPJZCXJRZQGZ}+${DQR5RNQTJRGJGZ})/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","value":"10","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:现金、国债、中央银行票据、政策性金融债券以及五个交易日内到期的其他金融工具不低于该产品资产净值的10%**/