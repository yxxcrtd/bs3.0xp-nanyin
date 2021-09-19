-- 画面表-增加机构选择弹出画面
DELETE FROM SYS_PAGE WHERE PAGE_ID = 'SelectInstitution';
INSERT INTO SYS_PAGE VALUES ('SelectInstitution', 'SYS', '/main/selectInstitution', 'main/selectInstitution', null, null, null, '02', '02', '机构选择', 'system', null, to_date('2021/07/16', 'yyyy/mm/dd'), 'system', to_date('2021/07/16', 'yyyy/mm/dd'));
