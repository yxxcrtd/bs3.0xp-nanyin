--公共数据变更-数据修改的提醒天数设置需要修改成与原主动提醒逻辑一致，即通过sys_activereminder_set表配置，而非目前的系统参数实现
DELETE FROM SYS_ACTIVEREMINDER_SET WHERE REM_TYPE = 'R9S1' ;
INSERT INTO SYS_ACTIVEREMINDER_SET (REM_TYPE, DAYS, URL, LABLE, DEALF, CLOSF, REMARK, MENU_ID) VALUES ('R9S1', 0, null, null, 'N', 'N', null, null);
