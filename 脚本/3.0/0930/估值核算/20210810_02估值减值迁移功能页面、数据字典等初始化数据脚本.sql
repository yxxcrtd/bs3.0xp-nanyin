-- 减值模型-批处理画面
DELETE FROM SYS_MENU where PAGE_ID = 'ModImpaireBatchTask001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_011_008', 'VAL', null, 'VALUATION_001_011', 'ModImpaireBatchTask001', 7, '2', '减值批处理任务', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

delete from sys_page where PAGE_ID = 'ModImpaireBatchTask001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModImpaireBatchTask001', 'VAL', '/model/impairment/batch/batchtask/ModImpaireBatchTask001', 'lossmodel/batch/batchtask/ModImpaireBatchTask001', 'VALUATION_001_011_008', null, null, '02', '01', '减值批处理任务', 'system', null, '05-AUG-21 03.49.51.000000 PM', 'system', '05-AUG-21 03.49.51.000000 PM');

delete from sys_page where PAGE_ID = 'ModImpaireBatchTask002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModImpaireBatchTask002', 'VAL', '/model/impairment/batch/batchtask/ModImpaireBatchTask002', 'lossmodel/impairment/batch/batchtask/ModImpaireBatchTask002', null, null, null, '02', '01', '减值批处理任务-详情', 'system', null, '05-AUG-21 03.49.51.000000 PM', 'system', '05-AUG-21 03.49.51.000000 PM');

-- LGD违约损失率管理初始化数据
delete from MOD_LGD_CONFIG;
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-070E-2FF8-E055-000000000001','F01','001',null,null,'system',null,sysdate,'admin',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-070F-2FF8-E055-000000000001','F01','002',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0710-2FF8-E055-000000000001','F01','003',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0711-2FF8-E055-000000000001','F01','004',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0712-2FF8-E055-000000000001','F01','005',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0713-2FF8-E055-000000000001','F01','006',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0714-2FF8-E055-000000000001','F01','007',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0715-2FF8-E055-000000000001','F01','008',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0716-2FF8-E055-000000000001','F01','009',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0717-2FF8-E055-000000000001','F01','010',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0718-2FF8-E055-000000000001','F01','011',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0719-2FF8-E055-000000000001','F01','012',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-071A-2FF8-E055-000000000001','F01','013',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-071B-2FF8-E055-000000000001','F01','014',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-071C-2FF8-E055-000000000001','F01','015',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-071D-2FF8-E055-000000000001','F01','016',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-071E-2FF8-E055-000000000001','F01','017',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-071F-2FF8-E055-000000000001','F01','018',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0720-2FF8-E055-000000000001','F01','020',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0721-2FF8-E055-000000000001','F01','021',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0722-2FF8-E055-000000000001','F01','022',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0723-2FF8-E055-000000000001','F01','139',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0724-2FF8-E055-000000000001','F01','140',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0725-2FF8-E055-000000000001','F01','141',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0726-2FF8-E055-000000000001','F01','142',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0727-2FF8-E055-000000000001','F01','143',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0728-2FF8-E055-000000000001','F01','158',null,null,'system',null,sysdate,'system',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0729-2FF8-E055-000000000001','F18',null,'Y',null,'system',null,sysdate,'admin',sysdate);
Insert into MOD_LGD_CONFIG (SEQNO,ASSET_TYPE,ASSET_DETAIL_TYPE,IS_CONTAIN_PLEDGE,LGD,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('C5CC868A-0730-2FF8-E055-000000000001','F18',null,'N',null,'system',null,sysdate,'system',sysdate);
commit;

--MOD_ASSET_MAPPING数据初始化脚本
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','001','F01','01','01','001','001','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','002','F01','01','02','002','002','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','003','F01','02','03','003','003','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','004','F01','02','04','004','004','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','005','F01','02','05','005','005','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','006','F01','02','06','006','006','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','158','F01','02','06','126','158','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','007','F01','02','07','007','007','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','008','F01','02','08','008','008','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','009','F01','02','08','009','009','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','010','F01','02','09','010','010','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','011','F01','02','10','011','011','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','012','F01','02','10','012','012','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','013','F01','02','10','013','013','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','014','F01','02','10','014','014','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','015','F01','02','11','015','015','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','016','F01','02','11','016','016','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','017','F01','02','11','017','017','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','018','F01','02','11','018','018','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','139','F01','02','12','127','139','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','140','F01','02','12','128','140','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','141','F01','02','12','129','141','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','020','F01','02','13','020','020','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','021','F01','03','14','021','021','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','022','F01','03','15','022','022','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','023','F01','04','16','023','142','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F01','024','F01','04','16','023','143','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','08','F06','08','64','039','039','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','08','F06','08','64','040','040','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','08','F06','08','65','039','039','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','08','F06','08','65','040','040','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','08','F06','08','30','042','042','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','31','043','043','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','31','044','044','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','31','045','045','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','31','046','046','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','32','047','047','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','33','048','048','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','33','049','049','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','33','050','050','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','33','051','051','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','33','052','052','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','33','053','053','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','33','054','054','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','34','048','048','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','34','049','049','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','34','050','050','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','34','051','051','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','34','052','052','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','34','053','053','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','34','055','055','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','35','056','056','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','36','057','057','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','36','058','058','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','36','059','059','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','36','060','060','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','36','061','061','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','36','062','062','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','36','063','063','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','36','064','064','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','36','065','065','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','36','066','066','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','36','067','067','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','36','068','068','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','37','069','069','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','38','070','070','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','39','071','048','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','39','071','049','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','39','071','050','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','39','071','051','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','39','071','071','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','39','072','048','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','39','072','049','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','39','072','050','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','39','072','051','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','39','072','072','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','39','124','138','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','40','073','073','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','074','074','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','075','075','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','076','076','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','077','077','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','078','078','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','079','079','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','080','144','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','081','049','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','081','048','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','081','051','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','081','083','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','082','049','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','082','048','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','082','051','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','082','084','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','083','085','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','130','145','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','130','146','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','130','147','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','130','148','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','130','138','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','131','149','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','131','150','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));
insert into MOD_ASEET_MAPPING(SEQ_NO,MAPPING_TYPE,input1,input2,output1,output2,output3,output4 ,output5,create_user,create_dept ,create_time,update_user,update_time) VALUES(sys_guid(),'01','F18','09','F06','09','41','131','151','system',' ',to_date('2021/7/1', 'yyyy/mm/dd'),'system',to_date('2021/7/1', 'yyyy/mm/dd'));


-- PD违约概率管理页面
delete from sys_page t where t.PAGE_ID='PdDefaultManager001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_011_004', 'VAL', null, 'VALUATION_001_011', 'PdDefaultManager001', 3, '2', 'PD违约概率管理', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

--前瞻因子配置页面
delete from sys_page t where t.PAGE_ID='PdFactorConfig001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig001', 'RSK', '/model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig001', 'lossmodel/pddefaultmanager/pdfactorconfig/PdFactorConfig001', null, null, null, '02', '01', '前瞻因子维护', 'system', null, '05-AUG-21 03.09.00.000000 PM', 'system', '05-AUG-21 03.09.00.000000 PM');

delete from sys_page t where t.PAGE_ID='PdFactorConfig002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig002', 'RSK', '/model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig002', 'lossmodel/pddefaultmanager/pdfactorconfig/PdFactorConfig002', null, null, null, '02', '01', '前瞻因子维护新建', 'system', null, '05-AUG-21 03.09.00.000000 PM', 'system', '05-AUG-21 03.09.00.000000 PM');

