/****ZGBSSE-554 【中债直连】存款类标的，现金及银行存款类  (  含大额存单 ) 备注check不合规**/
update CHB_DATACHECK_RULE_INV set CHECKMETHOD = 'lessThanLength' where checkruleid = '7c35f75f-dd70-4994-bb5d-ec0db27b2cd7';


update CHB_DATACHECK_RULE_INV set CHECKMETHOD = 'lessThanLength',REMARK = '资金运用方式不得超过300字节' where SRCPROPERTY = 'bussRepAssetMngProdInfo.repCapUseWay' AND CHECKMETHOD = 'lessThanCharLengthUTF8';
