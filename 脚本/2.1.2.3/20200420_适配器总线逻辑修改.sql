update  ADPT_INTERFACESOURCE  set  DBSQL='select SYS,HOST_NM,BATCH_NUM,TO_CHAR(DATA_DATE,''yyyymmdd'') DATA_DATE,READY_TIME from DIS_DATA_READY where DATA_DATE>:updateTimeStr'
where   sourceid='1042';