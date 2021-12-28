--代理销售机构信息登记表创建
drop table CHB_AGENT_INSTITUTION_SUB;
create table CHB_AGENT_INSTITUTION_SUB(
   REGIST_SEQNO                 CHAR(36) NOT NULL
  ,AGENT_INSTITUTION_CODE	VARCHAR2(32) NOT NULL
  ,AGENT_INSTITUTION_NAME	VARCHAR2(80)
  ,ISSUE_PARTY_ID	VARCHAR2(6) NOT NULL
  ,STATUS	VARCHAR2(2)
  ,SUMIT_SEQ VARCHAR2(20) NOT NULL
  ,CHB_SUBMIT_DEADLINE TIMESTAMP(6)
  ,SUBMIT_DEADLINE     TIMESTAMP(6)
  ,SEND_STATUS         VARCHAR2(50)
  ,SUBMIT_TIME         TIMESTAMP(6)
  ,SUBMIT_FEEDBACK_STATUS VARCHAR2(50)
  ,SUBMIT_FEEDBACK_MSG  VARCHAR2(2000)
  ,ENCLOSURE_NUM	VARCHAR2(2)
  ,ENCLOSURE_ADDRESS	VARCHAR2(200)
  ,ENCLOSURE_CHANGE_FLAG VARCHAR2(2)
  ,FILE_NO VARCHAR2(600)
  ,DATA_DATE	DATE
  ,REMARK	VARCHAR2(200)
  ,CREATE_USER	VARCHAR2(20)
  ,CREATE_TIME	TIMESTAMP(6)
  ,UPDATE_USER	VARCHAR2(32)
  ,UPDATE_TIME	TIMESTAMP(6)
);
comment on table CHB_AGENT_INSTITUTION_SUB is '代理销售机构信息登记表';
comment on column CHB_AGENT_INSTITUTION_SUB.REGIST_SEQNO is '登记序列 ';
comment on column CHB_AGENT_INSTITUTION_SUB.AGENT_INSTITUTION_CODE is '代销机构代码 ';
comment on column CHB_AGENT_INSTITUTION_SUB.AGENT_INSTITUTION_NAME is '代销机构名称 ';
comment on column CHB_AGENT_INSTITUTION_SUB.ISSUE_PARTY_ID is '发行机构代码 ';
comment on column CHB_AGENT_INSTITUTION_SUB.STATUS is '数据状态 ';
comment on column CHB_AGENT_INSTITUTION_SUB.SUMIT_SEQ is '报送流水号 ';
comment on column CHB_AGENT_INSTITUTION_SUB.CHB_SUBMIT_DEADLINE is '中债报送截止日 ';
comment on column CHB_AGENT_INSTITUTION_SUB.SUBMIT_DEADLINE is '行内报送截止日 ';
comment on column CHB_AGENT_INSTITUTION_SUB.SEND_STATUS is '报送状态 ';
comment on column CHB_AGENT_INSTITUTION_SUB.SUBMIT_TIME is '报送时间 ';
comment on column CHB_AGENT_INSTITUTION_SUB.SUBMIT_FEEDBACK_STATUS is '反馈状态 ';
comment on column CHB_AGENT_INSTITUTION_SUB.SUBMIT_FEEDBACK_MSG is '报送反馈消息 ';
comment on column CHB_AGENT_INSTITUTION_SUB.ENCLOSURE_NUM is '附件个数 ';
comment on column CHB_AGENT_INSTITUTION_SUB.ENCLOSURE_ADDRESS is '附件存放目录（包括文件名称）';
comment on column CHB_AGENT_INSTITUTION_SUB.ENCLOSURE_CHANGE_FLAG is '附件修改标志';
comment on column CHB_AGENT_INSTITUTION_SUB.FILE_NO is '附件代码';
comment on column CHB_AGENT_INSTITUTION_SUB.DATA_DATE is '数据日期 ';
comment on column CHB_AGENT_INSTITUTION_SUB.REMARK is '备注 ';
comment on column CHB_AGENT_INSTITUTION_SUB.CREATE_USER is '创建人 ';
comment on column CHB_AGENT_INSTITUTION_SUB.CREATE_TIME is '创建时间 ';
comment on column CHB_AGENT_INSTITUTION_SUB.UPDATE_USER is '更新人 ';
comment on column CHB_AGENT_INSTITUTION_SUB.UPDATE_TIME is '更新时间 ';

