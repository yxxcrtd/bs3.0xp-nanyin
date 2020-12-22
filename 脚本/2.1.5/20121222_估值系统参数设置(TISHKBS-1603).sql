--投资模式设置为非通道且不可编辑
update  sys_para set  param_value = '01', update_flg = '02' where para_code = 'CHL_MODEL_TYPE';