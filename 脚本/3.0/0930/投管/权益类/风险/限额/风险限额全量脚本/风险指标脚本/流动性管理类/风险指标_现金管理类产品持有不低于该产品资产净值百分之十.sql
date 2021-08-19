/**风险指标:现金管理类产品持有不低于该产品资产净值百分之十**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='现金管理类产品持有不低于该产品资产净值百分之十';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('0b60ec0ffb1e470baf462ef766218dfc','现金管理类产品持有不低于该产品资产净值百分之十','LIMIT_FLUID','(${XJGM}+${XJGZZYYHPJZCXJRZQGM}+${WGJYRDQJDQJTJRGJGZ})/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<=","value":"11.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<=","value":"10.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<=","unit":"PERCENT","is_model":"02"}}','N','','01','每只现金管理类产品持有不低于该产品资产净值10%的现金、国债、中央银行票据、政策性金融债券以及五个交易日内到期的其他金融工具','system',null,sysdate,'system',sysdate,'01','','','','','','');
 
/**风险指标:现金管理类产品持有不低于该产品资产净值百分之十**/