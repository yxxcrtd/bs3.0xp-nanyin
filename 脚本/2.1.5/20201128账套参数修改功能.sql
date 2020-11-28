--add by yangwenjun 20201128 增加账套参数修改页面 start
DELETE FROM SYS_PAGE WHERE PAGE_ID='ValAssetBook006';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ValAssetBook006', 'VAL', '/valuation/accounadjustmanage/assetbook/ValAssetBook006', 'valuation/accounadjustmanage/assetbook/ValAssetBook006', null, null, null, '02', '01', '账套参数修改', 'system', null,sysdate, 'system',sysdate);
--add by yangwenjun 20201128 增加账套参数修改页面 end
--add by yangwenjun 20201128 增加账套参数修改按钮 start
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID='ValAssetBook001' AND ELEMENT_ID='ValAssetBook001_btn_modification';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('ValAssetBook001', 'ValAssetBook001_btn_modification', '01', '01', null, '账套参数修改', 'system', null,sysdate, 'system',sysdate, null);
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID='ValAssetBook006' AND ELEMENT_ID='ValAssetBook006_btn_submit';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('ValAssetBook006', 'ValAssetBook006_btn_submit', '01', '02', null, '提交', 'system', null,sysdate, 'system',sysdate, null);
--add by yangwenjun 20201128 增加账套参数修改按钮 end
--add by yangwenjun 20201128 删除账套参数修改页面的草稿箱功能 start
delete from SYS_TRASH_NOT_TARGET_PAGE where PAGE_ID='ValAssetBook006';
insert into SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO, PAGE_ID) values ('A6986B98373434A7E0538C5AA8C081D0','ValAssetBook006');
--add by yangwenjun 20201128 删除账套参数修改页面的草稿箱功能 end
--add by yangwenjun 20201128 增加账套参数修改提交的工作流页面配置 start
DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID='ValAssetBook006' AND ELEMENT_ID='ValAssetBook006_btn_submit';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('ValAssetBook006', 'ValAssetBook006_btn_submit', 'oneApproval', 'system', null,sysdate, 'system', sysdate, 'ALL', '02');
--add by yangwenjun 20201128 增加账套参数修改提交的工作流页面配置 end

