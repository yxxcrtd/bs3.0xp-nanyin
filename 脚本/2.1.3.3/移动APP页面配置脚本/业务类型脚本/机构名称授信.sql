-- 插入Tm_App_Audit_Type_Confit

-- 插入主MENU
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'rsk';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('rsk', '风险管理', '风险管理', '0', 'null', 'null', 'null', 28, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

-- 插入MENU
delete from Tm_App_Audit_Type_Confit where MENU_ID = 'RSK24';
insert into Tm_App_Audit_Type_Confit (MENU_ID, MENU_NAME, BIG_BUSINESS_TYPE, PARENT_ID, AUDIT_TYPE, ICON, COLOR, ORDERBY, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('RSK24', '机构名单管理', '风险管理', 'rsk', 'RSK24', null, null, 29, sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 


-- 插入Tm_App_Audit_Tab_Confit
delete from Tm_App_Audit_Tab_Confit where BUSINESS_TYPE = 'RSK24' and TAB_ID = 'tab1';
insert into Tm_App_Audit_Tab_Confit (ID, BUSINESS_TYPE, TAB_NAME, TAB_ID, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3)
values ('43', 'RSK24', '机构名单管理', 'tab1', sysdate, 'zhuhc', '320099993', null, null, 'E', null, null, null);

 
-- 插入Tm_App_Audit_Tab_Detail
delete from Tm_App_Audit_Tab_Detail where TAB_CONFIG_ID = '43';

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('966', '43', 'listName', '机构名单名称', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 966);

insert into Tm_App_Audit_Tab_Detail (ID, TAB_CONFIG_ID, KEY, TITLE, TM_CREATETIME, TM_CREATER, TM_ORGID, TM_UPDATETIME, TM_UPDATER, TM_EFFECTFLAG, TM_FIELD1, TM_FIELD2, TM_FIELD3, ORDERBY)
values ('967', '43', 'rskAccessListDetailList', '[{"key":"listDetailName","title":"机构名称"},{"key":"listDetailId","title":"社会信用代码"},{"key":"operatePs","title":"操作方向"}]', sysdate, 'admin', '320099993', null, null, 'E', null, null, null, 967);
 
 
commit;

 
 
