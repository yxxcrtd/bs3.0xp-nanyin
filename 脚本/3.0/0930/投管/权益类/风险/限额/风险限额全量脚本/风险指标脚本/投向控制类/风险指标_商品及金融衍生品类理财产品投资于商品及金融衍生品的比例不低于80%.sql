/**风险指标:商品及金融衍生品类理财产品投资于商品及金融衍生品的比例不低于80%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='商品及金融衍生品类理财产品投资于商品及金融衍生品的比例不低于80%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('73cfb1263c5b4fbfa1003f045718b22f','商品及金融衍生品类理财产品投资于商品及金融衍生品的比例不低于80%','LIMIT_INVEST','${SPJJRYSPLZCGM}/${CPZZC}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<","value":"80.0000","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:商品及金融衍生品类理财产品投资于商品及金融衍生品的比例不低于80%**/