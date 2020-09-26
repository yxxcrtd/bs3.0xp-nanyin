-- 插入Tm_App_Audit_Type_Confit
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'ETR05';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('ETR05', '公募基金指令', '指令信息', 'etr', 'ETR05', null, null, 42, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 

 
-- 插入Tm_App_Audit_Tab_Confit
delete from Tm_App_Audit_Tab_Confit where BUSINESS_TYPE = 'ETR05' and TAB_ID = 'tab1';
insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('15', 'ETR05', '公募基金指令', 'tab1', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 
-- 插入Tm_App_Audit_Tab_Detail
delete from Tm_App_Audit_Tab_Detail where TAB_CONFIG_ID = '15';

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('244', '15', 'portfolioId', '组合代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 244);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('245', '15', 'vdate', '委托开始日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 245);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('246', '15', 'mdate', '委托终止日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 246);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('247', '15', 'finprodName', '资产名称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 247);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('248', '15', 'trmFundAbbr', '交易所基金简称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 248);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('249', '15', 'tradeMarket', '交易场所', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 249);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('250', '15', 'ps', '交易方向', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 250);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('251', '15', 'trmFundType', '交易所基金类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 251);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('252', '15', 'finprodMarketId', '资产代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 252);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('253', '15', 'assetType1', '资产二级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 253);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('254', '15', 'assetType10', '资产三级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 254);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('255', '15', 'assetType11', '资产四级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 255);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('256', '15', 'ccyStr', '币种', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 256);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('257', '15', 'entrustAmt', '委托金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 257);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('257', '15', 'entrustNum', '委托份额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 257);


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('258', '15', 'price', '委托价格', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 258);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('259', '15', 'secAcctId', '证券账户', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 259);
 
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('260', '15', 'chlId', '通道', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 260);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('261', '15', 'proAvailableCash', '组合T日可用现金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 261);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('262', '15', 'appProAvailableCash1', '组合T+1日可用现金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 262);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('263', '15', 'assetAvailablePosition', '资产可用持仓', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 263);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('264', '15', 'remark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 264);
  
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('267', '15', 'riskTrialRetDto', '[{"key":"kriName","title":"指标名称"},{"key":"kriType","title":"指标类型"},{"key":"portfolioName","title":"组合(包)名称"},{"key":"warningLine","title":"预警阈值"},{"key":"approveLine","title":"审批线"},{"key":"forbiddenLn","title":"违规阈值"},{"key":"kriValue","title":"当前实际值"},{"key":"oldValue","title":"原值"},{"key":"kriTrend","title":"趋势"},{"key":"kriStatus","title":"状态"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 267);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1028', '15', 'bussEtrInvestPersonInfoDto.realName', '投资经理', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1028);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1029', '15', 'bussEtrInvestPersonInfoDto.room', '所属部门', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1029);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1030', '15', 'bussEtrInvestPersonInfoDto.trader', '本方交易员', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1030);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1031', '15', 'bussEtrInvestPersonInfoDto.counterId', '交易对手', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1031);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1032', '15', 'bussEtrInvestPersonInfoDto.counterTrader', '对手方交易员', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1032);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1033', '15', 'bussEtrInvestPersonInfoDto.contactInfo', '联系方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1033);



commit;


