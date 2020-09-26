-- 插入Tm_App_Audit_Type_Confit
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'ETR08';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('ETR08', '回购投资委托', '指令信息', 'etr', 'ETR08', null, null, 39, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 



-- 插入Tm_App_Audit_Tab_Confit
delete from Tm_App_Audit_Tab_Confit where BUSINESS_TYPE = 'ETR08' and TAB_ID = 'tab1';
insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('12', 'ETR08', '回购投资委托', 'tab1', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 
-- 插入Tm_App_Audit_Tab_Detail
delete from Tm_App_Audit_Tab_Detail where TAB_CONFIG_ID = '12';

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('167', '12', 'entrustId', '委托代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 167);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('168', '12', 'portfolioId', '组合代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 168);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('169', '12', 'fTradeId', '成交编号', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 169);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('170', '12', 'settleSpeedStr', '清算速度', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 170);

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('171', '12', 'errorNumber', '误差值', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 171);
 

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('172', '12', 'assetType1', '回购类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 172);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('173', '12', 'ps', '交易方向', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 173);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('174', '12', 'calendarId', '交易日历', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 174);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('175', '12', 'termTypeReback', '期限品种', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 175);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('176', '12', 'term', '回购期限', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 176);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('177', '12', 'basis', '计息基础', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 177);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('178', '12', 'vdateAdd', '起息日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 178);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('179', '12', 'mdateAdd', '到期日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 179);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('180', '12', 'actCapDays', '实际占款天数', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 180);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('181', '12', 'securitiesCust', '证券账户', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 181);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('182', '12', 'chlId', '通道', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 182);


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('359', '12', 'minAmt', '最低成交金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 182);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('359', '12', 'proAvailableCash', '组合T日可用现金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 182);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('359', '12', 'appProAvailableCash1', '组合T+1日可用现金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 182);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('350', '12', 'assetAvailablePosition1', '可使用标准券额度', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 349);


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('359', '12', 'accAvailableCash', '托管户剩余现金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 182);


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('359', '12', 'availableDay', '资金可用日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 182);





insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('183', '12', 'vpayDate', '首期交收日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 183);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('184', '12', 'mpayDate', '到期交收日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 184);
 
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('186', '12', 'tradeMarket', '交易场所', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 186);

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('187', '12', 'aPPentrustAmtOne', '委托金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 168);



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('188', '12', 'rebackPrice', '回购利率%', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 188);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('189', '12', 'rebackIntAmt', '回购利息', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 189);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('190', '12', 'ccy', '币种', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 190);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('191', '12', 'mdateSettleAmt', '到期结算金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 191);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('192', '12', 'tradeAmtAndFee', '交易金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 192);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('193', '12', 'commissionAmt', '佣金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 193);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('194', '12', 'bussTrdTransferOrder', '[{"key":"isNeedSend","title":"是否发送指令"},{"key":"payTypeText","title":"划款类型"},{"key":"settleDateText","title":"支付日期"},{"key":"settleAmt","title":"金额"},{"key":"ccy","title":"币种"},{"key":"payAcctNo","title":"付款账户"},{"key":"payAcctName","title":"付款账户名称"},{"key":"recAcctNo","title":"收款账户"},{"key":"recAcctName","title":"收款账户名称"},{"key":"recAcctPoint","title":"收款账户网点"},{"key":"purpose","title":"用途"},{"key":"remark","title":"备注"},{"key":"portfolioId","title":"组合代码"},{"key":"portfolioName","title":"组合名称"},{"key":"inOutBank","title":"划款性质"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 194);

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('196', '12', 'riskTrialRetDto', '[{"key":"kriName","title":"指标名称"},{"key":"kriType","title":"指标类型"},{"key":"portfolioName","title":"组合(包)名称"},{"key":"warningLine","title":"预警阈值"},{"key":"approveLine","title":"审批线"},{"key":"forbiddenLn","title":"违规阈值"},{"key":"kriValue","title":"当前实际值"},{"key":"oldValue","title":"原值"},{"key":"kriTrend","title":"趋势"},{"key":"kriStatus","title":"状态"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 196);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1012', '12', 'trdInvestPersonInfo.trader', '本方交易员', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1012);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1013', '12', 'trdInvestPersonInfo.counterId', '交易对手', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1013);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1014', '12', 'trdInvestPersonInfo.counterTrader', '对手方交易员', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1014);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1015', '12', 'trdInvestPersonInfo.contactInfo', '联系方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1015);


 
 

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('347', '12', 'vdate', '委托开始日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 347);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('348', '12', 'mdate', '委托终止日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 348);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('349', '12', 'finprodId', '资产代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 349);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('350', '12', 'assetAvailablePosition', '资产可用持仓', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 349);



 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('351', '12', 'aPPentrustAmtTWO', '券面总额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 351);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('352', '12', 'price', '回购利率', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 352);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('353', '12', 'priceModel', '价格模式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 353);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('354', '12', 'firstprice', '首次净价', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 354);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('355', '12', 'firstprice2', '首次净价2', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 355);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('356', '12', 'settleSpreed', '清算速度', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 356);
 

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('357', '12', 'repoTerm', '回购期限', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 357);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('358', '12', 'repoTerm2', '回购期限2', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 358);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('359', '12', 'termType', '期限品种', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 359);


 


 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('363', '12', 'ccyStr', '币种', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 363);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('364', '12', 'remark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 364);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('350', '12', 'finprodName', '资产名称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 350);

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('367', '12', 'riskTrialRetDto', '[{"key":"kriName","title":"指标名称"},{"key":"kriType","title":"指标类型"},{"key":"portfolioName","title":"组合(包)名称"},{"key":"warningLine","title":"预警阈值"},{"key":"approveLine","title":"审批线"},{"key":"forbiddenLn","title":"违规阈值"},{"key":"kriValue","title":"当前实际值"},{"key":"oldValue","title":"原值"},{"key":"kriTrend","title":"趋势"},{"key":"kriStatus","title":"状态"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 367);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1058', '12', 'etrInvestPersonInfo.realName', '投资经理', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1058);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1059', '12', 'etrInvestPersonInfo.room', '所属部门', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1059);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1060', '12', 'etrInvestPersonInfo.trader', '本方交易员', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1060);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1061', '12', 'etrInvestPersonInfo.counterId', '交易对手', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1061);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1062', '12', 'etrInvestPersonInfo.counterTrader', '对手方交易员', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1062);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1063', '12', 'etrInvestPersonInfo.contactInfo', '联系方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1063);

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('2512', '12', 'bussRepoSec.totalPrinAmt', '汇总券面总额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 2510);



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('2511', '12', 'bussRepoSec.pledgeBondList', '[{"key":"bondCode","title":"债券代码"},{"key":"bondAbbrName","title":"债券简称"},{"key":"avaPledgePrin","title":"可质押券面总额"},{"key":"pledgePrin","title":"质押券面额"},{"key":"firstNetPrice","title":"首次净价"},{"key":"pledgeRatio","title":"折算比例%"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 2511);

commit;

    
