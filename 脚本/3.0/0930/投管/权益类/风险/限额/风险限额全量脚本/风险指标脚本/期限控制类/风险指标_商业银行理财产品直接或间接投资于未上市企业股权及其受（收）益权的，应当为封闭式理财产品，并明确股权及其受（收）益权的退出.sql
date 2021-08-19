/**风险指标:商业银行理财产品直接或间接投资于未上市企业股权及其受（收）益权的，应当为封闭式理财产品，并明确股权及其受（收）益权的退出**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='商业银行理财产品直接或间接投资于未上市企业股权及其受（收）益权的，应当为封闭式理财产品，并明确股权及其受（收）益权的退出';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('79c83fad8d8e423b97c60de1156babc3','商业银行理财产品直接或间接投资于未上市企业股权及其受（收）益权的，应当为封闭式理财产品，并明确股权及其受（收）益权的退出','LIMIT_TERM','${WSSQYGPJQSSYQTCR}-${FBSCPDQR}','SUPERVISION','01,02,03','DAYEND','','{"threshold_1":{"op":">=","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">=","unit":"DAY","is_model":"02"}}','{"threshold_1":{"op":">=","value":"1","unit":"DAY","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'02','','','','','','');
 
/**风险指标:商业银行理财产品直接或间接投资于未上市企业股权及其受（收）益权的，应当为封闭式理财产品，并明确股权及其受（收）益权的退出**/