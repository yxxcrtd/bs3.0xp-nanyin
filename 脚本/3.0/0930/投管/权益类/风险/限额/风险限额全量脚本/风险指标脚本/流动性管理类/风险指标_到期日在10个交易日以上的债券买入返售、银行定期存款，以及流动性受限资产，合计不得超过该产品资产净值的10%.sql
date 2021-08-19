/**风险指标:到期日在10个交易日以上的债券买入返售、银行定期存款，以及流动性受限资产，合计不得超过该产品资产净值的10%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='到期日在10个交易日以上的债券买入返售、银行定期存款，以及流动性受限资产，合计不得超过该产品资产净值的10%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('d21a46b9c286468b92d589a457e5cf43','到期日在10个交易日以上的债券买入返售、银行定期存款，以及流动性受限资产，合计不得超过该产品资产净值的10%','LIMIT_FLUID','${LDXSXZCGZ}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"10","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'02','','','','','','');
 
/**风险指标:到期日在10个交易日以上的债券买入返售、银行定期存款，以及流动性受限资产，合计不得超过该产品资产净值的10%**/