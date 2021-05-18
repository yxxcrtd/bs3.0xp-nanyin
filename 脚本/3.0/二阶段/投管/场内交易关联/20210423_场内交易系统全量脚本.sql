------------------------------------------------------------------base-----------------------------------------------------------------------------------

-- MQ消息队列(重复执行用)
DROP TABLE  TABLE SYS_MQ_QUEUE;
CREATE TABLE SYS_MQ_QUEUE(
	QUEUE_NAME VARCHAR2(50) NOT NULL,
	IS_DURABLE VARCHAR2(50) NOT NULL,
	IS_AUTO_DELETE VARCHAR2(50) NOT NULL,
	IS_EXCLUSIVE VARCHAR2(50) NOT NULL,
	REMARK VARCHAR2(200),
	PRIMARY KEY (QUEUE_NAME)
);
COMMENT ON TABLE SYS_MQ_QUEUE IS 'MQ消息队列';
COMMENT ON COLUMN SYS_MQ_QUEUE.QUEUE_NAME IS '队列名称';
COMMENT ON COLUMN SYS_MQ_QUEUE.IS_DURABLE IS '是否持久化(是：01 否：02)';
COMMENT ON COLUMN SYS_MQ_QUEUE.IS_AUTO_DELETE IS '是否自动删除(是：01 否：02)';
COMMENT ON COLUMN SYS_MQ_QUEUE.IS_EXCLUSIVE IS '是否独占(是：01 否：02)';
COMMENT ON COLUMN SYS_MQ_QUEUE.REMARK IS '备注';
-- MQ消息交换机(重复执行用)
---DROP TABLE SYS_MQ_EXCHANGE;
CREATE TABLE SYS_MQ_EXCHANGE(
	EXCHANGE_NAME VARCHAR2(50) NOT NULL,
	IS_DURABLE VARCHAR2(50) NOT NULL,
	EXCHANGE_TYPE VARCHAR2(50) NOT NULL,
	REMARK VARCHAR2(200),
	PRIMARY KEY (EXCHANGE_NAME)
);
COMMENT ON TABLE SYS_MQ_EXCHANGE IS 'MQ消息交换机';
COMMENT ON COLUMN SYS_MQ_EXCHANGE.EXCHANGE_NAME IS '交换机名称';
COMMENT ON COLUMN SYS_MQ_EXCHANGE.IS_DURABLE IS '是否持久化(是：01 否：02)';
COMMENT ON COLUMN SYS_MQ_EXCHANGE.EXCHANGE_TYPE IS '交换机类型(01:Direct,02:Fanout,03:Topic,04:Headers)';
COMMENT ON COLUMN SYS_MQ_EXCHANGE.REMARK IS '备注';
-- MQ消息路由(重复执行用)
---DROP TABLE SYS_MQ_ROUTE;
CREATE TABLE SYS_MQ_ROUTE(
	ROUTE_NAME VARCHAR2(50) NOT NULL,
	EXCHANGE_NAME VARCHAR2(50) NOT NULL,
	QUEUE_NAME VARCHAR2(50) NOT NULL,
	REMARK VARCHAR2(200),
	PRIMARY KEY (ROUTE_NAME,EXCHANGE_NAME,QUEUE_NAME)
);
COMMENT ON TABLE SYS_MQ_ROUTE IS 'MQ消息路由';
COMMENT ON COLUMN SYS_MQ_ROUTE.ROUTE_NAME IS '路由名称';
COMMENT ON COLUMN SYS_MQ_ROUTE.EXCHANGE_NAME IS '交换机名称';
COMMENT ON COLUMN SYS_MQ_ROUTE.QUEUE_NAME IS '队列名称';
COMMENT ON COLUMN SYS_MQ_ROUTE.REMARK IS '备注';
-- MQ消息发送记录表(重复执行用)
---DROP TABLE SYS_MQ_SEND_LOG;
CREATE TABLE SYS_MQ_SEND_LOG(
	ID VARCHAR2(32) NOT NULL,
	SEND_TIME TIMESTAMP(6) NOT NULL,
	MSG VARCHAR2(4000) NOT NULL,
	ROUTE_NAME VARCHAR2(50) NOT NULL,
	SEND_STATUS VARCHAR2(50) NOT NULL,
	SEND_BEAN_NAME VARCHAR2(1000) NOT NULL,
	CREATE_USER VARCHAR2(20) NOT NULL,
	CREATE_DEPT VARCHAR2(32),
	CREATE_TIME TIMESTAMP(6) NOT NULL,
	UPDATE_USER VARCHAR2(20) NOT NULL,
	UPDATE_TIME TIMESTAMP(6) NOT NULL,
	PRIMARY KEY (ID)
);
COMMENT ON TABLE SYS_MQ_SEND_LOG IS 'MQ消息发送记录表';
COMMENT ON COLUMN SYS_MQ_SEND_LOG.ID IS 'ID';
COMMENT ON COLUMN SYS_MQ_SEND_LOG.SEND_TIME IS '发送时间';
COMMENT ON COLUMN SYS_MQ_SEND_LOG.MSG IS '消息体';
COMMENT ON COLUMN SYS_MQ_SEND_LOG.ROUTE_NAME IS '路由名称';
COMMENT ON COLUMN SYS_MQ_SEND_LOG.SEND_STATUS IS '发送状态';
COMMENT ON COLUMN SYS_MQ_SEND_LOG.SEND_BEAN_NAME IS '发送端BEAN名称';
COMMENT ON COLUMN SYS_MQ_SEND_LOG.CREATE_USER IS '创建人';
COMMENT ON COLUMN SYS_MQ_SEND_LOG.CREATE_DEPT IS '创建部门';
COMMENT ON COLUMN SYS_MQ_SEND_LOG.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN SYS_MQ_SEND_LOG.UPDATE_USER IS '更新人';
COMMENT ON COLUMN SYS_MQ_SEND_LOG.UPDATE_TIME IS '更新时间';
-- MQ消息消费记录表(重复执行用)
---DROP TABLE SYS_MQ_RECEIVE_LOG;
CREATE TABLE SYS_MQ_RECEIVE_LOG(
	ID VARCHAR2(32) NOT NULL,
	MSG_ID VARCHAR2(32) NOT NULL,
	RECEIVE_TIME TIMESTAMP(6) NOT NULL,
	RECEIVE_BEAN_NAME VARCHAR2(1000) NOT NULL,
    RECEIVE_STATUS VARCHAR2(50) NOT NULL,
	CREATE_USER VARCHAR2(20) NOT NULL,
	CREATE_DEPT VARCHAR2(32),
	CREATE_TIME TIMESTAMP(6) NOT NULL,
	UPDATE_USER VARCHAR2(20) NOT NULL,
	UPDATE_TIME TIMESTAMP(6) NOT NULL,
	PRIMARY KEY (ID)
);
COMMENT ON TABLE SYS_MQ_RECEIVE_LOG IS 'MQ消息消费记录表';
COMMENT ON COLUMN SYS_MQ_RECEIVE_LOG.ID IS 'ID';
COMMENT ON COLUMN SYS_MQ_RECEIVE_LOG.MSG_ID IS '消息ID';
COMMENT ON COLUMN SYS_MQ_RECEIVE_LOG.RECEIVE_TIME IS '消费时间';
COMMENT ON COLUMN SYS_MQ_RECEIVE_LOG.RECEIVE_BEAN_NAME IS '消费端BEAN名称';
COMMENT ON COLUMN SYS_MQ_RECEIVE_LOG.RECEIVE_STATUS IS '消费状态';
COMMENT ON COLUMN SYS_MQ_RECEIVE_LOG.CREATE_USER IS '创建人';
COMMENT ON COLUMN SYS_MQ_RECEIVE_LOG.CREATE_DEPT IS '创建部门';
COMMENT ON COLUMN SYS_MQ_RECEIVE_LOG.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN SYS_MQ_RECEIVE_LOG.UPDATE_USER IS '更新人';
COMMENT ON COLUMN SYS_MQ_RECEIVE_LOG.UPDATE_TIME IS '更新时间';


----MQ 添加交换机队列及路由

/* MQ 添加交换机 */
delete from SYS_MQ_EXCHANGE where EXCHANGE_NAME = 'exchange.fanout.exchange.trade';
insert into SYS_MQ_EXCHANGE values ('exchange.fanout.exchange.trade', '01', '02', '');

delete from SYS_MQ_EXCHANGE where EXCHANGE_NAME = 'exchange.direct.yitihua.instruction';
insert into SYS_MQ_EXCHANGE values ('exchange.direct.yitihua.instruction', '01', '01', '');

delete from SYS_MQ_EXCHANGE where EXCHANGE_NAME = 'exchange.fanout.exchange.progress';
insert into SYS_MQ_EXCHANGE values ('exchange.fanout.exchange.progress', '01', '02', '');

/* MQ 添加队列 */
delete from SYS_MQ_QUEUE where QUEUE_NAME = 'queue.exchange.trade';
insert into SYS_MQ_QUEUE values ('queue.exchange.trade', '01', '02', '02', '');

delete from SYS_MQ_QUEUE where QUEUE_NAME = 'queue.yitihua.instruction';
insert into SYS_MQ_QUEUE values ('queue.yitihua.instruction', '01', '02', '02', '');

delete from SYS_MQ_QUEUE where QUEUE_NAME = 'queue.exchange.progress';
insert into SYS_MQ_QUEUE values ('queue.exchange.progress', '01', '02', '02', '');

/* MQ 添加路由 */
delete from SYS_MQ_ROUTE where ROUTE_NAME = 'exchange.trade.use';
insert into SYS_MQ_ROUTE values ('exchange.trade.use', 'exchange.fanout.exchange.trade', 'queue.exchange.trade', '');

delete from SYS_MQ_ROUTE where ROUTE_NAME = 'yitihua.instruction.dispatch';
insert into SYS_MQ_ROUTE values ('yitihua.instruction.dispatch', 'exchange.direct.yitihua.instruction', 'queue.yitihua.instruction', '');

delete from SYS_MQ_ROUTE where ROUTE_NAME = 'exchange.progress.update';
insert into SYS_MQ_ROUTE values ('exchange.progress.update', 'exchange.fanout.exchange.progress', 'queue.exchange.progress', '');




----新增页面
/*【投资交易日常交易】增加页面【场内交易】*/
delete from SYS_PAGE where PAGE_ID = 'TrdExchange001';
insert into SYS_PAGE values ('TrdExchange001','TRD','/invest/trade/investtrade/exchangesystem/TrdExchange001','invest/trade/investtrade/exchangesystem/TrdExchange001','INVEST_MANAGEMENT_001_003_032','','','02','01','场内交易','system','',to_date('2020/04/30', 'yyyy/mm/dd'), 'system',to_date('2020/04/30', 'yyyy/mm/dd'));

/* 新增场内交易工作流明细 */
delete from sys_page where PAGE_ID = 'TrdExchange002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange002', 'TRD', '/invest/trade/investtrade/exchangesystem/TrdExchange002', 'invest/trade/investtrade/exchangesystem/TrdExchange002', null, null, null, '02', '01', '场内交易委托复核', 'system', null, to_date('2020/04/30', 'yyyy/mm/dd'), 'system', to_date('2020/04/30', 'yyyy/mm/dd'));

delete from sys_page where PAGE_ID = 'TrdExchange003';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange003', 'TRD', '/invest/trade/investtrade/exchangesystem/TrdExchange003', 'invest/trade/investtrade/exchangesystem/TrdExchange003', null, null, null, '02', '01', '场内交易综合委托复核', 'system', null,to_date('2020/04/30', 'yyyy/mm/dd'), 'system', to_date('2020/04/30', 'yyyy/mm/dd'));

delete from sys_page where PAGE_ID = 'TrdExchange004';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange004', 'TRD', '/invest/trade/investtrade/exchangesystem/TrdExchange004', 'invest/trade/investtrade/exchangesystem/TrdExchange004', null, null, null, '02', '01', '场内交易委托撤审', 'system', null, to_date('2020/04/30', 'yyyy/mm/dd'), 'system', to_date('2020/04/30', 'yyyy/mm/dd'));

delete from sys_page where PAGE_ID = 'TrdExchange005';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange005', 'TRD', '/invest/trade/investtrade/exchangesystem/TrdExchange005', 'invest/trade/investtrade/exchangesystem/TrdExchange005', null, null, null, '02', '01', '场内交易综合委托撤审', 'system', null,to_date('2020/04/30', 'yyyy/mm/dd'), 'system', to_date('2020/04/30', 'yyyy/mm/dd'));


