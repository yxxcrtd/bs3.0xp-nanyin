/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.joyintech.tams.valuation.common.constants;

/**
 * 账套类型
 *
 * @author 强成西
 */
public enum AccountType {
    理财账("02"), 银行账("01");

    private String code;

    private AccountType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static AccountType fromCode(String code) {
        for (AccountType value : AccountType.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
