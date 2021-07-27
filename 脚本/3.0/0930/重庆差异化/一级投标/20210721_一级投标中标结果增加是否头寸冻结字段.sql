--add by sunhui 20210721 中标结果维护，对应明细中，增加【是否头寸冻结】字段；：投标指令标位   增加是否头寸冻结字段 start
ALTER TABLE BID_ORDER_BID_PRICE DROP COLUMN IS_FROZEN_POSITION;
ALTER TABLE BID_ORDER_BID_PRICE ADD IS_FROZEN_POSITION VARCHAR2(2);
COMMENT ON COLUMN BID_ORDER_BID_PRICE.IS_FROZEN_POSITION IS '是否头寸冻结';
--add by sunhui 20210721 中标结果维护，对应明细中，增加【是否头寸冻结】字段；：投标指令标位   增加是否头寸冻结字段 end