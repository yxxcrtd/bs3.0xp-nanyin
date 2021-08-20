delete from sys_datadict where DICT_CODE='SUBJECT_CCY_TYPE';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)　values ('SUBJECT_CCY_TYPE', 'RSK', '估值币种映射', 'SUBJECT_CCY_TYPE', '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='SUBJECT_CCY_TYPE';
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SUBJECT_CCY_TYPE', 'AUD', '06', 6, '0', 'system', '', sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SUBJECT_CCY_TYPE', 'CAD', '07', 7, '0', 'system', '', sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SUBJECT_CCY_TYPE', 'CNY', '01', 1, '0', 'system', '', sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SUBJECT_CCY_TYPE', 'EUR', '04', 4, '0', 'system', '', sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SUBJECT_CCY_TYPE', 'GBP', '05', 5, '0', 'system', '', sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SUBJECT_CCY_TYPE', 'HKD', '03', 3, '0', 'system', '', sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SUBJECT_CCY_TYPE', 'JPY', '09', 9, '0', 'system', '', sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SUBJECT_CCY_TYPE', 'SGD', '08', 8, '0', 'system', '', sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SUBJECT_CCY_TYPE', 'USD', '02', 2, '0', 'system', '', sysdate, 'system', sysdate, null);


delete from rsk_valtbl_subject where sub_code in('10020101','10020102','10020103','10020104','10020105','10020106','10020107','10020108','10020109');
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('10020101', '01', '人民币', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('10020102', '01', '美元', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('10020103', '01', '港币', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('10020104', '01', '欧元', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('10020105', '01', '英镑', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('10020106', '01', '澳元', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('10020107', '01', '加拿大元', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('10020108', '01', '新加坡元', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('10020109', '01', '日元', 'system', '', sysdate, 'system', sysdate);


delete from rsk_valtbl_subject where sub_code in('12040101','12040102','12040103','12040104','12040105','12040106','12040107','12040108','12040109');
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('12040101', '01', '人民币活期存款利息', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('12040102', '01', '美元活期存款利息', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('12040103', '01', '港币活期存款利息', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('12040104', '01', '欧元活期存款利息', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('12040105', '01', '英镑活期存款利息', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('12040106', '01', '澳元活期存款利息', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('12040107', '01', '加拿大元活期存款利息', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('12040108', '01', '新加坡元活期存款利息', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('12040109', '01', '日元活期存款利息', 'system', '', sysdate, 'system', sysdate);
