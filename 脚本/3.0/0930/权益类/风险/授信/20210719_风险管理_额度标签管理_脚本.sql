-- ----------------------------
-- Table structure for SYS_MENU
-- ----------------------------
INSERT INTO SYS_MENU ("MENU_ID", "SYSTEM_CODE", "ICON", "PARENT_MENU_ID", "PAGE_ID", "ORDER_NO", "IS_SPREAD", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('RISK_MANAGEMENT_001_013', 'MST', NULL, 'RISK_MANAGEMENT_001', NULL, '13', '02', '信用风险额度管理', 'system', 'system', sysdate, 'system', sysdate);

INSERT INTO SYS_MENU ("MENU_ID", "SYSTEM_CODE", "ICON", "PARENT_MENU_ID", "PAGE_ID", "ORDER_NO", "IS_SPREAD", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('RISK_MANAGEMENT_001_013_012', 'MST', NULL, 'RISK_MANAGEMENT_001_013', 'CreditRule001', '12', '02', '额度校验规则管理', 'system', 'system', sysdate, 'system', sysdate);

INSERT INTO SYS_MENU ("MENU_ID", "SYSTEM_CODE", "ICON", "PARENT_MENU_ID", "PAGE_ID", "ORDER_NO", "IS_SPREAD", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('RISK_MANAGEMENT_001_013_001', 'MST', NULL, 'RISK_MANAGEMENT_001_013', 'CeLine001', '1', '02', '额度标签管理', 'system', 'system', sysdate, 'system', sysdate);



-- ----------------------------
-- Table structure for SYS_PAGE
-- ----------------------------
INSERT INTO SYS_PAGE ("PAGE_ID", "SYSTEM_CODE", "PAGE_URL", "PAGE_FILE", "MENU_ID", "HEIGHT", "WIDTH", "IS_HP_SELECT", "IS_NEED_WORKFLOW", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('CreditRule001', 'MST', '/invest/risk/creditmanagement/creditrulemanage/CreditRule001', 'invest/risk/creditmanagement/creditrulemanage/CreditRule001', 'RISK_MANAGEMENT_001_002_012', NULL, NULL, '02', '01', '额度校验规则查询', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_PAGE ("PAGE_ID", "SYSTEM_CODE", "PAGE_URL", "PAGE_FILE", "MENU_ID", "HEIGHT", "WIDTH", "IS_HP_SELECT", "IS_NEED_WORKFLOW", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('CeLine001', 'RSK', '/invest/risk/creditmanagement/creditlinemanage/CeLine001', 'invest/risk/creditmanagement/creditlinemanage/CeLine001', 'RISK_MANAGEMENT_001_002_009', NULL, NULL, '02', '01', '额度标签管理', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_PAGE ("PAGE_ID", "SYSTEM_CODE", "PAGE_URL", "PAGE_FILE", "MENU_ID", "HEIGHT", "WIDTH", "IS_HP_SELECT", "IS_NEED_WORKFLOW", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('CeLine002', 'RSK', '/invest/risk/creditmanagement/creditlinemanage/CeLine002', 'invest/risk/creditmanagement/creditlinemanage/CeLine002', 'RISK_MANAGEMENT_001_002_010', NULL, NULL, '02', '01', '额度标签新建', 'system', NULL, sysdate, 'system', sysdate);


-- ----------------------------
-- Table structure for SYS_DATADICT
-- ----------------------------
INSERT INTO SYS_DATADICT ("DICT_CODE", "SYSTEM_CODE", "DICT_CNNAME", "DICT_ENNAME", "UPDATE_FLG", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', 'RSK', '额度分类', NULL, '02', 'system', NULL, sysdate, 'system', sysdate);

-- ----------------------------
-- Table structure for SYS_DATADICT_LIST
-- ----------------------------
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', '01', '资金类额度', '1', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', '02', '债券投资类额度', '2', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', '03', '次级债务额度', '3', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', '04', '无固定期限资本补充工具', '4', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', '05', '信用债质押专项额度', '5', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', '06', '受益权', '6', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', '07', '交易类额度', '7', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', '08', '企业信用债投资额度', '8', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', '09', '北金所债权融资计划额度', '9', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', '10', '同业ABS投资限额', '10', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', '11', 'CB/EB专项额度', '11', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('LIMIT_TYPE', '12', '不扣限额', '12', '0', 'system', NULL, sysdate, 'system', sysdate);




-- ----------------------------
-- Table structure for SYS_DATADICT
-- ----------------------------
INSERT INTO SYS_DATADICT ("DICT_CODE", "SYSTEM_CODE", "DICT_CNNAME", "DICT_ENNAME", "UPDATE_FLG", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('REDUCE_RULE', 'RSK', '扣减规则', NULL, '02', 'system', NULL, sysdate, 'system', sysdate);

-- ----------------------------
-- Table structure for SYS_DATADICT_LIST
-- ----------------------------
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('REDUCE_RULE', '01', '名义本金', '1', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('REDUCE_RULE', '02', '净值型资产-金额', '2', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('REDUCE_RULE', '03', '净值型资产-份额', '3', '0', 'system', NULL, sysdate, 'system', sysdate);




-- ----------------------------
-- Table structure for SYS_DATADICT
-- ----------------------------
INSERT INTO SYS_DATADICT ("DICT_CODE", "SYSTEM_CODE", "DICT_CNNAME", "DICT_ENNAME", "UPDATE_FLG", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('TERM_UNIT', 'RSK', '期限种类', NULL, '02', 'system', NULL, sysdate, 'system', sysdate);

-- ----------------------------
-- Table structure for SYS_DATADICT_LIST
-- ----------------------------
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('TERM_UNIT', 'D', '日', '1', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('TERM_UNIT', 'M', '月', '3', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('TERM_UNIT', 'W', '周', '2', '0', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('TERM_UNIT', 'Y', '年', '4', '0', 'system', NULL, sysdate, 'system', sysdate);




-- ----------------------------
-- Table structure for SYS_WFL_BUSINESS_MODULE
-- ----------------------------
INSERT INTO SYS_WFL_BUSINESS_MODULE ("PAGE_ID", "ELEMENT_ID", "MODULE_ID", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('CeLine002', 'CeLine001_btn_delete', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate);

INSERT INTO SYS_WFL_BUSINESS_MODULE ("PAGE_ID", "ELEMENT_ID", "MODULE_ID", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('CeLine002', 'CeLine002_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate);

INSERT INTO SYS_WFL_BUSINESS_MODULE ("PAGE_ID", "ELEMENT_ID", "MODULE_ID", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('CeLine002', 'CeLine002_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate);
