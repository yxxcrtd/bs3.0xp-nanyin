DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'OPR_TYPE';
insert into SYS_DATADICT values ('OPR_TYPE','SYS','操作类型','OPR_TYPE','02','system','',sysdate, 'system',sysdate);
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'OPR_TYPE';
insert into SYS_DATADICT_LIST values ('OPR_TYPE','01','新建','1','0','system','',sysdate, 'system',sysdate,null);
insert into SYS_DATADICT_LIST values ('OPR_TYPE','02','修改','2','0','system','',sysdate, 'system',sysdate,null);
insert into SYS_DATADICT_LIST values ('OPR_TYPE','03','撤回','3','0','system','',sysdate, 'system',sysdate,null);
insert into SYS_DATADICT_LIST values ('OPR_TYPE','04','关闭','4','0','system','',sysdate, 'system',sysdate,null);
insert into SYS_DATADICT_LIST values ('OPR_TYPE','05','退回','5','0','system','',sysdate, 'system',sysdate,null);
insert into SYS_DATADICT_LIST values ('OPR_TYPE','06','分发','6','0','system','',sysdate, 'system',sysdate,null);