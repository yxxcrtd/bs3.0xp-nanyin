-- 0.0.3框架版本-联社模式是否只能省联社用户更新平台日
DELETE FROM SYS_PARA WHERE PARA_CODE = 'LIANSHE_ONLYSLS_UPDATE_SYSDATE';
INSERT INTO SYS_PARA values ('SYS','LIANSHE_ONLYSLS_UPDATE_SYSDATE','02','01','','联社模式是否只能省联社用户更新平台日（01:是、02:否）','system','',to_date('2021/08/19', 'yyyy/mm/dd'), 'system',to_date('2021/08/19', 'yyyy/mm/dd'), null, '01');
