delete from CHB_INFOMGR_RESULT_CONFIG WHERE MGRTABLE_ID='CHB_BOT_AST_POS';
insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('8a00decd-210a-4ab2-aa1e-815346e3f060', 'CHB_BOT_AST_POS', 'POS_ID', '主键', '', 1, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('80831b3e-d24f-4b21-8c2b-88f25ed0e862', 'CHB_BOT_AST_POS', 'POS_DATE', '持仓日期', '', 2, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('f7dbdcab-5f7b-47a8-b73b-c085f63ee4e5', 'CHB_BOT_AST_POS', 'PLAN_ASSET_ID', '资管计划行内资产/负债编码', '', 3, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('d81fffbd-33e5-4ba7-9abc-7623314756fa', 'CHB_BOT_AST_POS', 'ASSET_CODE', '行内资产/负债编码', '', 4, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('8fbca0ee-2b56-4eb1-8fea-a53ca79a47e7', 'CHB_BOT_AST_POS', 'TOTAL_NUM', '总发行数量', '', 5, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('08124092-b153-4a2f-927d-6b09b9bed07e', 'CHB_BOT_AST_POS', 'ISSUE_AMT', '人民币发行金额', '', 6, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('0e901833-422b-45f6-b5d1-d1ec0b818a4f', 'CHB_BOT_AST_POS', 'UNINVEST_CASH', '未投资头寸', '', 7, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('1861f45b-66c8-4220-923d-d0f0ef968d12', 'CHB_BOT_AST_POS', 'POS_NUM', '持仓数量', '', 8, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('ddcfeb09-1f04-4509-a695-67cf916da365', 'CHB_BOT_AST_POS', 'POS_AMT', '底层资产折算人民币币值（元）', '', 9, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('5bde363f-b8c7-45ea-ace2-00627045abbe', 'CHB_BOT_AST_POS', 'ISFINAPLAN', '是否资管计划', 'SVR_YES_OR_NO', 10, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('3c7f7bbb-4e2e-4943-b813-b64b551e43fc', 'CHB_BOT_AST_POS', 'ISCHL', '是否通过通道', 'SVR_YES_OR_NO', 11, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('8e7c55cb-2e59-466b-8ebe-d777745e86f2', 'CHB_BOT_AST_POS', 'STATUS', '数据状态', 'MAINTAIN_STATUS', 12, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('df647067-8af0-4ebb-b263-5e44719b0ea3', 'CHB_BOT_AST_POS', 'ISSUE_PARTY_ID', '发行机构代码', '', 13, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('73b2f950-cfa6-4dce-b937-899512af3ec2', 'CHB_BOT_AST_POS', 'CREATE_USER', '创建人', '', 14, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('a8840acb-6636-4e5c-ad42-3a8d869ef682', 'CHB_BOT_AST_POS', 'CREATE_TIME', '创建时间', '', 15, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('d7f4010b-8141-460e-8461-2c5228bd3d0b', 'CHB_BOT_AST_POS', 'UPDATE_USER', '更新人', '', 16, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('37df13a6-9833-4821-8d58-1ac6fd305a76', 'CHB_BOT_AST_POS', 'UPDATE_TIME', '更新时间', '', 17, 'N');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('20adbbf0-5c7a-441e-a814-24eb9a9eeb0e', 'CHB_BOT_AST_POS', 'DATASOURCE', '数据来源', '', 18, 'N');

