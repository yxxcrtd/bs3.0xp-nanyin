-- 菜单表数据
DELETE FROM SYS_MENU WHERE page_id = 'MstProjectCatInfo001';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BASICDATA_MANAGEMENT_001_002_010', 'MST', null, 'BASICDATA_MANAGEMENT_001_002', 'MstProjectCatInfo001', 10, '02', '权益类资产估值行情信息', 'system', 'system', sysdate, 'system', sysdate);


-- 系统代码默认按照ALL生成，请各自按照需要修改。另外，画面表的菜单id，请自行补充
delete from SYS_PAGE where page_id = 'MstProjectCatInfo001';
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
 values ('MstProjectCatInfo001','ALL','/invest/basicdata/projectcatinfo/MstProjectCatInfo001','invest/basicdata/projectcatinfo/MstProjectCatInfo001','BASICDATA_MANAGEMENT_001_002_010','','','02','01','权益类资产估值行情信息','system',null,sysdate,'system',sysdate);


-- 画面元素表
delete from SYS_PAGE_ELEMENT where page_id = 'MstProjectCatInfo001';
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('MstProjectCatInfo001','MstProjectCatInfo001_btn_add','01','01','','新建','system',null,sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('MstProjectCatInfo001','MstProjectCatInfo001_btn_delete','01','01','','删除','system',null,sysdate, 'system',sysdate, null);
-- 对于查询页面固定添加查询、导出、重置、表格编辑按钮
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('MstProjectCatInfo001','MstProjectCatInfo001_btn_search','01','01','','查询','system',null,sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('MstProjectCatInfo001','MstProjectCatInfo001_btn_export','01','01','','导出','system',null,sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('MstProjectCatInfo001','MstProjectCatInfo001_btn_clear','01','02','','重置','system',null,sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('MstProjectCatInfo001','MstProjectCatInfo001_TBLROW_edit','01','01','','编辑','system',null,sysdate, 'system',sysdate, null);


-- 业务流程配置表
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'MstProjectCatInfo001';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) 
 values ('MstProjectCatInfo001','MstProjectCatInfo001_btn_delete','oneApproval','system',null,sysdate, 'system',sysdate, 'ALL', '02');


-- 系统代码默认按照ALL生成，请各自按照需要修改。另外，画面表的菜单id，请自行补充
delete from SYS_PAGE where page_id = 'MstProjectCatInfo002';
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
 values ('MstProjectCatInfo002','ALL','/invest/basicdata/projectcatinfo/MstProjectCatInfo002','invest/basicdata/projectcatinfo/MstProjectCatInfo002','BASICDATA_MANAGEMENT_001_002_010','','','02','01','权益类资产估值行情信息','system',null,sysdate,'system',sysdate);


-- 画面元素表
delete from SYS_PAGE_ELEMENT where page_id = 'MstProjectCatInfo002';
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('MstProjectCatInfo002','MstProjectCatInfo002_btn_save','01','02','','保存','system',null,sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('MstProjectCatInfo002','MstProjectCatInfo002_btn_submit','01','02','','提交','system',null,sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('MstProjectCatInfo002','MstProjectCatInfo002_btn_cancel','01','02','','取消','system',null,sysdate, 'system',sysdate, null);
-- 对于查询页面固定添加查询、导出、重置、表格编辑按钮


-- 业务流程配置表
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'MstProjectCatInfo002';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) 
 values ('MstProjectCatInfo002','MstProjectCatInfo002_btn_save','oneApproval','system',null,sysdate, 'system',sysdate, 'ALL', '02');
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) 
 values ('MstProjectCatInfo002','MstProjectCatInfo002_btn_submit','oneApproval','system',null,sysdate, 'system',sysdate, 'ALL', '02');


--数据字典
delete from SYS_DATADICT_LIST where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'MST30';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES 
('COMMON_BUSINESS_TYPE','MST30','权益类资产估值行情信息',30,'0','system',NULL,TIMESTAMP '2020-09-11 15:42:12.000000','system',TIMESTAMP '2020-09-11 15:42:12.000000')
;