--联社模式
update sys_para t set t.param_value='01' where t.system_code='SYS' and t.para_code='data_authority';
update sys_para t set t.param_value='02' where t.system_code='SYS' and t.para_code='AUTHORITY_TYPE';
update sys_para t set t.param_value='02' where t.system_code='SYS' and t.para_code='is_show_admin_flag';


--联社模式下:以下系统参数默认不可修改
update SYS_PARA t set t.update_flg = '01' where t.system_code='STL' AND t.para_code in ('settle_acct_no','settle_acct_name');
update SYS_PARA t set t.update_flg = '01' where t.system_code='INV' AND t.para_code = 'AUTHORIZE_LIMIT';
update SYS_PARA t set t.update_flg = '01' where t.system_code='VAL' AND t.para_code = 'PRICE_CAL_TYPE';
update SYS_PARA t set t.update_flg = '01' where t.system_code='PTL' AND t.para_code = 'portfolio_handle_userid';


--联社模式执行
delete from sys_datadict_list where dict_code='BATCH_BOOKSET_TYPE';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('BATCH_BOOKSET_TYPE', '1', '账套', 2, '0', 'system', null, '05-3月 -19 12.00.00.000000 上午', 'system', '05-3月 -19 12.00.00.000000 上午', null);

commit;