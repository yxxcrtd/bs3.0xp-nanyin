package com.joyintech.tams.exchange.common.constant;

public enum EntrustType {
    //根据恒生柜台转入可能使用的值
    ENTRUST("委托", "0"),
    QUERY("查询", "1"),
    CANCEL("撤单", "2"),
    REPLENISH("补单", "3"),
    CONFIRM("确认", "4"),
    LARGE("大宗", "5"),
    BORROW("信用融资", "6"),
    STOCK_LOAN("信用融券", "7"),
    COMBINE("组合买卖", "C"),
    COMBINE_CHANGE("组合转换", "D"),
    COMBINE_CANCEL("组合撤销", "E");


    public final String value;
    public final String name;

    EntrustType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
