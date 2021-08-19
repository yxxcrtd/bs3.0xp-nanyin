/**风险指标:混合类产品投资商品及金融衍生品类资产比例**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='混合类产品投资商品及金融衍生品类资产比例';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('c050098ae0eb44249bc4e267bcc7c864','混合类产品投资商品及金融衍生品类资产比例','LIMIT_INVEST','${SPJJRYSPLZCGM}/${CPZZC}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","value":"63.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","value":"80.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","value":"81.0000","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:混合类产品投资商品及金融衍生品类资产比例**/