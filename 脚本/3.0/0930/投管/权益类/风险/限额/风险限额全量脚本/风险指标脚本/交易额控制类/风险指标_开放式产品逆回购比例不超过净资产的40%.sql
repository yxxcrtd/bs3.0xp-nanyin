/**风险指标:开放式产品逆回购比例不超过净资产的40%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='开放式产品逆回购比例不超过净资产的40%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('e1df2697bde541c887e277c60553f655','开放式产品逆回购比例不超过净资产的40%','LIMIT_TRAN','${NHGZJYE}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"40.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:开放式产品逆回购比例不超过净资产的40%**/