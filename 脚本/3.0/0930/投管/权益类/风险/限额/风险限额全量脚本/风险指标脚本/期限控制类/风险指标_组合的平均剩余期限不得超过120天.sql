/**风险指标:组合的平均剩余期限不得超过120天**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='组合的平均剩余期限不得超过120天';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('8e7bbad65ae1492ebf0252ea420bda51','组合的平均剩余期限不得超过120天','LIMIT_TERM','${ZCSYQX}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">","value":"120","unit":"DAY","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:组合的平均剩余期限不得超过120天**/