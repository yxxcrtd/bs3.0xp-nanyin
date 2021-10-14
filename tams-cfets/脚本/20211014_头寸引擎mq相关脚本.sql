
--SELECT * FROM SYS_MQ_EXCHANGE sme ;
DELETE FROM SYS_MQ_EXCHANGE WHERE REMARK = 'postion';
INSERT INTO SYS_MQ_EXCHANGE(EXCHANGE_NAME, IS_DURABLE, EXCHANGE_TYPE, REMARK)VALUES('exchange.position.rule', '01', '02', 'postion');
INSERT INTO SYS_MQ_EXCHANGE(EXCHANGE_NAME, IS_DURABLE, EXCHANGE_TYPE, REMARK)VALUES('exchange.position.callback', '01', '02', 'postion');
INSERT INTO SYS_MQ_EXCHANGE(EXCHANGE_NAME, IS_DURABLE, EXCHANGE_TYPE, REMARK)VALUES('exchange.position.cancel', '01', '02', 'postion');
--select * from SYS_MQ_ROUTE smr WHERE REMARK ='postion';
DELETE FROM SYS_MQ_ROUTE WHERE REMARK ='postion';
INSERT INTO SYS_MQ_ROUTE(ROUTE_NAME, EXCHANGE_NAME, QUEUE_NAME, REMARK)VALUES('inv.etr.pos', 'exchange.position.rule', 'queue.position.rule', 'postion');
INSERT INTO SYS_MQ_ROUTE(ROUTE_NAME, EXCHANGE_NAME, QUEUE_NAME, REMARK)VALUES('inv.trd.pos', 'exchange.position.rule', 'queue.position.rule', 'postion');
INSERT INTO SYS_MQ_ROUTE(ROUTE_NAME, EXCHANGE_NAME, QUEUE_NAME, REMARK)VALUES('inv.*', 'exchange.position.callback', 'queue.position.callback', 'postion');
INSERT INTO SYS_MQ_ROUTE(ROUTE_NAME, EXCHANGE_NAME, QUEUE_NAME, REMARK)VALUES('inv.*', 'exchange.position.cancel', 'queue.position.cancel', 'postion');
--select * from SYS_MQ_QUEUE smq ;
DELETE FROM SYS_MQ_QUEUE WHERE REMARK = 'postion';
INSERT INTO SYS_MQ_QUEUE(QUEUE_NAME, IS_DURABLE, IS_AUTO_DELETE, IS_EXCLUSIVE, REMARK)VALUES('queue.position.rule', '01', '02', '02', 'postion');
INSERT INTO SYS_MQ_QUEUE(QUEUE_NAME, IS_DURABLE, IS_AUTO_DELETE, IS_EXCLUSIVE, REMARK)VALUES('queue.position.cancel', '01', '02', '02', 'postion');
INSERT INTO SYS_MQ_QUEUE(QUEUE_NAME, IS_DURABLE, IS_AUTO_DELETE, IS_EXCLUSIVE, REMARK)VALUES('queue.position.callback', '01', '02', '02', 'postion');
