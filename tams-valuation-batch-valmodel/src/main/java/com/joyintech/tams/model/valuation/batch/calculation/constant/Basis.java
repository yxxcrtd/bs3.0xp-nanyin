package com.joyintech.tams.model.valuation.batch.calculation.constant;

/**
 * 计息基础
 * @author zznn
 */
public enum Basis {
    A_360("A/360"),
    A_365("A/365"),
    A_365F("A/365F"),
    A_A("A/A"),
    _30_360("30/360"),
    A_A_D("A/A_D"),
    _30U_360("30U/360");

    /**
     * 计息基础名
     */
    private String name;

    /**
     * 获取计息基础名
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    private Basis(String name) {
        this.name = name;
    }

    public static Basis parse(String value) {
        Basis result = null;

        if (value == null) {
            return result;
        }

        switch (value) {
            case "A/360":
                result = Basis.A_360;
                break;
            case "A/365":
                result = Basis.A_365;
                break;
            case "A/365F":
                result = Basis.A_365F;
                break;
            case "A/A":
                result = Basis.A_A;
                break;
            case "30/360":
                result = Basis._30_360;
                break;
            case "A/A_D":
                result = Basis.A_A_D;
                break;
            case "30U/360":
                result = Basis._30U_360;
                break;
            default:
        }
        return result;
    }
}
