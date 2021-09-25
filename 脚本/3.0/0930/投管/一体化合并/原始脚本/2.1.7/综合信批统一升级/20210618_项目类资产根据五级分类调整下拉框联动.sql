--项目类资产根据五级分类调整下拉框联动
delete from sys_datadict_list_link where  LINKED_DICT_CODE = 'CUSTOM_ASSET_TYPE_FIVE' and LINKED_ITEM_CODE = '040' and DICT_CODE = 'SVR_ASSET_TYPE_SECOND_LEVEL' and ITEM_CODE = '2202';
