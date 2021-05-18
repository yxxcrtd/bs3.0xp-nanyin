delete from BOK_BOOKSET_EXT where param_code='PLAN_SUBTRACTION_TYPE' and bookset_id in ('00000000000000000002','00000000000000000003','00000000000000000004');
insert into BOK_BOOKSET_EXT values ('00000000000000000002','PLAN_SUBTRACTION_TYPE','01','项目类计提区间倒减方式','01：倒减科目余额；02：倒减前一日计算结果','system','',sysdate, 'system',sysdate);
insert into BOK_BOOKSET_EXT values ('00000000000000000003','PLAN_SUBTRACTION_TYPE','01','项目类计提区间倒减方式','01：倒减科目余额；02：倒减前一日计算结果','system','',sysdate, 'system',sysdate);
insert into BOK_BOOKSET_EXT values ('00000000000000000004','PLAN_SUBTRACTION_TYPE','01','项目类计提区间倒减方式','01：倒减科目余额；02：倒减前一日计算结果','system','',sysdate, 'system',sysdate);

