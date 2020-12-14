--账户维护费科目性质修改脚本
UPDATE bok_subject SET BASE_SUBJECT_NATURE='QTZC_SQZHWHF' where subject_no in ('66050104');
UPDATE bok_subject SET BASE_SUBJECT_NATURE='QTZC_ZZZHWHF' where subject_no in ('66050103');
UPDATE bok_subject SET BASE_SUBJECT_NATURE='QTZC_ZHWHF' where subject_no in ('66050101');
