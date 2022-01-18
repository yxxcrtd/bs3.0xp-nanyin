--现金管理类产品应当投资于期限在1年以内（含1年）的银行存款、债券回购、中央银行票据、同业存单
--风险指标_银行存款、债券回购、中央银行票据、同业存单的期限在1年以内（含1年）.sql
/**风险指标:银行存款、债券回购、中央银行票据、同业存单的期限在1年以内（含1年）**/
--HFZG-311【指标管理】指标名称与需求不一致
 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='银行存款、债券回购、中央银行票据、同业存单的期限在1年以内（含1年）';
 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='现金管理类产品应当投资于期限在1年以内（含1年）的银行存款、债券回购、中央银行票据、同业存单';
 INSERT INTO RSK_ISUP_INDICATOR (IND_ID, IND_NAME, IND_TYPE, EXPR, SUP_TYPE, TIME_POINT, SUP_FREQUENCY, SUP_DIRECTION, WARN_COND, APPROVE_COND, FOUL_COND, IS_ADJUST, ADJUST, IS_ENABLED, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_EXCP_WHEN_DIV_ZERO, COMPARE_DAYS, INSTITUTION_CODE, SUP_DISPLAY_CLASSIFY, ASSET_THR_TYPE, INVEST_PROPORTY, IS_DISPLAY_ORIGINAL, SUP_DOC) VALUES ('ee448bcdbb5d42ceb42b2b67c3d700bf', '现金管理类产品应当投资于期限在1年以内（含1年）的银行存款、债券回购、中央银行票据、同业存单', 'LIMIT_TERM', '${CKHGYPTYSYQX}/${FV202008200001}', 'SUPERVISION', '01,02,03', 'DAYTIME,DAYEND', null, '{"threshold_1":{"op":">","unit":"YEAR","is_model":"02"}}', '{"threshold_1":{"op":">","unit":"YEAR","is_model":"02"}}', '{"threshold_1":{"op":">","value":"1","unit":"YEAR","is_model":"02"}}', 'N', null, '01', null, 'system', null, TIMESTAMP '2021-10-26 20:53:48', 'system', TIMESTAMP '2021-10-26 20:53:48', '03', null, null, null, null, null, 'Y', null);--HFZG-311【指标管理】指标名称与需求不一致
/**风险指标:银行存款、债券回购、中央银行票据、同业存单的期限在1年以内（含1年）**/

--每只现金管理类产品持有不低于该产品资产净值5%的现金、国债、中央银行票据、政策性金融债券
--风险指标_现金、国债、中央银行票据、政策性金融债券不低于该产品资产净值的5%.sql
/**风险指标:现金、国债、中央银行票据、政策性金融债券不低于该产品资产净值的5%**/
--HFZG-311【指标管理】指标名称与需求不一致
 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='现金、国债、中央银行票据、政策性金融债券不低于该产品资产净值的5%';
 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='每只现金管理类产品持有不低于该产品资产净值5%的现金、国债、中央银行票据、政策性金融债券';
--  INSERT INTO RSK_ISUP_INDICATOR VALUES('72ed1f74da2c4e23bb7ad4a2bc0b04f3','每只现金管理类产品持有不低于该产品资产净值5%的现金、国债、中央银行票据、政策性金融债券','LIMIT_FLUID','(${XJGM}+${GZZYYHPJZCXJRZQGZ})/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","value":"5","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 INSERT INTO RSK_ISUP_INDICATOR (IND_ID, IND_NAME, IND_TYPE, EXPR, SUP_TYPE, TIME_POINT, SUP_FREQUENCY, SUP_DIRECTION, WARN_COND, APPROVE_COND, FOUL_COND, IS_ADJUST, ADJUST, IS_ENABLED, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_EXCP_WHEN_DIV_ZERO, COMPARE_DAYS, INSTITUTION_CODE, SUP_DISPLAY_CLASSIFY, ASSET_THR_TYPE, INVEST_PROPORTY, IS_DISPLAY_ORIGINAL, SUP_DOC) VALUES ('72ed1f74da2c4e23bb7ad4a2bc0b04f3', '每只现金管理类产品持有不低于该产品资产净值5%的现金、国债、中央银行票据、政策性金融债券', 'LIMIT_FLUID', '(${XJGM}+${GZZYYHPJZCXJRZQGZ})/${CPJZCJZ}', 'SUPERVISION', '01,02,03', 'DAYTIME,DAYEND', null, '{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}', '{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}', '{"threshold_1":{"op":"<","value":"5","unit":"PERCENT","is_model":"02"}}', 'N', null, '01', null, 'system', null, TIMESTAMP '2021-10-26 20:53:39', 'system', TIMESTAMP '2021-10-26 20:53:39', '03', null, null, null, null, null, 'Y', null);
