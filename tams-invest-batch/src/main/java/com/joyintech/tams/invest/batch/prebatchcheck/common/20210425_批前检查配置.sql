--流程中产品交易
delete from sys_batch_task where TASK_ID = '88601';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88601', 'INV', '流程中产品交易', null, 'processProductTradeTask', 1, 'system', null, sysdate, 'system', sysdate);
delete from sys_prebatch_task_add where TASK_ID = '88601';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88601', '00', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_batch_job where TASK_ID = '88601';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88601', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';
------- 增加多及法人 过滤
delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.processproducttrade.dao.ProcessProductTradeMapper.selectProcessProductTradeList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.processproducttrade.dao.ProcessProductTradeMapper.selectProcessProductTradeList', '5', '02', 'instCode', null, 'prebatch', 'processProductTradeTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);

--流程中的资产定义
delete from sys_batch_task where TASK_ID = '88602';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88602', 'INV', '流程中资产定义', null, 'processAssetTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88602';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88602', '01', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88602';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88602', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';
--资产定义由于部分资产不需要数据隔离，隔离在内部进行

--流程中的投资交易
delete from sys_batch_task where TASK_ID = '88603';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88603', 'INV', '流程中投资交易', null, 'processAssetTradeTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88603';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88603', '00', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88603';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88603', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';
------- 增加多及法人 过滤
delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.processassettrade.dao.ProcessAssetTradeMapper.selectProcessAssetTradeList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.processassettrade.dao.ProcessAssetTradeMapper.selectProcessAssetTradeList', '5', '02', 'instCode', null, 'prebatch', 'processAssetTradeTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);


--流程中的清算指令
delete from sys_batch_task where TASK_ID = '88604';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88604', 'INV', '流程中清算指令', null, 'processSettleOrderTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88604';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88604', '01', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88604';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88604', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';
------- 增加多及法人 过滤
delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.processsettleorder.dao.ProcessSettleOrderMapper.selectProcessSettleOrderList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.processsettleorder.dao.ProcessSettleOrderMapper.selectProcessSettleOrderList', '5', '02', 'instCode', null, 'prebatch', 'processSettleOrderTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);


--流程中的手工凭证
delete from sys_batch_task where TASK_ID = '88605';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88605', 'INV', '流程中手工凭证', null, 'processVoucherTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88605';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88605', '01', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88605';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88605', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';
------- 增加多及法人 过滤
delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.processvoucher.dao.ProcessVoucherMapper.selectProcessVoucherList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.processvoucher.dao.ProcessVoucherMapper.selectProcessVoucherList', '5', '02', 'instCode', null, 'prebatch', 'processVoucherTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);

--添加字典值配置
delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88601';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88601', '流程中产品交易', 2, '0', 'system', null,sysdate, 'system',sysdate, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88602';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88602', '流程中资产定义', 3, '0', 'system', null,sysdate, 'system',sysdate, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88603';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88603', '流程中投资交易', 4, '0', 'system', null,sysdate, 'system',sysdate, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88604';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88604', '流程中清算指令', 5, '0', 'system', null,sysdate, 'system',sysdate, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88605';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88605', '流程中手工凭证', 6, '0', 'system', null,sysdate, 'system',sysdate, null);

--追加-估值方法方案检查
delete from sys_batch_task where TASK_ID = '88606';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88606', 'INV', '估值方案配置', null, 'valuationSchemeTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88606';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88606', '00', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88606';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88606', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.valuationscheme.dao.ValuationSchemeMapper.selectValuationSchemeList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.valuationscheme.dao.ValuationSchemeMapper.selectValuationSchemeList', '5', '02', 'instCode', null, 'prebatch', 'valuationSchemeTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88606';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88606', '估值方案配置', 7, '0', 'system', null,sysdate, 'system',sysdate, null);

--追加-组合账户资金负头寸
delete from sys_batch_task where TASK_ID = '88607';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88607', 'INV', '产品现金负头寸', null, 'capitalPositionTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88607';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88607', '00', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88607';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88607', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.capitalposition.dao.CapitalPositionMapper.selectCapitalPositionList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.capitalposition.dao.CapitalPositionMapper.selectCapitalPositionList', '5', '02', 'instCode', null, 'prebatch', 'capitalPositionTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88607';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88607', '产品现金负头寸', 8, '0', 'system', null,sysdate, 'system',sysdate, null);

--追加-资产行情数据
delete from sys_batch_task where TASK_ID = '88608';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88608', 'INV', '资产行情数据', null, 'assetMarketDataTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88608';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88608', '01', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88608';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88608', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.dao.AssetMarketDataMapper.getAssetMarketDataResult';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.dao.AssetMarketDataMapper.getAssetMarketDataResult', '5', '02', 'instCode', null, 'prebatch', 'assetMarketDataTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88608';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88608', '资产行情数据', 9, '0', 'system', null,sysdate, 'system',sysdate, null);
