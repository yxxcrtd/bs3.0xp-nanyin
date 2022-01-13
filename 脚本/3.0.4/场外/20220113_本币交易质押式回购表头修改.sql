--CW-1379
update CFG_DATATABLE_SHOW set FROZEN = '1' where PAGECODE = 'IbuQuoteRepo' and DTCODE = 'ETRINFOS' and COLCODE = 'tradeFinishPercent';
update CFG_DATATABLE_SHOW set EXTENDCFG = '{"colAlign": "left","supportSearch":"精确"}' where PAGECODE = 'IbuQuoteRepo' and DTCODE = 'ETRINFOS' and COLCODE = 'entrustNum';