/**风险指标:银行存款、债券回购、中央银行票据、同业存单的期限在1年以内（含1年）**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='银行存款、债券回购、中央银行票据、同业存单的期限在1年以内（含1年）';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('ee448bcdbb5d42ceb42b2b67c3d700bf','银行存款、债券回购、中央银行票据、同业存单的期限在1年以内（含1年）','LIMIT_TERM','${CKHGYPTYSYQX}/${FV202008200001}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"YEAR","is_model":"02"}}','{"threshold_1":{"op":">","unit":"YEAR","is_model":"02"}}','{"threshold_1":{"op":">","value":"1","unit":"YEAR","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:银行存款、债券回购、中央银行票据、同业存单的期限在1年以内（含1年）**/