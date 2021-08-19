/**风险指标:单产品债券加权平均久期不超过5年**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='单产品债券加权平均久期不超过5年';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('10ecfe3a88d4441f975463052031fe4a','单产品债券加权平均久期不超过5年','LIMIT_TERM','${DCPZQJQPJJQ}','INTERNAL','03','DAYEND','','{"threshold_1":{"op":">","value":"5","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","unit":"NONE","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:单产品债券加权平均久期不超过5年**/