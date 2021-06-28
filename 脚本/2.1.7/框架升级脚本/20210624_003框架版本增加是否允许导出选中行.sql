-- 系统参数-是否允许导出选中行
DELETE FROM SYS_PARA WHERE PARA_CODE = 'export_selected_row';
insert into SYS_PARA values ('SYS', 'export_selected_row', '02', '01', null, '是否允许导出选中行，默认为否（01是，02否）', 'system','',to_date('2020/2/11', 'yyyy/mm/dd'), 'system',to_date('2020/2/11', 'yyyy/mm/dd'), null, '01');
-- 系统参数-PARAM_VALUE字段变更为2000
ALTER TABLE SYS_PARA MODIFY PARAM_VALUE VARCHAR(2000);
-- 系统参数-允许导出选中行为01的时候，对象外画面一览。示例：框架以外的，例如中债的月度统计表，这个导出是按照中债模板的，需要作为对象外。还有哪些画面需要产品组细查，多个画面的时候，逗号分隔。
DELETE FROM SYS_PARA WHERE PARA_CODE = 'export_selected_row_not_target_page';
insert into SYS_PARA values ('SYS', 'export_selected_row_not_target_page', 'SysBatchMonitor002', 'CustomerReportConfig001', 'CustomerReportPanel001', 'SysBatchLog001', 'SysDataProcessMark001', 'SysParaSet001', 'SysWflBusinessModule001', 'SysActionLog001', 'SysErrorInfo001', 'SvrMonthReport001', '01', null, '允许导出选中行为01的时候，对象外画面一览', 'system','',to_date('2020/2/11', 'yyyy/mm/dd'), 'system',to_date('2020/2/11', 'yyyy/mm/dd'), null, '01');
