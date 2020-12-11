--BS-1676
update stt_indicator set remark = '现金缺口=未来现金流出-未来现金流入-现金余额，负数表示为资金盈余，正数表示为有资金缺口' where ind_id = '1003';
update stt_indicator set remark = '二级风险缓释后缺口 = 现金缺口 + 一级流动性资产可变现市值*折现系数（默认为1）+ 二级流动性资产可变现市值*二级流动性资产变化率（风险因子' where ind_id = '1004';

--BS-1739
--备份
--INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CUSTOM_ASSET_TYPE_THREE', 'D003', 'CUSTOM_ASSET_TYPE_TWO', 'D02                                               ', 'system', null, sysdate, 'system', sysdate);
delete from SYS_DATADICT_LIST_LINK where dict_code = 'CUSTOM_ASSET_TYPE_THREE' and ITEM_CODE='D003' and LINKED_DICT_CODE = 'CUSTOM_ASSET_TYPE_TWO' AND LINKED_ITEM_CODE='D02                                               ';
