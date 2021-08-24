delete from chb_datacheck_rule_inv where checkruleid='3fc5b962-0721-4235-aad0-822c460ba13b' and page_id='FinBondTargetAsset002' and status='D';
delete from  chb_datacheck_condition_inv where checkruleid='3fc5b962-0721-4235-aad0-822c460ba13b';
delete from chb_datacheck_rule_inv where checkruleid='ccea9114-8adc-4341-95ec-a31b93091fda' and page_id='FinBondTargetAsset002' and status='D';
delete from  chb_datacheck_condition_inv where checkruleid='ccea9114-8adc-4341-95ec-a31b93091fda';
delete from chb_datacheck_rule_inv where checkruleid='90da2a6d-b448-4486-92bd-c1fdf920684e' and page_id='FinBondTargetAsset002' and status='D';
delete from  chb_datacheck_condition_inv where checkruleid='90da2a6d-b448-4486-92bd-c1fdf920684e';
delete from chb_datacheck_rule_inv where checkruleid='f032c148-e29d-412e-a829-1ffaf6d70e71' and page_id='FinBondTargetAsset002' and status='D';
delete from  chb_datacheck_condition_inv where checkruleid='f032c148-e29d-412e-a829-1ffaf6d70e71';
update chb_datacheck_rule_inv set checkmethod='inDatadict',objvalue='YES_NO' where checkruleid='884807E5-DF75-D920-E050-107F010077FD' and page_id='FinStockInfo002' and status='D';
