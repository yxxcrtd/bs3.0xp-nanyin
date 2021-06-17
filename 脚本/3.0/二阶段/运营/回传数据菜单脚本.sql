DELETE
FROM
	sys_page
WHERE
	page_id = 'ValPublicDataexport001';

INSERT
	INTO
	sys_page (PAGE_ID,
	SYSTEM_CODE,
	PAGE_URL,
	PAGE_FILE,
	MENU_ID,
	HEIGHT,
	WIDTH,
	IS_HP_SELECT,
	IS_NEED_WORKFLOW,
	REMARK,
	CREATE_USER,
	CREATE_DEPT,
	CREATE_TIME,
	UPDATE_USER,
	UPDATE_TIME)
VALUES ('ValPublicDataexport001',
'VAL',
'/valuation/dailyoperation/dailyaccountmake/valdataexport/ValPublicDataexport001',
'valuation/dailyoperation/dailyaccountmake/valdataexport/ValPublicDataexport001',
'ACCOUTING_001_001_005',
NULL,
NULL,
'02',
'02',
'数据回传',
'system',
NULL,
SYSDATE,
'system',
SYSDATE);

DELETE
FROM
	sys_menu
WHERE
	menu_id = 'ACCOUTING_001_001_005';

INSERT
	INTO
	sys_menu (MENU_ID,
	SYSTEM_CODE,
	ICON,
	PARENT_MENU_ID,
	PAGE_ID,
	ORDER_NO,
	IS_SPREAD,
	REMARK,
	CREATE_USER,
	CREATE_DEPT,
	CREATE_TIME,
	UPDATE_USER,
	UPDATE_TIME)
VALUES ('ACCOUTING_001_001_005',
'VAL',
'',
'ACCOUTING_001_001',
'ValPublicDataexport001',
'1',
'02',
'数据回传',
'system',
NULL,
SYSDATE,
'system',
SYSDATE);