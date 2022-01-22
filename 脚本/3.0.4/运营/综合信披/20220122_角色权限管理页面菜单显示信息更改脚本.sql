--sys_page表中menu_id与sys_menu中的保持一致，角色权限管理使用
update sys_page set sys_page.menu_id = (select menu_id from sys_menu where sys_menu.page_id = sys_page.page_id and
(sys_menu.menu_id != sys_page.menu_id or sys_page.menu_id is null ))
where
exists (
select 1 from
sys_menu
where
sys_menu.page_id = sys_page.page_id and
(sys_menu.menu_id != sys_page.menu_id or sys_page.menu_id is null )
);