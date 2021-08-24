-- 系统参数-是否做文件名特殊字符检查（生产必须是02，只有行方要做代码扫描的时候才能设置成01）
DELETE FROM SYS_PARA WHERE PARA_CODE = 'IS_NEED_CHECK_RANGE';
insert into SYS_PARA values ('SYS', 'IS_NEED_CHECK_RANGE', '02', '01', null, '是否做文件名特殊字符检查（01:是,02:否）', 'system','',to_date('2021/8/20', 'yyyy/mm/dd'), 'system',to_date('2021/8/20', 'yyyy/mm/dd'), null, '01');




