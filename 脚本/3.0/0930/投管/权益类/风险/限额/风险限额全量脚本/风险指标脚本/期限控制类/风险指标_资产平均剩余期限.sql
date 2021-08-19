/**风险指标:资产平均剩余期限**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='资产平均剩余期限';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('d5eed5a7a7ab416eb473428e1482f3cb','资产平均剩余期限','LIMIT_TERM','${ZCSYQX}/${CPJZCJZ}','INTERNAL','03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","value":"328.0000","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">=","value":"365.0000","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">=","unit":"DAY","is_model":"02"}}','N','','01','、、、、(、)、'+、、/、、','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:资产平均剩余期限**/