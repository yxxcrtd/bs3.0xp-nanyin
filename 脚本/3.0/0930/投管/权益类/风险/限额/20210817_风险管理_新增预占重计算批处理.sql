--etliu 新增预占重计算批处理
delete from SYS_BATCH_TASK where BEAN_NAME = 'rskParamReCalcTask';
INSERT INTO SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET) VALUES ('53', 'RSK', '预占重计算', '', 'rskParamReCalcTask', '23', 'system', null, sysdate, 'system', sysdate, null);
