
--【评级信息】菜单名称改为【评级信息管理】
UPDATE SYS_PAGE T SET T.REMARK = '评级信息管理' WHERE T.PAGE_ID = 'ComMarketManagement';

--【减值结果管理-普华】菜单名称改为【交易减值结果管理】
UPDATE SYS_PAGE T SET T.REMARK = '交易减值结果管理' WHERE T.PAGE_ID = 'AssetImpairmentManagement001';

--【减值结果管理-毕马威】菜单名称改为【资产减值结果管理】
UPDATE SYS_PAGE T SET T.REMARK = '资产减值结果管理' WHERE T.PAGE_ID = 'AssetImpairmentKpmgManagement001';

--违约概率管理（PD维护）界面不走多级流程，直接提交生效
UPDATE SYS_WFL_BUSINESS_MODULE t SET T.MODULE_ID='noneApproval' WHERE t.page_id = 'ModGradePdConfig002';

--减值阶段参数管理 界面不走多级流程，直接提交生效
UPDATE SYS_WFL_BUSINESS_MODULE t SET T.MODULE_ID='noneApproval' WHERE t.page_id = 'ModStagePara002';
