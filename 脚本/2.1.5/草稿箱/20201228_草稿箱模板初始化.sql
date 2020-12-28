--删除sys_trash_info
delete from sys_data_process_mark where exists(select 1 from sys_trash_info where sys_trash_info.seq_no = sys_data_process_mark.workflow_id and sys_trash_info.trash_name like '%公共模板%' );
delete from sys_trash_info where trash_name like '%公共模板%';
--插入 sys_trash_info
