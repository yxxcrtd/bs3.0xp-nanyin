package com.joyintech.tams.model.valuation.batch.calculation.constant;

/**
 * 曲线类型
 * @author zznn
 */
public enum LineType {
    即期("01"),
    到期("02");

    /**
     * 曲线类型名
     */
    private String name;

    /**
     * 获取曲线类型名
     *
     * @return name.鸡
     */
    public String getName() {
        return name;
    }

    private LineType(String name) {
        this.name = name;
    }

    public static LineType parse(String value) {
        LineType result = null;

        if (value == null) {
            return result;
        }

        switch (value) {
            case "01":
                result = LineType.即期;
                break;
            case "02":
                result = LineType.到期;
                break;
            default:
        }
        return result;
    }
}
