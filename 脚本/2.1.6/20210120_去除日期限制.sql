--到期日在一年以内的国债、中央银行票据和政策性金融债券规模 去除日期条件限制
delete from RSK_EVENT_ITEM_RULE   where event_code like 'EVENT_ID_XJGZZYYHPJZCXJRZQGM_0%' and item_code = 'mdate';