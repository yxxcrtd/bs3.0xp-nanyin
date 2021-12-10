update cfg_datatable_show set  frozen='0' where pagecode='CfetsEtrBondInfo' and  dtcode='ETRINFOS' ;
update cfg_datatable_show set  frozen='1' where pagecode='CfetsEtrBondInfo' and  dtcode='ETRINFOS' and colcode in ('effectiveStatusName','entrustStatusName','execProcess','transProcess');
