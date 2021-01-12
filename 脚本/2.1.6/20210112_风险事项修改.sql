--ZGBSS-41
update RSK_EVENT_ITEM_RULE set item_code ='C1,C2,C3,T98' where event_code = 'EVENT_ID_CPZZC_03' and item_code = 'ps';
update RSK_EVENT_ITEM_RULE set item_code = 'C4,C6,C9,C11,C12,C17,C20,T99'  where event_code = 'EVENT_ID_CPZZC_04' and item_code = 'ps' and item_value like '%C4%';
update RSK_EVENT_ITEM_RULE set item_value ='T07,T08,T09,T11,T38,T54,T56,T39,T98,C1,C2,C3,T24'  where event_code = 'EVENT_ID_XJGM_03' and item_code = 'ps' and item_value like '%C9%';
--ZGBSS-56
update RSK_EVENT_ITEM_RULE set item_value = 'C4,C6,C17' where event_code = 'EVENT_ID_CPJZCJZ_04' and  item_code = 'ps';
