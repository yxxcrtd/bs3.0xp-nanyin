package com.joyintech.tams.exchange.common.constant;

/*** 
* @Description: 市价方式Y
* @Param:  
* @return:  
* @Author: MAXX
* @Date: 2020/7/9 
*/
public enum MarketPriceType {
    LIMITED_PRICE("限价", "0"),
    MARKET_PRICE("市价", "1"),
    SDSFZYJGWT("深对手方最优价格委托", "2"),
    SBFZYJGWT("深本方最优价格委托", "3"),
    SJSCJSYCXWT("深即时成交剩余撤销委托", "4"),
    HSZYWDBCXWT("沪深最优五档并撤销委托", "5"),
    SQECJHCXWT("深全额成交或撤销委托", "6"),
    HZYWDBZXJWT("沪最优五档并转限价委托", "7");

    public final String value;
    public final String name;

    MarketPriceType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
