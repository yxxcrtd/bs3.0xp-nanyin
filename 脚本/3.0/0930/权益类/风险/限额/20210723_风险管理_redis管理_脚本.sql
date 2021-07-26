-- ----------------------------
-- Table structure for SYS_DATADICT_LIST
-- ----------------------------
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('REDIS_MODEL', 'RSK{INFO}ASSETAMOUNT', '资产实时规模信息', '13', '0', 'system', NULL, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('REDIS_MODEL', 'RSK{INDICATOR}TREND', '指标趋势信息', '14', '0', 'system', NULL, SYSDATE, 'system', SYSDATE);




-- ----------------------------
-- Table structure for SYS_PAGE
-- ----------------------------
INSERT INTO SYS_PAGE ("PAGE_ID", "SYSTEM_CODE", "PAGE_URL", "PAGE_FILE", "MENU_ID", "HEIGHT", "WIDTH", "IS_HP_SELECT", "IS_NEED_WORKFLOW", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('RskRedisParamConfig002', 'RSK', '/invest/riskredis/quota/redismgr/RskRedisParamConfig002', 'invest/riskredis/quota/redismgr/RskRedisParamConfig002', 'RISK_MANAGEMENT_001_004_009', NULL, NULL, '02', '02', 'Redis功能参数配置', 'system', NULL, SYSDATE, 'system', SYSDATE);




-- ----------------------------
-- Table structure for SYS_WFL_BUSINESS_MODULE
-- ----------------------------
INSERT INTO SYS_WFL_BUSINESS_MODULE ("PAGE_ID", "ELEMENT_ID", "MODULE_ID", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('RskRedisParamConfig002', 'RedisView001_btn_delete', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_WFL_BUSINESS_MODULE ("PAGE_ID", "ELEMENT_ID", "MODULE_ID", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('RskRedisParamConfig002', 'RskRedisParamConfig002_btn_save', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_WFL_BUSINESS_MODULE ("PAGE_ID", "ELEMENT_ID", "MODULE_ID", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('RskRedisParamConfig002', 'RskRedisParamConfig002_btn_submit', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);
