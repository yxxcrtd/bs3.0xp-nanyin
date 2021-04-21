package com.joyintech.tams.invest.trade.cstptrdmatchinfo.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.trade.cstpTrdMatchInfo.CstpTrdMatchInfo;
import com.joyintech.tams.invest.trade.cstp.dto.CstpSelectDto;
import com.joyintech.tams.invest.trade.investtrade.commandreceive.dto.TrdCommandReceive001Dto;

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

    private List<TrdMatchInfoDto> trdMatchInfos;
    
    private List<CstpSelectDto> cstpSelectDtoList;//cstp列表
    
    private List<TrdCommandReceive001Dto> trdCommandReceive001DtoList;//委托列表

    private String finprodMarketId;

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

	public List<TrdMatchInfoDto> getTrdMatchInfos() {
		return trdMatchInfos;
	}

	public void setTrdMatchInfos(List<TrdMatchInfoDto> trdMatchInfos) {
		this.trdMatchInfos = trdMatchInfos;
	}

	public List<CstpSelectDto> getCstpSelectDtoList() {
		return cstpSelectDtoList;
	}

	public void setCstpSelectDtoList(List<CstpSelectDto> cstpSelectDtoList) {
		this.cstpSelectDtoList = cstpSelectDtoList;
	}

	public List<TrdCommandReceive001Dto> getTrdCommandReceive001DtoList() {
		return trdCommandReceive001DtoList;
	}

	public void setTrdCommandReceive001DtoList(List<TrdCommandReceive001Dto> trdCommandReceive001DtoList) {
		this.trdCommandReceive001DtoList = trdCommandReceive001DtoList;
	}

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }
}
