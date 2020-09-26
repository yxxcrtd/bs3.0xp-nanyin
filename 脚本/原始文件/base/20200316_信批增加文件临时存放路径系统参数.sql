/**  增加系统参数  poi临时模板存放路径  add by  dongxiangyang  202020316*/
delete  from SYS_PARA  where   PARA_CODE='TEMPLATE_TMEP_PATH';
Insert into SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('DIS','TEMPLATE_TMEP_PATH','D:/home/tams/temp',null,null,'poi临时模板存放路径','system',null,to_timestamp('16-1月 -19 03.13.35.000000000 下午','DD-MON-RR HH.MI.SSXFF AM'),'fqq',to_timestamp('06-3月 -20 10.52.18.140000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
commit;