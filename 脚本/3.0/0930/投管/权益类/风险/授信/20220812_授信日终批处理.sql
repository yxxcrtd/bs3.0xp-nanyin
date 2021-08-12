delete from SYS_BATCH_TASK where TASK_NAME = 'creditOccupTask';
INSERT
  INTO
  SYS_BATCH_TASK (TASK_ID,
  SYSTEM_CODE,
  TASK_NAME,
  FTASK_ID,
  BEAN_NAME,
  TASK_PRI,
  CREATE_USER,
  CREATE_DEPT,
  CREATE_TIME,
  UPDATE_USER,
  UPDATE_TIME,
  IS_BATCH_START_TARGET)
VALUES ('60002',
'RSK',
'授信日终批处理',
NULL,
'creditOccupTask',
20,
'system',
NULL,
sysdate,
'system',
sysdate,
NULL);