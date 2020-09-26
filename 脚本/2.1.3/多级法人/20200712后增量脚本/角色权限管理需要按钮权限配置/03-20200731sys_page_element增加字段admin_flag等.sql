-- Add/modify columns 
alter table SYS_PAGE_ELEMENT add admin_flag VARCHAR2(2);
-- Add comments to the columns 
comment on column SYS_PAGE_ELEMENT.admin_flag
  is '是否管理权限，01：是';
  
  
  UPDATE SYS_PAGE_ELEMENT SET admin_flag = '01' WHERE remark IN ('查询','导出');
  
    UPDATE SYS_PAGE_ELEMENT spe SET admin_flag = '01' WHERE page_id IN (
    SELECT page_id FROM sys_page sp WHERE  sp.system_code = 'SYS'
    );