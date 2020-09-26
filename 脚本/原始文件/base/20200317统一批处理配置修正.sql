update SYS_BATCH_GRP
   set system_code = 'INV'
 WHERE SEQ_NO IN ('dis1', 'dis2');
 
 commit;