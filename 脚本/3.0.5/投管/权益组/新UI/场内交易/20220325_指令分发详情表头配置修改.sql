update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"3","unit":"yuan"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'F01EX002' and COLCODE = 'unitPrice';
update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"3","unit":"yuan"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'F01EX002' and COLCODE = 'allPrice';
update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"4","unit":"%"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'F01EX002' and COLCODE = 'matureYield';
update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"0","unit":"zhang"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'F01EX002' and COLCODE = 'entrustNum';
update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"2","unit":"yuan"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'F01EX002' and COLCODE = 'entrustAmt';

update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"0","unit":"zhang"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'EX005' and COLCODE = 'pledgeStandardNumber';

update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"2","unit":"yuan"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'F01EX001' and COLCODE = 'unitPrice';
update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"4","unit":"yuan"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'F01EX001' and COLCODE = 'allPrice';
update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"4","unit":"%"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'F01EX001' and COLCODE = 'matureYield';
-- 股票 - 配股
update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"0","unit":"gu"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'EX008' and COLCODE = 'entrustNum';
-- 股票 新股申购
update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"0","unit":"gu"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'EX009' and COLCODE = 'entrustNum';


-- 质押式回购 - 竞价交易
update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"2","unit":"%"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'F07EX001' and COLCODE = 'unitPrice';
update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"2","unit":"yuan"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'F07EX001' and COLCODE = 'entrustAmt';
update CFG_READONLY_FORM_SHOW set EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"0","unit":"zhang"}' where PAGECODE = 'CfetsEtrDispatcher' and DTCODE = 'CfetsEtrDispatcherInfo' and SPCDCODE = 'F07EX001' and COLCODE = 'pledgeStandardNumber';


--
