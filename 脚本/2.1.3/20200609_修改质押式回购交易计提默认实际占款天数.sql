/*add by xr for TISHKBS-637  (交易所回购自19年之后明确发文确定按实际占款天数)--修改默认配置 start*/
update SYS_PARA set data_type='02' where  para_code='SE_REP_DAY_TYPE';
/*add by xr for TISHKBS-637  (交易所回购自19年之后明确发文确定按实际占款天数)--修改默认配置 end*/