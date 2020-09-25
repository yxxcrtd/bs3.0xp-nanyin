/*
 * 文件名：FinProductDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.stock.dto;


import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.PaFinProduct;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.service.asset.common.dto.FinInterestFactorDto;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.component.base.core.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetDeptInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetEquityInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepDespositAssetInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepDlRepoInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;


/**
 * 标的类金融产品DTO
 * @author zhangnian
 * @version 1.0 2018年9月6日
 * @see FinProduct
 * @since 1.0
 */
public class FinStockInfoDto extends FinProduct{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;
    
    private String elementId;

    private List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList;


    /**
     * 上市时间
     */
    private String customerName;
    /**
     * 是否沪港通或深港通
     */

    /**
     * 是否融资融券标的
     */

    /**
     * 币种
     */
     private String board;
    private String shOrZh;
    private String isMarginFinprod;

    /**
     *
     */
    private List<SysUploadFileInfo> fileArray;

    /**
     * 存款类资产报送信息
     */
    private BaseRepDespositAssetInfoDto baseRepDespositAssetInfoDto;
    
    /**
     * 拆放同业及买入返售报送信息
     */
    private BaseRepDlRepoInfoDto baseRepDlRepoInfoDto;
    /**
     * 权益类资产中债报送
     */
    private BaseRepAssetEquityInfoDto baseRepAssetEquityInfoDto;
    
    /**
     * 负债报送信息
     */
    private BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto;
    
    private MstFileInputParameter mstFileInputParameter;
    
    /**
     * 资产一级分类
     */
    private String type6;
    
    /**
     * 资产二级分类
     */
    private String type7;
    
    /**
     * 日历名称
     */
    private String calendarName;
    
    /**
     * 交易对手类型---分行、通道
     */
    private String counterType;
    
    /**
     * 是否交易日历    
     */
    private String isTradeCalendar;
    /**
     * 资产提供行
     */
    private String assetProvider;
    
    /**
     * 资产提供行名称
     */
    private String assetProviderName;
    
    /**
     * 存款机构名
     */
    private String issuerName;
    
    /**
     * 资产推荐费率
     */
    private BigDecimal referRate;
    
    /**
     * 利率
     */
    private BigDecimal rate;
    
    /**
     * 计息要素
     */
    private FinInterestFactorDto finInterestFactorDto;
    /**
     *金融产品分类表Entity
     */
    private FinProductType finProductType;
    /**
     * 金融产品扩展Entity
     */
    private PaFinProduct paFinProduct;
    /**
     * 授信额度代码
     */
    private String seqno;

    /**
     * 授信配置代码
     */
    private String creditMstId;

    /**
     * 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     */
    private String customerId;

    /**
     * 批复流水号
     */
    private String pflsh;
    /**
     * 已占用额度，初始值为0
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal hapCreditAmt;

    /**
     * 实际授信主体
     */
    private String actualCreditProvid;
    /**
     * 是否占用主体授信
     */
    private String creditFlag;
    // 增加 二级，三级，四级，五级分类by赵东东 2019.10.31 end
    /**
     * 二级分类
     */
    private String type9;
    /**
     * 三级分类
     */
    private String type10;
    /**
     * 四级分类
     */
    private String type11;
    /**
     * 五级分类
     */
    private String type12;
    /**
     * 获取type9
     * @return
     */
    public String getType9() {
		return type9;
	}
     /**
      * 设置type9
      * @param type9
      */
	public void setType9(String type9) {
		this.type9 = type9;
	}
     /**
      * 获取type10
      * @return
      */
	public String getType10() {
		return type10;
	}
     /**
      * 设置type10
      * @param type10
      */
	public void setType10(String type10) {
		this.type10 = type10;
	}
     /**
      * 获取type11
      * @return
      */
	public String getType11() {
		return type11;
	}
     /**
      * 设置type11
      * @param type11
      */
	public void setType11(String type11) {
		this.type11 = type11;
	}
     /**
      * 获取type12
      * @return
      */
	public String getType12() {
		return type12;
	}
     /**
      * 设置type12
      * @param type12
      */
	public void setType12(String type12) {
		this.type12 = type12;
	}
	// 增加 二级，三级，四级，五级分类by赵东东 2019.10.31 end

    public BaseRepAssetEquityInfoDto getBaseRepAssetEquityInfoDto() {
        return baseRepAssetEquityInfoDto;
    }

    public void setBaseRepAssetEquityInfoDto(BaseRepAssetEquityInfoDto baseRepAssetEquityInfoDto) {
        this.baseRepAssetEquityInfoDto = baseRepAssetEquityInfoDto;
    }

