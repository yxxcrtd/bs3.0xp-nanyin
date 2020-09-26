delete from CHB_INFOMGR_RESULT_CONFIG WHERE MGRTABLE_ID='CHB_ASSET_INFO';
insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('32992d29-9e8b-4271-ab8c-0a1727b4c3c2', 'CHB_ASSET_INFO', 'ASSET_UUID', '主键', '', 1, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('5ae55d8e-7cdf-4e14-aed5-66812d9c4fb6', 'CHB_ASSET_INFO', 'ASSET_CODE', '行内资产负债编码', '', 2, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('e18d5378-0258-4952-8648-51cfc85643e0', 'CHB_ASSET_INFO', 'ISSUE_PARTY_ID', '发行机构代码', '', 3, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('fde041b8-0d2c-46bd-8c5e-581f1ade34c7', 'CHB_ASSET_INFO', 'ASSET_TYPE', '资产/负债类别', 'SVR_ASSETDEBT_TYPE', 4, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('de794f7e-84d4-4c0e-ab56-d3029d7182ee', 'CHB_ASSET_INFO', 'ASSET_TYPE2', '资产/负债二级类别', 'SVR_ASSET_TYPE_SECOND_LEVEL', 5, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('8d9d1181-0d26-4fac-b8db-b75b1da44fe3', 'CHB_ASSET_INFO', 'TRADE_MARKET', '交易流通场所', 'SVR_TRADE_MARKET', 6, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('8fc9f9be-208d-4d40-8d9c-3adc8fc590c5', 'CHB_ASSET_INFO', 'CCY', '币种', 'CURRENCY_CHB', 7, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('9235474d-bf38-43d7-bb87-b6370735a216', 'CHB_ASSET_INFO', 'VDATE', '起息日', '', 8, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('0e7e75fe-9b77-48d1-8375-7ddc662ec659', 'CHB_ASSET_INFO', 'MDATE', '到期日', '', 9, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('902a543e-8844-43c2-97d9-861df53ca34f', 'CHB_ASSET_INFO', 'STATUS', '数据状态', 'MAINTAIN_STATUS', 10, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('d253663c-aeed-4a87-a6f7-648f0c94d548', 'CHB_ASSET_INFO', 'BANK_NAME', '资金存入银行', '', 11, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('de4bff0d-773e-4fee-bfc5-afd1534899df', 'CHB_ASSET_INFO', 'AMT', '金额', '', 12, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('8a7425c3-bd2b-43a9-9ce1-acfe9b6cc267', 'CHB_ASSET_INFO', 'ASSET_YIELD', '利率', '', 13, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('a60f8c90-a611-4467-a37c-61489ef89e53', 'CHB_ASSET_INFO', 'BASIS', '计息基础', 'SVR_BASIS', 14, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('659dec87-d276-44a8-abef-371834ac4ae8', 'CHB_ASSET_INFO', 'IS_STRUCT', '是否为结构性存款', 'SVR_YES_OR_NO', 15, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('b66ea7e4-8aec-4b54-905b-6f6121027232', 'CHB_ASSET_INFO', 'TARGET_TYPE', '结构性存款挂钩标的类别', 'SVR_TARGET_TYPE', 16, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('91a2c6e9-21ff-4bbc-b7b1-d41eb29b2f9a', 'CHB_ASSET_INFO', 'TARGET', '结构性存款挂钩标的', '', 17, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('3df82981-8f51-414e-afd0-7aa558ae3aae', 'CHB_ASSET_INFO', 'ASSET_ID', '代码', '', 18, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('19fafbf9-6140-4551-bc3f-8e961e2be034', 'CHB_ASSET_INFO', 'ASSET_NAME', '名称', '', 19, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('ef654e74-8437-4cfe-89d0-2916b2ec9a72', 'CHB_ASSET_INFO', 'CHB_SECTYPE', '具体类别', 'SVR_SEC_DETAIL_TYPE', 20, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('50f54b56-90a7-407e-a2ff-67c36f43d6c5', 'CHB_ASSET_INFO', 'ISSUE_MODE', '发行方式', 'SVR_ISSUE_MODE_FUND', 21, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('52bcb6a3-4e44-4d57-a812-0bd77c2eccbb', 'CHB_ASSET_INFO', 'SEC_MAIN_GRADE', '主体评级', 'SVR_MAIN_GRADE', 22, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('443f7165-b277-462d-8df6-2211ea6d0d18', 'CHB_ASSET_INFO', 'PARTY_TYPE1', '发行机构类型（按规模划分）', 'SVR_PARTY_TYPE1', 23, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('ba3a7008-40b5-40fe-a184-d815d14d3dde', 'CHB_ASSET_INFO', 'PARTY_TYPE3', '发行机构类型（按技术领域划分）', 'SVR_PARTY_TYPE3', 24, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('da0f5b17-90c8-479e-a673-209394182b56', 'CHB_ASSET_INFO', 'PARTY_TYPE2', '发行机构类型（按经济类型划分）', 'SVR_PARTY_TYPE2', 25, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('20a6655d-f51e-42e4-a0e1-4c3b82e01e28', 'CHB_ASSET_INFO', 'INDUSTRY', '发行机构所属行业一级分类', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 26, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('ca8330b8-70ac-4032-80c6-01f8cd5ee66b', 'CHB_ASSET_INFO', 'INDUSTRY_LEVEL2', '发行机构所属行业二级分类', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 27, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('923d8021-01f5-4907-870e-3d4b717aef48', 'CHB_ASSET_INFO', 'REGIST_ORG', '登记托管机构', 'SVR_REGIST_ORG', 28, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('b54a8f4c-b25d-4bb5-834f-e026a1c25eff', 'CHB_ASSET_INFO', 'REGISTORG_COMMENT', '登记托管机构说明', '', 29, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('ff9a53f8-c2d6-45b1-ab70-b49c5c100cbb', 'CHB_ASSET_INFO', 'RIVAL_PARTY', '对手方', '', 30, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('7e46e3e1-f84b-4e7f-a954-e48d5ff5de09', 'CHB_ASSET_INFO', 'TARGET_TYPE1', '回购标的类别', 'SVR_TARGET_TYPE2', 31, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('b3c74ead-2749-48cd-b3cc-18c38a41d22d', 'CHB_ASSET_INFO', 'TARGET_AMT', '回购标的金额', '', 32, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('f277a636-a1ac-4bbe-ad1a-c3104f5a0a5d', 'CHB_ASSET_INFO', 'PROFIT_TYPE', '收/受益权类型', 'SVR_PROFIT_TYPE', 33, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('37d8dfd9-9649-49c6-8979-0daf3f4c108b', 'CHB_ASSET_INFO', 'IS_RESALE', '是否属于买入返售', 'SVR_YES_OR_NO', 34, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('982743e6-e60c-4282-a3e8-cf46932a922c', 'CHB_ASSET_INFO', 'RATE_FLAG', '是否有预期收益率', 'SVR_YES_OR_NO', 35, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('5c3ad2e3-6067-4806-8d28-79ab9c2816ed', 'CHB_ASSET_INFO', 'INTPAY_CYCLE', '付息频率（个月/次）', '', 36, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('08c034e1-f2e8-4f54-a5dc-c704d094f82d', 'CHB_ASSET_INFO', 'REPAY_CMT', '还本付息情况说明', '', 37, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('f0870ed4-8ae4-4f2d-8465-f23cc34beed5', 'CHB_ASSET_INFO', 'BASIS_TYPE', '基准利率种类', 'SVR_BASIS_TYPE', 38, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('73a1e612-c141-4e22-aa9b-4d86f9dfd326', 'CHB_ASSET_INFO', 'FLOAT_FACTOR', '浮动因子（%）', '', 39, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('54c54b06-fea3-421a-b17f-c60fd7e8aad6', 'CHB_ASSET_INFO', 'SPREAD_RATE', '利差(BP)', '', 40, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('d1367fee-8c64-4b7e-a9e6-acc31dfc4a2e', 'CHB_ASSET_INFO', 'FINANCIE', '融资人', '', 41, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('4f9c2a0f-4ea9-4003-bc50-f19887ca7827', 'CHB_ASSET_INFO', 'FCR_GRADE_IN', '融资人内部信用评级', '', 42, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('ac500e95-c733-4bad-b846-54c9476e247e', 'CHB_ASSET_INFO', 'FCR_GRADE_OUT', '外部评级机构名称及对融资人评级结果', '', 43, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('1695c130-923b-46b3-bd6b-f391c657a318', 'CHB_ASSET_INFO', 'FINANCIER_TYPE1', '融资人类型（按规模划分）', 'SVR_PARTY_TYPE1', 44, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('0c101a6e-1b6e-4aec-a4f5-6038561eedf4', 'CHB_ASSET_INFO', 'FINANCIER_TYPE2', '融资人类型（按技术领域划分）', 'SVR_PARTY_TYPE3', 45, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('6befe01a-0482-461a-bddc-e95b4c87d8f4', 'CHB_ASSET_INFO', 'FINANCIER_TYPE3', '融资人类型（按经济类型划分）', 'SVR_PARTY_TYPE2', 46, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('94500b91-20b5-4d19-ac85-c826bfa1072b', 'CHB_ASSET_INFO', 'FINANCE_PROJ', '融资项目', '', 47, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('35741800-a438-495a-84e3-3c7845b7c009', 'CHB_ASSET_INFO', 'INDUSTRY1', '融资人所属行业一级分类', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 48, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('f32751ef-3756-4c6c-b028-1387a27b7acd', 'CHB_ASSET_INFO', 'INDUSTRY_LEVEL2_TYPE', '融资人所属行业二级分类', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 49, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('db5ca9ed-0717-4905-8b0c-72aaa6df2d4c', 'CHB_ASSET_INFO', 'INDUSTRY_FLAG', '项目是否属于重点监控行业和领域', 'SVR_YES_OR_NO', 50, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('f5c3b41f-7e7f-4c31-9110-0b9de286c8d1', 'CHB_ASSET_INFO', 'MONIT_TYPE', '重点监控行业和领域类别', 'SVR_MOINT_TYPE', 51, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('4f93c70c-4d89-4ee5-b43a-ba12da5af392', 'CHB_ASSET_INFO', 'MON_TYPE_COMM', '重点监控行业和领域类别说明', '', 52, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('55801f72-15bb-40a2-b085-c15fb4ef1a6a', 'CHB_ASSET_INFO', 'GUA_TYPE', '担保方式', 'SVR_GUA_TYPE', 53, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('3aafe85c-f533-4218-86e6-2f0ba3658faa', 'CHB_ASSET_INFO', 'GUA_TYPE_REMARK', '担保情况说明', '', 54, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('ee20fa38-cd2d-4263-b512-580a7ac5cb37', 'CHB_ASSET_INFO', 'FINANCIER_GRADE', '融资人主体评级', 'SVR_MAIN_GRADE', 55, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('104af0b1-cff3-4217-90ff-c35211ec5407', 'CHB_ASSET_INFO', 'IN_GRADE', '资产内部评级', '', 56, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('f1e5d840-fa2d-4f77-9fcb-00accd1b2367', 'CHB_ASSET_INFO', 'OUT_GRADE', '资产外部评级', 'SVR_MAIN_GRADE', 57, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('dd01d37b-bef1-417a-8e00-258c5f9186a5', 'CHB_ASSET_INFO', 'CHOSE', '选择权', '', 58, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('c9d6d56c-9383-46fe-9f32-281363c25d24', 'CHB_ASSET_INFO', 'CONDITION_CMM', '行权条件说明', '', 59, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('55e978e1-703d-497f-baba-5a172b5259df', 'CHB_ASSET_INFO', 'FIN_AREA', '融资人所属地区', 'ADMINISTRATIVE_TYPE2', 60, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('115c728b-932d-429e-8ae2-0fa6f575c7d5', 'CHB_ASSET_INFO', 'FIN_ORG_CODE', '融资人组织机构（社会信用）代码', '', 61, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('15b025b9-a459-4dc3-8de6-6a7653ebbfad', 'CHB_ASSET_INFO', 'TOTAL_FEE_RATE', '融资总费率%', '', 62, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('bc67e3bb-8cfe-449a-9294-5f6fe72c3c07', 'CHB_ASSET_INFO', 'PRO_INDUSTRY', '融资项目所属行业一级分类', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 63, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('4640d8e4-e4c8-4bdc-b740-27265ab7d51a', 'CHB_ASSET_INFO', 'PRO_INDUSTRY_LEVEL2', '融资项目所属行业二级分类', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 64, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('a4d764b5-eff9-4613-b5fe-b92c1b5b15dd', 'CHB_ASSET_INFO', 'BONDTOSTOCK_FLAG', '是否投向“市场化债转股相关产品的部分”', 'SVR_YES_OR_NO', 65, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('75c8641e-7163-46db-b710-0ce385431242', 'CHB_ASSET_INFO', 'PPPFLAG', '公共PPP', 'SVR_YES_OR_NO', 66, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('5b8944e0-9362-4706-a2b5-1764a9702804', 'CHB_ASSET_INFO', 'ASSET_TYPE3', '二级资产细分类', 'SVR_YES_OR_NO', 67, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('affa3809-2fa4-428e-8e51-1ee9397306a3', 'CHB_ASSET_INFO', 'IS_RIGHT', '是否为收/受益权', 'SVR_YES_OR_NO', 68, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('91522b32-5e76-4e5a-b3fc-49404c05787b', 'CHB_ASSET_INFO', 'NOTSTAND_SEC_TYPE', '类型', 'SVR_ASSET_TYPE_BILL_LETTERCREDIT', 69, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('d10712cb-1b43-4a55-bab5-721c30e6c4cf', 'CHB_ASSET_INFO', 'NOTSTAND_SEC_NUM', '数量', '', 70, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('6eef9111-3227-4535-b85f-33abbcd198f1', 'CHB_ASSET_INFO', 'REMAIN_TERM', '加权剩余期限（天）', '', 71, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('946b96ea-51f2-41d9-af8c-defcce00c808', 'CHB_ASSET_INFO', 'MAX_REMAIN_TERM', '最长剩余期限（天）', '', 72, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('8e796f8d-cacc-4203-b626-5fddd796c6e1', 'CHB_ASSET_INFO', 'MIN_REMAIN_TERM', '最短剩余期限（天）', '', 73, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('a123bc2c-0ccd-454a-9531-7eadfe9ec14d', 'CHB_ASSET_INFO', 'INDUSTRY_SHARES', '行业一级分类', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 74, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('e4e6a549-7f96-4caa-bf2a-f4c9b69cd6ef', 'CHB_ASSET_INFO', 'INDUSTRY_SHARES2', '行业二级分类', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 75, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('4dd7b5e0-69da-4a4e-a7a0-2297b5345b43', 'CHB_ASSET_INFO', 'SHARES_TYPE', '股票类型', 'SVR_SHARES_TYPE', 76, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('b5ce39e5-1692-43ee-9f8f-adf6b476e64e', 'CHB_ASSET_INFO', 'INVEST_STAGE', '投资阶段', 'SVR_INVEST_STAGE', 77, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('2358466c-ac66-42a7-b51e-317a63a52790', 'CHB_ASSET_INFO', 'SHARES_TYPE1', '企业类型（按规模划分）', 'SVR_PARTY_TYPE1', 78, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('54adbed0-5029-4e00-a12e-6c076cb2d9bf', 'CHB_ASSET_INFO', 'SHARES_TYPE3', '企业类型（按技术领域划分）', 'SVR_PARTY_TYPE3', 79, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('72e6527d-8687-42e7-9523-e8e89f929f8a', 'CHB_ASSET_INFO', 'SHARES_TYPE2', '企业类型（按经济类型划分）', 'SVR_PARTY_TYPE2', 80, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('af0b3ef5-be82-4d63-8e5e-558cd3dbea8e', 'CHB_ASSET_INFO', 'IS_PLEDGE', '是否为质押融资', 'SVR_YES_OR_NO', 81, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('3e1048f6-78d2-4da8-870f-a144617720b0', 'CHB_ASSET_INFO', 'PARTY_CODE', '资管计划发起人机构编码', '', 82, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('fbe0edc1-6529-475e-b9d8-5d8d5926b7d4', 'CHB_ASSET_INFO', 'CHECK_CODE', '资管计划登记编码', '', 83, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('4852c604-cf9a-49fc-a238-dbd425474b63', 'CHB_ASSET_INFO', 'MANAGER_ID', '管理人', '', 84, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('7bf4dc73-378d-40a6-be33-7d941e812091', 'CHB_ASSET_INFO', 'TRUSTEE_ID', '托管人', '', 85, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('00fd8094-9df2-44a4-93f3-5b88805e95bd', 'CHB_ASSET_INFO', 'TRU_ACT_TYPE', '资金实际投向', 'SVR_TRU_ACT_TYPE', 86, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('0fc1fc09-6f0d-488b-a877-9c8375bbd900', 'CHB_ASSET_INFO', 'INVEST_TYPE', '资金运用方式', '', 87, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('15cfe7b0-5f19-4d7e-92ba-742565b61ab7', 'CHB_ASSET_INFO', 'CAP_USE_INDU', '资金运用行业一级分类', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 88, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('c043bfc7-d2da-4e81-931a-6783111353ba', 'CHB_ASSET_INFO', 'CAP_USE_INDU_LEVEL2', '资金运用行业二级分类', 'INSTITUTION_INDUSTRY_SECOND_LEVEL', 89, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('f79af936-f503-4b34-8610-4b8ba879b332', 'CHB_ASSET_INFO', 'PLAN_SDATE', '资管计划成立日期', '', 90, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('941f5ccb-1564-4e6b-b369-785e61ed02e1', 'CHB_ASSET_INFO', 'PLAN_EDATE', '资管计划终止日期', '', 91, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('98f9c72f-74c6-4536-81fd-13c634dde38d', 'CHB_ASSET_INFO', 'PRO_PROPERTY', '资管计划属性', 'SVR_PRO_PROPERTY', 92, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('7d5e9787-b993-4458-b326-cf47eb6b3132', 'CHB_ASSET_INFO', 'MAX_RATE', '预期最高收益率%', '', 93, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('0d96a6c3-5237-4e91-b3a7-320b44338082', 'CHB_ASSET_INFO', 'MIN_RATE', '预期最低收益率%', '', 94, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('7c89f023-2704-4078-a881-4f43e6da9273', 'CHB_ASSET_INFO', 'BUY_STRUCT', '购买结构', 'SVR_BUY_STRUCT', 95, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('ca1be8f6-9ed2-417c-a7e2-d4b3947e1db2', 'CHB_ASSET_INFO', 'MANAGE_TYPE', '管理方式', 'SVR_MANAGE_STYLE', 96, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('a03361f5-7c2f-4523-a63c-22568bd5743e', 'CHB_ASSET_INFO', 'IS_CTRL_BOTTOM', '是否掌控计划底层资产详情', 'SVR_YES_OR_NO', 97, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('38100020-7b1b-4a0a-8afd-f64acddb7aee', 'CHB_ASSET_INFO', 'MGR_FEE_RATE', '管理费率%', '', 98, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('efa557ca-a599-4a00-953a-0eadb55e1637', 'CHB_ASSET_INFO', 'TRU_FEE_RATE', '托管费率%', '', 99, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('65b47c4a-929c-4f1d-9e49-e801b72a806a', 'CHB_ASSET_INFO', 'TRADE_FEE_RATE', '交易相关合计费率%', '', 100, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('1af94b59-7991-49a7-ad9d-f967dd74423c', 'CHB_ASSET_INFO', 'AGENCY_FEE_RATE', '中介服务机构合计费率%', '', 101, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('5ca14cd5-3840-4afd-9a17-2a023a87c0df', 'CHB_ASSET_INFO', 'OTHER_FEE_RATE', '其他合计费率%', '', 102, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('0df32b04-ee74-4ab8-b05f-7a3cbdc2a436', 'CHB_ASSET_INFO', 'PRIVATEPRD_FLAG', '是否投向金融资产投资公司发行的私募资产管理产品', 'SVR_YES_OR_NO', 103, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('d75d900a-8680-4067-8318-274a906b4055', 'CHB_ASSET_INFO', 'REGIST_ORG1', '登记备案机构', 'SVR_REGIST_ORG2', 104, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('4b243d20-1411-4223-b433-b8e504a2c307', 'CHB_ASSET_INFO', 'IS_GOVERN_FUND', '是否属于政府投资基金', 'SVR_YES_OR_NO', 105, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('d12e6e5e-942d-44f2-a23c-ddadfd6acb75', 'CHB_ASSET_INFO', 'GOVERN_INVEST_FUND', '政府投资基金投向', 'SVR_GOV_INVEST_DIRECT', 106, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('1b11c34a-1157-4e43-b388-481a728afb80', 'CHB_ASSET_INFO', 'FUND_ENPE_NAME', '基金公司名称', '', 107, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('a21ce27b-e057-4452-b56f-acee9e396752', 'CHB_ASSET_INFO', 'MNG_ORG_NAME', '基金管理机构名称', '', 108, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('bcc39734-53b9-4f3e-927e-e3c04a77d537', 'CHB_ASSET_INFO', 'TRU_ORG_NAME', '基金托管机构名称', '', 109, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('af87f011-89ef-4728-81dc-dff6caa52169', 'CHB_ASSET_INFO', 'PRIVATEFUND_FLAG', '是否投向“金融资产投资公司或其附属机构发行的私募股权投资基金', 'SVR_YES_OR_NO', 110, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('546a8e7e-0f15-4cbc-9402-02ecec343416', 'CHB_ASSET_INFO', 'FUND_INVEST_ASSET', '基金投资资产', '', 111, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('9b8a0d81-5e16-4e0c-8eee-1582865cdd67', 'CHB_ASSET_INFO', 'TRUSTEE', '受托人', '', 112, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('aa8faa14-3849-4c38-9119-8a858b93582a', 'CHB_ASSET_INFO', 'NOM_CAPI', '名义本金', '', 113, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('da05f026-c23d-409f-98a6-63b0117c35a6', 'CHB_ASSET_INFO', 'TARGET_TYPE3', '标的类别', 'SVR_TARGET_TYPE', 114, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('e99f009b-cfe1-4e52-8aea-2abf8ed55537', 'CHB_ASSET_INFO', 'COUNTRY_AREA', '所属国家或地区', 'SVR_COUNTRY_AREA', 115, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('c8b70b6f-ba4a-4858-bd1b-a91da3fbe26f', 'CHB_ASSET_INFO', 'TERM_DATE', '期限（天）', '', 116, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('59b36329-2703-42e5-922f-2d7dbfd12119', 'CHB_ASSET_INFO', 'DEBT_SCALE', '负债规模', '', 117, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('8f99c5d3-5bf9-409f-adc5-901eab795101', 'CHB_ASSET_INFO', 'CUSTOMER_OBJ_TYPE', '客户主体类型', 'SVR_CUSTOMER_TYPE1', 118, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('d33caa88-101a-48a5-bc67-913f7b580ebb', 'CHB_ASSET_INFO', 'ISSUE_PARTY_NAME', '发行机构', '', 119, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('f72a4511-50fd-4820-a0d9-99bffb308556', 'CHB_ASSET_INFO', 'TERM_MONTH', '期限（月）', '', 120, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('dd459511-4729-40af-b5bd-3f73c390e202', 'CHB_ASSET_INFO', 'PARTY_GRADE', '发行机构主体信用评级', '', 121, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('0663e814-cf77-488a-a033-7c452f94b757', 'CHB_ASSET_INFO', 'SEC_CREDIT_GRADE_TYPE', '债券信用评级', '', 122, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('d0e2dc78-10df-4a56-9d07-f617364e3290', 'CHB_ASSET_INFO', 'SPECIAL_CMT', '是否含权等特殊条款情况说明', '', 123, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('1cf4e672-8117-4a96-a702-743d8dd77c3c', 'CHB_ASSET_INFO', 'CONTRACT_NAME', '合约名称', '', 124, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('80e57773-d23f-4ee3-847c-52efee8b173b', 'CHB_ASSET_INFO', 'INCOME_RATIO', '固定收益部分所占比例(%)', '', 125, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('8990f3a2-96b8-48c0-a201-59e68f14a882', 'CHB_ASSET_INFO', 'DER_INSTR_RATIO', '衍生金融工具所占比例(%)', '', 126, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('910d7ec3-ce60-4387-af7e-d74c41756309', 'CHB_ASSET_INFO', 'DER_INSTR_INV_TYPE', '衍生金融工具具体投资方式', '', 127, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('29f26899-2e0e-4c4f-9570-de99564e39b0', 'CHB_ASSET_INFO', 'DER_INSTR_TARGET', '衍生金融工具挂钩的标的资产', '', 128, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('834860a3-9a53-40bc-b861-f05a3d297719', 'CHB_ASSET_INFO', 'ACCT_MODE', '利息结算方式', '', 129, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('0ee06bb2-5e9d-44c7-b427-c5b201d74de6', 'CHB_ASSET_INFO', 'SPECIAL_CMT1', '含权情况说明', '', 130, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('8505f831-60ed-40c8-886c-0648536d792f', 'CHB_ASSET_INFO', 'MAX_YIELD', '结构性票据最高收益率%', '', 131, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('7a34e672-d84e-4342-98e5-7093ceb2695a', 'CHB_ASSET_INFO', 'MIN_YIELD', '结构性票据最低收益率%', '', 132, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('9a5df5a4-9db2-423e-abf3-b058e4d5aa33', 'CHB_ASSET_INFO', 'BASIC_PRICE', '挂钩标的资产基准价格', '', 133, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('60f830e5-3192-4aec-b408-f407b03a0418', 'CHB_ASSET_INFO', 'REG_DATE_PRICE', '挂钩标的资产登记日价格', '', 134, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('33d482b9-0fb9-4d28-ab38-be1106d00dd6', 'CHB_ASSET_INFO', 'TRADE_FEE', '交易费', '', 135, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('26605382-f686-4d41-b4a8-71223f9d3ca3', 'CHB_ASSET_INFO', 'ASSET_VALUE', '资产价值', '', 136, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('6368001c-2ad5-4917-b15a-1ee41d7b42d0', 'CHB_ASSET_INFO', 'IN_ASSET_TYPE', '行内资产类别', 'SVR_BANK_IN_ASSET_TYPE', 137, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('2a2e484a-86f1-4a29-95df-21fc7b06db5a', 'CHB_ASSET_INFO', 'IN_ASSET_TYPE_CMM', '行内资产类别说明', '', 138, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('85ac5c01-0f04-4e51-9777-51ee9746236d', 'CHB_ASSET_INFO', 'REMARK', '备注', '', 139, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('11610160-5402-4b52-a99c-007e0b0fd432', 'CHB_ASSET_INFO', 'BLN_AREA', '境内外', 'BLN_AREA', 140, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('56f58b0a-5203-4c68-9eba-b585352f7065', 'CHB_ASSET_INFO', 'ORG_ASSET_TYPE1', '资产一级分类', 'PINAN_ASSET_TYPE', 141, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('7a506be5-dd3c-43ab-8da8-316620ea0a07', 'CHB_ASSET_INFO', 'ORG_ASSET_TYPE2', '资产二级分类', '', 142, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('b340564a-cc92-4afd-8b05-9142511ae7b8', 'CHB_ASSET_INFO', 'ORG_ASSET_TYPE3', '资产三级分类', '', 143, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('e40feb2f-c057-4668-840f-1df0406e86fc', 'CHB_ASSET_INFO', 'PBC_ASSET_TYPE1', '人行资产一级分类', 'SVR_PBC_ASSET_TYPE_1', 144, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('24d57f3c-28c9-4a34-8706-6dfa4a4fcbe0', 'CHB_ASSET_INFO', 'PBC_ASSET_TYPE2', '人行资产二级分类', 'SVR_PBC_ASSET_TYPE_2', 145, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('09116328-f2b9-48f5-85e1-f9ebbfcec4d0', 'CHB_ASSET_INFO', 'PBC_ASSET_TYPE3', '人行资产三级分类', 'SVR_PBC_ASSET_TYPE_3', 146, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('43ea4c6b-6eb6-4f87-8034-959e85e6114e', 'CHB_ASSET_INFO', 'ASSET_CODE_OLD', '历史资产代码', '', 147, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('321c1c82-8bc0-4ce2-9c20-59d156f60688', 'CHB_ASSET_INFO', 'INDUSTRY_PBC', '融资人所属行业一级分类（人行表2-2）', 'INSTITUTION_INDUSTRY_FIRST_LEVEL', 148, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('05667fa0-5b54-4b7e-ac00-308a1e3288dd', 'CHB_ASSET_INFO', 'PRIVATE_FLAG', '是否投向地方政府融资平台（银行月报）', 'SVR_YES_OR_NO', 149, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('003fba38-4658-4d2b-a6b1-cfd63c5b5bb3', 'CHB_ASSET_INFO', 'BUY_PLACE', '发行人融资人所属地区（人行表2-3）', 'ADMINISTRATIVE_TYPE2', 150, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('83a8d7e9-e25d-4855-917d-7eeb90b2e435', 'CHB_ASSET_INFO', 'CREATE_USER', '创建人', '', 151, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('6444673f-bcec-4ab3-9129-eed529674bde', 'CHB_ASSET_INFO', 'CREATE_TIME', '创建时间', '', 152, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('1cc15c75-4fe3-4aab-b231-9a6423075485', 'CHB_ASSET_INFO', 'UPDATE_USER', '更新人', '', 153, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('1bd5f767-6364-4092-a30f-b61880464889', 'CHB_ASSET_INFO', 'UPDATE_TIME', '更新时间', '', 154, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('72ea25d4-6b93-4565-b1ab-1f597e01af79', 'CHB_ASSET_INFO', 'DATA_SOURCE', '数据来源', '', 155, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('cd0bd02b-1106-4f69-b744-761b1e29f8c4', 'CHB_ASSET_INFO', 'STATUS_DATE', '数据日期', '', 156, 'N');

