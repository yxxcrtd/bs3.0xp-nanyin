-- ----------------------------
-- Table structure for RSK_EVENT_PARA_REL
-- ----------------------------
alter table RSK_EVENT_PARA_REL add FORMULA_VAL VARCHAR2(150 BYTE);
comment on column RSK_EVENT_PARA_REL.FORMULA_VAL is '公式';




-- ----------------------------
-- Table structure for SYS_DATADICT_LIST
-- ----------------------------
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'EVENT_REL_ITEM' AND ITEM_CODE = 'deadline';

INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('EVENT_REL_ITEM', 'allPrice', '全价', '5', '0', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('EVENT_REL_ITEM', 'mixAmount', '占比金额', '8', '0', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('EVENT_REL_ITEM', 'totalFaceAmt', '券面总额（元）', '9', '0', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('EVENT_REL_ITEM', 'oneDay', '1日', '10', '0', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('EVENT_REL_ITEM', 'formula', '公式', '11', NULL, 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('EVENT_REL_ITEM', 'relatedPartyAmt', '关联方机构授信额度', '12', '0', 'system', NULL, sysdate, 'system', sysdate);

