-- 插入Tm_App_Audit_Type_Confit
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'ETR07';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('ETR07', '计划类利率型指令', '指令信息', 'etr', 'ETR07', null, null, 44, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 

-- 插入Tm_App_Audit_Tab_Confit
delete from Tm_App_Audit_Tab_Confit where BUSINESS_TYPE = 'ETR07' and TAB_ID = 'tab1';
insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('17', 'ETR07', '计划类利率型指令', 'tab1', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 
-- 插入Tm_App_Audit_Tab_Detail
delete from Tm_App_Audit_Tab_Detail where TAB_CONFIG_ID = '17';

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('286', '17', 'portfolioId', '组合代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 286);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('287', '17', 'vdate', '委托开始日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 287);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('288', '17', 'mdate', '委托终止日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 288);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('289', '17', 'finprodId', '资产代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 289);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('290', '17', 'ps', '交易方向', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 290);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('291', '17', 'finprodName', '资产名称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 291);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('292', '17', 'assetType1', '资产二级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 292);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('293', '17', 'assetType10', '资产三级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 293);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('294', '17', 'assetType11', '资产四级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 294);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('295', '17', 'ccyStr', '币种', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 295);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('296', '17', 'entrustAmt', '委托金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 296);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('297', '17', 'entrustNum', '委托本金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 297);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('298', '17', 'proAvailableCash', '组合T日可用现金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 298);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('299', '17', 'appProAvailableCash1', '组合T+1日可用现金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 299);
 


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('299', '17', 'appProAvailableCash2', '组合T+1日可用现金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 299);



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('300', '17', 'chlId', '通道', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 300);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('301', '17', 'channelAvailableCash', '通道可用现金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 301);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('302', '17', 'remark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 302);

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('305', '17', 'riskTrialRetDto', '[{"key":"kriName","title":"指标名称"},{"key":"kriType","title":"指标类型"},{"key":"portfolioName","title":"组合(包)名称"},{"key":"warningLine","title":"预警阈值"},{"key":"approveLine","title":"审批线"},{"key":"forbiddenLn","title":"违规阈值"},{"key":"kriValue","title":"当前实际值"},{"key":"oldValue","title":"原值"},{"key":"kriTrend","title":"趋势"},{"key":"kriStatus","title":"状态"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 305);
 


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1040', '17', 'etrInvestPersonInfo.realName', '投资经理', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1040);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1041', '17', 'etrInvestPersonInfo.room', '所属部门', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1041);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1042', '17', 'etrInvestPersonInfo.trader', '本方交易员', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1042);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1043', '17', 'etrInvestPersonInfo.counterId', '交易对手', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1043);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1044', '17', 'etrInvestPersonInfo.counterTrader', '对手方交易员', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1044);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1045', '17', 'etrInvestPersonInfo.contactInfo', '联系方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1045);

commit;

 
