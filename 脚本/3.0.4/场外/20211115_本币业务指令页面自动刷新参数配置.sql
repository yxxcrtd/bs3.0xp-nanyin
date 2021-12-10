delete from sys_para where SYSTEM_CODE = 'INV' and PARA_CODE in ('cfetsEtrIbBondinfo_positionTable_refresh_period','cfetsEtrIbBondinfo_etrTable_refresh_period');
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('INV', 'cfetsEtrIbBondinfo_positionTable_refresh_period ', '120000', '01', null, '本币业务指令画面持仓列表区域自动刷新时间(ms)', 'system', null, sysdate, 'system', sysdate, null, '01');

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('INV', 'cfetsEtrIbBondinfo_etrTable_refresh_period ', '120000', '01', null, '本币业务指令画面指令列表区域自动刷新时间(ms)', 'system', null, sysdate, 'system', sysdate, null, '01');