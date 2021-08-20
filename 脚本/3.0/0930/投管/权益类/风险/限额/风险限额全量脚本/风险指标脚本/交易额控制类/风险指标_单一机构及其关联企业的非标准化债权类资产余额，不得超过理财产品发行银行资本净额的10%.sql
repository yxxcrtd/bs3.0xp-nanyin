/**风险指标:单一机构及其关联企业的非标准化债权类资产余额，不得超过理财产品发行银行资本净额的10%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='单一机构及其关联企业的非标准化债权类资产余额，不得超过理财产品发行银行资本净额的10%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('1d99d4c013eb47f88c469a216238e3de','单一机构及其关联企业的非标准化债权类资产余额，不得超过理财产品发行银行资本净额的10%','LIMIT_TRAN','${DYGLF}/${FV202001010004}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"10","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','');
 
/**风险指标:单一机构及其关联企业的非标准化债权类资产余额，不得超过理财产品发行银行资本净额的10%**/