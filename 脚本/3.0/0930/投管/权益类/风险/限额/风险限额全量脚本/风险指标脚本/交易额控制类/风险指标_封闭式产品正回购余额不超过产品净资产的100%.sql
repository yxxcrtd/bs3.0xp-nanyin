/**风险指标:封闭式产品正回购余额不超过产品净资产的100%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='封闭式产品正回购余额不超过产品净资产的100%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('6642d9695ee746c09ad0f7fa5b2519ee','封闭式产品正回购余额不超过产品净资产的100%','LIMIT_TRAN','${ZHGZJYE}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"100","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:封闭式产品正回购余额不超过产品净资产的100%**/