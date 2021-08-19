/**风险指标:流动性资产比例**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='流动性资产比例';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('6e6c74193823448196164019993872fa','流动性资产比例','LIMIT_FLUID','(${XJGM}+${HBJJCCGM}+${DQRZSGJYRYNDZCJZ})/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","value":"20.0000","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:流动性资产比例**/