drop table STL_SEC_RECON_PARA;
create table STL_SEC_RECON_PARA
(
    SEQ_NO      varchar(50)   not null
        primary key,
    CUSTOMER_ID varchar(50)   null,
    CONFIG_DATE DATE          null,
    PARA_CODE   varchar(50)   null,
    PARA_VALUE  varchar(1000) null,
    CREATE_USER varchar(20)   null,
    CREATE_DEPT varchar(32)   null,
    CREATE_TIME DATE          null,
    UPDATE_USER varchar(32)   null,
    UPDATE_TIME DATE          null
);
comment on table STL_SEC_RECON_PARA is '券商对账参数';
comment on column STL_SEC_RECON_PARA.SEQ_NO is '序列号';
comment on column STL_SEC_RECON_PARA.CUSTOMER_ID is '券商代码';
comment on column STL_SEC_RECON_PARA.CONFIG_DATE is '配置日期';
comment on column STL_SEC_RECON_PARA.PARA_CODE is '参数代码';
comment on column STL_SEC_RECON_PARA.PARA_VALUE is '参数值';
comment on column STL_SEC_RECON_PARA.CREATE_USER is '创建人';
comment on column STL_SEC_RECON_PARA.CREATE_DEPT is '创建部门';
comment on column STL_SEC_RECON_PARA.CREATE_TIME is '创建时间';
comment on column STL_SEC_RECON_PARA.UPDATE_USER is '更新人';
comment on column STL_SEC_RECON_PARA.UPDATE_TIME is '更新时间';

delete
from SYS_DATADICT
where DICT_CODE = 'RECON_DEME';
delete
from SYS_DATADICT
where DICT_CODE = 'RECON_PARA_CODE';
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT,
                          CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RECON_DEME', 'STL', '对账维度', null, '02', 'system', null, systimestamp, 'system',
        systimestamp);
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT,
                          CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RECON_PARA_CODE', 'STL', '参数代码', null, '02', 'system', null, systimestamp, 'system',
        systimestamp);

delete
from SYS_DATADICT_LIST
where DICT_CODE in ('RECON_PARA_CODE', 'RECON_DEME');
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER,
                               CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('RECON_PARA_CODE', '01', '误差值', 1, '0', 'system', null, systimestamp, 'system', systimestamp,
        null);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER,
                               CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('RECON_PARA_CODE', '02', '对账维度', 2, '0', 'system', null, systimestamp, 'system', systimestamp,
        null);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER,
                               CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('RECON_DEME', '01', '证券汇总', 1, '0', 'system', null, systimestamp, 'system', systimestamp,
        null);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER,
                               CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('RECON_DEME', '02', '委托汇总', 2, '0', 'system', null, systimestamp, 'system', systimestamp,
        null);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER,
                               CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('RECON_DEME', '03', '成交明细', 3, '0', 'system', null, systimestamp, 'system', systimestamp,
        null);

delete
from sys_datadict_list
where DICT_CODE = 'COMMON_BUSINESS_TYPE'
  and ITEM_CODE = 'STL06';
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER,
                               CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'STL06', '券商对账参数设置', 806, '0', 'system', null, systimestamp, 'system',
        systimestamp, null);

delete
from sys_page_dictlist
where DICT_ID = 'COMMON_BUSINESS_TYPE'
  and DICT_ITEM_VAL = 'STL06';
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME,
                               DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                               UPDATE_TIME, INSTITUTION_CODE)
VALUES ('WorkflowMonitor001', '999999', 'COMMON_BUSINESS_TYPE', 'STL06', '券商对账参数设置新建', null, 'system', null,
        systimestamp, 'system', systimestamp, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME,
                               DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                               UPDATE_TIME, INSTITUTION_CODE)
VALUES ('MainPage', '999999', 'COMMON_BUSINESS_TYPE', 'STL06', '券商对账参数设置新建', null, 'system', null,
        systimestamp, 'system', systimestamp, null);


delete
from SYS_MENU
where MENU_ID = 'QS_MANAGEMENT_001_008';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK,
                      CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('QS_MANAGEMENT_001_008', 'STL', null, 'QS_MANAGEMENT_001', null, 8, '02', '日终数据核对', 'system', null,
        systimestamp, 'system',
        systimestamp);
delete
from SYS_MENU
where MENU_ID = 'QS_MANAGEMENT_001_008_005';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK,
                      CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('QS_MANAGEMENT_001_008_005', 'STL', null, 'QS_MANAGEMENT_001_008', 'StlSecReconPara001', 5, '02', '券商对账参数设置',
        'system', null, systimestamp, 'system',
        systimestamp);

delete
from SYS_PAGE
where PAGE_ID in ('StlSecReconPara001', 'StlSecReconPara002');
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT,
                      IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                      UPDATE_TIME)
VALUES ('StlSecReconPara001', 'STL', '/invest/basicdata/stlsecreconpara/StlSecReconPara001',
        'invest/basicdata/stlsecreconpara/StlSecReconPara001', 'QS_MANAGEMENT_001_008_005', null, null, '02', '01',
        '券商对账参数设置',
        'system', null, systimestamp, 'system',
        systimestamp);

INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT,
                      IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                      UPDATE_TIME)
VALUES ('StlSecReconPara002', 'STL', '/invest/basicdata/stlsecreconpara/StlSecReconPara002',
        'invest/basicdata/stlsecreconpara/StlSecReconPara002', null, null, null, '02', '01', '券商对账参数新建',
        'system', null, systimestamp, 'system',
        systimestamp);


delete
from sys_wfl_business_module
where PAGE_ID = 'StlSecReconPara002';
INSERT INTO sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME,
                                     UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('StlSecReconPara002', 'StlSecReconPara001_btn_delete', 'oneApproval', 'system', null, systimestamp,
        'system', systimestamp, 'ALL', '02');
INSERT INTO sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME,
                                     UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('StlSecReconPara002', 'StlSecReconPara002_btn_delete', 'oneApproval', 'system', null, systimestamp,
        'system', systimestamp, 'ALL', '02');
INSERT INTO sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME,
                                     UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('StlSecReconPara002', 'StlSecReconPara002_btn_save', 'oneApproval', 'system', null, systimestamp,
        'system', systimestamp, 'ALL', '02');
INSERT INTO sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME,
                                     UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('StlSecReconPara002', 'StlSecReconPara002_btn_submit', 'oneApproval', 'system', null, systimestamp,
        'system', systimestamp, 'ALL', '02');