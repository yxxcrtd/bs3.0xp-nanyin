delete from CHB_INFOMGR_RESULT_CONFIG WHERE MGRTABLE_ID='CHB_INVEST_TRADE';
insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983110', 'CHB_INVEST_TRADE', 'TRADE_SEQNO', '交易UUID', '', 1, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983111', 'CHB_INVEST_TRADE', 'TRADE_CODE', '行内交易编码', '', 2, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983112', 'CHB_INVEST_TRADE', 'INVEST_VARIETY', '投资品种', 'SVR_INVEST_TRADE_TYPE', 3, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983113', 'CHB_INVEST_TRADE', 'INVEST_TYPE', '投资类型', '', 4, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983114', 'CHB_INVEST_TRADE', 'PROD_ID', '产品代码', '', 5, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983115', 'CHB_INVEST_TRADE', 'REGIST_BANK_CODE', '发行机构代码', '', 6, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983116', 'CHB_INVEST_TRADE', 'ASSET_CODE', '行内资产/负债编码', '', 7, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983117', 'CHB_INVEST_TRADE', 'CCY', '币种', 'CURRENCY_CHB', 8, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983118', 'CHB_INVEST_TRADE', 'AMT', '发生金额', '', 9, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983119', 'CHB_INVEST_TRADE', 'CNY_AMT', '折算人民币金额', '', 10, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983120', 'CHB_INVEST_TRADE', 'NUM', '数量', '', 11, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983121', 'CHB_INVEST_TRADE', 'CUR_ASSET_TYPE', '资金流动类型', 'SVR_FUND_FLOW_TYPE', 12, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983122', 'CHB_INVEST_TRADE', 'CUR_ASSETDET_TYPE', '资金流动类型说明', '', 13, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983123', 'CHB_INVEST_TRADE', 'DEALDATE', '交易日', '', 14, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983124', 'CHB_INVEST_TRADE', 'TRADE_COUNTER', '交易对手', '', 15, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983125', 'CHB_INVEST_TRADE', 'UNIT_PRICE_FULL', '单位成交价格（全价）', '', 16, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983126', 'CHB_INVEST_TRADE', 'UNIT_PRICE_NET', '单位成交价格（净价）', '', 17, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983127', 'CHB_INVEST_TRADE', 'END_RATE', '到期收益率', '', 18, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983134', 'CHB_INVEST_TRADE', 'STATUS', '数据状态', 'MAINTAIN_STATUS', 25, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983135', 'CHB_INVEST_TRADE', 'CREATE_USER', '创建人', '', 26, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983136', 'CHB_INVEST_TRADE', 'CREATE_TIME', '创建时间', '', 27, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983128', 'CHB_INVEST_TRADE', 'CONTRACT_CODE', '成交编号/合同号', '', 19, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983129', 'CHB_INVEST_TRADE', 'REMARK', '备注', '', 20, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983130', 'CHB_INVEST_TRADE', 'CHL_CPCT_ID', '通道代码', '', 21, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983131', 'CHB_INVEST_TRADE', 'TRADE_TYPE', '交易类型', 'SVR_TRADE_TYPE_INVEST', 22, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983132', 'CHB_INVEST_TRADE', 'CHL_AGRT_NAME', '通道名称', '', 23, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983133', 'CHB_INVEST_TRADE', 'IS_UNDER_TRADE', '是否底层交易流水', 'SVR_YES_OR_NO', 24, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983137', 'CHB_INVEST_TRADE', 'UPDATE_USER', '更新人', '', 28, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983138', 'CHB_INVEST_TRADE', 'UPDATE_TIME', '更新时间', '', 29, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983139', 'CHB_INVEST_TRADE', 'STATUS_DATE', '数据日期', '', 30, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983140', 'CHB_INVEST_TRADE', 'REV_DATE', '撤销日', '', 31, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983141', 'CHB_INVEST_TRADE', 'DATA_SOURCE', '数据来源', '', 32, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983142', 'CHB_INVEST_TRADE', 'RATE', '利率', '', 33, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983143', 'CHB_INVEST_TRADE', 'BASIS', '计息基础', 'SVR_BASIS', 34, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983144', 'CHB_INVEST_TRADE', 'DEALTYPE', '交易类型', 'SVR_INVEST_TRADE_TYPE_S', 35, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('117254a4-0b19-4dea-97b4-e782fa983145', 'CHB_INVEST_TRADE', 'FACEVALUE', '面值', '', 36, 'Y');

