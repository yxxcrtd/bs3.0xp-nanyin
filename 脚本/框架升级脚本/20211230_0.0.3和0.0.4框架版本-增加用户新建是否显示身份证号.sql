-- 0.0.3和0.0.4框架版本-增加用户新建是否显示身份证号系统参数
DELETE FROM SYS_PARA WHERE PARA_CODE = 'IS_SHOW_SFZH';
INSERT INTO SYS_PARA values ('SYS','IS_SHOW_SFZH','02','01','','用户新建是否显示身份证号（01:是、02:否）','system','',to_date('2021/12/29', 'yyyy/mm/dd'), 'system',to_date('2021/12/29', 'yyyy/mm/dd'), null, '01');

-- 0.0.3和0.0.4框架版本-增加身份证号是否必输系统参数
DELETE FROM SYS_PARA WHERE PARA_CODE = 'IS_REQUIRED_SFZH';
INSERT INTO SYS_PARA values ('SYS','IS_REQUIRED_SFZH','02','01','','身份证号是否必输（01:是、02:否）','system','',to_date('2021/12/29', 'yyyy/mm/dd'), 'system',to_date('2021/12/29', 'yyyy/mm/dd'), null, '01');

-- 0.0.3和0.0.4框架版本-系统用户表增加身份证号字段
ALTER TABLE SYS_USER ADD SFZH VARCHAR(100);
COMMENT ON COLUMN SYS_USER.SFZH IS '身份证号';
