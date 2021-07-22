-- ----------------------------
-- Table structure for SYS_DATADICT_LIST
-- ----------------------------
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('STORAGE_DISTIN', 'varietyCode', '期限品种', '4', '0', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('STORAGE_DISTIN', 'counterId', '交易对手', '5', '0', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('STORAGE_DISTIN', 'relatedParty', '社会统一信用代码', '6', '0', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('STORAGE_DISTIN', 'isRelatedPartyCeOrg', '关联方机构授信额度', '28', '0', 'system', NULL, sysdate, 'system', sysdate);




-- ----------------------------
-- Table structure for SYS_DATADICT
-- ----------------------------
INSERT INTO SYS_DATADICT ("DICT_CODE", "SYSTEM_CODE", "DICT_CNNAME", "DICT_ENNAME", "UPDATE_FLG", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('PARAM_MATH_TYPE', 'RSK', '参数计算方式', 'PARAM_MATH_TYPE', '02', 'system', NULL, sysdate, 'system', sysdate);




-- ----------------------------
-- Table structure for SYS_DATADICT_LIST
-- ----------------------------
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('PARAM_MATH_TYPE', 'REDIS', 'REDIS', '1', NULL, 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('PARAM_MATH_TYPE', 'DATABASE', '数据库', '2', NULL, 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('PARAM_MATH_TYPE', 'PLUGIN', '插件模板', '3', NULL, 'system', NULL, sysdate, 'system', sysdate);
