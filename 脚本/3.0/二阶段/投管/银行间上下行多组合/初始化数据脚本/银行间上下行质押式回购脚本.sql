
DELETE FROM SYS_PAGE WHERE PAGE_ID = 'CfetsRepoDealQuotedPrice003';
insert into SYS_PAGE values ('CfetsRepoDealQuotedPrice003','CFETS','/invest/bankuplink/repo/CfetsRepoDealQuotedPrice003','invest/bankuplink/repo/CfetsRepoDealQuotedPrice003','','','','02','02','CFETS质押式回购报价','system','',sysdate, 'system',sysdate);


delete from SYS_DATADICT_LIST where DICT_CODE = 'DISTRIBUTION' and ITEM_CODE = '143';
insert into SYS_DATADICT_LIST values ('DISTRIBUTION','143','比例分配','143','0','system','',sysdate, 'system',sysdate,'');

delete from SYS_PAGE_DICTLIST where page_id = 'CfetsRepoDealQuotedPrice003';
insert into SYS_PAGE_DICTLIST values ('CfetsRepoDealQuotedPrice003','999999','DISTRIBUTION','139','平均分配','','admin','',sysdate, 'admin',sysdate,'');
insert into SYS_PAGE_DICTLIST values ('CfetsRepoDealQuotedPrice003','999999','DISTRIBUTION','143','比例分配','','admin','',sysdate, 'admin',sysdate,'');


delete from SYS_WFL_BUSINESS_MODULE where page_id = 'CfetsRepoDealQuotedPrice003';
insert into SYS_WFL_BUSINESS_MODULE values ('CfetsRepoDealQuotedPrice003','CfetsRepoDealQuotedPrice003_btn_done','oneApproval','system','0001',sysdate, 'system',sysdate,'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE values ('CfetsRepoDealQuotedPrice003','CfetsRepoDealQuotedPrice003_btn_repoRevoke','oneApproval','system','0001',sysdate, 'system',sysdate,'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE values ('CfetsRepoDealQuotedPrice003','CfetsRepoDealQuotedPrice003_btn_save','oneApproval','system','0001',sysdate, 'system',sysdate,'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE values ('CfetsRepoDealQuotedPrice003','CfetsRepoDealQuotedPrice003_btn_submit','oneApproval','system','0001',sysdate, 'system',sysdate,'ALL','02');


-------------------对手方质押券组件---------------------------------------
delete from SYS_PAGE where page_id = 'CfetsPledgedBond';
insert into SYS_PAGE values ('CfetsPledgedBond','CFETS','/invest/bankuplink/common/CfetsPledgedBond','invest/bankuplink/common/CfetsPledgedBond','','','','02','02','对手方质押券信息','system','',sysdate, 'system',sysdate);

