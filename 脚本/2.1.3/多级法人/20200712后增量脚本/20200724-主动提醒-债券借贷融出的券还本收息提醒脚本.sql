
/*主动提醒配置表:债券借贷融出的券还本收息提醒*/
delete from SYS_ACTIVEREMINDER_SET where REM_TYPE = 'R3S16';
Insert into SYS_ACTIVEREMINDER_SET (REM_TYPE,DAYS,URL,LABLE,DEALF,CLOSF,REMARK,MENU_ID) 
values ('R3S16',0,'/period/trdbondlendtargetduration/TrdBondLendTargetDuration001/init','TrdBondLendTargetDuration001','Y','Y',null,'ASSET_DURATION_MANAGEMENT_001_001_014');
