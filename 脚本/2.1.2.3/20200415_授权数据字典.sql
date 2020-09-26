delete from sys_datadict_list where dict_code='RSK_AUTHORIZE_TYPE1';
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','01','存款','1','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','05','拆借','5','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','06','回购','6','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','07','债券','7','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','08','项目类资产','8','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','16','基金','16','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','20','计划类资产','20','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','12','债券借贷','12','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','13','外汇即期','13','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','14','外汇远期','14','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','15','外汇掉期','15','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','11','股票','11','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE1','17','利率互换','17','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));

delete from sys_datadict_list where dict_code='RSK_AUTHORIZE_TYPE2';
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE2','01','利率债','1','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into sys_datadict_list values ('RSK_AUTHORIZE_TYPE2','02','信用债','2','0','system','',to_date('2020/03/09', 'yyyy/mm/dd'), 'system',to_date('2020/03/09', 'yyyy/mm/dd'));


delete from SYS_DATADICT_LIST_LINK where dict_code='PS' and linked_dict_code='RSK_AUTHORIZE_TYPE1';
insert into SYS_DATADICT_LIST_LINK values('PS','T01','RSK_AUTHORIZE_TYPE1','07','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T52','RSK_AUTHORIZE_TYPE1','16','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T01','RSK_AUTHORIZE_TYPE1','20','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T01','RSK_AUTHORIZE_TYPE1','01','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T01','RSK_AUTHORIZE_TYPE1','08','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T02','RSK_AUTHORIZE_TYPE1','07','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T19','RSK_AUTHORIZE_TYPE1','16','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T02','RSK_AUTHORIZE_TYPE1','20','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T02','RSK_AUTHORIZE_TYPE1','01','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T02','RSK_AUTHORIZE_TYPE1','08','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T04','RSK_AUTHORIZE_TYPE1','16','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T04','RSK_AUTHORIZE_TYPE1','20','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T06','RSK_AUTHORIZE_TYPE1','20','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T01','RSK_AUTHORIZE_TYPE1','13','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T02','RSK_AUTHORIZE_TYPE1','13','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T01','RSK_AUTHORIZE_TYPE1','14','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T02','RSK_AUTHORIZE_TYPE1','14','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T_B/S','RSK_AUTHORIZE_TYPE1','15','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T_S/B','RSK_AUTHORIZE_TYPE1','15','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T01','RSK_AUTHORIZE_TYPE1','11','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T02','RSK_AUTHORIZE_TYPE1','11','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T05','RSK_AUTHORIZE_TYPE1','16','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T18','RSK_AUTHORIZE_TYPE1','16','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T06','RSK_AUTHORIZE_TYPE1','16','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T05','RSK_AUTHORIZE_TYPE1','20','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T19','RSK_AUTHORIZE_TYPE1','20','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T24','RSK_AUTHORIZE_TYPE1','16','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('PS','T24','RSK_AUTHORIZE_TYPE1','20','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST_LINK where dict_code='BACK_LEND_DIRECTION' and linked_dict_code='RSK_AUTHORIZE_TYPE1';
insert into SYS_DATADICT_LIST_LINK values('BACK_LEND_DIRECTION','01','RSK_AUTHORIZE_TYPE1','05','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('BACK_LEND_DIRECTION','02','RSK_AUTHORIZE_TYPE1','05','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('BACK_LEND_DIRECTION','03','RSK_AUTHORIZE_TYPE1','06','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('BACK_LEND_DIRECTION','04','RSK_AUTHORIZE_TYPE1','06','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('BACK_LEND_DIRECTION','05','RSK_AUTHORIZE_TYPE1','12','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('BACK_LEND_DIRECTION','06','RSK_AUTHORIZE_TYPE1','12','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST_LINK where dict_code='IRS_TYPE' and linked_dict_code='RSK_AUTHORIZE_TYPE1';
insert into SYS_DATADICT_LIST_LINK values('IRS_TYPE','01','RSK_AUTHORIZE_TYPE1','17','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values('IRS_TYPE','02','RSK_AUTHORIZE_TYPE1','17','system','',to_date('2020/03/09', 'yyyy/mm/dd'),'system',to_date('2020/03/09', 'yyyy/mm/dd'));

commit;