--代理销售机构信息登记履历表创建
drop table CHB_AGENT_INSTITUTION_SUB_HIS;
create table CHB_AGENT_INSTITUTION_SUB_HIS(
   HIS_NO                 CHAR(36) NOT NULL
  ,AGENT_INSTITUTION_CODE	VARCHAR2(32) NOT NULL
  ,AGENT_INSTITUTION_NAME	VARCHAR2(80)
  ,ISSUE_PARTY_ID	VARCHAR2(6) NOT NULL
  ,STATUS	VARCHAR2(2)
  ,SUMIT_SEQ VARCHAR2(20) NOT NULL
  ,CHB_SUBMIT_DEADLINE TIMESTAMP(6)
  ,SUBMIT_DEADLINE     TIMESTAMP(6)
  ,SEND_STATUS         VARCHAR2(50)
  ,SUBMIT_TIME         TIMESTAMP(6)
  ,SUBMIT_FEEDBACK_STATUS VARCHAR2(50)
  ,SUBMIT_FEEDBACK_MSG  VARCHAR2(2000)
  ,ENCLOSURE_NUM	VARCHAR2(2)
  ,ENCLOSURE_ADDRESS	VARCHAR2(200)
  ,ENCLOSURE_CHANGE_FLAG VARCHAR2(2)
  ,FILE_NO VARCHAR2(600)
  ,DATA_DATE	DATE
  ,REMARK	VARCHAR2(200)
  ,CREATE_USER	VARCHAR2(20)
  ,CREATE_TIME	TIMESTAMP(6)
  ,UPDATE_USER	VARCHAR2(32)
  ,UPDATE_TIME	TIMESTAMP(6)
);
comment on table CHB_AGENT_INSTITUTION_SUB_HIS is '代理销售机构信息登记履历表';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.HIS_NO is '履历编号 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.AGENT_INSTITUTION_CODE is '代销机构代码 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.AGENT_INSTITUTION_NAME is '代销机构名称 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.ISSUE_PARTY_ID is '发行机构代码 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.STATUS is '数据状态 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.SUMIT_SEQ is '报送流水号 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.CHB_SUBMIT_DEADLINE is '中债报送截止日 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.SUBMIT_DEADLINE is '行内报送截止日 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.SEND_STATUS is '报送状态 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.SUBMIT_TIME is '报送时间 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.SUBMIT_FEEDBACK_STATUS is '反馈状态 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.SUBMIT_FEEDBACK_MSG is '报送反馈消息 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.ENCLOSURE_NUM is '附件个数 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.ENCLOSURE_ADDRESS is '附件存放目录（包括文件名称）';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.ENCLOSURE_CHANGE_FLAG is '附件修改标志';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.FILE_NO is '附件代码';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.DATA_DATE is '数据日期 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.REMARK is '备注 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.CREATE_USER is '创建人 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.CREATE_TIME is '创建时间 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.UPDATE_USER is '更新人 ';
comment on column CHB_AGENT_INSTITUTION_SUB_HIS.UPDATE_TIME is '更新时间 ';

  --委托销售产品编码信息登记表创建
