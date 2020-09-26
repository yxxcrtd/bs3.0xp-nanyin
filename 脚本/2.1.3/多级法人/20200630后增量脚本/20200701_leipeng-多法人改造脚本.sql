/***同一数据维护删除无用字段*/
delete FROM chb_infomgr_result_config WHERE MGRTABLE_ID = 'CHB_ASSET_INFO' AND COLUMN_ID = 'ISSUE_PARTY_ID';
delete FROM chb_infomgr_result_config WHERE MGRTABLE_ID = 'CHB_PRD_BASEINFO' AND COLUMN_ID = 'REG_CODE';



/***【统一数据维护】监管客户组别区域报送数据删除报错**/
DELETE FROM CHB_INFOMGR_CONFIG WHERE BASETABLEID = 'SALE_CUST_SVRREPORT';
insert into CHB_INFOMGR_CONFIG (BASEINFORMATIONID, BASETABLEID, BASECOLUMNID, BASECOLUMNNAME, BASECOLUMNTYPE, BUSINESSPKFLAG, DICTCODE)
values ('06552a39-70ac-43e4-998c-f941d04983de', 'SALE_CUST_SVRREPORT', 'SENDDATE', '下发日期', 'D', 'Y', null);

insert into CHB_INFOMGR_CONFIG (BASEINFORMATIONID, BASETABLEID, BASECOLUMNID, BASECOLUMNNAME, BASECOLUMNTYPE, BUSINESSPKFLAG, DICTCODE)
values ('9d21cd88-9e4b-44b9-b70d-fb86b757b99a', 'SALE_CUST_SVRREPORT', 'FINPROD_ID', '产品代码', 'T', 'Y', null);


/**新增资产类型公用状态表**/
DROP TABLE CHB_ASSET_SUB_STATUS;
CREATE TABLE CHB_ASSET_SUB_STATUS(
    REGIST_SEQNO 			VARCHAR2(50) not null,
    INSTITUTION_CODE 		VARCHAR2(50) not null,
    SEND_STATUS 			VARCHAR2(50) ,
	SUBMIT_TIME 			DATE ,
    SUBMIT_FEEDBACK_STATUS VARCHAR2(10) ,
    ASSET_TYPE 				VARCHAR2(10) NOT NULL,
    create_user            VARCHAR2(20) not null,
    create_dept            VARCHAR2(32),
    create_time            TIMESTAMP(6) not null,
    update_user            VARCHAR2(20) not null,
    update_time            TIMESTAMP(6) not null   
); 

comment on table CHB_ASSET_SUB_STATUS
  is '资产类型公用状态表';
comment on column CHB_ASSET_SUB_STATUS.REGIST_SEQNO
  is '业务表主键';
comment on column CHB_ASSET_SUB_STATUS.INSTITUTION_CODE
  is '机构编码';
comment on column CHB_ASSET_SUB_STATUS.SUBMIT_TIME
  is '报送反馈时间';  
comment on column CHB_ASSET_SUB_STATUS.send_status
  is '报送状态 01 未报送/02 手工处理/03 接口报送';
comment on column CHB_ASSET_SUB_STATUS.submit_feedback_status
  is '报送反馈状态';
comment on column CHB_ASSET_SUB_STATUS.ASSET_TYPE
  is '资产归属 : 01-资产基本信息 , 02-资产估值信息';