package com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam;

import com.joyintech.tams.model.valuation.batch.calculation.InputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.constant.SecIntSchCalMethod;
import com.joyintech.tams.model.valuation.batch.calculation.constant.Variety;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CashInfoDto;

import java.time.LocalDate;
import java.util.List;

/**
 * 剩余期限输入参数
 * @author zznn
 * @date 2021/7/1
 */
public class RemainingTermInputParameter implements InputParameter {

    /**
     * 基准日期
     */
    private LocalDate calDate;

    /**
     * 现金流信息
     */
    private List<CashInfoDto> cashInfoDtoList;

    /**
     * 首次付息日
     */
    private LocalDate firstPayDate;

    /**
     * 息票品种 0:附息 1:贴现 2:零息 3:利息本清
     */
    protected Variety variety;

    /**
     * 推算方式 0:起息日向后 1:到期日向前 2:首次付息日 息票品种为附息时必输，否则为空
     */
    protected SecIntSchCalMethod calMethod;

    public RemainingTermInputParameter(){
        super();
    }
    /**
     * 对象初始化
     * @param calDate 基准日
     * @param cashInfoDtoList 现金流信息
     * @param firstPayDate 首次付息日
     * @param variety 息票品种
     * @param calMethod 推算方式
     */
    public RemainingTermInputParameter(LocalDate calDate,List<CashInfoDto> cashInfoDtoList,
                                       LocalDate firstPayDate,Variety variety,SecIntSchCalMethod calMethod){
        super();
        this.calDate = calDate;
        this.cashInfoDtoList = cashInfoDtoList;
        this.firstPayDate = firstPayDate;
        this.variety = variety;
        this.calMethod = calMethod;
    }


    @Override
    public void check() {
        // 基准日期
        checkNull(calDate, "基准日期");

        // 现金流信息
        checkNull(cashInfoDtoList, "现金流信息");

        // 息票品种
        //checkNull(variety, "息票品种");

        // 推算方式
        checkNull(calMethod, "推算方式");

        if (SecIntSchCalMethod.首次付息日.equals(calMethod)) {
            // 计息基础
            checkNull(firstPayDate, "首次付息日");
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RemainingTermInputParameter [calDate=" + calDate + ", cashInfoDtoList=" + cashInfoDtoList
                + ", calMethod=" + calMethod + "]";
    }


    public LocalDate getCalDate() {
        return calDate;
    }


    public void setCalDate(LocalDate calDate) {
        this.calDate = calDate;
    }


    public List<CashInfoDto> getCashInfoDtoList() {
        return cashInfoDtoList;
    }


    public void setCashInfoDtoList(List<CashInfoDto> cashInfoDtoList) {
        this.cashInfoDtoList = cashInfoDtoList;
    }


    public LocalDate getFirstPayDate() {
        return firstPayDate;
    }


    public void setFirstPayDate(LocalDate firstPayDate) {
        this.firstPayDate = firstPayDate;
    }


    public SecIntSchCalMethod getCalMethod() {
        return calMethod;
    }


    public void setCalMethod(SecIntSchCalMethod calMethod) {
        this.calMethod = calMethod;
    }


    public Variety getVariety() {
        return variety;
    }


    public void setVariety(Variety variety) {
        this.variety = variety;
    }
}

