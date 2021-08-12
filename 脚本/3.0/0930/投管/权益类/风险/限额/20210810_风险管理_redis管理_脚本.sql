-- ----------------------------
-- Table structure for PA_FIN_PRODUCT
-- ----------------------------
alter table PA_FIN_PRODUCT add IS_CAN_DRAW_IN_ADVANCE VARCHAR2(50 BYTE) NULL;
comment on column PA_FIN_PRODUCT.IS_CAN_DRAW_IN_ADVANCE is '是否可提前支取';