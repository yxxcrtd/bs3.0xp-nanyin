delete from chb_datacheck_rule_inv where checkruleid='29b188c5-3c7c-4ed6-8d51-972236e57005' and page_id='FinBondTargetAsset002' and status='D';

delete from chb_datacheck_rule_inv where checkruleid in ('b46ff785-d69c-4074-8d84-b8f1ccd9a577','9f72f8e8-de6e-4eb4-a70d-0e3ebd7e86f4') and page_id='FinBondTargetAsset002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid in ('c985a4d1-4653-41b5-9695-806a74f85515','60da512f-aeed-43f9-8517-738eccdea871') and page_id='FinBondTargetAsset002' and status='D';
update chb_datacheck_rule_inv set remark='基金投资资产不能超过400字符'  where checkruleid='443E4A49-6810-5A9E-F150-007F01006A26' and page_id='FinFundTarget002' and status='D';
update chb_datacheck_rule_inv set remark='基金公司名称不能超过200字符'  where checkruleid='223E4A49-6810-5A9E-F150-007F01006A93' and page_id='FinFundTarget002' and status='D';
update chb_datacheck_rule_inv set remark='基金托管机构名称不可为空且不能超过200字符'  where checkruleid='443E4A49-6810-5A9E-F150-007F01006A25' and page_id='FinFundTarget002' and status='D';
update chb_datacheck_rule_inv set remark='名称不可为空且不能超过200字符'  where checkruleid='5abd7c53-e927-56b8-80b3-1fdd9d37d009' and page_id='FinFundTarget002' and status='D';

delete from  chb_datacheck_condition_inv where checkruleid in ('b46ff785-d69c-4074-8d84-b8f1ccd9a577','9f72f8e8-de6e-4eb4-a70d-0e3ebd7e86f4','60da512f-aeed-43f9-8517-738eccdea871');

delete from chb_datacheck_rule_inv where checkruleid='6baef1d8-56e8-49cb-a78a-c73f35b973a1' and page_id='FinBondTargetAsset002' and status='D';
delete from  chb_datacheck_condition_inv where checkruleid='6baef1d8-56e8-49cb-a78a-c73f35b973a1';

delete from chb_datacheck_rule_inv where checkruleid='6542fcf4-8ef3-445d-be1e-befe58f98787' and page_id='FinBondTargetAsset002' and status='D';
delete from  chb_datacheck_condition_inv where checkruleid='6542fcf4-8ef3-445d-be1e-befe58f98787';
delete from chb_datacheck_rule_inv where checkruleid='487bc868-722c-4829-8f1f-763e69f7b028' and page_id='FinBondTargetAsset002' and status='D';
delete from  chb_datacheck_condition_inv where checkruleid='487bc868-722c-4829-8f1f-763e69f7b028';
delete from chb_datacheck_rule_inv where checkruleid='636c51b3-bde8-4980-8808-ca7ae6d20593' and page_id='FinBondTargetAsset002' and status='D';
delete from  chb_datacheck_condition_inv where checkruleid='636c51b3-bde8-4980-8808-ca7ae6d20593';
delete from chb_datacheck_rule_inv where checkruleid='3b71a727-421a-4a3a-bf8e-05f8a7782f55' and page_id='FinBondTargetAsset002' and status='D';
delete from  chb_datacheck_condition_inv where checkruleid='3b71a727-421a-4a3a-bf8e-05f8a7782f55';
delete from chb_datacheck_rule_inv where checkruleid='e79b89c0-7ac9-4bd4-9647-ef51f61b42c8' and page_id='FinBondTargetAsset002' and status='D';
delete from  chb_datacheck_condition_inv where checkruleid='e79b89c0-7ac9-4bd4-9647-ef51f61b42c8';

delete from chb_datacheck_rule_inv where checkruleid='003E4A49-6810-5A9E-F150-007F01016A56' and page_id='FinFundTarget002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid='984807E5-KF28-D920-E060-007F0100ZQ32' and page_id='FinFundTarget002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid='003E4A49-6810-5A9E-F150-007F01026A56' and page_id='FinFundTarget002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid='284807E5-DF28-D920-F150-007F010099FD' and page_id='FinFundTarget002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid='284807E5-DF28-D920-F150-007F010098FD' and page_id='FinFundTarget002' and status='D';

delete from chb_datacheck_rule_inv where checkruleid='284807E5-DF28-D920-E050-007F010098FD' and page_id='FinStockInfo002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid='003E4A49-6810-5A9E-E050-007F01026A56' and page_id='FinStockInfo002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid='003E4A49-6810-5A9E-E050-007F01016A56' and page_id='FinStockInfo002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid='284807E5-DF28-D920-E050-007F010099FD' and page_id='FinStockInfo002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid='884807E5-EF00-D124-E050-007F010077FD' and page_id='FinStockInfo002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid='884807E5-EF00-D123-E050-007F010077FD' and page_id='FinStockInfo002' and status='D';



