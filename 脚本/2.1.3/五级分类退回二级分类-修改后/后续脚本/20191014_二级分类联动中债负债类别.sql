-- 需要执行二级分类目录下脚本  add by  chenyufei 20191014-->
DELETE FROM SYS_DATADICT_LIST_LINK WHERE DICT_CODE = 'SVR_ASSET_TYPE_SECOND_LEVEL' AND  LINKED_DICT_CODE = 'CUSTOM_ASSET_TYPE_THREE';

--  以下为画面五级分类关联资产负债类别，如是二级关联，数据恢复  -->
--  修改存款画面关联资产负债类别  -->
delete from SYS_PAGE_DICTLIST where   SYS_PAGE_DICTLIST.DICT_ID = 'SVR_ASSET_TYPE_SECOND_LEVEL' and SYS_PAGE_DICTLIST.PAGE_ID like '%FinDepositTarget002%' 
and SYS_PAGE_DICTLIST.DICT_ITEM_VAL in ('2501','2502','1004','1005');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinDepositTarget002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '1004', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinDepositTarget002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '1005', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');

-- 增加项目类画面关联资产负债类别  -->
delete from SYS_PAGE_DICTLIST where   SYS_PAGE_DICTLIST.DICT_ID = 'SVR_ASSET_TYPE_SECOND_LEVEL' and SYS_PAGE_DICTLIST.PAGE_ID like '%FinProjectTargetAsset002%' 
and SYS_PAGE_DICTLIST.DICT_ITEM_VAL in ('2617','2299');
INSERT INTO SYS_PAGE_DICTLIST  VALUES ('FinProjectTargetAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2617', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST  VALUES ('FinProjectTargetAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2299', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
-- 项目类三级多16、47无关联资产负债类别脚本  -->


-- 删除计划类净值型、计划类利率型画面关联部分资产负债类型  -->
delete from SYS_PAGE_DICTLIST where   SYS_PAGE_DICTLIST.DICT_ID = 'SVR_ASSET_TYPE_SECOND_LEVEL' and SYS_PAGE_DICTLIST.PAGE_ID like '%FinPlanNetAsset002%' 
and SYS_PAGE_DICTLIST.DICT_ITEM_VAL in ('2801','2802','2803','2804','2805','2806','2899');
delete from SYS_PAGE_DICTLIST where   SYS_PAGE_DICTLIST.DICT_ID = 'SVR_ASSET_TYPE_SECOND_LEVEL' and SYS_PAGE_DICTLIST.PAGE_ID like '%FinPlanProfitAsset002%' 
and SYS_PAGE_DICTLIST.DICT_ITEM_VAL in ('2801','2802','2803','2804','2805','2806','2899');


INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanNetAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2801', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanNetAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2802', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanNetAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2803', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanNetAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2804', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanNetAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2805', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanNetAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2806', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanNetAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2899', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');


INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanProfitAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2801', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanProfitAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2802', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanProfitAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2803', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanProfitAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2804', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanProfitAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2805', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanProfitAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2806', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanProfitAsset002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2899', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');

--  修改计划类货币型画面关联资产负债类别  -->
delete from SYS_PAGE_DICTLIST where   SYS_PAGE_DICTLIST.DICT_ID = 'SVR_ASSET_TYPE_SECOND_LEVEL' and SYS_PAGE_DICTLIST.PAGE_ID like '%FinPlanAssetC002%' 
and SYS_PAGE_DICTLIST.DICT_ITEM_VAL in ('2801','2802','2803','2804','2805','2806','2899','1701','1702','1703','1704','1705','1706');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanAssetC002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2801', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanAssetC002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2802', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanAssetC002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2803', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanAssetC002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2804', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanAssetC002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2805', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanAssetC002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2806', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('FinPlanAssetC002', '999999', 'SVR_ASSET_TYPE_SECOND_LEVEL', '2899', null, null, 'admin', null, TIMESTAMP '2019-03-06 00:00:00', 'admin', TIMESTAMP '2019-03-06 00:00:00');

insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1101','CUSTOM_ASSET_TYPE_THREE','B01','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1110','CUSTOM_ASSET_TYPE_THREE','B02','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1109','CUSTOM_ASSET_TYPE_THREE','B03','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2501','CUSTOM_ASSET_TYPE_THREE','B04','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2502','CUSTOM_ASSET_TYPE_THREE','B04','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1102','CUSTOM_ASSET_TYPE_THREE','B001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1112','CUSTOM_ASSET_TYPE_THREE','B002','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1112','CUSTOM_ASSET_TYPE_THREE','B003','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1112','CUSTOM_ASSET_TYPE_THREE','B004','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1112','CUSTOM_ASSET_TYPE_THREE','B005','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1112','CUSTOM_ASSET_TYPE_THREE','B006','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1112','CUSTOM_ASSET_TYPE_THREE','B007','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1113','CUSTOM_ASSET_TYPE_THREE','B008','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1113','CUSTOM_ASSET_TYPE_THREE','B009','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1114','CUSTOM_ASSET_TYPE_THREE','B010','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1114','CUSTOM_ASSET_TYPE_THREE','B011','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1115','CUSTOM_ASSET_TYPE_THREE','B012','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1115','CUSTOM_ASSET_TYPE_THREE','B013','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1115','CUSTOM_ASSET_TYPE_THREE','B014','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1115','CUSTOM_ASSET_TYPE_THREE','B015','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1116','CUSTOM_ASSET_TYPE_THREE','B016','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1116','CUSTOM_ASSET_TYPE_THREE','B017','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1116','CUSTOM_ASSET_TYPE_THREE','B018','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1114','CUSTOM_ASSET_TYPE_THREE','B14','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1114','CUSTOM_ASSET_TYPE_THREE','B15','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1114','CUSTOM_ASSET_TYPE_THREE','B16','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1301','CUSTOM_ASSET_TYPE_THREE','B17','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1107','CUSTOM_ASSET_TYPE_THREE','D01','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1002','CUSTOM_ASSET_TYPE_THREE','D001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1003','CUSTOM_ASSET_TYPE_THREE','D001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1004','CUSTOM_ASSET_TYPE_THREE','D001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1005','CUSTOM_ASSET_TYPE_THREE','D001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1002','CUSTOM_ASSET_TYPE_THREE','D002','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1003','CUSTOM_ASSET_TYPE_THREE','D002','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1004','CUSTOM_ASSET_TYPE_THREE','D002','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1005','CUSTOM_ASSET_TYPE_THREE','D002','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1201','CUSTOM_ASSET_TYPE_THREE','1201','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1202','CUSTOM_ASSET_TYPE_THREE','1202','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1203','CUSTOM_ASSET_TYPE_THREE','1203','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1204','CUSTOM_ASSET_TYPE_THREE','1204','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1205','CUSTOM_ASSET_TYPE_THREE','1205','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1206','CUSTOM_ASSET_TYPE_THREE','1206','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1207','CUSTOM_ASSET_TYPE_THREE','1207','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1208','CUSTOM_ASSET_TYPE_THREE','1208','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1209','CUSTOM_ASSET_TYPE_THREE','1209','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1210','CUSTOM_ASSET_TYPE_THREE','1210','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1299','CUSTOM_ASSET_TYPE_THREE','1299','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1301','CUSTOM_ASSET_TYPE_THREE','P02','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1399','CUSTOM_ASSET_TYPE_THREE','P03','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2612','CUSTOM_ASSET_TYPE_THREE','P04','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1301','CUSTOM_ASSET_TYPE_THREE','P05','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2612','CUSTOM_ASSET_TYPE_THREE','P06','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1399','CUSTOM_ASSET_TYPE_THREE','P07','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2201','CUSTOM_ASSET_TYPE_THREE','P08','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1399','CUSTOM_ASSET_TYPE_THREE','P09','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1399','CUSTOM_ASSET_TYPE_THREE','P10','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2101','CUSTOM_ASSET_TYPE_THREE','P11','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1702','CUSTOM_ASSET_TYPE_THREE','P12','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1701','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1702','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1703','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1704','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1705','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1706','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2801','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2802','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2803','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2804','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2805','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2806','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2899','CUSTOM_ASSET_TYPE_THREE','N001','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2801','CUSTOM_ASSET_TYPE_THREE','N002','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2802','CUSTOM_ASSET_TYPE_THREE','N002','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2803','CUSTOM_ASSET_TYPE_THREE','N002','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2804','CUSTOM_ASSET_TYPE_THREE','N002','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2805','CUSTOM_ASSET_TYPE_THREE','N002','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2806','CUSTOM_ASSET_TYPE_THREE','N002','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2899','CUSTOM_ASSET_TYPE_THREE','N002','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2801','CUSTOM_ASSET_TYPE_THREE','N003','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2802','CUSTOM_ASSET_TYPE_THREE','N003','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2803','CUSTOM_ASSET_TYPE_THREE','N003','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2804','CUSTOM_ASSET_TYPE_THREE','N003','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2805','CUSTOM_ASSET_TYPE_THREE','N003','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2806','CUSTOM_ASSET_TYPE_THREE','N003','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2899','CUSTOM_ASSET_TYPE_THREE','N003','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2801','CUSTOM_ASSET_TYPE_THREE','N004','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2802','CUSTOM_ASSET_TYPE_THREE','N004','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2803','CUSTOM_ASSET_TYPE_THREE','N004','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2804','CUSTOM_ASSET_TYPE_THREE','N004','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2805','CUSTOM_ASSET_TYPE_THREE','N004','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2806','CUSTOM_ASSET_TYPE_THREE','N004','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','2899','CUSTOM_ASSET_TYPE_THREE','N004','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1115','CUSTOM_ASSET_TYPE_THREE','B10','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1117','CUSTOM_ASSET_TYPE_THREE','B11','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('SVR_ASSET_TYPE_SECOND_LEVEL','1111','CUSTOM_ASSET_TYPE_THREE','B12','system','',to_date('2019/7/18', 'yyyy/mm/dd'),'system',to_date('2019/7/18', 'yyyy/mm/dd'));

