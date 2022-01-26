--删除多余字段脚本
delete from  CFG_DATATABLE_SHOW where DTCODE='CfetsManualSettleInfo' and PAGECODE='cfetsManualSettleQuery' and SPCDCODE='02,M,P' and COLCODE in ('spanTextTwo','unFinshAmt');

