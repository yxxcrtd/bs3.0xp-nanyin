alter table REP_ASSET_BOND add rep_sec_nature VARCHAR2(50);
comment on column REP_ASSET_BOND.rep_sec_nature is '债券性质：债性、股性';
delete from SYS_DATADICT where DICT_CODE = 'REP_SEC_NATURE' and SYSTEM_CODE = 'SVR';
insert into SYS_DATADICT values ('REP_SEC_NATURE','SVR','债券性质','','02','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where DICT_CODE ='REP_SEC_NATURE';
insert into SYS_DATADICT_LIST values ('REP_SEC_NATURE','01','债性','1','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('REP_SEC_NATURE','02','股性','2','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
