/** 记账凭证查询--业务类别 add by lby on 20200107  start**/
------==========================---------
------       添加会计场景       ---------
------==========================---------
delete from 
SYS_DATADICT_LIST sdl where sdl.dict_code = 'TABLE_ID' 
and sdl.item_code in (
'1009',
'1010',
'1011',
'1012',
'1421',
'1422',
'1707',
'1708',
'1803',
'1804',
'1813',
'1814',
'1815',
'1816',
'1817',
'1818',
'3007',
'3103',
'3104',
'3105',
'3107',
'3108',
'3109',
'3110',
'3201',
'3202',
'3203',
'3204',
'3205',
'3206',
'3207',
'3208',
'3209',
'3210',
'3401',
'3402',
'3403',
'3404',
'3405',
'3406',
'3407',
'3408',
'3409',
'3410',
'3411',
'8116',
'8117',
'B8103',
'B8104',
'B8105',
'B8106'
);
/**
1009  DVP账户划出
1010  DVP账户划回
1011  利率互换结算资金划入
1012  利率互换结算资金划出
**/
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1009', 'DVP账户划出', 600, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1010', 'DVP账户划回', 601, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1011', '利率互换结算资金划入', 602, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1012', '利率互换结算资金划出', 603, '0', 'system', null, sysdate, 'system', sysdate);
/**
1421  质押式逆回购-逾期返售
1422  质押式正回购-逾期返售
**/
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1421', '质押式逆回购-逾期返售', 604, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1422', '质押式正回购-逾期返售', 605, '0', 'system', null, sysdate, 'system', sysdate);
/**
1707  通道费-转出
1708  通道费-转入
**/
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1707', '通道费-转出', 606, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1708', '通道费-转入', 607, '0', 'system', null, sysdate, 'system', sysdate);
/**
1803  利率互换-期间/到期/提前终止收息
1804  利率互换-期间/到期/提前终止付息
**/
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1803', '利率互换-期间/到期/提前终止收息', 608, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1804', '利率互换-期间/到期/提前终止付息', 609, '0', 'system', null, sysdate, 'system', sysdate);
/**
1813  利率互换-交易保证金调整
1814  利率互换-每日盯市损益计提
1815  利率互换-每日盯市损益利息调整计提
1816  利率互换-利息净额-计提
1817  利率互换-每日盯市损益利息调整-结算
1818  利率互换-每日盯市损益-结算
**/
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1813', '利率互换-交易保证金调整', 611, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1814', '利率互换-每日盯市损益计提', 612, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1815', '利率互换-每日盯市损益利息调整计提', 613, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1816', '利率互换-利息净额-计提', 614, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1817', '利率互换-每日盯市损益利息调整-结算', 615, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '1818', '利率互换-每日盯市损益-结算', 616, '0', 'system', null, sysdate, 'system', sysdate);
/**
3007  港股通证券组合费支付
**/
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3007', '港股通证券组合费支付', 617, '0', 'system', null, sysdate, 'system', sysdate);
/**
3103  期权交易-平仓买入
3104  期权交易-平仓卖出
3105  期权交易-期权获赠
**/
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3103', '期权交易-平仓买入', 618, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3104', '期权交易-平仓卖出', 619, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3105', '期权交易-期权获赠', 620, '0', 'system', null, sysdate, 'system', sysdate);
/**
3107  期权交易-认购期权买入方行权
3108  期权交易-认沽期权买入方行权
3109  期权交易-买入方期权到期
3110  期权交易-卖出方期权到期
**/
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3107', '期权交易-认购期权买入方行权', 621, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3108', '期权交易-认沽期权买入方行权', 622, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3109', '期权交易-买入方期权到期', 623, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3110', '期权交易-卖出方期权到期', 624, '0', 'system', null, sysdate, 'system', sysdate);
/**
3201  期货交易-开仓买入
3202  期货交易-开仓卖出
3203  期货交易-平仓买入
3204  期货交易-平仓卖出

3205  期货交易-每日期货估值
3206  期货交易-确认平仓投资收益
3207  期货交易-期货交割违约
3208  期货交易-每日期货的无负债结算

3209  期货交易-期货交割被违约
3210  期货交易-券商保证金调整

3401  货币互换-交易日表外登记
3402  货币互换-首期费用计提
3403  货币互换-首期本金交易
3404  货币互换-每日公允价值变动

3405  货币互换-收入端利息计提
3406  货币互换-支出端利息计提
3407  货币互换-期间/到期/提前终止收息
3408  货币互换-期间/到期/提前终止付息

3409  货币互换-终止/到期冲减公允价值
3410  货币互换-到期日表外登记
3411  货币互换-到期本金交易
**/

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3201', '期货交易-开仓买入', 625, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3202', '期货交易-开仓卖出', 626, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3203', '期货交易-平仓买入', 627, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3204', '期货交易-平仓卖出', 628, '0', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3205', '期货交易-每日期货估值', 629, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3206', '期货交易-确认平仓投资收益', 630, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3207', '期货交易-期货交割违约', 631, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3208', '期货交易-每日期货的无负债结算', 632, '0', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3209', '期货交易-期货交割被违约', 633, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3210', '期货交易-券商保证金调整', 634, '0', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3401', '货币互换-交易日表外登记', 635, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3402', '货币互换-首期费用计提', 636, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3403', '货币互换-首期本金交易', 637, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3404', '货币互换-每日公允价值变动', 638, '0', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3405', '货币互换-收入端利息计提', 639, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3406', '货币互换-支出端利息计提', 640, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3407', '货币互换-期间/到期/提前终止收息', 641, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3408', '货币互换-期间/到期/提前终止付息', 642, '0', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3409', '货币互换-终止/到期冲减公允价值', 643, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3410', '货币互换-到期日表外登记', 644, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '3411', '货币互换-到期本金交易', 645, '0', 'system', null, sysdate, 'system', sysdate);

