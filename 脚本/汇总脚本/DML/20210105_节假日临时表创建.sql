--通过节假日表创建节假日临时表
drop table MST_HOLIDAY_tmp;
create table MST_HOLIDAY_tmp as select * from MST_HOLIDAY where 1=0;