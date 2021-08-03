delete
from SYS_DATADICT_LIST
where DICT_CODE = 'REMINDER_TYPE_SMALL'
  and ITEM_CODE = 'R5S3';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER,
                               CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('REMINDER_TYPE_SMALL', 'R5S3', '固定值参数更新提醒', 41, '0', 'system', null,
        systimestamp, 'system',
        systimestamp, null);

delete
from SYS_DATADICT_LIST_LINK
where DICT_CODE = 'REMINDER_TYPE_SMALL'
  and ITEM_CODE = 'R5S3';
INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER,
                                    CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REMINDER_TYPE_SMALL', 'R5S3', 'REMINDER_TYPE_BIG', 'R5',
        'system', null, systimestamp, 'system',
        systimestamp);

delete
from SYS_ACTIVEREMINDER_SET
where REM_TYPE = 'R5S3';
INSERT INTO SYS_ACTIVEREMINDER_SET (REM_TYPE, DAYS, URL, LABLE, DEALF, CLOSF, REMARK, MENU_ID)
VALUES ('R5S3', 0, '/invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam001/init',
        'RskSupervisionIndicatorParam001', 'Y', 'Y', null,
        'RISK_MANAGEMENT_001_004_007');


/*债券一级分销缴款提醒*/
delete from SYS_DATADICT_LIST where DICT_CODE = 'REMINDER_TYPE_BIG' and ITEM_CODE = 'R10';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER,
                                            CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('REMINDER_TYPE_BIG', 'R10', '分销缴款提醒', 10, '0', 'system', null,
        systimestamp, 'system',
        systimestamp, null);

delete
from SYS_DATADICT_LIST
where DICT_CODE = 'REMINDER_TYPE_SMALL'
  and ITEM_CODE = 'R10S1';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER,
                               CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('REMINDER_TYPE_SMALL', 'R10S1', '分销缴款提醒', 42, '0', 'system', null,
        systimestamp, 'system',
        systimestamp, null);

delete
from SYS_DATADICT_LIST_LINK
where DICT_CODE = 'REMINDER_TYPE_SMALL'
  and ITEM_CODE = 'R10S1';
INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER,
                                    CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REMINDER_TYPE_SMALL', 'R10S1', 'REMINDER_TYPE_BIG', 'R10',
        'system', null, systimestamp, 'system',
        systimestamp);

delete
from SYS_ACTIVEREMINDER_SET
where REM_TYPE = 'R10S1';
INSERT INTO SYS_ACTIVEREMINDER_SET (REM_TYPE, DAYS, URL, LABLE, DEALF, CLOSF, REMARK, MENU_ID)
VALUES ('R10S1', 0, '/invest/entrust/etrbondbiddingresult/EtrBondBiddingResultIb001/init',
        'EtrBondBiddingResultIb001', 'Y', 'Y', null,
        'DEAL_MANAGEMENT_001_002_002');

delete
from SYS_ACTIVEREMINDER_SET
where REM_TYPE = 'R10S2';
INSERT INTO SYS_ACTIVEREMINDER_SET (REM_TYPE, DAYS, URL, LABLE, DEALF, CLOSF, REMARK, MENU_ID)
VALUES ('R10S2', 0, '/invest/entrust/etrbondbiddingresult/EtrBondBiddingResultEx001/init',
        'EtrBondBiddingResultEx001', 'Y', 'Y', null,
        'DEAL_MANAGEMENT_001_003_002');