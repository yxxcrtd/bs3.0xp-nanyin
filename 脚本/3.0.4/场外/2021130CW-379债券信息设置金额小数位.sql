update CFG_DATATABLE_SHOW set EXTENDCFG ='{"colAlign": "right","dataType":"amount","precision":"4"}' 
where CFG_DATATABLE_SHOW.PAGECODE = 'CfetsEtrBondInfo' 
and CFG_DATATABLE_SHOW.DTCODE = 'BASEINFO'
and CFG_DATATABLE_SHOW.COLNAME in ('rate','priceXq','priceTj','gzRate');