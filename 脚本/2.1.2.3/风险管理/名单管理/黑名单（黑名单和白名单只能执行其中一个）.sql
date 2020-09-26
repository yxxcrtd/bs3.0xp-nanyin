--黑名单
delete from RSK_ACCESS_LIST_TYPE;
delete from rsk_access_list;

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('A2', 'AA01', '', '', '', '','01', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('A2', '债券黑名单', '02', '02', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('C2', 'CC01', '', '', '', '','01', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('C2', '计划类利率型黑名单', '02', '02', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('F2', 'FF01', '', '', '', '','01', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('F2', '项目类黑名单', '02', '02', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('32', 'AA01', '', '', '', '','03', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('32', '债券发行人黑名单', '02', '01', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('52', 'FF01', '', '', '', '','05', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('52', '融资人黑名单', '02', '01', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('22', 'CC01', '', '', '', '','02', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('22', '托管人黑名单', '02', '01', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('42', 'CC01', '', '', '', '','04', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('42', '管理人黑名单', '02', '01', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('62', 'CC01', '', '', '', '','03', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('62', '计划类利率型发行人黑名单', '02', '01', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');
