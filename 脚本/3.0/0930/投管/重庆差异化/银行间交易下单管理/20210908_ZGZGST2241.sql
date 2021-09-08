delete
from SYS_PAGE_DICTLIST
where PAGE_ID = 'CfetsRepoDealQuotedPr001'
  and DICT_ID = 'MATCH_STATUS';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME,
                                            DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                                            UPDATE_TIME, INSTITUTION_CODE)
VALUES ('CfetsRepoDealQuotedPr001', '999999', 'MATCH_STATUS', '01', null, null, 'system', null,
        systimestamp, 'system',
        systimestamp, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME,
                                            DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                                            UPDATE_TIME, INSTITUTION_CODE)
VALUES ('CfetsRepoDealQuotedPr001', '999999', 'MATCH_STATUS', '02', null, null, 'system', null,
        systimestamp, 'system',
        systimestamp, null);