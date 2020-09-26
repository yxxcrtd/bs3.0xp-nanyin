---------更改风险实占表历史表的实占编号长度----------------
alter table RSK_OCCUPY_INFO modify EVENT_ID VARCHAR2(128);

alter table RSK_OCCUPY_INFO_HIS modify EVENT_ID VARCHAR2(128);

