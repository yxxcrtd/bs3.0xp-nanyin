delete from chb_datacheck_rule_inv where checkruleid='29b188c5-3c7c-4ed6-8d51-972236e57005' and status='D';

delete from chb_datacheck_rule_inv where checkruleid in ('b46ff785-d69c-4074-8d84-b8f1ccd9a577','9f72f8e8-de6e-4eb4-a70d-0e3ebd7e86f4') and page_id='FinBondTargetAsset002' and status='D';
delete from chb_datacheck_rule_inv where checkruleid in ('c985a4d1-4653-41b5-9695-806a74f85515','60da512f-aeed-43f9-8517-738eccdea871') and page_id='FinBondTargetAsset002' and status='D';
update chb_datacheck_rule_inv set remark='基金投资资产不能超过400字符'  where checkruleid='443E4A49-6810-5A9E-F150-007F01006A26' and page_id='FinFundTarget002' and status='D';
update chb_datacheck_rule_inv set remark='基金公司名称不能超过200字符'  where checkruleid='223E4A49-6810-5A9E-F150-007F01006A93' and page_id='FinFundTarget002' and status='D';
update chb_datacheck_rule_inv set remark='基金托管机构名称不可为空且不能超过200字符'  where checkruleid='443E4A49-6810-5A9E-F150-007F01006A25' and page_id='FinFundTarget002' and status='D';
update chb_datacheck_rule_inv set remark='名称不可为空且不能超过200字符'  where checkruleid='5abd7c53-e927-56b8-80b3-1fdd9d37d009' and page_id='FinFundTarget002' and status='D';

