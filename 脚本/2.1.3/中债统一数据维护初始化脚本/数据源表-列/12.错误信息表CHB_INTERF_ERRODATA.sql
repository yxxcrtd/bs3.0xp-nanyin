delete from CHB_INFOMGR_RESULT_CONFIG WHERE MGRTABLE_ID='CHB_INTERF_ERRODATA';
insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983161', 'CHB_INTERF_ERRODATA', 'DATAUUID', 'uuid技术主键', '', 1, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983162', 'CHB_INTERF_ERRODATA', 'DATAID', '业务主键', '', 2, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983163', 'CHB_INTERF_ERRODATA', 'INTERF_TYPE', '接口类型', '', 3, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983164', 'CHB_INTERF_ERRODATA', 'FIRE_NAME', '文件名称', '', 4, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983165', 'CHB_INTERF_ERRODATA', 'ORG_DATA', '原始数据', '', 5, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983166', 'CHB_INTERF_ERRODATA', 'ERR_MSG', '错误信息', '', 6, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983167', 'CHB_INTERF_ERRODATA', 'STATUS', '有效状态', 'MAINTAIN_STATUS', 7, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983168', 'CHB_INTERF_ERRODATA', 'UPDATE_USER', '更新人', '', 8, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983169', 'CHB_INTERF_ERRODATA', 'CREATE_USER', '创建人', '', 9, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983170', 'CHB_INTERF_ERRODATA', 'UPDATE_TIME', '更新时间', '', 10, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983171', 'CHB_INTERF_ERRODATA', 'CREATE_TIME', '创建时间', '', 11, 'N');

