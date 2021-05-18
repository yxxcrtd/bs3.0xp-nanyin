/** 20201022_手工交收mq配置 add by yufei  **/

INSERT INTO SYS_MQ_EXCHANGE (EXCHANGE_NAME, IS_DURABLE, EXCHANGE_TYPE, REMARK) VALUES ('exchange.fanout.exchange.manualSettleTrade', '01', '02', null);

INSERT INTO SYS_MQ_QUEUE (QUEUE_NAME, IS_DURABLE, IS_AUTO_DELETE, IS_EXCLUSIVE, REMARK) VALUES ('queue.exchange.manualSettleTrade', '01', '02', '02', null);

INSERT INTO SYS_MQ_ROUTE (ROUTE_NAME, EXCHANGE_NAME, QUEUE_NAME, REMARK) VALUES ('exchange.manualSettleTrade.use', 'exchange.fanout.exchange.manualSettleTrade', 'queue.exchange.manualSettleTrade', null);
