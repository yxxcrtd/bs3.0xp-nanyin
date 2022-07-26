package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class BokBalance extends BokBalanceKey {
    /**
     * 原币币种：AED阿联酋迪拉姆(AED),AUD澳大利亚元（AUD）,CAD加拿大元（CAD）,CHF瑞士法郎（CHF）,CNY人民币（CNY）,DKK丹麦克朗（DKK）,EUR欧元（EUR）,GBP英镑（GBP）,HKD港元（HKD）,HUF匈牙利福林(HUF),JPY日元（JPY）,KHR柬埔寨瑞尔(KHR),KRW韩元(KRW),KZT哈萨克斯坦坚戈(KZT),MNT蒙古图格里克 (MNT),MOP澳门元（MOP）,MXN墨西哥元(MXN),MYR马来西亚林吉特(MYR),NOK挪威克朗(NOK),NZD新西兰元(NZD),PLN波兰兹罗提(PLN),RUB俄罗斯卢布（RUB）,SAR沙特里亚尔(SAR),SEK瑞典克朗（SEK）,SGD新加坡元（SGD）,THB泰铢(THB),TRY新土耳其里拉(TRY),TWD新台币（TWD）,USD美元（USD）,ZAR南非兰特(ZAR)
     */
    private String oCcy;

    /**
     * 原币余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oAmt;

    /**
     * 发生额（借方）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyODAmt;

    /**
     * 错误信息
     */
    private String errormsg;

    /**
     * 取得 原币币种：AED阿联酋迪拉姆(AED),AUD澳大利亚元（AUD）,CAD加拿大元（CAD）,CHF瑞士法郎（CHF）,CNY人民币（CNY）,DKK丹麦克朗（DKK）,EUR欧元（EUR）,GBP英镑（GBP）,HKD港元（HKD）,HUF匈牙利福林(HUF),JPY日元（JPY）,KHR柬埔寨瑞尔(KHR),KRW韩元(KRW),KZT哈萨克斯坦坚戈(KZT),MNT蒙古图格里克 (MNT),MOP澳门元（MOP）,MXN墨西哥元(MXN),MYR马来西亚林吉特(MYR),NOK挪威克朗(NOK),NZD新西兰元(NZD),PLN波兰兹罗提(PLN),RUB俄罗斯卢布（RUB）,SAR沙特里亚尔(SAR),SEK瑞典克朗（SEK）,SGD新加坡元（SGD）,THB泰铢(THB),TRY新土耳其里拉(TRY),TWD新台币（TWD）,USD美元（USD）,ZAR南非兰特(ZAR)
     * @return 原币币种：AED阿联酋迪拉姆(AED),AUD澳大利亚元（AUD）,CAD加拿大元（CAD）,CHF瑞士法郎（CHF）,CNY人民币（CNY）,DKK丹麦克朗（DKK）,EUR欧元（EUR）,GBP英镑（GBP）,HKD港元（HKD）,HUF匈牙利福林(HUF),JPY日元（JPY）,KHR柬埔寨瑞尔(KHR),KRW韩元(KRW),KZT哈萨克斯坦坚戈(KZT),MNT蒙古图格里克 (MNT),MOP澳门元（MOP）,MXN墨西哥元(MXN),MYR马来西亚林吉特(MYR),NOK挪威克朗(NOK),NZD新西兰元(NZD),PLN波兰兹罗提(PLN),RUB俄罗斯卢布（RUB）,SAR沙特里亚尔(SAR),SEK瑞典克朗（SEK）,SGD新加坡元（SGD）,THB泰铢(THB),TRY新土耳其里拉(TRY),TWD新台币（TWD）,USD美元（USD）,ZAR南非兰特(ZAR)
     */
    public String getoCcy() {
        return oCcy;
    }

    /**
     * 设置 原币币种：AED阿联酋迪拉姆(AED),AUD澳大利亚元（AUD）,CAD加拿大元（CAD）,CHF瑞士法郎（CHF）,CNY人民币（CNY）,DKK丹麦克朗（DKK）,EUR欧元（EUR）,GBP英镑（GBP）,HKD港元（HKD）,HUF匈牙利福林(HUF),JPY日元（JPY）,KHR柬埔寨瑞尔(KHR),KRW韩元(KRW),KZT哈萨克斯坦坚戈(KZT),MNT蒙古图格里克 (MNT),MOP澳门元（MOP）,MXN墨西哥元(MXN),MYR马来西亚林吉特(MYR),NOK挪威克朗(NOK),NZD新西兰元(NZD),PLN波兰兹罗提(PLN),RUB俄罗斯卢布（RUB）,SAR沙特里亚尔(SAR),SEK瑞典克朗（SEK）,SGD新加坡元（SGD）,THB泰铢(THB),TRY新土耳其里拉(TRY),TWD新台币（TWD）,USD美元（USD）,ZAR南非兰特(ZAR)
     * @param oCcy 原币币种：AED阿联酋迪拉姆(AED),AUD澳大利亚元（AUD）,CAD加拿大元（CAD）,CHF瑞士法郎（CHF）,CNY人民币（CNY）,DKK丹麦克朗（DKK）,EUR欧元（EUR）,GBP英镑（GBP）,HKD港元（HKD）,HUF匈牙利福林(HUF),JPY日元（JPY）,KHR柬埔寨瑞尔(KHR),KRW韩元(KRW),KZT哈萨克斯坦坚戈(KZT),MNT蒙古图格里克 (MNT),MOP澳门元（MOP）,MXN墨西哥元(MXN),MYR马来西亚林吉特(MYR),NOK挪威克朗(NOK),NZD新西兰元(NZD),PLN波兰兹罗提(PLN),RUB俄罗斯卢布（RUB）,SAR沙特里亚尔(SAR),SEK瑞典克朗（SEK）,SGD新加坡元（SGD）,THB泰铢(THB),TRY新土耳其里拉(TRY),TWD新台币（TWD）,USD美元（USD）,ZAR南非兰特(ZAR)
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy == null ? null : oCcy.trim();
    }

    /**
     * 取得 原币余额
     * @return 原币余额
     */
    public BigDecimal getoAmt() {
        return oAmt;
    }

    /**
     * 设置 原币余额
     * @param oAmt 原币余额
     */
    public void setoAmt(BigDecimal oAmt) {
        this.oAmt = oAmt;
    }

    /**
     * 取得 发生额（借方）
     * @return 发生额（借方）
     */
    public BigDecimal getTdyODAmt() {
        return tdyODAmt;
    }

    /**
     * 设置 发生额（借方）
     * @param tdyODAmt 发生额（借方）
     */
    public void setTdyODAmt(BigDecimal tdyODAmt) {
        this.tdyODAmt = tdyODAmt;
    }

    /**
     * 取得 错误信息
     * @return 错误信息
     */
    public String getErrormsg() {
        return errormsg;
    }

    /**
     * 设置 错误信息
     * @param errormsg 错误信息
     */
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg == null ? null : errormsg.trim();
    }
}