/*【投资交易日常交易】增加页面【场内交易查询】*/
delete from SYS_PAGE where PAGE_ID = 'TrdExchange006';
insert into SYS_PAGE values ('TrdExchange006','TRD','/invest/trade/investtrade/exchangesystem/TrdExchange006','invest/trade/investtrade/exchangesystem/TrdExchange006','INVEST_MANAGEMENT_001_003_033','','','02','01','场内交易查询','system','',to_date('2020/04/30', 'yyyy/mm/dd'), 'system',to_date('2020/04/30', 'yyyy/mm/dd'));

/*【系统管理】增加页面【终端配置】*/
delete from SYS_PAGE where PAGE_ID = 'SysExchangeTerminal001';
insert into SYS_PAGE values ('SysExchangeTerminal001','SYS','/invest/trade/investtrade/exchangesystem/SysExchangeTerminal001','invest/trade/investtrade/exchangesystem/SysExchangeTerminal001','SYSTEM_001_014_001','','','02','01','终端配置','system','',to_date('2020/04/30', 'yyyy/mm/dd'), 'system',to_date('2020/04/30', 'yyyy/mm/dd'));

/*【系统管理】增加页面【内存数据监控】*/
delete from SYS_PAGE where PAGE_ID = 'SysExchangeMonitor001';
insert into SYS_PAGE values ('SysExchangeMonitor001','SYS','/invest/trade/investtrade/exchangesystem/SysExchangeMonitor001','invest/trade/investtrade/exchangesystem/SysExchangeMonitor001','SYSTEM_001_014_002','','','02','01','内存数据监控','system','',to_date('2020/04/30', 'yyyy/mm/dd'), 'system',to_date('2020/04/30', 'yyyy/mm/dd'));


/*【系统管理】增加页面【系统动态监控】*/
delete from SYS_PAGE where PAGE_ID = 'SysExchangeMonitor002';
insert into SYS_PAGE values ('SysExchangeMonitor002','SYS','/invest/trade/investtrade/exchangesystem/SysExchangeMonitor002','invest/trade/investtrade/exchangesystem/SysExchangeMonitor002','SYSTEM_001_014_003','','','02','01','系统动态监控','system','',to_date('2020/04/30', 'yyyy/mm/dd'), 'system',to_date('2020/04/30', 'yyyy/mm/dd'));

/*【投资交易日常交易】增加页面【异常委托处理】*/
delete from SYS_PAGE where PAGE_ID = 'TrdExchangeException001';
insert into SYS_PAGE values ('TrdExchangeException001','TRD','/invest/trade/investtrade/exchangesystem/TrdExchangeException001','invest/trade/investtrade/exchangesystem/TrdExchangeException001','INVEST_MANAGEMENT_001_003_038','','','02','01','异常委托处理','system','',to_date('2020/04/30', 'yyyy/mm/dd'), 'system',to_date('2020/04/30', 'yyyy/mm/dd'));



----新增菜单

/*【投资交易日常交易】增加菜单【场内交易】*/
delete from SYS_MENU where PAGE_ID = 'TrdExchange001';
insert into SYS_MENU values ('INVEST_MANAGEMENT_001_003_032','TRD','','INVEST_MANAGEMENT_001_003','TrdExchange001','12','02','场内交易','system','',to_date('2020/04/30', 'yyyy/mm/dd'),'system',to_date('2020/04/30', 'yyyy/mm/dd'));

  /*【投资交易日常交易】增加菜单【场内交易】*/
delete from SYS_MENU where PAGE_ID = 'TrdExchange006';
insert into SYS_MENU values ('INVEST_MANAGEMENT_001_003_033','TRD','','INVEST_MANAGEMENT_001_003','TrdExchange006','12','02','场内交易查询','system','',to_date('2020/04/30', 'yyyy/mm/dd'),'system',to_date('2020/04/30', 'yyyy/mm/dd'));

  /*【投资交易日常交易】增加菜单【场内交易】*/
delete from SYS_MENU where PAGE_ID = 'SysExchange001';
insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SYSTEM_001_014','SYS',null,'SYSTEM_001','SysExchange001',14,'02','场内交易管理','system',null,to_date('2020/04/30', 'yyyy/mm/dd'),'system',to_date('2020/04/30', 'yyyy/mm/dd'));

delete from SYS_MENU where PAGE_ID = 'SysExchangeTerminal001';
insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SYSTEM_001_014_001','SYS',null,'SYSTEM_001_014','SysExchangeTerminal001',1,'02','终端配置','system',null,to_date('2020/04/30', 'yyyy/mm/dd'),'system',to_date('2020/04/30', 'yyyy/mm/dd'));

/*【系统管理】增加菜单【内存数据监控】*/
delete from SYS_MENU where PAGE_ID = 'SysExchangeMonitor001';
insert into SYS_MENU values ('SYSTEM_001_014_002','SYS',null,'SYSTEM_001_014','SysExchangeMonitor001',2,'02','内存数据监控','system',null,to_date('2020/04/30', 'yyyy/mm/dd'),'system',to_date('2020/04/30', 'yyyy/mm/dd'));

/*【系统管理】增加菜单【系统动态监控】*/
delete from SYS_MENU where PAGE_ID = 'SYSTEM_001_014_003';
insert into SYS_MENU values ('SYSTEM_001_014_003','SYS',null,'SYSTEM_001_014','SysExchangeMonitor002',3,'02','系统动态监控','system',null,to_date('2020/04/30', 'yyyy/mm/dd'),'system',to_date('2020/04/30', 'yyyy/mm/dd'));


/*【投资交易日常交易】增加菜单【异常委托处理】*/
delete from SYS_MENU where PAGE_ID = 'TrdExchangeException001';
insert into SYS_MENU values ('INVEST_MANAGEMENT_001_003_038','TRD','','INVEST_MANAGEMENT_001_003','TrdExchangeException001','12','02','异常委托处理','system','',to_date('2020/04/30', 'yyyy/mm/dd'),'system',to_date('2020/04/30', 'yyyy/mm/dd'));



----新增 系统参数：是否启动公平交易开关
/* 增加公平交易参数 */
delete from sys_para  where SYSTEM_CODE='TRD' and PARA_CODE ='TRD_CHECK_FAIR';
insert into SYS_PARA values ('TRD', 'TRD_CHECK_FAIR', '01', '01', '', '是否启动公平交易开关 01：开；02：关', 'system', '', to_date('2020/04/30', 'yyyy/mm/dd'), 'admin', to_date('2020/04/30', 'yyyy/mm/dd'), null, '01');

/* 增加触发风险审批参数 */
delete from SYS_PARA  where SYSTEM_CODE='TRD' and PARA_CODE ='TRD_CHECK_RISK_APPROVAL';
insert into SYS_PARA values ('TRD', 'TRD_CHECK_RISK_APPROVAL', '01', '01', '', '是否触发风险审批开关 01：开；02：关', 'system', '', to_date('2020/04/30', 'yyyy/mm/dd'), 'admin', to_date('2020/04/30', 'yyyy/mm/dd'), null, '01');

/* 增加委托价格不优于指令价格时的处理模式参数 */

delete from SYS_PARA  where SYSTEM_CODE='TRD' and PARA_CODE ='TRD_FAIR_MODE_ENTRUST_PRICE_BEST_THAN_INSTRUCTION';
insert into SYS_PARA values ('TRD', 'TRD_FAIR_MODE_ENTRUST_PRICE_BEST_THAN_INSTRUCTION', '01', '01', '', '委托价格不优于指令价格时的公平处理模式 01：禁止；02：剔除并继续', 'system', '', to_date('2020/04/30', 'yyyy/mm/dd'), 'admin', to_date('2020/04/30', 'yyyy/mm/dd'), null, '01');

/* 增加未接收指令是否必须参与公平交易参数 */
delete from SYS_PARA  where SYSTEM_CODE='TRD' and PARA_CODE ='TRD_FAIR_MODE_WITH_UNACCEPT_INSTRUCTION';
insert into SYS_PARA values ('TRD', 'TRD_FAIR_MODE_WITH_UNACCEPT_INSTRUCTION', '01', '01', '', '未接收指令的公平处理模式 01：是；02：否', 'system', '', to_date('2020/04/30', 'yyyy/mm/dd'), 'admin', to_date('2020/04/30', 'yyyy/mm/dd'), null, '01');


----新增批处理任务
/* 场内交易清算 */
delete from SYS_BATCH_TASK  where TASK_ID='4001';
insert into SYS_BATCH_TASK values ('4001', 'TRD', '场内交易清算', null, 'exchangeSettleTask', 1, 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));


----新增工作流配置
/*场内交易工作流*/
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'TrdExchange002';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange002', 'TrdExchange002_btn_submit', 'oneApproval', 'system', null, to_date('2020/04/30', 'yyyy/mm/dd'), 'system', to_date('2020/04/30', 'yyyy/mm/dd'));

delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'TrdExchange003';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange003', 'TrdExchange003_btn_submit', 'oneApproval', 'system', null, to_date('2020/04/30', 'yyyy/mm/dd'), 'system', to_date('2020/04/30', 'yyyy/mm/dd'));

delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'TrdExchange004';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange004', 'TrdExchange004_btn_submit', 'oneApproval', 'system', null, to_date('2020/04/30', 'yyyy/mm/dd'), 'system', to_date('2020/04/30', 'yyyy/mm/dd'));

delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'TrdExchange005';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange005', 'TrdExchange005_btn_submit', 'oneApproval', 'system', null, to_date('2020/04/30', 'yyyy/mm/dd'), 'system', to_date('2020/04/30', 'yyyy/mm/dd'));



----新增字典和字典明细

/* 数据字典:交换机类型 */
delete from SYS_DATADICT where DICT_CODE = 'EXCHANGE_TYPE';
delete from SYS_DATADICT_LIST where DICT_CODE = 'EXCHANGE_TYPE';
insert into SYS_DATADICT values ('EXCHANGE_TYPE','SYS','交换机类型','EXCHANGE_TYPE','02','system','',to_date('2019/05/07', 'yyyy/mm/dd'), 'system',to_date('2019/05/07', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('EXCHANGE_TYPE','01','Direct','1','0','system','',to_date('2019/4/10', 'yyyy/mm/dd'), 'system',to_date('2019/4/10', 'yyyy/mm/dd'), null);
insert into SYS_DATADICT_LIST values ('EXCHANGE_TYPE','02','Fanout','2','0','system','',to_date('2019/4/10', 'yyyy/mm/dd'), 'system',to_date('2019/4/10', 'yyyy/mm/dd'), null);
insert into SYS_DATADICT_LIST values ('EXCHANGE_TYPE','03','Topic','3','0','system','',to_date('2019/4/10', 'yyyy/mm/dd'), 'system',to_date('2019/4/10', 'yyyy/mm/dd'), null);
insert into SYS_DATADICT_LIST values ('EXCHANGE_TYPE','04','Headers','4','0','system','',to_date('2019/4/10', 'yyyy/mm/dd'), 'system',to_date('2019/4/10', 'yyyy/mm/dd'), null);

/* 数据字典:新增场内交易业务类型 */
delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'EXC01';

select * from sys_datadict_list;
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'EXC01', '场内交易下单', 999, '0', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));

delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'EXC02';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'EXC02', '场内交易撤销', 998, '0', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));


----页面元素权限控制
/*场内交易页面元素*/
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'TrdExchange001' and ELEMENT_ID in ('btn-entrust-cancel','btn-order-cancel','quick-btn-order','quick-btn-receive','quick-btn-release');
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange001', 'btn-entrust-cancel', '01', '01', null, '撤委托', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange001', 'btn-order-cancel', '01', '01', null, '停止订单', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange001', 'quick-btn-order', '01', '01', null, '批量下单', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange001', 'quick-btn-receive', '01', '01', null, '批量接收', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchange001', 'quick-btn-release', '01', '01', null, '批量放弃', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));

/*场内交易异常委托处理页面元素*/
delete from sys_page_element where PAGE_ID = 'TrdExchangeException001' and ELEMENT_ID = 'quick-btn-cancel';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdExchangeException001', 'quick-btn-cancel', '01', '01', null, '批量撤销', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));


/*交易系统终端管理页面权限控制*/
delete from sys_page_element where PAGE_ID = 'SysExchangeTerminal001' and ELEMENT_ID = 'terminal-binding-btn';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysExchangeTerminal001', 'terminal-binding-btn', '01', '01', null, '终端绑定', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));

delete from sys_page_element where PAGE_ID = 'SysExchangeTerminal001' and ELEMENT_ID = 'terminal-create-btn';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysExchangeTerminal001', 'terminal-create-btn', '01', '01', null, '创建终端', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));

