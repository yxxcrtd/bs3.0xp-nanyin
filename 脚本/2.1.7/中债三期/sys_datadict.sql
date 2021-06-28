delete  from SYS_DATADICT_LIST  where dict_code='TRANS_TYPE';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TRANS_TYPE','01','全角半角',1,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TRANS_TYPE','02','生僻字',2,'0','system',null,sysdate,'system',sysdate);

delete  from sys_datadict  where dict_code='TRANS_TYPE';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TRANS_TYPE','SVR','生僻字类型',null,'02','system',null,sysdate,'system',sysdate);


delete  from sys_datadict  where dict_code='SVR_CHANNEL';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_CHANNEL','SVR','渠道（投资者明细）',null,'02','system',null,sysdate,'system',sysdate);

delete  from SYS_DATADICT_LIST  where dict_code='SVR_CHANNEL';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_CHANNEL','01','柜面',1,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_CHANNEL','02','ATM',2,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_CHANNEL','03','VTM',3,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_CHANNEL','04','POS',4,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_CHANNEL','05','网银',5,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_CHANNEL','06','手机银行',6,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_CHANNEL','99','其他',7,'0','system',null,sysdate,'system',sysdate);

delete  from sys_datadict  where dict_code='SVR_EQUDET_RESULT';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_EQUDET_RESULT','SVR','投资者份额检查结果',null,'02','system',null,sysdate,'system',sysdate);

delete  from SYS_DATADICT_LIST  where dict_code='SVR_EQUDET_RESULT';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_EQUDET_RESULT','01','有偏差',1,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_EQUDET_RESULT','02','无偏差',2,'0','system',null,sysdate,'system',sysdate);



delete  from  SYS_DATADICT_LIST  where DICT_CODE='SVR_REPORT_CODE' AND ITEM_CODE='26' ;
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_REPORT_CODE','26','投资者明细信息登记',26,'0','system',null,sysdate,'system',sysdate);


