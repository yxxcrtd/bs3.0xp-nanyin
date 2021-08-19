/**风险指标:非标准化债权类资产比例**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='非标准化债权类资产比例';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('6b198a25db8e467f8faa168d5ca05390','非标准化债权类资产比例','LIMIT_TRAN','${FBZHZQLZCDYE}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"31.5000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"35.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:非标准化债权类资产比例**/