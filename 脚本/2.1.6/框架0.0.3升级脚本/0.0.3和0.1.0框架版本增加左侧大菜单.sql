-- 左侧菜单展示模式的系统参数
DELETE FROM SYS_PARA WHERE PARA_CODE = 'LEFT_MENU_DISPLAY_TYPE';
INSERT INTO SYS_PARA values ('SYS','LEFT_MENU_DISPLAY_TYPE','02','01','','左侧菜单展示模式（01:现有的一级一级展示、02:光大的子菜单合并展示）','system','',to_date('2021/03/05', 'yyyy/mm/dd'), 'system',to_date('2021/03/05', 'yyyy/mm/dd'),null, '01' );
