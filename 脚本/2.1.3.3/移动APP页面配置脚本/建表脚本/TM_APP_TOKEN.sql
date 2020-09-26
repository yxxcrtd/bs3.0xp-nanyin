-- Create table
create table TM_APP_TOKEN
(
  userid          VARCHAR2(50) not null,
  token           VARCHAR2(200) not null,
  expiration_data DATE not null,
  create_data     DATE not null
);
  
-- Add comments to the columns 
comment on column TM_APP_TOKEN.userid
  is '用户id';
comment on column TM_APP_TOKEN.token
  is '用户token';
comment on column TM_APP_TOKEN.expiration_data
  is '过期时间';
comment on column TM_APP_TOKEN.create_data
  is '创建时间';