delete from sys_page t where t.PAGE_ID='PdFactorWeightConfig003';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorWeightConfig003', 'RSK', '/model/impairment/pddefaultmanager/pdfactorconfig/PdFactorWeightConfig003', 'lossmodel/pddefaultmanager/pdfactorconfig/PdFactorWeightConfig003', null, null, null, '02', '01', '前瞻因子权重配置', 'system', null, '05-AUG-21 03.09.00.000000 PM', 'system', '05-AUG-21 03.09.00.000000 PM');

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig002' and t.ELEMENT_ID='PdFactorConfig002_btn_save';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig002', 'PdFactorConfig002_btn_save', '01', '02', null, '保存', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig002' and t.ELEMENT_ID='PdFactorConfig002_btn_submit';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig002', 'PdFactorConfig002_btn_submit', '01', '02', null, '提交', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig001' and t.ELEMENT_ID='PdFactorConfig001_btn_clear';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig001', 'PdFactorConfig001_btn_clear', '01', '02', null, '重置', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig001' and t.ELEMENT_ID='PdFactorConfig001_btn_add';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig001', 'PdFactorConfig001_btn_add', '01', '01', null, '新建', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig001' and t.ELEMENT_ID='PdFactorConfig001_btn_search';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig001', 'PdFactorConfig001_btn_search', '01', '01', null, '查询', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig001' and t.ELEMENT_ID='PdFactorConfig001_btn_delete';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig001', 'PdFactorConfig001_btn_delete', '01', '01', null, '删除', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig001' and t.ELEMENT_ID='PdFactorConfig001_btn_export';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig001', 'PdFactorConfig001_btn_export', '01', '01', null, '导出', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorWeightConfig003' and t.ELEMENT_ID='PdFactorWeightConfig003_btn_submit';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorWeightConfig003', 'PdFactorWeightConfig003_btn_submit', '01', '02', null, '提交', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));


delete from sys_wfl_business_module t where t.PAGE_ID='PdFactorConfig002' and t.ELEMENT_ID='PdFactorConfig002_btn_save';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig002', 'PdFactorConfig002_btn_save', 'oneApproRSK', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_wfl_business_module t where t.PAGE_ID='PdFactorConfig002' and t.ELEMENT_ID='PdFactorConfig002_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig002', 'PdFactorConfig002_btn_submit', 'oneApproRSK', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_wfl_business_module t where t.PAGE_ID='PdFactorConfig002' and t.ELEMENT_ID='PdFactorConfig001_btn_delete';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig002', 'PdFactorConfig001_btn_delete', 'oneApproRSK', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_wfl_business_module t where t.PAGE_ID='PdFactorWeightConfig003' and t.ELEMENT_ID='PdFactorWeightConfig003_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorWeightConfig003', 'PdFactorWeightConfig003_btn_submit', 'oneApproRSK', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));


--前瞻因子维护配置流程BusinessType
delete from sys_datadict_list t where t.DICT_CODE='COMMON_BUSINESS_TYPE' and t.ITEM_CODE='PDFACTOR';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_RSKUE, ORDER_NO, DEFAULT_RSKUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'PDFACTOR', '前瞻因子维护', 1221, '0', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_datadict_list t where t.DICT_CODE='COMMON_BUSINESS_TYPE' and t.ITEM_CODE='PDWEIGHT';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_RSKUE, ORDER_NO, DEFAULT_RSKUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'PDWEIGHT', '前瞻因子权重配置', 1222, '0', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));
commit;

--估值模型-二叉树模型批处理任务配置
delete from SYS_BATCH_TASK where TASK_ID = '2021';
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,IS_BATCH_START_TARGET) values ('2021','MODEL','二叉树模型批处理',null,'binaryTreeModelTask',60,'system',null,to_timestamp('2021-07-05 15:58:28','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2021-07-05 15:58:28','YYYY-MM-DD HH24:MI:SS'),'02');

--估值模型-二叉树模型系统参数配置
----菜单脚本----
delete from SYS_MENU where PAGE_ID='ModBintreeConfig001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_010_003', 'VAL', null, 'VALUATION_001_010', 'ModBintreeConfig001', 3, '2', '二叉树模型配置', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

----页面脚本----
delete from SYS_PAGE where PAGE_ID in('ModBintreeConfig001','ModBintreeConfig002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModBintreeConfig001', 'VAL', '/valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig001', 'valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig001', 'VALUATION_001_010_003', null, null, '02', '01', '二叉树模型参数配置', 'system', null, '05-AUG-21 10.50.26.000000 AM', 'system', '05-AUG-21 10.50.26.000000 AM');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModBintreeConfig002', 'VAL', '/modelvaluation/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig002', 'valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig002', null, null, null, '02', '01', '二叉树模型参数配置新建', 'system', null, '01-JUL-21 04.29.38.000000 PM', 'system', '01-JUL-21 04.29.38.000000 PM');
----流程配置----
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID in('ModBintreeConfig002');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModBintreeConfig002', 'ModBintreeConfig002_btn_save', 'oneApproval', 'system', null, '05-AUG-21 03.26.19.000000 PM', 'system', '05-AUG-21 03.26.19.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModBintreeConfig002', 'ModBintreeConfig002_btn_submit', 'oneApproval', 'system', null, '05-AUG-21 03.26.19.000000 PM', 'system', '05-AUG-21 03.26.19.000000 PM', 'ALL', '02');
----数据字典配置----
delete from SYS_DATADICT where DICT_CODE='MODEL_MARKET_MULTI';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MARKET_MULTI','MOD','市场乘数方法','BOARD_STATUS','02','system',null,sysdate,'system',sysdate);
delete from SYS_DATADICT_LIST where DICT_CODE='MODEL_MARKET_MULTI';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MARKET_MULTI','01','市净率法',1,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MARKET_MULTI','02','市盈率法',2,'0','system',null,sysdate,'system',sysdate);
delete from SYS_DATADICT where DICT_CODE='MODEL_BOARD_STATUS';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_BOARD_STATUS','MOD','上市状态','BOARD_STATUS','02','system',null,sysdate,'system',sysdate);
delete from SYS_DATADICT_LIST where DICT_CODE='MODEL_BOARD_STATUS';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_BOARD_STATUS','01','上市',1,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_BOARD_STATUS','02','未上市',2,'0','system',null,sysdate,'system',sysdate);
commit;

--风险溢价调整配置
/*数据字典*/
DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'MODEL_GRADE_TYPE';
insert into SYS_DATADICT values ('MODEL_GRADE_TYPE','MOD','评级来源_模型','MODEL_GRADE_TYPE','02','system','',to_date('2019/06/24', 'yyyy/mm/dd'), 'system',to_date('2019/06/24', 'yyyy/mm/dd'));

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'MODEL_GRADE_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_GRADE_TYPE','01','外部评级','1','2','system','',SYSDATE, 'system',SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_GRADE_TYPE','02','内部评级','2','2','system','',SYSDATE, 'system',SYSDATE);
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_GRADE_TYPE','03','系统计算','3','2','system','',SYSDATE, 'system',SYSDATE);

/*页面*/
DELETE FROM SYS_MENU WHERE MENU_ID = 'VALUATION_ACCOUNTING_001_008';
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_ACCOUNTING_001_008', 'VAL', null, 'VALUATION_ACCOUNTING_001', '', 8, '02', '普通固定收益资产估值', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_MENU WHERE MENU_ID = 'VALUATION_ACCOUNTING_001_008_002' AND PAGE_ID = 'RiskPremiumAdjConf001';
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_ACCOUNTING_001_008_002', 'VAL', null, 'VALUATION_ACCOUNTING_001_008', 'RiskPremiumAdjConf001', 2, '02', '风险溢价调整配置', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'RiskPremiumAdjConf001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RiskPremiumAdjConf001', 'VAL', '/valmodel/valgeneralfixedincomeassets/riskpremiumadjconf/RiskPremiumAdjConf001', 'valmodel/valgeneralfixedincomeassets/riskpremiumadjconf/RiskPremiumAdjConf001', 'VALUATION_001_010_002', null, null, '02', '01', '风险溢价调整配置', 'system', null, '05-AUG-21 02.46.27.000000 PM', 'system', '05-AUG-21 02.46.27.000000 PM');

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'RiskPremiumAdjConf002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RiskPremiumAdjConf002', 'VAL', '/valmodel/valgeneralfixedincomeassets/riskpremiumadjconf/RiskPremiumAdjConf002', 'valmodel/valgeneralfixedincomeassets/riskpremiumadjconf/RiskPremiumAdjConf002', null, null, null, '02', '01', '风险溢价调整修改', 'system', null, '29-JUL-21 04.17.52.000000 PM', 'system', '29-JUL-21 04.17.52.000000 PM');

DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID = 'RiskPremiumAdjConf002';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RiskPremiumAdjConf002', 'RiskPremiumAdjConf002_btn_submit', 'oneApproval', 'system', null, '29-JUL-21 04.17.53.000000 PM', 'system', '29-JUL-21 04.17.53.000000 PM');


--估值模型-批处理页面
DELETE FROM SYS_MENU where PAGE_ID = 'ModValBatchTask001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_010_005', 'VAL', null, 'VALUATION_001_010', 'ModValBatchTask001', 5, '2', '估值模型批处理', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

delete from sys_page where PAGE_ID = 'ModValBatchTask001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModValBatchTask001', 'VAL', '/modelvaluation/batch/batchtask/ModValBatchTask001', 'valmodel/batch/batchtask/ModValBatchTask001', 'VALUATION_001_010_005', null, null, '02', '01', '估值模型批处理任务', 'system', null, '05-AUG-21 03.49.51.000000 PM', 'system', '05-AUG-21 03.49.51.000000 PM');

delete from sys_page where PAGE_ID = 'ModValBatchTask002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('', 'VAL', '/modelvaluation/batch/batchtask/ModValBatchTask002', 'valmodel/batch/batchtask/ModValBatchTask002', null, null, null, '02', '01', '估值模型批处理任务-详情', 'system', null, '05-AUG-21 03.49.51.000000 PM', 'system', '05-AUG-21 03.49.51.000000 PM');

--估值模型-现金流折现模型曲线界面上下拉框配置
delete from SYS_PAGE_DICTLIST where page_id in ('CashFlowDiscount002');
insert into sys_page_dictlist values('CashFlowDiscount002','999999','SMOOTHSECTION','01','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('CashFlowDiscount002','999999','SMOOTHSECTION','02','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('CashFlowDiscount002','999999','CURVE_TYPE','01','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('CashFlowDiscount002','999999','CURVE_TYPE','02','','','system',null,sysdate,'system',sysdate,'');


--估值模型-股息增长率配置系统参数配置
delete from sys_menu t where t.PAGE_ID='ModStockDivitrateConfig001' ;
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_010_004', 'VAL', null, 'VALUATION_001_010', 'ModStockDivitrateConfig001', 4, '2', '股息增长率配置', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

delete from sys_page t where t.PAGE_ID='ModStockDivitrateConfig001' ;
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig001', 'VAL', '/valmodel/modstockdivitrateconfig/ModStockDivitrateConfig001', 'valmodel/modstockdivitrateconfig/ModStockDivitrateConfig001', 'VALUATION_001_010_004', null, null, '02', '01', '预期股息增长率配置查询', 'system', null, '05-AUG-21 02.56.41.000000 PM', 'system', '05-AUG-21 02.56.41.000000 PM');

delete from sys_page t where t.PAGE_ID='ModStockDivitrateConfig002' ;
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig002', 'VAL', '/valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002', '/valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002', null, null, null, '02', '01', '预期股息增长率配置查询', 'system', null, '05-AUG-21 06.42.39.000000 PM', 'system', '05-AUG-21 06.42.39.000000 PM');

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStockDivitrateConfig002' and t.ELEMENT_ID='ModStockDivitrateConfig002_btn_submit';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig002', 'ModStockDivitrateConfig002_btn_submit', '01', '02', null, '提交', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStockDivitrateConfig001' and t.ELEMENT_ID='ModStockDivitrateConfig001_btn_clear';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig001', 'ModStockDivitrateConfig001_btn_clear', '01', '02', null, '重置', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStockDivitrateConfig001' and t.ELEMENT_ID='ModStockDivitrateConfig001_btn_add';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig001', 'ModStockDivitrateConfig001_btn_add', '01', '01', null, '新建', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStockDivitrateConfig001' and t.ELEMENT_ID='ModStockDivitrateConfig001_btn_search';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig001', 'ModStockDivitrateConfig001_btn_search', '01', '01', null, '查询', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStockDivitrateConfig001' and t.ELEMENT_ID='ModStockDivitrateConfig001_btn_delete';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig001', 'ModStockDivitrateConfig001_btn_delete', '01', '01', null, '删除', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));



delete from sys_wfl_business_module t where t.PAGE_ID='ModStockDivitrateConfig002';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModStockDivitrateConfig002', 'ModStockDivitrateConfig001_btn_delete', 'oneApproval', 'system', null, '05-AUG-21 03.25.41.000000 PM', 'system', '05-AUG-21 03.25.41.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModStockDivitrateConfig002', 'ModStockDivitrateConfig002_btn_submit', 'oneApproval', 'system', null, '05-AUG-21 03.25.41.000000 PM', 'system', '05-AUG-21 03.25.41.000000 PM', 'ALL', '02');

delete from sys_datadict_list t where t.DICT_CODE='COMMON_BUSINESS_TYPE' and t.ITEM_CODE='STCOKDIVITRATE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'STCOKDIVITRATE', '预期股息增长率配置', 1225, '0', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

--减值模型-资产评级配置管理菜单
delete from SYS_MENU where PAGE_ID='AssetRatingAllocation001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_008_015', 'VAL', null, 'VALUATION_001_008', 'AssetRatingAllocation001', 15, '2', '资产评级来源配置', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

--减值模型- LGD违约损失率
delete from SYS_MENU where PAGE_ID='LossGivenDefaultManage001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_011_005', 'VAL', null, 'VALUATION_001_011', 'LossGivenDefaultManage001', 4, '2', 'LGD违约损失率管理', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');
commit;

--减值阶段参数表初始化
delete from MOD_STAGE_PARA;
insert into MOD_STAGE_PARA values ('MODELINIT00000000000','P3','01','01','90','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000001','P3','01','02','90','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000002','P3','01','03','20','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000003','P3','01','04','05','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000004','P2','01','01','30','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000005','P2','01','02','30','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000006','P2','01','03','19','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000007','P2','01','04','02','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000008','P2','02','05','3','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
commit;

--减值阶段参数配置sql
delete from SYS_DATADICT t where t.DICT_CODE='MODEL_ABSOLUTE_OF_ELEMENT';
insert into SYS_DATADICT values ('MODEL_ABSOLUTE_OF_ELEMENT','MOD','要素绝对性','MODEL_ABSOLUTE_OF_ELEMENT','02','system','',to_date('2019/06/24', 'yyyy/mm/dd'), 'system',to_date('2019/06/24', 'yyyy/mm/dd'));
delete from SYS_DATADICT t where t.DICT_CODE='MODEL_DEC_ELEMENT';
insert into SYS_DATADICT values ('MODEL_DEC_ELEMENT','MOD','减值要素','MODEL_DEC_ELEMENT','02','system','',to_date('2019/06/24', 'yyyy/mm/dd'), 'system',to_date('2019/06/24', 'yyyy/mm/dd'));


delete from SYS_DATADICT_LIST t where t.DICT_CODE='MODEL_ABSOLUTE_OF_ELEMENT';
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_ABSOLUTE_OF_ELEMENT','01','绝对','1','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_ABSOLUTE_OF_ELEMENT','02','相对','2','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST t where t.DICT_CODE='MODEL_DEC_ELEMENT';
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_DEC_ELEMENT','01','本金逾期超过天数（含）','1','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_DEC_ELEMENT','02','利息逾期超过天数（含）','2','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_DEC_ELEMENT','03','公司评级信息低于（含）','3','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_DEC_ELEMENT','04','风险五级分类','4','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_DEC_ELEMENT','05','公司评级信息下调级数','5','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));

