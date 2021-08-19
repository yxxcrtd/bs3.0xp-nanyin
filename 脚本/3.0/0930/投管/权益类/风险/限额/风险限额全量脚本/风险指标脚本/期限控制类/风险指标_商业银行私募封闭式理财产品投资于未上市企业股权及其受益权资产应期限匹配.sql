/**风险指标:商业银行私募封闭式理财产品投资于未上市企业股权及其受益权资产应期限匹配**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='商业银行私募封闭式理财产品投资于未上市企业股权及其受益权资产应期限匹配';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('47ba7d490d11417e8e8d0df4bec936c7','商业银行私募封闭式理财产品投资于未上市企业股权及其受益权资产应期限匹配','LIMIT_TERM','${FBSLCCPDQRHKFSLCCP}-${WSSQYGQSYZCDQR}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":"<","value":"0","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":"<","unit":"DAY","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:商业银行私募封闭式理财产品投资于未上市企业股权及其受益权资产应期限匹配**/