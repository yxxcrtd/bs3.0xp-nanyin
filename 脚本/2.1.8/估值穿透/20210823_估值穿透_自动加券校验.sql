update chb_datacheck_rule_inv set nullable='N'  where checkruleid='3fc4b95e-4469-4d07-b872-81386638951d' and page_id='FinBondTargetAsset002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid='884807E5-DF7A-D920-F150-117F010081FD' and page_id='FinFundTarget002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid='884807E5-DF7A-D920-F150-117F010082FD' and page_id='FinFundTarget002' and status='D';
