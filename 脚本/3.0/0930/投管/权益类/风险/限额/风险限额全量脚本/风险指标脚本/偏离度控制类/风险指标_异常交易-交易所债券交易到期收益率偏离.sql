/**风险指标:异常交易-交易所债券交易到期收益率偏离**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='异常交易-交易所债券交易到期收益率偏离';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('a94cc9cb98af4a788a2533796ac69110','异常交易-交易所债券交易到期收益率偏离','LIMIT_DEVIATION','${JYSZQJYDQSYL}-${JYSZQYIRZZGJSYL}','SUPERVISION','02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"50","unit":"BP","is_model":"01"}}','{"threshold_1":{"op":">","value":"100","unit":"BP","is_model":"01"}}','{"threshold_1":{"op":">","unit":"BP","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:异常交易-交易所债券交易到期收益率偏离**/