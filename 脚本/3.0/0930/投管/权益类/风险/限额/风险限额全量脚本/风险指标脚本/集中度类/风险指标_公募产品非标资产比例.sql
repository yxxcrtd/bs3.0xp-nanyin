/**风险指标:公募产品非标资产比例**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='公募产品非标资产比例';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('7ba24d006e41495aaed758c302fb4971','公募产品非标资产比例','LIMIT_CONCENT','${FBZCJE}/${CPJZCJZ}','CONTRACT','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"27.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"30.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:公募产品非标资产比例**/