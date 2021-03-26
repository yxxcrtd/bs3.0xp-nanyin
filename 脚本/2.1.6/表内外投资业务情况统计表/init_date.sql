--机构新增两个字段，三级行业和四级行业
delete from sys_datadict where dict_code='INSTITUTION_INDUSTRY_THIRD_LEVEL';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', 'TG', '机构所属行业三级分类', null, '02', 'system', null, sysdate, 'system', sysdate);
delete from sys_datadict where dict_code='INSTITUTION_INDUSTRY_FOURTH_LEVEL';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', 'TG', '机构所属行业四级分类', null, '02', 'system', null, sysdate, 'system', sysdate);


delete from sys_datadict_list where dict_code='INSTITUTION_INDUSTRY_THIRD_LEVEL';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '01', '中央银行服务', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '02', '商业银行', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '03', '政策性银行', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '04', '农村资金互助社', 4, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '05', '融资租赁服务', 5, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '06', '财务公司服务', 6, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '07', '典当', 7, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '08', '汽车金融公司', 8, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '09', '小额贷款公司', 9, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '10', '消费金融公司', 10, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '11', '网络借贷', 11, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '12', '其他货币金融', 12, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '13', '证券市场服务', 13, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '14', '公开募集证券投资基金', 14, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '15', '非公开募集证券投资基金', 15, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '16', '期货市场服务', 16, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '17', '资本投资服务', 17, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '18', '其他', 18, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '19', '金融信托与管理服务', 19, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '20', '控股公司服务', 20, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '21', '非金融机构支付服务', 21, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '22', '金融信息服务', 22, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '23', '金融资产管理公司', 23, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '24', '其他未列明金融业', 24, '0', 'system', null, sysdate, 'system', sysdate, null);


delete from sys_datadict_list where dict_code='INSTITUTION_INDUSTRY_FOURTH_LEVEL';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '01', '国有商业银行', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '02', '股份制银行', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '03', '城市商业银行', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '04', '农村商业银行', 4, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '05', '邮政储蓄机构', 5, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '06', '直销银行（百信银行）', 6, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '07', '外资银行', 7, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '08', '信托公司', 8, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '09', '其他', 9, '0', 'system', null, sysdate, 'system', sysdate, null);



delete from sys_datadict_list_link where dict_code='INSTITUTION_INDUSTRY_THIRD_LEVEL' and LINKED_DICT_CODE='INSTITUTION_INDUSTRY_SECOND_LEVEL';
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '01', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J66', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '02', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J66', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '03', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J66', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '04', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J66', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '05', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J66', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '06', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J66', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '07', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J66', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '08', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J66', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '09', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J66', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '10', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J66', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '11', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J66', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '12', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J66', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '13', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J67', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '14', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J67', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '15', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J67', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '16', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J67', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '17', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J67', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '18', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J67', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '19', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J69', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '20', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J69', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '21', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J69', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '22', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J69', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '23', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J69', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_THIRD_LEVEL', '24', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 'J69', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list_link where dict_code='INSTITUTION_INDUSTRY_FOURTH_LEVEL' and LINKED_DICT_CODE='INSTITUTION_INDUSTRY_THIRD_LEVEL';
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '01', 'INSTITUTION_INDUSTRY_THIRD_LEVEL', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '02', 'INSTITUTION_INDUSTRY_THIRD_LEVEL', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '03', 'INSTITUTION_INDUSTRY_THIRD_LEVEL', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '04', 'INSTITUTION_INDUSTRY_THIRD_LEVEL', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '05', 'INSTITUTION_INDUSTRY_THIRD_LEVEL', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '06', 'INSTITUTION_INDUSTRY_THIRD_LEVEL', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '07', 'INSTITUTION_INDUSTRY_THIRD_LEVEL', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '08', 'INSTITUTION_INDUSTRY_THIRD_LEVEL', '19', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INSTITUTION_INDUSTRY_FOURTH_LEVEL', '09', 'INSTITUTION_INDUSTRY_THIRD_LEVEL', '19', 'system', null, sysdate, 'system', sysdate);





--本地的认定规则
delete from sys_para where PARA_CODE='SVR_LOCAL_AFFIRM_RULE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, UPDATE_FLG, INSTITUTION_CODE)
values ('SVR', 'SVR_LOCAL_AFFIRM_RULE', '110000;120000', '01', null, '银监本地的认定规则-所属省', 'system', null, sysdate, 'system', sysdate, '01', null);
--收益率取值配置
delete from sys_para where PARA_CODE='SVR_VALCONFIG_FLAG';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, UPDATE_FLG, INSTITUTION_CODE)
values ('SVR', 'SVR_VALCONFIG_FLAG', '01', '01', null, '银监收益率取值配置', 'system', null, sysdate, 'system', sysdate, '01', null);
--年化成本计算配置
delete from sys_para where PARA_CODE='SVR_VALCALC_FLAG';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, UPDATE_FLG, INSTITUTION_CODE)
values ('SVR', 'SVR_VALCALC_FLAG', '01', '01', null, '银监年化成本计算配置', 'system', null, sysdate, 'system', sysdate, '01', null);

--数据接入
delete from sys_batch_task where task_id='4100';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('4100', 'SVR', '产品利率基本信息接口数据接入', null, 'svrProdRateInfoInterfTaskService', 4100, 'system', null, sysdate, 'system', sysdate);

delete from chb_interf_config where interf_id='CHB_PRD_RATE';
insert into chb_interf_config (INTERF_ID, LAST_UPDATETIME, REMARK, INTERF_NAME, CONTENT_TYPE, CONTENT_SRC, REF_REPORT, SYS_SCOPE, STATUS)
values ('CHB_PRD_RATE', to_date('1900-01-01','yyyy-MM-dd'), '产品利率基本信息接口数据', null, 'F', 'D', null, null, 'E');

delete from sys_batch_task where task_id='4101';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('4101', 'SVR', '资产利率基本信息接口数据接入', null, 'svrAssetRateInfoInterfTaskService', 4101, 'system', null, sysdate, 'system', sysdate);

delete from chb_interf_config where interf_id='CHB_ASSET_RATE';
insert into chb_interf_config (INTERF_ID, LAST_UPDATETIME, REMARK, INTERF_NAME, CONTENT_TYPE, CONTENT_SRC, REF_REPORT, SYS_SCOPE, STATUS)
values ('CHB_ASSET_RATE', to_date('1900-01-01','yyyy-MM-dd'), '资产利率基本信息接口数据', null, 'F', 'D', null, null, 'E');









