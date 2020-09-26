-- 插入Tm_App_Audit_Type_Confit

delete from Tm_App_Audit_Type_Confit where MENU_ID = 'RSK02';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('RSK02', '授权管理', '风险管理', 'rsk', 'RSK02', null, null, 30, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

  
-- 插入Tm_App_Audit_Tab_Confit
delete from Tm_App_Audit_Tab_Confit where BUSINESS_TYPE = 'RSK02' and TAB_ID = 'tab1';
insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('42', 'RSK02', '授权管理', 'tab1', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

commit;
-- 插入Tm_App_Audit_Tab_Detail
delete from Tm_App_Audit_Tab_Detail where TAB_CONFIG_ID = '42';

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('955', '42', 'rolePos', '角色类型', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 955);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('956', '42', 'userName', '用户', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 956);
 
 
insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('963', '42', 'selectedItem', '[{"key":"assetType","title":"资产类型"},{"key":"assetSecType","title":"资产二级分类"},{"key":"tradeMarket","title":"交易场所"},{"key":"tradeDirection","title":"交易方向"},{"key":"authorizeLimitAmt","title":"授权额度(万元)"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 964);
 

commit;

 
