-- 系统参数-允许导出选中行为01的时候，对象外画面一览。
DELETE FROM SYS_PARA WHERE PARA_CODE = 'export_selected_row';
insert into SYS_PARA values ('SYS', 'export_selected_row', '01', '01', null, '是否允许导出选中行，默认为否（01是，02否）', 'system','',to_date('2021/8/20', 'yyyy/mm/dd'), 'system',to_date('2021/8/20', 'yyyy/mm/dd'), null, '01');




