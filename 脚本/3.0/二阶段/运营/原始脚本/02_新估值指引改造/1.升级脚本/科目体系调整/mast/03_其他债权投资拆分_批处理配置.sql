--批处理匹配关系表
---计划类预处理交易
DELETE FROM BOK_MATCH_RLAT WHERE SOURCE='131' AND TARGET IN ('3503','3504','3604','3605','3703','3704');
insert into BOK_MATCH_RLAT values ('03','QY','131','3503','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','131','3504','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','131','3604','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','131','3605','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','131','3703','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','131','3704','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));

---计划类资产交易
DELETE FROM BOK_MATCH_RLAT WHERE SOURCE='4' AND TARGET IN ('3501','3505','3507','3601','3608','3701','3705','2752','2753','2754','2755','2756','2757');
insert into BOK_MATCH_RLAT values ('03','QY','4','3501','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','3505','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','3507','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','3601','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','3608','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','3701','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','3705','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','2752','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','2753','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','2754','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','2755','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','2756','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','4','2757','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));

DELETE FROM BOK_MATCH_RLAT WHERE SOURCE='4' AND TARGET ='3606';
insert into BOK_MATCH_RLAT values ('03','QY','4','3606','system','',to_date('2020/12/24', 'yyyy/mm/dd'), 'system',to_date('2020/12/24', 'yyyy/mm/dd'));

---计划类资产公允价值变动
DELETE FROM BOK_MATCH_RLAT WHERE SOURCE='527' AND TARGET IN ('3603','3706');
insert into BOK_MATCH_RLAT values ('03','QY','527','3603','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','527','3706','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));

---计划类资产计提
DELETE FROM BOK_MATCH_RLAT WHERE SOURCE='24' AND TARGET IN ('3502','3602','3702');
insert into BOK_MATCH_RLAT values ('03','QY','24','3502','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','24','3602','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','24','3702','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));

---资产减值计提
DELETE FROM BOK_MATCH_RLAT WHERE SOURCE='203' AND TARGET IN ('3506','3607');
insert into BOK_MATCH_RLAT values ('03','QY','203','3506','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','203','3607','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));

---结算指令
DELETE FROM BOK_MATCH_RLAT WHERE SOURCE='7' AND TARGET IN ('2750','2751');
insert into BOK_MATCH_RLAT values ('03','QY','7','2750','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));
insert into BOK_MATCH_RLAT values ('03','QY','7','2751','system','',to_date('2020/12/10', 'yyyy/mm/dd'), 'system',to_date('2020/12/10', 'yyyy/mm/dd'));














