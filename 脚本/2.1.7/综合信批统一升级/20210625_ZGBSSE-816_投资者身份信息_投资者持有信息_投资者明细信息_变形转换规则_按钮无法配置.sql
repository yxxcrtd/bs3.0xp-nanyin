
--投资者身份信息：EquityInfo001
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'EquityInfo001' AND ELEMENT_ID='EquityInfo001_btn_delete';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'EquityInfo001' AND ELEMENT_ID='EquityInfo001_btn_import';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'EquityInfo001' AND ELEMENT_ID='EquityInfo001_btn_export';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'EquityInfo001' AND ELEMENT_ID='EquityInfo001_btn_search';
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='EquityInfo001' and ELEMENT_ID='EquityInfo001_btn_add' and REMARK='新建';
delete from SYS_PAGE_ELEMENT where PAGE_ID='EquityInfo001' and ELEMENT_ID='EquityInfo001_btn_chbImport' and REMARK='导入代销数据';
delete from SYS_PAGE_ELEMENT where PAGE_ID='EquityInfo001' and ELEMENT_ID='EquityInfo001_btn_reset' and REMARK='重置';
--INSERT
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('EquityInfo001','EquityInfo001_btn_add','01','01',null,'新建','system',null,sysdate,'system',sysdate,'C');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('EquityInfo001','EquityInfo001_btn_chbImport','01','01',null,'导入代销数据','system',null,sysdate,'system',sysdate,'C');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('EquityInfo001','EquityInfo001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');

--投资者持有信息：InvestorsHoldInfo001
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfo001' AND ELEMENT_ID='InvestorsHoldInfo001_btn_add';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfo001' AND ELEMENT_ID='InvestorsHoldInfo001_btn_delete';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfo001' AND ELEMENT_ID='InvestorsHoldInfo001_btn_import';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfo001' AND ELEMENT_ID='InvestorsHoldInfo001_btn_export';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfo001' AND ELEMENT_ID='InvestorsHoldInfo001_btn_search';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfo001' AND ELEMENT_ID='InvestorsHoldInfo001_btn_reset';
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='InvestorsHoldInfo001' and ELEMENT_ID='InvestorsHoldInfo001_btn_chbImport' and REMARK='导入代销数据';
--INSERT
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('InvestorsHoldInfo001','InvestorsHoldInfo001_btn_chbImport','01','01',null,'导入代销数据','system',null,sysdate,'system',sysdate,'C');

--投资者明细信息：SvrEquityBusinessDetails001
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEquityBusinessDetails001' AND ELEMENT_ID='SvrEquityBusinessDetails001_btn_delete';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEquityBusinessDetails001' AND ELEMENT_ID='SvrEquityBusinessDetails001_btn_import';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEquityBusinessDetails001' AND ELEMENT_ID='SvrEquityBusinessDetails001_btn_export';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEquityBusinessDetails001' AND ELEMENT_ID='SvrEquityBusinessDetails001_btn_search';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEquityBusinessDetails001' AND ELEMENT_ID='SvrEquityBusinessDetails001_btn_reset';
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrEquityBusinessDetails001' and ELEMENT_ID='SvrEquityBusinessDetails001_btn_chbImport' and REMARK='导入代销数据';
--INSERT
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrEquityBusinessDetails001','SvrEquityBusinessDetails001_btn_chbImport','01','01',null,'导入代销数据','system',null,sysdate,'system',sysdate,'C');

--变形转换规则：SvrTransformRule001
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrTransformRule001' and ELEMENT_ID='TransformRule001_btn_add' and REMARK='新建';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrTransformRule001' and ELEMENT_ID='TransformRule001_btn_delete' and REMARK='删除';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrTransformRule001' and ELEMENT_ID='SvrTransformRule001_btn_export' and REMARK='导出';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrTransformRule001' and ELEMENT_ID='SvrTransformRule001_btn_search' and REMARK='查询';
--INSERT
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrTransformRule001','TransformRule001_btn_add','01','01',null,'新建','system',null,sysdate,'system',sysdate,'C');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrTransformRule001','TransformRule001_btn_delete','01','01',null,'删除','system',null,sysdate,'system',sysdate,'C');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrTransformRule001','SvrTransformRule001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate,'COM');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrTransformRule001','SvrTransformRule001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');



