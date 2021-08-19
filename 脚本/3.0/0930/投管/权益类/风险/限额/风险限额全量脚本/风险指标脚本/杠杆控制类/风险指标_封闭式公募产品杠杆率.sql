/**风险指标:封闭式公募产品杠杆率**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='封闭式公募产品杠杆率';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('01549e636aaf4bf499ab27a9365d267e','封闭式公募产品杠杆率','LIMIT_LECER','${CPZZC}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"180.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"200.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:封闭式公募产品杠杆率**/