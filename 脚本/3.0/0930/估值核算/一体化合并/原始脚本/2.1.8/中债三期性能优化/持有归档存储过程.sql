create or replace procedure CHB_BACKUP_HOLD_DATA(minDate IN VARCHAR,maxDate IN VARCHAR,newTable IN VARCHAR,oriTable in VARCHAR ,intType in Number)AS                                        
v_exists_table    Number ;
v_intType number :=intType;
v_table_name  VARCHAR2(40):=newTable;
v_table_name_ori  VARCHAR2(40):=oriTable;
v_dml_session_enable VARCHAR2(200):='alter session enable parallel dml';    
--check表创建并归档                        
v_create_check_sql  VARCHAR2(500)  := 'create table ' || v_table_name || ' nologging parallel 8 as select  * from chb_investhold_info_check where operate_status =''02'' and hold_date between to_date('''||minDate||''',''yyyy-MM-dd'') and to_date('''||maxDate||''',''yyyy-MM-dd'') and status =''E''';
--插入check               
v_insert_check_sql  VARCHAR2(500)  := 'insert /*+ append */  into  ' || v_table_name || ' select /*+ parallel(t,8) */ * from chb_investhold_info_check t where t.operate_status =''02'' and t.hold_date between to_date('''||minDate||''',''yyyy-MM-dd'') and to_date('''||maxDate||''',''yyyy-MM-dd'') and t.status =''E''';

v_delete_check_sql  VARCHAR2(500)  := 'delete from  ' || v_table_name_ori || ' t where t.operate_status =''02'' and t.hold_date between to_date('''||minDate||''',''yyyy-MM-dd'') and to_date('''||maxDate||''',''yyyy-MM-dd'') and t.status =''E''';
--info表创建并归档
v_create_info_sql  VARCHAR2(500)  := 'create table ' || v_table_name || ' nologging parallel 8 as select  * from chb_investhold_info where hold_date between to_date('''||minDate||''',''yyyy-MM-dd'') and to_date('''||maxDate||''',''yyyy-MM-dd'') and status =''E''';
--插入info                  
v_insert_info_sql  VARCHAR2(500)  := 'insert /*+ append */  into  ' || v_table_name || ' select /*+ parallel(info,8) */ * from chb_investhold_info info where info.hold_date between to_date('''||minDate||''',''yyyy-MM-dd'') and to_date('''||maxDate||''',''yyyy-MM-dd'') and info.status =''E''';                                                  
 --删除info                  
v_delete_info_sql  VARCHAR2(500)  := 'delete from ' || v_table_name_ori || ' info where info.hold_date between to_date('''||minDate||''',''yyyy-MM-dd'') and to_date('''||maxDate||''',''yyyy-MM-dd'') and info.status =''E''';

 --status表创建并归档                         
