/* add by tanxuan on 20200316 估值方法方案交易场所增加境外交易场所 start */

DELETE FROM SYS_DATADICT_LIST_LINK where DICT_CODE = 'VALUATION_TRADE_MARKET' and ITEM_CODE = '10' and LINKED_DICT_CODE = 'SEC_TYPE' and LINKED_ITEM_CODE = '01';
insert into SYS_DATADICT_LIST_LINK values ('VALUATION_TRADE_MARKET','10','SEC_TYPE','01','system','',to_date('2020/03/16', 'yyyy/mm/dd'), 'system',to_date('2020/03/16', 'yyyy/mm/dd'));

/* add by tanxuan on 20200316 估值方法方案交易场所增加境外交易场所 start */