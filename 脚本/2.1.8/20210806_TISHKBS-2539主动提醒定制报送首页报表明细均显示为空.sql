

----  删除参数
DELETE  SYS_PARA WHERE PARA_CODE = 'SVR_FIRST_MENU_ID';

---  新增 SVR_FIRST_MENU_ID  ，， 值为监管报送的 模块id 
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
select 'SVR', 'SVR_FIRST_MENU_ID', menu_id ,'01', null, '中债报送模块配置的menuId', 'system', null, SYSDATE, 'system', SYSDATE, null, '02' from sys_menu where remark = '监管报送';
