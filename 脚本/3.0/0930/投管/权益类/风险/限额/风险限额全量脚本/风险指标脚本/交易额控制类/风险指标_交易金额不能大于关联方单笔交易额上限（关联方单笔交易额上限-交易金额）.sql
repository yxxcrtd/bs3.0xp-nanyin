/**风险指标:交易金额不能大于关联方单笔交易额上限（关联方单笔交易额上限-交易金额）**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='交易金额不能大于关联方单笔交易额上限（关联方单笔交易额上限-交易金额）';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('f46d0d71ace84a3dad61859c07e93a90','交易金额不能大于关联方单笔交易额上限（关联方单笔交易额上限-交易金额）','LIMIT_TRAN','${GLFDBJYJESX}-${GLFDBJYJE}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":"<","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":"<","value":"0","unit":"NONE","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:交易金额不能大于关联方单笔交易额上限（关联方单笔交易额上限-交易金额）**/