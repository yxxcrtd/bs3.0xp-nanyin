/** add by yanya on 20201225 减值清理明细查询界面增加page**/
delete from sys_page where page_id = 'TrdAssetLossClear003';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdAssetLossClear003', 'TRD', '/invest/period/trdassetlossclear/TrdAssetLossClear003', 'invest/period/trdassetlossclear/TrdAssetLossClear003', 'ASSET_DURATION_MANAGEMENT_001_001_018', null, null, '02', '01', '资产减值清理', 'system', null, sysdate, 'system', sysdate);

/** add by yanya on 20201231 修复减值清理流程不能提交流程**/
delete from sys_wfl_business_module where page_id ='TrdAssetLossClear002' and ELEMENT_ID = 'TrdAssetLossClear001_btn_revoke';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdAssetLossClear002', 'TrdAssetLossClear001_btn_revoke', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE);
delete from sys_wfl_business_module where page_id ='TrdAssetLossClear003' and ELEMENT_ID = 'TrdAssetLossClear001_btn_revoke';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdAssetLossClear003', 'TrdAssetLossClear001_btn_revoke', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE);
