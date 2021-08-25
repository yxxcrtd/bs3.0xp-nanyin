--特殊资产评级配置
delete from sys_page_element where PAGE_ID='AssetRatingAllocation001';
--资产大类评级配置
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetRatingAllocation001', 'ValAssetCategories001_btn_add', '01', '01', null, '新建(资产大类评级配置)', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetRatingAllocation001', 'ValAssetCategories001_btn_delete', '01', '01', null, '删除(资产大类评级配置)', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetRatingAllocation001', 'ValAssetCategories001_btn_export', '01', '01', null, '导出(资产大类评级配置)', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetRatingAllocation001', 'AssetCategories001_btn_search', '01', '01', null, '查询(资产大类评级配置)', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetRatingAllocation001', 'ValAssetCategories002_btn_submit', '01', '01', null, '提交(资产大类评级配置)', 'system', null, sysdate, 'system', sysdate, null);

--特殊资产评级配置
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetRatingAllocation001', 'Specialassets_btn_add', '01', '01', null, '新建(特殊资产评级配置)', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetRatingAllocation001', 'Specialassets_btn_del', '01', '01', null, '删除(特殊资产评级配置)', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetRatingAllocation001', 'Specialassets_btn_chbexport', '01', '01', null, '导出(特殊资产评级配置)', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetRatingAllocation001', 'Specialassets001_btn_search', '01', '01', null, '查询(特殊资产评级配置)', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetRatingAllocation001', 'Specialassets002_btn_submit', '01', '01', null, '提交(特殊资产评级配置)', 'system', null, sysdate, 'system', sysdate, null);


