/**风险指标:现金、国债、中央银行票据、政策性金融债券不低于该产品资产净值的5%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='现金、国债、中央银行票据、政策性金融债券不低于该产品资产净值的5%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('72ed1f74da2c4e23bb7ad4a2bc0b04f3','现金、国债、中央银行票据、政策性金融债券不低于该产品资产净值的5%','LIMIT_FLUID','(${XJGM}+${GZZYYHPJZCXJRZQGZ})/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","value":"5","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:现金、国债、中央银行票据、政策性金融债券不低于该产品资产净值的5%**/