delete from sys_page_element where PAGE_ID = 'SysExchangeTerminal001' and ELEMENT_ID = 'terminal-remove-btn';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysExchangeTerminal001', 'terminal-remove-btn', '01', '01', null, '删除终端', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));

delete from sys_page_element where PAGE_ID = 'SysExchangeTerminal001' and ELEMENT_ID = 'risk-delete-btn';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysExchangeTerminal001', 'risk-delete-btn', '01', '01', null, '删除风控', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));

delete from sys_page_element where PAGE_ID = 'SysExchangeTerminal001' and ELEMENT_ID = 'risk-set-btn';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysExchangeTerminal001', 'risk-set-btn', '01', '01', null, '设置风控', 'system', null, to_date('2020/07/31', 'yyyy/mm/dd'), 'system', to_date('2020/07/31', 'yyyy/mm/dd'));


-------------------------------------------------------------------------mast-----------------------------------------------------------------------------


----新增业务表

/* 交易系统批量信息 */
drop table T_BATCHINFO;
create table T_BATCHINFO
(
  id             VARCHAR2(32) not null,
  order_id       VARCHAR2(32),
  price_mode     NUMBER(20),
  begin_price    NUMBER(20,4),
  price_offset   NUMBER(20,4),
  begin_volume   NUMBER(20),
  volume_offset  NUMBER(20),
  entrust_times  NUMBER(20),
  total_volume   NUMBER(20),
  order_volume   NUMBER(20),
  step_time      NUMBER(20),
  complete_times NUMBER(20),
  update_time    DATE,
  primary key (id)
);

/* 交易系统历史批量信息 */
drop table T_BATCHINFOHISTORY;
create table T_BATCHINFOHISTORY
(
  id             VARCHAR2(48) not null,
  batchinfo_id   VARCHAR2(32) not null,
  order_id       VARCHAR2(32),
  price_mode     NUMBER(20),
  begin_price    NUMBER(20,4),
  price_offset   NUMBER(20,4),
  begin_volume   NUMBER(20),
  volume_offset  NUMBER(20),
  entrust_times  NUMBER(20),
  total_volume   NUMBER(20),
  order_volume   NUMBER(20),
  step_time      NUMBER(20),
  complete_times NUMBER(20),
  update_time    DATE,
  batchinfo_day  VARCHAR2(32) not null,
  primary key (id)
);

/* 交易系统委托  */
drop table T_ENTRUST;
create table T_ENTRUST
(
  id                VARCHAR2(32) not null,
  parent_order_id   VARCHAR2(32),
  instruction_id    VARCHAR2(32) not null,
  portfolio_id      VARCHAR2(18) not null,
  user_id           VARCHAR2(18) not null,
  username          VARCHAR2(255) not null,
  account_id        VARCHAR2(32) not null,
  finance_account   VARCHAR2(32) not null,
  account_name      VARCHAR2(32) not null,
  asset_type        VARCHAR2(3) not null,
  market            VARCHAR2(8) not null,
  stock_code        VARCHAR2(32) not null,
  stock_name        VARCHAR2(32) default '' not null,
  entrust_type      VARCHAR2(1) not null,
  entrust_prop      VARCHAR2(4) not null,
  entrust_bs        VARCHAR2(1) not null,
  bs_flag           VARCHAR2(2) not null,
  entrust_volume    NUMBER(20) default 0 not null,
  entrust_price     NUMBER(20,4) default 0 not null,
  frozen_balance    NUMBER(20,4) default 0 not null,
  frozen_volume     NUMBER(20) default 0 not null,
  traded_balance    NUMBER(20,4) default 0 not null,
  traded_volume     NUMBER(20) default 0 not null,
  traded_price      NUMBER(20,4) default 0 not null,
  cancel_volume     NUMBER(20) default 0 not null,
  report_time       VARCHAR2(32),
  trade_time        VARCHAR2(32),
  cancel_time       VARCHAR2(32),
  error_code        NUMBER(8) default 0 not null,
  error_msg         VARCHAR2(128),
  trading_day       VARCHAR2(10) not null,
  local_entrust_id  VARCHAR2(32),
  broker_entrust_id VARCHAR2(32),
  ip_mac            VARCHAR2(64) default '',
  remark            VARCHAR2(64),
  create_time       DATE default sysdate not null,
  update_time       DATE default sysdate,
  entrust_status    NUMBER(6) default 0 not null,
  orig_entrust_id   VARCHAR2(32),
  full_price        NUMBER(20,4) default 0,
  report_no         VARCHAR2(32),
  operate_user      VARCHAR2(20),
  offset_flag       VARCHAR2(8),
  hedge_flag        VARCHAR2(8),
  entrust_balance   NUMBER(20,4) default 0 not null,
  primary key (id)
);
comment on column T_ENTRUST.operate_user
  is '操作人员';
comment on column T_ENTRUST.entrust_balance
  is '委托金额';

/* 交易系统历史委托 */
drop table T_ENTRUSTHISTORY;
create table T_ENTRUSTHISTORY
(
  id                VARCHAR2(48) not null,
  parent_order_id   VARCHAR2(32),
  instruction_id    VARCHAR2(32) not null,
  portfolio_id      VARCHAR2(18) not null,
  user_id           VARCHAR2(18) not null,
  username          VARCHAR2(255) not null,
  account_id        VARCHAR2(32) not null,
  finance_account   VARCHAR2(32) not null,
  account_name      VARCHAR2(32) not null,
  asset_type        VARCHAR2(3) not null,
  market            VARCHAR2(8) not null,
  stock_code        VARCHAR2(32) not null,
  stock_name        VARCHAR2(32) default '' not null,
  entrust_type      VARCHAR2(1) not null,
  entrust_prop      VARCHAR2(4) not null,
  entrust_bs        VARCHAR2(1) not null,
  bs_flag           VARCHAR2(2) not null,
  entrust_volume    NUMBER(20) default 0 not null,
  entrust_price     NUMBER(20,4) default 0 not null,
  frozen_balance    NUMBER(20,4) default 0 not null,
  frozen_volume     NUMBER(20) default 0 not null,
  traded_balance    NUMBER(20,4) default 0 not null,
  traded_volume     NUMBER(20) default 0 not null,
  traded_price      NUMBER(20,4) default 0 not null,
  cancel_volume     NUMBER(20) default 0 not null,
  report_time       VARCHAR2(32),
  trade_time        VARCHAR2(32),
  cancel_time       VARCHAR2(32),
  error_code        NUMBER(8) default 0 not null,
  error_msg         VARCHAR2(128),
  trading_day       VARCHAR2(10) not null,
  local_entrust_id  VARCHAR2(32),
  broker_entrust_id VARCHAR2(32),
  ip_mac            VARCHAR2(64) default '',
  remark            VARCHAR2(64),
  create_time       DATE default sysdate not null,
  update_time       DATE default sysdate,
  entrust_status    NUMBER(6) default 0 not null,
  orig_entrust_id   VARCHAR2(32),
  entrust_id        VARCHAR2(32) not null,
  full_price        NUMBER(20,4) default 0,
  report_no         VARCHAR2(32),
  operate_user      VARCHAR2(20),
  offset_flag       VARCHAR2(8),
  hedge_flag        VARCHAR2(8),
  primary key (id)
);

/* 交易系统综合委托 */
drop table T_INTEGRATED_ENTRUST;
create table T_INTEGRATED_ENTRUST
(
  id                VARCHAR2(32) not null,
  parent_order_id   VARCHAR2(32),
  instruction_id    VARCHAR2(32) not null,
  portfolio_id      VARCHAR2(32) not null,
  user_id           VARCHAR2(18) not null,
  username          VARCHAR2(255) not null,
  account_id        VARCHAR2(32) not null,
  finance_account   VARCHAR2(32) not null,
  account_name      VARCHAR2(32) not null,
  asset_type        VARCHAR2(3) not null,
  market            VARCHAR2(8) not null,
  stock_code        VARCHAR2(32) not null,
  stock_name        VARCHAR2(32) default '' not null,
  entrust_type      VARCHAR2(1) not null,
  entrust_prop      VARCHAR2(4) not null,
  entrust_bs        VARCHAR2(1) not null,
  bs_flag           VARCHAR2(2) not null,
  entrust_volume    NUMBER(20) default 0 not null,
  entrust_price     NUMBER(20,4) default 0 not null,
  entrust_balance   NUMBER(20,4) not null,
  discount          NUMBER(10,4) default 0.0000 not null,
  trans_code        VARCHAR2(32),
  expire_year_rate  NUMBER(10,4) default 0.000 not null,
  preterm_year_rate NUMBER(10,4) default 0.0000 not null,
  liaison_name      VARCHAR2(32),
  liaison_tel       VARCHAR2(32),
  prop_seat_no      VARCHAR2(16),
  prop_seat_name    VARCHAR2(32),
  seller_no         VARCHAR2(32),
  contract_id       VARCHAR2(32),
  first_settle_date VARCHAR2(8),
  back_date         VARCHAR2(8),
  compact_term      NUMBER(4) default 0 not null,
  back_balance      NUMBER(20,4) default 0.0000 not null,
  orig_serial_no    VARCHAR2(32),
  reduction_type    VARCHAR2(1) not null,
  frozen_balance    NUMBER(20,4) default 0 not null,
  frozen_volume     NUMBER(20) default 0 not null,
  traded_balance    NUMBER(20,4) default 0 not null,
  traded_volume     NUMBER(20) default 0 not null,
  traded_price      NUMBER(20,4) default 0 not null,
  cancel_volume     NUMBER(20) default 0 not null,
  report_time       VARCHAR2(32),
  trade_time        VARCHAR2(32),
  cancel_time       VARCHAR2(32),
  error_code        NUMBER(8) default 0 not null,
  error_msg         VARCHAR2(128),
  trading_day       VARCHAR2(10) not null,
  local_entrust_id  VARCHAR2(32),
  broker_entrust_id VARCHAR2(32),
  ip_mac            VARCHAR2(64) default '',
  remark            VARCHAR2(64),
  create_time       DATE default sysdate not null,
  update_time       DATE default sysdate,
  entrust_status    NUMBER(6) default 0 not null,
  orig_entrust_id   VARCHAR2(32),
  full_price        NUMBER(20,4) default 0,
  report_no         VARCHAR2(32),
  operate_user      VARCHAR2(20),
  primary key (id)
);

comment on column T_INTEGRATED_ENTRUST.entrust_balance is '综合业务中价格可能表示利率，资金不是由数量乘价格得来';
comment on column T_INTEGRATED_ENTRUST.operate_user is '操作人员';

/* 交易系统历史综合委托 */
drop table T_INTEGRATED_ENTRUSTHISTORY;
create table T_INTEGRATED_ENTRUSTHISTORY
(
  id                    VARCHAR2(48) not null,
  parent_order_id       VARCHAR2(32),
  instruction_id        VARCHAR2(32) not null,
  portfolio_id          VARCHAR2(32) not null,
  user_id               VARCHAR2(18) not null,
  username              VARCHAR2(255) not null,
  account_id            VARCHAR2(32) not null,
  finance_account       VARCHAR2(32) not null,
  account_name          VARCHAR2(32) not null,
  asset_type            VARCHAR2(3) not null,
  market                VARCHAR2(8) not null,
  stock_code            VARCHAR2(32) not null,
  stock_name            VARCHAR2(32) default '' not null,
  entrust_type          VARCHAR2(1) not null,
  entrust_prop          VARCHAR2(4) not null,
  entrust_bs            VARCHAR2(1) not null,
  bs_flag               VARCHAR2(2) not null,
  entrust_volume        NUMBER(20) default 0 not null,
  entrust_price         NUMBER(20,4) default 0 not null,
  entrust_balance       NUMBER(20,4) not null,
  discount              NUMBER(10,4) default 0.0000 not null,
  trans_code            VARCHAR2(32),
  expire_year_rate      NUMBER(10,4) default 0.000 not null,
  preterm_year_rate     NUMBER(10,4) default 0.0000 not null,
  liaison_name          VARCHAR2(32),
  liaison_tel           VARCHAR2(32),
  prop_seat_no          VARCHAR2(16),
  prop_seat_name        VARCHAR2(32),
  seller_no             VARCHAR2(32),
  contract_id           VARCHAR2(32),
  first_settle_date     VARCHAR2(32),
  back_date             VARCHAR2(8),
  compact_term          NUMBER(4) default 0 not null,
  back_balance          NUMBER(20,4) default 0.0000 not null,
  orig_serial_no        VARCHAR2(32),
  reduction_type        VARCHAR2(1) not null,
  frozen_balance        NUMBER(20,4) default 0 not null,
  frozen_volume         NUMBER(20) default 0 not null,
  traded_balance        NUMBER(20,4) default 0 not null,
  traded_volume         NUMBER(20) default 0 not null,
  traded_price          NUMBER(20,4) default 0 not null,
  cancel_volume         NUMBER(20) default 0 not null,
  report_time           VARCHAR2(32),
  trade_time            VARCHAR2(32),
  cancel_time           VARCHAR2(32),
  error_code            NUMBER(8) default 0 not null,
  error_msg             VARCHAR2(128),
  trading_day           VARCHAR2(10) not null,
  local_entrust_id      VARCHAR2(32),
  broker_entrust_id     VARCHAR2(32),
  ip_mac                VARCHAR2(64) default '',
  remark                VARCHAR2(64),
  create_time           DATE default sysdate not null,
  update_time           DATE default sysdate,
  entrust_status        NUMBER(6) default 0 not null,
  orig_entrust_id       VARCHAR2(32),
  integrated_entrust_id VARCHAR2(32) not null,
  full_price            NUMBER(20,4) default 0,
  report_no             VARCHAR2(32),
  operate_user          VARCHAR2(20),
  primary key (id)
);

