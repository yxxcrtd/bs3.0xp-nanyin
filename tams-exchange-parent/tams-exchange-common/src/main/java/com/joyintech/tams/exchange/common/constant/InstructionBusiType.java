package com.joyintech.tams.exchange.common.constant;

import java.util.HashSet;
import java.util.Set;

public enum InstructionBusiType {
    STOCK_BIDDING("股票竞价指令", "01"),
    FUND_BIDDING("基金竞价指令", "02"),
    LARGE_REPO("大宗债券质押式回购指令", "03"),
    LARGE_STOCK("大宗股票指令", "04"),
    LARGE_FUND("大宗基金指令", "05"),
    LARGE_BOND("大宗债券指令", "06"),
    BOND_BIDDING("债券竞价指令", "07"),
    BOND_SH_FIX("上交所固定收益平台现券交易指令", "08"),
    BOND_RESALE("债券回售指令", "09"),
    RIGHTS_ISSUE("配股指令", "10"),
    IPO_APPLY("新股申购指令", "11"),
    BOND_RATION("配债指令", "12"),
    CONVERTIBLE_BOND_APPLY("可转债申购指令", "13"),
    STD_BOND_IN("标准券出入库指令", "14"),
    REPO_BIDDING("债券质押式回购竞价指令", "15"),
    BOND_CONVERT("债转股指令", "16"),
    NEGOTIATED_REPO_OPEN("协议回购首期指令", "17"),
    NEGOTIATED_REPO_TERMINATION("协议回购提前购回指令", "18"),
    NEGOTIATED_REPO_EXPIRE("协议回购到期购回指令", "19"),
    NEGOTIATED_REPO_CONTINUE("协议回购到期续作指令", "20"),
    NEGOTIATED_REPO_EXCHANGE("协议回购中途换券指令", "21"),
    NEGOTIATED_REPO_RELEASE("协议回购解除质押指令", "22"),
    FUTURES("期货指令", "23"),
    ETF_APPLY("ETF申购赎回指令", "24");

    public final String value;
    public final String name;

    InstructionBusiType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    static Set<String> biddingType = new HashSet<String>() {
        {
            add(STOCK_BIDDING.value);
            add(FUND_BIDDING.value);
            add(BOND_BIDDING.value);
            add(REPO_BIDDING.value);
            add(FUTURES.value);
        }
    };

    public static boolean isBiddingType(String type) {
        return biddingType.contains(type);
    }
}
