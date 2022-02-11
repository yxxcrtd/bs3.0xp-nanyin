package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//登录返回头
public class RespLoginHeader implements IRespHeader {
    public String name;
    public char market;
    public String account;
    public char moneyType;
    public String accountNum;//客户号
    public int marketOrAccountSize;
    public String yybName;
    public String sysDate;//  系统工作日期
    public char sysStatus;//系统工作状态
    public String sysStatusName;// 系统工作状态名称
    public String yybCode;//   开户营业部代码
    public String versionNum;//柜台版本号
    public String mainAccountCodeInfo;//各主帐号代码信息
    public String backupHistoryDate;//大集中备份历史日期
    public String alarmInfo;//客户报警信息
    public String alarmCode;//报警代码
    public char marginAuth;//客户融资融券权限
    public String loginInfo;//上次登入信息
    public String loginVerifyInfo;// 周边登录校验信息
    public char openEndRight;//开放式基金权限
    public String clientRights;//客户权限
    public char buybackRights;//报价回购权限
    public char strongIDAuth;//客户是否开通强身份认证
    public char strongIDAuthPlatform;//该委托方式是否启用强身份认证平台
    public String warnInfoTag;//客户警示信息标志
    public String tradeCenter;//客户所属交易中心
    public int whitelistMark;//股权激励白名单标志
    public String warnInfo;//   客户警示信息


    @Override
    public int getResponseCount() {
        return marketOrAccountSize;
    }
}
