-- 0.0.3框架版本EXCEL处理优化对应(BASE库执行)
DELETE FROM SYS_PARA WHERE PARA_CODE = 'export_excel_type';
-- oracle
insert into SYS_PARA values ('SYS', 'export_excel_type', '01', '01', '数据字典（是否）', '兼容选择导出excel类型（01:xlsx，02:xls）', 'system','',to_date('2021/04/25', 'yyyy/mm/dd'), 'system',to_date('2021/04/25', 'yyyy/mm/dd'),null,'02');

