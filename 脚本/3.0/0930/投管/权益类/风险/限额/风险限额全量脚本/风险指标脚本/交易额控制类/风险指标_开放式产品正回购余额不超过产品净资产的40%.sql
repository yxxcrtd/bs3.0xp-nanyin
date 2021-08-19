/**风险指标:开放式产品正回购余额不超过产品净资产的40%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='开放式产品正回购余额不超过产品净资产的40%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('890f1a1f384d4052a68133838c429de9','开放式产品正回购余额不超过产品净资产的40%','LIMIT_TRAN','${ZHGZJYE}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"40","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:开放式产品正回购余额不超过产品净资产的40%**/