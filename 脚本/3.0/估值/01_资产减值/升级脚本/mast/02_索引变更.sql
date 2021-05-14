-- 估值优化
drop index INDEX_BOK_BOOKSET_BAL_DATE;
create index INDEX_BOK_BOOKSET_BAL_DATE on BOK_BAL_TABLE_DATA (BOOKSET_ID,BAL_DATE);
analyze table BOK_BAL_TABLE_DATA compute statistics;

drop index INDEX_BOK_VOUCH_MAIN;
create index INDEX_BOK_VOUCH_MAIN on BOK_VOUCH_MAIN (BOOKSET_ID,BOOK_DATE);
analyze table BOK_VOUCH_MAIN compute statistics;

drop index INDEX_BOK_VOUCH_DETAIL;
create index INDEX_BOK_VOUCH_DETAIL on BOK_VOUCH_DETAIL (VOUCH_NUM);

analyze table BOK_VOUCH_DETAIL compute statistics;
