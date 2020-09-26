/** 收益型产品提前赎回交易类型下拉框无选项修改 **/
update sys_page_dictlist set dict_item_val = 'C17' where page_id = 'FinProductAddReduce001' and dict_id = 'PS' and dict_item_val = 'C19';
