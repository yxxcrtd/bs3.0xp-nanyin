DELETE FROM SYS_DATADICT WHERE DICT_CODE  = 'DELIVERY_STATUS';
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'DELIVERY_STATUS';
insert into SYS_DATADICT values ('DELIVERY_STATUS','TG','交收状态','','02','system','',sysdate, 'system',sysdate);
insert into SYS_DATADICT_LIST values ('DELIVERY_STATUS','01','未交收','1','0','system','',sysdate, 'system',sysdate,'');
insert into SYS_DATADICT_LIST values ('DELIVERY_STATUS','02','已交收','1','0','system','',sysdate, 'system',sysdate,'');
insert into SYS_DATADICT_LIST values ('DELIVERY_STATUS','03','交收延期','1','0','system','',sysdate, 'system',sysdate,'');
insert into SYS_DATADICT_LIST values ('DELIVERY_STATUS','04','交收失败','1','0','system','',sysdate, 'system',sysdate,'');



DELETE FROM SYS_MENU WHERE MENU_ID IN ('SETTLE_MANAGEMENT_001_003','SETTLE_MANAGEMENT_001_003_001');
insert into SYS_MENU values ('SETTLE_MANAGEMENT_001_003','STL','','SETTLE_MANAGEMENT_001','','2','02','交收管理','system','',sysdate, 'system',sysdate);
insert into SYS_MENU values ('SETTLE_MANAGEMENT_001_003_001','STL','','SETTLE_MANAGEMENT_001_003','ManualSettle001','1','02','手工交收','system','',sysdate, 'system',sysdate);




DELETE FROM SYS_PAGE sp2 WHERE PAGE_ID = 'ManualSettle001';
insert into SYS_PAGE values ('ManualSettle001','TRD','/invest/trade/manualsettle/ManualSettle001','invest/trade/manualsettle/ManualSettle001','SETTLE_MANAGEMENT_001_003_001','','','02','01','手工交收','system','',sysdate, 'system',sysdate);

DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID = 'ManualSettle001';
insert into SYS_WFL_BUSINESS_MODULE values ('ManualSettle001','ManualSettle001_btn_settle','noneApproval','system','0001',sysdate, 'system',sysdate,'ALL','02');


DELETE FROM SYS_DATADICT WHERE DICT_CODE  = 'DELIVERY_BUS_TYPE';
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'DELIVERY_BUS_TYPE';
insert into SYS_DATADICT values ('DELIVERY_BUS_TYPE','TG','交收业务类别','','02','system','',sysdate, 'system',sysdate);
insert into SYS_DATADICT_LIST values ('DELIVERY_BUS_TYPE','01','上交所固定收益平台债券买入','1','0','system','',sysdate, 'system',sysdate,'');
insert into SYS_DATADICT_LIST values ('DELIVERY_BUS_TYPE','02','上交所固定收益平台债券卖出','1','0','system','',sysdate, 'system',sysdate,'');





DELETE FROM SYS_DATADICT WHERE DICT_CODE  = 'DELIVERY_OP_TYPE';
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'DELIVERY_OP_TYPE';
insert into SYS_DATADICT values ('DELIVERY_OP_TYPE','TG','交收操作类型','','02','system','',sysdate, 'system',sysdate);
insert into SYS_DATADICT_LIST values ('DELIVERY_OP_TYPE','01','交收','1','0','system','',sysdate, 'system',sysdate,'');
insert into SYS_DATADICT_LIST values ('DELIVERY_OP_TYPE','02','回溯','1','0','system','',sysdate, 'system',sysdate,'');
insert into SYS_DATADICT_LIST values ('DELIVERY_OP_TYPE','03','交收延期','1','0','system','',sysdate, 'system',sysdate,'');
insert into SYS_DATADICT_LIST values ('DELIVERY_OP_TYPE','04','交收失败','1','0','system','',sysdate, 'system',sysdate,'');






