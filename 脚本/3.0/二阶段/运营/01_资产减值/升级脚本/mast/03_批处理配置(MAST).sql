
--计划利率型资产：增加投资目的

---批处理任务
delete from SYS_BATCH_TASK where task_id='527';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('527','VAL','计划类资产公允价值变动-账务明细批处理','','deltRightFairValeAlterationsTask','70','system',to_date('2020/10/10', 'yyyy/mm/dd'), 'system',to_date('2020/10/10', 'yyyy/mm/dd'), '');


---批处理匹配关系表
delete from BOK_MATCH_RLAT where SOURCE='131' and MATCH_TYPE='03' and TARGET in ('1533','1534','1512','1513');
insert into BOK_MATCH_RLAT values ('03','QY','131','1533','system','',to_date('2020/10/09', 'yyyy/mm/dd'), 'system',to_date('2020/10/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','131','1534','system','',to_date('2020/10/09', 'yyyy/mm/dd'), 'system',to_date('2020/10/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','131','1512','system','',to_date('2020/10/09', 'yyyy/mm/dd'), 'system',to_date('2020/10/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','131','1513','system','',to_date('2020/10/09', 'yyyy/mm/dd'), 'system',to_date('2020/10/09', 'yyyy/mm/dd'));

delete from BOK_MATCH_RLAT where SOURCE='24' and MATCH_TYPE='02' and TARGET in ('JHLZC_ZHSYL_CB','JHLZC_GYJZSYL_CB');
insert into BOK_MATCH_RLAT values ('02','QY','24','JHLZC_ZHSYL_CB','system','',to_date('2020/10/09', 'yyyy/mm/dd'), 'system',to_date('2020/10/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('02','QY','24','JHLZC_GYJZSYL_CB','system','',to_date('2020/10/09', 'yyyy/mm/dd'), 'system',to_date('2020/10/09', 'yyyy/mm/dd'));

delete from BOK_MATCH_RLAT where SOURCE='24' and MATCH_TYPE='03' and TARGET in ('1531','1511');
insert into BOK_MATCH_RLAT values ('03','QY','24','1531','system','',to_date('2020/10/09', 'yyyy/mm/dd'), 'system',to_date('2020/10/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','24','1511','system','',to_date('2020/10/09', 'yyyy/mm/dd'), 'system',to_date('2020/10/09', 'yyyy/mm/dd'));


delete from BOK_MATCH_RLAT where SOURCE='24' and MATCH_TYPE='07';
insert into BOK_MATCH_RLAT values ('07','QY','24','','system','',to_date('2020/10/09', 'yyyy/mm/dd'), 'system',to_date('2020/10/09', 'yyyy/mm/dd'));

delete from BOK_MATCH_RLAT where SOURCE='527';
insert into BOK_MATCH_RLAT values ('02','QY','527','JHLZC_CB','system','',to_date('2020/10/10', 'yyyy/mm/dd'), 'system',to_date('2020/10/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('02','QY','527','JHLZC_ZHSYL_CB','system','',to_date('2020/10/10', 'yyyy/mm/dd'), 'system',to_date('2020/10/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('02','QY','527','JHLZC_GYJZSYL_CB','system','',to_date('2020/10/10', 'yyyy/mm/dd'), 'system',to_date('2020/10/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','527','1532','system','',to_date('2020/10/10', 'yyyy/mm/dd'), 'system',to_date('2020/10/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','527','1515','system','',to_date('2020/10/10', 'yyyy/mm/dd'), 'system',to_date('2020/10/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('07','QY','527','','system','',to_date('2020/10/10', 'yyyy/mm/dd'), 'system',to_date('2020/10/10', 'yyyy/mm/dd'));


-- 资产减值 批处理配置


delete from BOK_MATCH_RLAT where SOURCE='4' and TARGET in ('1537','1543');
insert into BOK_MATCH_RLAT values ('03','QY','4','1537','system','',to_date('2020/10/19', 'yyyy/mm/dd'), 'system',to_date('2020/10/19', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','1543','system','',to_date('2020/10/19', 'yyyy/mm/dd'), 'system',to_date('2020/10/19', 'yyyy/mm/dd'));

----同业借款资产:增加减值清理
delete from BOK_MATCH_RLAT where SOURCE='1' and TARGET in ('1116');
insert into BOK_MATCH_RLAT values ('03','QY','1','1116','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));

---计划类收益型资产
delete from BOK_TRADE_ORDER where BUSI_TYPE='09' and ps in ('T75','T77','T76');
insert into BOK_TRADE_ORDER values ('QY','09','T75','2','system','',to_date('2020/10/19', 'yyyy/mm/dd'), 'system',to_date('2020/10/19', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','09','T77','2','system','',to_date('2020/10/19', 'yyyy/mm/dd'), 'system',to_date('2020/10/19', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','09','T76','2','system','',to_date('2020/10/19', 'yyyy/mm/dd'), 'system',to_date('2020/10/19', 'yyyy/mm/dd'));

---同业借款资产
delete from BOK_TRADE_ORDER where BUSI_TYPE='01' and ps in ('T75','T77','T76');
insert into BOK_TRADE_ORDER values ('QY','01','T75','8','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','01','T77','9','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','01','T76','10','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));



--批处理匹配关系表
---债券：增加资产减值
delete from BOK_MATCH_RLAT where SOURCE='3' and MATCH_TYPE='03' and TARGET in ('1362','1367');
insert into BOK_MATCH_RLAT values ('03','QY','3','1362','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','3','1367','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));

----存款：增加资产减值
delete from BOK_MATCH_RLAT where SOURCE='2' and MATCH_TYPE='03' and TARGET in ('1114');
insert into BOK_MATCH_RLAT values ('03','QY','2','1114','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));


--批处理交易处理顺序
---债券：增加资产减值
delete from BOK_TRADE_ORDER where BUSI_TYPE='02' and PS in ('T75','T77','T76');
insert into BOK_TRADE_ORDER values ('QY','02','T75','16','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','02','T77','17','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','02','T76','18','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));


---存款：增加资产减值
delete from BOK_TRADE_ORDER where BUSI_TYPE='03' and PS in ('T75','T77','T76');
insert into BOK_TRADE_ORDER values ('QY','03','T75','11','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','03','T77','12','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','03','T76','13','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));

delete from SYS_BATCH_TASK t where t.TASK_ID = '203';
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('203','VAL','资产减值计提批处理',null,'assetImpairmentProvisionTask',75,'system',null,sysdate,'system',sysdate);

--批处理匹配关系表
----计划类资产交易入账
delete from BOK_MATCH_RLAT where SOURCE='4' and TARGET in ('1530','1535','1510','1514','2712','2713','2738','2739');
insert into BOK_MATCH_RLAT values ('03','QY','4','1530','system','',to_date('2020/10/28', 'yyyy/mm/dd'), 'system',to_date('2020/10/28', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','1535','system','',to_date('2020/10/28', 'yyyy/mm/dd'), 'system',to_date('2020/10/28', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','1510','system','',to_date('2020/10/28', 'yyyy/mm/dd'), 'system',to_date('2020/10/28', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','1514','system','',to_date('2020/10/28', 'yyyy/mm/dd'), 'system',to_date('2020/10/28', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','2712','system','',to_date('2020/10/28', 'yyyy/mm/dd'), 'system',to_date('2020/10/28', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','2713','system','',to_date('2020/10/28', 'yyyy/mm/dd'), 'system',to_date('2020/10/28', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','2738','system','',to_date('2020/10/28', 'yyyy/mm/dd'), 'system',to_date('2020/10/28', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','2739','system','',to_date('2020/10/28', 'yyyy/mm/dd'), 'system',to_date('2020/10/28', 'yyyy/mm/dd'));

delete from BOK_MATCH_RLAT where MATCH_TYPE = '03' and SOURCE = '203' and TARGET in('1113','1115','1359','1364','1540','1536');
insert into BOK_MATCH_RLAT values ('03','QY','203','1113','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','203','1115','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','203','1359','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','203','1364','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','203','1540','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','203','1536','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'));


--批处理交易处理顺序
---拆借、存款类资产、计划类资产、债券资产预处理交易批增加交易处理顺序：收息-还本-到期
delete from BOK_TRADE_ORDER where BUSI_TYPE in ('26','28','25','24');
insert into BOK_TRADE_ORDER values ('QY','26','T09','1','system','',to_date('2020/11/11', 'yyyy/mm/dd'), 'system',to_date('2020/11/11', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','26','T08','2','system','',to_date('2020/11/11', 'yyyy/mm/dd'), 'system',to_date('2020/11/11', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','26','T07','3','system','',to_date('2020/11/11', 'yyyy/mm/dd'), 'system',to_date('2020/11/11', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','28','T09','1','system','',to_date('2020/11/11', 'yyyy/mm/dd'), 'system',to_date('2020/11/11', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','28','T08','2','system','',to_date('2020/11/11', 'yyyy/mm/dd'), 'system',to_date('2020/11/11', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','28','T07','3','system','',to_date('2020/11/11', 'yyyy/mm/dd'), 'system',to_date('2020/11/11', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','25','T09','1','system','',to_date('2020/11/11', 'yyyy/mm/dd'), 'system',to_date('2020/11/11', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','25','T08','2','system','',to_date('2020/11/11', 'yyyy/mm/dd'), 'system',to_date('2020/11/11', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','25','T07','3','system','',to_date('2020/11/11', 'yyyy/mm/dd'), 'system',to_date('2020/11/11', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','24','T09','1','system','',to_date('2020/11/11', 'yyyy/mm/dd'), 'system',to_date('2020/11/11', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','24','T08','2','system','',to_date('2020/11/11', 'yyyy/mm/dd'), 'system',to_date('2020/11/11', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','24','T07','3','system','',to_date('2020/11/11', 'yyyy/mm/dd'), 'system',to_date('2020/11/11', 'yyyy/mm/dd'));

