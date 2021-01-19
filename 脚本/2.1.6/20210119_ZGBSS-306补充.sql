update rsk_event_item_rule set item_value = 'F07'  where event_code = 'EVENT_ID_LDXSXZCGZ_04'  and item_code = 'assetType' and item_value like '%F10%';
--根据需求回滚
update rsk_event_item_rule  set item_value =  'F07,F10'  where event_code in ('EVENT_ID_LDXSXZCGZ_04','EVENT_ID_LDXSXZCGZ_07','EVENT_ID_LDXSXZCGZ_05') and item_code = 'assetType' and item_value like '%F07%';
--ZGBSS-310
delete from RSK_EVENT_ITEM_RULE where EVENT_CODE = 'EVENT_ID_LDXSXZCGZ_07' and ITEM_CODE = 'mdate' and GROUP_ID = 'c4fb54f59929487c936b7f63365adc2d';
INSERT INTO RSK_EVENT_ITEM_RULE (EVENT_CODE, ITEM_CODE, GROUP_ID, ITEM_ANO_NAME, ITEM_VALUE, MATCH_TYPE, OFFSET_VAL, OFFSET_TYPE, OFFSET_UNIT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EVENT_ID_LDXSXZCGZ_07', 'mdate', 'c4fb54f59929487c936b7f63365adc2d', null, '${sysDate}', '>', 10, '+', '01', 'admin', '001', TIMESTAMP '2020-09-16 10:44:22', 'admin', TIMESTAMP '2020-09-16 10:44:22');
