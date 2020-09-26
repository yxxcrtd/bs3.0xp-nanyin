-- 插入Tm_App_Audit_Type_Confit
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'FINASSET06';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('FINASSET06', '项目类标的', '资产信息管理', 'finasset', 'FINASSET06', null, null, 35, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 
  
 
-- 插入Tm_App_Audit_Tab_Confit
delete from Tm_App_Audit_Tab_Confit where BUSINESS_TYPE = 'FINASSET06';

insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('44', 'FINASSET06', '基本信息', 'tab1', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('45', 'FINASSET06', '评级信息', 'tab2', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('46', 'FINASSET06', '其他信息', 'tab3', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('47', 'FINASSET06', '中债报送', 'tab4', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);


-- 插入Tm_App_Audit_Tab_Detail
delete from Tm_App_Audit_Tab_Detail where TAB_CONFIG_ID = '44' or TAB_CONFIG_ID = '45'  or TAB_CONFIG_ID = '46' or TAB_CONFIG_ID = '47' ;
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1500', '44', 'finprodMarketId', '资产代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1500);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1501', '44', 'finprodName', '资产名称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1501);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1502', '44', 'vdate', '起息日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1502);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1503', '44', 'mdate', '到期日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1503);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1504', '44', 'termDays', '期限', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1504);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1505', '44', 'issueAmt', '融资金额', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1505);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1506', '44', 'type9Name', '资产二级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1506);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1507', '44', 'type10Name', '资产三级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1507);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1508', '44', 'type11Name', '资产四级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1508);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1509', '44', 'type12Name', '资产五级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1509);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1510', '44', 'settleDays', '清算时效', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1510);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1511', '44', 'ccyName', '币种', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1511);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1512', '44', 'capiIncomeFeature', '保本标识', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1512);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1513', '44', 'calendarId', '交易日历', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1513);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1514', '44', 'buyOutRate', '买断利率%', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1514);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1515', '44', 'contractNo', '合同号', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1515);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1516', '44', 'assetSource', '资产来源', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1516);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1517', '44', 'projInvestIndustry', '项目投向行业（1）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1517);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1518', '44', 'projInvestIndustry2', '项目投向行业（2）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1518);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1519', '44', 'setFinancierName', '项目投向行业（2）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1519);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1520', '44', 'financier', '融资人', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1520);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1521', '44', 'sponsor', '担保人', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1521);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1522', '44', 'investAdviser', '投资顾问', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1522);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1523', '44', 'investNature', '资产投资性质', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1523);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1524', '44', 'pflsh', '批复号', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1524);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1525', '44', 'actualCreditProvid', '实际授信主体', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1525);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1526', '44', 'trialNo', '法审编号', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1526);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1526', '44', 'isNonStandard', '是否非标', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1527);



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1527', '44', 'remark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1527);



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1528', '44', 'bussFinRecommentDto.recommender', '资产推荐方', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1528);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1529', '44', 'bussFinRecommentDto.recfeeRate', '资产推荐费率%', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1529);


 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1530', '44', 'finInterestFactorDto.intType', '利率类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1530);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1531', '44', 'finInterestFactorDto.basis', '计息基础', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1531);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1532', '44', 'finInterestFactorDto.calRateType', '计息利率方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1532);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1533', '44', 'finInterestFactorDto.loadRate', '借款利率%', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1533);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1534', '44', 'finInterestFactorDto.investRate', '净投资收益率%', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1534);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1535', '44', 'finInterestFactorDto.highestRate', '利率上限', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1535);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1536', '44', 'finInterestFactorDto.lowestRate', '利率下限', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1536);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1537', '44', 'finInterestFactorDto.resetType', '基准利率调整规则', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1537);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1538', '44', 'finInterestFactorDto.observeBefDay', '延后时间', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1538);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1539', '44', 'finInterestFactorDto.observeBefUnit', '延后时间单位', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1539);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1540', '44', 'finInterestFactorDto.benchmarkId', '浮动利率', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1540);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1541', '44', 'finInterestFactorDto.coefficient', '系数', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1541);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1542', '44', 'finInterestFactorDto.spreadRate', '利差', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1542);




insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1543', '44', 'finInterestFactorDto.recfeeRate', '资产推荐费率%', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1543);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1544', '44', 'finInterestFactorDto.recommender', '资产推荐方', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1544);



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1545', '44', 'bussParticipantInfoDto.bussParticipantInfoDtoList', '[{"key":"counterId","title":"通道代码"},{"key":"counterName","title":"通道名称"},{"key":"managerCustomerName","title":"管理人"},{"key":"clientYield","title":"托管费率%"},{"key":"managerYield","title":"管理费率"},{"key":"otherYield","title":"其他费率"},{"key":"vatTaxRate","title":"增值税税率"},{"key":"adtTaxRate","title":"附加税税率"},{"key":"busiAcctNo","title":"托管账号"},{"key":"clientCustomerName","title":"托管人"},{"key":"contactWay","title":"联系方式"},{"key":"contact","title":"联系人"},{"key":"remark","title":"备注"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1545);

 




 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1546', '44', 'finPayInterestStdInfoDto.endTdayRule', '计息营业日准则', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1546);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1547', '44', 'finPayInterestStdInfoDto.payTdayRule', '付息营业日准则', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1547);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1548', '44', 'finPayInterestStdInfoDto.payCycle', '付息频率', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1548);
 
 

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1546', '44', 'finPayInterestStdInfoDto.calcRule', '推算方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1546);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1547', '44', 'finPayInterestStdInfoDto.firstPayDate', '首次付息日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1547);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1548', '44', 'finPayInterestStdInfoDto.remark', '自定义说明', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1548);
 


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1549', '44', 'finPayInterestStdInfoDto.finCashPlanList', '[{"key":"vdate","title":"计息开始日"},{"key":"mdate","title":"计息截止日"},{"key":"payDate","title":"支付日"},{"key":"rangeYield","title":"区间净投资收益率%"},{"key":"intPrinAmt","title":"计息本金（元）"},{"key":"intAmt","title":"应计利息（元）"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1548);




insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1546', '44', 'bussFinRepaymentDto.payCycle', '还款周期', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1548);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1547', '44', 'bussFinRepaymentDto.remark', '还款周期说明', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1548);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1548', '44', 'bussFinRepaymentDto.isPayIntFlag', '是否还本不带息', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1548);
 



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1549', '44', 'bussFinRepaymentDto.finCashPlanList', '[{"key":"mdate","title":"计息截止日"},{"key":"payDate","title":"还款日"},{"key":"prinAmt","title":"还款金额"},{"key":"intAmt","title":"应计利息"},{"key":"cashAmt","title":"本息和"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1550);



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1550', '45', 'busiFinBondRatingInfoDto.oType', '评级类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1550);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1551', '45', 'busiFinBondRatingInfoDto.shortLongTerm', '长短期', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1551);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1552', '45', 'busiFinBondRatingInfoDto.gradeResult', '评级结果', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1552);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1553', '45', 'busiFinBondRatingInfoDto.gradeDate', '发布日期', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1553);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1554', '45', 'busiFinBondRatingInfoDto.gradeOrgId', '评级机构', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1554);
 

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1556', '47', 'repAssetType', '资产/负债类别', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1556);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1557', '46', 'bussPostInvestInfoDto.postOrg', '投后责任机构', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1557);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1558', '46', 'bussPostInvestInfoDto.initInferiorRate', '初始劣后占比', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1558);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1559', '46', 'bussPostInvestInfoDto.isRepClause', '是否含回购条款', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1559);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1560', '46', 'bussPostInvestInfoDto.assetInsideType', '部门内部资产分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1560);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1561', '46', 'bussPostInvestInfoDto.assetBotType', '底层资产类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1561);


 


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1562', '46', 'bussFinProductCreditDto.finProductCreditList', '[{"key":"creditFlag","title":"增信主体"},{"key":"creditType","title":"增信方式"},{"key":"remark","title":"备注"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1562);

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1564', '46', 'bussPostInvestInfoDto.remark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1564);







insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1565', '47', 'bussRepAssetCreditNonstdInfoDto.repTradeMarket', '交易流通场所', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1565);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1566', '47', 'bussRepAssetCreditNonstdInfoDto.repProfitType', '收/受益权类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1566);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1567', '47', 'bussRepAssetCreditNonstdInfoDto.repIsResale', '是否属于买入返售', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1567);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1568', '47', 'bussRepAssetCreditNonstdInfoDto.repRateFlag', '是否有预期收益率', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1568);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1569', '47', 'bussRepAssetCreditNonstdInfoDto.repIntpayCycle', '付息频率（个月/次）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1569);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1570', '47', 'bussRepAssetCreditNonstdInfoDto.repRepayCmt', '还本付息情况说明', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1570);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1571', '47', 'bussRepAssetCreditNonstdInfoDto.repFinancier', '融资人', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1571);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1572', '47', 'bussRepAssetCreditNonstdInfoDto.repFcrGradeIn', '融资人内部信用评级', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1572);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1573', '47', 'bussRepAssetCreditNonstdInfoDto.repFcrGradeOut', '外部评级机构名称及融资人评级结果', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1573);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1574', '47', 'bussRepAssetCreditNonstdInfoDto.repFinancierType1', '融资人类型（按规模划分）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1574);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1575', '47', 'bussRepAssetCreditNonstdInfoDto.repFinancierType2', '融资人类型（按经济类型划分）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1575);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1576', '47', 'bussRepAssetCreditNonstdInfoDto.repFinancierType3', '融资人类型（按技术领域划分）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1576);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1577', '47', 'bussRepAssetCreditNonstdInfoDto.repFinanceProj', '融资项目', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1577);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1578', '47', 'bussRepAssetCreditNonstdInfoDto.repIndustry1', '融资人所属行业', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1578);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1579', '47', 'bussRepAssetCreditNonstdInfoDto.repIndustryLevel2', '融资人所属行业2', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1579);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1580', '47', 'bussRepAssetCreditNonstdInfoDto.repProIndustry', '融资项目所属行业', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1580);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1581', '47', 'bussRepAssetCreditNonstdInfoDto.repProIndustryLevel2', '融资项目所属行业2', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1581);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1582', '47', 'bussRepAssetCreditNonstdInfoDto.repIndustryFlag', '项目是否属于重点监控行业和领域', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1582);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1583', '47', 'bussRepAssetCreditNonstdInfoDto.repMonitType', '重点监控行业和领域类别', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1583);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1584', '47', 'bussRepAssetCreditNonstdInfoDto.repMonTypeComm', '重点监控行业和领域类别说明', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1584);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1585', '47', 'bussRepAssetCreditNonstdInfoDto.repGuaType', '担保方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1585);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1586', '47', 'bussRepAssetCreditNonstdInfoDto.repGuaTypeRemark', '担保情况说明', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1586);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1587', '47', 'bussRepAssetCreditNonstdInfoDto.repFinancierGrade', '融资人主体评级', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1587);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1588', '47', 'bussRepAssetCreditNonstdInfoDto.repInGrade', '资产内部评级', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1588);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1589', '47', 'bussRepAssetCreditNonstdInfoDto.repOutGrade', '对外对外资产外部评级', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1589);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1590', '47', 'bussRepAssetCreditNonstdInfoDto.repChose', '选择权', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1590);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1591', '47', 'bussRepAssetCreditNonstdInfoDto.repConditionCmm', '行权条件说明', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1591);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1592', '47', 'bussRepAssetCreditNonstdInfoDto.repFinArea', '融资人所属地区', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1592);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1593', '47', 'bussRepAssetCreditNonstdInfoDto.repFinOrgCode', '融资人组织机构（社会信用）代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1593);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1594', '47', 'bussRepAssetCreditNonstdInfoDto.repPrivatefundFlag', '投向金融资产投资公司或其附属机构发行的私募股权投资基金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1594);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1595', '47', 'bussRepAssetCreditNonstdInfoDto.repPppflag', '投向“公共私营合作项目”（PPP）的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1595);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1596', '47', 'bussRepAssetCreditNonstdInfoDto.repSmallcredit', '小微企业信贷资产流转和收益权转让', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1596);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1597', '47', 'bussRepAssetCreditNonstdInfoDto.repPrivateprdFlag', '投向地方政府融资平台的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1597);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1598', '47', 'bussRepAssetCreditNonstdInfoDto.repBondtostockFlag', '投向市场化债转股相关产品的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1598);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1599', '47', 'bussRepAssetCreditNonstdInfoDto.repAssignee', '受让形式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1599);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1600', '47', 'bussRepAssetCreditNonstdInfoDto.repFmdate', '法定到期日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1600);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1601', '47', 'bussRepAssetCreditNonstdInfoDto.repPrinBaseamt', '份额面值', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1601);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1602', '47', 'bussRepAssetCreditNonstdInfoDto.repCouponSpecies', '计息类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1602);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1603', '47', 'bussRepAssetCreditNonstdInfoDto.repIntpayRule', '利息分布方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1603);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1604', '47', 'bussRepAssetCreditNonstdInfoDto.repStructGrade', '结构档次', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1604);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1605', '47', 'bussRepAssetCreditNonstdInfoDto.repRepaymentType', '还本方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1605);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1606', '47', 'bussRepAssetCreditNonstdInfoDto.repPeriodRepaymentFlag', '分期还本条款标识', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1606);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1607', '47', 'bussRepAssetCreditNonstdInfoDto.repExcessProfitRate', '超额收益分配比例%', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1607);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1608', '47', 'bussRepAssetCreditNonstdInfoDto.repContainType', '含权类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1608);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1609', '47', 'bussRepAssetCreditNonstdInfoDto.repExerciseType', '行权方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1609);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1610', '47', 'bussRepAssetCreditNonstdInfoDto.repExerciseDate', '固定行权日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1610);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1611', '47', 'bussRepAssetCreditNonstdInfoDto.repFirstExerciseDate', '首次行权日期', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1611);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1612', '47', 'bussRepAssetCreditNonstdInfoDto.repExercisePeriod', '行权周期', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1612);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1613', '47', 'bussRepAssetCreditNonstdInfoDto.repExercisePrice', '行权价格', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1613);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1614', '47', 'bussRepAssetCreditNonstdInfoDto.repPerpetualClauseType', '永续条款类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1614);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1615', '47', 'bussRepAssetCreditNonstdInfoDto.repDeferredInterestType', '利息递延条款类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1615);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1616', '47', 'bussRepAssetCreditNonstdInfoDto.repDeferredInterestInt', '递延利息是否计息', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1616);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1617', '47', 'bussRepAssetCreditNonstdInfoDto.repFirstReprceDate', '首次重定价日期', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1617);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1618', '47', 'bussRepAssetCreditNonstdInfoDto.repReprceCycle', '重定价周期', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1618);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1619', '47', 'bussRepAssetCreditNonstdInfoDto.repPartRedemFlag', '部分赎回标识', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1619);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1620', '47', 'bussRepAssetCreditNonstdInfoDto.repPartRedemRate', '部分赎回比例%', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1620);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1621', '47', 'bussRepAssetCreditNonstdInfoDto.repPledgeType', '抵质押物类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1621);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1622', '47', 'bussRepAssetCreditNonstdInfoDto.repPledgeValue', '抵质押物价值（元）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1622);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1623', '47', 'bussRepAssetCreditNonstdInfoDto.repNatureGuarantee', '担保性质', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1623);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1624', '47', 'bussRepAssetCreditNonstdInfoDto.repRelationFinan', '担保人与融资人关系', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1624);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1625', '47', 'bussRepAssetCreditNonstdInfoDto.repBasicAssetType', '基础资产类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1625);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1626', '47', 'bussRepAssetCreditNonstdInfoDto.repRemark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1626);


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1627', '47', 'BussRepAssetAstBillInfoDto.repTradeMarket', '交易流通场所', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1627);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1628', '47', 'BussRepAssetAstBillInfoDto.repIsResale', '是否属于买入返售', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1628);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1629', '47', 'BussRepAssetAstBillInfoDto.repPrivatefundFlag', '投向金融资产投资公司或其附属机构发行的私募股权投资基金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1629);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1630', '47', 'BussRepAssetAstBillInfoDto.repPppflag', '投向“公共私营合作项目”（PPP）的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1630);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1631', '47', 'BussRepAssetAstBillInfoDto.repSmallcredit', '小微企业信贷资产流转和收益权转让', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1631);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1632', '47', 'BussRepAssetAstBillInfoDto.repPrivateprdFlag', '投向地方政府融资平台的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1632);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1633', '47', 'BussRepAssetAstBillInfoDto.repBondtostockFlag', '投向市场化债转股相关产品的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1633);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1634', '47', 'BussRepAssetAstBillInfoDto.repIsRight', '是否为收/受益权', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1634);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1635', '47', 'BussRepAssetAstBillInfoDto.repRemainTerm', '加权剩余期限（天）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1635);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1636', '47', 'BussRepAssetAstBillInfoDto.repMaxRemainTerm', '最长剩余期限（天）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1636);

 insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1637', '47', 'BussRepAssetAstBillInfoDto.repMinRemainTerm', '最短剩余期限（天）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1637);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1638', '47', 'BussRepAssetAstBillInfoDto.repNum', '数量', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1638);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1639', '47', 'BussRepAssetAstBillInfoDto.repDiscRate', '贴现利率', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1639);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1640', '47', 'BussRepAssetAstBillInfoDto.repAstBillType', '类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1640);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1641', '47', 'BussRepAssetAstBillInfoDto.repRemark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1641);

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1642', '47', 'bussRepFundInfoDto.repIndustryLevel2', '行业2', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1642);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1643', '47', 'bussRepFundInfoDto.repTradeMarket', '交易流通场所', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1643);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1644', '47', 'bussRepFundInfoDto.repPrivateprdFlag', '投向地方政府融资平台的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1644);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1645', '47', 'bussRepFundInfoDto.repBondtostockFlag', '投向市场化债转股相关产品的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1645);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1646', '47', 'bussRepFundInfoDto.repPrivatefundFlag', '投向金融资产投资公司或其附属机构发行的私募股权投资基金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1646);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1647', '47', 'bussRepFundInfoDto.repPppflag', '投向“公共私营合作项目”（PPP）的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1647);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1648', '47', 'bussRepFundInfoDto.repSmallcredit', '小微企业信贷资产流转和收益权转让', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1648);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1649', '47', 'bussRepFundInfoDto.repIsGovInvest', '是否属于政府投资基金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1649);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1650', '47', 'bussRepFundInfoDto.repGovInvestFor', '政府投资基金投向', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1650);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1651', '47', 'bussRepFundInfoDto.repIndustry', '行业1', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1651);

 insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1652', '47', 'bussRepFundInfoDto.repTrustPartyName', '登记备案机构', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1652);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1653', '47', 'bussRepFundInfoDto.repFundInvestAsset', '基金投资资产', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1653);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1654', '47', 'bussRepFundInfoDto.repFundCompName', '基金公司名称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1654);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1655', '47', 'bussRepFundInfoDto.repTrustPartyName', '基金托管机构名称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1655);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1656', '47', 'bussRepFundInfoDto.repInvestStage', '投资阶段', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1656);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1657', '47', 'bussRepFundInfoDto.repPartyType3', '投资企业类型（技术领域）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1657);

 insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1658', '47', 'bussRepFundInfoDto.repPartyType1', '投资企业类型（规模）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1658);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1659', '47', 'bussRepFundInfoDto.repPartyType2', '投资企业类型（经济类型）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1659);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1660', '47', 'bussRepFundInfoDto.repFinancialComissue', '是否由金融资产投资公司发行', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1660);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1661', '47', 'bussRepFundInfoDto.repRemark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1661);

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1662', '47', 'bussRepOtherInvestTargetInfoDto.repTradeMarket', '交易流通场所', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1662);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1663', '47', 'bussRepOtherInvestTargetInfoDto.repIntpayCycle', '付息频率（个月/次）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1663);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1664', '47', 'bussRepOtherInvestTargetInfoDto.repFinancier', '融资人', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1664);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1665', '47', 'bussRepOtherInvestTargetInfoDto.repFcrGradeOut', '外部评级机构名称及融资人评级结果', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1665);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1666', '47', 'bussRepOtherInvestTargetInfoDto.repFinanceProj', '融资项目', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1666);

 insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1667', '47', 'bussRepOtherInvestTargetInfoDto.repOutGrade', '对外资产外部评级', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1667);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1668', '47', 'bussRepOtherInvestTargetInfoDto.repFinancierType1', '融资人类型（按规模划分）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1668);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1669', '47', 'bussRepOtherInvestTargetInfoDto.repFinancierType2', '融资人类型（按经济类型划分）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1669);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1670', '47', 'bussRepOtherInvestTargetInfoDto.repFinancierType3', '融资人类型（按技术领域划分）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1670);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1671', '47', 'bussRepOtherInvestTargetInfoDto.repInstitSize', '人行机构规模', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1671);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1671', '47', 'bussRepOtherInvestTargetInfoDto.repIndustryFlag', '项目是否属于重点监控行业和领域', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1671);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1672', '47', 'bussRepOtherInvestTargetInfoDto.repMonitType', '重点监控行业和领域类别', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1672);

 insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1673', '47', 'bussRepOtherInvestTargetInfoDto.repMonTypeComm', '重点监控行业和领域类别说明', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1673);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1674', '47', 'bussRepOtherInvestTargetInfoDto.repIndustry1', '融资人所属行业', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1674);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1675', '47', 'bussRepOtherInvestTargetInfoDto.repIndustryLevel2', '融资人所属行业2', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1675);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1676', '47', 'bussRepOtherInvestTargetInfoDto.repProIndustry', '融资项目所属行业', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1676);



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1677', '47', 'bussRepOtherInvestTargetInfoDto.repProIndustryLevel2', '融资项目所属行业2', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1677);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1678', '47', 'bussRepOtherInvestTargetInfoDto.repPrivatefundFlag', '投向金融资产投资公司或其附属机构发行的私募股权投资基金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1678);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1679', '47', 'bussRepOtherInvestTargetInfoDto.repPppflag', '投向“公共私营合作项目”（PPP）的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1679);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1680', '47', 'bussRepOtherInvestTargetInfoDto.repSmallcredit', '小微企业信贷资产流转和收益权转让', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1680);



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1681', '47', 'bussRepOtherInvestTargetInfoDto.repPrivateprdFlag', '投向地方政府融资平台的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1681);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1682', '47', 'bussRepOtherInvestTargetInfoDto.repBondtostockFlag', '投向市场化债转股相关产品的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1682);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1683', '47', 'bussRepOtherInvestTargetInfoDto.repBankAssetType', '行内资产类别', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1683);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1684', '47', 'bussRepOtherInvestTargetInfoDto.repBankAssetTyprExplain', '行内资产类别说明', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1684);


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1685', '47', 'bussRepOtherInvestTargetInfoDto.repCountryArea', '所属国家或地区', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1685);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1686', '47', 'bussRepOtherInvestTargetInfoDto.repContainExpectedYield2', '是否有预期收益率', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1686);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1687', '47', 'bussRepOtherInvestTargetInfoDto.repFinancierOrgCode', '融资人组织机构（社会信用）代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1687);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1688', '47', 'bussRepOtherInvestTargetInfoDto.repAssetRegDate', '资产登记日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1688);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1689', '47', 'bussRepOtherInvestTargetInfoDto.repDeptEquity', '是否为债转股', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1689);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1690', '47', 'bussRepOtherInvestTargetInfoDto.repRemark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1690);





insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1691', '47', 'bussRepEquityInfoDto.repInvestStage', '投资阶段', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1691);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1692', '47', 'bussRepEquityInfoDto.repPartyType3', '投资企业类型（技术领域）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1692);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1693', '47', 'bussRepEquityInfoDto.repPartyType1', '投资企业类型（规模）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1693);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1694', '47', 'bussRepEquityInfoDto.repPartyType2', '投资企业类型（经济类型）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1694);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1695', '47', 'bussRepEquityInfoDto.repPrivatefundFlag', '投向金融资产投资公司或其附属机构发行的私募股权投资基金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1695);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1696', '47', 'bussRepEquityInfoDto.repPppflag', '投向“公共私营合作项目”（PPP）的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1696);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1697', '47', 'bussRepEquityInfoDto.repSmallcredit', '小微企业信贷资产流转和收益权转让', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1697);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1698', '47', 'bussRepEquityInfoDto.repBondtostockFlag', '投向市场化债转股相关产品的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1698);


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1699', '47', 'bussRepEquityInfoDto.repSharesType', '股票类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1699);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1700', '47', 'bussRepEquityInfoDto.repIsPledge', '是否为质押融资', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1700);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1701', '47', 'bussRepEquityInfoDto.repEquityQuit', '股权退出安排', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1701);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1702', '47', 'bussRepEquityInfoDto.repRemark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1702);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1703', '47', 'bussRepFundInfoDto.repInvestStage', '投资阶段', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1703);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1704', '47', 'bussFinBondRepInfoDto.repSectype', '具体类别', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1704);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1705', '47', 'bussFinBondRepInfoDto.repPartyType1', '发行机构类型（按规模划分）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1705);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1706', '47', 'bussFinBondRepInfoDto.repPartyType2', '发行机构类型（按经济类型划分)', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1706);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1707', '47', 'bussFinBondRepInfoDto.repPartyType3', '发行机构类型（按技术领域划分)', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1707);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1708', '47', 'bussFinBondRepInfoDto.repIndustry', '发行机构所属行业', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1708);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1709', '47', 'bussFinBondRepInfoDto.repFinancialComissue', '是否由金融资产投资公司发行', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1709);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1710', '47', 'bussFinBondRepInfoDto.repBankSecCapital', '是否商业银行二级资本债', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1710);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1711', '47', 'bussFinBondRepInfoDto.repBankSecPerpetual', '是否商业银行永续债', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1711);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1712', '47', 'bussFinBondRepInfoDto.repTradeMarket', '交易流通场所', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1712);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1713', '47', 'bussFinBondRepInfoDto.repRegistorgComment', '登记托管机构说明', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1713);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1714', '47', 'bussFinBondRepInfoDto.repPrivateprdFlag', '投向地方政府融资平台的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1714);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1715', '47', 'bussFinBondRepInfoDto.repBondtostockFlag', '投向市场化债转股相关产品的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1715);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1716', '47', 'bussFinBondRepInfoDto.repPrivatefundFlag', '投向金融资产投资公司或其附属机构发行的私募股权投资基金', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1716);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1717', '47', 'bussFinBondRepInfoDto.repPppflag', '投向“公共私营合作项目”（PPP）的部分', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1717);


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1718', '47', 'bussFinBondRepInfoDto.repSmallcredit', '小微企业信贷资产流转和收益权转让', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1718);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1719', '47', 'bussFinBondRepInfoDto.repRemark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1719);

  

commit;












