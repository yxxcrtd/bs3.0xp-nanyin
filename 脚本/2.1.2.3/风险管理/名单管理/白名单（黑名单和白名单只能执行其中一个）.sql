--白名单
delete from RSK_ACCESS_LIST_TYPE;
delete from rsk_access_list;

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('A1', 'AA01', '', '', '', '','01', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('A1', '债券白名单', '01', '02', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('C1', 'CC01', '', '', '', '','01', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('C1', '计划类利率型白名单', '01', '02', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('F1', 'FF01', '', '', '', '','01', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('F1', '项目类白名单', '01', '02', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('31', 'AA01', '', '', '', '','03', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('31', '债券发行人白名单', '01', '01', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('51', 'FF01', '', '', '', '','05', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('51', '融资人白名单', '01', '01', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('21', 'CC01', '', '', '', '','02', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('21', '托管人白名单', '01', '01', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('41', 'CC01', '', '', '', '','04', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('41', '管理人白名单', '01', '01', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

insert into RSK_ACCESS_LIST_TYPE (LIST_ID, RSK_ASSET_TYPE1, RSK_ASSET_TYPE2, RSK_ASSET_TYPE3, RSK_ASSET_TYPE4, RSK_ASSET_TYPE5, LIST_DETAIL_ID_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('61', 'CC01', '', '', '', '','03', 'admin', '', '03-3月 -20 11.03.45.000000 上午', 'admin', '03-3月 -20 11.03.45.000000 上午');

insert into rsk_access_list (LIST_ID, LIST_NAME, LIST_TYPE, LIST_DETAIL_TYPE, EFFDATE, DELDATE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, LIST_LEVEL)
values ('61', '计划类利率型发行人白名单', '01', '01', to_date('17-09-2017', 'dd-mm-yyyy'), to_date('20-09-2022', 'dd-mm-yyyy'), '', 'admin', '', sysdate, 'admin', sysdate, '');

