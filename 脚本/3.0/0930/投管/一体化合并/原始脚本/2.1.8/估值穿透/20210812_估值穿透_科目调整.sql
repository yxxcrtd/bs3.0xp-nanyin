
delete from rsk_valtbl_subject where sub_code in('102113','10211319');
delete from rsk_valtbl_subject where sub_code in('102106','102113');
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('102106', '01', '券商备付金', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('102113', '01', '期货备付金', 'system', '', sysdate, 'system', sysdate);

delete from rsk_valtbl_subject where sub_code in('110341','11034101','11034102');
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('110341', '01', '境外债券投资', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('11034101', '01', '境外债券投资成本', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('11034102', '01', '境外债券投资折溢价', 'system', '', sysdate, 'system', sysdate);
delete from rsk_valtbl_subject where sub_code in('12040641');
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('12040641', '01', '应收境外债券利息', 'system', '', sysdate, 'system', sysdate);


delete from rsk_valtbl_subject where sub_code in('2701','270101','27010101','270102','27010201');
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('2701', '01', '融入股票', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('270101', '01', '上交所融入股票', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('27010101', '01', '融入证券_股票', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('270102', '01', '深交所融入股票', 'system', '', sysdate, 'system', sysdate);
INSERT INTO rsk_valtbl_subject (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('27010201', '01', '融入证券_股票', 'system', '', sysdate, 'system', sysdate);
