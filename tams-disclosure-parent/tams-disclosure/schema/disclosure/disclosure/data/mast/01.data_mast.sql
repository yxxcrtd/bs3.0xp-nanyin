/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2018/7/15 星期日 下午 6:31:18                     */
/*==============================================================*/

DELETE FROM REP_FTP_PATH;
INSERT INTO REP_FTP_PATH
VALUES('01','销售文件信息披露','tamsuser','tamsuser','/home/tamsuser/file/01','22','192.168.70.52','admin','',sysdate,'admin',sysdate);
INSERT INTO REP_FTP_PATH
VALUES('02','成立公告','tamsuser','tamsuser','/home/tamsuser/file/02','22','192.168.70.52','admin','',sysdate,'admin',sysdate);
INSERT INTO REP_FTP_PATH
VALUES('03','重大事项公告','tamsuser','tamsuser','/home/tamsuser/file/03','22','192.168.70.52','admin','',sysdate,'admin',sysdate);
INSERT INTO REP_FTP_PATH
VALUES('04','净值披露','tamsuser','tamsuser','/home/tamsuser/file/04','22','192.168.70.52','admin','',sysdate,'admin',sysdate);
INSERT INTO REP_FTP_PATH
VALUES('05','临时性信息披露','tamsuser','tamsuser','/home/tamsuser/file/05','22','192.168.70.52','admin','',sysdate,'admin',sysdate);
INSERT INTO REP_FTP_PATH
VALUES('06','到期公告','tamsuser','tamsuser','/home/tamsuser/file/06','22','192.168.70.52','admin','',sysdate,'admin',sysdate);
INSERT INTO REP_FTP_PATH
VALUES('07','理财产品定期公告','tamsuser','tamsuser','/home/tamsuser/file/07','22','192.168.70.52','admin','',sysdate,'admin',sysdate);
INSERT INTO REP_FTP_PATH
VALUES('08','理财业务报告','tamsuser','tamsuser','/home/tamsuser/file/08','22','192.168.70.52','admin','',sysdate,'admin',sysdate);
INSERT INTO REP_FTP_PATH
VALUES('09','其他公告','tamsuser','tamsuser','/home/tamsuser/file/09','22','192.168.70.52','admin','',sysdate,'admin',sysdate);


DELETE FROM REP_TREE;
INSERT INTO REP_TREE
VALUES('001','','根节点','tamsuser','/home/tamsuser/file/01','22','192.168.70.52','admin','',sysdate,'admin',sysdate);

/*指标管理，被引用的指标：流动性风险指标ldxfxzb*/
update REP_INDEX_PRIMARY set IS_QUOTE = '01' where id = 'ldxfxzb';

/*删除 销售文件信息披露 zhangyf*/
delete from REP_FTP_PATH where id = '02';

/*REP_REPORTINFO:jasper改造，模板配置信息 zhangyf 20190220 start*/
delete from REP_REPORTINFO;
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (1,'BDCGGPL_nb','BDCGGPL_nb','/NB/BDCGGPL_nb','/home/tamsuser/jasper','被动超限公告','PROJECT','JasperSource',null,null,'{endDate:''Date''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (2,'CLGGPL_nb','CLGGPL_nb','/NB/CLGGPL_nb','/home/tamsuser/jasper','成立通知书','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (3,'DQPLGGFB_nb','DQPLGGFB_nb','/NB/DQPLGGFB_nb','/home/tamsuser/jasper','定期披露公告_封闭型','PROJECT','JasperSource',null,null,'{portnum:''String'',startDate:''Date'',endDate:''Date''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (4,'DQPLGGHQ_nb','DQPLGGHQ_nb','/NB/DQPLGGHQ_nb','/home/tamsuser/jasper','定期披露公告_活期型','PROJECT','JasperSource',null,null,'{portnum:''String'',startDate:''Date'',endDate:''Date''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (5,'DQPLGGKF_nb','DQPLGGKF_nb','/NB/DQPLGGKF_nb','/home/tamsuser/jasper','定期披露公告_开放型','PROJECT','JasperSource',null,null,'{portnum:''String'',startDate:''Date'',endDate:''Date''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (6,'DQSYGGFB_nb','DQSYGGFB_nb','/NB/DQSYGGFB_nb','/home/tamsuser/jasper','到期公告封闭式','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (7,'DQSYGGKF_nb','DQSYGGKF_nb','/NB/DQSYGGKF_nb','/home/tamsuser/jasper','到期公告开放式','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (8,'FBZHZQLZCPL_nb','FBZHZQLZCPL_nb','/NB/FBZHZQLZCPL_nb','/home/tamsuser/jasper','非标资产投资情况公告','PROJECT','JasperSource',null,null,'{endDate:''Date''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (9,'FHGGPL_nb','FHGGPL_nb','/NB/FHGGPL_nb','/home/tamsuser/jasper','分红公告','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (10,'FLTZPL_nb','FLTZPL_nb','/NB/FLTZPL_nb','/home/tamsuser/jasper','费率调整','PROJECT','JasperSource',null,null,'{portnum:''String'',endDate:''Date''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (11,'FXGG_nb','FXGG_nb','/NB/FXGG_nb','/home/tamsuser/jasper','发行公告','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (12,'GLJYPL_nb','GLJYPL_nb','/NB/GLJYPL_nb','/home/tamsuser/jasper','关联交易','PROJECT','JasperSource',null,null,'{endDate:''Date''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (13,'JESHSXPL_nb','JESHSXPL_nb','/NB/JESHSXPL_nb','/home/tamsuser/jasper','巨额赎回公告','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (14,'MJJESXTZPL_nb','MJJESXTZPL_nb','/NB/MJJESXTZPL_nb','/home/tamsuser/jasper','募集金额调整上限公告','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (15,'TZFW_nb','TZFW_nb','/NB/TZFW_nb','/home/tamsuser/jasper','投资范围调整公告(高风险至低风险)','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (16,'TZFW2_nb','TZFW2_nb','/NB/TZFW2_nb','/home/tamsuser/jasper','投资范围调整公告(低风险至高风险)','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (17,'TZTZBL_nb','TZTZBL_nb','/NB/TZTZBL_nb','/home/tamsuser/jasper','调整投资比例公告(高风险至低风险)','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (18,'TZTZBL2_nb','TZTZBL2_nb','/NB/TZTZBL2_nb','/home/tamsuser/jasper','调整投资比例公告(低风险至高风险)','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (19,'TZTZZCZL_nb','TZTZZCZL_nb','/NB/TZTZZCZL_nb','/home/tamsuser/jasper','调整投资资产种类公告(高风险至低风险)','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (20,'TZTZZCZL2_nb','TZTZZCZL2_nb','/NB/TZTZZCZL2_nb','/home/tamsuser/jasper','调整投资资产种类公告(低风险至高风险)','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values (21,'XDZCSYQPL_nb','XDZCSYQPL_nb','/NB/XDZCSYQPL_nb','/home/tamsuser/jasper','信贷资产收益权类资产投资情况公告','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
Insert into REP_REPORTINFO (ID,REPORTID,URI,FOLDER,REPOSITORY,REPORTDESC,DATASOURCE,GENERATOR,USERPWD,USERNAME,REPORTPARAMS,CREATE_DEPT,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES (22,'XHSQPL_nb','XHSQPL_nb','/NB/XHSQPL_nb','/home/tamsuser/jasper','销户通知书','PROJECT','JasperSource',null,null,'{portnum:''String''}',null,null,sysdate,null,sysdate);
/*REP_REPORTINFO:jasper改造，模板配置信息 zhangyf 20190220 end*/
