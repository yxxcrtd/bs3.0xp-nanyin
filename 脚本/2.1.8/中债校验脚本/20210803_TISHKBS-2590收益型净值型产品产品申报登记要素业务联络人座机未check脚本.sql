--产品新建页面业务联络人座机
delete from CHB_DATACHECK_RULE_INV where CHECKRULEID ='FINPRODUCTBUILD002-de294A49-6813-5A9E-F060-007F01006B25';
insert into CHB_DATACHECK_RULE_INV values ('FINPRODUCTBUILD002-de294A49-6813-5A9E-F060-007F01006B25','FinProductBuild002','isCustomizedString','bussRepFinProductInfoDto.repLiaisonTel','','^(0[0-9]{2,3}\-)([2-9][0-9]{6,7})+(\-[0-9]{1,8})?$','Y','业务联络人座机不合法,合法的模式：XXXX(3或4位)-XXXXXXXX（7或8位）-XXXXX（小于等于8位）分机号码可以不填','E','com.joyintech.tams.invest.common.ChbBaseCheckLib');

delete from CHB_DATACHECK_RULE_INV where CHECKRULEID ='de294A49-6813-5A9E-F060-007F01006B25';
insert into CHB_DATACHECK_RULE_INV values ('de294A49-6813-5A9E-F060-007F01006B25','FinNetValueProductBuild002','isCustomizedString','bussRepFinProductInfoDto.repLiaisonTel','','^(0[0-9]{2,3}\-)([2-9][0-9]{6,7})+(\-[0-9]{1,8})?$','Y','业务联络人座机不合法,合法的模式：XXXX(3或4位)-XXXXXXXX（7或8位）-XXXXX（小于等于8位）分机号码可以不填','E','com.joyintech.tams.invest.common.ChbBaseCheckLib');

--产品申报登记业务联络人座机
update CHB_DATACHECK_RULE_INV set status ='D' where CHECKRULEID='de104A49-6813-5A9E-E050-007F01006A66';
update CHB_DATACHECK_RULE_INV set objvalue ='^(0[0-9]{2,3}\-)([2-9][0-9]{6,7})+(\-[0-9]{1,8})?$',status ='E' where CHECKRULEID='de294A49-6813-5A9E-E050-007F01006B25';