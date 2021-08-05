delete from CHB_DATACHECK_RULE_INV where CHECKRULEID='LZ4827E5-DF35-D920-E050-234F01001091';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('LZ4827E5-DF35-D920-E050-234F01001091', 'FinProjectTargetAsset002', 'isNotEmpty', 'bussRepAssetCreditNonstdInfoDto.repRemark', null, '0', 'N', '备注要素不可为空', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');

delete from chb_datacheck_condition_inv where CHECKRULEID='LZ4827E5-DF35-D920-E050-234F01001091';
insert into chb_datacheck_condition_inv (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('LZ4827E5-DF35-D920-E050-234F01001981', 'LZ4827E5-DF35-D920-E050-234F01001091', 'compareValue', 'bussRepAssetCreditNonstdInfoDto.repTradeMarket', '==', '99', '当交易流通场所为“99 其他交易流通场所”时', 'E');
