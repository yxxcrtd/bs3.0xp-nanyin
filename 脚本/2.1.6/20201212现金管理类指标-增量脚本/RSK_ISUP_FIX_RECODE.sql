delete from RSK_ISUP_FIX_RECODE t where t.para_code = 'FV202009030001'and to_char(t.content) = '366';
delete from RSK_ISUP_FIX_RECODE t where t.para_code = 'FV202009030001'and to_char(t.content) = '365';
delete from RSK_ISUP_FIX_RECODE t where t.para_code = 'FV202009030001'and to_char(t.content) = '365';
delete from RSK_ISUP_FIX_RECODE t where t.para_code = 'FV202009030001'and to_char(t.content) = '365';
delete from RSK_ISUP_FIX_RECODE t where t.para_code = 'FV201907200001'and to_char(t.content) = '5000000';
delete from RSK_ISUP_FIX_RECODE t where t.para_code = 'FV201907200001'and to_char(t.content) = '380000000000';
delete from RSK_ISUP_FIX_RECODE t where t.para_code = 'FV201907200001'and to_char(t.content) = '450000000000';



insert into RSK_ISUP_FIX_RECODE (PARA_CODE, CONTENT, VALID_DATE, NOTIFY_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FV202009030001', '366', to_date('03-09-2020', 'dd-mm-yyyy'), null, 'zhaoyongliang', '001', '17-9月 -20 05.54.14.948000 下午', 'zhaoyongliang', '17-9月 -20 05.54.15.134000 下午', 'FIX');

insert into RSK_ISUP_FIX_RECODE (PARA_CODE, CONTENT, VALID_DATE, NOTIFY_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FV202009030001', '365', to_date('01-01-2021', 'dd-mm-yyyy'), null, 'zhaoyongliang', '001', '17-9月 -20 05.54.14.948000 下午', 'zhaoyongliang', '17-9月 -20 05.54.15.136000 下午', 'FIX');

insert into RSK_ISUP_FIX_RECODE (PARA_CODE, CONTENT, VALID_DATE, NOTIFY_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FV202009030001', '365', to_date('01-01-2022', 'dd-mm-yyyy'), null, 'zhaoyongliang', '001', '17-9月 -20 05.54.14.948000 下午', 'zhaoyongliang', '17-9月 -20 05.54.15.137000 下午', 'FIX');

insert into RSK_ISUP_FIX_RECODE (PARA_CODE, CONTENT, VALID_DATE, NOTIFY_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FV202009030001', '365', to_date('01-01-2023', 'dd-mm-yyyy'), null, 'zhaoyongliang', '001', '17-9月 -20 05.54.14.948000 下午', 'zhaoyongliang', '17-9月 -20 05.54.15.138000 下午', 'FIX');

insert into RSK_ISUP_FIX_RECODE (PARA_CODE, CONTENT, VALID_DATE, NOTIFY_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FV201907200001', '5000000', to_date('20-08-2020', 'dd-mm-yyyy'), null, 'zhaoyongliang', '001', '17-9月 -20 05.55.23.819000 下午', 'zhaoyongliang', '17-9月 -20 05.55.24.009000 下午', 'FIX');

insert into RSK_ISUP_FIX_RECODE (PARA_CODE, CONTENT, VALID_DATE, NOTIFY_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FV201907200001', '380000000000', to_date('21-08-2020', 'dd-mm-yyyy'), null, 'zhaoyongliang', '001', '17-9月 -20 05.55.23.819000 下午', 'zhaoyongliang', '17-9月 -20 05.55.24.010000 下午', 'FIX');

insert into RSK_ISUP_FIX_RECODE (PARA_CODE, CONTENT, VALID_DATE, NOTIFY_DATE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FV201907200001', '450000000000', to_date('21-09-2020', 'dd-mm-yyyy'), null, 'zhaoyongliang', '001', '17-9月 -20 05.55.23.819000 下午', 'zhaoyongliang', '17-9月 -20 05.55.24.010000 下午', 'FIX');

