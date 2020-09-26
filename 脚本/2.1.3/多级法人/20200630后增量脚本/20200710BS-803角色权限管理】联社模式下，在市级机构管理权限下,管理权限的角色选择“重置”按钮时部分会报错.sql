---- 将配置的clear结尾的重置改为 reset

 UPDATE Sys_Page_Element   SET   element_id = 
  SUBSTR(element_id,0 ,LENGTH(element_id)-5 )||'reset' 
     WHERE element_id LIKE '%_clear';