/** add by yanya on 20201224 凭证明细表增加科目名称、四级科目名称字段**/
alter table BOK_VOUCH_DETAIL add SUBJECT_NAME VARCHAR2(200);
alter table BOK_VOUCH_DETAIL add DETAIL_SUBJECT_NAME VARCHAR2(500);
comment on column BOK_VOUCH_DETAIL.SUBJECT_NAME is '科目名称';
comment on column BOK_VOUCH_DETAIL.DETAIL_SUBJECT_NAME is '四级科目名称';

/** add by yanya on 20201225 凭证明细表增加科目名称、四级科目名称字段历史数据刷新**/
/** 需特别注意这个脚本需要在刷新科目表数据之前刷新，否则会导致关联不上**/
update BOK_VOUCH_DETAIL upd
	set upd.subject_name =
       (select b.subject_name
          from BOK_VOUCH_DETAIL t
          left join BOK_VOUCH_MAIN m
            on t.vouch_num = m.vouch_num
          left join bok_bookset a
            on m.bookset_id = a.bookset_id
          left join bok_subject b
            on t.subject_no = b.subject_no
           and b.bookset_id = a.bookset_id_tmpl
         where t.vouch_subnum = upd.vouch_subnum),
       upd.detail_subject_name =
       (select sub.detail_subject_name
          from BOK_VOUCH_DETAIL t
          left join BOK_VOUCH_MAIN m
            on t.vouch_num = m.vouch_num
          left join bok_bookset a
            on m.bookset_id = a.bookset_id
          left join BOK_BUSS_DETSUB sub
            on sub.detail_subject_no = t.detail_subject_no
           and sub.bookset_id = m.bookset_id
         where t.vouch_subnum = upd.vouch_subnum);