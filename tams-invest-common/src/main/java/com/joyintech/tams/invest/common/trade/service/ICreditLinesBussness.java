package com.joyintech.tams.invest.common.trade.service;

import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;

import java.util.HashMap;
import java.util.List;

public interface ICreditLinesBussness {

    /**
     * 此处接口默认方法作为外部调用自定义传入参数使用，实现功能的个性化，此方法可不重写传入，重写方法传入后则作为自定义条件进行相关业务逻辑处理
     * 如：这里传入自定义授信品种列表
     * Add by xkni 2019-09-12
     * @return
     */
    default HashMap<String,Object> customParm(HashMap<String,Object> map) {
        return map;
    }
    /**
     * 占用、释放授信额度参数必要性check共通处理
     * Add by xkni 2019-09-12
     * @return
     */
    List<CreditLinesDealCommon>  checkData();
    /**
     * 资产授信、系列授信
     * Add by xkni 2019-09-12
     * @return
     */
    List<CreditLines> getAssetsCeLineList();
    /**
     * 机构授信
     * Add by xkni 2019-09-12
     * @return
     */
    List<CreditLines> getOrgCeLineList(HashMap<String,Object> map);

    /**
     * 品种授信
     * Add by xkni 2019-09-12
     * @return
     */
    List<CreditLines> getVarietiesCeLineList(HashMap<String,Object> map);

    /**
     * 占用、释放授信额度数据逻辑共通处理
     * Add by xkni 2019-09-12
     * @return
     */
    List<CreditLinesDealCommon> occupancyOrRelease(HashMap<String,Object> map);

    /**
     * 占用、释放授信额度共通处理
     * @param inputmodel 交易model
     * @param isNotBottomflag 是否是底层资产交易
     * @param isSave 是否入库
     * @param isdeleteflag 是否是作废表示//TODO
     * @return
     */
    List<CreditLinesDealCommon> isNeedOccupyCre(AllTradeCommonInputDto inputmodel, boolean isNotBottomflag, boolean isSave, Boolean isdeleteflag);
}
