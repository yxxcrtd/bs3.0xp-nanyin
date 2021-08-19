/**风险指标:混合类产品投资权益类资产比例**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='混合类产品投资权益类资产比例';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('e2b1d851658e4d208bc72e7bd64157a1','混合类产品投资权益类资产比例','LIMIT_INVEST','${QYLZCGM}/${CPZZC}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","value":"63","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","value":"80.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:混合类产品投资权益类资产比例**/