-- 增加「从业人员类型」数字字典「01 投资经理/02 产品审批人/03 产品设计人/04 产品营销人/05 理财登记人」
DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_EMPLOYEE_TYPE';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EMPLOYEE_TYPE','SVR','从业人员类型',null,'02','system',null,to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
-- --------------------------------------------------------------------------------------------------------------------------------------------------------
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_EMPLOYEE_TYPE';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EMPLOYEE_TYPE','01','投资经理',1,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EMPLOYEE_TYPE','02','产品审批人',2,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EMPLOYEE_TYPE','03','产品设计人',3,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EMPLOYEE_TYPE','04','产品营销人',4,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EMPLOYEE_TYPE','05','理财登记人',5,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

commit;

-- ——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————--
-- 增加「所属总行或分支行」数字字典 「01 总行部门/02 总行直属中心/03 分行/04 支行」
DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_BELONGS_BANK';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_BELONGS_BANK','SVR','所属总行或分支行',null,'02','system',null,to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
-- --------------------------------------------------------------------------------------------------------------------------------------------------------
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_BELONGS_BANK';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_BELONGS_BANK','01','总行部门',1,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_BELONGS_BANK','02','总行直属中心',2,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_BELONGS_BANK','03','分行',3,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_BELONGS_BANK','04','支行',4,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

commit;

-- ——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————--
-- 增加「学历」数字字典 「01 初中/02 高中/03 中专/04 大专/05 本科/06 硕士研究生/07 博士研究生/99 其他」
DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_EDUCATION';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EDUCATION','SVR','学历',null,'02','system',null,to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
-- --------------------------------------------------------------------------------------------------------------------------------------------------------
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_EDUCATION';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EDUCATION','01','初中',1,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EDUCATION','02','高中',2,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EDUCATION','03','中专',3,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EDUCATION','04','大专',4,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EDUCATION','05','本科',5,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EDUCATION','06','硕士研究生',6,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EDUCATION','07','博士研究生',7,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_EDUCATION','99','其他',8,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

commit;

-- ——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————--
-- 增加「学位」数字字典 「01 学士/02 硕士/03 博士/99 其他」
DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_ACADEMIC_DEGREE';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_ACADEMIC_DEGREE','SVR','学位',null,'02','system',null,to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
-- --------------------------------------------------------------------------------------------------------------------------------------------------------
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_ACADEMIC_DEGREE';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_ACADEMIC_DEGREE','01','学士',1,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_ACADEMIC_DEGREE','02','硕士',2,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_ACADEMIC_DEGREE','03','博士',3,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_ACADEMIC_DEGREE','99','其他',4,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

commit;

-- ——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————--
-- 增加「专业技术职称」数字字典 「01 助理级/02 中级/03 高级/99 其他」
DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_PROFESSIONAL_TITLE';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_PROFESSIONAL_TITLE','SVR','专业技术职称',null,'02','system',null,to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
-- --------------------------------------------------------------------------------------------------------------------------------------------------------
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_PROFESSIONAL_TITLE';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_PROFESSIONAL_TITLE','01','助理级',1,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_PROFESSIONAL_TITLE','02','中级',2,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_PROFESSIONAL_TITLE','03','高级',3,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_PROFESSIONAL_TITLE','99','其他',4,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

commit;

-- ——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————--
-- 增加「理财专业证书」数字字典 「01 行内专业证书/02 银行业专业人员职业资格证书/99 其他」
DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_FIN_PROFESSIONAL_CRTF';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_FIN_PROFESSIONAL_CRTF','SVR','理财专业证书',null,'02','system',null,to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
-- --------------------------------------------------------------------------------------------------------------------------------------------------------
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_FIN_PROFESSIONAL_CRTF';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_FIN_PROFESSIONAL_CRTF','01','行内专业证书',1,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_FIN_PROFESSIONAL_CRTF','02','银行业专业人员职业资格证书',2,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_FIN_PROFESSIONAL_CRTF','99','其他',3,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

commit;

-- ——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————--
-- 增加「登记业务分类」数字字典 「01 理财产品信息登记/02 投资者信息登记」
DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_REG_BUSI_TYPE';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_REG_BUSI_TYPE','SVR','登记业务分类',null,'02','system',null,to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
----------------------------------------------------------------------------------------------------------------------------------------------------------
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_REG_BUSI_TYPE';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_REG_BUSI_TYPE','01','理财产品信息登记',1,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_REG_BUSI_TYPE','02','投资者信息登记',2,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

commit;

-- ——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————--
-- 增加「登记人员类别」数字字典 「01 理财信息登记工作领导小组成员/02 系统管理员/03 系统操作员/04 系统开发及运维人员」
DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_REG_EMPLOYEE_TYPE';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_REG_EMPLOYEE_TYPE','SVR','登记人员类别',null,'02','system',null,to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('05-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
-- --------------------------------------------------------------------------------------------------------------------------------------------------------
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_REG_EMPLOYEE_TYPE';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_REG_EMPLOYEE_TYPE','01','理财信息登记工作领导小组成员',1,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_REG_EMPLOYEE_TYPE','02','系统管理员',2,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_REG_EMPLOYEE_TYPE','03','系统操作员',3,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_REG_EMPLOYEE_TYPE','04','系统开发及运维人员',4,'0','system',null,to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),
'system',to_timestamp('11-6月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

delete from SYS_DATADICT_LIST where DICT_CODE='SVR_REPORT_CODE' and ITEM_CODE='30' and ITEM_VALUE='从业人员信息登记';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR_REPORT_CODE','30','从业人员信息登记',30,'0','system',null,sysdate,'system',sysdate);


commit;

