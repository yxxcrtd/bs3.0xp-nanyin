/**风险指标:每只现金管理类产品投资组合的平均剩余存续期限不得超过240天**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='每只现金管理类产品投资组合的平均剩余存续期限不得超过240天';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('706ba2d5f19f44a381d9845c17ca4799','每只现金管理类产品投资组合的平均剩余存续期限不得超过240天','LIMIT_TERM','${ZCSYCXQX}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">","value":"240","unit":"DAY","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:每只现金管理类产品投资组合的平均剩余存续期限不得超过240天**/