    /**
     * 获取 creditFlag
     * @return creditFlag.
     */
    public String getCreditFlag() {
        return creditFlag;
    }

    
    /**
     * 设置 creditFlag
     * @param creditFlag creditFlag
     */
    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag;
    }

    /**
     * 选中的记录
     */
    private transient List<FinStockInfoDto> selectedItem;

    /**
     * 当日流通总股本
     */
    private String sShares;
    /**
     * 当日流通股本
     */
    private String cShares;
    


    /**
     * 获取 paFinProduct
     * @return paFinProduct.
     */
    public PaFinProduct getPaFinProduct() {
        return paFinProduct;
    }




    /**
     * 设置 paFinProduct
     * @param paFinProduct paFinProduct
     */
    public void setPaFinProduct(PaFinProduct paFinProduct) {
        this.paFinProduct = paFinProduct;
    }



    /**
     * 获取 finInterestFactorDto
     * @return finInterestFactorDto.
     */
    public FinInterestFactorDto getFinInterestFactorDto() {
        return finInterestFactorDto;
    }



    /**
     * 设置 finInterestFactorDto
     * @param finInterestFactorDto finInterestFactorDto
     */
    public void setFinInterestFactorDto(FinInterestFactorDto finInterestFactorDto) {
        this.finInterestFactorDto = finInterestFactorDto;
    }


    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    
    /**
     * 获取 baseFinProductBaseInfoList
     * @return baseFinProductBaseInfoList.
     */
    public List<BaseFinProductBaseInfoDto> getBaseFinProductBaseInfoList() {
        return baseFinProductBaseInfoList;
    }

    
    /**
     * 设置 baseFinProductBaseInfoList
     * @param baseFinProductBaseInfoList baseFinProductBaseInfoList
     */
    public void setBaseFinProductBaseInfoList(List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList) {
        this.baseFinProductBaseInfoList = baseFinProductBaseInfoList;
    }

    
    /**
     * 获取 mstFileInputParameter
     * @return mstFileInputParameter.
     */
    public MstFileInputParameter getMstFileInputParameter() {
        return mstFileInputParameter;
    }

    
    /**
     * 设置 mstFileInputParameter
     * @param mstFileInputParameter mstFileInputParameter
     */
    public void setMstFileInputParameter(MstFileInputParameter mstFileInputParameter) {
        this.mstFileInputParameter = mstFileInputParameter;
    }

    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }

    /**
     * 获取 type6
     * @return type6.
     */
    public String getType6() {
        return type6;
    }

    
    /**
     * 设置 type6
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    
    /**
     * 获取 type7
     * @return type7.
     */
    public String getType7() {
        return type7;
    }

    
    /**
     * 设置 type7
     * @param type7 type7
     */
    public void setType7(String type7) {
        this.type7 = type7;
    }

    
    /**
     * 获取 calendarName
     * @return calendarName.
     */
    public String getCalendarName() {
        return calendarName;
    }

    
    /**
     * 设置 calendarName
     * @param calendarName calendarName
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    
    /**
     * 获取 counterType
     * @return counterType.
     */
    public String getCounterType() {
        return counterType;
    }

    
    /**
     * 设置 counterType
     * @param counterType counterType
     */
    public void setCounterType(String counterType) {
        this.counterType = counterType;
    }

    
    /**
     * 获取 isTradeCalendar
     * @return isTradeCalendar.
     */
    public String getIsTradeCalendar() {
        return isTradeCalendar;
    }

    
    /**
     * 设置 isTradeCalendar
     * @param isTradeCalendar isTradeCalendar
     */
    public void setIsTradeCalendar(String isTradeCalendar) {
        this.isTradeCalendar = isTradeCalendar;
    }

    
    /**
     * 获取 assetProvider
     * @return assetProvider.
     */
    public String getAssetProvider() {
        return assetProvider;
    }

    
    /**
     * 设置 assetProvider
     * @param assetProvider assetProvider
     */
    public void setAssetProvider(String assetProvider) {
        this.assetProvider = assetProvider;
    }

    
    /**
     * 获取 assetProviderName
     * @return assetProviderName.
     */
    public String getAssetProviderName() {
        return assetProviderName;
    }

    
    /**
     * 设置 assetProviderName
     * @param assetProviderName assetProviderName
     */
    public void setAssetProviderName(String assetProviderName) {
        this.assetProviderName = assetProviderName;
    }

    
    /**
     * 获取 issuerName
     * @return issuerName.
     */
    public String getIssuerName() {
        return issuerName;
    }

    
    /**
     * 设置 issuerName
     * @param issuerName issuerName
     */
    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    
    /**
     * 获取 finProductType
     * @return finProductType.
     */
    public FinProductType getFinProductType() {
        return finProductType;
    }

    
    /**
     * 设置 finProductType
     * @param finProductType finProductType
     */
    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }

    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<FinStockInfoDto> getSelectedItem() {
        return selectedItem;
    }

    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinStockInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    
    /**
     * 获取 baseRepDespositAssetInfoDto
     * @return baseRepDespositAssetInfoDto.
     */
    public BaseRepDespositAssetInfoDto getBaseRepDespositAssetInfoDto() {
        return baseRepDespositAssetInfoDto;
    }


    
    /**
     * 设置 baseRepDespositAssetInfoDto
     * @param baseRepDespositAssetInfoDto baseRepDespositAssetInfoDto
     */
    public void setBaseRepDespositAssetInfoDto(BaseRepDespositAssetInfoDto baseRepDespositAssetInfoDto) {
        this.baseRepDespositAssetInfoDto = baseRepDespositAssetInfoDto;
    }


    
    /**
     * 获取 baseRepAssetDeptInfoDto
     * @return baseRepAssetDeptInfoDto.
     */
    public BaseRepAssetDeptInfoDto getBaseRepAssetDeptInfoDto() {
        return baseRepAssetDeptInfoDto;
    }


    
    /**
     * 设置 baseRepAssetDeptInfoDto
     * @param baseRepAssetDeptInfoDto baseRepAssetDeptInfoDto
     */
    public void setBaseRepAssetDeptInfoDto(BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto) {
        this.baseRepAssetDeptInfoDto = baseRepAssetDeptInfoDto;
    }




    
    /**
     * 获取 referRate
     * @return referRate.
     */
    public BigDecimal getReferRate() {
        return referRate;
    }




    
    /**
     * 设置 referRate
     * @param referRate referRate
     */
    public void setReferRate(BigDecimal referRate) {
        this.referRate = referRate;
    }




    
    /**
     * 获取 baseRepDlRepoInfoDto
     * @return baseRepDlRepoInfoDto.
     */
    public BaseRepDlRepoInfoDto getBaseRepDlRepoInfoDto() {
        return baseRepDlRepoInfoDto;
    }




    
    /**
     * 设置 baseRepDlRepoInfoDto
     * @param baseRepDlRepoInfoDto baseRepDlRepoInfoDto
     */
    public void setBaseRepDlRepoInfoDto(BaseRepDlRepoInfoDto baseRepDlRepoInfoDto) {
        this.baseRepDlRepoInfoDto = baseRepDlRepoInfoDto;
    }




    
    /**
     * 获取 rate
     * @return rate.
     */
    public BigDecimal getRate() {
        return rate;
    }




    
    /**
     * 设置 rate
     * @param rate rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }




    
    /**
     * 获取 seqno
     * @return seqno.
     */
    public String getSeqno() {
        return seqno;
    }




    
    /**
     * 设置 seqno
     * @param seqno seqno
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }




    
    /**
     * 获取 creditMstId
     * @return creditMstId.
     */
    public String getCreditMstId() {
        return creditMstId;
    }




    
    /**
     * 设置 creditMstId
     * @param creditMstId creditMstId
     */
    public void setCreditMstId(String creditMstId) {
        this.creditMstId = creditMstId;
    }




    
    /**
     * 获取 customerId
     * @return customerId.
     */
    public String getCustomerId() {
        return customerId;
    }




    
    /**
     * 设置 customerId
     * @param customerId customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }




    
    /**
     * 获取 pflsh
     * @return pflsh.
     */
    public String getPflsh() {
        return pflsh;
    }




    
    /**
     * 设置 pflsh
     * @param pflsh pflsh
     */
    public void setPflsh(String pflsh) {
        this.pflsh = pflsh;
    }




    
    /**
     * 获取 hapCreditAmt
     * @return hapCreditAmt.
     */
    public BigDecimal getHapCreditAmt() {
        return hapCreditAmt;
    }




    
    /**
     * 设置 hapCreditAmt
     * @param hapCreditAmt hapCreditAmt
     */
    public void setHapCreditAmt(BigDecimal hapCreditAmt) {
        this.hapCreditAmt = hapCreditAmt;
    }




    
    /**
     * 获取 actualCreditProvid
     * @return actualCreditProvid.
     */
    public String getActualCreditProvid() {
        return actualCreditProvid;
    }




    
    /**
     * 设置 actualCreditProvid
     * @param actualCreditProvid actualCreditProvid
     */
    public void setActualCreditProvid(String actualCreditProvid) {
        this.actualCreditProvid = actualCreditProvid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }



    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getShOrZh() {
        return shOrZh;
    }

    public void setShOrZh(String shOrZh) {
        this.shOrZh = shOrZh;
    }

    public String getIsMarginFinprod() {
        return isMarginFinprod;
    }

    public void setIsMarginFinprod(String isMarginFinprod) {
        this.isMarginFinprod = isMarginFinprod;
    }

    public String getsShares() {
        return sShares;
    }

    public void setsShares(String sShares) {
        this.sShares = sShares;
    }

    public String getcShares() {
        return cShares;
    }

    public void setcShares(String cShares) {
        this.cShares = cShares;
    }
}
