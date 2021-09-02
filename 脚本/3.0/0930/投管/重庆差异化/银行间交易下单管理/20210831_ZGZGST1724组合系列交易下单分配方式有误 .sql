delete
from SYS_PAGE_DICTLIST
where SYS_PAGE_DICTLIST.PAGE_ID = 'CfetsRepoDealQuotedPr003'
  and DICT_ID = 'DISTRIBUTION';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME,
                               DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME,
                               INSTITUTION_CODE)
VALUES ('CfetsRepoDealQuotedPr003', '999999', 'DISTRIBUTION', '139', '平均分配', null, 'admin', null, systimestamp, 'admin',
        systimestamp, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME,
                               DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME,
                               INSTITUTION_CODE)
VALUES ('CfetsRepoDealQuotedPr003', '999999', 'DISTRIBUTION', '143', '比例分配', null, 'admin', null, systimestamp, 'admin',
        systimestamp, null);