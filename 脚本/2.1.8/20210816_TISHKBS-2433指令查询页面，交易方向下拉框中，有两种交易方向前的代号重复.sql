--更新画面字典表查询页面的元素
update  SYS_PAGE_DICTLIST set DICT_ITEM_VAl='IRS01' where  page_id = 'TrdIRSTrade001' and  SUB_PAGE_ID ='999999' and DICT_id='IRS_TYPE' and DICT_ITEM_VAL='01';
update  SYS_PAGE_DICTLIST set DICT_ITEM_VAl='IRS02' where  page_id = 'TrdIRSTrade001' and  SUB_PAGE_ID ='999999' and DICT_id='IRS_TYPE' and DICT_ITEM_VAL='02';

--更新画面字典表新建页面的元素
update  SYS_PAGE_DICTLIST set DICT_ITEM_VAl='IRS01' where  page_id = 'TrdIRSTrade002' and  SUB_PAGE_ID ='999999' and DICT_id='IRS_TYPE' and DICT_ITEM_VAL='01';
update  SYS_PAGE_DICTLIST set DICT_ITEM_VAl='IRS02' where  page_id = 'TrdIRSTrade002' and  SUB_PAGE_ID ='999999' and DICT_id='IRS_TYPE' and DICT_ITEM_VAL='02';

--更新数据字典明细
update  SYS_DATADICT_LIST set ITEM_CODE='IRS01' where  DICT_CODE = 'IRS_TYPE' and  ITEM_CODE ='01';
update  SYS_DATADICT_LIST set ITEM_CODE='IRS02' where  DICT_CODE = 'IRS_TYPE' and  ITEM_CODE ='02';