drop table CHB_AGENT_PRD_SUB;
create table CHB_AGENT_PRD_SUB(
   REGIST_SEQNO   CHAR(36) NOT NULL
  ,AGENT_INSTITUTION_CODE VARCHAR2(32) NOT NULL
  ,ISSUE_PARTY_ID	VARCHAR2(6) NOT NULL
  ,PROD_ID   VARCHAR2(150)
  ,PROD_NAME VARCHAR2(300)
  ,PROD_REGIST_CODE VARCHAR2(50)
  ,STATUS	VARCHAR2(2)
  ,SUMIT_SEQ VARCHAR2(20) NOT NULL
  ,CHB_SUBMIT_DEADLINE TIMESTAMP(6)
  ,SUBMIT_DEADLINE     TIMESTAMP(6)
  ,SEND_STATUS         VARCHAR2(50)
  ,SUBMIT_TIME         TIMESTAMP(6)
  ,SUBMIT_FEEDBACK_STATUS VARCHAR2(50)
  ,SUBMIT_FEEDBACK_MSG  VARCHAR2(2000)
  ,DATA_DATE	DATE
  ,REMARK	VARCHAR2(200)
  ,CREATE_USER	VARCHAR2(20)
  ,CREATE_TIME	TIMESTAMP(6)
  ,UPDATE_USER	VARCHAR2(32)
  ,UPDATE_TIME	TIMESTAMP(6)
);
comment on table CHB_AGENT_PRD_SUB is '委托销售产品编码信息登记表';
comment on column CHB_AGENT_PRD_SUB.REGIST_SEQNO is '登记序列 ';
comment on column CHB_AGENT_PRD_SUB.AGENT_INSTITUTION_CODE is '代销机构代码 ';
comment on column CHB_AGENT_PRD_SUB.ISSUE_PARTY_ID is '发行机构代码 ';
comment on column CHB_AGENT_PRD_SUB.PROD_ID is '理财产品代码 ';
comment on column CHB_AGENT_PRD_SUB.PROD_NAME is '理财产品名称 ';
comment on column CHB_AGENT_PRD_SUB.PROD_REGIST_CODE is '产品登记编码 ';
comment on column CHB_AGENT_PRD_SUB.STATUS is '数据状态';
comment on column CHB_AGENT_PRD_SUB.SUMIT_SEQ is '报送流水号 ';
comment on column CHB_AGENT_PRD_SUB.CHB_SUBMIT_DEADLINE is '中债报送截止日 ';
comment on column CHB_AGENT_PRD_SUB.SUBMIT_DEADLINE is '行内报送截止日 ';
comment on column CHB_AGENT_PRD_SUB.SEND_STATUS is '报送状态 ';
comment on column CHB_AGENT_PRD_SUB.SUBMIT_TIME is '报送时间 ';
comment on column CHB_AGENT_PRD_SUB.SUBMIT_FEEDBACK_STATUS is '反馈状态 ';
comment on column CHB_AGENT_PRD_SUB.SUBMIT_FEEDBACK_MSG is '报送反馈消息 ';
comment on column CHB_AGENT_PRD_SUB.DATA_DATE is '数据日期 ';
comment on column CHB_AGENT_PRD_SUB.REMARK is '备注 ';
comment on column CHB_AGENT_PRD_SUB.CREATE_USER is '创建人 ';
comment on column CHB_AGENT_PRD_SUB.CREATE_TIME is '创建时间 ';
comment on column CHB_AGENT_PRD_SUB.UPDATE_USER is '更新人 ';
comment on column CHB_AGENT_PRD_SUB.UPDATE_TIME is '更新时间 ';


  --委托销售产品编码信息登记履历表创建
