/**风险指标:同一商业银行的存款、同业存单和债券，不得超过该商业银行最近一个季度末净资产的10%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='同一商业银行的存款、同业存单和债券，不得超过该商业银行最近一个季度末净资产的10%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('3839548908044ad18ecfb7a2e97343c0','同一商业银行的存款、同业存单和债券，不得超过该商业银行最近一个季度末净资产的10%','LIMIT_CONCENT','${SYYHCKZQGZ}/${SYYHZJYGJDMJZC}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"8.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"9.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"10","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:同一商业银行的存款、同业存单和债券，不得超过该商业银行最近一个季度末净资产的10%**/