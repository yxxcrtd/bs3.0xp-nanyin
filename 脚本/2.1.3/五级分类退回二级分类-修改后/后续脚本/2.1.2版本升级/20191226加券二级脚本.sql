--------------------加券二级脚本找二级对应关系，不应该找五级-----------------------------------------
DELETE FROM FIN_PRODUCT_TYPE_CONFIG T WHERE CONFIG_TYPE IN ('WINDTWO_TG_TYPE');

insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115001','WINDTWO_TG_TYPE','F01','B05','B001','','','B05','01','Y','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115002','WINDTWO_TG_TYPE','F01','B05','B001','','','B05','01','N','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115003','WINDTWO_TG_TYPE','F01','B05','B006','','','B05','06','N','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115004','WINDTWO_TG_TYPE','F01','B05','B006','','','B05','06','Y','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115005','WINDTWO_TG_TYPE','F01','B05','B002','','','B05','02','N','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115020','WINDTWO_TG_TYPE','F01','B05','B002','','','B05','02','Y','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115006','WINDTWO_TG_TYPE','F01','B05','B004','','','B05','04','N','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115007','WINDTWO_TG_TYPE','F01','B05','B005','','','B05','05','N','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115008','WINDTWO_TG_TYPE','F01','B05','B007','','','B05','07','N','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115009','WINDTWO_TG_TYPE','F01','B05','B004','','','B05','04','Y','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115010','WINDTWO_TG_TYPE','F01','B05','B005','','','B05','05','Y','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115011','WINDTWO_TG_TYPE','F01','B05','B007','','','B05','07','Y','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115012','WINDTWO_TG_TYPE','F01','B05','B003','','','B05','03','Y','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115013','WINDTWO_TG_TYPE','F01','B05','B003','','','B05','03','N','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115014','WINDTWO_TG_TYPE','F01','B06','B009','','','B06','09','','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115015','WINDTWO_TG_TYPE','F01','B08','B012','','','B08','12','','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115016','WINDTWO_TG_TYPE','F01','B08','B013','','','B08','13','','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115017','WINDTWO_TG_TYPE','F01','B04','B04','','','B04','','Y','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115018','WINDTWO_TG_TYPE','F01','B04','B04','','','B04','','N','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('111111115019','WINDTWO_TG_TYPE','F01','B06','B008','','','B06','08','','','','','system','',to_date('2019/9/20', 'yyyy/mm/dd'), 'system',to_date('2019/9/20', 'yyyy/mm/dd'));

commit;