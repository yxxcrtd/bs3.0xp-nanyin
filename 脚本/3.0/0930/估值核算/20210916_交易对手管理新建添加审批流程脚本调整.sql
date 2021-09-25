--交易对手管理新建添加审批流程脚本调整

update SYS_WFL_BUSINESS_MODULE set MODULE_ID = 'oneApproval' where ELEMENT_ID = 'ComTradeOpponent002_btn_save';
update SYS_WFL_BUSINESS_MODULE set MODULE_ID = 'oneApproval' where ELEMENT_ID = 'ComTradeOpponent002_btn_submit';