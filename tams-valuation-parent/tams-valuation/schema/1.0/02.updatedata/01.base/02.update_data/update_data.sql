
/**ADD                                             2018-09-21                                             START**/

/**ADD BY WANGHB IN 2018-9-21 增加利润表菜单及画面 START**/
delete from SYS_MENU where MENU_ID='VALSTATEACCOUNT_001_002_002';
insert into SYS_MENU values ('VALSTATEACCOUNT_001_002_002','VAL','','VALSTATEACCOUNT_001_002','ValProfitQuery001','4','02','利润表','system','',to_date('2018/02/05', 'yyyy/mm/dd'), 'system',to_date('2018/02/05', 'yyyy/mm/dd'));

delete from SYS_PAGE where PAGE_ID='ValProfitQuery001';
insert into SYS_PAGE values ('ValProfitQuery001','VAL','/valuation/accounadjustmanage/valprofitquery/ValProfitQuery001','/valuation/accounadjustmanage/valprofitquery/ValProfitQuery001','VALSTATEACCOUNT_001_002_002','','','02','02','利润表','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
/**ADD BY WANGHB IN 2018-9-21 增加利润表菜单及画面 END**/

/**增加批[存款资产预交易账务处理]。  add by yangjian 2018-09-21  start**/
-- [存款资产预交易账务处理]插入批处理任务表
delete from SYS_BATCH_TASK where task_id = '132';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) 
values ('132','VAL','存款资产预交易账务处理','','depositAssetsReadyDealTask','35','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');

-- [存款资产预交易账务处理]插入批处理任务组任务关联
delete from SYS_BATCH_JOB where BATCH_SEQ_NO = '6f8803b3aa6fbaffe050007f01003cad' and TASK_ID = '132';
insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01003cad','132','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
/**增加批[存款资产预交易账务处理]。  add by yangjian 2018-09-21  end**/

