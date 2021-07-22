-- ----------------------------
-- Table structure for RSK_ISUP_INDICATOR
-- ----------------------------
alter table RSK_ISUP_INDICATOR add IS_DISPLAY_ORIGINAL VARCHAR2(50 BYTE);
comment on column RSK_ISUP_INDICATOR.IS_DISPLAY_ORIGINAL is '是否显示原值';




-- ----------------------------
-- Table structure for SYS_DATADICT_LIST
-- ----------------------------
INSERT INTO SYS_DATADICT_LIST ("DICT_CODE", "ITEM_CODE", "ITEM_VALUE", "ORDER_NO", "DEFAULT_VALUE", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") 
VALUES ('IND_TYPE', 'LIMIT_SCALE', '投资规模类', '12', '0', 'system', NULL, SYSDATE, 'system', SYSDATE);

