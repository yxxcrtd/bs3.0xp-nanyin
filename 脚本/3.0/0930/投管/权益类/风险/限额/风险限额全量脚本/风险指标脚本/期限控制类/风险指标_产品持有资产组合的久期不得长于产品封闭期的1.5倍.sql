/**风险指标:产品持有资产组合的久期不得长于产品封闭期的1.5倍**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='产品持有资产组合的久期不得长于产品封闭期的1.5倍';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('02b90a7e5e5840519ea509668ee9b59c','产品持有资产组合的久期不得长于产品封闭期的1.5倍','LIMIT_TERM','${ZCZHJQ}/${CPFBQ}','SUPERVISION','03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":">","value":"1.5","unit":"NONE","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:产品持有资产组合的久期不得长于产品封闭期的1.5倍**/