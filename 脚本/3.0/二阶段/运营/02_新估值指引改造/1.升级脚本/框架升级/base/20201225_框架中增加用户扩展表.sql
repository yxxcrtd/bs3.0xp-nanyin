/*drop table SYS_USER_ADD cascade constraints;*/

/*==============================================================*/
/* Table: SYS_USER_ADD                                      */
/*==============================================================*/
create table SYS_USER_ADD 
(
   USER_ID              VARCHAR(32)          not null,
   LST_LOGIN_TIME       TIMESTAMP            not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_USER_ADD primary key (USER_ID)
);

comment on table SYS_USER_ADD is
'用户附属表';

comment on column SYS_USER_ADD.USER_ID is
'用户代码';

comment on column SYS_USER_ADD.LST_LOGIN_TIME is
'最后登录时间';

comment on column SYS_USER_ADD.CREATE_USER is
'创建人';

comment on column SYS_USER_ADD.CREATE_DEPT is
'创建部门';

comment on column SYS_USER_ADD.CREATE_TIME is
'创建时间';

comment on column SYS_USER_ADD.UPDATE_USER is
'更新人';

comment on column SYS_USER_ADD.UPDATE_TIME is
'更新时间';