delete from BOK_PROFIT_REP_CONFIG t where t.proj_code in ('1.1','1.1.11','1.1.11.1','1.1.11.2');
insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE, PROJ_NAME, PROJ_LEVEL, FORMULA, SHOW_FLG, SHOW_ORDER, SUBJECT_NO, CRDR_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('1.1', '1.利息收入', 1, '1.1.1+1.1.2+1.1.4+1.1.6+1.1.7+1.1.8+1.1.9+1.1.10+1.1.11', 'Y', 1, '', '', 'sys', '', sysdate, 'sys', sysdate);

insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE, PROJ_NAME, PROJ_LEVEL, FORMULA, SHOW_FLG, SHOW_ORDER, SUBJECT_NO, CRDR_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('1.1.11', '其他债权利息收入', 1, '1.1.11.1+1.1.11.2', 'Y', 11, '', '', 'sys', '', sysdate, 'sys', sysdate);

insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE, PROJ_NAME, PROJ_LEVEL, FORMULA, SHOW_FLG, SHOW_ORDER, SUBJECT_NO, CRDR_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('1.1.11.1', '公允价值变动类其他债权投资利息收入', 0, 'TdyBCAmt-TdyBDAmt+TdyBCoDAmt-TdyBCoCAmt', 'N', 12, '611120', 'C', 'sys', '', sysdate, 'sys', sysdate);

insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE, PROJ_NAME, PROJ_LEVEL, FORMULA, SHOW_FLG, SHOW_ORDER, SUBJECT_NO, CRDR_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('1.1.11.2', '摊余成本类其他债权投资利息收入', 0, 'TdyBCAmt-TdyBDAmt+TdyBCoDAmt-TdyBCoCAmt', 'N', 14, '601108', 'C', 'sys', '', sysdate, 'sys', sysdate);


delete from BOK_PROFIT_REP_CONFIG t where t.proj_code in ('1.2','1.2.11','1.2.11.1','1.2.11.2','1.2.11.3');
insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE, PROJ_NAME, PROJ_LEVEL, FORMULA, SHOW_FLG, SHOW_ORDER, SUBJECT_NO, CRDR_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('1.2', '2.投资收益', 1, '1.2.1+1.2.2+1.2.3+1.2.4+1.2.5+1.2.7+1.2.8+1.2.9+1.2.10+1.2.11', 'Y', 23, '', '', 'sys', '', sysdate, 'sys', sysdate);

insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE, PROJ_NAME, PROJ_LEVEL, FORMULA, SHOW_FLG, SHOW_ORDER, SUBJECT_NO, CRDR_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('1.2.11', '其他债权投资收益', 1, '1.2.11.1+1.2.11.2+1.2.11.3', 'Y', 11, '', '', 'sys', '', sysdate, 'sys', sysdate);

insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE, PROJ_NAME, PROJ_LEVEL, FORMULA, SHOW_FLG, SHOW_ORDER, SUBJECT_NO, CRDR_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('1.2.11.1', '公允价值变动类其他债权投资收益', 0, 'TdyBCAmt-TdyBDAmt+TdyBCoDAmt-TdyBCoCAmt', 'N', 12, '611111', 'C', 'sys', '', sysdate, 'sys', sysdate);

insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE, PROJ_NAME, PROJ_LEVEL, FORMULA, SHOW_FLG, SHOW_ORDER, SUBJECT_NO, CRDR_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('1.2.11.2', '摊余成本类其他债权投资收益', 0, 'TdyBCAmt-TdyBDAmt+TdyBCoDAmt-TdyBCoCAmt', 'N', 13, '611113', 'C', 'sys', '', sysdate, 'sys', sysdate);

insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE, PROJ_NAME, PROJ_LEVEL, FORMULA, SHOW_FLG, SHOW_ORDER, SUBJECT_NO, CRDR_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('1.2.11.3', '综合收益类其他债权投资收益', 0, 'TdyBCAmt-TdyBDAmt+TdyBCoDAmt-TdyBCoCAmt', 'N', 14, '611112', 'C', 'sys', '', sysdate, 'sys', sysdate);