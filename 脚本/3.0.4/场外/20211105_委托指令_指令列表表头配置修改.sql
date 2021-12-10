update cfg_datatable_show set  colname='执行进度(%)' where pagecode='CfetsEtrBondInfo' and  dtcode='ETRINFOS'  and colcode='execProcess';
update cfg_datatable_show set  colname='成交进度(%)' where pagecode='CfetsEtrBondInfo' and  dtcode='ETRINFOS'  and colcode='transProcess';
update cfg_datatable_show set  colname='到期收益率(%)' where pagecode='CfetsEtrBondInfo' and  dtcode='ETRINFOS'  and colcode='matureYield';
update cfg_datatable_show set  colname='行权收益率(%)' where pagecode='CfetsEtrBondInfo' and  dtcode='ETRINFOS'  and colcode='exerYield';
update cfg_datatable_show set  colname='债券分类' where pagecode='CfetsEtrBondInfo' and  dtcode='ETRINFOS'  and colcode='secType1Name';

update cfg_datatable_show set  extendcfg='{"colAlign": "right","dataType":"amount","precision":"4","supportSearch":"精确"}' where pagecode='CfetsEtrBondInfo' and  dtcode='ETRINFOS'  and colcode='unitPrice';
update cfg_datatable_show set  extendcfg='{"colAlign": "right","dataType":"amount","precision":"4","supportSearch":"精确"}' where pagecode='CfetsEtrBondInfo' and  dtcode='ETRINFOS'  and colcode='allPrice';
update cfg_datatable_show set  extendcfg='{"colAlign": "right","dataType":"amount","precision":"4","supportSearch":"精确"}' where pagecode='CfetsEtrBondInfo' and  dtcode='ETRINFOS'  and colcode='matureYield';
update cfg_datatable_show set  extendcfg='{"colAlign": "right","dataType":"amount","precision":"4","supportSearch":"精确"}' where pagecode='CfetsEtrBondInfo' and  dtcode='ETRINFOS'  and colcode='exerYield';