--减值阶段参数配置页面
delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStagePara001' and t.ELEMENT_ID='ModStagePara001_btn_clear';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara001', 'ModStagePara001_btn_clear', '01', '02', null, '重置', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStagePara001' and t.ELEMENT_ID='ModStagePara001_btn_export';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara001', 'ModStagePara001_btn_export', '01', '01', null, '导出', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStagePara001' and t.ELEMENT_ID='ModStagePara001_btn_search';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara001', 'ModStagePara001_btn_search', '01', '01', null, '查询', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStagePara002' and t.ELEMENT_ID='ModStagePara002_btn_submit';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara002', 'ModStagePara002_btn_submit', '01', '02', null, '提交', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_wfl_business_module t where t.PAGE_ID='ModStagePara002' and t.ELEMENT_ID='ModStagePara002_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara002', 'ModStagePara002_btn_submit', 'oneApproval', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_page_dictlist t where t.PAGE_ID='ModStagePara001' and t.DICT_ID='FIN_STAGE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara001', '999999', 'FIN_STAGE', 'P2', null, null, 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara001', '999999', 'FIN_STAGE', 'P3', null, null, 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_page_dictlist t where t.PAGE_ID='ModStagePara002' and t.DICT_ID='FIVE_CLASS_CLASSIFICATION';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara002', '999999', 'FIVE_CLASS_CLASSIFICATION', '02', null, null, 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara002', '999999', 'FIVE_CLASS_CLASSIFICATION', '03', null, null, 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara002', '999999', 'FIVE_CLASS_CLASSIFICATION', '04', null, null, 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara002', '999999', 'FIVE_CLASS_CLASSIFICATION', '05', null, null, 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

--配置流程BusinessType
delete from SYS_DATADICT_LIST t where t.DICT_CODE='COMMON_BUSINESS_TYPE' and t.ITEM_CODE='STAGEPARA';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'STAGEPARA', '减值阶段参数管理', 1224, '0', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));


--减值阶段参数管理
delete from sys_page t where t.PAGE_ID='ModStagePara001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara001', 'RSK', '/model/impairment/phaseparameter/ModStagePara001', 'lossmodel/phaseparameter/ModStagePara001', 'VALUATION_001_011_006', null, null, '02', '01', '减值阶段参数管理', 'system', null, '05-AUG-21 03.10.42.000000 PM', 'system', '05-AUG-21 03.10.42.000000 PM');

delete from sys_page t where t.PAGE_ID='ModStagePara002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara002', 'RSK', '/model/impairment/phaseparameter/ModStagePara002', 'lossmodel/phaseparameter/ModStagePara002', null, null, null, '02', '01', '减值阶段参数修改', 'system', null, '05-AUG-21 03.10.42.000000 PM', 'system', '05-AUG-21 03.10.42.000000 PM');

delete from sys_menu t where t.PAGE_ID='ModStagePara001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_011_006', 'VAL', null, 'VALUATION_001_011', 'ModStagePara001', 5, '2', '减值阶段参数管理', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');


--减值模型-数据加载批处理任务  + 减值模型-ECL计算批处理任务
delete from SYS_BATCH_TASK where BEAN_NAME in ('eclCalculationTask','impairmentDataLoadTask');
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,IS_BATCH_START_TARGET) values ('2041','MODEL','减值模型-数据加载批处理',null,'impairmentDataLoadTask',10,'system',null,to_timestamp('2021-07-16 11:03:09','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2021-07-16 11:03:09','YYYY-MM-DD HH24:MI:SS'),'02');
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,IS_BATCH_START_TARGET) values ('2045','MODEL','减值模型-ECL计算批处理',null,'eclCalculationTask',50,'system',null,to_timestamp('2021-07-16 11:03:09','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2021-07-16 11:03:09','YYYY-MM-DD HH24:MI:SS'),'02');

--减值模型-现金流折现模型批处理任务
delete from SYS_BATCH_TASK where TASK_ID = '2022';
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,IS_BATCH_START_TARGET) values ('2022','MODEL','现金流折现模型批处理',null,'cashFlowDiscountTask',60,'system',null,to_timestamp('2021-07-05 15:58:28','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2021-07-15 15:58:28','YYYY-MM-DD HH24:MI:SS'),'02');

--减值模型-资产减值阶段判断批处理
delete from SYS_BATCH_TASK where BEAN_NAME = 'assetImpairmentStageJudgeModelTask';
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,IS_BATCH_START_TARGET) values ('2042','MODEL','减值模型-资产减值阶段判断批处理',null,'assetImpairmentStageJudgeModelTask',20,'system',null,to_timestamp('2021-07-16 11:03:09','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2021-07-16 11:03:09','YYYY-MM-DD HH24:MI:SS'),'02');

--减值模型-EAD计算批处理任务
delete from SYS_BATCH_TASK t where t.BEAN_NAME='eadCalCulationBatchTask';
insert into SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('2043', 'MODEL', '减值模型-EAD计算批处理', null, 'eadCalCulationBatchTask', 30, 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), '02');

--减值模型-PD计算批处理任务
delete from SYS_BATCH_TASK where BEAN_NAME = 'pdCalculateTask';
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,IS_BATCH_START_TARGET) values ('2044','MODEL','减值模型-PD计算批处理',null,'pdCalculateTask',40,'system',null,to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'),'02');

--减值模型-数据推送批处理
delete from SYS_BATCH_TASK where BEAN_NAME = 'dataPushEclResultTask';
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,IS_BATCH_START_TARGET) values ('2026','MODEL','减值模型-数据推送批处理',null,'dataPushEclResultTask',60,'system',null,to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'),'02');

--估值模型-收益法模型批处理任务
delete from SYS_BATCH_TASK where TASK_ID = '2023' and BEAN_NAME='productAssetsIncomeMethodTask';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('2023', 'MODEL', '收益法模型批处理', null, 'productAssetsIncomeMethodTask', 60, 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), '02');

--估值模型-数据推送批处理任务
delete from SYS_BATCH_TASK where TASK_ID = '2024';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('2024', 'MODEL', '数据推送批处理', null, 'dataPushValuatIoninfoTask', 70, 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), '02');


--减值情况计提画面
DELETE FROM SYS_MENU WHERE PAGE_ID = 'ImpairmentProvision001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_011_007', 'VAL', null, 'VALUATION_001_011', 'ImpairmentProvision001', 6, '2', '减值情况计提表', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'ImpairmentProvision001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ImpairmentProvision001', 'RSK', '/model/impairment/impairmentprovision/ImpairmentProvision001', 'lossmodel/impairmentprovision/ImpairmentProvision001', 'VALUATION_001_011_007', null, null, '02', '02', '减值情况计提表', 'system', null, '05-AUG-21 03.50.40.000000 PM', 'system', '05-AUG-21 03.50.40.000000 PM');

DELETE FROM SYS_PAGE_DICTLIST WHERE PAGE_ID = 'ImpairmentProvision001' AND DICT_ID = 'CUSTOM_ASSET_TYPE_TWO';
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ImpairmentProvision001', '999999', 'CUSTOM_ASSET_TYPE_TWO', '01', null, null, 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ImpairmentProvision001', '999999', 'CUSTOM_ASSET_TYPE_TWO', '02', null, null, 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ImpairmentProvision001', '999999', 'CUSTOM_ASSET_TYPE_TWO', '03', null, null, 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ImpairmentProvision001', '999999', 'CUSTOM_ASSET_TYPE_TWO', '04', null, null, 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ImpairmentProvision001', '999999', 'CUSTOM_ASSET_TYPE_TWO', '05', null, null, 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ImpairmentProvision001', '999999', 'CUSTOM_ASSET_TYPE_TWO', '06', null, null, 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ImpairmentProvision001', '999999', 'CUSTOM_ASSET_TYPE_TWO', '08', null, null, 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ImpairmentProvision001', '999999', 'CUSTOM_ASSET_TYPE_TWO', '09', null, null, 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ImpairmentProvision001', '999999', 'CUSTOM_ASSET_TYPE_TWO', '18', null, null, 'system', null, SYSDATE, 'system', SYSDATE);


