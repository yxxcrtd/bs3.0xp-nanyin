
-- ----------------------------
-- Table structure for SYS_DATADICT
-- ----------------------------
INSERT INTO SYS_DATADICT ("DICT_CODE", "SYSTEM_CODE", "DICT_CNNAME", "DICT_ENNAME", "UPDATE_FLG", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('CREDIT_OBJ', 'RSK', '授信对象', NULL, '03', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('CREDIT_OBJ', '01', '金额', '1', '0', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('CREDIT_OBJ', '02', '份额', '1', '0', 'system', NULL, sysdate, 'system', sysdate);
