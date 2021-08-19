/**风险指标:商品及金融衍生品类产品投资商品及金融衍生品资产比例**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='商品及金融衍生品类产品投资商品及金融衍生品资产比例';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('238014b36f284312b8b0a20c316ac900','商品及金融衍生品类产品投资商品及金融衍生品资产比例','LIMIT_INVEST','${SPJJRYSPLZCGM}/${CPZZC}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<","value":"90.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","value":"80.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:商品及金融衍生品类产品投资商品及金融衍生品资产比例**/