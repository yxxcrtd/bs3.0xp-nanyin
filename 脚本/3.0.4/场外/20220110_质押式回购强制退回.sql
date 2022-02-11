--质押式回购 强制退回
delete from SYS_PAGE where PAGE_ID  = 'RepoEntrustForcedFinish';
INSERT INTO SYS_PAGE (PAGE_ID ,SYSTEM_CODE ,PAGE_URL ,PAGE_FILE ,MENU_ID ,HEIGHT ,WIDTH ,IS_HP_SELECT ,IS_NEED_WORKFLOW ,REMARK ,CREATE_USER ,CREATE_DEPT ,CREATE_TIME ,UPDATE_USER ,UPDATE_TIME)
VALUES ('RepoEntrustForcedFinish','ETR','/cfets/bankuplink/repo/RepoEntrustForcedFinish','cfets/repo/RepoEntrustForcedFinish','','','','02','01','质押式回购指令强制完成','system','',sysdate,'system',sysdate);

DELETE FROM  SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID = 'RepoEntrustForcedFinish';
INSERT INTO SYS_WFL_BUSINESS_MODULE(PAGE_ID ,ELEMENT_ID ,MODULE_ID ,CREATE_USER ,CREATE_DEPT ,CREATE_TIME ,UPDATE_USER ,UPDATE_TIME ,INSTITUTION_CODE ,IS_SEND_UORG)
VALUES ('RepoEntrustForcedFinish','RepoEntrustForcedFinish_btn_submit','oneApproval','system','',sysdate,'system',sysdate,'ALL','02');