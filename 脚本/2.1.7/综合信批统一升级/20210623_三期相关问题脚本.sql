DELETE FROM sys_trash_not_target_page WHERE page_id = 'SvrTransformRule002';
insert into sys_trash_not_target_page (SEQ_NO,page_id) values(sys_guid(),'SvrTransformRule002');