/**风险指标:理财产品不得直接或间接投资于公司主要股东发行的次级资产支持证券、主要股东的信贷资产及其受（收）益权**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='理财产品不得直接或间接投资于公司主要股东发行的次级资产支持证券、主要股东的信贷资产及其受（收）益权';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('0a60b2ac32af4acaba10fbb6c3a777e7','理财产品不得直接或间接投资于公司主要股东发行的次级资产支持证券、主要股东的信贷资产及其受（收）益权','FORBID_FUND','${BDTZCJZDZXDJQSYQ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','','','{"threshold_1":{"value":"Y,Y,","value_cn":"次级资产支持证券,信贷资产及其收受益权,","fb_model":"Y"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:理财产品不得直接或间接投资于公司主要股东发行的次级资产支持证券、主要股东的信贷资产及其受（收）益权**/