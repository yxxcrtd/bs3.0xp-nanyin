select *
  from all_tables a
 where a.OWNER = 'TAMS_MAST'
   and a.TABLE_NAME not like 'ACT_%'
   and a.TABLE_NAME not like 'BATCH_%'
   and a.TABLE_NAME not like 'SYS_%'
   and a.TABLE_NAME not like 'WFL_%'
   and a.TABLE_NAME not like 'MST_%'
   and a.TABLE_NAME not like 'VAL_%'
   and a.TABLE_NAME not in ('BOK_BATCH_TASK_LINK',
                            'BOK_MATCH_RLAT',
                            'BOK_SCHEME_CONFIG',
                            'BOK_SCHEME_CONFIG_VAL',
                            'BOK_SCHEME_INFO',
                            'BOK_ACCT_STANDARD',
                            'BOK_ACCT_STANDARD_NATURE',
                            'BOK_BATCH_TASK_GRP',
                            'BOK_SUBJECT_NATURE_GROUP',
                            'BOK_TRADE_ORDER',
                            'BOK_VAL_SCHEME_DETAIL',
                            'COM_EXCEL_IMP_TARGET',
                            'FEE_TABLE_SET',
                            'TAX_ADT_SCHEDULE',
                            'TAX_BOK_BALANCE',
                            'TAX_BOK_BALANCE_SUM',
                            'TAX_BOK_CONFIG',
                            'TAX_BOK_DETAIL',
                            'TAX_CF_BOK_CONFIG',
                            'TAX_TAXABLE_INFO',
                            'TAX_VAT_SCHEDULE');
