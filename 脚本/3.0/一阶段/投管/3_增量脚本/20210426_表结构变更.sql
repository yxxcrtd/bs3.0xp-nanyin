--资金账户信息表：【账号】字段长度由30改为60
alter table STL_CAPACCT_INFO modify busi_acct_no VARCHAR2(60);