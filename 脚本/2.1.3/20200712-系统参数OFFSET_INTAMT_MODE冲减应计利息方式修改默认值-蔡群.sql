-- 系统参数  OFFSET_INTAMT_MODE  冲减应计利息方式 改成 按实付利息冲减  01、按比例冲减  02、按实付利息冲减。改成默认02
update sys_para set param_value = '02' where para_code = 'OFFSET_INTAMT_MODE';