delete from sys_datadict where dict_code in
('SVR_ACADEMIC_DEGREE',
'SVR_BANK_REGU_BURE',
'SVR_BANK_REGU_BURE2',
'SVR_BELONGS_BANK',
'SVR_CHECK_INTERF_TYPE',
'SVR_EDUCATION',
'SVR_EMPLOYEE_TYPE',
'SVR_FIN_PROFESSIONAL_CRTF',
'SVR_INSTIT_SIZE',
'SVR_PROFESSIONAL_TITLE',
'SVR_REGIST_ORG3',
'SVR_REG_BUSI_TYPE',
'SVR_REG_EMPLOYEE_TYPE');

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', 'SVR', '监管机构', null, '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', 'SVR', '监管机构2', null, '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EMPLOYEE_TYPE', 'SVR', '从业人员类型', null, '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BELONGS_BANK', 'SVR', '所属总行或分支行', null, '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EDUCATION', 'SVR', '学历', null, '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ACADEMIC_DEGREE', 'SVR', '学位', null, '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PROFESSIONAL_TITLE', 'SVR', '专业技术职称', null, '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_FIN_PROFESSIONAL_CRTF', 'SVR', '理财专业证书', null, '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REG_BUSI_TYPE', 'SVR', '登记业务分类', null, '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REG_EMPLOYEE_TYPE', 'SVR', '登记人员类别', null, '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_CHECK_INTERF_TYPE', 'SVR', '校验接口类型', 'SVR_CHECK_INTERF_TYPE', '02', 'dubaojun', null, sysdate, 'dubaojun', sysdate);

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REGIST_ORG3', 'REP', '登记托管机构(中债)', null, '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_INSTIT_SIZE', 'SVR', '人行资产规模（中债）', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where dict_code in
('SVR_ACADEMIC_DEGREE',
'SVR_BANK_REGU_BURE',
'SVR_BANK_REGU_BURE2',
'SVR_BELONGS_BANK',
'SVR_CHECK_INTERF_TYPE',
'SVR_EDUCATION',
'SVR_EMPLOYEE_TYPE',
'SVR_FIN_PROFESSIONAL_CRTF',
'SVR_INSTIT_SIZE',
'SVR_PROFESSIONAL_TITLE',
'SVR_REGIST_ORG3',
'SVR_REG_BUSI_TYPE',
'SVR_REG_EMPLOYEE_TYPE');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ACADEMIC_DEGREE', '01', '学士', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ACADEMIC_DEGREE', '02', '硕士', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ACADEMIC_DEGREE', '03', '博士', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ACADEMIC_DEGREE', '99', '其他', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '01', '银保监会', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '02', '北京银保监局', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '03', '上海银保监局', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '04', '贵州银保监局', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '05', '宁夏银保监局', 5, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '06', '湖南银保监局', 6, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '07', '云南银保监局', 7, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '08', '福建银保监局', 8, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '09', '吉林银保监局', 9, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '10', '江苏银保监局', 10, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '11', '广东银保监局', 11, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '12', '青海银保监局', 12, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '13', '辽宁银保监局', 13, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '14', '湖北银保监局', 14, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '15', '海南银保监局', 15, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '16', '深圳银保监局', 16, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '17', '河南银保监局', 17, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '18', '甘肃银保监局', 18, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '19', '天津银保监局', 19, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '20', '山东银保监局', 20, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '21', '大连银保监局', 21, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '22', '宁波银保监局', 22, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '23', '广西银保监局', 23, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '24', '河北银保监局', 24, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '25', '西藏银保监局', 25, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '26', '浙江银保监局', 26, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '27', '四川银保监局', 27, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '28', '山西银保监局', 28, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '29', '安徽银保监局', 29, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '30', '重庆银保监局', 30, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '31', '黑龙江银保监局', 31, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '32', '陕西银保监局', 32, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '33', '江西银保监局', 33, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '34', '新疆银保监局', 34, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '35', '内蒙古银保监局', 35, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '36', '厦门银保监局', 36, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE', '37', '青岛银保监局', 37, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '02', '北京银保监局', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '03', '上海银保监局', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '04', '贵州银保监局', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '05', '宁夏银保监局', 5, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '06', '湖南银保监局', 6, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '07', '云南银保监局', 7, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '08', '福建银保监局', 8, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '09', '吉林银保监局', 9, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '10', '江苏银保监局', 10, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '11', '广东银保监局', 11, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '12', '青海银保监局', 12, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '13', '辽宁银保监局', 13, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '14', '湖北银保监局', 14, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '15', '海南银保监局', 15, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '16', '深圳银保监局', 16, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '17', '河南银保监局', 17, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '18', '甘肃银保监局', 18, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '19', '天津银保监局', 19, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '20', '山东银保监局', 20, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '21', '大连银保监局', 21, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '22', '宁波银保监局', 22, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '23', '广西银保监局', 23, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '24', '河北银保监局', 24, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '25', '西藏银保监局', 25, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '26', '浙江银保监局', 26, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '27', '四川银保监局', 27, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '28', '山西银保监局', 28, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '29', '安徽银保监局', 29, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '30', '重庆银保监局', 30, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '31', '黑龙江银保监局', 31, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '32', '陕西银保监局', 32, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '33', '江西银保监局', 33, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '34', '新疆银保监局', 34, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '35', '内蒙古银保监局', 35, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '36', '厦门银保监局', 36, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BANK_REGU_BURE2', '37', '青岛银保监局', 37, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BELONGS_BANK', '01', '总行部门', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BELONGS_BANK', '02', '总行直属中心', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BELONGS_BANK', '03', '分行', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_BELONGS_BANK', '04', '支行', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_CHECK_INTERF_TYPE', 'CHB_ASSET_INFO_SUB', '资产负债要素登记表', 5, '0', 'dubaojun', null, sysdate, 'dubaojun', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_CHECK_INTERF_TYPE', 'CHB_AST_VAL_SUB', '资产估值信息登记表', 7, '0', 'dubaojun', null, sysdate, 'dubaojun', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_CHECK_INTERF_TYPE', 'CHB_BOT_AST_POS_SUB', '底层资产持仓信息登记表', 8, '0', 'dubaojun', null, sysdate, 'dubaojun', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_CHECK_INTERF_TYPE', 'CHB_EMPLOYEE_INFO_SUB', '从业人员登记', 10, '0', 'dubaojun', null, sysdate, 'dubaojun', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_CHECK_INTERF_TYPE', 'CHB_INVEST_TRADE_SUB', '投资交易表登记', 6, '0', 'dubaojun', null, sysdate, 'dubaojun', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_CHECK_INTERF_TYPE', 'CHB_PRD_DEAL_SUB', '产品存续登记表', 4, '0', 'dubaojun', null, sysdate, 'dubaojun', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_CHECK_INTERF_TYPE', 'CHB_PRD_DECLARE_SUB', '个人产品申报登记', 1, '0', 'dubaojun', null, sysdate, 'dubaojun', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_CHECK_INTERF_TYPE', 'CHB_PRD_END_SUB', '产品终止登记', 9, '0', 'dubaojun', null, sysdate, 'dubaojun', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_CHECK_INTERF_TYPE', 'CHB_PRD_ISSUE_SUB', '个人发行登记表', 2, '0', 'dubaojun', null, sysdate, 'dubaojun', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_CHECK_INTERF_TYPE', 'CHB_PRD_RAISETOT_SUB', '募集总量登记', 3, '0', 'dubaojun', null, sysdate, 'dubaojun', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EDUCATION', '01', '初中', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EDUCATION', '02', '高中', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EDUCATION', '03', '中专', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EDUCATION', '04', '大专', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EDUCATION', '05', '本科', 5, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EDUCATION', '06', '硕士研究生', 6, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EDUCATION', '07', '博士研究生', 7, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EDUCATION', '99', '其他', 8, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EMPLOYEE_TYPE', '01', '投资经理', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EMPLOYEE_TYPE', '02', '产品审批人', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EMPLOYEE_TYPE', '03', '产品设计人', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EMPLOYEE_TYPE', '04', '产品营销人', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_EMPLOYEE_TYPE', '05', '理财登记人', 5, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_FIN_PROFESSIONAL_CRTF', '01', '行内专业证书', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_FIN_PROFESSIONAL_CRTF', '02', '银行业专业人员职业资格证书', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_FIN_PROFESSIONAL_CRTF', '99', '其他', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_INSTIT_SIZE', '01', '中型企业', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_INSTIT_SIZE', '02', '大型企业', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_INSTIT_SIZE', '03', '小型企业', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_INSTIT_SIZE', '04', '微型企业', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_INSTIT_SIZE', '99', '其他', 5, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PROFESSIONAL_TITLE', '01', '助理级', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PROFESSIONAL_TITLE', '02', '中级', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PROFESSIONAL_TITLE', '03', '高级', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PROFESSIONAL_TITLE', '99', '其他', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REGIST_ORG3', '01', '中央国债登记结算有限责任公司', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REGIST_ORG3', '02', '上海清算所', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REGIST_ORG3', '03', '中国证券登记结算有限责任公司', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REGIST_ORG3', '99', '其他', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REG_BUSI_TYPE', '01', '理财产品信息登记', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REG_BUSI_TYPE', '02', '投资者信息登记', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REG_EMPLOYEE_TYPE', '01', '理财信息登记工作领导小组成员', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REG_EMPLOYEE_TYPE', '02', '系统管理员', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REG_EMPLOYEE_TYPE', '03', '系统操作员', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_REG_EMPLOYEE_TYPE', '04', '系统开发及运维人员', 4, '0', 'system', null, sysdate, 'system', sysdate);
