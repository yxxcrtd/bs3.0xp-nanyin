--select * from cfg_datatable_show where PAGECODE='cfetsManualSettleQuery' and dtcode='CfetsManualSettleInfo' and SPCDCODE='04,M,P';
update cfg_readonly_form_show set COLNAME='理论交收金额' where PAGECODE='cfetsManualSettleQuery' and dtcode='CfetsManualSettleInfo' and SPCDCODE='04,M' and colcode='intAmtTheory';
update cfg_datatable_show set colname ='理论交收金额（元）' where pagecode='cfetsManualSettleQuery' and dtcode='CfetsManualSettleInfo'  and spcdcode='04,M,P' and colcode='totalAmtTheory';
