delete from  sys_para t where t.para_code='IS_CHANGE_STATUS_VALNETVALUECONFIRM';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VAL', 'IS_CHANGE_STATUS_VALNETVALUECONFIRM', 'Y', '01', '', '净值确认下发重新跑批时是否修改确认状态、下发状态 Y:是 N 否', 'system', '', sysdate, 'system', sysdate);
commit;