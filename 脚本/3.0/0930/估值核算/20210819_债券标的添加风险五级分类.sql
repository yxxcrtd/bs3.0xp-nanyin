--数据字典表添加风险五级分类字段
delete from SYS_DATADICT where DICT_CODE = 'BOND_RISK_TYPE';
INSERT INTO "SYS_DATADICT"("DICT_CODE", "SYSTEM_CODE", "DICT_CNNAME", "DICT_ENNAME", "UPDATE_FLG", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('BOND_RISK_TYPE', 'VAL', '风险五级分类', NULL, '02', 'system', NULL, sysdate, 'system', sysdate);
--数据字典明细表里添加关注、次级、可疑、损失、正常
delete from SYS_DATADICT_LIST where item_value = '关注' and dict_code = 'BOND_RISK_TYPE';
INSERT INTO "SYS_DATADICT_LIST"("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME", "INSTITUTION_CODE") VALUES ('BOND_RISK_TYPE', '001', '关注', '1', '0', 'system', NULL, sysdate, 'system', sysdate, NULL);
delete from SYS_DATADICT_LIST where item_value = '次级' and dict_code = 'BOND_RISK_TYPE';
INSERT INTO "SYS_DATADICT_LIST"("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME", "INSTITUTION_CODE") VALUES ('BOND_RISK_TYPE', '002', '次级', '2', '0', 'system', NULL, sysdate, 'system', sysdate, NULL);
delete from SYS_DATADICT_LIST where item_value = '可疑' and dict_code = 'BOND_RISK_TYPE';
INSERT INTO "SYS_DATADICT_LIST"("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME", "INSTITUTION_CODE") VALUES ('BOND_RISK_TYPE', '003', '可疑', '3', '0', 'system', NULL, sysdate, 'system', sysdate, NULL);
delete from SYS_DATADICT_LIST where item_value = '损失' and dict_code = 'BOND_RISK_TYPE';
INSERT INTO "SYS_DATADICT_LIST"("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME", "INSTITUTION_CODE") VALUES ('BOND_RISK_TYPE', '004', '损失', '4', '0', 'system', NULL, sysdate, 'system', sysdate, NULL);
delete from SYS_DATADICT_LIST where item_value = '正常' and dict_code = 'BOND_RISK_TYPE';
INSERT INTO "SYS_DATADICT_LIST"("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME", "INSTITUTION_CODE") VALUES ('BOND_RISK_TYPE', '005', '正常', '5', '0', 'system', NULL, sysdate, 'system', sysdate, NULL);
