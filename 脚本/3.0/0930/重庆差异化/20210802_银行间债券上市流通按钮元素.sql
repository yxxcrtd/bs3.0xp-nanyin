DELETE FROM sys_page_element where page_id = 'TrdBondMarketTradeIb001';

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdBondMarketTradeIb001', 'TrdBondMarketTrade001_btn_add', '01', '01', '', '新建', 'system', '', date'2021-08-02', 'system', date'2021-08-02', 'C');

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdBondMarketTradeIb001', 'TrdBondMarketTrade001_btn_reset', '01', '02', '', '重置', 'system', '', date'2021-08-02', 'system', date'2021-08-02', 'C');

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdBondMarketTradeIb001', 'TrdBondMarketTrade001_btn_revoke', '01', '01', '', '撤销', 'system', '', date'2021-08-02', 'system', date'2021-08-02', 'C');

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdBondMarketTradeIb001', 'TrdBondMarketTrade001_btn_search', '01', '01', '', '查询', 'system', '', date'2021-08-02', 'system', date'2021-08-02', 'COM');

