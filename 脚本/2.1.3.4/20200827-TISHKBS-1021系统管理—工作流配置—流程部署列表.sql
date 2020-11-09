
------ add by wj 20200828 TISHKBS-1021  系统管理—工作流配置—流程部署列表

alter table Sys_Page_Element modify (admin_flag varchar2(4) );
        
UPDATE Sys_Page_Element SET admin_flag = 'COM' WHERE ADMIN_FLAG = '01';
UPDATE Sys_Page_Element SET admin_flag = 'S' WHERE page_id = 'SysBatchProcess001' AND
element_id  IN ('SysBatchProcess001_TBLROW_edit','SysBatchProcess001_btn_add','SysBatchProcess001_btn_delete');

UPDATE sys_page_element SET  admin_flag = 'C' 
WHERE admin_flag IS  NULL;  



---- 若 未更新框架版本，误刷以上脚本，可以使用下面的脚本 回退
 -- UPDATE sys_page_element SET  admin_flag = ''  WHERE admin_flag  IN ('C');
  --     UPDATE Sys_Page_Element SET admin_flag = '01' WHERE ADMIN_FLAG IN ( 'COM','S');
  
