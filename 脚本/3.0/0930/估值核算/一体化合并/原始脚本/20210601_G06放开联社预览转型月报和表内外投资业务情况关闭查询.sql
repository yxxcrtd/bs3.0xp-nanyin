delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SvrTransMonthReport001' and ELEMENT_ID = 'ChbTransMonthReport001_btn_search';
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'InvestmentsInfo001' and ELEMENT_ID = 'InvestmentsInfo001_btn_search';
update sys_page_element set admin_flag='COM'  where PAGE_ID = 'SvrG06MonthReport001' and ELEMENT_ID = 'ChbG06MonthReport001_TBLROW_batch';

