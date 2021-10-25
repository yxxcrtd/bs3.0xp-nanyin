delete from REP_REPORTINFO where ID = '1112' and REPORTDESC = '一体化资产负债表';
delete from REP_REPORTINFO where ID = '1113' and REPORTDESC = '一体化利润表';
delete from REP_REPORTINFO where ID = '1114' and REPORTDESC = '一体化所有者权益变动表';

delete from rep_tree where TREE_ID = 'CP_003_001' and TREE_NAME = '一体化资产负债表';
delete from rep_tree where TREE_ID = 'CP_003_002' and TREE_NAME = '一体化利润表';
delete from rep_tree where TREE_ID = 'CP_003_003' and TREE_NAME = '一体化所有者权益变动表';

delete from REP_INFO where REP_ID = 'CP_003_001' and REPORT_NAME = '一体化资产负债表';
delete from REP_INFO where REP_ID = 'CP_003_002' and REPORT_NAME = '一体化利润表';
delete from REP_INFO where REP_ID = 'CP_003_003' and REPORT_NAME = '一体化所有者权益变动表';

delete from REP_SCREEN where REP_ID = 'CP_003_001';
delete from REP_SCREEN where REP_ID = 'CP_003_002';
delete from REP_SCREEN where REP_ID = 'CP_003_003';

