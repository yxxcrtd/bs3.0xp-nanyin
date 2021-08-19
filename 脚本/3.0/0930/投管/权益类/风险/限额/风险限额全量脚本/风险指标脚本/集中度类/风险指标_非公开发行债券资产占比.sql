/**风险指标:非公开发行债券资产占比**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='非公开发行债券资产占比';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('cacceddb3c8c4933b466161fde6ff6c2','非公开发行债券资产占比','LIMIT_CONCENT','${FGKFXZQZC}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","value":"25","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","value":"28","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","value":"30","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:非公开发行债券资产占比**/