--违约损失率管理菜单表REMARK错误，角色权限分配时，显示会错误
UPDATE sys_page t SET T.REMARK='违约损失率管理' WHERE t.page_id='LossGivenDefaultManage001';

--特殊资产违约损失率管理查询界面文件路径调整
UPDATE Sys_Page SET  PAGE_FILE='lossmodel/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig001' WHERE page_id ='LgdSpecialConfig001';


--特殊资产违约损失率管理新建界面文件路径调整
UPDATE Sys_Page SET  PAGE_FILE='lossmodel/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002' WHERE page_id ='LgdSpecialConfig002';

--特殊资产违约损失率管理新建流程调整为一级流程，直接生效
UPDATE SYS_WFL_BUSINESS_MODULE T SET T.MODULE_ID ='noneApproval' WHERE T.PAGE_ID='LgdSpecialConfig002';

