
--手工凭证
DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ValManualVoucher001','ValManualVoucher002','ValManualVoucher003');
insert into SYS_PAGE values ('ValManualVoucher001','VAL','/valuation/accounadjustmanage/manualvoucher/ValManualVoucher001','valuation/accounadjustmanage/manualvoucher/ValManualVoucher001','VALFINANCEMANAGE_001_001_001','','','02','02','手工凭证','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValManualVoucher002','VAL','/valuation/accounadjustmanage/manualvoucher/ValManualVoucher002','valuation/accounadjustmanage/manualvoucher/ValManualVoucher002','VALFINANCEMANAGE_001_001_001','','','02','01','查看手工凭证','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValManualVoucher003','VAL','/valuation/accounadjustmanage/manualvoucher/ValManualVoucher003','valuation/accounadjustmanage/manualvoucher/ValManualVoucher003','VALFINANCEMANAGE_001_001_001','','','02','01','新建手工凭证','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
--手工凭证模板
DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ValManualVoucherTemplete001','ValManualVoucherTemplete002','ValManualVoucherTemplete003');
insert into SYS_PAGE values ('ValManualVoucherTemplete001','VAL','/valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete001','valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete001','VALFINANCEMANAGE_001_001_002','','','02','02','手工凭证模板','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValManualVoucherTemplete002','VAL','/valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete002','valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete002','VALFINANCEMANAGE_001_001_002','','','02','02','查看手工凭证模板','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValManualVoucherTemplete003','VAL','/valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003','valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003','VALFINANCEMANAGE_001_001_002','','','02','02','新建手工凭证模板','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));

--记账凭证查询
DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ValVoucherQuery001','ValVoucherQuery002','ValVoucherQuery003');
insert into SYS_PAGE values ('ValVoucherQuery001','VAL','/valuation/accounadjustmanage/voucherquery/ValVoucherQuery001','valuation/accounadjustmanage/voucherquery/ValVoucherQuery001','VALFINANCEMANAGE_001_001_003','','','02','02','凭证查询','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValVoucherQuery002','VAL','/valuation/accounadjustmanage/voucherquery/ValVoucherQuery002','valuation/accounadjustmanage/voucherquery/ValVoucherQuery002','VALFINANCEMANAGE_001_001_003','','','02','02','查看凭证详情','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValVoucherQuery003','VAL','/valuation/accounadjustmanage/voucherquery/ValVoucherQuery003','valuation/accounadjustmanage/voucherquery/ValVoucherQuery003','VALFINANCEMANAGE_001_001_003','','','02','02','修改凭证备注','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));

--科目流水查询
DELETE FROM SYS_PAGE WHERE PAGE_ID='ValSubjectWaterQuery001';
insert into SYS_PAGE values ('ValSubjectWaterQuery001','VAL','/valuation/financemanage/accountbook/valsubjectwaterquery/ValSubjectWaterQuery001','valuation/financemanage/accountbook/valsubjectwaterquery/ValSubjectWaterQuery001','VALFINANCEMANAGE_001_001_004','','','02','02','科目流水查询','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));

--科目余额查询
DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ValSubjectBalanceQuery001','ValSubjectBalanceQuery002','ValSubjectBalanceQuery003');
insert into SYS_PAGE values ('ValSubjectBalanceQuery001','VAL','/valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery001','valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery001','VALFINANCEMANAGE_001_001_005','','','02','02','余额表查询','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValSubjectBalanceQuery002','VAL','/valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery002','valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery002','VALFINANCEMANAGE_001_001_005','','','02','02','科目余额查询查看账套号','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('ValSubjectBalanceQuery003','VAL','/valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery003','valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery003','VALFINANCEMANAGE_001_001_005','','','02','02','科目余额查询合并展示查看','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));

