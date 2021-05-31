--新增系统参数【是否调用头寸接口平台】
DELETE FROM SYS_PARA  WHERE PARA_CODE='IS_CALLINTERF_POSITION';
insert into SYS_PARA values ('VAL','IS_CALLINTERF_POSITION','Y','01','数据字典是否','是否调用头寸接口平台：Y 是  N  否','system','',to_date('2021/05/18', 'yyyy/mm/dd'), 'system',to_date('2021/05/18', 'yyyy/mm/dd'), 'ALL','01');

--新增系统参数【头寸接口平台-分组编码】
DELETE FROM SYS_PARA  WHERE PARA_CODE='CALLINTERF_GPCODE';
insert into SYS_PARA values ('VAL','CALLINTERF_GPCODE','YY2TG','','','头寸接口平台-分组编码','system','',to_date('2021/05/18', 'yyyy/mm/dd'), 'system',to_date('2021/05/18', 'yyyy/mm/dd'), 'ALL','01');

--新增系统参数【头寸接口平台-触发器编码】
DELETE FROM SYS_PARA  WHERE PARA_CODE='CALLINTERF_TRIGGER';
insert into SYS_PARA values ('VAL','CALLINTERF_TRIGGER','yy2tg_toucun','','','头寸接口平台-触发器编码','system','',to_date('2021/05/18', 'yyyy/mm/dd'), 'system',to_date('2021/05/18', 'yyyy/mm/dd'), 'ALL','01');
