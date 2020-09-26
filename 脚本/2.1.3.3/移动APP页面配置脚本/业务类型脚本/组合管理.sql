-- 插入Tm_App_Audit_Type_Confit
-- 插入主MENU
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'ptl';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('ptl', '组合管理', '组合管理', '0', 'null', 'null', 'null', 1, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

-- 插入MENU
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'PTL01';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('PTL01', '组合管理', '组合管理', 'ptl', 'PTL01', null, null, 2, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 



-- 插入Tm_App_Audit_Tab_Confit
delete from Tm_App_Audit_Tab_Confit where BUSINESS_TYPE = 'PTL01' and TAB_ID = 'tab1';
insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('26', 'PTL01', '组合管理', 'tab1', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 


-- 插入Tm_App_Audit_Tab_Detail
delete from Tm_App_Audit_Tab_Detail where TAB_CONFIG_ID = '26';

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('517', '26', 'portfolioId', '组合代码', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 1000);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('518', '26', 'portfolioName', '组合名称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 518);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('519', '26', 'portfolioType', '组合类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 519);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('520', '26', 'profitType', '收益类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 520);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('521', '26', 'openType', '开放类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 521);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('522', '26', 'raiseType', '募集方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 522);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('523', '26', 'investNature', '投资性质', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 523);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('524', '26', 'vdate', '成立日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 524);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('525', '26', 'mdate', '到期日', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 525);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('526', '26', 'isGuarantee', '是否保本', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 526);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('527', '26', 'remark', '备注', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 527);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('528', '26', 'unitvalueDec', '单位净值小数位', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 528);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('529', '26', 'unitvalueMode', '单位净值尾数处理方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 529);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('530', '26', 'milionProfitDec', '万份收益小数位', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 530);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('531', '26', 'milionProfitDecMode', '万份收益尾数处理方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 531);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('532', '26', 'rateDec', '利率小数位', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 532);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('533', '26', 'ratevalueMode', '利率处理方式', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 533);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('534', '26', 'isCountingCurInt', '托管活期利息计提', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 534);
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('535', '26', 'securitiesAccountInfoDto', '[{"key":"secAcctId","title":"证券账户账号"},{"key":"secAcctName","title":"证券账户名称"},{"key":"registOrg","title":"托管机构"},{"key":"secAcctSource","title":"账户来源"},{"key":"recAcctId","title":"回款账户"},{"key":"trdAcctId","title":"资金账户账号"},{"key":"acctName","title":"资金账号名称"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 535);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('536', '26', 'fundAccountInfoDto', '[{"key":"acctType","title":"资金账户类型"},{"key":"acctNo","title":"资金账户账号"},{"key":"acctName","title":"资金账户名称"},{"key":"bankDep","title":"开户银行"},{"key":"largePayno","title":"开户网点/大额支付行号"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 536);
 
 
commit;

  
