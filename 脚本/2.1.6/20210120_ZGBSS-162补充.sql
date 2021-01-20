--每只现金管理类产品投资于所有主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的10%
update RSK_EVENT_ITEM_RULE set item_value = '0,1,2' where event_code in ('EVENT_ID_TZYSYZTXYPJDYAAAJGHJ_07','EVENT_ID_TZYSYZTXYPJDYAAAJGHJ_08') and item_code = 'eventType';
delete from RSK_EVENT_ITEM_RULE where event_code in ('EVENT_ID_TZYSYZTXYPJDYAAAJGHJ_05','EVENT_ID_TZYSYZTXYPJDYAAAJGHJ_06');
--每只现金管理类产品投资于单一主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的2%
update RSK_EVENT_ITEM_RULE set item_value = '0,1,2' where event_code in ('EVENT_ID_DYZTPJDYAAAJGJRGJGZ_07','EVENT_ID_DYZTPJDYAAAJGJRGJGZ_08') and item_code = 'eventType';
delete from RSK_EVENT_ITEM_RULE where event_code in ('EVENT_ID_DYZTPJDYAAAJGJRGJGZ_05','EVENT_ID_DYZTPJDYAAAJGJRGJGZ_06');
--有固定期限银行存款的比例合计不得超过该产品资产净值的30%
--每只现金管理类产品投资于主体信用评级为AAA的同一商业银行的银行存款、同业存单占该产品资产净值的比例合计不得超过10%
update RSK_EVENT_ITEM_RULE set item_value = '0,1,2' where event_code in ('EVENT_ID_ZTPJAAAYHCKGZ_07','EVENT_ID_ZTPJAAAYHCKGZ_08') and item_code = 'eventType';
delete from RSK_EVENT_ITEM_RULE where event_code in ('EVENT_ID_ZTPJAAAYHCKGZ_05','EVENT_ID_ZTPJAAAYHCKGZ_06');
--杠杆水平巨额赎回除外
--现金管理类产品不得投资于股票、可转换债券、可交换债券、浮动利率债券、信用等级在AA+以下的债券和资产支持证券
--现金管理类产品持有不低于该产品资产净值百分之十
--到期日在10个交易日以上的债券买入返售、银行定期存款，以及流动性受限资产，合计不得超过该产品资产净值的10%
update RSK_EVENT_ITEM_RULE set item_value = '0,1,2' where event_code in ('EVENT_ID_LDXSXZCGZ_07','EVENT_ID_LDXSXZCGZ_08') and item_code = 'eventType';
delete from RSK_EVENT_ITEM_RULE where event_code in ('EVENT_ID_LDXSXZCGZ_05','EVENT_ID_LDXSXZCGZ_06');
--银行存款、债券回购、中央银行票据、同业存单的期限在1年以内（含1年）
--现金管理类产品应当投资于剩余期限在397天以内（含397天）的债券、在银行间市场和证券交易所市场发行的资产支持证券