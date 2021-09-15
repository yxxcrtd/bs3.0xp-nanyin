------增加字段
Alter Table sys_date Add INSTITUTION_CODE varchar2(32) default 'ALL';

alter table SYS_DATE drop constraint PK_SYS_DATE;
--drop index PK_SYS_DATE ;

alter table SYS_DATE add constraint PK_SYS_DATE primary key (INSTITUTION_CODE,CUR_DATE, SYSTEM_CODE);
