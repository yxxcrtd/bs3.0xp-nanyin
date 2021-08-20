/**风险指标:摊余成本法估值偏离度超过5%的产品占存续产品总数不得超过5%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='摊余成本法估值偏离度超过5%的产品占存续产品总数不得超过5%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('60045134d9c04524a33de8936d23e901','摊余成本法估值偏离度超过5%的产品占存续产品总数不得超过5%','LIMIT_DEVIATION','${TYCBFGZPLDZCXQCPS}','SUPERVISION','03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"4.25","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"5","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:摊余成本法估值偏离度超过5%的产品占存续产品总数不得超过5%**/