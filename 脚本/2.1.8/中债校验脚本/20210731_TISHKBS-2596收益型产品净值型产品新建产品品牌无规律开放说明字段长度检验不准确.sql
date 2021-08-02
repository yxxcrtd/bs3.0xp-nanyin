--产品品牌
delete from CHB_DATACHECK_RULE_INV where CHECKRULEID ='FINPRODUCTBUILD002-ed3E4B49-6813-5A9E-E050-007F01006A66';
insert into CHB_DATACHECK_RULE_INV values ('FINPRODUCTBUILD002-ed3E4B49-6813-5A9E-E050-007F01006A66','FinProductBuild002','lessThanCharLength','bussRepFinProductInfoDto.repFinprodBrand','','121','Y','产品品牌过长（最大长120）。','E','com.joyintech.tams.invest.common.ChbBaseCheckLib');

delete from CHB_DATACHECK_RULE_INV where CHECKRULEID ='ed3E4B49-6813-5A9E-E050-007F01006A66';
insert into CHB_DATACHECK_RULE_INV values ('ed3E4B49-6813-5A9E-E050-007F01006A66','FinNetValueProductBuild002','lessThanCharLength','bussRepFinProductInfoDto.repFinprodBrand','','121','Y','产品品牌过长（最大长120）。','E','com.joyintech.tams.invest.common.ChbBaseCheckLib');

--无规律开放说明(去掉UTF8)
update CHB_DATACHECK_RULE_INV set CHECKMETHOD = 'lessThanCharLength' where CHECKMETHOD = 'lessThanCharLengthUTF8' 
and status = 'E';