package com.joyintech.tams.invest.trade.cstptrdmatchinfo.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.trade.cstpTrdMatchInfo.CstpTrdMatchInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CstpTrdMatchInfoDto
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/9 0009
 * @Version V1.0
 **/
public class CstpTrdMatchInfoDto extends CstpTrdMatchInfo {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -7438510730454849159L;

    private List<TrdMatchInfo> trdMatchInfos;

    public List<TrdMatchInfo> getTrdMatchInfos() {
        return trdMatchInfos;
    }

    public void setTrdMatchInfos(List<TrdMatchInfo> trdMatchInfos) {
        this.trdMatchInfos = trdMatchInfos;
    }


    public String getMatchTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.下行交易匹配类型, super.getMatchType());
    }

    public String getPsStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }

    public String getSettleSpreedStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, super.getSettleSpreed());
    }
    public String getFinprodTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型,super.getFinprodType());
    }
    public String getInvAimStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.投资目的, super.getInvAim());
    }


}
