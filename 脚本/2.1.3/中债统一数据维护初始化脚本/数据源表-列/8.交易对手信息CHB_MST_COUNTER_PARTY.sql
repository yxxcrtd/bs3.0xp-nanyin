delete from CHB_INFOMGR_RESULT_CONFIG WHERE MGRTABLE_ID='CHB_MST_COUNTER_PARTY';
insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c36', 'CHB_MST_COUNTER_PARTY', 'COUNTER_ID', '交易对手代码', '', 1, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c37', 'CHB_MST_COUNTER_PARTY', 'COUNTER_TYPE', '交易对手类型', 'COUNTER_TYPE', 2, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c38', 'CHB_MST_COUNTER_PARTY', 'COUNTER_NAME', '交易对手名称', '', 3, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c39', 'CHB_MST_COUNTER_PARTY', 'LINK_ID', '关联代码', '', 4, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c40', 'CHB_MST_COUNTER_PARTY', 'MANAGER_ID', '管理人', '', 5, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c41', 'CHB_MST_COUNTER_PARTY', 'HEAD_BANK', '所属总行', '', 6, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c42', 'CHB_MST_COUNTER_PARTY', 'CONTACT', '联系人', '', 7, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c43', 'CHB_MST_COUNTER_PARTY', 'CONTACT_WAY', '联系方式', '', 8, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c44', 'CHB_MST_COUNTER_PARTY', 'REMARK', '备注', '', 9, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c45', 'CHB_MST_COUNTER_PARTY', 'CREATE_USER', '创建人', '', 10, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c46', 'CHB_MST_COUNTER_PARTY', 'CREATE_DEPT', '创建部门', '', 11, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c47', 'CHB_MST_COUNTER_PARTY', 'CREATE_TIME', '创建时间', '', 12, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c48', 'CHB_MST_COUNTER_PARTY', 'UPDATE_USER', '更新人', '', 13, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c49', 'CHB_MST_COUNTER_PARTY', 'UPDATE_TIME', '更新时间', '', 14, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c50', 'CHB_MST_COUNTER_PARTY', 'COUNTER_TYPE1', '交易对手一级类型', 'SVR_CUSTOMER_TYPE1', 15, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c51', 'CHB_MST_COUNTER_PARTY', 'COUNTER_TYPE2', '交易对手二级类型', 'SVR_CUSTOMER_TYPE2', 16, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c52', 'CHB_MST_COUNTER_PARTY', 'IS_CENTRAL_COUNTER', '是否为中央交易对手方', 'SVR_YES_OR_NO', 17, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c53', 'CHB_MST_COUNTER_PARTY', 'UUID', 'uuid技术主键', '', 18, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c54', 'CHB_MST_COUNTER_PARTY', 'STATUS', '数据状态', 'MAINTAIN_STATUS', 19, 'Y');

