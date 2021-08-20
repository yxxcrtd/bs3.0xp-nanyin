/**风险指标:杠杆水平巨额赎回除外**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='杠杆水平巨额赎回除外';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('5e69f2051f974c20a5380ed2546acdef','杠杆水平巨额赎回除外','LIMIT_LECER','(${CPZZC}-${SGJYRLJSHZ}-${WGJYRLJSHZ})/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"120.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','N','','01','每只现金管理类产品的杠杆水平不得超过120% 发生巨额赎回、连续3个交易日累计赎回20%以上或者连续5个交易日累计赎回30%以上的情形除外','system',null,sysdate,'system',sysdate,'02','','','','','','');
 
/**风险指标:杠杆水平巨额赎回除外**/