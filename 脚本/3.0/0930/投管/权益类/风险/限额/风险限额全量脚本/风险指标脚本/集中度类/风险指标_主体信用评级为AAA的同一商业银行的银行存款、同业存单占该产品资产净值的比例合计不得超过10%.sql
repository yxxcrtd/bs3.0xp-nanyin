/**风险指标:主体信用评级为AAA的同一商业银行的银行存款、同业存单占该产品资产净值的比例合计不得超过10%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='主体信用评级为AAA的同一商业银行的银行存款、同业存单占该产品资产净值的比例合计不得超过10%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('3452c8ccc01c40f7993573932279c219','主体信用评级为AAA的同一商业银行的银行存款、同业存单占该产品资产净值的比例合计不得超过10%','LIMIT_CONCENT','${ZTPJAAAYHCKGZ}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"10","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:主体信用评级为AAA的同一商业银行的银行存款、同业存单占该产品资产净值的比例合计不得超过10%**/