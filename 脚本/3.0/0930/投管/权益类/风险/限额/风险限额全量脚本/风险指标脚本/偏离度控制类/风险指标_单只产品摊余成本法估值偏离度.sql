/**风险指标:单只产品摊余成本法估值偏离度**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='单只产品摊余成本法估值偏离度';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('8a5b29ba9eaf452693745a859fdefbcc','单只产品摊余成本法估值偏离度','LIMIT_DEVIATION','${DZCPTYCBFGZPLD}','SUPERVISION','03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"4.25","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","value":"5","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:单只产品摊余成本法估值偏离度**/