--------------------------------基准汇率行情：新增字段【行情类型】 start--------------------------------
alter table MST_EXCHARGE_QUOTE add MARKET_TYPE VARCHAR2(50);
comment on column MST_EXCHARGE_QUOTE.MARKET_TYPE
  is '行情类型';
--------------------------------基准汇率行情：新增字段【行情类型】 end--------------------------------


--------------------------------基准汇率行情：新增字段【期限】 start--------------------------------
alter table MST_EXCHARGE_QUOTE add DEAD_LINE VARCHAR2(50);
comment on column MST_EXCHARGE_QUOTE.DEAD_LINE
  is '期限';
--------------------------------基准汇率行情：新增字段【期限】 end--------------------------------


/** 股票市场行情表结构调整，针对【成交金额】字段的精度问题。调整表结构时，请确保此表不被他人操作。add lby 李炳言 on 20210305 start **/
-- 添加临时列  用来存储 成交金额
alter table MST_SHARE_PRICE add tmp_deal_amount NUMBER(30,4);
--将目标字段中数据加入到临时列中
update MST_SHARE_PRICE set tmp_deal_amount = deal_amount ; 
--将目标字段数据清空
update MST_SHARE_PRICE set deal_amount = null; 
--更改目标字段类型
alter table MST_SHARE_PRICE modify deal_amount NUMBER(30,4);
--将临时列数据加回到目标字段中
update MST_SHARE_PRICE set deal_amount = tmp_deal_amount; 
--清除临时列
alter table MST_SHARE_PRICE drop column tmp_deal_amount; 
/** 股票市场行情表结构调整，针对【成交金额】字段的精度问题。调整表结构时，请确保此表不被他人操作。add lby 李炳言 on 20210305 end **/


--【外汇估值信息】增加主键【币种】
ALTER TABLE MST_VAL_FX DROP CONSTRAINT PK_MST_VAL_FX cascade drop index;
DROP INDEX PK_MST_VAL_FX;
ALTER TABLE MST_VAL_FX ADD CONSTRAINT PK_MST_VAL_FX PRIMARY KEY(FINPROD_ID2, DATASOURCE_TYPE, VAL_DATE, CCY);
