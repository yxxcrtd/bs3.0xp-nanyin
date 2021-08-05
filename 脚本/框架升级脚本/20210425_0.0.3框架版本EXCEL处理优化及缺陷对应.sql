-- 0.0.3框架版本EXCEL处理优化对应(BASE库执行)
DELETE FROM SYS_PARA WHERE PARA_CODE = 'export_excel_type';
-- mysql
--insert into SYS_PARA values ('SYS', 'export_excel_type', '01', '01', '数据字典（是否）', '兼容选择导出excel类型（01:xlsx，02:xls）', 'system','','2021/04/25 10:00:00', 'system','2021/04/25 10:00:00',null,'02');
-- oracle
insert into SYS_PARA values ('SYS', 'export_excel_type', '01', '01', '数据字典（是否）', '兼容选择导出excel类型（01:xlsx，02:xls）', 'system','',to_date('2021/04/25', 'yyyy/mm/dd'), 'system',to_date('2021/04/25', 'yyyy/mm/dd'),null,'02');

-- 0.0.3框架版本mysql start with connect by对应
/*use hx2jgbs_test;
DROP FUNCTION IF EXISTS queryChildrenAreaInfo;
create function queryChildrenAreaInfo(tableName varchar(100), areaId varchar(100)) returns varchar(10000)
BEGIN
DECLARE sTemp VARCHAR(10000);
DECLARE sTempChd VARCHAR(10000);
SET sTemp='$';
SET sTempChd = CAST(areaId AS CHAR);
IF tableName='bok_batch_task_grp' THEN
			WHILE sTempChd IS NOT NULL DO
			SET sTemp= CONCAT(sTemp,',',sTempChd);
			SELECT GROUP_CONCAT(bbtg.TASK_GRP_ID) INTO sTempChd FROM bok_batch_task_grp bbtg WHERE FIND_IN_SET(bbtg.P_TASK_GRP_ID,sTempChd)>0;
			END WHILE;	
ELSEIF tableName='sys_institution' THEN
			WHILE sTempChd IS NOT NULL DO
			SET sTemp= CONCAT(sTemp,',',sTempChd);
			SELECT GROUP_CONCAT(si.INSTITUTION_CODE) INTO sTempChd FROM sys_institution si WHERE FIND_IN_SET(si.UPPER_INSTITUTION_CODE,sTempChd)>0;
			END WHILE;
ELSEIF tableName='sys_department' THEN
			WHILE sTempChd IS NOT NULL DO
			SET sTemp= CONCAT(sTemp,',',sTempChd);
			SELECT GROUP_CONCAT(sd.DEPT_CODE) INTO sTempChd FROM SYS_DEPARTMENT sd WHERE FIND_IN_SET(sd.UPPER_DEPT_CODE,sTempChd)>0;
			END WHILE;
ELSEIF tableName='temp_test' THEN
			WHILE sTempChd IS NOT NULL DO
			SET sTemp= CONCAT(sTemp,',',sTempChd);
			SELECT GROUP_CONCAT(tt.idTree) INTO sTempChd FROM TEMP_TEST tt WHERE FIND_IN_SET(tt.fPortfolioId,sTempChd)>0;
			END WHILE;
END IF;
RETURN sTemp;
END;

*/
