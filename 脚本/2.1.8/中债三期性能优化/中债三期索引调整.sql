 
 
 drop index I_CHB_INVESTHOLDSUB_HOLD_DATE;
 drop index I_CHB_INVESTHOLD_INFO_SUB;
 drop index I_CHB_INVESTHOLD_INFO_SUB_2;
 drop index I_CHB_INVESTHOLD_INFO_SUB_3;
 drop index I_INVEST_BATCH;
 
 
  CREATE INDEX "I_CHB_INVESTHOLDSUB_HOLD_DATE" ON "CHB_INVESTHOLD_INFO_SUB" ("HOLD_DATE") ;
   
  CREATE INDEX "I_CHB_INVESTHOLD_INFO_SUB" ON "CHB_INVESTHOLD_INFO_SUB" ("REGIST_BANK_CODE", "INDENTIFY_MARK", "HOLD_DATE", "PRODID") ;
  
  CREATE INDEX "I_CHB_INVESTHOLD_INFO_SUB_2" ON "CHB_INVESTHOLD_INFO_SUB" ("REGIST_BANK_CODE", "INDENTIFY_MARK", "PRD_REGIST_CODE", "HOLD_DATE", "CCY", "SEND_STATUS"); 
   
  CREATE INDEX "I_CHB_INVESTHOLD_INFO_SUB_3" ON "CHB_INVESTHOLD_INFO_SUB" ("REGIST_SEQNO", "SEND_STATUS") ;

  CREATE INDEX "I_INVEST_BATCH" ON "CHB_INVESTHOLD_INFO_SUB" ("SEND_STATUS", "STATUS", "CHECK_SEQNO") ;
  
  
   drop index CHB_INVESTHOLD_INFO_CHECK_2;
   
   
   
   create index CHB_INVESTHOLD_INFO_CHECK_2 on CHB_INVESTHOLD_INFO_CHECK (OPERATE_STATUS,HOLD_DATE);
   
create or replace procedure UPDATE_SEQUENCE_CHB(incrNum IN VARCHAR2,sequenceName IN VARCHAR2)
   AS                                        
	v_incr NUMBER :=to_number(incrNum);
	v_seq_val     number ;
	v_seqname  VARCHAR2(160) := sequenceName;
	v_sql_data_text  VARCHAR2(500)  := 'alter sequence  ' || v_seqname || ' increment by ' || v_incr ||'cache 100 ';
	v_sql_query_text  VARCHAR2(500)  := 'select ' || v_seqname || ' .nextval from dual ';
                          
 BEGIN
 if v_incr > 0 THEN
execute immediate  v_sql_data_text;

execute immediate v_sql_query_text into v_seq_val;
 
v_sql_data_text := 'alter sequence  ' || v_seqname || '
                          increment by  1' ||'cache 100 ';
                          
 execute immediate  v_sql_data_text;
END IF;
END UPDATE_SEQUENCE_CHB;