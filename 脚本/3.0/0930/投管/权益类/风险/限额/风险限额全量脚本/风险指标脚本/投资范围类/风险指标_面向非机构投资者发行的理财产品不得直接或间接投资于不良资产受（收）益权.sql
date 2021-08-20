/**风险指标:面向非机构投资者发行的理财产品不得直接或间接投资于不良资产受（收）益权**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='面向非机构投资者发行的理财产品不得直接或间接投资于不良资产受（收）益权';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('6cbf8636542f47d7986b3533e6108c5d','面向非机构投资者发行的理财产品不得直接或间接投资于不良资产受（收）益权','FORBID_FUND','${BDZJHJITZYBLZCSYQ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','','','{"threshold_1":{"value":"Y,","value_cn":"不良资产,","fb_model":"Y"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:面向非机构投资者发行的理财产品不得直接或间接投资于不良资产受（收）益权**/