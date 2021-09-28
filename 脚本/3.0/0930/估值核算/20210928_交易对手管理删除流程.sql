--交易对手管理 删除流程配置
update sys_page set is_need_workflow ='01'  where page_id='ComTradeOpponent001';
update sys_wfl_business_module set module_id='oneApproval' where page_id='ComTradeOpponent002';