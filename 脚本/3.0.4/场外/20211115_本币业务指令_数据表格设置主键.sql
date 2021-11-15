-- 指令列表，设置主键
update cfg_datatable_show set iskey = 1 where pagecode = 'CfetsEtrBondInfo' and dtcode = 'ETRINFOS' and colcode = 'entrustId';

-- 持仓列表，设置主键
update cfg_datatable_show set iskey = 1 
where pagecode = 'CfetsEtrBondInfo' and dtcode = 'BONDS' and colcode in('portfolioId','finprodMarketId','busiModelStr');