comment on column T_INTEGRATED_ENTRUSTHISTORY.entrust_balance is '综合业务中价格可能表示利率，资金不是由数量乘价格得来';
comment on column T_INTEGRATED_ENTRUSTHISTORY.operate_user is '操作人员';

/* 交易系统订单 */
drop table T_ORDER;
create table T_ORDER
(
  id                VARCHAR2(32) not null,
  instruction_id    VARCHAR2(32) not null,
  portfolio_id      VARCHAR2(18) not null,
  user_id           VARCHAR2(18) not null,
  username          VARCHAR2(255) not null,
  account_id        VARCHAR2(32) not null,
  account_name      VARCHAR2(32),
  finance_account   VARCHAR2(32),
  asset_type        VARCHAR2(3) not null,
  algorithm_id      VARCHAR2(32),
  relative_order_id VARCHAR2(32),
  business_flag     VARCHAR2(1) not null,
  entrust_type      VARCHAR2(1) not null,
  entrust_prop      VARCHAR2(4) not null,
  entrust_bs        VARCHAR2(1) not null,
  bs_flag           VARCHAR2(2) not null,
  stock_code        VARCHAR2(32) default '' not null,
  stock_name        VARCHAR2(32) default '' not null,
  order_status      NUMBER(6) default 0 not null,
  order_volume      NUMBER(20) default 0 not null,
  order_price       NUMBER(20,4) default 0 not null,
  order_balance     NUMBER(20,4) default 0 not null,
  entrust_volume    NUMBER(20) default 0 not null,
  entrust_price     NUMBER(20,4) default 0 not null,
  entrust_balance   NUMBER(20,4) default 0 not null,
  frozen_balance    NUMBER(20,4) default 0 not null,
  frozen_volume     NUMBER(20) default 0 not null,
  traded_balance    NUMBER(20,4) default 0 not null,
  traded_volume     NUMBER(20) default 0 not null,
  traded_price      NUMBER(20,4) default 0 not null,
  cancel_volume     NUMBER(20) default 0 not null,
  error_code        NUMBER(8) default 0 not null,
  error_msg         VARCHAR2(128),
  trading_day       VARCHAR2(10) not null,
  ip_mac            VARCHAR2(64) default '',
  remark            VARCHAR2(64),
  create_time       DATE default sysdate not null,
  update_time       DATE default sysdate,
  full_price        NUMBER(20,4) default 0,
  offset_flag       VARCHAR2(8),
  hedge_flag        VARCHAR2(8),
  primary key (id)
);

/* 交易系统历史订单 */
drop table T_ORDERHISTORY;
create table T_ORDERHISTORY
(
  id                VARCHAR2(48) not null,
  instruction_id    VARCHAR2(32) not null,
  portfolio_id      VARCHAR2(18) not null,
  user_id           VARCHAR2(18) not null,
  username          VARCHAR2(255) not null,
  account_id        VARCHAR2(32) not null,
  account_name      VARCHAR2(32),
  finance_account   VARCHAR2(32),
  asset_type        VARCHAR(3) not null,
  algorithm_id      VARCHAR2(32),
  relative_order_id VARCHAR2(32),
  business_flag     VARCHAR(1) not null,
  entrust_type      VARCHAR(1) not null,
  entrust_prop      VARCHAR2(4) not null,
  entrust_bs        VARCHAR(1) not null,
  bs_flag           VARCHAR(2) not null,
  stock_code        VARCHAR2(32) default '' not null,
  stock_name        VARCHAR2(32) default '' not null,
  order_status      NUMBER(6) default 0 not null,
  order_volume      NUMBER(20) default 0 not null,
  order_price       NUMBER(20,4) default 0 not null,
  order_balance     NUMBER(20,4) default 0 not null,
  entrust_volume    NUMBER(20) default 0 not null,
  entrust_price     NUMBER(20,4) default 0 not null,
  frozen_balance    NUMBER(20,4) default 0 not null,
  frozen_volume     NUMBER(20) default 0 not null,
  traded_balance    NUMBER(20,4) default 0 not null,
  traded_volume     NUMBER(20) default 0 not null,
  traded_price      NUMBER(20,4) default 0 not null,
  cancel_volume     NUMBER(20) default 0 not null,
  error_code        NUMBER(8) default 0 not null,
  error_msg         VARCHAR2(128),
  trading_day       VARCHAR2(10) not null,
  ip_mac            VARCHAR2(64) default '',
  remark            VARCHAR2(64),
  create_time       DATE default sysdate not null,
  update_time       DATE default sysdate,
  order_id          VARCHAR2(32) not null,
  FULL_PRICE    NUMBER(20,4) default 0  null,
  primary key (id)
);

/* 交易系统成交 */
drop table T_TRADE;
create table T_TRADE
(
  id                VARCHAR2(32) not null,
  instruction_id    VARCHAR2(32) not null,
  parent_order_id   VARCHAR2(32),
  entrust_id        VARCHAR2(32) not null,
  account_id        VARCHAR2(32) not null,
  finance_account   VARCHAR2(32) not null,
  account_name      VARCHAR2(32) not null,
  market            VARCHAR2(8) not null,
  asset_type        VARCHAR2(3) not null,
  stock_code        VARCHAR2(32) not null,
  stock_name        VARCHAR2(32) default '' not null,
  business_flag     VARCHAR2(1) not null,
  entrust_type      VARCHAR2(1) not null,
  entrust_prop      VARCHAR2(4) not null,
  entrust_bs        VARCHAR2(1) not null,
  bs_flag           VARCHAR2(2) not null,
  trade_type        NUMBER(6) default 0 not null,
  traded_balance    NUMBER(20,4) default 0 not null,
  traded_volume     NUMBER(20) default 0 not null,
  traded_price      NUMBER(20,4) default 0 not null,
  clear_balance     NUMBER(20,4) default 0 not null,
  trade_time        VARCHAR2(32) not null,
  discount          NUMBER(10,4) default 0.0000 not null,
  trans_code        VARCHAR2(32),
  prop_seat_no      VARCHAR2(16),
  trading_day       VARCHAR2(10) not null,
  local_entrust_id  VARCHAR2(32),
  broker_entrust_id VARCHAR2(32) not null,
  broker_trade_id   VARCHAR2(32) default '' not null,
  create_time       DATE default sysdate not null,
  update_time       DATE default sysdate,
  report_no         VARCHAR2(32),
  offset_flag       VARCHAR2(8),
  hedge_flag        VARCHAR2(8),
  portfolio_id      VARCHAR2(18)
);

/* 交易系统历史成交 */
drop table T_TRADEHISTORY;
create table T_TRADEHISTORY
(
  id                VARCHAR2(48) not null,
  instruction_id    VARCHAR2(32) not null,
  parent_order_id   VARCHAR2(32),
  entrust_id        VARCHAR2(32) not null,
  account_id        VARCHAR2(32) not null,
  trade_id          VARCHAR2(32) not null,
  finance_account   VARCHAR2(32) not null,
  account_name      VARCHAR2(32) not null,
  market            VARCHAR2(8) not null,
  asset_type        VARCHAR2(3) not null,
  stock_code        VARCHAR2(32) not null,
  stock_name        VARCHAR2(32) default '' not null,
  business_flag     VARCHAR2(1) not null,
  entrust_type      VARCHAR2(1) not null,
  entrust_prop      VARCHAR2(4) not null,
  entrust_bs        VARCHAR2(1) not null,
  bs_flag           VARCHAR2(2) not null,
  trade_type        NUMBER(6) default 0 not null,
  traded_balance    NUMBER(20,4) default 0 not null,
  traded_volume     NUMBER(20) default 0 not null,
  traded_price      NUMBER(20,4) default 0 not null,
  clear_balance     NUMBER(20,4) default 0 not null,
  trade_time        VARCHAR2(32) not null,
  discount          NUMBER(10,4) default 0.0000 not null,
  trans_code        VARCHAR2(32),
  prop_seat_no      VARCHAR2(16),
  trading_day       VARCHAR2(10) not null,
  local_entrust_id  VARCHAR2(32),
  broker_entrust_id VARCHAR2(32) not null,
  broker_trade_id   VARCHAR2(32) default '' not null,
  create_time       DATE default sysdate not null,
  update_time       DATE default sysdate,
  report_no         VARCHAR2(32),
  offset_flag       VARCHAR2(8),
  hedge_flag        VARCHAR2(8),
  portfolio_id      VARCHAR2(18),
  primary key (id)
);

/*新增成交明细表*/
drop table INF_CONTRACT_DETAILFILE cascade constraints;
/*==============================================================*/
/* Table: INF_CONTRACT_DETAILFILE                               */
/*==============================================================*/
create table INF_CONTRACT_DETAILFILE 
(
   SEQNO                VARCHAR(32)          not null,
   ENTRUST_ID           VARCHAR(32),
   ORDER_ID             VARCHAR(32),
   ORDER_NO             VARCHAR(32),
   DEAL_NO              VARCHAR(32),
   PORTFOLIO_ID         VARCHAR(32),
   ACCT_NO              VARCHAR(50),
   BOND_ID              VARCHAR(50),
   FINPROD_ID           VARCHAR(50),
   PS                   VARCHAR(50),
   TRADE_MARKET         VARCHAR(50),
   TRADE_PLAT           VARCHAR(50),
   FINPROD_MARKET_ID    VARCHAR(50),
   SHARE_AMT            NUMERIC(30, 4),
   UNIT_CPRICE          NUMERIC(30, 14),
   UNIT_FPRICE          NUMERIC(30, 14),
   TRADE_AMT            NUMERIC(30, 14),
   TRADE_DATE           DATE,
   VDATE                DATE,
   MDATE                DATE,
   V_TRADE_ID           VARCHAR(32),
   CCY          VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   SCALE				VARCHAR(50),
   COUNTER_ID			VARCHAR2(64),
   SETTLE_DAY           DATE,
   BUSI_TYPE			VARCHAR(50),
   constraint PK_INF_CONTRACT_DETAILFILE primary key (SEQNO)
);

comment on table INF_CONTRACT_DETAILFILE is
'成交明细';

comment on column INF_CONTRACT_DETAILFILE.SEQNO is
'流水号';

comment on column INF_CONTRACT_DETAILFILE.ENTRUST_ID is
'委托代码，就是投管的指令';

comment on column INF_CONTRACT_DETAILFILE.ORDER_ID is
'订单代码';

comment on column INF_CONTRACT_DETAILFILE.ORDER_NO is
'交易委托编号，实际发到交易所的编号';

comment on column INF_CONTRACT_DETAILFILE.DEAL_NO is
'成交编号';

comment on column INF_CONTRACT_DETAILFILE.PORTFOLIO_ID is
'组合代码';

comment on column INF_CONTRACT_DETAILFILE.ACCT_NO is
'资金账号，券商资金账户';

comment on column INF_CONTRACT_DETAILFILE.BOND_ID is
'股东代码';

comment on column INF_CONTRACT_DETAILFILE.FINPROD_ID is
'金融产品代码';

comment on column INF_CONTRACT_DETAILFILE.PS is
'交易方向，买入、卖出等';

comment on column INF_CONTRACT_DETAILFILE.TRADE_MARKET is
'交易场所，银行间、上交所、深交所、柜面等';

comment on column INF_CONTRACT_DETAILFILE.TRADE_PLAT is
'交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等';

comment on column INF_CONTRACT_DETAILFILE.FINPROD_MARKET_ID is
'市场代码';

comment on column INF_CONTRACT_DETAILFILE.SHARE_AMT is
'成交数量，债券数量等';

