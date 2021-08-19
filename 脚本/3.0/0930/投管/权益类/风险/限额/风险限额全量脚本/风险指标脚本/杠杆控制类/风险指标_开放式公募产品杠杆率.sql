/**风险指标:开放式公募产品杠杆率**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='开放式公募产品杠杆率';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('61982d27700f41908f99adb3e7f38dba','开放式公募产品杠杆率','LIMIT_LECER','${CPZZC}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"126.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"140","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:开放式公募产品杠杆率**/