--添加‘投资者分类’参数
INSERT INTO SYS_DATADICT VALUES ('HOLDER_CLASSIFY','REP','投资者分类',null,'02','system',null,SYSDATE,'system',SYSDATE);
INSERT INTO SYS_DATADICT_LIST VALUES ('HOLDER_CLASSIFY','01','不特定社会公众','1','0','system',null,sysdate,'system',sysdate,null);
INSERT INTO SYS_DATADICT_LIST VALUES ('HOLDER_CLASSIFY','02','合格投资者','2','0','system',null,sysdate,'system',sysdate,null);

--数据库新增‘投资者分类’字段
ALTER TABLE CHB_EQU_HOLD ADD HOLDER_CLASSIFY char(2);
comment on column CHB_EQU_HOLD.HOLDER_CLASSIFY is '投资者分类';