--每只现金管理类产品投资于所有主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的10%
update RSK_ISUP_INDICATOR set time_point = '01,02,03' where ind_name = '每只现金管理类产品投资于所有主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的10%';
--每只现金管理类产品投资于单一主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的2%
update  RSK_ISUP_INDICATOR set time_point = '01,02,03' where ind_name = '每只现金管理类产品投资于单一主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的2%';
--有固定期限银行存款的比例合计不得超过该产品资产净值的30%
update  RSK_EVENT_ITEM_RULE set  item_value = '0,1,2'  where event_code  in ('EVENT_ID_GDQXCKGZ_07','EVENT_ID_GDQXCKGZ_08') and  item_code = 'eventType'; 
--每只现金管理类产品投资于主体信用评级为AAA的同一商业银行的银行存款、同业存单占该产品资产净值的比例合计不得超过10%
update RSK_ISUP_INDICATOR set time_point = '01,02,03' where  ind_name = '每只现金管理类产品投资于主体信用评级为AAA的同一商业银行的银行存款、同业存单占该产品资产净值的比例合计不得超过10%';
--杠杆水平巨额赎回除外
update RSK_ISUP_INDICATOR set time_point ='01,02,03' where ind_name = '杠杆水平巨额赎回除外';
--现金管理类产品不得投资于股票、可转换债券、可交换债券、浮动利率债券、信用等级在AA+以下的债券和资产支持证券
--现金管理类产品持有不低于该产品资产净值百分之十
update RSK_ISUP_INDICATOR set time_point ='01,02,03' where ind_name = '现金管理类产品持有不低于该产品资产净值百分之十';
--现金管理类产品应当投资于剩余期限在397天以内（含397天）的债券、在银行间市场和证券交易所市场发行的资产支持证券
update RSK_EVENT_ITEM_RULE set item_value = '0,1,2'  where event_code in  ('EVENT_ID_XJGLLCPYDTZYSYQJXZSJ_08','EVENT_ID_XJGLLCPYDTZYSYQJXZSJ_07') and item_code  = 'eventType';
--组合的平均剩余期限不得超过120天
--update RSK_ISUP_INDICATOR set time_point ='01,02,03' where ind_name = '组合的平均剩余期限不得超过120天';
--每只现金管理类产品投资组合的平均剩余存续期限不得超过240天
--现金管理类产品市值法资产净值与摊余成本法计算的资产净值正偏离度绝对值不超过0.5%
update RSK_ISUP_INDICATOR set time_point ='01,02,03' where ind_name = '现金管理类产品市值法资产净值与摊余成本法计算的资产净值正偏离度绝对值不超过0.5%';
update  RSK_EVENT_ITEM_RULE set item_value = '0,1,2' where event_code in ('EVENT_ID_SZFZCJZYTYCBFZCJZZPL_07','EVENT_ID_SZFZCJZYTYCBFZCJZZPL_08') and  item_code = 'eventType'; 
--现金管理类产品市值法资产净值与摊余成本法计算的资产净值负偏离度绝对值不超过0.25%
update RSK_ISUP_INDICATOR set time_point ='01,02,03' where ind_name = '现金管理类产品市值法资产净值与摊余成本法计算的资产净值负偏离度绝对值不超过0.25%';