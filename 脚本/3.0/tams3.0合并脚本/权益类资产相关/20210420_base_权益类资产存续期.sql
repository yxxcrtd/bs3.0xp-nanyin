--系统菜单表
delete from SYS_MENU where PAGE_ID in ('TrdProjectNetDuration001');
INSERT INTO SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES 
('ASSET_DURATION_MANAGEMENT_001_001_019','TRD',NULL,'ASSET_DURATION_MANAGEMENT_001_001','TrdProjectNetDuration001',11,'02','权益类资产存续期','system',NULL,TIMESTAMP '2020-08-27 16:46:33.000000','system',TIMESTAMP '2020-08-27 16:46:33.000000')
;


--------------------------------权益类资产存续期画面表------------------------------------------------------------
delete from SYS_PAGE where PAGE_ID in ('TrdProjectNetDuration001','TrdProjectNetDuration002');
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
 values ('TrdProjectNetDuration001','TRD','/invest/period/trdprojnetduration/TrdProjectNetDuration001','invest/period/trdprojnetduration/TrdProjectNetDuration001','ASSET_DURATION_MANAGEMENT_001_001_019','','','02','01','权益类资产存续期查询','system','',sysdate, 'system',sysdate);
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
 values ('TrdProjectNetDuration002','TRD','/invest/period/trdprojnetduration/TrdProjectNetDuration002','invest/period/trdprojnetduration/TrdProjectNetDuration002','','','','02','01','权益类资产现金分红','system','',sysdate, 'system',sysdate);


-------------------------------权益类存续期画面元素表 ----------------------------------------------------------------
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID in('TrdProjectNetDuration001','TrdProjectNetDuration002');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('TrdProjectNetDuration001','TrdProjectNetDuration001_btn_add','01','01','','新建','system','',sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('TrdProjectNetDuration001','TrdProjectNetDuration001_btn_revoke','01','01','','撤销','system','',sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('TrdProjectNetDuration001','TrdProjectNetDuration001_btn_search','01','01','','查询','system','',sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('TrdProjectNetDuration002','TrdProjectNetDuration002_btn_save','01','02','','保存','system','',sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('TrdProjectNetDuration002','TrdProjectNetDuration002_btn_submit','01','02','','提交','system','',sysdate, 'system',sysdate, null);


----------------------------流程模板 -------------------------------------------------------------------------------
DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID = 'TrdProjectNetDuration002';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) 
 values ('TrdProjectNetDuration002','TrdProjectNetDuration002_btn_save','oneApproval','system','0001',sysdate, 'system',sysdate, 'ALL', '02');
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) 
 values ('TrdProjectNetDuration002','TrdProjectNetDuration002_btn_submit','oneApproval','system','0001',sysdate, 'system',sysdate, 'ALL', '02');
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) 
 values ('TrdProjectNetDuration002','TrdProjectNetDuration001_btn_revoke','oneApproval','system','0001',sysdate, 'system',sysdate, 'ALL', '02');


-------------------------- 002 画面 限制交易方向 ------------------------------------------------------------------
DELETE FROM SYS_PAGE_DICTLIST WHERE PAGE_ID = 'TrdProjectNetDuration002' AND DICT_ID = 'BONUS_MODE';
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetDuration002','999999','BONUS_MODE','01','现金分红','','admin','',sysdate, 'admin',sysdate, null);


--数据字典
delete from SYS_DATADICT_LIST where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'TRD81';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES 
('COMMON_BUSINESS_TYPE','TRD81','权益类资产存续期',81,'0','system',NULL,sysdate,'system',sysdate)
;


--------------------------- 增加平安业务类型 -------------------------------------------------------------------------
delete from SYS_DATADICT_LIST where DICT_CODE = 'PINAN_BUSINESS_TYPE' AND ITEM_CODE = '162';
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
 values ('PINAN_BUSINESS_TYPE','162','权益类资产_现金分红','162','0','system','',sysdate, 'system',sysdate);

