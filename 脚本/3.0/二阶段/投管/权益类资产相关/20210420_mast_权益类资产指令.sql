--权益类资产指令采番用序列号
CREATE SEQUENCE WTQYZC_SEQUENCE INCREMENT BY 1 START WITH 1 MAXVALUE
9999999999999999999999999999 MINVALUE 1 NOCYCLE CACHE 20 NOORDER;


-----------------------------生成编号规则-----------------------------------------------------------------------------
delete from MST_BUSINESS_ID_RULE where COMMON_BUSINESS_TYPE in('ETR41');
insert into MST_BUSINESS_ID_RULE values ('ETR41_001','ETR41','','','','','','','04','WTQYZC','','','','WTQYZC_SEQUENCE','4','01','','','system','',sysdate, 'system',sysdate);