comment on column INF_CONTRACT_DETAILFILE.UNIT_CPRICE is
'成交净价';

comment on column INF_CONTRACT_DETAILFILE.UNIT_FPRICE is
'成交全价';

comment on column INF_CONTRACT_DETAILFILE.TRADE_AMT is
'交易金额';

comment on column INF_CONTRACT_DETAILFILE.TRADE_DATE is
'交易日期';

comment on column INF_CONTRACT_DETAILFILE.VDATE is
'首次结算日';

comment on column INF_CONTRACT_DETAILFILE.MDATE is
'到期结算日';

comment on column INF_CONTRACT_DETAILFILE.V_TRADE_ID is
'首期交易编号';

comment on column INF_CONTRACT_DETAILFILE.CCY is 
'交易币种';

comment on column INF_CONTRACT_DETAILFILE.CREATE_USER is
'创建人';

comment on column INF_CONTRACT_DETAILFILE.CREATE_DEPT is
'创建部门';

comment on column INF_CONTRACT_DETAILFILE.CREATE_TIME is
'创建时间';

comment on column INF_CONTRACT_DETAILFILE.UPDATE_USER is
'更新人';

comment on column INF_CONTRACT_DETAILFILE.UPDATE_TIME is
'更新时间';

comment on column INF_CONTRACT_DETAILFILE.SCALE is
'折算比例';

comment on column INF_CONTRACT_DETAILFILE.COUNTER_ID is
'交易对手';

comment on column INF_CONTRACT_DETAILFILE.SETTLE_DAY is
'交收日';

comment on column INF_CONTRACT_DETAILFILE.BUSI_TYPE is
'业务类别,数据字典DELIVERY_BUS_TYPE;01 上交所固定收益平台债券买入;02 上交所固定收益平台债券卖出';


/* 营业部信息表 */
drop table MST_SALE_DEPARTMENT cascade constraints;
/*==============================================================*/
/* Table: MST_SALE_DEPARTMENT       add by lijie2 20200514      */
/*==============================================================*/
create table MST_SALE_DEPARTMENT
(
   SALE_DEPID           VARCHAR(32)          not null,
   SALE_DEPNAME         VARCHAR(50),
   BROKER_ID            VARCHAR(50),
   IP_ADDR              VARCHAR(50),
   PORT_NUM             VARCHAR(50),
   CONTACT_ADDR         VARCHAR(1000),
   CONTACT_TEL          VARCHAR(50),
   FAX_NO               VARCHAR(50),
   IP_ADDR2             VARCHAR(50),
   PORT_NUM2            VARCHAR(50),
   DECLARE_UNIT         VARCHAR(50),
   IS_NETRETURN         VARCHAR(50),
   HANDLE_TYPE          VARCHAR(50),
   INTER_TYPE           VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   SH_SEATNO            VARCHAR(32),
   SZ_SEATNO            VARCHAR(32),
   SERVERINFO           VARCHAR2(1024),
   ZJ_SEATNO            VARCHAR(32),
   SQ_SEATNO            VARCHAR(32),
   constraint PK_MST_SALE_DEPARTMENT primary key (SALE_DEPID)
);

comment on table MST_SALE_DEPARTMENT is
'营业部信息表';

comment on column MST_SALE_DEPARTMENT.SALE_DEPID is
'营业部代码';

comment on column MST_SALE_DEPARTMENT.SALE_DEPNAME is
'营业部名称';

comment on column MST_SALE_DEPARTMENT.BROKER_ID is
'所属券商';

comment on column MST_SALE_DEPARTMENT.IP_ADDR is
'IP地址';

comment on column MST_SALE_DEPARTMENT.PORT_NUM is
'端口';

comment on column MST_SALE_DEPARTMENT.CONTACT_ADDR is
'联系地址';

comment on column MST_SALE_DEPARTMENT.CONTACT_TEL is
'联系电话';

comment on column MST_SALE_DEPARTMENT.FAX_NO is
'传真号码';

comment on column MST_SALE_DEPARTMENT.IP_ADDR2 is
'备用IP地址';

comment on column MST_SALE_DEPARTMENT.PORT_NUM2 is
'备用端口';

comment on column MST_SALE_DEPARTMENT.DECLARE_UNIT is
'申报单位';

comment on column MST_SALE_DEPARTMENT.IS_NETRETURN is
'是否净价回报';

comment on column MST_SALE_DEPARTMENT.HANDLE_TYPE is
'特殊处理业务';

comment on column MST_SALE_DEPARTMENT.INTER_TYPE is
'柜台接口类型';

comment on column MST_SALE_DEPARTMENT.CREATE_USER is
'创建人';

comment on column MST_SALE_DEPARTMENT.CREATE_DEPT is
'创建部门';

comment on column MST_SALE_DEPARTMENT.CREATE_TIME is
'创建时间';

comment on column MST_SALE_DEPARTMENT.UPDATE_USER is
'更新人';

comment on column MST_SALE_DEPARTMENT.UPDATE_TIME is
'更新时间';

comment on column MST_SALE_DEPARTMENT.SH_SEATNO is
'上交所席位号';

comment on column MST_SALE_DEPARTMENT.SZ_SEATNO is
'深交所席位号';

comment on column MST_SALE_DEPARTMENT.SERVERINFO is 
'柜台参数，柜台系统连接串';

comment on column MST_SALE_DEPARTMENT.ZJ_SEATNO is
'中金所席位号';

comment on column MST_SALE_DEPARTMENT.SQ_SEATNO is
'上期所席位号';


/* 交易系统合约表 */
drop table T_INSTRUMENT;
create table T_INSTRUMENT
(
  inner_code         VARCHAR2(32) not null,
  market             VARCHAR2(8) not null,
  stock_code         VARCHAR2(32) not null,
  stock_name         VARCHAR2(32) not null,
  buy_unit           NUMBER(8) default 0,
  price_step         NUMBER(8,4) default 0.0000,
  store_unit         NUMBER(8) default 0 not null,
  up_price           NUMBER(12,4) default 0.0000,
  down_price         NUMBER(12,4) default 0.0000,
  high_volume        NUMBER(18) default 0,
  low_volume         NUMBER(8) default 0,
  asset_type         CHAR(3) not null,
  par_value          NUMBER(8,2) default 0.00,
  stbtrans_type      CHAR(1),
  stbtrans_status    CHAR(1),
  sub_stock_type     VARCHAR2(4),
  low_balance        NUMBER(20,4) default 0.0000,
  high_balance       NUMBER(20,4) default 0.0000,
  exdividend_flag    CHAR(1),
  bondinv_flag       CHAR(1),
  stkcode_status     CHAR(1),
  stbtrans_flag      CHAR(1),
  market_low_volume  NUMBER(8),
  market_high_volume NUMBER(8),
  update_time        DATE default sysdate,
  is_full_price      VARCHAR2(3),
  asset_type1        VARCHAR2(3),
  trad_plat          VARCHAR2(64),
  trm_found_type     VARCHAR2(8),
  etf_type           VARCHAR2(8),
  pur_red_id         VARCHAR2(50),
  pur_red_abbr       VARCHAR2(50)
);
-- Add comments to the columns 
comment on column T_INSTRUMENT.trm_found_type
  is '交易所基金类型';
comment on column T_INSTRUMENT.etf_type
  is 'ETF类型';
comment on column T_INSTRUMENT.pur_red_id
  is 'ETF申赎代码';
comment on column T_INSTRUMENT.pur_red_abbr
  is 'ETF申赎简称';


/* 交易系统服务节点 */
drop table T_SERVICE_NODE;
create table T_SERVICE_NODE
(
  node_name    VARCHAR2(255) not null,
  node_address VARCHAR2(255) not null,
  node_weight  NUMBER(2) default 0 not null,
  node_type    VARCHAR2(32) not null,
  primary key (node_name)
);
comment on column T_SERVICE_NODE.node_name is '自定义节点名称，各节点不同';
comment on column T_SERVICE_NODE.node_address is 'ip:port';
comment on column T_SERVICE_NODE.node_weight is '权重';
comment on column T_SERVICE_NODE.node_type is '区分交易、终端';

/* 交易系统终端 */
drop table T_TERMINAL;
create table T_TERMINAL
(
  terminal_name VARCHAR2(64) not null,
  terminal_type NUMBER(2) default 0 not null,
  create_time   DATE default sysdate,
  primary key (terminal_name)
);

/* 交易系统终端映射 */
drop table T_TERMINALMAPPING;
create table T_TERMINALMAPPING
(
  id              VARCHAR2(32) not null,
  account_id      VARCHAR2(32) not null,
  terminal_name   VARCHAR2(64) not null,
  terminal_weight NUMBER(2) default 0 not null,
  create_time     DATE default sysdate,
  primary key (id)
);


/* 交易系统指令表 */
            
drop TABLE T_INSTRUCTION;
CREATE TABLE T_INSTRUCTION (
  entrust_id          VARCHAR2(32) not null,
  portfolio_id        VARCHAR2(32),
  portfolio_name      VARCHAR2(200),
  finprod_id          VARCHAR2(50),
  finprod_abbr        VARCHAR2(200),
  finprod_name        VARCHAR2(200),
  finprod_type2       VARCHAR2(64),
  acct_no             VARCHAR2(50),
  ccy                 VARCHAR2(50),
  busi_acct_no        VARCHAR2(30),
  acct_name           VARCHAR2(200),
  sec_acct_id         VARCHAR2(32),
  sec_acct_name       VARCHAR2(200),
  stock_holder_no     VARCHAR2(32),
  trade_market        VARCHAR2(50),
  trade_plat          VARCHAR2(50),
  finprod_market_id   VARCHAR2(20),
  ps                  VARCHAR2(50),
  deal_mode           VARCHAR2(50),
  counter_id          VARCHAR2(32),
  counter_seat        VARCHAR2(10),
  counter_trader      VARCHAR2(32),
  counter_info        VARCHAR2(1000),
  counter_dealer_id   VARCHAR2(32),
  appointment_number  VARCHAR2(10),
  deal_no             VARCHAR2(50) default '',
  trd_match_id        VARCHAR2(50) default '',
  quoteref_id         VARCHAR2(10),
  con_share_id        VARCHAR2(50),
  con_price           NUMBER(30,14),
  con_num             NUMBER(30,4),
  trade_date          DATE,
  vdate               DATE,
  mdate               DATE,
  entrust_amt         NUMBER(30,2),
  entrust_num         NUMBER(30,4),
  entrust_status      VARCHAR2(50),
  entrust_price       NUMBER(30,14),
  full_price          NUMBER(30,15),
  price_mode          VARCHAR2(50),
  entrust_left_amt    NUMBER(30,4),
  frozen_quantity     NUMBER(30,4),
  settle_speed        NUMBER(10) default 0,
  value_date          DATE,
  value_date2         DATE,
  maturity_date       DATE,
  maturity_date2      DATE,
  term_days           NUMBER(10) default 0,
  term_days2          NUMBER(10),
  purch_type          VARCHAR2(50),
  sale_code           VARCHAR2(50),
  min_amt             NUMBER(30,14) default 0,
  tar_finprod_id      VARCHAR2(50),
  tarinprod_abbr      VARCHAR2(50),
  tarinprod_market_id VARCHAR2(20),
  trade_id            VARCHAR2(20),
  trader_name         VARCHAR2(200),
  inv_manager_id      VARCHAR2(20),
  asset_type          VARCHAR2(50),
  asset_type1         VARCHAR2(50),
  asset_type10        VARCHAR2(50),
  asset_type11        VARCHAR2(50),
  traders_info        CLOB,
  inv_aim             VARCHAR2(128),
  approver_flage      VARCHAR2(50),
  our_trader          VARCHAR2(32),
  is_auto_receive     VARCHAR2(1),
  v_pay_date          DATE,
  m_pay_date          DATE,
  cap_days            NUMBER(10) default 0,
  m_int_amt           NUMBER(30,14) default 0.0,
  m_trade_amt         NUMBER(30,14) default 0.0,
  first_repo_ps       VARCHAR2(50),
  remark              VARCHAR2(1000),
  counter_name        VARCHAR2(200),
  derivative_bs_dir   VARCHAR2(8),
  derivative_oc_flag  VARCHAR2(8),
  entrust_buss_type   VARCHAR2(50),
  delivery_method     VARCHAR2(50),
  PRIMARY KEY (ENTRUST_ID)
)
;
COMMENT ON TABLE T_INSTRUCTION IS '指令';
comment on column T_INSTRUCTION.entrust_id
  is '指令编号';
comment on column T_INSTRUCTION.portfolio_id
  is '组合代码编号';
