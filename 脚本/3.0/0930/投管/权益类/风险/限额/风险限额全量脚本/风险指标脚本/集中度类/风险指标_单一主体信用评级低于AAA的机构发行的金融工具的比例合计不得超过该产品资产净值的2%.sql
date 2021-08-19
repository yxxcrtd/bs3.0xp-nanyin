/**风险指标:单一主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的2%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='单一主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的2%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('5470027b3edb423c80ac8bb71e295be7','单一主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的2%','LIMIT_CONCENT','${DYZTPJDYAAAJGJRGJGZ}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"2","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:单一主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的2%**/