--数据字典-减值阶段
DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'FIN_STAGE';
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FIN_STAGE', 'VAL', '减值阶段', 'FIN_STAGE', '02', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'FIN_STAGE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FIN_STAGE', 'P1', '一阶段', 1, '0', 'system', null, SYSDATE, 'system', SYSDATE);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FIN_STAGE', 'P2', '二阶段', 2, '0', 'system', null, SYSDATE, 'system', SYSDATE);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FIN_STAGE', 'P3', '三阶段', 3, '0', 'system', null, SYSDATE, 'system', SYSDATE);


--数据字典-数据来源-模型
DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'MODEL_DATA_SOURCE';
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_DATA_SOURCE', 'VAL', '数据来源-模型', 'MODEL_DATA_SOURCE', '02', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'MODEL_DATA_SOURCE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_DATA_SOURCE', '02', '手工', 1, '0', 'system', null, SYSDATE, 'system', SYSDATE);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_DATA_SOURCE', '01', '模型', 2, '0', 'system', null, SYSDATE, 'system', SYSDATE);

--资产减值管理页面
DELETE FROM SYS_MENU WHERE MENU_ID = 'RISK_MANAGEMENT_001_013_012' AND PAGE_ID = 'AssetImpairmentManagement001';
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RISK_MANAGEMENT_001_013_012', 'RSK', null, 'RISK_MANAGEMENT_001_013', 'AssetImpairmentManagement001', 12, '02', '资产减值管理', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'AssetImpairmentManagement001';
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetImpairmentManagement001', 'RSK', '/model/impairment/assetimpairmentmanagement/AssetImpairmentManagement001', 'model/impairment/assetimpairmentmanagement/AssetImpairmentManagement001', 'RISK_MANAGEMENT_001_013_012', null, null, '02', '02', '资产减值管理', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'AssetImpairmentManagement002';
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetImpairmentManagement002', 'RSK', '/model/impairment/assetimpairmentmanagement/AssetImpairmentManagement002', 'model/impairment/assetimpairmentmanagement/AssetImpairmentManagement002', NULL, null, null, '02', '01', '资产减值新建', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID = 'AssetImpairmentManagement002';
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('AssetImpairmentManagement002','AssetImpairmentManagement002_btn_save','oneApproval','system',null,SYSDATE,'system',SYSDATE);
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('AssetImpairmentManagement002','AssetImpairmentManagement002_btn_submit','oneApproval','system',null,SYSDATE,'system',SYSDATE);
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('AssetImpairmentManagement002','AssetImpairmentManagement001_btn_delete','oneApproval','system',null,SYSDATE,'system',SYSDATE);

-- 内外部评级映射管理界面上的评级机构下拉框展示值初始化
delete from SYS_PAGE_DICTLIST where page_id in ('InsideOutsideGrade001','InsideOutsideGrade002');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','01','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','02','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','03','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','04','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','05','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','06','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','07','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','08','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','09','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','10','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','11','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','12','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','13','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','14','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','15','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','16','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','17','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','18','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','19','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','20','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','21','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','22','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','23','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','24','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','25','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','26','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','27','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','28','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','29','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','30','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','31','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','32','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade002','999999','GRADE_PARTY','99','','','system',null,sysdate,'system',sysdate,'');

insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','01','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','02','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','03','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','04','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','05','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','06','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','07','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','08','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','09','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','10','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','11','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','12','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','13','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','14','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','15','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','16','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','17','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','18','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','19','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','20','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','21','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','22','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','23','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','24','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','25','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','26','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','27','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','28','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','29','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','30','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','31','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','32','','','system',null,sysdate,'system',sysdate,'');
insert into sys_page_dictlist values('InsideOutsideGrade001','999999','GRADE_PARTY','99','','','system',null,sysdate,'system',sysdate,'');

--内外部评级映射管理
DELETE FROM sys_menu WHERE PAGE_ID = 'InsideOutsideGrade001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ASSET_MANAGEMENT_001_003_020', 'VAL', null, 'ASSET_MANAGEMENT_001_003', 'InsideOutsideGrade001', 20, '2', '评级关系映射', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

DELETE FROM sys_page WHERE PAGE_ID = 'InsideOutsideGrade001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InsideOutsideGrade001', 'VAL', '/model/impairment/insideoutsidegrademanagement/InsideOutsideGrade001', 'lossmodel/insideoutsidegrademanagement/InsideOutsideGrade001', 'ASSET_MANAGEMENT_001_003_020', null, null, '02', '01', '内外部评级映射管理', 'system', null, '05-AUG-21 03.29.20.000000 PM', 'system', '05-AUG-21 03.29.20.000000 PM');
DELETE FROM sys_page WHERE PAGE_ID = 'InsideOutsideGrade002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InsideOutsideGrade002', 'VAL', '/model/impairment/insideoutsidegrademanagement/InsideOutsideGrade002', 'lossmodel/insideoutsidegrademanagement/InsideOutsideGrade002', null, null, null, '02', '01', '内外部评级映射管理新建', 'system', null, '05-AUG-21 03.29.20.000000 PM', 'system', '05-AUG-21 03.29.20.000000 PM');

-- 内部评级枚举值初始化
insert into SYS_DATADICT values ('MODEL_STANDARD_GRADE_RESULT','MOD','模型_标准评级结果','STANDARD_GRADE_RESULT','02','system','',to_date('2019/06/24', 'yyyy/mm/dd'), 'system',to_date('2019/06/24', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','01','AAA+','01','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','02','AAA','02','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','03','AAA-','03','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','04','AA+','04','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','05','AA','05','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','06','AA-','06','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','07','A+','07','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','08','A','08','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','09','A-','09','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','10','BBB+','10','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','11','BBB','11','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','12','BBB-','12','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','13','BB','13','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','14','B','14','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','15','CCC','15','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','16','CC','16','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','17','C','17','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','18','D','18','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));

-- 流程按钮添加begin
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InsideOutsideGrade002', 'ValInsideOutsideGrade002_btn_submit', 'noneApproval', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InsideOutsideGrade002', 'ValInsideOutsideGrade001_btn_delete', 'noneApproval', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

-- 流程按钮添加end
-- 内外部评级映射管理配置的业务类型begin
insert into sys_datadict_list values('COMMON_BUSINESS_TYPE','MOD09','内外部评级映射管理',1225,0,'system','',sysdate,'system',sysdate);

--评级PD维护页面
delete from sys_page t where t.PAGE_ID='ModGradePdConfig001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModGradePdConfig001', 'RSK', '/model/impairment/pddefaultmanager/gradepdconfig/ModGradePdConfig001', 'lossmodel/pddefaultmanager/gradepdconfig/ModGradePdConfigQuery001', 'VALUATION_001_011_004', null, null, '02', '01', '评级PD表查询', 'system', null, '04-AUG-21 06.44.23.000000 PM', 'system', '04-AUG-21 06.44.23.000000 PM');

delete from sys_page t where t.PAGE_ID='ModGradePdConfig002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModGradePdConfig002', 'RSK', '/model/impairment/pddefaultmanager/gradepdconfig/ModGradePdConfig002', 'lossmodel/pddefaultmanager/gradepdconfig/ModGradePdConfigInfo002', null, null, null, '02', '01', '评级PD维护新建', 'system', null, '03-AUG-21 07.37.38.000000 PM', 'system', '03-AUG-21 07.37.38.000000 PM');