comment on column T_INSTRUCTION.portfolio_name
  is '组合名称';
comment on column T_INSTRUCTION.finprod_id
  is '金融产品代码';
comment on column T_INSTRUCTION.finprod_abbr
  is '金融产品简称';
comment on column T_INSTRUCTION.finprod_name
  is '金融产品名称';
comment on column T_INSTRUCTION.finprod_type2
  is '金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层';
comment on column T_INSTRUCTION.acct_no
  is '网点账号';
comment on column T_INSTRUCTION.ccy
  is '币种';
comment on column T_INSTRUCTION.busi_acct_no
  is '资金账号';
comment on column T_INSTRUCTION.acct_name
  is '户名';
comment on column T_INSTRUCTION.sec_acct_id
  is '证券账户代码';
comment on column T_INSTRUCTION.sec_acct_name
  is '证券账户名称';
comment on column T_INSTRUCTION.stock_holder_no
  is '股东代码';
comment on column T_INSTRUCTION.trade_market
  is '交易场所';
comment on column T_INSTRUCTION.trade_plat
  is '交易平台';
comment on column T_INSTRUCTION.finprod_market_id
  is '市场代码';
comment on column T_INSTRUCTION.ps
  is '交易方向';
comment on column T_INSTRUCTION.deal_mode
  is '交易方式';
comment on column T_INSTRUCTION.counter_id
  is '交易对手';
comment on column T_INSTRUCTION.counter_seat
  is '对手席位';
comment on column T_INSTRUCTION.counter_trader
  is '对手方交易员';
comment on column T_INSTRUCTION.counter_info
  is '对手方联系方式';
comment on column T_INSTRUCTION.counter_dealer_id
  is '对手方交易商代码';
comment on column T_INSTRUCTION.appointment_number
  is '约定号';
comment on column T_INSTRUCTION.deal_no
  is '成交编号';
comment on column T_INSTRUCTION.trd_match_id
  is '初始交易编号';
comment on column T_INSTRUCTION.quoteref_id
  is '交易所请求号';
comment on column T_INSTRUCTION.con_share_id
  is '转股代码';
comment on column T_INSTRUCTION.con_price
  is '转股价格';
comment on column T_INSTRUCTION.con_num
  is '转股数量';
comment on column T_INSTRUCTION.trade_date
  is '委托日期';
comment on column T_INSTRUCTION.vdate
  is '委托开始日';
comment on column T_INSTRUCTION.mdate
  is '委托结束日';
comment on column T_INSTRUCTION.entrust_amt
  is '委托额度';
comment on column T_INSTRUCTION.entrust_num
  is '委托数量';
comment on column T_INSTRUCTION.entrust_status
  is '委托状态';
comment on column T_INSTRUCTION.entrust_price
  is '委托单价';
comment on column T_INSTRUCTION.full_price
  is '全价';
comment on column T_INSTRUCTION.price_mode
  is '限价模式';
comment on column T_INSTRUCTION.entrust_left_amt
  is '已完成数量 或者金额';
comment on column T_INSTRUCTION.frozen_quantity
  is '冻结数量或者金额';
comment on column T_INSTRUCTION.settle_speed
  is '清算速度';
comment on column T_INSTRUCTION.value_date
  is '起息日';
comment on column T_INSTRUCTION.value_date2
  is '起息日2';
comment on column T_INSTRUCTION.maturity_date
  is '到期日';
comment on column T_INSTRUCTION.maturity_date2
  is '到期日2';
comment on column T_INSTRUCTION.term_days
  is '期限天数';
comment on column T_INSTRUCTION.term_days2
  is '期限天数2';
comment on column T_INSTRUCTION.purch_type
  is '申购类型';
comment on column T_INSTRUCTION.sale_code
  is '配售代码';
comment on column T_INSTRUCTION.min_amt
  is '最低成交金额';
comment on column T_INSTRUCTION.tar_finprod_id
  is '目标金融产品代码';
comment on column T_INSTRUCTION.tarinprod_abbr
  is '目标金融产品简称';
comment on column T_INSTRUCTION.tarinprod_market_id
  is '目标金融产品市场代码';
comment on column T_INSTRUCTION.trade_id
  is '交易员编号';
comment on column T_INSTRUCTION.trader_name
  is '交易员姓名';
comment on column T_INSTRUCTION.inv_manager_id
  is '投资经理';
comment on column T_INSTRUCTION.asset_type
  is '资产类型';
comment on column T_INSTRUCTION.asset_type1
  is '资产二级分类';
comment on column T_INSTRUCTION.asset_type10
  is '资产三级分类';
comment on column T_INSTRUCTION.asset_type11
  is '资产四级分类';
comment on column T_INSTRUCTION.traders_info
  is '指令拥有的交易员JSON';
comment on column T_INSTRUCTION.inv_aim
  is '投资目的';
comment on column T_INSTRUCTION.approver_flage
  is '审批标识';
comment on column T_INSTRUCTION.our_trader
  is '本方交易员';
comment on column T_INSTRUCTION.is_auto_receive
  is '自动接收标志';
comment on column T_INSTRUCTION.v_pay_date
  is '首期结算日';
comment on column T_INSTRUCTION.m_pay_date
  is '到期结算日';
comment on column T_INSTRUCTION.cap_days
  is '占款天数';
comment on column T_INSTRUCTION.m_int_amt
  is '回购利息';
comment on column T_INSTRUCTION.m_trade_amt
  is '到期结算金额';
comment on column T_INSTRUCTION.first_repo_ps
  is '首期交易方向';
comment on column T_INSTRUCTION.remark
  is '指令备注';
comment on column T_INSTRUCTION.derivative_bs_dir
  is '买卖方向';
comment on column T_INSTRUCTION.derivative_oc_flag
  is '开平仓标志';
comment on column T_INSTRUCTION.entrust_buss_type
  is '指令业务类型';
comment on column T_INSTRUCTION.delivery_method
  is '交割方式';


/* 交易系统指令回购质押品表 */

drop TABLE T_INSTRUCTION_PLEDGE;
CREATE TABLE T_INSTRUCTION_PLEDGE (
  id                    VARCHAR2(64) not null,
  entrust_id            VARCHAR2(32) not null,
  finprod_id            VARCHAR2(50),
  finprod_abbr          VARCHAR2(200),
  trade_market          VARCHAR2(50),
  finprod_market_id     VARCHAR2(20),
  pledge_face_value     NUMBER(30,2) default 0,
  pledge_number         NUMBER(30,2) default 0,
  pledge_ratio          NUMBER(30,14) default 0,
  pledge_amt            NUMBER(30,2),
  old_finprod_id        VARCHAR2(50),
  old_finprod_abbr      VARCHAR2(200),
  old_trade_market      VARCHAR2(50),
  old_finprod_market_id VARCHAR2(20),
  old_pledge_face_value NUMBER(30,2),
  old_pledge_number     NUMBER(30,2),
  old_pledge_ratio      NUMBER(30,14),
  old_pledge_amt        NUMBER(30,2),
  used_pledge_number    NUMBER(30,2) default 0,
  PRIMARY KEY (ID)
);
comment on table T_INSTRUCTION_PLEDGE is '指令回购质押品';
COMMENT ON COLUMN T_INSTRUCTION_PLEDGE.ENTRUST_ID IS '指令编号';
COMMENT ON COLUMN T_INSTRUCTION_PLEDGE.FINPROD_ID IS '金融产品代码';
COMMENT ON COLUMN T_INSTRUCTION_PLEDGE.FINPROD_ABBR IS '金融产品简称';
COMMENT ON COLUMN T_INSTRUCTION_PLEDGE.TRADE_MARKET IS '交易场所';
COMMENT ON COLUMN T_INSTRUCTION_PLEDGE.FINPROD_MARKET_ID IS '市场代码';
COMMENT ON COLUMN T_INSTRUCTION_PLEDGE.PLEDGE_FACE_VALUE IS '质押面值';
COMMENT ON COLUMN T_INSTRUCTION_PLEDGE.PLEDGE_NUMBER IS '质押数量';
COMMENT ON COLUMN T_INSTRUCTION_PLEDGE.PLEDGE_RATIO IS '质押率';
COMMENT ON COLUMN T_INSTRUCTION_PLEDGE.PLEDGE_AMT IS '质押金额';
comment on column T_INSTRUCTION_PLEDGE.old_finprod_id is '原券金融产品代码';
comment on column T_INSTRUCTION_PLEDGE.old_finprod_abbr is '原金融产品简称';
comment on column T_INSTRUCTION_PLEDGE.old_trade_market is '原交易场所';
comment on column T_INSTRUCTION_PLEDGE.old_finprod_market_id is '原市场代码';
comment on column T_INSTRUCTION_PLEDGE.old_pledge_face_value is '原质押面值';
comment on column T_INSTRUCTION_PLEDGE.old_pledge_number is '原质押数量';
comment on column T_INSTRUCTION_PLEDGE.old_pledge_ratio is '原质押率';
comment on column T_INSTRUCTION_PLEDGE.old_pledge_amt is '原质押金额';
comment on column T_INSTRUCTION_PLEDGE.used_pledge_number is '已使用抵押券数量';

/*交易系统期货合约表*/
-- Create table
drop table T_FUTURES_INSTRUMENT;
create table T_FUTURES_INSTRUMENT
(
  inner_code                 VARCHAR2(32) not null,
  instrument_id              VARCHAR2(32),
  exchange_id                VARCHAR2(8),
  instrument_name            VARCHAR2(32),
  exchange_inst_id           VARCHAR2(32),
  product_id                 VARCHAR2(32),
  product_class              VARCHAR2(8),
  delivery_year              NUMBER(8),
  delivery_month             NUMBER(8),
  max_market_order_volume    NUMBER(8),
  min_market_order_volume    NUMBER(8),
  max_limit_order_volume     NUMBER(8),
  min_limit_order_volume     NUMBER(8),
  volume_multiple            NUMBER(8),
  price_tick                 NUMBER(12,4),
  create_date                VARCHAR2(10),
  open_date                  VARCHAR2(10),
  expire_date                VARCHAR2(10),
  start_deliv_date           VARCHAR2(10),
  end_deliv_date             VARCHAR2(10),
  inst_life_phase            VARCHAR2(8),
  is_trading                 NUMBER(1),
  position_type              VARCHAR2(8),
  position_date_type         VARCHAR2(8),
  long_margin_ratio          NUMBER(12,4),
  short_margin_ratio         NUMBER(12,4),
  max_margin_s_ide_algorithm VARCHAR2(8),
  underlying_instr_id        VARCHAR2(32),
  strike_price               NUMBER(30,4),
  options_type               VARCHAR2(8),
  underlying_multiple        NUMBER(12,4),
  combination_type           VARCHAR2(8),
  buy_unit                   NUMBER(8),
  create_time                DATE default sysdate not null,
  update_time                DATE default sysdate,
  primary key (INNER_CODE)
);
-- Add comments to the columns 
comment on column T_FUTURES_INSTRUMENT.inner_code
  is '内部合约代码';
comment on column T_FUTURES_INSTRUMENT.instrument_id
  is '合约代码';
comment on column T_FUTURES_INSTRUMENT.exchange_id
  is '交易所代码';
comment on column T_FUTURES_INSTRUMENT.instrument_name
  is '合约名称';
comment on column T_FUTURES_INSTRUMENT.exchange_inst_id
  is '合约在交易所的代码';
comment on column T_FUTURES_INSTRUMENT.product_id
  is '产品代码';
comment on column T_FUTURES_INSTRUMENT.product_class
  is '产品类型';
comment on column T_FUTURES_INSTRUMENT.delivery_year
  is '交割年份';
comment on column T_FUTURES_INSTRUMENT.delivery_month
  is '交割月';
comment on column T_FUTURES_INSTRUMENT.max_market_order_volume
  is '市价单最大下单量';
comment on column T_FUTURES_INSTRUMENT.min_market_order_volume
  is '市价单最小下单量';
comment on column T_FUTURES_INSTRUMENT.max_limit_order_volume
  is '限价单最大下单量';
comment on column T_FUTURES_INSTRUMENT.min_limit_order_volume
  is '限价单最小下单量';
comment on column T_FUTURES_INSTRUMENT.volume_multiple
  is '合约数量乘数';
comment on column T_FUTURES_INSTRUMENT.price_tick
  is '最小变动价位';
comment on column T_FUTURES_INSTRUMENT.create_date
  is '创建日';
comment on column T_FUTURES_INSTRUMENT.open_date
  is '上市日';
comment on column T_FUTURES_INSTRUMENT.expire_date
  is '到期日';
comment on column T_FUTURES_INSTRUMENT.start_deliv_date
  is '开始交割日';
