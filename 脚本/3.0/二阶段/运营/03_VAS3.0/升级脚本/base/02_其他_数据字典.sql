
--新增数据字典【权限模式】
delete from SYS_DATADICT where DICT_CODE = 'AUTHORITY_TYPE';
delete from SYS_DATADICT_LIST where DICT_CODE = 'AUTHORITY_TYPE';
insert into SYS_DATADICT values ('AUTHORITY_TYPE','SYS','权限模式','','02','system',null,sysdate, 'system',sysdate);
insert into SYS_DATADICT_LIST values ('AUTHORITY_TYPE', '01', '银行模式', 1, '0', 'system', null, sysdate, 'system', sysdate, 'ALL');
insert into SYS_DATADICT_LIST values ('AUTHORITY_TYPE', '02', '联社模式', 2, '0', 'system', null, sysdate, 'system', sysdate, 'ALL');
