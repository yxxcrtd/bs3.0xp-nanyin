--权益类资产交易采番用序列号
CREATE SEQUENCE JYQYZC_SEQUENCE INCREMENT BY 1 START WITH 1 MAXVALUE
9999999999999999999999999999 MINVALUE 1 NOCYCLE CACHE 20 NOORDER;


-----------------------------生成编号规则-----------------------------------------------------------------------------
delete from MST_BUSINESS_ID_RULE where COMMON_BUSINESS_TYPE in('TRD77');
insert into MST_BUSINESS_ID_RULE values ('TRD77_001','TRD77','','','','','','','04','JYQYZC','','','','JYQYZC_SEQUENCE','4','01','','','system','',sysdate, 'system',sysdate);
