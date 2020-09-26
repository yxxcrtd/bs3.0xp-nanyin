-- 插入Tm_App_Audit_Type_Confit
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'ETR04';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('ETR04', '存款类指令', '指令信息', 'etr', 'ETR04', null, null, 40, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 

-- 插入Tm_App_Audit_Tab_Confit
delete from Tm_App_Audit_Tab_Confit where BUSINESS_TYPE = 'ETR04' and TAB_ID = 'tab1';
insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('13', 'ETR04', '存款类指令', 'tab1', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 
-- 插入Tm_App_Audit_Tab_Detail
delete from Tm_App_Audit_Tab_Detail where TAB_CONFIG_ID = '13';

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('198', '13', 'portfolioId', '组合代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 198);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('199', '13', 'vdate', '委托开始日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 199);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('200', '13', 'mdate', '委托终止日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 200);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('201', '13', 'finprodName', '资产名称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 201);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('202', '13', 'assetType1', '资产二级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 202);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('203', '13', 'assetType10', '资产三级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 203);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('204', '13', 'assetType11', '资产四级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 204);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('205', '13', 'ccyStr', '币种', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 205);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('206', '13', 'finprodMarketId', '资产代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 206);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('207', '13', 'dateStart', '起息日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 207);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('208', '13', 'dateEnd', '到期日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 208);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('209', '13', 'termDays', '期限', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 209);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('210', '13', 'ps', '交易方向', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 210);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('211', '13', 'entrustAmt', '委托金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 211);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('212', '13', 'price', '借款利率', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 212);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('213', '13', 'getChannelId', '通道', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 213);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('214', '13', 'proAvailableCash', '组合T日可用现金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 214);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('215', '13', 'proAvailableCash1', '组合T+1日可用现金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 215);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('216', '13', 'rateApprovalCode', '利率审批编码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 216);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('217', '13', 'creditSource', '授信来源', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 217);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('218', '13', 'etrInvestPersonInfo', '本金金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 218);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('219', '13', 'rebackPrice', '回购利率%', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 219);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('220', '13', 'rebackIntAmt', '回购利息', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 220);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('221', '13', 'ccyText', '币种', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 221);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('222', '13', 'mdateSettleAmt', '到期结算金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 222);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('223', '13', 'tradeAmtAndFee', '交易金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 223);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('224', '13', 'commissionAmt', '佣金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 224);

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('226', '13', 'riskTrialRetDto', '[{"key":"kriName","title":"指标名称"},{"key":"kriType","title":"指标类型"},{"key":"portfolioName","title":"组合(包)名称"},{"key":"warningLine","title":"预警阈值"},{"key":"approveLine","title":"审批线"},{"key":"forbiddenLn","title":"违规阈值"},{"key":"kriValue","title":"当前实际值"},{"key":"oldValue","title":"原值"},{"key":"kriTrend","title":"趋势"},{"key":"kriStatus","title":"状态"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 226);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1018', '13', 'bussEtrInvestPersonInfoDto.realName', '投资经理', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1018);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1019', '13', 'bussEtrInvestPersonInfoDto.room', '所属部门', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1019);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1020', '13', 'bussEtrInvestPersonInfoDto.trader', '本方交易员', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1020);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1021', '13', 'bussEtrInvestPersonInfoDto.counterId', '交易对手', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1021);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1016', '13', 'bussEtrInvestPersonInfoDto.counterTrader', '对手方交易员', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1016);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1017', '13', 'bussEtrInvestPersonInfoDto.contactInfo', '联系方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1017);


commit;



   








