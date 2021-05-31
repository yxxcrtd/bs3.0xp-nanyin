-- 计划类增加投资目的

--账套参数增加【债权类资产公允价值变动计算过程小数位】
delete from BOK_BOOKSET_EXT where PARAM_CODE='DELTRIGHT_FAIRVALUE_DIGITS';
insert into BOK_BOOKSET_EXT values ('00000000000000000002','DELTRIGHT_FAIRVALUE_DIGITS','2','债权类资产公允价值变动计算过程小数位','','system','',to_date('2020/10/10', 'yyyy/mm/dd'), 'system',to_date('2020/10/10', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','DELTRIGHT_FAIRVALUE_DIGITS','2','债权类资产公允价值变动计算过程小数位','','system','',to_date('2020/10/10', 'yyyy/mm/dd'), 'system',to_date('2020/10/10', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','DELTRIGHT_FAIRVALUE_DIGITS','2','债权类资产公允价值变动计算过程小数位','','system','',to_date('2020/10/10', 'yyyy/mm/dd'), 'system',to_date('2020/10/10', 'yyyy/mm/dd'));

---历史数据处理
INSERT INTO BOK_BOOKSET_EXT
SELECT A.BOOKSET_ID,B.PARAM_CODE,B.PARAM_VALUE,B.PARAM_NAME,B.REMARK,
B.CREATE_USER,B.CREATE_DEPT,B.CREATE_TIME,B.UPDATE_USER,B.UPDATE_TIME
FROM BOK_BOOKSET A,BOK_BOOKSET_EXT B
WHERE A.BOOKSET_ID_TMPL = B.BOOKSET_ID
AND B.PARAM_CODE ='DELTRIGHT_FAIRVALUE_DIGITS'
AND A.BOOKSET_ID_TMPL IN ('00000000000000000002','00000000000000000003','00000000000000000004');


--账套参数增加【本位币差额处理方式】
delete from BOK_BOOKSET_EXT where PARAM_CODE='BCCYADJ_TYPE';
insert into BOK_BOOKSET_EXT values ('00000000000000000002','BCCYADJ_TYPE','01','本位币差额处理方式：01 利息收入  02 减值准备','','system','',to_date('2020/10/20', 'yyyy/mm/dd'), 'system',to_date('2020/10/20', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','BCCYADJ_TYPE','01','本位币差额处理方式：01 利息收入  02 减值准备','','system','',to_date('2020/10/20', 'yyyy/mm/dd'), 'system',to_date('2020/10/20', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','BCCYADJ_TYPE','01','本位币差额处理方式：01 利息收入  02 减值准备','','system','',to_date('2020/10/20', 'yyyy/mm/dd'), 'system',to_date('2020/10/20', 'yyyy/mm/dd'));

---历史数据处理
INSERT INTO BOK_BOOKSET_EXT
SELECT A.BOOKSET_ID,B.PARAM_CODE,B.PARAM_VALUE,B.PARAM_NAME,B.REMARK,
B.CREATE_USER,B.CREATE_DEPT,B.CREATE_TIME,B.UPDATE_USER,B.UPDATE_TIME
FROM BOK_BOOKSET A,BOK_BOOKSET_EXT B
WHERE A.BOOKSET_ID_TMPL = B.BOOKSET_ID
AND B.PARAM_CODE ='BCCYADJ_TYPE'
AND A.BOOKSET_ID_TMPL IN ('00000000000000000002','00000000000000000003','00000000000000000004');


--账套参数增加【还本收息冲抵减值准备处理日】
delete from BOK_BOOKSET_EXT where PARAM_CODE='OFFSETIMPT_DATETYPE';
insert into BOK_BOOKSET_EXT values ('00000000000000000002','OFFSETIMPT_DATETYPE','01','还本收息冲抵减值准备处理日','01:理论日  02:资金到账日（默认01理论日）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','OFFSETIMPT_DATETYPE','01','还本收息冲抵减值准备处理日','01:理论日  02:资金到账日（默认01理论日）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','OFFSETIMPT_DATETYPE','01','还本收息冲抵减值准备处理日','01:理论日  02:资金到账日（默认01理论日）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));

--账套参数增加【卖出冲抵减值准备处理方式】
delete from BOK_BOOKSET_EXT where PARAM_CODE='OFFSETIMPT_SHELL_TYPE';
insert into BOK_BOOKSET_EXT values ('00000000000000000002','OFFSETIMPT_SHELL_TYPE','01','卖出冲抵减值准备处理方式（包括债转股、回售、赎回）','01:数量占比 02：金额占比 03：原金额占比','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','OFFSETIMPT_SHELL_TYPE','01','卖出冲抵减值准备处理方式（包括债转股、回售、赎回）','01:数量占比 02：金额占比 03：原金额占比','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','OFFSETIMPT_SHELL_TYPE','01','卖出冲抵减值准备处理方式（包括债转股、回售、赎回）','01:数量占比 02：金额占比 03：原金额占比','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));

--账套参数增加【收息冲抵减值准备处理方式】
delete from BOK_BOOKSET_EXT where PARAM_CODE='OFFSETIMPT_SX_TYPE';
insert into BOK_BOOKSET_EXT values ('00000000000000000002','OFFSETIMPT_SX_TYPE','01','收息还本冲抵减值准备处理方式','01:数量占比 02：金额占比 03：原金额占比','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','OFFSETIMPT_SX_TYPE','01','收息还本冲抵减值准备处理方式','01:数量占比 02：金额占比 03：原金额占比','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','OFFSETIMPT_SX_TYPE','01','收息还本冲抵减值准备处理方式','01:数量占比 02：金额占比 03：原金额占比','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));

---历史数据处理
INSERT INTO BOK_BOOKSET_EXT
SELECT A.BOOKSET_ID,B.PARAM_CODE,B.PARAM_VALUE,B.PARAM_NAME,B.REMARK,
B.CREATE_USER,B.CREATE_DEPT,B.CREATE_TIME,B.UPDATE_USER,B.UPDATE_TIME
FROM BOK_BOOKSET A,BOK_BOOKSET_EXT B
WHERE A.BOOKSET_ID_TMPL = B.BOOKSET_ID
AND B.PARAM_CODE IN ('OFFSETIMPT_DATETYPE','OFFSETIMPT_SHELL_TYPE','OFFSETIMPT_SX_TYPE')
AND A.BOOKSET_ID_TMPL IN ('00000000000000000002','00000000000000000003','00000000000000000004');


insert into BOK_BOOKSET_EXT values ('00000000000000000002','LOSS_P3_SECACCR_SUBJECT_NO','02','减值资产第三阶段利息计提摊销差额计入','01：减值准备 02：原科目（默认02：原科目）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000002','LOSS_P3_SECACCR_YEILD_RECALC','01','减值资产第三阶段是否重新计算实际利率','01：不重算 02：重算（默认01：不重算）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','LOSS_P3_SECACCR_SUBJECT_NO','02','减值资产第三阶段利息计提摊销差额计入','01：减值准备 02：原科目（默认02：原科目）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','LOSS_P3_SECACCR_YEILD_RECALC','01','减值资产第三阶段是否重新计算实际利率','01：不重算 02：重算（默认01：不重算）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','LOSS_P3_SECACCR_SUBJECT_NO','02','减值资产第三阶段利息计提摊销差额计入','01：减值准备 02：原科目（默认02：原科目）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','LOSS_P3_SECACCR_YEILD_RECALC','01','减值资产第三阶段是否重新计算实际利率','01：不重算 02：重算（默认01：不重算）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));



INSERT INTO BOK_BOOKSET_EXT
SELECT A.BOOKSET_ID,B.PARAM_CODE,B.PARAM_VALUE,B.PARAM_NAME,B.REMARK,
B.CREATE_USER,B.CREATE_DEPT,B.CREATE_TIME,B.UPDATE_USER,B.UPDATE_TIME
FROM BOK_BOOKSET A,BOK_BOOKSET_EXT B
WHERE A.BOOKSET_ID_TMPL = B.BOOKSET_ID
AND B.PARAM_CODE in ('LOSS_P3_SECACCR_YEILD_RECALC','LOSS_P3_SECACCR_SUBJECT_NO')
AND A.BOOKSET_ID_TMPL IN ('00000000000000000002','00000000000000000003','00000000000000000004');
delete from BOK_BOOKSET_EXT where param_code ='IS_OFFSETIMPT_SX';
insert into BOK_BOOKSET_EXT values ('00000000000000000002','IS_OFFSETIMPT_SX','N','收息是否冲抵减值准备','Y：是 N：否（默认N否）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','IS_OFFSETIMPT_SX','N','收息是否冲抵减值准备','Y：是 N：否（默认N否）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','IS_OFFSETIMPT_SX','N','收息是否冲抵减值准备','Y：是 N：否（默认N否）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
delete from BOK_BOOKSET_EXT where param_code ='LOSS_TEST_RATE';
delete from BOK_BOOKSET_EXT where param_code ='LOSS_TEST_TREAD';
delete from BOK_BOOKSET_EXT where param_code ='LOSS_TEST_DATE';
insert into BOK_BOOKSET_EXT values ('00000000000000000002','LOSS_TEST_RATE','05','减值测算频率','数据字典 LOSS_TEST_RATE','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000002','LOSS_TEST_DATE','2','减值测算日','数字','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000002','LOSS_TEST_TREAD','T01,T02,T03','触发减值交易类型','交易类型 数据字典PS,逗号分隔。','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','LOSS_TEST_RATE','05','减值测算频率','数据字典 LOSS_TEST_RATE','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','LOSS_TEST_DATE','2','减值测算日','数字','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','LOSS_TEST_TREAD','T01,T02,T03','触发减值交易类型','交易类型 数据字典PS,逗号分隔。','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','LOSS_TEST_RATE','05','减值测算频率','数据字典 LOSS_TEST_RATE','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','LOSS_TEST_DATE','2','减值测算日','数字','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','LOSS_TEST_TREAD','T01,T02,T03','触发减值交易类型','交易类型 数据字典PS,逗号分隔。','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));

insert into BOK_BOOKSET_EXT values ('00000000000000000002','AFT_EXP_IS_CAL','N','资产到期之后是否计提减值','Y：是 N：否（默认N否）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','AFT_EXP_IS_CAL','N','资产到期之后是否计提减值','Y：是 N：否（默认N否）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','AFT_EXP_IS_CAL','N','资产到期之后是否计提减值','Y：是 N：否（默认N否）','system','',to_date('2020/10/29', 'yyyy/mm/dd'), 'system',to_date('2020/10/29', 'yyyy/mm/dd'));

INSERT INTO BOK_BOOKSET_EXT
SELECT A.BOOKSET_ID,B.PARAM_CODE,B.PARAM_VALUE,B.PARAM_NAME,B.REMARK,
B.CREATE_USER,B.CREATE_DEPT,B.CREATE_TIME,B.UPDATE_USER,B.UPDATE_TIME
FROM BOK_BOOKSET A,BOK_BOOKSET_EXT B
WHERE A.BOOKSET_ID_TMPL = B.BOOKSET_ID
AND B.PARAM_CODE in ('LOSS_TEST_RATE','LOSS_TEST_TREAD','LOSS_TEST_DATE','IS_OFFSETIMPT_SX','AFT_EXP_IS_CAL')
AND A.BOOKSET_ID_TMPL IN ('00000000000000000002','00000000000000000003','00000000000000000004');
--账套参数增加【同业借款四级科目类型】
delete from BOK_BOOKSET_EXT where PARAM_CODE='TYJK_FORSUBJECT';
insert into BOK_BOOKSET_EXT values ('00000000000000000002','TYJK_FORSUBJECT','02','同业借款四级科目类型','01：资产代码  02：期限品种','system','',to_date('2019/11/30', 'yyyy/mm/dd'), 'system',to_date('2019/11/30', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000003','TYJK_FORSUBJECT','02','同业借款四级科目类型','01：资产代码  02：期限品种','system','',to_date('2019/11/30', 'yyyy/mm/dd'), 'system',to_date('2019/11/30', 'yyyy/mm/dd'));
insert into BOK_BOOKSET_EXT values ('00000000000000000004','TYJK_FORSUBJECT','02','同业借款四级科目类型','01：资产代码  02：期限品种','system','',to_date('2019/11/30', 'yyyy/mm/dd'), 'system',to_date('2019/11/30', 'yyyy/mm/dd'));


---历史数据处理
INSERT INTO BOK_BOOKSET_EXT
SELECT A.BOOKSET_ID,B.PARAM_CODE,B.PARAM_VALUE,B.PARAM_NAME,B.REMARK,
B.CREATE_USER,B.CREATE_DEPT,B.CREATE_TIME,B.UPDATE_USER,B.UPDATE_TIME
FROM BOK_BOOKSET A,BOK_BOOKSET_EXT B
WHERE A.BOOKSET_ID_TMPL = B.BOOKSET_ID
AND B.PARAM_CODE ='TYJK_FORSUBJECT'
AND A.BOOKSET_ID_TMPL IN ('00000000000000000002','00000000000000000003','00000000000000000004');
