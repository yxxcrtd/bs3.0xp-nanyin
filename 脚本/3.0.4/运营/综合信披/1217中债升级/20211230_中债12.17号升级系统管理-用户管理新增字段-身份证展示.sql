-- 0.0.3和0.0.4框架版本-增加用户新建是否显示身份证号系统参数
DELETE FROM SYS_PARA WHERE PARA_CODE = 'IS_SHOW_SFZH';
INSERT INTO SYS_PARA values ('SYS','IS_SHOW_SFZH','01','01','','用户新建是否显示身份证号（01:是、02:否）','system','',to_date('2021/12/29', 'yyyy/mm/dd'), 'system',to_date('2021/12/29', 'yyyy/mm/dd'), null, '01');
