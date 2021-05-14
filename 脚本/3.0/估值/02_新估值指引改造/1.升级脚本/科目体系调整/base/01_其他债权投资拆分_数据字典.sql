--数据字典：指令类型
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE='ORDER_TYPE' AND ITEM_CODE IN ('N3501','N3601','N3701','N3505','N3606','N3705','N2752','N2753','N2754','N2755','N2756','N2757','2750','2751');
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N3501','摊余成本类其他债权投资-买入','178','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N3601','综合收益类其他债权投资-买入','179','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N3701','公允价值变动类其他债权投资-买入','180','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N3505','摊余成本类其他债权投资-卖出','181','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N3606','综合收益类其他债权投资-卖出','182','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N3705','公允价值变动类其他债权投资-卖出','183','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N2752','资金收款-摊余成本类其他债权投资收息','184','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N2753','资金收款-公允价值变动类其他债权投资收息','185','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N2754','资金收款-综合收益类其他债权投资收息','186','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N2755','资金收款-摊余成本类其他债权投资还本','187','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N2756','资金收款-公允价值变动类其他债权投资还本','188','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N2757','资金收款-综合收益类其他债权投资还本','189','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','2750','资金划款-其他债权投资买入','190','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','2751','资金收款-其他债权投资卖出','191','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);

--数据字典：会计场景代码
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE='TABLE_ID' AND ITEM_CODE IN (
'3501','3502','3503','3504','3505','3506','3507','3601',
'3602','3603','3604','3605','3606','3607','3608',
'3701','3702','3703','3704','3705','3706','2750',
'2751','2752','2753','2754','2755','2756','2757');
insert into SYS_DATADICT_LIST values ('TABLE_ID','3501','摊余成本类其他债权投资-买入','543','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3502','摊余成本类其他债权投资-利息计提','544','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3503','摊余成本类其他债权投资-收息','545','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3504','摊余成本类其他债权投资-还本到期','546','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3505','摊余成本类其他债权投资-卖出','547','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3506','摊余成本类其他债权投资-减值计提','548','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3507','摊余成本类其他债权投资-减值清理','549','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3601','综合收益类其他债权投资-买入','550','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3602','综合收益类其他债权投资-计提摊销','551','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3603','综合收益类其他债权投资-公允价值变动','552','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3604','综合收益类其他债权投资-收息','553','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3605','综合收益类其他债权投资-还本到期','554','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3606','综合收益类其他债权投资-卖出','555','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3607','综合收益类其他债权投资-减值计提','556','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3608','综合收益类其他债权投资-减值清理','557','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3701','公允价值变动类其他债权投资-买入','558','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3702','公允价值变动类其他债权投资-利息计提','559','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3703','公允价值变动类其他债权投资-收息','560','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3704','公允价值变动类其他债权投资-还本到期','561','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3705','公允价值变动类其他债权投资-卖出','562','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','3706','公允价值变动类其他债权投资-公允价值变动','563','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2750','资金划款-其他债权投资买入','564','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2751','资金收款-其他债权投资卖出','565','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2752','资金收款-摊余成本类其他债权投资收息','567','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2753','资金收款-公允价值变动类其他债权投资收息','568','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2754','资金收款-综合收益类其他债权投资收息','569','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2755','资金收款-摊余成本类其他债权投资还本','570','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2756','资金收款-公允价值变动类其他债权投资还本','571','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2757','资金收款-综合收益类其他债权投资还本','572','0','system','',to_date('2020/12/11', 'yyyy/mm/dd'), 'system',to_date('2020/12/11', 'yyyy/mm/dd'),NULL);
