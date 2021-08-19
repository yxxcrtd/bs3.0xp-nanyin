/**风险指标:反向交易-回购反向交易控制（同一产品）**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='反向交易-回购反向交易控制（同一产品）';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('9f83b6ccd67e4306a4a761ca475ea316','反向交易-回购反向交易控制（同一产品）','INNER_DIREATION','${TYCPHGFXJYFRONT}*${TYCPHGFXJYREVERSE}','SUPERVISION','02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":"<","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":"<","value":"0","unit":"NONE","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:反向交易-回购反向交易控制（同一产品）**/