--草稿箱模式非对象画面表
delete from SYS_TRASH_NOT_TARGET_PAGE where page_id = 'InvestorsQualityCheck002'; 
insert into SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO, PAGE_ID)
values (sys_guid(), 'InvestorsQualityCheck002');
