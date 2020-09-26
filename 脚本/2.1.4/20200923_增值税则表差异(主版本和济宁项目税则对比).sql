-- 不征税
update TAX_VAT_SCHEDULE set TAXABLE_SIGN = '02', FEE_TYPE = null, TAX_RATE = null
-- select * from TAX_VAT_SCHEDULE
 where SCH_ID = 'TAX001'
   and CAPI_INCOME_FEATURE = '02'
   and ASSET_CLASSIFY IN
       (select item_code
          from sys_datadict_list
         where DICT_CODE = 'TAX_ASSET_TYPE'
           AND ITEM_VALUE IN ('债券类资产-央行票据',
                              '债券类资产-央行票据',
                              '债券类资产-一般企业债',
                              '债券类资产-集合企业债',
                              '债券类资产-一般公司债',
                              '债券类资产-私募债',
                              '债券类资产-一般中期票据',
                              '债券类资产-集合票据',
                              '债券类资产-超短期融资债券',
                              '债券类资产-一般短期融资券',
                              '债券类资产-短期融资券',
                              '债券类资产-定向工具',
                              '债券类资产-国际机构债',
                              '债券类资产-政府支持机构债',
                              '债券类资产-可转债',
                              '债券类资产-可交换债',
                              '债券类资产-其他债券'));

-- 不征税
update TAX_VAT_SCHEDULE set TAXABLE_SIGN = '02', FEE_TYPE = null, TAX_RATE = null
-- select * from TAX_VAT_SCHEDULE
 where SCH_ID = 'TAX001'
   and CAPI_INCOME_FEATURE is null
   and ASSET_CLASSIFY IN
       (select item_code
          from sys_datadict_list
         where DICT_CODE = 'TAX_ASSET_TYPE'
           AND ITEM_VALUE IN ('基金类资产-货币基金',
                              '基金类资产-债券基金',
                              '基金类资产-股票基金',
                              '基金类资产-混合基金',
                              '基金类资产-其他公募基金',
                              '基金类资产-私募基金',
                              '基金类资产-FOF'));

--免税，改免税收入类型
update TAX_VAT_SCHEDULE set FEE_TYPE = '02'
-- select * from TAX_VAT_SCHEDULE
 where SCH_ID = 'TAX001'
   and CAPI_INCOME_FEATURE = '01'
   and ASSET_CLASSIFY IN
       (select item_code
          from sys_datadict_list
         where DICT_CODE = 'TAX_ASSET_TYPE'
           AND ITEM_VALUE = '质押式回购-逆回购');
