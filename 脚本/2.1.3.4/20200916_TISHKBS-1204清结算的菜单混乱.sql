  
--  TISHKBS-1204
--- 多级法人模式-【角色权限管理】：清结算的菜单混乱
  UPDATE sys_page SET remark = '来账分拣' WHERE page_id = 'InflowFundsCheckManagement001';
  UPDATE sys_page SET remark = '现金流查询' WHERE page_id = 'CashFlowBalanceManagement001';