/**ADD                                             2018-09-21                                              END**/
/**ADD                                             2018-09-22                                              begin**/
/** 资产负债表 页面和联动 zhangwei start **/
insert into SYS_MENU values ('VALSTATEACCOUNT_001_002','VAL','','VALSTATEACCOUNT_001','','2','02','财务报表','system','',to_date('2018/02/03', 'yyyy/mm/dd'), 'system',to_date('2018/02/03', 'yyyy/mm/dd'));
insert into SYS_MENU values ('VALSTATEACCOUNT_001_002_001','VAL','','VALSTATEACCOUNT_001_002','AssetLiabilityStatementQuery001','1','02','资产负债表','system','',to_date('2018/02/04', 'yyyy/mm/dd'), 'system',to_date('2018/02/04', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('AssetLiabilityStatementQuery001','VAL','/valuation/financialstatement/assetliailitystatement/AssetLiabilityStatementQuery001','valuation/financialstatement/assetliailitystatement/AssetLiabilityStatementQuery001','VALSTATEACCOUNT_001_002_001','','','02','02','资产负债表','system','',to_date('2018/09/12', 'yyyy/mm/dd'), 'system',to_date('2018/09/12', 'yyyy/mm/dd'));

insert into SYS_DATADICT_LIST_LINK values ('HALF_YEAR','01','REPORT_TERM_TYPE','04','system','',to_date('2018/09/12', 'yyyy/mm/dd'), 'system',to_date('2018/09/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('HALF_YEAR','02','REPORT_TERM_TYPE','04','system','',to_date('2018/09/13', 'yyyy/mm/dd'), 'system',to_date('2018/09/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('QUARTER','01','REPORT_TERM_TYPE','03','system','',to_date('2018/09/14', 'yyyy/mm/dd'), 'system',to_date('2018/09/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('QUARTER','02','REPORT_TERM_TYPE','03','system','',to_date('2018/09/15', 'yyyy/mm/dd'), 'system',to_date('2018/09/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('QUARTER','03','REPORT_TERM_TYPE','03','system','',to_date('2018/09/16', 'yyyy/mm/dd'), 'system',to_date('2018/09/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('QUARTER','04','REPORT_TERM_TYPE','03','system','',to_date('2018/09/17', 'yyyy/mm/dd'), 'system',to_date('2018/09/18', 'yyyy/mm/dd'));
/** 资产负债表 页面和联动 zhangwei start **/
/**ADD                                             2018-09-21                                              END**/

/**ADD                                             2018-09-27                                              START**/

/**增加接口用到的数据字典 add by wujie start**/
delete from SYS_DATADICT where dict_code in ('DEPOSIT_TYPE','ORG_VALCLASS','LENDING_TYPE',
'BONDREP_TYPE','BONDREP_ORG_VALCLASS','REGIST_ORG');
insert into SYS_DATADICT values ('DEPOSIT_TYPE','VAL','存款类资产类型','','02','system','',to_date('2018/9/26', 'yyyy/mm/dd'),'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('ORG_VALCLASS','VAL','机构核算分类','','02','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('LENDING_TYPE','VAL','拆借资产类型','','02','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('BONDREP_TYPE','VAL','回购标的类型','','02','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('BONDREP_ORG_VALCLASS','VAL','回购_机构核算分类','','02','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('REGIST_ORG','VAL','登记托管机构','','02','system','',to_date('2018/9/3', 'yyyy/mm/dd'), 'system',to_date('2018/9/3', 'yyyy/mm/dd'));


delete from SYS_DATADICT_LIST where dict_code in ('ORG_VALCLASS','BONDREP_ORG_VALCLASS','REGIST_ORG');
insert into SYS_DATADICT_LIST values ('ORG_VALCLASS','ORG001','境内银行业存款类金融机构','1','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORG_VALCLASS','ORG002','境内银行业非存款类金融机构','2','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORG_VALCLASS','ORG003','境内证券业金融机构','3','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORG_VALCLASS','ORG004','境内保险业金融机构','4','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORG_VALCLASS','ORG005','境内交易及结算类金融机构','5','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORG_VALCLASS','ORG006','境内金融控股公司','6','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORG_VALCLASS','ORG007','境内其他金融机构','7','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORG_VALCLASS','ORG008','境内特殊目的载体（SPV）','8','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORG_VALCLASS','ORG009','境外存款类金融机构','9','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORG_VALCLASS','ORG010','境外其他金融机构','10','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));


insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','ORG001','中央银行','1','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','ORG002','境内银行业存款类金融机构','2','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','ORG003','境内银行业非存款类金融机构','3','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','ORG004','境内证券业金融机构','4','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','ORG005','境内保险业金融机构','5','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','ORG006','境内交易及结算类金融机构','6','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','ORG007','境内金融控股公司','7','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','ORG008','境内其他金融机构','8','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','ORG009','境内特殊目的载体（SPV）','9','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','ORG010','境内非金融性公司','10','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));


insert into SYS_DATADICT_LIST values ('REGIST_ORG','01','中央国债登记结算有限责任公司','1','0','system','',to_date('2018/9/3', 'yyyy/mm/dd'), 'system',to_date('2018/9/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('REGIST_ORG','02','上海清算所','2','0','system','',to_date('2018/9/3', 'yyyy/mm/dd'), 'system',to_date('2018/9/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('REGIST_ORG','03','中国证券登记结算有限责任公司','3','0','system','',to_date('2018/9/3', 'yyyy/mm/dd'), 'system',to_date('2018/9/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('REGIST_ORG','04','理财直融工具','4','0','system','',to_date('2018/9/3', 'yyyy/mm/dd'), 'system',to_date('2018/9/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('REGIST_ORG','05','私募机构','5','0','system','',to_date('2018/9/3', 'yyyy/mm/dd'), 'system',to_date('2018/9/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('REGIST_ORG','99','其他','6','0','system','',to_date('2018/9/3', 'yyyy/mm/dd'), 'system',to_date('2018/9/3', 'yyyy/mm/dd'));
/**增加接口用到的数据字典 add by wujie end**/

/**ADD                                             2018-09-27                                              END**/


/**ADD                                             2018-09-28                                              START**/

/**===========净值下发确认页面，确认和下发增加流程。    add by :yangjian 2018/09/28  begin===============**/
--增加流程业务类型
delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'V02';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'V02', '净值下发确认', 1, '0', 'system', null, to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));

--净值下发页面支持流程
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValNetValueConfirm001';
--净值下发 - 确认按钮
delete from sys_wfl_business_module where PAGE_ID = 'ValNetValueConfirm001' and ELEMENT_ID = 'ValNetValueConfirm001_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValNetValueConfirm001', 'ValNetValueConfirm001_btn_submit', 'oneApproval', 'system', '0001', to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));
--净值下发 - 撤销确认按钮
delete from sys_wfl_business_module where PAGE_ID = 'ValNetValueConfirm001' and ELEMENT_ID = 'ValNetValueConfirm001_btn_delete';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValNetValueConfirm001', 'ValNetValueConfirm001_btn_delete', 'oneApproval', 'system', '0001', to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));

--净值下发 - 下发按钮
delete from sys_wfl_business_module where PAGE_ID = 'ValNetValueConfirm001' and ELEMENT_ID = 'ValNetValueConfirm001_btn_unfrozen';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValNetValueConfirm001', 'ValNetValueConfirm001_btn_unfrozen', 'oneApproval', 'system', '0001', to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));
--净值下发 - 撤销下发按钮
delete from sys_wfl_business_module where PAGE_ID = 'ValNetValueConfirm001' and ELEMENT_ID = 'ValNetValueConfirm001_btn_deleteunfrozen';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValNetValueConfirm001', 'ValNetValueConfirm001_btn_deleteunfrozen', 'oneApproval', 'system', '0001', to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));
/**===========净值下发确认页面，确认和下发增加流程。    add by :yangjian 2018/09/28  end===============**/

/**ADD                                             2018-09-28                                              END**/


/**ADD                                             2018-09-29                                              START**/

/**指令类型增加预期收益产品的 add by wujie start**/
delete from SYS_DATADICT_LIST where dict_code ='ORDER_TYPE' and ITEM_CODE in ('N8001','N8002','N8005');

insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N8001','预期收益型产品-认购申购','103','0','system','',to_date('2018/9/29', 'yyyy/mm/dd'), 'system',to_date('2018/9/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N8002','预期收益型产品-赎回到期','104','0','system','',to_date('2018/9/29', 'yyyy/mm/dd'), 'system',to_date('2018/9/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N8005','预期收益型产品-兑付客户收益','105','0','system','',to_date('2018/9/29', 'yyyy/mm/dd'), 'system',to_date('2018/9/29', 'yyyy/mm/dd'));

/**指令类型增加预期收益产品的 add by wujie end**/

/**add by chengzhong for 交易所债券任务去除    2018-10-09 start**/
delete from sys_batch_job where BATCH_SEQ_NO = '6f8803b3aa6fbaffe050007f01003cad' and task_id in ('10', '113');
delete from SYS_DATADICT_LIST where DICT_CODE = 'BUSI_TYPE' and ITEM_CODE = '20';
delete from sys_batch_task where task_id in ('10', '113');
/**add by chengzhong for 交易所债券任务去除    2018-10-08 start**/

/**add by zhangzhao for 预期收益型TA交易入账批处理    2018-10-09 start**/
--批处理任务
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('303','VAL','预期收益型TA交易-账务明细处理','','expReturnTaTradeBookTask','40','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');
--批处理分组明细
insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01003cad','303','system','',to_date('2018/10/08', 'yyyy/mm/dd'), 'system',to_date('2018/10/08', 'yyyy/mm/dd'));
/**add by zhangzhao for 预期收益型TA交易入账批处理    2018-10-09 end**/


/**增加预期收益产品用到的数据字典 add by wujie on 20181009 start**/
delete from SYS_DATADICT where DICT_CODE IN ('TERM_TYPE_PRODUCT','CLASSIFICATION_TYPE','PERIOD_UNIT','INTEREST_ROLE');

insert into SYS_DATADICT values ('TERM_TYPE_PRODUCT','VAL','期限品种(产品端)','','02','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('CLASSIFICATION_TYPE','VAL','分层类型','','02','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('PERIOD_UNIT','VAL','周期单位(持有上下限)','','02','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('INTEREST_ROLE','VAL','付息分红规则','','02','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));


delete from SYS_DATADICT_LIST where DICT_CODE IN ('TERM_TYPE_PRODUCT','CLASSIFICATION_TYPE','PERIOD_UNIT','INTEREST_ROLE');
insert into SYS_DATADICT_LIST values ('TERM_TYPE_PRODUCT','01','T+0','1','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TERM_TYPE_PRODUCT','02','7 天(含)以内','2','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TERM_TYPE_PRODUCT','03','7 天-1 个月(含)','3','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TERM_TYPE_PRODUCT','04','1-3 个月(含)','4','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TERM_TYPE_PRODUCT','05','3-6个月(含)','5','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TERM_TYPE_PRODUCT','06','6-12 个月(含)','6','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TERM_TYPE_PRODUCT','07','1 年以上','7','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('CLASSIFICATION_TYPE','01','金额分层','1','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('CLASSIFICATION_TYPE','02','期限分层','2','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('CLASSIFICATION_TYPE','03','续投周期分层','3','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PERIOD_UNIT','01','日','1','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PERIOD_UNIT','02','月','2','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PERIOD_UNIT','03','年','3','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('INTEREST_ROLE','01','到期','1','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('INTEREST_ROLE','02','申购计划同频','2','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('INTEREST_ROLE','03','周期','3','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('INTEREST_ROLE','04','不规则','4','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where DICT_CODE='BUSI_TYPE' and ITEM_CODE='27';
insert into SYS_DATADICT_LIST values ('BUSI_TYPE','27','预期收益型TA交易','27','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));

/**增加预期收益产品用到的数据字典 add by wujie on 20181009 end**/


/**add by ruanhongming 2018-10-09 start 纳税申报表数据字典*/
delete from sys_datadict where dict_code in( 'YEAR_DATE','DECLAR_DATE','SUM_DIMEN');
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DECLAR_DATE', 'SYS', '申报日期', 'DECLAR_DATE', '02', 'system', null, to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SUM_DIMEN', 'SYS', '汇总维度', 'SUM_DIMEN', '02', 'system', null, to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));

/**add by ruanhongming 2018-10-09 end 纳税申报表数据字典*/

/**add by ruanhongming 2018-10-09 start 纳税申报表数据字典明细*/
delete from sys_datadict_list where dict_code in( 'YEAR_DATE','DECLAR_DATE','SUM_DIMEN');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DECLAR_DATE', '01', '第一征期', 1, '0', 'system', null, to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DECLAR_DATE', '02', '第二征期', 2, '0', 'system', null, to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DECLAR_DATE', '03', '第三征期', 3, '0', 'system', null, to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DECLAR_DATE', '04', '第四征期', 4, '0', 'system', null, to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SUM_DIMEN', '01', '全产品', 1, '0', 'system', null, to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SUM_DIMEN', '02', '保本', 2, '0', 'system', null, to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SUM_DIMEN', '03', '非保本', 3, '0', 'system', null, to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SUM_DIMEN', '04', '单产品', 4, '0', 'system', null, to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));
/**add by ruanhongming 2018-10-09 end 纳税申报表数据字典明细*/

/**add by ruanhongming 2018-10-09 start 纳税申报表界面*/
delete from sys_menu where page_id = 'ValueAddedTaxQuery001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALSTATEACCOUNT_001_001_005', 'VAL', null, 'VALSTATEACCOUNT_001_001', 'ValueAddedTaxQuery001', 4, '02', '增值税台账查询', 'system', null, to_date('2018/10/09', 'yyyy/mm/dd'), 'system', to_date('2018/10/09', 'yyyy/mm/dd'));

delete from sys_page where page_id = 'ValueAddedTaxQuery001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValueAddedTaxQuery001', 'VAL', '/valuation/accounadjustmanage/valueaddedtax/ValueAddedTax001', '/valuation/accounadjustmanage/valueaddedtax/ValueAddedTax001', 'VALSTATEACCOUNT_001_001_005', null, null, '02', '02', '增值税台账查询', 'system', null, to_date('2018-01-01','yyyy-MM-dd'), 'system', to_date('2018-01-01','yyyy-MM-dd'));
/**add by ruanhongming 2018-10-09 end 纳税申报表界面*/


/**add by daiyue 2018-10-09 start [数据字典表]sheet 添加 SEC_DEAL_FEE_SUBJECT 1条 */
insert into SYS_DATADICT values ('SEC_DEAL_FEE_SUBJECT','VAL','债券交易费用损益计入方式','','02','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));

/**add by daiyue 2018-10-09 end [数据字典表]sheet 添加 SEC_DEAL_FEE_SUBJECT 1条 */

/**add by daiyue 2018-10-09 start  [数据字典明细表]添加 SEC_DEAL_FEE_SUBJECT 2条 */
insert into SYS_DATADICT_LIST values ('SEC_DEAL_FEE_SUBJECT','01','计入投资收益','1','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SEC_DEAL_FEE_SUBJECT','02','计入费用支出','2','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));

/**add by daiyue 2018-10-09 end  [数据字典明细表]添加SEC_DEAL_FEE_SUBJECT 2条 */

/**add by leipeng 2018-10-09 start  简易计税余额查询菜单 */
DELETE FROM SYS_MENU WHERE PAGE_ID = 'TaxBalanceQuery001';
insert into SYS_MENU values ('VALINFOSERVICE_001_007','VAL','','VALINFOSERVICE_001','TaxBalanceQuery001','5','02','简易计税余额查询','system','',to_date('2018/02/02', 'yyyy/mm/dd'), 'system',to_date('2018/02/02', 'yyyy/mm/dd'));
DELETE FROM SYS_PAGE WHERE PAGE_ID = 'TaxBalanceQuery001';
insert into SYS_PAGE values ('TaxBalanceQuery001','VAL','/valuation/trademanage/taxbalancequery/TaxBalanceQuery001','/valuation/trademanage/taxbalancequery/TaxBalanceQuery001','VALINFOSERVICE_001_007','','','02','02','简易计税余额查询','system','',to_date('2018/09/12', 'yyyy/mm/dd'), 'system',to_date('2018/09/12', 'yyyy/mm/dd'));

/**add by leipeng 2018-10-09 end  简易计税余额查询菜单 */

/**ADD                                             2018-09-29                                              END**/


/**ADD                                            2018-10-10                                                 start**/

/**add by caoguo 2018-10-10 start  数据字典：增加特殊核算模型 */
insert into SYS_DATADICT values ('BOK_SPEC_MODE','VAL','特殊核算模型','','02','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));

insert into SYS_DATADICT_LIST values ('BOK_SPEC_MODE','01','普通分层核算','1','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BOK_SPEC_MODE','02','收益分层核算','2','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));

/**add by caoguo 2018-10-10 end  数据字典：增加特殊核算模型 */

/**add by zhangwei for 预期收益型子产品 TA 固定费用计提入账    2018-10-10 start**/
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('300','VAL','预期收益型子产品TA固定费用计提-账务明细处理','','expReturnSubProductFixedFeeCalBookTask','80','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');
insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01003cad','300','system','',to_date('2018/10/08', 'yyyy/mm/dd'), 'system',to_date('2018/10/08', 'yyyy/mm/dd'));
/**add by zhangwei for 预期收益型子产品 TA 固定费用计提入账    2018-10-10 end**/

/**add by niuqianyu for 预期收益型产品估值计算_费前批处理    2018-10-10 start**/
insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01003cad','301','system','',to_date('2018/10/08', 'yyyy/mm/dd'), 'system',to_date('2018/10/08', 'yyyy/mm/dd'));
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('301','VAL','预期收益型产品估值计算_费前','','expReturnPrdValuationFeeBeforeTask','85','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');

/**add by niuqianyu for 预期收益型产品估值计算_费前批处理     2018-10-10 end**/

/**add by huangjing for 数据字典明细表：会计场景代码增加3条数据    2018-10-10 start**/
insert into SYS_DATADICT_LIST values ('TABLE_ID','2731','资金划款-存放同业起息','286','0','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TABLE_ID','2732','资金收款-存放同业到期/卖出','287','0','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TABLE_ID','2733','资金收款-存放同业收息','288','0','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));
/**add by huangjing for 数据字典明细表：会计场景代码增加3条数据    2018-10-10 end**/


/**add by yangjian for [买断式回购预交易账务处理]批执行相关数据脚本    2018-10-10 begin**/
--增加[买断式回购预交易账务处理]批处理
delete from SYS_BATCH_TASK where task_id = '161';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) 
values ('161','VAL','买断式回购预交易账务处理','','outrightProRepoDealAccTreatTask','35','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');
--[买断式回购预交易账务处理]添加到对应任务组中
delete from SYS_BATCH_JOB where batch_seq_no = '6f8803b3aa6fbaffe050007f01003cad' and task_id = '161';
insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01003cad','161','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
/**add by yangjian for [买断式回购预交易账务处理]批执行相关数据脚本    2018-10-10 end**/

/**add                                         2018-10-10                            end**/

/**                                 2018-10-11                                   start**/

/**add by zhangzhao for 所有者权益变动表    2018-10-11 begin**/
insert into SYS_MENU values ('VALSTATEACCOUNT_001_002_003','VAL','','VALSTATEACCOUNT_001_002','OwnerEquityChange001','2','03','所有者权益变动表','system','',to_date('2018/02/06', 'yyyy/mm/dd'), 'system',to_date('2018/02/06', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('OwnerEquityChange001','VAL','/valuation/accounadjustmanage/ownerequitychange/OwnerEquityChange001','/valuation/accounadjustmanage/ownerequitychange/OwnerEquityChange001','VALSTATEACCOUNT_001_002_003','','','02','02','所有者权益变动表','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
/**add by zhangzhao for 所有者权益变动表   2018-10-11 end**/


--数据字典：增加录入方式 add by wujie
delete from SYS_DATADICT where dict_code='INPUT_TYPE';
insert into SYS_DATADICT values ('INPUT_TYPE','VAL','录入方式','','02','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));


delete from SYS_DATADICT_LIST where dict_code='INPUT_TYPE';
insert into SYS_DATADICT_LIST values ('INPUT_TYPE','01','手工录入','1','0','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('INPUT_TYPE','02','系统导入','2','0','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('INPUT_TYPE','03','自动生成','3','0','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('INPUT_TYPE','04','批处理生成','4','0','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('INPUT_TYPE','05','接口导入','5','0','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('INPUT_TYPE','06','销售平台','6','0','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));

/**                                       2018-10-11                                   End**/


/**add                                         2018-10-12                            start**/

/**===========手工凭证，新建和删除增加流程。    add by :yangjian 2018/10/12  begin===============**/
--增加流程业务类型
delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'V03';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'V03', '手工凭证', 1, '0', 'system', null, '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');

--手工凭证新建页面支持流程
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValManualVoucher003';
--手工凭证 - 新建 - 提交
delete from sys_wfl_business_module where PAGE_ID = 'ValManualVoucher003' and ELEMENT_ID = 'ValManualVoucher003_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValManualVoucher003', 'ValManualVoucher003_btn_submit', 'oneApproval', 'system', '0001', to_date('2018/10/09', 'yyyy/mm/dd'), 'system', to_date('2018/10/09', 'yyyy/mm/dd'));

--手工凭证删除页面支持流程
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValManualVoucher002';
--手工凭证 - 删除 - 提交
delete from sys_wfl_business_module where PAGE_ID = 'ValManualVoucher002' and ELEMENT_ID = 'ValManualVoucher001_btn_delete';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValManualVoucher002', 'ValManualVoucher001_btn_delete', 'oneApproval', 'system', '0001', to_date('2018/10/09', 'yyyy/mm/dd'), 'system', to_date('2018/10/09', 'yyyy/mm/dd'));
/**===========手工凭证，新建和删除增加流程。    add by :yangjian 2018/10/12  end===============**/
--新增批处理-预期收益型TA中收计提-账务明细处理 王耕耘
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('302','VAL','预期收益型TA中收计提-账务明细处理','','expReturnFeeAccountsTask','90','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');
--新增批处理分组明细-预期收益型TA中收计提-账务明细处理 王耕耘
insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01003cad','302','system','',to_date('2018/10/08', 'yyyy/mm/dd'), 'system',to_date('2018/10/08', 'yyyy/mm/dd'));


/**add                                         2018-10-12                            end**/

/**add                                         2018-10-15                            start**/
/**add by huangjian  投资端费用支付-账务明细处理*/
delete from SYS_BATCH_TASK where task_id='9';

insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('9','VAL','投资端费用支付-账务明细处理','','investFeePayTask','40','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');

/**add by huangjian  投资端费用支付-账务明细处理*/

/**add by niuqianyu  资金账户查询*/
insert into SYS_MENU values ('VALINFOSERVICE_001_009','VAL','','VALINFOSERVICE_001','FundAccountQuery001','2','02','资金账户查询','system','',to_date('2018/02/02', 'yyyy/mm/dd'), 'system',to_date('2018/02/02', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('FundAccountQuery001','VAL','/valuation/trademanage/fundaccountquery/FundAccountQuery001','/valuation/trademanage/fundaccountquery/FundAccountQuery001','VALINFOSERVICE_001_009','','','02','02','资金账户查询','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('FundAccountQuery002','VAL','/valuation/trademanage/fundaccountquery/FundAccountQuery002','/valuation/trademanage/fundaccountquery/FundAccountQuery002','VALINFOSERVICE_001_009','','','02','02','查看资金账户信息','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

/**add by niuqianyu  资金账户查询*/
/**add                                         2018-10-15                            end**/

/**add                                         2018-10-16                            begin**/

/**add by huwei  账户活期利率*/
insert into SYS_MENU values ('VALINFOSERVICE_001_008','VAL','','VALINFOSERVICE_001','AccountCurrentRate001','4','02','账户活期利率','system','',to_date('2018/02/02', 'yyyy/mm/dd'), 'system',to_date('2018/02/02', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('AccountCurrentRate001','VAL','/valuation/trademanage/accountcurrentrate/AccountCurrentRate001','/valuation/trademanage/accountcurrentrate/AccountCurrentRate001','VALINFOSERVICE_001_008','','','02','02','账户活期利率','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
/**add by huwei  账户活期利率*/

/**一次性费用类型  add by wujie on 20181016 start**/
delete from SYS_DATADICT_LIST where dict_code='FEE_TYPE' and item_code in ('B13','B14');

insert into SYS_DATADICT_LIST values ('FEE_TYPE','B13','账户维护费','13','0','system','',to_date('2018/10/16', 'yyyy/mm/dd'), 'system',to_date('2018/10/16', 'yyyy/mm/dd'));

/**一次性费用类型  add by wujie on 20181016 end**/

/**台账报表 数据字典增加 上期维度  add by zhangzhao 20181016 start**/
delete from sys_datadict where dict_code = 'LAST_DIMEN';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LAST_DIMEN', 'SYS', '上期维度', 'LAST_DIMEN', '02', 'system', null, to_date('2018/2/3', 'yyyy/mm/dd'), 'system', to_date('2018/2/3', 'yyyy/mm/dd'));

delete from sys_datadict_list where dict_code = 'LAST_DIMEN';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LAST_DIMEN', '01', '环比', 1, '0', 'system', null, to_date('2018/2/3', 'yyyy/mm/dd'), 'system', to_date('2018/2/3', 'yyyy/mm/dd'));
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LAST_DIMEN', '02', '同比', 2, '0', 'system', null, to_date('2018/2/3', 'yyyy/mm/dd'), 'system', to_date('2018/2/3', 'yyyy/mm/dd'));
/**台账报表 数据字典增加 上期维度  add by zhangzhao 20181016 end**/

/**新增预期收益型母产品TA客户收益计提入账批处理 add by lihuadong start**/
delete from SYS_BATCH_TASK where task_id = '306';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('306','VAL','预期收益型母产品TA客户收益计提-账务明细处理','','expParPrdTaCusIncDrawBookTask','80','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');
delete from SYS_BATCH_JOB where BATCH_SEQ_NO = '6f8803b3aa6fbaffe050007f01003cad' and task_id = '306';
insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01003cad','306','system','',to_date('2018/10/08', 'yyyy/mm/dd'), 'system',to_date('2018/10/08', 'yyyy/mm/dd'));
/**新增预期收益型母产品TA客户收益计提入账批处理  add by lihuadong end**/

/**add                                         2018-10-17                            start**/
/**核算主体类别 add by wujie start**/
delete from SYS_DATADICT_LIST where dict_code='ACCT_SUBJECT_TYPE';
insert into SYS_DATADICT_LIST values ('ACCT_SUBJECT_TYPE','F16','理财产品','1','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ACCT_SUBJECT_TYPE','F18','投资组合','2','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ACCT_SUBJECT_TYPE','03','部门','3','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
/**核算主体类别 add by wujie end**/

/**add by chengzhong for 2.0.7.6.8版本框架升级 start **/
insert into SYS_PARA values ('SYS','ignore_username_case','01','01','数据字典（是否）','是否忽略用户名大小写','system','',to_date('2018/2/5', 'yyyy/mm/dd'), 'system',to_date('2018/2/5', 'yyyy/mm/dd'));
/**add by chengzhong for 2.0.7.6.8版本框架升级end **/
--added by wangzhiang 增加匹配类型 start
insert into SYS_DATADICT_LIST values ('MATCH_TYPE','08','任务代码_计提现金流类型','8','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
--added by wangzhiang 增加匹配类型 end

/**add                                         2018-10-17                            end**/
/**add                                         2018-10-18                            begin**/
/** 税务方案 add by zhangwei start 2018-10-18 **/
-- 数据字典
insert into SYS_DATADICT values ('COPY_RANGE','VAL','复制范围','','02','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COPY_RANGE','01','增值税则表','1','0','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COPY_RANGE','02','附加税则表','2','0','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COPY_RANGE','03','资产应税信息','3','0','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COPY_RANGE','04','参数设置','4','0','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));

-- 菜单和画面
insert into SYS_MENU values ('VALPARMANAGE_001_003','VAL','','VALPARMANAGE_001','','3','','税务参数','system','',to_date('2018/10/10', 'yyyy/mm/dd'), 'system',to_date('2018/10/10', 'yyyy/mm/dd'));
insert into SYS_MENU values ('VALPARMANAGE_001_003_001','VAL','','VALPARMANAGE_001_003','TaxScheme001','1','02','税务方案','system','',to_date('2018/10/10', 'yyyy/mm/dd'), 'system',to_date('2018/10/10', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('TaxScheme001','VAL','/valuation/taxparametermanage/taxscheme/TaxScheme001','valuation/taxparametermanage/taxscheme/TaxScheme001','VALPARMANAGE_001_003_001','','','02','02','税务方案','system','',to_date('2018/10/10', 'yyyy/mm/dd'), 'system',to_date('2018/10/10', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('TaxScheme002','VAL','/valuation/taxparametermanage/taxscheme/TaxScheme002','valuation/taxparametermanage/taxscheme/TaxScheme002','VALPARMANAGE_001_003_001','','','02','02','税务方案配置','system','',to_date('2018/10/10', 'yyyy/mm/dd'), 'system',to_date('2018/10/10', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('TaxScheme003','VAL','/valuation/taxparametermanage/taxscheme/TaxScheme003','valuation/taxparametermanage/taxscheme/TaxScheme003','VALPARMANAGE_001_003_001','','','02','02','增值税税则配置','system','',to_date('2018/10/10', 'yyyy/mm/dd'), 'system',to_date('2018/10/10', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('TaxScheme004','VAL','/valuation/taxparametermanage/taxscheme/TaxScheme004','valuation/taxparametermanage/taxscheme/TaxScheme004','VALPARMANAGE_001_003_001','','','02','02','附加税税则配置','system','',to_date('2018/10/10', 'yyyy/mm/dd'), 'system',to_date('2018/10/10', 'yyyy/mm/dd'));
/** 税务方案 add by zhangwei end 2018-10-18 **/

/** 计划类资产净值信息 菜单和画面  add by lishicheng  start 2018-10-18 **/
insert into SYS_PAGE values('ValNetPlanQuotation001','VAL','/valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation001','/valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation001','VALINFOSERVICE_001_004_008','','','02','02','计划类资产净值信息','system','',sysdate,'system',sysdate);
insert into SYS_MENU values('VALINFOSERVICE_001_004_008','VAL','','VALINFOSERVICE_001_004','ValNetPlanQuotation001','8','02','计划类资产净值信息','system','',sysdate,'system',sysdate);
insert into SYS_PAGE values ('ValNetPlanQuotation002','VAL','/valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation002','valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation002','VALINFOSERVICE_001_004_008',null,null,'02','02','查看计划类资产净值信息','system',null,sysdate,'system',sysdate);
insert into SYS_PAGE values ('ValNetPlanQuotation003','VAL','/valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation003','valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation003','VALINFOSERVICE_001_004_008',null,null,'02','02','新建计划类资产净值信息','system',null,sysdate,'system',sysdate);
/** 计划类资产净值信息 add by lishicheng start 2018-10-18 **/

/**add                                         2018-10-19                            start**/
/** 数据字典明细表添加数据 add by wangzhiang start**/
insert into SYS_DATADICT_LIST values ('BUSI_TYPE','30','资产重分类','30','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PS','T35','资产重分类','49','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));

/** 数据字典明细表添加数据 add by wangzhiang end**/

/** 数据字典明细表添加数据 add by wujie start**/
delete from SYS_DATADICT_LIST where dict_code='ORDER_TYPE' and item_code='N3001';
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N3001','投资类费用-交易费用-支付','106','0','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));
/** 数据字典明细表添加数据 add by wujie end**/



/**add                                         2018-10-19                            end**/

/**ADD                                             2018-10-22                                              start**/
/**主版本含权债摊销价格目标和摊销日期设置，杭州需要安个性化设置 add by chenggaoling start**/
UPDATE SYS_PARA SET PARAM_VALUE = 'B' WHERE SYSTEM_CODE = 'VAL' AND PARA_CODE = 'DSC_TRAGEPRICE_MODE';
UPDATE SYS_PARA SET PARAM_VALUE = '04',REMARK = '含权债摊销摊销日：数据字典含权债摊销日' WHERE SYSTEM_CODE = 'VAL' AND PARA_CODE = 'ISSECTOEXEDATE';
/**主版本含权债摊销价格目标和摊销日期设置，杭州需要安个性化设置 add by chenggaoling end**/

/**add by huwei  拆借类资产*/
insert into SYS_MENU values ('VALINFOSERVICE_001_003_006','VAL','','VALINFOSERVICE_001_003','ValLoanMarkquery001','5','02','拆借类资产','system','',to_date('2018/02/06', 'yyyy/mm/dd'), 'system',to_date('2018/02/06', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValLoanMarkquery001','VAL','/valuation/assets/loanmark/ValLoanMarkquery001','/valuation/assets/loanmark/ValLoanMarkquery001','VALINFOSERVICE_001_003_006','','','02','02','拆借类资产','system','',to_date('2018/10/10', 'yyyy/mm/dd'), 'system',to_date('2018/10/10', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValLoanMarkquery001','FINPROD_TYPE','F08','huwei','system',to_date('2018/10/19', 'yyyy/mm/dd'),'system',to_date('2018/10/19', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValLoanMarkquery001','FINPROD_TYPE','F09','huwei','system',to_date('2018/10/19', 'yyyy/mm/dd'),'system',to_date('2018/10/19', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValLoanMarkquery001','FINPROD_TYPE','F21','huwei','system',to_date('2018/10/19', 'yyyy/mm/dd'),'system',to_date('2018/10/19', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValLoanMarkquery002','VAL','/valuation/assets/loanmark/ValLoanMarkquery002','/valuation/assets/loanmark/ValLoanMarkquery002','VALINFOSERVICE_001_003_007','','','02','02','查看拆借类资产','system','',to_date('2018/10/10', 'yyyy/mm/dd'), 'system',to_date('2018/10/10', 'yyyy/mm/dd'));
alter table FIN_TRADE_PRODUCT add CALENDAR_ID  VARCHAR2(32 BYTE);
/**add by huwei  拆借类资产*/

/**add by 2018-10-22 lishicheng 计划类资产净值信息新增修改删除走流程 start**/
  --增加流程业务类型
delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'V04';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'V04', '计划类资产净值信息', 1, '0', 'system', null, '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');
--计划类资产净值信息新建页面支持流程
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValNetPlanQuotation003';
--计划类资产净值信息 - 新建 - 提交
delete from sys_wfl_business_module where PAGE_ID = 'ValNetPlanQuotation003' and ELEMENT_ID ='ValNetPlanQuotation003_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValNetPlanQuotation003', 'ValNetPlanQuotation003_btn_submit', 'oneApproval2', 'system', '0001', '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');
--计划类资产净值信息删除页面支持流程
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValNetPlanQuotation001';
--计划类资产净值信息 - 删除 - 提交
delete from sys_wfl_business_module where PAGE_ID = 'ValNetPlanQuotation001' and ELEMENT_ID = 'ValNetPlanQuotation001_btn_delete';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValNetPlanQuotation001', 'ValNetPlanQuotation001_btn_delete', 'oneApproval2', 'system', '0001', '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');
--计划类资产净值信息修改页面支持流程
--计划类资产净值信息 - 修改- 提交
delete from sys_wfl_business_module where PAGE_ID = 'ValNetPlanQuotation003' and ELEMENT_ID ='ValNetPlanQuotation003_btn_save';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValNetPlanQuotation003', 'ValNetPlanQuotation003_btn_save', 'oneApproval2', 'system', '0001', '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');

/**add by 2018-10-22 lishicheng 计划类资产净值信息新增修改删除走流程 end**/

/** add by 2018-10-22 zhangwei 估值方法方案需求变更 **/
-- 删除 - 画面数据字典过滤
delete from SYS_PAGE_DICTLIST spd where spd.page_id='ValuationMethodScheme003';
insert into SYS_DATADICT_LIST_LINK values ('VALUATION_METHOD','04','INV_AIM','AC','system','',to_date('2018/09/11', 'yyyy/mm/dd'), 'system',to_date('2018/09/11', 'yyyy/mm/dd'));
/** add by 2018-10-22 zhangwei 估值方法方案需求变更 **/
/**ADD                                             2018-10-22                                              end**/


/**ADD                                             2018-10-23                                              begin**/
/**add by wangqidong  回购类资产  start*/
insert into SYS_MENU values ('VALINFOSERVICE_001_003_007','VAL','','VALINFOSERVICE_001_003','ValRepurchaseAssetQuery001','5','02','回购类资产','system','',to_date('2018/02/06', 'yyyy/mm/dd'), 'system',to_date('2018/02/06', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValRepurchaseAssetQuery001','VAL','valuation/assets/repurchase/ValRepurchaseAssetQuery001','valuation/assets/repurchase/ValRepurchaseAssetQuery001','VALINFOSERVICE_001_003_007','','','02','02','回购类资产查询','system','',to_date('2018/2/1', 'yyyy/mm/dd'), 'system',to_date('2018/2/1', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValRepurchaseAssetQuery002','VAL','valuation/assets/repurchase/ValRepurchaseAssetQuery002','valuation/assets/repurchase/ValRepurchaseAssetQuery002','VALINFOSERVICE_001_003_007','','','02','02','查看回购类资产','system','',to_date('2018/2/1', 'yyyy/mm/dd'), 'system',to_date('2018/2/1', 'yyyy/mm/dd'));

insert into SYS_PAGE_DICTLIST values ('ValRepurchaseAssetQuery001','FINPROD_TYPE','F02','wqd','',to_date('2018/09/09', 'yyyy/mm/dd'), 'wqd',to_date('2018/09/09', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValRepurchaseAssetQuery001','FINPROD_TYPE','F03','wqd','',to_date('2018/09/09', 'yyyy/mm/dd'), 'wqd',to_date('2018/09/09', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValRepurchaseAssetQuery001','FINPROD_TYPE','F04','wqd','',to_date('2018/09/09', 'yyyy/mm/dd'), 'wqd',to_date('2018/09/09', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValRepurchaseAssetQuery001','FINPROD_TYPE','F05','wqd','',to_date('2018/09/12', 'yyyy/mm/dd'), 'wqd',to_date('2018/09/09', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValRepurchaseAssetQuery002','FINPROD_TYPE','F02','wqd','',to_date('2018/09/09', 'yyyy/mm/dd'), 'wqd',to_date('2018/09/09', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValRepurchaseAssetQuery002','FINPROD_TYPE','F03','wqd','',to_date('2018/09/09', 'yyyy/mm/dd'), 'wqd',to_date('2018/09/09', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValRepurchaseAssetQuery002','FINPROD_TYPE','F04','wqd','',to_date('2018/09/09', 'yyyy/mm/dd'), 'wqd',to_date('2018/09/09', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValRepurchaseAssetQuery002','FINPROD_TYPE','F05','wqd','',to_date('2018/09/12', 'yyyy/mm/dd'), 'wqd',to_date('2018/09/09', 'yyyy/mm/dd'));
/**add by wangqidong  回购类资产  end*/
--数据字典新增8004-产品_预期收益型_客户收益计提
delete SYS_DATADICT_LIST a where a.dict_code='TABLE_ID' and a.item_code='8004';
insert into SYS_DATADICT_LIST values ('TABLE_ID','8004','产品_预期收益型_客户收益计提','269','0','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));

--数据字典新增-含权债摊销日
delete from SYS_DATADICT where DICT_CODE = 'EXEC_DATE';
insert into SYS_DATADICT values ('EXEC_DATE','VAL','含权债摊销日','','02','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
--数据字典新增 含权债摊销日 -最近行权日  最近赎回行权日 最近回售行权日  到期日
delete from SYS_DATADICT_LIST where DICT_CODE = 'EXEC_DATE';
insert into SYS_DATADICT_LIST values ('EXEC_DATE','01','最近行权日','1','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('EXEC_DATE','02','最近赎回行权日','2','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('EXEC_DATE','03','最近回售行权日','3','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('EXEC_DATE','04','到期日','4','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));


/**add by ruanhongming  数据字典新增:交易日历、申购确认速度、赎回确认速度  start*/
insert into SYS_DATADICT values ('TRANSACTION_CALENDAR','VAL','交易日历','','02','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('APPLY_SPEED','VAL','申购确认速度','','02','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('REDEM_SPEED','VAL','赎回确认速度','','02','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));

insert into SYS_DATADICT_LIST values ('TRANSACTION_CALENDAR','01','中国银行间交易日历','1','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TRANSACTION_CALENDAR','02','上海交易所日历','2','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TRANSACTION_CALENDAR','03','深圳交易所日历','3','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('APPLY_SPEED','01','T+0','1','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('APPLY_SPEED','02','T+1','2','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('REDEM_SPEED','01','T+0','1','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('REDEM_SPEED','02','T+1','2','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));

/**add by ruanhongming  数据字典新增:交易日历、申购确认速度、赎回确认速度  end*/
/**ADD                                             2018-10-23                                              end**/
/**ADD                                             2018-10-24                                              begin**/
/** add by zhangwei 20181024 债券借贷计提账务处理 start **/
-- 在BASE和MAST都执行
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) 
values ('403','VAL','债券借贷计提-账务明细批处理','','secLendDrawBookDetailTask','60','system',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'), '');

insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01003cad','403','system','',to_date('2018/10/08', 'yyyy/mm/dd'), 'system',to_date('2018/10/08', 'yyyy/mm/dd'));
/** add by zhangwei 20181024 债券借贷计提账务处理 end **/

/**add by wujie on 20181024 start remark:增加投资目的获取方式*/
delete from SYS_DATADICT where dict_code='INVESTMENT_PURPOSE_ACCESS';
insert into SYS_DATADICT values ('INVESTMENT_PURPOSE_ACCESS','VAL','投资目的获取方式','','02','system','',to_date('2018/10/24', 'yyyy/mm/dd'), 'system',to_date('2018/10/24', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where dict_code='INVESTMENT_PURPOSE_ACCESS';
insert into SYS_DATADICT_LIST values ('INVESTMENT_PURPOSE_ACCESS','01','上游提供','1','0','system','',to_date('2018/10/24', 'yyyy/mm/dd'), 'system',to_date('2018/10/24', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('INVESTMENT_PURPOSE_ACCESS','02','估值方案生成','2','0','system','',to_date('2018/10/24', 'yyyy/mm/dd'), 'system',to_date('2018/10/24', 'yyyy/mm/dd'));
/**add by wujie on 20181024 end remark:增加投资目的获取方式*/

/** add by qujianzhong  现金流量表菜单、画面脚本 start **/
delete from SYS_MENU where MENU_ID='VALSTATEACCOUNT_001_002_004';
insert into SYS_MENU values ('VALSTATEACCOUNT_001_002_004','VAL','','VALSTATEACCOUNT_001_002','CashFlowsStatement001','3','02','现金流量表','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
delete from SYS_PAGE where PAGE_ID='CashFlowsStatement001';
insert into SYS_PAGE values ('CashFlowsStatement001','VAL','/valuation/accounadjustmanage/cashflowsstatement/CashFlowsStatement001','/valuation/accounadjustmanage/cashflowsstatement/CashFlowsStatement001','VALSTATEACCOUNT_001_002_004','','','02','02','现金流量表','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
/** add by qujianzhong  现金流量表菜单、画面脚本 end **/
/**ADD                                             2018-10-24                                              end**/
/**ADD                                             2018-10-25                                              begin**/
/**add by weipeng for 增值税百元应计利息小数位数处理 start **/
insert into SYS_PARA values ('VAL','SEC_INT_SCALE_BANK','12','01','(null)','百元面值应计利息精度(银行间)','system','',to_date('2018/10/23', 'yyyy/mm/dd'), 'system',to_date('2018/10/23', 'yyyy/mm/dd'));
insert into SYS_PARA values ('VAL','SEC_INT_SCALE_EXCHANGE','8','01','(null)','百元面值应计利息精度(交易所)','system','',to_date('2018/10/23', 'yyyy/mm/dd'), 'system',to_date('2018/10/23', 'yyyy/mm/dd'));
/**add by weipeng for 增值税百元应计利息小数位数处理  end **/
/**ADD                                             2018-10-25                                              end**/
/**add                                             2018-10-26                                               begin**/
/** add by qujianzhong 20181026 数据字典交易市场增加字典项‘理财直接融资工具平台’ start */
delete from SYS_DATADICT_LIST where dict_code='TRADE_MARKET' and item_code='19';
insert into SYS_DATADICT_LIST values ('TRADE_MARKET','19','理财直接融资工具平台','20','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
/** add by qujianzhong 20181026 数据字典交易市场增加字典项‘理财直接融资工具平台’ end */
/**净值下发附件上传下载 add by ruanhongming **/
DELETE FROM sys_page WHERE PAGE_ID = 'ValNetValueConfirm003';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValNetValueConfirm003', 'VAL', '/valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm003', 'valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm003', null, null, null, '02', '02', '净值确认下发附件上传', 'system', null, '01-2月 -18 12.00.00.000000 上午', 'system', '01-2月 -18 12.00.00.000000 上午');

/** add by ruanhongming 20181026 净值下发附件上传下载 end **/

/** add by niuqianyu 交易日历数据字典值修改 start **/
delete from SYS_DATADICT_LIST where DICT_CODE='TRANSACTION_CALENDAR';
insert into SYS_DATADICT_LIST values ('TRANSACTION_CALENDAR','IB','中国银行间交易日历','1','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TRANSACTION_CALENDAR','SH','上海交易所日历','2','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TRANSACTION_CALENDAR','SZ','深圳交易所日历','3','0','system','',to_date('2018/10/22', 'yyyy/mm/dd'), 'system',to_date('2018/10/22', 'yyyy/mm/dd'));

/** add by niuqianyu 交易日历数据字典值修改 end **/
/**add                                         2018-10-26                                                    end**/
/**add                                         2018-10-29                                                   start**/
/* 债券借贷标的债券计提-账务明细批处理 base库 add by zhangyf 20181029 start */
-- 批处理任务【脚本请在BASE和MAST都执行】：债券借贷标的债券计提-账务明细批处理
delete from SYS_BATCH_TASK where task_id ='404';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('404','VAL','债券借贷标的债券计提-账务明细批处理','','secLendFinDrawBookDetailTask','60','system',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'), '');

--数据字典明细表：债券借贷-融入债券卖出后购回
delete from SYS_DATADICT_LIST where DICT_CODE  ='TABLE_ID' and ITEM_CODE = '2521';
insert into SYS_DATADICT_LIST values ('TABLE_ID','2521','债券借贷-融入债券卖出后购回','289','0','system','',to_date('2018/10/26', 'yyyy/mm/dd'), 'system',to_date('2018/10/26', 'yyyy/mm/dd'));
/* 债券借贷标的债券计提-账务明细批处理 base库 add by zhangyf 20181029 end */

/* 金融产品类型  base库 add by zhangyf 20181029 start */
delete from SYS_DATADICT_LIST where DICT_CODE  ='BUSI_TYPE' and ITEM_CODE = '29';
insert into SYS_DATADICT_LIST values ('BUSI_TYPE','29','债券借贷标的债券交易','29','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where DICT_CODE  ='PS' and ITEM_CODE = 'T30';
insert into SYS_DATADICT_LIST values ('PS','T30','卖空债券结息','44','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where DICT_CODE  ='PS' and ITEM_CODE = 'T33';
insert into SYS_DATADICT_LIST values ('PS','T33','卖空债券还本','47','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
/* 金融产品类型  base库 add by zhangyf 20181029 end */

/* 债券借贷标的债券公允价值变动-账务明细批处理 zhangwei 20181029 start*/
-- 批处理任务【脚本请在BASE和MAST都执行】：债券借贷标的债券公允价值变动-账务明细批处理
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) 
values ('405','VAL','债券借贷标的债券公允价值变动-账务明细批处理','','secLendFinFloatBookDetailTask','70','system',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'), '');

insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01004cad','405','system','',to_date('2018/10/29', 'yyyy/mm/dd'), 'system',to_date('2018/10/29', 'yyyy/mm/dd'));
/* 债券借贷标的债券公允价值变动-账务明细批处理 zhangwei 20181029 end */

/**系统参数增加冲减应计利息方式 add by chengzhong start  2018-10-29**/
insert into SYS_PARA values ('VAL','OFFSET_INTAMT_MODE','01','01','(null)','冲减应计利息方式   01、按比例冲减  02、按实付利息冲减','system','',to_date('2018/10/23', 'yyyy/mm/dd'), 'system',to_date('2018/10/23', 'yyyy/mm/dd'));
/**系统参数增加冲减应计利息方式  add by chengzhong end  2018-10-29**/

/**系统参数增加估值模板选择add by chengzhong start  2018-10-29**/
delete from SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'HZ_SCHEME_SWITCH';
insert into SYS_PARA values ('VAL','SCHEME_MODE','01','01','(null)','估值模板选择 01：理财产品模板（系统推荐）02：基金模板（按货基和非货基模板)','system','',to_date('2017/9/13', 'yyyy/mm/dd'), 'system',to_date('2017/9/13', 'yyyy/mm/dd'));
/**系统参数增加增加估值模板选择add by chengzhong end  2018-10-29**/



/**add by lishicheng  操作手册 start 2018-10-29**/
insert into SYS_PAGE values ('DocumentManagement002','VAL','/valuation/documentmanagement/DocumentManagement002','valuation/documentmanagement/DocumentManagement002',null,null,null,'02','02','上传操作手册','system',null,to_timestamp('25-10月-18 01.55.53.057000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('25-10月-18 01.55.57.608000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
insert into SYS_PAGE values ('DocumentManagement001','VAL','/valuation/documentmanagement/DocumentManagement001','valuation/documentmanagement/DocumentManagement001',null,null,null,'02','02','操作手册管理','system',null,to_timestamp('24-10月-18 01.09.56.843000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-10月-18 01.10.02.262000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

/**add by lishicheng 操作手册  end 2018-10-29**/

/**add by huangjian 存款类资产 start 2018-10-29**/
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, DICT_ID, DICT_ITEM_VAL, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ValDespositAssetQuery002', 'FINPROD_TYPE', 'F07', 'zane', null, TIMESTAMP '2018-09-26 00:00:00', 'zane', TIMESTAMP '2018-09-26 00:00:00');
insert into SYS_PAGE_ELEMENT values ('ValDespositAssetQuery001','ValDespositAssetQuery001_btn_add','01','01','','新建','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

/**add by huangjian 存款类资产 end 2018-10-29**/

/**add by lishicheng  操作手册 start 2018-10-29**/
 update SYS_PAGE set REMARK='新建操作手册'  where  PAGE_ID='DocumentManagement002';
 update SYS_PAGE set REMARK='操作手册管理'  where  PAGE_ID='DocumentManagement001';
/**add by lishicheng 操作手册  end 2018-10-29**/
 --会计元素：债券借贷-融入债券利息计提
delete from SYS_DATADICT_LIST where DICT_CODE = 'FILE_TYPE' AND ITEM_CODE = '04';
insert into SYS_DATADICT_LIST values ('FILE_TYPE','04','净值确认下发附件','4','0','system','',to_date('2018/06/26', 'yyyy/mm/dd'), 'system',to_date('2018/06/26', 'yyyy/mm/dd'));	
/* 债券借贷标的债券计提-账务明细批处理 mast库 add by zhangyf 20181029 end */

 /**add                                            2018-10-29                                                end**/
/**ADD                                             2018-10-30                                              start**/
/**add by wujie on 20181030 start remark:指令类型增加应收款项转入、应收款项转出**/
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE='ORDER_TYPE' AND ITEM_CODE IN ('N2802','N2803');
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N2802','应收款项转入','107','0','system','',to_date('2018/10/30', 'yyyy/mm/dd'), 'system',to_date('2018/10/30', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N2803','应收款项转出','108','0','system','',to_date('2018/10/30', 'yyyy/mm/dd'), 'system',to_date('2018/10/30', 'yyyy/mm/dd'));
/**add by wujie on 20181030 end remark:指令类型增加应收款项转入、应收款项转出**/

/** 债券重分类画面相关脚本 add by leipeng start**/

--添加页面元素
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID='ValAssetReclassification001' AND ELEMENT_ID IN ('ValAssetReclassification001_btn_submit','ValAssetReclassification001_btn_search');
insert into SYS_PAGE_ELEMENT values ('ValAssetReclassification001','ValAssetReclassification001_btn_submit','01','01','','债券重分类提交','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('ValAssetReclassification001','ValAssetReclassification001_btn_search','01','01','','债券重分类查询','system','',to_date('2018/2/5', 'yyyy/mm/dd'), 'system',to_date('2018/2/5', 'yyyy/mm/dd'));

--添加页面信息
DELETE FROM SYS_PAGE WHERE PAGE_ID='ValAssetReclassification001';
insert into SYS_PAGE values ('ValAssetReclassification001','VAL','/valuation/dailyoperation/dailyaccountmake/valassetreclassification/ValAssetReclassification001','valuation/dailyoperation/dailyaccountmake/valassetreclassification/ValAssetReclassification001','DAILYOPERATION_001_001_007','','','02','01','资产重分类','system','',to_date('2018/10/24', 'yyyy/mm/dd'), 'system',to_date('2018/10/24', 'yyyy/mm/dd'));
DELETE FROM SYS_MENU WHERE MENU_ID='DAILYOPERATION_001_001_007';
insert into SYS_MENU values ('DAILYOPERATION_001_001_007','VAL','','DAILYOPERATION_001_001','ValAssetReclassification001','7','02','资产重分类','system','',to_date('2018/10/24', 'yyyy/mm/dd'), 'system',to_date('2018/10/24', 'yyyy/mm/dd'));


--增加流程业务类型
delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'V05';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'V05', '债券重分类', 1, '0', 'system', null, '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');

--债券重分类 - 新建 - 提交
delete from sys_wfl_business_module where PAGE_ID = 'ValAssetReclassification001' and ELEMENT_ID = 'ValAssetReclassification001_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValAssetReclassification001', 'ValAssetReclassification001_btn_submit', 'oneApproval', 'system', '0001', '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');

/** 债券重分类画面相关脚本 add by leipeng end**/

/** SYS_DATADICT_LIST 增加 (FLOAT_CALC_RULE 02 高水位法)   add by zhangzhao start**/
delete from SYS_DATADICT_LIST where DICT_CODE = 'FLOAT_CALC_RULE' and  ITEM_CODE = '02';
insert into SYS_DATADICT_LIST values ('FLOAT_CALC_RULE','02','高水位法','2','0','inter','',to_date('2018/06/20', 'yyyy/mm/dd'), 'system',to_date('2018/06/20', 'yyyy/mm/dd'));
/** SYS_DATADICT_LIST 增加 (FLOAT_CALC_RULE 02 高水位法)  add by zhangzhao end**/

/**ADD                                             2018-10-30                                              end**/
 

/**ADD                                             2018-10-31                                              start**/

/**add by wujie start remark:数据字典增加：结算方式(债券借贷)**/
delete from SYS_DATADICT_LIST where dict_code='SETTLEMENT_WAY_LENDING';
insert into SYS_DATADICT_LIST values ('SETTLEMENT_WAY_LENDING','01','劵款对付','1','0','system','',to_date('2018/10/31', 'yyyy/mm/dd'), 'system',to_date('2018/10/31', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SETTLEMENT_WAY_LENDING','02','券券对付','2','0','system','',to_date('2018/10/31', 'yyyy/mm/dd'), 'system',to_date('2018/10/31', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SETTLEMENT_WAY_LENDING','03','返券付费解券','3','0','system','',to_date('2018/10/31', 'yyyy/mm/dd'), 'system',to_date('2018/10/31', 'yyyy/mm/dd'));

delete from SYS_DATADICT where dict_code='SETTLEMENT_WAY_LENDING';
insert into SYS_DATADICT values ('SETTLEMENT_WAY_LENDING','VAL','结算方式(债券借贷)','','02','system','',to_date('2018/10/31', 'yyyy/mm/dd'), 'system',to_date('2018/10/31', 'yyyy/mm/dd'));
/**add by wujie end remark:数据字典增加：结算方式(债券借贷)**/

/**ADD                                             2018-10-31                                              end**/

/**ADD                                             2018-11-01                                              start**/
/**add by qujianzhong 债券类资产修改界面提交按钮走流程配置 start**/
update SYS_PAGE set is_need_workflow='01' where page_id='ValBondassetquery002';
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID='ValBondassetquery002' and ELEMENT_ID='ValBondassetquery002_btn_submit';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('ValBondassetquery002','ValBondassetquery002_btn_submit','oneApproval','system','0001',to_timestamp('03-2月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('03-2月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
/**add by qujianzhong 债券类资产修改界面提交按钮走流程配置 end**/

/**add by qujianzhong 债券类资产查询界面删除按钮走流程配置 start**/
update SYS_PAGE set is_need_workflow='01' where page_id='ValBondassetquery001';
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID='ValBondassetquery001' and ELEMENT_ID='ValBondassetquery001_btn_delete';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('ValBondassetquery001','ValBondassetquery001_btn_delete','oneApproval','system','0001',to_timestamp('03-2月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('03-2月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
/**add by qujianzhong 债券类资产查询界面删除按钮走流程配置 end**/

/**add by wujie start remark:数据字典：基数类型删除除了实收资本、资产净值外的其他项**/
delete from SYS_DATADICT_LIST where dict_code='BASE_TYPE' 
and item_code in ('01','02','05','06','07','08');
/**add by wujie end remark:数据字典：基数类型删除除了实收资本、资产净值外的其他项**/

/**add by wujie start remark:数据字典：回购_机构核算分类数据字典项修改**/
delete from SYS_DATADICT_LIST where dict_code='BONDREP_ORG_VALCLASS';
insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','IB','银行间','1','0','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','SH','上交所','2','0','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BONDREP_ORG_VALCLASS','SZ','深交所','3','0','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));
/**add by wujie end remark:数据字典：回购_机构核算分类数据字典项修改**/
--废弃预期收益型两个批处理 wanggengyun
delete sys_batch_task a  where a.task_id in('300','305');
delete sys_batch_job a where a.task_id in('305','300');
/**ADD                                             2018-11-01                                              end**/

/**ADD                                             2018-11-02                                              start**/
/** add by qujianzhong 20181102 业务类型增加：债券类资产信息 start**/
delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE='V06';
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('COMMON_BUSINESS_TYPE','V06','债券类资产信息',12,'0','system',null,to_timestamp('03-2月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('03-2月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
/** add by qujianzhong 20181102 业务类型增加：债券类资产信息 end**/

/**ADD                                             2018-11-02                                              end**/
/** add by haungjian 2018-11-02 杭州银行账 银行统计查询 start**/
insert into SYS_MENU values ('VALINFOSERVICE_001_010','VAL','','VALINFOSERVICE_001','ValBankAccountQuery001','1','02','银行账统计','system','',to_date('2018/02/02', 'yyyy/mm/dd'), 'system',to_date('2018/02/02', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValBankAccountQuery001','VAL','/valuation/bankaccount/ValBankAccountQuery001','/valuation/bankaccount/ValBankAccountQuery001','VALINFOSERVICE_001_010','','','02','02','银行账统计','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/10/31', 'yyyy/mm/dd'));
insert into SYS_PARA values ('VAL','SUBJECT_NO_LIST','22060101,22060201,64030101,64030201','01','(null)','科目集合','system','',to_date('2018/11/2', 'yyyy/mm/dd'), 'system',to_date('2018/11/2', 'yyyy/mm/dd'));
--新增交易方向_交易方向_赎回收益确认 wanggengyun
insert into SYS_DATADICT_LIST values ('PS','C15','赎回收益确认','40','0','system','',to_date('2018/7/25', 'yyyy/mm/dd'), 'system',to_date('2018/7/25', 'yyyy/mm/dd'));
--废弃预期收益型费前 wanggengyun
delete sys_batch_task a  where a.task_id in('301');
delete sys_batch_job a where a.task_id in('301');
/**ADD                                             2018-11-02                                              end**/ 

/**ADD                                             2018-11-05                                              start**/ 
/**add by leipeng 画面表添加脚本 start**/
DELETE FROM SYS_PAGE WHERE PAGE_ID='ValAssetReclassification002';
insert into SYS_PAGE values  ('ValAssetReclassification002','VAL','/valuation/dailyoperation/dailyaccountmake/valassetreclassification/ValAssetReclassification002','valuation/dailyoperation/dailyaccountmake/valassetreclassification/ValAssetReclassification002','DAILYOPERATION_001_001_007','','','02','01','资产重分类','system','',to_date('2018/10/24',  'yyyy/mm/dd'), 'system',to_date('2018/10/24', 'yyyy/mm/dd'));
/**add by leipeng 画面表添加脚本 end**/

/**add by niuqianyu 新增科目名称、业务类别数据字典 start**/
insert into SYS_DATADICT values ('SUBJECT_NAME','VAL','科目名称','','02','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('BUSINESS_TYPE','VAL','业务类别','','02','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));

insert into SYS_DATADICT_LIST values ('SUBJECT_NAME','SJT01','已实现未分配损益','1','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SUBJECT_NAME','SJT02','应付客户收益','2','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SUBJECT_NAME','SJT03','管理费收入','3','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SUBJECT_NAME','SJT04','存放托管行资金','4','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SUBJECT_NAME','SJT05','存放中央款项','5','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BUSINESS_TYPE','TYPE01','投资收益分配','1','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BUSINESS_TYPE','TYPE02','兑付客户收益','2','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BUSINESS_TYPE','TYPE03','兑付管理费','3','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));

/**add by niuqianyu 新增科目名称、业务类别数据字典   end**/
/**add by huangjian 日期维度、产品维度 start**/

insert into SYS_DATADICT values ('DATE_DIMENSION','VAL','日期维度','','02','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('PRODUCT_DIMENSION','VAL','产品维度','','02','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));

insert into SYS_DATADICT_LIST values ('DATE_DIMENSION','01','汇总','1','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('DATE_DIMENSION','02','年','2','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('DATE_DIMENSION','03','季度','3','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('DATE_DIMENSION','04','月','4','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('DATE_DIMENSION','05','日','5','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PRODUCT_DIMENSION','01','汇总','1','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PRODUCT_DIMENSION','02','本金标识','2','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PRODUCT_DIMENSION','03','产品','3','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));

/**add by huangjian 日期维度、产品维度 end**/

/** add by lishicheng 2018-11-05 文件类型增加操作手册 start**/
delete from SYS_DATADICT_LIST where DICT_CODE='FILE_TYPE' and ITEM_CODE='03';
insert into SYS_DATADICT_LIST values ('FILE_TYPE','03','操作手册','3','0','system','',to_date('2018/06/26', 'yyyy/mm/dd'), 'system',to_date('2018/06/26', 'yyyy/mm/dd'));
/** add by lishicheng 2018-11-05 文件类型增加操作手册 end**/


/**add by wujie on 20181105 start remark:标准资讯接口：估值来源数据字典**/
--数据字典
delete from SYS_DATADICT where dict_code='VALUE_SOURCE_ZX';
insert into SYS_DATADICT values ('VALUE_SOURCE_ZX','VAL','估值来源_资讯','','02','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where dict_code='VALUE_SOURCE_ZX';

--标准化
insert into SYS_DATADICT_LIST values ('VALUE_SOURCE_ZX','CBC','CBC','1','01','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('VALUE_SOURCE_ZX','CSD','CSD','2','01','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('VALUE_SOURCE_ZX','SCH','SCH','3','01','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));


--郑州专属
insert into SYS_DATADICT_LIST values ('VALUE_SOURCE_ZX','中债','CBC','1','01','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('VALUE_SOURCE_ZX','中证','CSD','2','01','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('VALUE_SOURCE_ZX','上清','SCH','3','01','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));

--邮储专属
insert into SYS_DATADICT_LIST values ('VALUE_SOURCE_ZX','ZA','CBC','1','01','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('VALUE_SOURCE_ZX','ZZ','CSD','2','01','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('VALUE_SOURCE_ZX','SQ','SCH','3','01','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));

/**add by wujie on 20181105 end remark:标准资讯接口：估值来源数据字典**/

/**ADD                                             2018-11-05                                              end**/ 

/**ADD                                             2018-11-06                                              start**/ 
/**add by niuqianyu 科目名称、业务类别数据字典修改 start**/
delete from SYS_DATADICT_LIST where DICT_CODE='SUBJECT_NAME';
insert into SYS_DATADICT_LIST values ('SUBJECT_NAME','01','已实现未分配损益','1','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SUBJECT_NAME','02','应付客户收益','2','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SUBJECT_NAME','03','管理费收入','3','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SUBJECT_NAME','04','存放托管行资金','4','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SUBJECT_NAME','05','存放中央款项','5','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where DICT_CODE='BUSINESS_TYPE';
insert into SYS_DATADICT_LIST values ('BUSINESS_TYPE','01','投资收益分配','1','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BUSINESS_TYPE','02','兑付客户收益','2','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BUSINESS_TYPE','03','兑付管理费','3','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));

/**add by niuqianyu 科目名称、业务类别数据字典修改 end**/

/**add by weipeng 金融产品类型数据字典修改 start**/
delete from SYS_DATADICT_LIST where DICT_CODE='FINPROD_TYPE';
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F01','债券','1','1','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F02','债券质押式正回购','2','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F03','债券质押式逆回购','3','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F04','债券买断式正回购','4','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F05','债券买断式逆回购','5','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F06','基金','6','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F07','存款','7','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F08','拆入','8','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F09','拆出','9','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F10','股票','10','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F11','即期','11','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F12','远期','12','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F13','掉期','13','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F14','利率互换','14','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F16','理财产品','16','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F17','计划类资产','17','0','system','',to_date('2018/04/13', 'yyyy/mm/dd'), 'system',to_date('2018/04/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F18','投资组合','18','0','system','',to_date('2018/10/16', 'yyyy/mm/dd'), 'system',to_date('2018/10/16', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F22','理财子产品','30','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','C03','固定管理费','19','0','system','',to_date('2018/8/8', 'yyyy/mm/dd'), 'system',to_date('2018/8/8', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','C04','浮动管理费','20','0','system','',to_date('2018/8/8', 'yyyy/mm/dd'), 'system',to_date('2018/8/8', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','C05','托管费','21','0','system','',to_date('2018/8/8', 'yyyy/mm/dd'), 'system',to_date('2018/8/8', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','C06','销售费','22','0','system','',to_date('2018/8/8', 'yyyy/mm/dd'), 'system',to_date('2018/8/8', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','C07','税额','23','0','system','',to_date('2018/8/8', 'yyyy/mm/dd'), 'system',to_date('2018/8/8', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F19','其他','24','0','system','',to_date('2018/8/8', 'yyyy/mm/dd'), 'system',to_date('2018/8/8', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F20','资金账户','25','0','system','',to_date('2018/8/20', 'yyyy/mm/dd'), 'system',to_date('2018/8/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F21','同业借款','26','0','system','',to_date('2018/8/20', 'yyyy/mm/dd'), 'system',to_date('2018/8/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','C09','外包服务费','27','0','system','',to_date('2018/8/23', 'yyyy/mm/dd'), 'system',to_date('2018/8/23', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','C10','代销费','28','0','system','',to_date('2018/8/23', 'yyyy/mm/dd'), 'system',to_date('2018/8/23', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','C11','交易费用','29','0','system','',to_date('2018/9/26', 'yyyy/mm/dd'), 'system',to_date('2018/9/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F23','债券借贷融入','30','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F24','债券借贷融出','31','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FINPROD_TYPE','F25','融入债券','32','0','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));

/**add by weipeng 金融产品类型数据字典修改 end**/

/** 资产应税方案 20181107 start **/
insert into SYS_PAGE values ('TaxScheme005','VAL','/valuation/taxparametermanage/taxscheme/TaxScheme005','valuation/taxparametermanage/taxscheme/TaxScheme005','VALPARMANAGE_001_003_001','','','02','02','资产应税信息','system','',to_date('2018/10/11', 'yyyy/mm/dd'), 'system',to_date('2018/10/11', 'yyyy/mm/dd'));

insert into SYS_DATADICT values ('MAINTAIN_MODE','VAL','维护方式','','02','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MAINTAIN_MODE','01','手工维护','1','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MAINTAIN_MODE','02','规则匹配','2','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
/** 资产应税方案 20181107 end **/


/**add by wujie on 20181107 start remark:计划类资产类型增加非货币基金**/
delete from SYS_DATADICT_LIST where dict_code='PLANNED_TYPE' and item_code='15';
insert into SYS_DATADICT_LIST values ('PLANNED_TYPE','15','非货币基金','15','0','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));
/**add by wujie on 20181107 end**/

/**add by weipeng 系统参数表新增 剩余期限计算日开关01:行权日 02:到期日 end**/
delete from SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'SEC_END_DAYS_SWITCH';
insert into SYS_PARA values ('VAL','SEC_END_DAYS_SWITCH','02','01','(null)','剩余期限计算日开关01:行权日 02:到期日','system','',to_date('2018/10/9', 'yyyy/mm/dd'), 'system',to_date('2018/10/9', 'yyyy/mm/dd'));
/**add by weipeng 系统参数表新增 剩余期限计算日开关01:行权日 02:到期日  end**/
/**ADD                                             2018-11-06                                              end**/ 


/**ADD                                             2018-11-13                                              start**/ 

/**add by yangjian on 20181113 start remark:数据移植页面相关脚本**/
--数据字典插入【数据移植模版类型】
delete from SYS_DATADICT where dict_code = 'DATA_TRANSPLANT_MODEL_TYPE';
insert into SYS_DATADICT values ('DATA_TRANSPLANT_MODEL_TYPE','VAL','数据移植模版类型','','02','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where dict_code = 'DATA_TRANSPLANT_MODEL_TYPE';
insert into SYS_DATADICT_LIST values ('DATA_TRANSPLANT_MODEL_TYPE','01','头寸模版','1','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('DATA_TRANSPLANT_MODEL_TYPE','02','增值税模版','2','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('DATA_TRANSPLANT_MODEL_TYPE','03','账务余额模版','3','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('DATA_TRANSPLANT_MODEL_TYPE','04','账务余额（在途债券）模版','4','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('DATA_TRANSPLANT_MODEL_TYPE','05','增值税_90号文模版','5','0','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
/**add by yangjian on 20181113 end remark:数据移植页面相关脚本**/

/**ADD                                             2018-11-13                                              end**/ 

/**ADD                                             2018-11-14                                              start**/ 
/**add by wujie on 20181114 start remark:数据字典增加结算状态**/
delete from SYS_DATADICT where dict_code='SETTLEMENT_STATUS';
insert into SYS_DATADICT values ('SETTLEMENT_STATUS','VAL','结算状态','','02','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where dict_code='SETTLEMENT_STATUS';
insert into SYS_DATADICT_LIST values ('SETTLEMENT_STATUS','01','结算成功','1','0','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SETTLEMENT_STATUS','02','结算失败','2','0','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
/**add by wujie on 20181114 end remark:数据字典增加结算状态**/

--数字字典新增一个 会计事件(资金收款-存放同业还本/到期)
delete SYS_DATADICT_LIST a where a.dict_code='TABLE_ID' and a.item_code='2734';
insert into SYS_DATADICT_LIST values ('TABLE_ID','2734','资金收款-存放同业还本/到期','288','0','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));


/**ADD                                             2018-11-14                                              end**/ 
/**ADD                                             2018-11-15                                              start**/ 
/** 数据导入 文件上传 zhangwei 20181115  start **/
insert into SYS_MENU values ('VALEXCLEIMP_001_005','VAL','','VALEXCLEIMP_001','DataFileUpload001','5','02','文件上传','system','',to_date('2018/02/02', 'yyyy/mm/dd'), 'system',to_date('2018/02/02', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('DataFileUpload001','VAL','/valuation/fileupload/DataFileUpload001','valuation/fileupload/DataFileUpload001','VALEXCLEIMP_001_005','','','02','02','文件上传','system','',to_date('2018/10/24', 'yyyy/mm/dd'), 'system',to_date('2018/10/24', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('DataFileUpload002','VAL','/valuation/fileupload/DataFileUpload002','valuation/fileupload/DataFileUpload002','VALEXCLEIMP_001_005','','','02','02','文件上传','system','',to_date('2018/10/25', 'yyyy/mm/dd'), 'system',to_date('2018/10/25', 'yyyy/mm/dd'));
/** 数据导入 文件上传 zhangwei 20181115 end **/

/**add by niuqianyu 科目流水查询、科目余额查询画面地址修改 start**/
delete from SYS_PAGE where PAGE_ID='ValSubjectWaterQuery001';
insert into SYS_PAGE values ('ValSubjectWaterQuery001','VAL','/valuation/financemanage/accountbook/valsubjectwaterquery/ValSubjectWaterQuery001','valuation/financemanage/accountbook/valsubjectwaterquery/ValSubjectWaterQuery001','VALFINANCEMANAGE_001_002_002','','','02','02','科目流水查询','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
delete from SYS_PAGE where PAGE_ID='ValSubjectBalanceQuery001';
insert into SYS_PAGE values ('ValSubjectBalanceQuery001','VAL','/valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery001','valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery001','VALFINANCEMANAGE_001_002_003','','','02','02','余额表查询','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
delete from SYS_PAGE where PAGE_ID='ValSubjectBalanceQuery002';
insert into SYS_PAGE values ('ValSubjectBalanceQuery002','VAL','/valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery002','valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery002','VALFINANCEMANAGE_001_002_003','','','02','02','科目余额查询查看账套号','system','',to_date('2018/9/14', 'yyyy/mm/dd'), 'system',to_date('2018/9/14', 'yyyy/mm/dd'));
delete from SYS_PAGE where PAGE_ID='ValSubjectBalanceQuery003';
insert into SYS_PAGE values ('ValSubjectBalanceQuery003','VAL','/valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery003','valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery003','VALFINANCEMANAGE_001_002_003','','','02','02','科目余额查询合并展示查看','system','',to_date('2018/9/14', 'yyyy/mm/dd'), 'system',to_date('2018/9/14', 'yyyy/mm/dd'));

/**add by niuqianyu 科目流水查询、科目余额查询画面地址修改 end**/

/**add by niuqianyu 增值税_发生场景数据字典 start**/
delete from SYS_DATADICT where dict_code = 'ALT_INCOME_SOURCE';
insert into SYS_DATADICT values ('ALT_INCOME_SOURCE','VAL','增值税_发生场景','','02','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where dict_code = 'ALT_INCOME_SOURCE';
insert into SYS_DATADICT_LIST values ('ALT_INCOME_SOURCE','01','计提','1','0','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ALT_INCOME_SOURCE','02','摊销','2','0','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ALT_INCOME_SOURCE','03','还本暂存','3','0','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ALT_INCOME_SOURCE','04','待抵扣增值额','4','0','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));
/**add by niuqianyu 增值税_发生场景数据字典 end**/
/**ADD                                             2018-11-15                                              end**/ 
/**ADD                                             2018-11-16                                              begin**/ 
/** 账套日期修改加流程 */

delete SYS_WFL_BUSINESS_MODULE a where a.page_id='ValBooksetDateSwitch002' and a.element_id='ValBooksetDateSwitch002_btn_submit';
insert into SYS_WFL_BUSINESS_MODULE values ('ValBooksetDateSwitch002','ValBooksetDateSwitch002_btn_submit','oneApproval','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

/**add by huangjian 2018-11-15     活期利率相关及数据字典 start**/
delete SYS_PAGE_ELEMENT a where a.page_id='AccountCurrentRate001' and a.element_id='AccountCurrentRate001_btn_add';
insert into SYS_PAGE_ELEMENT values ('AccountCurrentRate001','AccountCurrentRate001_btn_add','01','01','','账户活期利率新建','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
delete SYS_PAGE a where a.page_id='AccountCurrentRate002';
insert into SYS_PAGE values ('AccountCurrentRate002','VAL','/valuation/trademanage/accountcurrentrate/AccountCurrentRate002','/valuation/trademanage/accountcurrentrate/AccountCurrentRate002','VALINFOSERVICE_001_008','','','02','02','账户活期利率','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
delete SYS_PAGE_DICTLIST a where a.page_id='AccountCurrentRate002' and a.dict_id='BASIS';
insert into SYS_PAGE_DICTLIST values ('AccountCurrentRate002','BASIS','A/360','system','',to_date('2018/09/09', 'yyyy/mm/dd'), 'system',to_date('2018/09/09', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('AccountCurrentRate002','BASIS','A/365','system','',to_date('2018/09/09', 'yyyy/mm/dd'), 'system',to_date('2018/09/09', 'yyyy/mm/dd'));  
delete SYS_DATADICT a where a.dict_code='INT_RATE_STRUCTURE';
insert into SYS_DATADICT values ('INT_RATE_STRUCTURE','VAL','利率结构','','02','system','',to_date('2018/11/13', 'yyyy/mm/dd'), 'system',to_date('2018/11/13', 'yyyy/mm/dd'));
delete SYS_DATADICT_LIST a where a.dict_code='INT_RATE_STRUCTURE';
insert into SYS_DATADICT_LIST values ('INT_RATE_STRUCTURE','01','单层结构','1','0','system','',to_date('2018/11/13', 'yyyy/mm/dd'), 'system',to_date('2018/11/13', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('INT_RATE_STRUCTURE','02','多层结构','2','0','system','',to_date('2018/11/13', 'yyyy/mm/dd'), 'system',to_date('2018/11/13', 'yyyy/mm/dd'));
/**add by huangjian  2018-11-15    活期利率相关及数据字典 end**/
--added by dongxiangyang 批前检查是否控制批处理执行开关 start
delete SYS_PARA a where a.system_code='VAL' and a.para_code='PRE_BATCH_CHECK';
insert into SYS_PARA values ('VAL','PRE_BATCH_CHECK','OFF','01','(null)','批前检查是否控制批处理执行开关','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
--added by dongxiangyang 批前检查是否控制批处理执行开关 end
/**ADD                                             2018-11-16                                              end**/ 

/**ADD                                             2018-11-19                                              start**/ 

/**add by wujie on 20181119 start remark:数据字典增加流通市场**/
delete from SYS_DATADICT where dict_code='SHARES_TRADE_MARKET';
insert into SYS_DATADICT values ('SHARES_TRADE_MARKET','VAL','流通市场','','02','system','',to_date('2018/11/19', 'yyyy/mm/dd'), 'system',to_date('2018/11/19', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where dict_code='SHARES_TRADE_MARKET';
insert into SYS_DATADICT_LIST values ('SHARES_TRADE_MARKET','01','一级市场','1','0','system','',to_date('2018/11/19', 'yyyy/mm/dd'), 'system',to_date('2018/11/19', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SHARES_TRADE_MARKET','02','二级市场','2','0','system','',to_date('2018/11/19', 'yyyy/mm/dd'), 'system',to_date('2018/11/19', 'yyyy/mm/dd'));
/**add by wujie on 20181119 end remark:数据字典增加流通市场**/

/**add by wujie on 20181119 start remark:资金账户类型：券商保证金改为结算备付金**/
delete from SYS_DATADICT_LIST where dict_code='ACCT_TYPE' and ITEM_CODE='A04';
insert into SYS_DATADICT_LIST values ('ACCT_TYPE','A04','结算备付金','4','0','system','',to_date('2018/7/26', 'yyyy/mm/dd'), 'system',to_date('2018/7/26', 'yyyy/mm/dd'));
/**add by wujie on 20181119 end remark:资金账户类型：券商保证金改为结算备付金**/


/**ADD                                             2018-11-19                                              end**/ 

/**ADD                                             2018-11-20                                              start**/ 
/**add by weipeng on 20181120 start remark: 新增对账方案配置、产品对账方案配置页面，页面菜单相关配置 **/
delete from SYS_MENU where menu_id ='VALPARMANAGE_001_004';
delete from SYS_MENU where page_id = 'ValReconcileSchemeInfo001';
delete from SYS_MENU where page_id = 'ValProdSchemeConfig001';
insert into SYS_MENU values ('VALPARMANAGE_001_004','VAL','','VALPARMANAGE_001','','4','','对账参数','system','',to_date('2018/10/10', 'yyyy/mm/dd'), 'system',to_date('2018/10/10', 'yyyy/mm/dd'));
insert into SYS_MENU values ('VALPARMANAGE_001_004_001','VAL','','VALPARMANAGE_001_004','ValReconcileSchemeInfo001','1','02','对账方案配置','system','',to_date('2018/09/12', 'yyyy/mm/dd'), 'system',to_date('2018/09/12', 'yyyy/mm/dd'));
insert into SYS_MENU values ('VALPARMANAGE_001_004_002','VAL','','VALPARMANAGE_001_004','ValProdSchemeConfig001','2','02','产品对账方案配置','system','',to_date('2018/09/12', 'yyyy/mm/dd'), 'system',to_date('2018/09/12', 'yyyy/mm/dd'));

--对账方案配置页面
delete from SYS_PAGE where page_id in ('ValReconcileSchemeInfo001','ValReconcileSchemeInfo002');
insert into SYS_PAGE values ('ValReconcileSchemeInfo001','VAL','/valuation/valuationparametermanage/reconcilescheme/ValReconcileSchemeInfo001','valuation/valuationparametermanage/reconcilescheme/ValReconcileSchemeInfo001','VALPARMANAGE_001_004_001','','','02','02','对账方案配置','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValReconcileSchemeInfo002','VAL','/valuation/valuationparametermanage/reconcilescheme/ValReconcileSchemeInfo002','valuation/valuationparametermanage/reconcilescheme/ValReconcileSchemeInfo002','VALPARMANAGE_001_004_001','','','02','02','对账方案配置','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

--产品对账方案配置页面
delete from SYS_PAGE where page_id in ('ValProdSchemeConfig001','ValProdSchemeConfig002');
insert into SYS_PAGE values ('ValProdSchemeConfig001','VAL','/valuation/valuationparametermanage/prodschemeconfig/ValProdSchemeConfig001','valuation/valuationparametermanage/prodschemeconfig/ValProdSchemeConfig001','VALPARMANAGE_001_004_002','','','02','02','产品对账方案配置','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValProdSchemeConfig002','VAL','/valuation/valuationparametermanage/prodschemeconfig/ValProdSchemeConfig002','valuation/valuationparametermanage/prodschemeconfig/ValProdSchemeConfig002','VALPARMANAGE_001_004_002','','','02','02','产品对账方案配置新建','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
/**add by weipeng on 20181120 start remark: 新增对账方案配置，页面菜单相关配置 **/

/**add by weipeng on 20181120 start remark: 对账方案配置相关数据字典增加 **/
delete from SYS_DATADICT where dict_code in ('BLOCK_TYPE','COMPARE_TYPE','MY_GZB_COL_INDEX','MY_YEB_COL_INDEX');
insert into SYS_DATADICT values ('BLOCK_TYPE','VAL','估值对账配置_区块类型','','02','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('COMPARE_TYPE','VAL','对比类型','','02','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('MY_GZB_COL_INDEX','VAL','本系统估值表列指标','','02','system','',to_date('2018/11/20', 'yyyy/mm/dd'), 'system',to_date('2018/11/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('MY_YEB_COL_INDEX','VAL','本系统余额表列指标','','02','system','',to_date('2018/11/20', 'yyyy/mm/dd'), 'system',to_date('2018/11/20', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where dict_code in ('BLOCK_TYPE','COMPARE_TYPE','MY_GZB_COL_INDEX','MY_YEB_COL_INDEX');
insert into SYS_DATADICT_LIST values ('BLOCK_TYPE','01','科目行','1','0','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BLOCK_TYPE','02','汇总行','2','0','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMPARE_TYPE','C01','本系统对比对方系统','1','0','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMPARE_TYPE','C02','对方系统对比本系统','2','0','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_GZB_COL_INDEX','B','科目代码','1','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_GZB_COL_INDEX','C','科目名称','2','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_GZB_COL_INDEX','D','数量','3','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_GZB_COL_INDEX','E','单位成本','4','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_GZB_COL_INDEX','F','成本','5','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_GZB_COL_INDEX','G','成本占净值比（％）','6','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_GZB_COL_INDEX','H','行情收市价','7','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_GZB_COL_INDEX','I','市值','8','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_GZB_COL_INDEX','J','市值占净值比（％）','9','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_GZB_COL_INDEX','K','估值增值','10','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','B','科目代码','1','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','C','科目名称','2','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','F','期初余额（原币）','3','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','G','本期借方发生额（原币）','4','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','H','本期贷方发生额（原币）','5','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','J','期末余额（原币）','6','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','M','期初余额（本位币）','7','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','N','本期借方发生额（本位币）','8','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','O','本期贷方发生额（本位币）','9','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','Q','期末余额（本位币）','10','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where dict_code = 'SCHEME_TYPE';
insert into SYS_DATADICT_LIST values ('SCHEME_TYPE','01','估值方案','1','0','system','',to_date('2018/8/1', 'yyyy/mm/dd'), 'system',to_date('2018/8/1', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SCHEME_TYPE','02','税务方案','2','0','system','',to_date('2018/8/1', 'yyyy/mm/dd'), 'system',to_date('2018/8/1', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SCHEME_TYPE','03','影子定价方案','3','0','system','',to_date('2018/8/1', 'yyyy/mm/dd'), 'system',to_date('2018/8/1', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SCHEME_TYPE','04','估值表对账方案','4','0','system','',to_date('2018/8/1', 'yyyy/mm/dd'), 'system',to_date('2018/8/1', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SCHEME_TYPE','05','余额表对账方案','4','0','system','',to_date('2018/8/1', 'yyyy/mm/dd'), 'system',to_date('2018/8/1', 'yyyy/mm/dd'));

/**add by niuqianyu 余额类型 数据字典 start**/
delete SYS_DATADICT a where a.dict_code='BAL_TYPE';
insert into SYS_DATADICT values ('BAL_TYPE','VAL','余额类型','','02','system','',to_date('2018/11/20', 'yyyy/mm/dd'), 'system',to_date('2018/11/20', 'yyyy/mm/dd'));
delete SYS_DATADICT_LIST a where a.dict_code='BAL_TYPE';
insert into SYS_DATADICT_LIST values ('BAL_TYPE','01','计提类余额','1','0','system','',to_date('2018/11/20', 'yyyy/mm/dd'), 'system',to_date('2018/11/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BAL_TYPE','02','摊销类余额','2','0','system','',to_date('2018/11/20', 'yyyy/mm/dd'), 'system',to_date('2018/11/20', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BAL_TYPE','03','还本未收息余额','3','0','system','',to_date('2018/11/20', 'yyyy/mm/dd'), 'system',to_date('2018/11/20', 'yyyy/mm/dd'));
/**add by niuqianyu 余额类型 数据字典 end**/
/**ADD                                             2018-11-20                                              end**/ 
/**add by dongxiangyang on 20181120 start remark: 数据回传页面的配置 **/
delete from SYS_MENU where MENU_ID='DAILYOPERATION_001_001_008';
insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('DAILYOPERATION_001_001_008','VAL',null,'DAILYOPERATION_001_001','DataTransplant001',8,'04','数据移植','system',null,to_timestamp('24-10月-18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-10月-18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
delete from SYS_MENU where MENU_ID='DAILYOPERATION_001_001_010';
insert into SYS_MENU values ('DAILYOPERATION_001_001_010','VAL','','DAILYOPERATION_001_001','ValPublicDataexport001','8','02','数据回传','system','',to_date('2018/11/16', 'yyyy/mm/dd'), 'system',to_date('2018/11/16', 'yyyy/mm/dd'));

delete from SYS_PAGE where PAGE_ID='ValPublicDataexport001' or  PAGE_ID='ValInertfLog002';
insert into SYS_PAGE values ('ValPublicDataexport001','VAL','/valuation/dailyoperation/dailyaccountmake/valdataexport/ValPublicDataexport001','/valuation/dailyoperation/dailyaccountmake/valdataexport/ValPublicDataexport001','DAILYOPERATION_001_001_010','','','02','02','数据回传','system','',to_date('2018/11/16', 'yyyy/mm/dd'), 'system',to_date('2018/11/16', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValInertfLog002','VAL','/valuation/interf/inertfLog/ValInertfLog002','valuation/interf/inertfLog/ValInertfLog002','','','','02','02','回传日志','system','',to_date('2018/11/16', 'yyyy/mm/dd'), 'system',to_date('2018/11/16', 'yyyy/mm/dd'));

--Add By QuJianZhong 20181120 利润表增加报表维度 start
delete SYS_DATADICT a where a.dict_code='REPORT_DIMEN';
insert into SYS_DATADICT values ('REPORT_DIMEN','VAL','报表维度','','02','system','',to_date('2018/11/19', 'yyyy/mm/dd'), 'system',to_date('2018/11/19', 'yyyy/mm/dd'));
delete SYS_DATADICT_LIST a where a.dict_code='REPORT_DIMEN';
insert into SYS_DATADICT_LIST values ('REPORT_DIMEN','01','单账套','1','0','system','',to_date('2018/11/19', 'yyyy/mm/dd'), 'system',to_date('2018/11/19', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('REPORT_DIMEN','02','全账套','2','0','system','',to_date('2018/11/19', 'yyyy/mm/dd'), 'system',to_date('2018/11/19', 'yyyy/mm/dd'));
delete from SYS_PAGE where PAGE_ID = 'ValProfitQuery002';
insert into SYS_PAGE values ('ValProfitQuery002','VAL','/valuation/accounadjustmanage/valprofitquery/ValProfitQuery002','valuation/accounadjustmanage/valprofitquery/ValProfitQuery002','','','','02','02','利润表-明细列表','system','',to_date('2018/11/17', 'yyyy/mm/dd'), 'system',to_date('2018/11/17', 'yyyy/mm/dd'));
--Add By QuJianZhong 20181120 利润表增加报表维度 end
/**add by huangjian 2018-11-20     活期利率相关及数据字典 start**/
delete SYS_PAGE_ELEMENT a where a.page_id='AccountCurrentRate001' and a.element_id='AccountCurrentRate001_btn_delete';
insert into SYS_PAGE_ELEMENT values ('AccountCurrentRate001','AccountCurrentRate001_btn_delete','01','01','','账户活期利率删除','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
/**add by huangjian  2018-11-20    活期利率相关及数据字典 end**/

/**ADD                                             2018-11-20                                              end**/ 

/**ADD                                             2018-11-21                                              start**/ 


/**数据字典：增加【节假日_交易市场、是否节假日】 add by wujie on 20181121 start**/
delete from SYS_DATADICT where dict_code='HOLIDAY_TRADE_MARKET';
insert into SYS_DATADICT values ('HOLIDAY_TRADE_MARKET','VAL','节假日_交易市场','','02','system','',to_date('2018/11/21', 'yyyy/mm/dd'), 'system',to_date('2018/11/21', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where dict_code='HOLIDAY_TRADE_MARKET';
insert into SYS_DATADICT_LIST values ('HOLIDAY_TRADE_MARKET','IB','银行间','1','0','system','',to_date('2018/11/21', 'yyyy/mm/dd'), 'system',to_date('2018/11/21', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('HOLIDAY_TRADE_MARKET','SH','上交所','2','0','system','',to_date('2018/11/21', 'yyyy/mm/dd'), 'system',to_date('2018/11/21', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('HOLIDAY_TRADE_MARKET','SZ','深交所','3','0','system','',to_date('2018/11/21', 'yyyy/mm/dd'), 'system',to_date('2018/11/21', 'yyyy/mm/dd'));


delete from SYS_DATADICT where dict_code='IS_HOLIDAY';
insert into SYS_DATADICT values ('IS_HOLIDAY','VAL','是否节假日','','02','system','',to_date('2018/11/21', 'yyyy/mm/dd'), 'system',to_date('2018/11/21', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where dict_code='IS_HOLIDAY';
insert into SYS_DATADICT_LIST values ('IS_HOLIDAY','0','否','1','0','system','',to_date('2018/11/21', 'yyyy/mm/dd'), 'system',to_date('2018/11/21', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('IS_HOLIDAY','1','是','2','0','system','',to_date('2018/11/21', 'yyyy/mm/dd'), 'system',to_date('2018/11/21', 'yyyy/mm/dd'));

/**数据字典：增加【节假日_交易市场、是否节假日】 add by wujie on 20181121 end**/


/**数据字典：期限品种(内部拆借)增加 1年以上 add by wujie on 20181121 start**/
delete from SYS_DATADICT_LIST where dict_code='TERM_TYPE_RESOLUTE' and ITEM_CODE='IB1Y';
insert into SYS_DATADICT_LIST values ('TERM_TYPE_RESOLUTE','IB1Y','1年以上','11','0','system','',to_date('2018/11/21', 'yyyy/mm/dd'), 'system',to_date('2018/11/21', 'yyyy/mm/dd'));
/**数据字典：期限品种(内部拆借)增加 1年以上 add by wujie on 20181121 end**/

/**系统参数：财务报表编制单位 add by QuJianZhong 20181121 start*/
delete from SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'REPORT_ORG';
insert into SYS_PARA values ('VAL','REPORT_ORG','XX银行','01','(null)','财务报表编制单位，各项目自行配置','system','',to_date('2018/11/21', 'yyyy/mm/dd'), 'system',to_date('2018/11/21', 'yyyy/mm/dd'));
/**系统参数：财务报表编制单位 add by QuJianZhong 20181121 end*/


--added by wangwei1 报送报表:非法人财务数据报送  20181121 start
DELETE FROM SYS_MENU WHERE MENU_ID='VALSTATEACCOUNT_001_003';
Insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('VALSTATEACCOUNT_001_003','VAL',null,'VALSTATEACCOUNT_001',null,3,'02','报送报表','system',null,SYSDATE,'system',SYSDATE);

DELETE FROM SYS_MENU WHERE MENU_ID='VALSTATEACCOUNT_001_003_001';
Insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('VALSTATEACCOUNT_001_003_001','VAL',null,'VALSTATEACCOUNT_001_003','NonLggalFinancialDataSubReport001',1,'02','非法人财务数据报送','system',null,SYSDATE,'system',SYSDATE);

DELETE FROM SYS_PAGE WHERE PAGE_ID='NonLggalFinancialDataSubReport001';
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('NonLggalFinancialDataSubReport001','VAL','/valuation/accounadjustmanage/financialDataSub/NonLggalFinancialDataSubReport001','/valuation/accounadjustmanage/financialDataSub/NonLggalFinancialDataSubReport001','VALSTATEACCOUNT_001_003_001',null,null,'02','02','非法人财务数据报送','system',null,SYSDATE,'system',SYSDATE);
--added by wangwei1 报送报表:非法人财务数据报送   20181121  end

--added by yangjian 【产品估值表对账】与【产品余额表对账】功能集成   20181121  start
--【产品估值表对账】菜单
delete from sys_page where PAGE_ID = 'ProductValuationReconciliation001';
insert into SYS_PAGE values ('ProductValuationReconciliation001','VAL','/valuation/accounadjustmanage/valuareconcy/ProductValuationReconciliation001','/valuation/accounadjustmanage/valuareconcy/ProductValuationReconciliation001','DAILYOPERATION_001_001_011','','','02','02','产品估值表对账','system','',to_date('2018/11/16', 'yyyy/mm/dd'), 'system',to_date('2018/11/16', 'yyyy/mm/dd'));
delete from sys_menu where PAGE_ID = 'ProductValuationReconciliation001';
insert into SYS_MENU values ('DAILYOPERATION_001_001_011','VAL','','DAILYOPERATION_001_001','ProductValuationReconciliation001','9','02','产品估值表对账','system','',to_date('2018/11/21', 'yyyy/mm/dd'), 'system',to_date('2018/11/21', 'yyyy/mm/dd'));
--【产品余额表对账】菜单
delete from sys_page where PAGE_ID = 'ProductBalanceReconciliation001';
insert into SYS_PAGE values ('ProductBalanceReconciliation001','VAL','/valuation/accounadjustmanage/balancereconcy/ProductBalanceReconciliation001','/valuation/accounadjustmanage/balancereconcy/ProductBalanceReconciliation001','DAILYOPERATION_001_001_012','','','02','02','产品余额表对账','system','',to_date('2018/11/17', 'yyyy/mm/dd'), 'system',to_date('2018/11/17', 'yyyy/mm/dd'));
delete from sys_menu where PAGE_ID = 'ProductBalanceReconciliation001';
insert into SYS_MENU values ('DAILYOPERATION_001_001_012','VAL','','DAILYOPERATION_001_001','ProductBalanceReconciliation001','10','02','产品余额表对账','system','',to_date('2018/11/21', 'yyyy/mm/dd'), 'system',to_date('2018/11/21', 'yyyy/mm/dd'));

--增加区块类型枚举
delete from SYS_DATADICT where dict_code = 'BLOCK_TYPE';
insert into SYS_DATADICT values ('BLOCK_TYPE','VAL','估值对账配置_区块类型','','02','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where dict_code = 'BLOCK_TYPE';
insert into SYS_DATADICT_LIST values ('BLOCK_TYPE','01','科目行','1','0','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BLOCK_TYPE','02','汇总行','2','0','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
--增加对比类型枚举
delete from SYS_DATADICT where dict_code = 'COMPARE_TYPE';
insert into SYS_DATADICT values ('COMPARE_TYPE','VAL','对比类型','','02','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where dict_code = 'COMPARE_TYPE';
insert into SYS_DATADICT_LIST values ('COMPARE_TYPE','C01','本系统对比对方系统','1','0','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMPARE_TYPE','C02','对方系统对比本系统','2','0','system','',to_date('2018/11/14', 'yyyy/mm/dd'), 'system',to_date('2018/11/14', 'yyyy/mm/dd'));
--增加文件类型枚举
delete from SYS_DATADICT_LIST where dict_code = 'FILE_TYPE';
insert into SYS_DATADICT_LIST values ('FILE_TYPE','01','估值表','1','0','system','',to_date('2018/06/26', 'yyyy/mm/dd'), 'system',to_date('2018/06/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FILE_TYPE','02','余额表','2','0','system','',to_date('2018/06/26', 'yyyy/mm/dd'), 'system',to_date('2018/06/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FILE_TYPE','03','操作手册','3','0','system','',to_date('2018/06/26', 'yyyy/mm/dd'), 'system',to_date('2018/06/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FILE_TYPE','04','净值确认下发附件','4','0','system','',to_date('2018/06/26', 'yyyy/mm/dd'), 'system',to_date('2018/06/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FILE_TYPE','05','估值表对账文件','5','0','system','',to_date('2018/06/26', 'yyyy/mm/dd'), 'system',to_date('2018/06/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FILE_TYPE','06','余额表对账文件','6','0','system','',to_date('2018/06/26', 'yyyy/mm/dd'), 'system',to_date('2018/06/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FILE_TYPE','07','估值表对账结果文件-本系统对比对方系统','7','0','system','',to_date('2018/06/26', 'yyyy/mm/dd'), 'system',to_date('2018/06/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FILE_TYPE','08','估值表对账结果文件-对方系统对比本系统','8','0','system','',to_date('2018/06/26', 'yyyy/mm/dd'), 'system',to_date('2018/06/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FILE_TYPE','09','余额表对账结果文件-本系统对比对方系统','9','0','system','',to_date('2018/06/26', 'yyyy/mm/dd'), 'system',to_date('2018/06/26', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('FILE_TYPE','10','余额表对账结果文件-对方系统对比本系统','10','0','system','',to_date('2018/06/26', 'yyyy/mm/dd'), 'system',to_date('2018/06/26', 'yyyy/mm/dd'));
--added by yangjian 【产品估值表对账】与【产品余额表对账】功能集成   20181121  end


/**ADD                                             2018-11-21                                              end**/ 

/** 一般资产联动 added by zhangwei 20181121 start **/
-- GZ-025 : 一般资产联动 需求变更
-- 新增数据字典明细
delete from  SYS_DATADICT_LIST where dict_code = 'BOND_TYPE' and item_code = '035' and item_value = '境外国债';
delete from  SYS_DATADICT_LIST where dict_code = 'BOND_TYPE' and item_code = '036' and item_value = '境外其他债'; 
insert into SYS_DATADICT_LIST values ('BOND_TYPE','035','境外国债','35','0','system','',to_date('2018/7/27', 'yyyy/mm/dd'), 'system',to_date('2018/7/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('BOND_TYPE','036','境外其他债','36','0','system','',to_date('2018/7/28', 'yyyy/mm/dd'), 'system',to_date('2018/7/28', 'yyyy/mm/dd'));
-- 删除 原联动： （其他场外）交易场所 - 债券细分类
delete from SYS_DATADICT_LIST_LINK 
where linked_dict_code = 'VALUATION_TRADE_MARKET' and linked_item_code = '07' and dict_code = 'BOND_TYPE';
-- 添加 新联动：（其他场外）交易场所 - 债券细分类
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','035','VALUATION_TRADE_MARKET','07','system','',to_date('2018/09/08', 'yyyy/mm/dd'), 'system',to_date('2018/09/08', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','036','VALUATION_TRADE_MARKET','07','system','',to_date('2018/09/09', 'yyyy/mm/dd'), 'system',to_date('2018/09/09', 'yyyy/mm/dd'));
/** 一般资产联动 added by zhangwei 20181121 end **/

/**系统参数：估值表批处理是否自动生成净值下发 add by weipeng 20181121 start*/
delete from SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'NET_CONFIRM_GENNERATE';
insert into SYS_PARA values ('VAL','NET_CONFIRM_GENNERATE','Y','01','(null)','估值表批处理自动生成净值下发 N:否 Y(默认):是','system','',to_date('2018/11/21', 'yyyy/mm/dd'), 'system',to_date('2018/11/21', 'yyyy/mm/dd'));
/**系统参数：估值表批处理是否自动生成净值下发 add by weipeng 20181121 end*/

/**ADD                                             2018-11-22                                              start**/ 
/**add by niuqianyu 金融交易查询删除添加流程 start**/
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValFinprodTradeQuery001';

delete from  SYS_WFL_BUSINESS_MODULE where PAGE_ID='ValFinprodTradeQuery001';
insert into SYS_WFL_BUSINESS_MODULE values ('ValFinprodTradeQuery001','ValFinprodTradeQuery001_btn_delete','oneApproval','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where DICT_CODE='VAL_BUSINESS_TYPE' and ITEM_CODE='V07';
insert into SYS_DATADICT_LIST values ('VAL_BUSINESS_TYPE','V07','金融交易查询','7','0','system','',to_date('2018/9/6', 'yyyy/mm/dd'), 'system',to_date('2018/9/6', 'yyyy/mm/dd'));

/**add by niuqianyu 金融交易查询删除添加流程 end**/

/**add by niuqianyu 账套管理修改添加流程 start**/
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValAssetBook002';

delete from  SYS_WFL_BUSINESS_MODULE where PAGE_ID='ValAssetBook002';
insert into SYS_WFL_BUSINESS_MODULE values ('ValAssetBook002','ValAssetBook002_btn_submit','oneApproval','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where DICT_CODE='VAL_BUSINESS_TYPE' and ITEM_CODE='V08';
insert into SYS_DATADICT_LIST values ('VAL_BUSINESS_TYPE','V08','账套管理','8','0','system','',to_date('2018/9/6', 'yyyy/mm/dd'), 'system',to_date('2018/9/6', 'yyyy/mm/dd'));

/**add by niuqianyu 账套管理修改添加流程 end**/

/** 税务参数-参数设置 add by zhangwei 20181122 start **/
-- 页面
delete from SYS_PAGE where page_id = 'TaxScheme006';
insert into SYS_PAGE values ('TaxScheme006','VAL','/valuation/taxparametermanage/taxscheme/TaxScheme006','valuation/taxparametermanage/taxscheme/TaxScheme006','VALPARMANAGE_001_003_001','','','02','02','参数设置','system','',to_date('2018/10/12', 'yyyy/mm/dd'), 'system',to_date('2018/10/12', 'yyyy/mm/dd'));
-- 数据字典
delete from SYS_DATADICT where dict_code = 'SCHEME_TYPE2';
delete from SYS_DATADICT_LIST where dict_code = 'SCHEME_TYPE2';
insert into SYS_DATADICT values ('SCHEME_TYPE2','VAL','税务参数类型','','02','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SCHEME_TYPE2','1','金融商品转让','1','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SCHEME_TYPE2','2','持有期间收益','2','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('SCHEME_TYPE2','3','附加税','3','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
/** 税务参数-参数设置 add by zhangwei 20181122 end **/

/**add by huangjian 账户活期利率查询删除添加流程 start 2018-11-22**/
update SYS_PAGE set is_need_workflow = '01' where page_id = 'AccountCurrentRate001';
delete SYS_PAGE_ELEMENT a where a.page_id='AccountCurrentRate001' and a.element_id='AccountCurrentRate001_btn_delete';
insert into SYS_PAGE_ELEMENT values ('AccountCurrentRate001','AccountCurrentRate001_btn_delete','01','01','','账户活期利率删除','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE='V09';
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('COMMON_BUSINESS_TYPE','V09','账户活期利率',9,'0','system',null,to_timestamp('03-2月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('03-2月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

/**add by huangjian 账户活期利率查询删除添加流程 end 2018-11-22**/

/**add by niuqianyu 账套日期管理修改添加流程 start**/
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValBooksetDateSwitch002';
/**add by niuqianyu 账套日期管理修改添加流程 end**/

/** 账套日期管理编辑按钮 add by zhangwei start 2018-11-22 **/
delete from SYS_PAGE_ELEMENT where page_id = 'ValBooksetDateSwitch001' and element_id = 'ValBooksetDateSwitch001_TBLROW_edit';
insert into SYS_PAGE_ELEMENT values ('ValBooksetDateSwitch001','ValBooksetDateSwitch001_TBLROW_edit','01','01','','编辑','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
/** 账套日期管理编辑按钮 add by zhangwei end 2018-11-22 **/

/**增加画面：资产负债表-明细列表 Add By QuJianZhong start*/
delete from SYS_PAGE where PAGE_ID='AssetLiabilityStatementQuery002';
insert into SYS_PAGE values ('AssetLiabilityStatementQuery002','VAL','/valuation/financialstatement/assetliailitystatement/AssetLiabilityStatementQuery002','valuation/financialstatement/assetliailitystatement/AssetLiabilityStatementQuery002','','','','02','02','资产负债表-明细列表','system','',to_date('2018/11/22', 'yyyy/mm/dd'), 'system',to_date('2018/11/22', 'yyyy/mm/dd'));
/**增加画面：资产负债表-明细列表 Add By QuJianZhong end*/
/**ADD                                             2018-11-22                                              end**/ 

/**ADD                                             2018-11-23                                              start**/ 
/**add by niuqianyu 基金类资产增删改 添加流程 start**/
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValAssetsFundQuery001';
delete from  SYS_WFL_BUSINESS_MODULE where PAGE_ID='ValAssetsFundQuery001';
insert into SYS_WFL_BUSINESS_MODULE values ('ValAssetsFundQuery001','ValAssetsFundQuery001_btn_delete','oneApproval','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where DICT_CODE='VAL_BUSINESS_TYPE' and ITEM_CODE='V10';
insert into SYS_DATADICT_LIST values ('VAL_BUSINESS_TYPE','V10','基金类资产','10','0','system','',to_date('2018/9/6', 'yyyy/mm/dd'), 'system',to_date('2018/9/6', 'yyyy/mm/dd'));

update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValAssetsFundQuery002';
delete from  SYS_WFL_BUSINESS_MODULE where PAGE_ID='ValAssetsFundQuery002';
insert into SYS_WFL_BUSINESS_MODULE values ('ValAssetsFundQuery002','ValAssetsFundQuery002_btn_save','oneApproval','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('ValAssetsFundQuery002','ValAssetsFundQuery002_btn_submit','oneApproval','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
/**add by niuqianyu 基金类资产增删改 添加流程 end**/


/**数据字典：交易方向增加债券借贷涉及的交易方向 add by wujie on 20181123 start**/
delete from SYS_DATADICT_LIST where dict_code='PS' and ITEM_CODE in ('T28','T29','T30','T31','T32','T33','T34','T36','T37');
insert into SYS_DATADICT_LIST values ('PS','T28','融入债券收息','42','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PS','T29','融入债券利息返还','43','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PS','T30','卖空债券结息','44','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PS','T31','融入债券还本','45','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PS','T32','融入债券本金返还','46','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PS','T33','卖空债券还本','47','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PS','T34','融入债券卖出','48','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PS','T36','融入债券购回','50','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('PS','T37','债券借贷本方券交割','51','0','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
/**数据字典：交易方向增加债券借贷涉及的交易方向 add by wujie on 20181123 end**/


/**add by huangjian 账户活期利率查询添加流程 start 2018-11-23**/
update SYS_PAGE set is_need_workflow = '01' where page_id = 'AccountCurrentRate002';
delete SYS_PAGE_ELEMENT a where a.page_id='AccountCurrentRate002' and a.element_id='AccountCurrentRate002_btn_submit';
insert into SYS_PAGE_ELEMENT values ('AccountCurrentRate002','AccountCurrentRate002_btn_submit','01','01','','账户活期利率提交','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

delete from  SYS_WFL_BUSINESS_MODULE where PAGE_ID='AccountCurrentRate001';
delete from  SYS_WFL_BUSINESS_MODULE where PAGE_ID='AccountCurrentRate002';
insert into SYS_WFL_BUSINESS_MODULE values ('AccountCurrentRate002','AccountCurrentRate002_btn_submit','oneApproval','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('AccountCurrentRate002','AccountCurrentRate001_btn_delete','oneApproval','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
/**add by huangjian 账户活期利率查询添加流程 end 2018-11-23**/

/**add by JINMAODONG 界面参数明细关联增加金融产品类型的关联关系，用于帐套管理界面使用  START 2018-11-23**/
DELETE FROM SYS_PAGE_DICTLIST WHERE PAGE_ID IN ('ValAssetBook001','ValAssetBook002') AND DICT_ID = 'FINPROD_TYPE';
insert into SYS_PAGE_DICTLIST values ('ValAssetBook001','FINPROD_TYPE','F16','system','',to_date('2018/11/23', 'yyyy/mm/dd'), 'system',to_date('2018/11/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValAssetBook001','FINPROD_TYPE','F18','system','',to_date('2018/11/23', 'yyyy/mm/dd'), 'system',to_date('2018/11/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValAssetBook001','FINPROD_TYPE','F22','system','',to_date('2018/11/23', 'yyyy/mm/dd'), 'system',to_date('2018/11/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValAssetBook002','FINPROD_TYPE','F16','system','',to_date('2018/11/23', 'yyyy/mm/dd'), 'system',to_date('2018/11/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValAssetBook002','FINPROD_TYPE','F18','system','',to_date('2018/11/23', 'yyyy/mm/dd'), 'system',to_date('2018/11/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('ValAssetBook002','FINPROD_TYPE','F22','system','',to_date('2018/11/23', 'yyyy/mm/dd'), 'system',to_date('2018/11/23', 'yyyy/mm/dd'));
/**add by JINMAODONG 界面参数明细关联增加金融产品类型的关联关系，用于帐套管理界面使用 end 2018-11-23**/
/**ADD                                             2018-11-23                                              end**/ 
/**add by huangjian 存款类资产查询添加流程 start 2018-11-23**/
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValDespositAssetQuery001';
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValDespositAssetQuery002';

delete SYS_PAGE_ELEMENT a where a.page_id='ValDespositAssetQuery001' and a.element_id='ValDespositAssetQuery001_btn_delete';
insert into SYS_PAGE_ELEMENT values ('ValDespositAssetQuery001','ValDespositAssetQuery001_btn_delete','01','01','','存款类资产删除','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

delete SYS_PAGE_ELEMENT a where a.page_id='ValDespositAssetQuery002' and a.element_id='ValDespositAssetQuery002_btn_submit';
insert into SYS_PAGE_ELEMENT values ('ValDespositAssetQuery002','ValDespositAssetQuery002_btn_submit','01','01','','存款类资产提交','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE='V11';
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('COMMON_BUSINESS_TYPE','V11','存款类资产',11,'0','system',null,to_timestamp('03-2月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('03-2月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));


delete from  SYS_WFL_BUSINESS_MODULE where PAGE_ID='ValDespositAssetQuery002';
insert into SYS_WFL_BUSINESS_MODULE values ('ValDespositAssetQuery002','ValDespositAssetQuery002_btn_submit','oneApproval','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('ValDespositAssetQuery002','ValDespositAssetQuery001_btn_delete','oneApproval','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
/**add by huangjian 存款类资产查询添加流程 end 2018-11-23**/

/**ADD                                             2018-11-28                                              start**/ 
/**add by weipeng 对账方案配置余额表列指标数据字典变更 start 2018-11-28**/
delete from SYS_DATADICT_LIST where DICT_CODE='MY_YEB_COL_INDEX'; 
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','B','科目代码','1','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','C','科目名称','2','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','D','币种（原币）','3','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','E','期初余额方向（原币）','4','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','F','期初余额（原币）','5','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','G','本期借方发生额（原币）','6','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','H','本期贷方发生额（原币）','7','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','I','期末余额方向（原币）','8','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','J','期末余额（原币）','9','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','K','期初余额方向（本位币）','10','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','L','期初余额（本位币）','11','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','M','本期借方发生额（本位币）','12','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','N','本期贷方发生额（本位币）','13','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','O','期末余额方向（本位币）','14','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_YEB_COL_INDEX','P','期末余额（本位币）','15','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
/**add by weipeng 对账方案配置余额表列指标数据字典变更 end 2018-11-28**/
/**add by lishicheng 影子定价方案   以及走流程脚本 start 2018-11-28**/
delete from SYS_PAGE where PAGE_ID='ShadowPriceScheme001';
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ShadowPriceScheme001','VAL','/valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme001','valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme001','VALPARMANAGE_001_002_004',null,null,'02','02','影子定价方案','system',null,to_timestamp('22-11月-18 02.09.53.241000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('22-11月-18 02.09.59.360000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
delete from SYS_PAGE where PAGE_ID='ShadowPriceScheme002';
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ShadowPriceScheme002','VAL','/valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme002','valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme002','VALPARMANAGE_001_002_004',null,null,'02','02','新建影子定价方案','system',null,to_timestamp('22-11月-18 07.16.19.526000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('22-11月-18 07.16.25.525000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
delete from SYS_PAGE where PAGE_ID='ShadowPriceScheme003';
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ShadowPriceScheme003','VAL','/valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme003','/valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme003','VALPARMANAGE_001_002_004',null,null,'02','02','新建影子定价方案明细','system',null,to_timestamp('24-11月-18 06.15.46.949000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-11月-18 06.15.52.661000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
delete from SYS_MENU where MENU_ID='VALPARMANAGE_001_002_004';
insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('VALPARMANAGE_001_002_004','VAL',null,'VALPARMANAGE_001_002','ShadowPriceScheme001',1,'02','影子定价方案','system',null,to_timestamp('22-11月-18 01.57.50.226000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('22-11月-18 01.57.56.775000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE='V12'; 
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('COMMON_BUSINESS_TYPE','V12','影子定价方案',13,'0','system',null,to_timestamp('28-11月-18 03.14.51.785000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('28-11月-18 03.14.57.878000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
--影子定价方案新建复制走流程
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ShadowPriceScheme002';
delete from sys_wfl_business_module where PAGE_ID = 'ShadowPriceScheme002' and ELEMENT_ID ='ShadowPriceScheme002_btn_submit';
insert into sys_wfl_business_module values ('ShadowPriceScheme002', 'ShadowPriceScheme002_btn_submit', 'oneApproval2', 'system', '0001', '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');
--影子定价方案修改走流程
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ShadowPriceScheme002';
delete from sys_wfl_business_module where PAGE_ID = 'ShadowPriceScheme002' and ELEMENT_ID ='ShadowPriceScheme002_btn_save';
insert into sys_wfl_business_module values ('ShadowPriceScheme002', 'ShadowPriceScheme002_btn_save', 'oneApproval2', 'system', '0001', '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');
--影子定价方案明细修改走流程
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ShadowPriceScheme003';
delete from sys_wfl_business_module where PAGE_ID = 'ShadowPriceScheme003' and ELEMENT_ID ='ShadowPriceScheme003_btn_save';
insert into sys_wfl_business_module values ('ShadowPriceScheme003', 'ShadowPriceScheme003_btn_save', 'oneApproval2', 'system','0001', '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');
--影子定价方案明细新建复制走流程
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ShadowPriceScheme003';
delete from sys_wfl_business_module where PAGE_ID = 'ShadowPriceScheme003' and ELEMENT_ID ='ShadowPriceScheme003_btn_submit';
insert into sys_wfl_business_module values ('ShadowPriceScheme003', 'ShadowPriceScheme003_btn_submit', 'oneApproval2', 'system', '0001', '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');
--影子定价方案明细删除走流程
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ShadowPriceScheme001';
delete from sys_wfl_business_module where PAGE_ID = 'ShadowPriceScheme001' and ELEMENT_ID ='ShadowPriceScheme001_btn_delete';
insert into sys_wfl_business_module values ('ShadowPriceScheme001', 'ShadowPriceScheme001_btn_delete', 'oneApproval2', 'system', '0001', '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');
/**add by lishicheng 影子定价方案   end  2018-11-28**/


/**add by jinmaodong 框架升级到2.0.7.6.10附带的脚本 start 2018-11-28**/
delete from SYS_DATADICT_LIST where DICT_CODE = 'COMMON_BUSINESS_TYPE' AND ITEM_CODE ='S09';
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','S09','系统参数管理','9','0','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'SysParaSet002' AND ELEMENT_ID ='SysParaSet002_btn_save';
insert into SYS_WFL_BUSINESS_MODULE values ('SysParaSet002','SysParaSet002_btn_save','oneApproval','system','0001',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'SysParaSet002' AND ELEMENT_ID ='SysParaSet002_btn_submit';
insert into SYS_WFL_BUSINESS_MODULE values ('SysParaSet002','SysParaSet002_btn_submit','oneApproval','system','0001',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

delete from SYS_PAGE where PAGE_ID = 'SysParaSet002';
insert into SYS_PAGE values ('SysParaSet002','SYS','/system/para/SysParaSet002','system/para/SysParaSet002','SYSTEM_001_010','','','02','01','系统参数修改','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

delete from SYS_PARA where PARA_CODE = 'export_excel_type' ;
insert into SYS_PARA values ('SYS','export_excel_type','01','01','数据字典（是否）','兼容选择导出excel类型（01:xlsx，02:xls）','system','',to_date('2018/11/15', 'yyyy/mm/dd'), 'system',to_date('2018/11/15', 'yyyy/mm/dd'));
/**add by jinmaodong 框架升级到2.0.7.6.10附带的脚本 end 2018-11-28**/

/**ADD                                             2018-11-28                                              end**/ 

/**ADD                                             2018-11-29                                              start**/ 
/**add by lishicheng 影子定价方案删除走流程脚本 start 2018-11-29**/
--影子定价方案删除走流程
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ShadowPriceScheme001';
delete from sys_wfl_business_module where PAGE_ID = 'ShadowPriceScheme001' and ELEMENT_ID ='ShadowPriceScheme001_btn_dele';
insert into sys_wfl_business_module values ('ShadowPriceScheme001', 'ShadowPriceScheme001_btn_dele', 'oneApproval2', 'system', '0001', '03-2月 -18 12.00.00.000000 上午', 'system', '03-2月 -18 12.00.00.000000 上午');
/**add by lishicheng 影子定价方案删除走流程脚本  end 2018-11-29**/

/** Add By QuJianZhong 20181129 账套管理删除支持流程 satrt */
delete from SYS_PAGE where page_id = 'ValAssetBook005';
insert into SYS_PAGE values ('ValAssetBook005','VAL','/valuation/accounadjustmanage/assetbook/ValAssetBook005','valuation/accounadjustmanage/assetbook/ValAssetBook005','VALPARMANAGE_001_001_001','','','02','01','删除账套','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
delete from SYS_WFL_BUSINESS_MODULE where page_id='ValAssetBook005' and ELEMENT_ID='ValAssetBook001_btn_delete';
insert into SYS_WFL_BUSINESS_MODULE values ('ValAssetBook005','ValAssetBook001_btn_delete','oneApproval2','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
/* Add By QuJianZhong 20181129 账套管理删除支持流程 end */

/**ADD                                             2018-11-29                                              end**/

/**ADD                                             2018-11-30                                              start**/
/** Add By QuJianZhong 20181130 金融交易删除流程 start*/
/** 撤销原流程配置*/
update SYS_PAGE set is_need_workflow = '02' where page_id = 'ValFinprodTradeQuery001';
delete from  SYS_WFL_BUSINESS_MODULE where PAGE_ID='ValFinprodTradeQuery001' and ELEMENT_ID='ValFinprodTradeQuery001_btn_delete';
/** 新增流程配置*/
delete from SYS_PAGE where page_id='ValFinprodTradeQuery002';
insert into SYS_PAGE values ('ValFinprodTradeQuery002','VAL','/valuation/trademanage/finprodtradequery/ValFinprodTradeQuery002','valuation/trademanage/finprodtradequery/ValFinprodTradeQuery002','VALINFOSERVICE_001_002','','','02','01','金融交易删除','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
delete from  SYS_WFL_BUSINESS_MODULE where PAGE_ID='ValFinprodTradeQuery002' and ELEMENT_ID='ValFinprodTradeQuery001_btn_delete';
insert into SYS_WFL_BUSINESS_MODULE values ('ValFinprodTradeQuery002','ValFinprodTradeQuery001_btn_delete','oneApproval','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
/** Add By QuJianZhong 20181130 金融交易删除流程 end*/

/**add by qujianzhong 债券类资产查询界面删除流程 start**/
/** 撤销原流程配置*/
update SYS_PAGE set is_need_workflow='02' where page_id='ValBondassetquery001';
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID='ValBondassetquery001' and ELEMENT_ID='ValBondassetquery001_btn_delete';
/** 新增流程配置*/
delete from SYS_PAGE where page_id='ValBondassetquery003';
insert into SYS_PAGE values ('ValBondassetquery003','VAL','/valuation/assets/bondasset/ValBondassetquery003','valuation/assets/bondasset/ValBondassetquery003','VALINFOSERVICE_001_003_002','','','02','01','删除债券类资产','system','',to_date('2018/2/5', 'yyyy/mm/dd'), 'system',to_date('2018/2/5', 'yyyy/mm/dd'));
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID='ValBondassetquery003' and ELEMENT_ID='ValBondassetquery001_btn_delete';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('ValBondassetquery003','ValBondassetquery001_btn_delete','oneApproval','system','0001',to_timestamp('03-2月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('03-2月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
/**add by qujianzhong 债券类资产查询界面删除流程 end**/



/** add by zhangwei 估值方法方案添加流程 20181130 start**/
--增加流程业务类型
delete from SYS_DATADICT_LIST where dict_code = 'VAL_BUSINESS_TYPE' and item_code = 'V13' and item_value = '估值方法方案';
insert into SYS_DATADICT_LIST values ('VAL_BUSINESS_TYPE','V13','估值方法方案','13','0','system','',to_date('2018/9/6', 'yyyy/mm/dd'), 'system',to_date('2018/9/6', 'yyyy/mm/dd'));

-- 页面支持流程：估值方法方案 - 估值方案
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValuationMethodScheme002';

-- 业务流程模板： 估值方法方案 - 估值方案新建/修改/删除
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'ValuationMethodScheme002' and element_id = 'ValuationMethodScheme002_btn_submit';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'ValuationMethodScheme002' and element_id = 'ValuationMethodScheme002_btn_save';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'ValuationMethodScheme002' and element_id = 'ValuationMethodScheme001_btn_deleteScheme';
insert into SYS_WFL_BUSINESS_MODULE values ('ValuationMethodScheme002','ValuationMethodScheme002_btn_submit','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('ValuationMethodScheme002','ValuationMethodScheme002_btn_save','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('ValuationMethodScheme002','ValuationMethodScheme001_btn_deleteScheme','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));

-- 页面支持流程：估值方法方案 - 一般资产
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValuationMethodScheme003';

-- 业务流程模板： 估值方法方案 - 一般资产新建/修改/删除
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'ValuationMethodScheme003' and element_id = 'ValuationMethodScheme003_btn_submit';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'ValuationMethodScheme003' and element_id = 'ValuationMethodScheme003_btn_save';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'ValuationMethodScheme003' and element_id = 'ValuationMethodScheme003_btn_delete';
insert into SYS_WFL_BUSINESS_MODULE values ('ValuationMethodScheme003','ValuationMethodScheme003_btn_submit','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('ValuationMethodScheme003','ValuationMethodScheme003_btn_save','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('ValuationMethodScheme003','ValuationMethodScheme003_btn_delete','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));

-- 页面支持流程：估值方法方案 - 特殊资产
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValuationMethodScheme004';

-- 业务流程模板： 估值方法方案 - 特殊资产新建/修改/删除
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'ValuationMethodScheme004' and element_id = 'ValuationMethodScheme004_btn_submit';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'ValuationMethodScheme004' and element_id = 'ValuationMethodScheme004_btn_save';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'ValuationMethodScheme004' and element_id = 'ValuationMethodScheme004_btn_delete';
insert into SYS_WFL_BUSINESS_MODULE values ('ValuationMethodScheme004','ValuationMethodScheme004_btn_submit','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('ValuationMethodScheme004','ValuationMethodScheme004_btn_save','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('ValuationMethodScheme004','ValuationMethodScheme004_btn_delete','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
/** add by zhangwei 估值方法方案添加流程  20181130 end **/

/** 系统参数：对账文件上传功能目录 add by zhangwei 20181201 start**/
insert into SYS_PARA values ('VAL','FILEUPLOAD_ACCOUNT_CHECK_PATH','OUTPUT/ZX/TF','01','(null)','文件上传-对账文件上传功能目录','system','',to_date('2018/11/23', 'yyyy/mm/dd'), 'system',to_date('2018/11/23', 'yyyy/mm/dd'));
/** 系统参数：对账文件上传功能目录 add by zhangwei 20181201 end**/

/**ADD                                             2018-11-30                                              end**/
/**ADD                                             2018-12-04                                              begin**/

/**[SFTP登录方式修改，增加秘钥登录]。  add by yangjian 2018-12-04  start**/
--增加系统参数-【SFTP登录方式】；
delete from SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'SFTP_LOGIN_TYPE';
insert into SYS_PARA values ('VAL','SFTP_LOGIN_TYPE','01','01','(null)','SFTP登录方式：01:密码登录，02:私钥登录；','system','',to_date('2018/11/23', 'yyyy/mm/dd'), 'system',to_date('2018/11/23', 'yyyy/mm/dd'));
/**[SFTP登录方式修改，增加秘钥登录]。  add by yangjian 2018-12-04  end**/



/** add by zhangwei 税务方案添加流程 20181204 start**/
--增加流程业务类型
delete from SYS_DATADICT_LIST where dict_code = 'VAL_BUSINESS_TYPE' and item_code = 'V14' and item_value = '税务方案';
insert into SYS_DATADICT_LIST values ('VAL_BUSINESS_TYPE','V14','税务方案','14','0','system','',to_date('2018/9/7', 'yyyy/mm/dd'), 'system',to_date('2018/9/7', 'yyyy/mm/dd'));

-- 页面支持流程：税务方案 - 税务方案
update SYS_PAGE set is_need_workflow = '01' where page_id = 'TaxScheme002';

-- 业务流程模板： 税务方案 - 税务方案 新建/修改/删除
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme002' and element_id = 'TaxScheme002_btn_submit';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme002' and element_id = 'TaxScheme002_btn_save';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme002' and element_id = 'TaxScheme001_btn_deleteScheme';
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme002','TaxScheme002_btn_submit','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme002','TaxScheme002_btn_save','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme002','TaxScheme001_btn_deleteScheme','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));

-- 页面支持流程：税务方案 - 增值税则表
update SYS_PAGE set is_need_workflow = '01' where page_id = 'TaxScheme003';

-- 业务流程模板： 税务方案 - 增值税则表 新建/修改/删除
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme003' and element_id = 'TaxScheme003_btn_submit';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme003' and element_id = 'TaxScheme003_btn_save';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme003' and element_id = 'TaxScheme001_btn_vatTax_delete';
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme003','TaxScheme003_btn_submit','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme003','TaxScheme003_btn_save','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme003','TaxScheme001_btn_vatTax_delete','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));

-- 页面支持流程：税务方案 - 附加税则表
update SYS_PAGE set is_need_workflow = '01' where page_id = 'TaxScheme004';

-- 业务流程模板： 税务方案 - 附加税则表 新建/修改/删除
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme004' and element_id = 'TaxScheme004_btn_submit';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme004' and element_id = 'TaxScheme004_btn_save';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme004' and element_id = 'TaxScheme001_btn_adtTax_delete';
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme004','TaxScheme004_btn_submit','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme004','TaxScheme004_btn_save','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme004','TaxScheme001_btn_adtTax_delete','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));

-- 页面支持流程：税务方案 - 资产应税信息
update SYS_PAGE set is_need_workflow = '01' where page_id = 'TaxScheme005';

-- 业务流程模板： 税务方案 - 资产应税信息 新建/修改/删除
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme005' and element_id = 'TaxScheme005_btn_submit';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme005' and element_id = 'TaxScheme005_btn_save';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme005' and element_id = 'TaxScheme001_btn_taxable_delete';
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme005','TaxScheme005_btn_submit','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme005','TaxScheme005_btn_save','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme005','TaxScheme001_btn_taxable_delete','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));

-- 页面支持流程：税务方案 - 参数设置
update SYS_PAGE set is_need_workflow = '01' where page_id = 'TaxScheme006';

-- 业务流程模板： 税务方案 - 参数设置 新建/修改/删除
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme006' and element_id = 'TaxScheme006_btn_submit';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme006' and element_id = 'TaxScheme006_btn_save';
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TaxScheme006' and element_id = 'TaxScheme001_btn_config_delete';
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme006','TaxScheme006_btn_submit','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme006','TaxScheme006_btn_save','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('TaxScheme006','TaxScheme001_btn_config_delete','twoApproval','system','',to_date('2018/2/6', 'yyyy/mm/dd'), 'system',to_date('2018/2/6', 'yyyy/mm/dd'));
/** add by zhangwei 税务方案添加流程  20181204 end **/

/**add by weipeng 影子定价方案界面新增设为默认按钮，对应工作流脚本 start**/
delete from sys_wfl_business_module where PAGE_ID = 'ShadowPriceScheme002' and ELEMENT_ID ='ShadowPriceScheme001_btn_modify';
insert into sys_wfl_business_module values ('ShadowPriceScheme002', 'ShadowPriceScheme001_btn_modify', 'oneApproval2', 'system', '0001', sysdate, 'system', sysdate);
/**add by weipeng 影子定价方案界面新增设为默认按钮，对应工作流脚本 end**/


/**ADD                                             2018-12-04                                              end**/
/**ADD                                             2018-12-07                                              begin**/

/**                        add  2018-12-07  start                                    **/ 
/** 提交人：杨剑  日期：2018-12-07 start**/
update sys_batch_task set task_pri = '38' where task_id = '25' and bean_name = 'moneyFundDealCountingDrawingTask';
/** 提交人：杨剑  日期：2018-12-07 end**/

/**                        add  2018-12-07  end                                    **/ 
/**ADD                                             2018-12-07                                              end**/

/**ADD                                             2018-12-10                                              begin**/
/**                        add  2018-12-10  start                                    **/ 
/** 提交人：代月  日期：2018-12-10 start**/
delete from SYS_DATADICT_LIST where DICT_CODE = 'TAX_ASSET_TYPE' and ITEM_CODE = 'J01';
insert into SYS_DATADICT_LIST values ('TAX_ASSET_TYPE','J01','债券借贷-融出','63','0','system','',to_date('2018/8/15', 'yyyy/mm/dd'), 'system',to_date('2018/8/15', 'yyyy/mm/dd'));
/** 提交人：代月  日期：2018-12-10 end**/
/**                        add  2018-12-10  end                                    **/ 
/**ADD                                             2018-12-10                                              end**/


/**                        add  2018-12-11  start                                    **/ 
/** 提交人：张阳飞  日期：2018-12-11 start**/
--债券估值行情界面增加功能：增删改 
--债券估值行情界面
delete from SYS_PAGE where page_id in('ValBondValuationInfo001','ValBondValuationInfo003');
insert into SYS_PAGE values ('ValBondValuationInfo001','VAL','/valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo001','valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo001','VALINFOSERVICE_001_004_001','','','02','01','债券估值行情','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValBondValuationInfo003','VAL','/valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo003','valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo003','VALINFOSERVICE_001_004_001','','','02','01','新建债券估值行情','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
--债券估值行情界面元素：新建、删除
delete from SYS_PAGE_ELEMENT where page_id = 'ValBondValuationInfo001' and element_id in('ValBondValuationInfo001_btn_add','ValBondValuationInfo001_btn_delete');
insert into SYS_PAGE_ELEMENT values ('ValBondValuationInfo001','ValBondValuationInfo001_btn_add','01','01','','新建','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('ValBondValuationInfo001','ValBondValuationInfo001_btn_delete','01','01','','删除','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
--债券估值行情流程
DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE page_id in ('ValBondValuationInfo003','ValBondValuationInfo001');
insert into SYS_WFL_BUSINESS_MODULE values ('ValBondValuationInfo001','ValBondValuationInfo001_btn_delete','oneApproval','system','',to_date('2018/2/8', 'yyyy/mm/dd'), 'system',to_date('2018/2/8', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('ValBondValuationInfo003','ValBondValuationInfo003_btn_submit','oneApproval','system','',to_date('2018/2/8', 'yyyy/mm/dd'), 'system',to_date('2018/2/8', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('ValBondValuationInfo003','ValBondValuationInfo003_btn_save','oneApproval','system','',to_date('2018/2/8', 'yyyy/mm/dd'), 'system',to_date('2018/2/8', 'yyyy/mm/dd'));
--债券估值行情数据字典
DELETE FROM SYS_DATADICT_LIST where dict_code = 'VAL_BUSINESS_TYPE' and item_code = 'V16';
INSERT INTO SYS_DATADICT_LIST VALUES ('VAL_BUSINESS_TYPE','V16','债券估值行情','16','0','system','',to_date('2018/12/7', 'yyyy/mm/dd'), 'system',to_date('2018/12/7', 'yyyy/mm/dd'));
DELETE FROM SYS_DATADICT_LIST where dict_code = 'VALUATION_SOURCE' and item_code = 'EXP';
insert into SYS_DATADICT_LIST values ('VALUATION_SOURCE','EXP','专家法(EXP)','4','0','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));
/** 提交人：张阳飞  日期：2018-12-11 end**/

/** 提交人：黄健 日期：2018-12-11 start**/
delete SYS_WFL_BUSINESS_MODULE where page_id ='ValAssetsAmortizeSet002';
insert into SYS_WFL_BUSINESS_MODULE values ('ValAssetsAmortizeSet002','ValAssetsAmortizeSet001_btn_delete','oneApproval','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('ValAssetsAmortizeSet002','ValAssetsAmortizeSet002_btn_submit','oneApproval','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));

delete SYS_PAGE_ELEMENT where page_id in ('ValAssetsAmortizeSet001','ValAssetsAmortizeSet002');
insert into SYS_PAGE_ELEMENT values ('ValAssetsAmortizeSet001','ValAssetsAmortizeSet001_btn_search','01','01','','资产摊销查询','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('ValAssetsAmortizeSet001','ValAssetsAmortizeSet001_btn_export','01','01','','资产摊销导出','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('ValAssetsAmortizeSet001','ValAssetsAmortizeSet001_btn_delete','01','01','','资产摊销删除','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('ValAssetsAmortizeSet001','ValAssetsAmortizeSet001_btn_add','01','01','','资产摊销新建','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('ValAssetsAmortizeSet002','ValAssetsAmortizeSet002_btn_submit','01','01','','资产摊销提交','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));

delete SYS_PAGE where page_id in ('ValAssetsAmortizeSet001','ValAssetsAmortizeSet002');
insert into SYS_PAGE values ('ValAssetsAmortizeSet001','VAL','/valuation/assetsamortize/ValAssetsAmortizeSet001','valuation/assetsamortize/ValAssetsAmortizeSet001','VALPARMANAGE_001_005_001','','','02','01','资产摊销设置','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValAssetsAmortizeSet002','VAL','/valuation/assetsamortize/ValAssetsAmortizeSet002','valuation/assetsamortize/ValAssetsAmortizeSet002','VALPARMANAGE_001_005_001','','','02','01','资产摊销新建','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));

delete SYS_MENU where MENU_ID='VALPARMANAGE_001_005';
delete SYS_MENU where MENU_ID='VALPARMANAGE_001_005_001';
insert into SYS_MENU values ('VALPARMANAGE_001_005','VAL','','VALPARMANAGE_001','','5','','摊销参数','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));
insert into SYS_MENU values ('VALPARMANAGE_001_005_001','VAL','','VALPARMANAGE_001_005','ValAssetsAmortizeSet001','1','02','资产摊销设置','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE='V15';
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V15','资产摊销设置','15','0','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));

delete SYS_DATADICT where DICT_CODE='RECAL_TYPE';
insert into SYS_DATADICT values ('RECAL_TYPE','VAL','重算方式','','02','system','',to_date('2018/12/4', 'yyyy/mm/dd'), 'system',to_date('2018/12/4', 'yyyy/mm/dd'));
delete SYS_DATADICT_LIST where DICT_CODE='RECAL_TYPE';
insert into SYS_DATADICT_LIST values ('RECAL_TYPE','01','重算','1','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RECAL_TYPE','02','不重算','2','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('RECAL_TYPE','03','指定实际利率','3','0','system','',to_date('2018/10/09', 'yyyy/mm/dd'), 'system',to_date('2018/10/09', 'yyyy/mm/dd'));
/** 提交人：黄健 日期：2018-12-11 end**/

/** 提交人：张伟 日期：2018-12-11 start**/
--工作流业务流程 
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V01';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V02';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V03';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V04';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V05';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V06';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V07';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V08';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V09';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V10';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V11';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V12';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V13';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V14';
delete from SYS_DATADICT_LIST where dict_code = 'COMMON_BUSINESS_TYPE' and item_code = 'V15';
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V01','账套日期','1','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V02','净值下发确认','2','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V03','手工凭证','3','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V04','计划类资产净值信息','4','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V05','债券重分类','5','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V06','债券类资产信息','6','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V07','金融交易删除','7','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V08','账套管理','8','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V09','账户活期利率','9','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V10','基金类资产','10','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V11','存款类资产','11','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V12','影子定价方案','12','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V13','估值方法方案','13','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V14','税务方案','14','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V15','资产摊销设置','15','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));
--工作流估值业务流程 去除 VAL_BUSINESS_TYPE
delete from SYS_DATADICT_LIST where dict_code = 'VAL_BUSINESS_TYPE' and item_code = 'V01';
delete from SYS_DATADICT_LIST where dict_code = 'VAL_BUSINESS_TYPE' and item_code = 'V07';
delete from SYS_DATADICT_LIST where dict_code = 'VAL_BUSINESS_TYPE' and item_code = 'V08';
delete from SYS_DATADICT_LIST where dict_code = 'VAL_BUSINESS_TYPE' and item_code = 'V10';
delete from SYS_DATADICT_LIST where dict_code = 'VAL_BUSINESS_TYPE' and item_code = 'V13';
delete from SYS_DATADICT_LIST where dict_code = 'VAL_BUSINESS_TYPE' and item_code = 'V16';
/** 提交人：张伟 日期：2018-12-11 end**/
/**                        add  2018-12-11  end                                    **/ 


/**                        add  2018-12-12  start                                    **/ 
/** 提交人：张阳飞 日期：2018-12-12 start**/
--债券估值行情删除流程
delete from SYS_WFL_BUSINESS_MODULE where page_id='ValBondValuationInfo003' and element_id='ValBondValuationInfo001_btn_delete';
INSERT INTO SYS_WFL_BUSINESS_MODULE VALUES ('ValBondValuationInfo003','ValBondValuationInfo001_btn_delete','oneApproval','system','',to_date('2018/2/8', 'yyyy/mm/dd'), 'system',to_date('2018/2/8', 'yyyy/mm/dd'));
/** 提交人：张阳飞 日期：2018-12-12 end**/

/** 提交人：牛倩玉 日期：2018-12-12 start**/
delete from SYS_PAGE where PAGE_ID = 'BondLend001';
insert into SYS_PAGE values ('ValBondLend001','VAL','/valuation/trademanage/investtradequery/bondlend/ValBondLend001','valuation/trademanage/investtradequery/bondlend/ValBondLend001','VALINFOSERVICE_001_005','','','02','02','查看债券借贷交易','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
/** 提交人：牛倩玉 日期：2018-12-12 end**/
/**                        add  2018-12-12  end                                    **/ 


/**                        add  2018-12-14  start                                    **/ 
/** 提交人：王之昂 日期：2018-12-14 start**/
--数据字典表，估值市价增加专家法
delete from SYS_DATADICT_LIST where dict_code = 'VALUATION_PRICE' and ITEM_CODE = 'EXP';
insert into SYS_DATADICT_LIST values ('VALUATION_PRICE','EXP','专家法','11','0','system','',to_date('2018/12/5', 'yyyy/mm/dd'), 'system',to_date('2018/12/5', 'yyyy/mm/dd'));

--数据字典明细关联表，估值交易市场增加关联专家法
delete from SYS_DATADICT_LIST_LINK where dict_code = 'VALUATION_PRICE' and LINKED_DICT_CODE = 'VALUATION_TRADE_MARKET' and item_code = 'EXP' AND LINKED_ITEM_CODE in('01','03','04','05','07');
insert into SYS_DATADICT_LIST_LINK values ('VALUATION_PRICE','EXP','VALUATION_TRADE_MARKET','01','system','',to_date('2018/12/05', 'yyyy/mm/dd'), 'system',to_date('2018/12/05', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('VALUATION_PRICE','EXP','VALUATION_TRADE_MARKET','03','system','',to_date('2018/12/05', 'yyyy/mm/dd'), 'system',to_date('2018/12/05', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('VALUATION_PRICE','EXP','VALUATION_TRADE_MARKET','04','system','',to_date('2018/12/05', 'yyyy/mm/dd'), 'system',to_date('2018/12/05', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('VALUATION_PRICE','EXP','VALUATION_TRADE_MARKET','05','system','',to_date('2018/12/05', 'yyyy/mm/dd'), 'system',to_date('2018/12/05', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('VALUATION_PRICE','EXP','VALUATION_TRADE_MARKET','07','system','',to_date('2018/12/05', 'yyyy/mm/dd'), 'system',to_date('2018/12/05', 'yyyy/mm/dd'));
/** 提交人：王之昂 日期：2018-12-14 end**/

--框架升级至2.7.7 added by wangzhiang 2018-12-14 start
delete from SYS_PARA where para_code = 'date_select_allow_single';
insert into SYS_PARA values ('SYS','date_select_allow_single','02','01','数据字典(是否）','日期区间控件是否默认支持选取单边(01:是,02:否)','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
--框架升级至2.7.7 added by wangzhiang 2018-12-14 end
/**                        add  2018-12-14  end                                    **/ 

/**                        add  2018-12-15  start                                    **/ 
/** 提交人：王之昂 增加 零息/贴现债全价取值方式 日期：2018-12-15 start**/
delete from SYS_PARA where para_code = 'PRICE_CAL_TYPE';
insert into SYS_PARA values ('VAL','PRICE_CAL_TYPE','01','01','(null)','零息/贴现债全价取值方式：01:市价净价加计算出利息，02:市价全价','system','',to_date('2018/12/14', 'yyyy/mm/dd'), 'system',to_date('2018/12/14', 'yyyy/mm/dd'));
/** 提交人：王之昂 增加 零息/贴现债全价取值方式 日期：2018-12-15 end**/
/**                        add  2018-12-15  end                                    **/ 


/**                        add  2018-12-17  start                                    **/ 
/**数据字典：【指令类型】增加数据项 add by wujie on 20181217 start**/
delete from SYS_DATADICT_LIST where dict_code = 'ORDER_TYPE' and item_code in ('N1108','2727','2728','2729','2730');
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N1108','拆放同业（同业借款）中间收息','109','0','system','',to_date('2018/12/17', 'yyyy/mm/dd'), 'system',to_date('2018/12/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','2727','资金收款-同业借款买入','110','0','system','',to_date('2018/12/17', 'yyyy/mm/dd'), 'system',to_date('2018/12/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','2728','资金收款-同业借款卖出','111','0','system','',to_date('2018/12/17', 'yyyy/mm/dd'), 'system',to_date('2018/12/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','2729','资金收款-拆入起息','112','0','system','',to_date('2018/12/17', 'yyyy/mm/dd'), 'system',to_date('2018/12/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','2730','资金划款-拆入到期','113','0','system','',to_date('2018/12/17', 'yyyy/mm/dd'), 'system',to_date('2018/12/17', 'yyyy/mm/dd'));
/**数据字典：【指令类型】增加数据项 add by wujie on 20181217 end**/
/**                        add  2018-12-17  end                                    **/ 


/**                        add  2018-12-18  start                                    **/ 
/** 提交人：张伟  税务方案搜素栏权限要素 日期：2018-12-18 start**/
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_vatTax_add';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_vatTax_delete';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_vatTax_export';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_vatTax_search';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_adtTax_add';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_adtTax_delete';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_adtTax_export';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_adtTax_search';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_taxable_add';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_taxable_delete';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_taxable_export';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_taxable_search';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_config_add';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_config_delete';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_config_export';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_config_search';
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_vatTax_add','01','01','','增值税则表新建','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_vatTax_delete','01','01','','增值税则表删除','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_vatTax_export','01','01','','增值税则表导出','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_vatTax_search','01','01','','增值税则表查询','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_adtTax_add','01','01','','附加税则表新建','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_adtTax_delete','01','01','','附加税则表删除','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_adtTax_export','01','01','','附加税则表导出','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_adtTax_search','01','01','','附加税则表查询','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_taxable_add','01','01','','资产应税信息新建','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_taxable_delete','01','01','','资产应税信息删除','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_taxable_export','01','01','','资产应税信息导出','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_taxable_search','01','01','','资产应税信息查询','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_config_add','01','01','','参数设置新建','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_config_delete','01','01','','参数设置删除','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_config_export','01','01','','参数设置导出','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_config_search','01','01','','参数设置查询','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_TBLROW_taxable_edit';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_TBLROW_taxable_delete';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_TBLROW_taxable_copy';

delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_add_scheme';
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_add_scheme','01','01','','税务方案新建','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_vatTax_edit';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_adtTax_edit';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_taxable_edit';
delete from SYS_PAGE_ELEMENT where page_id = 'TaxScheme001' and element_id = 'TaxScheme001_btn_config_edit';
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_vatTax_edit','01','01','','增值税则表编辑','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_adtTax_edit','01','01','','附加税则表编辑','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_taxable_edit','01','01','','资产应税信息编辑','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('TaxScheme001','TaxScheme001_btn_config_edit','01','01','','参数设置编辑','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));

/** 提交人：张伟  税务方案搜素栏权限要素 日期：2018-12-18 end**/

/**                        add  2018-12-18  end                                    **/ 


/**                        add  2018-12-19  start                                    **/ 
/**提交人：吴洁 REMARK:系统参数表：增加回购标的期限品种取值开关  20181219 start**/
delete from SYS_PARA where system_code='VAL' and para_code='REPO_TERM_KIND_KG';
insert into SYS_PARA values ('VAL','REPO_TERM_KIND_KG','OFF','01','(null)','回购标的期限品种取值开关ON:开 OFF:关','system','',to_date('2018/12/17', 'yyyy/mm/dd'), 'system',to_date('2018/12/17', 'yyyy/mm/dd'));
/**提交人：吴洁 REMARK:系统参数表：增加回购标的期限品种取值开关  20181219 end**/

/**提交人：刘孟凯 REMARK:数据字典明细：【指令类型】增加存款类资产的类型  20181219 start**/
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE='ORDER_TYPE' AND ITEM_CODE IN ('N1110','2731','2732');
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N1110','存放同业-还本/到期','116','0','system','',to_date('2018/12/19', 'yyyy/mm/dd'), 'system',to_date('2018/12/19', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','2731','资金划款-存放同业起息','114','0','system','',to_date('2018/12/19', 'yyyy/mm/dd'), 'system',to_date('2018/12/19', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','2732','资金收款-存放同业卖出','115','0','system','',to_date('2018/12/19', 'yyyy/mm/dd'), 'system',to_date('2018/12/19', 'yyyy/mm/dd'));
/**提交人：刘孟凯 REMARK:数据字典明细：【指令类型】增加存款类资产的类型  20181219 end**/

/**提交人：程中   20181219 start**/
delete from sys_datadict_list where dict_code = 'DATA_TRANSPLANT_MODEL_TYPE' and ITEM_CODE = '04';
/**提交人：程中   20181219 end**/

/**                        add  2018-12-19  end                                    **/ 


/**                        add  2018-12-20  start                                    **/ 
/**add by huangjian 存款类资产查询添加流程 2018-12-20**/
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValDespositAssetQuery001';
update SYS_PAGE set is_need_workflow = '01' where page_id = 'ValDespositAssetQuery002';

delete SYS_PAGE_ELEMENT where PAGE_ID='ValDespositAssetQuery001' and ELEMENT_ID='ValDespositAssetQuery001_btn_delete';
insert into SYS_PAGE_ELEMENT values ('ValDespositAssetQuery001','ValDespositAssetQuery001_btn_delete','01','01','','存款类资产删除','system','',to_date('2018/12/20', 'yyyy/mm/dd'), 'system',to_date('2018/12/20', 'yyyy/mm/dd'));

delete SYS_PAGE_ELEMENT where PAGE_ID='ValDespositAssetQuery002' and ELEMENT_ID='ValDespositAssetQuery002_btn_submit';
insert into SYS_PAGE_ELEMENT values ('ValDespositAssetQuery002','ValDespositAssetQuery002_btn_submit','01','01','','存款类资产提交','system','',to_date('2018/12/20', 'yyyy/mm/dd'), 'system',to_date('2018/12/20', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE='V11';
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V11','存款类资产','11','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));

delete from  SYS_WFL_BUSINESS_MODULE where PAGE_ID='ValDespositAssetQuery002';
insert into SYS_WFL_BUSINESS_MODULE values ('ValDespositAssetQuery002','ValDespositAssetQuery002_btn_submit','oneApproval','system','',to_date('2018/12/20', 'yyyy/mm/dd'), 'system',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('ValDespositAssetQuery002','ValDespositAssetQuery001_btn_delete','oneApproval','system','',to_date('2018/12/20', 'yyyy/mm/dd'), 'system',to_date('2018/12/20', 'yyyy/mm/dd'));
/**add by huangjian 存款类资产查询添加流程 end 2018-12-20**/

/**add by huangjian 账户活期利率查询添加流程 start 2018-12-20**/
update SYS_PAGE set is_need_workflow = '01' where page_id = 'AccountCurrentRate001';
update SYS_PAGE set is_need_workflow = '01' where page_id = 'AccountCurrentRate002';
delete SYS_PAGE_ELEMENT a where a.page_id='AccountCurrentRate001' and a.element_id='AccountCurrentRate001_btn_delete';
insert into SYS_PAGE_ELEMENT values ('AccountCurrentRate001','AccountCurrentRate001_btn_delete','01','01','','账户活期利率删除','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
delete SYS_PAGE_ELEMENT a where a.page_id='AccountCurrentRate002' and a.element_id='AccountCurrentRate002_btn_submit';
insert into SYS_PAGE_ELEMENT values ('AccountCurrentRate002','AccountCurrentRate002_btn_submit','01','01','','账户活期利率提交','system','',to_date('2018/12/20', 'yyyy/mm/dd'), 'system',to_date('2018/12/20', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE='V09';
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','V09','账户活期利率','9','0','system','',to_date('2018/11/27', 'yyyy/mm/dd'), 'system',to_date('2018/11/27', 'yyyy/mm/dd'));

delete from  SYS_WFL_BUSINESS_MODULE where PAGE_ID='AccountCurrentRate002';
insert into SYS_WFL_BUSINESS_MODULE values ('AccountCurrentRate002','AccountCurrentRate002_btn_submit','oneApproval','system','',to_date('2018/12/20', 'yyyy/mm/dd'), 'system',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE values ('AccountCurrentRate002','AccountCurrentRate001_btn_delete','oneApproval','system','',to_date('2018/12/20', 'yyyy/mm/dd'), 'system',to_date('2018/12/20', 'yyyy/mm/dd'));
/**add by huangjian 账户活期利率查询添加流程 end 2018-12-20**/

/**add by huangjian 存款类资产账户活期利率 画面元素整理 2018-12-20**/
delete SYS_PAGE_ELEMENT where page_id in ('ValDespositAssetQuery001','ValDespositAssetQuery002','AccountCurrentRate001','AccountCurrentRate002');

insert into SYS_PAGE_ELEMENT values ('ValDespositAssetQuery001','ValDespositAssetQuery001_btn_search','01','01','','查询','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('ValDespositAssetQuery001','ValDespositAssetQuery001_btn_export','01','01','','导出','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('ValDespositAssetQuery001','ValDespositAssetQuery001_btn_add','01','01','','新建','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('AccountCurrentRate001','AccountCurrentRate001_btn_delete','01','01','','删除','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('ValDespositAssetQuery001','ValDespositAssetQuery001_btn_delete','01','01','','删除','system','',to_date('2018/12/20', 'yyyy/mm/dd'), 'system',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('AccountCurrentRate001','AccountCurrentRate001_btn_add','01','01','','新建','system','',to_date('2018/12/20', 'yyyy/mm/dd'), 'system',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('AccountCurrentRate001','AccountCurrentRate001_btn_export','01','01','','导出','system','',to_date('2018/12/20', 'yyyy/mm/dd'), 'system',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('AccountCurrentRate001','AccountCurrentRate001_btn_search','01','01','','查询','system','',to_date('2018/12/20', 'yyyy/mm/dd'), 'system',to_date('2018/12/20', 'yyyy/mm/dd'));

/**add by huangjian 存款类资产账户活期利率 画面元素整理 2018-12-20**/

/**数据字典：增加【估值表模板】 add by wujie on 20181220 start**/

delete from SYS_DATADICT where dict_code='VALUATION_TEMPLATE';
insert into SYS_DATADICT values ('VALUATION_TEMPLATE','VAL','估值表模板','','02','system','',to_date('2018/12/19', 'yyyy/mm/dd'), 'system',to_date('2018/12/19', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where dict_code='VALUATION_TEMPLATE';
insert into SYS_DATADICT_LIST values ('VALUATION_TEMPLATE','01','理财产品模板（系统推荐）','1','0','system','',to_date('2018/12/19', 'yyyy/mm/dd'), 'system',to_date('2018/12/19', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('VALUATION_TEMPLATE','02','基金模板（按货基和非货基模板)','2','0','system','',to_date('2018/12/19', 'yyyy/mm/dd'), 'system',to_date('2018/12/19', 'yyyy/mm/dd'));


/**数据字典：增加【估值表模板】 add by wujie on 20181220 end**/

/**                        add  2018-12-20  end                                    **/ 


/**                        add  2018-12-22  start                                    **/ 
/**add by huangjian 存款类资产活期利率 编辑按钮 2018-12-22 start **/
delete SYS_PAGE_ELEMENT where page_id='AccountCurrentRate001' and ELEMENT_ID='AccountCurrentRate001_TBLROW_edit';
delete SYS_PAGE_ELEMENT where page_id='ValDespositAssetQuery001' and ELEMENT_ID='ValDespositAssetQuery001_TBLROW_edit';
insert into SYS_PAGE_ELEMENT values ('AccountCurrentRate001','AccountCurrentRate001_TBLROW_edit','01','01','','编辑','system','',to_date('2018/12/21', 'yyyy/mm/dd'), 'system',to_date('2018/12/21', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('ValDespositAssetQuery001','ValDespositAssetQuery001_TBLROW_edit','01','01','','编辑','system','',to_date('2018/12/21', 'yyyy/mm/dd'), 'system',to_date('2018/12/21', 'yyyy/mm/dd'));
/**add by huangjian 存款类资产活期利率 编辑按钮 2018-12-22 end **/

/**add by huangjian 当初多添加的 现在元素表已经删除 补充重置 2018-12-22 start**/
delete SYS_PAGE_ELEMENT where page_id ='ValAssetsAmortizeSet002';
delete SYS_PAGE_ELEMENT where page_id='ValAssetsAmortizeSet001' and ELEMENT_ID='ValAssetsAmortizeSet001_btn_reset';
insert into SYS_PAGE_ELEMENT values ('ValAssetsAmortizeSet001','ValAssetsAmortizeSet001_btn_reset','01','01','','资产摊销重置','system','',to_date('2018/12/21', 'yyyy/mm/dd'), 'system',to_date('2018/12/21', 'yyyy/mm/dd'));
/**add by huangjian 当初多添加的 现在元素表已经删除 补充重置  2018-12-22 end**/

/**add by wangzhiang 框架升级2.0.7.8  2018-12-22 start**/
delete SYS_PAGE_ELEMENT where page_id ='SetDelegation002' and element_id in ('SetDelegation002_btn_save','SetDelegation002_btn_submit');
insert into SYS_PAGE_ELEMENT values ('SetDelegation002','SetDelegation002_btn_save','01','02','','保存','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('SetDelegation002','SetDelegation002_btn_submit','01','02','','提交','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
/**add by wangzhiang 框架升级2.0.7.8  2018-12-22 end**/

/**add by niuqianyu 基金类资产新建、删除、修改按钮  2018-12-22 start**/
delete SYS_PAGE_ELEMENT where page_id ='ValAssetsFundQuery001' and element_id in ('ValAssetsFundQuery001_btn_add','ValAssetsFundQuery001_btn_delete','ValAssetsFundQuery001_TBLROW_edit');
insert into SYS_PAGE_ELEMENT values ('ValAssetsFundQuery001','ValAssetsFundQuery001_btn_add','01','01','','新建','system','',to_date('2018/12/21', 'yyyy/mm/dd'), 'system',to_date('2018/12/21', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('ValAssetsFundQuery001','ValAssetsFundQuery001_btn_delete','01','01','','删除','system','',to_date('2018/12/21', 'yyyy/mm/dd'), 'system',to_date('2018/12/21', 'yyyy/mm/dd'));
insert into SYS_PAGE_ELEMENT values ('ValAssetsFundQuery001','ValAssetsFundQuery001_TBLROW_edit','01','01','','修改','system','',to_date('2018/12/21', 'yyyy/mm/dd'), 'system',to_date('2018/12/21', 'yyyy/mm/dd'));
/**add by niuqianyu 基金类资产新建、删除、修改按钮  2018-12-22 end**/
/**                        add  2018-12-22  end                                    **/ 


/** add                                   20181224                                 start**/
/**数据移植模板转菜单 add by wujie on 20181224 start**/

delete from SYS_MENU where MENU_ID = 'VALEXCLEIMP_001_007';
insert into SYS_MENU values ('VALEXCLEIMP_001_007','VAL','','VALEXCLEIMP_001','Transformation001','7','02','数据移植模板转换','system','',to_date('2018/12/22', 'yyyy/mm/dd'), 'system',to_date('2018/12/22', 'yyyy/mm/dd'));

delete from sys_page where page_id='Transformation001';
insert into SYS_PAGE values ('Transformation001','VAL','/valuation/transformation/Transformation001','valuation/transformation/Transformation001','VALEXCLEIMP_001_007','','','02','01','数据移植模板转换','system','',to_date('2018/12/22', 'yyyy/mm/dd'), 'system',to_date('2018/12/22', 'yyyy/mm/dd'));

/**数据移植模板转菜单 add by wujie on 20181224 end**/

/**数据移植模板转换新增数据字典:add by wujie on 201812224 start**/

delete from SYS_DATADICT where dict_code='TRANSPLANT_FORMATION_MODEL_TYPE';
insert into SYS_DATADICT values ('TRANSPLANT_FORMATION_MODEL_TYPE','VAL','数据移植转换模板类型','','02','system','',to_date('2018/12/22', 'yyyy/mm/dd'), 'system',to_date('2018/12/22', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where dict_code='TRANSPLANT_FORMATION_MODEL_TYPE';
insert into SYS_DATADICT_LIST values ('TRANSPLANT_FORMATION_MODEL_TYPE','01','产品余额报表','1','0','system','',to_date('2018/12/22', 'yyyy/mm/dd'), 'system',to_date('2018/12/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TRANSPLANT_FORMATION_MODEL_TYPE','02','资产余额报表','2','0','system','',to_date('2018/12/22', 'yyyy/mm/dd'), 'system',to_date('2018/12/22', 'yyyy/mm/dd'));


/**数据移植模板转换新增数据字典:add by wujie on 201812224 end**/

/**数据移植模板转菜单 add by wujie on 20181224 start**/

delete from SYS_MENU where MENU_ID = 'VALEXCLEIMP_001_007';
insert into SYS_MENU values ('VALEXCLEIMP_001_007','VAL','','VALEXCLEIMP_001','Transformation001','7','02','数据移植模板转换','system','',to_date('2018/12/22', 'yyyy/mm/dd'), 'system',to_date('2018/12/22', 'yyyy/mm/dd'));

delete from sys_page where page_id='Transformation001';
insert into SYS_PAGE values ('Transformation001','VAL','/valuation/transformation/Transformation001','valuation/transformation/Transformation001','VALEXCLEIMP_001_007','','','02','01','数据移植模板转换','system','',to_date('2018/12/22', 'yyyy/mm/dd'), 'system',to_date('2018/12/22', 'yyyy/mm/dd'));

/**数据移植模板转菜单 add by wujie on 20181224 end**/

/**数据移植模板转换新增数据字典:add by wujie on 201812224 start**/

delete from SYS_DATADICT where dict_code='TRANSPLANT_FORMATION_MODEL_TYPE';
insert into SYS_DATADICT values ('TRANSPLANT_FORMATION_MODEL_TYPE','VAL','数据移植转换模板类型','','02','system','',to_date('2018/12/22', 'yyyy/mm/dd'), 'system',to_date('2018/12/22', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where dict_code='TRANSPLANT_FORMATION_MODEL_TYPE';
insert into SYS_DATADICT_LIST values ('TRANSPLANT_FORMATION_MODEL_TYPE','01','产品余额报表','1','0','system','',to_date('2018/12/22', 'yyyy/mm/dd'), 'system',to_date('2018/12/22', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TRANSPLANT_FORMATION_MODEL_TYPE','02','资产余额报表','2','0','system','',to_date('2018/12/22', 'yyyy/mm/dd'), 'system',to_date('2018/12/22', 'yyyy/mm/dd'));

/**数据移植模板转换新增数据字典:add by wujie on 201812224 end**/
/** add                                   20181224                                 end**/


/** add                                   20181225                                start**/
/**提交人：牛倩玉 Remark：金融交易删除 start**/
delete from SYS_PAGE where PAGE_ID ='ValFinprodTradeQuery002' AND MENU_ID ='VALINFOSERVICE_001_002';
insert into SYS_PAGE values ('ValFinprodTradeQuery002','VAL','/valuation/trademanage/finprodtradequery/ValFinprodTradeQuery002','valuation/trademanage/finprodtradequery/ValFinprodTradeQuery002','VALINFOSERVICE_001_002','','','02','01','金融交易删除','system','',to_date('2018/2/3', 'yyyy/mm/dd'), 'system',to_date('2018/2/3', 'yyyy/mm/dd'));
/**提交人：牛倩玉 Remark：金融交易删除 end**/

/**提交人：张伟 Remark：债券类资产编辑权限 start**/
delete from SYS_PAGE_ELEMENT where page_id = 'ValBondassetquery001' and element_id = 'ValBondassetquery001_TBLROW_edit';
insert into SYS_PAGE_ELEMENT values ('ValBondassetquery001','ValBondassetquery001_TBLROW_edit','01','01','','编辑','system','',to_date('2018/2/4', 'yyyy/mm/dd'), 'system',to_date('2018/2/4', 'yyyy/mm/dd'));
/**提交人：张伟 Remark：债券类资产编辑权限 end**/

/**提交人：程中 Remark：修改产品固定管理费计提名称 start**/
update sys_batch_task set task_name = '全产品固定管理费计提-账务明细处理' where task_id = '33' and bean_name = 'fixedFeeCalBookTask';
/**提交人：程中 Remark：修改产品固定管理费计提名称 end**/
/** add                                   20181225                                end**/


/** add                                   20181227                                start**/
/**提交人：魏鹏 start**/
DELETE FROM SYS_BATCH_TASK WHERE task_id ='500' AND bean_name ='valFileUploadTask';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('500','VAL','批处理文件上传处理','','valFileUploadTask','200','system',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'), '');
DELETE FROM SYS_BATCH_JOB WHERE BATCH_SEQ_NO = '6f8803b3aa6fbaffe050007f01003cad' AND TASK_ID='500';
insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01003cad','500','system','',to_date('2018/10/30', 'yyyy/mm/dd'), 'system',to_date('2018/10/30', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST t where t.dict_code = 'MY_GZB_COL_INDEX' and t.item_value = '行情收市价';
insert into SYS_DATADICT_LIST values ('MY_GZB_COL_INDEX','H','市价','7','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MY_GZB_COL_INDEX','L','影子价格估值','11','0','E','system',to_date('2018/1/29', 'yyyy/mm/dd'), 'system',to_date('2018/1/29', 'yyyy/mm/dd'));
/**提交人：魏鹏 end**/
/** add                                   20181227                                end**/

commit;