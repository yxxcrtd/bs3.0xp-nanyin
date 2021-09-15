-------初始化数据
insert into SYS_DATE
select (select cur_date from sys_date where institution_code = 'ALL'),'ALL','admin','admin',sysdate,'admin',sysdate,
institution_code from sys_institution;

delete  SYS_DATE where institution_code = 'ALL';