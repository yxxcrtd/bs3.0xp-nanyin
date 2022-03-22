--本币业务交易
update CFG_DATATABLE_SHOW set COLNAME = '估价净价' where PAGECODE = 'IbuQuoteRepo' and DTCODE = 'ETRDETINFOPLEDGE' and COLCODE = 'firstNetPrice';
update CFG_DATATABLE_SHOW set COLNAME = '估价净价(元)' where PAGECODE = 'IbuQuoteRepo' and DTCODE = 'PLEDGEINFO' and COLCODE = 'firstNetPrice';
update CFG_DATATABLE_SHOW set COLNAME = '估价净价(元)' where PAGECODE = 'IbuQuoteRepo' and DTCODE = 'PLEDGEADDUSED' and COLCODE = 'firstNetPrice';
update CFG_DATATABLE_SHOW set COLNAME = '估价净价(元)' where PAGECODE = 'IbuQuoteRepo' and DTCODE = 'PLEDGEADDUSED04' and COLCODE = 'firstNetPrice';
update CFG_DATATABLE_SHOW set COLNAME = '估价净价(元)' where PAGECODE = 'IbuQuoteRepo' and DTCODE = 'ETRDETINFO' and COLCODE = 'firstNetPrice';
--成交确认
update CFG_DATATABLE_SHOW set COLNAME = '估价净价(元)' where PAGECODE = 'CfetsTrdQuery' and DTCODE = 'CfetsTrdQueryIbuPledgeInfo' and COLCODE = 'firstNetPrice';
update CFG_DATATABLE_SHOW set COLNAME = '估价净价(元)' where PAGECODE = 'CfetsTrdQuery' and DTCODE = 'CfetsTrdQueryRegPledgeInfo' and COLCODE = 'firstNetPrice';
--交收管理
update CFG_DATATABLE_SHOW set COLNAME = '估价净价(元)' where PAGECODE = 'cfetsManualSettleQuery' and DTCODE = 'CfetsManualSettleInfo' and COLCODE = 'firstNetPrice';