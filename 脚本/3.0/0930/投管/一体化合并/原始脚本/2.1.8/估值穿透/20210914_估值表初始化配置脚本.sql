--指标映射
delete from rsk_valtbl_struct_map where id in ('dd92cdec63c8481fabff','a44a2fba3898472896f1');
insert into rsk_valtbl_struct_map (ID, NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a44a2fba3898472896f1', '非QDII净值-（共通）', 'ruanyy', null, sysdate, 'cyh206', sysdate);

insert into rsk_valtbl_struct_map (ID, NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', 'QDII净值-（共通 ）', 'cyh206', 'BM02', sysdate, 'cyh206',sysdate);

--指标映射明细
delete from RSK_VALTBL_STRUCT_MAP_DTL where id in ('dd92cdec63c8481fabff','a44a2fba3898472896f1');

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '4', 'NUMBER', 'EXCHANGE_RATE', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '5', 'NUMBER', 'QUANTITY', null, 'admin', null,sysdate, 'admin',sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '6', 'NUMBER', 'UNIT_COST', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '7', 'NUMBER', 'COST_ORI', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '8', 'NUMBER', 'COST', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '9', 'PERCENT', 'CTNWR', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '10', 'NUMBER', 'MCP', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '11', 'NUMBER', 'SMV_ORI', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '12', 'NUMBER', 'SMV', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '13', 'PERCENT', 'MCR', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '14', 'NUMBER', 'VAL_INC_ORI', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '15', 'NUMBER', 'VAL_INC', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('dd92cdec63c8481fabff', '16', 'STRING', 'SUSPENSION_INFO', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a44a2fba3898472896f1', '10', 'NUMBER', 'SMV', '市值本币', 'ruanyy', '001', sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a44a2fba3898472896f1', '11', 'PERCENT', 'MCR', '市值占比', 'ruanyy', '001', sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a44a2fba3898472896f1', '12', 'NUMBER', 'VAL_INC', '估值增值本币', 'ruanyy', '001', sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a44a2fba3898472896f1', '13', 'STRING', 'SUSPENSION_INFO', '停牌信息', 'ruanyy', '001', sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a44a2fba3898472896f1', '3', 'NUMBER', 'QUANTITY', '数量', 'ruanyy', '001', sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a44a2fba3898472896f1', '4', 'NUMBER', 'UNIT_COST', '单位成本', 'ruanyy', '001', sysdate, 'cyh203', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a44a2fba3898472896f1', '7', 'NUMBER', 'COST', '成本本币', 'ruanyy', '001', sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a44a2fba3898472896f1', '8', 'PERCENT', 'CTNWR', '成本占比', 'ruanyy', '001', sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_STRUCT_MAP_DTL (ID, SRC_COL_INDEX, SRC_COL_DATA_TYPE, TGT_INDEX, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a44a2fba3898472896f1', '9', 'NUMBER', 'MCP', '行情价格', 'ruanyy', '001', sysdate, 'ruanyy', sysdate);

--估值表资产科目映射

delete from RSK_VALTBL_ASSET_SUB_MAP where id in ('33a065e3bb984674ae64','a11822dd3b7c40ee9f8a','d25b93ed1d73404d9a42','fe985eda73f04721a2e0');

insert into RSK_VALTBL_ASSET_SUB_MAP (ID, NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '巴美思3.5系统-（非QDII 净值）', 'ruanyy', null, sysdate, 'cyh206', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP (ID, NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '恒生3.0系统-（非QDII 净值）', 'ruanyy', null, sysdate, 'cyh206',sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP (ID, NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '赢时胜4.5系统-（非QDII 净值 ）', 'fqq', null, sysdate, 'cyh206', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP (ID, NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('fe985eda73f04721a2e0', '赢时胜2.5系统-（QDII 净值）', 'cyh206', 'BM02', sysdate, 'cyh206', sysdate);

--估值表资产科目映射明细
delete from RSK_VALTBL_ASSET_SUB_MAP_DTL where id in ('33a065e3bb984674ae64','a11822dd3b7c40ee9f8a','d25b93ed1d73404d9a42','fe985eda73f04721a2e0');

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '100201', '100201', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '102101', '102106', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '102104', '102106', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '102112', '102106', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '1031', '103106', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '103103', '103106', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '103105', '103106', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '110102', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11010304', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11021101', '11020101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11021199', '11020101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11022101', '11020101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11022199', '11020101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11023101', '11021001', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11023199', '11021001', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11024101', '11021001', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11024199', '11021001', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11029101', '11022101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11029199', '11022101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '1103', '12040621', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '110312', '110301', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '110353', '110321', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '110359', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11035B', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11035D', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11035G', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11035J', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11035Z', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '110511', '110501', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11051101', '11050101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11057201', '11050201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '1108', '1105', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '110802', '110501', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11080201', '11050101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '110804', '110501', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11080401', '11050101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '110812', '110501', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11081201', '11050101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '110813', '110501', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11081301', '11050101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '110871', '110501', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '11087101', '11050101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '111201', '120201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '120211', '120201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12021101', '12020101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12025101', '12020201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '120401', '120401', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040101', '12040101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '1204010101', '120401', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040102', '12040102', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040103', '12040103', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040104', '12040104', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040105', '12040105', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040106', '12040106', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040107', '12040107', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040108', '12040108', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040109', '12040109', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '120402', '120402', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040201', '120402', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040202', '120402', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040204', '120402', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '120403', '120403', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040301', '120403', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12040303', '120403', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '120404', '120404', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '120410', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12041012', '12040601', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12041053', '12040621', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12041059', '12040621', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '1204105B', '12040621', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '1204105D', '12040621', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '1204105G', '12040621', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '1204105J', '12040621', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '12041251', '12020201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '122199', '120418', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '2231', '2231', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '223101', '223101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '22310151', '22310101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '3003', '3003', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '300301', '300301', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '30030101', '300301', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '30030111', '300301', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '300302', '300302', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '300371', '300399', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '300399', '300399', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '31030101', '31030101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('33a065e3bb984674ae64', '31030102', '31030102', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1002', '1002', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '100201', '100201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1021', '1021', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '102101', '102106', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '102103', '102106', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1031', '1031', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '103101', '103106', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '103103', '103106', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1102', '1102', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11020101', '11020101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11022301', '11020101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11022A01', '11020101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11022E01', '11020101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11022I01', '11020101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11023101', '11021001', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11025801', '11021001', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11026101', '11021001', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11026601', '11022201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11027401', '11021001', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1103', '1103', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11030401', '11021001', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11030601', '11030101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110313', '110301', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11031301', '110301', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11031399', '110301', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110316', '110301', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11031601', '11030101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11031699', '11030102', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110319', '110301', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11031901', '11030101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11033301', '110311', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11033399', '110311', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11033401', '11030101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110339', '11031101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11033901', '11031101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110353', '110321', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11035301', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11035399', '11032102', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110354', '110321', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11035401', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11035499', '11032102', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110356', '110321', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11035601', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110357', '110321', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11035701', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11035799', '11032102', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110358', '110321', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11035801', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11035899', '11032102', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11036901', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11036999', '11032102', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11037001', '11032101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11037099', '11032102', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11037201', '11030101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11037901', '110301', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11038201', '11030101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1103I401', '11030101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1103L401', '11030101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1104', '1103', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110411', '11030101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11041101', '11030101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110431', '11031101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110451', '110321', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1105', '1105', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11050201', '11050201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11050299', '11050201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11050301', '11050101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11050399', '11050101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11050401', '11050101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11050499', '11050101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110507', '110502', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11050701', '11050201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110521', '22090202', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '110524', '110502', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11052401', '11050201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '11081013136809', '120203', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '111201', '12020101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1202', '1202', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '120303', '120302', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12030304', '120302', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '120305', '120302', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1204', '1204', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '120401', '120401', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12040101', '12040101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '120402', '120402', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12040201', '120402', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12040203', '120402', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12040204', '120402', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '120403', '120403', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12040301', '120403', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12040302', '120403', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12040304', '120403', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '120408', '120403', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '120409', '120403', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '120410', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041004', '12040601', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041006', '1204', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041012', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041013', '12040601', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041013136809', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041013139344', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041013145188', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041013145202', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041013145299', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041013145344', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041013145442', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041013150056', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041014', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041015', '12040601', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041016', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041019', '12040601', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041032', '12040611', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041036', '1204', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041039', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041051', '12040621', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041052', '12040621', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041053', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041054', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041056', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041057', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041058', '12040621', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041059', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041070', '120406', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041082', '1204', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '120411', '12040601', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041111', '12040601', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041131', '12040611', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12041151', '12040621', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '12049107', '1204', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1207', '1701', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '120701', '120418', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '1221', '220202', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '19021013136809', '120202', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '220201', '220201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '22020101', '22020101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '220202', '220203', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '220203', '22020201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '22020301', '22020201', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '223101', '223101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '22310111', '22310101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '22310151', '22310101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '3003', '3003', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '300301', '300301', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '300302', '300302', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '300312', '300302', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '30031204', '300302', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '31030101', '31030101', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a11822dd3b7c40ee9f8a', '31030102', '31030102', 'Y', null, 'ruanyy', null, sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1002', '1002', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '100201', '100201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1021', '1021', 'Y', null, 'admin', null,sysdate, 'admin',sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1031', '1031', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110201', '110201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11020101', '11020101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110223', '110201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11022301', '11020101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11022A', '110201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11022A01', '11020101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11022E', '110201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11022E01', '11020101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11022I', '110201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11022I01', '11020101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110231', '110210', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11023101', '11021001', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110233', '110210', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11023301', '11021001', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110234', '110210', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11023401', '11021001', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11025101', '11023101', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110258', '110210', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11025801', '11021001', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110261', '110210', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11026101', '11021001', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110266', '110222', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11026601', '11022201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110274', '110210', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11027401', '11021001', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11028101', '11022201', 'Y', null, 'admin', null,sysdate, 'admin',sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11028901', '11020101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110301', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11030101', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110302', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11030201', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11030202', '11030102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110303', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11030301', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11030302', '11030102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110304', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11030401', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110306', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11030601', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11030602', '11030102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110313', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11031301', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110314', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11031401', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110317', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11031701', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110319', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11031901', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110320', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11032001', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110321', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11032101', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110332', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11033201', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110333', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11033301', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110334', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11033401', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110336', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11033601', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110337', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11033701', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11033901', '11031101', 'Y', null, 'cyh206', 'BM02', sysdate, 'cyh206', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110341', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11034101', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110350', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11035001', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110352', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11035201', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11035301', '11032101', 'Y', null, 'cyh206', 'BM02', sysdate, 'cyh206', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110355', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11035501', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11035701', '11032101', 'Y', null, 'cyh206', 'BM02',sysdate, 'cyh206',sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11035801', '11032101', 'Y', null, 'cyh206', 'BM02', sysdate, 'cyh206', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110359', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11035901', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110360', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11036001', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110362', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11036201', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110363', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11036301', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11036302', '11031102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110365', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11036501', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110366', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11036601', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110368', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11036801', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110370', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11037001', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110372', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11037201', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110373', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11037301', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110376', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11037601', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110377', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11037701', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110380', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11038001', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110382', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11038201', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110383', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11038301', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110399', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11039901', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103A4', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103A401', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103A901', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103B501', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103B6', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103B601', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103B602', '11032102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103B9', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103B901', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103B902', '11032102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103C2', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103C201', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103C202', '11032102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103C4', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103C401', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103C402', '11032102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103C6', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103C601', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103C602', '11032102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103D1', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103D101', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103D102', '11032102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103D3', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103D301', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103D302', '11032102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103D4', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103D401', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103I4', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103I401', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103K1', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103K101', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103K102', '11032102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103K2', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103K201', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103K202', '11032102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103L4', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1103L401', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110401', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11040101', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11041101', '11030101', 'Y', null, 'cyh206', 'BM02', '03-SEP-21 04.47.51.339000 PM', 'cyh206', '03-SEP-21 04.47.51.339000 PM');

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11043101', '11031101', 'Y', null, 'cyh206', 'BM02', '03-SEP-21 04.48.11.779000 PM', 'cyh206', '03-SEP-21 04.48.11.779000 PM');

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11045101', '11032101', 'Y', null, 'cyh206', 'BM02', '03-SEP-21 04.48.30.881000 PM', 'cyh206', '03-SEP-21 04.48.30.881000 PM');

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1105', '1105', 'Y', null, 'www', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110507', '110502', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11050701', '11050201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110512', '110502', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11051201', '11050201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11051501', '11050401', 'Y', null, 'admin', null, '07-SEP-21 03.55.10.822000 PM', 'admin', '07-SEP-21 03.55.10.822000 PM');

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110521', '110502', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11052101', '11050201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11052301', '11050201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110524', '110502', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11052401', '11050201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11055201', '11050201', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110561', '110501', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11056101', '11050101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1108', '1105', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '110801', '110501', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11080101', '11050201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11090301', '31030101', 'Y', null, 'cyh206', 'BM02', sysdate, 'cyh206', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '111302', '110301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11130201', '11030101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '111310', '110311', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11131001', '11031101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '111318', '110321', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11131801', '11032101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '11131802', '11032102', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120201', '120201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12020101', '12020101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120207', '120202', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12020701', '12020201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12021101', '12020101', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120251', '120202', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12025101', '12020201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1203', '1203', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120303', '120302', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12030652', '12030202', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1204', '1204', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120401', '120401', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120410', '120406', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041001', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041002', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041003', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041004', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041006', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041013', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041017', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041018', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041019', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041020', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041032', '12040611', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041034', '12040611', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041036', '12040611', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041037', '12040611', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041049', '12040611', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041052', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041055', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041060', '12040611', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041062', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041063', '12040611', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041066', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041068', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041070', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041072', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041073', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041076', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041077', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041082', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041083', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120410B6', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120410B9', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120410C2', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120410C4', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120410C6', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120410D1', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120410D3', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120410H3', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120410I4', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120410L4', '12040611', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120411', '120406', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041101', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12041102', '12040611', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120420', '120406', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12042002', '12040601', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12042010', '12040611', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12042018', '12040621', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '120491', '120410', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12049107', '12041001', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '12049151', '12041001', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '1221', '1204', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '122101', '120401', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '220201', '220201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '22020101', '22020101', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '220204', '220203', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '22020401', '22020301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '220207', '220202', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '22020701', '22020201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '220251', '220202', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '22025101', '22020201', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '223101', '22020201', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '3003', '3003', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '300301', '300301', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '300302', '300302', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '300303', '300399', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '300304', '300399', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('d25b93ed1d73404d9a42', '300312', '300302', 'Y', null, 'fqq', null, sysdate, 'fqq', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('fe985eda73f04721a2e0', '100201', '100201', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('fe985eda73f04721a2e0', '11029601', '11022101', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('fe985eda73f04721a2e0', '11029701', '11022201', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('fe985eda73f04721a2e0', '12030401', '12030190', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('fe985eda73f04721a2e0', '12030601', '12030182', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('fe985eda73f04721a2e0', '120401', '120401', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('fe985eda73f04721a2e0', '120402', '120402', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('fe985eda73f04721a2e0', '120403', '120403', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('fe985eda73f04721a2e0', '300309', '300399', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_ASSET_SUB_MAP_DTL (ID, SRC_CODE, TGT_CODE, ALLOW_SUM, MEMO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('fe985eda73f04721a2e0', '300312', '300399', 'Y', null, 'admin', null, sysdate, 'admin', sysdate);

--估值表汇总科目映射
delete from RSK_VALTBL_SUMSUB_MAP where id in ('4b589bf1db1d48c0910d','a167adf8bc7d46fe8f6f');

insert into RSK_VALTBL_SUMSUB_MAP (ID, NAME, TRIM_CHARS, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('4b589bf1db1d48c0910d', '净值-（共通）-单位净值', ':,：', 'ruanyy', null, sysdate, 'admin', sysdate);

insert into RSK_VALTBL_SUMSUB_MAP (ID, NAME, TRIM_CHARS, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a167adf8bc7d46fe8f6f', '净值-（共通）-今日单位净值', ':,：', 'cyh206', 'BM02', sysdate, 'admin', sysdate);


--估值表汇总科目映射明细
delete from RSK_VALTBL_SUMSUB_MAP_DTL where id in ('4b589bf1db1d48c0910d','a167adf8bc7d46fe8f6f');

insert into RSK_VALTBL_SUMSUB_MAP_DTL (ID, SUM_CODE, SUM_NAME, HAS_NEXT_INDEX, NEXT_INDEX_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('4b589bf1db1d48c0910d', '103', '今日可用头寸', 'N', null, 'ruanyy', '001', sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_SUMSUB_MAP_DTL (ID, SUM_CODE, SUM_NAME, HAS_NEXT_INDEX, NEXT_INDEX_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('4b589bf1db1d48c0910d', '601', '实收资本', 'N', null, 'ruanyy', '001', sysdate, 'ruanyy', sysdate);

insert into RSK_VALTBL_SUMSUB_MAP_DTL (ID, SUM_CODE, SUM_NAME, HAS_NEXT_INDEX, NEXT_INDEX_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('4b589bf1db1d48c0910d', '604', '资产合计', 'N', null, 'ruanyy', '001', sysdate, 'cyh203', sysdate);

insert into RSK_VALTBL_SUMSUB_MAP_DTL (ID, SUM_CODE, SUM_NAME, HAS_NEXT_INDEX, NEXT_INDEX_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('4b589bf1db1d48c0910d', '605', '负债合计', 'N', null, 'ruanyy', '001', sysdate, 'cyh203', sysdate);

insert into RSK_VALTBL_SUMSUB_MAP_DTL (ID, SUM_CODE, SUM_NAME, HAS_NEXT_INDEX, NEXT_INDEX_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('4b589bf1db1d48c0910d', '701', '资产净值', 'N', null, 'ruanyy', '001', sysdate, 'cyh203', sysdate);

insert into RSK_VALTBL_SUMSUB_MAP_DTL (ID, SUM_CODE, SUM_NAME, HAS_NEXT_INDEX, NEXT_INDEX_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('4b589bf1db1d48c0910d', '702', '单位净值', 'N', null, 'ruanyy', '001', sysdate, 'cyh206', sysdate);

insert into RSK_VALTBL_SUMSUB_MAP_DTL (ID, SUM_CODE, SUM_NAME, HAS_NEXT_INDEX, NEXT_INDEX_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a167adf8bc7d46fe8f6f', '103', '可用今日头寸', 'N', null, 'cyh206', 'BM02', sysdate, 'cyh206', sysdate);

insert into RSK_VALTBL_SUMSUB_MAP_DTL (ID, SUM_CODE, SUM_NAME, HAS_NEXT_INDEX, NEXT_INDEX_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a167adf8bc7d46fe8f6f', '601', '实收资本', 'N', null, 'cyh206', 'BM02', sysdate, 'cyh206', sysdate);

insert into RSK_VALTBL_SUMSUB_MAP_DTL (ID, SUM_CODE, SUM_NAME, HAS_NEXT_INDEX, NEXT_INDEX_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a167adf8bc7d46fe8f6f', '604', '资产合计', 'N', null, 'cyh206', 'BM02',sysdate, 'cyh206',sysdate);

insert into RSK_VALTBL_SUMSUB_MAP_DTL (ID, SUM_CODE, SUM_NAME, HAS_NEXT_INDEX, NEXT_INDEX_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a167adf8bc7d46fe8f6f', '605', '负债合计', 'N', null, 'cyh206', 'BM02', sysdate, 'cyh206', sysdate);

insert into RSK_VALTBL_SUMSUB_MAP_DTL (ID, SUM_CODE, SUM_NAME, HAS_NEXT_INDEX, NEXT_INDEX_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a167adf8bc7d46fe8f6f', '701', '资产净值', 'N', null, 'cyh206', 'BM02', sysdate, 'cyh206', sysdate);

insert into RSK_VALTBL_SUMSUB_MAP_DTL (ID, SUM_CODE, SUM_NAME, HAS_NEXT_INDEX, NEXT_INDEX_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('a167adf8bc7d46fe8f6f', '702', '今日单位净值', 'N', null, 'cyh206', 'BM02', sysdate, 'cyh206', sysdate);





















