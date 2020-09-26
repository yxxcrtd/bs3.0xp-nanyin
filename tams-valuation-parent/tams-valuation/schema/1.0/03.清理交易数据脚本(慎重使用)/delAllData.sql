truncate table BOK_ACCT_SUBJECT_INFO;
truncate table BOK_DOC_INFO;
truncate table BOK_STAT_DET_POSIITON;
truncate table BOK_VAL_SUMMARY;
truncate table TRD_RECLASS_FAIL_INFO;
truncate table BOK_FILE_TRANSFER;
truncate table BOK_ACCT_LAYERING_INFO;
truncate table BOK_BALANCE;
truncate table BOK_BAL_TABLE_DATA;
truncate table BOK_BOOKSET_CAPACCT_INFO;
truncate table BOK_BOOKSET_OPTION;
truncate table BOK_BOOKSET_PLAN;
truncate table BOK_DETAIL_ID;
truncate table BOK_FILE_GEN;
truncate table BOK_STAT_BOK_POSITION;
truncate table BOK_VALBAL_TABLE_LOG;
truncate table BOK_VAL_SCHEME;
truncate table BOK_VAL_TABLE_DATA;
truncate table BOK_VOUCH_DETAIL;
truncate table BOK_VOUCH_MAIN;
truncate table BOK_VOUCH_MAIN_ERROR;
truncate table BOK_VOUCH_REL;
truncate table CBONDANALYSISCNBD;
truncate table CBONDBENCHMARK;
truncate table CHINAMUTUALFUNDNAV;
truncate table CMONEYMARKETFINCOME;
truncate table FIN_CASH_CALC_RULE;
truncate table FIN_CASH_INFO;
truncate table FIN_CASH_INT_INFO;
truncate table FIN_CASH_PAY_RULE;
truncate table FIN_CASH_PLAN;
truncate table FIN_DEAL_PLEDGE;
truncate table FIN_EXERCISE_PLAN;
truncate table FIN_FLOAT_ACCRUAL_SCHE;
truncate table FIN_FLOAT_CALC_RULE;
truncate table FIN_FLOAT_CALC_RULE_DETAIL;
truncate table FIN_FLOAT_COUNTING_RULE;
truncate table FIN_PRODUCT_CLASS;
truncate table FIN_PRODUCT_EXT;
truncate table FIN_PRODUCT_TYPE;
truncate table FIN_PRODUCT_VALUATION;
truncate table FIN_TRADE_PRODUCT_ADD;
truncate table FIN_VAL_OPEN_PLAN;
truncate table SHIBORPRICES;
truncate table STL_CAPACCT_INFO;
truncate table STL_FIN_PRODUCT_ACCT;
truncate table STL_ORDERS;
truncate table STL_ORDERS_EXT;
truncate table STL_SEC_MANAGE_ACCT;
truncate table TG_TRADE_FEE_DETAIL;
truncate table TRD_FIN_PRODUCT_BARTER;
truncate table TRD_PRODUCT_DEAL_EXT;
truncate table TRD_VOUCH_DEAL;
truncate table TRD_VOUCH_DEAL_DETAIL;
truncate table FIN_TRADE_PRODUCT;
truncate table TRD_PRODUCT_DEAL;
truncate table FIN_PRODUCT;
truncate table BOK_BOOKDETAIL_CURRENT;
truncate table BOK_BUSS_DETSUB;
truncate table BOK_DEAL_ACCOUNTING;
truncate table BOK_DEAL_ACCOUNTING_DETAIL;
truncate table BOK_MANUAL_BOOKDETAIL;
truncate table BOK_SUBJECT_DETAIL;
truncate table BOK_VALBAL_TABLE_STATUS;
truncate table BOK_BOOKDETAIL_ALL;
truncate table BOK_BATCH_TASK_TODO;

/*账套表_begin*/
delete BOK_BOOKSET where bookset_id not in ('00000000000000000002','00000000000000000003','00000000000000000004');
/*账套表_end*/
/*账套配置表_begin*/
delete BOK_BOOKSET_EXT where bookset_id not in ('00000000000000000002','00000000000000000003','00000000000000000004');
delete BOK_BOOKSET_DATE where bookset_id not in ('00000000000000000002','00000000000000000003','00000000000000000004');

/*科目表-企业_begin*/
delete BOK_SUBJECT where bookset_id not in ('00000000000000000002','00000000000000000003','00000000000000000004');
/*科目表-企业_end*/
/*科目性质表-企业_begin*/
--truncate table BOK_ACCT_STANDARD_NATURE;
/*科目性质表-企业_end*/
/*会计场景_begin*/
delete BOK_TABLELIST where bookset_id not in ('00000000000000000002','00000000000000000003','00000000000000000004');
delete BOK_TABLEDICT where bookset_id not in ('00000000000000000002','00000000000000000003','00000000000000000004');
delete BOK_SCENESUBJECT where bookset_id not in ('00000000000000000002','00000000000000000003','00000000000000000004');
delete BOK_SCENECONFIG where bookset_id not in ('00000000000000000002','00000000000000000003','00000000000000000004');

commit;