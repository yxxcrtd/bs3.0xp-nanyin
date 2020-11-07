Alter table BOK_SCHEME_INFO drop constraint PK_BOK_SCHEME_INFO cascade drop index;
drop index PK_BOK_SCHEME_INFO;

alter table BOK_SCHEME_INFO add constraint PK_BOK_SCHEME_INFO primary key (SCH_ID,SCHEME_TYPE);