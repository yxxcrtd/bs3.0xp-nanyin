/**风险指标:每日开放式产品组合久期**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='每日开放式产品组合久期';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('4cb6f05ad44e42c38ae3f159fe57ef0a','每日开放式产品组合久期','LIMIT_TERM','${AZZCJZJQDZHJQ}/${CPJZCJZ}','INTERNAL','03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"2.7000","unit":"YEAR","is_model":"02"}}','{"threshold_1":{"op":">","value":"3.0000","unit":"YEAR","is_model":"02"}}','{"threshold_1":{"op":">","unit":"YEAR","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:每日开放式产品组合久期**/