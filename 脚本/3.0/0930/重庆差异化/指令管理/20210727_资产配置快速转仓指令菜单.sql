DELETE FROM SYS_MENU WHERE MENU_ID='SETTLE_MANAGEMENT_001_001_003';
INSERT INTO SYS_MENU("MENU_ID", "SYSTEM_CODE", "ICON", "PARENT_MENU_ID", "PAGE_ID", "ORDER_NO", "IS_SPREAD", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('SETTLE_MANAGEMENT_001_001_003', 'ETR', NULL, 'SETTLE_MANAGEMENT_001_001', 'EtrAssetConfigTransfer001', '3', '02', '资产配置快速转仓指令', 'system', NULL, sysdate, 'system', sysdate);


DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('EtrAssetConfigTransfer001','EtrAssetConfigTransfer002');
INSERT INTO SYS_PAGE("PAGE_ID", "SYSTEM_CODE", "PAGE_URL", "PAGE_FILE", "MENU_ID", "HEIGHT", "WIDTH", "IS_HP_SELECT", "IS_NEED_WORKFLOW", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('EtrAssetConfigTransfer001', 'ETR', '/invest/entrust/etrastconfigtransfer/EtrAssetConfigTransfer001', 'invest/entrust/etrastconfigtransfer/EtrAssetConfigTransfer001', 'SETTLE_MANAGEMENT_001_001_003', NULL, NULL, '02', '01', '资产配置快速转仓指令', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE("PAGE_ID", "SYSTEM_CODE", "PAGE_URL", "PAGE_FILE", "MENU_ID", "HEIGHT", "WIDTH", "IS_HP_SELECT", "IS_NEED_WORKFLOW", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('EtrAssetConfigTransfer002', 'ETR', '/invest/entrust/etrastconfigtransfer/EtrAssetConfigTransfer002', 'invest/entrust/etrastconfigtransfer/EtrAssetConfigTransfer002', NULL, NULL, NULL, '02', '01', '资产配置快速转仓指令', 'system', NULL, sysdate, 'system', sysdate);

