/**风险指标:单一关联方单笔交易额度**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='单一关联方单笔交易额度';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('301d51a89e4f4cc6a4d86133fa338885','单一关联方单笔交易额度','LIMIT_TRAN','${DYGNFDBJYJE}','SUPERVISION','01,02','','','{"threshold_1":{"op":">=","value":"25000","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">=","value":"29000","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">=","value":"30000","unit":"WRMB","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:单一关联方单笔交易额度**/