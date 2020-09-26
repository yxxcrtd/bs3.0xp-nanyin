--Add By QuJianZhong 20200509 项目类资产，五级分类为PPP时，资产/负债类别只有"1399:其他权益类投资"，删除多余选项 start
DELETE FROM sys_datadict_list_link WHERE DICT_CODE ='SVR_ASSET_TYPE_SECOND_LEVEL' AND LINKED_DICT_CODE ='CUSTOM_ASSET_TYPE_FIVE' AND LINKED_ITEM_CODE = '049' AND ITEM_CODE IN ('1202','1203','1208','1299');
--Add By QuJianZhong 20200509 项目类资产，五级分类为PPP时，资产/负债类别只有"1399:其他权益类投资"，删除多余选项 end