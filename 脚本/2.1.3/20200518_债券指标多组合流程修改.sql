  --债券指标多组合流程修改
  delete from  SYS_WFL_BUSINESS_MODULE  where  PAGE_ID = 'EtrInvestBondInfo002' and ELEMENT_ID = 'EtrInvestBondInfo003_btn_submit';
  INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EtrInvestBondInfo002', 'EtrInvestBondInfo003_btn_submit', 'noneApproval', 'system', '0001', TIMESTAMP '2019-08-15 00:00:00', 'system', TIMESTAMP '2019-08-15 00:00:00');

 