comment on column T_FUTURES_INSTRUMENT.end_deliv_date
  is '结束交割日';
comment on column T_FUTURES_INSTRUMENT.inst_life_phase
  is '合约生命周期状态';
comment on column T_FUTURES_INSTRUMENT.is_trading
  is '当前是否交易';
comment on column T_FUTURES_INSTRUMENT.position_type
  is '持仓类型';
comment on column T_FUTURES_INSTRUMENT.position_date_type
  is '持仓日期类型';
comment on column T_FUTURES_INSTRUMENT.long_margin_ratio
  is '多头保证金率';
comment on column T_FUTURES_INSTRUMENT.short_margin_ratio
  is '空头保证金率';
comment on column T_FUTURES_INSTRUMENT.max_margin_s_ide_algorithm
  is '是否使用大额单边保证金算法';
comment on column T_FUTURES_INSTRUMENT.underlying_instr_id
  is '基础商品代码';
comment on column T_FUTURES_INSTRUMENT.strike_price
  is '执行价';
comment on column T_FUTURES_INSTRUMENT.options_type
  is '期权类型';
comment on column T_FUTURES_INSTRUMENT.underlying_multiple
  is '合约基础商品乘数';
comment on column T_FUTURES_INSTRUMENT.combination_type
  is '组合类型';
comment on column T_FUTURES_INSTRUMENT.buy_unit
  is '最小交易单位';

/* 交易系统动态监控 */
drop table T_MONITOR;
create table T_MONITOR
(
  id           VARCHAR2(32) not null,
  record_time  VARCHAR2(32) not null,
  record_name  VARCHAR2(32) not null,
  record_value NUMBER(8) not null,
  create_time  DATE default sysdate not null,
  primary key (id)
);

comment on column T_MONITOR.record_time is '监控时间，记录本次统计任务发起时间，记录到秒，格式直接转换为HH:mm:ss';
comment on column T_MONITOR.record_name is '监控指标，监控指标的中文名称';
comment on column T_MONITOR.record_value is '监控值';
comment on column T_MONITOR.create_time is '监控值生成的时间戳，精确到毫秒';

/* 交易系统历史动态监控 */
drop table T_MONITORHISTORY;
create table T_MONITORHISTORY
(
  id                VARCHAR2(48) not null,
  record_id       VARCHAR2(32) not null,
  record_time       VARCHAR2(32) not null,
  record_name       VARCHAR2(32) not null,
  record_value      NUMBER(8) not null,
  create_time       DATE default sysdate not null,
  primary key (id)
);

comment on column T_MONITORHISTORY.record_id is '监控记录编号';
comment on column T_MONITORHISTORY.record_time is '监控时间，记录本次统计任务发起时间，记录到秒，格式直接转换为HH:mm:ss';
comment on column T_MONITORHISTORY.record_name is '监控指标，监控指标的中文名称';
comment on column T_MONITORHISTORY.record_value is '监控值';
comment on column T_MONITORHISTORY.create_time is '监控值生成的时间戳，精确到毫秒';


/* 交易系统动态监控指标表格映射 */
drop table T_MONITORCATEGORY;
create table T_MONITORCATEGORY
(
  id                VARCHAR2(48) not null,
  record_name       VARCHAR2(32) not null,
  category_name     VARCHAR2(32) not null,
  create_time       DATE default sysdate not null,
  primary key (id)
);

comment on column T_MONITORCATEGORY.record_name is '监控指标';
comment on column T_MONITORCATEGORY.category_name is '表格名称';
comment on column T_MONITORCATEGORY.create_time is '创建时间';

/* 交易系统持仓表 */
drop TABLE "T_POSITION";
CREATE TABLE "T_POSITION" (
  id          VARCHAR2(32 BYTE)  not null,
  account_id      VARCHAR2(32 BYTE) not null,
  finance_account     VARCHAR2(32 BYTE),
  market        VARCHAR2(8 BYTE),
  stock_code      VARCHAR2(32 BYTE) not null,
  stock_name      VARCHAR2(64 BYTE),
  hold_volume     NUMBER(20,0) default 0,
  enable_volume     NUMBER(20,0) default 0,
  sell_frozen_volume  NUMBER(20,0) default 0,
  today_volume      NUMBER(20,0) default 0,
  create_time       DATE default sysdate,
  update_time       DATE default sysdate,
  primary key (id)
);

/*风控参数表*/
drop TABLE "T_ACCOUNT_RISK_PARAMETER";
CREATE TABLE "T_ACCOUNT_RISK_PARAMETER" (
  account_id   VARCHAR2(32 BYTE)  not null,
  flow       NUMBER(20,0)  default 0  not null,
  total_count  NUMBER(20,0)  default 0  not null,
  cancel_ratio   NUMBER(3,2)  default 0  not null,
  self_trade   CHAR(1 BYTE)  not null,
  create_time    DATE  default sysdate not null,
  update_time  DATE  default sysdate,
  primary key (account_id)
);
COMMENT ON COLUMN "T_ACCOUNT_RISK_PARAMETER"."ACCOUNT_ID" IS '账户编号';
COMMENT ON COLUMN "T_ACCOUNT_RISK_PARAMETER"."FLOW" IS '下单流量';
COMMENT ON COLUMN "T_ACCOUNT_RISK_PARAMETER"."TOTAL_COUNT" IS '委托总次数';
COMMENT ON COLUMN "T_ACCOUNT_RISK_PARAMETER"."CANCEL_RATIO" IS '撤单比列';
COMMENT ON COLUMN "T_ACCOUNT_RISK_PARAMETER"."SELF_TRADE" IS '自成交开关 1：开启 0：关闭';

/* 交易系统行情bar线表*/
create table T_MD_BAR
(
    instrument_id   VARCHAR2(32) not null,
    bar_type        NUMBER(8) not null,
    str_time        VARCHAR2(64) not null,
    open_price      NUMBER(20,4) not null,
    high_price      NUMBER(20,4) not null,
    low_price       NUMBER(20,4) not null,
    close_price     NUMBER(20,4) not null,
    volume          NUMBER(20) not null,
    turnover        NUMBER(20) not null,
    pre_close_price NUMBER(20,4) not null,
    position        NUMBER(20) not null,
    update_time     DATE default sysdate,
    trading_day     VARCHAR2(16) default '' not null
);

comment on column T_MD_BAR.position is '持仓量，期货、期权用';

/*交易系统算法表*/
drop TABLE "T_ALGORITHM";
CREATE TABLE "T_ALGORITHM"
(
    id               VARCHAR2(32)                   not null,
    algorithm_name   VARCHAR2(32)                   not null,
    begin_time       DATE                           not null,
    end_time         DATE                           not null,
    algorithm_status VARCHAR2(1)                    not null,
    parameter        VARCHAR2(1024) default '',
    stock_code       VARCHAR2(32)                   not null,
    volume           NUMBER(10, 0)                  not null,
    entrust_bs       VARCHAR2(1)                    not null,
    offset_flag      VARCHAR2(1)                    not null,
    limit_action     NUMBER(8, 0)                   not null,
    after_action     NUMBER(8, 0)                   not null,
    traded_volume    NUMBER(10, 0)                  not null,
    external_id      VARCHAR2(32),
    instruction_id   VARCHAR2(32)                   not null,
    error_code       NUMBER(8, 0)                   not null,
    error_msg        VARCHAR2(64),
    create_time      DATE           default sysdate not null,
    update_time      DATE           default sysdate not null,
    trading_day      VARCHAR2(10)                   not null,
    primary key (id)
);

COMMENT ON COLUMN "T_ALGORITHM"."VOLUME" IS '总数量';

COMMENT ON COLUMN "T_ALGORITHM"."ENTRUST_BS" IS '买卖标志，同委托';

COMMENT ON COLUMN "T_ALGORITHM"."OFFSET_FLAG" IS '开平仓标志，同委托';

COMMENT ON COLUMN "T_ALGORITHM"."LIMIT_ACTION" IS '买入若出现跌停时的操作，或者卖出出现涨停时的操作';

COMMENT ON COLUMN "T_ALGORITHM"."AFTER_ACTION" IS '超过时间后是撤单还是继续交易';

COMMENT ON COLUMN "T_ALGORITHM"."TRADED_VOLUME" IS '已成交数量';

COMMENT ON COLUMN "T_ALGORITHM"."EXTERNAL_ID" IS '外部ID，外接算法中心时用';

/*交易系统算法历史表*/
drop TABLE "T_ALGORITHMHISTORY";
CREATE TABLE "T_ALGORITHMHISTORY"
(
    id               VARCHAR2(32)                   not null,
    algorithm_id     VARCHAR2(32)                   not null,
    algorithm_name   VARCHAR2(32)                   not null,
    begin_time       DATE                           not null,
    end_time         DATE                           not null,
    algorithm_statUS VARCHAR2(1)                    not null,
    parameter        VARCHAR2(1024) default '',
    stock_code       VARCHAR2(32)                   not null,
    volume           NUMBER(10, 0)                  not null,
    entrust_bs       VARCHAR2(1)                    not null,
    offset_flag      VARCHAR2(1)                    not null,
    limit_action     NUMBER(8, 0)                   not null,
    after_action     NUMBER(8, 0)                   not null,
    traded_volume    NUMBER(10, 0)                  not null,
    external_id      VARCHAR2(32),
    instruction_id   VARCHAR2(32)                   not null,
    error_code       NUMBER(8, 0)                   not null,
    error_msg        VARCHAR2(64),
    create_time      DATE           default sysdate not null,
    update_time      DATE           default sysdate not null,
    trading_day      VARCHAR2(32)                   not null,
    primary key (id)
);

COMMENT ON COLUMN "T_ALGORITHMHISTORY"."VOLUME" IS '总数量';

COMMENT ON COLUMN "T_ALGORITHMHISTORY"."ENTRUST_BS" IS '买卖标志，同委托';

COMMENT ON COLUMN "T_ALGORITHMHISTORY"."OFFSET_FLAG" IS '开平仓标志，同委托';

COMMENT ON COLUMN "T_ALGORITHMHISTORY"."LIMIT_ACTION" IS '买入若出现跌停时的操作，或者卖出出现涨停时的操作';

COMMENT ON COLUMN "T_ALGORITHMHISTORY"."AFTER_ACTION" IS '超过时间后是撤单还是继续交易';

COMMENT ON COLUMN "T_ALGORITHMHISTORY"."TRADED_VOLUME" IS '已成交数量';

COMMENT ON COLUMN "T_ALGORITHMHISTORY"."EXTERNAL_ID" IS '外部ID，外接算法中心时用';

COMMENT ON COLUMN "T_ALGORITHMHISTORY"."TRADING_DAY" IS '交易日';

/*交易系统期货保证金表*/
drop table T_FUTURES_MARGIN_RATE;
create table T_FUTURES_MARGIN_RATE
(
    ID                           VARCHAR2(32) primary key,
    ACCOUNT_ID                   VARCHAR2(32),
    FINANCE_ACCOUNT              VARCHAR2(32),
    MARKET                       VARCHAR2(8),
    STOCK_CODE                   VARCHAR2(32),
    BROKER_ID                    VARCHAR2(32),
    INVESTOR_RANGE               VARCHAR2(4),
    HEDGE_FLAG                   VARCHAR2(4),
    LONG_MARGIN_RATIO_BY_MONEY   NUMBER(12, 4),
    LONG_MARGIN_RATIO_BY_VOLUME  NUMBER(12, 4),
    SHORT_MARGIN_RATIO_BY_MONEY  NUMBER(12, 4),
    SHORT_MARGIN_RATIO_BY_VOLUME NUMBER(12, 4),
    IS_RELATIVE                  NUMBER(1),
    INVEST_UNIT_ID               VARCHAR2(32)
);

comment on table T_FUTURES_MARGIN_RATE is '期货保证金';

comment on column T_FUTURES_MARGIN_RATE.ID is '主键';

comment on column T_FUTURES_MARGIN_RATE.ACCOUNT_ID is '账号';

comment on column T_FUTURES_MARGIN_RATE.FINANCE_ACCOUNT is '资金账号';

comment on column T_FUTURES_MARGIN_RATE.MARKET is '市场';

comment on column T_FUTURES_MARGIN_RATE.BROKER_ID is '交易商';

comment on column T_FUTURES_MARGIN_RATE.INVESTOR_RANGE is '投资者范围类型';

comment on column T_FUTURES_MARGIN_RATE.STOCK_CODE is '合约代码';

comment on column T_FUTURES_MARGIN_RATE.HEDGE_FLAG is '投机套保标志';

comment on column T_FUTURES_MARGIN_RATE.LONG_MARGIN_RATIO_BY_MONEY is '多头保证金率';

