--增加系统参数
delete from sys_para where para_code = 'IS_AVERAGE_DISTRIBUTION_OF_EXPENSES';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COM', 'IS_AVERAGE_DISTRIBUTION_OF_EXPENSES', 'N', '01', null, '费用计算是否根据组合平均分配（Y:平均分配；N:按比例分配）', 'system', null, '21-11月-19 04.05.38.930709 下午', 'admin', '21-11月-19 04.05.38.930709 下午');
