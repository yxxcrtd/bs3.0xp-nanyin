--费用支付交易：新增交易方向为【T10】的配置
DELETE FROM BOK_FEE_TABLE_SET WHERE FINPROD_TYPE='C11' AND PS ='T10';
insert into BOK_FEE_TABLE_SET values ('C11','T10','B01','3001','YFJSF','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'),'');
insert into BOK_FEE_TABLE_SET values ('C11','T10','B02','3001','YFYHS','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'),'');
insert into BOK_FEE_TABLE_SET values ('C11','T10','B03','3001','YFZGF','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'),'');
insert into BOK_FEE_TABLE_SET values ('C11','T10','B05','3001','YFYJ','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'),'');
insert into BOK_FEE_TABLE_SET values ('C11','T10','B06','3001','YFJYSXF','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'),'');
insert into BOK_FEE_TABLE_SET values ('C11','T10','B07','3001','YFJSF1','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'),'');
insert into BOK_FEE_TABLE_SET values ('C11','T10','B08','3001','YFZZDJSSXF','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'),'');
insert into BOK_FEE_TABLE_SET values ('C11','T10','B08','3001','YFSQSJSSXF','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'),'');
insert into BOK_FEE_TABLE_SET values ('C11','T10','B08','3001','YFZDJSSXF','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'),'');
insert into BOK_FEE_TABLE_SET values ('C11','T10','B13','1704','QTZC','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'),'');
insert into BOK_FEE_TABLE_SET values ('C11','T10','B99','1706','QTZC','system','',to_date('2018/12/4', 'yyyy/mm/dd'), 'system',to_date('2018/12/4', 'yyyy/mm/dd'),'');
