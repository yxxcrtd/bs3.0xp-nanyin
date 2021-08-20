/**风险指标:所有主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的10%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='所有主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的10%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('514ef2fa80c14302892e394454c37aad','所有主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的10%','LIMIT_CONCENT','${ZTPJDYAAAJGJRGJGZ}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"10","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:所有主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的10%**/