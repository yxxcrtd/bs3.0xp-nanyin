/**增加批[存款资产预交易账务处理]。  add by yangjian 2018-09-21  start**/
-- [存款资产预交易账务处理]插入批处理任务表
delete from SYS_BATCH_TASK where task_id = '132';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) 
values ('132','VAL','存款资产预交易账务处理','','depositAssetsReadyDealTask','35','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');
/**增加批[存款资产预交易账务处理]。  add by yangjian 2018-09-21  end**/
/**ADD                                             2018-09-21                                              END**/

/**add                                         2018-09-26                            begin**/
delete from TAX_VAT_SCHEDULE where TAX_VAT_SCHEDULE.SEQ_NO in ('58','59');
insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('58','PinganSW001','A24','02','01','02', '', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('59','PinganSW001','A25','02','01','02', '', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
/**add                                         2018-09-26                            end**/

/**add                                         2018-10-08                            start**/
/**add by chengzhong for 交易所债券任务去除   start**/
delete from sys_batch_task where task_id in ('10', '113');
/**add by chengzhong for 交易所债券任务去除   **/

/**add                                         2018-10-08                            end**/

/**add                                         2018-10-09                            start**/
/**add by daiyue for [账套配置表]sheet 添加 SEC_DEAL_FEE_SUBJECT 这一条   start**/
insert into BOK_BOOKSET_EXT values ('00000000000000000002','SEC_DEAL_FEE_SUBJECT','02','债券交易费用损益计入方式','01:计入投资收益,02:计入费用支出','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));

/**add by daiyue for [账套配置表]sheet 添加 SEC_DEAL_FEE_SUBJECT 这一条   end**/

/**批处理匹配关系表增加撤销批处理数据  add by:yangjian 2018/10/09  begin**/
delete from BOK_MATCH_RLAT where match_type = '06' and acct_standard = 'ALL' and source = '111' and target = 'Y';
insert into BOK_MATCH_RLAT values ('06','ALL','111','Y','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
/**批处理匹配关系表增加撤销批处理数据  add by:yangjian 2018/10/09  end**/

/**add                                         2018-10-09                            end**/
/**add by zhangzhao for 预期收益型TA交易入账批处理    2018-10-09 start**/
--批处理匹配关系表
insert into BOK_MATCH_RLAT values ('01','ALL','303','27','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','303','8001','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','303','8002','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','303','8005','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
--批处理任务
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('303','VAL','预期收益型TA交易-账务明细处理','','expReturnTaTradeBookTask','40','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');
--批处理交易处理顺序
insert into BOK_TRADE_ORDER values ('QY','27','C1','1','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','27','C2','2','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','27','C3','3','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','27','C6','4','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','27','C7','5','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','27','C5','6','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));

/**add by zhangzhao for 预期收益型TA交易入账批处理    2018-10-09 end**/

/**add                                         2018-10-10                            start**/
/**add by zhangwei for 预期收益型子产品 TA 固定费用计提入账（脚本在BASE和MAST都需要执行）, 匹配关系    2018-10-10 start**/
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('300','VAL','预期收益型子产品TA固定费用计提-账务明细处理','','expReturnSubProductFixedFeeCalBookTask','80','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');

insert into BOK_MATCH_RLAT values ('03','QY','300','8101','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','300','8103','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','300','8110','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','300','8112','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('02','QY','300','YFXSFWF','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('02','QY','300','YFGLRBC','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('02','QY','300','YFDXF','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('02','QY','300','YFWBFWF','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
/**add by zhangwei for 预期收益型子产品 TA 固定费用计提入账    2018-10-10 end**/

/**add by niuqianyu for 预期收益型产品估值计算_费前批处理 （脚本在BASE和MAST都需要执行）   2018-10-10 start**/
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('301','VAL','预期收益型产品估值计算_费前','','expReturnPrdValuationFeeBeforeTask','85','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');
/**add by niuqianyu for 预期收益型产品估值计算_费前批处理    2018-10-10 end**/

/**add by yangjian for [买断式回购预交易账务处理]批执行相关数据脚本    2018-10-10 begin**/
--增加[买断式回购预交易账务处理]批处理
delete from SYS_BATCH_TASK where task_id = '161';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) 
values ('161','VAL','买断式回购预交易账务处理','','outrightProRepoDealAccTreatTask','35','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');
----[买断式回购预交易账务处理]批处理对应匹配表增加数据。
delete from BOK_MATCH_RLAT where source = '161' and match_type = '01' and ACCT_STANDARD = 'ALL' and target = '22';
insert into BOK_MATCH_RLAT values ('01','ALL','161','22','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
delete from BOK_MATCH_RLAT where source = '161' and match_type = '03' and ACCT_STANDARD = 'QY' and target = '1414';
insert into BOK_MATCH_RLAT values ('03','QY','161','1414','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
/**add by yangjian for [买断式回购预交易账务处理]批执行相关数据脚本    2018-10-10 end**/

/**add                                         2018-10-10                            end**/

/**前台树形结构配置增加预期收益产品 add by wujie start**/
DELETE FROM BOK_BATCH_TASK_GRP WHERE TASK_GRP_ID = '1011' OR P_TASK_GRP_ID = '1011';
insert into BOK_BATCH_TASK_GRP values ('1011','收益型产品','InterfData','01','1011',103,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdBaseInfo','产品基础信息','InterfData','1011','ValPrdBaseInfo',100009,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdOpenPlan','产品开放计划','InterfData','1011','ValPrdOpenPlan',100012,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdPayRule','产品付息规则','InterfData','1011','ValPrdPayRule',100013,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdAccrualPlan','产品付息计划','InterfData','1011','ValPrdAccrualPlan',100014,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdFeeBaseinfo','产品费用信息','InterfData','1011','ValPrdFeeBaseinfo',100015,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdPayPlan','产品费用支付计划','InterfData','1011','ValPrdPayPlan',100016,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdRateAdjustInfo','产品费率信息','InterfData','1011','ValPrdRateAdjustInfo',100017,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdPriceAdjustInfo','产品报价信息','InterfData','1011','ValPrdPriceAdjustInfo',100018,'system',sysdate,'system',sysdate,'systeam');

DELETE FROM BOK_BATCH_TASK_GRP WHERE TASK_GRP_ID = '2021' OR P_TASK_GRP_ID = '2021';
insert into BOK_BATCH_TASK_GRP values ('2021','收益型TA交易','InterfDeal','(null)','2021',8,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdPeriodDeal','存续期交易','InterfDeal','2021','ValPrdPeriodDeal',100034,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdFeeDeal','费用交易','InterfDeal','2021','ValPrdFeeDeal',100035,'system',sysdate,'system',sysdate,'systeam');

/**前台树形结构配置增加预期收益产品 add by wujie end**/

--新增批处理-预期收益型TA中收计提-账务明细处理 王耕耘
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('302','VAL','预期收益型TA中收计提-账务明细处理','','expReturnFeeAccountsTask','90','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');
--新增批处理匹配关系表-预期收益型TA中收计提-账务明细处理
insert into BOK_MATCH_RLAT values ('03','QY','302','8105','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));

/**add                                         2018-10-11                            end**/

/**add                                         2018-10-15                            start**/
/**add by huangjian  投资端费用支付-账务明细处理*/
delete from SYS_BATCH_TASK where task_id='9';

insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('9','VAL','投资端费用支付-账务明细处理','','investFeePayTask','40','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');
insert into BOK_MATCH_RLAT values ('03','QY','9','8102','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','8104','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','8106','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','8108','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','8111','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','8113','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','3001','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','3002','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','3004','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));

insert into BOK_MATCH_RLAT values ('01','ALL','9','18','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','1701','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','1702','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','1703','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','3003','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','3005','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','9','3006','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));

/**add                                         2018-10-15                            end**/

/*wangbo 表分区批处理 添加 start */
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('400','VAL','表分区处理','','tablePartitionTask','400','system',to_date('2018/05/10', 'yyyy/mm/dd'), 'system',to_date('2018/05/10', 'yyyy/mm/dd'), '');

insert into BOK_MATCH_RLAT values ('09','ALL','400','BOK_BOOKDETAIL_ALL','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('09','ALL','400','BOK_BOOKDETAIL_CURRENT','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('09','ALL','400','BOK_VOUCH_MAIN','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('09','ALL','400','BOK_VOUCH_DETAIL','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('09','ALL','400','BOK_BAL_TABLE_DATA','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('09','ALL','400','BOK_BALANCE','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('09','ALL','400','BOK_VAL_TABLE_DATA','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('09','ALL','400','TAX_BOK_BALANCE','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('09','ALL','400','TAX_BOK_DETAIL','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));

/*wangbo 表分区批处理 添加 end */

/**新增预期收益型母产品TA客户收益计提入账批处理 add by lihuadong start**/
delete from SYS_BATCH_TASK where task_id = '306';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('306','VAL','预期收益型母产品TA客户收益计提-账务明细处理','','expParPrdTaCusIncDrawBookTask','80','system',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'), '');
delete from BOK_MATCH_RLAT where source = '306';
insert into BOK_MATCH_RLAT values ('03','QY','306','8004','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
/**新增预期收益型母产品TA客户收益计提入账批处理  add by lihuadong end**/

--前台树形结构配置 BOK_BATCH_TASK_GRP
DELETE FROM BOK_BATCH_TASK_GRP WHERE TASK_GRP_ID = '1011' OR P_TASK_GRP_ID = '1011';
insert into BOK_BATCH_TASK_GRP values ('1011','收益型产品','InterfData','01','1011',103,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdBaseInfo','产品基础信息','InterfData','1011','ValPrdBaseInfo',100009,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdOpenPlan','产品开放计划','InterfData','1011','ValPrdOpenPlan',100012,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdPayRule','产品付息规则','InterfData','1011','ValPrdPayRule',100013,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdAccrualPlan','产品付息计划','InterfData','1011','ValPrdAccrualPlan',100014,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdFeeBaseinfo','产品费用信息','InterfData','1011','ValPrdFeeBaseinfo',100015,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdPayPlan','产品费用支付计划','InterfData','1011','ValPrdPayPlan',100016,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdRateAdjustInfo','产品费率信息','InterfData','1011','ValPrdRateAdjustInfo',100017,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdPriceAdjustInfo','产品报价信息','InterfData','1011','ValPrdPriceAdjustInfo',100018,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdFloatFee ','浮动管理费计提规则 ','InterfData','1011','ValPrdFloatFee',100107,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdFloatFeeDetail ','浮动管理费计算明细规则','InterfData','1011','ValPrdFloatFeeDetail ',100108,'system',sysdate,'system',sysdate,'systeam');
DELETE FROM BOK_BATCH_TASK_GRP WHERE TASK_GRP_ID = '2021' OR P_TASK_GRP_ID = '2021';
insert into BOK_BATCH_TASK_GRP values ('2021','收益型TA交易','InterfDeal','(null)','2021',8,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdPeriodDeal','存续期交易','InterfDeal','2021','ValPrdPeriodDeal',100034,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdFeeDeal','费用交易','InterfDeal','2021','ValPrdFeeDeal',100035,'system',sysdate,'system',sysdate,'systeam');
/**预期收益型接口脚本 end**/

/**add                                         2018-10-16                            end**/
/**add                                       2018-10-17                            begin**/
/**账套配置表 增加 产品利息计提方式  产品是否本系统计提 start**/
insert into  BOK_BOOKSET_EXT ( BOOKSET_ID, PARAM_CODE, PARAM_VALUE, PARAM_NAME, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME ) select t.bookset_id, 'PRD_INT_ACCR_TYPE', '01', '产品利息计提方式', '01：区间倒减，02：每日新增', 'system', '', date'2018-10-17', 'system', date'2018-10-17' from BOK_BOOKSET t where t.bookset_type = '02';

insert into  BOK_BOOKSET_EXT ( BOOKSET_ID, PARAM_CODE, PARAM_VALUE, PARAM_NAME, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME ) select t.bookset_id, 'IS_PRD_INT_ACCR', 'Y', '产品是否本系统计提', '', 'system', '', date'2018-10-17', 'system', date'2018-10-17' from BOK_BOOKSET t where t.bookset_type = '02';
/**账套配置表 增加 产品利息计提方式  产品是否本系统计提 end**/

/**add                                       2018-10-17                            end**/
/**add                                       2018-10-24                            begin**/
/** add by zhangwei 20181024 债券借贷计提账务处理 start **/
-- 在BASE和MAST都执行
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) 
values ('403','VAL','债券借贷计提-账务明细批处理','','secLendDrawBookDetailTask','60','system',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'), '');

insert into BOK_MATCH_RLAT values ('03','QY','403','2502','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','403','2509','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
/** add by zhangwei 20181024 债券借贷计提账务处理 end **/

/**add by wujie on 20181024 start remark:[账套配置表]sheet 添加 INVESTMENT_PURPOSE_ACCESS 这一条   start**/
DELETE FROM BOK_BOOKSET_EXT WHERE BOOKSET_ID = '00000000000000000002' AND PARAM_CODE='INVESTMENT_PURPOSE_ACCESS';

insert into BOK_BOOKSET_EXT values ('00000000000000000002','INVESTMENT_PURPOSE_ACCESS','01','投资目的获取方式','01：上游提供 02：估值方案生成','system','',to_date('2018/10/24', 'yyyy/mm/dd'), 'system',to_date('2018/10/24', 'yyyy/mm/dd'));

/**add by wujie on 20181024 start remark:[账套配置表]sheet 添加 INVESTMENT_PURPOSE_ACCESS 这一条   start**/
/**add                                       2018-10-24                            end**/

/**add                                       2018-10-29                            begin**/
/* 债券借贷标的债券计提-账务明细批处理 mast库 add by zhangyf 20181029 start */
-- 批处理任务【脚本请在BASE和MAST都执行】：债券借贷标的债券计提-账务明细批处理
delete from SYS_BATCH_TASK where task_id ='404';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('404','VAL','债券借贷标的债券计提-账务明细批处理','','secLendFinDrawBookDetailTask','60','system',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'), '');

-- 批处理匹配关系表：债券借贷标的债券计提-账务明细批处理
delete from BOK_MATCH_RLAT where SOURCE =  '404';
insert into BOK_MATCH_RLAT values ('02','QY','404','JRFZCB','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','404','2515','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('07','QY','404','','system','',to_date('2018/10/26', 'yyyy/mm/dd'), 'system',to_date('2018/10/26', 'yyyy/mm/dd'));

--批处理前序任务表：债券借贷标的债券计提-账务明细批处理
delete from BOK_BATCH_TASK_LINK where task_id ='404';
insert into BOK_BATCH_TASK_LINK values ('404','402','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));

--批处理前序任务表
delete from BOK_TRADE_ORDER where BUSI_TYPE  ='29';
insert into BOK_TRADE_ORDER values ('QY','29','T30','1','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','29','T33','2','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','29','T28','3','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','29','T31','4','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','29','T29','5','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','29','T32','6','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','29','T34','7','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','29','T37','8','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','29','T36','9','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','29','T35','9','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));

--科目性质:金融负债成本
delete from BOK_SUBJECT_NATURE_GROUP where GROUP_ID  ='CBL_KMCX' and BASE_SUBJECT_NATURE = 'JRFZCB';
insert into BOK_SUBJECT_NATURE_GROUP(GROUP_ID,ACCT_STANDARD_ID,USE_REMARK,BASE_SUBJECT_NATURE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('CBL_KMCX','QY','成本类对应科目查询','JRFZCB','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));

--科目性质:债券借贷成本
delete from BOK_SUBJECT_NATURE_GROUP where GROUP_ID  ='CBL_KMCX' and BASE_SUBJECT_NATURE = 'ZQJDCB';
insert into BOK_SUBJECT_NATURE_GROUP(GROUP_ID,ACCT_STANDARD_ID,USE_REMARK,BASE_SUBJECT_NATURE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('CBL_KMCX','QY','成本类对应科目查询','ZQJDCB','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));

--会计场景：债券借贷-融入债券利息计提
delete from BOK_TABLELIST where BOOKSET_ID = '00000000000000000002' and TABLE_ID = '2515';
insert into BOK_TABLELIST (BOOKSET_ID, TABLE_ID, TABLE_NAME, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('00000000000000000002','2515', '债券借贷-融入债券利息计提', '',  'admin', '003',to_date('2018/4/25', 'yyyy/mm/dd'),'admin',to_date('2018/4/25', 'yyyy/mm/dd'));

--会计元素：债券借贷-融入债券利息计提
delete from BOK_TABLEDICT where BOOKSET_ID = '00000000000000000002' and TABLE_ID = '2515';
insert into BOK_TABLEDICT (BOOKSET_ID,DICT_ID, DICT_CODE, TABLE_ID, DICT_NAME, DICT_TYPE, DICT_SOURCE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('00000000000000000002','1180', 'ACCQUA', '2515', '核算数量', 'N', 'ACCQUA',  'admin', '003', to_date('2018/4/25', 'yyyy/mm/dd'), 'admin', to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TABLEDICT (BOOKSET_ID,DICT_ID, DICT_CODE, TABLE_ID, DICT_NAME, DICT_TYPE, DICT_SOURCE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('00000000000000000002','1181', 'INTAMT', '2515', '利息计提', 'N', 'INTAMT',  'admin', '003', to_date('2018/4/25', 'yyyy/mm/dd'), 'admin', to_date('2018/4/25', 'yyyy/mm/dd'));
/* 债券借贷标的债券计提-账务明细批处理 mast库 add by zhangyf 20181029 end */

/* 债券借贷标的债券公允价值变动-账务明细批处理 zhangwei 20181029 start*/
-- 批处理任务【脚本请在BASE和MAST都执行】：债券借贷标的债券公允价值变动-账务明细批处理
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) 
values ('405','VAL','债券借贷标的债券公允价值变动-账务明细批处理','','secLendFinFloatBookDetailTask','70','system',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'), '');

insert into BOK_MATCH_RLAT values ('02','QY','405','JRFZCB','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','405','2516','system','',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'));
/* 债券借贷标的债券公允价值变动-账务明细批处理 zhangwei 20181029 end */


/**add                                       2018-10-29                            end**/

/**add                                       2018-10-31                            end**/

insert into BOK_MATCH_RLAT values ('03','QY','300','8107','system','',to_date('2018/09/10', 'yyyy/mm/dd'), 'system',to_date('2018/08/29', 'yyyy/mm/dd'));
--预期收益型固定管理费账务匹配关系
/**add                                       2018-10-31                            end**/
/**add                                       2018-11-01                            begin**/
--全产品费用批处理新增匹配类型，固定费用信息
delete BOK_MATCH_RLAT a where a.source='33' and a.target in('C03','C05','C06','C09','C10');
insert into BOK_MATCH_RLAT values ('08','ALL','33','C03','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('08','ALL','33','C05','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('08','ALL','33','C06','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('08','ALL','33','C09','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('08','ALL','33','C10','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));


delete sys_batch_task a  where a.task_id in('300','305');
delete BOK_MATCH_RLAT a where  a.source='305' and a.target in('YFLR','8004');
delete BOK_MATCH_RLAT a where  a.source='300' and a.target in('8101',
'8103',
'8105',
'8107',
'8110',
'8112',
'YFXSFWF',
'YFGLRBC',
'YFDXF',
'YFWBFWF',
'C03',
'C05',
'C06',
'C09',
'C10'
);



/**add by wujie on 20181101 start remark:金融产品分类表字段映射表：回购映射关系调整**/
DELETE FROM FIN_PRODUCT_CLASS WHERE FINPROD_TYPE='F02' AND ASSET_TYPE='01';
insert into FIN_PRODUCT_CLASS values ('1259','F02','01','质押式正回购','','','IB','银行间','01','中央国债登记结算有限责任公司','','','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_CLASS values ('1260','F02','01','质押式正回购','','','SH','上交所','02','上海清算所','','','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_CLASS values ('1261','F02','01','质押式正回购','','','SZ','深交所','03','中国证券登记结算有限责任公司','','','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));


DELETE FROM FIN_PRODUCT_CLASS WHERE FINPROD_TYPE='F03' AND ASSET_TYPE='02';
insert into FIN_PRODUCT_CLASS values ('1269','F03','02','质押式逆回购','','','IB','银行间','01','中央国债登记结算有限责任公司','G01','质押式回购-逆回购','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_CLASS values ('1270','F03','02','质押式逆回购','','','SH','上交所','02','上海清算所','G01','质押式回购-逆回购','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_CLASS values ('1271','F03','02','质押式逆回购','','','SZ','深交所','03','中国证券登记结算有限责任公司','G01','质押式回购-逆回购','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));



DELETE FROM FIN_PRODUCT_CLASS WHERE FINPROD_TYPE='F04' AND ASSET_TYPE='03';
insert into FIN_PRODUCT_CLASS values ('1279','F04','03','买断式正回购','','','IB','银行间','01','中央国债登记结算有限责任公司','','','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_CLASS values ('1280','F04','03','买断式正回购','','','SH','上交所','02','上海清算所','','','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_CLASS values ('1281','F04','03','买断式正回购','','','SZ','深交所','03','中国证券登记结算有限责任公司','','','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));


DELETE FROM FIN_PRODUCT_CLASS WHERE FINPROD_TYPE='F05' AND ASSET_TYPE='04';
insert into FIN_PRODUCT_CLASS values ('1289','F05','04','买断式逆回购','','','IB','银行间','01','中央国债登记结算有限责任公司','G02','买断式回购-逆回购','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_CLASS values ('1290','F05','04','买断式逆回购','','','SH','上交所','02','上海清算所','G02','买断式回购-逆回购','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_CLASS values ('1291','F05','04','买断式逆回购','','','SZ','深交所','03','中国证券登记结算有限责任公司','G02','买断式回购-逆回购','system','',to_date('2018/11/1', 'yyyy/mm/dd'), 'system',to_date('2018/11/1', 'yyyy/mm/dd'));

/**add by wujie on 20181101 end remark:金融产品分类表字段映射表：回购映射关系调整**/


/**add                                       2018-11-01                            end**/
/**add                                       2018-11-02                            begin**/
--废弃预期收益型费前 wanggengyun
delete sys_batch_task a  where a.task_id in('301');
/**add                                       2018-11-02                            end**/

/**add                                       2018-11-05                            begin**/
--修改错误分录
update BOK_SCENESUBJECT set form_ele = 451 where SCENE_NODE_ID = '001083';
/**add                                       2018-11-05                            end**/
/**add                                       2018-11-15                            begin**/
--added by wangzhiang 影子定价默认方案 start
truncate table BOK_SHADOW_PRICE_DETAIL;
Insert into BOK_SHADOW_PRICE_DETAIL (SCHEME_DETAIL_ID,SCHEME_ID,TRADE_MARKET,SEC_TYPE,SEC_DETAIL_TYPE,VALUATION_PRICE,VALUATION_PRICE_2,QUOTA_DIGIT,QUOTA_DEC_TYPE,VALUATION_DATE,VDATE,MDATE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('DETAIL001','SHADOW001','01','01',null,'CBC',null,2,'','02',to_date('1900-01-01','yyyy-mm-dd'),to_date('2099-12-31','yyyy-mm-dd'),'sys','001',to_timestamp('04-6月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'sys',to_timestamp('04-6月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into BOK_SHADOW_PRICE_DETAIL (SCHEME_DETAIL_ID,SCHEME_ID,TRADE_MARKET,SEC_TYPE,SEC_DETAIL_TYPE,VALUATION_PRICE,VALUATION_PRICE_2,QUOTA_DIGIT,QUOTA_DEC_TYPE,VALUATION_DATE,VDATE,MDATE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('DETAIL002','SHADOW001','03','01',null,'CBC',null,2,'','02',to_date('1900-01-01','yyyy-mm-dd'),to_date('2099-12-31','yyyy-mm-dd'),'sys','001',to_timestamp('04-6月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'sys',to_timestamp('04-6月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into BOK_SHADOW_PRICE_DETAIL (SCHEME_DETAIL_ID,SCHEME_ID,TRADE_MARKET,SEC_TYPE,SEC_DETAIL_TYPE,VALUATION_PRICE,VALUATION_PRICE_2,QUOTA_DIGIT,QUOTA_DEC_TYPE,VALUATION_DATE,VDATE,MDATE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('DETAIL003','SHADOW001','04','01',null,'CBC',null,2,'','02',to_date('1900-01-01','yyyy-mm-dd'),to_date('2099-12-31','yyyy-mm-dd'),'sys','001',to_timestamp('04-6月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'sys',to_timestamp('04-6月 -18 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
INSERT INTO BOK_SCHEME_INFO (SCH_ID, SCHEME_TYPE, SCH_NAME, REMARK, IS_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SHADOW001', '03', '默认影子定价方案', '默认影子定价方案', 'Y', 'sys', null, TIMESTAMP '2018-08-06 00:00:00', 'sys', TIMESTAMP '2018-08-06 00:00:00');
--added by wangzhiang 影子定价默认方3l 案 end
delete BOK_MATCH_RLAT a where a.source='132' and a.target in('28','1103','1110');
insert into BOK_MATCH_RLAT values ('01','ALL','132','28','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','132','1103','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','132','1110','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));

/**add                                       2018-11-15                            end**/
/**add                                       2018-11-19                            start**/
/** add by chengzhong for 债券增加申购交易，修改交易排序 start*/
delete from BOK_TRADE_ORDER where BUSI_TYPE = '02';
insert into BOK_TRADE_ORDER values ('QY','02','T01','7','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','02','T02','8','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','02','T07','5','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','02','T08','4','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','02','T09','2','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','02','T22','3','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','02','T23','6','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','02','T04','1','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
/** add by chengzhong for 债券增加申购交易，修改交易排序 end*/
/**add                                       2018-11-19                            end**/
/**add                                       2018-11-20                            start**/
/** add by wangengyun for 预期收益型交易，修改交易排序 start*/
delete BOK_TRADE_ORDER a where a.busi_type='27';
insert into BOK_TRADE_ORDER values ('QY','27','C1','1','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','27','C2','2','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','27','C3','3','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','27','C4','4','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','27','C6','5','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','27','C15','6','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','27','C7','7','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_TRADE_ORDER values ('QY','27','C5','8','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
/** add by wangengyun for 预期收益型交易，修改交易排序 start*/
/** add by dongxiangyang for 数据回传模板脚本 start*/                        
delete from BOK_BATCH_TASK_GRP where task_grp_id='4011' or task_grp_id='ValAddTaxDataBack' or task_grp_id='CProdDealDataBack'  or task_grp_id='NProdDealDateBack' or task_grp_id='BProdCostDataBack' ;
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('4011','估值回传数据','DataBack','','4011',1,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValAddTaxDataBack','增值税缴纳','DataBack','4011','ValAddTaxDataBack',100100,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('CProdDealDataBack','货币性产品估值','DataBack','4011','CProdDealDataBack',100101,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('NProdDealDataBack','净值型产品估值','DataBack','4011','NProdDealDataBack',100102,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('BProdCostDataBack','产品费用','DataBack','4011','BProdCostDataBack',100103,'admin',to_date('2018-09-18','yyyy-MM-dd'),'admin',to_date('2018-09-18','yyyy-MM-dd'),'');


delete from VAL_INTERF_TASK where TASK_ID='37' or TASK_ID='38' or TASK_ID='39' or TASK_ID='40';
insert into VAL_INTERF_TASK values ('37','taxInfoRecRunner','增值税缴纳-数据回传','2161','E','S','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TASK values ('38','moneyProdValuationDataRunner','货币性产品估值-数据回传','2162','E','S','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TASK values ('39','netProdValuationDataRunner','净值型产品估值-数据回传','2163','E','S','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TASK values ('40','feeInfoRecRunner','产品费用-数据回传','2164','E','S','ZG','system','',sysdate,'system',sysdate);


delete from VAL_INTERF_DEFINE where INTERF_ID='100122' or INTERF_ID='100123' or INTERF_ID='100124'  or INTERF_ID='100125';
insert into VAL_INTERF_DEFINE values ('100122','增值税缴纳-数据回传','ValAddTaxDataBack','2161','system','',sysdate,'system',sysdate);	
insert into VAL_INTERF_DEFINE values ('100123','货币性产品估值-数据回传','CProdDealDataBack','2162','system','',sysdate,'system',sysdate);	
insert into VAL_INTERF_DEFINE values ('100124','净值型产品估值-数据回传','NProdDealDataBack','2163','system','',sysdate,'system',sysdate);	
insert into VAL_INTERF_DEFINE values ('100125','产品费用-数据回传','BProdCostDataBack','2164','system','',sysdate,'system',sysdate);

delete from VAL_INTERF_TEMPLATE where TMP_E_NAME='ValAddTaxDataBack' or TMP_E_NAME='CProdDealDataBack' or TMP_E_NAME='NProdDealDateBack' or TMP_E_NAME='BProdCostDataBack';
insert into VAL_INTERF_TEMPLATE values ('增值税缴纳-数据回传','ValAddTaxDataBack','ValAddTaxDataBack','100122','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('货币性产品估值-数据回传','CProdDealDataBack','CProdDealDataBack','100123','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('净值型产品估值-数据回传','NProdDealDataBack','NProdDealDataBack','100124','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('产品费用-数据回传','BProdCostDataBack','BProdCostDataBack','100125','02','ZG','system','',sysdate,'system',sysdate);
/** add by dongxiangyang for 数据回传模板脚本 end*/
/**add                                       2018-11-20                            start**/



/**add                                       2018-11-21                            start**/
/** add by wangbo for 接口发送 start*/ 
DELETE FROM VAL_INTERF_SERVER_INFO WHERE SYSTEMID = 'ZG' AND RS = 'S';
Insert into VAL_INTERF_SERVER_INFO (SERVERIP,SERVERPORT,USERNAME,PASSWORD,SERVERTYPE,STATUS,SYSTEMID,REMOTE_PATH,LOCAL_PATH,RS,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,SERVERNAME) values ('192.168.70.220','22','tamsuser','tamsuser','06','Y','ZG','E:\ZG','E:\interf_file','S','system',null,to_timestamp('21-9月 -18 03.10.37.000000000 下午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('21-9月 -18 03.10.37.000000000 下午','DD-MON-RR HH.MI.SSXFF AM'),null);

/** add by wangbo for 接口发送 end*/
/**add                                       2018-11-21                            end**/

/**add                                       2018-11-26                            start**/
/** add by  for 接口发送 start*/
delete from BOK_MATCH_RLAT where MATCH_TYPE = '03' AND ACCT_STANDARD = 'QY' AND SOURCE = '122' AND TARGET = '8032';
insert into BOK_MATCH_RLAT values ('03','QY','122','8032','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
/** add by  for 接口发送 end*/
/**add                                       2018-11-26                            end**/

/**add                                       2018-11-28                            start**/
/** add by  chendijun  【接口-债券借贷标的券卖出与购回】投资目的字段修改为可以为空**/
delete from VAL_TEMP_COL_CHECK_INFO where tmp_e_name = 'ValSeclendAssetDeal' and col_e_name = 'investAim';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','投资目的','investAim','1','N','50','INV_AIM','1');

/**add                                       2018-11-28                            end**/

/**add                                       2018-11-30                            start**/
/** add by chengzhong  资产类科目场景关联表 添加数据 start**/
truncate table VAL_SUBJECT_TABLEID;
insert into VAL_SUBJECT_TABLEID values ('100301','1101','存放同业');
insert into VAL_SUBJECT_TABLEID values ('100302','1105','拆放同业');
insert into VAL_SUBJECT_TABLEID values ('1102','','摊余成本类债券');
insert into VAL_SUBJECT_TABLEID values ('1103','','公允价值损益类债券');
insert into VAL_SUBJECT_TABLEID values ('1104','','综合收益类债券');
insert into VAL_SUBJECT_TABLEID values ('1106','1501','摊余成本类-计划类资产');
insert into VAL_SUBJECT_TABLEID values ('1107','1501','公允价值损益类-计划类资产');
insert into VAL_SUBJECT_TABLEID values ('7201','2508','债券借贷');
insert into VAL_SUBJECT_TABLEID values ('210101','2519','债券标的借贷');
insert into VAL_SUBJECT_TABLEID values ('1002','','活期存款');
insert into VAL_SUBJECT_TABLEID values ('1021','','活期存款');
insert into VAL_SUBJECT_TABLEID values ('1031','','活期存款');
insert into VAL_SUBJECT_TABLEID values ('120201','1401','质押式回购');
insert into VAL_SUBJECT_TABLEID values ('120202','1407','买断式回购');
insert into VAL_SUBJECT_TABLEID values ('120211','1401','质押式回购');
insert into VAL_SUBJECT_TABLEID values ('120212','1407','买断式回购');
insert into VAL_SUBJECT_TABLEID values ('120221','1401','质押式回购');
insert into VAL_SUBJECT_TABLEID values ('120222','1407','买断式回购');
insert into VAL_SUBJECT_TABLEID values ('120231','1401','质押式回购');
insert into VAL_SUBJECT_TABLEID values ('120232','1407','买断式回购');
insert into VAL_SUBJECT_TABLEID values ('110501','1605','货币基金');
insert into VAL_SUBJECT_TABLEID values ('110502','1626','非货币基金');
insert into VAL_SUBJECT_TABLEID values ('110503','1626','ETF基金');
insert into VAL_SUBJECT_TABLEID values ('110504','1626','LOF基金');
insert into VAL_SUBJECT_TABLEID values ('110505','1605','货币基金');
insert into VAL_SUBJECT_TABLEID values ('110506','1626','非货币基金');
insert into VAL_SUBJECT_TABLEID values ('2001','1105','拆入款项');
insert into VAL_SUBJECT_TABLEID values ('220211','1401','质押式回购');
insert into VAL_SUBJECT_TABLEID values ('220212','1407','买断式回购');
insert into VAL_SUBJECT_TABLEID values ('220221','1401','质押式回购');
insert into VAL_SUBJECT_TABLEID values ('220222','1407','买断式回购');
insert into VAL_SUBJECT_TABLEID values ('220231','1401','质押式回购');
insert into VAL_SUBJECT_TABLEID values ('220232','1407','买断式回购');
insert into VAL_SUBJECT_TABLEID values ('220201','1401','质押式回购');
insert into VAL_SUBJECT_TABLEID values ('220202','1407','买断式回购');
/** add by chengzhong  资产类科目场景关联表 添加数据 end**/
/**add                                       2018-11-30                            end**/


/**                                           2018-12-06                                      start**/ 
/** 提交人：王之昂  日期：2018-12-06 begin**/
DELETE FROM BOK_MATCH_RLAT WHERE TARGET ='2731';
insert into BOK_MATCH_RLAT values ('03','QY','7','2731','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
/** 提交人：王之昂  日期：2018-12-06 end**/

/** 提交人：金茂东  日期：2018-12-06 start**/
--更新帐套级别的产品利息计提方式为：02-每日新增（01：区间倒减，02：每日新增）
UPDATE BOK_BOOKSET_EXT SET PARAM_VALUE = '02' WHERE PARAM_CODE = 'PRD_INT_ACCR_TYPE'; 

--估值表 行情收市价列位数刷为14位小数位
alter table bok_val_table_data rename column CLOSE_PRICE to CLOSE_PRICE2;
alter table bok_val_table_data  add CLOSE_PRICE   NUMBER(30,14); 
comment on column BOK_VAL_TABLE_DATA.close_price  is '行情收市价';
update bok_val_table_data t1 set t1.CLOSE_PRICE = (select CLOSE_PRICE2 from BOK_VAL_TABLE_DATA t2 where t2.SEQ_NO = t1.seq_no );
alter table bok_val_table_data drop column CLOSE_PRICE2;
/** 提交人：金茂东  日期：2018-12-06 end**/
/**                                           2018-12-06                                      end**/ 


/**                                           2018-12-07                                      start**/ 
/** 提交人：金茂东  日期：2018-12-07 start**/
--接口交易数据导入增加结算指令接口
DELETE FROM BOK_BATCH_TASK_GRP WHERE TASK_GRP_ID IN  ('3031','ValCleanOrder') AND TASK_GRP_TYPE = 'InterfDeal';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('3031','结算指令','InterfDeal','','3031',100086,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValCleanOrder','结算指令','InterfDeal','3031','ValCleanOrder',100086,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
/** 提交人：金茂东  日期：2018-12-07 end**/

/** 提交人：杨剑  日期：2018-12-07 start**/
update sys_batch_task set task_pri = '38' where task_id = '25' and bean_name = 'moneyFundDealCountingDrawingTask';
/** 提交人：杨剑  日期：2018-12-07 end**/

/** 提交人：代月  日期：2018-12-07 start**/
--场景计税配置 添加两条初始化数据
delete from TAX_BOK_CONFIG where CONFIG_ID in ('2504YSZQJDFY001','2504YSZQJDFY002');
insert into TAX_BOK_CONFIG (CONFIG_ID, TABLE_ID, SUBJECT_NATURE, VAT_TYPE, TAX_P_TYPE, INCOME_SOURCE,IN_OUT, CALC_TYPE, CD_FLAG, CALC_SCALE,CALC_SNATURE, CALC_BASE,IS_CARRT,INT_REPAY_HANDLE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('2504YSZQJDFY001','2504', 'YSZQJDFY', 'TAX0102', '01', '01', '02', '02', '', '01', 'YSZQJDFY', '01', '101', '', 'SYS', 'SYSDEPT', to_date('2018/08/06', 'yyyy/MM/dd'), 'SYS', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_BOK_CONFIG (CONFIG_ID, TABLE_ID, SUBJECT_NATURE, VAT_TYPE, TAX_P_TYPE, INCOME_SOURCE,IN_OUT, CALC_TYPE, CD_FLAG, CALC_SCALE,CALC_SNATURE, CALC_BASE,IS_CARRT,INT_REPAY_HANDLE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('2504YSZQJDFY002','2504', 'YSZQJDFY', 'TAX0102', '02', '', '01', '02', '', '01', 'YSZQJDFY', '01', '101', '', 'SYS', 'SYSDEPT', to_date('2018/08/06', 'yyyy/MM/dd'), 'SYS', to_date('2018/08/06', 'yyyy/MM/dd'));

delete from TAX_CF_BOK_CONFIG where SEQ_NO in ('76','77');
insert into TAX_CF_BOK_CONFIG (SEQ_NO, CONFIG_ID,CONFIG_VALUE,BOK_CONFIG_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('76', 'SFZGGYJZBDS', 'N', '1339GYJZBDSY001', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_CF_BOK_CONFIG (SEQ_NO, CONFIG_ID,CONFIG_VALUE,BOK_CONFIG_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('77', 'SFZGGYJZBDS', 'N', '1340GYJZBDSY001', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
/** 提交人：代月  日期：2018-12-07 end**/

/** 提交人：璩建忠  日期：2018-12-07 start**/
--利润表表样配置 
truncate table BOK_PROFIT_REP_CONFIG;
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1','一、收入',1,'1.1+1.2+1.3+1.4+1.5','Y',1,null,null,'sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1','1.利息收入',1,'1.1.1+1.1.2+1.1.3+1.1.4+1.1.5+1.1.6+1.1.7+1.1.8+1.1.9','Y',2,null,null,'sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.1','债券利息收入',1,'1.1.1.1+1.1.1.2+1.1.1.3','Y',3,null,null,'sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.1.1','摊余成本类债券利息收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','N',null,'601102','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.1.2','公允价值损益类债券利息收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','N',null,'601103','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.1.3','综合收益类债券利息收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','N',null,'601104','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.2','存放款收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',4,'601111','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.3','拆放同业利息收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',5,'601121','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.4','买入返售利息收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',6,'601131','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.5','优先股投资股息收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',7,'601108','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.6','计划类资产利息收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',8,'601106','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.7','活期存款利息收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',9,'601101','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.8','债券借贷借出费收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',10,'601109','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.9','利息收入增值税备抵',1,'1.1.9.1+1.1.9.2','Y',11,null,null,'sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.9.1','利息收入增值税备抵',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','N',null,'601199','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.1.9.2','合作方利息收入增值税备抵',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','N',null,'601198','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2','2.投资收益',1,'1.2.1+1.2.2+1.2.3+1.2.4+1.2.5+1.2.6+1.2.7+1.2.8+1.2.9+1.2.10','Y',12,null,null,'sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.1','股票投资收益',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',13,'611108','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.10','衍生工具收益',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',22,'611190','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.2','债券投资收益',1,'1.2.2.1+1.2.2.2+1.2.2.3','Y',14,null,null,'sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.2.1','摊余成本类债券资产投资收益',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','N',null,'611102','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.2.2','公允价值损益类债券资产投资收益',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','N',null,'611103','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.2.3','综合收益类金融资产投资收益',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','N',null,'611104','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.3','基金投资收益',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',15,'611105','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.4','基金红利收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',16,'601105','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.5','计划类投资收益 ',1,'1.2.6.1+1.2.6.2','Y',17,null,null,'sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.5.1','摊余成本类-计划类投资收益',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','N',null,'611106','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.5.2','公允价值损益类-计划类投资收益',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','N',null,'611107','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.6','计划类投资红利收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',18,'601107','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.7','以公允价值计量且其变动计入当期损益的金融负债价差收益',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',19,'611109','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.8','投资收益增值税备抵',1,'1.2.8.1+1.2.8.2','Y',20,null,null,'sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.8.1','投资收益增值税备抵',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','N',null,'611199','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.8.2','合作方投资收益增值税备抵',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','N',null,'611198','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.2.9','红利收入增值税备抵',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',21,'611195','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.3','3.公允价值变动损益',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',23,'6101','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.4','4.汇兑损益',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',24,'6112','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('1.5','5.其他收入',0,'TdyOCAmt-TdyODAmt+TdyOCoDAmt-TdyOCoCAmt','Y',25,'6302','C','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2','二、支出',1,'2.1+2.2+2.3+2.4+2.5','Y',26,null,null,'sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.1','1.利息支出',0,'TdyODAmt-TdyOCAmt+TdyOCoCAmt-TdyOCoDAmt','Y',27,'6411','D','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.2','2.费用',1,'2.2.1+2.2.2+2.2.3+2.2.4+2.2.5+2.2.6+2.2.7+2.2.8','Y',28,null,null,'sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.2.1','销售服务费',0,'TdyODAmt-TdyOCAmt+TdyOCoCAmt-TdyOCoDAmt','Y',29,'6406','D','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.2.2','交易费用',0,'TdyODAmt-TdyOCAmt+TdyOCoCAmt-TdyOCoDAmt','Y',30,'6407','D','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.2.3','管理人报酬',0,'TdyODAmt-TdyOCAmt+TdyOCoCAmt-TdyOCoDAmt','Y',31,'6403','D','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.2.4','其他费用',0,'TdyODAmt-TdyOCAmt+TdyOCoCAmt-TdyOCoDAmt','Y',32,'6605','D','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.2.5','托管费',0,'TdyODAmt-TdyOCAmt+TdyOCoCAmt-TdyOCoDAmt','Y',33,'6404','D','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.2.6','代销费',0,'TdyODAmt-TdyOCAmt+TdyOCoCAmt-TdyOCoDAmt','Y',34,'6409','D','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.2.7','外包服务费',0,'TdyODAmt-TdyOCAmt+TdyOCoCAmt-TdyOCoDAmt','Y',35,'6410','D','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.2.8','推介费',0,'TdyODAmt-TdyOCAmt+TdyOCoCAmt-TdyOCoDAmt','Y',36,'6413','D','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.3','3.其他支出',0,'TdyODAmt-TdyOCAmt+TdyOCoCAmt-TdyOCoDAmt','Y',37,'6702','D','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.4','4.增值税及附加',0,'TdyODAmt-TdyOCAmt+TdyOCoCAmt-TdyOCoDAmt','Y',38,'6408','D','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('2.5','5.资产减值损失',0,'TdyODAmt-TdyOCAmt+TdyOCoCAmt-TdyOCoDAmt','Y',39,'6412','D','sys',null,SYSDATE,'sys',SYSDATE);
Insert into BOK_PROFIT_REP_CONFIG (PROJ_CODE,PROJ_NAME,PROJ_LEVEL,FORMULA,SHOW_FLG,SHOW_ORDER,SUBJECT_NO,CRDR_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3','三、利润总额',1,'1-2','Y',40,null,null,'sys',null,SYSDATE,'sys',SYSDATE);
/** 提交人：璩建忠  日期：2018-12-07 end**/
/**                                                2018-12-07                                      end**/ 


/**                                                2018-12-10                                      start**/ 
/** 提交人：代月  日期：2018-12-10 remark：债券借贷补充 start**/
delete from TAX_VAT_SCHEDULE where ASSET_CLASSIFY = 'J01';
insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('364','PinganSW001','J01','01','01','03', '0.03', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('365','PinganSW001','J01','','01','02', '', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('366','PinganSW001','J01','','02','03', '0.03', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));

insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('368','PinganSW002','J01','01','01','03', '0.03', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('369','PinganSW002','J01','','01','02', '', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('370','PinganSW002','J01','','02','03', '0.03', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
/** 提交人：代月  日期：2018-12-10 remark：债券借贷补充 end**/

/** 提交人：代月  日期：2018-12-10 remark：债券借贷再补充 start**/
delete from TAX_VAT_SCHEDULE where ASSET_CLASSIFY = 'J01';
insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('364','PinganSW001','J01','01','01','03', '0.03', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('365','PinganSW001','J01','02','01','02', '', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('366','PinganSW001','J01','','02','03', '0.03', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));

insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('368','PinganSW002','J01','01','01','03', '0.03', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('369','PinganSW002','J01','02','01','02', '', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_VAT_SCHEDULE (SEQ_NO, SCH_ID, ASSET_CLASSIFY, CAPI_INCOME_FEATURE, INCOME_TYPE,TAXABLE_SIGN,TAX_RATE,FEE_TYPE, EFF_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('370','PinganSW002','J01','','02','03', '0.03', '', to_date('2018/01/01','YYYY/MM/DD'), 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));

delete from BOK_SCHEME_CONFIG where CONFIG_ID in ('IS_CALC_TURNED','TURNED_CALC_TYPE');
insert into BOK_SCHEME_CONFIG (CONFIG_ID, SCHEME_TYPE, SCHEME_TYPE_2,CONFIG_NAME, REMARK,DICT_CODE,CONFIG_TYPE,SORT_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('IS_CALC_TURNED','02','1', '是否处理待抵扣增值额', 'Y/N', '+', '01', '3', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into BOK_SCHEME_CONFIG (CONFIG_ID, SCHEME_TYPE, SCHEME_TYPE_2,CONFIG_NAME, REMARK,DICT_CODE,CONFIG_TYPE,SORT_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('TURNED_CALC_TYPE','02','1', '待抵扣增值额处理方式', '01:按照数量扣减,02:按照增值额扣减', '+', '01', '3', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
/** 提交人：代月  日期：2018-12-10 remark：债券借贷再补充end**/

/** 提交人：代月  日期：2018-12-10 remark：债券借贷税务分类映射关系表 start**/
delete from FIN_PRODUCT_CLASS where OPTSN in ('1320','1321');
insert into FIN_PRODUCT_CLASS values ('1320','F23','F23','债券借贷融入','','','','','','','','','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));
insert into FIN_PRODUCT_CLASS values ('1321','F24','F24','债券借贷融出','','','','','','','J01','债券借贷-融出','system','',to_date('2018/12/10', 'yyyy/mm/dd'), 'system',to_date('2018/12/10', 'yyyy/mm/dd'));
/** 提交人：代月  日期：2018-12-10 remark：债券借贷税务分类映射关系表 end**/
/**                                                         2018-12-10                                      end**/ 


/**                                                2018-12-11                                      start**/ 
/** 提交人：代月  日期：2018-12-11 remark：其他综合收益是否计税添加配置 start**/
delete from TAX_BOK_CONFIG where CONFIG_ID in ('1322QTZHSY001','1325QTZHSY001','1326QTZHSY001','1337QTZHSY001','1338QTZHSY001');
insert into TAX_BOK_CONFIG (CONFIG_ID, TABLE_ID, SUBJECT_NATURE, VAT_TYPE, TAX_P_TYPE, INCOME_SOURCE,IN_OUT, CALC_TYPE, CD_FLAG, CALC_SCALE,CALC_SNATURE, CALC_BASE,IS_CARRT,INT_REPAY_HANDLE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('1322QTZHSY001','1322', 'QTZHSY', 'TAX0302', '01', '', '01', '01', 'C', '', '', '', '110', '', 'SYS', 'SYSDEPT', to_date('2018/08/06', 'yyyy/MM/dd'), 'SYS', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_BOK_CONFIG (CONFIG_ID, TABLE_ID, SUBJECT_NATURE, VAT_TYPE, TAX_P_TYPE, INCOME_SOURCE,IN_OUT, CALC_TYPE, CD_FLAG, CALC_SCALE,CALC_SNATURE, CALC_BASE,IS_CARRT,INT_REPAY_HANDLE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('1325QTZHSY001','1325', 'QTZHSY', 'TAX0302', '01', '', '01', '01', 'C', '', '', '', '110', '', 'SYS', 'SYSDEPT', to_date('2018/08/06', 'yyyy/MM/dd'), 'SYS', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_BOK_CONFIG (CONFIG_ID, TABLE_ID, SUBJECT_NATURE, VAT_TYPE, TAX_P_TYPE, INCOME_SOURCE,IN_OUT, CALC_TYPE, CD_FLAG, CALC_SCALE,CALC_SNATURE, CALC_BASE,IS_CARRT,INT_REPAY_HANDLE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('1326QTZHSY001','1326', 'QTZHSY', 'TAX0302', '01', '', '01', '01', 'C', '', '', '', '110', '', 'SYS', 'SYSDEPT', to_date('2018/08/06', 'yyyy/MM/dd'), 'SYS', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_BOK_CONFIG (CONFIG_ID, TABLE_ID, SUBJECT_NATURE, VAT_TYPE, TAX_P_TYPE, INCOME_SOURCE,IN_OUT, CALC_TYPE, CD_FLAG, CALC_SCALE,CALC_SNATURE, CALC_BASE,IS_CARRT,INT_REPAY_HANDLE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('1337QTZHSY001','1337', 'QTZHSY', 'TAX0302', '01', '', '01', '01', 'C', '', '', '', '110', '', 'SYS', 'SYSDEPT', to_date('2018/08/06', 'yyyy/MM/dd'), 'SYS', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_BOK_CONFIG (CONFIG_ID, TABLE_ID, SUBJECT_NATURE, VAT_TYPE, TAX_P_TYPE, INCOME_SOURCE,IN_OUT, CALC_TYPE, CD_FLAG, CALC_SCALE,CALC_SNATURE, CALC_BASE,IS_CARRT,INT_REPAY_HANDLE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('1338QTZHSY001','1338', 'QTZHSY', 'TAX0302', '01', '', '01', '01', 'C', '', '', '', '110', '', 'SYS', 'SYSDEPT', to_date('2018/08/06', 'yyyy/MM/dd'), 'SYS', to_date('2018/08/06', 'yyyy/MM/dd'));

delete from BOK_SCHEME_CONFIG where CONFIG_ID in ('QTZHSYSFJS');
insert into BOK_SCHEME_CONFIG (CONFIG_ID, SCHEME_TYPE, SCHEME_TYPE_2,CONFIG_NAME, REMARK,DICT_CODE,CONFIG_TYPE,SORT_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('QTZHSYSFJS','02','1', '其他综合收益是否计税', '其他综合收益是否计税', 'YES_OR_NO', '01', '1', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));

delete from BOK_SCHEME_CONFIG_VAL where CONFIG_ID = 'QTZHSYSFJS';
insert into BOK_SCHEME_CONFIG_VAL (SEQ_ID, SCHEME_ID, CONFIG_ID,CONFIG_VALUE,REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('29', 'PinganSW001', 'QTZHSYSFJS', 'N', '无', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into BOK_SCHEME_CONFIG_VAL (SEQ_ID, SCHEME_ID, CONFIG_ID,CONFIG_VALUE,REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('30', 'PinganSW002', 'QTZHSYSFJS', 'N', '无', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));

delete from TAX_CF_BOK_CONFIG where CONFIG_ID in ('QTZHSYSFJS');
insert into TAX_CF_BOK_CONFIG (SEQ_NO, CONFIG_ID,CONFIG_VALUE,BOK_CONFIG_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('80', 'QTZHSYSFJS', 'N', '1322QTZHSY001', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_CF_BOK_CONFIG (SEQ_NO, CONFIG_ID,CONFIG_VALUE,BOK_CONFIG_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('81', 'QTZHSYSFJS', 'N', '1325QTZHSY001', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_CF_BOK_CONFIG (SEQ_NO, CONFIG_ID,CONFIG_VALUE,BOK_CONFIG_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('82', 'QTZHSYSFJS', 'N', '1326QTZHSY001', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_CF_BOK_CONFIG (SEQ_NO, CONFIG_ID,CONFIG_VALUE,BOK_CONFIG_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('83', 'QTZHSYSFJS', 'N', '1337QTZHSY001', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into TAX_CF_BOK_CONFIG (SEQ_NO, CONFIG_ID,CONFIG_VALUE,BOK_CONFIG_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('84', 'QTZHSYSFJS', 'N', '1338QTZHSY001', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
/** 提交人：代月  日期：2018-12-11 remark：其他综合收益是否计税添加配置 end**/
/**                                                2018-12-11                                      end**/ 


/**                                                2018-12-12                                      start**/ 
/** 提交人：王之昂  日期：2018-12-12 start**/
alter table BOK_SCHEME_CONFIG modify DICT_CODE null;
delete BOK_SCHEME_CONFIG where CONFIG_ID in ('IS_CALC_TURNED','TURNED_CALC_TYPE');
insert into BOK_SCHEME_CONFIG (CONFIG_ID, SCHEME_TYPE, SCHEME_TYPE_2,CONFIG_NAME, REMARK,DICT_CODE,CONFIG_TYPE,SORT_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('IS_CALC_TURNED','02','1', '是否处理待抵扣增值额', 'Y/N', 'YES_OR_NO', '01', '3', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into BOK_SCHEME_CONFIG (CONFIG_ID, SCHEME_TYPE, SCHEME_TYPE_2,CONFIG_NAME, REMARK,DICT_CODE,CONFIG_TYPE,SORT_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('TURNED_CALC_TYPE','02','1', '待抵扣增值额处理方式', '01:按照数量扣减,02:按照增值额扣减', ' ', '01', '3', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
/** 提交人：王之昂  日期：2018-12-12 end**/
/**                                                2018-12-12                                      end**/ 

/**                                                2018-12-13                                      start**/ 
/** 提交人：yangjian  日期：2018-12-13 start**/
delete BOK_SCHEME_CONFIG_VAL where CONFIG_ID in ('IS_CALC_TURNED','TURNED_CALC_TYPE');
insert into BOK_SCHEME_CONFIG_VAL (SEQ_ID, SCHEME_ID, CONFIG_ID,CONFIG_VALUE,REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values('23', 'PinganSW001', 'IS_CALC_TURNED', 'N', '无', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into BOK_SCHEME_CONFIG_VAL (SEQ_ID, SCHEME_ID, CONFIG_ID,CONFIG_VALUE,REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values('24', 'PinganSW001', 'TURNED_CALC_TYPE', '01', '无', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into BOK_SCHEME_CONFIG_VAL (SEQ_ID, SCHEME_ID, CONFIG_ID,CONFIG_VALUE,REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values('25', 'PinganSW002', 'IS_CALC_TURNED', 'N', '无', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into BOK_SCHEME_CONFIG_VAL (SEQ_ID, SCHEME_ID, CONFIG_ID,CONFIG_VALUE,REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values('26', 'PinganSW002', 'TURNED_CALC_TYPE', '01', '无', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
/** 提交人：yangjian  日期：2018-12-13 end**/

/** 提交人：代月  日期：2018-12-13 start**/
--增值附加税开关
delete from BOK_SCHEME_CONFIG where CONFIG_ID in ('ISCALCINTALT','ISCALCFLOATALT');
insert into BOK_SCHEME_CONFIG (CONFIG_ID, SCHEME_TYPE, SCHEME_TYPE_2,CONFIG_NAME, REMARK,DICT_CODE,CONFIG_TYPE,SORT_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('ISCALCINTALT','02','1', '是否预估利息收入增值税附加', '是否预估利息收入增值税附加', 'YES_OR_NO', '01', '1', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into BOK_SCHEME_CONFIG (CONFIG_ID, SCHEME_TYPE, SCHEME_TYPE_2,CONFIG_NAME, REMARK,DICT_CODE,CONFIG_TYPE,SORT_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('ISCALCFLOATALT','02','1', '是否预估公允价值变动增值税附加', '是否预估公允价值变动增值税附加', 'YES_OR_NO', '01', '1', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
delete from BOK_SCHEME_CONFIG_VAL where CONFIG_ID in ('ISCALCINTALT','ISCALCFLOATALT');
insert into BOK_SCHEME_CONFIG_VAL (SEQ_ID, SCHEME_ID, CONFIG_ID,CONFIG_VALUE,REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('34', 'PinganSW001', 'ISCALCINTALT', 'Y', '无', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into BOK_SCHEME_CONFIG_VAL (SEQ_ID, SCHEME_ID, CONFIG_ID,CONFIG_VALUE,REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('35', 'PinganSW002', 'ISCALCINTALT', 'Y', '无', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into BOK_SCHEME_CONFIG_VAL (SEQ_ID, SCHEME_ID, CONFIG_ID,CONFIG_VALUE,REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('36', 'PinganSW001', 'ISCALCFLOATALT', 'Y', '无', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into BOK_SCHEME_CONFIG_VAL (SEQ_ID, SCHEME_ID, CONFIG_ID,CONFIG_VALUE,REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('37', 'PinganSW002', 'ISCALCFLOATALT', 'Y', '无', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
/** 提交人：代月  日期：2018-12-13 end**/

/** 提交人：代月  日期：2018-12-13 start**/
--科目性质分组
delete from BOK_SUBJECT_NATURE_GROUP where GROUP_ID in ('GYJZBDZC_KMCX');
insert into BOK_SUBJECT_NATURE_GROUP(GROUP_ID,ACCT_STANDARD_ID,USE_REMARK,BASE_SUBJECT_NATURE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('GYJZBDZC_KMCX','QY','公允价值查询对应科目','GYJZSYL_GYJZBD','system','',to_date('2018/8/23', 'yyyy/mm/dd'), 'system',to_date('2018/8/23', 'yyyy/mm/dd'));
insert into BOK_SUBJECT_NATURE_GROUP(GROUP_ID,ACCT_STANDARD_ID,USE_REMARK,BASE_SUBJECT_NATURE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('GYJZBDZC_KMCX','QY','公允价值查询对应科目','ZHSYL_GYJZBD','system','',to_date('2018/8/23', 'yyyy/mm/dd'), 'system',to_date('2018/8/23', 'yyyy/mm/dd'));
insert into BOK_SUBJECT_NATURE_GROUP(GROUP_ID,ACCT_STANDARD_ID,USE_REMARK,BASE_SUBJECT_NATURE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('GYJZBDZC_KMCX','QY','公允价值查询对应科目','GYJZBD','system','',to_date('2018/8/23', 'yyyy/mm/dd'), 'system',to_date('2018/8/23', 'yyyy/mm/dd'));
insert into BOK_SUBJECT_NATURE_GROUP(GROUP_ID,ACCT_STANDARD_ID,USE_REMARK,BASE_SUBJECT_NATURE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('GYJZBDZC_KMCX','QY','公允价值查询对应科目','GP_GYJZBD','system','',to_date('2018/8/23', 'yyyy/mm/dd'), 'system',to_date('2018/8/23', 'yyyy/mm/dd'));
insert into BOK_SUBJECT_NATURE_GROUP(GROUP_ID,ACCT_STANDARD_ID,USE_REMARK,BASE_SUBJECT_NATURE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('GYJZBDZC_KMCX','QY','公允价值查询对应科目','YXG_GYJZBD','system','',to_date('2018/8/23', 'yyyy/mm/dd'), 'system',to_date('2018/8/23', 'yyyy/mm/dd'));
insert into BOK_SUBJECT_NATURE_GROUP(GROUP_ID,ACCT_STANDARD_ID,USE_REMARK,BASE_SUBJECT_NATURE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('GYJZBDZC_KMCX','QY','公允价值查询对应科目','JHLZC_GYJZBD','system','',to_date('2018/8/23', 'yyyy/mm/dd'), 'system',to_date('2018/8/23', 'yyyy/mm/dd'));
insert into BOK_SUBJECT_NATURE_GROUP(GROUP_ID,ACCT_STANDARD_ID,USE_REMARK,BASE_SUBJECT_NATURE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('GYJZBDZC_KMCX','QY','公允价值查询对应科目','JJTZ_GZZZ','system','',to_date('2018/8/23', 'yyyy/mm/dd'), 'system',to_date('2018/8/23', 'yyyy/mm/dd'));
/** 提交人：代月  日期：2018-12-13 end**/

/**                                                2018-12-13                                      end**/ 


/**                                                2018-12-14                                      start**/ 
/** 提交人：代月  日期：2018-12-14 start**/
DELETE FROM BOK_SCHEME_CONFIG WHERE CONFIG_ID IN ('IS_CALC_TURNED','TURNED_CALC_TYPE');
insert into BOK_SCHEME_CONFIG (CONFIG_ID, SCHEME_TYPE, SCHEME_TYPE_2,CONFIG_NAME, REMARK,DICT_CODE,CONFIG_TYPE,SORT_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('IS_CALC_TURNED','02','1', '是否处理待抵扣增值额', 'Y/N', 'YES_OR_NO', '01', '3', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
insert into BOK_SCHEME_CONFIG (CONFIG_ID, SCHEME_TYPE, SCHEME_TYPE_2,CONFIG_NAME, REMARK,DICT_CODE,CONFIG_TYPE,SORT_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('TURNED_CALC_TYPE','02','1', '待抵扣增值额处理方式', '01:按照数量扣减,02:按照增值额扣减', '', '01', '3', 'sys', '', to_date('2018/08/06', 'yyyy/MM/dd'), 'sys', to_date('2018/08/06', 'yyyy/MM/dd'));
/** 提交人：代月  日期：2018-12-14 end**/
/**                                                2018-12-14                                      start**/ 

/**                                                2018-12-15                                      start**/ 
/** 提交人：王之昂 拆借同业存放结算指令配置追加 日期：2018-12-15 start**/
DELETE FROM BOK_MATCH_RLAT where MATCH_TYPE = '03' and SOURCE = '7' and TARGET in ('2732','2727','2728','2729','2730');
insert into BOK_MATCH_RLAT values ('03','QY','7','2732','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','7','2729','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','7','2727','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','7','2728','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','7','2730','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
/** 提交人：王之昂拆借同业存放结算指令配置追加  日期：2018-12-15 end**/
/**                                                2018-12-15                                      end**/ 


/**                                                2018-12-17                                      start**/ 
/** 提交人：黄静  日期：2018-12-17 start**/
delete from BOK_SUBJECT where SUBJECT_NO='11060202';
insert into BOK_SUBJECT(BOOKSET_ID,SUBJECT_NO,SUBJECT_NAME,FSUBJECT_ID,BASE_SUBJECT_NATURE,BAL_FLAG,SUBJECT_TYPE,SUBJECT_LEVEL,ACC_QUA_FLAG,ACC_INT_FLAG,OVERDRAWN_FLAG,GEN_DETSUB_FLAG,INV_AIM,SEC_MANAGE_ACCT_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('00000000000000000002','11060202','摊余成本类-境外计划类资产投资-利息调整','110602','JHLZC_LXTZ','D','01','3','','','','','','','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_SUBJECT(BOOKSET_ID,SUBJECT_NO,SUBJECT_NAME,FSUBJECT_ID,BASE_SUBJECT_NATURE,BAL_FLAG,SUBJECT_TYPE,SUBJECT_LEVEL,ACC_QUA_FLAG,ACC_INT_FLAG,OVERDRAWN_FLAG,GEN_DETSUB_FLAG,INV_AIM,SEC_MANAGE_ACCT_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('00000000000000000003','11060202','摊余成本类-境外计划类资产投资-利息调整','110602','JHLZC_LXTZ','D','01','3','','','','','','','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
/** 提交人：黄静  日期：2018-12-17 end**/
/**                                                2018-12-17                                      end**/ 


/**                                                2018-12-18                                      start**/ 
/**                                                2018-12-17                                      start**/ 
/** 提交人：王之昂  日期：2018-12-18 start**/
--批处理匹配关系表 拆借交易/计提变更
delete from BOK_MATCH_RLAT where source = '1' and TARGET in ('1107','1108','1109');
insert into BOK_MATCH_RLAT values ('03','QY','1','1107','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','1','1108','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','1','1109','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));

delete from BOK_MATCH_RLAT where source = '21' and TARGET in ('1108','1109');
insert into BOK_MATCH_RLAT values ('07','QY','21','1108','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('07','QY','21','1109','system','',to_date('2018/05/09', 'yyyy/mm/dd'), 'system',to_date('2018/05/09', 'yyyy/mm/dd'));
/** 提交人：王之昂  日期：2018-12-18 end**/
/**                                                2018-12-18                                      end**/ 

/**                                                2018-12-24                                      start**/
/** 提交人：吴洁  20181224 Remark：数据移植对应科目关系初始化 start**/
insert into TRS_SUBJECT_RELATION values ('SYS000001','00000000000000000002','IB001','','F01','02','AC','11020101','12040401','11020102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000002','00000000000000000002','IB002','','F01','02','AC','11020201','12040402','11020202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000003','00000000000000000002','IB003','','F01','02','AC','11020301','12040403','11020302','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000004','00000000000000000002','IB004','','F01','02','AC','11020401','12040404','11020402','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000005','00000000000000000002','IB005','','F01','02','AC','11020501','12040405','11020502','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000006','00000000000000000002','IB006','','F01','02','AC','11020601','12040406','11020602','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000007','00000000000000000002','IB007','','F01','02','AC','11020701','12040407','11020702','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000008','00000000000000000002','IB008','','F01','02','AC','11020801','12040408','11020802','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000009','00000000000000000002','IB009','','F01','02','AC','11020901','12040409','11020902','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000010','00000000000000000002','IB010','','F01','02','AC','11021001','12040410','11021002','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000011','00000000000000000002','IB011','','F01','02','AC','11021101','12040411','11021102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000012','00000000000000000002','IB012','','F01','02','AC','11021201','12040412','11021202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000013','00000000000000000002','IB013','','F01','02','AC','11021301','12040413','11021302','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000014','00000000000000000002','IB014','','F01','02','AC','11021401','12040414','11021402','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000015','00000000000000000002','IB015','','F01','02','AC','11021601','12040416','11021602','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000016','00000000000000000002','IB099','','F01','02','AC','11021501','12040415','11021502','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000017','00000000000000000002','JW001','','F01','02','AC','11029101','12040491','11029102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000018','00000000000000000002','JW099','','F01','02','AC','11029201','12040492','11029202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000019','00000000000000000002','SH001','','F01','02','AC','11023101','12040431','11023102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000020','00000000000000000002','SH002','','F01','02','AC','11023201','12040432','11023202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000021','00000000000000000002','SH003','','F01','02','AC','11023301','12040433','11023302','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000022','00000000000000000002','SH004','','F01','02','AC','11023401','12040434','11023402','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000023','00000000000000000002','SH005','','F01','02','AC','11023501','12040435','11023502','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000024','00000000000000000002','SH006','','F01','02','AC','11024301','12040443','11024302','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000025','00000000000000000002','SH007','','F01','02','AC','11023601','12040436','11023602','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000026','00000000000000000002','SH008','','F01','02','AC','11023701','12040437','11023702','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000027','00000000000000000002','SH009','','F01','02','AC','11023801','12040438','11023802','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000028','00000000000000000002','SH010','','F01','02','AC','11023901','12040439','11023902','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000029','00000000000000000002','SH011','','F01','02','AC','11024001','12040440','11024002','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000030','00000000000000000002','SH012','','F01','02','AC','11024101','12040441','11024102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000031','00000000000000000002','SH099','','F01','02','AC','11024201','12040442','11024202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000032','00000000000000000002','SZ001','','F01','02','AC','11026101','12040461','11026102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000033','00000000000000000002','SZ002','','F01','02','AC','11026201','12040462','11026202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000034','00000000000000000002','SZ003','','F01','02','AC','11026301','12040463','11026302','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000035','00000000000000000002','SZ004','','F01','02','AC','11026401','12040464','11026402','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000036','00000000000000000002','SZ005','','F01','02','AC','11026501','12040465','11026502','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000037','00000000000000000002','SZ006','','F01','02','AC','11027401','12040474','11027402','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000038','00000000000000000002','SZ007','','F01','02','AC','11026601','12040466','11026602','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000039','00000000000000000002','SZ008','','F01','02','AC','11026701','12040467','11026702','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000040','00000000000000000002','SZ009','','F01','02','AC','11026801','12040468','11026802','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000041','00000000000000000002','SZ010','','F01','02','AC','11026901','12040469','11026902','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000042','00000000000000000002','SZ011','','F01','02','AC','11027001','12040470','11027002','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000043','00000000000000000002','SZ012','','F01','02','AC','11027101','12040471','11027102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000044','00000000000000000002','SZ013','','F01','02','AC','11027201','12040472','11027202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000045','00000000000000000002','SZ099','','F01','02','AC','11027301','12040473','11027302','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000046','00000000000000000003','IB001','','F01','02','AC','11020101','12040401','11020102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000047','00000000000000000003','IB002','','F01','02','AC','11020201','12040402','11020202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000048','00000000000000000003','IB003','','F01','02','AC','11020301','12040403','11020302','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000049','00000000000000000003','IB004','','F01','02','AC','11020401','12040404','11020402','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000050','00000000000000000003','IB005','','F01','02','AC','11020501','12040405','11020502','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000051','00000000000000000003','IB006','','F01','02','AC','11020601','12040406','11020602','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000052','00000000000000000003','IB007','','F01','02','AC','11020701','12040407','11020702','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000053','00000000000000000003','IB008','','F01','02','AC','11020801','12040408','11020802','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000054','00000000000000000003','IB009','','F01','02','AC','11020901','12040409','11020902','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000055','00000000000000000003','IB010','','F01','02','AC','11021001','12040410','11021002','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000056','00000000000000000003','IB011','','F01','02','AC','11021101','12040411','11021102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000057','00000000000000000003','IB012','','F01','02','AC','11021201','12040412','11021202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000058','00000000000000000003','IB013','','F01','02','AC','11021301','12040413','11021302','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000059','00000000000000000003','IB014','','F01','02','AC','11021401','12040414','11021402','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000060','00000000000000000003','IB015','','F01','02','AC','11021601','12040416','11021602','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000061','00000000000000000003','IB099','','F01','02','AC','11021501','12040415','11021502','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000062','00000000000000000003','JW001','','F01','02','AC','11029101','12040491','11029102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000063','00000000000000000003','JW099','','F01','02','AC','11029201','12040492','11029202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000064','00000000000000000003','SH001','','F01','02','AC','11023101','12040431','11023102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000065','00000000000000000003','SH002','','F01','02','AC','11023201','12040432','11023202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000066','00000000000000000003','SH003','','F01','02','AC','11023301','12040433','11023302','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000067','00000000000000000003','SH004','','F01','02','AC','11023401','12040434','11023402','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000068','00000000000000000003','SH005','','F01','02','AC','11023501','12040435','11023502','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000069','00000000000000000003','SH006','','F01','02','AC','11024301','12040443','11024302','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000070','00000000000000000003','SH007','','F01','02','AC','11023601','12040436','11023602','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000071','00000000000000000003','SH008','','F01','02','AC','11023701','12040437','11023702','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000072','00000000000000000003','SH009','','F01','02','AC','11023801','12040438','11023802','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000073','00000000000000000003','SH010','','F01','02','AC','11023901','12040439','11023902','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000074','00000000000000000003','SH011','','F01','02','AC','11024001','12040440','11024002','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000075','00000000000000000003','SH012','','F01','02','AC','11024101','12040441','11024102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000076','00000000000000000003','SH099','','F01','02','AC','11024201','12040442','11024202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000077','00000000000000000003','SZ001','','F01','02','AC','11026101','12040461','11026102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000078','00000000000000000003','SZ002','','F01','02','AC','11026201','12040462','11026202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000079','00000000000000000003','SZ003','','F01','02','AC','11026301','12040463','11026302','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000080','00000000000000000003','SZ004','','F01','02','AC','11026401','12040464','11026402','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000081','00000000000000000003','SZ005','','F01','02','AC','11026501','12040465','11026502','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000082','00000000000000000003','SZ006','','F01','02','AC','11027401','12040474','11027402','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000083','00000000000000000003','SZ007','','F01','02','AC','11026601','12040466','11026602','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000084','00000000000000000003','SZ008','','F01','02','AC','11026701','12040467','11026702','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000085','00000000000000000003','SZ009','','F01','02','AC','11026801','12040468','11026802','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000086','00000000000000000003','SZ010','','F01','02','AC','11026901','12040469','11026902','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000087','00000000000000000003','SZ011','','F01','02','AC','11027001','12040470','11027002','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000088','00000000000000000003','SZ012','','F01','02','AC','11027101','12040471','11027102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000089','00000000000000000003','SZ013','','F01','02','AC','11027201','12040472','11027202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000090','00000000000000000003','SZ099','','F01','02','AC','11027301','12040473','11027302','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000091','00000000000000000002','IB001','','F01','02','FVTPL','11030101','12040501','','11030199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000092','00000000000000000002','IB002','','F01','02','FVTPL','11030201','12040502','','11030299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000093','00000000000000000002','IB003','','F01','02','FVTPL','11030301','12040503','','11030399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000094','00000000000000000002','IB004','','F01','02','FVTPL','11030401','12040504','','11030499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000095','00000000000000000002','IB005','','F01','02','FVTPL','11030501','12040505','','11030599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000096','00000000000000000002','IB006','','F01','02','FVTPL','11030601','12040506','','11030699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000097','00000000000000000002','IB007','','F01','02','FVTPL','11030701','12040507','','11030799','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000098','00000000000000000002','IB008','','F01','02','FVTPL','11030801','12040508','','11030899','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000099','00000000000000000002','IB009','','F01','02','FVTPL','11030901','12040509','','11030999','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000100','00000000000000000002','IB010','','F01','02','FVTPL','11031001','12040510','','11031099','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000101','00000000000000000002','IB011','','F01','02','FVTPL','11031101','12040511','','11031199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000102','00000000000000000002','IB012','','F01','02','FVTPL','11031201','12040512','','11031299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000103','00000000000000000002','IB013','','F01','02','FVTPL','11031301','12040513','','11031399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000104','00000000000000000002','IB014','','F01','02','FVTPL','11031401','12040514','','11031499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000105','00000000000000000002','IB015','','F01','02','FVTPL','11031601','12040516','','11031699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000106','00000000000000000002','IB099','','F01','02','FVTPL','11031501','12040515','','11031599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000107','00000000000000000002','JW001','','F01','02','FVTPL','11039101','12040591','','11039199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000108','00000000000000000002','JW099','','F01','02','FVTPL','11039201','12040592','','11039299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000109','00000000000000000002','SH001','','F01','02','FVTPL','11033101','12040531','','11033199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000110','00000000000000000002','SH002','','F01','02','FVTPL','11033201','12040532','','11033299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000111','00000000000000000002','SH003','','F01','02','FVTPL','11033301','12040533','','11033399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000112','00000000000000000002','SH004','','F01','02','FVTPL','11033401','12040534','','11033499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000113','00000000000000000002','SH005','','F01','02','FVTPL','11033501','12040535','','11033599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000114','00000000000000000002','SH006','','F01','02','FVTPL','11034301','12040543','','11034399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000115','00000000000000000002','SH007','','F01','02','FVTPL','11033601','12040536','','11033699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000116','00000000000000000002','SH008','','F01','02','FVTPL','11033701','12040537','','11033799','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000117','00000000000000000002','SH009','','F01','02','FVTPL','11033801','12040538','','11033899','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000118','00000000000000000002','SH010','','F01','02','FVTPL','11033901','12040539','','11033999','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000119','00000000000000000002','SH011','','F01','02','FVTPL','11034001','12040540','','11034099','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000120','00000000000000000002','SH012','','F01','02','FVTPL','11034101','12040541','','11034199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000121','00000000000000000002','SH099','','F01','02','FVTPL','11034201','12040542','','11034299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000122','00000000000000000002','SZ001','','F01','02','FVTPL','11036101','12040561','','11036199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000123','00000000000000000002','SZ002','','F01','02','FVTPL','11036201','12040562','','11036299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000124','00000000000000000002','SZ003','','F01','02','FVTPL','11036301','12040563','','11036399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000125','00000000000000000002','SZ004','','F01','02','FVTPL','11036401','12040564','','11036499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000126','00000000000000000002','SZ005','','F01','02','FVTPL','11036501','12040565','','11036599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000127','00000000000000000002','SZ006','','F01','02','FVTPL','11037401','12040574','','11037499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000128','00000000000000000002','SZ007','','F01','02','FVTPL','11036601','12040566','','11036699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000129','00000000000000000002','SZ008','','F01','02','FVTPL','11036701','12040567','','11036799','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000130','00000000000000000002','SZ009','','F01','02','FVTPL','11036801','12040568','','11036899','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000131','00000000000000000002','SZ010','','F01','02','FVTPL','11036901','12040569','','11036999','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000132','00000000000000000002','SZ011','','F01','02','FVTPL','11037001','12040570','','11037099','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000133','00000000000000000002','SZ012','','F01','02','FVTPL','11037101','12040571','','11037199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000134','00000000000000000002','SZ013','','F01','02','FVTPL','11037201','12040572','','11037299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000135','00000000000000000002','SZ099','','F01','02','FVTPL','11037301','12040573','','11037399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000136','00000000000000000003','IB001','','F01','02','FVTPL','11030101','12040501','','11030199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000137','00000000000000000003','IB002','','F01','02','FVTPL','11030201','12040502','','11030299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000138','00000000000000000003','IB003','','F01','02','FVTPL','11030301','12040503','','11030399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000139','00000000000000000003','IB004','','F01','02','FVTPL','11030401','12040504','','11030499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000140','00000000000000000003','IB005','','F01','02','FVTPL','11030501','12040505','','11030599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000141','00000000000000000003','IB006','','F01','02','FVTPL','11030601','12040506','','11030699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000142','00000000000000000003','IB007','','F01','02','FVTPL','11030701','12040507','','11030799','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000143','00000000000000000003','IB008','','F01','02','FVTPL','11030801','12040508','','11030899','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000144','00000000000000000003','IB009','','F01','02','FVTPL','11030901','12040509','','11030999','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000145','00000000000000000003','IB010','','F01','02','FVTPL','11031001','12040510','','11031099','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000146','00000000000000000003','IB011','','F01','02','FVTPL','11031101','12040511','','11031199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000147','00000000000000000003','IB012','','F01','02','FVTPL','11031201','12040512','','11031299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000148','00000000000000000003','IB013','','F01','02','FVTPL','11031301','12040513','','11031399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000149','00000000000000000003','IB014','','F01','02','FVTPL','11031401','12040514','','11031499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000150','00000000000000000003','IB015','','F01','02','FVTPL','11031601','12040516','','11031699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000151','00000000000000000003','IB099','','F01','02','FVTPL','11031501','12040515','','11031599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000152','00000000000000000003','JW001','','F01','02','FVTPL','11039101','12040591','','11039199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000153','00000000000000000003','JW099','','F01','02','FVTPL','11039201','12040592','','11039299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000154','00000000000000000003','SH001','','F01','02','FVTPL','11033101','12040531','','11033199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000155','00000000000000000003','SH002','','F01','02','FVTPL','11033201','12040532','','11033299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000156','00000000000000000003','SH003','','F01','02','FVTPL','11033301','12040533','','11033399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000157','00000000000000000003','SH004','','F01','02','FVTPL','11033401','12040534','','11033499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000158','00000000000000000003','SH005','','F01','02','FVTPL','11033501','12040535','','11033599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000159','00000000000000000003','SH006','','F01','02','FVTPL','11034301','12040543','','11034399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000160','00000000000000000003','SH007','','F01','02','FVTPL','11033601','12040536','','11033699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000161','00000000000000000003','SH008','','F01','02','FVTPL','11033701','12040537','','11033799','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000162','00000000000000000003','SH009','','F01','02','FVTPL','11033801','12040538','','11033899','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000163','00000000000000000003','SH010','','F01','02','FVTPL','11033901','12040539','','11033999','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000164','00000000000000000003','SH011','','F01','02','FVTPL','11034001','12040540','','11034099','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000165','00000000000000000003','SH012','','F01','02','FVTPL','11034101','12040541','','11034199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000166','00000000000000000003','SH099','','F01','02','FVTPL','11034201','12040542','','11034299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000167','00000000000000000003','SZ001','','F01','02','FVTPL','11036101','12040561','','11036199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000168','00000000000000000003','SZ002','','F01','02','FVTPL','11036201','12040562','','11036299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000169','00000000000000000003','SZ003','','F01','02','FVTPL','11036301','12040563','','11036399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000170','00000000000000000003','SZ004','','F01','02','FVTPL','11036401','12040564','','11036499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000171','00000000000000000003','SZ005','','F01','02','FVTPL','11036501','12040565','','11036599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000172','00000000000000000003','SZ006','','F01','02','FVTPL','11037401','12040574','','11037499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000173','00000000000000000003','SZ007','','F01','02','FVTPL','11036601','12040566','','11036699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000174','00000000000000000003','SZ008','','F01','02','FVTPL','11036701','12040567','','11036799','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000175','00000000000000000003','SZ009','','F01','02','FVTPL','11036801','12040568','','11036899','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000176','00000000000000000003','SZ010','','F01','02','FVTPL','11036901','12040569','','11036999','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000177','00000000000000000003','SZ011','','F01','02','FVTPL','11037001','12040570','','11037099','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000178','00000000000000000003','SZ012','','F01','02','FVTPL','11037101','12040571','','11037199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000179','00000000000000000003','SZ013','','F01','02','FVTPL','11037201','12040572','','11037299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000180','00000000000000000003','SZ099','','F01','02','FVTPL','11037301','12040573','','11037399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000181','00000000000000000002','IB001','','F01','02','FVOCI','11040101','12040601','11040102','11040199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000182','00000000000000000002','IB002','','F01','02','FVOCI','11040201','12040602','11040202','11040299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000183','00000000000000000002','IB003','','F01','02','FVOCI','11040301','12040603','11040302','11040399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000184','00000000000000000002','IB004','','F01','02','FVOCI','11040401','12040604','11040402','11040499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000185','00000000000000000002','IB005','','F01','02','FVOCI','11040501','12040605','11040502','11040599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000186','00000000000000000002','IB006','','F01','02','FVOCI','11040601','12040606','11040602','11040699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000187','00000000000000000002','IB007','','F01','02','FVOCI','11040701','12040607','11040702','11040799','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000188','00000000000000000002','IB008','','F01','02','FVOCI','11040801','12040608','11040802','11040899','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000189','00000000000000000002','IB009','','F01','02','FVOCI','11040901','12040609','11040902','11040999','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000190','00000000000000000002','IB010','','F01','02','FVOCI','11041001','12040610','11041002','11041099','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000191','00000000000000000002','IB011','','F01','02','FVOCI','11041101','12040611','11041102','11041199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000192','00000000000000000002','IB012','','F01','02','FVOCI','11041201','12040612','11041202','11041299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000193','00000000000000000002','IB013','','F01','02','FVOCI','11041301','12040613','11041302','11041399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000194','00000000000000000002','IB014','','F01','02','FVOCI','11041401','12040614','11041402','11041499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000195','00000000000000000002','IB015','','F01','02','FVOCI','11041601','12040616','11041602','11041699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000196','00000000000000000002','IB099','','F01','02','FVOCI','11041501','12040615','11041502','11041599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000197','00000000000000000002','JW001','','F01','02','FVOCI','11049101','12040691','11049102','11049199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000198','00000000000000000002','JW099','','F01','02','FVOCI','11049201','12040692','11049202','11049299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000199','00000000000000000002','SH001','','F01','02','FVOCI','11043101','12040631','11043102','11043199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000200','00000000000000000002','SH002','','F01','02','FVOCI','11043201','12040632','11043202','11043299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000201','00000000000000000002','SH003','','F01','02','FVOCI','11043301','12040633','11043302','11043399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000202','00000000000000000002','SH004','','F01','02','FVOCI','11043401','12040634','11043402','11043499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000203','00000000000000000002','SH005','','F01','02','FVOCI','11043501','12040635','11043502','11043599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000204','00000000000000000002','SH006','','F01','02','FVOCI','11044301','12040643','11044302','11044399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000205','00000000000000000002','SH007','','F01','02','FVOCI','11043601','12040636','11043602','11043699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000206','00000000000000000002','SH008','','F01','02','FVOCI','11043701','12040637','11043702','11043799','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000207','00000000000000000002','SH009','','F01','02','FVOCI','11043801','12040638','11043802','11043899','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000208','00000000000000000002','SH010','','F01','02','FVOCI','11043901','12040639','11043902','11043999','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000209','00000000000000000002','SH011','','F01','02','FVOCI','11044001','12040640','11044002','11044099','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000210','00000000000000000002','SH012','','F01','02','FVOCI','11044101','12040641','11044102','11044199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000211','00000000000000000002','SH099','','F01','02','FVOCI','11044201','12040642','11044202','11044299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000212','00000000000000000002','SZ001','','F01','02','FVOCI','11046101','12040661','11046102','11046199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000213','00000000000000000002','SZ002','','F01','02','FVOCI','11046201','12040662','11046202','11046299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000214','00000000000000000002','SZ003','','F01','02','FVOCI','11046301','12040663','11046302','11046399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000215','00000000000000000002','SZ004','','F01','02','FVOCI','11046401','12040664','11046402','11046499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000216','00000000000000000002','SZ005','','F01','02','FVOCI','11046501','12040665','11046502','11046599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000217','00000000000000000002','SZ006','','F01','02','FVOCI','11047401','12040674','11047402','11047499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000218','00000000000000000002','SZ007','','F01','02','FVOCI','11046601','12040666','11046602','11046699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000219','00000000000000000002','SZ008','','F01','02','FVOCI','11046701','12040667','11046702','11046799','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000220','00000000000000000002','SZ009','','F01','02','FVOCI','11046801','12040668','11046802','11046899','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000221','00000000000000000002','SZ010','','F01','02','FVOCI','11046901','12040669','11046902','11046999','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000222','00000000000000000002','SZ011','','F01','02','FVOCI','11047001','12040670','11047002','11047099','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000223','00000000000000000002','SZ012','','F01','02','FVOCI','11047101','12040671','11047102','11047199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000224','00000000000000000002','SZ013','','F01','02','FVOCI','11047201','12040672','11047202','11047299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000225','00000000000000000002','SZ099','','F01','02','FVOCI','11047301','12040673','11047302','11047399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000226','00000000000000000003','IB001','','F01','02','FVOCI','11040101','12040601','11040102','11040199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000227','00000000000000000003','IB002','','F01','02','FVOCI','11040201','12040602','11040202','11040299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000228','00000000000000000003','IB003','','F01','02','FVOCI','11040301','12040603','11040302','11040399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000229','00000000000000000003','IB004','','F01','02','FVOCI','11040401','12040604','11040402','11040499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000230','00000000000000000003','IB005','','F01','02','FVOCI','11040501','12040605','11040502','11040599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000231','00000000000000000003','IB006','','F01','02','FVOCI','11040601','12040606','11040602','11040699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000232','00000000000000000003','IB007','','F01','02','FVOCI','11040701','12040607','11040702','11040799','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000233','00000000000000000003','IB008','','F01','02','FVOCI','11040801','12040608','11040802','11040899','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000234','00000000000000000003','IB009','','F01','02','FVOCI','11040901','12040609','11040902','11040999','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000235','00000000000000000003','IB010','','F01','02','FVOCI','11041001','12040610','11041002','11041099','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000236','00000000000000000003','IB011','','F01','02','FVOCI','11041101','12040611','11041102','11041199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000237','00000000000000000003','IB012','','F01','02','FVOCI','11041201','12040612','11041202','11041299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000238','00000000000000000003','IB013','','F01','02','FVOCI','11041301','12040613','11041302','11041399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000239','00000000000000000003','IB014','','F01','02','FVOCI','11041401','12040614','11041402','11041499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000240','00000000000000000003','IB015','','F01','02','FVOCI','11041601','12040616','11041602','11041699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000241','00000000000000000003','IB099','','F01','02','FVOCI','11041501','12040615','11041502','11041599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000242','00000000000000000003','JW001','','F01','02','FVOCI','11049101','12040691','11049102','11049199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000243','00000000000000000003','JW099','','F01','02','FVOCI','11049201','12040692','11049202','11049299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000244','00000000000000000003','SH001','','F01','02','FVOCI','11043101','12040631','11043102','11043199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000245','00000000000000000003','SH002','','F01','02','FVOCI','11043201','12040632','11043202','11043299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000246','00000000000000000003','SH003','','F01','02','FVOCI','11043301','12040633','11043302','11043399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000247','00000000000000000003','SH004','','F01','02','FVOCI','11043401','12040634','11043402','11043499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000248','00000000000000000003','SH005','','F01','02','FVOCI','11043501','12040635','11043502','11043599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000249','00000000000000000003','SH006','','F01','02','FVOCI','11044301','12040643','11044302','11044399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000250','00000000000000000003','SH007','','F01','02','FVOCI','11043601','12040636','11043602','11043699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000251','00000000000000000003','SH008','','F01','02','FVOCI','11043701','12040637','11043702','11043799','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000252','00000000000000000003','SH009','','F01','02','FVOCI','11043801','12040638','11043802','11043899','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000253','00000000000000000003','SH010','','F01','02','FVOCI','11043901','12040639','11043902','11043999','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000254','00000000000000000003','SH011','','F01','02','FVOCI','11044001','12040640','11044002','11044099','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000255','00000000000000000003','SH012','','F01','02','FVOCI','11044101','12040641','11044102','11044199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000256','00000000000000000003','SH099','','F01','02','FVOCI','11044201','12040642','11044202','11044299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000257','00000000000000000003','SZ001','','F01','02','FVOCI','11046101','12040661','11046102','11046199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000258','00000000000000000003','SZ002','','F01','02','FVOCI','11046201','12040662','11046202','11046299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000259','00000000000000000003','SZ003','','F01','02','FVOCI','11046301','12040663','11046302','11046399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000260','00000000000000000003','SZ004','','F01','02','FVOCI','11046401','12040664','11046402','11046499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000261','00000000000000000003','SZ005','','F01','02','FVOCI','11046501','12040665','11046502','11046599','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000262','00000000000000000003','SZ006','','F01','02','FVOCI','11047401','12040674','11047402','11047499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000263','00000000000000000003','SZ007','','F01','02','FVOCI','11046601','12040666','11046602','11046699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000264','00000000000000000003','SZ008','','F01','02','FVOCI','11046701','12040667','11046702','11046799','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000265','00000000000000000003','SZ009','','F01','02','FVOCI','11046801','12040668','11046802','11046899','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000266','00000000000000000003','SZ010','','F01','02','FVOCI','11046901','12040669','11046902','11046999','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000267','00000000000000000003','SZ011','','F01','02','FVOCI','11047001','12040670','11047002','11047099','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000268','00000000000000000003','SZ012','','F01','02','FVOCI','11047101','12040671','11047102','11047199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000269','00000000000000000003','SZ013','','F01','02','FVOCI','11047201','12040672','11047202','11047299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000270','00000000000000000003','SZ099','','F01','02','FVOCI','11047301','12040673','11047302','11047399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000271','00000000000000000002','01','','F09','02','','10030201','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000272','00000000000000000002','02','','F09','02','','10030202','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000273','00000000000000000002','03','','F09','02','','10030203','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000274','00000000000000000002','04','','F09','02','','10030204','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000275','00000000000000000002','05','','F09','02','','10030205','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000276','00000000000000000002','06','','F09','02','','10030206','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000277','00000000000000000002','07','','F09','02','','10030207','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000278','00000000000000000002','08','','F09','02','','10030208','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000279','00000000000000000002','09','','F09','02','','10030209','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000280','00000000000000000002','10','','F09','02','','10030210','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000281','00000000000000000003','01','','F09','02','','10030201','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000282','00000000000000000003','02','','F09','02','','10030202','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000283','00000000000000000003','03','','F09','02','','10030203','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000284','00000000000000000003','04','','F09','02','','10030204','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000285','00000000000000000003','05','','F09','02','','10030205','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000286','00000000000000000003','06','','F09','02','','10030206','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000287','00000000000000000003','07','','F09','02','','10030207','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000288','00000000000000000003','08','','F09','02','','10030208','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000289','00000000000000000003','09','','F09','02','','10030209','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000290','00000000000000000003','10','','F09','02','','10030210','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000291','00000000000000000002','01','','F21','02','','10030201','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000292','00000000000000000002','02','','F21','02','','10030202','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000293','00000000000000000002','03','','F21','02','','10030203','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000294','00000000000000000002','04','','F21','02','','10030204','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000295','00000000000000000002','05','','F21','02','','10030205','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000296','00000000000000000002','06','','F21','02','','10030206','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000297','00000000000000000002','07','','F21','02','','10030207','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000298','00000000000000000002','08','','F21','02','','10030208','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000299','00000000000000000002','09','','F21','02','','10030209','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000300','00000000000000000002','10','','F21','02','','10030210','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000301','00000000000000000003','01','','F21','02','','10030201','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000302','00000000000000000003','02','','F21','02','','10030202','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000303','00000000000000000003','03','','F21','02','','10030203','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000304','00000000000000000003','04','','F21','02','','10030204','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000305','00000000000000000003','05','','F21','02','','10030205','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000306','00000000000000000003','06','','F21','02','','10030206','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000307','00000000000000000003','07','','F21','02','','10030207','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000308','00000000000000000003','08','','F21','02','','10030208','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000309','00000000000000000003','09','','F21','02','','10030209','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000310','00000000000000000003','10','','F21','02','','10030210','12040701','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000311','00000000000000000002','','','F08','02','','20010201','22310101','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000312','00000000000000000003','','','F08','02','','20010201','22310101','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000313','00000000000000000002','01','','F07','02','','10030101','12040201','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000314','00000000000000000002','02','','F07','02','','10030102','12040202','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000315','00000000000000000002','03','','F07','02','','10030103','12040203','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000316','00000000000000000002','04','','F07','02','','10030104','12040204','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000317','00000000000000000002','05','','F07','02','','10030105','12040205','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000318','00000000000000000002','06','','F07','02','','10030106','12040206','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000319','00000000000000000002','07','','F07','02','','10030107','12040207','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000320','00000000000000000002','08','','F07','02','','10030108','12040208','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000321','00000000000000000002','09','','F07','02','','10030109','12040209','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000322','00000000000000000002','10','','F07','02','','10030110','12040210','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000323','00000000000000000003','01','','F07','02','','10030101','12040201','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000324','00000000000000000003','02','','F07','02','','10030102','12040202','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000325','00000000000000000003','03','','F07','02','','10030103','12040203','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000326','00000000000000000003','04','','F07','02','','10030104','12040204','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000327','00000000000000000003','05','','F07','02','','10030105','12040205','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000328','00000000000000000003','06','','F07','02','','10030106','12040206','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000329','00000000000000000003','07','','F07','02','','10030107','12040207','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000330','00000000000000000003','08','','F07','02','','10030108','12040208','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000331','00000000000000000003','09','','F07','02','','10030109','12040209','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000332','00000000000000000003','10','','F07','02','','10030110','12040210','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000333','00000000000000000002','01','','F03','02','','12020101','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000334','00000000000000000002','03','','F03','02','','12021101','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000335','00000000000000000002','04','','F03','02','','12022101','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000336','00000000000000000002','99','','F03','02','','12023101','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000337','00000000000000000003','01','','F03','02','','12020101','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000338','00000000000000000003','03','','F03','02','','12021101','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000339','00000000000000000003','04','','F03','02','','12022101','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000340','00000000000000000003','99','','F03','02','','12023101','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000341','00000000000000000002','01','','F02','02','','22020101','22310201','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000342','00000000000000000002','03','','F02','02','','22021101','22310201','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000343','00000000000000000002','04','','F02','02','','22022101','22310201','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000344','00000000000000000002','99','','F02','02','','22023101','22310201','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000345','00000000000000000003','01','','F02','02','','22020101','22310201','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000346','00000000000000000003','03','','F02','02','','22021101','22310201','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000347','00000000000000000003','04','','F02','02','','22022101','22310201','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000348','00000000000000000003','99','','F02','02','','22023101','22310201','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000349','00000000000000000002','01','','F05','02','','12020201','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000350','00000000000000000002','03','','F05','02','','12021201','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000351','00000000000000000002','04','','F05','02','','12022201','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000352','00000000000000000002','99','','F05','02','','12023201','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000353','00000000000000000003','01','','F05','02','','12020201','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000354','00000000000000000003','03','','F05','02','','12021201','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000355','00000000000000000003','04','','F05','02','','12022201','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000356','00000000000000000003','99','','F05','02','','12023201','12040301','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000357','00000000000000000002','01','','F04','02','','22020201','22310201','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000358','00000000000000000003','01','','F04','02','','22020201','22310201','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000359','00000000000000000002','','01','F17','02','','11060101','12040901','11060102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000360','00000000000000000002','','02','F17','02','','11060201','12040902','11060202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000361','00000000000000000003','','01','F17','02','','11060101','12040901','11060102','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000362','00000000000000000003','','02','F17','02','','11060201','12040902','11060202','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000363','00000000000000000002','','01','F06','03','','11050101','12031101','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000364','00000000000000000002','','02','F06','03','','11050501','12031102','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000365','00000000000000000003','','01','F06','03','','11050101','12031101','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000366','00000000000000000003','','02','F06','03','','11050501','12031102','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000367','00000000000000000002','01','01','F06','01','','11050301','12031105','','11050399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000368','00000000000000000002','02','01','F06','01','','11050401','12031106','','11050499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000369','00000000000000000002','03','01','F06','01','','11050201','12031103','','11050299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000370','00000000000000000002','','02','F06','01','','11050601','12031104','','11050699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000371','00000000000000000003','01','01','F06','01','','11050301','12031105','','11050399','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000372','00000000000000000003','02','01','F06','01','','11050401','12031106','','11050499','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000373','00000000000000000003','03','01','F06','01','','11050201','12031103','','11050299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000374','00000000000000000003','','02','F06','01','','11050601','12031104','','11050699','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000375','00000000000000000002','','01','F17','01','','11070101','12033101','','11070199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000376','00000000000000000002','','02','F17','01','','11070201','12033102','','11070299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000377','00000000000000000003','','02','F17','01','','11070201','12033102','','11070299','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000378','00000000000000000003','','01','F17','01','','11070101','12033101','','11070199','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000379','00000000000000000002','','','A01','','','10020101','12040101','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000380','00000000000000000003','','','A01','','','10020101','12040101','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000381','00000000000000000002','','','A04','','','10210101','12040102','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
insert into TRS_SUBJECT_RELATION values ('SYS000382','00000000000000000003','','','A04','','','10210101','12040102','','','ADMIN','',to_date('2018/12/20', 'yyyy/mm/dd'), 'ADMIN',to_date('2018/12/20', 'yyyy/mm/dd'));
/** 提交人：吴洁  20181224 Remark：数据移植对应科目关系初始化 end**/
/**                                                2018-12-24                                      end**/


/** add                                   20181225                                start**/
/**提交人：程中 Remark：修改产品固定管理费计提名称 start**/
update sys_batch_task set task_name = '全产品固定管理费计提-账务明细处理' where task_id = '33' and bean_name = 'fixedFeeCalBookTask';
/**提交人：程中 Remark：修改产品固定管理费计提名称 end**/

/**提交人：王之昂  start**/
update BOK_BOOKSET_EXT set PARAM_VALUE = '02' where PARAM_CODE = 'PRD_INT_ACCR_TYPE';
/**提交人：王之昂  end**/

/** add                                   20181225                                end**/


/** add                                   20181227                                start**/
/**提交人：魏鹏  start**/
delete from BOK_BATCH_TASK_GRP where TASK_GRP_ID ='63' and TASK_GRP_NAME ='批处理文件上传'; 
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('63','批处理文件上传','task','9','500',3,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
delete from SYS_BATCH_TASK  where TASK_ID ='500' and BEAN_NAME ='valFileUploadTask';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('500','VAL','批处理文件上传处理','','valFileUploadTask','200','system',to_date('2018/10/17', 'yyyy/mm/dd'), 'system',to_date('2018/10/17', 'yyyy/mm/dd'), '');
/**提交人：魏鹏  end**/
/** add                                   20181227                                end**/

commit;

