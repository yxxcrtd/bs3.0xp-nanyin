-- 插入Tm_App_Audit_Type_Confit
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'TRD50';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('TRD50', '计划类货币型存续期', '投资交易', 'trd', 'TRD50', null, null, 9, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

  
-- 插入Tm_App_Audit_Tab_Confit
delete from Tm_App_Audit_Tab_Confit where BUSINESS_TYPE = 'TRD50' and TAB_ID = 'tab1';
insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('39', 'TRD50', '计划类货币型存续期', 'tab1', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 
-- 插入Tm_App_Audit_Tab_Detail
delete from Tm_App_Audit_Tab_Detail where TAB_CONFIG_ID = '39';

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('900', '39', 'finprodMarketId', '资产代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 900);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('901', '39', 'finprodMarketName', '资产名称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 901);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('902', '39', 'bonusMode', '分红类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 902);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('903', '39', 'registDate', '权益登记日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 903);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('904', '39', 'exRightsDate', '除权日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 904);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('905', '39', 'arriveDate', '分红到账日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 905);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('906', '39', 'reInvesDate', '红利再投日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 906);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('907', '39', 'tradeAmt', '分红金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 907);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('908', '39', 'qty', '分红份额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 908);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('909', '39', 'chlId', '通道', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 909);
 

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('910', '39', 'finAssetIntInfoList', '[{"key":"portfolioId","title":"组合代码"},{"key":"portfolioName","title":"组合名称"},{"key":"registShare","title":"权益登记日份额"},{"key":"unitCprice","title":"单位分红净值"},{"key":"tradeAmt","title":"分红金额"},{"key":"qty","title":"分红份额"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 910);

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('911', '39', 'bussTrdTransferOrder', '[{"key":"isNeedSend","title":"是否发送指令"},{"key":"payTypeText","title":"划款类型"},{"key":"settleDateText","title":"支付日期"},{"key":"settleAmt","title":"金额"},{"key":"ccy","title":"币种"},{"key":"payAcctNo","title":"付款账户"},{"key":"payAcctName","title":"付款账户名称"},{"key":"recAcctNo","title":"收款账户"},{"key":"recAcctName","title":"收款账户名称"},{"key":"recAcctPoint","title":"收款账户网点"},{"key":"purpose","title":"用途"},{"key":"remark","title":"备注"},{"key":"portfolioId","title":"组合代码"},{"key":"portfolioName","title":"组合名称"},{"key":"inOutBank","title":"划款性质"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 909);
 
 

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('913', '39', 'remark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 913);
 


 
commit;

 
