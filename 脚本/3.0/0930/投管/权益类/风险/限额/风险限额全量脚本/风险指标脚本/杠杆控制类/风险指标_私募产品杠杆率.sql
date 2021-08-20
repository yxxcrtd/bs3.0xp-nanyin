/**风险指标:私募产品杠杆率**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='私募产品杠杆率';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('a4ea247283e44b1ca8f6d7344b8d6d93','私募产品杠杆率','LIMIT_LECER','${CPZZC}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"180","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"200","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:私募产品杠杆率**/