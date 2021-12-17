package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

/**
 * create By MAXX on 2020/6/23
 */
public class RespAccountCheckHeader implements IRespHeader {
    public String name;
    public char market;
    public String account;
    public char moneyType;
    public String accountNum;//客户号
    public char versionNum;//柜台版本号
    public String yybId;
    public String warnMsg;//报警信息
    public String warnCode;// 报警信息代码
    public char marginTradingRights;//融资融券客户权限
    public char openEndRight;//开放式基金权限
    public String customerRights;//客户权限
    public char buybackRights;//报价回购权限
    public char strongIDAuth;//客户是否开通强身份认证
    public char strongIDAuthPlatform;//该委托方式是否启用强身份认证平台
    public String tradeCenter;//客户所属交易中心

    @Override
    public int getResponseCount() {
        return 0;
    }
}
