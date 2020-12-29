/****人行新增参数**/
DELETE FROM SYS_PARA WHERE PARA_CODE IN ('SVR_QDBK_IS_PRECISION','SVR_RAC_HAS_BOUNS');

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('SVR', 'SVR_QDBK_IS_PRECISION', '01', '01', '02', '余额计算时单位净值是否要保留5位小数，01：是，02：否', 'system', null, '15-APR-19 12.00.00.000000 AM', 'system', '15-APR-19 12.00.00.000000 AM', null, '01');
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('SVR', 'SVR_RAC_HAS_BOUNS', '01', '01', '01', '（募集兑付是否统计红利再投，01：是，02：否', 'system', null, '15-APR-19 12.00.00.000000 AM', 'system', '15-APR-19 12.00.00.000000 AM', null, '01');

/**时点报送维护页面**/
DELETE FROM SYS_PAGE WHERE PAGE_ID = 'RegTimePoints002';
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RegTimePoints002', 'SVR', '/svreport/system/regtimepoints/RegTimePoints002', 'svreport/system/regtimepoints/RegTimePoints002', null, null, null, '02', '01', '汇率行情信息新建', 'system', null, sysdate, 'system', sysdate);
