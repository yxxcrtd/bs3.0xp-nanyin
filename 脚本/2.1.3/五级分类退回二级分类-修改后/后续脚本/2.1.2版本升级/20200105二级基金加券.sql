-----------三级基金加券---------------------
DELETE FROM FIN_PRODUCT_TYPE_CONFIG T
 WHERE T.SEQNO IN ('000000005030',
                   '000000005031',
                   '000000005032',
                   '000000005033',
                   '000000005034',
                   '000000005035');
insert into FIN_PRODUCT_TYPE_CONFIG values ('000000005030','WINDTWO_TG_TYPE','F02','F01','F01','','','','','','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('000000005031','WINDTWO_TG_TYPE','F02','F02','F001','','','01','','','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('000000005032','WINDTWO_TG_TYPE','F02','F02','F003','','','03','','','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('000000005033','WINDTWO_TG_TYPE','F02','F02','F004','','','04','','','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('000000005034','WINDTWO_TG_TYPE','F02','F02','F006','','','06','','','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_TYPE_CONFIG values ('000000005035','WINDTWO_TG_TYPE','F02','F02','F007','','','07','','','','','','system','',to_date('2019/7/22', 'yyyy/mm/dd'), 'system',to_date('2019/7/22', 'yyyy/mm/dd'));


commit;