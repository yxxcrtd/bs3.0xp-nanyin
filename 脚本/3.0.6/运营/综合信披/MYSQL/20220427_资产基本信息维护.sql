
-- 资产要素公共PPP、是否投向市场化债转股相关产品的部分、投向地方政府融资平台的部分校验
delete from chb_datacheck_rule_inv where CHECKRULEID ='ASSET_INFO_DETAIL_RULE_20210414-00000000000000000000000000000033';
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS) VALUES ('ASSET_INFO_DETAIL_RULE_20210414-00000000000000000000000000000033', 'AssetInfoRegist002', 'isNotEmpty', 'svrBussRepAssetCreditNonstdInfoDto.pppflag', '', '', 'N', '公共PPP不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');



delete from chb_datacheck_rule_inv where CHECKRULEID ='ASSET_INFO_DETAIL_RULE_20210414-00000000000000000000000000000035';
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS) VALUES ('ASSET_INFO_DETAIL_RULE_20210414-00000000000000000000000000000035', 'AssetInfoRegist002', 'isNotEmpty', 'svrBussRepAssetCreditNonstdInfoDto.bondtostockFlag', '', '', 'N', '是否投向市场化债转股相关产品的部分不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');


delete from chb_datacheck_rule_inv where CHECKRULEID ='ASSET_INFO_DETAIL_RULE_20210525-00000000000000000000000000000016';
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS) VALUES ('ASSET_INFO_DETAIL_RULE_20210525-00000000000000000000000000000016', 'AssetInfoRegist002', 'isNotEmpty', 'svrBussRepAssetCreditNonstdInfoDto.repPrivateprdFlag', '', '', 'N', '投向地方政府融资平台的部分不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');



-- 资产要素是否商业银行优先股校验
delete from chb_datacheck_rule_inv where CHECKRULEID ='ASSET_INFO_DETAIL_RULE_20210522-00000000000000000000000000000015';
INSERT INTO chb_datacheck_rule_inv (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS) VALUES ('ASSET_INFO_DETAIL_RULE_20210522-00000000000000000000000000000015', 'AssetInfoRegist002', 'isNotEmpty', 'svrBussRepEquityInfoDto.bankSecPrefer', '', '', 'N', '是否商业银行优先股要素不可为空。', 'E', 'com.joyintech.service.chb.common.service.ChbBaseCheckLib');









