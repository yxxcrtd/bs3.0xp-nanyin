
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where SRCPROPERTY='bussRepAssetMngProdInfo.repMaxRate';
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where SRCPROPERTY='bussRepAssetMngProdInfo.repMinRate';
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where SRCPROPERTY='bussRepAssetMngProdInfo.repTruFeeRate';
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where SRCPROPERTY='bussRepAssetMngProdInfo.repOtherFeeRate';
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where SRCPROPERTY='bussRepAssetMngProdInfo.repTradeFeeRate';
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where SRCPROPERTY='bussRepAssetMngProdInfo.repMgrFeeRate';
update CHB_DATACHECK_RULE_INV set STATUS=replace(STATUS,'D','E') where SRCPROPERTY='bussRepAssetMngProdInfo.repAgencyFeeRate';

update CHB_DATACHECK_CONDITION_INV set STATUS=replace(STATUS,'D','E') where CHECKRULEID='884807E5-DF8F-D920-E150-007F010077FD';
update CHB_DATACHECK_CONDITION_INV set STATUS=replace(STATUS,'D','E') where CHECKRULEID='884807E5-DF8C-D920-E150-007F010077FD';

update CHB_DATACHECK_CONDITION_INV set STATUS=replace(STATUS,'D','E') where CHECKRULEID='884807E5-DF8A-D920-E150-007F010077FD';