--稳定版升级脚本20190705
alter table JXU_HOLIDAYLS ADD (FXID VARCHAR2(6));
update JXU_HOLIDAYLS set FXID=HOLIDAYSD;
commit;
alter table JXU_HOLIDAYLS drop (HOLIDAYSD);
alter table JXU_HOLIDAYLS rename  column "FXID" to "HOLIDAYSD";
update JXU_HOLIDAYLS set HOLIDAYSD='' where HOLIDAYSD='0';
commit;
alter table ADPT_INTERFACESOURCE ADD (FXID VARCHAR2(6));
update ADPT_INTERFACESOURCE set FXID=HOLIDAY;
commit;
alter table ADPT_INTERFACESOURCE drop (HOLIDAY);
alter table ADPT_INTERFACESOURCE rename  column "FXID" to "HOLIDAY";
update ADPT_INTERFACESOURCE set HOLIDAY='' where HOLIDAY='0';
commit;
alter table JXU_HOLIDAYSD ADD (FXID VARCHAR2(6));
update JXU_HOLIDAYSD set FXID=ID;
commit;
alter table JXU_HOLIDAYSD drop (ID);
alter table JXU_HOLIDAYSD rename  column FXID to ID;
alter table JXU_HOLIDAYSD modify "ID" not null;
alter table JXU_HOLIDAYSD add constraint PK_JMU_HOLIDAYSDID primary key (ID) enable validate;


alter table ADPT_INTERFACESOURCE add OUTFILEPATH VARCHAR2(255);
alter table ADPT_INTERFACESOURCE add OUTFILENAME VARCHAR2(100);
alter table ADPT_INTERFACESOURCE add OUTFILETYPE  NUMBER(3,0) DEFAULT 0;
alter table ADPT_INTERFACESOURCE add OUTFILEENCODETYPE NUMBER(3,0) default 0;
alter table ADPT_INTERFACESOURCE add OUTFILETITLEFLAG NUMBER(1,0) default 0;
alter table ADPT_INTERFACESOURCE add AFTERCALLCLASS VARCHAR2(100) default '0';

--导出文件名要用到的序列
create sequence FILE_SEQ minvalue 1 maxvalue 9999 start with 21 increment by 1 cache 20 cycle;

--适配器增强分组功能
alter table ADPT_INTERFACESOURCE add PROJECTGROUP VARCHAR2(100) default 'Interface-Auto';
alter table ADPT_INTERFACESOURCE add WRITESPILITCHAR VARCHAR2(100);