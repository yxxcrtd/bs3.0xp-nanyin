-- 插入Tm_App_Audit_Type_Confit
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'finasset';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('finasset', '资产信息管理', '资产信息管理', '0', 'null', 'null', 'null', 32, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

delete from Tm_App_Audit_Type_Confit where MENU_ID = 'FINASSET01';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('FINASSET01', '债券标的', '资产信息管理', 'finasset', 'FINASSET01', null, null, 33, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 
 


-- 插入Tm_App_Audit_Tab_Confit
delete from Tm_App_Audit_Tab_Confit where BUSINESS_TYPE = 'FINASSET01' ;

insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('3', 'FINASSET01', '基本信息', 'tab1', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);


insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('4', 'FINASSET01', '评级信息', 'tab2', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);


insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('5', 'FINASSET01', '中债报送', 'tab3', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

-- 插入Tm_App_Audit_Tab_Detail
 

delete from Tm_App_Audit_Tab_Detail where TAB_CONFIG_ID = '3' or TAB_CONFIG_ID = '4' or TAB_CONFIG_ID = '5' ;
 

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1301', '3', 'bussFinRepaymentDto.finCashPlanList', '[{"key":"mdate","title":"计息截止日"},{"key":"payDate","title":"支付日"},{"key":"prinAmt","title":"还本面值"},{"key":"intAmt","title":"应计利息"},{"key":"cashAmt","title":"本息和"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 50);


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1302', '3', 'finPayInterestStdInfoDto.payTdayRule', '营业日准则', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 45);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1303', '3', 'finPayInterestStdInfoDto.payCycle', '付息频率', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 46);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1304', '3', 'finPayInterestStdInfoDto.calcRule', '推算方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 47);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1305', '3', 'finPayInterestStdInfoDto.firstPayDate', '首次付息日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 48);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1306', '3', 'finPayInterestStdInfoDto.finCashPlanList', '[{"key":"vdate","title":"计息开始日"},{"key":"mdate","title":"计息截止日"},{"key":"payDate","title":"支付日"},{"key":"rangeYield","title":"区间利率"},{"key":"intPrinAmt","title":"计息本金"},{"key":"intAmt","title":"应计利息"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 49);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1307', '3', 'finInterestFactorDto.couponSpecies', '息票品种', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 32);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1308', '3', 'finInterestFactorDto.intType', '利率类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 33);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1309', '3', 'finInterestFactorDto.basis', '计息基础', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 34);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1310', '3', 'finInterestFactorDto.basisStyle', '利息分配方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 35);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1311', '3', 'finInterestFactorDto.rate', '初始利率', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 36);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1312', '3', 'finInterestFactorDto.highestRate', '利率上限', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 37);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1313', '3', 'finInterestFactorDto.lowestRate', '利率下限', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 38);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1314', '3', 'finInterestFactorDto.resetType', '基准利率调整规则', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 39);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1368', '3', 'finInterestFactorDto.observeBefDay', '延后时间', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 40);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1315', '3', 'finInterestFactorDto.observeBefUnit', '延后时间单位', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 41);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1316', '3', 'finInterestFactorDto.benchmarkId', '浮动利率', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 42);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1317', '3', 'finInterestFactorDto.coefficient', '系数', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 43);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1318', '3', 'finInterestFactorDto.spreadRate', '利差', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 44);


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1319', '3', 'postOrg', '投后责任机构', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 31);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1320', '3', 'sponsor', '担保人', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 30);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1321', '3', 'isCityBondStr', '是否城投债', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 29);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1322', '3', 'sustainableRemark', '特殊条例', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 28);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1323', '3', 'isFinOrg', '是否金融机构发行', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 27);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1324', '3', 'oFinprodId', '一级市场投标债券', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 26);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1325', '3', 'calendarName', '交易日历', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 25);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1326', '3', 'ccyName', '币种', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 24);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1327', '3', 'capiIncomeFeature', '保本标识', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 23);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1328', '3', 'isAutoUpdate', '是否允许wind数据覆盖', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 22);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1329', '3', 'blnArea', '境内外', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 21);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1330', '3', 'issueType', '发行方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 20);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1331', '3', 'isRightStr', '是否含权债', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 19);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1332', '3', 'isSecBondName', '是否次级债', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 18);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1333', '3', 'issuerName', '发行人', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 17);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1334', '3', 'issueAmt', '发行金额(万元)', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 16);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1335', '3', 'type12Name', '资产五级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 15);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1336', '3', 'type11Name', '资产四级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 14);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1337', '3', 'type10Name', '资产三级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 13);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1338', '3', 'type9Name', '资产二级分类', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 12);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1339', '3', 'isSusStr', '是否永续', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 11);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1340', '3', 'mdate', '到期日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 9);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1341', '3', 'vdate', '起息日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 8);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1342', '3', 'idate', '发行日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 7);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1343', '3', 'issuePrice', '发行价格', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 6);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1344', '3', 'registOrgName', '登记托管机构', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 5);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1345', '3', 'tradeMarketName', '交易场所', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 4);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1346', '3', 'finprodAbbr', '资产简称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 3);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1347', '3', 'finprodName', '资产名称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 2);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1348', '3', 'finprodMarketId', '资产代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1);



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1349', '5', 'bussFinBondRepInfoDto.repSectype', '具体类别', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1349);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1350', '5', 'bussFinBondRepInfoDto.repPartyType1', '发行机构类型（规模）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1350);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1351', '5', 'bussFinBondRepInfoDto.repPartyType2', '发行机构类型（经济类型）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1351);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1352', '5', 'bussFinBondRepInfoDto.repPartyType3', '发行机构类型（技术领域）', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1352);


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1353', '5', 'bussFinBondRepInfoDto.repInstitSize', '人行机构规模', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1353);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1353', '5', 'bussFinBondRepInfoDto.repIndustry', '发行机构所属行业1', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1353);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1354', '5', 'bussFinBondRepInfoDto.repIndustryLevel2', '发行机构所属行业2', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1354);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1355', '5', 'bussFinBondRepInfoDto.repFinancialComissue', '是否由金融资产投资公司发行', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1355);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1356', '5', 'bussFinBondRepInfoDto.repBankSecCapital', '是否商业银行二级资本债', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1356);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1357', '5', 'bussFinBondRepInfoDto.repBankSecPerpetual', '是否商业银行永续债', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1357);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1358', '5', 'bussFinBondRepInfoDto.repSecNature', '债券性质', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1358);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1358', '5', 'bussFinBondRepInfoDto.repTradeMarket', '交易流通场所', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1358);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1359', '5', 'bussFinBondRepInfoDto.repRegistorgComment', '登记托管机构说明', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1359);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1360', '5', 'bussFinBondRepInfoDto.repRemark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1360);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1361', '3', 'remark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1361);



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1362', '4', 'busiFinBondRatingInfoDto.mstBondCreditRatingList', '[{"key":"oType","title":"评级类型"},{"key":"shortLongTerm","title":"长短期"},{"key":"gradeResult","title":"评级结果"},{"key":"gradeDate","title":"发布日期"},{"key":"gradeOrgId","title":"评级机构"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1362);



insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1363', '3', 'busiFinExercisePlanDto.finExercisePlanList', '[{"key":"exerciseStatus","title":"行权状态"},{"key":"vdate","title":"行权申请起始日"},{"key":"mdate","title":"行权申请终止日"},{"key":"exerciseType","title":"行权方式"},{"key":"exerciseDate","title":"理论行权日"},{"key":"actExerciseDate","title":"实际行权日"},{"key":"exercisePrice","title":"行权价（净价）"},{"key":"exerciseRemark","title":"行权条款"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 45);

 
 


insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1367', '5', 'repAssetType', '资产/负债类别', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1);

commit;
