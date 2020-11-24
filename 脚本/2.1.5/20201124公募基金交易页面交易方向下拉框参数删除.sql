--add by yangwenjun 20201124 公募基金交易页面交易方向下拉框参数删除 start
delete from SYS_PAGE_DICTLIST where PAGE_ID='TrdFundInvestTrade001' and DICT_ID='PS' and DICT_ITEM_VAL in ('T05','T18','T19','T24','T66');
--add by yangwenjun 20201124 公募基金交易页面交易方向下拉框参数删除 end