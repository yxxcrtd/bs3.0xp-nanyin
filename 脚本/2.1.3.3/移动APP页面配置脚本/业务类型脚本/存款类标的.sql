-- 插入Tm_App_Audit_Type_Confit
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'FINASSET02';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('FINASSET02', '存款类标的', '资产信息管理', 'finasset', 'FINASSET02', null, null, 34, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);


-- 插入Tm_App_Audit_Tab_Confit
delete from Tm_App_Audit_Tab_Confit where BUSINESS_TYPE = 'FINASSET02' ;
insert into Tm_App_Audit_Tab_Confit(ID,BUSINESS_TYPE,TAB_NAME,TAB_ID,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3) 
values('6','FINASSET02','基本信息','tab1',sysdate,'zhuhc','320099993','','','E','','','') ;

insert into Tm_App_Audit_Tab_Confit(ID,BUSINESS_TYPE,TAB_NAME,TAB_ID,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3) 
values('7','FINASSET02','中债报送','tab2',sysdate,'zhuhc','320099993','','','E','','','') ;

 

-- 插入Tm_App_Audit_Tab_Detail 
delete from Tm_App_Audit_Tab_Detail where TAB_CONFIG_ID = '6' or TAB_CONFIG_ID = '7' ;
 
insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1401','6','finprodName','资产名称',sysdate,'admin','320099993','','','E','','','',1401);

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1400','6','finprodMarketId','资产代码',sysdate,'admin','320099993','','','E','','','',1400);

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1402','6','vdate','起息日',sysdate,'admin','320099993','','','E','','','',1402);

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1403','6','mdate','到期日',sysdate,'admin','320099993','','','E','','','',1403);

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1404','6','termDays','期限',sysdate,'admin','320099993','','','E','','','',1404);

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1405','6','issuerName','存借款机构',sysdate,'admin','320099993','','','E','','','',1405);

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1406','6','issueAmt','存款本金',sysdate,'admin','320099993','','','E','','','',1406) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1407','6','type9Name','资产二级分类',sysdate,'admin','320099993','','','E','','','',1407) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1408','6','type10Name','资产三级分类',sysdate,'admin','320099993','','','E','','','',1408) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1409','6','type11Name','资产四级分类',sysdate,'admin','320099993','','','E','','','',1409) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1410','6','type12Name','资产五级分类',sysdate,'admin','320099993','','','E','','','',1410) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1411','6','blnArea','境内外',sysdate,'admin','320099993','','','E','','','',1411) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1412','6','ccyName','币种',sysdate,'admin','320099993','','','E','','','',1412) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1413','6','calendarName','交易日历',sysdate,'admin','320099993','','','E','','','',1413) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1414','6','buyOutRate','买断利率',sysdate,'admin','320099993','','','E','','','',1414) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1415','6','contractNo','合同号',sysdate,'admin','320099993','','','E','','','',1415) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1416','6','assetSource','资产来源',sysdate,'admin','320099993','','','E','','','',1416) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1417','6','trialNo','法审编号',sysdate,'admin','320099993','','','E','','','',1417) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1418','6','postOrg','投后责任机构',sysdate,'admin','320099993','','','E','','','',1418) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1419','6','sponsor','担保人',sysdate,'admin','320099993','','','E','','','',1419) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1420','6','regulatoryRating','监管评级',sysdate,'admin','320099993','','','E','','','',1420) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1421','6','remark','备注',sysdate,'admin','320099993','','','E','','','',1421) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1422','6','BussFinRecommentDto.recommender','资产推荐方',sysdate,'admin','320099993','','','E','','','',1422) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1423','6','BussFinRecommentDto.recfeeRate','资产推荐费率%',sysdate,'admin','320099993','','','E','','','',1423) ;
 
insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1424','6','finInterestFactorDto.intType','利率类型',sysdate,'admin','320099993','','','E','','','',1424) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1425','6','finInterestFactorDto.basis','计息基础',sysdate,'admin','320099993','','','E','','','',1425) ;

insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1426','6','finInterestFactorDto.loadRate','借款利率',sysdate,'admin','320099993','','','E','','','',1426) ;
 
insert into Tm_App_Audit_Tab_Detail(ID,TAB_CONFIG_ID,KEY,TITLE,TM_CREATETIME,TM_CREATER,TM_ORGID,TM_UPDATETIME,TM_UPDATER,TM_EFFECTFLAG,TM_FIELD1,TM_FIELD2,TM_FIELD3,ORDERBY)
values('1427','6','bussParticipantInfoDto.bussParticipantInfoDtoList','[{"key":"counterId","title":"通道代码"},{"key":"counterName","title":"通道名称"},{"key":"managerCustomerName","title":"管理人"},{"key":"clientYield","title":"托管费率%"},{"key":"managerYield","title":"管理费率"},{"key":"otherYield","title":"其他费率"},{"key":"vatTaxRate","title":"增值税税率"},{"key":"adtTaxRate","title":"附加税税率"},{"key":"busiAcctNo","title":"托管账号"},{"key":"clientCustomerName","title":"托管人"},{"key":"contactWay","title":"联系方式"},{"key":"contact","title":"联系人"},{"key":"remark","title":"备注"}]',sysdate,'admin','320099993','','','E','','','',1427) ;

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1428', '6', 'finPayInterestStdInfoDto.payTdayRule', '营业日准则', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1428);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1429', '6', 'finPayInterestStdInfoDto.payCycle', '付息频率', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1429);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1430', '6', 'finPayInterestStdInfoDto.calcRule', '推算方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1430) ;

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1431', '6', 'finPayInterestStdInfoDto.firstPayDate', '首次付息日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1431) ;

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1432', '6', 'finPayInterestStdInfoDto.finCashPlanList', '[{"key":"vdate","title":"计息开始日"},{"key":"mdate","title":"计息截止日"},{"key":"payDate","title":"支付日"},{"key":"intAmt","title":"应计利息"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1432) ;

 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1434', '7', 'bussRepDepositInfoDto.repAssetType', '资产/负债类别', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1434) ;

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1435', '7', 'bussRepDepositInfoDto.repTradeMarket', '交易流通场所', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1435) ;

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1436', '7', 'bussRepDepositInfoDto.repDepoType', '存款类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1436) ;

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1437', '7', 'bussRepDepositInfoDto.repTarget', '结构性存款挂钩标的', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1437) ;

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1438', '7', 'bussRepDepositInfoDto.repRemark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1438);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('1439', '7', 'bussRepDepositInfoDto.repTargetType', '结构性存款挂钩标的类别', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1439);


 
commit ;
