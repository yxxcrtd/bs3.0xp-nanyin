/**风险指标:非开放式产品高流动性资产比例**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='非开放式产品高流动性资产比例';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('2a00c4add368454ab50f9937620e0df7','非开放式产品高流动性资产比例','LIMIT_TRAN','(${XJGM}+${FKFSCPGLDXZC})/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<=","value":"40","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<=","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<=","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:非开放式产品高流动性资产比例**/