/**
8116  产品-浮动管理费贴补
8117  产品-待摊费用计提
**/
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '8116', '产品-浮动管理费贴补', 646, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', '8117', '产品-待摊费用计提', 647, '0', 'system', null, sysdate, 'system', sysdate);

/**
B8103 产品固定管理费支付
B8104 产品固定管理费计提
B8105 产品浮动管理费支付
B8106 产品浮动管理费计提
**/
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', 'B8103', '产品固定管理费支付', 648, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', 'B8104', '产品固定管理费计提', 649, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', 'B8105', '产品浮动管理费支付', 650, '0', 'system', null, sysdate, 'system', sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TABLE_ID', 'B8106', '产品浮动管理费计提', 651, '0', 'system', null, sysdate, 'system', sysdate);
--------==========================---------
--------        修改名称          ---------
--------==========================---------
/**
1104  存放同业-卖出
1105  拆放同业-起息/转仓买入（拆出/同业借款）
1106  拆放同业-计提（拆出/同业借款）
1107  拆放同业-到期（同业借款）
1108  拆放同业-中间收息（同业借款）
1109  拆放同业-转仓卖出（拆出/同业借款）
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '存放同业-卖出' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1104';
update SYS_DATADICT_LIST sdl set sdl.item_value = '拆放同业-起息/转仓买入（拆出/同业借款）' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1105';
update SYS_DATADICT_LIST sdl set sdl.item_value = '拆放同业-计提（拆出/同业借款）' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1106';
update SYS_DATADICT_LIST sdl set sdl.item_value = '拆放同业-到期（同业借款）' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1107';
update SYS_DATADICT_LIST sdl set sdl.item_value = '拆放同业-中间收息（同业借款）' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1108';
update SYS_DATADICT_LIST sdl set sdl.item_value = '拆放同业-转仓卖出（拆出/同业借款）' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1109';

/**
1508  计划类净值型资产-申购划款
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '计划类净值型资产-申购划款' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1508';
/**
1517  计划类净值型资产-赎回到账
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '计划类净值型资产-赎回到账' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1517';
/**
1526  公允价值损益类货币型资产-赎回/到期
1527  公允价值损益类货币型资产-到期/赎回到账
1528  公允价值损益类货币型资产-卖出转仓
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '公允价值损益类货币型资产-赎回/到期' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1526';
update SYS_DATADICT_LIST sdl set sdl.item_value = '公允价值损益类货币型资产-到期/赎回到账' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1527';
update SYS_DATADICT_LIST sdl set sdl.item_value = '公允价值损益类货币型资产-卖出转仓' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1528';
/**
1601  货币基金-认购申请
1602  货币基金-认购确认
1603  货币基金-认购返款
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-认购申请' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1601';
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-认购确认' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1602';
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-认购返款' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1603';
/**
1607  货币基金-每日红利计提
1608  货币基金-现金分红
1609  货币基金-红利再投
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-每日红利计提' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1607';
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-现金分红' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1608';
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-红利再投' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1609';
/**
1613  货币基金-申购返款
1614  货币基金-份额调增
1615  货币基金-份额调减
1616  货币基金-返款利息确认
1617  货币基金-基金转换换入确认交易
1618  货币基金-基金转换换出确认交易
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-申购返款' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1613';
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-份额调增' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1614';
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-份额调减' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1615';
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-返款利息确认' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1616';
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-基金转换换入确认交易' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1617';
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-基金转换换出确认交易' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1618';
/**
1639  非货币基金及净值型资产-转换入确认交易
1640  非货币基金及净值型资产-转换出确认交易
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '非货币基金及净值型资产-转换入确认交易' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1639';
update SYS_DATADICT_LIST sdl set sdl.item_value = '非货币基金及净值型资产-转换出确认交易' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1640';
/**
1648  非货币基金及净值型资产-买入/转仓买入划款
1649  非货币基金及净值型资产-卖出/转仓卖出到账
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '非货币基金及净值型资产-买入/转仓买入划款' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1648';
update SYS_DATADICT_LIST sdl set sdl.item_value = '非货币基金及净值型资产-卖出/转仓卖出到账' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1649';
/**
1665  货币基金-买入/转仓买入划款
1666  货币基金-卖出/转仓卖出到账
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-买入/转仓买入划款' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1665';
update SYS_DATADICT_LIST sdl set sdl.item_value = '货币基金-卖出/转仓卖出到账' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1666';
/**
1811  利率互换-期间/到期/提前终止收息到账
1812  利率互换-期间/到期/提前终止付息划款
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '利率互换-期间/到期/提前终止收息到账' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1811';
update SYS_DATADICT_LIST sdl set sdl.item_value = '利率互换-期间/到期/提前终止付息划款' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '1812';
/**
2501  债券借贷-融出
2502  债券借贷-融出费计提
2504  债券借贷-融出到期-收取费用
2507  债券借贷-融出到期
2508  债券借贷-融入
2509  债券借贷-融入费计提
2510  债券借贷-融入期间标的债券收息
2511  债券借贷-融入期间标的债券付息
2512  债券借贷-融入到期-支付费用
2513  债券借贷-融入到期
2514  融入债券卖出
2515  融入债券利息计提
2516  融入券卖出后重估
2517  融入债券卖出后收息
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '债券借贷-融出' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2501';
update SYS_DATADICT_LIST sdl set sdl.item_value = '债券借贷-融出费计提' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2502';
update SYS_DATADICT_LIST sdl set sdl.item_value = '债券借贷-融出到期-收取费用' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2504';
update SYS_DATADICT_LIST sdl set sdl.item_value = '债券借贷-融出到期' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2507';
update SYS_DATADICT_LIST sdl set sdl.item_value = '债券借贷-融入' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2508';
update SYS_DATADICT_LIST sdl set sdl.item_value = '债券借贷-融入费计提' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2509';
update SYS_DATADICT_LIST sdl set sdl.item_value = '债券借贷-融入期间标的债券收息' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2510';

update SYS_DATADICT_LIST sdl set sdl.item_value = '债券借贷-融入期间标的债券付息' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2511';
update SYS_DATADICT_LIST sdl set sdl.item_value = '债券借贷-融入到期-支付费用' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2512';
update SYS_DATADICT_LIST sdl set sdl.item_value = '债券借贷-融入到期' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2513';
update SYS_DATADICT_LIST sdl set sdl.item_value = '融入债券卖出' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2514';
update SYS_DATADICT_LIST sdl set sdl.item_value = '融入债券利息计提' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2515';
update SYS_DATADICT_LIST sdl set sdl.item_value = '融入券卖出后重估' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2516';
update SYS_DATADICT_LIST sdl set sdl.item_value = '融入债券卖出后收息' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2517';
/**
2519  融入债券卖出后还本
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '融入债券卖出后还本' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2519';
/**
2521  融入债券卖出后购回
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '融入债券卖出后购回' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2521';
/**
2702  资金收款-债券类资产卖出/行权回售/行权赎回
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '资金收款-债券类资产卖出/行权回售/行权赎回' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2702';
/**
2717  资金收款-质押式逆回购到期/提前终止
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '资金收款-质押式逆回购到期/提前终止' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2717';
/**
2719  资金划款-质押式正回购到期/提前终止
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '资金划款-质押式正回购到期/提前终止' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2719';
/**
2721  资金收款-融入债券还本派息
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '资金收款-融入债券还本派息' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2721';
/**
2799  资金划款-其他支出划款
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '资金划款-其他支出划款' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '2799';
/**
3101  期权交易-开仓买入
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '期权交易-开仓买入' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '3101';
/**
3111  期权交易-买入方期权到期
3112  期权交易-卖出方期权到期
**/
update SYS_DATADICT_LIST sdl set sdl.item_value = '期权交易-买入方期权到期' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '3111';
update SYS_DATADICT_LIST sdl set sdl.item_value = '期权交易-卖出方期权到期' 
where sdl.dict_code = 'TABLE_ID'  and sdl.item_code = '3112';

/** 记账凭证查询--业务类别 add by lby on 20200107  end**/