drop table CHB_AGENT_PRD_SUB_HIS;
create table CHB_AGENT_PRD_SUB_HIS(
   HIS_NO   CHAR(36) NOT NULL
  ,AGENT_INSTITUTION_CODE VARCHAR2(32) NOT NULL
  ,ISSUE_PARTY_ID	VARCHAR2(6) NOT NULL
  ,PROD_ID   VARCHAR2(150)
  ,PROD_NAME VARCHAR2(300)
  ,PROD_REGIST_CODE VARCHAR2(50)
  ,STATUS	VARCHAR2(2)
  ,SUMIT_SEQ VARCHAR2(20) NOT NULL
  ,CHB_SUBMIT_DEADLINE TIMESTAMP(6)
  ,SUBMIT_DEADLINE     TIMESTAMP(6)
  ,SEND_STATUS         VARCHAR2(50)
  ,SUBMIT_TIME         TIMESTAMP(6)
  ,SUBMIT_FEEDBACK_STATUS VARCHAR2(50)
  ,SUBMIT_FEEDBACK_MSG  VARCHAR2(2000)
  ,DATA_DATE	DATE
  ,REMARK	VARCHAR2(200)
  ,CREATE_USER	VARCHAR2(20)
  ,CREATE_TIME	TIMESTAMP(6)
  ,UPDATE_USER	VARCHAR2(32)
  ,UPDATE_TIME	TIMESTAMP(6)
);
comment on table CHB_AGENT_PRD_SUB_HIS is '委托销售产品编码信息登记履历表';
comment on column CHB_AGENT_PRD_SUB_HIS.HIS_NO is '履历编号 ';
comment on column CHB_AGENT_PRD_SUB_HIS.AGENT_INSTITUTION_CODE is '代销机构代码 ';
comment on column CHB_AGENT_PRD_SUB_HIS.ISSUE_PARTY_ID is '发行机构代码 ';
comment on column CHB_AGENT_PRD_SUB_HIS.PROD_ID is '理财产品代码 ';
comment on column CHB_AGENT_PRD_SUB_HIS.PROD_NAME is '理财产品名称 ';
comment on column CHB_AGENT_PRD_SUB_HIS.PROD_REGIST_CODE is '产品登记编码 ';
comment on column CHB_AGENT_PRD_SUB_HIS.STATUS is '数据状态';
comment on column CHB_AGENT_PRD_SUB_HIS.SUMIT_SEQ is '报送流水号 ';
comment on column CHB_AGENT_PRD_SUB_HIS.CHB_SUBMIT_DEADLINE is '中债报送截止日 ';
comment on column CHB_AGENT_PRD_SUB_HIS.SUBMIT_DEADLINE is '行内报送截止日 ';
comment on column CHB_AGENT_PRD_SUB_HIS.SEND_STATUS is '报送状态 ';
comment on column CHB_AGENT_PRD_SUB_HIS.SUBMIT_TIME is '报送时间 ';
comment on column CHB_AGENT_PRD_SUB_HIS.SUBMIT_FEEDBACK_STATUS is '反馈状态 ';
comment on column CHB_AGENT_PRD_SUB_HIS.SUBMIT_FEEDBACK_MSG is '报送反馈消息 ';
comment on column CHB_AGENT_PRD_SUB_HIS.DATA_DATE is '数据日期 ';
comment on column CHB_AGENT_PRD_SUB_HIS.REMARK is '备注 ';
comment on column CHB_AGENT_PRD_SUB_HIS.CREATE_USER is '创建人 ';
comment on column CHB_AGENT_PRD_SUB_HIS.CREATE_TIME is '创建时间 ';
comment on column CHB_AGENT_PRD_SUB_HIS.UPDATE_USER is '更新人 ';
comment on column CHB_AGENT_PRD_SUB_HIS.UPDATE_TIME is '更新时间 ';

  --菜单配置
DELETE FROM SYS_PAGE WHERE PAGE_ID in ('AgentTabManageRegist001','AgentInstitutionRegist001','AgentPrdInfoRegist001');
delete from SYS_MENU where MENU_ID = 'REPORT_001_003_019';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_001_003_019', 'REP', null, 'REPORT_001_003', 'AgentTabManageRegist001', 19, '02', '代理销售信息登记', 'system', null, TIMESTAMP '2021-06-30 20:43:03', 'system', TIMESTAMP '2021-06-30 20:43:03');
INSERT INTO SYS_PAGE VALUES ('AgentTabManageRegist001','REP','/report/submitmanagement/agenttabmanageregist/AgentTabManageRegist001','report/submitmanagement/agenttabmanageregist/AgentTabManageRegist001','REPORT_001_001_026',null,null,'02','01','代理销售信息登记','system',null,sysdate,'system',sysdate);
INSERT INTO SYS_PAGE VALUES ('AgentInstitutionRegist001','REP','/report/submitmanagement/agentinstitutionregist/AgentInstitutionRegist001','report/submitmanagement/agentinstitutionregist/AgentInstitutionRegist001',null,null,null,'02','01','代理销售机构信息登记','system',null,sysdate,'system',sysdate);
INSERT INTO SYS_PAGE VALUES ('AgentPrdInfoRegist001','REP','/report/submitmanagement/agentprdinforegist/AgentPrdInfoRegist001','report/submitmanagement/agentprdinforegist/AgentPrdInfoRegist001',null,null,null,'02','01','委托销售产品编码登记','system',null,sysdate,'system',sysdate);

