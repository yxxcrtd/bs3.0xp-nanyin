/***【中债直连】存款类标的，现金及银行存款类 ( 含大额存单 ) 备注check不合规**/
UPDATE CHB_DATACHECK_RULE_INV SET STATUS = 'E' WHERE CHECKRULEID IN ('7c35f75f-dd70-4994-bb5d-ec0db27b4cd7','7c35f75f-dd70-4994-bb5d-ec0db27b2cd7');


DELETE FROM CHB_DATACHECK_RULE_INV WHERE CHECKRULEID ='d4d88925-e5f2-4618-DDCS-a6535a33f221';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('d4d88925-e5f2-4618-DDCS-a6535a33f221', 'FinBondTargetAsset002', 'isNotEmpty', 'finprodName', null, NULL, 'Y', '资产名称不能为空。', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');