--PD违约概率管理
delete from sys_page_element where PAGE_ID='PdDefaultManager001';
--前瞻因子维护
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'PdFactorConfig001_btn_add', '01', '01', null, '新建(前瞻因子维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'PdFactorConfig001_btn_clear', '01', '01', null, '重置(前瞻因子维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'PdFactorConfig001_btn_delete', '01', '01', null, '删除(前瞻因子维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'PdFactorConfig001_btn_export', '01', '01', null, '导出(前瞻因子维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'PdFactorConfig001_btn_search', '01', '01', null, '查询(前瞻因子维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'PdFactorConfig002_btn_submit', '01', '01', null, '提交(前瞻因子维护)', 'system', null, sysdate, 'system', sysdate, null);

--债券、其他债券评级PD维护
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModGradePdConfig001_btn_add', '01', '01', null, '新建(债券、其他债券评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModGradePdConfig001_btn_import', '01', '01', null, '导入(债券、其他债券评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModGradePdConfig001_btn_del', '01', '01', null, '删除(债券、其他债券评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModGradePdConfig001_btn_export', '01', '01', null, '导出(债券、其他债券评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModGradePdConfig001_btn_search', '01', '01', null, '查询(债券、其他债券评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModGradePdConfig002_btn_submit', '01', '01', null, '提交(债券、其他债券评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);

--回购评级PD维护
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModHgGradePdConfig001_btn_add', '01', '01', null, '新建(回购评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModHgGradePdConfig001_btn_import', '01', '01', null, '导入(回购评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModHgGradePdConfig001_btn_del', '01', '01', null, '删除(回购评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModHgGradePdConfig001_btn_export', '01', '01', null, '导出(回购评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModHgGradePdConfig001_btn_search', '01', '01', null, '查询(回购评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModHgGradePdConfig002_btn_submit', '01', '01', null, '提交(回购评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);

--存款评级PD维护
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModCkGradePdConfig001_btn_add', '01', '01', null, '新建(存款评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModCkGradePdConfig001_btn_import', '01', '01', null, '导入(存款评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModCkGradePdConfig001_btn_del', '01', '01', null, '删除(存款评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModCkGradePdConfig001_btn_export', '01', '01', null, '导出(存款评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModCkGradePdConfig001_btn_search', '01', '01', null, '查询(存款评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PdDefaultManager001', 'ModCkGradePdConfig002_btn_submit', '01', '01', null, '提交(存款评级PD维护)', 'system', null, sysdate, 'system', sysdate, null);

-- LGD违约损失率管理
delete from sys_page_element where PAGE_ID='LossGivenDefaultManage001';
--LGD违约损失率维护
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('LossGivenDefaultManage001', 'LossGivenDefaultMaintain_btn_chbexport', '01', '01', null, '导出(LGD违约损失率维护)', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('LossGivenDefaultManage001', 'LossGivenDefaultMaintain001_btn_search', '01', '01', null, '查询(LGD违约损失率维护)', 'system', null, sysdate, 'system', sysdate, null);

--特殊资产LGD维护
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('LossGivenDefaultManage001', 'LgdSpecialConfig001_btn_add', '01', '01', null, '查询(特殊资产LGD维护)', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('LossGivenDefaultManage001', 'LgdSpecialConfig001_btn_del', '01', '01', null, '删除(特殊资产LGD维护)', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('LossGivenDefaultManage001', 'LgdSpecialConfig001_btn_export', '01', '01', null, '导出(特殊资产LGD维护)', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('LossGivenDefaultManage001', 'LgdSpecialConfig002_btn_submit', '01', '01', null, '提交', 'system', null, sysdate, 'system', sysdate, null);


--减值阶段参数管理
delete from sys_page_element where PAGE_ID='ModStagePara001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ModStagePara001', 'ModStagePara001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ModStagePara001', 'ModStagePara001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);

--减值情况计提表
delete from sys_page_element where PAGE_ID='ImpairmentProvision001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ImpairmentProvision001', 'ImpairmentProvision001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ImpairmentProvision001', 'ImpairmentProvision001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);

--减值批处理任务
delete from sys_page_element where PAGE_ID='ModImpaireBatchTask001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ModImpaireBatchTask001', 'ModImpaireBatchTask001_btn_doExecBtn', '01', '01', null, '执行', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ModImpaireBatchTask001', 'ModImpaireBatchTask001_btn_searchBtn', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);

--减值结果管理—普华
delete from sys_page_element where PAGE_ID='AssetImpairmentManagement001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentManagement001', 'AssetImpairmentManagement001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentManagement001', 'AssetImpairmentManagement001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentManagement001', 'AssetImpairmentManagement001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentManagement001', 'AssetImpairmentManagement001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentManagement001', 'AssetImpairmentManagement001_btn_reset', '01', '01', null, '重置', 'system', null, sysdate, 'system', sysdate, null);

delete from sys_page_element where PAGE_ID='AssetImpairmentManagement002';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentManagement002', 'AssetImpairmentManagement002_btn_save', '01', '01', null, '保存', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentKpmgManagement002', 'AssetImpairmentManagement002_btn_submit', '01', '01', null, '提交', 'system', null, sysdate, 'system', sysdate, null);


--减值结果管理—毕马威
delete from sys_page_element where PAGE_ID='AssetImpairmentKpmgManagement001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentManagement001', 'AssetImpairmentKpmgManagement001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentManagement001', 'AssetImpairmentKpmgManagement001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentManagement001', 'AssetImpairmentKpmgManagement001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentManagement001', 'AssetImpairmentKpmgManagement001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentManagement001', 'AssetImpairmentKpmgManagement001_btn_reset', '01', '01', null, '重置', 'system', null, sysdate, 'system', sysdate, null);

delete from sys_page_element where PAGE_ID='AssetImpairmentKpmgManagement002';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentKpmgManagement002', 'AssetImpairmentKpmgManagement002_btn_save', '01', '01', null, '保存', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentKpmgManagement002', 'AssetImpairmentKpmgManagement002_btn_submit', '01', '01', null, '提交', 'system', null, sysdate, 'system', sysdate, null);

-- 贴现法曲线配置
delete from sys_page_element where PAGE_ID='CashFlowDiscount001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('CashFlowDiscount001', 'ValCashFlowDiscount001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('CashFlowDiscount001', 'ValCashFlowDiscount001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('CashFlowDiscount001', 'ValCashFlowDiscount001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('CashFlowDiscount001', 'ValCashFlowDiscount001_btn_reset', '01', '01', null, '重置', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('CashFlowDiscount001', 'CashFlowDiscount001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);

-- 风险溢价调整
delete from sys_page_element where PAGE_ID='RiskPremiumAdjConf001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('RiskPremiumAdjConf001', 'RiskPremiumAdjConf001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);


-- 二叉树模型配置
delete from sys_page_element where PAGE_ID='ModBintreeConfig001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ModBintreeConfig001', 'ModBintreeConfig001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ModBintreeConfig001', 'ModBintreeConfig001_btn_reset', '01', '01', null, '重置', 'system', null, sysdate, 'system', sysdate, null);

-- 股息增长率配置
delete from sys_page_element where PAGE_ID='ModStockDivitrateConfig001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ModStockDivitrateConfig001', 'ModStockDivitrateConfig001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ModStockDivitrateConfig001', 'ModStockDivitrateConfig001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);

delete from sys_page_element where PAGE_ID='ModStockDivitrateConfig002';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ModStockDivitrateConfig002', 'ModStockDivitrateConfig002_btn_submit', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);

--估值模型批处理
delete from sys_page_element where PAGE_ID='ModValBatchTask001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ModValBatchTask001', 'ModValBatchTask001_btn_doExecBtn', '01', '01', null, '执行', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ModValBatchTask001', 'ModValBatchTask001_btn_searchBtn', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);

--评级关系映射管理
delete from sys_page_element where PAGE_ID='InsideOutsideGrade001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('InsideOutsideGrade001', 'ValInsideOutsideGrade001_btn_add', '01', '01', null, '新增', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('InsideOutsideGrade001', 'ValInsideOutsideGrade001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('InsideOutsideGrade001', 'ValInsideOutsideGrade001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('InsideOutsideGrade001', 'InsideOutsideGrade001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);

delete from sys_page_element where PAGE_ID='InsideOutsideGrade002';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('InsideOutsideGrade002', 'ValInsideOutsideGrade002_btn_submit', '01', '01', null, '提交', 'system', null, sysdate, 'system', sysdate, null);


