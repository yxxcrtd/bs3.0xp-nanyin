delete from CHB_INFOMGR_RESULT_CONFIG WHERE MGRTABLE_ID='BOK_BALANCE';
insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('217254a4-0b19-4dea-97b4-e782fa983c80', 'BOK_BALANCE', 'B_CCY', '本币', 'CURRENCY_CHB', 10, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('217254a4-0b19-4dea-97b4-e782fa983c81', 'BOK_BALANCE', 'B_AMT', '本币余额', '', 11, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c71', 'BOK_BALANCE', 'BOOKSET_ID', '账套代码', '', 1, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c72', 'BOK_BALANCE', 'BALANCE_DATE', '余额日期', '', 2, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c73', 'BOK_BALANCE', 'SUBJECT_NO', '科目号', '', 3, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c74', 'BOK_BALANCE', 'FSUBJECT_NO', '父科目号', '', 4, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c75', 'BOK_BALANCE', 'SUBJECT_LEVEL', '科目级别', '', 5, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c76', 'BOK_BALANCE', 'BAL_FLAG', '余额方向', '', 6, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c77', 'BOK_BALANCE', 'BAL_CO_FLAG', '结转余额方向', '', 7, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c78', 'BOK_BALANCE', 'O_CCY', '原币', 'CURRENCY_CHB', 8, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c79', 'BOK_BALANCE', 'O_AMT', '原币余额', '', 9, 'Y');

