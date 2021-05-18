--批处理匹配关系表：结算指令增加【8017、8018】
DELETE FROM BOK_MATCH_RLAT WHERE MATCH_TYPE='03' AND SOURCE='7' AND TARGET IN ('8017','8018');
insert into BOK_MATCH_RLAT values ('03','QY','7','8017','system','',to_date('2020/12/23', 'yyyy/mm/dd'), 'system',to_date('2020/12/23', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','7','8018','system','',to_date('2020/12/23', 'yyyy/mm/dd'), 'system',to_date('2020/12/23', 'yyyy/mm/dd'));