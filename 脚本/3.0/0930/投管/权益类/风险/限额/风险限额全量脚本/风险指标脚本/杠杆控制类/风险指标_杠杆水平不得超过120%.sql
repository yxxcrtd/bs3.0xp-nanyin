/**风险指标:杠杆水平不得超过120%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='杠杆水平不得超过120%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('d7b0320a6e4e42d6808426c6d0257001','杠杆水平不得超过120%','LIMIT_LECER','${CPZZC}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"120","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:杠杆水平不得超过120%**/