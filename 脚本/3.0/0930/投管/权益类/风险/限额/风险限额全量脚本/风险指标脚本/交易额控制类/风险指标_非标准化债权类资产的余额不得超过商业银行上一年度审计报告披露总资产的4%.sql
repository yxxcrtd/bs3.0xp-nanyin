/**风险指标:非标准化债权类资产的余额不得超过商业银行上一年度审计报告披露总资产的4%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='非标准化债权类资产的余额不得超过商业银行上一年度审计报告披露总资产的4%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('a81baff4701f4c668dc13d36826337bc','非标准化债权类资产的余额不得超过商业银行上一年度审计报告披露总资产的4%','LIMIT_TRAN','${FBZHZQLZCDYE}/${FV202003020061}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"4","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:非标准化债权类资产的余额不得超过商业银行上一年度审计报告披露总资产的4%**/