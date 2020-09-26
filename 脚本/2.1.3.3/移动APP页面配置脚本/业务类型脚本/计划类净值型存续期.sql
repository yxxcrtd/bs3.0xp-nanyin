-- 插入Tm_App_Audit_Type_Confit
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'TRD13';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('TRD13', '计划类净值型存续期', '投资交易', 'trd', 'TRD13', null, null, 11, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 

-- 插入Tm_App_Audit_Tab_Confit
delete from Tm_App_Audit_Tab_Confit where BUSINESS_TYPE = 'TRD13' and TAB_ID = 'tab1';
insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('37', 'TRD13', '计划类净值型存续期', 'tab1', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);
 
-- 插入Tm_App_Audit_Tab_Detail
delete from Tm_App_Audit_Tab_Detail where TAB_CONFIG_ID = '37';

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('854', '37', 'finprodMarketId', '资产代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 854);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('855', '37', 'finprodMarketName', '资产名称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 855);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('856', '37', 'bonusMode', '分红类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 856);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('857', '37', 'registDate', '权益登记日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 857);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('858', '37', 'exRightsDate', '除权日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 858);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('859', '37', 'arriveDate', '分红到账日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 859);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('860', '37', 'reInvesDate', '红利再投日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 860);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('861', '37', 'unitCprice', '单位分红净值', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 861);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('862', '37', 'tradeAmt', '分红金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 862);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('863', '37', 'qty', '分红份额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 863);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('864', '37', 'chlId', '通道', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 864);
 


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('865', '37', 'finAssetIntInfoList', '[{"key":"portfolioId","title":"组合代码"},{"key":"portfolioName","title":"组合名称"},{"key":"registShare","title":"权益登记日份额"},{"key":"unitCprice","title":"单位分红净值"},{"key":"tradeAmt","title":"分红金额"},{"key":"qty","title":"分红份额"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 865);


 

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('868', '37', 'remark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 868);
 
 
commit;

 
