delete from CHB_INFOMGR_RESULT_CONFIG WHERE MGRTABLE_ID='CHB_PRD_BASEINFO';
insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c00', 'CHB_PRD_BASEINFO', 'PRODUUID', '主键', '', 1, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c01', 'CHB_PRD_BASEINFO', 'FPROD_ID', '父产品代码', '', 2, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c02', 'CHB_PRD_BASEINFO', 'PROD_ID', '产品代码', '', 3, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c03', 'CHB_PRD_BASEINFO', 'PROD_NAME', '产品名称', '', 4, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c04', 'CHB_PRD_BASEINFO', 'REG_CODE', '产品登记编码', '', 5, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c05', 'CHB_PRD_BASEINFO', 'PROD_INS_ID', '行内标识码', '', 6, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c06', 'CHB_PRD_BASEINFO', 'PROFIT_TYPE', '产品收益类型', 'SVR_PROD_PROFIT_TYPE', 7, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c07', 'CHB_PRD_BASEINFO', 'INVESTOR_TYPE', '投资者类型', '', 8, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c08', 'CHB_PRD_BASEINFO', 'STATUS', '数据状态', 'MAINTAIN_STATUS', 9, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c09', 'CHB_PRD_BASEINFO', 'BASIC', '计息基础', 'BASIS', 10, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c10', 'CHB_PRD_BASEINFO', 'PRODTYPE', '标识哪种产品', '', 11, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c11', 'CHB_PRD_BASEINFO', 'SALETARGET', '目标客户类型', 'SALETARGET_DECLARE', 12, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c12', 'CHB_PRD_BASEINFO', 'DURATIONFLAG', '存续状态', 'SVR_DURATIONFLAG', 13, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c13', 'CHB_PRD_BASEINFO', 'MDATE', '到期日', '', 14, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c14', 'CHB_PRD_BASEINFO', 'VDATE', '起息日', '', 15, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c15', 'CHB_PRD_BASEINFO', 'TERMINATEDATE', '提前终止日', '', 16, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c16', 'CHB_PRD_BASEINFO', 'IVSTORIENTATION', '投资性质', 'INVEST_TYPE', 17, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c17', 'CHB_PRD_BASEINFO', 'OPEN_BUSS_REMARK', '开放期业务说明', '', 18, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c24', 'CHB_PRD_BASEINFO', 'RAISE_VDATE', '募集起始日期', '', 25, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c25', 'CHB_PRD_BASEINFO', 'RAISE_ENDDATE', '募集结束日期', '', 26, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c26', 'CHB_PRD_BASEINFO', 'PROD_BRAND', '产品品牌', '', 27, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c18', 'CHB_PRD_BASEINFO', 'RAISE_PSTRDATE', '募集起始日期（从）', '', 19, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c19', 'CHB_PRD_BASEINFO', 'RAISE_PENDDATE', '募集起始日期（到）', '', 20, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c20', 'CHB_PRD_BASEINFO', 'ISSUING_STATUS', '发行状态', 'ISSUE_STATUS', 21, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c21', 'CHB_PRD_BASEINFO', 'RISK_LEVEL', '产品风险等级', 'RISKLEVEL', 22, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c22', 'CHB_PRD_BASEINFO', 'TERMTYPE', '产品期限', 'SVR_PRODUCT_TERM', 23, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c23', 'CHB_PRD_BASEINFO', 'RAISE_CCY', '募集币种', 'CURRENCY_CHB', 24, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c27', 'CHB_PRD_BASEINFO', 'PROD_STAGE', '产品期次', '', 28, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c28', 'CHB_PRD_BASEINFO', 'DESIGNER_NAME', '产品设计人姓名', '', 29, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c29', 'CHB_PRD_BASEINFO', 'DESIGNER_ID', '产品设计人身份证号', '', 30, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c30', 'CHB_PRD_BASEINFO', 'APPROVER_NAME', '产品审批人姓名', '', 31, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c31', 'CHB_PRD_BASEINFO', 'APPROVER_ID', '产品审批人身份证号', '', 32, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c32', 'CHB_PRD_BASEINFO', 'MANAGER_NAME', '投资经理姓名', '', 33, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c33', 'CHB_PRD_BASEINFO', 'MANAGER_ID', '投资经理身份证号', '', 34, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c34', 'CHB_PRD_BASEINFO', 'BOOK_MODE', '会计核算方式', 'SVR_BOOKMODEL_DECLARE', 35, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c35', 'CHB_PRD_BASEINFO', 'LIAISON_NAME', '业务联络人姓名', '', 36, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c36', 'CHB_PRD_BASEINFO', 'LIAISON_TEL', '业务联络人座机', '', 37, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c37', 'CHB_PRD_BASEINFO', 'LIAISON_MOBILE', '业务联络人手机', '', 38, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c38', 'CHB_PRD_BASEINFO', 'LIAISON_EMAIL', '业务联络人邮箱', '', 39, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c39', 'CHB_PRD_BASEINFO', 'INVEST_ARAE', '资金投向地区', 'SVR_SALEAREA_DECLARE', 40, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c40', 'CHB_PRD_BASEINFO', 'INVEST_COUNTRY', '产品投资国家或地区', 'SVR_COUNTRY_AREA', 41, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c41', 'CHB_PRD_BASEINFO', 'FINAN_SER_MODEL', '理财业务服务模式', 'SVR_SERVICEMODE', 42, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c42', 'CHB_PRD_BASEINFO', 'OPERATE_MODE', '产品运作模式', 'SVR_OPERATEMODE_DECLARE', 43, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c43', 'CHB_PRD_BASEINFO', 'CMMODE', '产品资产配置方式', 'SVR_CMMODE', 44, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c44', 'CHB_PRD_BASEINFO', 'PMMODE', '产品管理模式', 'SVR_PROD_MANAGE', 45, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c45', 'CHB_PRD_BASEINFO', 'MNAME', '实际管理人名称', '', 46, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c46', 'CHB_PRD_BASEINFO', 'INV_ASSET_TYPE', '投资资产类型', 'SVR_TRU_ACT_TYPE', 47, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c47', 'CHB_PRD_BASEINFO', 'CONT_MODE', '合作模式', 'SVR_CONTMODE', 48, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c48', 'CHB_PRD_BASEINFO', 'COOPERATION_NAME', '合作机构', '', 49, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c61', 'CHB_PRD_BASEINFO', 'SALE_FEE_RATE', '销售手续费率%', '', 62, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c62', 'CHB_PRD_BASEINFO', 'INVEST_PRIN_DATE', '投资本金到账日', 'SVR_AMTDATETYPE', 63, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c63', 'CHB_PRD_BASEINFO', 'INVEST_PROF_DATE', '投资收益到账日', 'SVR_AMTDATETYPE', 64, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c64', 'CHB_PRD_BASEINFO', 'TRU_FEE_RATE', '托管费率%', '', 65, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c65', 'CHB_PRD_BASEINFO', 'IN_DEP_CODE', '境内托管机构代码', '', 66, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c66', 'CHB_PRD_BASEINFO', 'IN_DEP_NAME', '境内托管机构名称', 'SVR_IN_DEP_NAME', 67, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c67', 'CHB_PRD_BASEINFO', 'OUT_DEP_CTRY', '境外托管机构国别', 'SVR_COUNTRY_AREA', 68, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c68', 'CHB_PRD_BASEINFO', 'OUT_DEP_NAME', '境外托管机构名称', '', 69, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c69', 'CHB_PRD_BASEINFO', 'TERM_RIGHT_FLAG', '发行机构提前终止权标识', 'SVR_HAVE_OR_NOT', 70, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c70', 'CHB_PRD_BASEINFO', 'CUS_REDEEM_FLAG', '客户赎回权标识', 'SVR_HAVE_OR_NOT', 71, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c71', 'CHB_PRD_BASEINFO', 'CREDIT_FLAG', '产品增信标识', 'SVR_HAVE_OR_NOT', 72, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c72', 'CHB_PRD_BASEINFO', 'CREDIT_TYPE', '产品增信机构类型', 'SVR_CREDIT_TYPE', 73, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c73', 'CHB_PRD_BASEINFO', 'PRICED_MODE', '产品定价方式', 'SVR_PRODVALUETYPE', 74, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c49', 'CHB_PRD_BASEINFO', 'CON_PRD_TRAGET', '结构性产品挂钩标的', 'SVR_TARGET_TYPE', 50, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c50', 'CHB_PRD_BASEINFO', 'IS_YIELD', '是否有预期收益率', 'SVR_YES_OR_NO', 51, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c51', 'CHB_PRD_BASEINFO', 'IS_YIELD_MEAS_BASIS', '是否有收益率测算依据', 'SVR_YES_OR_NO', 52, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c52', 'CHB_PRD_BASEINFO', 'INV_TYPE_RAT', '投资资产种类及比例', '', 53, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c53', 'CHB_PRD_BASEINFO', 'RISK_TYPE', '投资者风险偏好', 'SVR_RISKTYPE', 54, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c54', 'CHB_PRD_BASEINFO', 'HIGH_YIELD', '预计客户最高年收益率%', '', 55, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c55', 'CHB_PRD_BASEINFO', 'LOW_YIELD', '预计客户最低年收益率%', '', 56, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c56', 'CHB_PRD_BASEINFO', 'SALE_AREA', '产品销售地区', 'ADMINISTRATIVE_TYPE1', 57, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c57', 'CHB_PRD_BASEINFO', 'SALE_AMT_STR', '起点销售金额', '', 58, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c58', 'CHB_PRD_BASEINFO', 'CASH_PRIN_CCY', '兑付本金币种', 'CURRENCY_CHB', 59, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c59', 'CHB_PRD_BASEINFO', 'CASH_PROF_CCY', '兑付收益币种', 'CURRENCY_CHB', 60, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c60', 'CHB_PRD_BASEINFO', 'RAISE_AMT_PLAN', '计划募集金额（元）', '', 61, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c82', 'CHB_PRD_BASEINFO', 'OPEN_CIRCLE_DAYS', '其他规律开放周期（天）', '', 83, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c83', 'CHB_PRD_BASEINFO', 'RULE_COMMENT', '无规律开放说明', '', 84, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c84', 'CHB_PRD_BASEINFO', 'FIRST_OPEN_DAY', '首次开放周期起始日', '', 85, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c85', 'CHB_PRD_BASEINFO', 'HOLIDAY_ON_FLAG', '节假日是否开放', 'SVR_YES_OR_NO', 86, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c86', 'CHB_PRD_BASEINFO', 'AVG_OPEN_COUNT', '平均开放次数（年化）', '', 87, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c87', 'CHB_PRD_BASEINFO', 'OPEN_BUSS', '开放期业务', 'SVR_OPEN_BUSS', 88, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c88', 'CHB_PRD_BASEINFO', 'HIGH_YIELD_ISSUE', '预计最高收益率-发行', '', 89, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c89', 'CHB_PRD_BASEINFO', 'LOW_YIELD_ISSUE', '预计最低收益率-发行', '', 90, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c90', 'CHB_PRD_BASEINFO', 'CREDIT_MODE', '产品增信形式', 'SVR_CREDIT_MODE', 91, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c91', 'CHB_PRD_BASEINFO', 'ISSUE_PARTY_ID', '发行机构代码', '', 92, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c92', 'CHB_PRD_BASEINFO', 'OPENTYPEP', '开放模式', 'OPENMODE', 93, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c93', 'CHB_PRD_BASEINFO', 'OPENDAYP', '开放天数', '', 94, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c94', 'CHB_PRD_BASEINFO', 'OPENCYCLEP', '开放周期', '', 95, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c95', 'CHB_PRD_BASEINFO', 'OPENCYCLEUNITP', '开放周期单位', '', 96, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c96', 'CHB_PRD_BASEINFO', 'WEEKP', '周申', '', 97, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c97', 'CHB_PRD_BASEINFO', 'DAYP', '几号申', '', 98, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c98', 'CHB_PRD_BASEINFO', 'WORKDAYP', '第几工作日申', '', 99, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c99', 'CHB_PRD_BASEINFO', 'REMARK', '备注', '', 100, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983100', 'CHB_PRD_BASEINFO', 'DATA_SOURCE', '数据来源', '', 101, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c74', 'CHB_PRD_BASEINFO', 'PRD_VDATE', '产品起始日期', '', 75, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c75', 'CHB_PRD_BASEINFO', 'PRD_MDATE', '产品终止日期', '', 76, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c76', 'CHB_PRD_BASEINFO', 'EXP_AVG_YIELD', '平均预计客户年收益率', '', 77, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c77', 'CHB_PRD_BASEINFO', 'IS_CONSTRACTE', '是否结构化分层产品', 'SVR_YES_OR_NO', 78, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c78', 'CHB_PRD_BASEINFO', 'GRADE_TYPE', '层级类别', 'SVR_GRADE_TYPE', 79, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c79', 'CHB_PRD_BASEINFO', 'YIELD_COMMENT', '收益率分档情况说明', '', 80, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c80', 'CHB_PRD_BASEINFO', 'OPEN_MODE', '开放模式', 'SVR_OPEN_MODE', 81, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983c81', 'CHB_PRD_BASEINFO', 'OPEN_CIRCLE', '规律开放周期', 'SVR_REGULAR_OPEN_CYCLE', 82, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983101', 'CHB_PRD_BASEINFO', 'RAISE_TYPE', '募集方式', 'SVR_RAISE_TYPE', 102, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983102', 'CHB_PRD_BASEINFO', 'MANAGE_TYPE', '管理方式', 'SVR_PRO_PROPERTY', 103, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983103', 'CHB_PRD_BASEINFO', 'UPDATE_USER', '更新人', '', 104, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983104', 'CHB_PRD_BASEINFO', 'UPDATE_TIME', '更新时间', '', 105, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983105', 'CHB_PRD_BASEINFO', 'CREATE_USER', '创建人', '', 106, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983106', 'CHB_PRD_BASEINFO', 'CREATE_TIME', '创建时间', '', 107, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983107', 'CHB_PRD_BASEINFO', 'EXC_FIN_SECTOR', '是否金融同业专属', 'SVR_YES_OR_NO', 108, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983108', 'CHB_PRD_BASEINFO', 'IS_SHORT_TERM', '是否设置最短持有期限', 'SVR_YES_OR_NO', 109, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983109', 'CHB_PRD_BASEINFO', 'SHORT_TERM', '最短持有期限(天)', '', 110, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983110', 'CHB_PRD_BASEINFO', 'IS_FREE_REDEM', '最短持有期后是否自由赎回', 'SVR_YN_REDEM', 111, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983111', 'CHB_PRD_BASEINFO', 'CASH_MANA_CATE', '是否现金管理类', 'SVR_YES_OR_NO', 112, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983112', 'CHB_PRD_BASEINFO', 'YIELD', '业绩比较基准%', '', 113, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983113', 'CHB_PRD_BASEINFO', 'INVEST_FEE_RATE', '投资管理费率%', '', 114, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983114', 'CHB_PRD_BASEINFO', 'FIDUCIARY_DUTY', '受托职责', 'SVR_YES_OR_NO', 115, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983115', 'CHB_PRD_BASEINFO', 'TRANSFER_TYPE', '转让类型', 'SVR_TRANSFER_TYPE', 116, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983116', 'CHB_PRD_BASEINFO', 'IS_MARKET_FUND', '货币市场基金标识', 'SVR_YES_OR_NO', 117, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('227254a4-0b19-4dea-97b4-e782fa983117', 'CHB_PRD_BASEINFO', 'CUSTOMER_TYPE', '客户类型', 'SVR_PBC_CUSTTYPE', 118, 'Y');

