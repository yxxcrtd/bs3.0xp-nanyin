-- 系统参数9个特殊参数处理脚本  add by wj 20200708 start
update SYS_PARA t set t.update_flg ='02';
update SYS_PARA t set t.update_flg = '01' where t.system_code='STL' AND t.para_code in ('settle_acct_no','settle_acct_name');
update SYS_PARA t set t.update_flg = '01' where t.system_code='INV' AND t.para_code = 'AUTHORIZE_LIMIT';
update SYS_PARA t set t.update_flg = '01' where t.system_code='VAL' AND t.para_code = 'PRICE_CAL_TYPE';
update SYS_PARA t set t.update_flg = '01' where t.system_code='PTL' AND t.para_code = 'portfolio_handle_userid';
update SYS_PARA t set t.update_flg = '01' where t.system_code='STL' AND t.para_code = 'prod_tru_acct_no';
update SYS_PARA t set t.update_flg = '01' where t.system_code='STL' AND t.para_code = 'prod_tru_acct_name';
update SYS_PARA t set t.update_flg = '01' where t.system_code='STL' AND t.para_code = 'lending_acct_no';
update SYS_PARA t set t.update_flg = '01' where t.system_code='STL' AND t.para_code = 'lending_acct_name';
-- 系统参数9个特殊参数处理脚本  add by wj 20200708 start