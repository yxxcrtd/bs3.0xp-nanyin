-- 银行模式执行脚本
Alter Table sys_date Add INSTITUTION_CODE varchar2(32) default 'ALL';
alter table SYS_DATE drop constraint PK_SYS_DATE;
alter table SYS_DATE add constraint PK_SYS_DATE primary key (INSTITUTION_CODE,CUR_DATE, SYSTEM_CODE);

-- 联社模式执行脚本
Alter Table sys_date Add INSTITUTION_CODE varchar2(32) default 'ALL';
alter table SYS_DATE drop constraint PK_SYS_DATE;
alter table SYS_DATE add constraint PK_SYS_DATE primary key (INSTITUTION_CODE,CUR_DATE, SYSTEM_CODE);
insert into SYS_DATE select (select cur_date from sys_date where institution_code = 'ALL'),'ALL','admin','admin',sysdate,'admin',sysdate,
institution_code from sys_institution;
delete SYS_DATE where institution_code = 'ALL';
DELETE FROM SYS_PARA WHERE PARA_CODE = 'LIANSHE_ONLYSLS_UPDATE_SYSDATE';
INSERT INTO SYS_PARA values ('SYS','LIANSHE_ONLYSLS_UPDATE_SYSDATE','02','01','','联社模式是否只能省联社用户更新平台日（01:是、02:否）','system','',to_date('2021/08/20', 'yyyy/mm/dd'), 'system',to_date('2021/08/20', 'yyyy/mm/dd'), null, '01');



