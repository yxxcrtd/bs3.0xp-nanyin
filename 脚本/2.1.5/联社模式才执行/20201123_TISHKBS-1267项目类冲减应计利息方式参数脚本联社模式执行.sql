delete from sys_para where SYSTEM_CODE='VAL' and PARA_CODE ='OFFSET_INTAMT_MODE_JHL';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, UPDATE_FLG, INSTITUTION_CODE)
values ('VAL', 'OFFSET_INTAMT_MODE_JHL', '01', '', '', '项目类冲减应计利息方式   01、按比例冲减  02、按实付利息冲减', 'system', '', sysdate, 'system', sysdate, '02', '');
