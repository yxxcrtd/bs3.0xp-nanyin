/**风险指标:非私募封闭式产品不得投资未上市企业股权**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='非私募封闭式产品不得投资未上市企业股权';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('16edbd55cd2544dea3bc4d67dc7ea729','非私募封闭式产品不得投资未上市企业股权','FORBID_FUND','${FSMCPBDTZWSSQYGQ}','SUPERVISION','01,02','DAYEND','','','','{"threshold_1":{"value":"101,102,202,","value_cn":"公募封闭式,公募开放式,私募开放式,","fb_model":"Y"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:非私募封闭式产品不得投资未上市企业股权**/