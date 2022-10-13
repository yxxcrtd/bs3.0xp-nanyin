delete from SYS_PAGE where PAGE_ID = 'DisProdPlanManage003';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DisProdPlanManage003', 'DIS', '/disclosure/information/config/disprodplanmanage/DisProdPlanManage003', '/disclosure/information/config/disprodplanmanage/DisProdPlanManage003', 'null', null, null, '02', '02', '报表文本描述产品树', 'system', null,SYSDATE, 'system', SYSDATE);