-- 流程按钮添加begin
delete from sys_wfl_business_module t where t.ELEMENT_ID='ModGradePdConfig001_btn_delete';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModGradePdConfig002', 'ModGradePdConfig001_btn_delete', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

delete from sys_wfl_business_module t where t.ELEMENT_ID='ModGradePdConfig001_btn_import';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModGradePdConfig002', 'ModGradePdConfig001_btn_import', 'oneApproval', 'system', null, '25-JUN-21 04.54.32.000000 PM', 'system', '25-JUN-21 04.54.32.000000 PM', 'ALL', '02');

delete from sys_wfl_business_module t where t.ELEMENT_ID='ModGradePdConfig002_btn_save';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModGradePdConfig002', 'ModGradePdConfig002_btn_save', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

delete from sys_wfl_business_module t where t.ELEMENT_ID='ModGradePdConfig002_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModGradePdConfig002', 'ModGradePdConfig002_btn_submit', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

-- 流程按钮添加end

--配置代办事项业务类型begin--
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'MOD07', '评级PD维护', 1224, '0', 'system', null, '01-JUL-21 03.24.24.000000 PM', 'system', '01-JUL-21 03.24.24.000000 PM');
--配置代办事项业务类型end--

--MOD_CURVE_MAPPING初始化脚本
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1','01','中债国债曲线','SP','1231','中债国债收益率曲线','','','system','',to_date('2021/6/16', 'yyyy/mm/dd'), 'system',to_date('2021/6/16', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2','01','中债国债曲线','MA','1232','中债国债收益率曲线','','','system','',to_date('2021/6/17', 'yyyy/mm/dd'), 'system',to_date('2021/6/17', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3','02','中债企业债曲线','SP','1901','中债企业债收益率曲线(A)','08','A','system','',to_date('2021/6/18', 'yyyy/mm/dd'), 'system',to_date('2021/6/18', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('4','02','中债企业债曲线','MA','1902','中债企业债收益率曲线(A)','08','A','system','',to_date('2021/6/19', 'yyyy/mm/dd'), 'system',to_date('2021/6/19', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('5','02','中债企业债曲线','SP','2221','中债企业债收益率曲线(B)','20','B','system','',to_date('2021/6/20', 'yyyy/mm/dd'), 'system',to_date('2021/6/20', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('6','02','中债企业债曲线','MA','2222','中债企业债收益率曲线(B)','20','B','system','',to_date('2021/6/21', 'yyyy/mm/dd'), 'system',to_date('2021/6/21', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('7','02','中债企业债曲线','SP','1851','中债企业债收益率曲线(A+)','07','A+','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('8','02','中债企业债曲线','MA','1852','中债企业债收益率曲线(A+)','07','A+','system','',to_date('2021/6/23', 'yyyy/mm/dd'), 'system',to_date('2021/6/23', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('9','02','中债企业债曲线','SP','2171','中债企业债收益率曲线(A-)','09','A-','system','',to_date('2021/6/24', 'yyyy/mm/dd'), 'system',to_date('2021/6/24', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('10','02','中债企业债曲线','MA','2172','中债企业债收益率曲线(A-)','09','A-','system','',to_date('2021/6/25', 'yyyy/mm/dd'), 'system',to_date('2021/6/25', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('11','02','中债企业债曲线','SP','1251','中债企业债收益率曲线(AA)','05','AA','system','',to_date('2021/6/26', 'yyyy/mm/dd'), 'system',to_date('2021/6/26', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('12','02','中债企业债曲线','MA','1252','中债企业债收益率曲线(AA)','05','AA','system','',to_date('2021/6/27', 'yyyy/mm/dd'), 'system',to_date('2021/6/27', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('13','02','中债企业债曲线','SP','2211','中债企业债收益率曲线(BB)','17','BB','system','',to_date('2021/6/28', 'yyyy/mm/dd'), 'system',to_date('2021/6/28', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('14','02','中债企业债曲线','MA','2212','中债企业债收益率曲线(BB)','17','BB','system','',to_date('2021/6/29', 'yyyy/mm/dd'), 'system',to_date('2021/6/29', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('15','02','中债企业债曲线','SP','2191','中债企业债收益率曲线(CC)','26','CC','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('16','02','中债企业债曲线','MA','2192','中债企业债收益率曲线(CC)','26','CC','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('17','02','中债企业债曲线','SP','1441','中债企业债收益率曲线(AA+)','04','AA+','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('18','02','中债企业债曲线','MA','1442','中债企业债收益率曲线(AA+)','04','AA+','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('19','02','中债企业债曲线','SP','2251','中债企业债收益率曲线(AA-)','06','AA-','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('20','02','中债企业债曲线','MA','2252','中债企业债收益率曲线(AA-)','06','AA-','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('21','02','中债企业债曲线','SP','1261','中债企业债收益率曲线(AAA)','02','AAA','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('22','02','中债企业债曲线','MA','1262','中债企业债收益率曲线(AAA)','02','AAA','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('23','02','中债企业债曲线','SP','2201','中债企业债收益率曲线(BBB)','14','BBB','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('24','02','中债企业债曲线','MA','2202','中债企业债收益率曲线(BBB)','14','BBB','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('25','02','中债企业债曲线','SP','2231','中债企业债收益率曲线(CCC)','23','CCC','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('26','02','中债企业债曲线','MA','2232','中债企业债收益率曲线(CCC)','23','CCC','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('27','02','中债企业债曲线','SP','2241','中债企业债收益率曲线(AAA-)','03','AAA-','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('28','02','中债企业债曲线','MA','2242','中债企业债收益率曲线(AAA-)','03','AAA-','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('29','02','中债企业债曲线','SP','1911','中债企业债收益率曲线(BBB+)','13','BBB+','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('30','02','中债企业债曲线','MA','1912','中债企业债收益率曲线(BBB+)','13','BBB+','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('31','03','中债资产支持证券曲线','SP','2331','中债资产支持证券收益率曲线(A+)','07','A+','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('32','03','中债资产支持证券曲线','MA','2332','中债资产支持证券收益率曲线(A+)','07','A+','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('33','03','中债资产支持证券曲线','SP','2311','中债资产支持证券收益率曲线(AA)','05','AA','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('34','03','中债资产支持证券曲线','MA','2312','中债资产支持证券收益率曲线(AA)','05','AA','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('35','03','中债资产支持证券曲线','SP','2161','中债资产支持证券收益率曲线(AA+)','04','AA+','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('36','03','中债资产支持证券曲线','MA','2162','中债资产支持证券收益率曲线(AA+)','04','AA+','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('37','03','中债资产支持证券曲线','SP','2321','中债资产支持证券收益率曲线(AA-)','06','AA-','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('38','03','中债资产支持证券曲线','MA','2322','中债资产支持证券收益率曲线(AA-)','06','AA-','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('39','03','中债资产支持证券曲线','SP','1331','中债资产支持证券收益率曲线(AAA)','02','AAA','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('40','03','中债资产支持证券曲线','MA','1332','中债资产支持证券收益率曲线(AAA)','02','AAA','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('41','03','中债资产支持证券曲线','SP','2301','中债资产支持证券收益率曲线(AAA-)','03','AAA-','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));
insert into MOD_CURVE_MAPPING(SEQ_NO,CURVE_GROUP_CODE,CURVE_GROUP_NAME,CURVE_TYPE,CURVE_CODE,CURVE_NAME,GRADE_RESULT,GRADE_RESULT_NAME,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('42','03','中债资产支持证券曲线','MA','2302','中债资产支持证券收益率曲线(AAA-)','03','AAA-','system','',to_date('2021/6/30', 'yyyy/mm/dd'), 'system',to_date('2021/6/30', 'yyyy/mm/dd'));


--特殊资产LGD维护页面
delete from sys_page t where t.PAGE_ID='LgdSpecialConfig001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LgdSpecialConfig001', 'RSK', '/model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig001', 'lossmodel/lgddefultmanager/lgdspecialconfig/LgdSpecialConfigQuery001', null, null, null, '02', '01', '特殊资产LGD维护查询', 'system', null, '05-AUG-21 02.37.06.000000 PM', 'system', '05-AUG-21 02.37.06.000000 PM');

delete from sys_page t where t.PAGE_ID='LgdSpecialConfig002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LgdSpecialConfig002', 'RSK', '/model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002', 'lossmodel/lgddefultmanager/lgdspecialconfig/LgdSpecialConfigInfo002', null, null, null, '02', '01', '特殊资产LGD维护新建', 'system', null, '05-AUG-21 02.37.06.000000 PM', 'system', '05-AUG-21 02.37.06.000000 PM');

-- 流程按钮添加begin
delete from sys_wfl_business_module t where t.ELEMENT_ID='LgdSpecialConfig001_btn_delete';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LgdSpecialConfig002', 'LgdSpecialConfig001_btn_delete', 'oneApproval', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_wfl_business_module t where t.ELEMENT_ID='LgdSpecialConfig002_btn_save';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LgdSpecialConfig002', 'LgdSpecialConfig002_btn_save', 'oneApproval', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_wfl_business_module t where t.ELEMENT_ID='LgdSpecialConfig002_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LgdSpecialConfig002', 'LgdSpecialConfig002_btn_submit', 'oneApproval', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

-- 流程按钮添加end

--配置代办事项业务类型begin--
delete from sys_datadict_list t where t.DICT_CODE='COMMON_BUSINESS_TYPE' and t.ITEM_CODE='MOD08';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'MOD08', '特殊资产LGD维护', 1300, '0', 'system', null, '05-JUL-21 03.36.53.000000 PM', 'system', '05-JUL-21 03.36.53.000000 PM');
--配置代办事项业务类型end--


--现金流折现模型曲线配置菜单和页面
delete from sys_menu where PAGE_ID = 'CashFlowDiscount001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_009_004', 'VAL', null, 'VALUATION_001_009', 'CashFlowDiscount001', 4, '2', '现金流折现模型曲线配置', 'system', null, '05-AUG-21 10.05.34.000000 AM', 'system', '05-AUG-21 10.05.34.000000 AM');

delete from sys_menu where PAGE_ID = 'CashFlowDiscount001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CashFlowDiscount001', 'VAL', '/valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount001', 'valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount001', 'VALUATION_001_009_004', null, null, '02', '01', '现金流折现模型曲线配置', 'system', null, '05-AUG-21 04.20.44.000000 PM', 'system', '05-AUG-21 04.20.44.000000 PM');

delete from sys_menu where PAGE_ID = 'CashFlowDiscount002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CashFlowDiscount002', 'VAL', '/valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount002', 'valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount002', null, null, null, '02', '01', '现金流折现模型曲线配置新建', 'system', null, '05-AUG-21 06.37.18.000000 PM', 'system', '05-AUG-21 06.37.18.000000 PM');


-- 配置工作流按钮
delete from sys_wfl_business_module where PAGE_ID = 'CashFlowDiscount002';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CashFlowDiscount002', 'ValCashFlowDiscount001_btn_delete', 'oneApproval', 'system', null, '05-AUG-21 03.43.43.000000 PM', 'system', '05-AUG-21 03.43.43.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CashFlowDiscount002', 'ValCashFlowDiscount002_btn_save', 'oneApproval', 'system', null, '05-AUG-21 03.43.49.000000 PM', 'system', '05-AUG-21 03.43.49.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CashFlowDiscount002', 'ValCashFlowDiscount002_btn_submit', 'oneApproval', 'system', null, '05-AUG-21 03.43.37.000000 PM', 'system', '05-AUG-21 03.43.37.000000 PM', 'ALL', '02');


-- 现金流折现模型曲线配置的业务类型begin
delete from sys_datadict_list where dict_code='COMMON_BUSINESS_TYPE' and item_code='MOD06';
insert into sys_datadict_list values('COMMON_BUSINESS_TYPE','MOD06','现金流折现模型曲线配置',1224,0,'system','',sysdate,'system',sysdate);
-- end

-- 曲线族枚举begin
insert into SYS_DATADICT values ('CURVE_GROUP','MOD','曲线族','CURVEGROUP','02','system','',to_date('2019/06/24', 'yyyy/mm/dd'), 'system',to_date('2019/06/24', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('CURVE_GROUP','01','中债国债曲线','1','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('CURVE_GROUP','02','中债企业债曲线','2','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('CURVE_GROUP','03','中债资产支持证券曲线','3','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
-- end

--平滑区间枚举begin
insert into SYS_DATADICT values ('SMOOTHSECTION','MOD','平滑区间','SMOOTHSECTION','02','system','',to_date('2019/06/24', 'yyyy/mm/dd'), 'system',to_date('2019/06/24', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SMOOTHSECTION','01','1个月','1','2','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SMOOTHSECTION','02','3个月','2','2','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
--end

-- 映射类型_模型枚举begin
insert into SYS_DATADICT values ('MODEL_MAPPING_TYPE','MOD','映射类型_模型','MODEL_MAPPING_TYPE','02','system','',to_date('2019/06/24', 'yyyy/mm/dd'), 'system',to_date('2019/06/24', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MAPPING_TYPE','01','资产分类映射','1','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
--end

-- 资产大类评级配置的业务类型begin
delete from sys_datadict_list where dict_code='COMMON_BUSINESS_TYPE' and item_code = 'MOD01';
insert into sys_datadict_list values('COMMON_BUSINESS_TYPE','MOD01','资产大类评级配置',1223,0,'system','',sysdate,'system',sysdate);
-- end

-- 资产分类评级配置枚举begin
delete from sys_datadict where dict_code ='MODEL_SEC_DETAIL_TYPE';
insert into sys_datadict values('MODEL_SEC_DETAIL_TYPE','VAL','资产分类','','02','system','',sysdate,'system',sysdate);

delete from SYS_DATADICT_LIST where DICT_CODE = 'MODEL_SEC_DETAIL_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','001','本行发行的同业存单','1','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','002','他行发行的同业存单','2','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','003','国债','3','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','004','地方政府债','4','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','005','央票','5','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','006','政策性金融债','6','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','158','政策性金融次级债','7','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','007','铁道债','8','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','008','金融债','9','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','009','金融机构次级债','10','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','010','企业债','11','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','012','公司债','12','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','014','交易所私募债','13','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','015','短融','14','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','016','超短融','15','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','017','中票','16','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','018','银行间私募债','17','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','139','交易商协会ABN','18','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','140','银保监会主管ABS','19','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','141','证监会主管ABS','20','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','020','外国债券','21','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','021','理财直接融资工具','22','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','022','其他理财直接融资工具','23','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','011','可交换债券','24','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','013','可转债','25','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','142','上市银行优先股','26','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','143','非上市银行优先股','27','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','08','新增可投资资产','28','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_SEC_DETAIL_TYPE','09','非标准化债权类资产','29','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
--资产分类评级配置end

--资产评级来源配置页面
delete from sys_menu where PAGE_ID in('AssetRatingAllocation001','Specialassets001','Specialassets002');
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_008_015', 'VAL', null, 'VALUATION_001_008', 'AssetRatingAllocation001', 15, '2', '资产评级来源配置', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');


delete from SYS_PAGE where PAGE_ID in('AssetRatingAllocation001','Specialassets001','Specialassets002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetRatingAllocation001', 'RSK', '/model/impairment/assetratingallocation/AssetRatingAllocation001', 'lossmodel/assetratingallocation/AssetRatingAllocation001', 'VALUATION_001_011_001', null, null, '02', '01', '资产评级配置', 'system', null, '05-AUG-21 03.07.29.000000 PM', 'system', '05-AUG-21 03.07.29.000000 PM');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('Specialassets001', 'RSK', '/model/impairment/assetratingallocation/specialassets/Specialassets001', 'lossmodel/assetratingallocation/specialassets/Specialassets001', null, null, null, '02', '01', '特殊资产评级配置查询', 'system', null, '05-AUG-21 03.07.29.000000 PM', 'system', '05-AUG-21 03.07.29.000000 PM');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('Specialassets002', 'RSK', '/model/impairment/assetratingallocation/specialassets/Specialassets002', 'lossmodel/assetratingallocation/specialassets/Specialassets002', null, null, null, '02', '01', '特殊资产评级配置新建', 'system', null, '05-AUG-21 03.07.29.000000 PM', 'system', '05-AUG-21 03.07.29.000000 PM');
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('Specialassets002','RSK','/model/impairment/assetratingallocation/specialassets/Specialassets002','model/impairment/assetratingallocation/specialassets/Specialassets002',null,null,null,'02','01','特殊资产评级配置新建','system',null,sysdate,'system',sysdate);


delete from SYS_PAGE where PAGE_ID in('LossGivenDefaultManage001','LossGivenDefaultMaintain001','LossGivenDefaultMaintain002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LossGivenDefaultManage001', 'RSK', '/model/impairment/lossgivendefaultmanage/LossGivenDefaultManage001', 'lossmodel/lossgivendefaultmanage/LossGivenDefaultManage001', 'VALUATION_001_011_005', null, null, '02', '01', '资产评级配置', 'system', null, '05-AUG-21 03.07.29.000000 PM', 'system', '05-AUG-21 03.07.29.000000 PM');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LossGivenDefaultMaintain001', 'RSK', '/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain001', 'lossmodel/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain001', null, null, null, '02', '01', '特殊资产评级配置查询', 'system', null, '05-AUG-21 03.07.29.000000 PM', 'system', '05-AUG-21 03.07.29.000000 PM');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LossGivenDefaultMaintain002', 'RSK', '/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002', 'lossmodel/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002', null, null, null, '02', '01', '特殊资产评级配置新建', 'system', null, '05-AUG-21 03.07.29.000000 PM', 'system', '05-AUG-21 03.07.29.000000 PM');

--数据字典-评级类型-减值模型
delete from SYS_DATADICT where DICT_CODE='MODEL_MAIN_GRADE_TYPE';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MAIN_GRADE_TYPE','MOD','评级类型-减值模型',null,'02','system',null,sysdate,'system',sysdate);
delete from SYS_DATADICT_LIST where DICT_CODE='MODEL_MAIN_GRADE_TYPE';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MAIN_GRADE_TYPE','01','主体',1,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MAIN_GRADE_TYPE','02','债项',2,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MAIN_GRADE_TYPE','03','融资人',3,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MAIN_GRADE_TYPE','04','担保人',4,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MAIN_GRADE_TYPE','05','较高值',5,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MAIN_GRADE_TYPE','06','较低值',6,'0','system',null,sysdate,'system',sysdate);

delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID in('Specialassets002');
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('Specialassets002','Specialassets001_btn_delete','noneApproval','system',null,sysdate,'system',sysdate);
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('Specialassets002','Specialassets002_btn_save','noneApproval','system',null,sysdate,'system',sysdate);
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('Specialassets002','Specialassets002_btn_submit','noneApproval','system',null,sysdate,'system',sysdate);

delete from SYS_DATADICT where DICT_CODE='MODEL_DEC_CONFIG_TYPE';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_DEC_CONFIG_TYPE','MOD','减值配置类型_模型',null,'02','system',null,sysdate,'system',sysdate);
delete from SYS_DATADICT_LIST where DICT_CODE='MODEL_DEC_CONFIG_TYPE';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_DEC_CONFIG_TYPE','01','资产大类评级配置',1,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_DEC_CONFIG_TYPE','02','特殊资产评级配置',2,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_DEC_CONFIG_TYPE','03','评级PD配置',3,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_DEC_CONFIG_TYPE','04','前瞻因子配置',4,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_DEC_CONFIG_TYPE','05','资产大类LGD配置',5,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_DEC_CONFIG_TYPE','06','特殊资产LGD配置',6,'0','system',null,sysdate,'system',sysdate);

delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE in('MOD02','MOD05');
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('COMMON_BUSINESS_TYPE','MOD02','特殊资产评级配置',33,'0','system',null,to_timestamp('2020-06-10 20.05.26.000000000','YYYY-MM-DD HH24.MI.SSXFF'),'system',to_timestamp('2020-06-10 20.05.26.000000000','YYYY-MM-DD HH24.MI.SSXFF'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('COMMON_BUSINESS_TYPE','MOD05','LGD违约损失率维护',33,'0','system',null,to_timestamp('2020-06-10 20.05.26.000000000','YYYY-MM-DD HH24.MI.SSXFF'),'system',to_timestamp('2020-06-10 20.05.26.000000000','YYYY-MM-DD HH24.MI.SSXFF'));

delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID in('LossGivenDefaultMaintain002');
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('LossGivenDefaultMaintain002','LossGivenDefaultMaintain002_btn_save','noneApproval','system',null,sysdate,'system',sysdate);
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('LossGivenDefaultMaintain002','LossGivenDefaultMaintain002_btn_submit','noneApproval','system',null,sysdate,'system',sysdate);

--利润表、资产负债表、理财产品净值变动表界面菜单地址改为新落地后的三表对应界面地址
delete from sys_menu where MENU_ID ='ACCOUTING_001_004_002_001';
delete from sys_menu where MENU_ID ='ACCOUTING_001_004_002_002';
delete from sys_menu where MENU_ID ='ACCOUTING_001_004_002_003';
Insert into sys_menu (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ACCOUTING_001_004_002_001','VAL',null,'ACCOUTING_001_004_002','OwnerEquityChangeNew001',6,'03','理财产品净值变动表','system',null,sysdate,'system',sysdate);
Insert into sys_menu (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ACCOUTING_001_004_002_002','VAL',null,'ACCOUTING_001_004_002','ValProfitNewQuery001',7,'02','利润表','system',null,sysdate,'system',sysdate);
Insert into sys_menu (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ACCOUTING_001_004_002_003','VAL',null,'ACCOUTING_001_004_002','AssetLiabilityStatementNewQuery001',5,'02','资产负债表','system',null,sysdate,'system',sysdate);


--weipeng: 资产减值升级时删除了手工凭证-计提后得批处理，需要给恢复回来
--批处理任务表添加批处理任务：手工凭证-计提后：手工凭证-计提后
DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '204';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('204','VAL','手工凭证批处理-计提后','','manualVoucherTask','83','system',to_date('2021/02/24', 'yyyy/mm/dd'), 'system',to_date('2021/02/24', 'yyyy/mm/dd'), '');

--批处理任务组添加批处理任务：手工凭证-计提后
DELETE FROM SYS_BATCH_JOB WHERE TASK_ID = '204' AND BATCH_SEQ_NO = '6f8803b3aa6fbaffe050007f01003cad';
insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01003cad','204','system','',to_date('2021/2/24', 'yyyy/mm/dd'), 'system',to_date('2021/2/24', 'yyyy/mm/dd'));

--匹配关系表增加【手工凭证-计提后】批处理配置：
DELETE FROM bok_match_rlat t WHERE t.source = '204';
insert into bok_match_rlat (MATCH_TYPE, ACCT_STANDARD, SOURCE, TARGET, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('01', 'ALL', '204', '23', 'system', null, sysdate, 'system', sysdate);

--删除203任务原先多余一条批处理配置，原先是手工凭证-计提后的配置
DELETE FROM bok_match_rlat t WHERE t.source = '203' AND TARGET= '23';