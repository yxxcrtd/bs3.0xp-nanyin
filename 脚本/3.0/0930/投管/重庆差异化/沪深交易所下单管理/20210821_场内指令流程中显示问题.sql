delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'EtrExchangeTrade001' and ELEMENT_ID in ('EtrExchangeTrade001_btn_save','EtrExchangeTrade001_btn_submit');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME,
                                                  UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('EtrExchangeTrade001', 'EtrExchangeTrade001_btn_save', 'oneApproval', 'system', '0001',
        TO_TIMESTAMP('2021-05-12 14:24:03.000000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'system',
        TO_TIMESTAMP('2021-04-21 00:00:00.000000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME,
                                                  UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('EtrExchangeTrade001', 'EtrExchangeTrade001_btn_submit', 'oneApproval', 'system', '0001',
        TO_TIMESTAMP('2021-05-12 14:24:03.000000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'system',
        TO_TIMESTAMP('2021-04-21 00:00:00.000000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'ALL', '02');