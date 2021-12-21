update SYS_BATCH_TASK set TASK_PRI = 3 where BEAN_NAME = 'investDealRevokeTask';
update SYS_BATCH_TASK set TASK_PRI = 4 where BEAN_NAME = 'stlDownDataDealTask';
update SYS_BATCH_TASK set TASK_PRI = 6, SYSTEM_CODE = 'STL' where BEAN_NAME = 'stlSettleTransDataGatherTask';
update SYS_BATCH_TASK set TASK_PRI = 6, SYSTEM_CODE = 'STL' where BEAN_NAME = 'downlinkTradeSendTask';