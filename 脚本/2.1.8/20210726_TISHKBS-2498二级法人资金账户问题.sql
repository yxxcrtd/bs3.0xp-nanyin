  ----  删除主键
  alter table STL_CAPACCT_INFO drop constraint PK_STL_CAPACCT_INFO;
drop index PK_STL_CAPACCT_INFO;

-- 创建主键
  alter table STL_CAPACCT_INFO add constraint PK_STL_CAPACCT_INFO primary key (seq_no);
  
  -- 创建原索引
  create  index IDX1_STL_CAPACCT_INFO on STL_CAPACCT_INFO (ACCT_NO, CCY);