v_create_status_sql  VARCHAR2(500)  := 'create table ' || v_table_name || ' nologging parallel 8  as select  t.* from chb_submit_status t left join chb_investhold_info_sub sub on t.seqno = sub.sumit_seq where sub.hold_date between to_date('''||minDate||''',''yyyy-MM-dd'') and to_date('''||maxDate||''',''yyyy-MM-dd'')'; 
 --插入status                         
v_insert_status_sql  VARCHAR2(500)  := 'insert /*+ append */  into  ' || v_table_name || '  select /*+ parallel(t,8) */ t.* from chb_submit_status t left join chb_investhold_info_sub sub on t.seqno = sub.sumit_seq where sub.hold_date between to_date('''||minDate||''',''yyyy-MM-dd'') and to_date('''||maxDate||''',''yyyy-MM-dd'')' ; 
  --插入status                         
v_delete_status_sql  VARCHAR2(500)  := 'delete from ' || v_table_name_ori || ' t where exists ( select 1  from chb_investhold_info_sub sub where t.seqno = sub.sumit_seq and sub.hold_date between to_date('''||minDate||''',''yyyy-MM-dd'') and to_date('''||maxDate||''',''yyyy-MM-dd'')) '; 
                                             
--sub表创建并归档
v_create_sub_sql  VARCHAR2(500)  := 'create table ' || v_table_name || ' nologging parallel 8 as select  * from chb_investhold_info_sub where hold_date between to_date('''||minDate||''',''yyyy-MM-dd'') and to_date('''||maxDate||''',''yyyy-MM-dd'')' ; 
--插入sub
v_insert_sub_sql  VARCHAR2(500)  := 'insert /*+ append */  into  ' || v_table_name || '  select /*+ parallel(sub,8) */ * from chb_investhold_info_sub sub where hold_date between to_date('''||minDate||''',''yyyy-MM-dd'') and to_date('''||maxDate||''',''yyyy-MM-dd'')'; 
--插入sub
v_delete_sub_sql  VARCHAR2(500)  := 'delete from ' || v_table_name_ori || ' sub where hold_date between to_date('''||minDate||''',''yyyy-MM-dd'') and to_date('''||maxDate||''',''yyyy-MM-dd'')' ; 
--关闭归档日志,开启并发                   
v_nologging_parrel VARCHAR2(100):='alter table '|| v_table_name || ' nologging parallel 8';

v_nologging_parrel_ori VARCHAR2(100):='alter table '|| v_table_name_ori || ' nologging parallel 8';
--开启归档日志,关闭并发                          
v_logging_noparallel VARCHAR2(100):='alter table '|| v_table_name || ' logging noparallel';

v_logging_noparallel_ori VARCHAR2(200):='alter table '|| v_table_name_ori || ' logging noparallel';

v_dml_session_disable VARCHAR2(200):='alter session disable parallel dml';                  
 BEGIN
--表是否存在 
 select   count(TABLE_NAME) into  v_exists_table from user_tables  where table_name = v_table_name;
 execute immediate v_dml_session_enable;
 --没有则直接创建
IF v_exists_table = 0 THEN
 IF v_intType =0 THEN --check表归档
execute immediate  v_create_check_sql;
execute immediate  v_logging_noparallel;
execute immediate v_nologging_parrel_ori;
execute immediate  v_delete_check_sql;
commit;
execute immediate v_logging_noparallel_ori;
END IF;
 IF v_intType =1 THEN --info表归档
 execute immediate  v_create_info_sql;
execute immediate  v_logging_noparallel;
execute immediate v_nologging_parrel_ori;
execute immediate  v_delete_info_sql;
commit;
execute immediate v_logging_noparallel_ori;
END IF;
 IF v_intType =2 THEN --status表归档
execute immediate  v_create_status_sql;
execute immediate  v_logging_noparallel;
execute immediate v_nologging_parrel_ori;
execute immediate  v_delete_status_sql;
commit;
execute immediate v_logging_noparallel_ori;
END IF;
IF v_intType =3 THEN --sub表归档
execute immediate  v_create_sub_sql;
execute immediate  v_logging_noparallel;
execute immediate v_nologging_parrel_ori;
execute immediate  v_delete_sub_sql;
commit;
execute immediate v_logging_noparallel_ori;
END IF;
ELSE  --表已存在，则直接插入
--开启
IF v_intType =0 THEN --check表归档
execute immediate  v_nologging_parrel;
execute immediate  v_nologging_parrel_ori;
execute immediate  v_insert_check_sql;
commit;
execute immediate  v_logging_noparallel;
execute immediate  v_delete_check_sql;
commit;
execute immediate  v_logging_noparallel_ori;
END IF;

IF v_intType =1 THEN --info表归档
execute immediate  v_nologging_parrel;
execute immediate  v_nologging_parrel_ori;
execute immediate  v_insert_info_sql;
commit;
execute immediate  v_logging_noparallel;
execute immediate  v_delete_info_sql;
commit;
execute immediate  v_logging_noparallel_ori;
END IF;
 
 IF v_intType =2 THEN --status表归档
execute immediate  v_nologging_parrel;
execute immediate  v_nologging_parrel_ori;
execute immediate  v_insert_status_sql;
commit;
execute immediate  v_logging_noparallel;
execute immediate  v_delete_status_sql;
commit;
execute immediate  v_logging_noparallel_ori;
END IF;
 
 IF v_intType =3 THEN --sub表归档
execute immediate  v_nologging_parrel;
execute immediate  v_nologging_parrel_ori;
execute immediate  v_insert_sub_sql;
commit;
execute immediate  v_logging_noparallel;
execute immediate  v_delete_sub_sql;
commit;
execute immediate  v_logging_noparallel_ori;
END IF;
END IF;
execute immediate v_dml_session_disable;
END CHB_BACKUP_HOLD_DATA;