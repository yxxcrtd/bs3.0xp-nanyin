/**风险指标:有固定期限银行存款的比例合计不得超过该产品资产净值的30%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='有固定期限银行存款的比例合计不得超过该产品资产净值的30%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('0eac6af1774241eb8941aa86edb31e87','有固定期限银行存款的比例合计不得超过该产品资产净值的30%','LIMIT_CONCENT','${GDQXCKGZ}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"30","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:有固定期限银行存款的比例合计不得超过该产品资产净值的30%**/