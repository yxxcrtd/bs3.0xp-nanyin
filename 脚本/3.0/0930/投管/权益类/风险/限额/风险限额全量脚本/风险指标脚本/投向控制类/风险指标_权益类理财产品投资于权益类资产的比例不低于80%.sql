/**风险指标:权益类理财产品投资于权益类资产的比例不低于80%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='权益类理财产品投资于权益类资产的比例不低于80%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('9526b4cd489a45b6918702ae3b388fe5','权益类理财产品投资于权益类资产的比例不低于80%','LIMIT_INVEST','${QYLZCGM}/${CPZZC}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","value":"80.0000","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:权益类理财产品投资于权益类资产的比例不低于80%**/