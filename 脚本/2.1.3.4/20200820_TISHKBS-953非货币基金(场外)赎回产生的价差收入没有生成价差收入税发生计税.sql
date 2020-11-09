-- 【计税流水查询、记账凭证查询-晋城UAT测试】非货币基金（场外）赎回产生的价差收入没有生成价差收入税发生计税  李志修复
UPDATE TAX_BOK_CONFIG SET SUBJECT_NATURE = 'GYJZSYLTZSY' WHERE CONFIG_ID = '1634TZSY001';