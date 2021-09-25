--交易对手管理页面交易对手类别下拉显示调整

update SYS_PAGE_DICTLIST set dict_item_val='03' where page_id='ComTradeOpponent001' and dict_id='COUNTER_TYPE' and dict_item_val='06';

update SYS_PAGE_DICTLIST set dict_item_val='03' where page_id='ComTradeOpponent002' and dict_id='COUNTER_TYPE' and dict_item_val='06';