comment on column T_FUTURES_MARGIN_RATE.LONG_MARGIN_RATIO_BY_VOLUME is '多头保证金费';

comment on column T_FUTURES_MARGIN_RATE.SHORT_MARGIN_RATIO_BY_MONEY is '空头保证金率';

comment on column T_FUTURES_MARGIN_RATE.SHORT_MARGIN_RATIO_BY_VOLUME is '空头保证金费';

comment on column T_FUTURES_MARGIN_RATE.INVEST_UNIT_ID is '投资单元代码';


---- 添加索引

CREATE INDEX ENTRUST_ACCOUNT_ID_INDEX ON T_ENTRUSTHISTORY (ACCOUNT_ID ASC);
CREATE INDEX ENTRUST_ID_INDEX  ON T_ENTRUSTHISTORY (ENTRUST_ID ASC);
CREATE INDEX ENTRUST_INSTRUCTION_ID_INDEX ON T_ENTRUSTHISTORY (INSTRUCTION_ID ASC);
CREATE INDEX ENTRUST_PORTFOLIO_ID_INDEX ON T_ENTRUSTHISTORY (PORTFOLIO_ID ASC);
CREATE INDEX ENTRUST_USER_ID_INDEX ON T_ENTRUSTHISTORY (USER_ID ASC);

CREATE INDEX INTEGRATED_ACCOUNT_ID_INDEX ON T_INTEGRATED_ENTRUSTHISTORY (ACCOUNT_ID ASC);
CREATE INDEX INTEGRATED_ENTRUST_ID_INDEX ON T_INTEGRATED_ENTRUSTHISTORY (INTEGRATED_ENTRUST_ID ASC);
CREATE INDEX INTEGRATED_INSTRUCTION_INDEX ON T_INTEGRATED_ENTRUSTHISTORY (INSTRUCTION_ID ASC);
CREATE INDEX INTEGRATED_PORTFOLIO_ID_INDEX ON T_INTEGRATED_ENTRUSTHISTORY (PORTFOLIO_ID ASC);
CREATE INDEX INTEGRATED_USER_ID_INDEX ON T_INTEGRATED_ENTRUSTHISTORY (USER_ID ASC);
  
CREATE INDEX TRADE_ACCOUNT_ID_INDEX ON T_TRADEHISTORY (ACCOUNT_ID ASC);
CREATE INDEX TRADE_ENTRUST_ID_INDEX ON T_TRADEHISTORY (ENTRUST_ID ASC);
CREATE INDEX TRADE_STOCK_CODE_INDEX ON T_TRADEHISTORY (STOCK_CODE ASC);

create index IDX_INSTRUMENT on T_MD_BAR (INSTRUMENT_ID, TRADING_DAY DESC);

----新增序列

/* 交易系统历史委托序列 */
drop sequence AUTOINC_ENTRUSTHISTORY;
create sequence AUTOINC_ENTRUSTHISTORY
minvalue 1
maxvalue 9999999999999999999999999999
start with 8355
increment by 1
nocache;

/* 交易系统历史综合序列 */
drop sequence AUTOINC_INTEGRATEDTHISTORY;
create sequence AUTOINC_INTEGRATEDTHISTORY
minvalue 1
maxvalue 9999999999999999999999999999
start with 26
increment by 1
nocache;

/* 交易系统历史订单序列 */
drop sequence AUTOINC_ORDERHISTORY;
create sequence AUTOINC_ORDERHISTORY
minvalue 1
maxvalue 9999999999999999999999999999
start with 6621
increment by 1
nocache;

/* 交易系统历史成交序列 */
drop sequence AUTOINC_TRADEHISTORY;
create sequence AUTOINC_TRADEHISTORY
minvalue 1
maxvalue 9999999999999999999999999999
start with 37
increment by 1
nocache;

/* 交易系统历史综合委托序列 */
drop sequence INTEGRATED_ENTRUSTHISTORY;
create sequence INTEGRATED_ENTRUSTHISTORY
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
nocache;

/* 交易系统历史综合序列 */
drop sequence INTEGRATED_HISTORY_ID_SEQ;
create sequence INTEGRATED_HISTORY_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1701
increment by 1
cache 20;

/* 交易系统历史综合序列1 */
drop sequence INTEGRATED_HISTORY_SEQ;
create sequence INTEGRATED_HISTORY_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
nocache;

/* 交易系统批量信息序列 */
drop sequence SEQ_BATCHINFO;
create sequence SEQ_BATCHINFO
minvalue 1
maxvalue 9999999999999
start with 807
increment by 1
nocache;

/* 交易系统历史委托序列 */
drop sequence T_ENTRUSTHISTORY_ID_SEQ;
create sequence T_ENTRUSTHISTORY_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
nocache;

/* 交易系统历史成交序列 */
drop sequence T_TRADEHISTORY_ID_SEQ;
create sequence T_TRADEHISTORY_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 421
increment by 1
cache 20;

/* 交易系统历史成交序列1 */
drop sequence T_TRADEHISTORY_SEQ;
create sequence T_TRADEHISTORY_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
nocache;

/* 交易系统终端映射序列 */
drop sequence T_TERMINALMAPPING_ID_SEQ;
create sequence T_TERMINALMAPPING_ID_SEQ
minvalue 1
maxvalue 9999999999999999999
start with 41
increment by 1
cache 20;


/* 交易系统历史动态监控序列 */
drop sequence T_MONITORHISTORY_ID_SEQ;
create sequence T_MONITORHISTORY_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
nocache;

/* 交易系统动态监控指标表格映射序列 */
drop sequence T_MONITORCATEGORY_ID_SEQ;
create sequence T_MONITORCATEGORY_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
nocache;

/*交易系统算法历史表序列*/
drop sequence T_ALGORITHMHISTORY_ID_SEQ;
create sequence T_ALGORITHMHISTORY_ID_SEQ
    minvalue 1
    maxvalue 9999999999999999999999999999
    start with 1
    increment by 1
    nocache;

----新增触发器

/* 交易系统历史委托触发器 */
drop trigger AUTOINC_ENTRUSTHISTORY;
CREATE OR REPLACE TRIGGER "AUTOINC_ENTRUSTHISTORY" BEFORE INSERT ON "T_ENTRUSTHISTORY" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW
begin
select autoinc_entrusthistory.nextval into :new.Id from dual;
end autoinc_entrusthistory;
/

/* 交易系统历史订单触发器 */
drop trigger AUTOINC_ORDERHISTORY;
CREATE OR REPLACE TRIGGER "AUTOINC_ORDERHISTORY" BEFORE INSERT ON "T_ORDERHISTORY" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW
begin
select autoinc_orderhistory.nextval into:new.Id from dual;
end autoinc_orderhistory;
/

/* 交易系统历史批量信息触发器 */
drop trigger INTEGRATED_HISTORY_TG;
CREATE OR REPLACE TRIGGER "INTEGRATED_HISTORY_TG" BEFORE INSERT ON "T_INTEGRATED_ENTRUSTHISTORY" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW
declare
begin
  select INTEGRATED_HISTORY_ID_SEQ.nextval into :new.ID from dual;
end INTEGRATED_HISTORY_ID_SEQ;
/

/* 交易系统历史批量信息触发器 */
drop trigger SEQ_TRIG;
create or replace trigger SEQ_TRIG before insert on T_BATCHINFOHISTORY referencing old as old new as new for each row
declare
begin
  select SEQ_BATCHINFO.nextval into :new.ID from dual;
end SEQ_TRIG;
/

/* 交易系统历史成交触发器 */
drop trigger T_TRADEHISTORY_ID_TG;
CREATE OR REPLACE TRIGGER "T_TRADEHISTORY_ID_TG" BEFORE INSERT ON "T_TRADEHISTORY" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW
declare
begin
  select T_TRADEHISTORY_ID_SEQ.nextval into :new.ID from dual;
end T_TRADEHISTORY_ID_SEQ;
/

/* 交易系统终端映射触发器 */
drop trigger T_TERMINALMAPPING_ID_TRIG;
CREATE OR REPLACE TRIGGER "T_TERMINALMAPPING_ID_TRIG" BEFORE INSERT ON "T_TERMINALMAPPING" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW
declare
begin
  select T_TERMINALMAPPING_ID_SEQ.nextval into :new.ID from dual;
end T_TERMINALMAPPING_ID_TRIG;
/

/* 交易系统历史动态监控触发器 */
drop trigger T_MONITORHISTORY_ID_TG;
create or replace trigger "T_MONITORHISTORY_ID_TG"
    before insert on "T_MONITORHISTORY"
    for each row
declare
begin
    if :new.id is null then
        select T_MONITORHISTORY_ID_SEQ.nextval into :new.id from dual;
    end if;
end ;
/

/* 交易系统动态监控指标表格映射触发器 */
drop trigger T_MONITORCATEGORY_ID_TG;
create or replace trigger "T_MONITORCATEGORY_ID_TG"
    before insert on "T_MONITORCATEGORY"
    for each row
declare
begin
    if :new.id is null then
        select T_MONITORCATEGORY_ID_SEQ.nextval into :new.id from dual;
    end if;
end ;
/

/*合约表updatetime触发器*/
drop trigger INSTRUMENT_TRIGGER;
CREATE or replace TRIGGER INSTRUMENT_TRIGGER 
  BEFORE UPDATE ON T_INSTRUMENT REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW
begin
     :new.UPDATE_TIME := sysdate;
end;
/

/* 交易系统风控参数表触发器 */
drop trigger ACCOUNTRISKPARMATER_TRIGGER;
CREATE TRIGGER ACCOUNTRISKPARMATER_TRIGGER BEFORE UPDATE ON T_ACCOUNT_RISK_PARAMETER REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW
begin
     :new.UPDATE_TIME := sysdate;
end;
/

/* 交易系统算法历史表触发器 */
drop trigger T_ALGORITHMHISTORY_ID_TG;
create or replace trigger "T_ALGORITHMHISTORY_ID_TG"
    before insert
    on "T_ALGORITHMHISTORY"
    for each row
declare
begin
    if :new.id is null then
        select T_ALGORITHMHISTORY_ID_SEQ.nextval into :new.id from dual;
    end if;
end ;
/

----初始化数据
/* 增加服务节点及终端映射 */
DELETE FROM T_SERVICE_NODE;
insert into T_SERVICE_NODE (node_name, node_address, node_weight, node_type) values ('阿里云', '192.168.55.183:8091', 2, 'TRADE');
insert into T_SERVICE_NODE (node_name, node_address, node_weight, node_type) values ('阿里云2', '192.168.55.183:', 1, 'TRADE');

DELETE FROM T_TERMINAL WHERE terminal_name IN ('mock','ufx');
insert into T_TERMINAL (terminal_name, terminal_type, create_time) values ('mock', 1, to_date('19-06-2020 14:45:33', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_TERMINAL (terminal_name, terminal_type, create_time) values ('ufx', 2, to_date('19-06-2020 14:46:46', 'dd-mm-yyyy hh24:mi:ss'));

DELETE FROM T_TERMINALMAPPING WHERE ID = '1' AND terminal_name = '1234:27004935';
insert into T_TERMINALMAPPING (id, account_id, terminal_name, create_time) values ('1', '1234:27004935', 'mock', to_date('19-06-2020 15:22:11', 'dd-mm-yyyy hh24:mi:ss'));

/* 交易系统动态监控指标表格映射表添加记录 */
DELETE FROM T_MONITORCATEGORY;
insert into T_MONITORCATEGORY (ID, RECORD_NAME, CATEGORY_NAME,CREATE_TIME) values ('1', '0', '在线账户', SYSDATE);

insert into T_MONITORCATEGORY (ID, RECORD_NAME, CATEGORY_NAME,CREATE_TIME) values ('2', '1', '积压队列', SYSDATE);

insert into T_MONITORCATEGORY (ID, RECORD_NAME, CATEGORY_NAME,CREATE_TIME) values ('3', '2', '异常队列', SYSDATE);

insert into T_MONITORCATEGORY (ID, RECORD_NAME, CATEGORY_NAME,CREATE_TIME) values ('4', '3', '积压队列', SYSDATE);

insert into T_MONITORCATEGORY (ID, RECORD_NAME, CATEGORY_NAME,CREATE_TIME) values ('5', '4', '异常队列', SYSDATE);

/* 风控参数表新增数据 */
INSERT INTO T_ACCOUNT_RISK_PARAMETER("ACCOUNT_ID", "FLOW", "TOTAL_COUNT", "CANCEL_RATIO", "SELF_TRADE") VALUES ('0', '2000', '20000', '0.5', '1');






