/**风险指标:混合类产品投资于商品及金融衍生品类资产的比例需小于80%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='混合类产品投资于商品及金融衍生品类资产的比例需小于80%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('ce3ca67074ba44da8f6b910edd1ddf26','混合类产品投资于商品及金融衍生品类资产的比例需小于80%','LIMIT_INVEST','${SPJJRYSPLZCGM}/${CPZZC}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">=","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">=","value":"80.0000","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:混合类产品投资于商品及金融衍生品类资产的比例需小于80%**/