/****添加转型情况月度报表按钮权限**/
DELETE FROM SYS_PAGE_ELEMENT WHERE ELEMENT_TYPE = 'ChbTransMonthReport001_btn_chbexport';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrTransMonthReport001', 'ChbTransMonthReport001_btn_chbexport', '01', '01', null, '导出中债excel', 'system', null, sysdate, 'system', sysdate);
/****添加转型情况月度报表按钮权限**/


/***删除任务组中[人行单产品dat文件生成]的跑批**/
DELETE from sys_batch_job where task_id = '3045';
/***删除任务组中[人行单产品dat文件生成]的跑批**/
