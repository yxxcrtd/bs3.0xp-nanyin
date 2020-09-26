delete from CHB_INFOMGR_RESULT_CONFIG WHERE MGRTABLE_ID='CHB_PRD_DEAL';
insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c80', 'CHB_PRD_DEAL', 'SEQNO', '交易编号', '', 1, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c81', 'CHB_PRD_DEAL', 'TRADE_ID', '上游系统交易编码', '', 2, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c82', 'CHB_PRD_DEAL', 'FPROD_ID', '父产品代码', '', 3, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c83', 'CHB_PRD_DEAL', 'PROD_ID', '产品代码', '', 4, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c84', 'CHB_PRD_DEAL', 'ISSUE_PARTY_ID', '发行机构代码', '', 5, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c85', 'CHB_PRD_DEAL', 'INDENTIFY_MARK', '识别标识', '', 6, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c86', 'CHB_PRD_DEAL', 'BUY_PLACE', '销售区域', 'ADMINISTRATIVE_TYPE3', 7, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c87', 'CHB_PRD_DEAL', 'TRADE_TYPE', '交易类型', 'SVR_DEAL_TYPE', 8, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c88', 'CHB_PRD_DEAL', 'SETTDATE_DATE', '确认日', '', 9, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c89', 'CHB_PRD_DEAL', 'PAY_DATE', '本金支付日', '', 10, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c90', 'CHB_PRD_DEAL', 'INTEREST_DATE', '利息支付日', '', 11, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c91', 'CHB_PRD_DEAL', 'INT_AMT', '利息金额', '', 12, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c92', 'CHB_PRD_DEAL', 'TRADE_PRINCE', '交易本金(赎回时存净金额=交易本金-赎回费)', '', 13, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c93', 'CHB_PRD_DEAL', 'UNIT_BONUS_AMOUNT', '单位分红金额', '', 14, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c94', 'CHB_PRD_DEAL', 'REDEMPTION_FEE', '赎回费', '', 15, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c95', 'CHB_PRD_DEAL', 'TRADE_QTY', '交易份额', '', 16, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c96', 'CHB_PRD_DEAL', 'CCY', '币种', 'CURRENCY_CHB', 17, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c97', 'CHB_PRD_DEAL', 'STATUS', '数据状态', 'MAINTAIN_STATUS', 18, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c98', 'CHB_PRD_DEAL', 'REMARK', '备注', '', 19, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983104', 'CHB_PRD_DEAL', 'INTEREST_CCY', '收益币种', 'CURRENCY_CHB', 25, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983105', 'CHB_PRD_DEAL', 'CUSTOMER_TYPE1', '客户一级类型', 'SVR_CUSTOMER_TYPE1', 26, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983106', 'CHB_PRD_DEAL', 'CUSTOMER_TYPE2', '客户二级类型', 'SVR_CUSTOMER_TYPE2', 27, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983c99', 'CHB_PRD_DEAL', 'REV_DATE', '撤销日', '', 20, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983100', 'CHB_PRD_DEAL', 'UPDATE_USER', '更新人', '', 21, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983101', 'CHB_PRD_DEAL', 'UPDATE_TIME', '更新时间', '', 22, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983102', 'CHB_PRD_DEAL', 'CREATE_USER', '创建人', '', 23, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983103', 'CHB_PRD_DEAL', 'CREATE_TIME', '创建时间', '', 24, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983107', 'CHB_PRD_DEAL', 'CUST_COUNTS', '保有客户数', '', 28, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983108', 'CHB_PRD_DEAL', 'TRADE_COST', '交易成本', '', 29, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983109', 'CHB_PRD_DEAL', 'ISAGENCY', '是否代销', 'SVR_YES_OR_NO', 30, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('447254a4-0b19-4dea-97b4-e782fa983109', 'CHB_PRD_DEAL', 'DATA_SOURCE', '数据来源', '', 31, 'N');

