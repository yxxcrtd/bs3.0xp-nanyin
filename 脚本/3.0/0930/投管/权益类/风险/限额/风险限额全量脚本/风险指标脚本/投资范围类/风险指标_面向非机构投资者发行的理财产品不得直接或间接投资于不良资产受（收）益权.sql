/**风险指标:面向非机构投资者发行的理财产品不得直接或间接投资于不良资产受（收）益权**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='面向非机构投资者发行的理财产品不得直接或间接投资于不良资产受（收）益权';
 INSERT INTO RSK_ISUP_INDICATOR (IND_ID, IND_NAME, IND_TYPE, EXPR, SUP_TYPE, TIME_POINT, SUP_FREQUENCY, SUP_DIRECTION, WARN_COND, APPROVE_COND, FOUL_COND, IS_ADJUST, ADJUST, IS_ENABLED, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_EXCP_WHEN_DIV_ZERO, COMPARE_DAYS, INSTITUTION_CODE, SUP_DISPLAY_CLASSIFY, ASSET_THR_TYPE, INVEST_PROPORTY, IS_DISPLAY_ORIGINAL) VALUES('6cbf8636542f47d7986b3533e6108c5d','面向非机构投资者发行的理财产品不得直接或间接投资于不良资产受（收）益权','FORBID_FUND','${BDZJHJITZYBLZCSYQ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','','','{"threshold_1":{"value":"Y,","value_cn":"不良资产,","fb_model":"Y"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:面向非机构投资者发行的理财产品不得直接或间接投资于不良资产受（收）益权**/